 
 
 
## Coordinate DP (4)
**0. [Unique Paths II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Paths%20II.java)**      Level: Medium
      

跟unique path的grid一样, 目标走到右下角, 但是grid里面可能有obstacle, 不能跨越. 求unique path 的count.

#### 坐标DP
- dp[i][j]: # of paths to reach grid[i][j]
- dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
- 考虑最终结尾需要的状态:如何组成,写出公式.
- 公式中注意处理能跳掉的block, marked as 1. '到不了', 即为 0 path in dp[i][j].



---
**1. [Unique Path.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Path.java)**      Level: Medium
      

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
**2. [Maximal Square.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Square.java)**      Level: Medium
      

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
**3. [Minimum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Path%20Sum.java)**      Level: Medium
      

#### DP
- 往右下角走, 计算最短的 path sum. 典型的坐标型.
- 注意: init 第一行的时候, 要accumulate dp[0][j - 1] + grid[i][j], 而不是单纯assign grid[i][j]

##### rolling array
TODO




---
