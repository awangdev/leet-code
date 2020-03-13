M
tags: Greedy, Stack, Monotonous Stack
time: O(n)
space: O(n)

#### Monotonous Stack (Increasing)
- Greedy: Remove 1) earlier digits(数位靠前权值大), 2) large digits
- Keep a increasing stack that:
    - use stack.peek() to guard incoming digit
    - if peek is larger than incoming digit, continue `stack.pop()`
- Result: monotonous increasing stack. Print it in correct order.


```
/*
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
*/
/*
Monotonous Stack (Increasing): 
- Remove 1) early, 2) large digits
- Keep a increasing stack that:
    - use stack.peek() to guard incoming digit
    - if peek is larger than incoming digit, continue `stack.pop()`
- Result: monotonous increasing stack. Print it in correct order.
*/
class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        
        // Monotonous Stack
        int i = 0, n = num.length();
        while (i < n) {
            char c = num.charAt(i++);
            while (k > 0 && !stack.isEmpty() && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        
        // handle coner case when all digits are equal: 33333, that k never decreases
        while (k-- > 0) stack.pop();
        
        // Output:
        StringBuffer sb = new StringBuffer();
        while(!stack.isEmpty()) sb.append(stack.pop());
        sb.reverse();
        while(sb.length() > 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

```