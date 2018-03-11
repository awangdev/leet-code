M
1517368869
tags: DP

和House Robber I 类似,  DP. 根据dp[i-1]是否被rob来讨论dp[i]: dp[i] = Math.max(dp[i-1], dp[i - 2] + nums[i - 1]);

特别的是，末尾的last house 和 first house相连。这里就需要分别讨论两种情况:    
1. 第一个房子被rob    
2. 第一个房子没被rob   

分出两个branch, 可以看做两种状态. 
可以考虑用两个DP array, 也可以加一维度, 补充这个状态.

```
/*
Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery 
so that he will not get too much attention. This time, all houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. 
Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.

Subscribe to see which companies asked this question


 Dynamic Programming
Hide Similar Problems (E) House Robber (M) Paint House (E) Paint Fence (M) House Robber III

*/

/*
Thougths:
If there is a circle, which give 2 branches: index i == 0 was robbed, or index i == 0 was not robbed.
Create the 2nd dimension for this status. dp[i][j], where j = 0 or 1.
dp[i][0]: if index i = 0 was not robbed, what's the max at i.
dp[i][1]: if index i = 0 was robbed, what's the max at i.
Note:
1. Deal with final position with extra care.
2. Initialize the dp carefully
*/
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[][] dp = new int[n][2];
        // index i = 0, not robbed
        dp[0][0] = 0;
        dp[1][0] = nums[1];
        // index i = 0, robbed
        dp[0][1] = nums[0];
        dp[1][1] = dp[0][1];
        
        for (int i = 2; i < n; i++) {
            if (i == n - 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i -2][0] + nums[i]);
                dp[i][1] = dp[i - 1][1];
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i -2][0] + nums[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i -2][1] + nums[i]);
            }
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}

/*
	Each house depends on front and back houses
	Two possible case for the last house: rob or not robbed. So we can do two for loop, then compare the 
	two differnet future.
*/
public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        } else if (nums.length == 1) {
        	return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int n = nums.length;

        //Last house not robbed
        int[] dp1 = new int[n];
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n - 1; i++) {
        	dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
        }
        dp1[n - 1] = dp1[n - 2];

        //Last house robbed
        int[] dp2 = new int[n];
        dp2[0] = 0;
        dp2[1] = nums[1];
        for (int i = 2; i < n - 2; i++) {
        	dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i]);
        }
        dp2[n - 1] = dp2[n - 3] + nums[n - 1];

        //Compare
        return Math.max(dp2[n - 1], dp1[n - 1]);
    }
}





```