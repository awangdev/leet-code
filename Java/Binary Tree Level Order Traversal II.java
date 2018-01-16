M

方法1:
跟Binary Tree Level Order Traversal一样,只不过存result一直存在存在0位.

方法2(略复杂, 不需要):
普通BFS，用一个queue，加上一个queue.size()来交替换行. 
或者多用一个queue来存下个level的nodes

方法3:
DFS, 根据level来append每个list
rst里面add(0,...)每次都add在list开头

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

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            final ArrayList<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                final TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(0, list);
        }// end while
        
        return result;
    }
}

/*
Thoughts:
Breadth first traversal. Add to 0 position every time.
BFS uses a queue for level -> traversal completes when the queue is drained.
Use another queue to store next level, and switch with current queue when need to be.
*/
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        final List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> queueLevel = new LinkedList<>();
        List<Integer> level = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            level.add(node.val);
            if (node.left != null) {
                queueLevel.add(node.left);
            }
            if (node.right != null) {
                queueLevel.add(node.right);
            }
            if (queue.isEmpty()) {
                queue = queueLevel;
                result.add(0, level);
                queueLevel = new LinkedList<>();
                level = new ArrayList<>();
            }
        }
        return result;
    }
}
 
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
