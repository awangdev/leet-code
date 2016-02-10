曾经有过。2D转1D。注意 行 = index/宽度 ； 列 = index%宽度。
```
/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.

Tags: Array, Binary Search
Similar Problems: (M) Search a 2D Matrix II

*/

/*
Thought:
Binary search.
Treat 2D matrix as a 1D array. spot = m * i + j;
start = 0;
end = m * n - 1;
*/
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        	return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0;
        int end = m * n - 1;
        int mid;
        int i;
        int j;
        while (start + 1 < end) {
        	mid = start + (end - start) / 2;
        	i = mid / n;
        	j = mid % n;
        	if (matrix[i][j] == target) {
        		return true;
        	} else if (matrix[i][j] > target) {
        		end = mid;
        	} else {
        		start = mid;
        	}
        }
        int s1 = start / n;
        int s2 = start % n;
        int e1 = end / n;
        int e2 = end % n;

        return matrix[s1][s2] == target || matrix[e1][e2] == target;
    }
}
```