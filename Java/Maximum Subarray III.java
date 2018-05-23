R

```
/*

Given an array of integers and a number k, 
find k non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.


Example
Given [-1,4,-2,3,-2,3], k=2, return 8

Note
The subarray should contain at least one number

Tags Expand 
LintCode Copyright Dynamic Programming Subarray Array
*/

// Should be partition DP
/**
dp[i][j]: max sum for first i items, divided into j parts
dp[n + 1][k + 1]
dp[0][0] = 0;

dp[i][j] = Math.max(dp[x][j - 1] + maxSubArray(x+1,i)), x = 1 ~ n
http://www.cnblogs.com/lishiblog/p/4183917.html
 */
public class Solution {
    public int maxSubArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n + 1][k + 1];
        // dp[i][0] = 0;

        for (int j = 1; j <= k; j++) {
            for (int i = j; i <= n; i++) { // ??? why i = j
                dp[i][j] = Integer.MIN_VALUE;

                int endMax = 0;
                int max = Integer.MIN_VALUE;
                for (int x = i - 1; x >= j - 1; x--) { // ??? why x = i-1, x >= j -1?
                    endMax = Math.max(nums[x], nums[x] + endMax);
                    max = Math.max(max, endMax);
                    dp[i][j] = dp[i][j] < dp[x][j - 1] + max ? dp[x][j - 1] + max : dp[i][j];
                }
            }
        }
        return dp[n][k];
    }
}

```