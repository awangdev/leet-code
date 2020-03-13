H
tags: DFS, BFS, DP

给一个string, 里面有括号和其他字符. 以最少刀 剪出 valid string, 求所有这样的string.

这个题目有多种解法, 最强就是O(n) space and time

#### DFS and reduce input string
- Goal: identify invalid parentheses and remove (minimum removals)
- Step:
    - Detect the incorrect parentheses by tracking/counting (similar to validation of the parentheses string): `if(count<0)`
    - When invalid occurs: 
        - chance for correction. Remove the incorrect parentheses, one at a time
        - dfs on the rest of the s that has not been tested yet: start index from index i
    - Core edge cases:
        - Do not correct twice of the same parenthesis by checking [j-1] pos
        - Make sure to attempt correction of all possible parenthesis within tested range: because it outputs all results at the same level
        - return/finish once correction done
- Success case: 
    - a string s passed test: make sure it passes REVERSED string test!
    - Core Concept: `if a parenthese string is valid, the reverse of it should also be valid`
    - Test s with open='(', close=')' first; **reverse s**, and test it with open=')', close='('
- Minor details
    - only procceed to remove invalid parenthese when `count<0`, and also break && return dfs after the recursive calls.
    - The above 2 facts eliminates all the redundant results.
    - **Reverse string** before alternating open and close parentheses, so when **returning final result, it will return the correct order**.
- How does it guarantee minimum removals?
    - When seeing a chance to correct, it jumps into a for loop of DFS. It `return` after the for loop. This stops additional testing
    - When invalid occurs, correct it right away: minimum correction
- Complexity:
    - O(nk), k being the # of recursive calls. It takes n calls to finish a full string case.

#### BFS
- Similar to DFS, we wnat to test: 1) test input s valid, 2) remove 1 invalid parenthesis at a time, 3) process substring
- instead of testing all substrings (timeout), we want to establish rules to improve reprocess:
    - Test1: skip regular char. No need to test it.
    - Test2: if redundant paren, do 1 is enough. skip adjacent ones.
    - Test3: if last removed extra paren is '(', the next ')' must be a valid pair. LastRemoved char: pecial handling by using a struct: `class Node {String s, int index, char lastRemoved}`
- How to end tests? When there is data in rst, stop adding to queue.

```
/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
*/
// DFS, slight simplification
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> rst = new ArrayList<>();
        if (s == null) return rst;
        dfs(rst, s, 0, 0, '(', ')');
        return rst;
    }
    
    private void dfs(List<String> rst, String s, int i, int j, char open, char close) {
        for (int count = 0; i < s.length(); i++) {
            count += s.charAt(i) == open ? 1 : 0;
            count -= s.charAt(i) == close ? 1 : 0;
            if (count < 0) {
                int init = j;
                for (; j <= i; j++) {
                    if (s.charAt(j) == close && (j == init || s.charAt(j) != s.charAt(j - 1))) {
                        dfs(rst, s.substring(0, j) + s.substring(j + 1), i, j, open, close);
                    }
                }
                return;
            }
        }
        
        // s passed test
        String reversed = new StringBuffer(s).reverse().toString();
        if (open == '(') {
            dfs(rst, reversed, 0, 0, close, open);
        } else {
            rst.add(reversed);
        }
    }
}


// Original DFS
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> rst = new ArrayList<>();
        if (s == null || s.length() == 0) {
            rst.add(s);
            return rst;
        }

        dfs(rst, s, 0, 0, '(', ')');
        return rst;
    }

    private void dfs(List<String> rst, String s, int x, int y, char open, char close) {
        // for loop start from i to validate all chars
        for (int count = 0, i = x; i < s.length(); i++) {
            if (s.charAt(i) == open) count++;
            if (s.charAt(i) == close) count--;
            if (count < 0) {
                // remove char if invalid: try all candidates from [j ~ i], but skip consecutive close parenthese
                for (int j = y; j <= i; j++) {
                    if (s.charAt(j) == close && (j == y || s.charAt(j - 1) != close)) {
                        dfs(rst, s.substring(0, j) + s.substring(j+1), i, j, open, close);
                    }
                }
                return;
            }
        }
        // reverse s, and reverse open/close, call dfs
        String reverse = new StringBuffer(s).reverse().toString();
        if (open == '(') {
            dfs(rst, reverse, 0, 0, close, open);
        } else { // return result if all validations passed
            rst.add(reverse);    
        }
    }
}



// Inspired from: https://leetcode.com/problems/remove-invalid-parentheses/discuss/75041/Java-BFS-solution-16ms-avoid-generating-duplicate-strings

class Solution {
    
    private class Node {
        String s;
        int index;
        char lastRemoved; // Smart way to maintain relationship with last recorded node
        public Node(String s, int index, char lastRemoved) {
            this.s = s;
            this.index = index;
            this.lastRemoved = lastRemoved;
        }
    }
    
    public List<String> removeInvalidParentheses(String s) {
        List<String> rst = new ArrayList<>();
        if (validate(s)) {
            rst.add(s);
            return rst;
        };
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(s, 0, ')'));
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            String ss = node.s;
            // Attempt to remove 1 char after failing 3 tests
            for (int i = node.index; i < ss.length(); i++) {
                char c = ss.charAt(i);
                // Test1: skip regular char
                if (c != '(' && c != ')') continue; 
                // Test2: if redundant paren, do 1 is enough. skip adjacent ones.
                if (i != node.index && ss.charAt(i - 1) == c) continue;
                // Test3: if last removed extra paren is '(', the next ')' must be a valid pair
                if (node.lastRemoved == '(' && c == ')') continue;
                // Generate sub and test it
                String sub = ss.substring(0, i) + ss.substring(i + 1);
                if (validate(sub)) rst.add(sub);
                else if (rst.isEmpty()) q.offer(new Node(sub, i, c)); // only add to queue if rst empty
            }
        }
            
        return rst;
    }
    
    // validate by testing open and close parens
    private boolean validate(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            count += c == '(' ? 1 : 0;
            count -= c == ')' ? 1 : 0;
            if (count < 0) return false;
        }
        return count == 0;
    }
}

```