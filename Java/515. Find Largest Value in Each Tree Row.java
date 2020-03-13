M
tags: Tree, BFS, DFS
time: O(n)
space: O(n)

#### Method1: DFS
- faster than BFS, using less space if not couting final rst: stack size, O(logn)
- time: O(n), visit all

#### Method2: BFS with queue
- loop over queue level and record max


```
/*
You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
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
DFS, use level to track: 1) time to add new item, 2) index to insert max val
time: O(n)
space: O(n)
*/
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        dfs(rst, root, 0);
        return rst;
    }
    
    private void dfs(List<Integer> rst, TreeNode node, int level) {
        if (node == null) return;
        if (level == rst.size()) rst.add(node.val);
        else rst.set(level, Math.max(rst.get(level), node.val));
        dfs(rst, node.left, level + 1);
        dfs(rst, node.right, level + 1);
    }
}

/*
BFS: 
time: O(n)
space: O(n)
*/
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = queue.peek().val;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            rst.add(max);
        }
        
        return rst;
    }
}

```