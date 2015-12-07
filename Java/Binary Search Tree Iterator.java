理解binary search tree inorder traversal的规律。
都是先找left.left.left ....left. 为top。
然后再找parent,然后再right.

这个题目里面找到rst之后，首先考虑这个rst.right
	其实rst在这里虽然是most left node, 但对于rst.right来说，其实它也是parent.
所以每次把left全部弄一边的时候，parent node其实也都是顾及到了的。
```
/*
Design an iterator over a binary search tree with the following rules:

Elements are visited in ascending order (i.e. an in-order traversal)
next() and hasNext() queries run in O(1) time in average.
Have you met this question in a real interview? Yes
Example
For the following binary search tree, in-order traversal by using iterator is [1, 6, 10, 11, 12]

   10
 /    \
1      11
 \       \
  6       12
Challenge
Extra memory usage O(h), h is the height of the tree.

Super Star: Extra memory usage O(1)

Tags Expand 
Binary Tree LintCode Copyright Non Recursion Binary Search Tree Google LinkedIn Facebook
*/

/*
	Thoughts://http://blog.csdn.net/u014748614/article/details/46800891
	Put all left nodes into stack. Then top of stack must be the first element in in-order-traversal.
	We never add right node into stack directly, but ever time before returnning the rst node, we take care of rst.right right away.
		That is, find next() when rst.right as root.
	very smart use of a 'currnt' node.
	It's like a pointer on the tree, but only operates when that current node is not null, and under condition of having left child.
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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */
public class BSTIterator {
	public Stack<TreeNode> stack = new Stack<TreeNode>();
	public TreeNode current;
    //@param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
    	current = root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
    	return current != null || !stack.isEmpty();
    }
    
    //@return: return next node
    public TreeNode next() {
    	while (current != null) {
    		stack.push(current);
    		current = current.left;
    	}
    	TreeNode rst = stack.pop();
    	current = rst.right;
    	return rst;
    }
}










```