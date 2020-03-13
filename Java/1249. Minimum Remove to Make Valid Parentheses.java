M
tags: String, Stack
time: O(n)
space: O(n)

#### Stack
- Goal: remove extra '(' or ')' so it is valid.
- Forward thinking: use stack to track '(' and ')', then keep appending partial string to output
- Backward thinking: use stack to filter out false indexes, and remove them in the end


```
/*
Given a string s of '(' , ')' and lowercase English characters. 

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"
 

Constraints:

1 <= s.length <= 10^5
s[i] is one of  '(' , ')' and lowercase English letters.

*/


/*
Goal: remove extra ( or ) so it is valid.
Forward thinking: use stack to track ( and ), then keep appending partial string to final result
Backward thinking: use stack to filter out false indexes, and remove them in the end
*/
class Solution {
    public String minRemoveToMakeValid(String s) {

        Set<Integer> set = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') stack.push(i);
            if (c == ')') {
                if (!stack.isEmpty()) stack.pop();
                else set.add(i); // given ')' but missing '(', record ')' as false index
            }
        }
        // remaining false indexes of '('
        while (!stack.isEmpty()) set.add(stack.pop());
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(i)) sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}

```