E
1533515503
tags: Tree

找longest path (include or not include root)

跟Binary Tree Maximum Path Sum 的想法一样: 处理single path, 或者combined path (do not include curr root)

#### Singlepath, combined path
- `int[]{combinedPath, singlePath}`;
- pick single path + 1: `singlePath = Math.max(left[1] , right[1]) + 1`;
- complete left/right child, or join curr root: `combinedPath = Math.max(Math.max(left[0], right[0]), left[1] + right[1] + 1)`;

```
/*
Given a binary tree, you need to compute the length of the diameter of the tree. 
The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
This path may or may not pass through the root.

Example:
Given a binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.


*/

/*
No special condition: left+root+right, or left, or right
recursive on itself.
end condition: if null -> 0, if leaf -> 1
*/
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int[] rst = dfs(root);
        
        return Math.max(rst[0], rst[1]) - 1;
    }
    
    private int[] dfs(TreeNode root) {
        if (root == null) return new int[] {0, 0};
        
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int singlePath = Math.max(left[1] , right[1]) + 1; // pick single path + 1
        int combinedPath = Math.max(Math.max(left[0], right[0]), left[1] + right[1] + 1); // complete left/right child, or join curr root.
        return new int[]{combinedPath, singlePath};
    }
}
```