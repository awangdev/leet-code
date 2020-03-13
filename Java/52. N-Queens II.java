H
tags: Backtracking
time: O(n!)
space: O(n)

跟 N-Queens 一样, 不是找所有结果, 而是count多少结果.

#### Backtracking (with replacement)
- Each row has just 1 Queen value
- As CC book suggests, use `int[] columns` of length n to store all queen col positions for n rows
  - `int[] columns` is slightly easier to backtrack by updating certain index i with new col
  -  list will usualy has the add/remove pattern for backtracking

#### Backtracking
- 当list.size() == n 的时候，说明找到了一个Solution。
- 1. dfs function (List<Integer>, n)
- 2. validate function


```
/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.

Example
For n=4, there are 2 distinct solutions.

Tags Expand 
Recursion

Thoughts:
Exact same as NQueens, except we don't print the map. Instead, simply add the record in rst.
At the end, return rst.size(), which would be unique answer.
*/

class Solution {
    public int totalNQueens(int n) {
        if (n <= 0) return 0;
        return dfs(new int[n], 0);
    }
    
    private int dfs(int[] columns, int row) {
        if (row == columns.length) return 1;
        int count = 0;
        for (int col = 0; col < columns.length; col++) { 
            if (validate(columns, row, col)) {
                columns[row] = col; // place queen
                count += dfs(columns, row + 1);
            }
        }
        return count;
    }
    
    // Validate the prior row, colomn & diagnal
    private boolean validate(int[] columns, int row, int col) {
        for (int newRow = 0; newRow < row; newRow++) {
            int newCol = columns[newRow];
            if (col == newCol || Math.abs(row - newRow) == Math.abs(col - newCol)) {
                return false;
            }
        }
        return true;
    }
}

/*
Thougths:
Goal: dfs and count all solutions
1. dfs function (List<Integer>, n)
2. validate function
*/
class Solution {
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        return dfs(new ArrayList<>(), n);
    }
    
    private int dfs(List<Integer> list, int n) {
        if (list.size() == n) {
            return 1;
        }
        int count = 0;
        for (int col = 0; col < n; col++) {
            if (validateBoard(list, col)) {
                list.add(col);
                count += dfs(list, n);
                list.remove(list.size() - 1);
            }
        }
        return count;
    }
    
    private boolean validateBoard(List<Integer> list, int newColNum) {
        int newRowNum = list.size();
        for (int rowNum = 0; rowNum < list.size(); rowNum++) {
            int colNum = list.get(rowNum);
            if (colNum == newColNum || Math.abs(newColNum - colNum) == Math.abs(newRowNum - rowNum)) {
                return false;
            }
        }

        return true;
    }
}

```