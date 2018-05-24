M
1523330238
tags: Tree, DFS, DP, Status DP

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

```
/*
The thief has found himself a new place for his thievery again. 
There is only one entrance to this area, called the "root." 
Besides the root, each house has one and only one parent house. 
After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.

Subscribe to see which companies asked this question


 Tree Depth-first Search
Hide Similar Problems (E) House Robber (M) House Robber II


*/

/*
Thoughts:
There are lots of redundant calculations in this particular dfs.
Let:
- dp[0] represent: max value when you do not pick up root i.
- dp[1] represent: max value when you do pick up root i.

The logic break down should be the same as in DFS.

This is different from regular DP, where we have a global dp[][].
Here we still have to do dp, combine with dfs.
*/
class Solution {
    public int rob(TreeNode root) {
        int[] dp = dfs(root);
        return Math.max(dp[0], dp[1]);
    }
    
    public int[] dfs(TreeNode root) {
        int[] dp = new int[2]; // {0, 0}
        if (root == null) {
            return dp;
        }
        int[] leftDP = dfs(root.left);
        int[] rightDP = dfs(root.right);
        dp[0] = Math.max(leftDP[0], leftDP[1]) + Math.max(rightDP[0], rightDP[1]); // do not pick root
        dp[1] = root.val + leftDP[0] + rightDP[0]; // do pick root
        return dp;
    }
}

/*
Thoughts:
DFS. Always deal with the 3 ndoes: root, left, and right.
Either of them can be picked or not picked: overeall 6 possibilities: a bit of redundant calculation
Right/Left are independent to create combination cases.
*/
class Solution {
    public int rob(TreeNode root) {
        return Math.max(dfs(root, true), dfs(root, false));
    }
    
    public int dfs(TreeNode root, boolean visited) {
        if (root == null) {
            return 0;
        }
		if (root.left == null && root.right == null) {
			return visited ? root.val : 0;
		}
        int leftMaxValue = 0;
        int rightMaxValue = 0;
        if (visited) { // If root visited, we can't use left/right children
            leftMaxValue = dfs(root.left, !visited);
            rightMaxValue = dfs(root.right, !visited);
            return root.val + leftMaxValue + rightMaxValue;
        } else {
            leftMaxValue = Math.max(dfs(root.left, visited), dfs(root.left, !visited));
            rightMaxValue = Math.max(dfs(root.right, visited), dfs(root.right, !visited));
            return leftMaxValue + rightMaxValue;
        }
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*
	3.24.2016
	Thought: dfs should be able to handle this.
*/
public class Solution {
    public int rob(TreeNode root) {
    	if (root == null) {
    		return 0;
    	} else if (root.left == null && root.right == null) {
    		return root.val;
    	}
        return Math.max(dfs(root,true), dfs(root, false));
    }

    public int dfs (TreeNode node, boolean visit) {
    	if (node.left == null && node.right == null) {
    		if (visit){
    			return node.val;
    		} else {
    			return 0;
    		}
    	} 
    	int left = 0;
    	int right = 0;
    	if (visit) {
    		if (node.left != null) {
				left = dfs(node.left, !visit);
			}
			if (node.right != null) {
				right = dfs(node.right, !visit);
			}
			return node.val + left + right;
    	} else {
    		if (node.left != null) {
				left = Math.max(dfs(node.left, !visit), dfs(node.left, visit));
			}
			if (node.right != null) {
				right = Math.max(dfs(node.right, !visit), dfs(node.right, visit));
			}
			return left + right;
    	}
    	
    }
}


```