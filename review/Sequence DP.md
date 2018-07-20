 
 
 
## Sequence DP (19)
**0. [Longest Common Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Subsequence.java)**      Level: Medium      Tags: [DP, Double Sequence DP, Sequence DP]
      

给两个string, A, B. 找这两个string里面的LCS: 最长公共字符长度 (不需要是continuous substring)

#### Double Sequence DP
- 设定dp长度为(n+1), 因为dp[i]要用来表示前i个(ith)时候的状态, 所以长度需要时i+1才可以在i位置, hold住i.
- 双序列: 两个sequence之间的关系, 都是从末尾字符看起, 分析2种情况:
- 1. A最后字符不在common sequence 或者 B最后字符不在common sequence.
- 2. A/B最后字符都在common sequence. 总体count + 1.



---

**1. [Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Edit%20Distance.java)**      Level: Hard      Tags: [DP, Double Sequence DP, Sequence DP, String]
      
time: O(MN)
Space: O(N)

两个字符串, A要变成B, 可以 insert/delete/replace, 找最小变化operation count

#### Double Sequence
- 考虑两个字符串的末尾index s[i], t[j]: 如果需要让这两个字符一样, 可能使用题目给出的三种operation: insert/delete/replace?
- 先calculate最坏的情况, 3种operation count + 1; 然后在比较match的情况.
- 注意, 在i或者j为0的时候, 变成另外一个数字的steps只能是全变.
- 第一步, 空间时间都是O(MN), O(MN)
- 滚动数组优化, 空间O(N)

##### Detail analysis
- insert: assume insert on s, `#ofOperation = (s[0 ~ i] to t[0 ~ j-1]) + 1;`
- delete: assume delete on t, `#ofOperatoin = (s[0 ~ i - 1] to t[0 ~ j]) + 1;`
- replace: replace both s and t, `#ofOperatoin = (s[0 ~ i - 1] to t[0 ~ j - 1]) + 1;`
- dp[i][j]代表了两个 sequence 互相之间的性质: s[0 ~ i] 转换成 s[0~j] 所需要的最少 operation count
- init: 当i==0, dp[0][j] = j; 每次都要 + j 个character; 同理, 当j==0, dp[i][0] = i;
- 而dp[i][j]有两种情况处理: `s[i] == t[j]` or `s[i] != t[j]`

##### 何时initialize
- 这种判断取决于经验: 如果知道initialization可以再 double for loop 里面一起做, 那么可以留着那么做
- 这样属于 `需要什么, initialize什么`
- 事后在做space optimization的时候, 可以轻易在 1st dimension 上做rolling array

#### Search
- 可以做, 但是不建议:这道题需要找 min count, 而不是search/find all solutions, 所以search会写的比较复杂, 牛刀杀鸡.



---

**2. [Climbing Stairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Climbing%20Stairs.java)**      Level: Easy      Tags: [DP, Memoization, Sequence DP]
      

每一步可以走1步或者2步, 求总共多少种方法爬完梯子.

#### Recursive + Memoization
- 递归很好写, 但是重复计算, timeout. time: O(2^n)
- O(2^n): each n can spawn 2 dfs child, at next level, it will keep spawn. Total 2^n nodes will spawn.
- 用全局变量int[] memo 帮助减少重复计算
- O(n) time, space

#### DP
- 加法原理, 最后一步被前两种走法决定: dp[i] = dp[i - 1] + dp[i - 2]
- 基础sequence DP, int[] dp = int[n + 1];
- DP[]存的是以 1-based index的状态
- dp[i]: count # of ways to finish 前i个 台阶
- 需要知道dp[n] 的状态, 但是最大坐标是[n-1], 所以int[n+1]
- dp[0]往往是有特殊状态的
- O(n) space, time

#### 序列DP, 滚动数组
- [i] only associates with [i-2], [i-1].
- %2
- O(1) space



---

**3. [K Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/K%20Edit%20Distance.java)**      Level: Hard      Tags: [DP, Double Sequence DP, Sequence DP, Trie]
      

给一串String, target string, int k. 找string array里面所有的candidate: 变化K次, 能变成target.

#### Trie
TODO

#### Double Sequence DP
- Edit Distance的follow up.
- 其实就是改一下 minEditDistance的function, 带入K作比较罢了.
- 写起来跟Edit Distance 的主要逻辑是一模一样的.
- 但是LintCode 86% test case 时候timeout. 
- Time O(mnh), where h = words.length, 如果 n ~ m, Time 就几乎是 O(n^2), 太慢.



---

**4. [Paint House.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House.java)**      Level: Easy      Tags: [DP, Sequence DP, Status DP]
      
time: O(nm), m = # of colors
space: O(nm)

要paint n个房子, 还有 nx3的cost[][]. 求最少用多少cost paint 所有房子.

