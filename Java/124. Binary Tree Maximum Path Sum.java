H
tags: Tree, DFS, DP, Tree DP
time: O(n)
space: O(logn)

找max path sum,  可以从任意treeNode 到任意 treeNode.

#### DFS
- IMPORTANT: DO NOT ASSUME positive integers
- Overall idea: write example and realize 2 cases at each node:
    - 1. `combo sum`: left + right + root
    - 2. `single path sum WITH curr node`: left/right + root
- DFS returns the path over curr node: a path needs to be continuous, so we cannot skip curr node.
- IMPORTANT, key discovery: if left/right single path over curr node is less than 0: reutrn 0. 
    - Parent path will simply drop this path, since we want **maximize** the path sum.
    - It is so IMPORTANT: when left or right becomes 0, when comparing with global combo path:
        - it automatically covers a special case: `single left/right path + node`, since one of left/right == 0!!!
- With the above understanding: what if I want to skip curr node and just want left/right path w/o curr node:
    - it is handled and compared with global in dfs(node.left) or dfs(node.right) automatically!
- time: O(n), go over whole tree
- space: O(logn), tree height.

#### DP的思想
- tree给我们2条branch, 每条branch就类似于 dp[i - 1], 这里类似于dp[left], dp[right] 这样
- 找到 dp[left], dp[right] 以后, 跟 curr node结合. 
- 因为是找max sum, 并且可以skip nodes, 所以需要全局变量max
- 每次dfs() return的一定是可以继续 `continuously link 的 path`, 所以return `one single path sum + curr value`.

#### DFS, PathSum object
- 用 PathSum 比较特别. 没有 data structure的时候, 写起来比较繁琐.
- 第一次做有点难理解，复杂原因是：因为可能有负值啊。不能乱assume正数。   
- single path max 的计算是为了给后面的comboMax用的。
- 如果single path max小于0，那没有什么加到parent上面的意义，所以就被再次刷为0.
- combo的三种情况：(root可能小于0)   
- 1. 只有left    
- 2. 只有right
- 3. root大于0，那么就left,right,curr全部加起来。
- 情况1和情况2取一个最大值，然后和情况三比较。做了两个Math.max(). 然后就有了这一层的comboMax

```
/*
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.


Example
Given the below binary tree:

  1
 / \
2   3
return 6.

Tags Expand 
Divide and Conquer Dynamic Programming Recursion

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
// DP, DFS, 99.73%
public class Solution {
    int global = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        dfs(root); 
        return global;

        // int pathOverRootMax = dfs(root); Math.max(pathOverRootMax, global); // NO NEED, because single path is covered in dfs, when left/right == 0.
    }
    
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, dfs(node.left)); // left single path w/0 node, OR: no left path at all
        int right = Math.max(0, dfs(node.right)); // right single path w/0 node, OR: no right path at all
        global = Math.max(global, left + right + node.val); // compare combo max with global max: 3 cases.
        return Math.max(left, right) + node.val; // always return the max single continuous path 
    }
}

// DFS
// Slight different writing than above, but similar to the `PathSum` approach below
public class Solution {
    int global = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return global;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left); // left single path max
        int right = dfs(node.right); // right single path max
        global = Math.max(global, left + right + node.val);

        // always return the max path over curr node; OR: skip completely if negative
        // if any path < 0, it should be ignore instead adding to parent combo path
        return Math.max(0, Math.max(left, right) + node.val); 
    }
}

// Use a customized object to track single path, v.s. combo path
public class Solution {
    // Class used to carry sum in recursive dfs
    private class PathSum {
        int singlePathMax;
        int combinedPathMax;
        PathSum(int singlePathMax, int combinedPathMax) {
            this.singlePathMax = singlePathMax;
            this.combinedPathMax = combinedPathMax;
        }
    }
    
    // Goal: find the combined path value, if applicable
    public int maxPathSum(TreeNode root) {
        PathSum result = dfs(root);
        return result.combinedPathMax;
    }
    
    public PathSum dfs(TreeNode root) {
        if (root == null) {
            return new PathSum(0, Integer.MIN_VALUE);
        }
        //Divide
        PathSum left = dfs(root.left);
        PathSum right = dfs(root.right);
        
        //Conquer

        //Step 1: build single path max (continuous path sum including curr root)
        int singlePathMax = Math.max(left.singlePathMax, right.singlePathMax) + root.val;
        //If less than 0, set to 0. It'll be dropped for combinedPathMax, so leave it to 0.
        singlePathMax = Math.max(singlePathMax, 0);
        
        //Step2: build combined path max.
        // Compare leftChild.combo vs. rightChild.combo
        int combinedPathMax = Math.max(left.combinedPathMax, right.combinedPathMax);
        // Compare with left.single + right.single + root.val
        combinedPathMax = Math.max(combinedPathMax, left.singlePathMax + right.singlePathMax + root.val);
        
        // Return the summary for the current node.
        return new PathSum(singlePathMax, combinedPathMax);
    }
    
}


```