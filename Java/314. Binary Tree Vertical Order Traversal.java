M
tags: BFS, DFS, Hash Table, Tree
time: O(n)
space: O(n)

给一个Binary Tree, traverse所有node, 按照vertial order 排列成output: List<List> 

重点是: col里面有排序, lower level的排在前面; 如果node遇到collision在同一个位置: 根据他们的相对位置 先放left, 再放right

#### BFS
- 1) level-traverse all nodes, 2) add node to appropriate col list(using map)
- For final output: 
    - Use min/max to track map keys, since the keys are continous
    - Map does not provide random access; unless map key is marked with sequence i = [min, max]
- Since each vertical list is appended level by level: no need to sort during output. FAST
- time: O(n), visit all nodes
- spac: O(n) to store

#### DFS
- Regular DFS to traverse all nodes, and add node to appropriate col list (using map)
- Problem: DFS does not provide natural ordering for nodes on a row.
    - Left side may have a super deep Right child branch, which will be added to col list first (since left side is visisted first)
    - It is wrong because right branch may have nodes in same column but at higher level
    - To Solve: preserve `level` for all nodes in same column
- Need to sort the final list, and slow: visit all nodes O(n) + O(KMlogM), K = # of cols, M = # of items in col
- Time: O(nLogN). O(n) + O(KMlogM), K = # of cols, M = # of items in col; in extrem, it can be a vertical line of nodes, then sort: O(nLogN)
- Space: O(n)


```
/*
Given a binary tree, return the vertical order traversal of its nodes' values. 
(ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
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
BFS: naturally level-traverse all nodes, add node to appropriate col list
Use min/max to track map keys, since it's continous
*/
class Solution {
    class Node {
        int offset;
        TreeNode treeNode;
        public Node (int offset, TreeNode treeNode) {
            this.offset = offset;
            this.treeNode = treeNode;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();
        if (root == null) return rst;
        
        int min = 0, max = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, root));
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                int offset = node.offset;
                map.putIfAbsent(offset, new ArrayList<>());
                map.get(offset).add(node.treeNode.val);
                
                if (node.treeNode.left != null) queue.offer(new Node(offset - 1, node.treeNode.left));
                if (node.treeNode.right != null) queue.offer(new Node(offset + 1, node.treeNode.right));
                
                min = Math.min(min, offset);
                max = Math.max(max, offset);
            }
        }        
        
        for (int offset = min; offset <= max; offset++) {
            if (map.containsKey(offset)) rst.add(map.get(offset));
        }
        
        return rst;
    }
}

/*
Use Map<Col Integer, List<Integer>> to track cols
dfs: add curr at col, dfs(col-1, node.left), dfs(col+1, node.right);
at end: sort map.keySet, and out

DFS can fail, if we assume `node.left subtree` always appears on top of `node.right subtree`
Therefore, between each column, node should have order as well: level
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
// DFS and add offset to a map<offset, list>, record global min,max, pick list in order (small -> large offset)
// DFS and add offset to a map<offset, list>, record global min,max, pick list in order (small -> large offset)
class Solution {
    class Node {
        int val, level;
        public Node (int val, int level) {
            this.val = val;
            this.level = level;
        }
    }
    int min = 0, max = 0;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer, List<Node>> map = new HashMap<>();
        dfs(map, root, 0, 0);
        
        List<List<Integer>> rst = new ArrayList<>();
        for (int offset = min; offset <= max; offset++) {
            if (map.containsKey(offset)) {
                List<Node> list = map.get(offset);
                list.sort(Comparator.comparing(a -> a.level));
                rst.add(output(list));
            }
        }
        return rst;
    }
    
    public void dfs(Map<Integer, List<Node>> map, TreeNode node, int offset, int level) {
        if (node == null) return;
        map.putIfAbsent(offset, new ArrayList<>());
        map.get(offset).add(new Node(node.val, level));
        
        max = Math.max(max, offset);
        min = Math.min(min, offset);
        
        dfs(map, node.left, offset - 1, level + 1);
        dfs(map, node.right, offset + 1, level + 1);
    }
    
    private List<Integer> output(List<Node> list) {
        List<Integer> rst = new ArrayList<>();
        for (Node node : list) {
            rst.add(node.val);
        }
        return rst;
    }
}
```