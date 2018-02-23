H
1519368767

Double sequence DP. 与regular expression 很像.

注意1: 分析字符 ?, * 所代表的真正意义, 然后写出表达式.
注意2: 搞清楚initialization 的时候 dp[i][0] 应该always false.当p为empty string, 无论如何都match不了 (除非s="" as well)
    同时 dp[0][j]不一定是false. 比如s="",p="*" 就是一个matching.

```
/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/

/*
Thoughts:
? -> any single character
* -> any empty, one or more of any characters.
dp[i][j]: can we match s[0 ~ i -1] and p[0 ~ j - 1]? true/false
There can be    different conditions:
A. p[j] != '*'
    1. last index match => dp[i - 1][j - 1]
    2. last index == ?  => dp[i - 1][j - 1]
B. p[j] == "*"
    1. * is empty => dp[i][j - 1]
    2. * match 1 or more chars => dp[i - 1][j]

init:
when p == empty and s not empty, we defined it's not a match.
However, if s == empty, p can still be *, which will be a match, so no need to hard define dp[0][j] to be true/false.

Time,Space O(MN)
*/
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        
        // dp[0][j] = false; dp[i][0] = false; 
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }
                if (j == 0) { // When p is empty but s is not empty, should not match
                    dp[i][j] = false;
                    continue;
                }
                dp[i][j] = false;
                if (pp[j - 1] != '*') {
                    if (i >= 1 && (ss[i - 1] == pp[j - 1] || pp[j - 1] == '?')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    dp[i][j] |= dp[i][j - 1];// '*' -> empty
                    if (i >= 1) { // '*' matches one or more of any character
                        dp[i][j] |= dp[i - 1][j]; 
                    }
                }
            }
        }
        return dp[m][n];
    }
}


// Optimize, rolling array: Space O(N)
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[2][n + 1];
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        int curr = 0;
        int prev = 1;
        
        // dp[0][j] = false; dp[i][0] = false; 
        
        for (int i = 0; i <= m; i++) {
            prev = curr;
            curr = 1 - prev;
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[curr][j] = true;
                    continue;
                }
                if (j == 0) { // When p is empty but s is not empty, should not match
                    dp[curr][j] = false;
                    continue;
                }
                dp[curr][j] = false;
                if (pp[j - 1] != '*') {
                    if (i >= 1 && (ss[i - 1] == pp[j - 1] || pp[j - 1] == '?')) {
                        dp[curr][j] = dp[prev][j - 1];
                    }
                } else {
                    dp[curr][j] |= dp[curr][j - 1];// '*' -> empty
                    if (i >= 1) { // '*' matches one or more of any character
                        dp[curr][j] |= dp[prev][j]; 
                    }
                }
            }
        }
        return dp[curr][n];
    }
}
```