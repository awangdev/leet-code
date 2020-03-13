 
 
 
## Queue (3)
**0. [Max Sum of Rectangle No Larger Than K.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Sum%20of%20Rectangle%20No%20Larger%20Than%20K.java)**      Level: Hard      Tags: [Array, BST, Binary Search, DP, Queue, TreeSet]
      
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

**1. [621. Task Scheduler.java](https://github.com/awangdev/LintCode/blob/master/Java/621.%20Task%20Scheduler.java)**      Level: Medium      Tags: [Array, Enumeration, Greedy, PriorityQueue, Queue]
      

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

**2. [346. Moving Average from Data Stream.java](https://github.com/awangdev/LintCode/blob/master/Java/346.%20Moving%20Average%20from%20Data%20Stream.java)**      Level: Easy      Tags: [Design, Queue, Sliding Window]
      

给一个interface, design一个structure, 能够计算moving window average.

#### Queue
- 读懂题目, 注意average 和 window 的处理.
- 简单的queue.size() comparison
- Note: if we it is calculate moving-window-product, better to use deque :)
- Sliding window?
    - It has the spirit of slinding window: 1) maintain a range; 2) check range size `if (queue.size() > size)`
    - Though, the solution must use a data structure to store data; it is not the traditional sliding window type of `left/right` pointer problem



---

