H
1525315201
tags: Backtracking

N-Queen 问题, 给数字n, 和 nxn board, 找到所有N-queens的答案.

#### Backtracking
- 用dfs找所有情况, 每一个iteration, 从找一行里挑合适的点, dfs
- 选中的点加进candidate list 里面, 记得要backtracking.
- 每一个candidate都需要validation, 检查 row, col, 2 diagnal 有没有queen

#### validate n queue at certain (x, y)
- 1. array 里面不能有 target row#
- 2. diagnal. 记得公式：
- row1 - row2 == col1 - col2.     Diagnal elelment.fail
- row1 - row2 == - (col1 - col2). Diagnal element. fail
- Draw a 3x3 board to test the 2 scanarios:
- (0,0) and (3,3) are diagnal
- (0,2) and (2,0) are diagnal


```
/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that 
no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, 
where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Hide Tags Backtracking


*/

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> rst = new ArrayList<>();
        if (n <= 0) {
            return rst;
        }
        dfs(rst, new ArrayList<>(), n);

        return rst;
    }

    private void dfs(List<List<String>> rst, List<Integer> list, int n){
        if (list.size() == n) {
            rst.add(createBoard(list));
            return;
        }
        //For next row, which col to put queen? Now do recursive:
        for (int i = 0; i < n; i++) {
            if (validate(list, i)) {
                list.add(i);
                dfs(rst, list, n);
                list.remove(list.size() - 1);
            }
        }
    }
    
    /*
      Validate the board with given input.
      Draw a 3x3 board to test the 2 scanarios:
      - (0,0) and (3,3) are diagnal
      - (0,2) and (2,0) are diagnal
    */
    private boolean validate(List<Integer> list, int newColNum) {
        int newRowNum = list.size(); // the new row that colNum is going to be put on
        for (int rowNum = 0; rowNum < list.size(); rowNum++) {
            //check row, check diagnal
            int colNum = list.get(rowNum);
            if (colNum == newColNum || Math.abs(rowNum - newRowNum) == Math.abs(colNum - newColNum)) {
                return false;
            }
        }
        return true;
    }
    /*
    private boolean validate(List<Integer> list, int newColNum) {
        int newRowNum = list.size(); // the new row that colNum is going to be put on
        for (int rowNum = 0; rowNum < list.size(); rowNum++) {
            //check row
            int colNum = list.get(rowNum);
            if (colNum == newColNum) {
                return false;
            }
            //check diagnal
            //q1 row - newQ row == q1 col - newQ col
            if (rowNum - newRowNum == colNum - newColNum) {
                return false;
            }
            //q1 row - newQ row == -(q1 col - newQ col)
            if (rowNum - newRowNum == - (colNum - newColNum)) {
                return false;
            }
        }
        return true;
    }*/

    private List<String> createBoard(List<Integer> list){
        List<String> board = new ArrayList<>();
        for (int row = 0; row < list.size(); row++) {
            StringBuffer sb = new StringBuffer();
            for (int col : list) {
                if (row == col) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            board.add(sb.toString());
        }
        return board;
    }
}


/*
  Recap: 12.08.2015
  NQueen: turns into a 1D array of row#'s. What are all of the possible combinations?
  Validate the board:
    With 1 potential canddate, a row# to put in the array
    1. the 1D array cannot have duplicate of the candidate
    2. check diagnals:
      row1 - row2 == col1 - col2. Diagnal elelment.fail
      row1 - row2 == -(col1 - col2). Diagnal element. fail
      That is:  delta_row = Q1 row - Q2 row
                delta_col = Q1 col - Q2 col
      Let delta_row = the difference in rows between the two queens, and delta_col = the difference in columns. 
      The two queens will be on the same diagonal if delta_row == delta_col or delta_row == -delta_col

  Create the board:
    carete 2d arraylist based on the 1-D array of row#'s

    corner case: if n<=2, no solution
*/

class Solution {

    public ArrayList<ArrayList<String>> solveNQueens(int n) {
      ArrayList<ArrayList<String>> rst = new ArrayList<ArrayList<String>>();
      if (n <= 0) {
        return rst;
      }
      ArrayList<Integer> list = new ArrayList<Integer>(); //1D array
      helper(rst, list, n);

      return rst;
    }

    /*
      Validate the board with given input.
    */
    public boolean validate(ArrayList<Integer> list, int rowNum) {
      int colNum = list.size(); // the column that rowNum is going to be put on
      for (int col = 0; col < list.size(); col++) {
        //check row
        if (list.get(col) == rowNum) {
          return false;
        }
        //check diagnal
        //q1 col - newQ col == q1 row - newQ row
        if (col - colNum == list.get(col) - rowNum) {
          return false;
        }
        //q1 col - newQ col == -(q1 row - newQ row)
        if (col - colNum == -(list.get(col) - rowNum)) {
          return false;
        }
      }
      return true;
    }

    public ArrayList<String> createBoard(ArrayList<Integer> list){
      ArrayList<String> board = new ArrayList<String>();
      for (int row = 0; row < list.size(); row++) {
        StringBuffer sb = new StringBuffer();
        for (int col : list) {
          if (row == col) {
            sb.append("Q");
          } else {
            sb.append(".");
          }
        }
        board.add(sb.toString());
      }
      return board;
    }


    public void helper(ArrayList<ArrayList<String>> rst, ArrayList<Integer> list, int n){
      if (list.size() == n) {
        rst.add(createBoard(list));
        return;
      }
      //For next Queen, which row to put? Now do recursive:
      for (int i = 0; i < n; i++) {
        if (validate(list, i)) {
          list.add(i);
          helper(rst, list, n);
          list.remove(list.size() - 1);
        }
      }
    }
};



//Older version: the naming in validate() is confusing
/*
Thinking process:
1. Choose / Not choose concept.
2. N-Queue facts:
  Each column has 1 Q. Each row has 1 Q. 
  That is: each column has 1 Q, which can be present as a row number.
  Use a 1-D array: index is column number, value is row number
3. When adding a new row Number into the 1-D array, validate it.
4. Use same procedure in 'permutaions' problem. 
  The 1-D array 'cols' will be filled with all kinds of combination from 1 ~ n.
  Only when cols.size() == n, return a solution
5. When returnning the solution, return the format as a board. ArrayList<String[]>
*/
import java.util.*;
class NQueens {
    /**
     * Get all distinct N-Queen solutions
     * @param n: The number of queens
     * @return: All distinct solutions
     * For example, A string '...Q' shows a queen on forth position
     */
    ArrayList<ArrayList<String>> solveNQueens(int n) {
      ArrayList<ArrayList<String>> rst = new ArrayList<ArrayList<String>>();
      if (n <= 0) {
        return rst;
      }
      search(n, new ArrayList<Integer>(), rst);
      return rst;
    } 

    ArrayList<String> createBoard(ArrayList<Integer> cols) {
      ArrayList<String> solution = new ArrayList<String>();
      for (int i = 0; i < cols.size(); i++) {
            StringBuffer sb = new StringBuffer();
        for (int j = 0; j < cols.size(); j++){
          if (j == cols.get(i)) {
            sb.append( "Q");
          } else {
            sb.append( ".");
          }
        }
        solution.add(sb.toString());
      }
      return solution;
    }

    boolean isValid (ArrayList<Integer> cols, int col) {
      int row = cols.size();
      for (int i = 0; i < row; i++) {
      if (cols.get(i) == col ) {
        return false;
      }
      //Check diagnal: Q1_row - Q2_row == Q1_col - Q2_col
      //In this case:
      //col: the target queen's column#
      //cols.get(i): the target queen's row#
      //We compare them with (row: current max_column#) and (i: current row#), to check if valid
      if (i - cols.get(i) == row - col) {
        return false;
      }   
      if (i + cols.get(i) == row + col) {
        return false;
      }
      }
      return true;
    }

    void search(int n, ArrayList<Integer> cols, ArrayList<ArrayList<String>> rst) {
      if (cols.size() == n) {
        rst.add(createBoard(cols));
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

    public static void main(String[] args){
      NQueens test = new NQueens();
      test.solveNQueens(4);
    }

};



/*
Thoughts: goal is to place all n queens on the board. DFS.
1. Save all nodes in set by sequence #, where each # can translate to x,y corrdinate
2. once confirm on a position, find the set of positions that removed by this queen, put in subset.
3. remove subset from set, and dfs(set, n-1, list, rst)
4. if n == 0, save list to rst (or check list.size(), or check set.size())
5. Use a global boolean board[][] to track attempted/failed position. If position attempted, then there is no need to try again.

On first row: attemp queen from [0 ~ n-1] to launch the dfs. Each row will have a queen.

However, persist && find the neighbors to remove from the set, is a bit redundant code. Not going with this approach
*/
```