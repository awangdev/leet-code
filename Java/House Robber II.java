M
1523329114
tags: DP, Sequence DP

和House Robber I 类似, 搜刮房子, 相邻不能动. 特点是: 现在nums排成了圈, 首尾相连.

#### Sequence DP
- 根据dp[i-1]是否被rob来讨论dp[i]: dp[i] = Math.max(dp[i-1], dp[i - 2] + nums[i - 1]);
- 特别的是，末尾的last house 和 first house相连. 这里就需要分别讨论两种情况: 第一个房子被搜刮, 或者第一个房子没被搜刮
- be careful with edge case nums = [0], only with 1 element.
- Time,space: O(n)

#### 两个状态
- 是否搜刮了第一个房子, 分出两个branch, 可以看做两种状态.
- 可以考虑用两个DP array; 也可以加一dp维度, 补充这个状态.
- 连个维度表示的是2种状态(1st house being robbed or not); 这两种状态是平行世界的两种状态, 互不相关.

#### Rolling array
- 与House Robber I一样, 可以用%2 来操作rolling array, space reduced to O(1)

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

// Sequence DP, new dp[n + 1][2]
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        long[][] dp = new long[n + 1][2];
        dp[0][0] = 0; // not picking any number
        dp[1][0] = 0; // not picking nums[0]
        dp[1][1] = nums[0]; // pick nums[0]
        
        for (int i = 2; i < n; i++) { // spare the (i = n) case
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][0] + nums[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][1] + nums[i - 1]);
        }
        // i = n;
        dp[n][0] = Math.max(dp[n - 1][0], dp[n - 2][0] + nums[n - 1]);
        dp[n][1] = dp[n - 1][1];
        
        return (int) Math.max(dp[n][0], dp[n][1]);
    }
}

// rolling array, O(1) space
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        long[][] dp = new long[2][2];
        dp[0][0] = 0; // not picking any number
        dp[1][0] = 0; // not picking nums[0]
        dp[1][1] = nums[0]; // pick nums[0]
        
        for (int i = 2; i < n; i++) { // spare the (i = n) case
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 2) % 2][0] + nums[i - 1]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 2) % 2][1] + nums[i - 1]);
        }
        // i = n;
        dp[n % 2][0] = Math.max(dp[(n - 1) % 2][0], dp[(n - 2) % 2][0] + nums[n - 1]);
        dp[n % 2][1] = dp[(n - 1) % 2][1];
        
        return (int) Math.max(dp[n % 2][0], dp[n % 2][1]);
    }
}
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
Thougths:
Rolling array:
index [i] is only calculated based on prior [i - 1] and [i - 2].
[i - 1] and [i - 2] can be distinguished by using %2.
*/
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[][] dp = new int[2][2];
        // index i = 0, not robbed
        dp[0][0] = 0;
        dp[1][0] = nums[1];
        // index i = 0, robbed
        dp[0][1] = nums[0];
        dp[1][1] = dp[0][1];
        
        for (int i = 2; i < n; i++) {
            if (i == n - 1) {
                dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 2) % 2][0] + nums[i]);
                dp[i % 2][1] = dp[(i - 1) % 2][1];
            } else {
                dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 2) % 2][0] + nums[i]);
                dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 2) % 2][1] + nums[i]);
            }
        }
        return Math.max(dp[(n - 1) % 2][0], dp[(n - 1) % 2][1]);
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