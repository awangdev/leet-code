/*
Binary Tree Inorder Traversal
Given a binary tree, return the inorder traversal of its nodes' values.

Example
Given binary tree {1,#,2,3},

   1
    \
     2
    /
   3
 

return [1,3,2].

Challenge
Can you do it without recursion?

Tags Expand 
Binary Tree

Thinking process:
1. Use recursive function: divide and conquer
	recursive on left
	result.add( current.val)
	recursive on right
2. Use Stack to do traversal
	while stack not empty {
		stack all left childs 
		result.(current.val)
		stack 1 right child
	}
*/



public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Inorder in ArrayList which contains node values.
     */

    
    	//Recursive - Divide and Conquer
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> rst =  new ArrayList<Integer>();
        if (root == null) {
            return rst;
        }
        inorderRec(rst, root);
        return rst;
    }
    public void inorderRec(ArrayList<Integer> rst, TreeNode root) {
        if (root == null) {
            return;
        }
        inorderRec(rst, root.left);
        rst.add(root.val);
        inorderRec(rst, root.right);
    }

	//Traversal using Stack
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if (root == null) {
            return rst;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        stack.push(curr);
        while (!stack.empty()) {
            while (curr != null && curr.left != null) {
                curr = curr.left;
                stack.push(curr);
            }
            curr = stack.pop();
            rst.add(curr.val);
            curr = curr.right;
            if (curr != null) {
                stack.push(curr);
            }
        }
        return rst;    
    }
    
    
    
}

