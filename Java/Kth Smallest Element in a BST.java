M
tags: BST

很容想到Inorder-binary-search-tree Traversal
Recursive 不难，然后稍微优化一下，确保rst.size() == k 时候，就可以return了。
Iterative 稍微难想点：先把最左边的add， pop() stack， 加上右边（如果存在）； 下一个轮回，如果又左孩子，又是一顿加。

```
/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).
Credits:
Special thanks to @ts for adding this problem and creating all test cases.

Hide Company Tags Google
Hide Tags Tree Binary Search
Hide Similar Problems (M) Binary Tree Inorder Traversal

*/

/*

	Based on binary seach tree, just do a in-order-traversal.
	Store in rst.
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
	//Iterative

	Add all left.
	pop top (which will be left-most node)
	set node = node.right;
		if right != null, add to stack.    Will trigger the left-adding-while-loop
		if right == null, now node = null. Will not trigger the left-adding-whilte-loop
*/
public class Solution {
	public int kthSmallest(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return -1;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode node = root;
        while(!stack.isEmpty()) {
            //Left first
            while (node != null && node.left != null) { 
                stack.add(node.left);
                node = node.left;
            }
            //Process left/curr
            node = stack.pop();
            k--;
            if (k == 0) {
                return node.val;
            }
            node = node.right;
            if (node != null) {
                stack.push(node);
            }
         }

         return -1;
    }

}



// Recursive
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return -1;
        }
        
        ArrayList<TreeNode> rst = new ArrayList<TreeNode>();
        helper(rst, root, k);
        
        if (rst.size() < k) {
            return -1;
        }
        return rst.get(k - 1).val;
    }
    
    
    public void helper(ArrayList<TreeNode> rst, TreeNode node, int k) {
        if (rst.size() == k) {
            return;
        }
        if (node.left == null && node.right == null) {
            rst.add(node);
            return;
        }
        
        if (node.left != null) {
            helper(rst, node.left, k);
        }
        rst.add(node);
        if (node.right != null) {
            helper(rst, node.right, k);
        }
    }
}




```