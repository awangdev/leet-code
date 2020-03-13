H
1529560210
tags: Tree, DFS, BST

BST里面有2个node misplace, 要归为. 要求: O(1) extra space

#### Observation
- BST inorder traversal should give small -> large sequence
- misplaced means: a **large**->small item would occur, and later a large>**small** would occur. 
- The first large && second small item are the 2 candidates. Example
- [1, 5,  7, 10,    12, 15, 18]
- [1, 5, `15, 10`, `12,  7`, 18]

#### dfs, O(1) extra space
- traverse, and take note of the candidate
- at the end, swap value of the 2 candidates

#### O(n) space
- inorder traversal the nodes and save in array, find the 2 items misplanced and swap them
- But O(n) space should not be allowed


```
/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?

*/

// DFS, track nodeA, nodeB. Beats 100%: https://leetcode.com/submissions/detail/159980654/
class Solution {
    TreeNode nodeA, nodeB, preNode = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        dfs(root);
        
        // swap
        int temp = nodeA.val;
        nodeA.val = nodeB.val;
        nodeB.val = temp;
    }
    
    /*
        DFS function that in-order traverse the tree.
     */
    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        
        // Assign nodeA, nodeB
        if (nodeA == null && preNode.val >= node.val) nodeA = preNode;
        if (nodeA != null && preNode.val >= node.val) nodeB = node;
        preNode = node; // regardless, assign preNode as curr mid

        dfs(node.right);
    }
}
```