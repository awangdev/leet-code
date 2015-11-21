Divide, && all.
注意 null states
```
/*
Check if two binary trees are identical. Identical means the two binary trees have the same structure and every identical position has the same value.

Example
    1             1
   / \           / \
  2   2   and   2   2
 /             /
4             4
are identical.

    1             1
   / \           / \
  2   3   and   2   3
 /               \
4                 4
are not identical.
*/

/*
	Thoughts:
	Do a divide and conquer traversal on left and right.
	&& all results.
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
     * @param a, b, the root of binary trees.
     * @return true if they are identical, or false.
     */
    public boolean isIdentical(TreeNode a, TreeNode b) {
    	if (a == null && b == null) {
    		return true;
    	} else if ( a == null || b == null) {
    		return false;
    	} else {
    		return a.val == b.val && isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
    	}
    }
}

```