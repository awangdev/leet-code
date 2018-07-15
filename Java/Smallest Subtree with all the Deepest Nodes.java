M
1531605756
tags: Tree, Divide and Conquer, DFS
time: O(n)
space: O(n)

给一个tree, 按照题意找最一个node满足: 
1. 这个node的subtree涵盖最深level的所有leaves. 
2. 这个node必须是能找到的最deep那个

条件2的需求是因为: root本身就是满足条件1的node, 还有很多Higher-level node也是如此, 所以要找那个deepest.


#### DFS on tree
- 分析题目, 思想是: 看到tree里面所有的leaves, 找到他们最deep的 common ancestor
- Maintain a map <Node, maxChildDepth>
- Recursively dfs: return deepest node that has all leaves by these comparisons:
- 1. If left,right child same depth, return root: they need common ancestor
- 2. If not same depth, return the one with larger depth
- 被传送去上一个level的, 永远都是subtree里面符合题意的: the node containing all leaf nodes
- Visit all nodes once O(n), space O(n)

#### BFS
- Find all leaves at deepest level
- Use map to track each node-parent
- Backtrack all nodes to find common ancestor

```
/*

Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.

 

Example 1:

Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation:



We return the node with value 2, colored in yellow in the diagram.
The nodes colored in blue are the deepest nodes of the tree.
The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
Both the input and output have TreeNode type.
 

Note:

The number of nodes in the tree will be between 1 and 500.
The values of each node are unique.

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
1. Recursively dfs, find max depth leaf-parent
2. If left,right child same depth, return root
3. If not same depth, return the one with larger depth
*/
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root, 0, new HashMap<>());
    }
    
    public TreeNode dfs(TreeNode node, int depth, Map<TreeNode, Integer> map) {
        if (node == null) return node;
        if (node.left == null && node.right == null) {
            map.put(node, depth);
            return node;
        }
        // find leaf-parent, just return
        TreeNode left = dfs(node.left, depth + 1, map);
        TreeNode right = dfs(node.right, depth + 1, map);
        if (left == null || right == null) {
            int maxChildDepth = left == null ? map.get(right) : map.get(left);
            map.put(node, maxChildDepth);
            return left == null ? right : left;
        }
        if (map.get(left) == map.get(right)) {
            map.put(node, map.get(left));
            return node;
        }
        
        if (map.get(left) > map.get(right)) {
            map.put(node, map.get(left));
            return left;
        } else {
            map.put(node, map.get(right));
            return right;
        }
    }
}
/*
1. BFS find leaf, store child->parent in map
2. Find common ancestor for the leaves with the map
*/
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        // check input
        
        // find leaves with queue, build map
        
        // find common ancestor of all leaves
    }
}
```