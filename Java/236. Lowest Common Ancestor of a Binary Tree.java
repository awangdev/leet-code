M
tags: Tree, DFS
time: O(n)
space: O(n)

给一个Binary Tree root, 以及两个node p, q. 找 p 和 q 的 lowest common ancestor

#### DFS
- 因为是 binary tree, 所以直接盲目搜索搜索path不efficient, use extra space and waste time
- 巧用DFS来找每一个node的common ancestor. Need the assumption: 1. unique nodes across tree; 2. must have a solution
  - Base Case: 当root == null, p or q is found (`root == p || root == q`)，那么就return the root as LCA
  - 三种情况:
    - 1. leftLCA and rightLCA all found: `each path has found one of p and q node as LCA`. Therefore, curr root is the lowest ancestor
    - 2. One of leftLCA and rightLCA is found: return whichever one found
    - 3. both LCAs are null, return null
- Worst case, visit all nodes to find p q at last level, last two leaves: time O(n), stack space O(n)

```
/**
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

*/

/*
Thoughts:
Use the 'lowestCommonAncestor' to track the ancestor for at least 1 node.
At any node, if it's a ancestor, the ancestor of left node && ancestor of right node must both exist.
Only return root when p or q is matched; other cases, return null
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || q == root || p == root) return root; // p or q is found, return itself as LCA

        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);
        
        if (leftLCA != null && rightLCA != null) return root;
        else if (leftLCA != null || rightLCA != null) // found a LCA of p and q
          return leftLCA != null ? leftLCA : rightLCA;
        return null;
    }
}


/*
Alternative, build dfs list
1. run dfs to find list of nodes to reach p, and the list of nodes to reach q. boolean dfs(list, p)
    - return true if found
    - backtrack if not found
2. compare and find last common node
time: O(n)
space: O(n)
*/

```
