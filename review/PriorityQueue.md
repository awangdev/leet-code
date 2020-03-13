 
 
 
## PriorityQueue (23)
**0. [Design Search Autocomplete System.java](https://github.com/awangdev/LintCode/blob/master/Java/Design%20Search%20Autocomplete%20System.java)**      Level: Hard      Tags: [Design, Hash Table, MinHeap, PriorityQueue, Trie]
      

Description is long, but in short: 做 search auto complete. 

Best problem to review Trie (prefix search), Top K frequent elements (Hash Map), and MinHeap (PriorityQueue)

Easier to revisit https://leetcode.com/problems/design-search-autocomplete-system/description/

#### 思考方向
- 做text的search, 毋庸置疑要用Prefix tree, trie.

##### Find all possible word/leaf, 两种方案:
- Trie造好之后, 做prefix search, 然后DFS/BFS return all leaf items. [high runtime complexity]
- 在TrieNode里面存所有的possible words. [high space usage]
- in memory space 应该不是大问题, 所以我们可以选择 store all possible words

##### Given k words, find top k frequent items. 肯定用MinHeap, 但也有两种方案:
- Store MinHeap with TrieNode: 因为会不断搜索新此条, 同样的prefix (尤其是在higher level), 会被多次搜索.
- [complexity: need to update heaps across all visited TrieNodes once new sentence is completed]
- Compute MinHeap on the fly: 当然我们不能每次都来一个DFS不然会很慢, 所以就必须要store list of possible candidates in TrieNode.
- 这里就用到了`Top K Frequent Words` 里面的 `Map<String, freq>`, 这样O(m) 构建 min-heap其实很方便.

##### Train the system
- 每次 `#` 后 标记一个词条被add进search history. 那么就要 `insert it into trie`.
- 这一条在最后遇到`#`再做就可以了, 非常简洁

#### Trie, PriorityQueue, HashMap
- Trie Prefix Search + maintain top k frequent items
- 



---

**1. [Number of Airplane in the sky.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Airplane%20in%20the%20sky.java)**      Level: Medium      Tags: [Array, Interval, PriorityQueue, Sort, Sweep Line]
      
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

**2. [Ugly Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Ugly%20Number%20II.java)**      Level: Medium      Tags: [DP, Enumeration, Heap, Math, PriorityQueue]
      

#### DP
- curr index is based on previous calculation: the min of all 3 previous factors
- O(n)

#### PriorityQueue, DP
- 非常brutle的。
- 每次把dp[i-1]拿出来，不管三七二十一，分别乘以2,3,5. 出来的结果放进priority queue做比较。
- 最后时间是n*log(n*3)
- 注意：use long, use HashSet确保没有重复
- O(nlogn)




---

**3. [The Maze II.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Maze%20II.java)**      Level: Medium      Tags: [BFS, DFS, PriorityQueue]
      
#### BFS
- if already found a good/shorter route, skip
- `if (distMap[node.x][node.y] <= node.dist) continue;`
- This always terminates the possibility to go return to original route, because the dist will be double/higher



---

**4. [Trapping Rain Water II.java](https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water%20II.java)**      Level: Hard      Tags: [BFS, Heap, MinHeap, PriorityQueue]
      
给一个2Dmap, 每个position 有 height. 找Trapping water sum.


#### Min Heap
- 用PriorityQueue把选中的height排序,为走位, create class Cell (x,y, height).

##### 注意几个理论
- 1. 从matrix四周开始考虑，发现matrix能Hold住的水，取决于height低的block
- 2. 必须从外围开始考虑，因为水是被包裹在里面，外面至少需要现有一层
- 以上两点就促使我们用min-heap: 也就是natural order的PriorityQueue<Cell>.

##### Steps
- 1. process的时候，画个图也可以搞清楚: 就是四个方向都走走，用curr cell的高度减去周围cell的高度.
- 2. 若大于零，那么周围的cell就有积水: 因为cell已经是外围最低, 所以内部更低的, 一定有积水.
- 3. 每个visited的cell都要mark, avoid revisit
- 4. 根据4个方向的走位 `(mX, mY)` 创建新cell 加进queue里面: cell(mX, mY) 已经计算过积水后, 外围墙小时, `(mX, mY)`就会变成墙.
- 5. 因为做的是缩小一圈的新围墙, height = Math.max(cell.h, neighbor.h);
- 和trapping water I 想法一样。刚刚从外围，只是能加到跟外围cell高度一致的水平面。往里面，很可能cell高度变化。   
- 这里要附上curr cell 和 move-to cell的最大高度。

##### 为什么想到用Heap (min-heap - priorityQueue)
- 要找到bucket的最短板
- 每次需要最先处理最短的那条 (on top)

##### 为什么从外向里遍历
- 木桶理论, 包水, 是从外面包住里面
- 洋葱剥皮, 用完丢掉



---

**5. [Subarray Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20Closest.java)**      Level: Medium      Tags: [PreSum, PriorityQueue, Sort, Subarray]
      

给一串数字, 找subarray的首尾index, 条件: subarray最接近0.

#### PreSum + index in class
- Can be a 2D array, or a `class Point` to store preSum + index
- Sort preSum: smaller (有可能负数) 靠前, 大数字靠后
- 比较preSum种相连接的两个节点, 找差值min; 因为最接近的两个preSum节点的差值肯定是最小
- min所在的两个节点的index, 就是result candidate: 这两个index可能再原nums里面相差很远
- time O(nlogn), sort
- space: O(n)

#### 为何没有用 map<preSum, index> ?
- 因为map虽然能存 preSum + index, 但是无法有效排序
- 所以用一个class来存这两个信息, 然后合理排序



---

**6. [Kth Largest Element in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element%20in%20an%20Array.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, MinHeap, PriorityQueue, Quick Sort]
      
kth largest in array

#### PriorityQueue, MinHeap
- Need to maintain k large elements, where the smallest will be compared and dropped if applicable: 
- Maintain k elements with min value: consider using minHeap
- add k base elements first
- Maintain MinHeap: only allow larger elements (which will squzze out the min value)
- Remove peek() of queue if over size
- O(nlogk)


#### Quick Sort
- 用Quick Sort 里面partion的一部分
- sort结束后是ascending的, 那么 n - k 就是第k大. 
- partion的结果是那个low, 去找 low==nums.size() - k， 也就是倒数第K个。    
- 没找到继续partion recursively.
- sort的过程是排一个从小到大的list. (同样的代码还可以好xth smallest，mid变成x就好)
- Steps:
- 每个iteration, 找一个pivot,然后从low,和high都和pivot作比较。    
- 找到一个low>pivot, high<pivot, 也就可以swap了。    
- 得到的low就是当下的partion point了
- Overall O(nlogN), average O(n) for this problem.



---

**7. [The Maze III.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Maze%20III.java)**      Level: Hard      Tags: [BFS, DFS, PriorityQueue]
      
#### BFS
- 跟 Maze I, II 类似, 用一个 Node[][] 来存每一个(x,y)的state.
- Different from traditional BFS(shortest path): `it terminates BFS when good solution exists (distance), but will finish all possible routes`
- 1. `Termination condition`: if we already have a good/better solution on nodeMap[x][y], no need to add a new one
- 2. Always cache the node if passed the test in step1
- 3. Always offer the moved position as a new node to queue (as long as it fits condition)
- 4. Finally the item at nodeMap[target.x][target.y] will have the best solution.



---

**8. [[lint]. Merge k Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Merge%20k%20Sorted%20Arrays.java)**      Level: Medium      Tags: [Heap, MinHeap, PriorityQueue]
      

Same as merge k sorted list, use priorityQueue

#### Priority Queue
- 由Merge k sorted list启发。用PriorityQueue,存那k个首发element
- PriorityQueue需要存储单位: 自己建一个Class Node 存val, x, y index.    
- 因为array里没有 'next' pointer，只能存x,y来推next element
- Not sure why `new PriorityQueue<>(Comparator.comparing(a -> a.val));` is slower



---

**9. [347. Top K Frequent Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/347.%20Top%20K%20Frequent%20Elements.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue]
      

给一串数字, 找到top k frequent element, 并且time complexity 要比nLogN要好

#### Method1: Bucket Sort. HashMap + bucket List[]
- Use HashMap to store <num, freq>
- Bucket `List<Integer>[]`: stores <count, list unique element with that count>
    - Size of the data structure will be uniqe item size.
    - The bucket[i] stores item at frequency i
- Simply loop from bucket.length -> 0, when bucket[i] not null, add to result.
- Solid O(n)


#### Method2: PriorityQueue, MinHeap
- Use regualr priorityQueue to sort by frequency ascendingly
- the queue.peek() record has lowest frequency, which is replacable
- Always only maintain k elements in the queue, so sorting is O(logk)
- IMPORTANT: remember to `rst.add(0, x)` for desired ordering
- time faster than maxHeap: O(nlogk)
- option1: just use `map<num, freq>`; option2: use `class Record {int num; int freq}`

#### MaxHeap Attempt. INCORRECT
- 题目有提醒: 必须beetter than O(nLog(n)).
- max heap approach stores all nodes: it is wrong
    - even though freq count size m < n, but it can be m == n. ALL unique. 
    - then it is O(nlogN) again.
- therefore, storing all items into pq is INCORRECT.



---

**10. [56. Merge Intervals.java](https://github.com/awangdev/LintCode/blob/master/Java/56.%20Merge%20Intervals.java)**      Level: Medium      Tags: [Array, PriorityQueue, Sort, Sweep Line]
      


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

**11. [252. Meeting Rooms.java](https://github.com/awangdev/LintCode/blob/master/Java/252.%20Meeting%20Rooms.java)**      Level: Easy      Tags: [PriorityQueue, Sort, Sweep Line]
      

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

**12. [855. Exam Room.java](https://github.com/awangdev/LintCode/blob/master/Java/855.%20Exam%20Room.java)**      Level: Medium      Tags: [PriorityQueue, Sort, TreeMap, TreeSet]
      

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



---

**13. [253. Meeting Rooms II.java](https://github.com/awangdev/LintCode/blob/master/Java/253.%20Meeting%20Rooms%20II.java)**      Level: Medium      Tags: [Greedy, Heap, PriorityQueue, Sort, Sweep Line]
      

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

**14. [1094. Car Pooling.java](https://github.com/awangdev/LintCode/blob/master/Java/1094.%20Car%20Pooling.java)**      Level: Medium      Tags: [Greedy, Heap, PriorityQueue, Sort]
      

#### Method1: bucket sort
- define the bucket by index: the total distance is fixed [0, 1000]
- +/- capacities for each pos and save into the bucket
- go over the bucket and see if the total cap goes over input capacity
- O(n), trips size
- space: O(1), bucket size 1000 is constant
- `IMPORTANT`: before using PQ to sort, consider bucket sort:
    - if the boundary set and seems resonable? i.e., max size = `1000`
    - is the sorted items index based?

#### Method2: Priority Queue, sort distnace
- Like meeting room, merge interval
- process items on same index



---

**15. [621. Task Scheduler.java](https://github.com/awangdev/LintCode/blob/master/Java/621.%20Task%20Scheduler.java)**      Level: Medium      Tags: [Array, Enumeration, Greedy, PriorityQueue, Queue]
      

#### PriorityQueue; Greedy
- 正面去做: 
    - count task出现的次数
    - 然后PQ sort Task object in descending order
- 每个section: k slots = n + 1. Same task being n slots apart, meaning one section has n + 1 slots.
    - 目标是穷尽 k, or 穷尽 pq (poll k times, but will save it back to queue if Task # > 0)
    - 如果qp 真的穷尽, break, return count
    - 不然, count += k, where k are just # of idle intervals
- time O(n) + constant time O(xlogx), where x = 26
- extra space O(x) ~ O(1)


#### Array, count frequency, enumerate
- Enumerate to understand: 
    - 1.module tasks in module/section; 
    - 2.Only need sum the intervals/slots, not return actual layout
    - Perfect case: all letters appear identical # times: just line them up separate in order.
    - Real case: task appears different times
- 1. Place maxCount task as header followed with n slots: define (maxCount-1) sections
- 2. For tasks with less # than maxCount# can fill the (maxCount-1) sections; what about the tail section?
- 3. Any task with same maxTask#, of if prior sections all filled, will fill the tail section
- To count overall slots/intervals, come up with this equation:
    - 1. Fixed sections: `(maxCount - 1) * (n + 1)`
    - 2. Plus all repeating maxCount tasks: calculate by couting identical maxCount of them
    - 3. Exception: if the first (max - 1) sections are all filled completely, and we still have extra task (ex: when n is not large enough), then just return tasks.length
- time O(n), space O(1)
- ??? Need to study



---

**16. [414. Third Maximum Number.java](https://github.com/awangdev/LintCode/blob/master/Java/414.%20Third%20Maximum%20Number.java)**      Level: Easy      Tags: [Array, PriorityQueue]
      
#### pq
- 注意special case: `when less than 3 items, return maximum`
- PQ是natural order: remain peek() will be the 3rd maximum




---

**17. [1057. Campus Bikes.java](https://github.com/awangdev/LintCode/blob/master/Java/1057.%20Campus%20Bikes.java)**      Level: Medium      Tags: [Bucket Sort, Greedy, PriorityQueue, Sort]
      

#### Method1: PriorityQueue
- PQ can be used to sort on multiple attributes
- follow the specified rules, and build all possible pairs of visits vs. bike. Pair {int dist, workerIndex, bikeIndex}
- PQ to sort them
    - first by dist
    - if same dist, sort by workerIndex
    - if same workderIndex, sort by bikeIndex
- process all candidates, and skip the ones (workers/bikes) visited 

#### Method2: Bucket Sort
- Similar to using PQ: the goal is to find: 1) min dist; 2) closer worker index, 3) closer bike index
- can use bucket sort to hold all possible distances [0 ~ 2000]: bucket[List of pairs]
    - do a hard iteration (ordered access from min dist). 
- time: O(mn), no need to sort
- space: O(mn)



---

**18. [23. Merge k Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/23.%20Merge%20k%20Sorted%20Lists.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, Linked List, Merge Sort, PriorityQueue]
      

给一个array of ListNode, 把所有node按照大小连成一条.

#### Method1: Divide and Conquer, Merge sort
- By Definition, merge sort: divide the list into 2 parts
- recursively merge them together.
- time complexity: O(nlogk) divide by log(k) times, each recursive call can work on n nodes.
- space: O(logk) stacks 

#### Method2: Priorityqueue
- Iterative, PQ来排列所有list的leading node.
- Note: k lists need to be sorted (luckily, already given)
- 时间：n*O(logk), where n = total node number, and PriorityQueue: logk, 
- Note:
    - 1. 不要忘记customized priority需要一个customized new Comparator<T>()
    - 2. Given list 里面也可能有null node, 不要忘记查.

#### Followup
- 如果k很大，一个机器上放不下所有的k list怎么办？ 
- 如果Merge起来的很长，一个机器上放不下怎么办？




---

**19. [218. The Skyline Problem.java](https://github.com/awangdev/LintCode/blob/master/Java/218.%20The%20Skyline%20Problem.java)**      Level: Hard      Tags: [BIT, Divide and Conquer, HashHeap, Heap, PriorityQueue, Segment Tree, Sweep Line]
      

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

**20. [692. Top K Frequent Words.java](https://github.com/awangdev/LintCode/blob/master/Java/692.%20Top%20K%20Frequent%20Words.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue, Trie]
      

给一串String. 找到top k frequent words.

#### Method1: Bucket Sort
- 1) Build frequency map, 2) use frequency map to build freq bucket
- Loop from largest bucket freq -> 0, and output.
- Time: Solid O(n)
- Space: O(n)

#### Method2: PriorityQueue - Min Heap
- O(n) space of map, O(nlogk) to build queue.
- limit minHeap queue size to k: add to queue if found suitable item; always reduce queue if size > k

#### Method3: PriorityQueue - Max Heap
- 用HashMap存frequency, 用ArrayList存lists of words
- create一个Node class, 然后用PriorityQueue.   
- PriorityQueue里面用到了 String.compareTo(another String).巧妙。
- time: PQ uses O(nlogn), overall O(nlogn)
- slower, because the maxHeap needs to add all candidates

#### Trie && MinHeap屌炸天   
- 可以做一下
- http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/


#### Deleted Attempt: HashMap + collections.sort()
- 用HashMap存frequency, 用ArrayList存lists of words。最后返回从尾部向前数的k个。   
- 注意排序时Collection.sort()的cost是O(nLogk)
- not efficient




---

**21. [57. Insert Interval.java](https://github.com/awangdev/LintCode/blob/master/Java/57.%20Insert%20Interval.java)**      Level: Hard      Tags: [Array, PriorityQueue, Sort, Sweep Line]
      

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

**22. [215. Kth Largest Element in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/215.%20Kth%20Largest%20Element%20in%20an%20Array.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, MinHeap, PriorityQueue, Quick Select, Quick Sort]
      

kth largest in array

#### PriorityQueue, MinHeap
- Use minHeap to maintain PQ of k size and return PQ.peek()
    - Maintain MinHeap: only allow larger elements (which will squzze out the min value)
    - Remove peek() of queue if over size
- O(nlogk)

#### Quick Select, Quick Sort
- 用Quick Sort 里面partion的一部分: sort结束后是ascending的.
  - kth largest = (n - k)th smallest
  - in partioned array (quick sort), the portion before pivot are less than pivot
  - that is, the `pivot value` is the divider: anything after pivot is larger than it.
  - after `swap(nums, low, pivot)`: index low has the (n-k)th smallest, if `low = n-k`
- Steps:
  - each iteration: pick pivot,然后从low,和high都和pivot作比较
  - Find `low>pivot, high<pivot` to swap
  - The new low is the next partion point
- Time: average O(n), worst case O(n^2)
- space: O(1) extra spaces besides recursive stack



---

