 
 
 
## DP (51)
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
**1. [Maximum Product Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Product%20Subarray.java)**      Level: Medium
      

从一组数列(正负都有)里面找一串连续的子序列, 而达到乘积product最大值.

#### DP
- 求最值, 想到DP. Time/Space O (n)
- 两个特别处: 
- 1. 正负数情况, 需要用两个DP array. 
- 2. continuous prodct 这个条件决定了在Math.min, Math.max的时候, 
- 是跟nums[x]当下值比较的, 如果当下值更适合, 会舍去之前的continous product, 然后重新开始.
- 这也就注定了需要一个global variable 来hold result.

#### Space optimization, rolling array
- maxProduct && minProduct 里面的 index i, 都只能 i - 1相关, 所以可以省去redundant operatoins
- Time: O(n), space: O(1)



---
**2. [k Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/k%20Sum.java)**      Level: Hard
      

DP. 公式如何想到, 还需要重新理解.

dp[i][j][m]: # of possibilities such that from j elements, pick m elements and sum up to i. 
i: [0~target]

dp[i][j][m] = dp[i][j-1][m] + dp[i - A[j - 1]][j-1][m-1]
            (i not included)   (i included)



---
**3. [Longest Continuous Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Continuous%20Increasing%20Subsequence.java)**      Level: Easy
      

简单的DP:dp[i]存在i点时连续上升#. dp[i]时可能为0, 一旦断开.

方法1: dp[i], maintain max
方法2: 用一个数存current count,  maintain max. 也是DP, 稍微简化.



---
**4. [Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Subsequence.java)**      Level: Medium
      

无序数组, 找最长的上升(不需要连续)数组 的长度. 先做O(n^2), 然后可否O(nLogN)?

#### DP, double for loop, O(n^2)
- 考虑nums[i]的时候, 在[0, i) 里count有多少小于nums[i]
- 对于所有 i in [0, n), 最常的increasing序列有多少length?
- max需要在全局维护: nums是无序的, nums[i]也可能是一个很小的值, 所以末尾dp[i]并不是全局的max, 而只是对于nums[i]的max.
- 正因此, 每个nums[i]都要和每个nums[j] 作比较, j < i.
- 时间复杂度  O(n^2)


#### O(nLogN)
- 维持一个list of increasing sequence
- 这个list其实是一个base-line, 记录着最低的increasing sequence.
- 当我们go through all nums的时候, 如果刚好都是上升, 直接append
- 如果不上升, 应该去list里面, 找到最小的那个刚好大于new num的数字, 把它换成num
- 这样就完成了baseline. 举个例子, 比如替换的刚好是在list最后一个element, 等于就是把peak下降了, 那么后面其他的数字就可能继续上升.
- '维护baseline就是一个递增的数列' 的证明, 还没有仔细想.



---
**5. [Unique Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree.java)**      Level: Medium
      

Not quite clear.
根据左右分割而总结出了原理, 每次分割, 左右两边都会有一定数量的permutation, 总体上的情况数量当然是相乘.
然后每一个不同的分割点都加一遍:
f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

然后把数学公式转换成DP的方程, 有点玄学的意思啊! 不好想.



---
**6. [Unique Paths II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Paths%20II.java)**      Level: Medium
      

跟unique path的grid一样, 目标走到右下角, 但是grid里面可能有obstacle, 不能跨越. 求unique path 的count.

#### 坐标DP
- dp[i][j]: # of paths to reach grid[i][j]
- dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
- 考虑最终结尾需要的状态:如何组成,写出公式.
- 公式中注意处理能跳掉的block, marked as 1. '到不了', 即为 0 path in dp[i][j].



---
**7. [Counting Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Counting%20Bits.java)**      Level: Medium
      

Bit题目 用num的数值本身表示DP的状态.
这里, dp[i] 并不是和 dp[i-1]有逻辑关系; 而是dp[i] 和dp[i>>1], 从binary representation看出有直接关系.



