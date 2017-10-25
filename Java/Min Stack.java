E

双Stack：一个正常stack，另一个minStack存当下level最小值. 注意维护minStack的变化

另外. 如果要maxStack，也是类似做法

```
/*
Implement a stack with min() function, which will return the smallest number in the stack.

It should support push, pop and min operation all in O(1) cost.

Example
push(1)
pop()   // return 1
push(2)
push(3)
min()   // return 2
push(1)
min()   // return 1
Note
min operation will never be called if there is no number in the stack.

Tags Expand 
Stack

Thoughts:
using 2 stacks: one regular, the other one trackes min element
MinStack (0 ~ i): for i elements in regular stack, at each ith, the min element is stored at MinStack(i). This means, there can be duplicated mins for different ith.

Note: remember to check if minStack isEmpty(), empty stack does not have peek()
*/

public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int number) {
        stack.push(number);
        if (minStack.isEmpty()) {
            minStack.push(number);
        } else {
            minStack.push(minStack.peek() >= number ? number : minStack.peek());
        }
    }

    public int pop() {
        minStack.pop();
        return stack.pop();
    }

    public int min() {
        return minStack.peek();
    }
}

/*
    Regular stack and minStack.
    MinStack keeps the minnimum value at current time (there can be duplicates if min value does not change)
*/
class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            if (minStack.peek() >= x) {
                minStack.push(x);
            } else {
                minStack.push(minStack.peek());
            }
        }
    }
    
    public void pop() {
        minStack.pop();
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


```