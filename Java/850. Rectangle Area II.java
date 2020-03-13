H
tags: Segment Tree, Sweep Line
time: O(n^2)
space: O(n)

#### Sweep Line + Merge Interval concept
- Inspired by: https://leetcode.com/problems/rectangle-area-ii/discuss/137941/Java-TreeMap-solution-inspired-by-Skyline-and-Meeting-Room
- First consider regular sweep line and realize problem: each vertical line has multiple block segments
    - Easy: take a list of vertical dots, and calculate the height diff
    - We can use a TreeMap with y-coordinate as key, so to `natural sort by y-coordinate`
- Trick: can NOT remove used y coordinate from map, because the rectangle may continue to expand to right side.
- apply simple equation to calc area: `(long)preY * (p.x - preX)`
- time: 
    - sort initial queue: O(nlogn)
    - process queue: O(n)
        - TreeMap insertion: O(logn)
        - TreeMap traversal: O(n)
    - overall, process queue can be O(n^2)
- space: O(n)

#### Sweep Line concept, bottom->top sweep
- https://leetcode.com/problems/rectangle-area-ii/discuss/137914/C%2B%2BPython-Discretization-and-O(NlogN)

#### Segment Tree
- TODO lol

```

/*
We are given a list of (axis-aligned) rectangles.  Each rectangle[i] = [x1, y1, x2, y2] , where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates of the top-right corner of the ith rectangle.

Find the total area covered by all rectangles in the plane.  Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
Output: 6
Explanation: As illustrated in the picture.
Example 2:

Input: [[0,0,1000000000,1000000000]]
Output: 49
Explanation: The answer is 10^18 modulo (10^9 + 7), which is (10^9)^2 = (-7)^2 = 49.
Note:

1 <= rectangles.length <= 200
rectanges[i].length = 4
0 <= rectangles[i][j] <= 10^9
The total area covered by all rectangles will never exceed 2^63 - 1 and thus will fit in a 64-bit signed integer.
*/


/*
#### Sweep Line
- Seems that we can sweep vertically like Meeting Room, Skyline; we we can sweep horizontally from bottom->top


*/

class Solution {
    class Point {
        int x, y, count; // count used for vertical merge sort
        Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    public int rectangleArea(int[][] rectangles) {
        int mod = 1000000007;
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> (a.x == b.x) ? b.y - a.y : a.x - b.x);
        for (int[] r : rectangles) {
            pq.offer(new Point(r[0], r[1], 1));
            pq.offer(new Point(r[0], r[3], -1));
            pq.offer(new Point(r[2], r[1], -1));
            pq.offer(new Point(r[2], r[3], 1));
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int preX = 0, preY = 0, result = 0;
        
        // O(n)
        while (!pq.isEmpty()) {
            Point p = pq.peek();
            while (!pq.isEmpty() && pq.peek().x == p.x) {
                p = pq.poll();
                map.put(p.y, map.getOrDefault(p.y, 0) + p.count);
            }
            // preX/preY=0 initialy; wait till 2nd cycle, because we ewant `p.x` to reference next x
            result += ((long)preY * (p.x - preX)) % mod;
            result %= mod;
            preY = calcHeightDiff(map);
            preX = p.x;
        }
        
        return result;
    }
    
    // Use TreeMap sort entry <Y-coordinate, count> by key by natrual order of Y-coordinate
    // merge interval vertically and produce a height
    // WHY not using a PQ to hold? The y values stays in map until later use; PQ will consume and drop the record.
    // O(nlogn)
    private int calcHeightDiff(TreeMap<Integer, Integer> map) {
        int height = 0, pre = 0, count = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (count > 0) height += e.getKey() - pre;
            count += e.getValue();
            pre = e.getKey();
        }
        return height;
    }
}

```