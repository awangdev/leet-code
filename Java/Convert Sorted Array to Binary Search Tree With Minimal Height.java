/*
Given a sorted (increasing order) array, Convert it to create a binary tree with minimal height.

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4,5,6,7], return

     4
   /   \
  2     6
 / \    / \
1   3  5   7
Note
There may exist multiple valid solutions, return any of them.

Tags Expand 
Cracking The Coding Interview Recursion Binary Tree

Thoughts:
1. Find middle point x.
2. All index before x, goes to left of the tree. Same apply to right tree
	build sub array and pass alone: we can pass index start, end.
	use parent node and pass along
3. Recur on left side array.

*/


/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param A: an integer array
     * @return: a tree node
     */
    public TreeNode sortedArrayToBST(int[] A) {  
        TreeNode root = null;
        if (A == null || A.length == 0) {
        	return root;
        }
        root = helper(0, A.length - 1, A);
        return root;
    }  

    public TreeNode helper(int start, int end, int[] A) {
    	if (start > end) {
    		return null;
    	}
    	//add middle node
    	int mid = start + (end - start)/2;
    	TreeNode node = new TreeNode(A[mid]);
    	//Split and append child
    	node.left = helper(start, mid - 1, A);
    	node.right = helper(mid + 1, end, A);
    	return node;
    }
}
