/*
Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.

This matrix has the following properties:

    * Integers in each row are sorted from left to right.

    * Integers in each column are sorted from up to bottom.

    * No duplicate integers in each row or column.

Example
Consider the following matrix:

[

    [1, 3, 5, 7],

    [2, 4, 7, 8],

    [3, 5, 9, 10]

]

Given target = 3, return 2.

Challenge
O(m+n) time and O(1) extra space



Thinking process:
Every right element > left element
Every down element > up element
We can start looking from the left-bottom: compare right and left to trace upwards.
*/

//Start from left-bottom, find the lowest first occurrence. Not correct for this problem tho:
public int searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target) {
        if (matrix == null || matrix.size() == 0) {
            return 0;
        }
        
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        int r = rows - 1;
        int c = 0;
        int current;
        
        while (r >= 0 && c < cols) {
            current = matrix.get(r).get(c);
            if (current == target) {
                return cols * r + c + 1;
            } else if (current < target) {
                c++;
            } else if (current > target) {
                r--;
            }
        }
        
        return 0;
    }


//Need to find all occurrences from top-left. Count them all:
public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @param: A number you want to search in the matrix
     * @return: An integer indicate the occurrence of target in the given matrix
     */
    public int searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target) {
        if (matrix == null || matrix.size() == 0) {
            return 0;
        }
        
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        int r = rows - 1;
        int c = 0;
        int count = 0;
        
        while (r >= 0 && c < cols) {
            int current = matrix.get(r).get(c);
            //If found:
            if (current == target) {
                count++;
                //Can go either up or down:
                if (c + 1 < cols) {
                    c++;
                } else if (r - 1 >= rows) {
                    r--;
                } else {
                    //If at bound, just return result.
                    return count;
                }
            //Not Found:
            } else if (current < target) {
                c++;
            } else if (current > target) {
                r--;
            }
        }
        
        return count;
    }
}

