DFS
Divide and conquer
```
/*
71% Accepted
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example
Given a binary tree as follow:

        1

     /     \ 

   2       3

          /    \

        4      5  

The maximum depth is 3

Tags Expand 
Tree Binary Tree Depth First Search

Thinking process:
check if root is null, return 0 if so.
Divide and return integer as the depth
Conquer: find the max and return depth + 1.
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
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        return Math.max(left, right) + 1;
    }
}


```