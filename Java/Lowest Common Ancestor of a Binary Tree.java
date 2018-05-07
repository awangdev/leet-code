M
1525707446
tags: Tree, DFS

给一个Binary Tree root, 以及两个node p, q. 找 p 和 q 的 lowest common ancestor

#### DFS
- 因为是 binary tree, 所以直接盲目搜索搜索path不efficient. 巧用DFS来找每一个node的common ancestor
- 当root == null或者 p q 任何一个在findLCA底部被找到了(root== A || root == B)，那么就return 这个root.     
- 三种情况:
- 1. A,B都找到，那么这个level的node就是其中一层的ancestor: 其实，最先recursively return到的那个，就是最底的LCA parent.   
- 2. A 或者 B 找到，那就还没有公共parent, return 非null得那个。   
- 3. A B 都null, 那就找错了没有呗, return null
- Worst case, visit all nodes to find p q at last level, last two leaves: time/space O(n)

```
/**
LeetCode: Lowest Common Ancestor of a Binary Tree

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: 
“The lowest common ancestor is defined between two nodes v and w 
as the lowest node in T that has both v and w as descendants 
(where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. 

Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself 
according to the LCA definition.

*/

/*

Given the root and two nodes in a Binary Tree. 
Find the lowest common ancestor(LCA) of the two nodes.

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
Use the 'lowestCommonAncestor' to track the ancestor for at least 1 node.
At any node, if it's a ancestor, the ancestor of left node && ancestor of right node must both exist.
Only return root when p or q is matched; other cases, return null
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || q == root || p == root) {
            return root;
        }
        TreeNode leftChildAncestor = lowestCommonAncestor(root.left, p, q);
        TreeNode rightChildAncestor = lowestCommonAncestor(root.right, p, q);
        
        if (leftChildAncestor != null && rightChildAncestor != null) {
            return root;
        } else if (leftChildAncestor != null || rightChildAncestor != null) {
            return leftChildAncestor != null ? leftChildAncestor : rightChildAncestor;
        }
        return null;
    }
}

/*
  Thoughts:
  Revisit this on 12.11.2015.
  To correctly understand this approach when there is not 'parent' atribute available in node.

  We divide and coquer (in this case DFS) into 2 branches, and we are actually asking each node to check:
    Do I have a leaf child of nodeA (could be futher down in the tree)?
    Do I have a leaf child of nodeB (could be futher down in the tree)?
  1. If I have leaf child of A && B, then i'm the deepest parent in line! Return.
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
```
