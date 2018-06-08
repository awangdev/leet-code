 
 
 
## Binary Search (35)
**0. [Count of Smaller Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Number.java)**      Level: Review      Tags: [Binary Search, Segment Tree]
      

和平时的segment tree问题不同。 0 ～ n-1代表实际数字。是造一个based on real value的segment tree.
Modify时，把array里面的value带进去，找到特定的位子（leaf）,然后count+1. 

最终在SegmentTree leaf上面全是array里面实际的数字。

trick:   
在query前，给进去的start和end是： 0 ~ value-1.   
value-1就是说，找比自己所在range小1的range（那么自然而然地就不包括自己了），这样就找到了smaller number.   


[那么其他做过的SegmentTree是怎么样呢？]   
那些构成好的SegmentTree(找min,max,sum)也有一个Array。但是构成Tree时候，随Array的index而构架。   
也就是说，假如有Array[x,y,....]:在leaf,会有[0,0] with value = x. [1,1] with value = y. 

[但是这题]   
构成时，是用actual value.也就是比如Array[x,y,....]会产生leaf:[x,x]with value = ..; [y,y]with value =...    

其实很容易看穿:   
若给出一个固定的array构成 SegmentTree，那估计很简单：按照index从0~array.lengh，leaf上就是[0,0] with value = x.

若题目让构造一个空心SegmentTree, based on value 0 ~ n-1 (n <= 10000), 然后把一个Array的value modify 进去。   
这样八成是另外一种咯。



---

**1. [Search Rotated in Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20Rotated%20in%20Sorted%20Array%20II.java)**      Level: Medium      Tags: [Array, Binary Search]
      

Allow duplicates之后：
因为最终binary search的结果也是O(n)
所以这道题要记得： 既然是O(n), 那来个简单的for loop 也就好了。

当然，要跟面试官提起来原因。别一上来就只有for。。。


---

