M
1530034378
tags: DP, Interval DP, Memoization, DFS

给一个string s, 找最长的sub-sequence which is also palindrome.

注意！subsequence并不是substring, 是可以skip letter / non-continuous character sequence
    
#### Interval DP
- 用[i][j]表示区间的首尾
- 考虑3个情况: 砍头, 砍尾, 砍头并砍尾 (考虑首尾关系)
- Iteration一定是以i ~ j 之间的len来看的. 
- len = j - i + 1; 那么反推, 如果len已知, j = len + i -1;
- 注意考虑len == 1, len == 2是的特殊情况.
- time/space: O(n^2)

#### Memoization
- 同样的方式model dp[i][j]: range [i, j] 之间的  max palindromic length
- 三种情况: 
- 1. 首尾match 继而 dfs[i+1, j-1]
- 2. 首尾不match,dfs[i+1,j] 
- 3. 首尾不match,dfs[i,j-1] 
- 注意: init dp[i][j]=-1, dfs的时候查dp[i][j] 是否算过
- more about dfs: bottom-up, first dive deep into dfs(i+1,j-1) till the base cases.
- time/space: O(n^2)
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
class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int n = s.length();
        int[][] dp = new int[n][n];
        
        // len == 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        // len == 2
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = arr[i] == arr[i + 1] ? 2 : 1;
        }
        
        // len == 3
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) { // i cannot over step into s[i, j]
                int j = len + i - 1;
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                if (arr[i] == arr[j]) { // as long as s[i]==s[j], they'll build outter palindrome from inner subsequence
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        return dp[0][n - 1];
    }
}


/**
Memoization
dp[i][j]: max palindrom length in range [i, j]
Three conditions:
1. i == j, check [i + 1, j - 1]
2. i != j, check [i + 1, j]
3. i != j, check [i, j - 1]

Init dp[i][j] = -1 to track the progress, memoization

Space: O(n^2)
Time: O(n^2)
*/

class Solution {
    int[][] dp = null;
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int n = s.length();
        dp = new int[n][n];
        // init
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        // len = 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // len = 2
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1;
        }

        return dfs(s, 0, n - 1);
    }

    public int dfs(String s, int x, int y) {
        if (dp[x][y] != -1) {
            return dp[x][y];
        }
        if (s.charAt(x) == s.charAt(y)) {
            dfs(s, x + 1, y - 1);
            if (dp[x + 1][y - 1] != -1) {
                dp[x][y] = dp[x + 1][y - 1] + 2;
            }
        } else {
            dp[x][y] = Math.max(dfs(s, x + 1, y), dfs(s, x, y - 1));
        }
        return dp[x][y];
    }
}

// Same memoization, where dfs does not return any value
class Solution {
    int[][] dp = null;
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int n = s.length();
        dp = new int[n][n];
        // init
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        // len = 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // len = 2
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1;
        }

        dfs(s, 0, n - 1);

        return dp[0][n - 1];
    }

    public void dfs(String s, int x, int y) {
        if (dp[x][y] != -1) {
            return;
        }
        if (s.charAt(x) == s.charAt(y)) {
            dfs(s, x + 1, y - 1);
            if (dp[x + 1][y - 1] != -1) {
                dp[x][y] = dp[x + 1][y - 1] + 2;
                return;
            }
        }

        dfs(s, x + 1, y);
        dfs(s, x, y - 1);
        dp[x][y] = Math.max(dp[x + 1][y], dp[x][y - 1]);
    }
}


```
