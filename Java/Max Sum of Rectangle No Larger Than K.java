H
1528567587
tags: Binary Search, BST, TreeSet, DP, Queue, Array

给定一个非空的二维矩阵matrix与一个整数k，在矩阵内部寻找和不大于k的最大矩形和。

#### BST, Array, preSum
- 将问题reduce到: row of values, find 1st value >= target.
- 1. loop over startingRow; 2. loop over [startingRow, m - 1]; 3. Use TreeSet to track areas and find boundary defined by k.
- When building more rows/cols the rectangle, total sum could be over k: 
- when it happens, just need to find a new starting row or col, 
- where the rectangle area can reduce/remain <= k
- 找多余area的起始点: extraArea = treeSet.ceiling(totalSum - k). 也就是找 减去k 后 起始的/左边的area.
- 去掉这些左边的起始area, 剩下的就 <=k.    (num - extraArea)
- 为什么用TreeSet: area的大小无规律, 并且要找 >= 任意值 的第一个value. 给一串non-sorted数字, 找 >= target的数, 如果不写binary search, 那么用BST最合适
- O(m^2*nlogn)

#### 思想
- 从最基本的O(m^2*n^2) 考虑: 遍历 startingRow/startingCol
- rectangle? layer by layer? 可以想到Presum的思想, 大于需要的sum的时候, 减掉多余的部分
- 如何找到多余的area? 那么就是search: 把需要search的内容存起来, 可以想到用BST(TreeSet), 或者自己写Binary Search.

```
/*
Given a non-empty 2D matrix matrix and an integer k, 
find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 
is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

thanks to: http://bookshadow.com/weblog/2016/06/22/leetcode-max-sum-of-sub-matrix-no-larger-than-k/
*/

/*
Thoughts:
Start from (0,0), move -> right, down, circle rectangle and compare area.
- start row shifts from [0 ~ m - 1], since rectangle may start from any row
- start column can shift from [0 ~ n - 1], since rectangle may start from any col.
- 1. loop over startingRow; 2. loop over [startingRow, m - 1]; 3. Use TreeSet to track boundary defined by k.
- When building more rows/cols the rectangle, total sum could be over k: when it happens, just need to find a new starting row or col, where the rectangle area can reduce/remain <= k
- 找多余area的起始点: extraArea = treeSet.ceiling(totalSum - k). 也就是找 减去k 后 起始的/左边的area.
- 去掉这些左边的起始area, 剩下的就 <=k.    (num - extraArea)
- 为什么用TreeSet: area的大小无规律, 并且要找 >= 任意值 的第一个value. 给一串non-sorted数字, 找 >= target的数, 如果不写binary search, 那么用BST最合适
*/

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        // check input
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return -1;
        }
        // since O(row^2), make sure m represents the smaller length
        int m = Math.min(matrix.length, matrix[0].length);
        int n = Math.max(matrix.length, matrix[0].length);
        boolean rowFirst = matrix.length < matrix[0].length;
        int max = Integer.MIN_VALUE;
        // 3 loop layers: 
        // 1. int[] preSum; 2. TreeSet, store all area for curr iteration, 3. newSum in new row
        for (int row = 0; row < m; row++) {
            int[] preSum = new int[n];
            for (int i = row; i < m; i++) { // iteration
                TreeSet<Integer> areaSet = new TreeSet<>();
                int newSum = 0; // adding up with preSum[j]
                for (int j = 0; j < n; j++) {
                    preSum[j] += rowFirst? matrix[i][j] : matrix[j][i];
                    newSum += preSum[j];
                    if (newSum <= k) max = Math.max(max, newSum);
                    Integer extraArea = areaSet.ceiling(newSum - k);
                    if (extraArea != null) {
                        max = Math.max(max, newSum - extraArea);
                    }
                    areaSet.add(newSum);
                }
            }
        }
        return max == Integer.MIN_VALUE ? -1 : max;
    }
}


```