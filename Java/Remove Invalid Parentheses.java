R
1530244778
tags: DFS, BFS, DP

给一个string, 里面有括号和其他字符. 以最少刀 剪出 valid string, 求所有这样的string.

这个题目有多种解法, 最强就是O(n) space and time

#### DFS and reduce input string
- in dfs: remove the incorrect parentheses one at a time
- detect the incorrect parentheses by tracking/counting (similar to validation of the parentheses string): `if(count<0)`
- once detected, remove the char from middle of s, and dfs on the rest of the s that has not been tested yet.

##### Core concept: reverse test
- `if a parenthese string is valid, the reverse of it should also be valid`
- Test s with open='(', close=')' first; **reverse s**, and test it with open=')', close='('

##### Minor details
- only procceed to remove invalid parenthese when `count<0`, and also break && return dfs after the recursive calls.
- The above 2 facts eliminates all the redundant results.
- Reverse string before alternating open and close parentheses, so when returning final result, it will return the correct order.
- Open questions: how does it guarantee minimum removals?

##### Backtracking
- 如果用stringbuffer, 那么久不会每次create new string, 但是需要maintain这个string buffer, 就会backtracking

##### Complexity
- Seems to be O(n), but need to derive

#### BFS
TODO

#### DP

```
/*
Remove the minimum number of invalid parentheses in order to make the input string valid. 
Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
*/

// DFS, 98%
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

// DFS, StringBuffer, a bit more concise, 98%
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> rst = new ArrayList<>();
        if (validateInput(rst, s)) return rst;

        dfs(rst, new StringBuffer(s), 0, 0, '(', ')');
        return rst;
    }

    private void dfs(List<String> rst, StringBuffer sb, int x, int y, char open, char close) {
        // for loop start from i to validate all chars
        for (int count = 0, i = x; i < sb.length(); i++) {
            if (sb.charAt(i) == open) count++;
            if (sb.charAt(i) == close) count--;
            if (count >=0) continue;
            // remove char if invalid: try all candidates from [j ~ i], but skip consecutive close parenthese
            for (int j = y; j <= i; j++) {
                if (sb.charAt(j) == close && (j == y || sb.charAt(j - 1) != close)) {
                    dfs(rst, sb.deleteCharAt(j), i, j, open, close);
                    sb.insert(j, close);
                }
            }
            return;
        }
        // reverse s, and reverse open/close, call dfs
        sb.reverse();
        if (open == '(') {
            dfs(rst, sb, 0, 0, close, open);
        } else { // return result if all validations passed
            rst.add(sb.toString());    
        }
        sb.reverse();
    }

    private boolean validateInput(List<String> rst, String s) {
        if (s == null) return true;
        if (s.length() == 0) {
            rst.add("");
            return true;
        }
        return false;
    }
}


// BFS: http://www.cnblogs.com/grandyang/p/4944875.html
```