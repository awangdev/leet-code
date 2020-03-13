E
tags: Binary Search, Tree, BST
time: O(logn)
space: O(1)

给一个BST, 和一个double target, 走位找到最接近的number.

Concept: Iterate over all logN nodes in the BST and record the closest. Rather than finding the value at +/- 0.5 precision

#### Binary Search
- 记录找到过的closest
- Binary Search, 根据current node走位, until null leaf
- time: O(logn), space O(1) since no extra space used

#### DFS, Recursive
- when less than curr val, consider left
- when greater than curr val, consider right
- dfs到底, 然后每一层比较, 再return
- time: O(logn), space: O(logn)


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
Binary search, maintain a closest value.
Note: initial closest in real case is just the root, since we start from the root
*/
public class Solution {
    public int closestValue(TreeNode root, double target) {
				if (root == null) return 0;
        double closest = root.val;
        while (root != null) {
            if (root.val == target) return root.val;
            if (Math.abs(target - closest) >= Math.abs(target - root.val)) {
                closest = root.val;
            }
            if (root.val > target) root = root.left;
            else root = root.right;
        }//END while
        return (int)closest;
  	}
}

/*
DFS:
before going into the left/right child, compare target with root, leftChild and rightChild see if a match
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

```