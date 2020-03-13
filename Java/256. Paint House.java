E
tags: DP, Sequence DP, Status DP
time: O(nm), m = # of colors
space: O(nm), or O(1) with rolling array

要paint n个房子, 还有 nx3的cost[][]. 求最少用多少cost paint 所有房子.

#### Sequence DP
- 求dp[i]的min cost, depends on the color of dp[n-1]
  - 选中最后一个房子的颜色同时, 根据dp[i - 1]的颜色/cost + cost[i-1], 来找出最低的cost
- Need to have status with dp array: int[index][color status]
  - dp[i][j]: 前i个house 刷成 j 号颜色的最小cost.
  - dp[0][j] = 0: 0th house, no cost
- 计算顺序: 从每一个house开始算起 [0 ~ n], first for loop
- time: O(nm), m = # of colors
- space: O(nm)

#### Rolling Array
- 观察发现 index[i] 只跟 [i-1] 相关, 所以2位就足够, %2
- space:O(1)

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
Method1: DP, TopDown + rolling array
Find minimum, think of DP. Think of last house: 
if red, the front options are blue/green
if green, the front options are red/blue
if blue, the front options are red/green

If define dp[i] : min cost to paint to house i.
min(dp[i - 1] + redCost, dp[i - 1] + blueCost,  dp[i - 1] + greenCost)

top-down:
- dp[n][color]: cost to paint n houses
    - depends on the color of dp[n-1][color]
- dp[0][color] = 0;
    - dp[n][0] = max(dp[n-1][1], dp[n-1][2]) + cost[n-1][0]
    - dp[n][1] = ...
    - dp[n][2] = ...
- return max of dp[n][0,1,2]

*/
// Method1: simple way to write it for this problem.
class Solution {
    int R = 0, B = 1, G = 2;
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0] == null || costs[0].length == 0) return 0;
        int n = costs.length; // n houses
        int[][] dp = new int[n + 1][3];
        dp[0][R] = dp[0][B] = dp[0][G] = 0; // house no.0
        
        for (int i = 1; i <= n; i++) {
            dp[i][R] = Math.min(dp[i - 1][B], dp[i - 1][G]) + costs[i-1][R];
            dp[i][B] = Math.min(dp[i - 1][R], dp[i - 1][G]) + costs[i-1][B];
            dp[i][G] = Math.min(dp[i - 1][R], dp[i - 1][B]) + costs[i-1][G];
        }
        
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++) min = Math.min(min, dp[n][j]);
        return min;
    }
}

// Rolling array:
class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[2][3]; // rolling array
        
        for (int i = 1; i <= n; i++) {
            int last = (i - 1) % 2, curr = i % 2; // rolling array
            for (int j = 0; j < 3; j++) { // simplify j
                dp[curr][j] = Math.min(dp[last][(j + 1) % 3], dp[last][(j + 2) % 3]) + costs[i-1][j];
            }
        }
        
        return Math.min(Math.min(dp[n%2][0], dp[n%2][1]), dp[n%2][2]);
    }
}

/*
Method2: make it generic, M colors
*/
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length, m = costs[0].length;
        int[][] dp = new int[n + 1][m]; //dp[0][0] = dp[0][1] = dp[0][2] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {// Select color j at index i
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < m; k++) {// Select color k at index i-1
                    if (j == k) continue; // avoid same color
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) min = Math.min(min, dp[n][j]);
        return min;
    }
}

// Rolling Array, optimize space
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length, m = costs[0].length;
        int[][] dp = new int[2][m]; //dp[0][0] = dp[0][1] = dp[0][2] = 0;
        
        for (int i = 1; i <= n; i++) {
            int curr = i % 2, last = (i - 1) % 2;
            for (int j = 0; j < m; j++) {// Select color j at index i
                dp[curr][j] = Integer.MAX_VALUE;
                for (int k = 0; k < m; k++) {// Select color k at index i-1
                    if (j == k) continue; // avoid same color
                    dp[curr][j] = Math.min(dp[curr][j], dp[last][k] + costs[i - 1][j]);
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) min = Math.min(min, dp[n%2][j]);
        return min;
    }
}
```