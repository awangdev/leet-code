M
1521312479
tags: Array, DP

2D array, 算走到最右下角，有多少种方式.

##### DP
- 计数DP.注意方程式前两位置加在一起: 前两种情况没有overlap, 也不会缺情况.
- 注意initialization, 归1.
- 需要initialize的原因是,也是一个reminder: 在方程中会出现-1index
- Of course, row i = 0, or col j = 0, there is only 1 way to access
- time O(mn), space O(mn)

##### 滚动数组
- [i] 只跟 [i - 1] 有关系, 用 curr/prev 建立滚动数组.
- space O(n) 优化空间


```
/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note
m and n will be at most 100.

Example
1,1     1,2     1,3     1,4     1,5     1,6     1,7     
2,1
3,1                                             3,7

Above is a 3 x 7 grid. How many possible unique paths are there?

Tags Expand 
Array Dynamic Programming

*/



/*
Thoughts:
Count # of ways to reach bottom-right: it depends on top/left
dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
Init:
For all i == 0, there is only 1 way to reach each spot: can only move down or right.

Rolling Array:
1st dimension [i] only deals with [i - 1].
Build rolling array to save space: O(n)
*/
class Solution {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[][] dp = new int[2][n];
        int curr = 0;
        int prev = 0;
        // Init j = 0 col
        for (int i = 0; i < 2; i++) {
            dp[i][0] = 1;
        }
        // Init i = 0 row
        for (int j = 0; j < n; j++) {
            dp[curr][j] = 1;
        }
        
        // Calcualte the dp[i][j]
        for (int i = 1; i < m; i++) {
            prev = curr;
            curr = 1 - prev;
            for (int j = 1; j < n; j++) {
                dp[curr][j] = dp[prev][j] + dp[curr][j - 1];
            }
        }
        
        return dp[curr][n - 1];
    }
}


/*
Thoughts:
'how many ways' -> Could do DFS, but try DP
Robot moves: (0, 1) or (1, 0)
gird[x][y]: #paths to reach x,y.
There are only 2 ways for getting to (x, y): from (x-1, y) or (x, y-1)
Then, the sub problem is grid[x-1,y], and grid[x, y-1].
grid[x][y] = Math.min(grid[x-1,y], grid[x, y-1]) + 1;

Boundary: when x = 0, grid[0, 0~y] = 0~y; same for y=0, grid[0~x, 0] = 0~x;
Path: should go from y++ and y=0, because when we advance +1 row, we'd use previous x/y, which should be calculated already.
*/
class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        // Initialization
        final int[][] grid = new int[m][n];
        for (int i = 0; i < n; i++) {
            grid[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            grid[i][0] = 1;
        }
        // Calculate based on equation
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }
        return grid[m-1][n-1];        
    }
}

/*
Thinking process:
f[x][y]: want to find out all possible path
To get to f[m][m] from f[m-1][n-1] has 2 way: f[m-1][n] or f[m][n-1].
After found 'f[m-1][n-1]', store it to a Hashmap with the #path.
Every node pair (x,y) should have 1 solution.
Recursively add up to (0,0), will find out the total path.

1. Own solution: user HashMap to memorize
*/
public class Solution {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    private int m,n;
    public int uniquePaths(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }
        this.m = m;
        this.n = n;
        HashMap<ArrayList<Integer>, Integer> his = 
            new HashMap<ArrayList<Integer>, Integer>();
        int right = helper(0, 1, his);
        int down = helper(1, 0, his);
        return right + down;
    }
    
    public int helper(int x, int y, HashMap<ArrayList<Integer>, Integer> his) {
        ArrayList<Integer> pair = new ArrayList<Integer>();
        pair.add(x);
        pair.add(y);
        if (his.containsKey(pair)) {
            return his.get(pair);
        }
        if (x >= this.m -1 || y >= this.n - 1) {
            his.put(pair, 1);
            return his.get(pair);
        }
        int right = helper(x, y + 1, his);
        int down = helper(x + 1, y, his);
        his.put(pair, right + down);
        return his.get(pair);
    }
}

/*

2. 9Chapter solution
Thinking process:
1. Assume (r,c) where r>=1, c>=1. Any node (r,c) has 2 ways to get to: (r-1, c) from top, or (r,c-1) from left-side.
2. (r-1, c) and (r,c-1) stores the possible paths to get to them
3. (r,c) = (r-1, c) + (r,c-1)
4. Initialize the top-row and left-column to be 1: Assuming landing on any initial node has path # of 1.
5. From top-bottom traverse
*/

public class Solution {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    //Traverse
    public int uniquePaths(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }
        int[][] matrix = new int[m][n];
        //Initialize
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            matrix[0][i] = 1;
        }
        //Traverse
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }
        return matrix[m - 1][n - 1];
    }
}


```