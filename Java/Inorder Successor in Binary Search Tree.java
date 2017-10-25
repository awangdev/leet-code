M

画inorder图，发现规律.每个node的后继node(successor)有几种情况:   
1. node.right 是个leaf到底了。那么就return.   
2. set rightNode = node.right， 但发现rightNode has a lot left children to leaf.   
3. 比如, node.right == null， 也就是node自己是leaf，要回头看山顶找Inorder traversal规则里的下一个。   
	发现：其实就是每层都把路过的curr node放在stack里，最上面的，就是当下改return的那个successor:) Done.

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
/*
	Analysis the structure. 
	1. Note1: When directing left, save curr in stack. Curr node will be re-visit with rules of in-order traversal. Write an example, will help refresh memory.
			In the future, if node == target && node.right == null, then stack.pop() is the successor.

	2. Note2: When p is found, and node.right != null. Be careful where: if node.right just ends there, surely return it, that's it; 
			However, if rightNode has left child, well, have to trace all the way down to left---end of the leaf

	Stack uses O(h) space
*/
 //
 //
public class Solution {
    Stack<TreeNode> stack = new Stack<TreeNode>();
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        
        if (root == p) {
            if (root.right != null) {
            	//Hunt down rightNode's left leaf
                TreeNode rightNode = root.right;
                while (rightNode.left != null) {
                    rightNode = rightNode.left;
                }
                return rightNode;
            } else if (!stack.isEmpty()) {
            	//Inorder pattern rules:
                return stack.pop();
            } else {
                return null;
            }
        }
        
        if (p.val < root.val && root.left != null) {
            stack.push(root);
            return inorderSuccessor(root.left, p);
        } else if (p.val > root.val && root.right != null) {
            return inorderSuccessor(root.right, p);
        }
        
        return null;
    }
}

```
