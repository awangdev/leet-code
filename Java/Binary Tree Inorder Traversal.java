1. Recursive: Divide and Conquer
2. Stack: add left nodes all the way; then print curr; move to right, add right if possible.
3. Recursive with helper method


注意inorder traversal在check right node的事后，
不论right == null or != null, 每次都要强行move to right.

如果不node = node.right,
很可能发生窘境：
node alays = stack.top(), 然后stack.top()一直是一开始把left 全部遍历的内容。所以就会infinite loop, 永远在左边上下上下。


```
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

*/

/*
    3. Use a helper method, recursively add to rst
*/

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Inorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if (root == null) {
            return rst;
        }
        helper(rst, root);
        
        return rst;
    }

    public void helper(ArrayList<Integer> rst, TreeNode node) {
        if (node == null) {
            return;
        }
        helper(rst, node.left);
        rst.add(node.val);
        helper(rst, node.right);
    }
}

/*
    2. Non-recursive
    Inorder traversal: use 1 stack, push left till end; pirnt/store curr; push right to stack
    'Curr' is always moving along with the curret position, representing the current node.

    Note: after curr = curr.right, curr could be null; this will skip the while loop, and move on to next element.

    Trick: in Inorder, we care the right node least. So we keep going with left and curr; 
    only when there is a right node, we add it;
    even after this, we go deep into that right node's left children all the way down.
*/

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Inorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if (root == null) {
            return rst;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        stack.add(curr);
        while (!stack.isEmpty()) {
            while (curr != null && curr.left != null) {
                stack.push(curr.left);
                curr = curr.left;
            }
            //Pop the top node: the curr node
            curr = stack.pop();
            rst.add(curr.val);
            //Move to right node, and push to satck if needed
            curr = curr.right;
            if (curr!= null) {
                stack.push(curr);
            }
        }
        return rst;
    }    
}


/*
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


```