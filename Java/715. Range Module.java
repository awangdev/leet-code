H
tags: TreeSet, Segment Tree
time: query O(logn), update O(n)
space: O(n)

#### TreeSet
- start with considering array structure but operation are all O(n)
    - what if we can easily find range, and update
- TreeSet:
    - build a class `Interval {int start, end;}`
    - build a customized `compareTo` that sorts the interval by start at default, but sort by end if a.start==b.start
    - Query: TreeSet allow us to find element in O(logn)
    - Add Range: finding the starting pointing takes O(logn), but update can be worst to update O(n)
    - Remove Range: finding the starting pointing takes O(logn), but update can be worst to update O(n)

```
/*
A Range Module is a module that tracks ranges of numbers. Your task is to design and implement the following interfaces in an efficient manner.

addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
queryRange(int left, int right) Returns true if and only if every real number in the interval [left, right) is currently being tracked.
removeRange(int left, int right) Stops tracking every real number currently being tracked in the interval [left, right).
Example 1:
addRange(10, 20): null
removeRange(14, 16): null
queryRange(10, 14): true (Every number in [10, 14) is being tracked)
queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite the remove operation)
Note:

A half open interval [left, right) denotes all real numbers left <= x < right.
0 < left < right < 10^9 in all calls to addRange, queryRange, removeRange.
The total number of calls to addRange in a single test case is at most 1000.
The total number of calls to queryRange in a single test case is at most 5000.
The total number of calls to removeRange in a single test case is at most 1000.
*/
class RangeModule {

    class Interval implements Comparable<Interval> {
        int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public int compareTo(Interval target) {
            if (this.end == target.end) return this.start - target.start; // if same end, sort by start
            return this.end - target.end; // otherwise, sort by start
        }
    }
    
    TreeSet<Interval> ranges;
    public RangeModule() {
          ranges = new TreeSet<>();
    }
    
    public void addRange(int start, int end) {
        Iterator<Interval> iterator = ranges.tailSet(new Interval(0, start - 1)).iterator(); // make sure it overlaps on `start-1`, since [start-1, start] can merge into 1 interval
        while (iterator.hasNext()) {
            Interval interval = iterator.next();
            if (end < interval.start) break; // out of range
            // remove the curr element & mark new range
            iterator.remove();
            start = Math.min(start, interval.start);
            end = Math.max(end, interval.end);
        }
        ranges.add(new Interval(start, end));
    }
    
    public boolean queryRange(int start, int end) {
        Interval interval = ranges.higher(new Interval(0, start)); // find range larger or equal to [0, start)
        return interval != null && interval.start <= start && end <= interval.end;        
    }
    
    public void removeRange(int start, int end) {
        Iterator<Interval> iterator = ranges.tailSet(new Interval(0, start)).iterator(); // find [start, ...] elements
        List<Interval> newRanges = new ArrayList<>();
        while (iterator.hasNext()) {
            Interval interval = iterator.next();
            if (end < interval.start) break; // out of range
            // remove the curr element & break down into new range
            iterator.remove();
            if (interval.start < start) newRanges.add(new Interval(interval.start, start));
            if (end < interval.end) newRanges.add(new Interval(end, interval.end));
        }
        for (Interval interval : newRanges) ranges.add(interval);
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
```