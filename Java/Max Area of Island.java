E

虽然Easy, 但用到DFS最基本的想法.
1. dive deep
2. mark VISITED
3. sum it up

更要注意, 要从符合条件value==1的地方开始dfs.
对于什么island都没有的情况，area应该位0， 而不是Integer.MIN_VALUE, 问清楚考官那小伙, 别写顺手。

```
/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.
*/

/*
Thoughts:

DFS to all directions: 
dx = {0, 1, 0, -1} 
dy = {1, 0, -1, 0}
1. Each DFS deep dive returns the result area
2. Compare result with max area
Note: when island is found and counted into area, it needs to be flipped to other digits just to avoid revisiting.
*/

class Solution {
    private static int[] DX = {0, 1, 0, -1};
    private static int[] DY = {1, 0, -1, 0};
    private static int VISITED = -1;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }    
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
            grid[x][y] = VISITED;
            int currentLevelSum = 1;
            for (int i = 0; i < DX.length; i++) {
                currentLevelSum += dfs(grid, x + DX[i], y + DY[i]);
            }
            return currentLevelSum;
        }
        return 0;
    }
}


```