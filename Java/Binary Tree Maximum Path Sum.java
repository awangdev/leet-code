H
1526524522
tags: Tree, DFS, DP, Tree DP

找max path sum,  可以从任意treeNode 到任意 treeNode.

#### Kinda, Tree DP
- 两个情况: 1. combo sum: left+right+root; 2. single path sum
- Note1: the path needs to be continuous, curr node cannot be skipped
- Note2: what about I want to skip curr node: handled by lower level of dfs(), where child branch max was compared.
- Note3: skip left/right child branch sum, by comparing with 0. 小于0的, 没必要记录

#### DP的思想
- tree给我们2条branch, 每条branch就类似于 dp[i - 1], 这里类似于dp[left], dp[right] 这样
- 找到 dp[left], dp[right] 以后, 跟 curr node结合. 
- 因为是找max sum, 并且可以skip nodes, 所以需要全局变量max
- 每次dfs() return的一定是可以继续 `continuously link 的 path`, 所以return `one single path sum + curr value`.

#### DFS, PathSum object
- that just solves everything
/*
private class PathSum {
    int singlePathMax;
    int combinedPathMax;
    PathSum(int singlePathMax, int combinedPathMax) {
        this.singlePathMax = singlePathMax;
        this.combinedPathMax = combinedPathMax;
    }
}
*/

#### Previous Notes
##### Note1
- 用 PathSum 比较特别. 没有 data structure的时候, 写起来比较繁琐.
- 第一次做有点难理解，复杂原因是：因为可能有负值啊。不能乱assume正数。   
- single path max 的计算是为了给后面的comboMax用的。
- 如果single path max小于0，那没有什么加到parent上面的意义，所以就被再次刷为0.
- combo的三种情况：(root可能小于0)   
- 1. 只有left    
- 2. 只有right
- 3. root大于0，那么就left,right,curr全部加起来。
- 情况1和情况2取一个最大值，然后和情况三比较。做了两个Math.max(). 然后就有了这一层的comboMax

##### Note2
- 12.11.2015 recap
- totally 5 conditions:   
- (save in single): left + curr.val OR right + curr.val   
- (save in combo):left, right, OR left + curr.val + right   



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
    int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        maxPathDown(root);
        return max;
    }
    
    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left)); // left single path
        int right = Math.max(0, maxPathDown(node.right)); // right single path
        max = Math.max(max, left + right + node.val); // compare combo max with global max
        return Math.max(left, right) + node.val; // always return the max single continuous path 
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


/*
Thoughts:
Don't assume positive integers .

Two ways of picking nodes: 1. Single Path (left or right)has a maximum.   2. Combine them into a final result: combinedPathMax

1. singlePathMax, two results: either pick left+root or right+root.
2. combinedPathMax: take left-max as a whole, take right-max as a whole. 
	3 possible results: left-max without parent(like...when parent<0), right-max without parent(like...when parent<0), left-max + right-max + parent.
3. Use a special container to store current node's singlePathMax and combinedPathMax. 

Note:12.03.2015
It's complex, because we could have nagative number.

Combo is compared through: just left, just right, or combo of all.
*/
public class Solution {
    private class PathSumType {
        int singlePathMax;
        int combinedPathMax;
        PathSumType(int singlePathMax, int combinedPathMax) {
            this.singlePathMax = singlePathMax;
            this.combinedPathMax = combinedPathMax;
        }
    }
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxPathSum(TreeNode root) {
        PathSumType result = helper(root);
        return result.combinedPathMax;
    }
    
    public PathSumType helper(TreeNode root) {
        if (root == null) {
            return new PathSumType(0, Integer.MIN_VALUE);
        }
        //Divide
        PathSumType left = helper(root.left);
        PathSumType right = helper(root.right);
        //Conquer
        //Step 1: prepare single path max for parent-level comparison.
        int singlePathMax = Math.max(left.singlePathMax, right.singlePathMax) + root.val;
        singlePathMax = Math.max(singlePathMax, 0);//If less than 0, no need to keep, because it only decrease parent-level max.
        
        //first comparison: does not include root node at all(this would be applicable when curr.val < 0, so we take this condition into account)
        int combinedPathMax = Math.max(left.combinedPathMax, right.combinedPathMax);
        //second comparison: 
        combinedPathMax = Math.max(combinedPathMax, left.singlePathMax + right.singlePathMax + root.val);
        
        return new PathSumType(singlePathMax, combinedPathMax);
    }
    
}

```