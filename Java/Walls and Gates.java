M
1531812629
tags: BFS, DFS, Backtracking

给一个room 2D grid. 里面有墙-1, 门0, 还有empty space INF(Math.MAX_VALUE). 

对每个empty space而言, fill it with dist to nearest gate.

#### DFS
- Form empty room: it can reach different gate, but each shortest length will be determined by the 4 directions. 
- Option1(NOT applicable). DFS on INF, mark visited, summerize results of 4 directions. 
- hard to resue: we do not know the direction in cached result dist[i][j]
- Option2. DFS on gate, and each step taken to each direction will +1 on the spot: distance from one '0'; 
- Through dfs from all zeros, update each spot with shorter dist
- Worst time: O(mn), where entre rooms[][] are gates. It takes O(mn) to complete the iteration. Other gates be skipped by `if (rooms[x][y] <= dist) return;`

#### BFS
- TODO? why BFS better?

```
/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 
to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. 
If it is impossible to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

 */

/*
Form empty room: it can reach different gate, but each shortest length will be determined by the 4 directions. 
Option1. DFS on INF, mark visited, summerize results of 4 directions. it's hard to resue: we do not know the direction in cached result dist[i][j]
Option2. DFS on gate, and each step taken to each direction will +1 on the spot: distance from one '0'; through dfs from all zeros, update each spot with shorter dist
*/
class Solution {
    public void wallsAndGates(int[][] rooms) {
        // check input
        if (validate(rooms)) return;
        
        // find all 0's with for loop
        int m = rooms.length, n = rooms[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0 && !visited[i][j]) {
                    runDfs(rooms, visited, i, j, 0);
                }
            }
        }
    }
    
    // mark visited with dist (compare if existed)
    public void dfs(int[][] rooms, boolean[][] visited, int x, int y, int dist) {
        if (validateCoordinate(rooms, x, y)) return;
        if (rooms[x][y] <= dist) return;

        // update rooms[x][y]
        visited[x][y] = true;
        rooms[x][y] = dist; //Math.min(rooms[x][y], dist);
        
        // dfs if applicable
        runDfs(rooms, visited, x, y, dist);
        visited[x][y] = false;
    }
    
    private void runDfs(int[][] rooms, boolean[][] visited, int x, int y, int dist) {
        dfs(rooms, visited, x-1, y, dist + 1);
        dfs(rooms, visited, x+1, y, dist + 1);
        dfs(rooms, visited, x, y-1, dist + 1);
        dfs(rooms, visited, x, y+1, dist + 1);
    }
    
    private boolean validateCoordinate(int[][] rooms, int x, int y) {
        return x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] == -1 || rooms[x][y] == 0;
    }
    
    private boolean validate(int[][] rooms) {
        return rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0;
    }
    
}
```