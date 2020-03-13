M
tags: Stack, Two Stacks, Tree
time: O(n)
space: O(n)

如题, POST-ORDER traversal.

LeetCode给了hard, 应该是觉得stack的做法比较难想到.

#### Method1: DFS, Recursive
- trivial, 先加left recursively, 再加right recursively, 然后组成头部.
- Option1 w/o helper; option2 with dfs helper.

#### Method2, Iterative, Stack
- Option1: reversely add to list
- 双stack的思想, 需要在图纸上画一画
    - 原本需要的顺序是: 先leftChild, rightChild, currNode.
    - 营造一个stack, reversely process: 先currNode, 再rightChild, 再leftChild
    - 这样出来的结果是reverse的, 那么翻转一下就可以了.
- reverse add: `list.add(0, x)`;
- 利用stack的特点
    - 每次加element进stack的时候, 想要在 bottom/后process的, 先加
    - 想要下一轮立刻process的, 最后push进stack.
- Option2: regular sequence add to stack: add curr, right, left
    - Use set to contain the processed children 
    - only process curr if its children is processed


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

// Method1, Option1: dfs w/o helper. always add left, add right, then add middle
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;
        rst.addAll(postorderTraversal(root.left));
        rst.addAll(postorderTraversal(root.right));
        rst.add(root.val);

        return rst;
    }
}

// Method1, Option2: recursive with dfs helper
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        dfs(rst, root);
        return rst;
    }

    public void dfs(List<Integer>rst, TreeNode node) {
        if (node == null) return;
        dfs(rst, node.left);
        dfs(rst, node.right);
        rst.add(node.val);
    }
}

// Simpler version, add to begining of list.
// V2
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            rst.add(0, node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return rst;
    }
}

// Method2, Iterative, Option2: regular sequence add to stack: add curr, right, left
// only process curr if its children is processed
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> set = new HashSet<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (validate(set, node)) {
                stack.pop();
                rst.add(node.val);
                set.add(node);
                continue;
            }
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return rst;
    }
    
    private boolean validate(Set<TreeNode> set, TreeNode node) {
        if(node.left == null && node.right == null) return true;
        boolean left = set.contains(node.left), right = set.contains(node.right);
        if (left && right) return true;
        if ((node.left == null && right) || (node.right == null && left)) return true;
        return false;
    }
}
```