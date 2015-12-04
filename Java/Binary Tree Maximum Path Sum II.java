比Binary Tree Maximum Path Sum I 简单许多.
因为条件给的更多：at least 1 node + have to start from root => have to have root.
Single path: either left or right.
If the path sum < 0, just skip it.
```
/*
Binary Tree Maximum Path Sum II

Given a binary tree, find the maximum path sum from root.

The path may end at any node in the tree and contain at least one node in it.

Example
Given the below binary tree:

  1
 / \
2   3
return 4. (1->3)

Tags Expand 
Binary Tree
*/

/*
	Thoughts: maximum path sum from root, so it must include root, and it will be a single path from root to some point in the tree.
	(seems easier than Binary Tree Maximum path Sum I)
	'contains at least 1 node' -> at least have root.
	However, depending on child is positive or negative, we choose add or no add child
*/
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param root the root of binary tree.
     * @return an integer
     */
    public int maxPathSum2(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	return Math.max(helper(root.left),helper(root.right)) + root.val;
    }

    public int helper(TreeNode node) {
    	if (node == null) {
    		return 0;
    	} else if (node.left == null && node.right == null) {
    		return node.val > 0 ? node.val : 0;
    	}
    	int sum = Math.max(helper(node.left), helper(node.right)) + node.val;
    	return sum > 0 ? sum : 0;
    }
}

```