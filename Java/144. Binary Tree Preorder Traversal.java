M
tags: Stack, Tree, DFS, BFS
time: O(n)
space: O(n)

#### Recursive, DFS, Divide and conquer
- 加root, left, then right. Obvious
- Option1: recursive on preorderTraversal. the dfs function returns List
- Option2: pass in rst, and write a void dfs.

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
DFS Option1: add root, then left, then right
*/
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;
        rst.add(root.val);
        rst.addAll(preorderTraversal(root.left));
        rst.addAll(preorderTraversal(root.right));
        return rst;
    }
}

// DFS Option2: add root, then left, then right
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;
        dfs(rst, root);
        return rst;
    }

    public void dfs(List<Integer>rst, TreeNode node){
        if (node != null) {
            rst.add(node.val);
            dfs(rst, node.left);
            dfs(rst, node.right);
        }
    }
}

/*
BFS
Want the sequence in root, left, and right.
Queue? NO. After root.left is process, it should go to root.left.left. rather than root.right.

We need to proces root, then put root.right at bottom, stack root.left on top, then work on root.left's children first.
*/
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> rst = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            rst.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return rst;
    }
}



```