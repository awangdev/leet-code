M
tags: DP, PreSum
time: O(mn) build, O(1) query
space: O(mn)

#### Method1: rangeSum/caching
- build rangeSum[i][j]: square range sum from (0,0) to (i,j), O(mn) to init
- query: time O(1)

#### Method2: presum/caching
- build rowPreSum[i][j]: row i sum from [0 ~ j], O(mn) to init
- callign takes O(m); space O(mn)


```
/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/

/*
- Method1: rangeSum/caching
- build rangeSum[i][j]: range sum from (0,0) to (i,j), O(mn) to init
- query: timeO(1), 
*/
class NumMatrix {
    int[][] rangeSum;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return;
        int m = matrix.length, n = matrix[0].length;
        rangeSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rangeSum[i + 1][j + 1] = rangeSum[i + 1][j] + rangeSum[i][j+1] + matrix[i][j] - rangeSum[i][j];
            }    
        }
    }
   
    public int sumRegion(int row1, int col1, int row2, int col2) {        
        int sum = 0;
        sum += rangeSum[row2+1][col2+1] - rangeSum[row1][col2+1] - rangeSum[row2+1][col1]
                + rangeSum[row1][col1];
        return sum;
    }
}


/*
- Method2:  presum/caching
- build rowPreSum[i][j]: row i sum from [0 ~ j], O(mn) to init
- callign takes O(m); space O(mn)
*/
class NumMatrix {
    int[][] preSum;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return;
        int m = matrix.length, n = matrix[0].length;
        preSum = new int[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i][j + 1] = preSum[i][j] + matrix[i][j];
            }    
        }
    }
   
    public int sumRegion(int row1, int col1, int row2, int col2) {        
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += preSum[i][col2 + 1] - preSum[i][col1];
        }
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
```