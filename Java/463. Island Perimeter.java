E
tags: Hash Table
time: O(n)

#### Brutle, Count Blocks and Walls
- 每个格子 +4 个墙;
- 每个shared的墙要减去: 从每个island走去另外一个, 都-1 (最终没面墙, -2)

#### Hash Table
- 把每个block相连的block全部存在以当下block为key的list里面. 那么这里需要把2D坐标, 转化成一个index.
- 最后得到的map, 所有的key-value应该都有value-key的反向mapping, 那么就可以消除干净, 每一次消除就是一个shared wall.
- 一点点optimization: DFS去找所有的island, 如果island的图非常大, 而island本身不大,那么适合optimize.
- 但是整体代码过于复杂. 不建议写.


```
/*
You are given a map in form of a two-dimensional integer grid 
where 1 represents land and 0 represents water. 
Grid cells are connected horizontally/vertically (not diagonally). 
The grid is completely surrounded by water, and there is exactly one island 
(i.e., one or more connected land cells). 

The island doesn't have "lakes" (water inside that isn't connected to the water around the island). 
One cell is a square with side length 1. 

The grid is rectangular, width and height don't exceed 100. 
Determine the perimeter of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
https://leetcode.com/problems/island-perimeter/description/
*/

/*
DFS all of the 1's
1. each island gives 4 walls
2. one side of shared wall will cause -1. Each shared wall will be counted 2 times, from each size of the wall.
*/
class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null) return 0;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 1) continue;
                count += 4;
                for (int k = 0; k < dx.length; k++) {
                    int mX = i + dx[k], mY = j + dy[k];
                    if (validate(grid, mX, mY)) count -= 1;
                }
            }    
        }
        return count;
    }

    private boolean validate(int[][] grid, int x, int y) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1;
    }
}

// incomplete version using DFS. The code became complicated and not necessary.
/*
class Solution {
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    int blocks = 0;
    int walls = 0;
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(map, grid, i, j);
                    return blocks * 4 - walls * 2;
                }
            }    
        }
        return 0;
    }
    
    private void dfs(Map<Integer, ArrayList<Integer>> map, int[][] grid, int x, int y) {
        if (grid[x][y] != 1) {
            return;
        }

        grid[x][y] = -1;
        int index = calculateIndex(grid[0].length, x, y);
        if (!map.containsKey(index)) {
            map.put(index, new ArrayList<>());    
        }

        for (int i = 0; i < dx.length; i++) {
            int mX = x + dx[i];
            int mY = y + dy[i];
            if (mX >= 0 && mY >= 0 && mX < grid.length && mY < grid[0].length && grid[mX][mY] != 0) {
                map.put(index, calculateIndex(grid[0].length, mX, mY));
                dfs(grid, mX, mY);
            }
        }       
    }
    
    private calculateIndex(int width, int x, int y) {
        return width * x + y;
    }
}*/

```