---
**8. [Bomb Enemy.java](https://github.com/awangdev/LintCode/blob/master/Java/Bomb%20Enemy.java)**      Level: Medium
      

分方向考虑的方法很容易想到,但是四个方向移动的代码比较繁琐.
往上炸: 要从顶向下考虑
往下炸: 要从下向上考虑

熟练写2D array index 的变换.



---
**9. [Decode Ways.java](https://github.com/awangdev/LintCode/blob/master/Java/Decode%20Ways.java)**      Level: Review
      

确定末尾的2种状态: single letter or combos. 然后计算出单个letter的情况, 和双数的情况
note: calculate number from characters, need to - '0' to get the correct integer mapping.
注意: check value != '0', 因为'0' 不在条件之中(A-Z)




---
**10. [House Robber.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber.java)**      Level: Easy
      

搜刮房子, 相邻的不能碰. 每个房子里有value, 求max.

#### Sequence DP
- 看最后结尾状态的前一个或前两个的情况，再综合考虑当下的
- 思考的适合搞清楚当下的和之前的情况的关系

#### Rolling Array
- [i]'只和前两个位子 [i-1], [i - 2]'相关
- 用%2来标记 [i], [i - 1], [i - 2]三个位置.
- 其他滚动时惯用curr/prev来表示坐标, 这里%2虽然抽象, 但是更加实用.




---
**11. [House Robber II.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20II.java)**      Level: Medium
      

和House Robber I 类似, 搜刮房子, 相邻不能动. 特点是: 现在nums排成了圈, 首尾相连.

#### Sequence DP
- 根据dp[i-1]是否被rob来讨论dp[i]: dp[i] = Math.max(dp[i-1], dp[i - 2] + nums[i - 1]);
- 特别的是，末尾的last house 和 first house相连. 这里就需要分别讨论两种情况: 第一个房子被搜刮, 或者第一个房子没被搜刮

#### 两个状态
- 是否搜刮了第一个房子, 分出两个branch, 可以看做两种状态.
- 可以考虑用两个DP array; 也可以加一dp维度, 补充这个状态.
- 连个维度表示的是2种状态(1st house being robbed or not); 这两种状态是平行世界的两种状态, 互不相关.

#### Rolling array
与House Robber I一样, 可以用%2 来操作rolling array



---
**12. [Best Time to Buy and Sell Stock I.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20I.java)**      Level: Easy
      

理解意思是关键:
   每天都就交易价格，n天只让买卖一次，那就找个最低价买进，找个最高价卖出。
   记录每天最小值Min是多少。O(n)
   每天都算和当下的Min买卖，profit最大多少.

这里就可以DP, memorize the min[i]: the minimum among [0 ~ i]; 然后用当天的price做减法算max.
更进一步, 用一个min来表示min[i], 因为计算中只需要当下的min.


Brutle:
每天都试着买进，然后之后的每一天尝试卖出. double for loop, O(n^2). timeout.
其中很多都是没必要的计算：[7, 1, 5, 3, 6, 4]。 if we know buyin with 1 is cheapest, we don't need to buyin at 5, 3, 6, 4 later on; we'll only sell on higher prices.



---
**13. [Best Time to Buy and Sell Stock III .java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III%20.java)**      Level: Hard
      

比stock II 多了一个限制：只有2次卖出机会。

方法1:
DP加状态: 只卖2次, 把买卖分割成5个状态模块.
在模块index 0, 2, 4: 没有持有股票. 1. 一直在此状态, max profit不变; 2. 刚卖掉, dp[i][前状态] + profit
在模块index 1, 3: 持有股票. 1. 一直在此状态, daily profit. 2. 刚刚买进, 状态改变, 但是没有profit yet: dp[i][前状态]

注意: 把每天的partial profit (diff)加在一起, 最终的overall profit是一样的. 唯一更好的是, 不需要记录中间买入的时间点.

方法2:
也就是：找峰头；然后往下再找一个峰头。

怎么样在才能Optimize两次巅峰呢？

从两边同时开始找Max！（棒棒的想法）

   leftProfit是从左往右，每个i点上的最大Profit。
   rightProfit是从i点开始到结尾，每个点上的最大profit.
   那么在i点上，就是leftProfit，和右边rightProfit的分割点。在i点，leftProfit+rightProfit相加，找最大值。

三个O(n),还是O(n)



---
**14. [Best Time to Buy and Sell Stock IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20IV.java)**      Level: Hard
      

方法1:
DP. 根据StockIII, 不难发现StockIV就是把状态划分为2k+1份. 那么同样的代码, 移植.
注意1: 如果k很大, k>n/2, 那么长度为n的数组里面, 最多也只能n/2个transaction, 
	  那么题目简化为stockII, 给n数组, 无限次transaction.
注意2: n可以用滚动数组(prev/now)代替.
注意3: 最后状态是'没有stock'的都该考虑, 做一个 for 循环比较max. 当然, 来一个profit variable,
		不断比较, 也是可以的.

方法2:
记得要理解： 为什么 i-1天的卖了又买，可以和第 i 天的卖合成一次交易？    
   因为每天交易的price是定的。所以卖了又买，等于没卖！这就是可以合并的原因。要对价格敏感啊少年。

Inspired from here:    
http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html

局部最优解 vs. 全局最优解：     
   local[i][j] = max(global[i – 1][j – 1] + diff, local[i – 1][j] + diff)    
   global[i][j] = max(global[i – 1][j], local[i][j])     

local[i][j]: 第i天，当天一定进行第j次交易的profit     
global[i][j]: 第i天，总共进行了j次交易的profit.

local[i][j]和global[i][j]的区别是：local[i][j]意味着在第i天一定有交易（卖出）发生。    
   当第i天的价格高于第i-1天（即diff > 0）时，那么可以把这次交易（第i-1天买入第i天卖出）跟第i-1天的交易（卖出）合并为一次交易，即local[i][j]=local[i-1][j]+diff；    
   当第i天的价格不高于第i-1天（即diff<=0）时，那么local[i][j]=global[i-1][j-1]+diff，而由于diff<=0，所以可写成local[i][j]=global[i-1][j-1]。    
   (Note:在我下面这个solution里面没有省去 +diff）   

global[i][j]就是我们所求的前i天最多进行k次交易的最大收益，可分为两种情况：    
   如果第i天没有交易（卖出），那么global[i][j]=global[i-1][j]；     
   如果第i天有交易（卖出），那么global[i][j]=local[i][j]。    





---
**15. [Paint House II.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House%20II.java)**      Level: Review
      

一般的DP被加了状态变成2D. 
考虑最后位, 而前一位i-1又被i位的颜色限制, 于是在考虑 min dp[i] 时候, 又多了一层iteration.
所以变成了O(NK^2)

注意: 
1. 序列型dp[i]表示'前i-1个'的结果. 所以dp最好设定为 int[n + 1] size. 
    然而, 颜色在这里是状态, 所以保留在 j: [ 0~k)
2. [[8]] 这样的edge case. 跑不进for loop, 所以特殊handle.

Optimization:
如果已知每次都要从cost里面选两个不同的最小cost,那么先把最小挑出来, 就不必有第三个for loop
O(NK)



---
**16. [Backpack.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack.java)**      Level: Medium
      

考虑: 用i个item (可跳过地取), 是否能装到weight w?
需要从'可能性'的角度考虑, 不要搞成单一的最大值问题.

1. 背包可装的物品大小和总承重有关.
2. 不要去找dp[i]前i个物品的最大总重, 找的不是这个. 
    dp[i]及时找到可放的最大sum, 但是i+1可能有更好的值, 把dp[i+1]变得更大更合适.

boolean[][] dp[i][j]表示: 有前i个item, 用他们可否组成size为j的背包? true/false.
(反过来考虑了，不是想是否超过size j, 而是考虑是否能拼出exact size == j)
**注意**: 虽然dp里面一直存在i的位置, 实际上考虑的是在i位置的时候, 看前i-1个item.

多项式规律:
1. picked A[i-1]: 就是A[i-1]被用过, weight j 应该减去A[i-1]. 那么dp[i][j]就取决于dp[i-1][j-A[i-1]]的结果.
2. did not pick A[i-1]: 那就是说, 没用过A[i-1], 那么dp[i][j]就取决于上一行d[i-1][j]
dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]]

结尾:
跑一遍dp 最下面一个row. 从末尾开始找, 最末尾的一个j (能让dp[i][j] == true)的, 就是最多能装的大小 :)   

