E
tags: Hash Table, Enumeration
time: (mn)
space: (mn)

#### Hash Set
- 用HashSet存visited row/col/block.
    - 在nest for loop里面validate row,col,and block.     
    - Special: validate block要利用i 和 j 增长的规律   
- i, j are [0~n) can build block boundary in a for loop:
    - `int c = 3 * (i % 3) + j % 3;` //make use of how i and j increases
    - `int r = 3 * (i / 3) + j / 3;`

#### A bit Slower approach
- 单独做block validation: validate block的时候虽然看到了4层for. 其实也就是n^2
- 可能代码稍微复杂一点

```
/*
Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true
Example 2:

Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being 
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.
*/
class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0 || board.length != board[0].length) {
            return false;
        }
        int n = board.length;
    
        for (int i = 0; i < n; i++) {
            HashSet<Character> row = new HashSet<>(), col = new HashSet<>(), block = new HashSet<>();
            for (int j = 0; j < n; j++) {
                //Check row
                if (!helper(board[i][j], row)) return false;
                //Check col, revert use of i,j
                if (!helper(board[j][i], col)) return false;
                //check block
                int c = 3 * (i % 3) + j % 3;//make use of how i and j increases
                int r = 3 * (i / 3) + j / 3;
                if (!helper(board[r][c], block)) return false;
            }
        }
        return true;
    }
    
    private boolean helper(char c, Set<Character> set) {
        if (!set.contains(c)) set.add(c);
        else if (c != '.') return false;
        return true;
    }
};

```