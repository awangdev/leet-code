M
tags: String, Stack
time: O(n)
space: O(n)

给一个path, simplify成最简单形式. 注意考虑edge case

#### Stack
- 理解unix path:
    - 1. `.` 代表current directory, 可以忽略. 
    - 2. `../` 表示previous level. 
    - 3. double slash 可以忽略.
    - 4. empty string 要output `/`
- parse by '/', and go over using stack
    - put [folder] in stack
    - ".." pop() 1 element of the stack, if anything
    - "." stays the same
- output stack reversely: connect with '/', skip tail

```
/*
Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix

Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.

 

Example 1:

Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.
Example 2:

Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
Example 3:

Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
Example 4:

Input: "/a/./b/../../c/"
Output: "/c"
Example 5:

Input: "/a/../../b/../c//.//"
Output: "/c"
Example 6:

Input: "/a//b////c/d//././/.."
Output: "/a/b/c"
*/
/*
- parse by '/', and go over using stack
    - put [folder] in stack
    - ".." pop() 1 element of the stack, if anything
    - "." stays the samee
- output stack reversely: connect with '/', skip tail /
*/

class Solution {
    public String simplifyPath(String path) {
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
        while (!stack.isEmpty()) sb.insert(0, "/" + stack.pop());
        if (sb.length() == 0) sb.append("/");

        return sb.toString();
    }
}

```