E
1521773643
tags: Binary Search, Tree

给一个BST, 和一个double target, 走位找到最接近的number.

#### Recursive
- when less than curr val, consider left
- when greater than curr val, consider right
- dfs到底, 然后每一层比较, 再return

#### Binary Search
- 记录找到过的closest
- Binary Search, 根据current node走位,
- 找到 node.val == target, 或者走位走完, return closest

```
/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

Tags: Tree Binary Search
Similar Problems: (M) Count Complete Tree Nodes, (H) Closest Binary Search Tree Value II

*/

/*
Thoughts:
dfs, before going into the left/right child, compare target with root, leftChild and rightChild see if a match
*/
class Solution {
    public int closestValue(TreeNode root, double target) {
        if (root.left != null && target < root.val) {
            int leftResult = closestValue(root.left, target);
            return Math.abs(leftResult - target) < Math.abs(root.val - target) ? leftResult : root.val;
        }
        if (root.right != null && target > root.val) {
            int rightResult = closestValue(root.right, target);
            return Math.abs(rightResult - target) < Math.abs(root.val - target) ? rightResult : root.val;
        }
        return root.val;
    }
}

/*
Thoughts:
Binary search, maintain a closest value.
Note: initial closest in real case is just the root, since we start from the root
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
public class Solution {
    public int closestValue(TreeNode root, double target) {
 		if (root == null) {
 			return 0;
 		}       
 		double closest = root.val;
 		while (root != null) {
 			if (root.val == target) {
 				return root.val;
 			} else {
 				if (Math.abs(target - closest) >= Math.abs(target - root.val)) {
 					closest = root.val;
 				}
 				if (root.val > target) {
 					root = root.left;
 				} else {
 					root = root.right;
 				}
 			}
 		}//END while
 		return (int)closest;
    }
}
```