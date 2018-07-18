 
 
 
## PreSum (9)
**0. [Maximum Average Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20II.java)**      Level: Review      Tags: [Array, Binary Search, PreSum]
      

给int[] nums 和 window min size k. window size可以大于K. 找最大的连续数列average value.

- Binary Search的思想, 用在所要找的这个 average sum 上面. 大小是在[min, max]之中
- 找k的时候, 是>=k都可以, 巧用一个 min(preSum)的概念.
- 找k的时候, 画图, 可以看出来, 其实要的是 k window 里面的sum [x, i], 所以要用 sum[0, i] - sum[0, x]

需要仔细去读下面的notes.



---

**1. [Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray.java)**      Level: Easy      Tags: [Array, DFS, DP, Divide and Conquer, PreSum, Sequence DP]
      
time: O(n)
space: O(n), O(1) rolling array

给一串数组, 找数组中间 subarray 数字之和的最大值

#### Sequence DP
- dp[i]: 前i个element,包括 last element (i-1), 可能组成的 subarray 的最大sum.
- init: dp = int[n + 1], dp[0]: first 0 items, does not have any sum
- 因为continous sequence, 所以不满足条件的时候, 会断. That is: need to take curr num, regardless => can drop prev max in dp[i]
- track overall max 
- init dp[0] = 0; max = MIN_VALUE 因为有负数
- Time, space O(n)
- Rolling array, space O(1)

#### Divide and Conquer, DFS
- 找一个mid piont, 考虑3种情况: 只要左边, 只要右边, cross-mid
- left/rigth 的case, 直接 dfs
- corss-mid case: continuous sum max from left + continous sum max from right + mid
- continuous sum max from one direction:


---

**2. [Maximum Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray%20II.java)**      Level: Medium      Tags: [Array, DP, Greedy, PreSum, Sequence DP]
      

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

**3. [Count of Range Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Range%20Sum.java)**      Level: Hard      Tags: [BST, Divide and Conquer, Merge Sort, PreSum]
      

TODO: Write the code + merge function

#### Divide and Conquer + PreSum + MergeSort
- 算法非常厉害就是了: 先做presum[], 那么 sum range [i,j] 就等于是preSum[j+1] - preSum[i]
- 分治: 考虑[start, mid] range里面的结果, 再考虑[mid, end] range里面的结果. (分开来 mergeSort)
- 最后考虑[low,high]总体的结果
- 小技巧: PreSum 做成了 (n + 1) length, 那么求range sum [i,j] 就可以简化成 preSum[j] - preSum[i]
- NOTE: should write merge() function, but that is minor, just use `Arrays.sort(nums, start, end)`, OJ passed
- Every mergeSort() has a for loop => O(n log n)

