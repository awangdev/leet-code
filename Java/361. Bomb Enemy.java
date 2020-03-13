M
tags: DP, Coordinate DP
time: O(mn)
space: O(n) by calculating column sum

2D grid, 每个格子里面可能是 'W' wall, 'E' enemy, 或者是 '0' empty.

一个bomb可以往4个方向炸. 求在grid上面, 最大能炸掉多少个敌人.

#### Method1: Corrdinate DP
- Space, Time: O(MN)
- dp[i][j] 就是(i, j)上最多能炸掉的enemy数量
- dp[i][j] 需要从4个方向加起来, 也就是4个方向都要走一遍, 所以分割成 UP/Down/Left/Right 4个 int[][]
- 最后一步的时候求max
- 分方向考虑的方法很容易想到,但是四个方向移动的代码比较繁琐.
- 往上炸: 要从顶向下考虑
- 往下炸: 要从下向上考虑
- 熟练写2D array index 的变换.

#### Method2: Analyze, col count array:
- Inspired by: https://leetcode.com/problems/bomb-enemy/discuss/83387/Short-O(mn)-time-O(n)-space-solution
- Analyize the problem: need to add up 2 directions at any given point.
    - Notice 1: if I traverse row by row, each colSum at a specific col j is likely to be the same
        - Unless there is a Wall in last row, so we have to calclate the col sum starting from current row, below the Wall
    - Notice 2: for row it is the same:
        - If I in a new spot row[i][j], where (i-1) is Wall, i need to sum row from 0
- we will process each point:
    - process row by row and add up row sum
    - buffer col[j] in an array vertically so we can resue
    - make sure to recalculate row sum or col sum if last row index or last col index is Wall
- time: O(mn) traversal
- space: O(n) only use a column array

```
/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), 
return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point
until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)
*/


/*
Method1:
It goes off towards 4 difference directions: UP/DOWN/LEFT/RIGHT
Normally: we could traverse the 2D map, use each point to go 4 directions: O(MN*(M+N)) ~ O(MN^2) or O(NM^2)
Need to optimize: standing on any point, the 4 directions are likely to be calculated in earlier time.
Consider UP case:
up[i][j]: the # of bombed enemy at (i,j) can be:
1. up[i-1][j], if grid[i-1][j]== '0'
2. up[i-1][j] + 1 if grid[i-1][j]== 'E'
3. 0, if grid[i-1][j]== 'W'

We'll sum up UP/DOWN/LEFT/RIGHT at the end. During initialize of the 4 directions, ignore 'W'.

dp[i][j] = UP[i][j] + DOWN[i][j] + LEFT[i][j] + RIGHT[i][j].

*/
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] up = new int[m][n];
        int[][] down = new int[m][n];
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];
        
        // UP
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 'W') {
                    up[i][j] = grid[i][j] == 'E' ? 1 : 0;
                    up[i][j] += i - 1 >= 0 ? up[i - 1][j] : 0;
                }
            }
        }

        // DOWN
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 'W') {
                    down[i][j] = grid[i][j] == 'E' ? 1 : 0;
                    down[i][j] += i + 1 < m ? down[i + 1][j] : 0;                    
                }
            }
        }
        
        // LEFT
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 'W') {
                    left[i][j] = grid[i][j] == 'E' ? 1 : 0;
                    left[i][j] += j - 1 >= 0 ? left[i][j - 1] : 0;
                }
            }
        }

        // RIGHT
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] != 'W') {
                    right[i][j] = grid[i][j] == 'E' ? 1 : 0;
                    right[i][j] += j + 1 < n ? right[i][j + 1] : 0;
                }
            }
        }
        
        
        // DP
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    max = Math.max(max, up[i][j] + down[i][j] + left[i][j] + right[i][j]);
                }
            }   
        }
        return max;
    }
}


/*
Method2:
- Inspired by: https://leetcode.com/problems/bomb-enemy/discuss/83387/Short-O(mn)-time-O(n)-space-solution
- Analyize the problem: need to add up 2 directions at any given point.
    - Notice 1: if I traverse row by row, each colSum at a specific col j is likely to be the same
        - Unless there is a Wall in last row, so we have to calclate the col sum starting from current row, below the Wall
    - Notice 2: for row it is the same:
        - If I in a new spot row[i][j], where (i-1) is Wall, i need to sum row from 0
- we will process each point:
    - process row by row and add up row sum
    - buffer col[j] in an array vertically so we can resue
    - make sure to recalculate row sum or col sum if last row index or last col index is Wall
*/
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[] colSum = new int[n];
        int max = 0;
        int row = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {                
                if (j == 0 || grid[i][j - 1] == 'W') {
                    row = 0;
                    for (int k = j; k < n && grid[i][k] != 'W'; k++) {
                        row += grid[i][k] == 'E' ? 1 : 0;
                    }
                }
                
                if (i == 0 || grid[i - 1][j] == 'W') {
                    colSum[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++) {
                        colSum[j] += grid[k][j] == 'E' ? 1 : 0;
                    }
                }
                if (grid[i][j] == '0') max = Math.max(max, colSum[j] + row);
            }
        }
        return max;
    }
}




```