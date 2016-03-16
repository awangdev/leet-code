M


方法1:PriorityQueue + 一个Class来解决。Ｏ(nlogn)

方法2:这里有尝试了一下用一个sorted Array + HashMap： 也还行，但是handle edge的时候,HashMap 要小心，因为相同时间start和end的map key 就会重复了。

```
/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

For example
Given [[0, 30],[5, 10],[15, 20]],
return 2.

Tags: Heap Greedy Sort
Similar Problems: (H) Merge Intervals, (E) Meeting Rooms
*/




// Similar to Meeting Room I, using Point class and Priorityqueue
// Creating a customized class, but makes the problem a bit easier to think.
public class Solution {
    class Point {
        int pos, flag;
        public Point(int pos, int flag) {
            this.pos = pos;
            this.flag = flag;
        }
    }
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        PriorityQueue<Point> queue = new PriorityQueue<Point>(2, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p1.pos - p2.pos;
            }
        });
        
        for (int i = 0; i < intervals.length; i++) {
            queue.offer(new Point(intervals[i].start, 1));
            queue.offer(new Point(intervals[i].end, -1));
        }
        int count = 0;
        int rst = 0;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            count += p.flag;
            while (!queue.isEmpty() && p.pos == queue.peek().pos) {
                p = queue.poll();
                count += p.flag;
            }
            rst = Math.max(count, rst);
        }
        
        return rst;
    }
}

/*
Thoughts: This seems to be: how many concurrent meetings do we have?
Just return the count that we used in Meeting I.

Though, instead of Prority queue + Point class, let me try to use just array and a hashmap.

Using HashMap is tricky in this:
Overallpping spot will be put on the same hashmap key. Be careful with handling the overlapping.
Here, I decide to merge the edge when generate the map, so during the count check, need to skip
duplicated time spot to prevent incorrectly re-use of certain time spot.


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
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int[] timeSpot = new int[intervals.length * 2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < intervals.length; i++) {
            timeSpot[i] = intervals[i].start;
            timeSpot[intervals.length + i] = intervals[i].end;
            if (map.containsKey(intervals[i].start)) {
                map.put(intervals[i].start, map.get(intervals[i].start) + 1);
            } else {
                map.put(intervals[i].start, 1);
            }
            if (map.containsKey(intervals[i].end)) {
                map.put(intervals[i].end, map.get(intervals[i].end) - 1);
            } else {
                map.put(intervals[i].end, -1);
            }
        }
        Arrays.sort(timeSpot);
        int count = 0;
        int max = 0;
        for (int i = 0; i < timeSpot.length; i++) {
            count += map.get(timeSpot[i]);
            while (i + 1 < timeSpot.length && timeSpot[i] == timeSpot[i + 1]) {
                i++;
            }
            max = Math.max(count, max);
        }
        return max;
    }
}

```