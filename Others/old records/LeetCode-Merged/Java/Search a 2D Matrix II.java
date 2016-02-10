自己的想法比较老爷车啊。其实一旦想透了就有救。下次试一试自己来画一画来approach。
题目想法：在右上角开刷，大小不等那么就往唯一可以去的两个方向移动，绝不回头，总可以找到。
对题目描述的情景和性质要认真考虑清楚再下手。。
```
/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.

Tags: Divide and Conquer, Binary Search
Similar Problems: (M) Search a 2D Matrix

*/
/*
Attempt3, http://blog.csdn.net/xudli/article/details/47015825
Do a run through smartly. Think about in a 1D array, we only have 2 directions, which is easy: if less, addup; if more, minus some.
In this relationship, the only we know is: every row, rightside is greater; every column, below is greater.
Magic from that post: set the starting point on right-up-corner, where you only have 2 directions to go:
1. If target is less than curr, move left, j--. (Upper place has nothing, don't go up)
2. If target is more than curr, move down, i++. (right side has ntohing, don't go right)
In next iteration, cut off upper row and right column. Here is the idea:
In 1: target < curr, then target < j+1 , then can put j+1 column on a throw pending
In 2: target > curr, then target > i - 1 row, then can put i - 1 column on a throw pending.
Combine both cases, the upper row and right column will not impact our next iteration, so just imagine we cut them off after dealing with them.
Therefore, in next iteration, we only need to care about the same case again

*/
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
          return false;
        }    
        int i = 0;
        int j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
          if (matrix[i][j] == target) {
            return true;
          } else if (target > matrix[i][j]) {
            i++;
          } else {
            j--;
          }
        }
        return false;
    }
}



/*
Attempt2, Thoughts:
1. Targe has to live in a column, so find this column first
2. If target > columnA(i), and target < columA(i+1), then target must be in columnA.
3. Do a binary search on columnA.
However, there is simple counter case:
[
1,  2,  3,  4,  5,
100,101,102,103,104
]
Look for 100.
Won't work in below code, because when I do binary search on 1st row, it goes all across the row but can't identify that the target is actually in front columns. This is beacuse, the end of 1st row does not have a meaningful relationship with the head of 2nd row. 
It fails on locating the correct column. And code is too long ...
*/
/*

public class Solution {
      public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
          return false;
        }    
        
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0;
        int end = matrix[0].length - 1;
        int i = 0;
        int j = 0;
        int mid;
        int col = -1;
        //Find column
        while (start + 1 < end) {
          mid = start + (end - start) / 2;
          j = mid;
          if (matrix[i][j] == target) {
            return true;
          } else if (j + 1 < n && matrix[i][j] < target && target < matrix[i][j + 1]) {
            col = j;
            break;
          } else if (j - 1 >= 0 && matrix[i][j - 1] < target && target < matrix[i][j]) {
            col = j - 1;
            break;
          } else if (target > matrix[i][j]) {
            start = mid;
          } else if (target < matrix[i][j]) {
            end = mid;
          }
        }
        if (col == -1) {//1
          j = end;
          col = target >= matrix[i][j] ? end : start;
        }
        
        //Find target
        start = 0;
        end = m - 1;
        j = col;
        while (start + 1 < end) {
          mid = start + (end - start) / 2;
          i = mid;
          if (matrix[i][j] == target) {
            return true;
          } else if (target > matrix[i][j]) {
            start = mid;
          } else if (target < matrix[i][j]) {
            end = mid;
          }
        }//End while
        return matrix[start][j] == target || matrix[end][j] == target;
      }
}

*/

/*
Attempt1, Thoughts:
But not correct: because the mid point calculation is based on linear relationship between start and end. In this problem, the relationship is not linear.
The following idea only considers the way to jump to different spots, but didn't take care of how to generate the mid index in the first place.
Each spot has 3 direction to go for larget element, and another 3 directions to go for smaller element.
If match, return true;
If target is greater than mid, move start to large element around mid
  if target > (i+1,j+1) : start = (i+1, j+1)
  if target > (i,j+1) : start = (i, j+1)
  if target > (i+1,j) : start = (i+1, j)
If target is less than mid, move end to small element around mid
  if target < (i-1,j-1) : end = (i-1,j-1) 
  if target < (i,j-1) : end = (i, j-1)
  if target < (i-1,j) : end = (i-1, j)
init:
start = 0
end = m*n - 1;

However, mid = start + (end - start) / 2; won't work here.
*/


```