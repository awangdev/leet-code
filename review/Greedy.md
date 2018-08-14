 
 
 
## Greedy (17)
**0. [Majority Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Number%20II.java)**      Level: Medium      Tags: [Enumeration, Greedy]
      

#### Array
- 分三份：a b c考虑
- 若a: countA++; 或b: countB++
- 或c:countA--, countB--
- 注意: 按照if statement的顺序, valA&&countA 比valB&&countB有优先性
- 最后出现的两个count>0的a和b,自然是potentially大于1/3的。其中有一个大于1/3.
- 比较countA和countB哪个大，就return哪一个。



---

**1. [Queue Reconstruction by Height.java](https://github.com/awangdev/LintCode/blob/master/Java/Queue%20Reconstruction%20by%20Height.java)**      Level: Medium      Tags: [Greedy]
      

别无他法, 只能写一遍例子, 找规律,然后greedy. 
需要写一遍发现的规律比如: 从h大的开始排列, 先放入k小的. 写comparator的时候要注意正确性.
如果要sort, 并且灵活insert:用arrayList. 自己做一个object.
最后做那个'matchCount'的地方要思路清晰, 找到最正确的spot, 然后greedy insert.

O(n) space, O(nLog(n)) time, because of sorting.

可能有简化的余地, 代码有点太长.
比如试一试不用额外空间?



---

**2. [Wildcard Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java)**      Level: Hard      Tags: [Backtracking, DP, Greedy, String]
      

Double sequence DP. 与regular expression 很像.

注意1: 分析字符 ?, * 所代表的真正意义, 然后写出表达式.
注意2: 搞清楚initialization 的时候 dp[i][0] 应该always false.当p为empty string, 无论如何都match不了 (除非s="" as well)
    同时 dp[0][j]不一定是false. 比如s="",p="*" 就是一个matching.



---

**3. [Meeting Rooms II.java](https://github.com/awangdev/LintCode/blob/master/Java/Meeting%20Rooms%20II.java)**      Level: Medium      Tags: [Greedy, Heap, PriorityQueue, Sort, Sweep Line]
      

给一串数字pair, 代表会议的开始/结束时间. 找同时又多少个会议发生(需要多少件房间)

#### PriorityQueue
- PriorityQueue + 一个Class来解决.Ｏ(nlogn)
- 跟 Number of Airpline in the sky是同一道题

#### 方法2: 尝试了一下用一个sorted Array + HashMap
也还行，但是handle edge的时候,HashMap 要小心，因为相同时间start和end的map key 就会重复了。



---

**4. [Coins in a Line.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line.java)**      Level: Medium      Tags: [DP, Game Theory, Greedy]
      

拿棋子游戏, 每个人可以拿1个或者2个, 拿走最后一个子儿的输. 问: 根据给的棋子输, 是否能确定先手的输赢?

Game Theory: 如果我要赢, 后手得到的局面一定要'有输的可能'.

#### DP, Game Theory
- 要赢, 必须保证对手拿到棋盘时, 在所有他可走的情况中, '有可能败', 那就足够.
- 设计dp[i]:表示我面对i个coins的局面时是否能赢, 取决于我拿掉1个,或者2个时, 对手是不是会可能输?
- dp[i] = !dp[i - 1] || !dp[i-2]
- 时间: O(n), 空间O(n)
- 博弈问题, 常从'我的第一步'角度分析, 因为此时局面最简单.

#### Rolling Array
空间优化O(1). Rolling array, %2



---

**5. [Jump Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game.java)**      Level: Medium      Tags: [Array, DP, Greedy]
      

给出步数，看能不能jump to end.

#### Greedy - start from index = 0
- Keep track of farest can go
- 一旦 farest >= nums.length - 1, 也就是到了头, 就可以停止, return true.
- 一旦 farest <= i, 也就是说, 在i点上, 已经走过了步数, 不能再往前跳, 于是 return false
- This can be done using DP. However, greedy algorithm is fast in this particular problem.

#### Greedy - start from index = n - 1
- greedy: start from end, and mark last index
- loop from i = [n - 2 -> 0], where i + nums[i] should always >= last index
- check if last == 0 when returning. It means: can we jump from index=0 to the end?
- Time: O(n), beat 100%

#### DP
- DP[i]: 在i点记录，i点之前的步数是否可以走到i点？ True of false.
- 其实j in [0~i)中间只需要一个能到达i 就好了
- Function: DP[i] = DP[j] && (A[j] >= i - j), for all j in [0 ~ i)
- Return: DP[dp.length - 1];
- Time: O(n^2)




---

**6. [Maximum Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray%20II.java)**      Level: Medium      Tags: [Array, DP, Greedy, PreSum, Sequence DP, Subarray]
      

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

**7. [Remove Duplicate Letters.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicate%20Letters.java)**      Level: Hard      Tags: [Greedy, Hash Table, Stack]
      

#### Hash Table, Greedy
- count[] = int[256], 不需要 `c-'a'`
- boolean visited[]: 一旦一个字母固定了位置后, 再次遇到时候, 直接跳过用过的character
- 如果tail字母可以变小, 那就delete掉tail, 重新接上新字母 (前提条件: 去掉的字母后面还会再出现, set visited[tail] = false)
- Space: O(1) count[], visited[].
- Time: Go through all letters O(n)

#### Stack
- Use stack instead of stringBuffer: keep append/remove last added item
- However, stringBuffer appears to be faster than stack.



---

**8. [Jump Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game%20II.java)**      Level: Hard      Tags: [Array, Coordinate DP, DP, Greedy]
      

给一串数字 是可以跳的距离. goal: 跳到最后的index 所可能用的最少次数.

#### Greedy
- always aiming for the `farest can go`
- if the `farest can go` breaches the end, return steps
- otherwise, send `start=end+1`, `end=farest` and keep stepping from here
- though trying with 2 loops, worst case [1,1,1,...1,1] could have O(n^2)
- But on average should be jumpping through the array without looking back
- time: average O(n)

#### Previous Notes, Greedy
- 维护一个range, 是最远我们能走的. 
- index/i 是一步一步往前, 每次当 i <= range, 做一个while loop， 在其中找最远能到的地方 maxRange
- 然后更新 range = maxRange
- 其中step也是跟index是一样, 一步一步走.
- 最后check的condition是，我们最远你能走的range >= nums.length - 1, 说明以最少的Step就到达了重点。Good.

#### Even simpler Greedy
- 图解 http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
- track the farest point
- whenver curr index reachest the farest point, that means we are making a nother move, so count++
- there seems to have one assumption: must have a solution. Otherwise, count will be wrong number. 
- 其实跟第一个greedy的思维模式是一模一样的.


#### DP 
- DP[i]: 在i点记录，走到i点上的最少jump次数
- dp[i] = Math.min(dp[i], dp[j] + 1);
- condition (j + nums[j] >= i)
- 注意使用 dp[i] = Integer.MAX_VALUE做起始值, 来找min
- time: O(n^2), slow, and timesout



---

**9. [Gas Station.java](https://github.com/awangdev/LintCode/blob/master/Java/Gas%20Station.java)**      Level: Medium      Tags: [Greedy]
      

给一串gas station array, 每个index里面有一定数量gas.

给一串cost array, 每个index有一个值, 是reach下一个gas station的油耗.

array的结尾地方, 再下一个点是开头, 形成一个circle route.

找一个index, 作为starting point: 让车子从这个点, 拿上油, 开出去, 还能开回到这个starting point

#### Greedy
- 不论从哪一个点开始, 都可以记录总油耗, `total = {gas[i] - cost[i]}`. 最后如果total < 0, 无论从哪开始, 必然都不能走回来
- 可以记录每一步的油耗积累, `remain += gas[i] - cost[i]`
- 一旦 remain < 0, 说明之前的starting point 不合适, 也就是说, 初始点肯定在后面的index. 重设: start = i + 1
- single for loop. Time: O(n)

#### NOT DP
- 看似有点像 House Robber II, 但是问题要求的是: 一个起始点的index
- 而不是求: 最后点可否走完/最值/计数



---

**10. [Task Scheduler.java](https://github.com/awangdev/LintCode/blob/master/Java/Task%20Scheduler.java)**      Level: Medium      Tags: [Array, Enumeration, Greedy, PriorityQueue, Queue]
      

#### Array, count frequency, enumerate
- Enumerate to understand: 1. we can module the tasks in module/section; 2. Only need sum the intervals/slots, not return actual layout
- Perfect condition, all letters appear identical # times: just line them up separate in order.
- Real case: task appears different times
- 1. Place maxCount task as header followed with n slots: define (maxCount-1) sections
- 2. For tasks with less # than maxCount# can fill the (maxCount-1) sections; what about the tail section?
- 3. Any task with same maxTask#, of if prior sections all filled, will fill the tail section
- To count overall slots/intervals, come up with this equation:
- 1. Fixed sections: `(maxCount - 1) * (n + 1)`
- 2. Plus all repeating maxCount tasks: calculate by couting identical maxCount of them
- 3. Exception: if the first (max - 1) sections are all filled completely, and we still have extra task (ex: when n is not large enough), then just return tasks.length
- time O(1), space O(1)

#### PriorityQueue
- 正面去做: 
- summerize 每个task出现的次数, 然后qp sort Task object, count 大的靠前
- 起始每个section: k slots = n + 1
- 目标是穷尽 k, 或者 穷尽 pq (poll k times, but will save it back to queue if Task # > 0)
- 如果qp 真的穷尽, break, return count
- 不然, count + remain of k
- extra space O(x), time O(n) + constant time O(xlogx), where x = 26



---

**11. [Rearrange String k Distance Apart.java](https://github.com/awangdev/LintCode/blob/master/Java/Rearrange%20String%20k%20Distance%20Apart.java)**      Level: Hard      Tags: [Greedy, Hash Table, Heap]
      

给一个string, 全是lowercase letter, 要求重新排列: 然后每个unique的character要有k distance apart.

跟Task Scheduler有点像, 只不过Task那道题里面还可以用其他方法求count, 这道题要求出排列结果

#### PriorityQueue + HashTable
- PriorityQueue排序+分布排列的一个经典用法.
- Count frequency and store in pq.
- Consume element of pq for k rounds, each time pick one element from queue.
- Exception: if k still has content but queue is consumed: cannot complete valid string, return "";
- space, O(n) extra space in sb, O(26) constant space with pq.
- time: O(n) to add all items



---

**12. [Best Time to Buy and Sell Stock II.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Status DP]
      
time: O(n)
space: O(1) greedy, O(n) dp

和Stock I 的区别：可以买卖多次，求总和的最大盈利.

#### 几种其他不同的思路:
- Greedy, 每次有相邻的diff符合profit条件, 就卖了, 最后把所有的diff加在一起. 计算delta, 其实简单粗暴, 也还不错.
- 如下, 从低谷找peek, sell.
- DP. (old dp solution BuyOn[], SellOn[])
- DFS计算所有(timeout).Improvement on DFS -> DP -> calculate sellOn[i] and buyOn[i], and then return buyOn[i]. 有点难想, 但是代码简单, 也是O(n)

#### Greedy
- 画图, 因为可以无限买卖, 所以只要有上升, 就有profit
- 所有卖掉的, 平移加起来, 其实就是overall best profit
- O(n)

#### 找涨幅最大的区间，买卖：
- 找到低谷，买进:peek = start + 1 时候，就是每次往前走一步;若没有上涨趋势，继续往低谷前进。
- 涨到峰顶，卖出:一旦有上涨趋势，进一个while loop，涨到底, 再加个profit.
- profit += prices[peek - 1] - prices[start]; 挺特别的。
- 当没有上涨趋势时候，peek-1也就是start, 所以这里刚好profit += 0.

#### DP, sequence dp + status
- 想知道前i天的最大profit, 那么用sequence DP: 
- dp[i]: represents 前i天的最大profit
- 当天的是否能卖, 取决于昨天是否买进, 也就是 `昨天买了或者卖了的状态`: 加状态, dp[i][0], dp[i][1]
- `买`的状态 `dp[i][0]` = 1. 今天买入, 昨天卖掉的dp[i-1][1]结果 - price[i]; 2. 今天不买, 跟昨天买的status dp[i-1][0] 结果 比较.
- `卖`的状态 `dp[i][1]` = 1. 今天卖出, 昨天买进的dp[i-1][0]结果 + price[i]; 2. 今天不卖, 跟昨天卖的status dp[i-1][1] 结果 比较.
- 注意init: 
- dp[0][0] = dp[0][1] = 0; // 0 days, 
- dp[1][0] = 0; // sell on 1st day, haven't bought, so just 0 profit.
- dp[1][0] = -prices[0]; // buy on 1st day, with cost of prices[0]

##### Rolling Array
- [i] 和 [i - 1] 相关联, roll




---

**13. [Best Time to Buy and Sell Stock with Transaction Fee.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Transaction%20Fee.java)**      Level: Medium      Tags: [Array, DP, Greedy, Sequence DP, Status DP]
      
time: O(n)
space: O(n), O(1) rolling array

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

**14. [Find the Celebrity.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Celebrity.java)**      Level: Medium      Tags: [Array, Greedy]
      
time: O(n)
space: O(1)

有n个人, 其中有个人是celebrity, 满足条件 `Celeb knows nobody; Everyone else knows the celeb`. 找到celeb

#### Understand the property
- If brutly find celeb by comparing all possible pair: take complete O(n^2) handshakes.
- Instead, we can perform pruning, or like survival mode:
- 1. Assume a celeb = 0, and compare with all i = [1~ n-1]
- 2. If `celeb candidate know i, set celeb = i` as the next candidate (ex: prev canddiate invalid when he knows i)
- 3. For last standing celeb candidate: compare with all for validation
- Why performing the last run of validation? There could be someone dropped out before we execute `know(celeb, i)`. 

##### 思考逻辑
- 先写出来[0 ~ n - 1], 最简单的方式 O(n^2) 检查, 记录每个人的状态.
- 逐渐发现, 因为 celeb 谁都不会认识, 那么当任何candidate knows anyone, 他自身就不是celeb.
- 我们可以greedy地, 一旦fail一个, 就立刻假设下一个是celeb candidate
- 最终还是要检查一遍, 避免错漏.
- 想一下happy case: 如果 celeb=0,  那么 know(celeb, i) 永远都是false, 然后 celeb一直保持0, 坚持到verify所有人.



---

**15. [Minimum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Subarray.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Subarray]
      
time: O(m)
space: O(1)

给一串数组, unsorted, can have negative/positive num. 找数组中间 subarray 数字之和的最小值

#### DP
- 看到 min value, 至少考虑dp:
- Consider last num: min sum will be (preMinSum + curr, or curr)
- Use preMinSum to cache previouly calcualted min sum, also compare with +curr.
- Have a global min to track: because the preMinSum can be dis-continuous. 
- 也可以写成 dp[i] 但是没什么必要



---

**16. [Cracking the Safe.java](https://github.com/awangdev/LintCode/blob/master/Java/Cracking%20the%20Safe.java)**      Level: Hard      Tags: [DFS, Greedy, Math]
      

#### Greedy, Iterative
- For 2 passwords, the shortest situation is both passwords overlap for n-1 chars.
- We can use a window to cut out last (n-1) substring and append with new candidate char from [k-1 ~ 0]
- Track the newly formed string; if new, add the new char to overall result
- Note: this operation will run for k^n times: for all spots of [0 ~ n - 1] each spot tries all k values [k-1 ~ 0]
- Same concept as dfs

#### DFS
- Same concept: use window to cut out tail, and append with new candidate
- do this for k^n = Math.pow(k, n) times



---

