E
1516868090
tags: Tree, BST

方法1:
适合复习BST. 用DFS对待每个node. 注意BST的特征: 所有left nodes都小于当下node, 所有right nodes都大于当下node.

根据题意用[L,R]切割.如果node.val<L, 直接连node带左边全丢掉, return node.right. 处理R也是一样.
分制是, DFS leftNode, rightNode. 然后接在node.left, node.right.

方法2: 用迭代, 还没有写.

```
/*
Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:
Input: 
    1
   / \
  0   2

  L = 1
  R = 2

Output: 
    1
      \
       2
Example 2:
Input: 
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output: 
      3
     / 
   2   
  /
 1

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
0. Have node = root, and return node.
1. Consider root. If < L, node = node.right; if >R, node = node.left;
2. DFS into left, right.
3. At leaf, if node.left, node.right all null, return node. If not in [L, R], return null.
*/
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        TreeNode node = root; // 3, 0
        if (node.val < L) { // 0 < 1
            return trimBST(node.right, L, R); // 2
        } else if (node.val > R) {
            return trimBST(node.left, L, R);
        }
        node.left = trimBST(node.left, L, R); // 0, 1
        node.right = trimBST(node.right, L, R); // 4
        return node;
    }
}


```