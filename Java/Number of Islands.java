M
1520355237
tags: DFS, BFS, Union Find, Matrix DFS

给一个2Dmatrix, 里面是1和0, 找#of island.

#### DFS
- More or less like a graph problem: visit all nodes connected with the starting node.
- top level 有一个 double for loop, 查看每一个点.
- 每当遇到1, count+1, 然后DFS helper function 把每个跟这个当下island 相关的都Mark成 '0'
- 这样确保每个visited 过得island都被清扫干净
- O(mn) time, visit all nodes

#### Union Find
- 可以用union-find， 就像Number of island II 一样.
- 只不过这个不Return list, 而只是# of islands
- Union Find is independent from the problem: it models the union status of integers.
- 记住UnionFind的模板和几个变化(Connecting Graph I, II, III), 最后归总的代码写起来就比较简单.

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
        if (!validateInput(grid, x, y)) {
            return;
        }
        grid[x][y] = '0';
        for (int i = 0; i < dx.length; i++) {
            dfs(grid, x + dx[i], y + dy[i]);
        }
    }

    private boolean validateInput(char[][] grid, int x, int y) {
        return x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0';
    }
}

/*
Thoughts:
Similart to ConnectingGraph, and we count # of unions left.
Build UnionFind and let query return # of unions left (isolated island in this problem).
Need to know which island to connect/union, need to go 4 directions.
Convert 2D matrix to 1D index = rowNum * numOfColumn + colNum
*/
class Solution {
    
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        
        UnionFind unionFind = new UnionFind(m * n);
        int totalLandCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalLandCount += grid[i][j] == '1' ? 1 : 0;
            }    
        }
        // # of island blocks, goal is to connect them all.
        unionFind.setCount(totalLandCount);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < dx.length; k++) { // 4 directions
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (!validateInput(grid, x, y)) {
                            // Attemp to union all of the 4 directions
                            unionFind.union(convertToIndex(i, j, n), convertToIndex(x, y, n));
                        }
                    }
                }
            }    
        }
        
        return unionFind.query();
    }

    // 1D index = rowNum * numOfColumn + colNum
    private int convertToIndex(int x, int y, int rowLength) {
        return x * rowLength + y;
    }
    private boolean validateInput(char[][] grid, int x, int y) {
        return x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0';
    }
}

class UnionFind {
    int father[] = null;
    int count;

    public UnionFind(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            father[rootX] = rootY;
            count--;
        }
    }
    
    public int query() {
        return count;
    }
    
    public void setCount(int value) {
        count = value;
    }

    private int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
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