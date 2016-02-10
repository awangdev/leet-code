/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3


Tags: Depth-first Search, Breadth-first Search, Union Find
Similar Problems: (M) Surrounded Regions, (M) Walls and Gates


*/

/*
Attempt2: Use mark to mark all adjacent island.
1. set initial mark = 1
2. double for loop on each (i,j).
3. mark method: 
	if a new island found, 
		a. mark it with mark 
		b. try to mark all adjacent spot
		c. return true
	if no new island found, return false;
4. based on returnning value of mark at the double-for loop, increase mark or not.

return mark - 1, because we set a initial mark == 1, which eases the marking process. but it always has an extra 1.
*/

public class Solution {
	public int[][] matrix;
	public int mark = 1;
    public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
     		return 0;
     	}  
     	matrix = new int[grid.length][grid[0].length];

     	for(int i = 0; i < grid.length; i++) {
     		for (int j = 0; j < grid[0].length; j++) {
     			mark = mark(i, j, grid)? (mark + 1) : mark;
     		}
     	}
     	return mark - 1;
    }

    public boolean mark(int i, int j, char[][] grid) {
    	if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length) {
    		if (matrix[i][j] == 0 && grid[i][j] == '1') {
    			matrix[i][j] = mark;
    			mark(i - 1, j, grid);
    			mark(i + 1, j, grid);
    			mark(i, j - 1, grid);
    			mark(i, j + 1, grid);
    			return true;
    		}
    	}
    	return false;
    }
}


/*
1st attempt, Not working.
Thoughts:
Ues DP to store DP[i][j]: till row i, and element j on row i, what is the number of island we had.

public class Solution {
    public int numIslands(char[][] grid) {
     	if (grid == null || grid.length == 0 || grid[0].length == 0) {
     		return 0;
     	}   
     	int[][] DP = new int[grid.length][grid[0].length];
     	DP[0][0] = grid[0][0] == '1' ? 1 : 0;
     	int max = DP[0][0];
     	int r = grid.length;
     	int c = grid[0].length;

     	for (int i = 0; i < r; i++) {
     		for (int j = 0; j < c; j++) {
     		    if (i == 0 && j == 0) {
     		        continue;
     		    }
     			if(grid[i][j] == '0') {
     				DP[i][j] = max;
     			} else {// grid[i][j] == '1'
     			    if (i == 0 && r == 1) {
     			        if (j - 1 >= 0 && DP[i][j - 1] != 0 && grid[i][j - 1] != '0') {
     			            DP[i][j] = max;
     			        } else {
     			            DP[i][j] = max + 1;
     			            max = max + 1;
     			        }
     			        continue;
     			    }
     			    if (j == 0) {
     			         if ((j + 1 < c && grid[i][j + 1] != '0') || (i - 1 >= 0 && grid[i - 1][j] != '0')) {
     			            DP[i][j] = max;
     			        } else {
     			            DP[i][j] = max + 1;
     			            max = max + 1;
     			        }
     			        continue;
     			    }
     				//DP[i][j] != 0 && Check UP,DOWN, RIGHT, LEFT == 1? 
     				if ((i - 1 >= 0 && DP[i - 1][j] != 0 && grid[i - 1][j] != '0') ||
     					(i + 1 < r  && DP[i + 1][j] != 0 && grid[i + 1][j] != '0') ||
						(j - 1 >= 0 && DP[i][j - 1] != 0 && grid[i][j - 1] != '0') ||
						(j + 1 < c  && DP[i][j + 1] != 0 && grid[i][j + 1] != '0') ) {
     						DP[i][j]  = max;
					}else {//NO adjacent is 1
						DP[i][j] = max + 1;
						max = max + 1;
					}
     			}

     		}
     	}
 
     	return max;
    }
}

*/
