双Stack. 小心stack.peek() 而不是 pop
```
/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Tags: Stack, Design
Similar Problems: (H) Sliding Window Maximum


Thoughts:
Use a regular Stack: linked list.
Save that minimum integer in a HashMap with each stack value. At each level of the stack, it always stores the min till that moment.
Use another stack to hold that 'up-to-date' min values.

Note:
Stack: peek()

*/

class MinStack {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> min = new Stack<Integer>();
    public void push(int x) {
        stack.push(x);
        if (min.isEmpty() || x < min.peek()){
            min.push(x);
        } else {
            min.push(min.peek());
        }
    }

    public void pop() {
        stack.pop();
        min.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }
}


```