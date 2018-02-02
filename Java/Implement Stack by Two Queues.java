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