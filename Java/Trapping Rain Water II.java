H
1520744061
tags: Heap, BFS

用PriorityQueue把选中的height排序。为走位，create class Cell (x,y, height).

#### 注意几个理论
1. 从matrix四周开始考虑，发现matrix能Hold住的水，取决于height低的block。
2. 必须从外围开始考虑，因为水是被包裹在里面，外面至少需要现有一层。

以上两点就促使我们用min-heap: 也就是natural order的PriorityQueue<Cell>.

#### 具体步骤
1. process的时候，画个图也可以搞清楚: 就是四个方向都走走，用curr cell的高度减去周围cell的高度.
   若大于零，那么周围的cell就有积水。
2. 每个visited的cell都要mark. 
3. 根据4个方向的走位, 创建新cell 加进queue里面. 因为做的是缩小一圈的新围墙, height = Math.max(cell.h, neighbor.h);

再多一句解释: 和trapping water I 想法一样。刚刚从外围，只是能加到跟外围cell高度一致的水平面。往里面，很可能cell高度变化。   
这里要附上curr cell 和 move-to cell的最大高度。

#### 为什么想到用Heap (min-heap - priorityQueue)
- 要找到bucket的最短板
- 每次需要最先处理最短的那条 (on top)


#### 为什么从外向里遍历
- 木桶理论, 包水, 是从外面包住里面
- 洋葱剥皮, 用完丢掉

```
/**
LeetCode: https://leetcode.com/problems/trapping-rain-water-ii/description/
Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.
*/

/*
Thoughts:
1. Each spot needs to know the wall height from 4 directions
2. The water height of current spot is determined by the lowest of the 4 walls
=> Use Priority queue to store position sorted by height.

Go layer by layer: outside layer first, then process queue => BFS
剥洋葱皮

Time: O(mn), queue with check through all items
Space: O(mn), queue size
*/
class Solution {
    class Cell {
        int x, y, h;
        public Cell(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        if(heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
            return 0;
        }
        
        int m = heightMap.length;
        int n = heightMap[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        boolean[][] visited = new boolean[m][n];
        // Prepare queue
        PriorityQueue<Cell> queue = new PriorityQueue<Cell>(new Comparator<Cell>() {
           public int compare(Cell a, Cell b) {
               return a.h - b.h;
           }
        });
        
        // LEFT/RIGHT
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            queue.offer(new Cell(i, 0, heightMap[i][0]));
            queue.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
        }
        // TOP/BOTTOM
        for (int j = 0; j < n; j++) {
            visited[0][j] = true;
            visited[m - 1][j] = true;
            queue.offer(new Cell(0, j, heightMap[0][j]));
            queue.offer(new Cell(m - 1, j, heightMap[m - 1][j]));
        }
        
        // Calculate total
        int total = 0;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int i = 0; i < dx.length; i++) {
                int mX = cell.x + dx[i];
                int mY = cell.y + dy[i];
                if (mX >= 0 && mX < m && mY >= 0 && mY < n && !visited[mX][mY]) {
                    visited[mX][mY] = true;
                    total += cell.h > heightMap[mX][mY] ? cell.h - heightMap[mX][mY] : 0;
                    queue.offer(new Cell(mX, mY, Math.max(cell.h, heightMap[mX][mY])));
                }
            }
        }
        return total;
    }
}


/*
Trapping Rain Water II
Given n x m non-negative integers representing an elevation map 2d 
where the area of each cell is 1 x 1, compute how much water it is able to trap after raining.


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

public class Solution {
    class Cell {
        int x;
        int y;
        int h;
        public Cell(int x, int y, int height) {
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
            for (int i = 0; i < 4; i++) {
                int nx = cell.x + xs[i];
                int ny = cell.y + ys[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    sum += Math.max(0, cell.h - heights[nx][ny]);
                    queue.offer(new Cell(nx, ny, Math.max(heights[nx][ny], cell.h)));
                }
            }
        }//end while
        return sum;
    }
};


/*
*** Bellow solution is incorrect ***
Not sure why it's not correct.

Similar to the 2D version: record the highest from 4 different directions in array:
maxUp[], maxDown[], maxLeft[], maxRight[].
then calculate each index.

time: O(n^2)
space: O(n^2)
*/
class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        int[][] maxLeft = new int[m][n];
        int[][] maxRight = new int[m][n];
        int[][] maxUp = new int[m][n];
        int[][] maxDown = new int[m][n];
        
        // Prepare the highest matrixes from 4 sides
        for (int i = 0; i < m; i++) {
            maxLeft[i][0] = heightMap[i][0];
            maxRight[i][n - 1] = heightMap[i][n - 1];
        }
        for (int j = 0; j < n; j++) {
            maxUp[0][j] = heightMap[0][j];
            maxDown[m - 1][j] = heightMap[m - 1][j];
        }
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                maxLeft[i][j] = Math.max(maxLeft[i][j - 1], heightMap[i][j]);
                maxRight[i][n - j - 1] = Math.max(maxRight[i][n - j], heightMap[i][n - j - 1]);
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < m; i++) {
                maxUp[i][j] = Math.max(maxUp[i - 1][j], heightMap[i][j]);
                maxDown[m - i - 1][j] = Math.max(maxDown[m - i][j], heightMap[m - i - 1][j]);
            }
        }
        
        // Calculate total
        int total = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lowestHeight = Math.min(Math.min(maxLeft[i][j], maxRight[i][j]), Math.min(maxUp[i][j], maxDown[i][j]));
                total += lowestHeight > heightMap[i][j] ? lowestHeight - heightMap[i][j] : 0;
            }
        }
        return total;
    }
}

```