E

两个Queue,交互倒水
用一个Temp做swap

做法1:
逻辑在top()/pop()里, 每次换水，查看末尾项.

做法2:
逻辑在push里面:
1. x 放q2。
2. q1全部offer/append到q2.
3. 用一个Temp做swap q1, q2.
q1的头，就一直是最后加进去的值.

```
/*
LeetCode:
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and all test cases.
*/
/*
Thoughts:
1. When top()/pop() on the queue, we need to consume all the items on that queue first, then return the last item.
2. Need to save the consumed items back to the queue.
*/
class MyStack {
    private Queue<Integer> queue;
    private Queue<Integer> tempQueue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
        tempQueue = new LinkedList<>();
    }
    
    /** Find the top and backfill queue with all consumed items */
    private int findTop() {
        while (queue.size() > 1) {
            tempQueue.offer(queue.poll());
        }
        int num = queue.poll();
        queue = tempQueue;
        tempQueue = new LinkedList<>();
        return num;
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return findTop();
    }
    
    /** Get the top element. */
    public int top() {
        int num = findTop();
        queue.offer(num);
        return num;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */


/*
LintCode:
Implement Stack by Two Queues

Implement a stack by two queues. The queue is first in first out (FIFO). 
That means you can not directly pop the last element in a queue.

Have you met this question in a real interview? Yes
Example
push(1)
pop()
push(2)
isEmpty() // return false
top() // return 2
pop()
isEmpty() // return true
Tags Expand 
Stack Queue

*/

/*
	Thoughts:
	2 queue are like two cups. We are fliping water into/out between q1 and q2.
	pop and top are fliping water.
	Use p1 as the base.
*/

class Stack {
	private Queue<Integer> q1 = new LinkedList<Integer>();
	private Queue<Integer> q2 = new LinkedList<Integer>();
    // Push a new item into the stack
    public void push(int x) {
        q1.offer(x);
    }

    // Pop the top of the stack
    public void pop() {
       	while (q1.size() > 1) {
       		q2.offer(q1.poll());
       	}
       	q1.poll();
       	swap();
    }

    // Return the top of the stack
    public int top() {
       	while (q1.size() > 1) {
       		q2.offer(q1.poll());
       	}
       	int rst = q1.poll();
       	q2.offer(rst);
       	swap();
       	return rst;
    }

    public void swap(){
		Queue<Integer> temp = q1;
       	q1 = q2;
       	q2 = temp;
    }

    // Check the stack is empty or not.
    public boolean isEmpty() {
        return q1.isEmpty();
    }    
}
```