E
tags: String, Stack
time: O(n)
space: O(n)

#### Stack
- 剥皮过程。解铃还须系铃人   
- 左边的外皮'{['在stack底部   
- 右边的外皮应该和stack顶上的左外皮一一对应 

```
/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

Example
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Tags Expand 
Stack
*/

class Solution {
    public boolean isValid(String s) {
        if (s == null) return true;
        if (s.length() % 2 != 0) return false;

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty() || (c == '(' || c == '{' || c == '[')) stack.push(c);
            else if (validate(stack.peek(), c)) stack.pop();
            else return false;
        }
        return stack.isEmpty();
    }
    
    private boolean validate(char a, char b) {
        if (a == '(') return b == ')';
        if (a == '{') return b == '}';
        if (a == '[') return b == ']';
        return false;
    }
}


```