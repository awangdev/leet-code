/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
*/

/*
Thoughts: Need to it iteratively
Steps:
1. added root + all left most (whichever added last, process that first, so STACK)
2. Process the stack, for each node, if right !=null, then add right, called it rightNode
3. Add all rightNode's left child and grandchild ... etc.
Test
				5
		3				9
	1		4		6		10
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (root == null) {
        	return rst;
        }
        //Add left children
        dfsOnLeft(stack, root);
        //Process
        while(!stack.isEmpty()) {
        	TreeNode node = stack.pop();
        	rst.add(node.val);
        	if (node.right != null) {
        		node = node.right;
        		dfsOnLeft(stack, node);
        	}
        }//end while
        return rst;
    }

    public void dfsOnLeft(Stack<TreeNode> stack, TreeNode node) {
    	stack.push(node);
        while(node.left != null) {
        	node = node.left;
        	stack.push(node);
        }
    }
}