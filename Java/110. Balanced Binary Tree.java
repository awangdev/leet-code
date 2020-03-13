E
tags: Tree, DFS

给一个binary tree, 看是否是height-balanced

#### DFS with end state
- DFS using depth marker: 每个depth都存一下。然后如果有不符合条件的，存为-1.
- 一旦有 <0 或者差值大于1， 就全部返回Integer.MIN_VALUE. Integer.MIN_VALUE比较极端, 确保结果的正确性。
- 最后比较返回结果是不是<0. 是<0，那就false.
- Traverse 整个tree, O(n)


#### DFS, maxDepth function
- Same concept as above solution, but cost more traversal efforts
- 试图计算所有情况

```
/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree,  
in which the depth of the two subtrees of every node never differ by more than 1.

Example
Given binary tree A={3,9,20,#,#,15,7}, B={3,#,20,15,7}

A)  3            B)    3 
   / \                  \
  9  20                 20
    /  \                / \
   15   7              15  7
The binary tree A is a height-balanced binary tree, but B is not.

Tags Expand 
Binary Search Divide and Conquer Recursion

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
DFS: find each subtree's depth, and compare the two.
However, making DFS for every node is very costly: the recursive calculations of depth are done repeatedly, so we want to at least tell, if a path has failed, no need to dive deep -> need a boolean-ish signature. 
However, we can't return both boolean && depth (we actually don't need other depth valuese greater than 1).
Combine the boolean && depth signature to mark the failed case: by using a negative number.
*/
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return calDepth(root) > 0;
    }
    
    private int calDepth(TreeNode node) {
        if (node == null) return 0;
        int leftDepth = calDepth(node.left);
        int rightDepth = calDepth(node.right);
        if (leftDepth < 0 || rightDepth < 0 || (Math.abs(leftDepth - rightDepth)) > 1) {
            return Integer.MIN_VALUE;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }
}

/*
    Recursive 2:
    Calculate a node's maxDepth. Compare a parent node's sub tree for maxDepth
*/
public class Solution {
   public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }
}




```