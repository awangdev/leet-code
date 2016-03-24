M

和House Robber I 类似,  DP.

根据dp[i-1]是否被rob来讨论dp[i]: dp[i] = Math.max(dp[i-1], dp[i - 2] + nums[i - 1]);

特别的是，末尾的last house 和 first house相连。这里就需要分别讨论两种情况:    
1. 最后一个房子被rob    
2. 最后一个房子没被rob   

两种情况做完，综合对比一下.

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