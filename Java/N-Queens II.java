H
1525324321
tags: Backtracking

跟 N-Queens 一样, 不是找所有结果, 而是count多少结果.

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


class Solution {
    /**
     * Calculate the total number of distinct N-Queen solutions.
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    public int totalNQueens(int n) {
      ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
      if (n <= 0) {
        return 0;
      }
      search(n, new ArrayList<Integer>(), rst);
      return rst.size();
    }

    boolean isValid (ArrayList<Integer> cols, int col) {
      int row = cols.size();
      for (int i = 0; i < row; i++) {
      if (cols.get(i) == col ) {
        return false;
      }     
      if (i - cols.get(i) == row - col) {
        return false;
      }   
      if (i + cols.get(i) == row + col) {
        return false;
      }
      }
      return true;
    }

    void search(int n, ArrayList<Integer> cols, ArrayList<ArrayList<Integer>> rst) {
      if (cols.size() == n) {
        rst.add(cols);
        return;
      }
      for (int i = 0; i < n; i++) {
        if (!isValid(cols, i)) {
          continue;
        }
        cols.add(i);
        search(n, cols, rst);
        cols.remove(cols.size() - 1);
      }
    }

};



```