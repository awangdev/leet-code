 
 
 
## PreSum (13)
**0. [Maximum Size Subarray Sum Equals k.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Size%20Subarray%20Sum%20Equals%20k.java)**      Level: Medium      Tags: [Hash Table, PreSum, Subarray]
      

#### Map<preSumValue, index>
- use `Map<preSum value, index>` to store inline preSum and its index.
- 1. Build presum incline
- 2. Use map to cache current preSum value and its index: `Map<preSum value, index>`
- 3. Each iteration: calculate possible preSum candidate that prior target sequence. ex: `(preSum - k)`
- 4. Use the calculated preSum candidate to find index
- 5. Use found index to calculate for result. ex: calculate range.



---

**1. [Submatrix Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Submatrix%20Sum.java)**      Level: Medium      Tags: [Array, Hash Table, PreSum]
      
给一个int[][] matrix, 找一个sub matrix, where the sum == 0.

#### PreSum的思想
- 算出一个右下角点(i,j)到(0,0)的大小: 上一块 + 左一块 + curr node - overlap area
- preSum[i][j]: sum from (0,0) to (i-1,j-1)
- same approach as `subarray sum`: use hashmap to store diff->index; if diff re-appears, that means sum of 0 has occurred
- sequence of calculation: 1. iterate over start row. 2. iterate over end row. 3. iterate over col number (this is where hashmap is stored based on)
- the iteration over col is like a screening: find previous sum and determine result
- Note: 其实并没有真的去找 `== 0` 的解答,而是根据特性来判断 `剩下的/后来加上的一定是0`



---

**2. [Maximum Average Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20II.java)**      Level: Review      Tags: [Array, Binary Search, PreSum]
      
给int[] nums 和 window min size k. window size可以大于K. 找最大的连续数列average value.

- Binary Search的思想, 用在所要找的这个 average sum 上面. 大小是在[min, max]之中
- 找k的时候, 是>=k都可以, 巧用一个 min(preSum)的概念.
- 找k的时候, 画图, 可以看出来, 其实要的是 k window 里面的sum [x, i], 所以要用 sum[0, i] - sum[0, x]

需要仔细去读下面的notes.



---

**3. [Subarray Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20Closest.java)**      Level: Medium      Tags: [PreSum, PriorityQueue, Sort, Subarray]
      

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

**4. [Maximum Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray%20II.java)**      Level: Medium      Tags: [Array, DP, Greedy, PreSum, Sequence DP, Subarray]
      
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

**5. [[lint]. Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Subarray%20Sum.java)**      Level: Easy      Tags: [Array, Hash Table, Lint, PreSum, Subarray]
      

给一串数字, 找其中的一个subarray的 [start, end] index, 条件: subarary sum == 0.

#### Hash Table
- `subarray sum equals k` 的简单版: k = 0
    - 求preSum, 然后不断check `map.containsKey(preSum - k)`. 
    - 如果 `priorSum = preSum - k == 0`, 说明 [priorSum.index + 1, curr index] 就是我们要找的这一段

#### Previous notes, same preSum + map solution
- 分析出，如果sum[0~a]=x, 然后sum[0~b]=x, 说明sum[a+1 ~ b] == 0
- 用hashMap存每个sum[0~i]的值和index i. 如果有重复，就找到了一组sum为0的数组.



---

**6. [303. Range Sum Query - Immutable.java](https://github.com/awangdev/LintCode/blob/master/Java/303.%20Range%20Sum%20Query%20-%20Immutable.java)**      Level: Easy      Tags: [DP, PreSum]
      

给一串数字, 求sumRange.

#### PreSum
- pre sum 的definition
- preSum也是dp[]一种最简易的形式把.
- dp[i], preSum[i]: 前(i-1)个元素的和.



---

**7. [327. Count of Range Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/327.%20Count%20of%20Range%20Sum.java)**      Level: Hard      Tags: [BIT, Divide and Conquer, Merge Sort, PreSum, Segment Tree]
      

TODO: Write the code + merge function

#### Divide and Conquer + PreSum + MergeSort
- https://leetcode.com/problems/count-of-range-sum/discuss/77990/Share-my-solution
- 1) build preSum[n+1]: then sum range [i,j]= preSum[j+1] - preSum[i]
- 2) Divide and Conquer: 
    - 先考虑[start, mid] range里的 ran sum result
    - 再考虑[mid, end] range里面的结果
    - 最后考虑[low, high]总体的结果
