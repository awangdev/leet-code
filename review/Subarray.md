 
 
 
## Subarray (11)
**0. [Minimum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Subarray.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Subarray]
      

给一串数组, unsorted, can have negative/positive num. 找数组中间 subarray 数字之和的最小值

#### DP
- 看到 min value, 至少考虑dp:
- Consider last num: min sum will be (preMinSum + curr, or curr)
- Use preMinSum to cache previouly calcualted min sum, also compare with +curr.
- Have a global min to track: because the preMinSum can be dis-continuous. 
- 也可以写成 dp[i] 但是没什么必要



---

**1. [Maximum Average Subarray I.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20I.java)**      Level: Easy      Tags: [Array, Subarray]
      

简单的求sum of fixed window k, 同时求max avg, 结尾求余数就好.



---

**2. [Minimum Size Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Size%20Subarray%20Sum.java)**      Level: Medium      Tags: [Array, Binary Search, Subarray, Two Pointers]
      

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

**3. [Maximum Size Subarray Sum Equals k.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Size%20Subarray%20Sum%20Equals%20k.java)**      Level: Medium      Tags: [Hash Table, PreSum, Subarray]
      

#### Map<preSumValue, index>
- use `Map<preSum value, index>` to store inline preSum and its index.
- 1. Build presum incline
- 2. Use map to cache current preSum value and its index: `Map<preSum value, index>`
- 3. Each iteration: calculate possible preSum candidate that prior target sequence. ex: `(preSum - k)`
- 4. Use the calculated preSum candidate to find index
- 5. Use found index to calculate for result. ex: calculate range.



---

**4. [Subarray Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20Closest.java)**      Level: Medium      Tags: [PreSum, PriorityQueue, Sort, Subarray]
      

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

**5. [Maximum Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray%20II.java)**      Level: Medium      Tags: [Array, DP, Greedy, PreSum, Sequence DP, Subarray]
      
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

**6. [[lint]. Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Subarray%20Sum.java)**      Level: Easy      Tags: [Array, Hash Table, Lint, PreSum, Subarray]
      

给一串数字, 找其中的一个subarray的 [start, end] index, 条件: subarary sum == 0.

#### Hash Table
- `subarray sum equals k` 的简单版: k = 0
    - 求preSum, 然后不断check `map.containsKey(preSum - k)`. 
    - 如果 `priorSum = preSum - k == 0`, 说明 [priorSum.index + 1, curr index] 就是我们要找的这一段

#### Previous notes, same preSum + map solution
- 分析出，如果sum[0~a]=x, 然后sum[0~b]=x, 说明sum[a+1 ~ b] == 0
- 用hashMap存每个sum[0~i]的值和index i. 如果有重复，就找到了一组sum为0的数组.



---

**7. [53. Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/53.%20Maximum%20Subarray.java)**      Level: Easy      Tags: [Array, DFS, DP, Divide and Conquer, PreSum, Sequence DP, Subarray]
      

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

**8. [152. Maximum Product Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/152.%20Maximum%20Product%20Subarray.java)**      Level: Medium      Tags: [Array, DP, PreProduct, Subarray]
      

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

**10. [523. Continuous Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/523.%20Continuous%20Subarray%20Sum.java)**      Level: Medium      Tags: [Coordinate DP, DP, Math, PreSum, Subarray]
      

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

