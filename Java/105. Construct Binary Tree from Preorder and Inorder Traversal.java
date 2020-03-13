M
tags: Array, Tree, DFS, Divide and Conquer, Hash Table
time: O(n)
space: O(n)

如题

#### DFS ApproachA:
- use preorder to find root, one index at a time (global index)
- use the root to divide and conquer inorder int[] to 2 sides;
    - root.left = dfs(left); root.right = dfs(right)
    - end stage: start == end index, create a node
- can use a map to store inorder <val, index> for O(1) find

#### DFS
- 和Construct from Inorder && Postorder 想法一样。
- 写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。
- 跟Convert Sorted Array to Binary Tree类似, 找到对应的index, 然后:
    - node.left = dfs(...), node.right = dfs(...)
- Divide and Conquer
    - optimize on finding `mid node`: given value, find mid of inorder:
- Instead of searching linearly, just store inorder sequence in `map <value -> index>`, O(1)
- IMPORATANT: the mid from inorder sequence will become the main baseline to tell range: 
- `range of subTree = (mid - inStart)`
- sapce: O(n), time: O(n) access

```
/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note
You may assume that duplicates do not exist in the tree.

Example
Given inorder [1,2,3] and preorder [2,1,3]

return a tree

  2

 /  \

1    3

Tags Expand 
Binary Tree
*/


/*
- use preorder to find root, 1 index at a time
- use the root to divide and conquer inorder int[] to 2 sides;
    - root.left = dfs(left); root.right = dfs(right)
    - end stage: start == end index, create a node
- can use a map to store inorder <val, index> for O(1) find
*/
class Solution {
    int index = 0;
    Map<Integer, Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0
            || preorder.length != inorder.length) return null;
        map = buildMap(inorder);
        return dfs(inorder, preorder, 0, inorder.length - 1);
    }
    
    public TreeNode dfs(int[] inorder, int[] preorder, int start, int end) {
        if (start > end) return null; // catch dfs attempting to process visisted nodes
        int rootVal = preorder[index++];
        int rootInd = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        if (start == end) return root;
        
        root.left = dfs(inorder, preorder, start, rootInd - 1);
        root.right = dfs(inorder, preorder, rootInd + 1, end);
        
        return root;
    }
    
    private Map<Integer, Integer> buildMap(int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return map;
    }
}

 /*
Thougths:
DFS with tracking of preorder/inorder sequence indexes.
preorder: start from root, traverse all left children, and then all right children.
inorder: if found the root node in the sequence, all indexes less than the root is left sub tree; same applies to right indexes.

1. Use preorder head index as root
2. Find the root node index in inorder sequence.
3. split into subproblems: track by indexes
*//**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 class Solution {
    Map<Integer, Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0
            || preorder.length != inorder.length) return null;
        map = buildMap(inorder);
        int n = preorder.length;
        return dfs(preorder, 0, n - 1, inorder, 0, n - 1);
    }
    
    public TreeNode dfs(int[] preorder, int preStart, int preEnd,
                        int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootInd = map.get(preorder[preStart]);
            
        if (rootInd < 0) return null;
        
        //root.left
        root.left = dfs(preorder, preStart + 1, preStart + (rootInd - inStart),
                        inorder, inStart, rootInd - 1);
        //root.right
        root.right = dfs(preorder, preStart + (rootInd - inStart) + 1, preEnd,
                         inorder, rootInd + 1, inEnd);
        
        return root;
    }

    private Map<Integer, Integer> buildMap(int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return map;
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
