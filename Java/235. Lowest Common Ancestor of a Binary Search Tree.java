E
tags: Tree, DFS, BST
time: O(logn)
space: O(logn)

给 binary search tree root, q node, p node. 找到p q 的lowest common ancestor

#### Find path with BST
- 利用 BST 的性质，可以直接搜到target node，而做成两个长度不一定相等的 path
- 然后很简单找到LCA 
- O(n) space, O(logn) time

#### DFS
- Beofre lasts common ancestor is found: p and q should follow same search pattern:
    - compare with root, then dfs(left) or dfs(right)
    - util p and q fall on two sides of root, then return root
    - 非常巧妙, 但是也比较局限; 稍微变条件, 就很难recursive.
- 几种情况:
    - 1. one of p, q 在leaf, 那么此时的root其实就是lowest common ancestor
    - 2. 如果p, q 在root的左右两边, 这就是分叉口, 那么root就是lowest common ancestor
    - 3. 如果p, q 在root的同一边 (左,右), 那么继续dfs
- O(logn) extra space: recursive stack space
- O(logn) time

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

Example 1:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
Example 2:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 

Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the BST.


Tags: Tree
Similar Problems: (M) Lowest Common Ancestor of a Binary Tree

*/

/*
Thoughts:
Based on the value of p and q, use BST to find the node, and store the visited nodes in two separate lists.
Find last common item in the list to return.
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return root;
        }
        List<TreeNode> listP = new ArrayList<>();
        List<TreeNode> listQ = new ArrayList<>();
        findNode(root, p, listP);
        findNode(root, q, listQ);
        int size = Math.min(listP.size(), listQ.size());
        TreeNode parent = root;
        for (int i = 0; i < size; i++) {
            if (listP.get(i).val == listQ.get(i).val) {
                parent = listP.get(i);
            } else {
                return parent;
            }
        }
        return parent;
    }
    
    private void findNode(TreeNode node, TreeNode target, List<TreeNode> list) {
        while (node != null) {
            list.add(node);
            if (node.val == target.val) return;
            if (node.val > target.val) node = node.left;
            else node = node.right;
        }
    }
}


/*
Thoughts:
Besides the method of finding all ancestors, we can look at the problem in a greedy way.
Move both p and q to find the ancestor:
- If the root is on the left of both p and q, that means the ancestor must be on right side of root
- same applies to the other direction.

This leads to a compact recursive solution.

However, the iterative way might be more useful in real development
where it utilize data struture
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return root;
        }
        if (root.val < p.val && root.val < q.val) { // move right
            return lowestCommonAncestor(root.right, p, q);
        } else if (root.val > p.val && root.val > q.val) { // move left
            return lowestCommonAncestor(root.left, p, q);
        }
        // root is between p and q.
        return root;
    }
}

```