##### 如何 count range?
- 这里比较特别的一个做法: 找一个 [low, mid]里面的i, mid 之后的preSum作比较 (解释源自: https://blog.csdn.net/qq508618087/article/details/51435944)
- 即在右边数组找到两个边界, 设为`m, n`, 
- 其中m是在右边数组中第一个使得`sum[m] - sum[i] >= lower`的位置, 
- n是第一个使得`sum[n] - sum[i] > upper`的位置, 
- 这样`n-m`就是与左边元素i所构成的位于`[lower, upper]`范围的区间个数. 

##### 神奇的重点: 为什么要 merge and sort
- 边界[lower, higher] 在 sorted array 好作比较, 一旦国界, 就可以停止计算, 减少不必要计算.
- 上面这个n,m的做法可行的前提: preSum[]里面前后两个 range[low, mid], [mid, high]已经sorted了
- 也就是说, 在recursively mergeSort()的时候, 真的需要merge sorted 2 partitions
- 也许会问: 能不能sort呢, sort不久打乱了顺序? 对,打乱的是preSum[]的顺序.
- 但是不要紧: 很巧妙的, 分治的时候, 前半段/后半段 都在原顺序保留的情况下 分开process完了, 最后才merge
- 在做m,n 的range的时候, 原理如下, 比如preSum被分成这么两段: `[A,B,C]`, `[D,E,F]`
- 每一个preSum value `A` 在跟 preSum[i] 作比较的时候 `A - preSum < lower`, 都是单一作比较, 不牵扯到 B, C
- 因此, `[A, B, C]` 是否保留一开始 preSum的顺序在此时不重要
- 此时最重要的是, `[A,B,C]`以及排序好, 那么在于 `lower` boundary 作比较的时候, 一旦过界, 就可以停止计算(减少不必要的计算)


#### BST
- TODO?



---

**4. [Submatrix Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Submatrix%20Sum.java)**      Level: Medium      Tags: [Array, Hash Table, PreSum]
      

给一个int[][] matrix, 找一个sub matrix, where the sum == 0.

#### PreSum的思想
- 算出一个右下角点(i,j)到(0,0)的大小: 上一块 + 左一块 + curr node - overlap area
- preSum[i][j]: sum from (0,0) to (i-1,j-1)
- same approach as `subarray sum`: use hashmap to store diff->index; if diff re-appears, that means sum of 0 has occurred
- sequence of calculation: 1. iterate over start row. 2. iterate over end row. 3. iterate over col number (this is where hashmap is stored based on)
- the iteration over col is like a screening: find previous sum and determine result
- Note: 其实并没有真的去找 `== 0` 的解答,而是根据特性来判断 `剩下的/后来加上的一定是0`



---

**5. [Range Sum Query - Immutable.java](https://github.com/awangdev/LintCode/blob/master/Java/Range%20Sum%20Query%20-%20Immutable.java)**      Level: Easy      Tags: [DP, PreSum]
      

给一串数字, 求sumRange.

#### PreSum
- 就是pre sum 的definition
- preSum也是dp[]一种最简易的形式把.
- dp[i], preSum[i]: 前(i-1)个元素的和.



---

**6. [Subarray Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20Closest.java)**      Level: Medium      Tags: [PreSum, Sort]
      

给一串数字, 找subarray的首尾index, 条件: subarray最接近0.

#### PreSum
- Can be a 2D array, or a `class Point`
- Sort preSum: smaller (有可能负数) 靠前, 大数字靠后
- 比较preSum种相连接的两个节点, 找差值min
- 因为最接近的两个preSum节点的差值肯定是最小
- min所在的两个节点的index, 就是result candidate: 这两个index可能再原nums里面相差很远
- time O(nlogn), sort
- space: O(n)



---

**7. [Subarray Sum Equals K.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20Equals%20K.java)**      Level: Medium      Tags: [Array, Hash Table, PreSum]
      
time: O(n)
space: O(n)

#### Hash Table
- Hash Table two sum 思想, but `save frequency of current preSum`
- From the orignal presum solution: `target = preSum[j] - preSum[i - 1]`
- `k = sum - portion`, and reversely, `portion = sum - k`
- We know the value of sum and k, so portion will be requested
- portion is just previously calcualted sum; track its frequency using `map<preSumValue, frequency>`
- map.get(portion) = the # of possible ways to reach k
- O(n) time, O(n) space

#### PreSum, O(n^2)
- move from starting point i = [0 ~ n -1] and define range = [i ~ j]
- use presum to verify k: `preSum[j] - preSum[i - 1]`
- O(n^2): `1 + 2 + 3 + 4 ... + n ~= O(n^2)`




---

**8. [Maximum Size Subarray Sum Equals k.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Size%20Subarray%20Sum%20Equals%20k.java)**      Level: Medium      Tags: [Hash Table, PreSum]
      
time: O(n)
space: O(n)

#### Map<preSumValue, index>
- use `Map<preSum value, index>` to store inline preSum and its index.
- 1. Build presum incline
- 2. Use map to cache current preSum value and its index: `Map<preSum value, index>`
- 3. Each iteration: calculate possible preSum candidate that prior target sequence. ex: `(preSum - k)`
- 4. Use the calculated preSum candidate to find index
- 5. Use found index to calculate for result. ex: calculate range.



---

