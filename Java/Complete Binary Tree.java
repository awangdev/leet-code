E
1522011523
tags: Tree, BFS, DFS

#### BFS
- 当出现了第一次有 null children的node的时候, 说明到了leaf level, mark flag = true;
- 自此以后，queue再不该有node再有child; queue后面出现的node的left/right child应该都是null
- 否则就是有问题, return false;

#### DFS
- Count left-most-leaf depth
- Count right-most-leaf depth
- 如果两个depth不一样, 就 false
- LintCode跑不了


```
/*
Check a binary tree is completed or not. 
A complete binary tree is not binary tree that every level is completed filled except the deepest level. 
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

//DFS
//pseudocode, can't run LintCode
public class Solution {
    public boolean isComplete(TreeNode root) {
    	if (root == null) {
    		return true;
    	}

		return countLeftDepth(root) == countRightDepth(root);
	}

	private int countLeftDepth(TreeNode node) {
		if (node == null) {
			return 0;
		}
		return countLeftDepth(node.left) + 1;
	}

	private int countRightDepth(TreeNode node) {
		if (node == null) {
			return 0;
		}
		return countRightDepth(node.right) + 1;
	}
}

```