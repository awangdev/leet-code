/*

Given a collection of intervals, merge all overlapping intervals.

Example
Given intervals => merged intervals:

[                     [
  [1, 3],               [1, 6],
  [2, 6],      =>       [8, 10],
  [8, 10],              [15, 18]
  [15, 18]            ]
]
Challenge
O(n log n) time and O(1) extra space.

Tags Expand 
Sort Array

Thoughts:
1. use comparator to sort list. Well, no need to create a new class. Just do it inline.
2. iterate through the list and merge (whenever there is overlap)


Review:
List: size(), get(..), remove(..)
Comparator
*/


/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
    /**
     * @param intervals: Sorted interval list.
     * @return: A new sorted interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
        	return intervals;
        }

      //  intervals.sort(new CustomComparator());
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
        		return a.start - b.start;
        	}
        });
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