时间，空间都是：O(mn)




---
**17. [Perfect Squares.java](https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Squares.java)**      Level: Medium
      

分割型. 考虑最后的数字: 要是12割个1出来, 剩下11怎么考虑? 割个4出来,剩下8怎么考虑?
就变成了dp = Min{dp[i - j^2] + 1}

时间复杂度: 乍一看是O(n*sqrt(n)). 实际也是. 但如何推导?
考虑sqrt(1) + sqrt(2) + ....sqrt(n):找这个的upper bound and lower bound.
最后发现它的两边是 A*n*sqrt(n) <= actual time complexity <= B*n*sqrt(n)
那么就是O(n*sqrt(n))啦

Previous Notes:
一开始没clue.看了一下提示。

１.　第一步想到了，从数学角度，可能是从最大的perfect square number开始算起。
２.　然后想法到了dp， 假设最后一步用了最大的maxSqrNum, 那么就在剩下的 dp[i - maxSqrNum^2] +１　不就好了？
３.　做了，发现有个问题．．．最后一步选不选maxSqrNum?  比如12就是个例子。
	然后就根据提示，想到BFS。顺的。 把1～maxSqrNum 都试一试。找个最小的。
	看我把12拆分的那个example. 那很形象的就是BFS了。
	面试时候，如果拆分到这个阶段不确定，那跟面试官陶瓷一下，说不定也就提示BFS了。



