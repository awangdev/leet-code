E

BFS   

Use a flag . 当出现了第一次有 null children的node的时候，   
说明complete tree的最低level出现了。    
自此以后，queue再不该有node再有child, queue后面出现的node的左右孩子应该都是null.    


```
/*
Check a binary tree is completed or not. A complete binary tree is not binary tree that every level is completed filled except the deepest level. In the deepest level, all nodes must be as left as possible. See more definition

Have you met this question in a real interview? Yes
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