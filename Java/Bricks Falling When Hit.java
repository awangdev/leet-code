H
1532820928
tags: Union Find

给一个matrix of 1 and 0, `1` 代表brick. 连着ceiling的brick就不会drop. 给一串coordinate hits[][], 记录每次take down 1 brick 后, 会drop多少个.

#### UnionFind
- 1. 我们知道大部分的brick可能都是连着ceiling, 所以每次正向检查都traverse all and timeout
- 2. 能否用union, 把connect都装在一起, 然后drop brick的时候把连着的都drop掉? 难: 因为还是要check所有brick当下的status.
- 受其他人的解答启发, 由于是计算count,我们可以`反向考虑`: 
- 把hit-brick全部mark=2 (就当舍弃不算), 观察整个局面的最后一步, 先把所有还连着ceiling的brick算一下总数, 统计在unionFind的 全部统计在count[0] 里面. 
- 剩下的不连着ceiling的也就是一个个isolated island
- 做法: 把hit-brick 一个个加回去, 然后再做一次union, 看看最终连到ceiling的有多少个. 增加的count, 就是正向思考时 dropped brick 数量!

##### Union Find 变种
- 还是用数字index做union find, 但是把每一个index都+1, 右移一位, 而[0]留下来做特殊用途:
- 用union at 0来 统计总共的remain count of ceiling-connected bricks, where `x = 0`. 
- 如果在其他其他题目种, 条件可能就不是`x=0`, 但也可以用这个 union index = 0 来做一个root的统计
- 关键: 把最后一个hit brick加回去, 然后再重新union一下这个hit-brick周围: count增加的变化, 不就是缺少hit-brick时候掉下去的数量.


#### DFS (timeout)
- 考虑每个hit的四周, 全部traverse, 没有连着ceiling就全部: 
- 比如是 200 x 200 的 全部是1的matrix, 任何一次traverse都要到顶; 重复计算, 所以timeout
- 算法是没错, 但是不efficient.
- 想要减少重复计算, 但是又不能提前计算: grid在不断变化. 所以看能不能把连着ceiling的都group起来, 可以O(1)快速check?


```
/**
We have a grid of 1s and 0s; the 1s in a cell represent bricks.  
A brick will not drop if and only if it is directly connected to the top of the grid, 
or at least one of its (4-way) adjacent bricks will not drop.

We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j),
the brick (if it exists) on that location will disappear, 
and then some other bricks may drop because of that erasure.

Return an array representing the number of bricks that will drop after each erasure in sequence.

Example 1:
Input: 
grid = [[1,0,0,0],[1,1,1,0]]
hits = [[1,0]]
Output: [2]
Explanation: 
If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
Example 2:
Input: 
grid = [[1,0,0,0],[1,1,0,0]]
hits = [[1,1],[1,0]]
Output: [0,0]
Explanation: 
When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. 
So each erasure will cause no bricks dropping.  
Note that the erased brick (1, 0) will not be counted as a dropped brick.
 

Note:

The number of rows and columns in the grid will be in the range [1, 200].
The number of erasures will not exceed the area of the grid.
It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
An erasure may refer to a location with no brick - if it does, no bricks drop.

*/

/**
- mark hit-brick elements
- union the rest with unionAround
- process
*/
class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int[] count;
    int[] parent;

    public int[] hitBricks(int[][] grid, int[][] hits) {
        
        int m = grid.length, n = grid[0].length;
        buildUnionFind(m * n + 1);

        // mark hit-brick
        for (int i = 0; i < hits.length; i++) {
            if (grid[hits[i][0]][hits[i][1]] == 1) {
                grid[hits[i][0]][hits[i][1]] = 2;
            }
        }

        // union all island, and accumulate count for ceiling-connected island
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    unionAround(grid, i, j);
                }
            }
        }

        int[] rst = new int[hits.length];
        int currCount = count[find(0)]; // sum of remaining ceiling-connected brick count
        for (int i = hits.length - 1; i >= 0; i--) {
            int x = hits[i][0], y = hits[i][1];
            if (grid[x][y] == 2) {
                unionAround(grid, x, y);
                grid[x][y] = 1;
            }
            int newCount = count[find(0)];
            rst[i] = newCount > currCount ? newCount - currCount - 1 : 0;
            currCount = newCount;
        }

        return rst;
    }

    private void unionAround(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        
        for (int i = 0; i < 4; i++) {
            int nX = x + dx[i], nY = y + dy[i];
            if (!validate(grid, nX, nY)) continue;
            union(x * n + y + 1, nX * n + nY + 1); // shift the save by 1 to reserve lot[0]
        }

        if (x == 0) { // if ceiling, union to ceiling brick at index = 0
            union(x * n + y + 1, 0);
        }
    }
    
    /** Classic UnionFind functions */
    private void buildUnionFind(int size) {
        parent = new int[size];
        count = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            count[i] = 1;
        }
    }

    private int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if (parentX != parentY) {
            parent[parentX] = parentY;
            count[parentY] += count[parentX]; // accumulate union size
        }
    }
    
    // validate border and equals 1
    private boolean validate(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1;
    }
}



// correct algorithm, but not efficient
// times out: every hit, we need to traverse all the way to ceiling via dfs, which consumes too much time
class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    public int[] hitBricks(int[][] grid, int[][] hits) {
        // dfs towards 4 directions
        // clean entire boolean[][] map, and count
        int[] count = new int[hits.length];
        for (int i = 0; i < hits.length; i++) {
            int x = hits[i][0], y = hits[i][1];
            grid[x][y] = 0;
            for (int j = 0; j < 4; j++) {
                Set<String> visited = new HashSet<>();
                if(!dfs(grid, visited, x + dx[j], y + dy[j])) count[i] += flip(grid, visited);
            }
        }
        
        return count;
    }
    
    private int flip(int[][] grid, Set<String> visited) {
        int count = 0;
        for (String s : visited) {
            String[] ss = s.split("@");
            int i = Integer.parseInt(ss[0]), j = Integer.parseInt(ss[1]);
            if (grid[i][j] > 0) { // visited
                grid[i][j] = 0;
                count++;
            }
        }
        return count;
    }
    
    // generates a map of connected nodes. If any node reaches top, terminate dfs.
    // return false: no ceiling reached
    // each dfs starts at '1'
    private boolean dfs(int[][] grid, Set<String> visited, int x, int y) {
        String key = x + "@" + y;
        if (!validate(grid, x, y) || visited.contains(key)) return false;
        
        //TODO: check ceiling
        if (x == 0 && grid[x][y] == 1) return true;
        visited.add(key);
        
        if (dfs(grid, visited, x + 1, y) || dfs(grid, visited, x - 1, y) || 
            dfs(grid, visited, x, y + 1) || dfs(grid, visited, x, y - 1)) {
            return true;
        }
    
        return false;
    }
    
    // validate border and equals 1
    private boolean validate(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1;
    }
}
```