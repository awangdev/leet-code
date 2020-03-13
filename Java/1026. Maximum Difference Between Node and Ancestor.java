M
tags: Tree, DFS
time: O(n)
space: O(logn)

#### Method1: Top-Down DFS
- cache parent max and min => produce current max and min
- pass the max and min to dfs
- compare and return the max of dfs(left), dfs(right)
- time: O(n)
- space: O(logn)
- easy to write, a bit hard to think of 

#### Method2: Bottom-up DFS
- pass up the local (min, max) as object `Val{max, min}`
- easy to think of, but more code to write

```
/*

Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

(A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)

 

Example 1:



Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: 
We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 

Note:

The number of nodes in the tree is between 2 and 5000.
Each node will have value between 0 and 100000.

*/
// Method1
class Solution {
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }
    
    private int dfs(TreeNode node, int max, int min) {
        if (node == null) return max - min;
        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        return Math.max(dfs(node.left, max, min), dfs(node.right, max, min));
    }
}


/*

Method2: bottom up approach: pass up the local (min, max)
- end state: leaf
- 
*/
class Solution {
    
    class Val{
        int max, min;
        public Val(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }
    
    int rst = Integer.MIN_VALUE;
    
    public int maxAncestorDiff(TreeNode root) {
        dfs(root);
        return rst;
    }
    
    private Val dfs(TreeNode node) {
        if (node == null) return new Val(Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (node.left == null && node.right == null) return new Val(node.val, node.val);
        Val left = dfs(node.left), right = dfs(node.right);
        int min = Math.min(left.min, right.min);
        int max = Math.max(left.max, right.max);

        int maxDiff = Math.max(Math.abs(node.val - min), Math.abs(node.val - max));
        rst = Math.max(rst, maxDiff);
        
        return new Val(Math.max(node.val, max), Math.min(node.val, min));
    }
}
```