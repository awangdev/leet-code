M
1520573473
tags: Array, Backtracking

Backtracking:
找到开头的字母, 然后recursively DFS 去把word串到底:
每到一个字母, 朝四个方向走, 之中一个true就可以.

Note:每次到一个字母，mark一下'#'. 4个path recurse回来后，mark it back.

Backtracking方法2:    
用一个boolean visited[][]



```
/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.


Example
Given board =

[
  "ABCE",
  "SFCS",
  "ADEE"
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

Tags Expand 
Backtracking

*/

/*
Thoughts:
Word Search II 简化版: 只有DFS, 没有words的结构优化
1. DFS (board, x, y)
2. Backtracking: mark visited item '#'
*/

class Solution {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0
            || word == null || word.length() == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j] && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, String word, int x, int y, int index) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length
            || board[x][y] != word.charAt(index) || board[x][y] == '#') {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        board[x][y] = '#';
        for (int i = 0; i < dx.length; i++) {
            if (dfs(board, word, x + dx[i], y + dy[i], index + 1)) {
                return true;
            }
        }
        board[x][y] = word.charAt(index);
        return false;
    }
    
}

/*
Thoughts:
1. find starting index i,j
2. Start divde&&conqure: each iteration.
    In each interation: make sure board[i][j] == word.charAt(currentCheckingIndex); If not match, return false and terminate the interation
3. Therefore, if (start) == word.length(), that means all previous-start indexes are matched, so we have a match; return true in this case.


Note: if can use boolean || boolean || boolean, use it and save processing power: once one boolean works, it won't process the rest || elements


*/
public class Solution {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null) {
            return false;
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean rst = search(board, word, i, j, 0);
                    if (rst) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean search(char[][] board, String word, int i, int j, int start) {
        if (start == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)) {
            return false;
        }
        board[i][j] = '#';
        boolean rst = search(board, word, i, j - 1, start + 1)
        || search(board, word, i, j + 1, start + 1)
        || search(board, word, i + 1, j, start + 1)
        || search(board, word, i - 1, j, start + 1);   
        board[i][j] = word.charAt(start);
        return rst;
    }
}



//dfs: search through the board, going to different directions, while also increasing index. when index == word.length, that's end.
//use visited[][] to mark visited places.

public class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null 
            || board[0].length == 0 || word == null || word.length() == 0) {
            return false;        
        }
        int height = board.length;
        int width = board[0].length;
        boolean[][] visited = new boolean[height][width];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) != board[i][j]) {
                    continue;
                }
                if (dfs(0, i, j, visited, board, word)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean dfs (int index, int x, int y, boolean[][] visited, char[][] board, String word) {
        if (index == word.length()) {
            return true;
        }
        
        int height = board.length;
        int width = board[0].length;
        if (x < 0 || x >= height || y < 0 || y >= width || board[x][y] != word.charAt(index) || visited[x][y]) {
            return false;
        }
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0,  0, 1, -1};
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (dfs(index + 1, nx, ny, visited, board, word)) {
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }
}





```