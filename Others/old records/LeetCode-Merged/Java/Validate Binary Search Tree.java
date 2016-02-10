注意带有upper/lower bound.
定义：binary search tree, 左边的所有node都比root和右边的小；右边的所有node都比root和左边的大。
最短的这个解法，判断对的case，其余false.

```
/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:
    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

Tags: Tree Depth-first Search
Similar Problems: (M) Binary Tree Inorder Traversal

*/

/*Current solution: Do the same as before, however, make sure to add MIN and MAX in the recursive funtion

Note: becareful with Integer MAX and MIN. Use Long MAX and MIN
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
public class Solution {
    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean check(TreeNode node, long MIN, long MAX) {
        if (node == null) {
            return true;
        }
        if (node.val > MIN && node.val < MAX &&
            check(node.left, MIN, node.val) &&
            check(node.right, node.val, MAX)) {
            return true;
        } else {
            return false;
        }
    }
}



//Another long solution, check false case and return the true case.
//NOTE: be careful with the false case on '=='
public class Solution {
    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean check(TreeNode node, long MIN, long MAX) {
        if (node == null) {
            return true;
        }
        if (node.val >= MAX || node.val <= MIN) {
            return false;
        }
        return check(node.left, MIN, node.val) && check(node.right, node.val, MAX);
    }
}

```