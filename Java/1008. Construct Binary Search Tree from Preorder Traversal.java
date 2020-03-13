M
tags: DFS, Tree
time: O(n)
space: O(n)

#### Method1: Top Down DFS
- This approach highly relies on the preorder rules
    - we can use validation rules to navigate throug hteh preorder array
    - use a global index
- time:  O(n)


```
/**
Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

 

Note: 

1 <= preorder.length <= 100
The values of preorder are distinct.
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
Method1: Top Down O(n)
- Use preorder to pick one index at a time
*/
class Solution {
    int index = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return null;
        
        return dfs(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private TreeNode dfs(int[] preorder, int min, int max) {
        if (index == preorder.length) return null;
        int val = preorder[index];
        
        if (val <= min || val >= max) return null;
        index++;
        TreeNode node = new TreeNode(val);
        node.left = dfs(preorder, min, val);
        node.right = dfs(preorder, val, max);
        return node;
    }
}

/*
Alternatives tried: use start as root; in remaining array, search for mid point such that 
- nums[mid] < root.val < nums[mid + 1]
- we can split the array into 2 parts and build sub tree accordingly
- time: O(n * logn) since it requires search. Not prefered

*/

```