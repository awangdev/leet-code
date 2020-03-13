M
tags: Heap, Sort, Divide and Conquer
time: O(klogk)
space: O(k)

#### PriorityQueue
- Create customized Point{} class
- Sort by distance
- Maintain queue size <= K

#### Divide and Conquer
- ?, select sort?

```
/*
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 

Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
*/


class Solution {
    class Point {
        int x, y, distance;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.distance = x * x + y * y;
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        if (points == null || K < 0 ) return new int[][]{};
        
        PriorityQueue<Point> queue = new PriorityQueue<>(
            Comparator.comparing((Point p) -> p.distance).reversed());
        
        for (int[] point : points) {
            queue.offer(new Point(point[0], point[1]));
            if (queue.size() > K) queue.poll();
        } 
        return buildResult(queue);
    }
    
    private int[][] buildResult(PriorityQueue<Point> queue) {
        int[][] result = new int[queue.size()][2];
        while(!queue.isEmpty()) {
            Point p = queue.poll();
            result[queue.size()] = new int[]{p.x, p.y};
        }
        return result;
    }
}

```