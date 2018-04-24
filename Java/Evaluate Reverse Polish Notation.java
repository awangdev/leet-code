M
1524553446
tags: Stack

给一个 RPN string list, 根据这个list, 计算结果.

#### Stack
- stack 里面 存数字
- 每次遇到operator, 都拿前2个数字计算
- 计算结果存回到stack里面, 方便下一轮使用.
- Time,Space O(n)


```
/**
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. 
That means the expression would always evaluate to a result 
and there won't be any divide by zero operation.

Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 */


 class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<Long> stack = new Stack<>();
        for (String s : tokens) {
            if (isOperator(s)) {
                long numB = stack.pop();
                long numA = stack.pop();
                stack.push(eval(numA, numB, s));
            } else {
                stack.push(Long.parseLong(s));
            }
        }
        return stack.pop().intValue();
    }
    
    public boolean isOperator(String s) {
        if (s.length() != 1) {
            return false;
        }
        char c = s.charAt(0);
        return c == '+' || c == '-' || c == '/' || c == '*';
    }
    
    public long eval(long a, long b, String s) {
        long rst = 0;
    	switch (s) {
    		case "*":
    			rst = a * b;
    			break;
    		case "/":
    			rst = a / b;
    			break;
    		case "+":
    			rst = a + b;
    			break;
    		case "-":
    			rst = a - b;
    			break;
    	}
    	return rst;
    }
}
```