/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

Tags: Tree Binary Search
Similar Problems: (M) Count Complete Tree Nodes, (H) Closest Binary Search Tree Value II

*/


/*
Thoughts:
Binary search, maintain a closest value.
Note: initial closest in real case is just the root, since we start from the root
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
    public int closestValue(TreeNode root, double target) {
 		if (root == null) {
 			return 0;
 		}       
 		double closest = root.val;
 		while (root != null) {
 			if (root.val == target) {
 				return root.val;
 			} else {
 				if (Math.abs(target - closest) >= Math.abs(target - root.val)) {
 					closest = root.val;
 				}
 				if (root.val > target) {
 					root = root.left;
 				} else {
 					root = root.right;
 				}
 			}
 		}//END while
 		return (int)closest;
    }
}