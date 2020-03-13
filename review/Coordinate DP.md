 
 
 
## Coordinate DP (17)
**0. [Jump Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game%20II.java)**      Level: Hard      Tags: [Array, Coordinate DP, DP, Greedy]
      

给一串数字 是可以跳的距离. goal: 跳到最后的index 所可能用的最少次数.

#### Method1: Greedy
- maintain the `farest can go`, and use it the target for i increse towards. Why?
    - 1) when I know the `farest can go`, in fact it is just currently 1 step away.
    - 2) why to iterate from curr `i to farset`? In range [i, farest], we will calc the new `maxRange`
    - 3) once `i` reaches `farset`, update `farest = maxRange`
- greedy concept: once we know the farest we can reach at the moment, it is just 1 step away :)
- On average should be jumpping through the array without looking back
- time: average O(n)
- Impl:
    - 图解 http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
    - track the farest point
    - whenver curr index reachest the farest point, that means we are making a nother move, so count++
    - there seems to have one assumption: must have a solution. Otherwise, count will be wrong number. 
    - 其实跟第一个greedy的思维模式是一模一样的.


#### Method2: DP
- DP[i]: 在i点记录，走到i点上的最少jump次数
- dp[i] = Math.min(dp[i], dp[j] + 1);
- condition (j + nums[j] >= i)
- 注意使用 dp[i] = Integer.MAX_VALUE做起始值, 来找min
- time: O(n^2), slow, and timesout



---

