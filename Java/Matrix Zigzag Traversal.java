E

分析4个step:right, left-bottom,down,right-up    
implement时注意index.有点耐心

```
/*
Matrix Zigzag Traversal

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in ZigZag-order.

Example
Given a matrix:

[
  [1, 2,  3,  4],
  [5, 6,  7,  8],
  [9,10, 11, 12]
]
return [1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]

Tags Expand 
LintCode Copyright Matrix
*/

/*
	Always have the 4 states: right 1 step, left-bottom corner, right 1 step, up-right-corner. 
	If any of the 4 steps can't proceed because of hitting the wall, just go down 1 step. 

*/

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @return: an array of integers
     */ 
    public int[] printZMatrix(int[][] matrix) {
        int[] rst = null;
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
        	return rst;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        rst = new int[n * m];
        if (matrix.length == 1) {
            return matrix[0];
        }
        int i = 0, j = 0;
        int ind = 0;
        rst[ind] = matrix[i][j];
        ind++;
        while (ind < rst.length) {
        	//Right 1 step, or down
    		if (j + 1 < m || i + 1 < n) {
    			if (j + 1 < m) j++;
    			else if (i + 1 < n) i++;
    			rst[ind++] = matrix[i][j];
    		}
    		//Move left-bottom:
    		while (j - 1 >= 0 && i + 1 < n) {
				rst[ind++] = matrix[++i][--j];
    		}
        	//Move down, or right
    		if (j + 1 < m || i + 1 < n) {
    			if (i + 1 < n) i++;
    			else if (j + 1 < m) j++;
    			rst[ind++] = matrix[i][j];
    		} 
        	//Move right-up:
    		while (j + 1 < m && i - 1 >= 0) {
    			rst[ind++] = matrix[--i][++j];
    		}
        }//end while
        return rst;
    }
}

```