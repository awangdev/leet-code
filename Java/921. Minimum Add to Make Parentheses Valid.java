M
tags:
time: O(n)
space: O(1)


#### Method1: Stack
- use stack to verify the input/output of '(' and ')'
- time, space: O(n)

#### Method1: Simpilfy stack with open parentheses count
- time:(n), space: O(1)

```
/*
Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.

 

Example 1:

Input: "())"
Output: 1
Example 2:

Input: "((("
Output: 3
Example 3:

Input: "()"
Output: 0
Example 4:

Input: "()))(("
Output: 4
 

Note:

S.length <= 1000
S only consists of '(' and ')' characters.
*/

// Method1: stack
class Solution {
    public int minAddToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(c);
            else if (stack.isEmpty()) count++;
            else stack.pop();
        }
        return count + stack.size();
    }
}

// Method: simplify with openCount: open parentheses count
class Solution {
    public int minAddToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0, openCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') openCount++;
            else if (openCount == 0) count++;
            else openCount--;
        }
        return count + openCount;
    }
}
```