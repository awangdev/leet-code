M
tags: Stack, Tree, BFS
time: O(n)
space: O(n)
    
#### Queue
- 简单的level traversal.根据level奇数偶数而add到不同位子.
- Option1: based on level % 2, insert to front/end of list
- Option2: based on level, insert right/left of node into queue

```
/* 
Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).

Example
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
 

return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
]
Tags Expand 
Tree Search Breadth First Search Queue Binary Tree

*/

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

//BFS. first level = 0; level % 2 = 1, list.add(0, ...)
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();
        if (root == null) return rst;
        
        int level = 0, size = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0 ; i < size; i++) {
                TreeNode node = queue.poll();
                if (level % 2 == 0) list.add(node.val);
                else list.add(0, node.val);
                if(node.left!= null) queue.offer(node.left);
                if(node.right!= null) queue.offer(node.right);
            }
            level++;
            rst.add(list);
        }
        
        return rst;
    }
}

```
