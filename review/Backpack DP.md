 
 
 
## Backpack DP (3)
**0. [Backpack.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack.java)**      Level: Medium
      

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

**1. [Backpack II.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20II.java)**      Level: Medium
      

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

**2. [Backpack V.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20V.java)**      Level: Medium
      

#### Backpack DP
- 与背包1不同: 这里不是check可能性(OR)或者最多能装的size是多少; 而是计算有多少种正好fill的可能性.
- 对于末尾, 还是两种情况:
- 1. i-1位置没有加bag
- 2. i-1位置加了bag
- 两种情况可以fill满w的情况加起来, 就是我们要的结果.
- 如常: dp[n + 1][w + 1]
- Space, time: O(MN)
- Rolling array, 空间优化, 滚动数组. Space: O(M)

#### 降维打击, 终极优化
- 分析row(i-1)的规律, 发现所有row(i)的值, 都跟row(i-1)的左边element相关, 而右边element是没用的.
- 所以可以被override.
- Space: O(M), 真*一维啊!
- Time: O(MN)



---

