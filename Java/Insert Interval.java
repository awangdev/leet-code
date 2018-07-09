H
1528350453
tags: Array, Sort, PriorityQueue

#### Sweep Line
- Interval 拆点，PriorityQueue排点
- Merge时用count==0作判断点
- 注意, 一定要compare curr `p.x == queue.peek().x` 确保重合的点全部被process: `count+=p.x`
- PriorityQueue: O(logN). 扫n点, 总共：O(nLogn)


#### Basic Implementation
- 这里已经给了 **sorted** intervals by start point.
- 直接找到可以insert newInterval的位子. Insert
- 然后loop to merge entire interval array
- 因为给的是个list, 所以方便`intervals.remove(i)`
- remove之前都会重新assgin `pre.end`, 确保被remove的node.end 被capture
- O(n) 

#### 另外
- 因为interval已经sort, 本想用Binary Search O(logn). 
- 但是找到interval insert position 最后 merge还是要用 O(n), 所以不必要 binary Search

```

/*
Given a non-overlapping interval list which is sorted by start point.

Insert a new interval into it, make sure the list is still in order and non-overlapping 
(merge intervals if necessary).

Example
Insert [2, 5] into [[1,2], [5,9]], we get [[1,9]].

Insert [3, 4] into [[1,2], [5,9]], we get [[1,2], [3,4], [5,9]].

Tags Expand 
Basic Implementation

*/

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */



/*
Thoughts:
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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> rst = new ArrayList<>();
        if (intervals == null || intervals.size() == 0 || newInterval == null) {
            if (newInterval != null) rst.add(newInterval);
            return rst;
        }
        
        // build priority queue
        PriorityQueue<Point> queue = buildQueue(intervals, newInterval);
        // iterate over queue, count and build interval
        int count = 0;
        Interval interval = new Interval();
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (count == 0) {//detect start
                interval.start = p.val;
            }    
            
            count += p.flag;
            while (!queue.isEmpty() && p.val == queue.peek().val) {
                p = queue.poll();
                count += p.flag;
            }
            
            if (count == 0) {
                interval.end = p.val;
                rst.add(interval);
                interval = new Interval();
            }
        }
        return rst;
    }
    
    private PriorityQueue<Point> buildQueue(List<Interval> intervals, Interval newInterval) {
        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparing(p -> p.val));
        queue.offer(new Point(newInterval.start, 1));
        queue.offer(new Point(newInterval.end, -1));
        for (Interval interval : intervals) {
            queue.offer(new Point(interval.start, 1));
            queue.offer(new Point(interval.end, -1));
        }
        return queue;
    }
    
}




/*
O(n) time, O(1) space
Thoughts:
1. Find right position to insert: find the last start position that's <= newInterval.start
2. After insertion, merge.
3. How to merge? Look at merge inerval question
*/
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.size() == 0 || newInterval == null) {
            if (newInterval != null) {
                intervals.add(newInterval);
            }
            return intervals;
        }
        
        //Insert
        int index = 0;
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).start <= newInterval.start) {
                index = i;
                break;
            }
        }
        intervals.add(index, newInterval);
        return merge(intervals);
    }
    
    private List<Interval> merge(List<Interval> intervals) {
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


/*
    2.26.2016 Not done. Looks like merging process still take O(n)
    Can we do Binary Search for the index to insert newInterval.start, newInterval.end
    find position x that has newInterval.start <= x.start
    find position y that has y.end <= newInterval.end
*/
class Solution {
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();

        //Biary search for inStart, inEnd
        int inStart = 0
        int inEnd = 0;
        int start = 0;
        int end = intervals.size();
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start)/2;
            if (newInterval.start <= intervals.get(mid)) {
                if (mid == 0 || intervals.get(mid).start == newInterval.start 
                    || intervals.get(mid-1).start < newInterval.start) {
                    inStart = mid;
                    break;
                }
                end = mid;
            } else {
                start = mid;
            }
        }
        start = 0;
        end = intervals.size();
        while (start + 1 < end) {
            mid = start + (end - start)/2;
            if (newInterval.end <= intervals.get(mid)) {
                if (mid == 0 || intervals.get(mid).start == newInterval.end 
                    || intervals.get(mid-1).start < newInterval.end) {
                    inEnd = mid;
                    break;
                }
                end = mid;
            } else {
                start = mid;
            }
        }
        
        int diff = 
        

        return result;
    }
}


```
