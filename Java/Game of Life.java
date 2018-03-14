M
1521012714
tags: Array

#### basic
- 简单的implementation, 把count function写清楚就好.
- time: O(mn), extra space: O(mn)
- 注意结尾要一个个board[i][j]copy

#### follow up
unlimited border? 可能需要分割board. 用大框分割, 每次换行的时候, 重复计算2行就好了. see details below.

#### improvement: do it in place!
- time: O(mn), extra space: O(1)
- bit manipulation: 用第二个bit来存previous value.
- 因为我们只考虑 0 和 1 而已, 所以可以这样取巧. 但是并不scalable.
- 如果需要存multiple state, 可能需要移动更多位, 或者用一个 state map
- 注意 bit manipulation 的细节: <<1, >>1, 还有 mast的用法: |, & 


```
/*
According to the Wikipedia's article: "The Game of Life, also known simply as Life, 
is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) 
using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: 
You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. 
In principle, the board is infinite, which would cause problems 
when the active area encroaches the border of the array. How would you address these problems?
Credits:

*/

/*
Thoughts
1:
< 2  => 0    			=> STATE = 2 
> 3  => 0				=> STATE = 2 
==2, ==3 => 1   => 1

O(mn)
*/

// time O(mn * 8) = O(mn), Space(mn)
class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 ||  board[0] == null ||  board[0].length ==0) {
        	return;
        }
        int m = board.length;
        int n = board[0].length;
        int[][] newBoard = new int[m][n];
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
          	    int countNeighbor = count(board, i , j);
                if (board[i][j] == 1) {
                    newBoard[i][j] = (countNeighbor < 2 || countNeighbor > 3) ? 0 : 1;
                } else { // board[i][j] == 0
                    newBoard[i][j] = count(board, i, j) == 3 ? 1 : 0;
                }
          }
        }
        // copy
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
                board[i][j] = newBoard[i][j];        
            }
        }
    }
    
    int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
    int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};
    
    // optimize: no need to count all.
    private int count(int[][] board, int x, int y) {
        int count = 0;
        for (int i = 0; i < dx.length; i++) {
            int mX = x + dx[i];
            int mY = y + dy[i];
            if (mX >= 0 && mX < board.length && mY >= 0 && mY < board[0].length) {
                count += board[mX][mY];
            }
        }
        return count;
    }
}


/*
Use 2nd bit as storage for previous status '0' or '1'
0 => 00
1 => 10

This solution is not quite scalable because it only works with '0' and '1'

We could implement state map if having multiple conditions.
*/
class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 ||  board[0] == null ||  board[0].length ==0) {
        	return;
        }
        int m = board.length;
        int n = board[0].length;
        
        // Shift: use 2nd bit to store previous condition
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] << 1;
            }
        }
        
        // Count and calculate
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
          	    int countNeighbor = count(board, i , j);
                
                // Access 2nd bit for previous value
                if ((board[i][j] >> 1) == 1) {
                    board[i][j] = (countNeighbor >= 2 && countNeighbor <= 3) ? board[i][j] | 1 : board[i][j];
                } else { // board[i][j] == 0
                    board[i][j] = count(board, i, j) == 3 ? board[i][j] | 1 : board[i][j];
                }
          }
        }

        // Filter out 2nd bit and only 1st bit as result
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] & 1; // remove 2nd bit, which stores previous value
            }
        }
    }
    
    int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
    int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};
    
    // optimize: no need to count all.
    private int count(int[][] board, int x, int y) {
        int count = 0;
        for (int i = 0; i < dx.length; i++) {
            int mX = x + dx[i];
            int mY = y + dy[i];
            if (mX >= 0 && mX < board.length && mY >= 0 && mY < board[0].length) {
                count += (board[mX][mY] >> 1);
            }
        }
        return count;
    }
}

/*

Unlimited? board太大, 一个电脑放不下, 怎么分割?
定一个大框
每次移动大框的时候, 留着重复2就行了.
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x

a a a a a a a a a a
y y y y y y y y y y
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
x x x x x x x x x x
 */
```