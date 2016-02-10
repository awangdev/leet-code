李特这的这个题目不错。写一遍example就能看出来inorder traversal。当然啦，不能直接全部traverse了，因为题目说有空间限制。

那么就traversal on the fly， 先左手DFS， 然后每次加上一个右手node,都再来一遍左手DFS。

存到一个后进先出的数据结构里，stack呗，然后头顶就是最小的了。

```
/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Tags: Tree Stack Design
Similar Problems: (M) Binary Tree Inorder Traversal, (M) Flatten 2D Vector, (M) Zigzag Iterator, (M) Peeking Iterator, (M) Inorder Successor in BST

*/

/*
Attempt, Thoughts:
Test
				5
		3				9
	1		4		6		10
return: 1,3,4,5,6,9,10. Looks like in-order taversal style, though don't traversal all at once because we can only store O(h) elements.
However, we can do inorder traversal on the fly, by mantaining a stack.
How about Priority queue of all left-most elements. 
	Do a run-through on left elements, add them all.
	When pop one element:
		(it cannot have left, because we've initially added them already)
		if has right:
			add right node
			check right's node's left-most(DFS), added all left nodes and left nodes' left-child
Well.. the way I did it, does not need priority queue. Just use a stack will be fine.
*/
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
	private Stack<TreeNode> stack = new Stack<TreeNode>();
    public BSTIterator(TreeNode root) {
        if (root == null) {
            return;
        }
        stack.push(root);
        while(root.left != null) {
        	root = root.left;
        	stack.push(root);
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        int rst = node.val;
        if (node.right != null) {
        	node = node.right;
        	stack.push(node);
        	while(node.left != null) {
	        	node = node.left;
	        	stack.push(node);
	        }
        }
        return rst;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
```