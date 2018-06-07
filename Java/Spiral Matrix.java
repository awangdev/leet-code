M
1528298716
tags: Array, Enumeration

从(0,0)坐标, 走完spiral matrix, 把结果存在list里.

#### DX, DY
- Basic implementation, array, enumeration
- 写一下position前进的方向: RIGHT->DOWN->LEFT->UP
- 用一个direction status 确定方向
- 写一个compute direction function 改变方向 `(direction + 1) % 4`
- `boolean[][] visited` 来track走过的地方

```
/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
*/

/*
Thoughts:
- Keep visited
- keep moving until hit visited, then turn
*/class Solution {
    int[] dx = {0, 1, 0, -1}; // RIGHT->DOWN->LEFT->UP
    int[] dy = {1, 0, -1, 0};

    public List<Integer> spiralOrder(int[][] matrix) {
        // check edge case
        List<Integer> rst = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return rst;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        // handle single col case
        if (n == 1) {
            for (int i = 0; i < m; i++) {
                rst.add(matrix[i][0]);
            }
            return rst;
        }

        // construct boolean visited[][], dx{}, dy{}
        boolean[][] visited = new boolean[m][n];

        // while keep moving until count == m*n
        int i = 0, x = 0, y = 0;
        int direction = 0;
        while (i < m * n) {
            i++;
            rst.add(matrix[x][y]);
            visited[x][y] = true;

            // compute x/y based on current direction
            direction = computeDirection(visited, x, y, direction);
            x += dx[direction];
            y += dy[direction];
        }

        return rst;
    }

    private int computeDirection(boolean[][] visited, int x, int y, int currDirection) {
        int nextX = x + dx[currDirection];
        int nextY = y + dy[currDirection];
        if (nextX >= 0 && nextX < visited.length && nextY >= 0 && nextY < visited[0].length && !visited[nextX][nextY]) {
            return currDirection;
        }
        return (currDirection + 1) % 4;
    }
}

```