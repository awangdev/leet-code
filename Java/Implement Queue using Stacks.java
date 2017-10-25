E

双Stack. 一个是等于是queue，一个是backfillStack.
Tricky: 是在pop()和peek()的时候backfill, 并且要等到stack用完再backfill.
写一下例子就知道，如果提早backfill，stack.peek()就不是queue的head了.


```
/*
LeetCode:
Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/

/*
Thoughts:
Using two stack.
Stack1 holds the correct representation of the queue: first added item appears on top of stack.
Stack2 used to insert new item, which will be like a inverse queue.
Note: Only backfill stack2 into stack1, when stack1.isEmpty() during queue.pop/queue.peek: only backfilling when regular queue is drained.
*/
class MyQueue {
    private Stack<Integer> stack;
    private Stack<Integer> backfillStack;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
        backfillStack = new Stack<>();
    }
    
    private void backfill() {
        while (!backfillStack.isEmpty()) {
            stack.push(backfillStack.pop());
        }
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        backfillStack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stack.isEmpty()) {
            backfill();
        }
        return stack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (stack.isEmpty()) {
            backfill();
        }
        return stack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty() && backfillStack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */


/*
LintCode: Implement Queue by Two Stacks
As the title described, you should only use two stacks to implement a queue's actions.

The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

Both pop and top methods should return the value of first element.

Example
For push(1), pop(), push(2), push(3), top(), pop(), you should return 1, 2 and 2

Challenge
implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE.

Thoughts:
1. Push everything into stack2: whatever comes in last, will be on top.
2. Pop and Top: return stack1's top element. 
3. Initially, when stack1 is empty, need to reverse all stack2 and put into stack: like pouring water from cup stack2 into cup stack1.
    Or:when stack1 has been top() over, pour stack2 into stack1 again: the stack2's bottom becomes stack1's top, which is correct: returning the oldest element of queue (front of queue)

Tags Expand 
LintCode Copyright Stack Queue
*/


public class Solution {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public void pourS2ToS1(){
        while (!stack2.empty()) {
            stack1.push(stack2.peek());
            stack2.pop();
        }
    }
    public Solution() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();  
    }
    
    public void push(int element) {
        stack2.push(element);
    }

    public int pop() {
        if (stack1.empty()) {
            pourS2ToS1();
        }
        return stack1.pop();
    }

    public int top() {
        if (stack1.empty()) {
            pourS2ToS1();
        }
        return stack1.peek();
    }
}


```