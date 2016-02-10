/*
Given a non-overlapping interval list which is sorted by start point.

Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).

Have you met this question in a real interview? Yes
Example
Insert [2, 5] into [[1,2], [5,9]], we get [[1,9]].

Insert [3, 4] into [[1,2], [5,9]], we get [[1,2], [3,4], [5,9]].

Tags Expand 
Basic Implementation

Thoughts:
1. Find right position to insert: find the last start position that's <= newInterval.start
2. After insertion, merge.
3. How to merge? Look at merge inerval question
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
class Solution {
    /**
     * Insert newInterval into intervals.
     * @param intervals: Sorted interval list.
     * @param newInterval: A new interval.
     * @return: A new sorted interval list.
     */
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.size() == 0 || newInterval == null) {
            if (newInterval != null) {
                intervals.add(newInterval);
            }
            return intervals;
        }
        //Insert
        int start = newInterval.start;
        int front = -1;
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).start <= start) {
                front = i;
            }
        }
        if (front == -1) {
            intervals.add(0, newInterval);
        }
        intervals.add(front + 1, newInterval);
     
        //Merge
        Interval pre = intervals.get(0);
        Interval curr = null;
        for (int i = 1; i < intervals.size(); i++) {
            curr = intervals.get(i);
            if (pre.end >= curr.start) {
                pre.end = pre.end > curr.end ? pre.end : curr.end;
                intervals.remove(i);
                i--;
            } else {
                pre = curr;
            }
        }
    
        return intervals;
    }
}