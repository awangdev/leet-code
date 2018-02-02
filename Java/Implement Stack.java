E

stack 后入先出. 
Data Structure: ArrayList 
return/remove ArrayList的末尾项。

```
/*
 Implement Stack

Implement a stack. You can use any data structure inside a stack except stack itself to implement it.


Example
push(1)
pop()
push(2)
top()  // return 2
pop()
isEmpty() // return true
push(3)
isEmpty() // return false
Tags Expand 
Array Stack
*/

/*
Thoughts:
use arraylist and a index tracker - leng
push: add to end
pop: remove end
top: get end.
isEmpty: return length
*/

class Stack {
	private ArrayList<Integer> list = new ArrayList<Integer>();
    // Push a new item into the stack
    public void push(int x) {
    	list.add(x);
    }

    // Pop the top of the stack
    public void pop() {
    	if (list.size() > 0) {
    		list.remove(list.size() - 1);
    	}
    }

    // Return the top of the stack
    public int top() {
    	if (list.size() > 0) { 
    		return list.get(list.size() - 1);
    	}
        return -1;
    }

    // Check the stack is empty or not.
    public boolean isEmpty() {
        return list.size() == 0;
    }    
}
```