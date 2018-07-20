 
 
 
## DP (82)
**0. [Stone Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Stone%20Game.java)**      Level: Medium      Tags: [DP]
      

这个DP有点诡异. 需要斟酌。
NOT DONE YET


---

**1. [Ugly Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Ugly%20Number%20II.java)**      Level: Medium      Tags: [DP, Heap, Math]
      

非常brutle的。
每次把dp[i-1]拿出来，不管三七二十一，分别乘以2,3,5. 出来的结果放进priority queue做比较。
最后时间是n*log(n*3)

注意：
Long
HashSet确保没有重复。


---

**2. [Coin Change.java](https://github.com/awangdev/LintCode/blob/master/Java/Coin%20Change.java)**      Level: Medium      Tags: [Backpack DP, DP, Memoization]
      

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

**3. [Maximum Product Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Product%20Subarray.java)**      Level: Medium      Tags: [Array, DP, Subarray]
      

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

**4. [k Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/k%20Sum.java)**      Level: Hard      Tags: [DP]
      

DP. 公式如何想到, 还需要重新理解.

dp[i][j][m]: # of possibilities such that from j elements, pick m elements and sum up to i. 
i: [0~target]

dp[i][j][m] = dp[i][j-1][m] + dp[i - A[j - 1]][j-1][m-1]
            (i not included)   (i included)



---

**5. [Unique Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DP, Tree]
      

Not quite clear.
根据左右分割而总结出了原理, 每次分割, 左右两边都会有一定数量的permutation, 总体上的情况数量当然是相乘.
然后每一个不同的分割点都加一遍:
f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

然后把数学公式转换成DP的方程, 有点玄学的意思啊! 不好想.



---

**6. [Unique Paths II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Paths%20II.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP]
      

跟unique path的grid一样, 目标走到右下角, 但是grid里面可能有obstacle, 不能跨越. 求unique path 的count.

#### 坐标DP
- dp[i][j]: # of paths to reach grid[i][j]
- dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
- 考虑最终结尾需要的状态:如何组成,写出公式.
- 公式中注意处理能跳掉的block, marked as 1. '到不了', 即为 0 path in dp[i][j].



---

**7. [Bomb Enemy.java](https://github.com/awangdev/LintCode/blob/master/Java/Bomb%20Enemy.java)**      Level: Medium      Tags: [Coordinate DP, DP]
      

2D grid, 每个格子里面可能是 'W' wall, 'E' enemy, 或者是 '0' empty.

一个bomb可以往4个方向炸. 求在grid上面, 最大能炸掉多少个敌人.

#### Corrdinate DP
- Space, Time: O(MN)
- dp[i][j] 就是(i, j)上最多能炸掉的enemy数量
- dp[i][j] 需要从4个方向加起来, 也就是4个方向都要走一遍, 所以分割成 UP/Down/Left/Right 4个 int[][]
- 最后一步的时候求max
- 分方向考虑的方法很容易想到,但是四个方向移动的代码比较繁琐.
- 往上炸: 要从顶向下考虑
- 往下炸: 要从下向上考虑
- 熟练写2D array index 的变换.

似乎还有一个更简洁的方法, 用col count array: http://www.cnblogs.com/grandyang/p/5599289.html



---

**8. [Perfect Squares.java](https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Squares.java)**      Level: Medium      Tags: [BFS, DP, Math, Partition DP]
      

给一个数字n, 找到这个数字 最少能用多少个 平方数组成. 

平方数比如: 1, 4, 9, 16 ... etc

#### Partition DP
- 遇到最值, 想到DP.
- 看到分割字眼, 想到分割型 DP. 
- 思考, 如果 j * j = 9, 那么 j = 3 就是最少的一步; 但是如果是10呢? 就会分割成1 + 9 = 1 + j * j 
- 考虑最后的数字: 要是12割个1出来, 剩下11怎么考虑? 割个4出来,剩下8怎么考虑?
- partion的方式: 在考虑dp[i - x]的时候,  x 不是1, 而是 x = j*j.
- 就变成了dp = Min{dp[i - j^2] + 1}

#### 时间复杂度
- 乍一看是O(n*sqrt(n)). 实际也是. 但如何推导?
- 考虑上限: 把小的数字变成大的 推导上限; 考虑下限: 把数字整合归小, 找到下限.
- 考虑sqrt(1) + sqrt(2) + ....sqrt(n):找这个的upper bound and lower bound.
- 最后发现它的两边是 A*n*sqrt(n) <= actual time complexity <= B*n*sqrt(n)
- 那么就是O(n*sqrt(n))啦

#### BFS
- minus all possible (i*i) and calculate the remain
- if the remain is new, add to queue (use a hashset to mark calculated item)
- find shortest path / lowest level number

#### Previous Notes
- 一开始没clue.看了一下提示
- １.　第一步想到了，从数学角度，可能是从最大的perfect square number开始算起。
- ２.　然后想法到了dp， 假设最后一步用了最大的maxSqrNum, 那么就在剩下的 dp[i - maxSqrNum^2] +１　不就好了？
- ３.　做了，发现有个问题．．．最后一步选不选maxSqrNum?  比如12就是个例子。
- 然后就根据提示，想到BFS。顺的。 把1～maxSqrNum 都试一试。找个最小的。
- 看我把12拆分的那个example. 那很形象的就是BFS了。
- 面试时候，如果拆分到这个阶段不确定，那跟面试官陶瓷一下，说不定也就提示BFS了。



---

**9. [Backpack VI.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20VI.java)**      Level: Medium      Tags: [Backpack DP, DP]
      

给一个数组nums, 全正数, 无重复数字; 找: # of 拼出m的方法.

nums 里的数字, 可以重复使用. 不同的order可以算作不同的拼法.

#### Backpack DP
- dp[i] 表示: # of ways to fill weight i
- 1维: dp[w]: fill weigth w 有多少种方法. 前面有多少种可能性, 就sum多少个:
- dp[w] = sum{dp[w - nums[i]]}, i = 0~n

##### 分析
- 拼背包时, 可以有重复item, 所以考虑'最后被放入的哪个unique item' 就没有意义了.
- 背包问题, 永远和weight分不开关系.
- 这里很像coin chagne: 考虑最后被放入的东西的value/weigth, 而不考虑是哪个.






---

**10. [Copy Books.java](https://github.com/awangdev/LintCode/blob/master/Java/Copy%20Books.java)**      Level: Hard      Tags: [Binary Search, DP, Partition DP]
      

给一串书pages[i], k个人, pages[i] 代表每本书的页数. k个人从不同的点同时开始抄书. 

问, 最快什么时候可以抄完?

#### Partition DP
- 第一步, 理解题目要求的问题: 前k个人copy完n本书, 找到最少的用时; 也可以翻译成, n本书, 让k个人来copy, 也就是分割成k段.
- 最后需要求出 dp[n][k]. 开: int[n+1][k+1]. 
- 在[0 ~ n - 1]本书里, 最后一个人可以选择copy 1 本, 2 本....n本, 每一种切割的方法的结果都不一样
- 木桶原理, 因为K个人同时开始, 最坏的情况决定结果: `Math.max(dp[j][k - 1], sum)`, where j is the cut.
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

**11. [Scramble String.java](https://github.com/awangdev/LintCode/blob/master/Java/Scramble%20String.java)**      Level: Hard      Tags: [DP, Interval DP, String]
      

- 给两个string S, T. 检验他们是不是scramble string.
- scramble string 定义: string可以被分拆成binary tree的形式, 也就是切割成substring;
- 旋转了不是leaf的node之后, 形成新的substring, 这就是原来string的 scramble.


#### Interval DP 区间型
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

**12. [Best Time to Buy and Sell Stock with Cooldown.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Cooldown.java)**      Level: Medium      Tags: [DP]
      

Sequence DP
跟StockIII很像. 分析好HaveStock && NoStock的状态, 然后看最后一步.



---

**13. [Longest Common Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Subsequence.java)**      Level: Medium      Tags: [DP, Double Sequence DP, Sequence DP]
      

给两个string, A, B. 找这两个string里面的LCS: 最长公共字符长度 (不需要是continuous substring)

#### Double Sequence DP
- 设定dp长度为(n+1), 因为dp[i]要用来表示前i个(ith)时候的状态, 所以长度需要时i+1才可以在i位置, hold住i.
- 双序列: 两个sequence之间的关系, 都是从末尾字符看起, 分析2种情况:
- 1. A最后字符不在common sequence 或者 B最后字符不在common sequence.
- 2. A/B最后字符都在common sequence. 总体count + 1.



---

**14. [Interleaving String.java](https://github.com/awangdev/LintCode/blob/master/Java/Interleaving%20String.java)**      Level: Hard      Tags: [DP, String]
      

双序列DP, 从最后点考虑.
拆分问题的末尾, 考虑和s1, s2 subsequence之间的关联.

求存在性, boolean




---

**15. [Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Edit%20Distance.java)**      Level: Hard      Tags: [DP, Double Sequence DP, Sequence DP, String]
      
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

**16. [Distinct Subsequences.java](https://github.com/awangdev/LintCode/blob/master/Java/Distinct%20Subsequences.java)**      Level: Hard      Tags: [DP, String]
      

Double Sequence DP:
0. DP size (n+1): 找前nth的结果, 那么dp array就需要开n+1, 因为结尾要return dp[n][m]
1. 在for loop 里面initialize dp[0][j] dp[i][0]
2. Rolling array 优化成O(N): 如果dp[i][j]在for loop里面, 就很好替换 curr/prev



---

**17. [Regular Expression Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Regular%20Expression%20Matching.java)**      Level: Review      Tags: [Backtracking, DP, String]
      



---

**18. [Wildcard Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java)**      Level: Hard      Tags: [Backtracking, DP, Greedy, String]
      

Double sequence DP. 与regular expression 很像.

注意1: 分析字符 ?, * 所代表的真正意义, 然后写出表达式.
注意2: 搞清楚initialization 的时候 dp[i][0] 应该always false.当p为empty string, 无论如何都match不了 (除非s="" as well)
    同时 dp[0][j]不一定是false. 比如s="",p="*" 就是一个matching.



---

**19. [Ones and Zeroes.java](https://github.com/awangdev/LintCode/blob/master/Java/Ones%20and%20Zeroes.java)**      Level: Hard      Tags: [DP]
      

还是Double Sequence, 但是考虑第三种状态: 给的string array的用量.
所以开了3维数组.

如果用滚动数组优化空间, 需要把要滚动的那个for loop放在最外面, 而不是最里面.
当然, 这个第三位define在 dp[][][]的哪个位置, 问题都不大.

另外, 注意在外面calcualte zeros and ones, 节约时间复杂度.



---

**20. [Word Break II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Break%20II.java)**      Level: Hard      Tags: [Backtracking, DFS, DP, Memoization]
      

#### DFS + Memoization
- Realize the input s expands into a tree of possible prefixes.
- We can do top->bottom(add candidate+backtracking) OR bottom->top(find list of candidates from subproblem, and cross-match)
- DFS on string: find a valid word, dfs on the suffix. [NO backtraking in the solution]
- DFS returns List<String>: every for loop takes a prefix substring, and append with all suffix (result of dfs)
- Memoization: `Map<substring, List<String>>`, which reduces repeated calculation if the substring has been tried.
- Time O(n!). Worst case, permutation of unique letters: `s= 'abcdef....'`, and `dict=[a,b,c,d,e,f...]`

#### Regular DPs
- 两个DP一起用, 解决了timeout的问题: when a invalid case 'aaaaaaaaa' occurs, isValid[] stops dfs from occuring
- 1. isWord[i][j], subString(i,j)是否存在dict中？
- 2. 用isWord加快 isValid[i]: [i ～ end]是否可以从dict中找到合理的解？      
- 从末尾开始查看i：因为我们需要测试isWord[i][j]时候，j>i, 而我们观察的是[i,j]这区间；       
- j>i的部分同样需要考虑，我们还需要知道isValid[0～j+1]。 所以isValid[x]这次是表示[x, end]是否valid的DP。     
- i 从 末尾到0, 可能是因为考虑到isWord[i][j]都是在[0~n]之内，所以倒过来数，坐标比较容易搞清楚。     
- (回头看Word Break I， 也有坐标反转的做法)
- 3. dfs 利用 isValid 和isWord做普通的DFS。

#### Timeout Note
- Regarding regular solution: 如果不做memoization或者dp, 'aaaaa....aaa' will repeatedly calculate same substring
- Regarding double DP solution: 在Word Break里面用了set.contains(...), 在isValid里面，i 从0开始. 但是, contains()本身是O(n); intead,用一个isWord[i][j]，就O(1)判断了i~j是不是存在dictionary



---

**21. [Unique Path.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Path.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP]
      

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

**22. [Maximal Rectangle.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Rectangle.java)**      Level: Hard      Tags: [Array, DP, Hash Table, Stack]
      

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

**23. [Maximal Square.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Square.java)**      Level: Medium      Tags: [Coordinate DP, DP]
      

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

**24. [Longest Increasing Path in a Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Path%20in%20a%20Matrix.java)**      Level: Hard      Tags: [Coordinate DP, DFS, DP, Memoization, Topological Sort]
      

m x n 的matrix, 找最长增序的序列长度. 这里默认连续的序列.

- 接成圈是不行的, 所以visit过得 (x,y)就不能再去了.
- 斜角方向不能走, 只能走上下左右
- 无法按照坐标DP来做, 因为计算顺序4个方向都可以走.
- 最终要visit所有node, 所以用DFS搜索比较合适.

#### DFS, Memoization
- 简单版: longest path, only allow right/down direction: 
- `dp[x][y] = Math.max(dp[prevUpX][prevUpY], or dp[prevUpX][prevUpY] + 1)`; and compare the other direction as well
- This problem, just compare the direction from dfs result
- DFS太多重复计算; memoization (dp[][], visited[][]) 省去了重复计算
- initialize dp[x][y] = 1, (x,y) 自己也算path里的一格
- dfs(matrix, x, y): 每次检查(x,y)的4个neighbor (nx, ny), 如果他们到(x,y)是递增, 那么就考虑和比较:
- Maht.max(dp[x][y], dp[nx][ny] + 1); where dp[n][ny] = dfs(matrix, nx, ny)
- top level: O(mn), 尝试从每一个 (x,y) 出发
- O(m * n * k), where k is the longest path

#### Topological sort
还没有做



---

**25. [Coins in a Line.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line.java)**      Level: Medium      Tags: [DP, Game Theory, Greedy]
      

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

**26. [Coins in a Line II.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20II.java)**      Level: Medium      Tags: [Array, DP, Game Theory, Memoization, MiniMax]
      

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

**27. [Climbing Stairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Climbing%20Stairs.java)**      Level: Easy      Tags: [DP, Memoization, Sequence DP]
      

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

**28. [Coins in a Line III.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20III.java)**      Level: Hard      Tags: [Array, DP, Game Theory, Interval DP, Memoization]
      

还是2个人拿n个coin, coin可以有不同的value. 

只不过这次选手可以从任意的一头拿, 而不限制从一头拿. 算先手会不会赢?

#### Memoization + Search
- 跟Coins in a Line II 一样, MaxiMin的思想: 找到我的劣势中的最大值
- dp[i][j] 代表在[i,j]区间上 选手最多能取的value 总和
- 同样, sum[i][j]表示[i] 到 [j]间的value总和
- 对手的最差情况, 也就是先手的最好情况:
- dp[i][j] = sum[i][j] - Math.min(dp[i][j - 1], dp[i + 1][j]);
- 这里需要search, 画出tree可以看明白是如何根据取前后而分段的.

#### 博弈 + 区间DP
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

**29. [Burst Balloons.java](https://github.com/awangdev/LintCode/blob/master/Java/Burst%20Balloons.java)**      Level: Hard      Tags: [DP, Divide and Conquer, Interval DP, Memoization]
      

一排球, 每个球有value, 每次扎破一个, 就会积分: 左*中间*右 的值. 求, 怎么扎, 最大值?

TODO: Need more thoughts on why using dp[n + 2][n + 2] for memoization, but dp[n][n] for interval DP.

#### Interval DP
- 因为数组规律会变, 所以很难找'第一个burst的球'. 反之, 想哪一个是最后burst?
- 最后burst的那个变成一堵墙: 分开两边, 分开考虑, 加法原理; 最后再把中间的加上.
- dp[i][j] represent max value on range [i, j)
- Need to calculate dp[i][j] incrementally, starting from range size == 3 ---> n
- Use k to divide the range [i, j) and conquer each side.

##### Interval DP 三把斧:
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

**30. [Nim Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Nim%20Game.java)**      Level: Easy      Tags: [Brainteaser, DP, Game Theory]
      

#### Brainteaser
- 著名Nim游戏
- 写一些，发现n=4,5,6,7,8...etc之后的情况有规律性: 谁先手拿到4就输了.
- 最终很简单n%4!=0就可以了,  time, space O(1)

#### DP
- 正规地找规律做, 就跟 coins in a line 一样, 按照先手后手来做
- 可以rolling array 优化空间
- Time O(n), 当然啦, 这个题目这样会timeout, 可以使用brainteaser的做法写出结果.



---

**31. [K Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/K%20Edit%20Distance.java)**      Level: Hard      Tags: [DP, Double Sequence DP, Sequence DP, Trie]
      

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

**32. [Jump Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game.java)**      Level: Medium      Tags: [Array, DP, Greedy]
      

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

**33. [Coin Change 2.java](https://github.com/awangdev/LintCode/blob/master/Java/Coin%20Change%202.java)**      Level: Medium      Tags: [Backpack DP, DP]
      

给串数字, target amount, 求总共多少种方式可以reach the amount.

#### DP
- O(MN): M, total target amount; N: size of coins
- 类似于: 网格dp, unique path 里面的2种走法: 从上到下, 从左到右
- 状态: dp[i]: sum of ways that coins can add up to i.
- Function: dp[j] += dp[j - coins[i]];
- Init: dp[0] = 1 for ease of calculation; other dp[i] = 0 by default
- note: 避免重复count, 所以 j = coins[i] as start
- 注意 coins 需要放在for loop 外面, 主导换coin的流程, 每个coin可以用无数次, 所以在每一个sum value上都尝试用一次每个coin

#### knapsack problem: backpack problem



---

**34. [Paint House.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House.java)**      Level: Easy      Tags: [DP, Sequence DP, Status DP]
      
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

**35. [Decode Ways.java](https://github.com/awangdev/LintCode/blob/master/Java/Decode%20Ways.java)**      Level: Medium      Tags: [DP, Partition DP, String]
      

给出一串数字, 要翻译(decode)成英文字母. [1 ~ 26] 对应相对的英文字母. 求有多少种方法可以decode.

#### Partition DP
- 加法原理
- 确定末尾的2种状态: single letter or combos. 然后计算出单个letter的情况, 和双数的情况
- 定义dp[i] = 前i个digits最多有多少种decode的方法. new dp[n + 1].
- 加法原理: 把不同的情况, single-digit, double-digit 的情况加起来
- note: calculate number from characters, need to - '0' to get the correct integer mapping.
- 注意: check value != '0', 因为'0' 不在条件之中(A-Z)
- Space, Time O(n)




---

**36. [Longest Continuous Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Continuous%20Increasing%20Subsequence.java)**      Level: Easy      Tags: [Array, Coordinate DP, DP]
      

找连续的持续上升子序列的长度.

#### Coordinate DP
- 1D coordinate, dp 的角标, 就是代表 index i 的状态
- 求最值, dp[i] = 在index i位置的最长子序列
- 如果 nums[i] > nums[i - 1], dp[i] = dp[i - 1] + 1
- 如果没有持续上升, 那么dp[i] = 1, 重头来过
- maintain max

#### Basic
- 用一个数存current count,  maintain max



---

**37. [Minimum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Path%20Sum.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP]
      

#### DP
- Time, Space O(MN)
- 往右下角走, 计算最短的 path sum. 典型的坐标型.
- 注意: init 第一行的时候, 要accumulate dp[0][j - 1] + grid[i][j], 而不是单纯assign grid[i][j]

#### Rolling Array
- Time O(MN), Space O(1)
- 需要在同一个for loop里面完成initialization, 和使用dp[i][j]
- 原因: dp[i % 2][j] 在被计算出来的时候, 是几乎马上在下一轮是要被用的; 被覆盖前不备用,就白算
- 如果按照第一种方法, 在开始initialize dp, 看起来固然简单, 但是不方便空间优化



---

**38. [Counting Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Counting%20Bits.java)**      Level: Medium      Tags: [Bit Manipulation, Bitwise DP, DP]
      

给一个数组, 算里面有多少bit 1. 

#### Bitwise DP
- 对于每一个数字, 其实很简单就能算出来: 每次 >>1, 然后 & 1 就可以count 1s. Time: 一个数字可以 >>1 O(logN) 次
- 现在要对[0 ~ num] 都计算, 也就是N个数字, 时间复杂度: O(nLogN).
- 用DP来优化, 查找过的number的1s count, 存下来在 dp[number]里面.
- 计算你顺序从 0 -> num, count过的数字就可以重复利用.
- Bit题目 用num的数值本身表示DP的状态.
- 这里, dp[i] 并不是和 dp[i-1]有逻辑关系; 而是dp[i] 和dp[i>>1], 从binary representation看出有直接关系.



---

**39. [Continuous Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Continuous%20Subarray%20Sum.java)**      Level: Medium      Tags: [Coordinate DP, DP, Math, Subarray]
      

给一个非负数的数列和数字k(可正负, 可为0). 找到连续子序列(长度超过2), 使得这个subarray的sum 是 k的倍数. 问: 是否可能?

#### DP
- O(n^2)
- 需要记录在0 ~ i点(包括nums[i], 以nums[i]结尾)的sum, 坐标型动态规划.
- dp[i] = dp[i - 1] + nums[i];
- 最后移动, 作比较

#### 直接算结果
- 从sum = 每次[i ~ j]的所有情况
- 验证



---

**40. [House Robber.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber.java)**      Level: Easy      Tags: [DP, Sequence DP]
      
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

**41. [House Robber II.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20II.java)**      Level: Medium      Tags: [DP, Sequence DP, Status DP]
      

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

**42. [House Robber III.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20III.java)**      Level: Medium      Tags: [DFS, DP, Status DP, Tree]
      

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

**43. [Paint House II.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House%20II.java)**      Level: Hard      Tags: [DP, Sequence DP, Status DP]
      
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

**44. [Best Time to Buy and Sell Stock III .java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III%20.java)**      Level: Hard      Tags: [Array, DP, Sequence DP]
      

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

**45. [Best Time to Buy and Sell Stock IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20IV.java)**      Level: Hard      Tags: [DP, Sequence DP]
      

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

**46. [Russian Doll Envelopes.java](https://github.com/awangdev/LintCode/blob/master/Java/Russian%20Doll%20Envelopes.java)**      Level: Hard      Tags: [Binary Search, Coordinate DP, DP]
      

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

**47. [Backpack.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack.java)**      Level: Medium      Tags: [Backpack DP, DP]
      

给i本书, 每本书有自己的重量 int[] A, 背包有自己的大小M, 看最多能放多少重量的书?

#### Backpack DP 1
- 简单直白的思考 dp[i][m]: 前i本书, 背包大小为M的时候, 最多能装多种的书?
- **注意**: 背包问题, 重量weight一定要是一维.
- dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + A[i - 1]);
- 每一步都track 最大值
- 最后return dp[n][m]
- 时间空间  O(mn)
- Rolling array, 空间O(m)

#### Backpack DP 2
- true/false求解, 稍微曲线救国: 重点是, 最后, 按照weight从大到小遍历, 第一个遇到true的, index就是最大值.  
- 考虑: 用i个item (可跳过地取), 是否能装到weight w?
- 需要从'可能性'的角度考虑, 不要搞成单一的最大值问题.
- 1. 背包可装的物品大小和总承重有关.
- 2. 不要去找dp[i]前i个物品的最大总重, 找的不是这个. 
    dp[i]及时找到可放的最大sum, 但是i+1可能有更好的值, 把dp[i+1]变得更大更合适.

##### 做法
- boolean[][] dp[i][j]表示: 有前i个item, 用他们可否组成size为j的背包? true/false.
- (反过来考虑了，不是想是否超过size j, 而是考虑是否能拼出exact size == j)
- **注意**: 虽然dp里面一直存在i的位置, 实际上考虑的是在i位置的时候, 看前i-1个item.

##### 多项式规律
- 1. picked A[i-1]: 就是A[i-1]被用过, weight j 应该减去A[i-1]. 那么dp[i][j]就取决于dp[i-1][j-A[i-1]]的结果.
- 2. did not pick A[i-1]: 那就是说, 没用过A[i-1], 那么dp[i][j]就取决于上一行d[i-1][j]
- dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]]

##### 结尾
- 跑一遍dp 最下面一个row. 从末尾开始找, 最末尾的一个j (能让dp[i][j] == true)的, 就是最多能装的大小 :)   
- 时间，空间都是：O(mn)




---

**48. [Backpack II.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20II.java)**      Level: Medium      Tags: [Backpack DP, DP]
      

给i本书, 每本书有自己的重量 int[] A, 每本书有value int[] V

背包有自己的大小M, 看最多能放多少value的书?

#### Backpack DP
- 做了Backpack I, 这个就如出一辙, 只不过: dp存的不是max weight, 而是 value的最大值.
- 想法还是，选了A[i - 1] 或者没选A[i - 1]时候不同的value值.
- 时间空间O(mn)
- Rolling Array, 空间O(m)

#### Previous DP Solution
- 如果无法达到的w, 应该mark as impossible. 一种简单做法是mark as -1 in dp. 
- 如果有负数value, 就不能这样, 而是要开一个can[i][w]数组, 也就是backpack I 的原型.
- 这样做似乎要多一些代码, 好像并不是非常需要




---

**49. [Backpack V.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20V.java)**      Level: Medium      Tags: [Backpack DP, DP]
      

#### Backpack DP
- 与背包1不同: 这里不是check可能性(OR)或者最多能装的size是多少; 而是计算有多少种正好fill的可能性.
- dp[i][w]: 用前i本书, 正好fill到 w weight的可能性.
- 对于末尾, 还是两种情况:
- 1. i-1位置没有加bag
- 2. i-1位置加了bag
- 两种情况可以fill满w的情况加起来, 就是我们要的结果.
- 如常: dp[n + 1][w + 1]
- 重点: dp[0][0] 表示0本书装满weight=0的包, 这里我们必须 dp[0][0] = 1, 给后面的 dp function 做base
- Space, time: O(MN)
- Rolling array, 空间优化, 滚动数组. Space: O(M)

#### 降维打击, 终极优化
- 分析row(i-1)的规律, 发现所有row(i)的值, 都跟row(i-1)的左边element相关, 而右边element是没用的.
- 所以可以被override.
- Space: O(M), 真*一维啊!
- Time: O(MN)



---

**50. [Decode Ways II.java](https://github.com/awangdev/LintCode/blob/master/Java/Decode%20Ways%20II.java)**      Level: Hard      Tags: [DP, Partition DP]
      

给出一串数字, 要翻译(decode)成英文字母. [1 ~ 26] 对应相对的英文字母. 求有多少种方法可以decode.

其中字符可能是 "*", 可以代表 [1 - 9]

#### DP
- 跟decode way I 一样, 加法原理, 切割点时: 当下是取了 1 digit 还是 2 digits 来decode
- 定义dp[i] = 前i个digits最多有多少种decode的方法. new dp[n + 1].
- 不同的情况是: 每一个partition里面, 如果有"*", 就会在自身延伸出很多不同的可能
- 那么: dp[i] = dp[i - 1] * (#variations of ss[i]) + dp[i - 2] * (#variations of ss[i,i+1])

##### 特点
- 枚举的能力: 具体分析 '*' 出现的位置, 枚举出数字, 基本功. 注意!!题目说 * in [1, 9].   (如果 0 ~ 9 会更难一些)
- 理解取MOD的原因: 数字太大, 取mod来给最终结果: 其实在 10^9 + 7 这么大的 mod 下, 大部分例子是能通过的.
- 枚举好以后, 其实这个题目的写法和思考过程都不难




---

**51. [Palindrome Partitioning II.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Partitioning%20II.java)**      Level: Hard      Tags: [DP, Partition DP]
      

给一个String s, 找出最少用多少cut, 使致 切割出的每一个substring, 都是palindrome

#### Partition DP
- Find minimum cut: 分割型DP
- dp[i]: 最少cut多少刀, 使得前 i 长度的string, 割出来都是palindrome
- 最终要得到 dp[n], 所以 int[n + 1]
- 移动切刀, 看在哪里切, index j in [0 ~ i]
- 考虑[j, i - 1] 是否是回文串, 如果是, 那么: dp[i]= min(dp[i], d[j] + 1).
- note: 估计遍历 j 的时候, 反过来遍历也可以.

#### 计算Palindrome的优化
- 利用palindrome的性质, 可以算出 boolean palindrome[i, j]的情况. 
- 找一个任意mid point:
- 1. 假设palindrome是奇数长度, 那么 mid 是单独的字符, 而两边的字符 [mid-1], [mid+1] 应该完全相等.
- 2. 假设palindrome是偶数长度, 那么 [mid] 和 [mid + 1] 这样位置的字符应该相等.
- 这样做出来 palindrome[i, j]: 从字符 i 到 字符 j 的 substring 是否是 palindrome
- 这样就给我们的问题合理降维, 目前是time: O(n^2). 
- 不然求一次palindrome, 就是n, 会变成O(n^3)

#### Previous Notes
- Double for loop 检查每种substring string (i~j). 若i,j相邻或者同点，那么肯定isPal；否则，i,j之间的（i+1, j-1）一定得isPal。
- 看上去，在检查i,j的时候，中间按的（i+1, j-1）怎么可能先知道？ 其实不然..在j慢慢长大的时候，所有的0~j的substring都检查过。所以isPal[i+1][j-1]一定是已经知道结果的。
- okay.那么假如以上任意一种情况成立，也就是说isPal[i][j] == true。那就要判断，切到第一层循环参数j的末尾点时，有多少种切法？
- 想法很顺：我们naturally会想到，把i之前的cut加上i~j之间发生的不就好了。
- 反正现在j不变，现在就看吧i定在哪里，cut[i - 1]是否更小/最小； 再在cut[i-1]基础上+1就完了。
- 当然，如果i==0, 而 i~j又是isPal,那没啥好谈的，不必切，0刀。
- 最终，刷到cut[s.length() - 1] 也就是最后一点。 return的理所应当。




---

**52. [Backpack III.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20III.java)**      Level: Hard      Tags: [Backpack DP, DP]
      

给n种不同的物品, int[] A weight, int[] V value, 每种物品可以用无限次

问最大多少value可以装进size是 m 的包?

#### DP
- 可以无限使用物品, 就失去了last i, last unique item的意义: 因为可以重复使用.
- 所以可以转换一个角度:
- 1. 用i **种** 物品, 拼出w, 并且满足题目条件(max value). 这里因为item i可以无限次使用, 所以考虑使用了多少次K.
- 2. K虽然可以无限, 但是也被 k*A[i]所限制: 最大不能超过背包大小.
- dp[i][w]: 前i种物品, fill weight w 的背包, 最大价值是多少.
- dp[i][w] = max {dp[i - 1][w - k*A[i-1]] + kV[i-1]}, k >= 0
- Time O(nmk)
- 如果k = 0 或者 1, 其实就是 Backpack II: 拿或者不拿

#### 优化
- 优化时间复杂度, 画图发现:
- 所计算的 (dp[i - 1][j - k*A[i - 1]] + k * V[i - 1]) 
- 其实跟同一行的 dp[i][j-A[i-1]] 那个格子, 就多出了 V[i-1]
- 所以没必要每次都 loop over k times
- 简化: dp[i][j] 其中一个可能就是: dp[i][j - A[i - 1]] + V[i - 1]
- Time O(mn)

#### 空间优化到1维数组
- 根据上一个优化的情况, 画出 2 rows 网格
- 发现 dp[i][j] 取决于: 1. dp[i - 1][j], 2. dp[i][j - A[i - 1]]
- 其中: dp[i - 1][j] 是上一轮 (i-1) 的结算结果, 一定是已经算好, ready to be used 的
- 然而, 当我们 i++,j++ 之后, 在之前 row = i - 1, col < j的格子, 全部不需要.
- 降维简化: 只需要留着 weigth 这个 dimension, 而i这个dimension 可以省略: 
- (i - 1) row 不过是需要用到之前算出的旧value: 每一轮, j = [0 ~ m], 那么dp[j]本身就有记录旧值的功能.
- 变成1个一位数组
- 降维优化的重点: 看双行的左右计算方向
- Time(mn). Space(m)



---

**53. [Longest Common Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Substring.java)**      Level: Medium      Tags: [DP, Double Sequence DP, Sequence DP, String]
      

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

**54. [Longest Increasing Continuous subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence.java)**      Level: Easy      Tags: [Array, Coordinate DP, DP]
      

https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/

O(n)跑2遍for.
O(1)是用了两个int来存：每次到i点时，i点满足条件或不满足条件所有的longestIncreasingContinuousSubsequence.
特点：返跑一回，ans还是继续和left轮的ans作比较；求的所有情况的最大值嘛。



---

**55. [Longest Increasing Continuous subsequence II.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence%20II.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP, Memoization]
      

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

**56. [Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray.java)**      Level: Easy      Tags: [Array, DFS, DP, Divide and Conquer, PreSum, Sequence DP, Subarray]
      
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

**57. [Maximum Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray%20II.java)**      Level: Medium      Tags: [Array, DP, Greedy, PreSum, Sequence DP, Subarray]
      

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

**58. [Fibonacci.java](https://github.com/awangdev/LintCode/blob/master/Java/Fibonacci.java)**      Level: Easy      Tags: [DP, Math, Memoization]
      

#### Memoization
- fib[n] = fibonacci(n - 1) + fibonacci(n - 2);

#### DP array.
- 滚动数组, 简化DP

#### recursively calculate
- recursively calculate fib(n - 1) + fib(n - 2). 公式没问题, 但是时间太长, timeout.




---

**59. [Binary Tree Maximum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum.java)**      Level: Hard      Tags: [DFS, DP, Tree, Tree DP]
      

找max path sum,  可以从任意treeNode 到任意 treeNode.

#### Kinda, Tree DP
- 两个情况: 1. combo sum: left+right+root; 2. single path sum
- Note1: the path needs to be continuous, curr node cannot be skipped
- Note2: what about I want to skip curr node: handled by lower level of dfs(), where child branch max was compared.
- Note3: skip left/right child branch sum, by comparing with 0. 小于0的, 没必要记录

#### DP的思想
- tree给我们2条branch, 每条branch就类似于 dp[i - 1], 这里类似于dp[left], dp[right] 这样
- 找到 dp[left], dp[right] 以后, 跟 curr node结合. 
- 因为是找max sum, 并且可以skip nodes, 所以需要全局变量max
- 每次dfs() return的一定是可以继续 `continuously link 的 path`, 所以return `one single path sum + curr value`.

#### DFS, PathSum object
- that just solves everything


---

**60. [Combination Sum IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20IV.java)**      Level: Medium      Tags: [Array, Backpack DP, DP]
      

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

**61. [Unique Binary Search Tree II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree%20II.java)**      Level: Medium      Tags: [BST, DP, Divide and Conquer, Tree]
      

给一个数字n, 找到以(1...n)为node的所有unique BST.

#### BST
- 根据BST规则, divide and conquer
- 取一个value, 然后分两半(start, value - 1), (value + 1, end) 分别dfs
- 然后左右两边的结果cross match

#### DP? Memoization?



---

**62. [Max Sum of Rectangle No Larger Than K.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Sum%20of%20Rectangle%20No%20Larger%20Than%20K.java)**      Level: Hard      Tags: [Array, BST, Binary Search, DP, Queue, TreeSet]
      

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

**63. [Flip Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Flip%20Game%20II.java)**      Level: Medium      Tags: [Backtracking, DFS, DP]
      

String 只包含 + , - 两个符号. 两个人轮流把consecutive连续的`++`, 翻转成 `--`.

如果其中一个人再无法翻转了, 另一个人就赢. 求: 给出string, 先手是否能赢.

#### Backtracking
- curr player 每走一步, 就生成一种新的局面, dfs on this
- 等到dfs结束, 不论成功与否, 都要backtracking
- curr level: 把"++" 改成 "--"; backtrack的时候, 改回 '--'
- 换成boolean[] 比 string/stringBuilder要快很多, 因为不需要重新生成string.
- ++ 可以走 (n - 1)个位置: 
- T(N) = (N - 2) * T(N - 2) = (N - 4) * (N - 2) * T(N - 4) ... = O(N!)

##### iterate based on "++"
- 做一个String s的 replica: string or stringBuilder
- 每次dfs后, 然后更替里面的字符 "+" => "-"
- 目的只是Mark已经用过的index
- 真正的dfs 还是在 original input string s 身上展开
- 每次都重新生成substring, 并不是很efficient

##### Game theory
- 保证p1能胜利，就必须保持所有p2的move都不能赢
- 或者说, 在知道棋的所有情况时, 只要p2有一种路子会输, p1就一定能走对路能赢.
- 同时，p1只要在可走的Move里面，有一个move可以赢就足够了。
- p1: player1, p2: player2

#### O(N^2) 的 DP
- 需要Game Theory的功底, Nim game. https://www.jiuzhang.com/qa/941/
- http://www.1point3acres.com/bbs/thread-137953-1-1.html
- TODO: https://leetcode.com/problems/flip-game-ii/discuss/73954/Theory-matters-from-Backtracking(128ms)-to-DP-(0ms)



---

**64. [Longest Palindromic Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Palindromic%20Substring.java)**      Level: Medium      Tags: [DP, String]
      

给一个string, 找到最长的palindrome substring.

Related: Longest Palindromic Subsequence, Palindrome Partioning II

O(n^2) is not too hard to think of. How about O(n)?

#### String, Palindrome definition
- 从中间劈开, 遍历i: 从n个不同的点劈开: 每次劈开都看是否可以从劈开出作为palindromic的中点延伸
- palindrome两种情况: odd, even palindrome
- Worst case: 整个string都是相同字符，time complexity变成： 1 + 2 +３　＋　．．．　＋n = O(n^2)

#### DP: isPalin[][]
- 穷举double for loop. O(n^2)
- boolean isPalin[i][j], 每次确认有palindrome就记录下来true / false
- 穷举的for loop计算顺序: end point j, and stat point i = [0, j]
- 在计算 isPalin[i][j]的时候, isPalin[i+1][j-1]应该已经计算过了.
- double for loop: O(n^2). slower, because it guarantees O(n^2) due to the for loop

#### O(n) 
- TODO
- https://www.felix021.com/blog/read.php?2040



---

**65. [Longest Palindromic Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Palindromic%20Subsequence.java)**      Level: Medium      Tags: [DFS, DP, Interval DP, Memoization]
      

给一个string s, 找最长的sub-sequence which is also palindrome.

注意！subsequence并不是substring, 是可以skip letter / non-continuous character sequence
    
#### Interval DP
- 用[i][j]表示区间的首尾
- 考虑3个情况: 砍头, 砍尾, 砍头并砍尾 (考虑首尾关系)
- Iteration一定是以i ~ j 之间的len来看的. 
- len = j - i + 1; 那么反推, 如果len已知, j = len + i -1;
- 注意考虑len == 1, len == 2是的特殊情况.
- time/space: O(n^2)

#### Memoization
- 同样的方式model dp[i][j]: range [i, j] 之间的  max palindromic length
- 三种情况: 
- 1. 首尾match 继而 dfs[i+1, j-1]
- 2. 首尾不match,dfs[i+1,j] 
- 3. 首尾不match,dfs[i,j-1] 
- 注意: init dp[i][j]=-1, dfs的时候查dp[i][j] 是否算过
- more about dfs: bottom-up, first dive deep into dfs(i+1,j-1) till the base cases.
- time/space: O(n^2)
- prepare dp[n][n]: O(n^2); dfs: visit all combinations of [i,j]: O(n^2)




---

**66. [Jump Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game%20II.java)**      Level: Hard      Tags: [Array, Coordinate DP, DP, Greedy]
      

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

**67. [Triangles.java](https://github.com/awangdev/LintCode/blob/master/Java/Triangles.java)**      Level: Medium      Tags: [Array, Coordinate DP, DFS, DP, Memoization]
      

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

**68. [Range Sum Query - Immutable.java](https://github.com/awangdev/LintCode/blob/master/Java/Range%20Sum%20Query%20-%20Immutable.java)**      Level: Easy      Tags: [DP, PreSum]
      

给一串数字, 求sumRange.

#### PreSum
- 就是pre sum 的definition
- preSum也是dp[]一种最简易的形式把.
- dp[i], preSum[i]: 前(i-1)个元素的和.



---

**69. [Remove Invalid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Invalid%20Parentheses.java)**      Level: Review      Tags: [BFS, DFS, DP]
      

给一个string, 里面有括号和其他字符. 以最少刀 剪出 valid string, 求所有这样的string.

这个题目有多种解法, 最强就是O(n) space and time

#### DFS and reduce input string
- in dfs: remove the incorrect parentheses one at a time
- detect the incorrect parentheses by tracking/counting (similar to validation of the parentheses string): `if(count<0)`
- once detected, remove the char from middle of s, and dfs on the rest of the s that has not been tested yet.

##### Core concept: reverse test
- `if a parenthese string is valid, the reverse of it should also be valid`
- Test s with open='(', close=')' first; **reverse s**, and test it with open=')', close='('

##### Minor details
- only procceed to remove invalid parenthese when `count<0`, and also break && return dfs after the recursive calls.
- The above 2 facts eliminates all the redundant results.
- Reverse string before alternating open and close parentheses, so when returning final result, it will return the correct order.
- Open questions: how does it guarantee minimum removals?

##### Backtracking
- 如果用stringbuffer, 那么久不会每次create new string, 但是需要maintain这个string buffer, 就会backtracking

##### Complexity
- Seems to be O(n), but need to derive

#### BFS
TODO

#### DP



---

**70. [Number Of Corner Rectangles.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20Of%20Corner%20Rectangles.java)**      Level: Medium      Tags: [DP, Math]
      

具体看题目: count # of valid rectangles (four corner are 1) in a grid[][].

#### basic thinking + Math
- Fix two rows and count matching columns
- Calculate number rectangles with `combination` concept:
- total number of combinations of pick 2 points randomly: count * (count - 1) / 2

#### DP
- TODO. HOW?

#### Brutle
- O(m^2 * n^2), times out



---

**71. [Palindromic Substrings.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindromic%20Substrings.java)**      Level: Medium      Tags: [DP, String]
      

根据题意, count # of palindromic substring. (不同index截取出来的substring算不同的情况)

#### isPalin[][]
- build boolean[][] to check isPalin[i][j] with DP concept
- check all candidates isPalin[][]
- O(n^2)

#### odd/even split check
https://leetcode.com/problems/palindromic-substrings/discuss/105689/Java-solution-8-lines-extendPalindrome



---

**72. [Paint Fence.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20Fence.java)**      Level: Easy      Tags: [DP, Sequence DP]
      
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

**73. [Word Break.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Break.java)**      Level: Medium      Tags: [DP, Sequence DP]
      
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

**74. [Best Time to Buy and Sell Stock I.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20I.java)**      Level: Easy      Tags: [Array, DP, Sequence DP]
      

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

**75. [Best Time to Buy and Sell Stock II.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Status DP]
      
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

**76. [Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Subsequence.java)**      Level: Medium      Tags: [Binary Search, Coordinate DP, DP, Memoization]
      
time: O(n^2) dp, O(nLogN) binary search
space: O(n)

无序数组, 找最长的上升(不需要连续)数组 的长度. 先做O(n^2), 然后可否O(nLogN)?

#### DP, double for loop, O(n^2)
- 找subsequence: 不需要continous, 可以skip candidate
- 考虑nums[i]结尾的时候, 在[0, i), dp[i - 1] 里count有多少小于nums[i]
- dp[i]: 到i为止 (对于所有 j in [0, i], 记录max length of increasing subsequence
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

**77. [Best Time to Buy and Sell Stock with Transaction Fee.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Transaction%20Fee.java)**      Level: Medium      Tags: [Array, DP, Greedy, Sequence DP, Status DP]
      
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

**78. [Target Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Target%20Sum.java)**      Level: Medium      Tags: [DFS, DP]
      

// 如何想到从中间initialize



---

**79. [Number of Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Longest%20Increasing%20Subsequence.java)**      Level: Medium      Tags: [Coordinate DP, DP]
      
time: O(n^2)
time: O(n)

给一串 unsorted sequence, 找到长 increasing subsequence 的个数!

#### Coordinate DP
- 需要能够判断综合题, 分清楚情况和套路: combination of `longest subsequence` and `ways to do`, as well as global variable. 
- len[i] (我们平时的dp[i]): 在前i个元素中, 最长的 increasing subsequence length;
- count[i]: 在前i个元素中, 并且以 len[i]这个长度为准的 subsequence的 count. 或者: 在前i个元素中, ways to reach longest increasing subsequence.
- `len[i] == len[j] + 1`: same length, but different sequence, so add all `count[i] += count[j]`
- `len[i] < len[j] + 1`: 这就是更长的情况找到了, 那么有多少次 count[j] 有多少, count[i] 就有多少. 仔细想sequence: 长度增长了, 但是ways to reach i 没有增长.
- 同样的判断需要用在 maxLen 和 maxFreq上:
- 如果没有增长 maxLen 不变, maxFreq上面需要 +=count[i] (同一种长度, 多了更多的做法)
- 如果maxLen 变长, maxFreq 也就是采用了 count[i] = count[j]
- TODO: Is rolling array possible?

#### 相关
- 都是 Coordiate DP, DP的鼻祖家族:
- Longest Increasing Subsequence (跟这道题的一部分一模一样)
- Longest Continuous Increasing Subsequence (连续, 只check dp[i - 1])
- Longest Increasing Continuous Subsequence I, II (Lintcode, II 是matrix)



---

**80. [Minimum Swaps To Make Sequences Increasing.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Swaps%20To%20Make%20Sequences%20Increasing.java)**      Level: Medium      Tags: [Coordinate DP, DP, Status DP]
      


#### DP
- 特点: 上一步可能是swaped也可能是fixed
- 考虑A,B之间的现状: `A[i] > A[i - 1] && B[i] > B[i - 1]` 或者 `A[i] > B[i - 1] && B[i] > A[i - 1]`
- 问题: 如何把这个状态变成合理的strick-increasing状态?
- `A[i] > A[i - 1] && B[i] > B[i - 1]`: 1. 已经合理, 也不动.  2. [i], [i-1] 全部都swap
- `A[i] > B[i - 1] && B[i] > A[i - 1]`, 交错开来, 所以调换[i], 或者[i-1]: 1. 换[i-1]. 2. 换[i]
- 注意因为求min, 所以init value应该是 Integer.MAX_VALUE;



---

**81. [Minimum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Subarray.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Subarray]
      
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

