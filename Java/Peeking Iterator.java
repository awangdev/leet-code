M
tags: BST

再一次理解错题意. peek() 就是头顶，但是不一定是最大值啊。总是把PEEK想成了最大值，然后用2 STACK做了最大值的cache，练的一手好双stack，可惜错了。

回到原题，其实不难。找一个cache来存next()的值，然后每次next()里面维护这个cache就好。

```
/*
Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

Call next() gets you 1, the first element in the list.

Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

Hint:

Think of "looking ahead". You want to cache the next element.
Is one variable sufficient? Why or why not?
Test your design with call order of peek() before next() vs next() before peek().
For a clean implementation, check out Google's guava library source code. (https://github.com/google/guava/blob/703ef758b8621cfbab16814f01ddcc5324bdea33/guava-gwt/src-super/com/google/common/collect/super/com/google/common/collect/Iterators.java#L1125)
Follow up: How would you extend your design to be generic and work with all types, not just integer?

It looks like the guava library uses 'E' for generic element

Tags: Design
Similar Problems: (M) Binary Search Tree Iterator, (M) Flatten 2D Vector, (M) Zigzag Iterator

*/

/*
Second attempt.
Thoughts: Of coruse can't store in a queue, that will be brutle and meaning less.
Instead, use a iterator variable, cache, to hold next().
When called next(), move forward; otherwise, return the cache.
Make sure also return the cached peek, and update cache with next() value.
*/
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
	private int cache;
	private Iterator<Integer> itt;
	private boolean notEnd;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    itt = iterator;
	    cache = itt.next();
	    notEnd = iterator.hasNext();
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return cache;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		int curr = cache;
		notEnd = itt.hasNext();
		if (itt.hasNext()) {
			cache = itt.next();
		}
	    return curr;
	}

	@Override
	public boolean hasNext() {
	    return notEnd;
	}
}




/*
Attempt1, failed. Reason: I thought we are looking for the real max-peek element! However, this problem only asks for peek() element, which is not necessarily the maximun element. This mistake is bloody.
Thoughts:
To find peek, have to run through the iterator at least once. O(n).
Store everything in 2 stacks:
We want to process the end of the iterator first, put everything into stack.
Therefore the top of the stack is the next() element of iterator.
Also, use second stack to hold max value for each element stage.

Each stack1 element has a max coresponding element in stack2. For example, [5,9,1,3,6]
s1: 6,3,1,9,5				[5 gets out first]
s2: 6,6,6,9,9				[end 9 gets out first]
*/

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
	private Stack<Integer> s1;
	private Stack<Integer> s2;
	private int size;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    s1 = new Stack<Integer>();
	    s2 = new Stack<Integer>();
	    Stack<Integer> temp = new Stack<Integer>();
	    size =  0;
	    int max = Integer.MIN_VALUE;
	    while(iterator.hasNext()) {
	    	temp.push(iterator.next());
	    	size++;
	    }
	    while(!temp.empty()) {
	    	s1.push(temp.peek());
	    	max = Math.max(max, temp.peek());
	    	s2.push(max);
	    	temp.pop();
	    }
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		if (s1.size() > size) {
			s1.pop();
			return s2.pop();
		} else {
			return s2.peek();
		}
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	
	@Override
	public Integer next() {
		size--;
	    return s1.pop();
	}

	@Override
	public boolean hasNext() {
	    return !s1.empty();
	}
}
```