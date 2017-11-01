E

弄明白path的意义: 连接node的edge.
要找MAX, 可以在class scope里面定义一个max variable.

用minimum amount of code来概括几种不同的情况: left == root, right == root, or left == root == right.

```
/*
Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output:

2
Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output:

2
Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
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
class Solution {
    private int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        countUnivaluePath(root);
        return max;
    }
    
    public int countUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        final int rootValue = root.val;
        final TreeNode leftNode = root.left;
        final TreeNode rightNode = root.right;
        int leftCount = countUnivaluePath(leftNode);
        int rightCount = countUnivaluePath(rightNode);
        int leftPathCount = (leftNode != null && leftNode.val == rootValue) ? leftCount + 1 : 0;
        int rightPathCount = (rightNode != null && rightNode.val == rootValue) ? rightCount + 1 : 0;
        // leftPathCount and rightPathCount both exist only when left == root == right.
        max = Math.max(max, leftPathCount + rightPathCount);
        // For upper level, only one path needs to be counted
        return Math.max(leftPathCount, rightPathCount);
    }
}
```