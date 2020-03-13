M
tags: DFS, BFS, Union Find, Matrix DFS
time: O(n)
space: O(n)

给一个2Dmatrix, 里面是1和0, 找#of island.

#### Method1, DFS
- visit all nodes connected with the starting node
    - double for loop, test all starting nodes
    - val == 1: 1) count++; 2)DFS from this (i,j);
    - Mark visited (x,y) = '0'
- time: O(n), visit all nodes
- space: O(n), stack

#### Method2, Union Find
- 可以用union-find， 就像Number of island II 一样.
    - 只不过这个不Return list, 而只是# of islands
    - Union Find is independent from the problem: it models the union status of integers.
    - Return the total # of unions (which is # of islands)
- in reality: it is a bit slow.
- time: visit all nodes just once, O(n). Union Find will visit all nodes once and union them
- space: O(n), union find takes O(n) space
- 记住UnionFind的模板和几个变化(Connecting Graph I, II, III), 最后归总的代码写起来就比较简单. 

#### Method3: BFS
- use queue to hold 1 island, keep adding 4-direction islands; mark visited with '0' 
- check entire board for any remaining one.

```
/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
*/


/*
Method1: DFS
- DFS and flip the bit-1 on the grid to 0 as we go: to 4 different directions
- Loop through all positions
- Visited spots won't be visited again because they are updated to '0'
*/

class Solution {
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
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
        if (validateInput(grid, x, y)) return;
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
Method2: Union Find
Similart to ConnectingGraph, and we count # of unions left.
Build UnionFind and let query return # of unions left (isolated island in this problem).
Need to know which island to connect/union, need to go 4 directions.
Convert 2D matrix to 1D index = rowNum * numOfColumn + colNum
*/
class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null) return 0;
        int m = grid.length, n = grid[0].length;
        
        // init with # of island blocks, goal is to connect them all.
        UnionFind uf = new UnionFind(m * n);
        uf.setCount(countIsland(grid));

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < dx.length; k++) { // 4 directions
                        int x = i + dx[k], y = j + dy[k];
                        if (!validate(grid, x, y)) { // Attemp to union all of the 4 directions
                            uf.union(convertToIndex(i, j, n), convertToIndex(x, y, n));
                        }
                    }
                }
            }    
        }
        // output the united total # of islands
        return uf.query();
    }
    
    private int countIsland(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                count += grid[i][j] == '1' ? 1 : 0;
            }    
        }
        return count;
    }

    // 1D index = rowNum * numOfColumn + colNum
    private int convertToIndex(int x, int y, int rowLength) {
        return x * rowLength + y;
    }
    private boolean validate(char[][] grid, int x, int y) {
        return x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0';
    }
}

// Union Find definition
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
        if (father[x] == x) return x;
        return father[x] = find(father[x]);
    }
}

// Method3: BFS
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    grid[i][j] = '0';
                    queue.offer(i * grid[0].length + j);
                    processQueue(queue, grid);
                }
            }
        }
        return count;
    }
    
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    public void processQueue(Queue<Integer> queue, char[][] grid) {
        int m = grid.length, n = grid[0].length;
        while(!queue.isEmpty()) {
            int key = queue.poll();
            int x = key / n, y = key % n;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (validate(grid, nx, ny)) {
                    grid[nx][ny] = '0';
                    queue.offer(nx * n + ny);
                }
            }
        }
    }
    
    private boolean validate(char[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1';
    }
}


/*
Thoughts:
UnionFind with map rather than array
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


```