M
tags: BST

```
/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

Example
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
Tags Expand 
Dynamic Programming Depth First Search

Thinking process:
- For a BST, root can be any node from node(1) to node(n).
- For each root, left nodes has mutiple forms of BST, and right node has mutiple forms of BST.
- For each root node, divide and conquer left / right
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
     * @paramn n: An integer
     * @return: A list of root
     */
    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }
    public ArrayList<TreeNode> generate(int start, int end) {
        ArrayList<TreeNode> rst = new ArrayList<TreeNode>();
        if (start > end) {
            rst.add(null);
            return rst;
        }
        for (int i = start; i <= end; i++){
            ArrayList<TreeNode> left = generate(start, i - 1);
            ArrayList<TreeNode> right = generate(i +  1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    rst.add(root);
                }
            }
        }
        return rst;
    }
}


```