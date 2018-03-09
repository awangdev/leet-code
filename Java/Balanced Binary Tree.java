M
1519713672
tags: Tree, DFS

1. DFS using depth marker: 每个depth都存一下。然后如果有不符合条件的，存为-1.
   一旦有 <0 或者差值大于1， 就全部返回Integer.MIN_VALUE. Integer.MIN_VALUE比较极端, 确保结果的正确性。
   最后比较返回结果是不是<0. 是<0，那就false.
   Traverse 整个tree, O(n)

2. Only calculate depth using maxDepth function. Same concept as in 1, but cost more traversal efforts.

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
        if (root == null) {
            return true;
        }
        return markDepth(root) > 0;
    }
    
    private int markDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = markDepth(node.left);
        int rightDepth = markDepth(node.right);
        if (leftDepth < 0 || rightDepth < 0 || (Math.abs(leftDepth - rightDepth)) > 1) {
            return Integer.MIN_VALUE;
        }
        return Math.max(leftDepth, rightDepth) + 1;
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
/*  3.3.2016 recap:
    Recursive 1:
    Use helper to calculate depth, and also check if left/right depth differ by 1. If all good, return actual depth
*/
 
 public class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root) > 0;
    }
    
    public int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = helper(node.left);
        int rightDepth = helper(node.right);
        
        if (leftDepth < 0 || rightDepth < 0 || Math.abs(leftDepth - rightDepth) > 1) {
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




/*
    Failed Solution:
        check cases:
        root == null, return true;
        left = root.left; right = root.right;
        left == null && right == null : true;
        left == null && right != null && (right.left != null || right.right != null) {
            false;
        }
        return isBalance(left) && isBalance(right).

    failed case:[1,2,2,3,3,null,null,4,4]
            1
        2       2
     3     3     
    4 4
Previous notes:
2. 从基本的题目理解考虑，想到leaf node的情况。如果判断了leaf node, 那其他node应该就是可以recursive。
   直接在isBalanced上面recursive.
   关键return false的判断情况：如果有个node是null, 那么同一行相邻的那个，一旦有了children,那么就说明两个分支的depth已经是>=2了，那么就return false.
   
   然后这个可能是个小小的优化，因为不需要计算所有的depth.一旦发现一个false,其他的就不需要计算，直接返回了。



*/


```