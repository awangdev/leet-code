M
tags: PriorityQueue, Sort, TreeMap, TreeSet
time: O(logn)
space: O(n)

#### Method1 :PriorityQueue
- Use priority queue to sort by customized class interval{int dist; int x, y;}. 
- Sort by larger distance and then sort by start index
- seat(): pq.poll() to find interval of largest distance. Split and add new intervals back to queue.
- leave(x): one seat will be in 2 intervals: remove both from pq, and merge to a new interval.
- 主方程写出来其实很好写, 就是 split + add interval, 然后 find + delete interval 而已. 最难的是构建data structure
- seat(): O(logn), leave(): O(n)
- `Trick: 构建虚拟 boundary`
    - 如果是开头的seat, 或者是结尾的seat, 比较难handle: 一开始坐在seat=0的时候, 没有interval啊!
    - Trick就是, 我们自己定义个虚拟的座位 `seat=-1`, `seat=N`
    - 一开始有一个 interval[-1, N] 然后就建立了boundary.
    - 从此以后, 每次split成小interval的时候:
    - 遇到 `interval[-1, y]`, distance就是 `(y - 0)`
    - 遇到 `interval[x, N]`, distance就是 `(N - 1 - x)`
    - 当然正常的interval dist 就是 `(y - x) / 2`
- distance 中间点
    - Interval.dist 我们其实做的是 distance的中间点 `(y - x) / 2`
    - 这里的dist是 `距离两边的距离` 而不是 x, y 之间的距离. 这里要特别注意.

#### Method2: TreeSet + TreeMap
- TreeSet<Interval>
- TreeMap<starting Pos, Interval>
- seat(): O(logn)
    - find largest dist with TreeSet.first()
    - break into 2 intervals; save to set and save to map
- leave(x): O(logn)
    - find the interval before starting point x using TreeMap.floorEntry()
    - merge and store back to set/map
- for test case it is slower than PQ, because it saves to 2 data structure

```
/*
In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  
If there are multiple such seats, they sit in the seat with the lowest number.  
(Also, if no one is in the room, then the student sits at seat number 0.)

Return a class ExamRoom(int N) that exposes two functions: 
ExamRoom.seat() returning an int representing what seat the student sat in, 
and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  
It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.

 

Example 1:

Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
Output: [null,0,9,4,2,null,5]
Explanation:
ExamRoom(10) -> null
seat() -> 0, no one is in the room, then the student sits at seat number 0.
seat() -> 9, the student sits at the last seat number 9.
seat() -> 4, the student sits at the last seat number 4.
seat() -> 2, the student sits at the last seat number 2.
leave(4) -> null
seat() -> 5, the student​​​​​​​ sits at the last seat number 5.
​​​​​​​

Note:

1 <= N <= 10^9
ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
 */

// PriorityQueue
class ExamRoom {
    PriorityQueue<Interval> pq;
    int N;

    class Interval {
        int x, y, dist;
        public Interval(int x, int y) {
            this.x = x;
            this.y = y;
            if (x == -1) {
                this.dist = y;
            } else if (y == N) {
                this.dist = N - 1 - x;
            } else {
                this.dist = Math.abs(x - y) / 2;    
            }
        }
    }

    public ExamRoom(int N) {
        this.pq = new PriorityQueue<>((a, b) -> a.dist != b.dist? b.dist - a.dist : a.x - b.x);
        this.N = N;
        pq.add(new Interval(-1, N));
    }

    // O(logn): poll top candidate, split into two new intervals
    public int seat() {
        int seat = 0;
        Interval interval = pq.poll();
        if (interval.x == -1) seat = 0;
        else if (interval.y == N) seat = N - 1;
        else seat = (interval.x + interval.y) / 2; 
        
        pq.offer(new Interval(interval.x, seat));
        pq.offer(new Interval(seat, interval.y));
            
        return seat;
    }
    
    // O(n)Find head and tail based on p. Delete and merge two ends
    public void leave(int p) {
        Interval head = null, tail = null;
        List<Interval> intervals = new ArrayList<>(pq);
        for (Interval interval : intervals) {
            if (interval.x == p) tail = interval;
            if (interval.y == p) head = interval;
            if (head != null && tail != null) break;
        }
        // Delete
        pq.remove(head);
        pq.remove(tail);
        // Merge
        pq.offer(new Interval(head.x, tail.y));
    }
}


// Method2: Ordered Map
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
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
```