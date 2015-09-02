/*
Invert a binary tree.

Example
  1         1
 / \       / \
2   3  => 3   2
   /       \
  4         4
Challenge
Do it in recursion is acceptable, can you do it without recursion?

Tags Expand 
Binary Tree

Thoughts:
1. Swap every node's left and right child. Recursion seems good.

2. If not recursion, can use a queue to keep track of nodes. Keep swapping until the queue
is processed.
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
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void invertBinaryTree(TreeNode root) {
        if (root == null) {
        	return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
        	TreeNode node = queue.poll();
        	TreeNode temp = node.left;
        	node.left = node.right;
        	node.right = temp;
        	if (node.left != null) {
        		queue.offer(node.left);
        	}
        	if (node.right != null) {
        		queue.offer(node.right);
        	}
        }
    }
}


//Now, solution 2, try recursion.
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void invertBinaryTree(TreeNode root) {
        if (root == null) {
        	return;
        }
    	TreeNode temp = root.left;
    	root.left = root.right;
    	root.right = temp;

    	invertBinaryTree(root.left);
    	invertBinaryTree(root.right);		
    }
}



