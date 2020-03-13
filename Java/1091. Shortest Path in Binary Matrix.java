M
tags: BFS
time: O(n^2)
time: O(n^2)


#### BFS
- find shortest path using queue
- time/space: O(n^2), n = grid length
- why SKIP `boolean visited[i][j]`? after a position grid[i][j] is used: 
    - 1) the curr path will not return to (i, j)
    - 2) other route that may eventually reach (i, j) need not to be recorded,
        - because the other route is already longer than the curr path
    - therefore, we just simply block the visited node by `grid[x][y] = 1`
        - note: block it right after it is added to the queue, so other nodes at same level will not attempt this visited node.

```


/*
In an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.

 

Example 1:

Input: [[0,1],[1,0]]


Output: 2

Example 2:

Input: [[0,0,0],[1,1,0],[1,1,0]]


Output: 4

 

Note:

1 <= grid.length == grid[0].length <= 100
grid[r][c] is 0 or 1

*/

class Solution {
    int[] dx = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
    int N;
    public int shortestPathBinaryMatrix(int[][] grid) {
        N = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        if (grid[0][0] == 0) queue.offer(new int[] {0, 0});
        grid[0][0] = 1;
        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] pos = queue.poll();
                
                if (pos[0] == N - 1 && pos[1] == N -1) return depth;
                for (int i = 0; i < dx.length; i++) {
                    int x = pos[0] + dx[i], y = pos[1] + dy[i];
                    if (validate(grid, x, y)) {
                        queue.offer(new int[] {x, y});
                        grid[x][y] = 1;
                    }
                }
            }
            depth++;
        }
        return -1;
    }
    
    private boolean validate(int[][] grid, int i, int j) {
        return (i >= 0 && i < N && j >= 0 && j < N && grid[i][j] == 0);
    }
}
```