M
1531693483
tags: String, Stack
time: O(n)
space: O(n)

给一个path, simplify成最简单形式. 注意考虑edge case

#### Stack
- 理解unix path 的情况, 不懂得要问: 
- 1. `.` 代表current directory, 可以忽略. 
- 2. `../` 表示previous level. 
- 3. double slash 可以忽略.
- 4. empty string 要output `/`
- 最终就是用stack (`上一个加进去的item, 用来备选pop() out`), 遇到 `../` pop()掉上一个加上去的item, 其余加进stack
- 最终用 '/' 把所有item连接起来.

```
/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Corner Cases:

Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".

*/

class Solution {
    public String simplifyPath(String path) {
        if (validate(path)) return path;
        
        // build stack, may skip
        Stack<String> stack = new Stack<>();
        String[] parts = path.split("/");
        for (String s : parts) {
            if (s.isEmpty() || s.equals(".")) continue;
            if (s.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
                continue;
            }
            stack.push(s);
        }
        
        // build output
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.insert(0, "/" + stack.pop());
        }
        if (sb.length() == 0) sb.append("/");

        return sb.toString();
    }
    
    private boolean validate(String path) { // '../' case in begining
        if (path == null || path.length() == 0) return true;
        if (path.charAt(0) != '/') return true;
        return false;
    }
}


```