#### Sequence DP
- 求dp[i]的min cost, 但是不知道最后一个房子选什么颜色, 那么就遍历最后一个房子(i - 1)的颜色
- 选中最后一个房子的颜色同时, 根据dp[i - 1]的颜色/cost + cost[i-1], 来找出最低的cost
- 考虑DP最后一个位置的情况(颜色选择):需要附带颜色status在DP[i]上: 定义二维数组, 其中一位是status
- dp[i][j]: 前i个house 刷成 j 号颜色的最小cost.
- dp[0][j] = 0: 0th house, no cost
- 计算顺序: 从每一个house开始算起 [0 ~ n], first for loop
- 然后选ith 房子的 color, 再选(i-1)th 房子的color. Double for loop, skip same color

#### Rolling Array
- 观察发现 index[i] 只跟 [i-1] 相关, 所以2位就足够, %2



---

**5. [House Robber.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber.java)**      Level: Easy      Tags: [DP, Sequence DP]
      
time: O(n)
space: O(n) or rolling array O(1)

搜刮房子, 相邻的不能碰. 每个房子里有value, 求max.

#### Sequence DP
- dp[i]: 前i个房子拿到的max gain
- 看最后结尾状态的前一个或前两个的情况，再综合考虑当下的
- 搞清楚当下[i]的和之前[i-x]的情况的关系: 不可以连着house, 那么就直接考虑 dp[i-2]的情况
- Sequence DP, new dp[n + 1];

#### Rolling Array
- [i]'只和前两个位子 [i-1], [i - 2]'相关
- 用%2来标记 [i], [i - 1], [i - 2]三个位置.
- 其他滚动时惯用curr/prev来表示坐标, 这里%2虽然抽象, 但是更加实用.




---

**6. [House Robber II.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20II.java)**      Level: Medium      Tags: [DP, Sequence DP, Status DP]
      

和House Robber I 类似, 搜刮房子, 相邻不能动. 特点是: 现在nums排成了圈, 首尾相连.

#### Sequence DP
- dp[i][status]: 在 status=[0,1] 情况下, 前i个 房子拿到的 max rob gain. status=0, 1st house robbed; status=1, 1st house skipped
- 根据dp[i-1]是否被rob来讨论dp[i]: dp[i] = Math.max(dp[i-1], dp[i - 2] + nums[i - 1]);
- 特别的是，末尾的last house 和 first house相连. 这里就需要分别讨论两种情况: 第一个房子被搜刮, 或者第一个房子没被搜刮
- be careful with edge case nums = [0], only with 1 element.
- Time,space: O(n)

#### 两个状态
- 是否搜刮了第一个房子, 分出两个branch, 可以看做两种状态.
- 可以考虑用两个DP array; 也可以加一dp维度, 补充这个状态.
- 连个维度表示的是2种状态(1st house being robbed or not); 这两种状态是平行世界的两种状态, 互不相关.

#### Rolling array
- 与House Robber I一样, 可以用%2 来操作rolling array, space reduced to O(1)



---

**7. [Paint House II.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House%20II.java)**      Level: Hard      Tags: [DP, Sequence DP, Status DP]
      
time: O(NK^2):
space: (NK)

一排n个房子, 每个房子可涂成k种颜色, 涂每个房子的价钱不一样, 用costs[][]表示. 

costs[0][1]表示涂了index是0的房子, 用了color 1.

规则: 相邻的两个房子不能使同一种颜色

求: 最少的cost 

#### DP
- 跟Paint House I 几乎一模一样, 只不过paint color更多了: k colors.
- 先考虑单纯地用dp[i]表示涂前 i 个房子的最小cost
- 但是 dp[i] 和 dp[i-1] 两个index选什么颜色会互相影响, 难讨论, 于是加状态: 序列DP被加了状态变成2D. 
- 考虑最后位, 而前一位i-1又被i位的颜色限制, 于是在考虑 min dp[i] 时候, 又多了一层iteration.
- 做dp[i][j]: # cost for 前 i 个房子, 所以要先pick (i-1) 房子的cost, 然后在找出 (i-2)房子的cost
- K种颜色 => O(NK^2)
- 如果不优化, 跟Paint House I 几乎是一模一样的代码
- Time O(NK^2), space(NK)
- Rolling array: reduce space to O(K)

#### 注意
- 序列型dp[i]表示'前i-1个'的结果. 所以dp最好设定为 int[n + 1] size. 
- 然而, 颜色在这里是状态, 所以保留在 j: [ 0~k)
- [[8]] 这样的edge case. 跑不进for loop, 所以特殊handle.

#### Optimization Solution
- Time: O(NK)
- 如果已知每次都要从cost里面选两个不同的最小cost,那么先把最小两个挑出来, 就不必有第三个for loop 找 min
- 每次在数列里面找: 除去自己之外的最小值, 利用最小值/次小值的思想
- 维持2个最值: 最小值/次小值. 
- 计算的时候, 如果除掉的不是最小值的index, 就给出最小值; 如果除掉的是最小值的index, 就给出次小值.
- Every loop: 1. calculate the two min vlaues for each i; 2. calcualte dp[i][j]
- 如何想到优化: 把表达式写出来, 然后看哪里可以优化
- 另外, 还是可以rolling array, reduce space complexity to O(K)



