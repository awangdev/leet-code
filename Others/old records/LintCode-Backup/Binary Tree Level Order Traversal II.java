M

普通BFS，用一个queue，加上一个queue.size()来交替换行.


```

/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
(ie, from left to right, level by level from leaf to root).

Example
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
 

return its bottom-up level order traversal as:

[
  [15,7],
  [9,20],
  [3]
]
Tags Expand 
Queue Binary Tree Binary Tree Traversal Breadth First Search
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
1. Non-recursive
similar to Binary Tree Level Order Traversal I, just when adding into the final result,
add to the top all the time. Then the first added will be at the bottom: result.add(0, list)
2. Recursive:
    Similar to Level Traversal I, do a dfs. The difference is: everytime, we use ArrayList<ArrayList<>> like a stack by doing add(0, newList);
    when populating the levelArrayList, make sure to address the correct corresponding level.

 */

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: buttom-up level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrderButtom(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return result;
        }
        /*
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            result.add(0, list);
        }*/
        
        dfs(root, 0, result);
        return result;
    }
    public void dfs(TreeNode root, int level, ArrayList<ArrayList<Integer>> rst) {
        if (root == null) {
            return;
        }
        if (level >= rst.size()) {
            rst.add(0, new ArrayList<Integer>());
        }
        dfs(root.left, level + 1, rst);
        dfs(root.right, level + 1, rst);
        rst.get(rst.size() - level - 1).add(root.val);
    }
}

```
