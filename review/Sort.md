 
 
 
## Sort (31)
**0. [Largest Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Number.java)**      Level: Medium      Tags: [Sort]
      
给一串数字, 非负数, 把所有数字串联起来, 组成最大数字.

因为结果很大, 所以用string表示 

#### Sort, Comparator
- 考虑 more significant spot 应该拿到更大的值
- 如果sort number,  comparator 会比较难写: 每个digit的weight不同, 要分别讨论个位数和多位数.
- goal: 让较大的组合数排在前面, 让较小的组合数排在后面
- 不如: 组合两种情况, 用String比较一下大小 (也可以用 integer来比较组合数, 但是为保险不超Integer.MAX_VALUE, 这里比较String)
- String.compareTo() 是按照 lexicographically, 字典顺序排列的
- 利用compareTo, 来倒序排列 string, 刚好就得到我们要的结果.
- O(nlogn), 排序



---

**1. [QuickSort.java](https://github.com/awangdev/LintCode/blob/master/Java/QuickSort.java)**      Level: Medium      Tags: [Quick Sort, Sort]
      
implement quick sort.

#### Quick Sort
- 首先partition. 返还一个partition的那个中间点的位置: 这个时候, 所有小于nums[partitionIndex] 都应该在 partitionIndex左边
- 然后劈开两半
- 前后各自 quick sort, recursively
- 注意：在partition里面, 比较的时候nums[start] < pivot, nums[end]>pivot, 如果写成了 <= 会 stack overflow.
- Time O(nlogn), Space: O(1)



---

**2. [Sort List.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20List.java)**      Level: Medium      Tags: [Divide and Conquer, Linked List, Merge Sort, Sort]
      
#### Merge sort
- 1. find middle. 快慢指针
- 2. Sort: 切开两半，先sort前半, 如果先sort了mid.next~end, sort后，中间点mid.next == null，再sort前半段
- 3. Merge:  假设given list A, B 已经是sorted, 然后按照大小，混合。
- 要recursively call sortList() on partial list.

#### Quick sort
- 想做可以看讲义：http://www.jiuzhang.com/solutions/sort-list/
- 但是quick sort不建议用在list上面。
- 排列list, merge sort可能更可行和合理。原因分析在下面， 以及： http://www.geeksforgeeks.org/why-quick-sort-preferred-for-arrays-and-merge-sort-for-linked-lists/



---

**3. [Wiggle Sort.java](https://github.com/awangdev/LintCode/blob/master/Java/Wiggle%20Sort.java)**      Level: Medium      Tags: [Array, Sort]
      
方法1:
排序, nLog(n). 然后把直线上坡变成层叠山峰, 需要每隔几个(题目中是每隔2位)就做个swap 造成高低不平.
Note: 每隔山峰之间是相互没有关系的, 所以每次只要操心 [i], [i-1]两个位置就好了.

方法2:
O(n)
看好奇数偶数位的规律, 然后根据题目给出的规律, 跑一遍, 每次只关注两个位置: 把不合适的[i], [i-1]调换位置就好了.

方法3:
跟法2一样, 只是更巧妙一点罢了:
第一遍想太多. 其实做一个fall-through就能把问题解决，原因是因为：
这样的fall-through每次在乎两个element，可以一口气搞定，无关乎再之前的elements。
特别的一点：flag来巧妙的掌控山峰和低谷的变化。又是神奇的一幕啊！
这样子的奇观，见过就要知道了，没见过的时候有点摸不着头脑。



---

**4. [The Smallest Difference.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Smallest%20Difference.java)**      Level: Medium      Tags: [Array, Sort, Two Pointers]
      


---

**5. [Number of Airplane in the sky.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Airplane%20in%20the%20sky.java)**      Level: Medium      Tags: [Array, Interval, PriorityQueue, Sort, Sweep Line]
      
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

**6. [Sort Colors II.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Colors%20II.java)**      Level: Medium      Tags: [Partition, Quick Sort, Sort, Two Pointers]
      
Sort Color的普通版, sort all k colors in colors array.

Details 参见: https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Color.java

#### Quick Sort
- O(nk)



---

**7. [Sort Letters by Case.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Letters%20by%20Case.java)**      Level: Medium      Tags: [Partition, Sort, String, Two Pointers]
      
给一串字符(ASCII 大写, 小写字母), 要求sort 小写字母, 在大写字母前面. 

字母间的前后顺序无所谓, 也不需要preserve original order .

跟sort color分成相似.

#### Partition + Two pointers
- 其实就是quick sort里面的partition function的简化版
- Two pointers, 找一个 pivot 'a' 来区分大写小写字母
- ASCII code 里面 大写字母在小写字母前面, 数字更小
- 然后 while, move start++, end--,
- 每一轮都swap

#### Two pointers
- 直接用两个 pointer left/right 标记开头结尾
- 每次遇到 `>= 'a'` 就是小写字母, swap(chars, i, left);
- 每次遇到 `< 'a'` 就是大写字母, swap(chars, i, right);
- 注意: 每次处理完left swap, 任由for loop i++, 因为确定 [0 left] 都是准确的
- 每次处理完 right swap, 我们不确定从 right index 换过来的是不是正确的, 所以 i--, 跟for loop 的 i++抵消.
- 写 while loop 的 solution看起来更容易理解.



---

**8. [Insertion Sort List.java](https://github.com/awangdev/LintCode/blob/master/Java/Insertion%20Sort%20List.java)**      Level: Medium      Tags: [Linked List, Sort]
      
input一串数字, 需要出sorted output. 每次insert一个数字时, 都要放到正确的sorted的位置

每次insertion的时候, 都从input里面减掉这个数字

#### Linked List
- 把list里面每个元素都拿出来，scan and insert一遍
- Time O(n^2), worst case, 每次放入n个数字里面的element, 刚好都是最大的
- 所以每次要traverse n nodes, 然后走n次

##### 思考方法
- 如果已经有个sorted list, insert一个element进去。怎么做？
- while 里面每个元素都小于 curr, keep going
- 一旦curr在某个点小了，加进去当下这个空隙。



---

**9. [Subarray Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20Closest.java)**      Level: Medium      Tags: [PreSum, PriorityQueue, Sort, Subarray]
      

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

**10. [Sort Colors.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Colors.java)**      Level: Medium      Tags: [Array, Partition, Quick Sort, Sort, Two Pointers]
      
给一串数字 nums, 数字代表颜色[0,1,2]; 要求 sort nums, 数字最终按照大小排列. 

虽然叫sort color, 其实就是sort 这些 numbers, 只不过抽象了一下.

#### partition array, the base of quick sort
- partition the array by pivot k = {0, 1, 2}
- 每一次partition都return starting point of the current partition
- 然后根据下一个 color, 去还没有sort 干净的那个部分, 再sort一下就好
- time O(kn), where k = 0 => O(n)
- 这里只是partion, 并不需要recursively quick sort, 所以结果是简单的O(n)

#### One pass
- have two pointers, left/right
- start tracks red, end tracks blue. Swap red/blue to right position, and left++ or right--.
- leave white as is and it will be sorted automatically
- be very careful with index i: when swapping with index right, we do not know what is nums[right], so need to re-calculate index i .
- O(n)
- Note: this one pass solution does not work if there are more than 3 colors. Need to use the regular quick sorty.

#### Counting sort
- TODO: count occurance and reassign array



---

**11. [Partition Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Partition%20Array.java)**      Level: Medium      Tags: [Array, Quick Sort, Sort, Two Pointers]
      
给一串数字, 和 int k. 根据k的值partition array, 找到第一个i, nums[i] >= k.

#### Two Pointer
- Quick sort的基础. 
- Partition Array根据pivot把array分成两半。
- 从array两边开始缩进。while loop到遍历完。非常直白的implement。
- 注意low/high,或者叫start/end不要越边界
- O(n)
- 注意: 这里第二个inner while `while(low <= high && nums[high] >= pivot) {..}` 采用了 `nums[high] >= pivot`
- 原因是题目要找第一个nums[i] >= k, 也就是说, 即便是nums[i]==k也应该swap到前面去
- 这个跟quick sort 原题有一点点不一样.




---

**12. [[tool]. MergeSort.java](https://github.com/awangdev/LintCode/blob/master/Java/[tool].%20MergeSort.java)**      Level: Medium      Tags: [Lint, Merge Sort, Sort]
      

#### Merge Sort
- Divide and conquer, recursively
- 先从中间分段, merge sort 左边 (dfs), merge sort 右边
- 最后merge起来
- merge的时候因为是做int[], 所以没办法必须要O(n) space
- Time O(nlogn), Space O(n)



---

**13. [56. Merge Intervals.java](https://github.com/awangdev/LintCode/blob/master/Java/56.%20Merge%20Intervals.java)**      Level: Medium      Tags: [Array, PriorityQueue, Sort, Sweep Line]
      


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

**14. [252. Meeting Rooms.java](https://github.com/awangdev/LintCode/blob/master/Java/252.%20Meeting%20Rooms.java)**      Level: Easy      Tags: [PriorityQueue, Sort, Sweep Line]
      

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

**15. [259. 3Sum Smaller.java](https://github.com/awangdev/LintCode/blob/master/Java/259.%203Sum%20Smaller.java)**      Level: Medium      Tags: [Array, Sort, Two Pointers]
      
1. Similar to 15. 3Sum, but simpler.
1. 只需要count triplet, 但是不需要save triplet, 而且还不需要handle duplicated triplets
1. 发现start, end满足条件时候，(end - start)就是所有 sum <target的情况了。
1. 而一旦 > target, 那么就end--
1. 两层循环, O(n2)



---

**16. [855. Exam Room.java](https://github.com/awangdev/LintCode/blob/master/Java/855.%20Exam%20Room.java)**      Level: Medium      Tags: [PriorityQueue, Sort, TreeMap, TreeSet]
      

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

**17. [253. Meeting Rooms II.java](https://github.com/awangdev/LintCode/blob/master/Java/253.%20Meeting%20Rooms%20II.java)**      Level: Medium      Tags: [Greedy, Heap, PriorityQueue, Sort, Sweep Line]
      

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

**18. [1048. Longest String Chain.java](https://github.com/awangdev/LintCode/blob/master/Java/1048.%20Longest%20String%20Chain.java)**      Level: Medium      Tags: [Bucket Sort, DP, Hash Table, Sort]
      

#### Hash table, DP
- store `Map<Word, Longest Chain Length>`
- sort all words, try from short to long: short word will be calculated first to serve later words as candidate
- time: O(nlogn)
- space: O(n)

#### Hash Table, Bucket Sort,DP
- store `Bucket: List[17] of words`, given word size limit [0 ~ 16]
- time: O(n)
- space: O(n)



---

**19. [15. 3Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/15.%203Sum.java)**      Level: Medium      Tags: [Array, Sort, Two Pointers]
      
#### sort array, for loop + two pointer
- 处理duplicate wthin triplets: 
    - 如果最外圈的移动点i重复, 一直顺到结尾的最后一个再用.
    - 如果是triplet内有重复, 用完start point, 移动到结尾.
- Note:
   - 1. 找 value triplets, 多个结果。注意，并非找index。    
   - 2. 要升序, 第一层for loop 从最后一个元素挑起, 保证了顺序。    
   - 3. 去掉duplicate: check用过的同样的数字，都跳掉。不需要用同样的数字再计算一边已有结果。
- 时间 O(n^2), 两个nested loop

#### For loop + 2Sum
- HashMap 2Sum. Remember to handle duplicates
   - 1. For loop 挑个数字A
   - 2. 2Sum 出一堆2个数字的结果
   - 3. Cross match 步骤1里面的A



---

**20. [349. Intersection of Two Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/349.%20Intersection%20of%20Two%20Arrays.java)**      Level: Easy      Tags: [Binary Search, Hash Table, Sort, Two Pointers]
      

#### Set
- 用到hashset找unique && duplicate: O(m+n)

#### Binary Search
- 可以用binary search 找数字. 
- Note:binary search一定需要array sorted: nLog(m)



---

**21. [1094. Car Pooling.java](https://github.com/awangdev/LintCode/blob/master/Java/1094.%20Car%20Pooling.java)**      Level: Medium      Tags: [Greedy, Heap, PriorityQueue, Sort]
      

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

**22. [973. K Closest Points to Origin.java](https://github.com/awangdev/LintCode/blob/master/Java/973.%20K%20Closest%20Points%20to%20Origin.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, Sort]
      

#### PriorityQueue
- Create customized Point{} class
- Sort by distance
- Maintain queue size <= K

#### Divide and Conquer
- ?, select sort?



---

**23. [169. Majority Element.java](https://github.com/awangdev/LintCode/blob/master/Java/169.%20Majority%20Element.java)**      Level: Easy      Tags: [Array, Bit Manipulation, Divide and Conquer, Moore Voting, Sort]
      

#### HashMap count occurance
- Time, Space: O(n)


#### Moore Voting Algorithm 投票消减
- 前提: input必须valid, 比较罕见少用. Moore Voting Algorithm: https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
- 与当下candidate相同, vote++. 与之不同, vote--.
- Majority Number是指超半数, 多1个就行: 消减至最后, 会至少有vote>=1.
- 那么: vote++, vote--到最后剩下的就是winner. 
- 这个方法比较greedy, 前提是: valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。
- time: O(n), space O(1)

#### Sort
- sort entire nums array
- assume there is a solution, then nums[n/2] must be that majority num
- time O(nlogn)

#### Divide and Conquer
1. Recursive approach
1. For ange rangeA & rangeB, rangeA has majorElementA and rangeB has majorElementB
    - majorElementA = majorElementB, of course this element will be the major number for whole range
    - if majorElementA != majorElementB, then need to count both elements in whole range
    - of course the larger occurance will be the major num

#### Bit manipulation
- TODO

#### Related Problems
- Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。
- Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。



---

**24. [242. Valid Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/242.%20Valid%20Anagram.java)**      Level: Easy      Tags: [Hash Table, Sort]
      

#### int[26]

#### HashMap<Character, Integer>



---

**25. [1057. Campus Bikes.java](https://github.com/awangdev/LintCode/blob/master/Java/1057.%20Campus%20Bikes.java)**      Level: Medium      Tags: [Bucket Sort, Greedy, PriorityQueue, Sort]
      

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

**26. [1033. Moving Stones Until Consecutive.java](https://github.com/awangdev/LintCode/blob/master/Java/1033.%20Moving%20Stones%20Until%20Consecutive.java)**      Level: Easy      Tags: [Basic Implementation, Sort]
      

#### Analyze to understand
- put 3 elements into array, sort and follow below rules:
- min: 
    - if 3 elements consecutive, 0 move.
    - if only 1 pair of the two elemnets consecutive or if they have 1 slot in between, it needs exactly 1 move
    - otherwise, at most 2 moves
- max: # of open slots between them (high - low + 1) - n, where n = 3
- Follow up: `1040. Moving Stones Until Consecutive` is more interesting with special rulese (cannot move to `ending spot`), and it uses sliding window concept



---

**27. [274.H-Index.java](https://github.com/awangdev/LintCode/blob/master/Java/274.H-Index.java)**      Level: Medium      Tags: [Bucket Sort, Hash Table, Sort]
      

找到h-index, 给的citation int[] 并不是sorted. h-index 的definition 具体看题目.

#### Sort, find h from end
- 例子写出来，发现可以sort以后按照定义搜索一遍。 nlogn.
- 搜索一遍时候可以优化，用binary search. 但是没意义，因为array.sort已经用了nlogn
- 题目给的规则, 从小到大排序后: 剩下的 paper `n-h`, 全部要 <= h 个 citation.
- time O(nlogn), search O(n)

##### 正向思考
- 从i = 0 开始找第一个 `citations[i] >= h`, 就是第一个符合 h-index 规则的paper, return h

##### 反向思考
- 如果从 h = n, 每次h--; 那么 `x = n - h` 就是从 `[0 ~ n)` 开始找第一个 `dictations[x] >= h`, 就是结果
- 同时, `dictations[x-1]` 就是最后一个(dictation最多的)其余的paper.

#### Couting Sort / Bucket Sort
- O(n)
- Bucket sort的思想(更像是counting sort?): 过一遍 input, 把dictation value 作为 index, 分布在bucket[index]上++
- bucket[x] 是 count when # of citation == x. 
- 如果 x 大于 n的时候, 就超出了index范围, 但是刚好这个问题可以包容, 把这样的情况记位在bucket[n]就可以
- 巧妙: `sum += bucket[h]` where `h = [n ~ 0]` 利用了h-index的definition:
- #of papers (sum of bucket[n]...bucket[0]) has more than h cidations 
- 这里运用到了bucket sort的思想, 但是并不是sorting, 而h-index的定义运用的很巧妙.
- Read more about actual bucket sort: https://en.wikipedia.org/wiki/Bucket_sort

#### More about Counting Sort
1. Application: for number/character range
1. Steps:
	- Find range, define countArray
	- Count element and record in the array
	- PreSum the countArray
	- Start from beginning of the array, `print & decrese count` to produce the sorted elements




---

**28. [350. Intersection of Two Arrays II.java](https://github.com/awangdev/LintCode/blob/master/Java/350.%20Intersection%20of%20Two%20Arrays%20II.java)**      Level: Easy      Tags: [Binary Search, Hash Table, Sort, Two Pointers]
      

#### HashMap
- Map of nums1: <num, # appearance>
- check nums2 against nums1 map
- time:O(n + m)
- space:O(n + m)

#### Binary Search
- 



---

**29. [767. Reorganize String.java](https://github.com/awangdev/LintCode/blob/master/Java/767.%20Reorganize%20String.java)**      Level: Medium      Tags: [Greedy, Hash Table, Heap, Sort, String]
      


We want to exhaust largest population and merge like merging k list.
Problem: largest population may result in them being adjacent. How to resolve?

1) process and check at the end, or, 2) sanitize first and process assume correct input

#### Method1: K(k=2) seats apart problem (w/o sanitization)
- Aggregate map<char, count>, and sort the entry with priority queue.(Optionally, can use object `Letter {char c, int count}`)
- Naturally: we want to prioritize the largest population and exhaust it first, so we want to keep it in the a buffer queue
    - it is a queue, first in first out
    - monitor queue size k = 2, so that it holds off the just last-processed letter for 1 unit of time
    - the buffer then sends the last-process item to the main priority queue (pq will sort it again)
- Error handling: largest population may have extra letter
    - the main PQ has already exhausted
    - but the largest-population-letter will end up stuck in the buffer queue
    - it will never be picked up again so the final result sb will be shorter than orignal string: that is the error case
- Option0. Similar to `621. Task Scheduler`:
    - use a buffer to hold potential letter to add back, but NOT ADD BACK YET, until k slots have been filled.
- time: O(m), m = # of unique letters
- space: O(nmLogm), n = length, pq sorting requires mlogm, we will visit all n nodes.

#### Method2: HashMap<Num, # occurance>, Sort (Sanitize input)
- put all in map<char, count>
    - Sanitize the input: if certain popular char count is over (n + 1)/2, then it should fail right away, just return empty map.
    - Once the input is sanitized, when building results, we can be greedy and consume most popular char and then the rest 
- Int[2] can be used store char and count 
    - PriorityQueue can sort int[]. Okay to not specific length of int[] when defining pq.
    - Alternatively, can use a Letter {char c, int count} to represent





---

**30. [57. Insert Interval.java](https://github.com/awangdev/LintCode/blob/master/Java/57.%20Insert%20Interval.java)**      Level: Hard      Tags: [Array, PriorityQueue, Sort, Sweep Line]
      

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

