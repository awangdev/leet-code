M
tags: Tree, DFS
time: O(n)
space: O(h)

#### DFS
- Given that left & right nodes are always available in pair, at each level: 
  - perform dfs to find new root: return deepest left node as root
  - pick out curr left node and fix current level: 
    - add right node as left node
    - add root as right node

```
/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

Example:

Input: [1,2,3,4,5]

    1
   / \
  2   3
 / \
4   5

Output: return the root of the binary tree [4,5,2,#,#,3,1]

   4
  / \
 5   2
    / \
   3   1  
Clarification:

Confused what [4,5,2,#,#,3,1] means? Read more below on how binary tree is serialized on OJ.

The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:

   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as [1,2,3,#,#,4,#,#,5].
*/


/*
- Given that left & right nodes are always available in pair, at each level: 
    - perform dfs to find new root: return deepest left node as root
    - pick out curr left node and fix current level: 
      - add right node as left node
      - add root as right node
*/
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) return root;
        TreeNode leftNode = root.left;
        // DFS to finish work on left path and return it as new root
        TreeNode newRoot = upsideDownBinaryTree(leftNode);
        
        // set original right to be root;
        leftNode.left = root.right;
        leftNode.right = root;
        
        // turn curr root into a leaf
        root.right = null;
        root.left = null;

        // return new root
        return newRoot;
    }
}
```