找到的都存hashset. exist就return那个duplicate
```
/*
Lowest Common Ancestor II

Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.

The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.

The node has an extra attribute parent which point to the father of itself. The root's parent is null.

Example
For the following binary tree:

  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4

LCA(5, 6) = 7

LCA(6, 7) = 7

Tags Expand 
LintCode Copyright Binary Tree
*/

/*
	Thoughts:
	Try to get upper-level parent, store in hashMap.
	First time when the node duplicate in map, that will be the first common parent.
*/

/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */
public class Solution {
    /**
     * @param root: The root of the tree
     * @param A, B: Two node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
                                                 ParentTreeNode A,
                                                 ParentTreeNode B) {
    	if (root == null || (A == null && B == null)) {
    		return null;
    	} else if (A == null || B == null) {
    		return A == null ? B : A;
    	}

    	HashSet<ParentTreeNode> set = new HashSet<ParentTreeNode>();
    	while (A != null || B != null) {
    		if (A != null) {
    			if (set.contains(A)) {
    				return A;
    			}
    			set.add(A);
    			A = A.parent;
    		}
    		if (B != null) {
    			if (set.contains(B)) {
    				return B;
    			}
    			set.add(B);
    			B = B.parent;
    		}
    	}
 		return root;
    }
}

```