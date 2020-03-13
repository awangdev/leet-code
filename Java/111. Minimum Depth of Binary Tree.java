E
tags: Tree, DFS, BFS
time: O(n)
space: O(n)

#### BFS
- Shortest path; minimum depth: 想到BFS, check level by level, BFS更能确保更快找到结果
- depth definition: reach to a leaf node, where node.left == null && node.right == null
- BFS using queue, track level.

#### DFS
- Divide and Conquer to find min depth. 
    - if one of child is null, return the other child depth + 1
    - Pick the min of the two child depth + 1
- need to visit all nodes


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
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        
        if (root.left == null || root.right == null) {
            return (root.left == null ? rightDepth : leftDepth) + 1;
        }
        
        return Math.min(leftDepth, rightDepth) + 1;
    }
}

```