---

**8. [Best Time to Buy and Sell Stock III .java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III%20.java)**      Level: Hard      Tags: [Array, DP, Sequence DP]
      

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

**9. [Best Time to Buy and Sell Stock IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20IV.java)**      Level: Hard      Tags: [DP, Sequence DP]
      

有int[] price of stock, 最多做 k transactions.  求最大profit.

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

**10. [Longest Common Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Substring.java)**      Level: Medium      Tags: [DP, Double Sequence DP, Sequence DP, String]
      

#### Double Sequence DP
- 两个string, 找最值: longest common string length
- 序列型, 并且是双序列, 找两个序列 (两维的某种性质)
- dp[i][j]: 对于 A 的前i个字母, 对于 B 的前j个字母, 找最长公共substring的长度
- dp = new int[m + 1][n + 1]
- dp[i][j] = dp[i - 1][j - 1] + 1; only if A.charAt(i - 1) == B.charAt(j - 1)
- 注意track max, 最后return
- space O(n^2), time(n^2)

##### Rolling array
- 空间优化, [i] 只有和 [i - 1] 相关, 空间优化成 O(n)

#### String
- 找所有A的substring, 然后B.contains()
- track max substring length
- O(n^2) time



---

**11. [Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray.java)**      Level: Easy      Tags: [Array, DFS, DP, Divide and Conquer, PreSum, Sequence DP, Subarray]
      
time: O(n)
space: O(n), O(1) rolling array

给一串数组, unsorted, can have negative/positive num. 找数组中间 subarray 数字之和的最大值

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

**12. [Maximum Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray%20II.java)**      Level: Medium      Tags: [Array, DP, Greedy, PreSum, Sequence DP, Subarray]
      

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

**13. [Paint Fence.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20Fence.java)**      Level: Easy      Tags: [DP, Sequence DP]
      
time: O(n)
space: O(n)

#### DP
- 最多2个fence 颜色相同
- 假设i是和 i-1不同，那么结果就是 (k-1)*dp[i - 1]
- 假设i是何 i-1相同，那么根据条件，i-1和i-2肯定不同。那么所有的结果就是(k-1)*dp[i-2]
- dp[i]: count # of ways to paint 前i个 fence
- 加法原理
- time, space: O(n)
- rolling array: space O(1)

#### Previous Notes
- 这题目很有意思. 一开始分析的太复杂, 最后按照这个哥们的想法（http://yuanhsh.iteye.com/blog/2219891） 的来做，反而简单了许多。
- 设定T（n）的做法，最后题目化简以后就跟Fibonacci number一样一样的。详细分析如下。
- 做完，还是觉得如有神。本来是个Easy题，想不到，就是搞不出。




---

**14. [Word Break.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Break.java)**      Level: Medium      Tags: [DP, Sequence DP]
      
time: O(n^2)
space: O(n)

给一个String word, 和一个字典, 检查是否word可以被劈开, 而所有substring都应该是dictionary里面的words.

#### Sequence DP
- true/false problem, think about dp
- 子问题: 前i个字母, 是否可以有valid break
- 检查dp[j] && substring(j, i), for all j = [0 ~ i]
- dp = new boolean[n + 1]; dp[0] = true;
- goal: if there is a j,  `dp[j] == true && word[j, n] in dict`. Need iterate over i = [0 ~ n], also j = [0, i]
- 注意, 用set代替list, 因为要用 contains().

#### Previous notes
##### 方法2(attempt4 code)    
- 与Word BreakII用同样的DP。
- valid[i]: 记录从i到valid array末尾是否valid.

##### 方法1:（attempt3 code）
- state,rst[i]: 从[0～i] inclusive的string是否可以在dict中break开来找到？      
- function: rst[i] = true if (rst[i - j] && set.contains(s.substring(i - j, i))); j in[0~i]     
- 1. rst[i - j] 记录的是[0, i-j]这一段是否可以break后在dict找到。     
- 2. 若true，再加上剩下所有[i-j, i]都能在dict找到，那么rst[i] = rst[0, i - j] && rst[i-j, i] == true
- 优化：找dict里面最长string, 限制j的增大。




---

**15. [Best Time to Buy and Sell Stock I.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20I.java)**      Level: Easy      Tags: [Array, DP, Sequence DP]
      

给个array of stock prices, 限制能交易(买/买)一轮, 问如何找到最大profit.

#### 理解意思是关键
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
- 其中很多都是没必要的计算：[7, 1, 5, 3, 6, 4]。 if we know buyin with 1 is cheapest, we don't need to buyin at 5, 3, 6, 4 later on; we'll only sell on higher prices.



---

**16. [Best Time to Buy and Sell Stock II.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Status DP]
      
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

**17. [Best Time to Buy and Sell Stock with Transaction Fee.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Transaction%20Fee.java)**      Level: Medium      Tags: [Array, DP, Greedy, Sequence DP, Status DP]
      
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

**18. [Minimum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Subarray.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Subarray]
      
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

