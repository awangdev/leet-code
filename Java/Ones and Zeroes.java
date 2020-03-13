H
1519371920
tags: DP

还是Double Sequence, 但是考虑第三种状态: 给的string array的用量.
所以开了3维数组.

如果用滚动数组优化空间, 需要把要滚动的那个for loop放在最外面, 而不是最里面.
当然, 这个第三位define在 dp[][][]的哪个位置, 问题都不大.

另外, 注意在外面calcualte zeros and ones, 节约时间复杂度.

```
/*
In the computer world, use restricted resource you have
to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively.
On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form
with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.
Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4
Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, 
which are “10,”0001”,”1”,”0”

Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2
Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
*/


/*
Thoughts:
We need to track:
how many 0's being used: i
how many 1's being used: j
how many items being formed/taken from the strs array: k

dp[i][j][k]: given i 0's, j 1's and k items from the array, what's max# of strings can we form?

for instance if strs[k - 1] = '0011' => dp[i][j][k] = 1 + dp[i - 2][j - 2][k - 1] | i >= 2, j >= 2

dp[0][0][0] = 0; // no 0 or 1 and no item => 0
dp[0][0][k] = 0; // no 0 or 1 => 0
dp[i][j][0] = 0; // no items =>0

Two possible conditions:
1. can't match, dp[i][j][k] = dp[i][j][k - 1]. 0's and 1's are not being used, but moving on to next item, k--
2. find a match, but have to make sure remaining balance of 0's and 1's is not negative
dp[i][j][k] = Math.max(dp[i][j][k - 1], dp[i - countZero][j - countOne][k - 1] + 1| i >=countZero && j>=countOne);

Space: O(MNK)
Time: O(MNK)
*/

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0 || (m == 0 && n == 0)) {
            return 0;
        }
        int len = strs.length;
        int[][][] dp = new int[m + 1][n + 1][len + 1];
        
        // Count zeros and ones. space O(K), time O(KH), H is longest string length;
        int[] ones = new int[len];
        int[] zeros = new int[len];
        for (int k = 0; k < len; k++) {
            char[] ss = strs[k].toCharArray();
            for (char c : ss) {
                ones[k] += c == '1' ? 1 : 0;
                zeros[k] += c == '0' ? 1 : 0;
            }
        }

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= len; k++) {
                    if (k == 0 || (i == 0 && j == 0)) {
                        dp[i][j][k] = 0;
                        continue;
                    }
                    dp[i][j][k] = dp[i][j][k - 1]; // no matching
                    int countZero = zeros[k - 1];
                    int countOne = ones[k - 1];
                    if (i >= countZero && j >= countOne) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - countZero][j - countOne][k - 1] + 1);
                    }
                }
            }
        }
        return dp[m][n][len];
    }
}

/**
Optimization.
Note, for rolling array to work properly, it's best to have k-loop at top level.
Now Space O(MN), time is still O(MNK)
 */
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0 || (m == 0 && n == 0)) {
            return 0;
        }
        int len = strs.length;
        int[][][] dp = new int[m + 1][n + 1][2];
        // rolling array
        int curr = 1;
        int prev = 0;
        
        // Count zeros and ones. space O(K), time O(KH), H is longest string length;
        int[] ones = new int[len];
        int[] zeros = new int[len];
        for (int k = 0; k < len; k++) {
            char[] ss = strs[k].toCharArray();
            for (char c : ss) {
                ones[k] += c == '1' ? 1 : 0;
                zeros[k] += c == '0' ? 1 : 0;
            }
        }
        for (int k = 0; k <= len; k++) {
            prev = curr;
            curr = 1 - prev;
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (k == 0 || (i == 0 && j == 0)) {
                        dp[i][j][curr] = 0;
                        continue;
                    }
                    dp[i][j][curr] = dp[i][j][prev]; // no matching
                    int countZero = zeros[k - 1];
                    int countOne = ones[k - 1];
                    if (i >= countZero && j >= countOne) {
                        dp[i][j][curr] = Math.max(dp[i][j][curr], dp[i - countZero][j - countOne][prev] + 1);
                    }
                }
            }
        }
        return dp[m][n][curr];
    }
}


/*
Original
Space: O(MNK)
Time: O(MNKH), where H is the longest string length.
 */
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0 || (m == 0 && n == 0)) {
            return 0;
        }
        int len = strs.length;
        int[][][] dp = new int[m + 1][n + 1][len + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= len; k++) {
                    if (k == 0 || (i == 0 && j == 0)) {
                        dp[i][j][k] = 0;
                        continue;
                    }
                    // Count
                    char[] ss = strs[k - 1].toCharArray();
                    int countOne = 0;
                    int countZero = 0;
                    for (char c : ss) {
                        countOne += c == '1' ? 1 : 0;
                        countZero += c == '0' ? 1 : 0;
                    }
                     dp[i][j][k] = dp[i][j][k - 1]; // no matching
                    if (i >= countZero && j >= countOne) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - countZero][j - countOne][k - 1] + 1);
                    }
                }
            }
        }
        return dp[m][n][len];
    }
}
```