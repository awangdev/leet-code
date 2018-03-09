M
1518540216
tags: DP

区间型动态规划. 
1. 用[i][j]表示区间的首尾
2. 考虑3个情况: 砍头, 砍尾, 砍头并砍尾 (考虑首尾关系)
3. Iteration一定是以i ~ j 之间的len来看的. len = j - i + 1; 那么反推, 如果len已知, j = len + i -1;

注意考虑len == 1, len == 2是的特殊情况.

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
                if (arr[i] == arr[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        return dp[0][n - 1];
    }
}
```