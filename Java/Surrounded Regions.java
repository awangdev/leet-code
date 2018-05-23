R
1520479490
tags: DFS, BFS, Union Find

给一个2D board, 里面是 'X' 和 'O'. 把所有被X包围的area都涂成'X'. 

从四个边的edge出发, 像感染僵尸病毒一样扩散, 把靠边的node全部mark, 然后将还是'O'的改成X, 最后回复marks -> 'O'

#### Union Find
- UnionFind里面这次用到了一个rank的概念, 需要review. rank[] 也就是在tracking每一个node所在union的size.
- 目的是: always并到大的union里面
- note: 将2D coordinate (x,y) 转换成1D: index = x * n + y

#### DFS
- TODO

#### BFS
- TODO


```
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

*/
/*
Thoughts:
The only where it can't be surrounded is when the O is on edge; also, same to any connecting O with it.
1. Find all edging O and union all the neighbors, mark them differently
2. Assign all other positiions to X if not alreay is.
3. Flip marked positions back to O

Use union the 4 directions of O to mark them
*/
class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        UnionFind unionFind = new UnionFind(m * n + 1);
        int root = m * n;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    continue;
                }
                int curr = i * n + j;
                // Merging the edge with the virtual root position
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    unionFind.union(curr, root);
                    continue;
                }
                // If edge is 'O', union them.
                for (int k = 0; k < dx.length; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    
                    if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                        unionFind.union(curr, x * n + y);
                    }
                }
            }
        }

        // Assign 'X'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && unionFind.find(i * n + j) != root) {
                    board[i][j] = 'X';
                }
            }
        }
    }
}

class UnionFind {
    int[] father;
    int[] rank;
    public UnionFind(int x) {
        father = new int[x];
        rank = new int[x];
        for (int i = 0; i < x; i++) {
            father[i] = i;
        }
        rank[x - 1] = x;
    }
    
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        if (rank[rootX] < rank[rootY]) {
            father[rootX] = rootY;
        } else {
            if (rank[rootX] == rank[rootY]) {
                rank[rootX]++;
            }
            father[rootY] = rootX;
        }
    }

    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
}

/*
Previous notes:
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

```