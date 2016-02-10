扫描线是个好厨师。
注意接头点要考虑所有开会结会的情况，不要恰巧漏掉相接的点。
这开会的dude是个超人。瞬间移动接上下一个会议。
```
/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.
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
Thought:
Use scane line.
Note: special care for edge point: make sure to process all connecting point before shuouting the result.
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
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
        	return true;
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
        while (!queue.isEmpty()) {
        	Point p = queue.poll();
        	count += p.flag;
        	while(!queue.isEmpty() && p.pos == queue.peek().pos) {
        		p = queue.poll();
        		count += p.flag;
        	}
        	if (count > 1) {
        		return false;
        	}
        }
        return true;
    }
}
```