M
tags: Array, DP, Coordinate DP
time: O(mn)
space: O(mn), rolling array O(n)

2D array, 算走到最右下角，有多少种方式.

#### DP, 加法原理
- 计数DP: 2 ways to reach (i,j): dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    - non-overlapping: `dp[i - 1][j]`, `dp[i][j - 1]`
    - covers the only 2 possible way to reach (i,j)
- initialization: dp[i][0] = 1, dp[0][i] = 1
    - Of course, row i = 0, or col j = 0, there is only 1 way to reach
- time O(mn), space O(mn)

##### 滚动数组 Rolling Array
- [i] 只跟 [i - 1] 有关系, 用 curr/prev 建立滚动数组.
- space O(n) 优化空间

#### DFS + Memoization
- move from (0,0) towards (m, n)
- use Map<coordinate, steps> as memoization technique

```
/*
A robot is located at the top-left corner of a m x n dp (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the dp (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note
m and n will be at most 100.

Example
1,1     1,2     1,3     1,4     1,5     1,6     1,7     
2,1
3,1                                             3,7

Above is a 3 x 7 dp. How many possible unique paths are there?

Tags Expand 
Array Dynamic Programming

*/

/*
DP: bottom-up approach. 加法原理
'how many ways' -> Could do DFS, but try DP
Robot moves: (0, 1) or (1, 0)
gird[x][y]: #paths to reach x,y.
There are only 2 ways for getting to (x, y): from (x-1, y) or (x, y-1)
Then, the sub problem is dp[x-1,y], and dp[x, y-1].
dp[x][y] = Math.min(dp[x-1,y], dp[x, y-1]) + 1;

Boundary: when x = 0, dp[0, 0~y] = 0~y; same for y=0, dp[0~x, 0] = 0~x;
Path: should go from y++ and y=0, because when we advance +1 row, we'd use previous x/y, which should be calculated already.
*/
class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        // Initialization
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 0; i < n; i++) dp[0][i] = 1;

        // Calculate based on equation
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];        
    }
}

/*
Rolling Array:
1st dimension [i] only deals with [i - 1].
Build rolling array to save space: O(n)
*/
class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        // Initialization
        int[][] dp = new int[2][n];
        for (int i = 0; i < 2; i++) dp[i][0] = 1;
        for (int i = 0; i < n; i++) dp[0][i] = 1;

        // Calculate based on equation
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i%2][j] = dp[(i - 1)%2][j] + dp[i%2][j - 1];
            }
        }
        return dp[(m-1)%2][n-1];        
    }
}

/*
DFS, Memoization
f[x][y]: want to find out all possible path
To get to f[m][m] from f[m-1][n-1] has 2 way: f[m-1][n] or f[m][n-1].
After found 'f[m-1][n-1]', store it to a Hashmap with the #path.
Every node pair (x,y) should have 1 solution.
Recursively add up to (0,0), will find out the total path.

1. Own solution: user HashMap to memorize
*/
public class Solution {
    int m,n;
    public int uniquePaths(int m, int n) {
        if (m <= 1 || n <= 1) return 1;
        this.m = m;
        this.n = n;
        return dfs(0, 0, new HashMap<>());
    }
    
    public int dfs(int x, int y, HashMap<String, Integer> map) {
        String key = getKey(x, y);
        if (map.containsKey(key)) return map.get(key);
        if (x >= m -1 || y >= n - 1) map.put(key, 1);
        else map.put(key, dfs(x, y + 1, map) + dfs(x + 1, y, map));
        return map.get(key);
    }

    private String getKey(int x, int y) {
        return x + "," + y;
    }
}

```