M

方法1: 两个for loop brutle force。 DFS把每个跟1相关的都Mark一遍.生成一个island.

方法2:
可以用union-find， 就像Number of island II 一样。
只不过这个不Return list, 而只是# of islands

```
/*in
Given a boolean 2D matrix, find the number of islands.

Example
Given graph:

[
  [1, 1, 0, 0, 0],
  [0, 1, 0, 0, 1],
  [0, 0, 0, 1, 1],
  [0, 0, 0, 0, 0],
  [0, 0, 0, 0, 1]
]
return 3.

Note
0 is represented as the sea, 1 is represented as the island. 
If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

*/

/*
Thoughts:
- DFS and flip the bit-1 on the grid to 0 as we go: to 4 different directions
- Loop through all positions
- Visited spots won't be visited again because they are updated to '0'
*/
class Solution {
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0') {
            return;
        }
        grid[x][y] = '0';
        for (int i = 0; i < dx.length; i++) {
            dfs(grid, x + dx[i], y + dy[i]);
        }
    }
}

/*
Thoughts:
UnionFind.
Traverse all points of grid and count the total number of island. See Number of Islands II for details.
Note: need to initialize the 1D array first with all 1's. 
Therefore, when we start perform union-find, we already have knowledge of entire island status.

However, it's not as straight-forward as DFS though.
*/
class Solution {
    class UnionFind {
        private HashMap<Integer, Integer> map = new HashMap<>();
        
        /*
        Model the disjoint set with 1D array
        During initialization, assume each spot has itself as the parent
        */
        public UnionFind(int size) {
            for (int i = 0; i < size; i++) {
                map.put(i, i);
            }
        }
        
        /*
        Use one key and find out the root parent of this set where they key belongs to.
        */
        public int findRootParent(int item) {
            int parent = map.get(item);
            while (parent != map.get(parent)) {
                parent = map.get(parent);
            }
            return parent;
        }

        /*
        Find the root parent of each item. If the root parent is different,
        join them together by adding them into the map as <key, value> pair.
        */
        public void union(int itemX, int itemY) {
            int parentX = findRootParent(itemX);
            int parentY = findRootParent(itemY);
            if (parentX != parentY) {
                map.put(parentX, parentY);
            }
        }
    }

    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        final int[] islands = new int[m * n];
        final UnionFind unionFind = new UnionFind(m * n);
        // Initialize island
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int pos = i * n + j;
                    islands[pos] = 1;
                    count++;
                }
            }
        }
        // Merge island and decrease count if on merged island
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int pos = i * n + j;
                if (islands[pos] != 1) {
                    continue;
                }
                for (int k = 0; k < dx.length; k++) {
                    int adjX = i + dx[k];
                    int adjY = j + dy[k];
                    int adjPos = adjX * n + adjY;
                    if (adjX >= 0 && adjX < m && adjY >= 0 && adjY < n && islands[adjPos] == 1) {
                        int currSpotRoot = unionFind.findRootParent(pos);
                        int adjSpotRoot = unionFind.findRootParent(adjPos);
                        if (currSpotRoot != adjSpotRoot) {
                            count--;
                            unionFind.union(currSpotRoot, adjSpotRoot);
                        }
                    }

                }
            }
        }
        return count;
    }
}

/*
  12.12.2015 recap
  We are checking if a sets of adjacent nodes are int the same set
  We union all neighbors
  Generate the list of islands: actually return the # of islands

  AND yes, this is doable. Number of island II is using union-find
*/

/*

Thoughts:
1. Use a 2D integer matrix to map the islands, by default the 2D [][] is all 0.
2. When the matrix point is 0, means it has not been checked. Check it against the grid.
3. Whenever there is a match, assign mark to that point.
4. Only increase mark when all the recursion finishes.
5. return mark - 1

*/
public class Solution {
    int[][] matrix;
    int mark;
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length ==0 || grid[0].length == 0) {
            return 0;
        }
        matrix = new int[grid.length][grid[0].length];
        mark = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                mark = check(i, j, mark, grid) ? (mark + 1) : mark;
            }
        }
        
        return mark - 1;
        }

        public boolean check(int x, int y, int mark, boolean[][] grid) {
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length) {
                if (matrix[x][y] == 0 && grid[x][y]) {
                    matrix[x][y] = mark; 
                    check(x + 1, y, mark, grid); 
                    check(x - 1, y, mark, grid); 
                    check(x, y + 1, mark, grid);
                    check(x, y - 1, mark, grid);
                    return true;
                }
            } 
            return false;
        }
}


//from leetcode:
public class Solution {

    public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
        return 0;
      }  
      int count = 0;
      for(int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          count = mark(i, j, grid)? (count + 1) : count;
        }
      }
      return count;
    }

    public boolean mark(int i, int j, char[][] grid) {
      if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length) {
        if (grid[i][j] == '1') {
          grid[i][j] = 'M';
          mark(i - 1, j, grid);
          mark(i + 1, j, grid);
          mark(i, j - 1, grid);
          mark(i, j + 1, grid);
          return true;
        }
      }
      return false;
    }
}
```