E
tags: DP, Sequence DP, Status DP
time: O(n)
space: O(n) or rolling array O(1)

搜刮房子, 相邻的不能碰. 每个房子里有value, 求max.

#### Sequence DP
- dp[i]: 前i个房子拿到的max gain
- 看最后结尾状态的前一个或前两个的情况，再综合考虑当下的
- 搞清楚当下[i]的和之前[i-x]的情况的关系: 不可以连着house, 那么就直接考虑 dp[i-2]的情况
- Sequence DP, new dp[n + 1];
- Rolling Array
    - [i]'只和前两个位子 [i-1], [i - 2]'相关
    - 用%2来标记 [i], [i - 1], [i - 2]三个位置.
    - 其他滚动时惯用curr/prev来表示坐标, 这里%2虽然抽象, 但是更加实用.

#### Method2: Status DP
- dp[i] depends on nums[i-1] or nums[i-2] based on the state at (i-1)
    - use dp[n][2] to store dp[i] and stages
    - dp[0][0] = 0; dp[0][1] = nums[0]
- calculation
    - dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]). The prior house can be either state.
    - dp[i][1] = dp[i - 1][0] + nums[i]. The prior house must be `NOT ROBBED`.
- return `Math.max(dp[n - 1][0], dp[n - 1][1])`

```
/*
You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that 
adjacent houses have security system connected and it will automatically 
contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.


Example
Given [3, 8, 4], return 8.

Challenge
O(n) time and O(1) memory.

Tags Expand 
Dynamic Programming

*/

/**
Method1: Sequence DP
*/
// DP[i]: max value for first i items; new dp[n + 1];
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        long[] dp = new long[n + 1];
        dp[0] = 0; 
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return (int) dp[n];
    }
}
// Method1: improve, rolling array
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        long[] dp = new long[2];
        dp[0] = 0; 
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i % 2] = Math.max(dp[(i - 1)%2], dp[(i - 2)%2] + nums[i - 1]);
        }
        return (int) dp[n % 2];
    }
}




/*
Method2: Status DP
- dp[i] depends on nums[i-1] or nums[i-2] based on the state at (i-1)
    - use dp[n][2] to store dp[i] and stages
    - dp[0][0] = 0; dp[0][1] = nums[0]
- dp[i][0] = max of (dp[i - 1][1], dp[i - 1][0])
- dp[i][1] = dp[i - 1][0] + nums[i]
*/

class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}


```