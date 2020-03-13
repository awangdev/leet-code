M
tags: Heap, Greedy, Sort, Sweep Line, PriorityQueue
time: O(nlogn)
space: O(n)

给一串数字pair, 代表会议的开始/结束时间. 找同时又多少个会议发生(需要多少件房间)

#### Method1: sort both start and end times
- Sort start times, and end times in 2 different arrays
- Loop over start time
    - when start[i] < end[endIndex], Count++, need more room
    - start[i] >= end[endIndex], done using some room, move to next end time, endIndex++ (like vacating a room)
- Note: we never decrese count because:
    - what ever count reaches, it is the max
    - since we keep moving endIndex, when start[i] >= end[endIndex], we will just reuse meeting room w/o count++
- time: O(nlogn)
- space: O(n)
- somehow, super fast, over 100%
- inspired by: https://leetcode.com/problems/meeting-rooms-ii/discuss/67855/Explanation-of-%22Super-Easy-Java-Solution-Beats-98.8%22-from-%40pinkfloyda

#### Method2: Sweep Line
- Use sweep line to process, track max count as max # of rooms needed
- 跟 Number of Airpline in the sky是同一道题
- time: O(nlogn)
- space: O(n)

#### Method3: 尝试了一下用一个sorted Array + HashMap
也还行，但是handle edge的时候,HashMap 要小心，因为相同时间start和end的map key 就会重复了。

```
/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/


class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null) return 0;

        int[] starts = extract(intervals, 0);
        int[] ends = extract(intervals, 1);
        
        int count = 0, endIndex = 0;
        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[endIndex]) count++;
            else endIndex++;
        }
        
        return count;
    }
    
    private int[] extract(int[][] intervals, int index) {
        int[] array = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            array[i] = intervals[i][index];
        }
        Arrays.sort(array);
        return array;
    }
}


/*
Thoughts:
Counts the num of concurrent meetings.
Use sweep line to modle the inverval into time Points, where 1 means start of meeting and -1 means end of meeting.
Sort all points by time stamp, and accumulate +1, -1
Mark the max of count
*/
// find # of meethings open at same time, using sweep line to count
class Solution {
    class Point {
        int pos, flag;
        public Point(int pos, int flag) {
            this.pos = pos;
            this.flag = flag;
        }
    }
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null) return 0;

        // init
        int count = 0, max = 0;
        PriorityQueue<Point> queue = buildQueue(intervals);

        // process
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