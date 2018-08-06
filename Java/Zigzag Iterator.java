M
tags: BST

这个题目相对简单. 做的时候我先考虑起来k条怎么办. 那么用个map把index和每个listmark一下就好了。
每次next(), 相应的list的头拿下来就好。
然后就跑圈呗，每次刷一个list头。不难。只要把几个variable维护清楚就行。
```
/*
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. 
If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". 
For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].

Tags: Design
Similar Problems: (M) Binary Search Tree Iterator, (M) Flatten 2D Vector, (M) Peeking Iterator

*/

/*
Thoughts:
HashMap<index, different list>
alter the index to pick correct list
maintain a total size variable.
hashNext: size > 0?
next(): while(map.get(index).length() == 0) : find next list.
return list.get(0), also list.remove(0)

Note: be careful with all the size, length, index. size does not change once fixed. Remember in next(): index++ and length--;
*/
public class ZigzagIterator {
	private HashMap<Integer, List<Integer>> map;
    private int length = 0;
    private int size = 0;
    private int index = 0;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        map = new HashMap<Integer, List<Integer>>();
        if (v1 != null && v1.size() > 0) {
        	map.put(size, v1);
        	length += v1.size();
        	size++;
        }
        if (v2 != null && v2.size() > 0) {
        	map.put(size, v2);
        	length += v2.size();
        	size++;
        }
        if (length == 0) {
        	return;
        }
    }

    public int next() {
        while(map.get(index).size() == 0) {
        	index++;
        	if (index == size) {
        		index = 0;
        	}
        }
        int rst = map.get(index).get(0);
        map.get(index).remove(0);
        length--;
        index++;
        if (index == size) {
        	index = 0;
        }
        return rst;
    }

    public boolean hasNext() {
        return length > 0;
    }	
}


/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
```