/*
Trapping Rain Water II
Given n x m non-negative integers representing an elevation map 2d where the area of each cell is 1 x 1, compute how much water it is able to trap after raining.


Example
Given 5*4 matrix

[12,13,0,12]
[13,4,13,12]
[13,8,10,12]
[12,13,12,12]
[13,13,13,13]
return 14.

Tags Expand 

LintCode Copyright Heap Matrix

*/

/*
Thoughts: same idea as the trap Rain Water I.
Since this is not 1-way run through a 1D array (2D array can go 4 directions...), need to mark visted spot.

Use PriorityQueue, sort lowest on top, because the lowest surroundings determines the best we can get.

Bukkit theory: the lowest bar determines the height of the bukkit water. So, we always process the lowest first. 
Therefore, we use a min-heap, a natural order priorityqueue based on height.

Note: when adding a new block into the queue, comparing with the checked origin, we still want to add the higher height into queue. 
(The high bar will always exist and hold the bukkit.)

Step:
1. Create Cell (x,y,h)
2. Priorityqueue on Cell of all 4 borders
3. Process each element in queue, and add surrounding blocks into queue.
4. Mark checked block


*/


//NOT TEST YET
public class Solution {
	Class Block {
		int x;
		int y;
		int h;
		public Block(int x, int y, int height) {
			this.x = x;
			this.y = y;
			this.h = height;
		}
	}
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    public int trapRainWater(int[][] heights) {
    	if (heights == null || heights.length == 0 || heights[0].length == 0) {
    		return 0;
    	}

    	PriorityQueue<Cell> queue = new PriorityQueue<Cell>(1, new Comparator<Cell>(){
    		public int compare(Cell A, Cell B) {
    			return A.h - B.h;
    		}
    	});
    	int n = heights.length;
    	int m = heights[0].length;
    	boolean[][] visited = new boolean[n][m];

    	//LEFT-RIGHT
    	for (int i = 0; i < n; i++) {
    		visited[i][0] = true;
    		visited[i][m - 1] = true;
    		queue.offer(new Cell(i, 0, heights[i][0]));
    		queue.offer(new Cell(i, m - 1, heights[i][m - 1]));
    	}
    	//TOP-BOTTOM
    	for (int i = 0; i < m; i++) {
    		visited[0][i] = true;
    		visited[n - 1][i] = true;
    		queue.offer(new Cell(0, i, heights[0][i]));
    		queue.offer(new Cell(n - 1, i, heights[n - 1][i]));
    	}

    	int[] xs = {0,  0, 1, -1};
    	int[] ys = {1, -1, 0,  0};
    	int sum = 0;
    	while (!queue.isEmpty()) {
    		Cell cell = queue.poll();
    		visited[cell.x][cell.y] = true;
    		for (int i = 0; i < 4; i++) {
    			int nx = cell.x + xs[i];
    			int ny = cell.y + xs[i];
    			if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
    				visited[nx][ny] = true;
    				if (cell.h > heights[nx][ny]) {
    					sum+= (cell.h - heights[nx][ny]);
    				}
    				//OR sum += Math.max(0, cell.h - heights[nx][ny])
    				queue.offer(new Cell(nx, ny, Math.max(heights[nx][ny], cell.h)));
    			}
    		}
    	}//end while
    	return sum;
    }
};















