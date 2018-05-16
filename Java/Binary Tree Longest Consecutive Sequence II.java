M
1526453838
tags: Tree

TODO:
- maybe use a object to catch? like in: https://www.jianshu.com/p/571b93217ee3
- my way is similar, should find out why not working.
- simple code: http://www.cnblogs.com/grandyang/p/6864398.html


```
/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, 
[1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. 
On the other hand, the path can be in the child-Parent-child order, 
where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].
*/

// NOT quite working, and too complicated
class Solution {
    int max = 0;
    public int longestConsecutive (TreeNode root) {
        if (root == null) {
            return 0;
        }
        int directDepth = dfs(root, 1);
        return Math.max(max, directDepth);
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }
        int val = node.val;
        // check node
        int leftDepth = 0, rightDepth = 0;
        if (node.left != null) {
            if (depth < 0 && node.left.val + 1 == val) {
                leftDepth = dfs(node.left, depth - 1);
            } else if (depth > 0 && node.left.val - 1 == val) {
                leftDepth = dfs(node.left, depth + 1);
            }
        }
        if (node.right != null) {
            if (depth < 0 && node.right.val + 1 == val) {
                rightDepth = dfs(node.right, depth - 1);
            } else if (depth > 0 && node.right.val - 1 == val) {
                rightDepth = dfs(node.right, depth + 1);
            }
        }
        // regular depth compare between math, left, right
        max = Math.max(max, Math.max(
                       Math.abs(leftDepth), Math.abs(rightDepth)));
        
        // Calculate overall connected depth
        if (node.left != null && node.right != null &&
            (node.left.val + 1 == node.right.val - 1 || 
             node.left.val - 1 == node.right.val + 1)) {
            max = Math.max(max, Math.abs(leftDepth)+Math.abs(rightDepth) - 1);
        }

        // Return the consecutive depth based on input depth
        if (depth > 0) {
            return leftDepth > 0 && rightDepth > 0 ? 
                   Math.max(leftDepth, rightDepth) : 
                   (leftDepth > 0 ? leftDepth : rightDepth);
        }


        return leftDepth < 0 && rightDepth < 0 ? 
               Math.min(leftDepth, rightDepth) : 
               (leftDepth > 0 ? rightDepth : leftDepth);
    }
}

```