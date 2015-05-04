/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

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