**1. [Triangles.java](https://github.com/awangdev/LintCode/blob/master/Java/Triangles.java)**      Level: Medium      Tags: [Array, Coordinate DP, DFS, DP, Memoization]
      
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

**2. [Longest Increasing Continuous subsequence II.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence%20II.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP, Memoization]
      
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

**3. [Longest Increasing Continuous subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence.java)**      Level: Easy      Tags: [Array, Coordinate DP, DP]
      
https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/

O(n)跑2遍for.
O(1)是用了两个int来存：每次到i点时，i点满足条件或不满足条件所有的longestIncreasingContinuousSubsequence.
特点：返跑一回，ans还是继续和left轮的ans作比较；求的所有情况的最大值嘛。



---

**4. [Russian Doll Envelopes.java](https://github.com/awangdev/LintCode/blob/master/Java/Russian%20Doll%20Envelopes.java)**      Level: Hard      Tags: [Binary Search, Coordinate DP, DP]
      
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

**5. [Number of Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Longest%20Increasing%20Subsequence.java)**      Level: Medium      Tags: [Coordinate DP, DP]
      

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

**6. [Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Subsequence.java)**      Level: Medium      Tags: [Binary Search, Coordinate DP, DP, Memoization]
      

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

**7. [Longest Valid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Valid%20Parentheses.java)**      Level: Hard      Tags: [Coordinate DP, Stack, String]
      
给一串string, 里面只有`(`, `)`. 找最长valid parentheses 的长度.

#### 1D Coordinate DP
- use dp[i] track local max, maintain global max
- int[] dp. dp[i]: longest valid string that ends on i.
- 结尾是 ')', 2种情况: 1. 刚好s[i-1]是'('; 2. s[i]的')'更前面的一个起始'(' 对应
- 注意, 结尾如果是'('属于不合理情况, 忽略.
- init: dp[0] = 0, 单个char不可能成型.
- 计算顺序: 从左到右, 找local max, maintain global max
- O(n) space, O(n) runtime

#### Stack
- Stack 里面存所有的open/close parentheses.
- 如果遇到stack.top()刚好开合结掉, 就stack.pop().
- 剩下的都是不合理的elements.
- 有点像negatively找 solution: `endIndex - 最后一个failedIndex(stack.pop()) - 1`, 应该就是最后一个succeeded string的长度
- 每次更新 endIndex 为stack.top(), 然后从stack继续找下一个failedIndex
- 所有的length作比较, 就可以找出最长length
- O(n) stack space, O(n) runtime. 应该比dp慢一点, 因为做了2遍O(n)




---

**8. [Longest Increasing Path in a Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Path%20in%20a%20Matrix.java)**      Level: Hard      Tags: [Coordinate DP, DFS, DP, Memoization, Topological Sort]
      
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

**9. [Minimum Swaps To Make Sequences Increasing.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Swaps%20To%20Make%20Sequences%20Increasing.java)**      Level: Medium      Tags: [Coordinate DP, DP, Status DP]
      

#### DP
- 特点: 上一步可能是swaped也可能是fixed
- 考虑A,B之间的现状: `A[i] > A[i - 1] && B[i] > B[i - 1]` 或者 `A[i] > B[i - 1] && B[i] > A[i - 1]`
- 问题: 如何把这个状态变成合理的strick-increasing状态?
- `A[i] > A[i - 1] && B[i] > B[i - 1]`: 1. 已经合理, 也不动.  2. [i], [i-1] 全部都swap
- `A[i] > B[i - 1] && B[i] > A[i - 1]`, 交错开来, 所以调换[i], 或者[i-1]: 1. 换[i-1]. 2. 换[i]
- 注意因为求min, 所以init value应该是 Integer.MAX_VALUE;



---

**10. [674. Longest Continuous Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/674.%20Longest%20Continuous%20Increasing%20Subsequence.java)**      Level: Easy      Tags: [Array, Coordinate DP, DP, Sliding Window]
      

找连续的持续上升子序列的长度.

#### Sliding window
- update the window start index;
    - `left` in sliding window
    - update when we need to start a new range: `nums[i-1] >= nums[i]` 
- calculate the max distance `i - widowStart + 1`
- O(n) time and O(1) space

#### Simple Array solution
- size++ when meeting condition `nums[i] > nums[i - 1]`
- otherwise, reset size = 1
- track max all the way

#### Coordinate DP
- 1D coordinate, dp 的角标, 就是代表 index i 的状态
- 求最值, dp[i] = 在index i位置的最长子序列
    - 如果 nums[i] > nums[i - 1], dp[i] = dp[i - 1] + 1
    - 如果没有持续上升, 那么dp[i] = 1, 重头来过
- maintain max




---

**11. [221. Maximal Square.java](https://github.com/awangdev/LintCode/blob/master/Java/221.%20Maximal%20Square.java)**      Level: Medium      Tags: [Coordinate DP, DP]
      

只能往右边,下面走, 找面积最大的 square. 也就是找到变最长的 square.

#### DP
- 正方形, 需要每条边都是`一样长度`.
    - 以右下角为考虑点, 必须满足条件: left/up/diagonal的点都是1
    - 并且, 如果三个点分别都衍生向三个方向, 那么最长的 square 边就是他们之中的最短边 (受最短边限制)
- dp[i][j]: max square length when reached at (i, j), from the 3 possible directions
- dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
- init: 每个点都可能是边长1, 如果 matrix[i][j] == '1'
- Space, time O(mn)
- Rolling array: [i] 和 [i - 1] 之间的关系, 想到滚动数组优化 space, O(n) sapce.




---

**12. [62. Unique Path.java](https://github.com/awangdev/LintCode/blob/master/Java/62.%20Unique%20Path.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP]
      

2D array, 算走到最右下角，有多少种方式.

#### DP, 加法原理
- 计数DP: 2 ways to reach (i,j): dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    - non-overlapping: `dp[i - 1][j]`, `dp[i][j - 1]`
    - covers the only 2 possible way to reach (i,j)
- initialization: dp[i][0] = 1, dp[0][i] = 1
    - Of course, row i = 0, or col j = 0, there is only 1 way to reach
- time O(mn), space O(mn)

##### 滚动数组 Rolling Array
- [i] 只跟 [i - 1] 有关系, 用 curr/prev 建立滚动数组.
- space O(n) 优化空间

#### DFS + Memoization
- move from (0,0) towards (m, n)
- use Map<coordinate, steps> as memoization technique



---

**13. [64. Minimum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/64.%20Minimum%20Path%20Sum.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP]
      

#### DP
- Time, Space O(MN)
- 往右下角走, 计算最短的 path sum. 典型的坐标型.
- 注意: init 第一行的时候, 要accumulate dp[0][j - 1] + grid[i][j], 而不是单纯assign grid[i][j]
- Rolling Array
    - Time O(MN), Space O(N)
    - 1) 需要在同一个for loop里面完成initialization, 2) reuse dp[i][j]
    - Reason: dp[i % 2][j] 在被计算出来的时候, 马上在下一轮会被用. 被覆盖前不用,就白算
    - Option3 below initialize dp outside of loop: 看起来固然简单, 但是不方便空间优化

#### DFS (top-down) Thinking process
- Enumerate the possible 2 paths: go right, go down
- sub problem: dfs(i+1,j), dfs(i,j+1); pick the min of the two
- memoization: after the path from a point (i,j) to end is computed, record memo[i][j] to avoid repatative calculation
- time: O(mn), only visite and record memo[i][j] once.
- space: O(mn) extrem case of m=100000, n = 2; the stack height is O(mn)


---

**14. [63. Unique Paths II.java](https://github.com/awangdev/LintCode/blob/master/Java/63.%20Unique%20Paths%20II.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP]
      

跟unique path的grid一样, 目标走到右下角, 但是grid里面可能有obstacle, 不能跨越. 求unique path 的count.

#### 坐标DP
- dp[i][j]: # of paths to reach grid[i][j]
- Bottom-up: at end, there are 2 ways to reach dp[i][j]
    - dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
- Handle obstacle (cause dp[i][j] to be 0).

#### 坐标DP
- dp[i][j]: # of paths to reach grid[i][j]
- Bottom-up: at end, there are 2 ways to reach dp[i][j]
    - dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
- Handle obstacle (cause dp[i][j] to be 0).



---

**15. [523. Continuous Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/523.%20Continuous%20Subarray%20Sum.java)**      Level: Medium      Tags: [Coordinate DP, DP, Math, PreSum, Subarray]
      

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

**16. [361. Bomb Enemy.java](https://github.com/awangdev/LintCode/blob/master/Java/361.%20Bomb%20Enemy.java)**      Level: Medium      Tags: [Coordinate DP, DP]
      

2D grid, 每个格子里面可能是 'W' wall, 'E' enemy, 或者是 '0' empty.

一个bomb可以往4个方向炸. 求在grid上面, 最大能炸掉多少个敌人.

#### Method1: Corrdinate DP
- Space, Time: O(MN)
- dp[i][j] 就是(i, j)上最多能炸掉的enemy数量
- dp[i][j] 需要从4个方向加起来, 也就是4个方向都要走一遍, 所以分割成 UP/Down/Left/Right 4个 int[][]
- 最后一步的时候求max
- 分方向考虑的方法很容易想到,但是四个方向移动的代码比较繁琐.
- 往上炸: 要从顶向下考虑
- 往下炸: 要从下向上考虑
- 熟练写2D array index 的变换.

#### Method2: Analyze, col count array:
- Inspired by: https://leetcode.com/problems/bomb-enemy/discuss/83387/Short-O(mn)-time-O(n)-space-solution
- Analyize the problem: need to add up 2 directions at any given point.
    - Notice 1: if I traverse row by row, each colSum at a specific col j is likely to be the same
        - Unless there is a Wall in last row, so we have to calclate the col sum starting from current row, below the Wall
    - Notice 2: for row it is the same:
        - If I in a new spot row[i][j], where (i-1) is Wall, i need to sum row from 0
- we will process each point:
    - process row by row and add up row sum
    - buffer col[j] in an array vertically so we can resue
    - make sure to recalculate row sum or col sum if last row index or last col index is Wall
- time: O(mn) traversal
- space: O(n) only use a column array



---

