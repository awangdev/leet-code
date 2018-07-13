M
1531465969
tags: DP, Math

具体看题目: count # of valid rectangles (four corner are 1) in a grid[][].

#### basic thinking + Math
- Fix two rows and count matching columns
- Calculate number rectangles with `combination` concept:
- total number of combinations of pick 2 points randomly: count * (count - 1) / 2

#### DP
- TODO. HOW?

#### Brutle
- O(m^2 * n^2), times out

```
/*
Given a grid where each entry is only 0 or 1, find the number of corner rectangles.

A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle.
Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.

 

Example 1:

Input: grid = 
[[1, 0, 0, 1, 0],
 [0, 0, 1, 0, 1],
 [0, 0, 0, 1, 0],
 [1, 0, 1, 0, 1]]
Output: 1
Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
 

Example 2:

Input: grid = 
[[1, 1, 1],
 [1, 1, 1],
 [1, 1, 1]]
Output: 9
Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
 

Example 3:

Input: grid = 
[[1, 1, 1, 1]]
Output: 0
Explanation: Rectangles must have four distinct corners.
 

Note:

The number of rows and columns of grid will each be in the range [1, 200].
Each grid[i][j] will be either 0 or 1.
The number of 1s in the grid will be at most 6000.
*/

// O(M^2 * N), math: Combination concept
class Solution {
    public int countCornerRectangles(int[][] grid) {
        int rst = 0;
        if (validate(grid)) return rst;
        int m = grid.length, n = grid[0].length;
        
        // find 2 rows O(M^2), and pick columns
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m; j++) {
                int count = 0;
                for (int k = 0; k < n; k++) {
                    if (grid[i][k] == 1 && grid[j][k] == 1) count++;
                }
                if (count > 0) rst += count * (count - 1) / 2;   // total # of combination of 2 items: n(n-1)/2
            }
        }
        return rst;
    }
    private boolean validate(int[][] grid) {
        return grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0;
    }
}

// Brutle, timesout
class Solution {
    public int countCornerRectangles(int[][] grid) {
        int rst = 0;
        if (validate(grid)) return rst;
        int m = grid.length, n = grid[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) { // grid[i][j], starting point
                for (int h = i + 1; h < m; h++) { // pick right-end corner
                    for (int k = j + 1; k < n; k++) {
                        rst += validateRect(grid, i, j, h, k) ? 1 : 0;
                    }
                }
            }
        }
        return rst;
    }
    
    private boolean validateRect(int[][] grid, int i, int j, int h, int k) {
        return grid[i][j] == 1 && grid[h][k] == 1 && grid[i][k] == 1 && grid[h][j] == 1;
    }

    private boolean validate(int[][] grid) {
        return grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0;
    }
}

```