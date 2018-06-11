M
1527832674
tags: BST, DP, Tree, Divide and Conquer

给一个数字n, 找到以(1...n)为node的所有unique BST.

#### BST
- 根据BST规则, divide and conquer
- 取一个value, 然后分两半(start, value - 1), (value + 1, end) 分别dfs
- 然后左右两边的结果cross match

#### DP? Memoization?

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
    public List<TreeNode> generateTrees(int n) {
     //   List<TreeNode> rst = new ArrayList<>();
     //   if (n <= 0) {
     //      return rst;
     //   }
        return generate(1, n);
    }

    public List<TreeNode> generate(int start, int end) {
        List<TreeNode> rst = new ArrayList<>();
        if (start > end) {
            rst.add(null);
            return rst;
        }
        for (int i = start; i <= end; i++){
            List<TreeNode> leftChildren = generate(start, i - 1);
            List<TreeNode> rightChildren = generate(i + 1, end);
            for (TreeNode left : leftChildren) {
                for (TreeNode right : rightChildren) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    rst.add(root);
                }
            }
        }
        return rst;
    }
}



```
