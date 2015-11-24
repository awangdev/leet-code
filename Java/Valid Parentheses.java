剥皮形态。
左边的外皮在stack底部。
右边的外皮应该和最顶上的左外皮一一对应。
```
/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

Example
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Tags Expand 
Stack
*/

/*
	Thoughts:
	Did this on Leetcode. Think about how do we naturally check it? 
	Use stack to hold '({[', and check against peek() every time on next element.
	Check it nagativly. If all pass, return true;

	Note:
	If stack is not cleanup, then it's false.
*/
public class Solution {
    /**
     * @param s A string
     * @return whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
    	if (s == null || s.length() < 2) {
    		return false;
    	}
    	Stack<Character> stack = new Stack<Character>();
    	char[] arr = s.toCharArray();
    	for (int i = 0; i < arr.length; i++) {
    		if (arr[i] == '(' || arr[i] == '[' || arr[i] == '{') {
    			stack.push(arr[i]);
    		} else if (arr[i] == ')' || arr[i] == ']' || arr[i] == '}') {
    			if (stack.isEmpty()) {
    				return false;
    			} else {
    				char c = stack.pop();
    				if ((c == '(' && arr[i] == ')') ||
    					(c == '[' && arr[i] == ']') ||
    					(c == '{' && arr[i] == '}')) {
    					continue;
    				}
    				return false;
    			}
    		} else {
    			return false;
    		}
    	}
    	return stack.isEmpty();
    }
}

```