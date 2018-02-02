E
1517561820

似乎没什么算法特点, 就是array基本运算, 然后分割成一个helper function去做重复计算, 剪短代码.
注意check MxN 的分界线.

```
/*
A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 

Example 1:

Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
Output: True
Explanation:
1234
5123
9512

In the above grid, the diagonals are "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]", 
and in each diagonal all elements are the same, so the answer is True.
Example 2:

Input: matrix = [[1,2],[2,2]]
Output: False
Explanation:
The diagonal "[1, 2]" has different elements.
Note:

matrix will be a 2D array of integers.
matrix will have a number of rows and columns in range [1, 20].
matrix[i][j] will be integers in range [0, 99].

*/

/*
Thoughts:
Basic implementation, 3 sections:
1. x,y=0: center diagonal
2. x=1~n, y=0: left-bottom section
3. x=0, y=1~n: right-top section
*/
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            if(!validation(i, 0, matrix)) {
                return false;   
            }
        }
        
        for (int j = 0; j < m; j++) {
            if(!validation(0, j, matrix)) {
                return false;   
            }
        }
        
        return true;
    }
    
    private boolean validation(int x, int y, int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = x, j = y; i < n && j < m; i++, j++) {
            if (matrix[i][j] != matrix[x][y]) {
                return false;
            }
        }
        return true;
    }
}
```