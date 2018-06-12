M
1520479490
tags: DFS, BFS, Union Find, Matrix DFS

给一个2D board, 里面是 'X' 和 'O'. 把所有被X包围的area都涂成'X'. 

从四个边的edge出发, 像感染僵尸病毒一样扩散, 把靠边的node全部mark, 然后将还是'O'的改成X, 最后回复marks -> 'O'

#### Union Find
- UnionFind里面这次用到了一个rank的概念, 需要review. rank[] 也就是在tracking每一个node所在union的size.
- 目的是: always并到大的union里面
- note: 将2D coordinate (x,y) 转换成1D: index = x * n + y

#### DFS: mark all invalid 'O'
- Reversed thinking: find surrounded nodes, how about filter out border nodes && their connections?
- Need to traverse all the border nodes, consider dfs, visit all.
- loop over border: find any 'O', and dfs to find all connected nodes, mark them as 'M'
- time: O(mn) loop over all nodes to replace remaining 'O' with 'X'

#### DFS: mark all valid 'O'
- More like a graph problem: traverse all 'O' spots, and mark as visited int[][] with area count [1 -> some number]
- Run dfs as top->bottom: mark area count and dsf into next level
- End condition: if any 'O' reaches border, mark the global map<count, false>
- keep dfs untill all connected nodes are visited.
- At the end, O(mn) loop over the matrix and mark 'X' for all the true area from map.
- Practice: write code to verify

### BFS
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

// DFS
public class Solution {
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        //Check the board
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    fill(board, i , j);
                }
            }
        }
        char mark = 'M', candidate = 'O', goal = 'X';
        //Replacement
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == candidate) {
                    board[i][j] = goal;
                } 
                if (board[i][j] == mark) {
                    board[i][j] = candidate;
                }
            }
        }
    }
    
    //DFS to traverse all border nodes and their connected nodes
    public void fill(char[][] board, int x, int y) {
        int row = board.length, col = board[0].length;
        if (x < 0 || x >= row || y < 0 || y >= col || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'M';
        for (int i = 0; i < dx.length; i++) {
            fill(board, x + dx[i], y + dy[i]);
        }
    }
}


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


```