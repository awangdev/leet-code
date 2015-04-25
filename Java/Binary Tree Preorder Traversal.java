/*
Given a binary tree, return the preorder traversal of its nodes' values.

Note
Given binary tree {1,#,2,3},

   1
    \
     2
    /
   3
 

return [1,2,3].

Example
Challenge
Can you do it without recursion?

Tags Expand 
Tree Binary Tree
//Recommend way: using a stack
//Recursive way can be seen here: http://www.ninechapter.com/solutions/binary-tree-preorder-traversal/

Thinking process:
Check if root is null
use a container to save results

use current node
put right on stack
put left on stack
4. In next run, the ‘left’ will be on top of stack, and will be taken first. So the order becomes: parent -> left -> right
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
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
         Stack<TreeNode> stack = new Stack<TreeNode>();
         ArrayList<Integer> result = new ArrayList<Integer>();
         //Check top
         if (root == null) {
             return result;
         }
         //save root
         stack.push(root);
         //add to result, and load on stack. Right-side at the bottom
         while (!stack.empty()) {
             TreeNode node = stack.pop();
             result.add(node.val);
             if (node.right != null) {
                 stack.push(node.right);
             }
             if (node.left != null) {
                 stack.push(node.left);
             }
         }//while
         
         return result;
    }
}



//Divide and Conquer - recursive
/*
Check root == null?
Dive them into 2 recursive calls: get result from left, get result from right
Conquer - add all of the results together and return. As the pre-order defines:
add current parent
add left nodes
add right nodes.
*/

  //Divide and conquer
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
       
        if (root == null) {
            return result;
        }
        ArrayList<Integer> left = preorderTraversal(root.left);
        ArrayList<Integer> right = preorderTraversal(root.right);
        
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        
        return result;
    }

