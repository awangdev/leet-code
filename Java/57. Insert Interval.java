H
tags: Array, Sort, PriorityQueue, Sweep Line
time: O(n)
space: O(n)

#### Method1: Convert to list, insert, and merge list
- 这里已经给了 **sorted** intervals by start point;
    - 1) 直接找到可以insert newInterval的位子. Insert and convert to list
    - 2) Merge: Use `pre, curr` to iterate over list, and remove curr after merging
        - remove之前都会重新assgin `pre.end`, 确保被remove的node.end 被capture
    - 3) Convert back to int[][]
- time/space: O(n) 
- code is slightly better to read

#### Method2: Insert on the fly, and handle edge cases
- handle edge cases:
    - new interval is non-overlapping
        - 1) head
        - 2) tail
        - 3) in middle
    - new interval is overlapping:
        - 1) end index in existing interval; reuse the existing interval end to close new range
        - 2) end index in the gap of 2 intervals, use new interval.end to close the new range
- time, space: O(n)

#### Method3: Sweep Line
- Interval 拆点，PriorityQueue排点
- Merge时用count==0作判断点
- 注意, 一定要compare curr `p.x == queue.peek().x` 确保重合的点全部被process: `count+=p.x`
- PriorityQueue: O(logN). 扫n点, 总共：O(nLogn). SLOW.


#### 另外
- 因为interval已经sort, 本想用Binary Search O(logn). 
- 但是找到interval insert position 最后 merge还是要用 O(n), 所以不必要 binary Search

```

/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

*/
/**
Method1: convert to list first, and use a merge function
*/
class Solution {
    int START = 0, END = 1;
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if (n == 0) return new int[][]{newInterval};
        List<int[]> list = addInterval(intervals, newInterval); //1. Insert without merge
        merge(list);//2. Merge
        return toArray(list); // 3. convert back to int[][]
    }

    private void merge(List<int[]> list) {
        int[] pre = list.get(0), curr = null;
        for (int i = 1; i < list.size(); i++) {
            curr = list.get(i);
            if (pre[END] >= curr[START]) {
                pre[END] = pre[END] > curr[END] ? pre[END] : curr[END];
                list.remove(i);
                i--;
            } else pre = curr;
        }
    }

    private List<int[]> addInterval(int[][] intervals, int[] newInterval) {
        List<int[]> list = new LinkedList<>();
        Integer front = null;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][START] <= newInterval[START]) front = i + 1; // found the spot to insert
            list.add(intervals[i]);
        }
        // insert newInterval
        if (front == null) list.add(0, newInterval);
        else list.add(front, newInterval);
        return list;
    }
    
    private int[][] toArray(List<int[]> list) {
        int[][] rst = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            rst[i][START] = list.get(i)[START];
            rst[i][END] = list.get(i)[END];
        }
        return rst;
    } 
}

/* Method2: Insert on the fly, and handle edge cases
- handle edge cases:
    - new interval is non-overlapping
        - 1) head
        - 2) tail
        - 3) in middle
    - new interval is overlapping:
        - 1) end index in existing interval; reuse the existing interval end to close new range
        - 2) end index in the gap of 2 intervals, use new interval.end to close the new range
- time, space: O(n)
*/
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int start = newInterval[0], end = newInterval[1];
        if (n == 0) return new int[][]{newInterval};

        List<int[]> list = new LinkedList<>();
        if (end < intervals[0][0]) list.add(0, new int[]{start, end}); //non-overlapping: head
        
        for (int i = 0; i < n; i++) {
            int lo = intervals[i][0], hi = intervals[i][1];
            if (lo > end || hi < start) {
                list.add(intervals[i]); // free interval to insert
                if (hi < start && isEnd(intervals, end, i)) list.add(new int[]{start, end}); // non-overlapping insert
                continue;
            }
            if (lo <= start && start <= hi) start = lo;
            if (lo <= end && end <= hi) end = hi;
            if (end == hi || isEnd(intervals, end, i)) list.add(new int[]{start, end}); // merge
        }
        
        // non-overlapping: tail
        if (end > intervals[n-1][1]) list.add(new int[]{start, end});
        return convert(list);
    }
    
    private boolean isEnd(int[][] intervals, int end, int i) {
        return i + 1 < intervals.length && end < intervals[i+1][0];
    }

    private int[][] convert(List<int[]> list) {
        int[][] rst = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            rst[i][0] = list.get(i)[0];
            rst[i][1] = list.get(i)[1];
        }
        return rst;
    }
}




/*
Method3: sweep line
What's the difference from merge intervals?
1. Create Class point (x, flag)
2. sort point in min-heap
3. when count increase and decreases to 0, that means we can close an interval
*/
/*
Option1: 
- convert all intervals into points with open/close flag, sort, and merge. When count == 0, close a interval.
- priority queue, O(n) space, O(nlogn) time
*/
class Solution {
    class Point {
        int val, flag;
        public Point(int val, int flag) {
            this.val = val;
            this.flag = flag;
        }
    }
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> rst = new LinkedList<>();
        PriorityQueue<Point> queue = buildQueue(intervals, newInterval);

        int count = 0, start = 0, end = 0;
        while (!queue.isEmpty()) { // iterate over queue, count and build interval
            Point p = queue.poll();
            if (count == 0) start = p.val; //detect start
            count += p.flag;

            while (!queue.isEmpty() && p.val == queue.peek().val) { // overlapping point
                p = queue.poll();
                count += p.flag;
            }
            
            if (count == 0) rst.add(new int[]{start, p.val}); // detect end; end = p.val;
        }
        return convert(rst);
    }
    
    private PriorityQueue<Point> buildQueue(int[][] intervals, int[] newInterval) {
        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparing(p -> p.val));
        queue.offer(new Point(newInterval[0], 1));
        queue.offer(new Point(newInterval[1], -1));
        for (int[] interval : intervals) {
            queue.offer(new Point(interval[0], 1));
            queue.offer(new Point(interval[1], -1));
        }
        return queue;
    }
    
    private int[][] convert(List<int[]> list) {
        int[][] rst = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            rst[i][0] = list.get(i)[0];
            rst[i][1] = list.get(i)[1];
        }
        return rst;
    }   
}




```
