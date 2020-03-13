M
tags: Array, DP, Coordinate DP
time: O(mn)
space: O(n) rolling array

#### DP
- Time, Space O(MN)
- 往右下角走, 计算最短的 path sum. 典型的坐标型.
- 注意: init 第一行的时候, 要accumulate dp[0][j - 1] + grid[i][j], 而不是单纯assign grid[i][j]
- Rolling Array
    - Time O(MN), Space O(N)
    - 1) 需要在同一个for loop里面完成initialization, 2) reuse dp[i][j]
    - Reason: dp[i % 2][j] 在被计算出来的时候, 马上在下一轮会被用. 被覆盖前不用,就白算
    - Option3 below initialize dp outside of loop: 看起来固然简单, 但是不方便空间优化

#### DFS (top-down) Thinking process
- Enumerate the possible 2 paths: go right, go down
- sub problem: dfs(i+1,j), dfs(i,j+1); pick the min of the two
- memoization: after the path from a point (i,j) to end is computed, record memo[i][j] to avoid repatative calculation
- time: O(mn), only visite and record memo[i][j] once.
- space: O(mn) extrem case of m=100000, n = 2; the stack height is O(mn)
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
// Option1: inline use `Integer.MAX_VALUE` to handle `i==0` or `j==0` cases
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = grid[i][j]; // Initialize
                if (i == 0 && j == 0) continue; // skip starting point
                // Calculate DP
                int fromUp = i == 0 ? Integer.MAX_VALUE : dp[i - 1][j];
                int fromLeft = j == 0 ? Integer.MAX_VALUE : dp[i][j - 1];
                dp[i][j] += Math.min(fromUp, fromLeft);
                
            }
        }
        
        return dp[m - 1][n - 1];
    }
}

// Rolling array
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[2][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Initialize
                if (i == 0 && j == 0) {
                    dp[i % 2][j] = grid[i][j];
                    continue;
                } 
                // Calculate DP
                int fromUp = i == 0 ? Integer.MAX_VALUE : dp[(i - 1) % 2][j];
                int fromLeft = j == 0 ? Integer.MAX_VALUE : dp[i % 2][j - 1];
                dp[i % 2][j] = Math.min(fromUp, fromLeft) + grid[i][j];
                
            }
        }
        
        return dp[(m - 1) % 2][n - 1];
    }
}

// Optional2: Not optimal code, but can be optimized with rolling array
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = grid[i][j]; // Initialize
                if (i == 0 && j == 0) continue;    
                if (i == 0 && j > 0) dp[i][j] += dp[i][j - 1];
                else if (i > 0 && j == 0) dp[i][j] += dp[i - 1][j];
                else dp[i][j] += Math.min(dp[i - 1][j], dp[i][j - 1]); // Calculate DP
            }
        }
        return dp[m - 1][n - 1];
    }
}

// Optional2: Rolling array, space: O(n)
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[2][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i%2][j] = grid[i][j]; // Initialize
                if (i == 0 && j == 0) continue;    
                if (i == 0 && j > 0) dp[i%2][j] += dp[i%2][j - 1];
                else if (i > 0 && j == 0) dp[i%2][j] += dp[(i - 1)%2][j];
                else dp[i%2][j] += Math.min(dp[(i - 1)%2][j], dp[i%2][j - 1]); // Calculate DP
            }
        }
        return dp[(m - 1)%2][n - 1];
    }
}

// Optoin3: init the 1st row, 1st col outside: cannot do rolling array
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        // init:
        for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int j = 1; j < n; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];
        
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