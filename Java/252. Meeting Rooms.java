E
tags: Sort, Sweep Line, PriorityQueue
time: O(nlogn)
space: O(1)

- 注意接头点要考虑所有开会结会的情况，不要恰巧漏掉相接的点
- 开会的是超人。瞬间移动接上下一个会议

#### Method1: sort input and compare if curr.end & next.start overlaps
- sort: `Arrays.sort(intervals, Comparator.comparing(i -> i[0]))`
- time: O(nlogn), space: O(1)

#### Method2: Sweep line
- class Point{pos, flag}, PriorityQueue排序。计算count
- 跟 Number of Airplanes in the Sky 是一个类型的题目
- time: O(nlogn), space O(n)
- Not necessary for this problem, since it requires extra space with pq.

```
/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

Example 1:

Input: [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: [[7,10],[2,4]]
Output: true
*/

/*
Method1:
- sort by first digit O(nlogn)
- and compare if end time overlaps
*/
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null) return true;
        Arrays.sort(intervals, Comparator.comparing(i -> i[0]));

        // Compare tail to head
        for (int i = 0; i < intervals.length - 1; i++) {
            // overlap happens
            if (intervals[i][1] > intervals[i + 1][0]) return false;
        }
        return true;
    }
}

/*
Method2: Sweep line
- store all time to point(val, flag) and sort by val
- check if at any given time, count flag is >=2, meaning 2 meetings are open
- extra space O(n)
*/
public class Solution {
    class Point {
        int pos, flag;
        public Point(int pos, int flag) {
            this.pos = pos;
            this.flag = flag;
        }
    }

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null) return true;
        
        // Prepare sweep line points
        PriorityQueue<Point> queue = buildQueue(intervals);

        int count = 0; // count how many meeting happening concurrently
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            count += p.flag;

            // For all point marked on x (overlap on point x), check the flag status
            while (!queue.isEmpty() && p.pos == queue.peek().pos) {
                p = queue.poll();
                count += p.flag;
            }
            if (count > 1) return false;
        }
        
        return true;
    }

    // Prepare sweep line points
    private PriorityQueue<Point> buildQueue(int[][] intervals) {
        PriorityQueue<Point> queue = new PriorityQueue<Point>(Comparator.comparing(p -> p.pos));
        for (int i = 0; i < intervals.length; i++) {
            queue.offer(new Point(intervals[i][0], 1));
            queue.offer(new Point(intervals[i][1], -1));
        }
        return queue;
    } 
}
```