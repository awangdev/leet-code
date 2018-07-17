M
1531810563
tags: Hash Table
time: O(mnk), where `m = A.row`, `n = B.col`, `k = A.col = B.row`
space: O(1) extra

给两个matrics, 做乘积. 注意, 是sparse matrix (特点: 很多0).

#### Hash Table
- Recall matric multiplication rules: result[i][j] = sum(A-row[i] * B-col[j])
- `sparse matric: lots positions are zero`
- 平白地写matric multiplication 没有意义, 重点就是optimization:
- `optimization`: for A-zero-row, and B-zero-col, there is no need to calculate, just return 0.
- 1. Find A-zero-rows and store in setA, same for setB
- 2. during multiplication, reduce time complexity.
- Base: O(mnk), where `m = A.row`, `n = B.col`, `k = A.col = B.row`

#### Matrices
- 乘法规则: result[i][j] = sum(A-row[i] * B-col[j])
- A column size == B row size. 并且: 计算顺序是iterate over A column size

```
/*
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

Input:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

Output:

     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |

*/

// Faster solution
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        if (validate(A, B)) {
            return new int[][]{};
        }
        
        // iterate over A, B, create setA, setB for reduce row/col
        int m = A.length, n = B[0].length, index = B.length;
        int[][] rst = new int[m][n];
        
        // base loop, reduce 
        for (int i = 0; i < m; i++) {
            for (int ind = 0; ind < index; ind++) { // index = A col number or B row number
                if (A[i][ind] == 0) continue;
                for (int j = 0; j < n; j++) {
                    if (B[ind][j] == 0) continue;
                    rst[i][j] += A[i][ind] * B[ind][j];
                }
            }
        }
        
        return rst;
    }
    
    private boolean validate(int[][] A, int[][] B) {
        if (A == null || B == null) return true;
        if (A[0].length != B.length) return true;
        return false;
    }
}

// Original solution, a bit slower
// Trying to reduce empty A row, or empty B column by marking their row #, col # in set
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        if (validate(A, B)) {
            return new int[][]{};
        }
        
        // iterate over A, B, create setA, setB for reduce row/col
        int m = A.length, n = B[0].length, index = B.length;
        int[][] rst = new int[m][n];
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        
        // base loop, reduce 
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (setA.contains(i) || setB.contains(j)) {
                    rst[i][j] = 0;
                    continue;
                }
                boolean zeroA = true, zeroB = true;
                for (int ind = 0; ind < index; ind++) { // index = A col number or B row number
                    rst[i][j] += A[i][ind] * B[ind][j];
                    zeroA = zeroA && A[i][ind] == 0;
                    zeroB = zeroB && B[ind][j] == 0;
                }
                if (zeroA) setA.add(i);
                if (zeroB) setB.add(j);
            }
        }
        
        return rst;
    }
    
    private boolean validate(int[][] A, int[][] B) {
        if (A == null || B == null) return true;
        if (A[0].length != B.length) return true;
        return false;
    }
}

```