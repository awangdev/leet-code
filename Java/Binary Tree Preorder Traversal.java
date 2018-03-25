E
1522009726
tags: Stack, Tree, DFS, BFS

#### Recursive
- 加root, left, then right. Obvious
- Divide and conquer
- 其实也不需要helper function

#### Iterative
- 先加root, 然后push上需要末尾process的在stack垫底(root.right), 然后push root.left
- Stack: push curr, push right, push left.   

```
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

*/

/*
Thoughts:
DFS, add root, then left, then right
*/
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) {
            return rst;
        }
        rst.add(root.val);
        rst.addAll(preorderTraversal(root.left));
        rst.addAll(preorderTraversal(root.right));
        return rst;
    }
}

/*
Thoughts:
BFS. Want the sequence in root, left, and right.
Queue? NO. After root.left is process, it should go to root.left.left. rather than root.right.

We need to proces root, then put root.right at bottom, stack root.left on top, then work on root.left's children first.
*/
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) {
            return rst;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            rst.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        
        return rst;
    }
}

/*
    Previous notes
    Recap: 12.08.2015
    Draw a few nodes and will realize to use stack
        Cannot use queue, because whatever added on it first, will first process. 
        That means if we add curr,left,right; they will be processed first... but we want to traverse all left nodes first.
    IN FACT: binary tree traversal are all using stack...
*/

//Iterative
public class Solution {
   
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if (root == null) {
            return rst;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                rst.add(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return rst;
    }
}

//recursive
public class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if (root == null) {
            return rst;
        }   
        helper(rst, root);
        return rst;
    }

    public void helper(ArrayList<Integer>rst, TreeNode node){
        if (node != null) {
            rst.add(node.val);
            helper(rst, node.left);
            helper(rst, node.right);
        }
    }
}

```