M

利用 BST的性质，可以直接搜到target node，而做成两个长度不一定相等的list。然后很简单找到LCA 


```
/*
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined 
between two nodes v and w as the lowest node in T that has both v and w as descendants 
(where we allow a node to be a descendant of itself).”

        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. 
Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

Tags: Tree
Similar Problems: (M) Lowest Common Ancestor of a Binary Tree

*/
/*
Thoughts:
Create 2 path: l1, l2.
First different node's parent, will be the LCA
Do binary search to generate the path l1,l2

Note:
When one of the target is root, make sure parent = root, and return root at the end. This is because: the if statement (l1.get(i).val != l2.get(i).val) won't capture this case; instead, the for loop ends by i == size. So, be careful here.
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
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        ArrayList<TreeNode> l1 = new ArrayList<TreeNode>();
        ArrayList<TreeNode> l2 = new ArrayList<TreeNode>();
        binarySearch(root, p, l1);
        binarySearch(root, q, l2);

        TreeNode parent = root;
        int size = l1.size() > l2.size() ? l2.size() : l1.size();
        for (int i = 0; i < size; i++) {
            if (l1.get(i).val == l2.get(i).val) {
                parent = l1.get(i);
            } else {
                return parent;
            }
        }
        return parent;
    }


    public void binarySearch(TreeNode root, TreeNode target, ArrayList<TreeNode> list) {
        TreeNode node = root;
        while (node != null) {
            list.add(node);
            if (node.val == target.val) {
                return;
            } else if (node.val < target.val) {
                node = node.right;
            } else {
                node = node.left;
            }
        }//END while
    }
}
```