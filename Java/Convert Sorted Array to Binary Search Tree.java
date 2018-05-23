E
1519630976
tags: Tree, DFS, Divide and Conquer

如题

#### DFS
- Binary Search Tree特点: 左边的node都比右边的node小. 
- 如果要height相差<1, 必须左右sub tree均分. 做DFS(num, start, end)
- 在每一个level, 找到中间点, 然后分割2办, 继续dfs
- Divide and Conquer

```
/*
Given an array where elements are sorted in ascending order, 
convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree
 in which the depth of the two subtrees of every node never differ by more than 1.


Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
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
/*
Thoughts: to keep height diff < 1, need make sure left-sub and right sub tree have relatively same amount of nodes.
Divide the nums from mid point, DFS with index
*/
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return dfs(nums, 0, nums.length - 1);
    }
    
    public TreeNode dfs(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        if (start > end || end >= nums.length) {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs(nums, start, mid - 1);
        node.right = dfs(nums, mid + 1, end);
        return node;
    }
}





/*
LintCode
Given a sorted (increasing order) array, Convert it to create a binary tree with minimal height.

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4,5,6,7], return

     4
   /   \
  2     6
 / \    / \
1   3  5   7
Note
There may exist multiple valid solutions, return any of them.

Tags Expand 
Cracking The Coding Interview Recursion Binary Tree

Thoughts:
1. Find middle point x.
2. All index before x, goes to left of the tree. Same apply to right tree
  build sub array and pass alone: we can pass index start, end.
  use parent node and pass along
3. Recur on left side array.

*/

//Binary Search的感觉. 中间一开两半, divde and conquer,左右各自recursive下去build left/right child.
public class Solution {
    /**
     * @param A: an integer array
     * @return: a tree node
     */
    public TreeNode sortedArrayToBST(int[] A) {  
        TreeNode root = null;
        if (A == null || A.length == 0) {
          return root;
        }
        root = helper(0, A.length - 1, A);
        return root;
    }  

    public TreeNode helper(int start, int end, int[] A) {
      if (start > end) {
        return null;
      }
      //add middle node
      int mid = start + (end - start)/2;
      TreeNode node = new TreeNode(A[mid]);
      //Split and append child
      node.left = helper(start, mid - 1, A);
      node.right = helper(mid + 1, end, A);
      return node;
    }
}
```