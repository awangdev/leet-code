扫描线问题。
Interval 拆点，PriorityQueue排点。
Merge时用count==0作判断点。

PriorityQueue: O(logN). 扫n点，总共：O(nLogn)

```
/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

Tags:Array, Sort
Similar Problems: (H) Merge Intervals


*/

/*
Thoughts:
What's the difference from merge intervals?
1. Create Class point (x, flag)
2. sort point in min-heap
3. when count increase and decreases to 0, that means we can close an interval
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

	class Point {
		int x;
		int flag;
		public Point(int x, int flag) {
			this.x = x;
			this.flag = flag;
		}
	}
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> rst = new ArrayList<Interval>();
        if (intervals == null && newInterval == null) {
        	return rst;
        } else if (intervals == null) {
        	rst.add(newInterval);
        	return rst;
        } else if (newInterval == null) {
        	return intervals;
        }

        PriorityQueue<Point> queue = new PriorityQueue<Point>(1, new Comparator<Point>(){
        	public int compare(Point a, Point b){
        		return a.x - b.x;
        	}
        });

        for (Interval range: intervals) {
        	queue.add(new Point(range.start, 1));
        	queue.add(new Point(range.end, -1));
        }

        queue.add(new Point(newInterval.start, 1));
        queue.add(new Point(newInterval.end, -1));

        int count = 0;
        int startNew = 0;
        int endNew = 0;
        while (!queue.isEmpty()) {
        	Point p = queue.poll();
        	if (count == 0) {
        		startNew = p.x;
        	}
        	count += p.flag;

        	while (!queue.isEmpty() && p.x == queue.peek().x) {
        		p = queue.poll();
        		count += p.flag;
        	}

        	if (count == 0) {
        		endNew = p.x;
        		Interval addInterval = new Interval(startNew, endNew);
        		rst.add(addInterval);
        	}

        }//end while

        return rst;

    }
}

















```