H
1521561205
tags: DFS, Memoization, Topological Sort, DP, Coordinate DP

m x n 的matrix, 找最长增序的序列长度. 这里默认连续的序列.

- 接成圈是不行的, 所以visit过得 (x,y)就不能再去了.
- 斜角方向不能走, 只能走上下左右
- 无法按照坐标DP来做, 因为计算顺序4个方向都可以走.
- 最终要visit所有node, 所以用DFS搜索比较合适.

#### DFS, Memoization
- 简单版: longest path, only allow right/down direction: 
- `dp[x][y] = Math.max(dp[prevUpX][prevUpY], or dp[prevUpX][prevUpY] + 1)`; and compare the other direction as well
- This problem, just compare the direction from dfs result
- DFS太多重复计算; memoization (dp[][], visited[][]) 省去了重复计算
- initialize dp[x][y] = 1, (x,y) 自己也算path里的一格
- dfs(matrix, x, y): 每次检查(x,y)的4个neighbor (nx, ny), 如果他们到(x,y)是递增, 那么就考虑和比较:
- Maht.max(dp[x][y], dp[nx][ny] + 1); where dp[n][ny] = dfs(matrix, nx, ny)
- top level: O(mn), 尝试从每一个 (x,y) 出发
- O(m * n * k), where k is the longest path

#### Topological sort
还没有做

```
/*
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down.
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/

/*
Thoughts:
1. Need to start from each position (x,y) to 4 directions: O(4 * m * n)
2. For each direction, if meet the requirement curr < neighbor, move to neighbor
3. We should avoid recalculating the longest path start from (i, j) in the board, should memorize it with dp[x][y] and flag them as visited.

dp[x][y]: max increasing path length if starting form point (x, y).

init: dp[x][y] = 1, any element by itself counts as 1
*/
class Solution {
    boolean[][] visited;
    int[][] dp;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        visited = new boolean[m][n];
        dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(matrix, i, j));
            }
        }
        return max;
    }
    
    private int dfs(int[][] matrix, int x, int y) {
        if (visited[x][y]) {
            return dp[x][y];
        }
        // Initialize:
        dp[x][y] = 1;

        // Calculating, dfs
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (validateCoordinate(matrix, x, y, nx, ny)) {
                dp[x][y] = Math.max(dp[x][y], dfs(matrix, nx, ny) + 1);
            }
        }
        visited[x][y] = true;
        return dp[x][y];
    }

    private boolean validateCoordinate(int[][] matrix, int x, int y, int nx, int ny) {
        return nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length
                && matrix[x][y] < matrix[nx][ny];
    }
}
```