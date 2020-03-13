E
tags: Hash Table, Stack, Tree
time: O(n)
space: O(logn)

Inorder traverse Binary Tree

#### Method1: DFS
- option1: dfs + rst list to carry results
- option2: Divide and Conquer, 在自己的基础上recursive, 不用helper function
- O(n) time

#### Method2: Iterative, Stack inorder traversal
- 1) Add root.leftPath all the way to leaf, 2) process curr 3) Move to right if applicable 4) add all right.leftPath
- O(n) time, O(h) space


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
// Method1 option1: DFS with helper
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<Integer>();
        dfs(rst, root);
        return rst;
    }
    
    public void dfs(List<Integer> rst, TreeNode node) {
        if (node == null) return;
        dfs(rst, node.left);
        rst.add(node.val);
        dfs(rst, node.right);
    }
}

/*
// Method1 Option2: DFS w/o helper
Recursive, append left, itself, then right
*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;
        List<Integer> left = inorderTraversal(root.left);
        List<Integer> right = inorderTraversal(root.right);
        
        rst.addAll(left);
        rst.add(root.val);
        rst.addAll(right);
        return rst;
    }
}


/*
Method2: Iterative, Stack, always treat left-most-leaf with priority
- add node.left till end.
- consume stack.pop()
- if has right, add node.right and push all node.right's left children to stack
*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        addLeftPath(stack, node); // Dive deep to left path till leaf
        
        while (!stack.isEmpty()) {
            node = stack.pop(); // Add to rst
            rst.add(node.val);
            // Add node.right and all its left children
            if (node.right != null) {
                node = node.right;
                addLeftPath(stack, node);
            }
        }
        return rst;
    }
    
    private void addLeftPath(Stack<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}


```