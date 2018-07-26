E
1521097808
tags: Sort, Sweep Line, PriorityQueue

- 注意接头点要考虑所有开会结会的情况，不要恰巧漏掉相接的点
- 开会的是超人。瞬间移动接上下一个会议

#### 方法1:
找是否有overlap. priorityQueue 按照start time排序好以后, 比较current和peek: current.end > peek.start?

#### 方法2: Sweep line
- class Point{pos, flag}, PriorityQueue排序。计算count
- 跟 Number of Airplanes in the Sky 是一个类型的题目

```
/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.

Hide Company Tags Facebook
Hide Tags Sort
Hide Similar Problems (H) Merge Intervals (M) Meeting Rooms II

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
/*
Thoughts:
Cannot have overlap -> sort by the interval.start using priority queue
*/
// Check over lap, sorting by priority queue
class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        PriorityQueue<Interval> queue = new PriorityQueue<Interval>(new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        
        // Sort
        for (Interval interval: intervals) {
            queue.offer(interval);
        }

        // Compare tail to head
        while (!queue.isEmpty()) {
            Interval head = queue.poll();
            Interval next = queue.peek();
            if (next != null && head.end > next.start) { // overlap happens
                return false;
            }
        }
        
        return true;
    }
}

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

/*
Thought:
Use Sweep Line.
Note: special care for edge point: make sure to process all connecting point before shuouting the result.
*/

 //use Sweep Line
public class Solution {
    class Point {
        int pos, flag;
        public Point(int pos, int flag) {
            this.pos = pos;
            this.flag = flag;
        }
    }
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        
        // Prepare sweep line points
        PriorityQueue<Point> queue = new PriorityQueue<Point>(2, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p1.pos - p2.pos;
            }
        });
        for (int i = 0; i < intervals.length; i++) {
            queue.offer(new Point(intervals[i].start, 1));
            queue.offer(new Point(intervals[i].end, -1));
        }

        int count = 0; // count how many meeting happening concurrently
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            count += p.flag;

            // For all point marked on x (overlap with point), check the flag status
            while (!queue.isEmpty() && p.pos == queue.peek().pos) {
                p = queue.poll();
                count += p.flag;
            }
            if (count > 1) {
                return false;
            }
        }
        
        return true;
    }
}
```