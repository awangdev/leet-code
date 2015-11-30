2D转1D。
Binary Search
```
/*
Write an efficient algorithm that searches for a value in an m x n matrix.

This matrix has the following properties:

    * Integers in each row are sorted from left to right.

    * The first integer of each row is greater than the last integer of the previous row.

Example
Consider the following matrix:

[

    [1, 3, 5, 7],

    [10, 11, 16, 20],

    [23, 30, 34, 50]

]

Given target = 3, return true.

Challenge
O(log(n) + log(m)) time

Tags Expand 
Binary Search Matrix


*/

/*
    Thoughts: 11.29.2015
    The problem is updated on LintCode. Practice again.
    Convert into 1D array. Binary Search
*/
public class Solution {
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0;
        int end = row * col - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start)/2;
            int num = matrix[mid/col][mid%col];
            if (target == num) {
                return true;
            } else if (num < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return (matrix[start/col][start%col] == target || matrix[end/col][end%col] == target);
    }
}








/*
Thinking process:
0. The elements are unique, no duplicates
Treat it as a 1-D array
Do binary search
    1. check head element
    2. find the element until only start & end element left
    3. Deal with start and end
*/

public class Solution {
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
     //Turn 2D array into 1D array and perform regular binary search
    public boolean searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target) {
        if(matrix.size() == 0) {
            return false;
        }
        // write your code
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        int start = 0;
        int end = rows * cols - 1;
        int mid;
        
        while (start + 1 < end) {//This will leave exact 1 start and 1 end element for next code section
            mid = start + (end - start) / 2;
            int midVal = matrix.get(mid / cols).get(mid % cols); //trick to get element
            
            if (midVal == target) {
                return true;
            } else if (midVal < target) {
                start = mid;
            } else {
                end = mid;
            }
        }//end while
        
        //Deal with the 1 start and 1 end element
        int startVal = matrix.get(start / cols).get(start % cols); 
        int endVal = matrix.get(end / cols).get(end % cols); 
        if (startVal == target || endVal == target) {
            return true;
        } else {
            return false;
        }
    }
}


```