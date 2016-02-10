M

第一次做有点难理解，复杂原因是：因为可能有负值啊。不能乱assume正数。   
   single path max 的计算是为了给后面的comboMax用的。
   如果single path max小于0，那没有什么加到parent上面的意义，所以就被再次刷为0.

combo的三种情况：(root可能小于0)   
   1. 只有left    
   2。 只有右边   
   3. root大于0，那么就left,right,curr全部加起来。

情况1和情况2去一个最大值，然后和情况三比较。做了两个Math.max(). 然后就有了这一层的comboMax


12.11.2015 recap:   
   So totally, 5 conditions:   
   (save in single)    
        left + curr.val OR right + curr.val   
   (save in combo:)    
   left, right, OR left + curr.val + right   



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