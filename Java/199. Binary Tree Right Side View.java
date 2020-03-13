M
tags: Tree, DFS, BFS
time: O(n)
space: O(n)

给一个binary tree, 从右边看过来, return all visible nodes

#### BFS
- 最右: 即level traversal每一行的最末尾.   
- BFS, queue 来存每一行的内容, save end node into list
- time: O(n) visit all nodes
- space: O(n) worst case unbalanced tree to have n nodes in final results


#### DFS
- Use Map<Level, Integer> to override the result at each level
- dfs: 
    - dfs(node.left) and then dfs(node.right) because we want to log right side last
- record global max depth for iteration purpose
- time: O(n) visit all nodes
- space: O(n) worst case unbalanced tree to have n stacks (and n nodes in final results)


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
        if (root == null) return result;

        // init queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // loop over queue with while loop; inner while loop to complete level
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                TreeNode node = queue.poll();
                if (size == 0) result.add(node.val);
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
    int maxDepth = -1;
	public List<Integer> rightSideView(TreeNode root) {
        // init map, dfs
        Map<Integer, Integer> map = new HashMap<>();
        dfs(map, root, 0);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= maxDepth; i++) result.add(map.get(i));
        return result;
    }

    private void dfs(Map<Integer, Integer> map, TreeNode node, int depth) {
        if(node == null) return;
        map.put(depth, node.val);
        maxDepth = Math.max(maxDepth, depth);
        dfs(map, node.left, depth + 1);
        dfs(map, node.right, depth + 1);
    }
}


```