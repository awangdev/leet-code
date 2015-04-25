/*
33% Accepted
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.

The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.

Example
        4

    /     \

  3         7

          /     \

        5         6

For 3 and 5, the LCA is 4.

For 5 and 6, the LCA is 7.

For 6 and 7, the LCA is 7.

Tags Expand 
Binary Tree LintCode Copyright


Think process:
Divide and conquer:
start from root, divide into 2 ways …
At the bottom, if it’s node1 or node2, send back.
If any line that’s not having node1 or node2 as leaf, they will become null.
At the ancestor spot: because node1!=null and node2!=null, the ancestor return itself. 
From this point, any level higher, it will return the ancestor because the other child-line has been null at the time.
When it returns to the top, return solution : ancestor
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
     * @param root: The root of the binary search tree.
     * @param A and B: two nodes in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null || root == A || root == B) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);
        
        if (left == null && right == null) {
            return null;
        } else if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }
}
/*
//Another way using regular way: see solution: 
http://www.ninechapter.com/solutions/lowest-common-ancestor/
*/
