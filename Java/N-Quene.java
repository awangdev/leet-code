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
//TODO: NOT DONE YET
*/

