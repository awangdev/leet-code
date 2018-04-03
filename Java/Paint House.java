E
1522770984
tags: DP, Sequence DP

要paint n个房子, 还有 nx3的cost[][]. 求最少用多少cost paint 所有房子.

#### Sequence DP
- 求知道dp[n]的min cost, 但是不知道最后一个房子选什么颜色
- 那么就遍历最后一个房子(i - 1)的颜色
- 选中最后一个房子的颜色同时, 来选择 (i - 2)的颜色, 来找出最低的cost
- 考虑DP最后一个位置的情况. 发现给出了一些特殊条件, 需要附带在DP[i]上,
- 那么就定义二维数组

#### Rolling Array
- 观察发现 index[i] 只跟 [i-1] 相关, 所以2位就足够, %2
```
/*
There are a row of n houses, each house can be painted with one of the three colors: 
red, blue or green. The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color red; 
costs[1][2] is the cost of painting house 1 with color green, and so on... 

Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

 */
/*
Thoughts:
Consider last step: what color was chosen,
and what options can be taken from costs to find minimum
dp[i][0~2]: cost of paiting for the i houses, when last house patined with costs[i][0, 1, or 2]
*/
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0] == null || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length; // n houses
        int m = costs[0].length;
        int[][] dp = new int[n + 1][m];
        dp[0][0] = dp[0][1] = dp[0][2] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {// Color of house i-1, frin dp[i][j]
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < m; k++) {// Color of house i - 2, from dp[i][j - 1]
                    if (j == k) { // avoid same color
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            min = Math.min(min, dp[n][j]);
        }
        return min;
    }
}

// Rolling Array, optimize space
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0] == null || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length; // n houses
        int m = costs[0].length;
        int[][] dp = new int[2][m];
        dp[0][0] = dp[0][1] = dp[0][2] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {// Color of house i-1, frin dp[i][j]
                dp[i % 2][j] = Integer.MAX_VALUE;
                for (int k = 0; k < m; k++) {// Color of house i - 2, from dp[i][j - 1]
                    if (j == k) { // avoid same color
                        continue;
                    }
                    dp[i % 2][j] = Math.min(dp[i % 2][j], dp[(i - 1) % 2][k] + costs[i - 1][j])    ;
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            min = Math.min(min, dp[n % 2][j]);
        }
        return min;
    }
}

/*
Thgouths:
Find minimum, think of DP.
Think of last house: 
if red, the front options are blue/green
if green, the front options are red/blue
if blue, the front options are red/green

If define dp[i] : min cost to paint to house i.
min(dp[i - 1] + redCost, dp[i - 1] + blueCost,  dp[i - 1] + greenCost)

we don't know the status of last selection: blue/green/red?
redefine dp[i][j]: choose color j and the minimum is?

init:
dp[0][0] = costs[0][0];
dp[0][1] = costs[0][1];
dp[0][2] = costs[0][2];

*/
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int[][] dp = new int[n][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
            
        for (int i = 1; i < n; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        return Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
    }
}

// little optimize, not necessary
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int[][] dp = new int[n][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
            
        for (int i = 1; i < n; i++) {
            for (int j = 0 ; j < 3; j++) {
                dp[i][j] = costs[i][j] + Math.min(dp[i - 1][(j + 1)%3], dp[i - 1][(j + 2)%3]);
            }
        }
        return Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
    }
}
```