M
tags: DP, Interval DP, Memoization, DFS
time: O(n^2)
space: O(n^2)

给一个string s, 找最长的sub-sequence which is also palindrome.

注意！subsequence并不是substring, 是可以skip letter / non-continuous character sequence
    
#### Interval DP
- Use example to understand: for any given ending char, 3 cases of palindromes
    - a. ss[i, j] is a palindrome. dp[i+1][j-1] + 2 since the two indexes are counted, assume dp[i + 1][j - 1] is calculated
    - b. ss[i + 1, j] is a palindrome
    - c. ss[i, j - 1] is a palindrome
- time/space: O(n^2)
- **Option1: start processing substring from tail**
    - set: `i = [n-1 towards 0]`, `j = i + 1`
    - consider ss[i, j], ss[i + 1, j], ss[i, j - 1]
    - since i starts from n - 1 -> 0 and j = i + 1, these are calculated and ready to go: dp[i+1][j-1], dp[i+1][j] and dp[i][j-1]  
    - FAST: skipped the initialization
- **Option2: start processing substring from head**
    - 用[i][j]表示区间的首尾: 考虑3个情况: 砍头, 砍尾, 砍头并砍尾 (考虑首尾关系)
    - Iteration on len:
    - len = j - i + 1; 那么反推, 如果len已知, `j = len + i - 1`;
    - 注意考虑len == 1, len == 2是的特殊情况.


#### Memoization
#### DFS + Memoization
- consider sub problems with 3 major cases
    - a. ss[i, j] is a palindrome: dfs check ss[i + 1, j - 1]
    - b. ss[i + 1, j] maybe a palindrome: dfs check ss[i + 1, j]
    - c. ss[i, j - 1] maybe a palindrome: dfs check ss[i, j - 1]
- memo[i][j]: max palindrome length in range [i, j], if calculated, return directly
- Init memo[i][j] = -1 to track the progress, memoization
    - 注意: init dp[i][j]=-1, dfs的时候查dp[i][j] 是否算过
    - more about dfs: bottom-up, first dive deep into dfs(i+1,j-1) till the base cases.
- Space: O(n^2)
- Time: O(n^2)
- prepare dp[n][n]: O(n^2); dfs: visit all combinations of [i,j]: O(n^2)



```
/*
Given a string s, find the longest palindromic subsequence's length in s.
You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".

*/

/*
Thougths:
1. 区间型. Consider dp[i][j] represent the status from s[i] ~ s[j].
Iterate over the length between i and j: len = j - i + 1 => j = len + i - 1;
For all possible length, what try i and j.

2. For a string s[i ~ j], it contains a palindrome if:
    a. s[i, j] is a palindrome. dp value + 2 since the two indexes are counted, we need dive dpper into dp[i + 1][j - 1]
    b. s[i + 1, j] is a palindrome
    c. s[i, j - 1] is a palindrome

3. Initialize for three different length:
    a. len == 1 -> dp = 1
    b. len == 2 -> dp = 2
    c. len == 3 -> calculate
    
4. return dp[0][n - 1]: whole string
*/
// Method1, Option1
class Solution {
    public int longestPalindromeSubseq(String s) {
        char[] ss = s.toCharArray();
        int n = s.length();
        int[][] dp = new int[n][n];
        
        for (int i = n - 1; i >= 0; i--) { // starting from tail
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (ss[i] == ss[j]) dp[i][j] = dp[i + 1][j - 1] + 2;
                else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]); 
            }
        }
        return dp[0][n - 1];
    }
}

// Method1, Option2
class Solution {
    public int longestPalindromeSubseq(String s) {
        char[] ss = s.toCharArray();
        int n = s.length();
        int[][] dp = new int[n][n];
        
        // len == 1
        for (int i = 0; i < n; i++) dp[i][i] = 1;
        
        // len == 2
        for (int i = 0; i < n - 1; i++) dp[i][i + 1] = ss[i] == ss[i + 1] ? 2 : 1;
        
        // len == 3
        for (int len = 3; len <= n; len++) {
            // starting from head
            for (int i = 0; i <= n - len; i++) { // `i <= n - len` to keep j bounded by n
                int j = len + i - 1;

                // exclude ss[i] or exclude ss[j]
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]); 
                
                // if ss[i]==ss[j], include outter ss[i] and ss[j] to build larger palindrome from inner subsequence
                if (ss[i] == ss[j]) dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
            }
        }
        return dp[0][n - 1];
    }
}


/**
#### DFS + Memoization
- consider sub problems with 3 major cases
    - a. ss[i, j] is a palindrome: dfs check ss[i + 1, j - 1]
    - b. ss[i + 1, j] maybe a palindrome: dfs check ss[i + 1, j]
    - c. ss[i, j - 1] maybe a palindrome: dfs check ss[i, j - 1]
- memo[i][j]: max palindrome length in range [i, j], if calculated, return directly
- Init memo[i][j] = -1 to track the progress, memoization
- Space: O(n^2)
- Time: O(n^2)
*/

class Solution {
    Integer[][] memo = null;
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        memo = new Integer[n][n];
        return dfs(s, 0, n - 1);
    }

    public int dfs(String s, int i, int j) {
        if (memo[i][j] != null) return memo[i][j];
        if (i > j) return 0;
        if (i == j) return 1;
        if (s.charAt(i) == s.charAt(j)) memo[i][j] = dfs(s, i + 1, j - 1) + 2;
        else memo[i][j] = Math.max(dfs(s, i + 1, j), dfs(s, i, j - 1));
        
        return memo[i][j];
    }
}

// Slight complex way, with initialization
class Solution {
    int[][] memo = null;
    public int longestPalindromeSubseq(String s) {
        
        if (s.length() == 1) return 1;
        
        int n = s.length();
        memo = new int[n][n];
        // init
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) memo[i][j] = -1;
        }

        // len = 1
        for (int i = 0; i < n; i++) memo[i][i] = 1;

        // len = 2
        for (int i = 0; i < n - 1; i++) memo[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1;

        return dfs(s, 0, n - 1);
    }

    public int dfs(String s, int x, int y) {
        if (memo[x][y] != -1) return memo[x][y];
        if (s.charAt(x) == s.charAt(y)) {
            dfs(s, x + 1, y - 1);
            if (memo[x + 1][y - 1] != -1) memo[x][y] = memo[x + 1][y - 1] + 2;
        } else {
            memo[x][y] = Math.max(dfs(s, x + 1, y), dfs(s, x, y - 1));
        }
        return memo[x][y];
    }
}

```
