扫描线+Count无敌手。注意start end把interval给合起来。
count==0的时候，就是每次start end双数抵消的时候，就应该是一个interval的开头/结尾。写个例子就知道了。

空间：O(2n) -> O(n)
时间,priorityqueue: O(nlogn)

记得怎么写comparator



或者O(n)
Collections.sort() on interval.start之后，试着跑一遍，按照merge的需求，把需要merge的地方续好，然后减掉多余的interval就好。
Basic implementation
/*
    new Comparator<Object>(){
        public int compare(obj1, obj2) {
            return obj1.x - obj2.x;
        }

    }
*/

```
/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

Tags: Array, Sort
Similar Problems: (H) Insert Interval, (E) Meeting Rooms (M) Meeting Rooms II

*/

/*
    Thoughts: 12.09.2015
    Recap. Use O(1)

    Sort by start time.
    then it overlaps: check on pre.end and curr.start.
    if overlaps: curr.start will be overlapped; also check on curr.end and pre.end, decide who ends this interval
    
    border case: null, return itself; or length==1, return.
*/

class Solution {
    /**
     * @param intervals: Sorted interval list.
     * @return: A new sorted interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        Interval prev = intervals.get(0);
        Interval curr;

        for (int i = 1; i < intervals.size(); i++) {
            curr  = intervals.get(i);
            if (prev.end >= curr.start) {
                if (prev.end <= curr.end) {
                    prev.end = curr.end;
                }
                intervals.remove(i);
                i--;
            } else {
                prev = curr;         
            }
        }

        return intervals;
    }

}

/*
Thoughts:
Again use scan line. Quite similar to Meeting Rooms, flight schedules... etc
This one: when count ==0, make sure to keep track start and end, add into the rst
When writing out example, whenever count==0, that indicates an end of a interval.

HOWEVER, this uses O(n) space, while this problem requests O(1) space
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
		int pos;
		int flag;
		public Point(int pos, int flag) {
			this.pos = pos;
			this.flag = flag;
		}
	}

    public List<Interval> merge(List<Interval> intervals) {
    	List<Interval> rst = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
        	return rst;
        }
        PriorityQueue<Point> queue = new PriorityQueue<Point>(
        	new Comparator<Point>(){
        		public int compare(Point a, Point b){
        			return (a.pos - b.pos);
        		}
        	}
        );
        for (Interval range : intervals) {
        	queue.add(new Point(range.start, 1));
        	queue.add(new Point(range.end, -1));
        }
        int count = 0;
        int start = 0;
        int end = 0;
        while (!queue.isEmpty()) {
        	Point p = queue.poll();
        	if (count == 0) {
        		start = p.pos;
        	}
        	count += p.flag;
        	while(!queue.isEmpty() && p.pos == queue.peek().pos) {
        		p = queue.poll();
        		count += p.flag;
        	}
        	if (count == 0) {
        		end = p.pos;
        		rst.add(new Interval(start, end));
        	}
        }
        return rst;
    }
}








```