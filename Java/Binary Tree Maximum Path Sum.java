/*
23% Accepted
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

Example
Given the below binary tree,

       1
      / \
     2   3
Return 6.

Tags Expand 
Dynamic Programming Tree Depth First Search

Thinking process
Two steps of picking nodes: 1. Single Path (left or right)has a maximum.   2. Combine them into a final result: combinedPathMax
1. singlePathMax, 2 results: either pick left+root or right+root.
2. combinedPathMax: take left-max as a whole, take right-max as a whole. 
	3 possible results: left-max without parent, right-max without parent, left-max + right-max + parent.
3. Use a special container to store current node's singlePathMax and combinedPathMax. 
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
    private class PathSumType {
        int singlePathMax;
        int combinedPathMax;
        PathSumType(int singlePathMax, int combinedPathMax) {
            this.singlePathMax = singlePathMax;
            this.combinedPathMax = combinedPathMax;
        }
    }
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxPathSum(TreeNode root) {
        PathSumType result = helper(root);
        return result.combinedPathMax;
    }
    
    public PathSumType helper(TreeNode root) {
        if (root == null) {
            return new PathSumType(0, Integer.MIN_VALUE);
        }
        //Divide
        PathSumType left = helper(root.left);
        PathSumType right = helper(root.right);
        //Conquer
        //Step 1: prepare single path max for parent-level comparison.
        int singlePathMax = Math.max(left.singlePathMax, right.singlePathMax) + root.val;
        singlePathMax = Math.max(singlePathMax, 0);//If less than 0, no need to keep, because it only decrease parent-level max.
        
        int combinedPathMax = Math.max(left.combinedPathMax, right.combinedPathMax);
        combinedPathMax = Math.max(combinedPathMax, left.singlePathMax + right.singlePathMax + root.val);
        
        return new PathSumType(singlePathMax, combinedPathMax);
    }
    
}

