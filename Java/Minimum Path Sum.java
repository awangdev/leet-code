M
1521325066
tags: Array, DP, Coordinate DP

#### DP
- 往右下角走, 计算最短的 path sum. 典型的坐标型.
- 注意: init 第一行的时候, 要accumulate dp[0][j - 1] + grid[i][j], 而不是单纯assign grid[i][j]

##### rolling array
TODO


```
/*
Given a m x n grid filled with non-negative numbers, 
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note
You can only move either down or right at any point in time.

Example
Tags Expand 
Dynamic Programming
*/

/*
Thoughts:
Can only move down/right, means at any (i, j), the path comes from top/left.
say dp[i][j] is the min path sum,
so dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]

init: 
dp[0][0] = grid[0][0]
dp[0 ~ m][0] and dp[0][0 ~ n] should be accumulated for all [0, 0~n], [0 ~ m, 0]

*/
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        // init:
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        
        // calculate
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        
        return dp[m - 1][n - 1];
    }
}


```