E

Divide and Conquery一个最小值. 注意处理Leaf的null, 用Integer.MAX_VALUE代替，这样可以避免错误counting.

```
/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Example
Given a binary tree as follow:

        1

     /     \ 

   2       3

          /    \

        4      5  

The minimum depth is 2

Tags Expand 
Depth First Search

Thinking Process:
Note: a little different from maxDepth:
If 1,#,2: the left route 1-# does not count as a path, so the minDepth is actually 2 in this example.
We need a helper function to calculate the minimum.
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
    //recursive:
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }
    
    public int getMin(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }
}


```