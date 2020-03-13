 
 
 
## Sweep Line (7)
**0. [Number of Airplane in the sky.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Airplane%20in%20the%20sky.java)**      Level: Medium      Tags: [Array, Interval, PriorityQueue, Sort, Sweep Line]
      
#### Sweep Line
- 把Interval拆分成数轴上的Point 
- 起飞mark 1   
- 降落mark -1     
- 用PriorityQueue排序， loop through queue, 计算(起飞+降落)值可能有的max。

#### 注意
- 同时起飞和降落，就是 1 - 1 = 0. 所以在while loop里面有第二个while loop，    
- 当坐标x重合时，在这里做完所有x点的加减，然后再比较 max。     
- 这避免了错误多count，或者少count



---

**1. [56. Merge Intervals.java](https://github.com/awangdev/LintCode/blob/master/Java/56.%20Merge%20Intervals.java)**      Level: Medium      Tags: [Array, PriorityQueue, Sort, Sweep Line]
      


给一串int[Interval] (unsorted), 把所以Interval merge起来.

#### Method1: Sweep Line with Priority Queue
- O(nlogn) time (PriorityQueue), O(n) space     
    - 1. 扫描线+Count: when `count==0`, startFlags==endFlags. 是interval的开头/结尾 (write an example)
    - 2. Note: remember to merge points on same sweep line position
- Comparator: `new PriorityQueue<>(Comparator.comparing(p -> p.val))`;

#### Method2: Sort Intervals and append end logically
- Sort intervals: O(nlogn), extra space O(n) when creating rst list
    - `Arrays.sort(intervals, Comparator.comparing(i -> i[0]));`
    - 找到结尾 interval, 满足条件就可以save
    - 如果不到return的条件, 就继续延伸 interval.end

#### Method3: Sort Interval, Remove overlaop interval & modify interval
- Less applicable when input is `int[][] intervals`, but more applicable when we have `List<int[]> intervals`
- Related example: Insert Interval
- Sort fist, loop over and merge, cut off overlapped interval. 
    - sort by Interval.start: `intervals.sort(Comparator.comparing(interval -> interval.start)); // O(nlogn)`
    - 用两个相连的Interval: curr, next
    - 如果 curr.end覆盖了 next.start: 需要merge. 那么比较一下 curr.end vs. next.end    
    - 一旦merge, 需要remove被覆盖的 next interval: `list.remove(i+1)`
    - 若没有重合，就继续iteration
- time O(nlogn), space O(1)



---

**2. [252. Meeting Rooms.java](https://github.com/awangdev/LintCode/blob/master/Java/252.%20Meeting%20Rooms.java)**      Level: Easy      Tags: [PriorityQueue, Sort, Sweep Line]
      

- 注意接头点要考虑所有开会结会的情况，不要恰巧漏掉相接的点
- 开会的是超人。瞬间移动接上下一个会议

#### Method1: sort input and compare if curr.end & next.start overlaps
- sort: `Arrays.sort(intervals, Comparator.comparing(i -> i[0]))`
- time: O(nlogn), space: O(1)

#### Method2: Sweep line
- class Point{pos, flag}, PriorityQueue排序。计算count
- 跟 Number of Airplanes in the Sky 是一个类型的题目
- time: O(nlogn), space O(n)
- Not necessary for this problem, since it requires extra space with pq.



---

**3. [850. Rectangle Area II.java](https://github.com/awangdev/LintCode/blob/master/Java/850.%20Rectangle%20Area%20II.java)**      Level: Hard      Tags: [Segment Tree, Sweep Line]
      

#### Sweep Line + Merge Interval concept
- Inspired by: https://leetcode.com/problems/rectangle-area-ii/discuss/137941/Java-TreeMap-solution-inspired-by-Skyline-and-Meeting-Room
- First consider regular sweep line and realize problem: each vertical line has multiple block segments
    - Easy: take a list of vertical dots, and calculate the height diff
    - We can use a TreeMap with y-coordinate as key, so to `natural sort by y-coordinate`
- Trick: can NOT remove used y coordinate from map, because the rectangle may continue to expand to right side.
- apply simple equation to calc area: `(long)preY * (p.x - preX)`
- time: 
    - sort initial queue: O(nlogn)
    - process queue: O(n)
        - TreeMap insertion: O(logn)
        - TreeMap traversal: O(n)
    - overall, process queue can be O(n^2)
- space: O(n)

#### Sweep Line concept, bottom->top sweep
- https://leetcode.com/problems/rectangle-area-ii/discuss/137914/C%2B%2BPython-Discretization-and-O(NlogN)

#### Segment Tree
- TODO lol



---

**4. [253. Meeting Rooms II.java](https://github.com/awangdev/LintCode/blob/master/Java/253.%20Meeting%20Rooms%20II.java)**      Level: Medium      Tags: [Greedy, Heap, PriorityQueue, Sort, Sweep Line]
      

给一串数字pair, 代表会议的开始/结束时间. 找同时又多少个会议发生(需要多少件房间)

#### Method1: sort both start and end times
- Sort start times, and end times in 2 different arrays
- Loop over start time
    - when start[i] < end[endIndex], Count++, need more room
    - start[i] >= end[endIndex], done using some room, move to next end time, endIndex++ (like vacating a room)
- Note: we never decrese count because:
    - what ever count reaches, it is the max
    - since we keep moving endIndex, when start[i] >= end[endIndex], we will just reuse meeting room w/o count++
- time: O(nlogn)
- space: O(n)
- somehow, super fast, over 100%
- inspired by: https://leetcode.com/problems/meeting-rooms-ii/discuss/67855/Explanation-of-%22Super-Easy-Java-Solution-Beats-98.8%22-from-%40pinkfloyda

#### Method2: Sweep Line
- Use sweep line to process, track max count as max # of rooms needed
- 跟 Number of Airpline in the sky是同一道题
- time: O(nlogn)
- space: O(n)

#### Method3: 尝试了一下用一个sorted Array + HashMap
也还行，但是handle edge的时候,HashMap 要小心，因为相同时间start和end的map key 就会重复了。



---

**5. [218. The Skyline Problem.java](https://github.com/awangdev/LintCode/blob/master/Java/218.%20The%20Skyline%20Problem.java)**      Level: Hard      Tags: [BIT, Divide and Conquer, HashHeap, Heap, PriorityQueue, Segment Tree, Sweep Line]
      

#### Sweep Line, Time O(nLogN), Space O(n)
- Analysis (inspired by, but not same solution: https://leetcode.com/problems/the-skyline-problem/solution/)
    - If there are just 2 overlapping building (totally 4 points on x-axis), here is the outline process:
    - Process x coordinate from left->right, one at a time.
        - 1) compare all `on-going heights` and find max, add as new outline point
        - 2) Handling building end: if the position ends a building, need to remove this height from the list of `on-going heights`
    - Requires 2 heap:
        1) sort by x coordinates
        2) `on-going heights`: maintain a pq of ongoing heights
- Steps: 
    - original reference http://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html?_sm_au_=isVmHvFmFs40TWRt
    - 画图分析: 需要找到 non-overlaping height point at current index; also height needs to be different than prev height peek to be visible.
    - `on-going heights`: 用max-heap (reversed priorityqueue)，再iterate heightPoints 来存最大的height
    - NOTE: heightQueue里面加一个0, 用来在结尾的时候做closure
- time: initial sort O(nlogn) + calculate n * O(nlogn) [maxQueue sort]
- space: O(n)

#### Segment Tree
- 看了一些做法, segment tree写法很复杂, 估计在面试中难以用segment tree来写: https://www.cnblogs.com/tiezhibieek/p/5021202.html

#### HashHeap
- HashHeap template 可以考虑: https://www.jiuzhang.com/solution/building-outline/#tag-highlight-lang-java

Binary Indexed Tree?





---

**6. [57. Insert Interval.java](https://github.com/awangdev/LintCode/blob/master/Java/57.%20Insert%20Interval.java)**      Level: Hard      Tags: [Array, PriorityQueue, Sort, Sweep Line]
      

#### Method1: Convert to list, insert, and merge list
- 这里已经给了 **sorted** intervals by start point;
    - 1) 直接找到可以insert newInterval的位子. Insert and convert to list
    - 2) Merge: Use `pre, curr` to iterate over list, and remove curr after merging
        - remove之前都会重新assgin `pre.end`, 确保被remove的node.end 被capture
    - 3) Convert back to int[][]
- time/space: O(n) 
- code is slightly better to read

#### Method2: Insert on the fly, and handle edge cases
- handle edge cases:
    - new interval is non-overlapping
        - 1) head
        - 2) tail
        - 3) in middle
    - new interval is overlapping:
        - 1) end index in existing interval; reuse the existing interval end to close new range
        - 2) end index in the gap of 2 intervals, use new interval.end to close the new range
- time, space: O(n)

#### Method3: Sweep Line
- Interval 拆点，PriorityQueue排点
- Merge时用count==0作判断点
- 注意, 一定要compare curr `p.x == queue.peek().x` 确保重合的点全部被process: `count+=p.x`
- PriorityQueue: O(logN). 扫n点, 总共：O(nLogn). SLOW.


#### 另外
- 因为interval已经sort, 本想用Binary Search O(logn). 
- 但是找到interval insert position 最后 merge还是要用 O(n), 所以不必要 binary Search



---

