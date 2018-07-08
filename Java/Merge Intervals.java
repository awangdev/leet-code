M
1531077570
tags: Array, Sort, Sweep Line, PriorityQueue

给一串int[Interval] (unsorted), 把所以Interval merge起来.

#### Sweep Line with Priority Queue
- O(nlogn) time (PriorityQueue), O(n) space     
- 扫描线+Count无敌手。注意start end把interval给合起来。   
- count==0的时候，就是每次start end双数抵消的时候，就应该是一个interval的开头/结尾。写个例子就知道了。   
- 记得怎么写comparator. New way: new PriorityQueue<>(Comparator.comparing(p -> p.val));
- 在 LeetCode里面，Sweep Line比方法2要快很多.

#### Sort Interval 
- Sort by interval.start之后，试着跑一遍，按照merge的需求，把需要merge的地方续好，然后减掉多余的interval就好。
- sort by Interval.start: `intervals.sort(Comparator.comparing(interval -> interval.start)); // O(nlogn)`
- Related example: Insert Interval
- 用两个相连的Interval: curr, next
- 如果 curr.end覆盖了 next.start: 需要merge. 那么比较一下 curr.end vs. next.end    
- 一旦merge, 需要remove被覆盖的 next interval: `list.remove(i+1)`
- 若没有重合，就继续iteration
- time O(nlogn), space O(1)

#### Sort Intervals and append end logically
- Sort intervals: O(nlogn), extra space O(n) when creating rst list
- 找到结尾 interval, 满足条件就可以save
- 如果不到return的条件, 就继续延伸 interval.end

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

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

Tags: Array, Sort
Similar Problems: (H) Insert Interval, (E) Meeting Rooms (M) Meeting Rooms II

*/
// sweep line, extra space used
class Solution {
    class Point {
        int val, flag;
        public Point(int val, int flag) {
            this.val = val;
            this.flag = flag;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> rst = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return rst;
        }
        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparing(p -> p.val));
        
        for (Interval interval : intervals) {
            queue.offer(new Point(interval.start, 1));
            queue.offer(new Point(interval.end, -1));
        }
        
        int count = 0;
        Interval interval = new Interval();
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (count == 0) {//detect start
                interval.start = p.val;
            }    
            count += p.flag;
            //proces all points on same position p.val
            while (!queue.isEmpty() && p.val == queue.peek().val) {
                p = queue.poll();
                count += p.flag;
            }
            if (count == 0) {//detect end
                interval.end = p.val;
                rst.add(interval);
                interval = new Interval();
            }
        }
        
        return rst;
    }
}

/*
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
Thoughts:
Again use Sweep Line. Quite similar to Meeting Rooms, flight schedules... etc
This one: when count ==0, make sure to keep track start and end, add into the rst
When writing out example, whenever count==0, that indicates an end of a interval.

HOWEVER, this uses O(n) space, while this problem requests O(1) space
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    class Point{
        int x, flag;
        public Point(int x, int flag) {
            this.x = x;
            this.flag = flag;
        }
    }
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> rst = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
            return rst;
        }
        PriorityQueue<Point> queue = new PriorityQueue<Point>(2, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p1.x - p2.x;
            }
        });
        
        for (Interval entry : intervals) {
            queue.offer(new Point(entry.start, 1));
            queue.offer(new Point(entry.end, -1));            
        }
        
        int count = 0;
        int start = 0;
        int end = 0;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (count == 0) {//detect start
                start = p.x;
            }    
            count += p.flag;
            while (!queue.isEmpty() && p.x == queue.peek().x) {//proces all points on same position x
                p = queue.poll();
                count += p.flag;
            }
            if (count == 0) {//detect end
                end = p.x;
                rst.add(new Interval(start, end));
            }
        }
        
        return rst;
    }
}

// extra space used for rst O(n)
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


```
