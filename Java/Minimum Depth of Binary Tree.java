E
1525669253
tags: Tree, DFS, BFS

#### DFS
- Divide and Conquery一个最小值. 
- 注意处理Leaf的null: null leaf 出现的时候, 就忽略这个leaf, 直接return算有leaf
- 另一种count的方法: 用Integer.MAX_VALUE代替 null leaf，这样可以避免错误counting. (不能直接recursive)
- 这个无论如何都要走所有node, 所以dfs应该比较适合.

#### BFS
- Shortest path; minimum depth: 想到BFS, check level by level, BFS更能确保更快找到结果
- depth definition: reach to a leaf node, where node.left == null && node.right == null
- BFS using queue, track level.

```
/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path 
from the root node down to the nearest leaf node.

Example
Given a binary tree as follow:

        1

     /     \ 

   2       3

          /    \

        4      5  

The minimum depth is 2

Tags Expand 
Depth First Search
*/

/* 
BFS, 99.67%
minimum depth, consider BFS, it reaches the termination point faster:
if any node has null left/right child, that indicates the first leaf
*/
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) return level;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return level;
    }
}


// DFS
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        }
        
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        
        if (root.left == null || root.right == null) {
            return (root.left == null ? rightDepth : leftDepth) + 1;
        }
        
        return Math.min(leftDepth, rightDepth) + 1;
    }
}

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

/**
Previous notes:
Thinking Process:
Note: a little different from maxDepth:
If 1,#,2: the left route 1-# does not count as a path, so the minDepth is actually 2 in this example.
We need a helper function to calculate the minimum.
 */
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    //recursive:
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }
    
    public int getMin(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }
}


```