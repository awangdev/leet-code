/*
http://www.lintcode.com/en/problem/number-of-airplanes-in-the-sky/
Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?

Example
For interval list [[1,10],[2,3],[5,8],[4,7]], return 3

Note
If landing and flying happens at the same time, we consider landing should happen at first.

Tags Expand 
LintCode Copyright Array Interval
*/


/*
Thoughts: same as the number of meeting.
Use a Point class {time, flag} and mark all time spot, and use a min-heap(PriorityQueue) to sort it.

Note: LintCode forces me to put '10' in PriorityQueue constructor?
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
	class Point {
		int x;
		int flag;
		public Point(int x, int flag) {
			this.x = x;
			this.flag = flag;
		}
	}
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) { 
    	if (airplanes == null || airplanes.size() == 0) {
    		return 0;
    	}
    	PriorityQueue<Point> queue = new PriorityQueue<Point>(10,
    	    new Comparator<Point>(){
    	    public int compare(Point a, Point b) {
    	        return a.x - b.x;
    	    }
    	});
    	for (Interval interval : airplanes) {
    		queue.offer(new Point(interval.start, 1));
    		queue.offer(new Point(interval.end, -1));
    	}
    	int count = 0;
    	int max = 0;
    	while (!queue.isEmpty()) {
    		Point p = queue.poll();
    		count+= p.flag;
    		while (!queue.isEmpty() && queue.peek().x == p.x) {
    			p = queue.poll();
    			count += p.flag;
    		}
    		max = Math.max(count, max);
    	}
    	return max;
    }
}
