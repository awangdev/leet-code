M
1519197096

经典序列型.
设定dp长度为(n+1), 因为dp[i]要用来表示前i个(ith)时候的状态, 所以长度需要时i+1才可以在i位置, hold住i.

双序列: 两个sequence之间的关系, 都是从末尾字符看起, 分析3种情况:
1. A最后字符不在common sequence.
2. B最后字符不在common sequence.
3. A/B最后字符都在common sequence. 总体+1.

```
/*
Given two strings, find the longest comment subsequence (LCS).

Your code should return the length of LCS.

Example
For "ABCD" and "EDCA", the LCS is "A" (or D or C), return 1

For "ABCD" and "EACB", the LCS is "AC", return 2

Clarification
What's the definition of Longest Common Subsequence?

    * The longest common subsequence (LCS) problem is to find the longest subsequence common to all sequences in a set of sequences (often just two). (Note that a subsequence is different from a substring, for the terms of the former need not be consecutive terms of the original sequence.) It is a classic computer science problem, the basis of file comparison programs such as diff, and has applications in bioinformatics.

    * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem

Tags Expand 
LintCode Copyright Longest Common Subsequence Dynamic Programming

*/

/*
Thoughts:
Sequence DP.
Common Subsequence: doesn't have to be conneccted subsequence.
Consider the last position of each string, there are 3 possible conditions:
1. A's last index is not part of common subsequence.   Next step, consider: A[0 ~ n-1] and B
2. B's last index is not part of common subsequence    Next step, consider: B[0 ~ n-1] and A
3. A's last index == B's last index, +1 on the result.    Next step, consider: A[0 ~ n-1] and B[0 ~ n-1]

=> Each condition results in a sub problem.

dp[i][j]: longest common subsequence length for items: A[0 ~ i - 1] and B[0 ~ j - 1]
dp[i][j] = Max{dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1] + 1| A[i - 1]==B[i - 1] }

Space: O(MN)
Time: O(MN)
*/
public class Solution {
    public int longestCommonSubsequence(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        
        return dp[m][n];
    }
}

// Optimization: Rolling array
// Space: O(N), Time: O(MN)
public class Solution {
    public int longestCommonSubsequence(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[2][n + 1];
        
        int curr = 1;
        int prev = 0;
        for (int i = 1; i <= m; i++) {
            prev = curr;
            curr = 1 - prev;
            for (int j = 1; j <= n; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[curr][j] = Math.max(dp[prev][j], dp[curr][j - 1]);
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[curr][j] = Math.max(dp[curr][j], dp[prev][j - 1] + 1);
                }
            }
        }
        
        return dp[curr][n];
    }
}

// Print the longest common subsequence
public class Solution {
    public int longestCommonSubsequence(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m + 1][n + 1];
        int[][] pi = new int[m + 1][n + 1]; // stores case1, case2, or case3
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                pi[i][j] = dp[i - 1][j] > dp[i][j - 1] ? 1 : 2;

                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                    if (dp[i][j] == dp[i - 1][j - 1] + 1) {
                        pi[i][j] = 3;
                    }
                }
            }
        }

        //Prepare for printing
        char[] res = new char[dp[m][n]];
        int i = m;
        int j = n;
        int w = dp[m][n] - 1;
        while (i > 0 && j > 0) {
            if (pi[i][j] == 1) {
                i--;
            } else if (pi[i][j] == 2) {
                j--;
            } else {//3
                res[w] = A.charAt(i - 1);
                i--;
                j--;
                w--;
            }
        }

        for (int k = 0; k < dp[m][n]; k++) {
            System.out.print(res[k]);
        }
        System.out.println();
        
        return dp[m][n];
    }
}

/*
Preivous Note.

Thinking process:
Using DP.
check[i][j] means: the length of longest common subsequnce between A(0 ~ i) and B(0 ~ j).
Then there are two ways to reach check[i][j]:
1. A(i-1) == B(j - 1), then check[i][j] = check[i - 1][j - 1] + 1;
2. A(i-1) != B(j - 1), then pick the max between (i-1,j) ,  (i,j-1)  and (i, j )
Note: check[][] is initialized with all 0's. Index (0,0) is used as starting 0.
 */
public class Solution {
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int[][] check = new int[A.length()  + 1][B.length() + 1];
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    check[i][j] = check[i - 1][j - 1] + 1;
                } else {
                    check[i][j] = Math.max(check[i][j], check[i - 1][j]);
                    check[i][j] = Math.max(check[i][j], check[i][j - 1]);
                }
            }
        }
        return check[A.length()][B.length()];
    }
}



```