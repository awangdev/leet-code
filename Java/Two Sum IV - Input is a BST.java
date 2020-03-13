E
1533407180
tags: Tree

HashSet to store visited items. Same old 2 sum trick.

```
/*
Given a Binary Search Tree and a target number, 
return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
*/

// hashmap, in-order traverse for smaller items, recursively
class Solution {
    Set<Integer> set = new HashSet<>();
    
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        if (findTarget(root.left, k)) return true;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        if (findTarget(root.right, k)) return true;
        
        return false;
    }
}
```