好像没啥技术含量. 做就行了。
validate block的时候虽然看到了4层for.其实也就是n^2.
```
/*
Determine whether a Sudoku is valid.

The Sudoku board could be partially filled, where empty cells are filled with the character ..


Example
The following partially filed sudoku is valid.

Valid Sudoku (Image can display here: http://www.lintcode.com/en/problem/valid-sudoku/#)

Note
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

Clarification
What is Sudoku?

http://sudoku.com.au/TheRules.aspx
https://zh.wikipedia.org/wiki/%E6%95%B8%E7%8D%A8
https://en.wikipedia.org/wiki/Sudoku
http://baike.baidu.com/subview/961/10842669.htm
Tags Expand 
Matrix
*/

/*
	Thoughts:
	Each row/col/block can only 1 ~ 9, no duplicates
	Use HashSet, reinitiate 3 times.
	traverse row, col, block
	O(n^2)
*/

class Solution {
    /**
      * @param board: the board
        @return: wether the Sudoku is valid
      */
    public boolean isValidSudoku(char[][] board) {
    	if (board == null || board.length == 0 || board[0].length == 0 || board.length != board[0].length) {
    		return false;
    	}
    	HashSet<Character> row,col,block;
    	int n = board.length;
    
    	for (int i = 0; i < n; i++) {
    		row = new HashSet<Character>();
    		col = new HashSet<Character>();
    		for (int j = 0; j < n; j++) {
    			//Check row
    			if (!row.contains(board[i][j])) {
    				row.add(board[i][j]);
    			} else if (board[i][j] != '.'){
    				return false;
    			}
    			//Check col
    			if (!col.contains(board[j][i])) {
    				col.add(board[j][i]);
    			} else if (board[j][i] != '.'){
    				return false;
    			}
    		}
    	}
    	
    	for (int i = 0; i < n; i+=3) {
    		for (int j = 0; j < n; j+=3) {
    			block = new HashSet<Character>();
	    		//Check block
	    		for (int k = 0; k < 3; k++) { 
	    			for (int h = 0; h < 3; h++) {
	    				if (!block.contains(board[i+k][j+h])) {
		    				block.add(board[i+k][j+h]);
		    			} else if (board[i+k][j+h] != '.'){
		    				return false;
		    			}
	    			}
	    		}
    		}

    	}


    	return true;
    }
};

```