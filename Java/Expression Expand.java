M
1520800825
tags: Divide and Conquer, Stack, DFS


#### DFS
- 与Stack时需要考虑的一些function类似. 特别之处: **检查[ ]的结尾**
- 因为DFS时候, 括号里的substring会被保留着进入下一个level, 所以我们在base level要keep track of substring.
- 用int paren 来track 括号的开合, 当paren再次==0的时候 找到closure ']'

#### Stack
- Stack存 [ ] 里面的内容, detect 括号开头结尾: 结尾时process inner string
- 有很多需要注意的细节才能做对:
- Stack<Object> 也可以用, 每个地方要注意 cast. 存进去的需要是Object: String, Integer
- 几个 type check: instanceof String, Character.isDigit(x), Integer.valueOf(int num)
- 出结果时候, 不能轻易 sb.reverse().toString(): sb.reverse() 翻转了整个连在一起的string, 错.
- 用另一个Stack<String>作为buffer, 先把stack里面的内容倒出来 (pure), 但是每个item里面顺序不变.
- 最后再从buffer里面倒进StringBuffer.


```
/*
Given an expression s includes numbers, letters and brackets. 
Number represents the number of repetitions inside the brackets(can be a string or another expression)．
Please expand expression to be a string.
*/

/*
Thoughts:
Can DFS. Also, use stack to hold all inner bucket.
In the original string, block of string is separated by #[ ].
Can use stack to hold 2 piece of information:
1. # where the str repeats, and it also marks the start of the string
2. Store each char of the string itself
3. Once flatten the inner str, repeat the str # times and add back to stack

As we iterate over the string
- detect '[' to determine the time to add start marker #.
- detect ']' when it's time to retrieve the # and str, save results

Important: use Stack to hold generic Object!
*/
public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */

    public String expressionExpand(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Stack<Object> stack = new Stack<>();
        int number = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '[') {
                stack.push(Integer.valueOf(number));
                number = 0;
            } else if (c == ']') {
                String str = popStack(stack);
                Integer num = (Integer) stack.pop();
                for (int i = 0; i < num; i++) {
                    stack.push(str);
                }
            } else {
                stack.push(String.valueOf(c));
            }
        }

        return popStack(stack);
    }
    
    private String popStack(Stack<Object> stack) {
        StringBuffer sb = new StringBuffer();
        Stack<String> buffer = new Stack<>();
        while (!stack.isEmpty() && (stack.peek() instanceof String)) {
            buffer.push((String) stack.pop());
        }
        
        while (!buffer.isEmpty()) {
            sb.append(buffer.pop());
        }
        return sb.toString();
    }
}


/*
Thoughts:
DFS, process inner string each time.
Use "#[" as detection.
Important:
1. Finding the closure of ']' is tricky: need to track the parentheses
2. Until we find the closure ']', keep track of the un-flatten substring for dfs to use
*/
public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        String substring = "";
        int number = 0;
        int paren = 0; // parentheses tracker
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                if (paren > 0) { // if paren == 0, it indicates the outside 1st '[', no need to record
                    substring += c;
                }
                paren++;
            } else if (c == ']') {
                paren--;
                if (paren == 0) {
                    String innerString = expressionExpand(substring);
                    for (int num = 0; num < number; num++) {
                        sb.append(innerString);
                    }
                    number = 0;
                    substring = "";
                } else {
                    substring += c;
                }
            } else if (Character.isDigit(c)) {
                if (paren == 0) {
                    number = number * 10 + (c - '0');    
                } else {
                    substring += c;
                }
            } else  {
                if (paren == 0) {
                    sb.append(String.valueOf(c));    
                } else {
                    substring += c;
                }
            }
        }
        return sb.toString();
    }
}
```