 
 
 
## dfs (1)
**0. [Binary Tree Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence.java)**      Level: Medium
      

找到binary tree 里的最长 consecutive sequence.

#### DFS
- Divide and Conquer. dfs
- 分开 看左边/右边
- 如果左边满足连续递增的规则, dfs (depth + 1), 如果不满足规则, dfs(depth = 1)
- 右边也是一样
- 对结果跟max作比较, return



---

