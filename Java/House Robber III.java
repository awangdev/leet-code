H

由于无法用简单的方法构造DP array, 所以采取了普通的DFS。

The catch:    
判断当下的node是否被采用，用一个boolean来表示. 

1. 如果curr node被采用，那么下面的child一定不能被采用。
2. 如果curr node不被采用，那么下面的children有可能被采用，但也可能略过，所以这里用Math.max() 比较一下两种可能有的dfs结果。


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