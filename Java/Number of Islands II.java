H
1520396316
tags: Union Find

给一个island grid[][], and list of operations to fill a particualr (x,y) position.

count # of remaining island after each operation.

#### Union Find, model with int[]
- 把board转换成1D array， 就可以用union-find来判断了. 
- 用int[] father 的unionFind, 需要转换2D position into 1D index. 这样比较clean
- 判断时，是在四个方向各走一步，判断是否是同一个Land.
- 每走一次operator，都会count++. 若发现是同一个island, count--
- count的加减, 都放在了UnionFind自己的function里面, 方便tracking, 给几个helper function就对了.
- Time: O(k * log(mn))

#### Union Find, model with Hashmap 
- 用HashMap的Union-find.


#### Note:
- Proof of UnionFind log(n) time: https://en.wikipedia.org/wiki/Proof_of_O(log*n)_time_complexity_of_union%E2%80%93find

```
/*
A 2d grid map of m rows and n columns is initially filled with water. 
We may perform an addLand operation which turns the water at position (row, col) into a land. 

Given a list of positions to operate, count the number of islands after each addLand operation. 

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 

You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

*/

/*
Thoughts:

1. UnionFind with count of island: initially 0 island, with addLand, it creates land.
2. Need to union with the 4 directions every time when adding new land.
3. Turn 2D array into 1D, then use unionFind.
4. have query function in UnionFind to get final result.

Time: O(k logm*n): k = positions.length; union(x,y) time is log(m*n)
https://en.wikipedia.org/wiki/Proof_of_O(log*n)_time_complexity_of_union%E2%80%93find
*/
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> rst = new ArrayList<>();
        if (validateInput(m, n, positions)) {
            return rst;
        }
        
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int[][] grid = new int[m][n];
        UnionFind unionFind = new UnionFind(m * n);
        
        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];
            if (grid[x][y] == 1) { // no need to fill
                continue;
            }
            grid[x][y] = 1;
            unionFind.increaseCount();
            for (int j = 0; j < dx.length; j++) {
                int movedX = x + dx[j];
                int movedY = y + dy[j];
                if (validateBorder(grid, movedX, movedY, m, n)) {
                    unionFind.union(x * n + y, movedX * n + movedY);
                }
            }
            rst.add(unionFind.query());
        }

        return rst;
    }

    private boolean validateBorder(int[][] grid, int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1;
    }

    private boolean validateInput(int m, int n, int[][] positions) {
        return m <= 0 || n <= 0 || positions == null || positions.length == 0 || positions[0] == null || positions[0].length == 0;
    }
}

class UnionFind {
    int[] father;
    int count;
    
    public UnionFind(int x) {
        father = new int[x];
        count = 0;
        for (int i = 0; i < x; i++) {
            father[i] = i;
        }
    }
    
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            father[rootX] = rootY;
            count--;
            return;
        }
    }
    
    public int query() {
        return count;
    }
    
    public void increaseCount() {
        count++;
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
The problem is to find how many disjoint sets there are on the island. We should use union-find approach.
1. Build Union-Find class
2. Greedy approach: every new spot-fill will count++ first.
3. Go 4 directions from the spot, and check if the adjacent spots are islands.
4. If a adjacent spot is island, use union-find to figure out both current && adjacent spot's root parent.
5. If the root parents are different: the are not joined together and count has been over-added, so count--

Note1: hugely relying on the default behavior of Union-Find: find parent && be able to union two set. Even though the union-find code is just 3 lines.
Note2: The Union-Find solution we provided is based on 1D array, using a HashMap<child, parent>. So, if we want to use this Union-Find model, 
we need to transform the 2D array into 1D array. 1D array postion = x * col + y.
Note3: There can be other ways to model UnionFind, for instance, just use a array: int[] roots = new int[m * n];
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

    private int dx[] = {1, -1, 0, 0};
    private int dy[] = {0, 0, 1, -1};
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        final List<Integer> result = new ArrayList<>();
        if (positions == null || positions.length == 0 || positions[0].length == 0) {
            return result;
        }
        
        int count = 0;
        final int[] islands = new int[m * n];
        final UnionFind unionFind = new UnionFind(m * n);
        for (int i = 0; i < positions.length; i++) {
            // Find current spot and mark as 1. Greedily count++
            int x = positions[i][0];
            int y = positions[i][1];
            int pos = x * n + y;
            islands[pos] = 1;
            count++;
            // Check if pos's adjacent spots has been marked as island; if so, count--
            for (int j = 0; j < dx.length; j++) {
                int adjX = x + dx[j];
                int adjY = y + dy[j];
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
            result.add(count);
        }
        return result;
    }
    
}


/*
LintCode:
Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k). 
Originally, the 2D matrix is all 0 which means there is only sea in the matrix. 
The list pair has k operator and each operator has two integer A[i].x, A[i].y means 
that you can change the grid matrix[A[i].x][A[i].y] from sea to island. 

Return how many island are there in the matrix after each operator.


Example
Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].

return [1,1,2,2].

Note
0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

Tags Expand 
Union Find
*/


/*
Thoughts:
Each pos(x,y) turns that sea spot into a island spot.
Imagine each island spot is a node in the graph, and each island(many island spots) has a root parent.
In for loop, try to add operators into the matrix one after another.
	Every time when adding a new island spot, check its sourandings and see if there are islands existed.
	If surrounding spot was island:
		To check if the surrouding spot are on common island (use find and union). 
		Since the operator spot was sea, then it's root parent is itself. Then, souranding spot has different island root, 
		they will surely have differet root parent, but they will do after they connect, so we do count--.

On the otherhand, if surrounding was just sea, then count++ is natural

Note:
1. Know how to write up simple union find class
2. Convert 2D array into 1D
*/

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
	class UnionFind {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		public UnionFind(int length) {
			for (int i = 0; i < length; i++) {
				map.put(i,i);
			}
		}
		public int find(int target) {
			int parent = map.get(target);
			while (parent != map.get(parent)) {
				parent = map.get(parent);
			}
			return parent;
		}

		public void union(int x, int y) {
			int findX = find(x);
			int findY = find(y);
			if (findX != findY) {
				map.put(findX, findY);
			}
		}
	}
    /**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
    	List<Integer> rst = new ArrayList<Integer>();
    	if (operators == null || operators.length == 0) {
    		return rst;
    	}
    	int count = 0;
    	int[] island = new int[m*n];
    	UnionFind uf = new UnionFind(m*n);
    	int[] xs = {-1, 1,  0, 0};
    	int[] ys = {0,  0, -1, 1};
    	for (int i = 0; i < operators.length; i++) {
    		int x = operators[i].x;
    		int y = operators[i].y;
    		int pos = x * m + y;
    		count++;
    		if (island[pos] != 1) {
    			island[pos] = 1;
	    		for (int j = 0; j < 4; j++) {
	    			int nx = x + xs[j];
	    			int ny = y + ys[j];
	    			int newPos = nx * m + ny;
	    			if (nx >= 0 && nx < n && ny >= 0 && ny < m && island[newPos] == 1) {//when new position is land
	    				int findA = uf.find(pos);
	    				int findB = uf.find(newPos);
	    				if (findA != findB) {
	    					count--;
	    					uf.union(pos, newPos);
	    				}
	    			}
	    		}
    		}
    		rst.add(count);
    	}

    	return rst;
    }
}


/*
Thoughts:
A naive solution would be utilizing numIslands.
It's accurate and brutle. However, it timesout at 155 / 158 test cases.
It's O(mn)*O(k) which could lead to O(n^3) if given n = m = k.
*/
class Solution {
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        final List<Integer> result = new ArrayList<>();
        if (positions == null || positions.length == 0 || positions[0].length == 0) {
            return result;
        }
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '0';
            }
        }
        for (int i = 0; i < positions.length; i++) {
            grid[positions[i][0]][positions[i][1]] = '1';
            result.add(numIslands(grid));
        }
        return result;
    }
    
    private int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        char[][] localGrid = new char[grid.length][grid[0].length];
         for (int i = 0; i < localGrid.length; i++) {
            for (int j = 0; j < localGrid[i].length; j++) {
                localGrid[i][j] = grid[i][j];
            }
        }
        
        int count = 0;
        for (int i = 0; i < localGrid.length; i++) {
            for (int j = 0; j < localGrid[i].length; j++) {
                if (localGrid[i][j] == '1') {
                    count++;
                    dfs(localGrid, i, j);
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

```