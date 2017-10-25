E

基础binary tree traversal. 注意对null child的判断

```
/*
Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:
Input: 
    Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
         3
        / \
       4   5
      / \   \ 
     5   4   7
Note: The merging process must start from the root nodes of both trees.
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
1. Regular traversal. Always pass t1, t2.
2. Chech closing conditions: thinking from the leaf's perspective where it stops the resersive calls
*/
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        final TreeNode node = new TreeNode(0);
        if (t2 == null) {
            node.val += t1.val;
            node.left = mergeTrees(t1.left, null);
            node.right = mergeTrees(t1.right, null);
        } else if (t1 == null) {
            node.val += t2.val;
            node.left = mergeTrees(null, t2.left);
            node.right = mergeTrees(null, t2.right);
        } else {
            node.val = t1.val + t2.val;
            node.left = mergeTrees(t1.left, t2.left);
            node.right = mergeTrees(t1.right, t2.right);
        }
        return node;
    }
}
```