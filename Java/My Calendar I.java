M
1533013643
tags: Array, TreeMap

Given a list of interval as calendar items. Check if newly added calendar item is overlapping.

Understand it is only checking time, but not requiring to insert into right spot. No need to overthink.

#### Simply O(n) check on array
- number of test cases is small, like 1000, so less concern about the time complexity
- simply loop over the list of intervals, and check if any overlapping.
- where to insert does not really matter: every time we are just checking for overlaopping, not merging any range
- **IMPORTANT**: if interval over lapping, they will have this property `Math.max(s1, s2) < Math.min(e1, e2)`. This will help detect the overlapping very easily.
- O(n^2) runtime, with simple code. But somehow this approach is faster than the TreeMap solution: maybe the test cause causes avg O(n)?

#### TreeMap
- One constraint from the simply array solution: it always cost O(n) to find the potential overlapping interval
- We can manually sort and always manually try to find the correct element via binary search, or we could store the interval in a treeMap<startKey, endValue>, where the intervals are sorted by `start`.
- As result, all we need to do for book(start, end) is to find the next element ceiling(start), last element floor(start), and check for overlapping
- This approach also saves the custom data structure
- Overall cost O(nlogn)

##### About TreeMap
- always with key sorted ascendingly 
- more costly than regular HashMap because of the sorting. Building treemap of n items: O(nlogn)

#### Sweep line
- use `Point{int start, end; boolean start}` to mark start/end of class. Add to pq.
- Adding new item to pq, sort, and check if overlapping occurs by counting started classes
- If started classes > 1, that means we overlapped.
- Every time it could consume all classes to find the overlap, O(n^2).
- Not quite need to sort or insert at correct point, and this solution requires longer code. Not quite worthy it for a simple problem.


```
/**
Implement a MyCalendar class to store your events. 
A new event can be added if adding the event will not cause a double booking.

Your class will have the method, book(int start, int end). 
Formally, this represents a booking on the half open interval [start, end), 
the range of real numbers x such that start <= x < end.

A double booking happens when two events have some non-empty intersection 
(ie., there is some time that is common to both events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully 
without causing a double booking. Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
Example 1:
MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false
MyCalendar.book(20, 30); // returns true
Explanation: 
The first event can be booked.  The second can't because time 15 is already booked by another event.
The third event can be booked, as the first event takes every time less than 20, but not including 20.
Note:

The number of calls to MyCalendar.book per test case will be at most 1000.
In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].

 */


// array, using Math.max(s, start) < Math.min(e, end)
class MyCalendar {
    class Interval {
        int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    List<Interval> intervals;
    public MyCalendar() {
        intervals = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        for (Interval interval : intervals) {
            int s = interval.start, e = interval.end;
            if (Math.max(s, start) < Math.min(e, end)) {
                return false;
            }
        }
        intervals.add(new Interval(start, end));
        return true;
    }
}

// tree map
class MyCalendar {
    TreeMap<Integer, Integer> map;
    public MyCalendar() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer floorKey = map.floorKey(start);
        if (floorKey != null && map.get(floorKey) > start) return false;
        Integer ceilingKey = map.ceilingKey(start);
        if (ceilingKey != null && ceilingKey < end) return false;
        
        map.put(start, end);
        return true;
    }
}

```