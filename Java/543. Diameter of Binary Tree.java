E
tags: Tree
time: O(n) when non-balanced
space: O(n) when non-balanced

找longest path (include or not include root)

跟Binary Tree Maximum Path Sum 的想法一样: 处理single path, 或者combined path (do not include curr root)

#### Singlepath and CombinedPath
- Option1: Use local single path max & global combined max
    - Since the local combined diameter is used for comparision, but not used for specific calculation
    - calculate path length (diameter), understand:
        - for single path: child single path value + 1 (curr node)
        - for combined path including curr node: left child single + right child path 
- Option2: record local combined and single path for each iteration
    - `int[]{combinedPath, singlePath}`;
    - single path: pick single path + 1: `singlePath = Math.max(left[1] , right[1]) + 1`;
    - combined path `combinedPath = Math.max(Math.max(left[0], right[0]), left[1] + right[1] + 1)`, find max from:
        - 1) complete left child combined path
        - 2) complete right child combined path
        - 3) combined path with curr root
    - Note: we treat a single node itself with diameter of 1, so we want to -1 in final result
        - problem statement wants the path length (not # of nodes or depth)

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
// Option1: with global combined max
class Solution {
    int globalCombinedMax = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return globalCombinedMax;
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        
        int left = dfs(root.left);
        int right = dfs(root.right);
        int localCombined = left + right;
        globalCombinedMax = Math.max(globalCombinedMax, localCombined); // complete left/right child, or join curr root.
        return Math.max(left , right) + 1; // pick single path + 1
    }
}


/*
Option2:
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
        int singlePath = Math.max(left[1] , right[1]) + 1; // pick single path + curr node
        int combinedPath = Math.max(Math.max(left[0], right[0]), left[1] + right[1] + 1); // complete left/right child, or join curr root.
        return new int[]{combinedPath, singlePath};
    }
}


```