M
tags: Two Pointers
time: O(n)
space: O(1)



#### Method1: Merge Interval
- There can be 1 overlapping on any interval, calculate the inner intersection: lo(A[i][0], B[j][0]), hi(A[i][1], B[j][1])
    - if low <= hi, a valid intersection exist; add
    - also, if A[i][1] < B[j][1]; that is A[i].end < B[j].end, then i++; otherwise j++
        - because the further-away `end` has been used, so move on.
- O(n)

#### Method2: Sweep line
- code is much more complex (pq, Point, process code... etc) than method1
- we can use point to track open/close, also want to specify if point belongs to A/B
- mark 2 global parameters: aOpen, bOpen.
    - process when A/B close, record if (aOpen, bOpen) has overlaop
    - clear up corresponding global parameter after A/B closes
- sort all pointers in priority queue by index
- Point: {boolean isOpen; int index}
- process the queue and remember to clean up all items on same index
- time: O(nlogn)
- space: O(n)


```
/*
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  
The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  
For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

 

Example 1:



Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 

Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/

/*
#### Method1 Merge Interval
- There can be 1 overlapping on any interval: lo(start points) <= hi (end points)
- move up which ever interval.end is the left, because that must have been processed
- O(n)
*/
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> list = new ArrayList<>();

        int i = 0, j = 0;
        int m = A.length, n = B.length;
        while (i < m && j < n) {
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if (lo <= hi) list.add(new int[]{lo, hi});
           
            if (A[i][1] < B[j][1]) i++;
            else j++;
        }
       
        int[][] rst = new int[list.size()][2];
        for (int k = 0; k < list.size(); k++) {
            rst[k] = list.get(k);
        }
       
        return rst;
    }
   
}


/*
Method2: Sweep line
- we can use point to track open/close, also want to specify if point belongs to A/B
- we'll mark 2 global parameters: aOpen, bOpen.
    - process when A/B close, record if (aOpen, bOpen) has overlaop
    - clear up corresponding global parameter after A/B closes
- sort all pointers in priority queue by index
- Point: {boolean isOpen; int index}
- process the queue and remember to clean up all items on same index
- time: O(nlogn)
- space: O(n)
*/
class Solution {
   
    class Point {
        boolean isOpen;
        int index;
        char mark;
        public Point(int index, boolean isOpen, char mark) {
            this.index = index;
            this.isOpen = isOpen;
            this.mark = mark;
        }
    }
    Integer openA = null, openB = null;
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> list = new ArrayList<>();

        PriorityQueue<Point> queue = buildQueue(A, B);
       
        while (!queue.isEmpty()) {
            List<Point> points = new ArrayList<>();
            Point p = queue.poll();
            points.add(p);
            while (!queue.isEmpty() && queue.peek().index == p.index) {
                points.add(queue.poll());
            }
            int[] output = processPoints(points);
            if (output != null) list.add(output);
        }
       
        int[][] rst = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            rst[i] = list.get(i);
        }
       
        return rst;
    }
   
    public int[] processPoints(List<Point> points) {
        Integer end = null, start = null;
        int[] output = null;
       
        // process
        for (Point p : points) {
            int index = p.index;
            if (p.mark == 'A' && p.isOpen) openA = index;
            else if (p.mark == 'B' && p.isOpen) openB = index;
            else if (!p.isOpen) end = index;
        }
        if (openA != null && openB != null && end != null) {
            output = new int[]{Math.max(openA, openB), end};
        }
        // clean up
        for (Point p : points) {
            if (p.mark == 'A' && !p.isOpen) openA = null;
            if (p.mark == 'B' && !p.isOpen) openB = null;
        }
        return output;
    }
   
    public PriorityQueue<Point> buildQueue(int[][] A, int[][] B) {
        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparing(p -> p.index));
        populate(queue, A, 'A');
        populate(queue, B, 'B');
        return queue;
    }
   
    private void populate(PriorityQueue<Point> queue, int[][] arr, char mark) {
        for (int i = 0; i < arr.length; i++) {
            queue.offer(new Point(arr[i][0], true, mark));
            queue.offer(new Point(arr[i][1], false, mark));
        }
    }
   
}
```