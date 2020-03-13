M
1534346957
tags: Array

#### Move forward till end
- Similar concept as `The Maze`: keep walking until hit wall, turn back
- fix direction `dx[direction % 4]`

```
/*
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

/*
Use while loop, reach end and stop
*/
class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    public int[][] generateMatrix(int n) {
        int[][] grid = new int[n][n];
        int step = 1, i = 0, j = 0, direction = 0;
        grid[i][j] = step++;
        while (step <= n * n) {
            int x = dx[direction % 4];
            int y = dy[direction % 4];
            i += x;
            j += y;
            while (i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 0) {
                grid[i][j] = step++;
                i += x;
                j += y;
            }
            i -= x;
            j -= y;
            direction++;
        }
        return grid;
    }
}
```