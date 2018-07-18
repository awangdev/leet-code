M
1531896045
tags: DP, DFS

// 如何想到从中间initialize

```
/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
*/

/*
target S range [-1000, 1000]
dp[i][amount]: for the first i items, # of ways to sum up to certain amount
dp[i][j] = dp[i-1][j - nums[i-1]] + dp[i-1][j + nums[i-1]]; negative, or positive on item[i-1]. 加法原理.
int[][] dp = new int[n+1][S+1]
dp[0][anyamount] = 0;
dp[1][num[0]] = 1;

// set up Math.abs(S); if negative, the # of ways will be the same for the absolute value.
goal: dp[n][S]
*/
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum < Math.abs(S)) return 0;
        
        // create dp, and init
        int n = nums.length, m = (sum << 1) + 1;
        int[][] dp = new int[n][m];
        dp[0][sum - nums[0]] += 1;
        dp[0][sum + nums[0]] += 1;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) { 
                if (j - nums[i] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i]];
                }
                if (j + nums[i] < m) {
                    dp[i][j] += dp[i - 1][j + nums[i]];    
                }
            }
        }
        
        return dp[n - 1][S + sum];
    }
}
```