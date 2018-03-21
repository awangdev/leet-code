E
1521646879
tags: Hash Table, Stack, Tree

Inorder traverse Binary Tree

#### Recursive
- 在自己的基础上recursive, 不用helper function
- Divide and Conquer, with helper(dfs) method

#### Stack
- Add left nodes all the way   
- Print curr   
- Move to right, add right if possible.   
  
注意stack.pop()在加完left-most child 的后，一定要curr = curr.right.

若不右移, 很可能发生窘境:

curr下一轮还是去找自己的left-most child，不断重复curr and curr.left, 会infinite loop, 永远在左边上下上下。


```
/*
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
Recursion Binary Tree Binary Tree Traversal

*/

/*
Thoughts:
Recursive, append left, itself, then right
*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) {
            return rst;
        }
        if (root.left == null && root.right == null) {
            rst.add(root.val);
            return rst;
        }
        List<Integer> left = inorderTraversal(root.left);
        List<Integer> right = inorderTraversal(root.right);
        
        rst.addAll(left);
        rst.add(root.val);
        rst.addAll(right);
        return rst;
    }
}

/*
    recap 3.15.2016
    Recursive
*/
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<Integer>();
        if (root == null) {
            return rst;
        }
        dfs(rst, root);
        return rst;
    }
    
    public void dfs(List<Integer> rst, TreeNode node) {
        if (node.left != null) {
            dfs(rst, node.left);
        }
        rst.add(node.val);
        if (node.right != null) {
            dfs(rst, node.right);
        }
    }
}




/*
    recap 3.15.2016
    Iterative
    stack: add left till end; consume top, if has right, add right; push right.left till end of right's left node.
*/
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<Integer>();
        if (root == null) {
            return rst;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        //Initialize
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        //iteratively add && process via inorder traversal
        while (!stack.isEmpty()) {
            node = stack.pop();
            rst.add(node.val);
            if (node.right != null) {//process right, but put right's left children on top of stack
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            
        }
        return rst;
    }
}


/*
    1. Use a helper method, recursively add to rst
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
            //Move to right node, and push to stack if needed
            curr = curr.right;
            if (curr!= null) {
                stack.push(curr);
            }
        }
        return rst;
    }    
}





```