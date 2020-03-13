M
tags: DFS, BFS, Tree, Binary Tree, Hash Table
times: O(nlogn)
space: O(n)

Very similar to `314. Binary Tree Vertical Order Traversal` with 1 special condition: if 2 nodes at same (offset, level):
sort it by its value 

#### Method1: DFS
- the special requirement causes: we have to track exact position of nodes
- Using `Node {int offset, level, val}` and `Map<offset, Map<level, List<Val>>>`:
    - set all nodes to its correct position
    - output all together
- the `max/min` offset allows us to loop over the map in a ordered manner (save efforts of sorting)
- time: O(n) to mark all nodes at correct spot, but `O(nlogn)` to sort the vertical array
- space: O(n), mark all nodes in the nested map

#### Method2: BFS + Hash table
- A (offset, level) has 2 nodes: use nested `Map<offset, Map<level, List<Val>>>` to track nodes
- Also need a `class Node{int offset; TreeNode node}` to build queue: 
    - need `offset`: queue at each level cannot derive level index
    - need `TreeNode`: `Node` extends original `TreeNode` so we can queue it.
- lots code to write due to the `class Node` for BFS

```
/*
Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

 

Example 1:



Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).
Example 2:



Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: 
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 

Note:

The tree will have between 1 and 1000 nodes.
Each node's value will be between 0 and 1000.
*/

// Method1: DFS
class Solution {
    Map<Integer, Map<Integer, List<Integer>>> map = new HashMap<>(); // map<offset, Map<level, List<Val>>>
    int min = 0, max = 0;

    class Node {
        int offset, level, val;
        public Node (int offset, int level, int val) {
            this.offset = offset;
            this.level = level;
            this.val = val;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);
        return flattenMap();
    }
    
    private void dfs(TreeNode node, int offset, int level) {
        if (node == null) return;
        
        map.putIfAbsent(offset, new HashMap<>());
        map.get(offset).putIfAbsent(level, new ArrayList<>());
        map.get(offset).get(level).add(node.val);
        
        min = Math.min(min, offset);
        max = Math.max(max, offset);
        
        dfs(node.left, offset - 1, level + 1);
        dfs(node.right, offset + 1, level + 1);
    }
    
    private List<List<Integer>> flattenMap() {
        List<List<Integer>> rst = new ArrayList<>();
        for (int offset = min; offset <= max; offset++) {
            Map<Integer, List<Integer>> levelMap = map.get(offset);
            List<Integer> levels = new ArrayList<>(levelMap.keySet());
            List<Integer> list = new ArrayList<>();
            Collections.sort(levels);
            for (int level : levels) {
                Collections.sort(levelMap.get(level));
                list.addAll(levelMap.get(level));
            }
            rst.add(list);
        }
        return rst;
    }
}


// Method2: BFS
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
    Map<Integer, Map<Integer, List<Integer>>> map = new HashMap<>(); // map<offset, Map<level, List<Val>>>

    class Node {
        int offset;
        TreeNode treeNode;
        public Node (int offset, TreeNode treeNode) {
            this.offset = offset;
            this.treeNode = treeNode;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, root));

        int min = 0, max = 0, level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node node = queue.poll();
                int offset = node.offset;
                populateMap(node, offset, level);

                if (node.treeNode.left != null) queue.offer(new Node(offset - 1, node.treeNode.left));
                if (node.treeNode.right != null) queue.offer(new Node(offset + 1, node.treeNode.right));
                
                min = Math.min(min, offset);
                max = Math.max(max, offset);
            }
            level++;
        }
        
        return flattenMap(min, max);
    }
    
    private void populateMap(Node node, int offset, int level) {
        map.putIfAbsent(offset, new HashMap<>());
        map.get(offset).putIfAbsent(level, new ArrayList<>());
        map.get(offset).get(level).add(node.treeNode.val);
    }
    
    private List<List<Integer>> flattenMap(int min, int max) {
        List<List<Integer>> rst = new ArrayList<>();
        for (int offset = min; offset <= max; offset++) {
            Map<Integer, List<Integer>> levelMap = map.get(offset);
            List<Integer> levels = new ArrayList<>(levelMap.keySet());
            List<Integer> list = new ArrayList<>();
            Collections.sort(levels);
            for (int level : levels) {
                Collections.sort(levelMap.get(level));
                list.addAll(levelMap.get(level));
            }
            rst.add(list);
        }
        return rst;
    }
}
```