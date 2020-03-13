E
tags: Tree, Recursion, BST

#### DFS based on BST rules
- sum up the matching L & R
    - Find (L,R) on left child
    - Find (L,R) on right child
    - Find (L,R) covering the root node
- space O(n), worst case O(logn), height of dfs.
- time O(n) to find all nodes between (L, R)

#### Iterative
- Using stack, or queue, list: any data structure (we are not doing ordered search)
- space O(n)
- time O(n)

```
/*
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

 

Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23
 

Note:

The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.
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

// Recursive
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        if (root.val < L) return rangeSumBST(root.right, L, R);
        if (root.val > R) return rangeSumBST(root.left, L, R);
        return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R);
    }
    
}

// Iterative: stack
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int sum = 0;
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            if (n == null) continue;
            if (n.val < R) stack.push(n.right); // search into right child
            if (n.val > L) stack.push(n.left); // search into left child
            if (L <= n.val && n.val <= R) sum += n.val;
        }
        return sum;
    }
}
// Iterative: queue
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        Queue<TreeNode> list = new LinkedList<>();
        list.offer(root);
        int sum = 0;
        while (!list.isEmpty()) {
            TreeNode n = list.poll();
            if (n == null) continue;
            if (n.val < R) list.offer(n.right); // search into right child
            if (n.val > L) list.offer(n.left); // search into left child
            if (L <= n.val && n.val <= R) sum += n.val;
        }
        return sum;
    }
}
```