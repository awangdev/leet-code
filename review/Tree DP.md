 
 
 
## Tree DP (1)
**0. [Binary Tree Maximum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum.java)**      Level: Hard      Tags: [DFS, DP, Tree, Tree DP]
      

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

