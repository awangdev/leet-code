E
tags: DFS, Tree
1525670127

检查binary tree是否 identical. 

特点: subtree如果是有旋转的, 只要tree node value相等, 就可以算是identical

#### DFS
- 在DFS的基础上, 比对左左,左右,右左,右右

```
/*
Check two given binary trees are identical or not. 
Assuming any number of tweaks are allowed. 
A tweak is defined as a swap of the children of one node in the tree.

Example
    1             1
   / \           / \
  2   3   and   3   2
 /                   \
4                     4
are identical.

    1             1
   / \           / \
  2   3   and   3   4
 /                   \
4                     2
are not identical.

Note
There is no two nodes with the same value in the tree.

Challenge
O(n) time

Tags Expand 
Binary Tree
*/

/*
    check isTweakedIdentical(a.left, b.right);

    corner case: if both null, true;
    if one null, false
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
     * @param a, b, the root of binary trees.
     * @return true if they are tweaked identical, or false.
     */
    public boolean isTweakedIdentical(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return a == null && b == null;
        }
        if (a.val != b.val) {
            return false;
        }
        return (isTweakedIdentical(a.left, b.left) && isTweakedIdentical(a.right, b.right))
            || (isTweakedIdentical(a.left, b.right) && isTweakedIdentical(a.right, b.left));
    }
}




```