M
1531703331
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
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0 ; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (level % 2 == 1) addChild(queue, node.left, node.right);
                else addChild(queue, node.right, node.left);
            }
            level++;
            rst.add(list);
        }
        
        return rst;
    }
    
    private void addChild(Queue<TreeNode> queue, TreeNode nodeA, TreeNode nodeB) {
        if (nodeA != null) queue.offer(nodeA);
        if (nodeB != null) queue.offer(nodeB);
    }
}
 
 
 /*
Thought:
1. realize: queue is no longer can be used. draw a example map to see why.
Instead, use 2 stacks.
Because we can only take the top of stack, and we are constantly adding to the top of the stac, so we need 2 stacks. One is the current one, will be empty every time when we finish the level. The other one is nextLevel, which holds next level’s nodes temporarily.
2. Use a boolean to track if which level it’s running at.
 */
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: A list of lists of integer include 
     *          the zigzag level order traversal of its nodes' values 
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return result;
        }
        
        Stack<TreeNode> currentLevel = new Stack<TreeNode>();
        Stack<TreeNode> nextLevel = new Stack<TreeNode>();
        
        currentLevel.push(root);
        boolean regularOrder = false;
        
        while (!currentLevel.empty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            
            while (!currentLevel.empty()) {
                TreeNode temp = currentLevel.pop();
                list.add(temp.val);
                if (regularOrder) {
                    addLevel(nextLevel, temp.right);
                    addLevel(nextLevel, temp.left);
                } else {
                    addLevel(nextLevel, temp.left);
                    addLevel(nextLevel, temp.right);
                }
            }
            result.add(list);
            regularOrder = !regularOrder;
            Stack<TreeNode> tmp = currentLevel;
            currentLevel = nextLevel;
            nextLevel = tmp;
        }
        return result;
    }
    
    public void addLevel(Stack<TreeNode> level, TreeNode node) {
        if (node != null) {
            level.push(node);
        }
    }
}


```
