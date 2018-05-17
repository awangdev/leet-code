E
1526525767
tags: Tree, DFS

给一个inputSum, 然后dfs, 找到是否有一条path, 得出的path sum 跟 inputSum 一样.

#### DFS
- 确定好结尾的条件: is leaf && val == sum
- 每一层减掉node.val, 然后dfs.
- 写一写: root == null => false 对parent nodes的影响. 这里发现没影响, 所以可以简化成用1个functionDFS.


```
/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path 
such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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
DFS on the function itself, keep subtracting the root.val.
when root == null && sum == 0, return true;
*/

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && sum == root.val) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}


class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return dfs(root, sum);
    }
    
    public boolean dfs(TreeNode root, int sum) {
        if (root.left == null && root.right == null && sum == root.val) {
            return true;
        }
        boolean rst = false;
        if (root.left != null) {
            rst |= dfs(root.left, sum - root.val);
        }
        if (root.right != null) {
            rst |= dfs(root.right, sum - root.val);
        }
        return rst;
    }
}
```