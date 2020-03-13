 
 
 
## TreeSet (4)
**0. [K Empty Slots.java](https://github.com/awangdev/LintCode/blob/master/Java/K%20Empty%20Slots.java)**      Level: Hard      Tags: [Array, BST, TreeSet]
      
题目解析后: find 2 number, that: 1. k slots between the 2 number, 2. no slots taken between the two number.

#### BST
- BST structure not given, use TreeSet to build BST with each node
- Every time find last/next inorder element 
- `treeSet.lower(x)`, `treeSet.higher(x)`
- 一旦位置相隔(k + 1), 就满足题目条件
- O(nlogn), good enough

#### Track slots of days
- Reverse the array, save days index into days[], where the new index is slot.
- days[i]: at slot i, which day a flower will be planted
- O(n)
- Needs to understand: http://www.cnblogs.com/grandyang/p/8415880.html



---

**1. [Max Sum of Rectangle No Larger Than K.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Sum%20of%20Rectangle%20No%20Larger%20Than%20K.java)**      Level: Hard      Tags: [Array, BST, Binary Search, DP, Queue, TreeSet]
      
给定一个非空的二维矩阵matrix与一个整数k，在矩阵内部寻找和不大于k的最大矩形和。

#### BST, Array, preSum
- 将问题reduce到: row of values, find 1st value >= target.
- 1. loop over startingRow; 2. loop over [startingRow, m - 1]; 3. Use TreeSet to track areas and find boundary defined by k.
- When building more rows/cols the rectangle, total sum could be over k: 
- when it happens, just need to find a new starting row or col, 
- where the rectangle area can reduce/remain <= k
- 找多余area的起始点: extraArea = treeSet.ceiling(totalSum - k). 也就是找 减去k 后 起始的/左边的area.
- 去掉这些左边的起始area, 剩下的就 <=k.    (num - extraArea)
- 为什么用TreeSet: area的大小无规律, 并且要找 >= 任意值 的第一个value. 给一串non-sorted数字, 找 >= target的数, 如果不写binary search, 那么用BST最合适
- O(m^2*nlogn)

#### 思想
- 从最基本的O(m^2*n^2) 考虑: 遍历 startingRow/startingCol
- rectangle? layer by layer? 可以想到Presum的思想, 大于需要的sum的时候, 减掉多余的部分
- 如何找到多余的area? 那么就是search: 把需要search的内容存起来, 可以想到用BST(TreeSet), 或者自己写Binary Search.



---

**2. [855. Exam Room.java](https://github.com/awangdev/LintCode/blob/master/Java/855.%20Exam%20Room.java)**      Level: Medium      Tags: [PriorityQueue, Sort, TreeMap, TreeSet]
      

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

**3. [715. Range Module.java](https://github.com/awangdev/LintCode/blob/master/Java/715.%20Range%20Module.java)**      Level: Hard      Tags: [Segment Tree, TreeSet]
      

#### TreeSet
- start with considering array structure but operation are all O(n)
    - what if we can easily find range, and update
- TreeSet:
    - build a class `Interval {int start, end;}`
    - build a customized `compareTo` that sorts the interval by start at default, but sort by end if a.start==b.start
    - Query: TreeSet allow us to find element in O(logn)
    - Add Range: finding the starting pointing takes O(logn), but update can be worst to update O(n)
    - Remove Range: finding the starting pointing takes O(logn), but update can be worst to update O(n)



---

