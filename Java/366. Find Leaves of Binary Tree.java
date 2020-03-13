M
tags: Tree, DFS
time: O(n) 
space: O(h)

#### DFS: store nodes at the same depth
- the leaves are at depth 0 and the root is at highest depth
- dfs: the depth = index of the rst, start from depth = 0 at leaf
- end state: leaf node, add to rst, and return depth


```

/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
 

Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2          
 

2. Now removing the leaf [2] would result in this tree:

          1          
 

3. Now removing the leaf [1] would result in the empty tree:

          []         
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
- init rst
- dfs: the depth = index of the rst, start from depth = 0 at leaf
- end state: leaf node, add to rst, and return depth
*/
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();
        dfs(rst, root);
        return rst;
    }
    
    private int dfs(List<List<Integer>> rst, TreeNode node) {
        if (node == null) return 0;
        
        int leftDepth = dfs(rst, node.left);
        int rightDepth = dfs(rst, node.right);
        int currDepth = Math.max(leftDepth, rightDepth);
        if(rst.size() <= currDepth) rst.add(new ArrayList<>());
        rst.get(currDepth).add(node.val);

        return currDepth + 1;
    }
}
```