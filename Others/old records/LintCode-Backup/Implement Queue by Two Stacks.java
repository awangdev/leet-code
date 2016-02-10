/*
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

