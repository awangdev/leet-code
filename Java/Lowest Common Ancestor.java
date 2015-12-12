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


*/

/*
  Thoughts:
  Revisit this on 12.11.2015.
  To correctly understand this approach when there is not 'parent' atribute available in node.

  We divide and coquer (in this case DFS) into 2 branches, and we are actually asking each node to check:
    Do I have a leaf child of nodeA (could be futher down in the tree)?
    Do I have a leaf child of nodeB (could be futher down in the tree)?
  1. If I have leaf child of A && B, then i'm the deepest parent! Return.
  2. If I only have A, or B: mark myself as an ancestor of A or B.
  3. If I don't have leaf child of A nor B, I'm not an ancestor, failed, return null.

  After the common ancestor is found at any deep level, and returned itself to parent level,
    we can assume other branches must be null (because they are not ancestor, since we are),
    then the this common ancestor node will be passed to highest level.

  However, with one problem:
  When review the problem, calling the recursive functions of the 'lowestCommonAncestor' is just 
  confusing. It's not easy to see the relationship between leef child and ancestor candidates.
  
 
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

      if (left != null && right != null) {//Found both A leaf and B leaf 
        return root;
      } else if (left != null || right != null) {
        return left != null ? left : right;
      } else {
        return null;
      }
    }
}

/*
//Another way using regular way: see solution: 
This is for the case that each node can reach its higher level parent....
Basically put all parents into a list, 
[root, next level, next level... parent, nodeA]
[root, next level, next level... parent, nodeB]
compare these 2 lists see when to have a different node. that node.parent is the parent

Or, if the list is identical, then their parent might just be same right-above-level parent.

http://www.ninechapter.com/solutions/lowest-common-ancestor/
*/


/*
Older same version
Think process:
Divide and conquer:
start from root, divide into 2 ways …
At the bottom, if it’s node1 or node2, send back.
1. If any line that’s not having node1 or node2 as leaf, they will become null.
2. At the ancestor spot: because node1!=null and node2!=null, the ancestor return itself. 
3. From this point, any level higher, it will return the ancestor because the other child-line has been null at the time.
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
