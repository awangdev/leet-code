H

用HashMap的Union-find.

把board转换成1D array， 就可以用union-find来判断了。 判断时，是在四个方向各走一步，判断是否是同一个Land.

每走一次operator，都会count++. 若发现是同一个island, count--

```
/*
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
Image each isleand spot is a node in the graph, and each island(many island spots) has a root parent.
In for loop, try to add operators into the matrix one after another.
	Every time when adding a new island spot, check its sourandings and see if there are islands existed.
	If souranding island was land:
		To check if the surrouding spot are on common island (use find and union). 
		Since the operator spot was sea, the it's root parent is itself. Then, souranding spot has different island root, 
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

```