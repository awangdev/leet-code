E

(不知为何LeetCode把Merge Interval, Insert Interval 标为Hard)

Collections.sort(..., new comparator): sort by Interval.start.

画两个相连的Interval， prev, curr:
prev只有 prev.end覆盖了 curr.start， 才需要merge. 那么比较一下, marege.     
记得如果merge, 一定要list.remove(i), 并且i--， 因为改变了List的大小。

若没有重合，就继续iteration: prev = curr. move on.


```
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

*/

/*

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

```