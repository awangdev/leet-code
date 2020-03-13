M
tags: Array, Sort, Sweep Line, PriorityQueue
time: O(nlogn)
space: O(n)


给一串int[Interval] (unsorted), 把所以Interval merge起来.

#### Method1: Sweep Line with Priority Queue
- O(nlogn) time (PriorityQueue), O(n) space     
    - 1. 扫描线+Count: when `count==0`, startFlags==endFlags. 是interval的开头/结尾 (write an example)
    - 2. Note: remember to merge points on same sweep line position
- Comparator: `new PriorityQueue<>(Comparator.comparing(p -> p.val))`;

#### Method2: Sort Intervals and append end logically
- Sort intervals: O(nlogn), extra space O(n) when creating rst list
    - `Arrays.sort(intervals, Comparator.comparing(i -> i[0]));`
    - 找到结尾 interval, 满足条件就可以save
    - 如果不到return的条件, 就继续延伸 interval.end

#### Method3: Sort Interval, Remove overlaop interval & modify interval
- Less applicable when input is `int[][] intervals`, but more applicable when we have `List<int[]> intervals`
- Related example: Insert Interval
- Sort fist, loop over and merge, cut off overlapped interval. 
    - sort by Interval.start: `intervals.sort(Comparator.comparing(interval -> interval.start)); // O(nlogn)`
    - 用两个相连的Interval: curr, next
    - 如果 curr.end覆盖了 next.start: 需要merge. 那么比较一下 curr.end vs. next.end    
    - 一旦merge, 需要remove被覆盖的 next interval: `list.remove(i+1)`
    - 若没有重合，就继续iteration
- time O(nlogn), space O(1)

/*
    // old comparator
    new Comparator<Object>(){
        public int compare(obj1, obj2) {
            return obj1.x - obj2.x;
        }
    }
    // simplier way to define comparator
    Comparator.comparing(p -> p.val)

    // example
    intervals.sort(Comparator.comparing(interval -> interval.start));
*/


```
/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

*/

/*
Method1: sweep line, extra space used
- turn into point struct, sort
- mark start flag = 1, end flag = -1. struct { int val, flag = 1/-1;}
- when count == 0, close a period
*/
class Solution {
    public class Point {
        int val, flag;
        public Point(int val, int flag) {
            this.val = val;
            this.flag = flag;
        }
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null) return intervals;

        PriorityQueue<Point> queue = build(intervals);
        List<int[]> list = compute(queue);
        
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    
    private List<int[]> compute(PriorityQueue<Point> queue) {
        List<int[]> list = new ArrayList<>();
        
        int count = 0;
        int[] interval = new int[2];
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (count == 0) interval[0] = p.val;
            count += p.flag;
            //proces all points on same position p.val
            while (!queue.isEmpty() && p.val == queue.peek().val) {
                p = queue.poll();
                count += p.flag;
            }
            if (count == 0) {//detect end
                interval[1] = p.val;
                list.add(interval);
                interval = new int[2];
            }
        }
        return list;
    }

    private PriorityQueue<Point> build(int[][] intervals) {
        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparing(p -> p.val));
        for (int[] interval : intervals) {
            queue.offer(new Point(interval[0], 1));
            queue.offer(new Point(interval[1], -1));
        }
        return queue;
    }
}


/**
    Method2: Sort Intervals and append end logically
 */
// extra space used for rst O(n)
class Solution {
    int START = 0;
    int END = 1;
    public int[][] merge(int[][] intervals) {
        if (intervals == null) return intervals;
        List<int[]> rst = new ArrayList();

        // Sort intervals
        Arrays.sort(intervals, Comparator.comparing(i -> i[START]));

        int[] last = null;
        for (int[] curr : intervals) {
            if (last == null || last[END] < curr[START]) {// Found closure
                rst.add(curr);
                last = curr;
            } else { // last != null && last[END] >= curr[START]
                // Keep `last` to have the longest END index possible, 
                // so to merge all visited interval in-between
                last[END] = Math.max(last[END], curr[END]);
            }
        }

        int[][] result = new int[rst.size()][2];
        for (int i = 0; i < rst.size(); i++) result[i] = rst.get(i);
        return result;
    }
}


// Old fn interface with List<Interval>
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> rst = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return rst;
        }
        // Sort intervals
        intervals.sort(Comparator.comparing(i -> i.start));
        
        Interval last = null;
        for (Interval item : intervals) {
            if (last == null || last.end < item.start) {// Found on item, add.
                rst.add(item);
                last = item;
            } else {
                // Keep the last item to have longest end index possible, 
                // so to merge all visited index
                last.end = Math.max(last.end, item.end);
            }
        }

        return rst;
    }
}




/*
    Method3: 
    Space O(1), time: O(nlogn)

    Sort by start time.
    then it overlaps: check on pre.end and curr.start.
    if overlaps: curr.start will be overlapped; also check on curr.end and pre.end, decide who ends this interval
    
    border case: null, return itself; or length==1, return.
*/

class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        intervals.sort(Comparator.comparing(interval -> interval.start)); // O(nlogn)
        Interval prev = intervals.get(0);
        Interval curr;

        for (int i = 1; i < intervals.size(); i++) {
            curr  = intervals.get(i);
            if (prev.end >= curr.start) {
                prev.end = prev.end >= curr.end ? prev.end : curr.end;
                intervals.remove(i);
                i--;
            } else {
                prev = curr;         
            }
        }
        return intervals;
    }
}

class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        intervals.sort(Comparator.comparing(interval -> interval.start)); // O(nlogn)
        int i = 0;
        while(i < intervals.size() - 1) {
            Interval curr = intervals.get(i), next = intervals.get(i + 1);
            if (curr.end >= next.start) {
                curr.end = curr.end >= next.end ? curr.end : next.end;
                intervals.remove(i + 1);
                continue;
            }
            i++;
        }
        return intervals;
    }
}


```
