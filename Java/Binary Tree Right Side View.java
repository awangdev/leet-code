M
1526769889
tags: Tree, DFS, BFS

给一个binary tree, 从右边看过来, return all visible nodes

#### BFS
- 最右:即level traversal每一行的最末尾.   
- BFS, queue 来存每一行的内容, save end node into list

#### DFS
- Use Map<Level, Integer> 来存每一个level的结果
- dfs(node.right), 然后 dfs(node.left)

```
/*
Given a binary tree, imagine yourself standing on the right side of it, 
return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
*/

/*
right side view:
- the tree may not be complete
- always find right-most. if right child not available, dfs into left child
- tracking back is hard for dfs
- bfs: on each level, record the last item of the queue
*/

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // check edge case
        if (root == null) {
            return result;
        }

        // init queue, result list
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // loop over queue with while loop; inner while loop to complete level
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                TreeNode node = queue.poll();
                if (size == 0) {
                    result.add(node.val);
                }
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
        }
        return result;
    }
}


/*
DFS: mark each level with map<level, node>
1. dfs right side first, then left side at each level
2. if candidate not exist, add to map, if exist, skip.
*/

class Solution {
	public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // check edge case
        if (root == null) {
            return result;
        }
        // init map, dfs
        Map<Integer, Integer> map = new HashMap<>();
        int depth = dfs(map, root, 0);
        // output result 
        for (int i = 0; i <= depth; i++) {
            if (map.containsKey(i))
                result.add(map.get(i));
        }
        return result;
    }

    private int dfs(Map<Integer, Integer> map, TreeNode node, int depth) {
        if(node == null) {
            return 0;
        }
        if (!map.containsKey(depth)) {
            map.put(depth, node.val);
        }
        int rightDepth = dfs(map, node.right, depth + 1);
        int leftDepth = dfs(map, node.left, depth + 1);
        return Math.max(leftDepth, rightDepth) + depth;
    }
}


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
02.09.2016 Revist
Thoughts:
BFS: traverse all levels, save to ArrayList, get all nodes at end of level list.
*/

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rst = new ArrayList<Integer>();
        if (root == null) {
            return rst;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int size = queue.size();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            size--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (size == 0) {
                rst.add(node.val);
                size = queue.size();
            }
        }
        
        return rst;
    }
}


/*

自己想了这个方法，有可能不是特别efficient.
一个queue放普通的BFS。
一个queue放level。
同时维护一个parent value；维护一个跟着BFS跑的level。
每个node都有一个lv。一旦lv和正在跑的level不一样，证明lv>level，那么也就是说，刚刚换行拉。parent的值，就是上一行最右边的值。DONE.


Thoughts:
Use 2 queue: one for BFS, one for level. Each node in queue has a corresponding level
Track level.
WHen level != levelQ.poll(), that means we are moving to next level, and we should record the previous(parent) node's value.
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