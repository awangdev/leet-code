M

1. DFS using depth marker: 每个depth都存一下。然后如果有不符合条件的，存为-1.
   一旦有-1， 就全部返回。
   最后比较返回结果是不是-1. 是-1，那就false.
   Traverse 整个tree, O(n)

2. 从基本的题目理解考虑，想到leaf node的情况。如果判断了leaf node, 那其他node应该就是可以recursive。
   直接在isBalanced上面recursive.
   关键return false的判断情况：如果有个node是null, 那么同一行相邻的那个，一旦有了children,那么就说明两个分支的depth已经是>=2了，那么就return false.
   
   然后这个可能是个小小的优化，因为不需要计算所有的depth.一旦发现一个false,其他的就不需要计算，直接返回了。


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


/*
    12.11.2015
    Recap: 
    The original way of marking depth and -1 is good. However, that has to traverse entire tree.

    Today, let's think about the leaf case, and see if we can directly recurse on isBalanced itself.
    leaf case:
    root == null, return true;
    left = root.left; right = root.right;
    left == null && right == null : true;

    left == null && right != null && (right.left != null || right.right != null) {
        false;
    }
    
    need to isBalance(left) && isBalance(right).
*/

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        //One of left or right is null.
        if (left == null && (right.left != null || right.right != null)) {
            return false;
        } 
        if (right == null && (left.left != null || left.right != null)) {
            return false;
        }
        //none of left or right is null
        return isBalanced(left) && isBalanced(right);
    }
}


/*

Thinking process:
making use depth first search.
same process as maxDepth() method.
after recursive call, check if Math.abs(left - right) > 1. If so, return -1.
If any case return -1, they all return -1.
at the top return, check if -1.

*/
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }
    
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);     
        
        if (Math.abs(left - right) > 1 || left == -1 || right == -1) {
            return -1;
        }
        
        return Math.max(left, right) + 1;
    }
}



```