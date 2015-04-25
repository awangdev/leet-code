/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note
You can only move either down or right at any point in time.

Example
Tags Expand 
Dynamic Programming

Thinking process:
1. Check null, lenght == 0
2. Min Sum = sum of array. Initialization is a bit different, for example: each row element is added up from previous elemenet. (Not simple value assign from given grid)
	- Assign (0,0) to grid[0][0]
	- Row 1st row and 1st col, add up values 
3. f(x,y) = sum of path value. f(x,y) = Math.Min(f(x-1,y), f(x, y-1))
4. return f(r-1)(c-1)

*/


public class Solution {
    /**
     * @param grid: a list of lists of integers.
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[][] matrix = new int[row][col];
        matrix[0][0] = grid[0][0];
        //Add up for 1st row && 1st col
        for (int i = 1; i < row; i++) {
            matrix[i][0] = matrix[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < col; j++) {
            matrix[0][j] = matrix[0][j - 1] + grid[0][j];
        }
        //Evaluate
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i][j - 1])
                                + grid[i][j];
            }
        }
        return matrix[row - 1][col - 1];
        
    }
}


