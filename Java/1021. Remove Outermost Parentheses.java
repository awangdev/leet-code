E
tags: Stack

#### Stack
- use stack to hold potential pair
- when stack is empty: detect outtermost element, dont add to final result
- time: O(n), space O(n)

#### Count occurance
- solution from discussion, time O(n), space O(1)
- save space, but less scalable: think about if there are 100 different pairs, then the couting will be a bit complex to handle.

```
/*
A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.

A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.

Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.

Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.

 

Example 1:

Input: "(()())(())"
Output: "()()()"
Explanation: 
The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
Example 2:

Input: "(()())(())(()(()))"
Output: "()()()()(())"
Explanation: 
The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
Example 3:

Input: "()()"
Output: ""
Explanation: 
The input string is "()()", with primitive decomposition "()" + "()".
After removing outer parentheses of each part, this is "" + "" = "".
 

Note:

S.length <= 10000
S[i] is "(" or ")"
S is a valid parentheses string
 
 */

class Solution {
    public String removeOuterParentheses(String S) {
        if (S == null || S.length() == 0) return S;
        
        Stack<Character> stack = new Stack<>();
        StringBuffer sb = new StringBuffer();
        
        for (char c : S.toCharArray()) {
            if (stack.isEmpty()) { // detect outter most element
                stack.push(c);
            } else {
                if (isPair(stack.peek(), c)) { // handle pair
                    stack.pop();
                    if (!stack.isEmpty()) {
                        sb.append(c);
                    }
                } else {// handle single
                    stack.push(c);
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
    
    private boolean isPair(char a, char b) {
        return a == '(' && b ==')';
    }
}

// count 
class Solution {
    public String removeOuterParentheses(String S) {
        StringBuilder s = new StringBuilder();
        int count = 0;
        for (char c : S.toCharArray()) {
            if (c == '(' && count++ > 0) s.append(c);
            if (c == ')' && count-- > 1) s.append(c);
        }
        return s.toString();
    }
}
```