---
**18. [Palindrome Partitioning II.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Partitioning%20II.java)**      Level: Hard
      

Find minimum cut: 分割型DP
考虑[j, i - 1] 是否是回文串, 如果是, 那么: dp[i]= min{d[j] + 1}.

利用palindrome的性质, 可以算出 boolean palindrome[i, j]的情况. 
这样就给我们的问题合理降维, 目前是time: O(n^2). 
不然求一次palindrome, 就是n, 会变成O(n^3)

Previous Notes:
Double for loop 检查每种substring string (i~j). 若i,j相邻或者同点，那么肯定isPal；否则，i,j之间的（i+1, j-1）一定得isPal。
看上去，在检查i,j的时候，中间按的（i+1, j-1）怎么可能先知道？ 其实不然..在j慢慢长大的时候，所有的0~j的substring都检查过。所以isPal[i+1][j-1]一定是已经知道结果的。

okay.那么假如以上任意一种情况成立，也就是说isPal[i][j] == true。那就要判断，切到第一层循环参数j的末尾点时，有多少种切法？
想法很顺：我们naturally会想到，把i之前的cut加上i~j之间发生的不就好了。
反正现在j不变，现在就看吧i定在哪里，cut[i - 1]是否更小/最小； 再在cut[i-1]基础上+1就完了。
    当然，如果i==0, 而 i~j又是isPal,那没啥好谈的，不必切，0刀。

最终，刷到cut[s.length() - 1] 也就是最后一点。 return的理所应当。


---
**19. [Backpack V.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20V.java)**      Level: Medium
      

与背包1不同: 这里不是check可能性(OR)或者最多能装的size是多少; 而是计算有多少种正好fill的可能性.

对于末尾, 还是两种情况:
1. i-1位置没有加bag
2. i-1位置加了bag

两种情况可以fill满w的情况加起来, 就是我们要的结果.

如常: dp[n + 1][w + 1]

方法1:
Space: O(MN)
Time: O(MN)

方法2:
空间优化, 滚动数组
Space: O(M) * 2 = O(M)
Time: O(MN)

方法3:
降维打击, 终极优化: 分析row(i-1)的规律, 发现所有row(i)的值, 都跟row(i-1)的左边element相关, 而右边element是没用的.
所以可以被override.
Space: O(M), 真*一维啊!
Time: O(MN)



---
**20. [Backpack VI.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20VI.java)**      Level: Medium
      

拼背包时, 可以有重复item, 所以考虑'最后被放入的哪个unique item' 就没有意义了.
背包问题, 永远和weight分不开关系.
这里很像coin chagne: 考虑最后被放入的东西的value/weigth, 而不考虑是哪个.

1维: dp[w]: fill了weigth w 有多少种方法. 前面有多少种可能性, 就sum多少个:
dp[w] = sum{dp[w - nums[i]]}, i = 0~n





