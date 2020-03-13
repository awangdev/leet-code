 
 
 
## Memoization (15)
**0. [Triangles.java](https://github.com/awangdev/LintCode/blob/master/Java/Triangles.java)**      Level: Medium      Tags: [Array, Coordinate DP, DFS, DP, Memoization]
      
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

**1. [Longest Increasing Continuous subsequence II.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence%20II.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP, Memoization]
      
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

**2. [Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Subsequence.java)**      Level: Medium      Tags: [Binary Search, Coordinate DP, DP, Memoization]
      

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

**3. [Coins in a Line II.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20II.java)**      Level: Medium      Tags: [Array, DP, Game Theory, Memoization, MiniMax]
      
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

**4. [Longest Increasing Path in a Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Path%20in%20a%20Matrix.java)**      Level: Hard      Tags: [Coordinate DP, DFS, DP, Memoization, Topological Sort]
      
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

**5. [Coins in a Line III.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20III.java)**      Level: Hard      Tags: [Array, DP, Game Theory, Interval DP, Memoization]
      
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

**6. [Burst Balloons.java](https://github.com/awangdev/LintCode/blob/master/Java/Burst%20Balloons.java)**      Level: Hard      Tags: [DP, Divide and Conquer, Interval DP, Memoization]
      
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

**7. [1216. Valid Palindrome III.java](https://github.com/awangdev/LintCode/blob/master/Java/1216.%20Valid%20Palindrome%20III.java)**      Level: Hard      Tags: [DFS, DP, Memoization, String]
      

#### Method1: DP, utilize `Longest Palindrome Subsequence`
- Transform the problem:
    - `removing at most k items to make it a palindrome`
    - that is: find the longest palindrome subsequence with length x, such that `n - x <= k`
- `516. Longest Palindromic Subsequence` utilizes Interval DP to find LPS length x
- at the end, perform n - x <= k?
- time: O(n^2) to find LPS
- space: O(n^2) for dp

#### Method2: DFS with Memo
- Either times out or too much space used
- time: O(n^2)
- space: O(n^2) or O(k*n^2)



---

**8. [509. Fibonacci Number.java](https://github.com/awangdev/LintCode/blob/master/Java/509.%20Fibonacci%20Number.java)**      Level: Easy      Tags: [DP, Math, Memoization]
      
#### Memoization
- fib[n] = fibonacci(n - 1) + fibonacci(n - 2);

#### DP array.
- 滚动数组, 简化DP

#### recursively calculate
- recursively calculate fib(n - 1) + fib(n - 2). 公式没问题, 但是时间太长, timeout.




---

**9. [322. Coin Change.java](https://github.com/awangdev/LintCode/blob/master/Java/322.%20Coin%20Change.java)**      Level: Medium      Tags: [Backpack DP, DFS, DP, Memoization]
      

给一串不同数额的coins, 和total amount to spent. 求 最少 用多少个coin可以组合到这个amount. 每种coins个数不限量.


#### DP, Bottom -> UP 从小到大的顺序!
- define dp[x], 积累到amount x, 最少用多少个coin
- function: `dp[x] = Math.min(dp[x], dp[x - coinValue] + 1)`. two branches based on choosing coinValue or not
- initialization
    - dp[0], zero amount uses 0 coin. so dp[0] = 0
    - Utilize `Integer.MAX_VALUE` as default val for initialize dp[x]: 1) alert error stage; 2) easy comparison

#### Method2: Memoization, DFS, Top->Down
- create subproblem: (coins, amount - pickedCoin)
- memo[i] 依然表示: min # of coints to make amount i
- initialize memo[i] = Integer.MAX_VALUE
- 先选最后一步(遍历coins),  然后dfs做同样的操作
- 记录memo[amount] 如果已经给过value, 不要重复计算, 直接return.
- time: O(n * S), worst case it runs n coins for S(amount) iterations
- space: O(S)



---

**10. [140. Word Break II.java](https://github.com/awangdev/LintCode/blob/master/Java/140.%20Word%20Break%20II.java)**      Level: Hard      Tags: [Backtracking, DFS, DP, Hash Table, Memoization]
      

找出所有 word break variations, given dictionary. (`Word Break I` only checks possibility)

利用 memoization: `Map<prefix, List<suffix variations>>`

#### DFS + Memoization, pick a prefix, and find a list of suffix candidates
- IMPORANT, Memoization: `Map<prefix, List<suffix variations>>` to build substring segments. Reduces repeated calculation if the substring has been tried.
- Realize the input s expands into a tree of possible prefixes.
- Find list of candidates from subproblem, and cross-match
- DFS returns List<String> segments of target s: every for loop takes a prefix substring, and append with all suffix (result of dfs)
- Time O(n!). Worst case, permutation of unique letters: `s= 'abcdef....'`, and `dict=[a,b,c,d,e,f...]`

#### Method2: DFS on suffix + memo of failed cases, like in WordBreakI
- DFS on string: find a valid prefix, dfs on the suffix, building individual candidate in list till substring exhaust. 
- improvement:
    - use memo to record failed case (solved the timeout issue explained below)
    - use min/max to as boundary for dict check.
- core code is short; helper code is slightly longer

#### Method3: Regular DPs, kinda too slow
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

**11. [70. Climbing Stairs.java](https://github.com/awangdev/LintCode/blob/master/Java/70.%20Climbing%20Stairs.java)**      Level: Easy      Tags: [DP, Memoization, Sequence DP]
      
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
    - dp[0]往往是有特殊状态的. 这里, dp[0]: 1种方式可以原地不动
    - dp[1]=1, 1种方式到达index=1, 
    - 之后的`dp[2] = dp[0]+dp[1]`: 就是dp[0]的走法 or dp[1]的走法
- O(n) space, time

#### 序列DP, 滚动数组
- [i] only associates with [i-2], [i-1].
- %2
- O(1) space



---

**12. [170. Two Sum III - Data structure design.java](https://github.com/awangdev/LintCode/blob/master/Java/170.%20Two%20Sum%20III%20-%20Data%20structure%20design.java)**      Level: Easy      Tags: [Design, Hash Table, Memoization]
      

#### Hash Table, Memo
- Use Map<number, count > to store the inputs
- Iterate over map to find the pair
- Use Set<int> memo to store the success cases for fast return
- time: O(n), loop over all elements in map
- space: O(n), store all elements in map & memoization set



---

**13. [516. Longest Palindromic Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/516.%20Longest%20Palindromic%20Subsequence.java)**      Level: Medium      Tags: [DFS, DP, Interval DP, Memoization]
      

给一个string s, 找最长的sub-sequence which is also palindrome.

注意！subsequence并不是substring, 是可以skip letter / non-continuous character sequence
    
#### Interval DP
- Use example to understand: for any given ending char, 3 cases of palindromes
    - a. ss[i, j] is a palindrome. dp[i+1][j-1] + 2 since the two indexes are counted, assume dp[i + 1][j - 1] is calculated
    - b. ss[i + 1, j] is a palindrome
    - c. ss[i, j - 1] is a palindrome
- time/space: O(n^2)
- **Option1: start processing substring from tail**
    - set: `i = [n-1 towards 0]`, `j = i + 1`
    - consider ss[i, j], ss[i + 1, j], ss[i, j - 1]
    - since i starts from n - 1 -> 0 and j = i + 1, these are calculated and ready to go: dp[i+1][j-1], dp[i+1][j] and dp[i][j-1]  
    - FAST: skipped the initialization
- **Option2: start processing substring from head**
    - 用[i][j]表示区间的首尾: 考虑3个情况: 砍头, 砍尾, 砍头并砍尾 (考虑首尾关系)
    - Iteration on len:
    - len = j - i + 1; 那么反推, 如果len已知, `j = len + i - 1`;
    - 注意考虑len == 1, len == 2是的特殊情况.


#### Memoization
#### DFS + Memoization
- consider sub problems with 3 major cases
    - a. ss[i, j] is a palindrome: dfs check ss[i + 1, j - 1]
    - b. ss[i + 1, j] maybe a palindrome: dfs check ss[i + 1, j]
    - c. ss[i, j - 1] maybe a palindrome: dfs check ss[i, j - 1]
- memo[i][j]: max palindrome length in range [i, j], if calculated, return directly
- Init memo[i][j] = -1 to track the progress, memoization
    - 注意: init dp[i][j]=-1, dfs的时候查dp[i][j] 是否算过
    - more about dfs: bottom-up, first dive deep into dfs(i+1,j-1) till the base cases.
- Space: O(n^2)
- Time: O(n^2)
- prepare dp[n][n]: O(n^2); dfs: visit all combinations of [i,j]: O(n^2)





---

**14. [1043. Partition Array for Maximum Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/1043.%20Partition%20Array%20for%20Maximum%20Sum.java)**      Level: Medium      Tags: [DFS, DP, Graph, Memoization]
      

#### Top-Down DFS + Memoization
- Pick a subset (max-size k), and produce sub problem to solve by dfs
- NOTE: no need to change actual index value. That makes this problem easier (no need to record the choice path)
- time: O(n), calc memo[n]
- space: O(n), memo + stack depth



---

