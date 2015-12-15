可以用union-find， 就像Number of island II 一样。
只不过这个不Return list, 而只是# of islands

可以自己再做一次。
```
/*
Given a boolean 2D matrix, find the number of islands.

Example
Given graph:

[
  [1, 1, 0, 0, 0],
  [0, 1, 0, 0, 1],
  [0, 0, 0, 1, 1],
  [0, 0, 0, 0, 0],
  [0, 0, 0, 0, 1]
]
return 3.

Note
0 is represented as the sea, 1 is represented as the island. 
If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

*/

/*
  12.12.2015 recap
  We are checking if a sets of adjacent nodes are int the same set
  We union all neighbors
  Generate the list of islands: actually return the # of islands

  AND yes, this is doable. Number of island II is using union-find
*/

/*

Thoughts:
1. Use a 2D integer matrix to map the islands, by default the 2D [][] is all 0.
2. When the matrix point is 0, means it has not been checked. Check it against the grid.
3. Whenever there is a match, assign mark to that point.
4. Only increase mark when all the recursion finishes.
5. return mark - 1

*/
public class Solution {
    int[][] matrix;
    int mark;
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length ==0 || grid[0].length == 0) {
            return 0;
        }
        matrix = new int[grid.length][grid[0].length];
        mark = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                mark = check(i, j, mark, grid) ? (mark + 1) : mark;
            }
        }
        
        return mark - 1;
        }

        public boolean check(int x, int y, int mark, boolean[][] grid) {
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length) {
                if (matrix[x][y] == 0 && grid[x][y]) {
                    matrix[x][y] = mark; 
                    check(x + 1, y, mark, grid); 
                    check(x - 1, y, mark, grid); 
                    check(x, y + 1, mark, grid);
                    check(x, y - 1, mark, grid);
                    return true;
                }
            } 
            return false;
        }
}
```