M
1519794111
tags: Binary Tree, DFS

分析题意后, 按照题意:
1. reserve right child
2. DFS flatten部分
3. 移花接木
4. flatten 剩下的.

```
/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.

Challenge
Do it in-place without any extra memory.

Tags Expand 
Binary Tree Depth First Search
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
1. Move root.left to root.rigthMost child (while loop find leaf)
2. Take notion of the first root.right, and flatten(xx) on it.
3. On each level, if not left child, flatten right child.
*/
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null) {
            flatten(root.right);
        } else {
            // Reserve right sub tree
            TreeNode rightNode = root.right;
            
            // Move left child to right side, cut off original left child
            root.right = root.left;
            root.left = null;

            // Flatten the new right child
            flatten(root.right);

            // Append previous right child to end of flatten tree
            TreeNode node = root;
            while (node.right != null) {
                node = node.right;
            }
            node.right = rightNode;
            flatten(root.right);
        }
    }
}


```