最prefer 2 stack的做法。特别清楚。
stack1和stack2合作。
记得这个做法。。。挺神奇的。



```
/*
Binary Tree Postorder Traversal
Given a binary tree, return the postorder traversal of its nodes' values.

Example
Given binary tree {1,#,2,3},

   1
    \
     2
    /
   3
 

return [3,2,1].

Challenge
Can you do it without recursion?

Tags Expand 
Binary Tree

*/


/*
    2. Non-recursive, interative
    use 2 stacks: pull water from s1 into s2
    in s2, we want: at each level, parentNode at bottom, then rightNode, then leftNode
    loop through s2, then we print out leftNode, rightNode, parentNode ... in postOrder.
*/
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Postorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if (root == null) {
            return rst;
        }
        //Recursive:
        /*
        ArrayList<Integer> right = postorderTraversal(root.right);
        ArrayList<Integer> left = postorderTraversal(root.left);
        rst.addAll(left);
        rst.addAll(right);
        rst.add(root.val);
        */
        //Non-recursive:
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s1.push(root);
        while (!s1.empty()) {
            TreeNode curr = s1.pop();
            s2.push(curr);
            if (curr.left != null) {
                s1.push(curr.left);
            }
            if (curr.right != null) {
                s1.push(curr.right);
            }
        }
        while (!s2.empty()) {
            rst.add(s2.pop().val);
        }
        return rst;
    }
}


/*
    Recap 12.08.2015
    Besides the 2 old ways of doing it, we can:
    3. Recursive with helper method.

*/
//Recursive with helper
public class Solution {
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if (root == null) {
            return rst;
        }
        helper(rst, root);
        return rst;
    }

    public void helper(ArrayList<Integer>rst, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            rst.add(node.val);
            return;
        }
        helper(node.left);
        helper(node.right);
        rst.add(node.val);
    }
}



/*

Thinking process:
1. Resursive: （divide and conquer）
    rec on left node
    rec on right node
    rst.addAll(left)
    rst.addAll(right)
    rst.add(curr)

*/
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Postorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if (root == null) {
            return rst;
        }
        //Recursive:
        ArrayList<Integer> right = postorderTraversal(root.right);
        ArrayList<Integer> left = postorderTraversal(root.left);
        rst.addAll(left);
        rst.addAll(right);
        rst.add(root.val);  
        return rst;
    }
}






```