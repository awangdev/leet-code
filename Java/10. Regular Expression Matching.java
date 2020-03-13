H
tags: String, DP, Sequence DP, Double Sequence DP, Backtracking

跟WildCard Matching 一样, 分清楚情况讨论 string p last char is '*' 还有并不是 '*'

IMPORTANT: '*' 需要有一个 prefix element [elm], so it becomes `[elm]*`. There 2 possible cases:
- [elm] repeats 0 times: move p, j + 2
- [elm] repeats 1 or more times: need s[i] == p[i], then move s, i+1

#### DFS, Top-Down, Break into sub problems.
- DFS on remaining of s and p. Analyze the different cases when next char == '*'
- End case: both i,j reached end true; or one of them reached end.
- The two different cases when given any index j on p, the  p[j+1]=='*'
    - TRUE:
        - ignore p[j, j+1], continue from p[j+2]
        - check if s[i]==p[j] or p[j]='.'; continue from s[i+1] and p
    - FALSE: check i,j, and move forward with s[i+1], p[j+1]
- If next p char != '*', check curr s[i] ?= p[i]
- Improvement with memo with 2D Booelan[][] memo: much faster
    - memo[i][j] records result the remaining strings: s.substring(i) compare with p.substring(j)
    - use `Boolean`: when memo[i][j] != null, return something!

#### DP, Sequence DP, Bottom-Up
- Two sequence, DP, find if possible to match.
- The '*' takes effect of preceding/prior element, so we can start matching from end.
- DP[i][j]: is it possible to match s[0 ~ i - 1] and p[0 ~ j - 1].
- Check last index of s and p, there can be a few possibilities:
    - 1. s[i-1]==p[j-1] and they are normal characters => && dp[i - 1][j - 1];
    - 2. p[j-1] == '.', match => dp[i - 1][j - 1]
    - 3. p[j-1] == '*':
        - a. ignore a* => |= dp[i][j - 2];
        - b. use a*    => |= dp[i - 1][j]; 
- init: dp[0][j] and dp[i][0] will all be false since there cannot be any match.


```
/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/

/*
Method1: 
DFS on remaining of s and p. Analyze the different cases.
End case: both i,j reached end true; or one of them reached end.

The two different cases: given any index j on p, check if the  j+1=='*'
- YES:
    - ignore p[j, j+1], continue from p[j+2]
    - check if s[i]==p[j] or p[j]='.'; continue from s[i+1] and p
- NO: check i,j, and move forward with s[i+1], p[j+1]
*/

class Solution {
    int n, m;
    public boolean isMatch(String s, String p) {
        n = s.length();
        m = p.length();
        
        return dfs(s, 0, p, 0);
    }
    
    private boolean dfs(String s, int i, String p, int j) {
        if (j >= m) return i >= n;

        char pc = p.charAt(j);
        if (j+1 < m && p.charAt(j + 1) == '*') {
            if (dfs(s, i, p, j+2)) return true; // when * reuslts in 0, move j
            if (i < n && (pc == '.' || s.charAt(i) == pc)) return dfs(s, i+1, p, j); // when * reuslts not as 0, move i
            return false;
        }
        if (i < n && (pc == '.' || s.charAt(i) == pc)) { // next pc not equal to '*'
            return dfs(s, i+1, p, j+1);
        }
        return false;
    }
}

// DFS + Memoization
class Solution {
    int n, m;
    Boolean[][] memo;
    public boolean isMatch(String s, String p) {
        n = s.length();
        m = p.length();
        memo = new Boolean[n+2][m+2];
        return dfs(s, 0, p, 0);
    }
    
    private boolean dfs(String s, int i, String p, int j) {
        if (j >= m) return i >= n;
        if (memo[i][j] != null) return memo[i][j];

        char pc = p.charAt(j);
        if (j+1 < m && p.charAt(j + 1) == '*') {
            // when * reuslts in 0 [elm], move j
            memo[i][j+2] = dfs(s, i, p, j+2);
            if (memo[i][j+2]) return true;
            
            // when * reuslts not into 1 or more [elm], move i
            if (i < n && (pc == '.' || s.charAt(i) == pc)) {
                memo[i+1][j] = dfs(s, i+1, p, j);
                return memo[i+1][j];
            }
        } else if (i < n && (pc == '.' || s.charAt(i) == pc)) { // next pc not equal to '*'
            memo[i+1][j+1] = dfs(s, i+1, p, j+1);
            return memo[i+1][j+1];
        }
        memo[i][j] = false;
        return memo[i][j];
    }
}


/*
Method2: DP
Thoughts:
Two sequence, DP, find if possible to match.
The '*' takes effect of preceding element, so we can start matching from end.
DP[i][j]: is it possible to match s[0 ~ i - 1] and p[0 ~ j - 1].
Check last index of s and p, there can be a few possibilities:
1. s[i-1]==p[j-1] and they are normal characters => && dp[i - 1][j - 1];
2. p[j-1] == '.', match => dp[i - 1][j - 1]
3. p[j-1] == '*':
    a. ignore a* => |= dp[i][j - 2];
    b. use a*    => |= dp[i - 1][j]; 

init:
dp[0][j] and dp[i][0] will all be false since there can't be any match.

*/
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        
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

                // j >= 1
                dp[i][j] = false;
                if (pp[j - 1] != '*') {
                    if (i >= 1 && (ss[i - 1] == pp[j - 1] || pp[j - 1] == '.')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else { // tail = '*'. ex: a*
                    if (j >= 2 ) { // ignore a*, repeat 0 times
                        dp[i][j] |= dp[i][j - 2];
                    }
                    // repeat the char befeore * for 1 time, so ss[i-1] should match pp[j-2] or pp[j-2] == '.'
                    if (j >= 2 && i >= 1 && (ss[i - 1] == pp[j - 2] || pp[j - 2] == '.')) { 
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
```