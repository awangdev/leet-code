E

读例子理解题意.
理清counter case. Basic implementation

```
/*
In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.

You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.

The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.

If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

Example 1:
Input: 
nums = 
[[1,2],
 [3,4]]
r = 1, c = 4
Output: 
[[1,2,3,4]]
Explanation:
The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
Example 2:
Input: 
nums = 
[[1,2],
 [3,4]]
r = 2, c = 4
Output: 
[[1,2],
 [3,4]]
Explanation:
There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
Note:
The height and width of the given matrix is in range [1, 100].
The given r and c are all positive.
*/

/*
Thoughts:
1. Original row * col needs to equal with new r*c: total # of elements should stay the same. Otherwise, return original
2. If the total number of items cannot be shaped into evenly into r/c form, return original
3. Maintain the new row/col index outside of nums for loop.
*/
class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int totalElements = nums.length * nums[0].length;
        if (totalElements != r * c || totalElements % r != 0) {
            return nums;
        }
        final int[][] result = new int[r][c];
        int newR = 0;
        int newC = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                result[newR][newC] = nums[i][j];
                newC++;
                if (newC == c) {
                    newC = 0;
                    newR++;
                }
            }
        }
        return result;
    }
}

```