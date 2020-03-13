M
tags: DFS, Tree, BFS
time: O(n)
space: O(n)

#### Method1: DFS, top-down
- key concetpL the `common ancester of deppest leaves` must have its `two branch being same depth`. problem sovled.
- dfs on both branch
- if returned depth equals & equal to max depth, record common ancestor
- time: O(n) traversal 1 pass
- space: O(n) dfs worst case depth

#### Method2: bottom-up, find leaves first and traverse backwards
- 1) find leaf nodes, and store backward map to root (DFS/ BFS both work)
- 2) use leaf nodes to find way backwards till common node is found; return
- time: O(n) but two passes
- space: O(n) dsf + map storage
- this approach is more brutle and uses exrtra spaces

```

/*
Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

The node of a binary tree is a leaf if and only if it has no children
The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.
 

Example 1:

Input: root = [1,2,3]
Output: [1,2,3]
Explanation: 
The deepest leaves are the nodes with values 2 and 3.
The lowest common ancestor of these leaves is the node with value 1.
The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
Example 2:

Input: root = [1,2,3,4]
Output: [4]
Example 3:

Input: root = [1,2,3,4,5]
Output: [2,4,5]
 

Constraints:

The given tree will have between 1 and 1000 nodes.
Each node of the tree will have a distinct value between 1 and 1000.
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
Method1: the common ancester of deppest leaves must have its two branch being same depth. problem sovled.
- dfs on both branch
- if returned depth equals & equal to max depth, record common ancestor
*/
class Solution {
    TreeNode lca;
    int max = 0;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) return root;
        dfs(root, 0);
        return lca;
    }
    
    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            max = Math.max(max, depth);
            return depth;
        }
        
        int leftDepth = dfs(node.left, depth + 1);
        int rightDepth = dfs(node.right, depth + 1);
        
        if (leftDepth == max && rightDepth == max) {
            lca = node;
        }
        return Math.max(leftDepth, rightDepth);
    }
}


/*
Method2:
1) find leaf nodes, and store backward map to root (DFS/ BFS both work)
2) use leaf nodes to find way backwards till common node is found; return
*/
class Solution {
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    Map<Integer, Queue<TreeNode>> depthMap = new HashMap<>();
    int max = 0;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) return root;
        dfs(root, 0);
        Queue<TreeNode> queue = depthMap.get(max); // leaves
        if (queue.size() == 1) return queue.poll();
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<TreeNode> set = new HashSet<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                set.add(parentMap.get(node));
            }
            if (set.size() == 1) return set.iterator().next();
            queue.addAll(set);
        }
        
        return root;
    }
    
    private void dfs(TreeNode node, int depth) {
        if (node.left == null && node.right == null) {
            max = Math.max(max, depth);
            depthMap.putIfAbsent(depth, new LinkedList<>());
            depthMap.get(depth).offer(node);
            return;
        }
        if (node.left != null) {
            parentMap.put(node.left, node);
            dfs(node.left, depth + 1);
        }
        if (node.right != null) {
            parentMap.put(node.right, node);
            dfs(node.right, depth + 1);    
        }
    }
}
```