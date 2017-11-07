E

BST: inorder-traversal: 先left node(adding to stack till left leav), 再process stack.peek (mid node), 再 add rightNode && dive to rightNode.left leaf

```
/*
Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
Note: There are at least two nodes in this BST.
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
Thoughts:
Given BST, we can in-order traverse the nodes and keep note of the minDiff.
*/
class Solution {
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int minDiff = Integer.MAX_VALUE;
        
        final Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        //Initialize: dive deep to left leaf
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        node = stack.peek();
        TreeNode lastNode = node;
        while (!stack.isEmpty()) {
            // Mid: take mid, calculate diff based on lastNode
            node = stack.pop();
            if (node.val != lastNode.val) {
                minDiff = Math.min(minDiff, Math.abs(node.val - lastNode.val));
            }
            lastNode = node;
            // Right: stack right node, and attempt to all rightNode.left tll the leaf
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        return minDiff;
    }
}
```