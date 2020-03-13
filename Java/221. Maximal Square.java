M
tags: DP, Coordinate DP
time: O(mn)
space: O(mn)

只能往右边,下面走, 找面积最大的 square. 也就是找到变最长的 square.

#### DP
- 正方形, 需要每条边都是`一样长度`.
    - 以右下角为考虑点, 必须满足条件: left/up/diagonal的点都是1
    - 并且, 如果三个点分别都衍生向三个方向, 那么最长的 square 边就是他们之中的最短边 (受最短边限制)
- dp[i][j]: max square length when reached at (i, j), from the 3 possible directions
- dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
- init: 每个点都可能是边长1, 如果 matrix[i][j] == '1'
- Space, time O(mn)
- Rolling array: [i] 和 [i - 1] 之间的关系, 想到滚动数组优化 space, O(n) sapce.


```

/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
*/

// dp = new int[m + 1][n + 1];
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;
       
        // calculate
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '0') continue;
                dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen * maxLen;
    }
}

/*
dp = new int[m][n];
Thoughts:
Square: each border has same length.
Matrix && square problem: consider right-bottom corner.
Let dp[i][j] be the max border length, limited by points up/left/diagnal
currLength = Math.min(up, left, diagnal) + 1.

init: i = 0 row, has length = 1; j = 0 col, has length = 1.
*/
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxLen = 0;
        // Init:
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
                maxLen = Math.max(dp[i][j], maxLen);
            }
        }

        // calculation
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxLen = Math.max(dp[i][j], maxLen);
                }
            }
        }
        return maxLen * maxLen;
    }
}


/*
Thoughts:
[i] and [i - 1] can be used to reduce space with rolling array
*/
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[2][n];
        int maxLen = 0;
        int curr = 0;
        int prev = 0;

        // calculation
        for (int i = 0; i < m; i++) {
            curr = prev;
            prev = 1 - curr;
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[curr][j] = matrix[i][j] == '1' ? 1 : 0;
                    maxLen = Math.max(dp[curr][j], maxLen);
                    continue;
                }
                if (matrix[i][j] == '1') {
                    dp[curr][j] = Math.min(Math.min(dp[prev][j], dp[curr][j - 1]), dp[prev][j - 1]) + 1;
                } else {
                    dp[curr][j] = 0;
                }
                maxLen = Math.max(dp[curr][j], maxLen);
            }
        }
        return maxLen * maxLen;
    }
}

```