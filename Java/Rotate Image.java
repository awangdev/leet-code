M
tags: Array, Enumeration

#### 找公式规律
- 找到个转角度的规律公式: r = c; c = (w - r)
- 用temp variable, swap in place.

```
/*
You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).

Have you met this question in a real interview? Yes
Example
Given a matrix

[
    [1,2],
    [3,4]
]
rotate it by 90 degrees (clockwise), return

[
    [3,1],
    [4,2]
]
Challenge
Do it in-place.

Tags Expand 
Cracking The Coding Interview Matrix
*/


//in matrix, to find next position: r = c; c = (w - r). Work on the equation => oldR = w - c, oldC = r 
//In pace: do loop, change 4 at once. do a quater of the matrix
public class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int width = matrix.length;
        for (int i = 0; i < width/2; i++) {
            for (int j = 0; j < Math.ceil(width/2.0); j++) {
               int temp = matrix[i][j];
               matrix[i][j] = matrix[width - 1 - j][i];
               matrix[width - 1 - j][i] = matrix[width - 1 - i][width - 1 - j];
               matrix[width - 1 - i][width - 1 - j] = matrix[j][width - 1 - i];
               matrix[j][width - 1 - i] = temp;
            }
        }
    }
}
```