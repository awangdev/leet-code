M
1521692416
tags: Stack, Two Stacks, Tree

如题, POST-ORDER traversal.

LeetCode给了hard, 应该是觉得stack的做法比较难想到.

#### Recursive
trivial, 先加left recursively, 再加right recursively, 然后组成头部.

#### Stack
- 双stack的思想, 需要在图纸上画一画
- 原本需要的顺序是: 先leftChild, rightChild, currNode.
- 营造一个stack, reversely process: 先currNode, 再rightChild, 再leftChild
- 这样出来的结果是reverse的, 那么翻转一下就可以了.
- v1做的时候用了stack1, stack2, 因为根据这个双stack的思想而来
- v2简化, 可以放在一个stack里面, 每次record result 的时候: rst.add(0, item);

##### 利用stack的特点
- 每次加element进stack的时候, 想要在 bottom/后process的, 先加
- 想要下一轮立刻process的, 最后push进stack.

##### 注意
这些binary tree traversal的题目.常常有多个做法:recursive or iterative

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

// Recursive: always add left, add right, then add middle
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) {
            return rst;
        }
        rst.addAll(postorderTraversal(root.left));
        rst.addAll(postorderTraversal(root.right));
        rst.add(root.val);

        return rst;
    }
}

/*
Iteratively: stack
V1
it's cleaner to draw two stacks and simulate the add proces on paper
- stack2 is a placeholder with reversed post-order. It'll be used to generate final results.
- Item in stack1 will be added with reversed order: left, right
- Always save the parent node to stack2: it enters stack2 first than its 2 children, so it'll be at bottom
- Next round: since right child is on top, it'll be processed first and added to stack2
*/
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) {
            return rst;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        
        while (!stack2.isEmpty()) {
            rst.add(stack2.pop().val);
        }

        return rst;
    }
}

// Simpler version, add to begining of list.
// V1
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) {
            return rst;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            rst.add(0, node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
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


```