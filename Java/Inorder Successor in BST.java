M
1533276962
tags: BST, Tree

找 Inorder traversal规则里的下一个.

主要想法是考虑: 
    1. 如果 node.right == null, 找上一个unprocessed node alone the inorder traversal path
    2. 如果 node.right != null, successor 一定在这个node.right那个subtree里面
最后竟然可以简化成几行, 非常全面的BST问题: 有search, 有对inorder traversal的理解, 还有坑.

#### Short Recursive and Iterative without Stack
- Previous solution, we use stack to hold previous cached/unprocessed items: but do we need use catch to hold them?
- If moving left: `p.val < root.val`, then root (parent of left child) is a successor candidate, so save `rst = root`.
- If moving right or equal: `p.val >= root.val`, the successor has nothing to do with curr node, so just directly dive into root.right.
- Both iterative and recursive solution can be simplified as such.


#### Previous Iterative + stack
- Iteratively search
- Still need stack to store previously unprocessed items along the path

#### Previous Recursive + Stack
- 画inorder图，发现规律.每个node的后继node(successor)有几种情况:   
- 1. node.right 是个leaf到底了。那么就return.   
- 2. set rightNode = node.right， 但发现rightNode has a lot left children to leaf.   
- 3. 比如, node.right == null， 也就是node自己是leaf，要回头看山顶找Inorder traversal规则里的下一个。   
- 发现：其实就是每层都把路过的curr node放在stack里，最上面的，就是当下改return的那个successor:) Done.

```
/*
Given a binary search tree (See Definition) and a node in it, find the in-order successor of that node in the BST.

Have you met this question in a real interview? Yes
Example
Given tree = [2,1] and node = 1:

  2
 /
1
return node 2.

Given tree = [2,1,3] and node = 2:

  2
 / \
1   3
return node 3.

Note
If the given node has no in-order successor in the tree, return null.

Challenge
O(h), where h is the height of the BST.

Tags Expand 
Binary Search Tree Binary Tree
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Short Recursive version:
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return root;

        if (p.val < root.val) {
            TreeNode left = inorderSuccessor(root.left, p);
            return left != null ? left : root;
        } else { // p.val >= root.val
            return inorderSuccessor(root.right, p);
        }
    }
}

// Short Iterative version:
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode rst = null;
        while (root != null) {
            if (p.val < root.val) {
                rst = root; // cache curr node as parent of left child
                root = root.left;
            } else { // p.val >= root.val
                root = root.right;
            }
        }
        return rst;
    }
}


// Prev iterative: code too long/complicated with stack
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            if (p == root) {
                break;
            } else if (p.val < root.val) {
                if (root.left == null) return null;
                stack.push(root);
                root = root.left;
            } else { //p.val > root.val
                if (root.right == null) return null;
                root = root.right;
            }
        }
        
        if (root.right != null) { //in-order next node: Hunt down rightNode's left leaf
            TreeNode rst = root.right;
            while (rst.left != null) {
                rst = rst.left;
            }
            return rst;
        } else if (!stack.isEmpty()) {
            return stack.pop(); //Inorder pattern rules:
        }
        return null;
    }
}

/*
	Analysis the structure. 
	1. Note1: When directing left, save curr in stack. Curr node will be re-visit with rules of in-order traversal. Write an example, will help refresh memory.
			In the future, if node == target && node.right == null, then stack.pop() is the successor.

	2. Note2: When p is found, and node.right != null. Be careful where: if node.right just ends there, surely return it, that's it; 
			However, if rightNode has left child, well, have to trace all the way down to left---end of the leaf

	Stack uses O(h) space
*/
// Prev recursive: code too long/complicated with stack.
public class Solution {
    Stack<TreeNode> stack = new Stack<>();
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        
        if (root == p) {
            if (root.right != null) { //in-order next node: Hunt down rightNode's left leaf
                TreeNode rst = root.right;
                while (rst.left != null) {
                    rst = rst.left;
                }
                return rst;
            } else if (!stack.isEmpty()) {
                return stack.pop(); //Inorder pattern rules:
            }
            return null;
        }
        
        if (p.val < root.val && root.left != null) { // search left, with root added at bottom of stack
            stack.push(root);
            return inorderSuccessor(root.left, p);
        } else if (p.val > root.val && root.right != null) { // search right
            return inorderSuccessor(root.right, p);
        }
        
        return null;
    }
}

```
