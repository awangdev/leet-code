E

O(n) 直接找到可以insert newInterval的位子. Insert。

然后loop to merge entire interval array

另外: 因为interval已经sort, 本想用Binary Search O(logn). 但是找到interval insert position， merge还是要用 O(n)。      
比如刚好newInterval cover entire  list....
```

/*
Given a non-overlapping interval list which is sorted by start point.

Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).

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
O(n)
Thoughts:
1. Find right position to insert: find the last start position that's <= newInterval.start
2. After insertion, merge.
3. How to merge? Look at merge inerval question


*/
class Solution {
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