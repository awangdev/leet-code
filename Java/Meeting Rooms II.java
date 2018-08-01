M
1521167295
tags: Heap, Greedy, Sort, Sweep Line, PriorityQueue

给一串数字pair, 代表会议的开始/结束时间. 找同时又多少个会议发生(需要多少件房间)

#### 方法1
- PriorityQueue + 一个Class来解决.Ｏ(nlogn)
- 跟 Number of Airpline in the sky是同一道题

#### 方法2: 尝试了一下用一个sorted Array + HashMap
也还行，但是handle edge的时候,HashMap 要小心，因为相同时间start和end的map key 就会重复了。

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
Counts the num of concurrent meetings.
Use sweep line to modle the inverval into time Points, where 1 means start of meeting and -1 means end of meeting.
Sort all points by time stamp, and accumulate +1, -1
Mark the max of count
*/
class Solution {
    class Point {
        int pos, flag;
        public Point(int pos, int flag) {
            this.pos = pos;
            this.flag = flag;
        }
    }
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        int count = 0, max = 0;
        // init
        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparing(a -> a.pos));
        for (Interval interval: intervals) {
            queue.offer(new Point(interval.start, 1));
            queue.offer(new Point(interval.end, -1));
        }
        
        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            count += curr.flag;
            while (!queue.isEmpty() && curr.pos == queue.peek().pos) {
                curr = queue.poll();
                count += curr.flag;
            }
            
            max = Math.max(count, max);
        }
        return max;
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