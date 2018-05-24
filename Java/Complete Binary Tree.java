E
1522011523
tags: Tree, BFS

A complete binary tree is a binary tree in which every level, except possibly the last,

is completely filled, and all nodes are as far left as possible

#### BFS
- 当出现了第一次有 null children的node的时候, 说明到了leaf level, mark flag = true;
- 自此以后，queue再不该有node再有child; queue后面出现的node的left/right child应该都是null
- 否则就是有问题, return false;


```
/*
Check a binary tree is completed or not. 
A complete binary tree is a binary tree that every level is completed filled except the deepest level. 
In the deepest level, all nodes must be as left as possible. See more definition

Example
    1
   / \
  2   3
 /
4
is a complete binary.

    1
   / \
  2   3
   \
    4
is not a complete binary.

Challenge
Do it in O(n) time

Tags Expand 
Binary Tree
*/

/*
	Thoughts: 
	Do a BFS.
	Once null occur, all the rest following it has to be null
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

public class Solution {
    /**
     * @param root, the root of binary tree.
     * @return true if it is a complete binary tree, or false.
     */
    public boolean isComplete(TreeNode root) {
    	if (root == null) {
    		return true;
    	}

    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.offer(root);
        boolean flag = false;
    	while (!queue.isEmpty()) {
    		TreeNode node = queue.poll();
    		if (flag && (node.left != null || node.right != null)) {
    		    return false;
    		}
    		if (node.left == null && node.right != null) {
    			return false;
    		} else if (node.left == null || node.right == null) {
    		    flag = true;
    		} 
    		if (node.left != null) {
        		queue.offer(node.left);    		    
    		}
            if (node.right != null) {
                queue.offer(node.right);    
            }
    	}

    	return true;
    }

}


```