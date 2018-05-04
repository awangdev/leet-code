 
 
 
## Sequence DP (14)
**0. [Coin Change.java](https://github.com/awangdev/LintCode/blob/master/Java/Coin%20Change.java)**      Level: Medium
      

给一串不同数额的coins, 和total amount to spent. 求 最少 用多少个coin可以组合到这个amount. 每种coins个数不限量.

#### DP
- 找对方程dp[x], 积累到amount x最少用多少个coin: #coin是value, index是 [0~x].
- 子问题的关系是: 如果用了一个coin, 那么就应该是f[x - coinValue]那个位置的#coins + 1

##### initialization
- 处理边界, 一开始0index的时候, 用value0. 
- 中间利用Integer.MAX_VALUE来作比较, initialize dp[x]
- 注意, 一旦 Integer.MAX_VALUE + 1 就会变成负数. 这种情况会在coin=0的时候发生.

##### Optimization
- 方法1: 直接用Integer.MAX_VALUE
- 方法2: 用-1, 稍微简洁一点, 每次比较dp[i]和 dp[i - coin] + 1, 然后save. 不必要做多次min比较.

#### Memoization
- dp[i] 依然表示: min # of coints to make amount i
- initialize dp[i] = Integer.MAX_VALUE
- 先选最后一步(遍历coins),  然后dfs做同样的操作
- 记录dp[amount] 如果已经给过value, 不要重复计算, 直接return.
- 但是这道题没必要强行做memoization, 普通DP的状态和方程相对来说很好找到



---

**1. [Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Subsequence.java)**      Level: Medium
      

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

**2. [Climbing Stairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Climbing%20Stairs.java)**      Level: Easy
      

#### Recursive + Memoization
- 递归很好写, 但是重复计算, timeout. time: O(2^n)
- O(2^n): each n can spawn 2 dfs child, at next level, it will keep spawn. Total 2^n nodes will spawn.
- 用全局变量int[] memo 帮助减少重复计算
- O(n) time, space

#### DP
- 最后一步被前两种走法决定: dp[i] = dp[i - 1] + dp[i - 2]
- 基础sequence DP, int[] dp = int[n + 1];
- DP[]存的是以 1-based index的状态
- 需要知道dp[n] 的状态, 但是最大坐标是[n-1], 所以int[n+1]
- dp[0]往往是有特殊状态的
- O(n) space, time

#### 序列DP, 滚动数组
- [i] only associates with [i-2], [i-1].
- %2
- O(1) space



---

**3. [Coin Change 2.java](https://github.com/awangdev/LintCode/blob/master/Java/Coin%20Change%202.java)**      Level: Medium
      

给串数字, target amount, 求总共多少种方式可以reach the amount.

#### DP
- O(MN): M, total target amount; N: size of coins
- 状态: dp[i]: sum of ways that coins can add up to i.
- Function: dp[j] += dp[j - coins[i]];
- Init: dp[0] = 1 for ease of calculation; other dp[i] = 0 by default
- note: 避免重复count, 所以 j = coins[i] as start
- 注意 coins 可能需要放在for loop 外面, 而主导换coin的流程. 
- 类似于: 网格dp, unique path 里面的2种走法: 从上到下, 从左到右



---

**4. [Paint House.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House.java)**      Level: Easy
      

要paint n个房子, 还有 nx3的cost[][]. 求最少用多少cost paint 所有房子.

#### Sequence DP
- 求知道dp[n]的min cost, 但是不知道最后一个房子选什么颜色
- 那么就遍历最后一个房子(i - 1)的颜色
- 选中最后一个房子的颜色同时, 来选择 (i - 2)的颜色, 来找出最低的cost
- 考虑DP最后一个位置的情况. 发现给出了一些特殊条件, 需要附带在DP[i]上,
- 那么就定义二维数组

#### Rolling Array
- 观察发现 index[i] 只跟 [i-1] 相关, 所以2位就足够, %2


---

**5. [House Robber.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber.java)**      Level: Easy
      

搜刮房子, 相邻的不能碰. 每个房子里有value, 求max.

#### Sequence DP
- 看最后结尾状态的前一个或前两个的情况，再综合考虑当下的
- 思考的适合搞清楚当下的和之前的情况的关系
- Sequence DP, new dp[n + 1];

#### Rolling Array
- [i]'只和前两个位子 [i-1], [i - 2]'相关
- 用%2来标记 [i], [i - 1], [i - 2]三个位置.
- 其他滚动时惯用curr/prev来表示坐标, 这里%2虽然抽象, 但是更加实用.




---

**6. [House Robber II.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20II.java)**      Level: Medium
      

和House Robber I 类似, 搜刮房子, 相邻不能动. 特点是: 现在nums排成了圈, 首尾相连.

#### Sequence DP
- 根据dp[i-1]是否被rob来讨论dp[i]: dp[i] = Math.max(dp[i-1], dp[i - 2] + nums[i - 1]);
- 特别的是，末尾的last house 和 first house相连. 这里就需要分别讨论两种情况: 第一个房子被搜刮, 或者第一个房子没被搜刮
- be careful with edge case nums = [0], only with 1 element.

#### 两个状态
- 是否搜刮了第一个房子, 分出两个branch, 可以看做两种状态.
- 可以考虑用两个DP array; 也可以加一dp维度, 补充这个状态.
- 连个维度表示的是2种状态(1st house being robbed or not); 这两种状态是平行世界的两种状态, 互不相关.

#### Rolling array
与House Robber I一样, 可以用%2 来操作rolling array



---

**7. [Paint House II.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House%20II.java)**      Level: Hard
      

一排n个房子, 每个房子可涂成k种颜色, 涂每个房子的价钱不一样, 用costs[][]表示. 

costs[0][1]表示涂了index是0的房子, 用了color 1.

规则: 相邻的两个房子不能使同一种颜色

求: 最少的cost 

#### DP
- 先考虑单纯地用dp[i]表示涂前 i 个房子的最小cost
- 但是 dp[i] 和 dp[i-1] 两个index选什么颜色会互相影响, 难讨论, 于是加状态: 序列DP被加了状态变成2D. 
- 考虑最后位, 而前一位i-1又被i位的颜色限制, 于是在考虑 min dp[i] 时候, 又多了一层iteration.
- 做dp[i][j]: # cost for 前 i 个房子, 所以要先pick (i-1) 房子的cost, 然后在找出 (i-2)房子的cost
- K种颜色 => O(NK^2)
- 如果不优化, 跟Paint House I 几乎是一模一样的代码


#### 注意
- 序列型dp[i]表示'前i-1个'的结果. 所以dp最好设定为 int[n + 1] size. 
- 然而, 颜色在这里是状态, 所以保留在 j: [ 0~k)
- [[8]] 这样的edge case. 跑不进for loop, 所以特殊handle.

#### Optimization
- O(NK)
- 如果已知每次都要从cost里面选两个不同的最小cost,那么先把最小两个挑出来, 就不必有第三个for loop 找 min
- 每次在数列里面找: 除去自己之外的最小值, 利用最小值/次小值的思想
- 维持2个最值: 最小值/次小值. 
- 计算的时候, 如果除掉的不是最小值的index, 就给出最小值; 如果除掉的是最小值的index, 就给出次小值.
- Every loop: 1. calculate the two min vlaues for each i; 2. calcualte dp[i][j]
- 如何想到优化: 把表达式写出来, 然后看哪里可以优化



---

**8. [Best Time to Buy and Sell Stock I.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20I.java)**      Level: Easy
      

给个array of stock prices, 限制能交易(买/买)一轮, 问如何找到最大profit.

#### 理解意思是关键
- 每天都就交易价格，n天只让买卖一次，那就找个最低价买进，找个最高价卖出
- 记录每天最小值Min是多少. O(n)
- 每天都算和当下的Min买卖，profit最大多少.

#### DP
- Find min value for first i items, new dp[n + 1].
- 然后用当天的price做减法算max profit.
- Time, Space: O(n)
- 更进一步, 用一个min来表示min[i], 因为计算中只需要当下的min.

#### Rolling array
- index i only depend on [i - 2]
- Space O(n)

#### Brutle Failed
- 每天都试着买进，然后之后的每一天尝试卖出. double for loop, O(n^2). timeout.
- 其中很多都是没必要的计算：[7, 1, 5, 3, 6, 4]。 if we know buyin with 1 is cheapest, we don't need to buyin at 5, 3, 6, 4 later on; we'll only sell on higher prices.



---

**9. [Best Time to Buy and Sell Stock II.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.java)**      Level: Easy
      

和Stock I 的区别：可以买卖多次，求总和的最大盈利.

#### 几种其他不同的思路:
- Greedy, 每次有相邻的diff符合profit条件, 就卖了, 最后把所有的diff加在一起. 计算delta, 其实简单粗暴, 也还不错.
- 如下, 从低谷找peek, sell.
- DP. (old dp solution BuyOn[], SellOn[])
- DFS计算所有(timeout).Improvement on DFS -> DP -> calculate sellOn[i] and buyOn[i], and then return buyOn[i]. 有点难想, 但是代码简单, 也是O(n)

#### Greedy
- 画图, 因为可以无限买卖, 所以只要有上升, 就卖
- 所有卖掉的, 平移加起来, 其实就是overall best profit
- O(n)

#### 找涨幅最大的区间，买卖：
- 找到低谷，买进:peek = start + 1 时候，就是每次往前走一步;若没有上涨趋势，继续往低谷前进。
- 涨到峰顶，卖出:一旦有上涨趋势，进一个while loop，涨到底, 再加个profit.
- profit += prices[peek - 1] - prices[start]; 挺特别的。
- 当没有上涨趋势时候，peek-1也就是start, 所以这里刚好profit += 0.

#### DP
- 想知道前i天的最大profit, 那么用sequence DP
- 当天的是否能卖, 取决于昨天是否买进, 也就是昨天买了或者卖了的状态: 加状态, 2D DP
- 如果今天是卖的状态, 那么昨天: 要么买进了, 今天 +price 卖出; 要么昨天刚卖, 今天不可能再卖, profit等同.
- 如果今天是买的状态, 那么昨天: 要么卖掉了, 今天 -price 买入; 要么昨天刚卖, 今天不可能再买, profit等同.

#### Rolling Array
- [i] 和 [i - 1] 相关联, roll




---

**10. [Best Time to Buy and Sell Stock III .java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III%20.java)**      Level: Hard
      

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
- 注意: 只有在状态index: 0, 2, 4, 也就是卖掉股票的时候, 猜可以积累profit

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

**11. [Best Time to Buy and Sell Stock IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20IV.java)**      Level: Hard
      

#### DP
- 根据StockIII, 不难发现StockIV就是把状态划分为2k+1份. 那么同样的代码, 移植.

##### 注意1: 
- 如果k很大, k>n/2, 那么长度为n的数组里面, 最多也只能n/2个transaction
- 那么题目简化为stockII, 给n数组, 无限次transaction.
- 注意, status的数量是 2k+1
- Time O(NK), Space O(2k+1) to store the status

##### 注意2: 
- 最后状态是'没有stock'的都该考虑, 做一个 for 循环比较max. 
- 当然, 来一个profit variable, 不断比较, 也是可以的.

#### 方法2
- (previous notes, 熟练第一种方法的思考就可以)
- 记得要理解：为什么 i-1天的卖了又买，可以和第 i 天的卖合成一次交易？    
- 因为每天交易的price是定的。所以卖了又买，等于没卖！这就是可以合并的原因。要对价格敏感啊少年。
- Inspired from here: http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html

##### 局部最优解 vs. 全局最优解：     
- local[i][j] = max(global[i – 1][j – 1] + diff, local[i – 1][j] + diff)    
- global[i][j] = max(global[i – 1][j], local[i][j])     
- local[i][j]: 第i天，当天一定进行第j次交易的profit     
- global[i][j]: 第i天，总共进行了j次交易的profit.

- local[i][j]和global[i][j]的区别是：local[i][j]意味着在第i天一定有交易（卖出）发生。    
- 当第i天的价格高于第i-1天（即diff > 0）时，那么可以把这次交易（第i-1天买入第i天卖出）跟第i-1天的交易（卖出）合并为一次交易，即local[i][j]=local[i-1][j]+diff；    
- 当第i天的价格不高于第i-1天（即diff<=0）时，那么local[i][j]=global[i-1][j-1]+diff，而由于diff<=0，所以可写成local[i][j]=global[i-1][j-1]。    
- (Note:在我下面这个solution里面没有省去 +diff）   

- global[i][j]就是我们所求的前i天最多进行k次交易的最大收益，可分为两种情况：    
- 如果第i天没有交易（卖出），那么global[i][j]=global[i-1][j]；     
- 如果第i天有交易（卖出），那么global[i][j]=local[i][j]。    





---

**12. [Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray.java)**      Level: Easy
      

给一串数组, 找数组中间 subarray 数字之和的最大值

#### Sequence DP
- dp[i]: 前i个element, 包括element i 在内的 continous subsequence 的最大sum是多少?
- 因为continous sequence, 所以不满足条件的时候, 会断: track overall max,
- init dp[0] = 0; max = MIN_VALUE 因为有负数
- Time, space O(n)
- Rolling array, space O(1)


#### Previous Notes
##### 方法1
- 比较像DP, 维持一个sums[i]: 从i向前位数, 所有正数的和. 一旦sums[i - 1]<0, 意味着sums[i-1]对maxSum没有好处,
- 那么就assign: sums[i]=nums[i]
- 这个做法比较中规中矩, makes sense

##### 方法2(better)
- 想着用一用prefix sum. 把值一个个叠加。
- 然后presum[j] - presum[i- 1] 就是 (i,j)之间的和。



---

**13. [Maximum Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray%20II.java)**      Level: Medium
      

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

