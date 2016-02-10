/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Tags: Stack, String
Similar Problems: (M) Generate Parentheses, (H) Longest Valid Parentheses
*/
/*
Attemp3: http://fisherlei.blogspot.com/2013/01/leetcode-valid-parentheses.html
Use stack.

If the incoming char is '(,{,[', add on top.
Otherwise, check incoming char against stack.peek(), and see if they are '),},]'. If not, false.
It's the natural process of checking the parentheses by looking at the most inner circle; well, easist is just to use a stack to hold the front part of parentheses.

Note: 
Stack: isEmpty(), empty()
Be careful with the case '[])' where stack could be empty, but we still try to peek() it.
*/
public class Solution {
    public boolean isValid(String s) {
        if (s == null || s.equals("()") || s.equals("{}") ||s.equals("[]")) {
            return true;
        }
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        stack.push(arr[0]);
        for (int i = 1; i < s.length(); i++) {
            if ("({[".indexOf(arr[i]+"") != -1) {
                stack.push(arr[i]);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            if (arr[i] == ')' && stack.peek() != '(') {
                return false;
            }
            if (arr[i] == '}' && stack.peek() != '{') {
                return false;
            }
            if (arr[i] == ']' && stack.peek() != '[') {
                return false;
            }
            stack.pop();
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}

/*
2nd attempt failed.
Recursive: when one block is correct, the stuff inside of them must be correct.
Incorrect: for some cases.
*/
public class Solution {
    public boolean isValid(String s) {
        if (s == null || s.equals("()") || s.equals("{}") ||s.equals("[]")) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        char c = s.charAt(0);
        int index = 0;
        switch (c) {
            case '(' : index = s.indexOf(')'); break;
            case '{' : index = s.indexOf('}'); break;
            case '[' : index = s.indexOf(']'); break;
            default: index = -1; break;     
        }

        if (index == -1) {
            return false;
        }

        boolean middle =  isValid(s.substring(1, index));
        boolean after = (index == s.length() - 1) ? true : isValid(s.substring(index));

        return middle && after;
    }
}

/*
First attemp, Thoughts:
break into char array.
Count against each char

However didnt consider :"([)]". failed
*/

public class Solution {
    public boolean isValid(String s) {
        if (s == null) {
        	return true;
        }
        int[] count = new int[3];
        for (char c : s.toCharArray()) {
        	switch (c) {
        		case '(' : count[0]++; break;
				case ')' : count[0]--; break;
				case '{' : count[1]++; break;
				case '}' : count[1]--; break;
				case '[' : count[2]++; break;
        		case ']' : count[2]--; break;		
        	}
        }
        return (count[0] + count[1] + count[2]) == 0;
    }
}