- NOTE: should write merge() function, but that is minor, just use `Arrays.sort(nums, start, end)`, OJ passed
- Every mergeSort() has a for loop => O(n log n)
- 如何 count range?
    - 这里比较特别的一个做法: 找一个 [low, mid]里面的i, mid 之后的preSum作比较 (解释源自: https://blog.csdn.net/qq508618087/article/details/51435944)
    - 即在右边数组找到两个边界, 设为`m, n`, 
    - 其中m是在右边数组中第一个使得`sum[m] - sum[i] >= lower`的位置, 
    - n是第一个使得`sum[n] - sum[i] > upper`的位置, 
    - 这样`n-m`就是与左边元素i所构成的位于`[lower, upper]`范围的区间个数. 

##### 神奇的重点: 为什么要 merge and sort
- 边界[lower, higher] 在 sorted array 好作比较, 一旦过界, 就可以停止计算, 减少不必要计算.
- 上面这个n,m的做法可行的前提: preSum[]里面前后两个 range[low, mid], [mid, high]已经sorted了
    - 也就是说, 在recursively mergeSort()的时候, 真的需要merge sorted 2 partitions
    - 也许会问: 能不能sort呢, sort不久打乱了顺序? 对,打乱的是preSum[]的顺序.
    - 但是不要紧: 很巧妙的, 分治的时候, 前半段/后半段 都在原顺序保留的情况下 分开process完了, 最后才merge
- 在做m,n 的range的时候, 原理如下, 比如preSum被分成这么两段: `[A,B,C]`, `[D,E,F]`
    - 每一个preSum value `A` 在跟 preSum[i] 作比较的时候 `A - preSum < lower`, 都是单一作比较, 不牵扯到 B, C
    - 因此, `[A, B, C]` 是否保留一开始 preSum的顺序在此时不重要
- 此时最重要的是, `[A,B,C]`以及排序好, 那么在于 `lower` boundary 作比较的时候, 一旦过界, 就可以停止计算(减少不必要的计算)


#### BIT
- TODO?

#### Segment Tree
- This segment tree approach(https://leetcode.com/problems/count-of-range-sum/discuss/77987/Java-SegmentTree-Solution-36ms) 
    - does not build segment tree based on given nums index
    - it is built on sorted preSum array.
- regular segment tree based on nums array does not work:
    - segment tree based on input array is good for: search/query by index
    - is NOT good at: given range sum/value, find indexes
    - why? segment tree is built based on index division, not by range value division.



---

**8. [53. Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/53.%20Maximum%20Subarray.java)**      Level: Easy      Tags: [Array, DFS, DP, Divide and Conquer, PreSum, Sequence DP, Subarray]
      

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

**9. [560. Subarray Sum Equals K.java](https://github.com/awangdev/LintCode/blob/master/Java/560.%20Subarray%20Sum%20Equals%20K.java)**      Level: Medium      Tags: [Array, Hash Table, PreSum, Subarray]
      

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

**10. [304. Range Sum Query 2D - Immutable.java](https://github.com/awangdev/LintCode/blob/master/Java/304.%20Range%20Sum%20Query%202D%20-%20Immutable.java)**      Level: Medium      Tags: [DP, PreSum]
      

#### Method1: rangeSum/caching
- build rangeSum[i][j]: square range sum from (0,0) to (i,j), O(mn) to init
- query: time O(1)

#### Method2: presum/caching
- build rowPreSum[i][j]: row i sum from [0 ~ j], O(mn) to init
- callign takes O(m); space O(mn)




---

**11. [724. Find Pivot Index.java](https://github.com/awangdev/LintCode/blob/master/Java/724.%20Find%20Pivot%20Index.java)**      Level: Easy      Tags: [Array, PreSum]
      

#### PreSum
- want to find `nums[i - 1] == nums[n - 1] - nums[i]`, given: 
    - preSum[i], sum from [0, i] inclusive
    - preSum[j] - preSum[i] = [i+1, j] inclusive
- O(n) to build preSum
- O(n) to find pivot



---

**12. [523. Continuous Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/523.%20Continuous%20Subarray%20Sum.java)**      Level: Medium      Tags: [Coordinate DP, DP, Math, PreSum, Subarray]
      

给一个非负数的数列和数字k(可正负, 可为0). 找到连续子序列(长度超过2), 使得这个subarray的sum 是 k的倍数. 问: 是否可能?

#### Method1: Validate Mod Result
- Check if mod result exist in earlier preSum
- Utilize `Pigeonhole principle` to optimize:
    - 1) put positive integers into k slots
    - 2) when # of integers > 2*k, then there must be a range sum that is multipler of k
    - more illustration here: https://leetcode.com/problems/continuous-subarray-sum/solution/
- Draw the presum and try to take mod of each presum and save to set, we realize
    - 1) assume a mod result = 7, and we mark it in the set
    - 2) some time later, after summing up more values, (7 + x + y ...+ z) % k == 7
        - it means `(x + y ...+ z) % k == 0`
        - There is a `整除` exist; return true
- Meanwhile, if we want to record the list of indexes, we can use a Map rather than set.
- Note: if all we do to the presum is to % k, therefore `preSum % k` can represent `presum` in some cases.
- time: O(n)
- space: O(k), size restrited by mod result of `%k`


#### Method2: DP, PreSum
- PreSum[]: 
    - 1) cal preSum array
    - 2) preSum(i, j) = continuous range sum
    - 3) determine if `preSum(i, j) % k == 0`
- time: O(n^2)
- DP (坐标型. specifically, preSum[])
    - 记录在0 ~ i点(包括nums[i], 以nums[i]结尾)的sum, 坐标型动态规划.
    - dp[i] = dp[i - 1] + nums[i];

#### Method3: 直接算结果
- 从sum = 每次[i ~ j]的所有情况
- time: O(n^2)
- space: O(1)



---

