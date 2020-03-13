 
 
 
## Array (126)
**0. [Jump Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game%20II.java)**      Level: Hard      Tags: [Array, Coordinate DP, DP, Greedy]
      

给一串数字 是可以跳的距离. goal: 跳到最后的index 所可能用的最少次数.

#### Method1: Greedy
- maintain the `farest can go`, and use it the target for i increse towards. Why?
    - 1) when I know the `farest can go`, in fact it is just currently 1 step away.
    - 2) why to iterate from curr `i to farset`? In range [i, farest], we will calc the new `maxRange`
    - 3) once `i` reaches `farset`, update `farest = maxRange`
- greedy concept: once we know the farest we can reach at the moment, it is just 1 step away :)
- On average should be jumpping through the array without looking back
- time: average O(n)
- Impl:
    - 图解 http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
    - track the farest point
    - whenver curr index reachest the farest point, that means we are making a nother move, so count++
    - there seems to have one assumption: must have a solution. Otherwise, count will be wrong number. 
    - 其实跟第一个greedy的思维模式是一模一样的.


#### Method2: DP
- DP[i]: 在i点记录，走到i点上的最少jump次数
- dp[i] = Math.min(dp[i], dp[j] + 1);
- condition (j + nums[j] >= i)
- 注意使用 dp[i] = Integer.MAX_VALUE做起始值, 来找min
- time: O(n^2), slow, and timesout



---

