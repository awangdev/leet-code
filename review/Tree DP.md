 
 
 
## Tree DP (1)
**0. [124. Binary Tree Maximum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/124.%20Binary%20Tree%20Maximum%20Path%20Sum.java)**      Level: Hard      Tags: [DFS, DP, Tree, Tree DP]
      

找max path sum,  可以从任意treeNode 到任意 treeNode.

#### DFS
- IMPORTANT: DO NOT ASSUME positive integers
- Overall idea: write example and realize 2 cases at each node:
    - 1. `combo sum`: left + right + root
    - 2. `single path sum WITH curr node`: left/right + root
- DFS returns the path over curr node: a path needs to be continuous, so we cannot skip curr node.
- IMPORTANT, key discovery: if left/right single path over curr node is less than 0: reutrn 0. 
    - Parent path will simply drop this path, since we want **maximize** the path sum.
    - It is so IMPORTANT: when left or right becomes 0, when comparing with global combo path:
        - it automatically covers a special case: `single left/right path + node`, since one of left/right == 0!!!
- With the above understanding: what if I want to skip curr node and just want left/right path w/o curr node:
    - it is handled and compared with global in dfs(node.left) or dfs(node.right) automatically!
- time: O(n), go over whole tree
- space: O(logn), tree height.

#### DP的思想
- tree给我们2条branch, 每条branch就类似于 dp[i - 1], 这里类似于dp[left], dp[right] 这样
- 找到 dp[left], dp[right] 以后, 跟 curr node结合. 
- 因为是找max sum, 并且可以skip nodes, 所以需要全局变量max
- 每次dfs() return的一定是可以继续 `continuously link 的 path`, 所以return `one single path sum + curr value`.

#### DFS, PathSum object
- 用 PathSum 比较特别. 没有 data structure的时候, 写起来比较繁琐.
- 第一次做有点难理解，复杂原因是：因为可能有负值啊。不能乱assume正数。   
- single path max 的计算是为了给后面的comboMax用的。
- 如果single path max小于0，那没有什么加到parent上面的意义，所以就被再次刷为0.
- combo的三种情况：(root可能小于0)   
- 1. 只有left    
- 2. 只有right
- 3. root大于0，那么就left,right,curr全部加起来。
- 情况1和情况2取一个最大值，然后和情况三比较。做了两个Math.max(). 然后就有了这一层的comboMax



---

