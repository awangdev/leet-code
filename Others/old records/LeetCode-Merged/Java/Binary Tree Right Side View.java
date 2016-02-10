自己想了这个方法，有可能不是特别efficient.
一个queue放普通的BFS。
一个queue放level。
同时维护一个parent value；维护一个跟着BFS跑的level。
每个node都有一个lv。一旦lv和正在跑的level不一样，证明lv>level，那么也就是说，刚刚换行拉。parent的值，就是上一行最右边的值。DONE.
```
/*
Given a binary tree, imagine yourself standing on the right side of it, 
return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \\
2     3         <---
 \\     \\
  5     4       <---
You should return [1, 3, 4].

Tags: Tree, Depth-first Search, Breadth-first Search
Similar Problems: (M) Populating Next Right Pointers in Each Node

*/

/*
Thoughts:
Use 2 queue: one for BFS, one for level. Each node in queue has a corresponding level
Track level.
WHen level != levelQ.poll(), that means we are moving to next level, and we should record the previous(parent) node's value.
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
    public List<Integer> rightSideView(TreeNode root) {
    	List<Integer> rst = new ArrayList<Integer>();
    	if (root == null) {
    		return rst;
    	}   
    	Queue<TreeNode> q = new LinkedList<TreeNode>();
    	Queue<Integer> levelQ = new LinkedList<Integer>();
    	q.offer(root);
    	levelQ.offer(1);
    	int level = 1;
    	int parent = root.val;
    	TreeNode node = null;
    	
    	while (!q.isEmpty()) {
    		node = q.poll();
    		int lv = levelQ.poll();
    		if (level != lv) {
    		    level++;
    			rst.add(parent);
    		}
    		parent = node.val;
    		if (node.left != null) {
    			q.offer(node.left);
    			levelQ.offer(lv + 1);
    		} 
    		if (node.right != null) {
    			q.offer(node.right);
    			levelQ.offer(lv + 1);
    		}
    	}//END while
    	rst.add(parent);
    	return rst;
    }
}










```