**1. [Missing Ranges.java](https://github.com/awangdev/LintCode/blob/master/Java/Missing%20Ranges.java)**      Level: Medium      Tags: [Array]
      
#### Basic Implementation
- O(n)
- 两个pointer， 每次计较prev和curr之间的部分.
- 然后prev = curr，向前移动一格
- TODO: check the edge case and make sure max/min of int are checked



---

**2. [Missing Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Missing%20Number.java)**      Level: Easy      Tags: [Array, Bit Manipulation, Math]
      
给一串unique数字, 数字取自 [0 ~ n], 无序, 找第一个skipped的数字.

#### Swap 
- 跟First Missing Positive 非常像, 只有一行代码的区别.
- swap 所有的数字, 到自己的correct position
- 最后一个for loop找到错位的index, 也就是缺的数字.

#### Bit Manipulation
- XOR will only retain bits that are different 1 ^ 0 = 1, but 0^0, 1^1 == 0
- Use that feature, 把所有value都和index XOR了
- 剩下的多余的数字, 其实是那个index无法被XOR消掉, 也就是那个缺的number value.
- 注意: 题目告诉数字是 [0 ~ n], 然而缺一个数字, 那么在[0 ~ n - 1] 里面, 最大的数字(不管缺没缺), 一定是 n = nums.length.

#### HastSet
- 全存, 找missing
- O(n) space, 不合题意

#### sorting
- sort, 找1st missing
- O(n log n) 太慢, 不合题意



---

**3. [Triangles.java](https://github.com/awangdev/LintCode/blob/master/Java/Triangles.java)**      Level: Medium      Tags: [Array, Coordinate DP, DFS, DP, Memoization]
      
给一个list<list<Integer>> triangle, 细节原题. 找 min path sum from root.

#### DFS + Memoization
- 其实跟给一个2D matrix没有什么区别, 可以做dfs, memoization.
- initialize memo: pathSum[i][j] = MAX_VALUE; 计算过的path省略
- Bottom-top: 先dfs到最深的path, 然后逐步网上返回
- `OR 原理: min(pathA, pathB) + currNode`
- 浪费一点空间, pathSum[n][n]. space: O(n^2), where n = triangle height
- 时间:O(n^2). Visit all nodes once: 1 + 2 + 3 + .... n = n^2

#### DP
- 跟dfs的原理很像, `OR 原理: min(pathA, pathB) + currNode`
- init dp[n-1][j] = node values
- build from bottom -> top: dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
- 跟传统的coordinate dp有所不同, inner for loop 是需要计算 j <= i, 原因是triangle的性质.
- 空间: dp[n][n]. space: O(n^2)
- 时间:O(n^2). Visit all nodes once: 1 + 2 + 3 + .... n = n^2

#### DP + O(n) space 
- Based on the DP solution: the calculation always depend on `next row` for col at `j` and `j + 1`
- 既然只depend on next row, 可以用rolling array来处理: reduce to O(n) space.
- Further: 可以降维, 把第一维彻底去掉, 变成 dp[n]
- 同样是double for loop, 但是只在乎column changes: `dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);`  



---

**4. [Summary Ranges.java](https://github.com/awangdev/LintCode/blob/master/Java/Summary%20Ranges.java)**      Level: Medium      Tags: [Array]
      
给一串sorted list, 中间有缺数字, return 所有数字的range string (example 看题目)

#### Basic implementation
- 用一个list as the buffer to store candidates
- when: 1. end of nums; 2. not continuous integer => convert list to result



---

**5. [Longest Increasing Continuous subsequence II.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence%20II.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP, Memoization]
      
#### Coordinate DP
- due to access permission, not test
- dp[i][j]: longest continuous subsequence length at coordinate (i, j)
- dp[i][j] should come from (i-1,j) and (i, j-1).
- dp[0][0] = 1
- condition: from up/left, must be increasing
- return dp[m-1][n-1]

#### Memoization
- O(mn) space for dp and flag.
- O(mn) runtime because each spot will be marked once visited. 
- 这个题目的简单版本一个array的例子：从简单题目开始想DP会简单一点。每个位置，都是从其他位置（上下左右）来的dpValue +　１.　如果啥也没有的时候，init state 其实都是1， 就一个数字，不增不减嘛。




---

**6. [Plus One.java](https://github.com/awangdev/LintCode/blob/master/Java/Plus%20One.java)**      Level: Easy      Tags: [Array, Math]
      
简单的实现, 加1, 进位. 唯一取巧的地方, 最后如果要多一位, 一定是10000...这个模式, 可以走捷径, 直接来个+1size的array, 然后第一位=1.
注意,转换成long也不合理,用太多memory.


---

**7. [Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Consecutive%20Sequence.java)**      Level: Hard      Tags: [Array, Hash Table, Union Find]
      
给一串数字, unsorted, 找这串数字里面的连续元素序列长度 (consecutive序列, 是数字连续, 并不是说要按照原order)

#### HashSet
- 要想看连续元素, 必须要num++, num--这样搜索
- 1. 需要O(1)找到元素
- 2. 需要简单快速找到 num - 1, num + 1.
- 如果用min,max开array, 耗费空间
- 用HashSet来存, 用set.contains() 来查找 num - 1, num + 1 存在与否
- for loop. O(n) 
- 里面的while loop 一般不会有O(n); 一旦O(n), 也意味着set 清零, for loop也不会有更多 inner while 的衍生.
- overall O(n) 时间复杂度


#### Union Find
- 最终是要把相连的元素算一下总长, 其实也就是把元素group起来, 相连的group在一起, 于是想到UnionFind
- 这里用到了一个`int[] size` 来帮助处理 `合并的时候parent是哪个`的问题: 永远往group大的union里去
- main function 里面, 有一个map来track, 每个元素, 只处理1遍.
- union的内容: current number - 1, current number + 1
- https://www.jianshu.com/p/e6b955ca208f

##### 特点
- Union Find 在index上做好像更加容易
- 其他union find function: `boolean connected(a,b){return find(a) == find(b)}`



---

**8. [Find Minimum in Rotated Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Minimum%20in%20Rotated%20Sorted%20Array.java)**      Level: Medium      Tags: [Array, Binary Search]
      
画图, 最小值被rotate之后, 变成array中间的最低谷.
并且, 左边山坡的最小值, 大于右边山坡的最大值. 
以此来给binary search做判断.

O(nlogn)



---

**9. [Minimum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Subarray.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Subarray]
      

给一串数组, unsorted, can have negative/positive num. 找数组中间 subarray 数字之和的最小值

#### DP
- 看到 min value, 至少考虑dp:
- Consider last num: min sum will be (preMinSum + curr, or curr)
- Use preMinSum to cache previouly calcualted min sum, also compare with +curr.
- Have a global min to track: because the preMinSum can be dis-continuous. 
- 也可以写成 dp[i] 但是没什么必要



---

**10. [Maximum Average Subarray I.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20I.java)**      Level: Easy      Tags: [Array, Subarray]
      

简单的求sum of fixed window k, 同时求max avg, 结尾求余数就好.



---

**11. [Median of Two Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Median%20of%20Two%20Sorted%20Arrays.java)**      Level: Hard      Tags: [Array, Binary Search, DFS, Divide and Conquer]
      
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




---

**12. [Word Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search.java)**      Level: Medium      Tags: [Array, Backtracking, DFS]
      

#### DFS, Backtracking:
- 找到开头的字母, 然后recursively DFS 去把word串到底:
- 每到一个字母, 朝四个方向走, 之中一个true就可以.
- Note:每次到一个字母，mark一下'#'. 4个path recurse回来后，mark it back.

#### Note: other ways of marking visited:
- 用一个boolean visited[][]
- Use hash map, key = x@y




---

**13. [Triangle Count.java](https://github.com/awangdev/LintCode/blob/master/Java/Triangle%20Count.java)**      Level: Medium      Tags: [Array]
      
其实也就是3sum的变形, 或者而说2sum的变形. 主要用2 pointers来做.
注意, 在选index时候每次定好一个 [0 ~ i], 在这里面找点start, end, 然后i 来组成triangle.
Note巧妙点:
在此之中, 如果一种start/end/i 符合, 那么从这个[start~end]中, 大于start的都可以, 所以我们count+= end-start.
反而言之, <end的其他index, 就不一定能符合nums[start] + nums[end] > nums[i]



---

**14. [Minimum Size Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Size%20Subarray%20Sum.java)**      Level: Medium      Tags: [Array, Binary Search, Subarray, Two Pointers]
      

给一串positive integer, 找最短的subarray sum, where the sum >= s

#### Two pointer
- 全部是positive integer, 那么preSum一定是增长的.
- 那其实就用two pointer: `start=0, end=0` 不断往前移动. 策略: 
- 1. end++ until a solution where sum >= s is reached
- 2. 然后移动start; 记录每个solution, Math.min(min, end - start);
- 3. 然后再移动end，往下找
- Note: 虽然一眼看上去是nested loop.但是分析后，发现其实就是按照end pointer移动的Loop。start每次移动一格。总体上，还是O(n)

#### Binary Search
- O(nlogn) NOT DONE.

#### Double For loop
- O(n^2), inefficient



---

**15. [Construct Binary Tree from Inorder and Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Tree]
      
#### DFS, Divide and Conquer
- 写个Inorder和Postorder的例子。利用他们分left/right subtree的规律解题。
- Postorder array 的末尾， 就是当下层的root.   
- 在Inorder array 里面找到这个root,就刚好把左右两边分割成left/right tree。
- 这题比较tricky地用了一个helper做recursive。 特别要注意处理index的变化, precisely考虑开头结尾
- runtime: O(n), visit && build all nodes

#### Improvement
- `findMid(arr)` can be replaced with a map<value, index>, no need execute O(n) search at runtime



---

**16. [Maximal Rectangle.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Rectangle.java)**      Level: Hard      Tags: [Array, DP, Hash Table, Stack]
      
#### 方法1: monotonous stack
分解开来, 其实是'Largest Rectangle in Histogram', 只不过这里要自己model heights.
一个2D array里面的rectangle, 最终也是用height * width做出来的.
巧妙在于, 把每一行当做底边, 算出这个底边, 到顶部的height: 
- 如果底边上的一个value==0, 那么算作没有height(以这个底边做rectangle, value==0的位置是空中楼阁, 不能用)
- 如果底边上的value==1, 那么就把上面的height加下来, 做成histogram

如果看具体实例, 有些row似乎是白算的, 但是没有办法, 这是一个搜索的过程, 最终会比较出最优解.

#### 方法2: DP
Coordinate DP?



---

**17. [Longest Increasing Continuous subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence.java)**      Level: Easy      Tags: [Array, Coordinate DP, DP]
      
https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/

O(n)跑2遍for.
O(1)是用了两个int来存：每次到i点时，i点满足条件或不满足条件所有的longestIncreasingContinuousSubsequence.
特点：返跑一回，ans还是继续和left轮的ans作比较；求的所有情况的最大值嘛。



---

**18. [Max Area of Island.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Area%20of%20Island.java)**      Level: Easy      Tags: [Array, DFS]
      
#### DFS
- 虽然Easy, 但用到DFS最基本的想法.
- 1. dive deep
- 2. mark VISITED
- 3. sum it up
- Time: worst O(mn), traverse all possible nodes

- 更要注意, 要从符合条件value==1的地方开始dfs.
- 对于什么island都没有的情况，area应该位0， 而不是Integer.MIN_VALUE, 问清楚考官那小伙, 别写顺手。



---

**19. [Find Peak Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element.java)**      Level: Medium      Tags: [Array, Binary Search]
      
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

**20. [K Empty Slots.java](https://github.com/awangdev/LintCode/blob/master/Java/K%20Empty%20Slots.java)**      Level: Hard      Tags: [Array, BST, TreeSet]
      
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

**21. [Game of Life.java](https://github.com/awangdev/LintCode/blob/master/Java/Game%20of%20Life.java)**      Level: Medium      Tags: [Array]
      
#### basic
- 简单的implementation, 把count function写清楚就好.
- time: O(mn), extra space: O(mn)
- 注意结尾要一个个board[i][j]copy

#### follow up
unlimited border? 可能需要分割board. 用大框分割, 每次换行的时候, 重复计算2行就好了. see details below.

#### improvement: do it in place!
- time: O(mn), extra space: O(1)
- bit manipulation: 用第二个bit来存previous value.
- 因为我们只考虑 0 和 1 而已, 所以可以这样取巧. 但是并不scalable.
- 如果需要存multiple state, 可能需要移动更多位, 或者用一个 state map
- 注意 bit manipulation 的细节: <<1, >>1, 还有 mast的用法: |, & 




---

**22. [Rotate Image.java](https://github.com/awangdev/LintCode/blob/master/Java/Rotate%20Image.java)**      Level: Medium      Tags: [Array, Enumeration]
      
#### 找公式规律
- 找到个转角度的规律公式: r = c; c = (w - r)
- 用temp variable, swap in place.



---

**23. [Combination Sum IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20IV.java)**      Level: Medium      Tags: [Array, Backpack DP, DP]
      
给一串数字candidates (no duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 可以用任意多次.

#### Backpack DP
- 计数问题, 可以想到DP. 其实就是Backpack VI.
- 从x个数字里面找candidate(可以重复用同一个数字), 来sum up to target. 找: # of ways to form the sequence.
- Backpack VI: 给一个数组nums, 全正数, 无重复数字; 找: # of 拼出m的方法
- dp[i]: # of ways to build up to target i
- consider last step: 如果上一步取的是 candidate A, 那么就该加到dp[i]:
- dp[i] += dp[i - A]
- 要找overall dp[i], 就做一个for loop: dp[i] = sum{dp[i - num]}, where for (num: nums)
- Time: O(mn). m = size of nums, n = target
- If we optimize dp for loop, 需要Sort nums. O(mlogm). will efficient 如果m是constant或者relatively small. Overall: O(n)

#### DFS, backtracking
- 尽管思考方式是对的, 但是 times out
- 可以重复使用数字的时候, 比如用1 来拼出 999, 这里用1就可以走999 dfs level, 不efficient



---

**24. [Wiggle Sort.java](https://github.com/awangdev/LintCode/blob/master/Java/Wiggle%20Sort.java)**      Level: Medium      Tags: [Array, Sort]
      
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

**25. [[tool] Quick Select - Median.java](https://github.com/awangdev/LintCode/blob/master/Java/[tool]%20Quick%20Select%20-%20Median.java)**      Level: Easy      Tags: [Array, Lint, Quick Select, Quick Sort, Two Pointers]
      

给一串无序数组, 找到median(sort之后 位置在中间的数字).

#### Quick Select
- 跟`kth largest element in an Array`的 template一样.
- quickSelect 可以找到 kth 最小的元素
    - 利用这个原理, 找这个kth最小值, 然后如果 == target index, 就找到了我们的median
- 主要步骤: 
    - 1. partition
    - 2. check end state `pivot index ?= target index`
    - 3. recursive call one part of the array 
- time: 与quickSort不同在于, 每次只要在一半list里面recurring, 所以把O(logn)的时间复杂度降到O(n)
    - n + n/2 + n/4 + n/8 + ....+ 1 = O(2n) = O(n)
- space: O(logn), based on recursive stacks




---

**26. [Friends Of Appropriate Ages.java](https://github.com/awangdev/LintCode/blob/master/Java/Friends%20Of%20Appropriate%20Ages.java)**      Level: Medium      Tags: [Array, Math]
      
#### Array, Math
- 这个问题更在于问题本身的分析 (而且还有多余条件); 最终的for loop 也比较不standard.
- People younger than 15 cannot make requests due to the first rule.
- From the age of 15, people can make requests to the same age: a[i] * (a[i] - 1) requests.
- People can make requests to younger people older than 0.5 * i + 7: a[j] * a[i] requests.
- The third rule is redundant as the condition is already covered by the second rule.
- TODO: the approach.



---

**27. [Best Time to Buy and Sell Stock III.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III.java)**      Level: Hard      Tags: [Array, DP, Sequence DP]
      
比stock II 多了一个限制：只有2次卖出机会.

#### DP加状态
- 只卖2次, 把买卖分割成5个状态模块.
- 在状态index 0, 2, 4: 没有持有股票. 1. 一直在此状态, max profit不变; 2. 刚卖掉, dp[i][前状态] + profit
- 在状态index 1, 3: 持有股票. 1. 一直在此状态, daily profit. 2. 刚刚买进, 状态改变, 但是没有profit yet: dp[i][前状态]

##### Partial profit
- 把每天的partial profit (diff)加在一起, 最终的overall profit是一样的. 唯一更好的是, 不需要记录中间买入的时间点.
- 什么时候会积累profit呢? 
- 1. 原本就持有股票的, 如果毫无动作, 那么状态不变, 积累profit diff. 
- 2. 卖出了股票, 状态改变, 积累profit diff.
- 注意: 只有在状态index: 0, 2, 4, 也就是卖掉股票的时候, 才可以积累profit

##### Rolling Array
- [i] 只有和 [i-1] 打交道, reduce space
- O(1) space, O(n) time

#### 找峰头
- 找峰头；然后往下再找一个峰头。
- 怎么样在才能Optimize两次巅峰呢？从两边同时开始找Max！（棒棒的想法）
- leftProfit是从左往右，每个i点上的最大Profit。
- rightProfit是从i点开始到结尾，每个点上的最大profit.
- 那么在i点上，就是leftProfit，和右边rightProfit的分割点。在i点，leftProfit+rightProfit相加，找最大值。
- 三个O(n),还是O(n)



---

**28. [Subsets II.java](https://github.com/awangdev/LintCode/blob/master/Java/Subsets%20II.java)**      Level: Medium      Tags: [Array, BFS, Backtracking, DFS]
      

给一串integers(may have duplicates), 找到所有可能的subset. result里面不能有重复.

#### DFS
- DFS, 找准需要pass along的几个数据结构. 先`sort input`, 然后DFS
- Using for loop approach: 每个dfs call是一种可能性，直接add into result.     
- 为了除去duplicated result, skip used item at current level: `if (i > depth && nums[i] == nums[i - 1]) continue;`
- sort O(nlogn), subset: O(2^n)
- space O(2^n), save results

#### BFS
- Regular BFS, 注意考虑如果让one level to generate next level
- skip duplicate: `if (i > endIndex && nums[i] == nums[i - 1]) continue;`
- 1. 用queue来存每一次的candidate indexes
- 2. 每一次打开一层candiates, add them all to result
- 3. 并且用每一轮的candidates, populate next level, back into queue.
- srot O(nlogn), subset: O(2^n)
- should be same O(2^n). slower than dfs

#### Previous notes:
- 在DFS种skip duplicate candidates, 基于sorted array的技巧：    
- 一旦for loop里面的i!=index，并且nums[i] == nums[i-1],
- 说明x=nums[i-1]已经在curr level 用过，不需要再用一次: [a,x1,x2]，x1==x2    
- i == index -> [a,x1]    
- i == index + 1 -> [a,x2]. 我们要skip这一种
- 如果需要[a,x1,x2]怎么办？ 其实这一种在index变化时，会在不同的两个dfs call 里面涉及到。

#### 注意
- 不能去用result.contains(), 这本身非常costly O(nlogn)
- 几遍是用 list.toString() 其实也是O(n) iteration, 其实也是增加了check的时间, 不建议




---

**29. [Container With Most Water.java](https://github.com/awangdev/LintCode/blob/master/Java/Container%20With%20Most%20Water.java)**      Level: Medium      Tags: [Array, Two Pointers]
      
#### Two Pointers
- 木桶理论。盛水的最高取决于最低的那面墙。
- 左右两墙，往中间跑动。
- 另:若一面墙已经小于另外一面，就要移动，换掉矮墙（可能下一面更高，或更低)
- 但决不能换掉当下的高墙，因为低墙已经limit的盛水的上限，若高墙移动，导致两墙之间距离减少，就注定水量更少了。（弄啥来，不能缺心眼啊）



---

**30. [Coins in a Line II.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20II.java)**      Level: Medium      Tags: [Array, DP, Game Theory, Memoization, MiniMax]
      
给一串coins, 用values[]表示; 每个coin有自己的value. 先手/后手博弈,
每次只能 按照从左到右的顺序, 拿1个或者2个棋子, 最后看谁拿的总值最大.

MiniMax的思考方法很神奇, 最后写出来的表达式很简单

#### DP, Game Theory 自考过程比较长
- 跟Coins in a line I 不一样: 每个coin的value不同.
- 用到MiniMax的思想, 这里其实是MaxiMin. Reference: http://www.cnblogs.com/grandyang/p/5864323.html
- Goal: 使得player拿到的coins value 最大化.
- 设定dp[i]: 从index i 到 index n的最大值. 所以dp[0]就是我们先手在[0 ~ n]的最大取值 
- 于此同时, 你的对手playerB也想最大化, 而你的选择又不得不被对手的选择所牵制.
- 用MaxiMin的思想, 我们假设一个当下的状态, 假想对手playerB会做什么反应(从对手角度, 如何让我输)
- 在劣势中(对手让我输的目标下)找到最大的coins value sum


##### 推算表达式
- Reference里面详细介绍了表达式如何推到出来, 简而言之:
- 如果我选了i, 那么对手就只能选(i+1), (i+2) 两个位置, 而我在对方掌控时的局面就是min(dp[i+2], dp[i+3])
- 如果我选了i和(i+1), 那么对手就只能选(i+2), (i+3) 两个位置, 而我在对方掌控时的局面就是min(dp[i+3], dp[i+4])
- 大家都是可选1个或者2个coins
- 目标是maximize上面两个最坏情况中的最好结果

##### 简化表达式
- 更加简化一点: 如果我是先手, dp[i]代表我的最大值.
- 取决于我拿了[i], 还是[i] + [i+1], 对手可能是dp[i + 1], 或者是dp[i+2]
- 其实dp[i] = Math.max(sum - dp[i + 1], sum - dp[i + 2]);
- 这里的sum[i] = [i ~ n] 的sum, 减去dp[i+1], 剩下就是dp[i]的值没错了

##### Initialization
- 这个做法是从最后往前推的, 注意initialize dp末尾的值.
- dp = new int[n + 1]; dp[n] = 0; // [n ~ n]啥也不选的时候, 为0.
- sum = new int[n + 1]; sum[n] = 0; // 啥也不选的时候, 自然等于0
- 然后记得initialize (n-1), (n-2)

##### 时间/空间
Time O(n)
Space O(n): dp[], sum[]



---

**31. [The Smallest Difference.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Smallest%20Difference.java)**      Level: Medium      Tags: [Array, Sort, Two Pointers]
      


---

**32. [Subarray Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20II.java)**      Level: Hard      Tags: [Array, Binary Search, Two Pointers]
      


---

**33. [Submatrix Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Submatrix%20Sum.java)**      Level: Medium      Tags: [Array, Hash Table, PreSum]
      
给一个int[][] matrix, 找一个sub matrix, where the sum == 0.

#### PreSum的思想
- 算出一个右下角点(i,j)到(0,0)的大小: 上一块 + 左一块 + curr node - overlap area
- preSum[i][j]: sum from (0,0) to (i-1,j-1)
- same approach as `subarray sum`: use hashmap to store diff->index; if diff re-appears, that means sum of 0 has occurred
- sequence of calculation: 1. iterate over start row. 2. iterate over end row. 3. iterate over col number (this is where hashmap is stored based on)
- the iteration over col is like a screening: find previous sum and determine result
- Note: 其实并没有真的去找 `== 0` 的解答,而是根据特性来判断 `剩下的/后来加上的一定是0`



---

**34. [Number of Airplane in the sky.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Airplane%20in%20the%20sky.java)**      Level: Medium      Tags: [Array, Interval, PriorityQueue, Sort, Sweep Line]
      
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

**35. [Find Minimum in Rotated Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Minimum%20in%20Rotated%20Sorted%20Array%20II.java)**      Level: Hard      Tags: [Array, Binary Search]
      
一个需要严谨思考的题目. 因为有duplicate, 会导致不断平移, 所以最终time complexity是O(n)
所以不如直接扫一遍, 给出答案.

但是还是写一个Binary Search的样子, 只不过worst结果是O(n)



---

**36. [Maximum Average Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20II.java)**      Level: Review      Tags: [Array, Binary Search, PreSum]
      
给int[] nums 和 window min size k. window size可以大于K. 找最大的连续数列average value.

- Binary Search的思想, 用在所要找的这个 average sum 上面. 大小是在[min, max]之中
- 找k的时候, 是>=k都可以, 巧用一个 min(preSum)的概念.
- 找k的时候, 画图, 可以看出来, 其实要的是 k window 里面的sum [x, i], 所以要用 sum[0, i] - sum[0, x]

需要仔细去读下面的notes.



---

**37. [My Calendar I.java](https://github.com/awangdev/LintCode/blob/master/Java/My%20Calendar%20I.java)**      Level: Medium      Tags: [Array, TreeMap]
      
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




---

**38. [Two Sum II - Input array is sorted.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Sum%20II%20-%20Input%20array%20is%20sorted.java)**      Level: Medium      Tags: [Array, Binary Search, Two Pointers]
      
升序array, 找2SUM.

#### Two pointers
- 排序好的array. Two pointer移动start和end，核查sum.
- 注意sum用long.
- O(n) time

#### Binary Search, 因为已经排好序了啊
- 定住一个valueA, 然后在剩下的里面 binary serach 找 (target - valueB)
- for loop O(n), binary search O(logn)
- overall time: O(nLogN), 就不写了



---

**39. [Coins in a Line III.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20III.java)**      Level: Hard      Tags: [Array, DP, Game Theory, Interval DP, Memoization]
      
LeetCode: Predict the Winner

还是2个人拿n个coin, coin可以有不同的value. 

只不过这次选手可以从任意的一头拿, 而不限制从一头拿. 算先手会不会赢?

#### Memoization + Search
- 跟Coins in a Line II 一样, MaxiMin的思想: 找到我的劣势中的最大值
- `dp[i][j] 代表在[i,j]区间上 选手最多能取的value 总和`
- 同样, sum[i][j]表示[i] 到 [j]间的value总和
- 对手的最差情况, 也就是先手的最好情况:
- dp[i][j] = sum[i][j] - Math.min(dp[i][j - 1], dp[i + 1][j]);
- 这里需要search, 画出tree可以看明白是如何根据取前后而分段的.

#### 博弈 + 区间DP, Interval DP
- 因为是看区间[i,j]的情况, 所以可以想到是区间 DP
- 这个方法需要复习, 跟数学表达式的推断相关联: S(x) = - S(y) + m. 参考下面的公式推导.
- dp[i][j]表示 从index(i) 到 index(j), 先手可以拿到的最大值与对手的数字差. 也就是S(x).
- 其中一个S(x) = dp[i][j] = a[i] - dp[i + 1][j]
- m 取在开头, m 取在末尾的两种情况:
- dp[i][j] = max{a[i] - dp[i + 1][j], a[j] - dp[i][j - 1]}
- len = 1, 积分就是values[i]
- 最后判断 dp[0][n] >= 0, 最大数字和之差大于0, 就赢.
- 时间/空间 O(n^2)

##### 公式推导
- S(x) = X - Y, 找最大数字和之差, 这里X和Y是选手X的总分, 选手Y的总分. 
- 对于选手X而言: 如果S(x)最大值大于0, 就是赢了; 如果最大值都小于0, 就一定是输了. 
- 选手Y: S(y)来表示 对于Y,  最大数字和之差. S(y) = Y - X
- 根据S(x) 来看, 如果从 数字和X里面, 拿出一个数字 m, 也就是 X = m + Xwithout(m)
- S(x) = m + Xwithout(m) - Y = m + (Xwithout(m) - Y). 
- 如果我们从全局里面索性去掉m, 那么 S(y'') = Y - Xwithout(m)
- 那么推算下来: S(x) = m + (Xwithout(m) - Y) = m - (Y - Xwithout(m)) = m - S(y'')
- 在这个问题里面, 我们model X 和 Y的时候, 其实都是 dp[i][j], 而区别在于先手/后手.
- 将公式套用, 某一个S(x) = a[i] - dp[i + 1][j],  也就是m=a[i], 而 S(y'') = dp[i + 1][j]

##### 注意
- 如果考虑计算先手[i, j]之间的最大值, 然后可能还需要两个数组, 最后用于比较先手和opponent的得分大小 => 那么就要多开维.
- 我们这里考虑的数字差, 刚好让人不需要计算先手的得分总值, 非常巧妙.
- Trick: 利用差值公式, 推导有点难想到.

##### 区间型动态规划
- 找出[i, j]区间内的性质: dp[i][j]下标表示区间范围 [i, j]
- 子问题: 砍头, 砍尾, 砍头砍尾
- loop应该基于区间的length
- template: 考虑len = 1, len = 2; 设定i的时候一定是 i <= n - len; 设定j的时候, j = len + i - 1;




---

**40. [Partition Array by Odd and Even.java](https://github.com/awangdev/LintCode/blob/master/Java/Partition%20Array%20by%20Odd%20and%20Even.java)**      Level: Easy      Tags: [Array, Two Pointers]
      
- 更正常的start/end partition pointer类似: when condition meet, swap
- Clean up TODO



---

**41. [Combination Sum III.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20III.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      
给一个integer k, 和一个target n. 

从positive数字[1 ~ 9], 找到所有unique的 组合(combination) int[], size = k, 要求每个combination的和 = n.

(隐藏条件, 需要clarify): 同一个candidate integer [1 ~ 9], 只可以用一次.

#### DFS, Backtracking
- 跟Combination Sum I, II 没什么太大区别, 只不过, 一定要用k个数字, 也就是一个for loop里面的特别条件
- 考虑input: 没有重复数字 [1 ~ 9]
- 考虑candidate重复利用: 不可以重复利用, next level dfs 时候, curr index + 1
- the result is trivial, save success list into result.

##### Time Complexity
- Which one?
- worst case: tried all numbers and cannot find: O(m!), m = 9, all possible integers in [1~9]
- C(n,k), n choose k problem : `n! / (k! * (n-k)!)` => ends up being `O(min(n^k, n^(n-k)))`



---

**42. [Best Time to Buy and Sell Stock with Transaction Fee.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Transaction%20Fee.java)**      Level: Medium      Tags: [Array, DP, Greedy, Sequence DP, Status DP]
      

跟Stock II 一样, 买卖无限, 需先买在卖. 附加条件: 每个sell transaction要加一笔fee.

#### Sequence DP
- 与StockII一样, dp[i]: represents 前i天的最大profit.
- sell 的时候, 才完成了一次transaction, 需要扣fee; 而买入不扣fee.
- model sell on dp[i] day (which depends on dp[i-1]) and each day can be sell/buy => add status to dp[i][status]
- status[0] buy on this day, status[1] sell on this day
- dp[i][0] = Math.max(dp[i-1][0], dp[i - 1][0] - prices[i]);
- dp[i][1] = Math.max(dp[i-1][1], dp[i - 1][1] + prices[i] - fee);
- init: dp[0][0,1] = 0; dp[1][1] = 0; dp[1][0] = - prices;
- return dp[n][1]



---

**43. [Maximum Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray%20II.java)**      Level: Medium      Tags: [Array, DP, Greedy, PreSum, Sequence DP, Subarray]
      
给一串数组, 找数组中间 两个不交互的 subarray 数字之和的最大值

#### DP
- 考虑两个方向的dp[i]: 包括i在内的subarray max sum. 
- dp[i] 的特点是: 如果上一个 dp[i - 1] + nums[i - 1] 小于 nums[i-1], 那么就舍弃之前, 从头再来:
- dp[i] = Math.max(dp[i - 1] + nums.get(i - 1), nums.get(i - 1));
- 缺点: 无法track全局max, 需要记录max.
- 因为我们现在要考虑从左边/右边来的所有max, 所以要记录maxLeft[] 和 maxRight[] 
- maxLeft[i]: 前i个元素的最大sum是多少 (不断递增); maxRight反之, 从右边向左边
- 最后比较maxLeft[i] + maxRight[i] 最大值
- Space, Time O(n)
- Rolling array, reduce some space, but can not reduce maxLeft/maxRight

#### preSum, minPreSum
- preSum是[0, i] 每个数字一次加起来的值
- 如果维持一个minPreSum, 就是记录[0, i]sum的最小值(因为有可能有负数)
- preSum - minPreSum 就是在 [0, i]里, subarray的最大sum值
- 把这个最大subarray sum 记录在array, left[] 里面
- right[] 是一样的道理
- enumerate一下元素的排列顺位, 最后 max = Math.max(max, left[i] + right[i + 1])



---

**44. [Sort Colors.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Colors.java)**      Level: Medium      Tags: [Array, Partition, Quick Sort, Sort, Two Pointers]
      
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

**45. [Word Ladder II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Ladder%20II.java)**      Level: Hard      Tags: [Array, BFS, Backtracking, DFS, Hash Table, String]
      
给一串string, start word, end word. 找到所有从 startWord -> endWord的最短路径list. 

变化方式: mutate 1 letter at a time.

#### BFS + Reverse Search
- 用BFS找最短路径.
- 问题: how to effectively store the path, if the number of paths are really large? 
- If we store Queue<List<String candidates>>: all possibilities will very large and not maintainable
- 用BFS做出一个反向structure, 然后再reverse search

##### BFS Prep Step
- BFS 找到所有start string 可以走到的地方 s, 放在一个overall structure里面: 注意, 存的方式 Map<s, list of sources>
- BFS时候每次都变化1step, 所以记录一次distance, 其实就是最短路径candidate (止步于此)
- 1. 反向mutation map: `destination/end string -> all source candidates` using queue: `Mutation Map`
- Mutation Map<s, List<possible src>>: list possible source strings to mutate into target key string.
- 2. 反向distance map: `destination/end string -> shortest distance to reach dest`
- Distance Map<s, possible/shortest distance>: shortest distance from to mutate into target key string.
- BFS prep step 并没解决问题, 甚至都没有用到end string. 我们要用BFS建成的反向mapping structure, 做search

##### Search using DFS
- 从结尾end string 开始扫, 找所有可以reach的candidate && only visit candidate that is 1 step away
- dfs 直到找到start string.

##### Bi-directional BFS: Search using BFS
- reversed structure 已经做好了, 现在做search 就可以: 也可以选用bfs.
- `Queue<List<String>>` to store candidates, searching from end-> start



---

**46. [Spiral Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Spiral%20Matrix.java)**      Level: Medium      Tags: [Array, Enumeration]
      
从(0,0)坐标, 走完spiral matrix, 把结果存在list里.

#### DX, DY
- Basic implementation, array, enumeration
- 写一下position前进的方向: RIGHT->DOWN->LEFT->UP
- 用一个direction status 确定方向
- 写一个compute direction function 改变方向 `(direction + 1) % 4`
- `boolean[][] visited` 来track走过的地方



---

**47. [The Spiral Matrix II.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Spiral%20Matrix%20II.java)**      Level: Medium      Tags: [Array]
      
#### Move forward till end
- Similar concept as `The Maze`: keep walking until hit wall, turn back
- fix direction `dx[direction % 4]`



---

**48. [Partition Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Partition%20Array.java)**      Level: Medium      Tags: [Array, Quick Sort, Sort, Two Pointers]
      
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

**49. [Max Sum of Rectangle No Larger Than K.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Sum%20of%20Rectangle%20No%20Larger%20Than%20K.java)**      Level: Hard      Tags: [Array, BST, Binary Search, DP, Queue, TreeSet]
      
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

**50. [Search for a Range.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20for%20a%20Range.java)**      Level: Medium      Tags: [Array, Binary Search]
      
给sorted array, 有重复数字, 找跟target重合所在的range.

#### Binary Search
- 2个while loop
- 找first/last occurance
- TODO: Can the code be simplified?




---

**51. [Search a 2D Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20a%202D%20Matrix.java)**      Level: Medium      Tags: [Array, Binary Search]
      
给2D matrix, 每行sorted, 每行的首位都大于上一行的末尾. goal: find target from matrix

#### 2D matrix 转1D array
- 一行一行是从小到大, sorted, 连续的, 可以看做1D sorted array
- Binary Search



---

**52. [Largest Rectangle in Histogram.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Rectangle%20in%20Histogram.java)**      Level: Hard      Tags: [Array, Monotonous Stack, Stack]
      
给n个bar,组成柱状图histogram. 求在这一排柱状图里面可以找到的面积最大的长方形.

思考: 找长方形面积, 无非是找两个index, 然后底边长度 * height.

#### Monotonous Stack
- 重点是根据找Histogram里面rectangle的性质, 维持一个单调递增的Stack
- 在loop over indexes的时候:
- 如果高度>= previous peek(), 那么对于那个peek, 就意味着, 往下走, 一直走高嘛, 之前的peek总可以继续抄底
- 什么时候不能抄底了呢? 就是有一个下降趋势的时候
- 这时候并不是calculate所有前面的peek, 而是考虑 大于 current height的之前所有的peek.
- 把这些peek到 current height 前一格的rectangle全部找出来: stack.pop()
- 这个stack.pop()的过程里面, 其实没有算上 current height, 因为需要留到下一轮, 把current index加进stack 再说
- 为什么用stack? 因为需要知道连续递增的peek, stack.peek() O(1), 好用
  而其实不用stack, 也可以用其他方式记录所有height, 只不过要 O(n)去找peek不方便

#### 知识点
- 理解monotonous stack 是如何被维护的
- 维护monotonous stack 是题目需要, 而不是stack本身性质, 是一种借助 stack.peek() O(1)的巧妙用法.




---

**53. [[lint]. Product of Array Exclude Itself.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Product%20of%20Array%20Exclude%20Itself.java)**      Level: Medium      Tags: [Array, Lint]
      



---

**54. [[lint]. Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Anagrams.java)**      Level: Medium      Tags: [Array, Hash Table, Lint]
      

把anagram找到并output

#### HashMap
- 存在int[26], Arrays.toString(arr) 就是 string key: character frequency map
- anagram都有一样的key, 存进hashmap<string, list of anagrams>
- output anagrams

#### HashMap + Sort
- HashMap 的做法. sort每个string, 存进HashMap, 重复的就是anagrams,最后输出。   
- toCharArray
- Arrays.sort
- Stirng.valueOf(char[])
- 时间n*L*O(logL),L是最长string的长度。

#### Previous Notes
- Arrays.toString(arr)的做法。arr是int[26], assuming only have 26 lowercase letters.    
- Count occurrance, 然后convert to String，作为map的key.
- Time complexity: nO(L)
- 另一种做法：http://www.jiuzhang.com/solutions/anagrams/   
- 1. take each string, count the occurrance of the 26 letters. save in int[]count.   
- 2. hash the int[] count and output a unique hash value; hash = hash * a + num; a = a * b.   
- 3. save to hashmap in the same way as we do. 
- 这一步把for s: strs 里面的时间复杂度降到了O(L). L = s.length().   
- Need to work on the getHash() function.
- 时间变成n*O(L). Better.




---

**55. [[lint]. 3 Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%203%20Sum%20Closest.java)**      Level: Medium      Tags: [Array, Lint, Two Pointers]
      
3Sum 的一种简单形式, 并且都没有找index, value, 而只是找个sum罢了.

double for loop。 2Sum只能用土办法 left/right 2 pointers。 O(n^2)

注意：check closest时候用long, 以免int不够用



---

**56. [[lint]. Unique Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Unique%20Characters.java)**      Level: Easy      Tags: [Array, Lint, String]
      
determine if characters are unique in string

#### HashSet
- space O(n), time O(n)

#### char[]
- space O(n), time O(nlogn)

#### no additional data structure
- double for loop:  O(n^2)




---

**57. [[lint]. Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Subarray%20Sum.java)**      Level: Easy      Tags: [Array, Hash Table, Lint, PreSum, Subarray]
      

给一串数字, 找其中的一个subarray的 [start, end] index, 条件: subarary sum == 0.

#### Hash Table
- `subarray sum equals k` 的简单版: k = 0
    - 求preSum, 然后不断check `map.containsKey(preSum - k)`. 
    - 如果 `priorSum = preSum - k == 0`, 说明 [priorSum.index + 1, curr index] 就是我们要找的这一段

#### Previous notes, same preSum + map solution
- 分析出，如果sum[0~a]=x, 然后sum[0~b]=x, 说明sum[a+1 ~ b] == 0
- 用hashMap存每个sum[0~i]的值和index i. 如果有重复，就找到了一组sum为0的数组.



---

**58. [[lint]. Recover Rotated Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Recover%20Rotated%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Lint]
      
rotate的意思，是有个点断开，把一边的array节选出来放在另外一边。
Rotate三步：
rotate前半
rotate后半
rotate全部

注意先找到断点。


---

**59. [[lint]. 2 Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%202%20Sum%20II.java)**      Level: Medium      Tags: [Array, Binary Search, Lint, Two Pointers]
      
与 2sum II - input array is sorted类似. 都是sort array, 然后two pointer.

LintCode的题. 注意找的是greater/bigger than target。

由于给定条件允许O(nLogn):   
   sort
   two pointer

while里面two pointer移动。每次如果num[left]+num[right] > target，那么其中所有num[left++]的加上num[right]都>target.   
也就是,num[right]不动，计算加入挪动left能有多少组，那就是: right-left这么多。 全部加到count上去。     
然后right--.换个right去和前面的left部分作比较。



---

**60. [42. Trapping Rain Water.java](https://github.com/awangdev/LintCode/blob/master/Java/42.%20Trapping%20Rain%20Water.java)**      Level: Hard      Tags: [Array, Stack, Two Pointers]
      

这道题目的方法比较多.

#### Method1: Max wall from both sides
- Array: Left Max Wall vs Right Max Wall.
- 对于每个index而言, vertically 能存放的最大水柱, 就是靠 左 右 最高墙决定的: 
    - min(leftHighestWall, rightHighestWall) - currHeight.
- time: O(n)
- space: O(n)

#### Method2: Two Pointers
- Optimization from Method1: two pointer, 还是找左边最高和右边最高. O(1) space.
- 利用到了方法3里面的想法一样: 整个structure是被中间的最高bar 二分天下:
- always limited by the shorter wall: 左边按照maxLeft来计算, 右边按照maxRight来计算.
- time: O(n)
- space: O(1)

#### Method3: 2 Pointers, start from 2 sides
- 1. 找中间最高bar的index    
- 2. 两面往中心扫：每次加上（topBarIndex - currIndex）* (elevation from previous index).也就是每次加一个横条
- 3. 每次还要减去block自身的height
- time: O(n)
- space: O(1)

#### Method4: Stack
- 主要想法和方法3一致: 在山坡下坡的基础上, 一直用stack堆积bottom. 
- 最后遇到上升之前, 此时bottom可以用来跟stack之前堆积的所有下坡index做比较, 算跟他们高度相差的积水.
- 用了stack记录下坡, 然后用个while loop一挖到底的想法非常棒.
- time: O(n)
- space: O(n)




---

**61. [448. Find All Numbers Disappeared in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/448.%20Find%20All%20Numbers%20Disappeared%20in%20an%20Array.java)**      Level: Easy      Tags: [Array, Bucket Sort]
      

#### Method1: Bucket Sort concept, set val to its correct position
- Given: values are [1,n], so val can represent index. Therefore, set val to its correct position
- 小心handle i:
    - value是 1-based
    - 每次换位, 需要`i--`, 重新省察`nums[i]`

#### Method2: 做标记 (negative number, or super large number)
- Option1: use negative number to mark visited:
    - 很巧妙地运用了标记的方法, 标记成负数，证明visit过。
    - Preserve原数的负数，这样可以继续用此负数的绝对值来寻找原数所该被定的位置。非常巧妙！
- Option2: use large number (larger than n)
    - 跟方法2类似，也是做标记，这一次是加上一个大于n的数（因为题目给了n的border），最后check一下就好。为不超Integer.MAX_VALUE, 每次加n前，取余数。
    - 做标记的方法固然快，但是相对来说比较hacky，在常规的代码中，估计不会用到.




---

**62. [849. Maximize Distance to Closest Person.java](https://github.com/awangdev/LintCode/blob/master/Java/849.%20Maximize%20Distance%20to%20Closest%20Person.java)**      Level: Easy      Tags: [Array, Basic Implementation, Two Pointers]
      

给一排座位, 一个人去坐: 找离两边的人都最远的地方(中间点), return 跟旁边人的最大distance

是Exam Room 的同种概念, 简单化题目: 这里只考虑一个人就好了

#### Basic Implementation, Two Pointers: start/end
- start/end point, 然后比较大小记录dist
    - 注意1: 如果第一个座位没有人, 特殊处理, dist = [0 ~ end]
    - 注意2: 如果最后一个座位没有人, 特殊处理: dist = [n - 1 - start];
- 其余: `dist = Math.max(dist, (end - start) / 2)`
- 相关题目: 几乎同样概念 `Binary Gap`, 升级复杂版`Exam Room`




---

**63. [766. Toeplitz Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/766.%20Toeplitz%20Matrix.java)**      Level: Easy      Tags: [Array]
      

#### Check diagonal
- 似乎没什么算法特点, 就是array基本运算, 然后分割成一个helper function去做重复计算, 剪短代码.
- 注意check MxN 的分界线.

#### Simpler Solution
- the goal is to check [i][j] == [i+1][j+1] for every i and j.



---

**64. [1053. Previous Permutation With One Swap.java](https://github.com/awangdev/LintCode/blob/master/Java/1053.%20Previous%20Permutation%20With%20One%20Swap.java)**      Level: Medium      Tags: [Array, Greedy, Permutation]
      

#### Analyze Permutation behavior
- concept similar to `31. Next Permutation`
- 1) first pass: find the one that is in incorrect order
- 2) second pass: find the right spot to swap



---

**65. [56. Merge Intervals.java](https://github.com/awangdev/LintCode/blob/master/Java/56.%20Merge%20Intervals.java)**      Level: Medium      Tags: [Array, PriorityQueue, Sort, Sweep Line]
      


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

**66. [665. Non-decreasing Array.java](https://github.com/awangdev/LintCode/blob/master/Java/665.%20Non-decreasing%20Array.java)**      Level: Easy      Tags: [Array]
      

- 比较升序的时候, 必须要估计到 `i-1, i, i+1` 三个数位.
- 写出来`i-1, i, i+1`之间的关系, 然后做合理的fix.
    1. reduce nums[i+1] to fix
    1. raise nums[i+1] to fix
- 需要真的fix数组, 因为loop through做比较时会用到fix后的数字.




---

**67. [244. Shortest Word Distance II.java](https://github.com/awangdev/LintCode/blob/master/Java/244.%20Shortest%20Word%20Distance%20II.java)**      Level: Medium      Tags: [Array, Design, Hash Table, Two Pointers]
      

#### Map
- Prep: 存Map<word, index list>
- Process: 相继从两个 index list 里面拿出 p1,p2
    - 根据index的大小, 移动双指针: try to move the pointers closer; always calculate diff
- Optionally: if one list is much larger, do binary search on the larger list



---

**68. [80.Remove Duplicates from Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/80.Remove%20Duplicates%20from%20Sorted%20Array%20II.java)**      Level: Medium      Tags: [Array, Two Pointers]
      
给一个sorted array, 把重复的去掉: 也就是把不重复的按照顺序贴上来, array末尾多余的位置无所谓.

最多可重复出元素的数量不超过2个. return unique item 的长度.

#### Basic 
- sorted array, 重复元素都在一起
- 跟 `Remove Duplicates from Sorted Array` 几乎一模一样, 只不过unique index现在可以 validate 2 位
- 其余一模一样, use index to track unique item; skip if duplicated for more than 2 times
- O(n) time, O(1) space
- 这里也可以真的用2个pointers 写while loop, 但是没有必要, 只是单纯地走一个for loop其实就足够.

#### Follow up: k duplicates, Two Pointers
- when index i and i-1 are diff, use count=1 to start
- in while loop, keep count++ until count==k
- reset when next diff comes in



---

**69. [674. Longest Continuous Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/674.%20Longest%20Continuous%20Increasing%20Subsequence.java)**      Level: Easy      Tags: [Array, Coordinate DP, DP, Sliding Window]
      

找连续的持续上升子序列的长度.

#### Sliding window
- update the window start index;
    - `left` in sliding window
    - update when we need to start a new range: `nums[i-1] >= nums[i]` 
- calculate the max distance `i - widowStart + 1`
- O(n) time and O(1) space

#### Simple Array solution
- size++ when meeting condition `nums[i] > nums[i - 1]`
- otherwise, reset size = 1
- track max all the way

#### Coordinate DP
- 1D coordinate, dp 的角标, 就是代表 index i 的状态
- 求最值, dp[i] = 在index i位置的最长子序列
    - 如果 nums[i] > nums[i - 1], dp[i] = dp[i - 1] + 1
    - 如果没有持续上升, 那么dp[i] = 1, 重头来过
- maintain max




---

**70. [1007. Minimum Domino Rotations For Equal Row.java](https://github.com/awangdev/LintCode/blob/master/Java/1007.%20Minimum%20Domino%20Rotations%20For%20Equal%20Row.java)**      Level: Medium      Tags: [Array, Greedy]
      


#### Method1: Count all occurrance, and count on overlap indexes
- when there is a value that can cover entire row of size n
    - it must be: `n = countA[i] + countB[i] - overlap[i]`
- Code easy to write and read
- time: O(n)
- space: O(1)

#### Method2: Negative count
- Observation: if A[0] works, no need to check B[0].
- Because if both A[0] and B[0] exist in all dominoes,
    - when you swap A[0] in a whole row,
    - you will swap B[0] in a whole at the same time.
    - The result of trying A[0] and B[0] will be the same.
- time: O(n)
- space: O(1)

#### Method3: positive count Match
- there should exist 1 numbers, that can appear in (A[i], B[i]).
- failure case: there exist at least 1 index, that does not have the common number
- maximum case: there can be 2 numbers, that both will make it work.
- findCommon2, and count them:
    - set.add(A[0], A[B]),
    - if any new one does not exist in set, remove it from set
        - if set is empty() , return -1
- use the 2 numbers from set to do a sweep and count in A, O(n), return the less appearance one.
- time: O(n)
- space: O(1)



---

**71. [485. Max Consecutive Ones.java](https://github.com/awangdev/LintCode/blob/master/Java/485.%20Max%20Consecutive%20Ones.java)**      Level: Easy      Tags: [Array, Basic Implementation]
      

- preserve max
- 清零count 



---

**72. [896. Monotonic Array.java](https://github.com/awangdev/LintCode/blob/master/Java/896.%20Monotonic%20Array.java)**      Level: Easy      Tags: [Array]
      
basic implementation



---

**73. [26.Remove Duplicates from Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/26.Remove%20Duplicates%20from%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Two Pointers]
      
给一个sorted array, 把重复的去掉: 也就是把不重复的按照顺序贴上来, array末尾多余的位置无所谓.

return unique item 的长度.

#### Two Pointers
- sorted array, 重复元素都在一起
- Two pointers 其实也可以是一个 for loop pointer, 另一个 dynamic variable.
- track unique index
- skip duplicated items
- O(n)

#### 思考模式:
- Remove Duplicate from Array 不同于remove from linked list.
- LinkedList里面我们是最好不要动node.val的，直接把node去掉。
- 而array我们很难直接把node去掉，又不能用新array，那么就要：
- 把不重复的element一个个放到最前面。
- 这个思想跟merge two sorted array （其中一个后续非常长的array可以放下arr1,arr2） 类似。
- 就是找个不会事后mess up，不会去动得index,把满足条件的element 填进去。这样保证了in place.
- *反向思维*：remove duplicate, 实际上也是找unique elements, and insert into original array



---

**74. [41. First Missing Positive.java](https://github.com/awangdev/LintCode/blob/master/Java/41.%20First%20Missing%20Positive.java)**      Level: Hard      Tags: [Analysis, Array, Edge Case]
      

给一串无序数字, 有负数: 找这个array里面第一个 missing的 positive integer

missing positive integer 其实是以 [1, n] 来做比较的.

#### Array分析, index 技巧
- 用while loop, 不断地尝试把 number 送到该放的地方
- 如果 index = nums[i] 超过了nums.length, 当然就不移动了
- 注意: 检查 val != nums[val], avoid infinitely loop
- 检验: nums[i] 是否等于 i, 如果不对, 就找到了结果

#### Edge Case
1. 如果nums==null, 其实missing positive integer 自然而然是 1
1. 有可能这串数字里没有断开的integer, 但是最大的integer在首位 (因为index超标, 无法被放到正确的地方)
    - 这种时候, n被放在 index 0, 其实就是说, 下一个integer应该是 n + 1
1. 最终, 如果array本来就是完全sorted, 也不缺, 还符合角标的条件, 那么唯一下一个就是array范围外的第一个positive number: n



---

**75. [717. 1-bit and 2-bit Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/717.%201-bit%20and%202-bit%20Characters.java)**      Level: Easy      Tags: [Array]
      
理解题目: 
1. single-bit always starts with '0', two-bits always start with '1'.
1. Therefore there is ONLY 1 way to reach end.

#### 方法1
Greedy.
从第一个bit开始: 如果 % 2 == 1, 一定是跳两位; 如果0, 一定是跳一位.
loop to end, and see if index reaches the end.

#### 方法2
用DP硬做了一下: 
1. 如果i位是0, 那么前面dp[i-1]或者dp[i-2] true就够了.
2. 如果i位是1, 那么i-1位必须是1才满足规则, 并且 dp[i-2]需要true.



---

**76. [53. Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/53.%20Maximum%20Subarray.java)**      Level: Easy      Tags: [Array, DFS, DP, Divide and Conquer, PreSum, Sequence DP, Subarray]
      

给一串数组, unsorted, can have negative/positive num. 找数组中间 subarray 数字之和的最大值

#### PreSum
- 想着用一用prefix sum. 把值一个个叠加
- 然后presum[j] - presum[i- 1] 就是 (i,j)之间的和
- O(n^2), not as sufficient


#### Sequence DP
- dp[i]: last element(或包括前i个element), 可能组成的 subarray 的最大sum.
    - dp[i] = Math.max(dp[i-1]+lastElement, lastElement(drop dp[i-1]))
- init: 
    - dp = int[n + 1], 
    - dp[0]: first 0 items, does not have any sum
- 因为continous sequence, 所以不满足条件的时候, 会断. 
    - need to take curr num regardless => can drop prev max in dp[i]
- track overall max 
- init dp[0] = 0; max = MIN_VALUE 因为有负数
- Time, space O(n)
- Rolling array, space O(1)

#### Divide and Conquer, DFS
- 找一个mid piont, 考虑3种情况: 1) 只要左边, 2) 只要右边, 3) cross-mid
- left/rigth case: 直接 dfs
- corss-mid case: continuous sum max from left + continous sum max from right + mid
- continuous sum max from one direction:
- Worst case O(n^2): visit all nodes O(n); in dfs: calculates continuous sum (including mid), which is also O(n)


---

**77. [152. Maximum Product Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/152.%20Maximum%20Product%20Subarray.java)**      Level: Medium      Tags: [Array, DP, PreProduct, Subarray]
      

从一组数列(正负都有)里面找一串连续的子序列, 而达到乘积product最大值.

#### Method1: DP, Two PreProduct array
- Continuous product can be positive/negative/zero
    - If nums[i] > 0, want prior largest product[i-1] * nums[i]
    - If nums[i] < 0, want prior smallest product[i-1] * nums[i]
    - If nums[i] == 0, product = 0
- `prior product[i-1]: 想到DP
    - 1. 正负数情况, 需要用两个 `PreProduct` array: minProduct[], maxProduct[]
    - 2. continuous prodct: it has to utilize curr nums[i]
        - 是跟nums[x]当下值比较的, 如果当下值更适合, 会舍去之前的continous product, 然后重新开始.
        - Use a global variable to hold overall result.
- Time/Space O (n)
- Space optimization, rolling array
    - maxProduct && minProduct 里面的 index i, 都只能 i - 1相关, 所以可以省去redundant operatoins
    - Time: O(n)
    - space: O(1)

#### Method2: hold `local max at index i` and `local min at index i`
- same concept as method1, but simplified: given that we always have to use nums[i], so only 1 result can be passed on
- FAST, simple to write and read
- time: O(n)
- space: O(1)

#### Failed attempt: `memo[i][j]` of continuous product from index i -> j
- working solution, BUT Time/Space complexity O(n^2) are too much



---

**78. [259. 3Sum Smaller.java](https://github.com/awangdev/LintCode/blob/master/Java/259.%203Sum%20Smaller.java)**      Level: Medium      Tags: [Array, Sort, Two Pointers]
      
1. Similar to 15. 3Sum, but simpler.
1. 只需要count triplet, 但是不需要save triplet, 而且还不需要handle duplicated triplets
1. 发现start, end满足条件时候，(end - start)就是所有 sum <target的情况了。
1. 而一旦 > target, 那么就end--
1. 两层循环, O(n2)



---

**79. [977. Squares of a Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/977.%20Squares%20of%20a%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Two Pointers]
      

#### Two Pointers
- negative index i, positive index j



---

**80. [31. Next Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/31.%20Next%20Permutation.java)**      Level: Medium      Tags: [Array, Permutation]
      

#### Permutation Behavior
- Great write up: https://leetcode.com/problems/next-permutation/solution/
- next lexicographically permutation: `smallest` but `larger than curr` permutation:
     - find first low point from right [low]
     - find the slight larger [high] to swap with [low]
     - make sure right side of low is eventually the smallest
- Analyze the use cases, to find next low permutation, 2 major steps:
    - 1) Find `first low/drop candidate` from right
    - 2) Find `first high where nums[high] > nums[low]` from right
    - 3) swap(low, high). 
        - By now, [low, n-1] forms a greater permutation
        - but it is not the smallest, because right side [low + 1, n - 1] is descending
    - 4) reverse(low + 1, n-1) to create ascending slopt on right of low (smallest next lexicographically permutation)
- Corner case: if input array is decending (1st low not found), reverse it all together O(n)
- time: O(n) visit all indexes
- space: O(1) not using additional
- Similar question: `1053. Previous Permutation With One Swap`




---

**81. [238. Product of Array Except Self.java](https://github.com/awangdev/LintCode/blob/master/Java/238.%20Product%20of%20Array%20Except%20Self.java)**      Level: Medium      Tags: [Array, PreProduct]
      

给一串数字, output rst[n], 每个index是 除了nums[i]以外 所有itemd的乘积.

#### Array, PreProduct
- 分析普通做法, 了结到用从左到右一遍O(n), 从右到左一遍 O(n) 就可以
- 注意carry的维护
- 第一轮:PreProduct (跟preSum的感觉有点像)
    - PreProduct[i] stores product from num[0] -> num[i-1] (skipping current num[i])
    - init preProduct[i] = 1, as base for product
    - 错过一位操作: always `preProduct[i] *= carry;` and `carry *= nums[i]`
- 第二轮: 从右边乘起, 每次在index i, 收到的carry都是 `nums[i+1] *....* nums[end]`
    - 第一轮的结果 * 第二轮的结果, 刚好在index i 缺少掉 nums[i]. 如题所愿.
- Time: O(n)



---

**82. [62. Unique Path.java](https://github.com/awangdev/LintCode/blob/master/Java/62.%20Unique%20Path.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP]
      

2D array, 算走到最右下角，有多少种方式.

#### DP, 加法原理
- 计数DP: 2 ways to reach (i,j): dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    - non-overlapping: `dp[i - 1][j]`, `dp[i][j - 1]`
    - covers the only 2 possible way to reach (i,j)
- initialization: dp[i][0] = 1, dp[0][i] = 1
    - Of course, row i = 0, or col j = 0, there is only 1 way to reach
- time O(mn), space O(mn)

##### 滚动数组 Rolling Array
- [i] 只跟 [i - 1] 有关系, 用 curr/prev 建立滚动数组.
- space O(n) 优化空间

#### DFS + Memoization
- move from (0,0) towards (m, n)
- use Map<coordinate, steps> as memoization technique



---

**83. [15. 3Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/15.%203Sum.java)**      Level: Medium      Tags: [Array, Sort, Two Pointers]
      
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

**84. [55. Jump Game.java](https://github.com/awangdev/LintCode/blob/master/Java/55.%20Jump%20Game.java)**      Level: Medium      Tags: [Array, DP, Greedy]
      

给出步数，看能不能jump to end.

#### Greedy
- start from index = 0
    - Keep track of farest can go
    - 一旦 farest >= nums.length - 1, 也就是到了头, 就可以停止, return true.
    - 一旦 farest <= i, 也就是说, 在i点上, 已经走过了步数, 不能再往前跳, 于是 return false
- start from index = n - 1
    - greedy: start from end, and mark last index
    - loop from i = [n - 2 -> 0], where i + nums[i] should always >= last index
    - check if last == 0 when returning. It means: can we jump from index=0 to the end?
- time: O(n)
- space: O(1)

#### DP
- DP[i]: 在i点记录，i点之前的步数是否可以走到i点？ True of false.
- 其实j in [0~i)中间只需要一个能到达i 就好了
- Function: DP[i] = DP[j] && (j + A[j] >= i), for all j in [0 ~ i)
- Return: DP[dp.length - 1];
- time: O(n^2)
- space: O(n)




---

**85. [189. Rotate Array.java](https://github.com/awangdev/LintCode/blob/master/Java/189.%20Rotate%20Array.java)**      Level: Easy      Tags: [Array, Rotation]
      
#### Rotate array in place
- rotate all
- rotate 2 sides: < k or >= 


#### Rotate by buffer the k array



---

**86. [119. Pascal's Triangle II.java](https://github.com/awangdev/LintCode/blob/master/Java/119.%20Pascal's%20Triangle%20II.java)**      Level: Easy      Tags: [Array, Basic Implementation]
      

简单处理 list. code is very similar to Pascal triangle I.

- 注意 `list = Arrays.asList(x, y, z ...)` 给fixed-size list, 不能直接 list.add().
- Use `new ArrayList<>(Arrays.asList(...))` to wrap it up.




---

**87. [277. Find the Celebrity.java](https://github.com/awangdev/LintCode/blob/master/Java/277.%20Find%20the%20Celebrity.java)**      Level: Medium      Tags: [Adjacency Matrix, Array, Graph, Greedy, Pruning]
      

有n个人, 其中有个人是celebrity, 注意必要条件 `Celeb knows nobody; Everyone else knows the celeb`. 找到celeb

Note: the relationship graph can be presented as an adjacency matrix, but graph is not directly used in this problem.

#### Pruning
- Given assumption: 1) `only 1 celebrity`, 2) person k, who knows nobody ahead of him or after him.
- if first pass finds candidate, `person k`, it means:
    - person [0, k-1] are not celebrity: they know a previous or current candidate
    - person k knows no one between [k + 1,  n): k+1 to n-1 can not be the celebrity either. 
    - person k is just the last standing possible celebrity
- second pass validation: we do not know if `knows(celeb, [0~k-1] )`. Do a final O(n) check
- time:O(n), space O(1)
- DO NOT: Brutle compare all -> all: O(n^2) handshakes.

##### 思考逻辑
- 先写出来[0 ~ n - 1], 最简单的方式 O(n^2) 检查, 记录每个人的状态.
    - 逐渐发现, 因为 celeb 谁都不会认识, 那么当任何candidate knows anyone, 他自身就不是celeb.
    - 我们可以greedy地, 一旦fail一个, 就立刻假设下一个是celeb candidate
- 最终还是要检查一遍, 避免错漏.
- 想一下happy case: 如果 celeb=0,  那么 know(celeb, i) 永远都是false, 然后 celeb一直保持0, 坚持到verify所有人.



---

**88. [245. Shortest Word Distance III.java](https://github.com/awangdev/LintCode/blob/master/Java/245.%20Shortest%20Word%20Distance%20III.java)**      Level: Medium      Tags: [Array, Design, Hash Table, Two Pointers]
      

跟243/244不同: 这里允许list里面有重复的word.

#### Method1: Two Pointers, one pass
- Follow up of 243. Shortested Word Distance
- 特别handle `word == word1 == word2` case:
    - p1 and p2 will always be the same
    - when `word == word1 == word2`, simply calculate distance using the `old p1 or p2` with `curr index i`
- The rest impl aligns with 243.

#### Method2: Hash Table
- when `word1==word2`, make usre to skip `p1==p2` by increasing i or j
- The rest impl aligns with 244
- Time: still O(n), but slower than Method1: 2 passes
- Space: uses extra space O(n) to hold all indexes



---

**89. [621. Task Scheduler.java](https://github.com/awangdev/LintCode/blob/master/Java/621.%20Task%20Scheduler.java)**      Level: Medium      Tags: [Array, Enumeration, Greedy, PriorityQueue, Queue]
      

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

**90. [747. Largest Number At Least Twice of Others.java](https://github.com/awangdev/LintCode/blob/master/Java/747.%20Largest%20Number%20At%20Least%20Twice%20of%20Others.java)**      Level: Easy      Tags: [Array]
      

多种简单操作:
- O(n) solution: 找最大值, 和第二大的值, 看是否符合题意, 就行了.
- O(2n) 最简单方法: 可以loop 两遍: 找最值; 作比较.
- O(2n) 举反例: 有一个不满足, 就够反对这个'at least twice of alllll others'.



---

**91. [88. Search in Rotated Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/88.%20Search%20in%20Rotated%20Sorted%20Array%20II.java)**      Level: Medium      Tags: [Array, Binary Search]
      

#### Binary Search
- Also most identical to `33. Search in Rotated Sorted Array`:
    - find where nums[mid] lands by comparing to nums[start]. i.e., if nums[mid] < nums[start], on right half of the array
    - when `nums[mid] == nums[start]`: duplicate. Shift by start++
- the worst case of `nums[mid] == nums[start]` willl cause O(n),
- but if duplicate is not entire array, should be O(logn)



---

**92. [561. Array Partition I.java](https://github.com/awangdev/LintCode/blob/master/Java/561.%20Array%20Partition%20I.java)**      Level: Easy      Tags: [Array]
      

给串数字, size=2n, 找pairs, 然后需要sum of min(pair) 最大.

(a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

#### Sort, basics
- 从结果出发, 只需要找到加法的结果，而不强调具体配对.
- 写一写example发现规律: 升序排列会让 `高位的min(pair)` 最大化, 于是`一言不合先排列`
- 找到排列取单数位的规律，再考虑负数和正数的相同规律，即可找到排列求解的方法。
- sort, O(nlogn)




---

**93. [39. Combination Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/39.%20Combination%20Sum.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      

给一串数字candidates (no duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 可以用任意多次.


#### DFS, Backtracking
- 考虑input: 没有duplicate, 不需要sort
- 考虑重复使用的规则: 可以重复使用, 那么for loop里面dfs的时候, 使用curr index i
- the result is trivial, save success list into result.
- Time and Space complexity:
    - transform the analysis as for `40. Combination Sum II`
    - Since this problem allows reuse of elemenets, assume they exist in original input as duplicates
    - time: O(k * 2^n), k = avg rst length
    - space: O(k) stack depth, if not counting result size



---

**94. [1040. Moving Stones Until Consecutive II.java](https://github.com/awangdev/LintCode/blob/master/Java/1040.%20Moving%20Stones%20Until%20Consecutive%20II.java)**      Level: Medium      Tags: [Array, Sliding Window]
      


#### Analyze to understand
- Make sure to sort array: we need to use the actual number range `A[j] - A[i]`, which requires the array to  be sorted
- we want to form a new array where A[n-1] - A[0] + 1 == n; order does not matter but all slots need to be filled consecutivly
- max moves: https://leetcode.com/problems/moving-stones-until-consecutive-ii/discuss/289357/c%2B%2B-with-picture
    - A interval will be automatically dropped between A[0] and A[1], if moving A[0] first
    - Same, a interval between A[n-2] and A[n-1] will be dropped when moving A[n-1] first
    - so largest possible move = firstItem + remaining range size - n items = 1 + (A[n-1] - A[1] + 1) - n = A[n-1] - A[1] -n + 2
        - or A[n-2] - A[0] - n + 2
- min moves: `Sliding Window`
    - use slinding window to assume a right pointer, to make sure A[right] - A[left] + 1 < n; otherwise, move left++
    - check # of included stones
    - calculate remaining, which is remaining moves
- Handle min move edge case:
    - Consecutive Array up to right = n - 1; need 2 moves to finish



---

**95. [88. Merge Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/88.%20Merge%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Two Pointers]
      

给两个排好序的数组, merge. 其中一个数组nums1有多余的位置

#### Basics
- A够长，那么可以从A的尾部开始加新元素: 从尾部，是大数字优先排末尾的.  
- Deal with remaining:
    - When A values are used up, put remian of B into it
    - When B values are finished, there is nothing todo. The remain of A is already in place.



---

**96. [122. Best Time to Buy and Sell Stock II.java](https://github.com/awangdev/LintCode/blob/master/Java/122.%20Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Status DP]
      

和Stock I 的区别：可以买卖多次，求总和的最大盈利.

#### 几种不同的思路
1. Greedy, 每次有相邻的diff符合profit条件, 就卖了, 最后把所有的diff加在一起. 计算delta, 其实简单粗暴, 也还不错.
2. 从低谷找peek, sell.
3. DP. (old dp solution BuyOn[], SellOn[])
4. DFS计算所有(timeout).Improvement on DFS -> DP -> calculate sellOn[i] and buyOn[i], and then return buyOn[i]. 有点难想, 但是代码简单, 也是O(n)

#### 1. Greedy
- 画图, 因为可以无限买卖, 所以只要有上升, 就有profit
- 所有卖掉的, 平移加起来, 其实就是overall best profit
- 当天卖光, 再买进.
- O(n) time

#### 2. 找涨幅最大的区间, 买卖
- 找到低谷，买进:peek = start + 1 时候，就是每次往前走一步;若没有上涨趋势，继续往低谷前进。
- 涨到峰顶，卖出:一旦有上涨趋势，进一个while loop，涨到底, 再加个profit.
- profit += prices[peek - 1] - prices[start]; 挺特别的。
- 当没有上涨趋势时候，peek-1也就是start, 所以这里刚好profit += 0.

#### 3. DP, sequence dp + status
- 想知道前i天的最大profit, 那么用sequence DP: 
- dp[i]: represents 前i天的最大profit
- 当天的是否能卖, 取决于昨天是否买进, 也就是 `昨天买了或者卖了的状态`: 加状态, dp[i][0], dp[i][1]
- `买`的状态 `dp[i][0]` = 
    - 1. 今天买入, 昨天sell后的dp[i-1][1]值 - 今天的价格price[i];
    - 2. 今天不买, compare with 昨天buy的dp[i-1][0]值.
- `卖`的状态 `dp[i][1]` = 
    - 1. 今天卖出, 昨天buy的 dp[i-1][0]值 + price[i]; 
    - 2. 今天不卖, compare with 昨天sell后的 dp[i-1][1]值.
- 注意init: 
    - dp[0][0] = dp[0][1] = 0; // day 0 buy/sell: no profit regardless of buy/sell status
    - dp[1][1] = 0; // day 1 sell: haven't bought, so just 0 profit.
    - dp[1][0] = - prices[0]; // day 1 buy: just cost of prices[0]
- Note: `int[][] dp = new int[n+1][2]`以后, index是 1-based. for loop 注意使用 `prices[i-1]`
- O(n) time, O(n) space

##### Rolling Array
- [i] 和 [i - 1] 相关联, roll




---

**97. [243. Shortest Word Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/243.%20Shortest%20Word%20Distance.java)**      Level: Easy      Tags: [Array, Two Pointers]
      


#### Two Pointers
- Use 2 pointers to record **most recent** pos of word1 and word2
    - move pointer i [0 ~ n) and keep refreshing pos1 and pos2
    - both pos1 and pos2 will be as adjacent as possible since they both moving towards same direction
- keep recalculating best distance when either word is matched
- 而同一时间，只需要计算一个最近的curr distance: greedy不断变更A/B index, 做比较




---

**98. [414. Third Maximum Number.java](https://github.com/awangdev/LintCode/blob/master/Java/414.%20Third%20Maximum%20Number.java)**      Level: Easy      Tags: [Array, PriorityQueue]
      
#### pq
- 注意special case: `when less than 3 items, return maximum`
- PQ是natural order: remain peek() will be the 3rd maximum




---

**99. [1267. Count Servers that Communicate.java](https://github.com/awangdev/LintCode/blob/master/Java/1267.%20Count%20Servers%20that%20Communicate.java)**      Level: Medium      Tags: [Array, Graph]
      

#### two axis array, cross-reference
- analyze problem, and realize we want to eliminate `isolated servers`
- count row[], count col[]
- cross-reference row[] and col[]: `row[i]==1 & col[j]==1` indicates a isolated server

### DFS brutle
- Unfortunately, this problems checks unconnected items, so dfs needs to brutlely check entire row or column
- Only add if `vertical + horizontal count` > 1
- time: O(mn) * O(m + n)



---

**100. [169. Majority Element.java](https://github.com/awangdev/LintCode/blob/master/Java/169.%20Majority%20Element.java)**      Level: Easy      Tags: [Array, Bit Manipulation, Divide and Conquer, Moore Voting, Sort]
      

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

**101. [78. Subsets.java](https://github.com/awangdev/LintCode/blob/master/Java/78.%20Subsets.java)**      Level: Medium      Tags: [Array, BFS, Backtracking, Bit Manipulation, DFS]
      

给一串unique integers, 找到所有可能的subset. result里面不能有重复.

#### DFS
- dfs的两种路子: 1. pick&&skip dfs, 2. for loop dfs
- 1. pick&&skip dfs: 取或者不取 + backtracking. 当level/index到底，return 一个list. Bottom-up, reach底部, 才生产第一个solution.
- 2. for loop dfs: for loop + backtracking. 记得：做subset的时候, 每个dfs recursive call是一种独特可能，先加进rst.  top-bottom: 有一个solution, 就先加上.
- Time&&space: subset means independent choice of either pick&&not pick. You pick n times: `O(2^n)`, 3ms
- space: O(2^n) results

#### Bit Manipulation
- n = nums.length, 那么在每一个index, 都是 pick / not pick: 0/1
- 考虑subset index 0/1的bit map: range 的就是 [0000...00 ~ 2^n-1]
- 每一个bitmap就能展现出一个subset的内容: all the 1 represents picked indexes
- 做法:
- 1. 找出Range
- 2. 遍历每一个bitmap candidate
- 3. 对每一个integer 的 bit representation 遍历, 如果是1, add to list
- time: O(2^n * 2^n) = O(4^n), still 3ms, fast.

#### Iterative, BFS
- BFS, 注意考虑如果让one level to generate next level
    - 1. maintain a list of Indexe to store candidate indexes.
    - 2. 每一次打开一层candiates, add them all to result
    - 3. 并且用每一轮的candidates, populate next level, back into queue.
- should be same O(2^n), but actual run time 7ms, slower
- O(n) space



---

**102. [380. Insert Delete GetRandom O(1).java](https://github.com/awangdev/LintCode/blob/master/Java/380.%20Insert%20Delete%20GetRandom%20O(1).java)**      Level: Medium      Tags: [Array, Design, Hash Table]
      

#### Hash Table, Swap when removing
- Map: `map<val, index>, Lis: tracks `index->value`
- list maintain 用来 insert/remove/random operations.
- Remove: swap input valueIndex & tialIndex = list.size() -1.
    - list.remove(object) 应该是要O(logn) 做一个search的.
    - list.remove(list.size() - 1) is cheapter



---

**103. [560. Subarray Sum Equals K.java](https://github.com/awangdev/LintCode/blob/master/Java/560.%20Subarray%20Sum%20Equals%20K.java)**      Level: Medium      Tags: [Array, Hash Table, PreSum, Subarray]
      

给一串数字, 找其中的 # of subarray的 where subararySum == k.

#### Method1: Hash Table to sture presum (like in 2 sum problem)
- Approach#4 of https://leetcode.com/problems/subarray-sum-equals-k/solution/
- Hash Table two sum 思想, but to save frequency of current sum: `preSumCount<sum, count>`
    - for loop 从左开始积累 `preSumCount<sum, count>`
    - derive `priorSum = sum - k`: 看看前面有多少此种sum, `preSumCount.get(priorSum)`
        - `# ways to reach priorSum` gives # of ways for that `priorSum + k = curr Sum`
        - therefore, count += preSumCount.get(priorSum)
- O(n) time, O(n) space
- Note: 如果需要实际index, 可以存 `Map<Integer, List<Index>>`

#### Method2: Calculate each individual range, with PreSum
- presum: socalled `cummulative sum`
- move from starting point i = [0 ~ n -1] and test each `range = [i ~ j]`
- use presum to verify k: `preSum[j + 1] - preSum[i]`
- time: O(n^2): `1 + 2 + 3 + 4 ... + n ~= O(n^2)`




---

**104. [219. Contains Duplicate II.java](https://github.com/awangdev/LintCode/blob/master/Java/219.%20Contains%20Duplicate%20II.java)**      Level: Easy      Tags: [Array, Hash Table]
      

Unsorted array, 找出是否有duplicate elemenets: 必要条件是, 这两个element的index i,j 的大小最多相差k.

#### HashSet
- 很巧妙地根据k range地条件
    - 把HashSet里面的值控制在[i - k, i]
    - 每次不断地往set里面加新元素, 从set里减去末尾index的元素
- 而set.add(x)如果遇到重复, 会return false.
- 一旦在这个length k 的 range里面, 有重复, 就符合条件. 
- Time O(n)

#### HashTable<value, List of duplicates>
- Time O(nm), m = # of duplicates. 太慢
- 记录每个element value的index in the list
- 一旦有重复element重复, 就把整个list of indexes 端出来, 查看有没有符合条件的: (index - i) <= k


#### 这两种做法的区别很有艺术感觉
- 方法1是限定选拔的candidate, 不合格就去掉, 那么一旦有符合条件的(duplicates), 那么一定中, 剩下的就不看了.
- 方法2是把符合条件的index找出来, 集中处理, 但是所有candidate都会选出来
- 就好像招人一样: 一种是遇到好的就停止; 第二种是看过所有人, 从中选拔最好的. 显然第一种更快.




---

**105. [287. Find the Duplicate Number.java](https://github.com/awangdev/LintCode/blob/master/Java/287.%20Find%20the%20Duplicate%20Number.java)**      Level: Medium      Tags: [Array, Binary Search, Binary Search on Value, Cycle Detection, Slow Fast Pointer, Two Pointers]
      

#### Method1: Slow Fast Pointer
- Use LinkedList Cycle Concept:
    - Each element the array is like a `Node {int currIndex; int val;}`, where the `val` is also pointer to next Node
    - A node is like a portal; a pointer can: 1) visit a node by currIndex, 2) pick up newIndex = `nums[currIndex]`, then keep repeating step 1 and 2.
    - Important: since nums is immutable, the pointer footprint is unique/linear
    - Just like linked list. Therefore, use slow/fast pointer to detect cycle.
- https://leetcode.com/problems/find-the-duplicate-number/solution/
- it is now the same as `142. Linked List Cycle II`

#### Method2: Binary Search on value
- 注意不要思维定式: binary search `NOT on index`
    - `binary search on value`: [1, n]
    - O(logN)
- validate(nums, candidate): for loop to count number of `value <= candidate`
    - `count == candidate`: no duplicate from [1 ~ candidate]. 
    - `count < candidate`: missing element in [1~ candidate], so duplicates are in later range. start = mid;
    - `count > candidate`: there are duplicates in [1~ candidate]. end = mid;
- Time: O(nLogN)
- Space: O(1)



---

**106. [217. Contains Duplicate.java](https://github.com/awangdev/LintCode/blob/master/Java/217.%20Contains%20Duplicate.java)**      Level: Easy      Tags: [Array, Hash Table]
      


无序数组, 找是否有重复element, return true/false.

#### HashSet
- No brain: HashSet.
- Time O(n), Space O(n)

#### Sort, Binary Search
- Arrays.sort(x): Time O(nLogN), Space O(1)
- 排序后, 重复数会排在一起, 然后 binary search



---

**107. [64. Minimum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/64.%20Minimum%20Path%20Sum.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP]
      

#### DP
- Time, Space O(MN)
- 往右下角走, 计算最短的 path sum. 典型的坐标型.
- 注意: init 第一行的时候, 要accumulate dp[0][j - 1] + grid[i][j], 而不是单纯assign grid[i][j]
- Rolling Array
    - Time O(MN), Space O(N)
    - 1) 需要在同一个for loop里面完成initialization, 2) reuse dp[i][j]
    - Reason: dp[i % 2][j] 在被计算出来的时候, 马上在下一轮会被用. 被覆盖前不用,就白算
    - Option3 below initialize dp outside of loop: 看起来固然简单, 但是不方便空间优化

#### DFS (top-down) Thinking process
- Enumerate the possible 2 paths: go right, go down
- sub problem: dfs(i+1,j), dfs(i,j+1); pick the min of the two
- memoization: after the path from a point (i,j) to end is computed, record memo[i][j] to avoid repatative calculation
- time: O(mn), only visite and record memo[i][j] once.
- space: O(mn) extrem case of m=100000, n = 2; the stack height is O(mn)


---

**108. [229. Majority Element II.java](https://github.com/awangdev/LintCode/blob/master/Java/229.%20Majority%20Element%20II.java)**      Level: Medium      Tags: [Array, Moore Voting]
      

#### Two counters, Moore Voting
1. Moore voting: vote可以加减, 一旦为零, 换下一个candidate, 之前抵消掉的算作清零.
1. 一个array里面, 最多也只有2个数字 出现次数大于2次, 所以用A/B表示.
1. count overall apperance at the end for the two items: countA, countB
1. save to result as valA, valB
1. 有点 moore voting的意思: 
    - 当count == 0的时候, reset 
    - 两个candidate A/B都不等, 那么countA--, countB--
1. 最终重新计数, 然后比较出结局.
1. 注意: 按照if statement的顺序, valA&&countA 比valB&&countB有优先性



#### Sort + count
- O(nlogN)



---

**109. [121. Best Time to Buy and Sell Stock.java](https://github.com/awangdev/LintCode/blob/master/Java/121.%20Best%20Time%20to%20Buy%20and%20Sell%20Stock.java)**      Level: Easy      Tags: [Array, DP, Sequence DP]
      
给个array of stock prices, 限制能交易(买/买)一轮, 问如何找到最大profit.

#### min[n]
- 每天都就交易价格，n天只让买卖一次，那就找个最低价买进，找个最高价卖出
- 记录每天最小值Min是多少. O(n)
- 每天都算和当下的Min买卖，profit最大多少.

#### DP
- Find min value for first i items, new dp[n + 1].
- dp[i]: 前i天, prices最小的price是多少: min cost of first i days
- 然后用当天的price做减法dp[i]算max profit.
- Time, Space: O(n)
- 更进一步, 用一个min来表示min[i], 因为计算中只需要当下的min.

#### Rolling array
- index i only depend on [i - 2]
- Space O(n)

#### Brutle Failed
- 每天都试着买进，然后之后的每一天尝试卖出. double for loop, O(n^2). timeout.
    - 其中很多都是没必要的计算：[7, 1, 5, 3, 6, 4]
    - if we know buyin with 1 is cheapest, we dont need to buyin at 5, 3, 6, 4 later on;
    - only need to sell on higher prices.



---

**110. [1146. Snapshot Array.java](https://github.com/awangdev/LintCode/blob/master/Java/1146.%20Snapshot%20Array.java)**      Level: Medium      Tags: [Array, Hash Table, TreeMap]
      


#### Hash Table, TreeMap; atomic save
- store efficiently: use List<Map<snapId, val>>. only preserve changed itemd
- if no match, find last modifed item based on snapId, use TreeMap.floorEntry
    - map.floorEntry(id) return the item.key lower or equal to id
- Utilize a `buffer: Map<Integer, Integer>` and perform atomic save



---

**111. [605. Can Place Flowers.java](https://github.com/awangdev/LintCode/blob/master/Java/605.%20Can%20Place%20Flowers.java)**      Level: Easy      Tags: [Array, Greedy]
      

#### Array
- just check flowerbed[i-1] & flowerbed[i+1] and count



---

**112. [1. Two Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/1.%20Two%20Sum.java)**      Level: Easy      Tags: [Array, Hash Table]
      

#### HashMap<value, index>
- 相对暴力简洁: 找到一个value, 存一个index
- 若在HashMap里面 match 到结果, 就return HashMap里存的index. 
- O(n) space && time.

#### Sort array, two pointer
- 前后++, --搜索. Sort 用时O(nlogn).     
- 1. 第一步 two pointer 找 value.       
- 2. 注意，要利用额外的空间保留original array， 用来时候找index. (此处不能用HashMap，因为以value 为key，但value可能重复)      
- O(n) space, O(nlogn) time.    




---

**113. [118. Pascal's Triangle.java](https://github.com/awangdev/LintCode/blob/master/Java/118.%20Pascal's%20Triangle.java)**      Level: Easy      Tags: [Array, Basic Implementation, List]
      



---

**114. [283. Move Zeroes.java](https://github.com/awangdev/LintCode/blob/master/Java/283.%20Move%20Zeroes.java)**      Level: Easy      Tags: [Array, Two Pointers]
      

Move non-zero elements to front of array; preseve order.

#### Two Pointers
- Outside pointer that moves in certain condition. 
- Save appropirate elements



---

**115. [63. Unique Paths II.java](https://github.com/awangdev/LintCode/blob/master/Java/63.%20Unique%20Paths%20II.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP]
      

跟unique path的grid一样, 目标走到右下角, 但是grid里面可能有obstacle, 不能跨越. 求unique path 的count.

#### 坐标DP
- dp[i][j]: # of paths to reach grid[i][j]
- Bottom-up: at end, there are 2 ways to reach dp[i][j]
    - dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
- Handle obstacle (cause dp[i][j] to be 0).

#### 坐标DP
- dp[i][j]: # of paths to reach grid[i][j]
- Bottom-up: at end, there are 2 ways to reach dp[i][j]
    - dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
- Handle obstacle (cause dp[i][j] to be 0).



---

**116. [105. Construct Binary Tree from Preorder and Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/105.%20Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Hash Table, Tree]
      

如题

#### DFS ApproachA:
- use preorder to find root, one index at a time (global index)
- use the root to divide and conquer inorder int[] to 2 sides;
    - root.left = dfs(left); root.right = dfs(right)
    - end stage: start == end index, create a node
- can use a map to store inorder <val, index> for O(1) find

#### DFS
- 和Construct from Inorder && Postorder 想法一样。
- 写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。
- 跟Convert Sorted Array to Binary Tree类似, 找到对应的index, 然后:
    - node.left = dfs(...), node.right = dfs(...)
- Divide and Conquer
    - optimize on finding `mid node`: given value, find mid of inorder:
- Instead of searching linearly, just store inorder sequence in `map <value -> index>`, O(1)
- IMPORATANT: the mid from inorder sequence will become the main baseline to tell range: 
- `range of subTree = (mid - inStart)`
- sapce: O(n), time: O(n) access



---

**117. [40. Combination Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/40.%20Combination%20Sum%20II.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      

给一串数字candidates (can have duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 只可以用一次.

#### DFS, Backtracking
- when the input has duplicates, and want to skip redundant items? 考虑重复使用的规则: 不可以重复使用
    - 1. sort.  考虑input: 有duplicate, 必须sort
    - 2. in for loop, skip same neighbor: `if (i > index && candidates[i] == candidates[i - 1]) continue;`
        - 因为在同一个level里面重复的数字在下一个dfs level里面是会被考虑到的, 这里必须skip
- the result is trivial, save success list into result.
- Time complexity: O(k * 2^n), k = average result length
    - 1) Assume on average, there are k elements in result 
    - 2) Since element can be used ONLY once, so the total # of solutions can be `C(n, k)`: `pick k out of n`
    - 3) Now let k be any number [0, n], so total # of solutions can be: `C(n,0) + C(n,1) + C(n,2) + ... C(n,n) = 2^n`
    - 4) Now: `the new ArrayList<>(list)` takes average O(k) time
    - Total: O(k * 2^n)
- Space: O(n), stack depth, if not counting results size



---

**118. [724. Find Pivot Index.java](https://github.com/awangdev/LintCode/blob/master/Java/724.%20Find%20Pivot%20Index.java)**      Level: Easy      Tags: [Array, PreSum]
      

#### PreSum
- want to find `nums[i - 1] == nums[n - 1] - nums[i]`, given: 
    - preSum[i], sum from [0, i] inclusive
    - preSum[j] - preSum[i] = [i+1, j] inclusive
- O(n) to build preSum
- O(n) to find pivot



---

**119. [33. Search in Rotated Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/33.%20Search%20in%20Rotated%20Sorted%20Array.java)**      Level: Medium      Tags: [Array, Binary Search]
      

#### Binary Search
- 关键点, 是找到 [mid]是在左边/还是右边的continous increasing subarray: 比较 `A[start] < A[mid]`
- 在两个section 里面分别讨论 target 的位置     
    - 1. `nums[start] < nums[mid]`: start是从index=0开始的, 那就说明 `mid在前半段`
    - 2. `nums[start] > nums[mid]`: start是从index=0开始的, 那就说明 `mid在后半段`
- Binary search template: 
    - 1) `start + 1 < end` (adjacent indexes)
    - 2) start/end = mid, 
    - 3) compare start and end individually

#### binary search break point, 然后继续binary search target
- 1. binay search break point     
- 2. binary search target      
- 注意等号，在判断target在前半段还是后半段：if (A[p1] <= target && target <= A[breakPoint])      





---

**120. [1170. Compare Strings by Frequency of the Smallest Character.java](https://github.com/awangdev/LintCode/blob/master/Java/1170.%20Compare%20Strings%20by%20Frequency%20of%20the%20Smallest%20Character.java)**      Level: Easy      Tags: [Array, String]
      

#### Method1: letter frequency map, kinda bucket sort
- Goal: find word count that fits into `f(queries[i]) < f(W)`
- What if: we can store the f(W) as preSum, then goal: `rst[i] = preSum[end] - preSum[queryWordCount]`
    - count(W) and store in count[i]
    - calc preSum
    - processs queries array
- kinda bucket sort: 
    - 1) we know the boundary of the word length, so we can create `bucket`
    - 2) `function count(w)` can produce a value that sort a word into a specific bucket slot
        - extend: the bucket can store keys that links back to the word (if there are follow up questions)
- time: O(m + n)
- space: O(m + n)

#### Method2: No brain solution, basic impl based on the desc, w/o search. 
- time: 
    - O(nm) to count all words, O(nlogn) to sort the wordCount
    - O(nm) to to count all queries
    - O(n^2) to perform the match 
- space: O(n)



---

**121. [34. Find First and Last Position of Element in Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/34.%20Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array.java)**      Level: Medium      Tags: [Array, Binary Search]
      

#### Binary Search
- need search left bound & right bound.
- use input parameter `direction` to binary search function
- Option0: simplification, inspired by `278. First Bad Version - Method1: Check is-NOT-BadVersion`
    - 1) if found match, but NOT sure it is desired boundary, just leave it and keep going
    - 2) check the final results after `binary search while loop` completes
    - WHY? code is easier to read in this way.



---

**122. [689. Maximum Sum of 3 Non-Overlapping Subarrays.java](https://github.com/awangdev/LintCode/blob/master/Java/689.%20Maximum%20Sum%20of%203%20Non-Overlapping%20Subarrays.java)**      Level: Hard      Tags: [Array, DP]
      

#### DP, Divide and conquer
- split into 3 parts [0, i -1], [i, i + k -1]. [i + k, n - 1]
- NOTE: be very careful about index handling: 
    - `presum[i + 1] - presum[0]` gives inclusive range of `[0, i]`
- Use DP to record the starting position of max sum, 
- inspired by: https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/108231/C%2B%2BJava-DP-with-explanation-O(n)
    - 1) calculate preSum with range [0, n]
    - 2) calculate leftMaxIndex[], rightMaxIndex[]
    - 3) test middle range to find max solution
- Note: the test range for 1, 2, 3 always start with assumption that k has been consumed from one side
- Note: When need to record at max/min value change, we can check/assign it manually (rather than use a object to carry & sort)




---

**123. [149. Max Points on a Line.java](https://github.com/awangdev/LintCode/blob/master/Java/149.%20Max%20Points%20on%20a%20Line.java)**      Level: Hard      Tags: [Array, Geometry, Hash Table, Math]
      

给list of (x,y) coordinates. Determine  # of points on the same line

#### Observation
- If given n points, we can calculate all possible slopes. O(n^2) times
- For the two dots that generates the same slope, these dots could be on **parallel** slopes
- figure out how to prune the parallel dots

#### Trick: prune parallel dots using greatest common divider
- GCD: greatest common divider
- Devide the x and y by their greatest common divider, such that x and y can be reduced to minimum value
- All other x and y can be reduced to such condition as well
- track the final reduced (x,y) in a map: they are the key to the count
- No need to use Map<Integer, Map<Integer, Integer>> to perform 2 level mapping; just `map<String, Integer>`, where the key is "x@y"



---

**124. [57. Insert Interval.java](https://github.com/awangdev/LintCode/blob/master/Java/57.%20Insert%20Interval.java)**      Level: Hard      Tags: [Array, PriorityQueue, Sort, Sweep Line]
      

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

**125. [611. Valid Triangle Number.java](https://github.com/awangdev/LintCode/blob/master/Java/611.%20Valid%20Triangle%20Number.java)**      Level: Medium      Tags: [Array, Two Pointers]
      

#### Method1: Fix max and backward counting
- Sort nums: O(nlogn)
- Set max value fixed on right side at k
    - set 2nd value from right index j
    - set last value at min index i
    - if `nums[i] + nums[j] > nums[k]`: with fixed j, i can pick from [i, j-1] combinations
        - then j--, to pick another j candidate
    - maintain a window [i,j]; if invalid, move i++
- time: O(n^2)
- Note: very similar to 3-sum, fixing 1 index and use 2 pointers to move window

#### Method2: Fix min and forward counting
- Sort nums: O(nlogn)
- Set min value at i
    - set 2nd value at j=i+1; and 3rd value at k=i+2
    - find max of k that fits into triangle
    - count all possible k candidates from [j+1, k]
    - then move j to a new candidate
- O(n^2)



---

