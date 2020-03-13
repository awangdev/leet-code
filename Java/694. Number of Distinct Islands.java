M
tags: DFS, Hash Table
time: O(n)
space: O(n)

#### DFS + HashSet
- DFS can find # of island, just like `200.Number of Islands`, aim to count total
- We need to map same-shap land
    - One approach: print the **footprint** starting from coordinate (0, 0)
    - Another approach: print the actual island in its boundary, like a QR code. (too hard to code, skip)
- Footprint approach: 
    - 1. always assume a newly found islands starts from (0, 0)
    - 2. take 4 direction from init pos and keep printing the footprint
    - 3. Since we always visit nodes from top->right->nextrow, we always visit top-left cornor of a new island, and the footprint will be identical
        - Otherwises, if needed, we can sort the footprint and output the hash
- time: O(n), visit all
- space: O(n), store footprint, and dfs stacks worst case visit all nodes 

```
/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.
*/
/*
- DFS can find # of island.
- We need to map same-shap land
    - One approach: print the footprint starting from coordinate (0, 0)
*/
class Solution {
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int numDistinctIslands(int[][] grid) {
        if (grid == null) return 0;
        int m = grid.length, n = grid[0].length;
        Set<String> count = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuffer sb = new StringBuffer();
                    dfs(grid, sb, i, j, 0, 0);
                    count.add(sb.toString());
                }
            }
        }
        return count.size();
    }
    
    private void dfs(int[][] grid, StringBuffer sb, int x, int y, int xPos, int yPos) {
        if (validateInput(grid, x, y)) return;
        grid[x][y] = 0;
        sb.append(xPos).append("-").append(yPos).append(",");
        for (int i = 0; i < dx.length; i++) {
            dfs(grid, sb, x + dx[i], y + dy[i], xPos+dx[i], yPos+dy[i]);
        }
    }

    private boolean validateInput(int[][] grid, int x, int y) {
        return x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0;
    }
}
```