**2. [Guess Number Higher or Lower.java](https://github.com/awangdev/LintCode/blob/master/Java/Guess%20Number%20Higher%20or%20Lower.java)**      Level: Easy      Tags: [Binary Search]
      

binary search 公式



---

**3. [2 Sum II - Input array is sorted.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum%20II%20-%20Input%20array%20is%20sorted.java)**      Level: Medium      Tags: [Array, Binary Search, Two Pointers]
      

升序array, 找2SUM.

#### 方法1:
- 排序好的array. Two pointer移动start和end，核查sum.
- 注意sum用long.
- O(n) time

#### 方法2: Binary Search, 因为已经排好序了啊
- 定住一个valueA, 然后在剩下的里面 binary serach 找 (target - valueB)
- O(nLogN), 就不写了



---

**4. [2 Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum%20II.java)**      Level: Medium      Tags: [Array, Binary Search, Two Pointers]
      

与 2sum II - input array is sorted类似. 都是sort array, 然后two pointer.

LintCode的题. 注意找的是greater/bigger than target。

由于给定条件允许O(nLogn):   
   sort
   two pointer

while里面two pointer移动。每次如果num[left]+num[right] > target，那么其中所有num[left++]的加上num[right]都>target.   
也就是,num[right]不动，计算加入挪动left能有多少组，那就是: right-left这么多。 全部加到count上去。     
然后right--.换个right去和前面的left部分作比较。



---

**5. [Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Subsequence.java)**      Level: Medium      Tags: [Binary Search, Coordinate DP, DP, Memoization, Sequence DP]
      

无序数组, 找最长的上升(不需要连续)数组 的长度. 先做O(n^2), 然后可否O(nLogN)?

#### DP, double for loop, O(n^2)
- 考虑nums[i]结尾的时候, 在[0, i) 里count有多少小于nums[i]
- 对于所有 i in [0, n), 最常的increasing序列有多少length?
- max需要在全局维护: nums是无序的, nums[i]也可能是一个很小的值, 所以末尾dp[i]并不是全局的max, 而只是对于nums[i]的max.
- 正因此, 每个nums[i]都要和每个nums[j] 作比较, j < i.
- dp[i] = Maht.max(dp[i], dp[j] + 1); j = [0 , i - 1]
- 时间复杂度  O(n^2)


#### O(nLogN)
- 维持一个list of increasing sequence
- 这个list其实是一个base-line, 记录着最低的increasing sequence.
- 当我们go through all nums的时候, 如果刚好都是上升, 直接append
- 如果不上升, 应该去list里面, 找到最小的那个刚好大于new num的数字, 把它换成num
- 这样就完成了baseline. 举个例子, 比如替换的刚好是在list最后一个element, 等于就是把peak下降了, 那么后面其他的数字就可能继续上升.
- '维护baseline就是一个递增的数列' 的证明, 还没有仔细想.



---

**6. [Copy Books.java](https://github.com/awangdev/LintCode/blob/master/Java/Copy%20Books.java)**      Level: Hard      Tags: [Binary Search, DP, Partition DP]
      

给一串书pages[i], k个人, pages[i] 代表每本书的页数. k个人从不同的点同时开始抄书. 

问, 最快什么时候可以抄完?

#### Partition DP
- 第一步, 理解题目要求的问题: 前k个人copy完n本书, 找到最少的用时; 也可以翻译成, n本书, 让k个人来copy, 也就是分割成k段.
- 最后需要求出 dp[n][k]. 开: int[n+1][k+1]. 
- 在[0 ~ n - 1]本书里, 最后一个人可以选择copy 1 本, 2 本....n本, 每一种切割的方法的结果都不一样
- 木桶原理, 因为K个人同时开始, 最坏的情况决定结果
- dp[n][k] = Math.min(Math.max(dp[j][k - 1], sum[j+1, n-1]), loop over i, k, j)
- Time: O(kn^2), space O(nk)

##### Init
- Init: dp[0][0] = 0, 0个人0本书
- Integer.MAX_VALUE的运用:
- 当 i = 1, k = 1, 表达式: dp[i][k] = Math.min(dp[i][k], Math.max(dp[j][k - 1], sum));
- 唯一可行的情况就只有一种: i=0, k=0, 刚好 0 个人 copy 0 本书, dp[0][0] = 0.
- 其他情况, i = 1, k = 0, 0 个人读 1本书, 不可能发生: 所以用Integer.MAX_VALUE来冲破 Math.max, 维持荒谬值.
- 当 i=0, k=0 的情况被讨论时候, 上面的方程式才会按照实际情况计算出 dp[i][k]
- 这道题的init是非常重要而tricky的

##### 计算顺序
- k个人, 需要一个for loop; 
- k个人, 从copy1本书开始, 2, 3, ... n-1,所以 i=[1, n], 需要第二个for loop
- 在每一个i上, 切割的方式可以有[0 ~ i] 中, 我们要计算每一种的worst time

##### 滚动数组
- [k] 只有和 [k - 1] 相关
- Space: O(n)

#### Binary Search
- 根据: 每个人花的多少时间(time)来做binary search: 每个人花多久时间, 可以在K个人之内, 用最少的时间完成?
- time variable的范围不是index, 也不是page大小. 而是[minPage, pageSum]
- validation 的时候注意3种情况: 人够用 k>=0, 人不够所以结尾减成k<0, 还有一种是time(每个人最多花的时间)小于当下的页面, return -1
- O(nLogM). n = pages.length; m = sum of pages.




---

**7. [Valid Perfect Square.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Perfect%20Square.java)**      Level: Review      Tags: [Binary Search, Math]
      

Binary找sqrt. 基本 mid+1, mid-1 template.
注意: define index as long. 



---

**8. [Intersection of Two Arrays II.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Arrays%20II.java)**      Level: Easy      Tags: [Binary Search, Hash Table, Sort, Two Pointers]
      

方法1:
用HashMap: 存一个nums1, 再拿nums2 check against map. 时间/空间:O(n)

方法2:
Binary search? 需要array sorted. 否则时间O(nlogn)不值得.
[没做完, 有错]



---

**9. [Find Peak Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element.java)**      Level: Medium      Tags: [Array, Binary Search]
      

binary search. 
Goal: find peak, where both sides are descending
最左边, 最右边是Integer.MIN_VALUE时候, 也能构成中间数mid是peak的条件.

Note:
没有必要特别check (mid-1)<0或者(mid+1)>=n.
证明:
1. 最左端: 当start=0, end = 2 => mid = 1, mid-1 = 0;
2. 最右端: 当end = n - 1, start = n - 3; mid = (start+end)/2 = n - 2; 
那么mid + 1 = n - 2 + 1 = n - 1 < n 是理所当然的



---

**10. [Pow(x,n).java](https://github.com/awangdev/LintCode/blob/master/Java/Pow(x,n).java)**      Level: Medium      Tags: [Binary Search, Math]
      

傻做就O(n), 要更好就考虑O(logN).
减少重复计算, 一切两半.

注意:
- n/2的奇数偶数
- n的正负
- n == 0的情况, x == 0, x == 1 的情况.


---

**11. [Minimum Size Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Size%20Subarray%20Sum.java)**      Level: Medium      Tags: [Array, Binary Search, Two Pointers]
      

方法1:
2 pointer, O(n). 找subarray, start 或 end pointer，每次一格这样移动.

好的策略: 
1. 先找一个solution, 定住end. 
2. 然后移动start; 记录每个solution if occurs
3. 然后再移动end，往下找。

Note: 虽然一眼看上去是nested loop.但是分析后，发现其实就是按照end pointer移动的Loop。start每次移动一格。总体上，还是O(n)

方法2:
Double for loop, base i 每次只+1, 所以最后O(n^2)

方法3:
Binary Search, O(nLogN)
Not done yet



---

**12. [Kth Smallest Number in Sorted Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Number%20in%20Sorted%20Matrix.java)**      Level: Medium      Tags: [Binary Search, Heap]
      

方法1:
和Merge K sorted Array/ List 类似：使用PriorityQueue。

因为Array的element无法直接找到next,所以用一个class Node 存value, x,y positions.

方法2:
Binary Search
https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85182/my-solution-using-binary-search-in-c


变型: Kth Largest in N Arrays


---

**13. [Find Minimum in Rotated Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Minimum%20in%20Rotated%20Sorted%20Array.java)**      Level: Medium      Tags: [Array, Binary Search]
      

画图, 最小值被rotate之后, 变成array中间的最低谷.
并且, 左边山坡的最小值, 大于右边山坡的最大值. 
以此来给binary search做判断.

O(nlogn)



---

**14. [Find Minimum in Rotated Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Minimum%20in%20Rotated%20Sorted%20Array%20II.java)**      Level: Hard      Tags: [Array, Binary Search]
      

一个需要严谨思考的题目. 因为有duplicate, 会导致不断平移, 所以最终time complexity是O(n)
所以不如直接扫一遍, 给出答案.

但是还是写一个Binary Search的样子, 只不过worst结果是O(n)



---

**15. [Find Peak Element II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element%20II.java)**      Level: Hard      Tags: [Binary Search, DFS, Divide and Conquer]
      

2Dmatrix, 里面的value有一些递增, 递减的特点(细节比较长, 看原题). 目标是找到peak element

#### DFS

##### 基本原理
- 我们不可能一口气准确定位(x,y), 但是我们可以再一个row/col里面, 找到1D array的 peak.
- 根据这个点, 再往剩下两个方向移动
- 1. 在中间的一行i=midX, 找到peak所在的y.
- 2. 在中间的一列j=midY, 找到peak所在的x. (有可能强势override之前找到的y, 也就是放弃那一行的peak, 在midY上找peak)
- 3. 根据 (x,y) 的4个neighbor check (x,y)是不是 peak, 如果不是, 像更高的位置移动一格
- 4. 根据之前算的 midX, midY 把board分成4个象限, 在每一份里面再继续找
- 这个题目LintCode不给做了, 所以思路对的, 但是解答还没有再次验证.

##### 剪枝/切分象限
- 每次只是找到一个row/col里面的peak而已!
- 找到这个点, 就等于把board切成了两半.
- 然后, 再跟剩下的相邻的两个位置比较, 就知道了哪里更大, 就去哪里找peak, 也就是又切了第二刀.
- 切第二刀的时候, 也要把(x, y) 移到需要取的象限. 进行DFS
- 根据mid row 切割: 
- http://www.jiuzhang.com/solution/find-peak-element-ii/#tag-highlight-lang-java
- http://courses.csail.mit.edu/6.006/spring11/lectures/lec02.pdf

##### 时间复杂度
- 每一个level都减一半
- T(n) = n + T(n/2) = n + n/2 + n/4 + ... + 1 = n(1 + 1/2 + .... + 1/n) = 2n = O(n)

#### Binary Search
- TODO
- O(nLogN)



---

**16. [Sqrt(x).java](https://github.com/awangdev/LintCode/blob/master/Java/Sqrt(x).java)**      Level: Easy      Tags: [Binary Search, Math]
      

#### s- qrt(int x)
- 理解题意, 从[0, x]找一个可以m*m=x的值.
- 注意, 如果找不到, 最后问考官该return一个什么值：按道理，因为return int, 会取整，那么return一个平方最close to x就可以.
- 注意 mid 用 long, 因为很可能超过最大int.

#### sqrt(double x)
- 二分float number, 应该用精度来定义结尾.
- 还是二分, 但是判断条件变成: while ( end - start > eps)
- eps = 1e-12,也就是精度到1e-12



---

**17. [First Bad Version.java](https://github.com/awangdev/LintCode/blob/master/Java/First%20Bad%20Version.java)**      Level: Easy      Tags: [Binary Search]
      

Binary Search

根据isBadVersion的性质，判断还如何end=mid or start=mid.     
isBadVersion 是有方向的嘛，一个点错了，后面全错。



---

**18. [Wood Cut.java](https://github.com/awangdev/LintCode/blob/master/Java/Wood%20Cut.java)**      Level: Medium      Tags: [Binary Search]
      

二分的思想: 判断的是一个 validate() function, 而不是简单的'=='

不需要sort! 为什么呢? 因为我们不是在given array上面二分, 我们是根据最大值在[0, max]上二分.

Overall time: O(nLogM), where M = largest wood length



---

**19. [Find the Duplicate Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Duplicate%20Number.java)**      Level: Medium      Tags: [Array, Binary Search, Two Pointers]
      

- 注意不要思维定式: 以为mid是index
- 这里mid其实是binary search on value [1, n] 的一个value.
- 再次用到validate() function

Time: O(nLogN)



---

**20. [Maximum Average Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20II.java)**      Level: Review      Tags: [Array, Binary Search, PreSum]
      

给int[] nums 和 window min size k. window size可以大于K. 找最大的连续数列average value.

- Binary Search的思想, 用在所要找的这个 average sum 上面. 大小是在[min, max]之中
- 找k的时候, 是>=k都可以, 巧用一个 min(preSum)的概念.
- 找k的时候, 画图, 可以看出来, 其实要的是 k window 里面的sum [x, i], 所以要用 sum[0, i] - sum[0, x]

需要仔细去读下面的notes.



---

**21. [Classical Binary Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Classical%20Binary%20Search.java)**      Level: Easy      Tags: [Binary Search]
      

#### Binary Search Template
- while: start + 1 < end
- mid = start + (end - start) / 2;
- 根据mid作比较
- 末尾double check start, end.




---

**22. [Closest Binary Search Tree Value.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Binary%20Search%20Tree%20Value.java)**      Level: Easy      Tags: [BST, Binary Search, Tree]
      

给一个BST, 和一个double target, 走位找到最接近的number.

#### Recursive
- when less than curr val, consider left
- when greater than curr val, consider right
- dfs到底, 然后每一层比较, 再return

#### Binary Search
- 记录找到过的closest
- Binary Search, 根据current node走位,
- 找到 node.val == target, 或者走位走完, return closest



---

**23. [Count Complete Tree Nodes.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20Complete%20Tree%20Nodes.java)**      Level: Medium      Tags: [Binary Search, Tree]
      

Complete Tree就是说, 最后一个level可能是缺node的(不是说最右下角缺node, 别忘了!)

#### DFS + Optimization
- 每次看最左left depth和最右leaf depth 是不是一样, 如果一样, 直接 2 ^ h - 1就好
- 不一样的话, 再DFS

##### Trick
- 直接DFS会timeout, O(n), 其实可以optimize
- to pass the test with O(h^2), 位运算: Math.pow(2, h) = 2 << (h - 1). 神奇!
- 2 << 1就是把所有bits往左移动一位, 也就是 * 2 

#### Iteratively
- See details in comments inline. 要对tree非常理解
- binary tree one child tree nodes # = 2 ^ h - 1; 所以一个child tree + root = 2 ^ h



---

**24. [Closest Number in Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Number%20in%20Sorted%20Array.java)**      Level: Easy      Tags: [Binary Search]
      

- Binary Search 的一种变型, LintCode无法再跑一边.
- 考虑mid-1, mid+1.
- 一旦没有mid = target.index。 那么target最终就narrow down在(mid-1,mid) 或者(mid,mid+1)   



---

**25. [Russian Doll Envelopes.java](https://github.com/awangdev/LintCode/blob/master/Java/Russian%20Doll%20Envelopes.java)**      Level: Hard      Tags: [Binary Search, Coordinate DP, DP]
      

俄罗斯套娃, 这里用envelope来表现. 给一串array, 每一个[x, y] 是envelope 长宽. [[5,4],[6,4],[6,7],[2,3]]. 

看用这些套娃, 可以最多套几个.

#### DP: 1D Coordinate
- envelopes没有顺序, 先排序 (主要根据第一个index排序)
- 然后观察: 排序过后, 就变成了1D的坐标动态规划.
- max number 取决于上一个成功Russian doll的 max value + 1
- 上一个index不知道, 所以遍历找上一个index. 
- 当下index i 的状态, 取决于前面index j 的状态, 所以遍历两个index.
- O(n^2)的DP, n = envelopes.length;

#### DP: 2D Coordinate
- 这个方法是自己想出来的, 但是时间复杂度太大, timeout
- 把envelop标记在2D grid上面, 然后像走机器人一样, 求到最右下角的最大 count max.
- count 当下能存在多少Russian doll
- 两种情况: 当下coordinate 没有target, 当下coordinate有target
- 当下coordinate 没有target: 如同机器人走法, Math.max(dp[i - 1][j], dp[i][j - 1])
- 当下coordinate 有target: dp[i - 1][j - 1] + dp[i][j]
- timeout: O(n^2), n = largest coordinate.




---

**26. [Last Position of Target.java](https://github.com/awangdev/LintCode/blob/master/Java/Last%20Position%20of%20Target.java)**      Level: Easy      Tags: [Binary Search]
      

给一个sorted integer array, 找target出现的最后的index. array 里有重复数字

有重复,不是末尾点，继续binary search



---

**27. [Count of Smaller Numbers After Self.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Numbers%20After%20Self.java)**      Level: Review      Tags: [BST, Binary Indexed Tree, Binary Search, Divide and Conquer, Segment Tree]
      

给一串数字nums[], 求一个新数组result, where result[i] = # of smaller items on right of nums[i]

#### Binary Search
- sort and insert 进一个新list, 新的list是sorted
- 从末尾 i = n-1 遍历nums[]
- 每一次insert nums[i] 进list的位置, 就是# of smaller items on right side of nums[i]
- 每次记录下result[i]
- **问题**: 这里的binary search 是用 `end = list.size(); while(start<end){...}`做的, 可否换成用`end=list.size() - 1`?

#### Binary Indexed Tree
- TODO, have code

#### Segment Tree
- TODO, it seems too complicated for this problem.



---

**28. [H-Index II.java](https://github.com/awangdev/LintCode/blob/master/Java/H-Index%20II.java)**      Level: Medium      Tags: [Binary Search]
      

找到h-index, 给的citation int[] 已经sorted. h-index 的definition 具体看题目.

#### Binary Search
- H-index的一个优化, 找target value, 满足 `value >= h`, where `h = n - mid`
- O(nlogn)



---

**29. [Interval Minimum Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Minimum%20Number.java)**      Level: Medium      Tags: [Binary Search, Divide and Conquer, Segment Tree]
      

给一串数字 int[], 然后一个query Interval[], 每个interval是 [start, end], 找query 区间里的最小值.

#### Segment Tree
- SegtmentTree, methods: Build, Query. 这题是在SegmentTreeNode里面存min.
- 类似的有存:max, sum, min



---

**30. [Interval Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Sum.java)**      Level: Medium      Tags: [Binary Search, Segment Tree]
      

给一串数字 int[], 然后一个query Interval[], 每个interval是 [start, end], 找query 区间里的sum.

#### Segment Tree + Binary Search
- 其实是segment tree 每个node上面加个sum
- 记得Segment Tree methods: Build, Query
- Note: 存在SegmentTreeNode里面的是sum.  其他题目可能是min,max,count ... or something else.



---

**31. [Search a 2D Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20a%202D%20Matrix.java)**      Level: Medium      Tags: [Array, Binary Search]
      

给2D matrix, 每行sorted, 每行的首位都大于上一行的末尾. goal: find target from matrix

#### 2D matrix 转1D array
- 一行一行是从小到大, sorted, 连续的, 可以看做1D sorted array
- Binary Search



---

**32. [Search a 2D Matrix II.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20a%202D%20Matrix%20II.java)**      Level: Medium      Tags: [Binary Search, Divide and Conquer]
      

给matrix, 每一行sorted, 每一列从上往下sorted, 找target是否存在

#### Binary Search
- 根据给定的性质, 其实点选的极端一点: x = 最下面的row, y = 当下一行里面最小的left position. 
- (x,y)在左下角
- 在此情况下, 只能往一个方向运行: 如果小于target, y++; 如果大于target, 那么只能x--
- 每次操作, 都是删掉一行, 或者一列, 再也不需要回头看
- `while (x >= 0 && y < col) {}` 确保不会跑脱
- 同样的方式: 可以从右上角(0, col - 1) 开始, 代码稍微改一改

#### Divide and Conquer?
- TODO



---

**33. [Search for a Range.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20for%20a%20Range.java)**      Level: Medium      Tags: [Array, Binary Search]
      

给sorted array, 有重复数字, 找跟target重合所在的range.

#### Binary Search
- 2个while loop
- 找first/last occurance
- TODO: Can the code be simplified?




---

**34. [Median of two Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Median%20of%20two%20Sorted%20Arrays.java)**      Level: Hard      Tags: [Array, Binary Search, DFS, Divide and Conquer]
      

著名的找两个sorted array的中位数. 中位数定义: 如果两个array总长为偶数, 取平均值.
题目要求在 log(m + n) 时间内解决

- 看到log(m+n), 就想到binary search, 或者是recursive 每次砍一半
- 两个sorted array 参差不齐, 肯定不能做简单的binary search

#### Divide and Conquer, recursive
- 这里有个数学排除思想: 考虑A, B各自的中间点.
- 如果A[mid] < B[mid], 那么 A[0 ~ mid - 1] 就不在 median的range里面, 可以排除. divide/conquer就这么来的.
- 具体逻辑看代码, 大致意思就是: 每次都取比较A 和 B [x + k / 2 - 1] 的位置, 然后做range 排除法
- end cases: 
- 1. 如果我们发现dfs()里面A或者B的start index溢出了, 那么就是最简单的case: midian一定在另外那个array里面
- 2. 如果 k == 1: 就是找A/B 里面的1st item, 那么做个 `Math.max(A[startA], B[startB])` 就可以
- 总共的数字长度是 (m + n) 而且每次都有一般的内容被删除, 那么time就是 O(log(m + n))

#### Binary Search
TODO:



---

