 
 
 
## Status DP (5)
**0. [Paint House.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House.java)**      Level: Easy      Tags: [DP, Sequence DP, Status DP]
      
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

**1. [House Robber II.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20II.java)**      Level: Medium      Tags: [DP, Sequence DP, Status DP]
      

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

**2. [House Robber III.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20III.java)**      Level: Medium      Tags: [DFS, DP, Status DP, Tree]
      

Houses被arrange成了binary tree, 规则还是一样, 连续相连的房子不能同时抄.

求Binary Tree neighbor max 能抄多少.

#### DFS
- 判断当下的node是否被采用，用一个boolean来表示. 
- 如果curr node被采用，那么下面的child一定不能被采用.
- 如果curr node不被采用，那么下面的children有可能被采用，但也可能略过，所以这里用Math.max() 比较一下两种可能有的dfs结果。
- dfs重复计算:每个root都有4种dive in的可能性, 假设level高度是h, 那么时间O(4^(h)), where h = logN, 也就是O(n^2)

#### DP, DFS
- 并不是单纯的DP, 是在发现DFS很费劲后, 想能不能代替一些重复计算?
- 基本思想是dfs解法一致: 取root找最大值, 或者不取root找最大值
- 在root上DFS, 不在dfs进入前分叉; 每一个level按照状态来存相应的值: dp[0] root not picked, dp[1] root picked.
- Optimization: DP里面, 一口气找leftDP[]会dfs到最底层, 然后自下向上做计算
- 这个过程里面, 因为没有在外面给dfs()分叉, 计算就不会重叠, 再也不用回去visit most-left-leaf了, 算过一遍就完事.
- 然而, 普通没有dp的dfs, 在算完visited的情况下的dfs, 还要重新dfs一遍!visited的情况.
- Space O(h), time O(n), 或者说是O(2^h), where h = log(n)

#### DP 特点
- 不为状态而分叉dfs
- 把不同状态model成dp
- 每一个dfs都return一个based on status的 dp array.
- 等于一次性dfs计算到底, 然后back track, 计算顶部的每一层.
- DP 并不一定要是以n为base的. 也可以是局部的去memorize状态->value.



---

**3. [Paint House II.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House%20II.java)**      Level: Hard      Tags: [DP, Sequence DP, Status DP]
      
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

**4. [Best Time to Buy and Sell Stock II.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Status DP]
      
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

