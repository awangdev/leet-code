/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Hide Tags Breadth-first Search

Thinking Process:
Since dfs does not work, try bfs.
Very similar to DFS, however, when checking the 4 bounaries: 
1. chcek the curruent point.
2. Add surrounding points into a queue.
3. Deal with the queue immediately via a while loop

*/
public class Solution {
    private char[][] board;
    private int row;
    private int col;
    private char target;
    private char mark;
    private Queue<Integer> queue = new LinkedList<Integer>();
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        this.board = board;
        row = board.length;
        col = board[0].length;
        target = 'O';
        mark = 'M';
        //Check the board
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    check(i,j);
                }
            }
        }
        //Replacement
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == target) {
                    board[i][j] = 'X';
                } 
                if (board[i][j] == mark) {
                    board[i][j] = target;
                }
            }
        }
    }
    //BFS
    public void check(int i, int j) {
        fill(board, i, j);
        while(!queue.isEmpty()) {
            int val = queue.poll();
            int x = val / col;
            int y = val % col;
            fill(board, x - 1, y);
            fill(board, x + 1, y);
            fill(board, x, y - 1);
            fill(board, x, y + 1);
        }
    }
    public void fill(char[][] board, int i, int j) {
        if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] != target) {
            return;
        }
        board[i][j] = mark;
        queue.offer(i * col + j);
    }
}




/*
Thinking process:
Using DFS.
1. Whenever the edge has an 'O', all touching point with 'O' will be non-surrounded by 'X'. SO check the 4 bounds first. Mark all non-surrounded point as M.
2. Replace all remaining 'O' with 'X'
3. Replace 'M' with 'O'
However, in the LeetCode test, DFS gives stack overflow. So we'd use BFS instead.
*/

/*

//The following is using DFS, but gives Stackoverflow.
public class Solution {
    private char[][] board;
    private int row;
    private int col;
    private char target;
    private char mark;
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        this.board = board;
        target = 'O';
        mark = 'M';
        row = board.length;
        col = board[0].length;
        
        //check bound
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0 || i == row - 1 || j == col - 1) {
                    check(i, j);
                }
            }
        }
        //1. replace remaining target with 'x'
        //2. replace all mark with 'O'
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == target) {
                    board[i][j] = 'X';
                } else if (board[i][j] == mark) {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    public void check(int i, int j) {
        if (i < 0 || j < 0 || i > row - 1 || j > col - 1) {
            return;
        }
        if (board[i][j] == target) {
            board[i][j] = mark;
            check(i - 1, j);
            check(i + 1, j);
            check(i, j - 1);
            check(i, j + 1);
        }
    }

}

*/