---
**21. [Copy Books.java](https://github.com/awangdev/LintCode/blob/master/Java/Copy%20Books.java)**      Level: Hard
      

#### 方法1: Binary Search
- 根据: 每个人花的多少时间(time)来做binary search: 每个人花多久时间, 可以在K个人之内, 用最少的时间完成?
- time variable的范围不是index, 也不是page大小. 而是[minPage, pageSum]
- validation 的时候注意3种情况: 人够用 k>=0, 人不够所以结尾减成k<0, 还有一种是time(每个人最多花的时间)小于当下的页面, return -1
- O(nLogM). n = pages.length; m = sum of pages.

#### 方法2: DP
k个人copy完i本书.
定义Integer.MAX_VALUE的地方需要注意.
Review: 为什么有i level的iteration? Chapter4.1



---
**22. [Backpack II.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20II.java)**      Level: Medium
      

做了Backpack I, 这个就如出一辙, 只不过: dp存的不是w可否存成功true/false. dp存的是加上sum value的最大值.
想法还是，选了A[i - 1] 或者没选A[i - 1]时候不同的value值.

O(m)的做法:   
想想，的确我们只care 最后一行，所以一个存value的就够了.
注意：和bakcpackI的 O(m)一样的，j是倒序的。如果没有更好的j，就不要更新。就是这个道理.

注意: 如果无法达到的w, 应该mark as impossible. 一种简单做法是mark as -1 in dp. 
如果有负数value, 就不能这样, 而是要开一个can[i][w]数组, 也就是backpack I 的原型.




---
**23. [Backpack III.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20III.java)**      Level: Review
      

可以无限使用物品, 就失去了last i, last unique item的意义: 因为可以重复使用.

1. 所以可以转换一个角度: 用i种物品, 拼出w, 并且满足题目条件(max value).
这里因为item i可以无限次使用, 所以要遍历使用了多少次K.

2. K虽然可以无限, 但是也被 k*A[i]所限制: 最大不能超过背包大小.

这样一来, 就close loop, 可以做了.

优化: Review
降维: 需要画图review
变成1个一位数组: 看双行的左右计算方向



---
**24. [Longest Palindromic Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Palindromic%20Subsequence.java)**      Level: Medium
      

区间型动态规划. 
1. 用[i][j]表示区间的首尾
2. 考虑3个情况: 砍头, 砍尾, 砍头并砍尾 (考虑首尾关系)
3. Iteration一定是以i ~ j 之间的len来看的. len = j - i + 1; 那么反推, 如果len已知, j = len + i -1;

注意考虑len == 1, len == 2是的特殊情况.



---
**25. [Scramble String.java](https://github.com/awangdev/LintCode/blob/master/Java/Scramble%20String.java)**      Level: Hard
      

- 给两个string S, T. 检验他们是不是scramble string.
- scramble string 定义: string可以被分拆成binary tree的形式, 也就是切割成substring;
- 旋转了不是leaf的node之后, 形成新的substring, 这就是原来string的 scramble.


#### Range DP 区间型
- 降维打击, 分割, 按照长度来dp.
- dp[i][j][k]: 数组S从index i 开始, T从index j 开始, 长度为k的子串, 是否为scramble string

##### Break down
- 一切两半以后, 看两种情况: , 或者不rotate这两半. 对于这些substring, 各自验证他们是否scramble.
- 不rotate分割的两半: S[part1] 对应  T[part1] && S[part2] 对应  T[part2]. 
- rotate分割的两半: S[part1] 对应  T[part2] && S[part2] 对应  T[part1]. 

##### Initialization
- len == 1的时候, 其实无法旋转, 也就是看S,T的相对应的index是否字符相等.
- initialization非常非常重要. 很神奇, 这个initailization 打好了DP的基础, 后面一蹴而就, 用数学表达式就算出了结果.
- input s1, s2 在整个题目的主要内容里面, 几乎没有用到, 只是用在initialization时候.
- More details, 看解答



---
**26. [Best Time to Buy and Sell Stock with Cooldown.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Cooldown.java)**      Level: Medium
      

Sequence DP
跟StockIII很像. 分析好HaveStock && NoStock的状态, 然后看最后一步.



---
**27. [Longest Common Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Subsequence.java)**      Level: Medium
      

给两个string, A, B. 找这两个string里面的LCS: 最长公共字符长度 (不需要是continuous substring)

#### Double Sequence DP
- 设定dp长度为(n+1), 因为dp[i]要用来表示前i个(ith)时候的状态, 所以长度需要时i+1才可以在i位置, hold住i.
- 双序列: 两个sequence之间的关系, 都是从末尾字符看起, 分析2种情况:
- 1. A最后字符不在common sequence 或者 B最后字符不在common sequence.
- 2. A/B最后字符都在common sequence. 总体count + 1.



---
**28. [Interleaving String.java](https://github.com/awangdev/LintCode/blob/master/Java/Interleaving%20String.java)**      Level: Hard
      

双序列DP, 从最后点考虑.
拆分问题的末尾, 考虑和s1, s2 subsequence之间的关联.

求存在性, boolean




---
**29. [Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Edit%20Distance.java)**      Level: Hard
      

两个字符串, A要变成B, 可以 insert/delete/replace, 找最小变化operation count

#### Double Sequence
- 考虑两个字符串变换的最后点: 需要insert/delete/replace? 分析每种情况, 然后列出表达式.
- 先calculate最坏的情况, 3种operation count + 1; 然后在比较match的情况.
- 注意, 在i或者j为0的时候, 变成另外一个数字的steps只能是全变.
- 第一步, 空间时间都是O(MN)
- 滚动数组优化, 空间O(N)

#### Search



---
**30. [Distinct Subsequences.java](https://github.com/awangdev/LintCode/blob/master/Java/Distinct%20Subsequences.java)**      Level: Hard
      

Double Sequence DP:
0. DP size (n+1): 找前nth的结果, 那么dp array就需要开n+1, 因为结尾要return dp[n][m]
1. 在for loop 里面initialize dp[0][j] dp[i][0]
2. Rolling array 优化成O(N): 如果dp[i][j]在for loop里面, 就很好替换 curr/prev



---
**31. [Regular Expression Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Regular%20Expression%20Matching.java)**      Level: Review
      



---
**32. [Wildcard Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java)**      Level: Hard
      

Double sequence DP. 与regular expression 很像.

注意1: 分析字符 ?, * 所代表的真正意义, 然后写出表达式.
注意2: 搞清楚initialization 的时候 dp[i][0] 应该always false.当p为empty string, 无论如何都match不了 (除非s="" as well)
    同时 dp[0][j]不一定是false. 比如s="",p="*" 就是一个matching.



---
**33. [Ones and Zeroes.java](https://github.com/awangdev/LintCode/blob/master/Java/Ones%20and%20Zeroes.java)**      Level: Hard
      

还是Double Sequence, 但是考虑第三种状态: 给的string array的用量.
所以开了3维数组.

如果用滚动数组优化空间, 需要把要滚动的那个for loop放在最外面, 而不是最里面.
当然, 这个第三位define在 dp[][][]的哪个位置, 问题都不大.

另外, 注意在外面calcualte zeros and ones, 节约时间复杂度.



---
**34. [Word Break II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Break%20II.java)**      Level: Review
      

两个DP一起用.解决了timeout的问题     
1. isWord[i][j], subString(i,j)是否存在dict中？

2. 用isWord加快 isValid[i]: [i ～ end]是否可以从dict中找到合理的解？      
	从末尾开始查看i：因为我们需要测试isWord[i][j]时候，j>i, 而我们观察的是[i,j]这区间；       
	j>i的部分同样需要考虑，我们还需要知道isValid[0～j+1]。 所以isValid[x]这次是表示[x, end]是否valid的DP。     
	i 从 末尾到0, 可能是因为考虑到isWord[i][j]都是在[0~n]之内，所以倒过来数，坐标比较容易搞清楚。     
	(回头看Word Break I， 也有坐标反转的做法)

3. dfs 利用 isValid 和isWord做普通的DFS。

Note:
在Word Break里面用了set.contains(...), 在isValid里面，i 从0开始。 但是，contains()本身是O(n).     
在这道题里面应该是因为word dictionary太大，加上nest for, 变成O(n^3)所以timeout.

istead,用一个isWord[i][j]，就O(1)判断了i~j是不是存在dictionary里面。



---
**35. [Unique Path.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Path.java)**      Level: Medium
      

2D array, 算走到最右下角，有多少种方式.

##### DP
- 计数DP.注意方程式前两位置加在一起: 前两种情况没有overlap, 也不会缺情况.
- 注意initialization, 归1.
- 需要initialize的原因是,也是一个reminder: 在方程中会出现-1index
- Of course, row i = 0, or col j = 0, there is only 1 way to access
- time O(mn), space O(mn)

##### 滚动数组
- [i] 只跟 [i - 1] 有关系, 用 curr/prev 建立滚动数组.
- space O(n) 优化空间




---
**36. [Maximal Rectangle.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Rectangle.java)**      Level: Hard
      

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
**37. [Maximal Square.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Square.java)**      Level: Medium
      

只能往右边,下面走, 找面积最大的 square. 也就是找到变最长的 square.

#### DP
- 正方形, 需要每条边都是一样长度.
- 以右下角为考虑点, 必须满足条件: left/up/diagonal的点都是1
- 并且, 如果三个点分别都衍生向三个方向, 那么最长的 square 边就是他们之中的最短边 (受最短边限制)
- dp[i][j]: max square length when reached at (i, j), from the 3 possible directions
- dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
- Space, time O(mn)

##### init
每个点都可能是边长1, 如果 matrix[i][j] == '1'

##### 滚动数组
[i] 和 [i - 1] 之间的关系, 想到滚动数组优化 space, O(n) sapce.



---
**38. [Minimum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Path%20Sum.java)**      Level: Medium
      

#### DP
- 往右下角走, 计算最短的 path sum. 典型的坐标型.
- 注意: init 第一行的时候, 要accumulate dp[0][j - 1] + grid[i][j], 而不是单纯assign grid[i][j]

##### rolling array
TODO




---
**39. [House Robber III.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20III.java)**      Level: Hard
      

Houses被arrange成了binary tree, 规则还是一样, 连续相连的房子不能同时抄.
求Binary Tree neighbor max 能抄多少.

#### DFS
- 判断当下的node是否被采用，用一个boolean来表示. 
- 如果curr node被采用，那么下面的child一定不能被采用.
- 如果curr node不被采用，那么下面的children有可能被采用，但也可能略过，所以这里用Math.max() 比较一下两种可能有的dfs结果。
- dfs重复计算:每个root都有4种dive in的可能性, 假设level高度是h, 那么时间O(4^(h)), where h = logN, 也就是O(n^2)

#### DP 
- 并不是单纯的DP, 是在发现DFS很费劲后, 想能不能代替一些重复计算?
- 在DFS的基础上, 在每一个level上面来一个dp.
- 基本思想是dfs解法一致: 取root找最大值, 或者不取root找最大值
- Optimization: DP里面, 一口气找leftDP[]会dfs到最底层, 然后自下向上做计算
- 这个过程里面, 再也不用回去visit most-left-leaf了, 算过一遍就完事.
- 然而, 普通没有dp的dfs, 在算完visited的情况下的dfs, 还要重新dfs一遍!visited的情况.
- Space O(h), time O(n), 或者说是O(2^h), where h = log(n)



---
**40. [Longest Increasing Path in a Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Path%20in%20a%20Matrix.java)**      Level: Hard
      

m x n 的matrix, 找最长增序的序列长度. 这里默认连续的序列.

- 接成圈是不行的, 所以visit过得 (x,y)就不能再去了.
- 斜角方向不能走, 只能走上下左右

#### DP, DFS
- DFS太多重复计算; memoization (dp[][], visited[][]) 省去了重复计算
- initialize dp[x][y] = 1, (x,y) 自己也算path里的一格
- O(m * n * k), where k is the longest path

#### Topological sort
还没有做



---
**41. [Coins in a Line.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line.java)**      Level: Medium
      

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
**42. [Coins in a Line II.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20II.java)**      Level: Medium
      

给一串coins, 用values[]表示; 每个coin有自己的value. 先手/后手博弈,
每次只能拿1个或者2个棋子, 最后看谁拿的总值最大.

MiniMax的思考方法很神奇, 最后写出来的表达式很简单

##### DP, Game Theory 自考过程比较长
- 跟Coins in a line I 不一样: 每个coin的value不同.
- 用到MiniMax的思想, 这里其实是MaxiMin. Reference: http://www.cnblogs.com/grandyang/p/5864323.html
- Goal: 使得player拿到的coins value 最大化. 
- 于此同时, 你的对手playerB也想最大化, 而你的选择又不得不被对手的选择所牵制.
- 用MaxiMin的思想, 我们假设一个当下的状态, 假想对手playerB会做什么反应(从对手角度, 如何让我输)
- 在劣势中(对手让我输的目标下)找到最大的coins value sum
- 设定dp[i]: 从index i 到 index n的最大值. 所以dp[0]就是我们先手在[0 ~ n]的最大取值

##### 推算表达式
Reference里面详细介绍了表达式如何推到出来, 简而言之:
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
**43. [Climbing Stairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Climbing%20Stairs.java)**      Level: Easy
      

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
**44. [Coins in a Line III.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20III.java)**      Level: Hard
      

还是2个人拿n个coin, coin可以有不同的value. 只不过这次选手可以从任意的一头拿, 而不限制从一头拿. 算先手会不会赢?

#### Memoization + Search
- 跟Coins in a Line II 一样, MiniMax的思想: 找到我的掠视中的最大值
- dp[i][j] 代表在[i,j]区间上的先手最多能取的value 总和
- 同样, sum[i][j]表示[i] 到 [j]间的value总和
- dp[i][j] = sum[i][j] - Math.min(dp[i][j - 1], dp[i + 1][j]);
- 这里需要search, 画出tree可以看明白是如何根据取前后而分段的.

#### 博弈 + 区间DP
(这个方法需要复习, 跟数学表达式的推断相关联)
- S(x) = X - Y, 找最大数字差. 如果最大值都大于0, 就是赢了; 如果小于0, 就输了. 
- dp[i][j]表示 从index(i) 到 index(j), 先手可以拿到的最大值与对手的数字差. 也就是S(x) = X - Y.
- dp[i][j] = max{a[i] - dp[i + 1][j], a[j] - dp[i][j - 1]}
- 最后判断 dp[0][n] >= 0

#### 注意
- 如果考虑计算先手[i, j]之间的最大值, 然后可能还需要两个数组, 最后用于比较先手和opponent的得分大小 => 那么就要多开维.
- 我们这里考虑的数字差, 刚好让人不需要计算先手的得分总值, 非常巧妙.

#### 区间型动态规划
- 找出[i, j]区间内的性质: dp[i][j]下标表示区间范围 [i, j]
- 子问题: 砍头, 砍尾, 砍头砍尾
- loop应该基于区间的length
- template: 考虑len = 1, len = 2; 设定i的时候一定是 i <= n - len; 设定j的时候, j = len + i - 1;




---
**45. [Burst Balloons.java](https://github.com/awangdev/LintCode/blob/master/Java/Burst%20Balloons.java)**      Level: Hard
      

一排球, 每个球有value, 每次扎破一个, 就会积分: 左*中间*右 的值. 求, 怎么扎, 最大值?

TODO: Need more thoughts on why using dp[n + 2][n + 2] for memoization, but dp[n][n] for range DP.

#### Range DP
- 因为数组规律会变, 所以很难找'第一个burst的球'. 反之, 想哪一个是最后burst?
- 最后burst的那个变成一堵墙: 分开两边, 分开考虑, 加法原理; 最后再把中间的加上.
- dp[i][j] represent max value on range [i, j)
- Need to calculate dp[i][j] incrementally, starting from range size == 3 ---> n
- Use k to divide the range [i, j) and conquer each side.

##### Range DP 三把斧:
- 中间劈开
- 砍断首或尾
- Range区间作为iteration的根本

##### Print the calculation process
- use pi[i][j] and print recursively.
- Print k, using pi[i][j]: max value taken at k

#### Memoization
- 其实会做之后挺好想的一个DP
- dp[i][j] =  balloons i~j 之间的 max. 
- 然后找哪个点开始burst? 设为x。
- For loop 所有的点作为x， 去burst。
- 每次burst都切成了三份：左边可以recusive 求左边剩下的部分的最大值 + 中间3项相乘 + 右边递归下去求最大值。
- Note: 这个是Memoization, 而不纯是DP
- 因为recursive了，其实还是搜索，但是memorize了求过的值，节省了Processing




---
**46. [Nim Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Nim%20Game.java)**      Level: Easy
      

#### Brainteaser
- 著名Nim游戏
- 写一些，发现n=4,5,6,7,8...etc之后的情况有规律性: 谁先手拿到4就输了.
- 最终很简单n%4!=0就可以了,  time, space O(1)

#### DP
- 正规地找规律做, 就跟 coins in a line 一样, 按照先手后手来做
- 可以rolling array 优化空间
- Time O(n), 当然啦, 这个题目这样会timeout, 可以使用brainteaser的做法写出结果.



---
**47. [K Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/K%20Edit%20Distance.java)**      Level: Hard
      

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
**48. [Jump Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game.java)**      Level: Medium
      

给出步数，看能不能jump to end.

#### DP
- DP[i]: 在i点记录，i点之前的步数是否可以走到i点？ True of false.
- 其实j in [0~i)中间只需要一个能到达i 就好了
- Function: DP[i] = DP[j] && (A[j] >= i - j), for all j in [0 ~ i)
- Return: DP[dp.length - 1];

#### Greedy
- Keep track of farest can go
- 一旦 farest >= nums.length - 1, 也就是到了头, 就可以停止, return true.
- 一旦 farest <= i, 也就是说, 在i点上, 已经走过了步数, 不能再往前跳, 于是 return false



---
**49. [Coin Change 2.java](https://github.com/awangdev/LintCode/blob/master/Java/Coin%20Change%202.java)**      Level: Medium
      

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
**50. [Paint House.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House.java)**      Level: Easy
      

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
