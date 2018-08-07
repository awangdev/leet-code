M
1532015904
tags: BFS, DFS, Hash Table, Tree
time: O(n)
space: O(n)

给一个Binary Tree, traverse所有node, 按照vertial order 排列成output: List<List> 

重点是: col里面有排序, 在higher level的排在前面; 如果node遇到collision在同一个位置: 根据他们的相对位置 先放left, 再放right

#### BFS
- 应该比较好想: naturally level-traverse all nodes, add node to appropriate col list
- Use min/max to track map keys, since the keys are continous
- Map does not provide random access; unless map key is marked with sequence i = [min, max]

#### DFS
- 一开始很容易想到: enumerate一下, 先放curr node.val, 然后node.left.val, node.right.val. 非常简单
- 但是最简单的方法有错: assume所有left subtree都 排在right subtree. 但是: right subtree可能先有一个lower-left-branch,  appear in a column first.
- 所以还要preserve column list的order.
- 这里我们用了 `Map<col, Node>` 来track col, Node 里面用了 `node.level`来track level (其实再一个map也可以)
- 这样在结尾要sort,就会非常慢: Visit all nodes O(n) + O(logK) + O(KlogM), K = # of cols, M = # of items in col
- 应该也是可以optimize map keys的, 反正都是continuous key



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
        int col;
        TreeNode treeNode;
        public Node (int col, TreeNode treeNode) {
            this.col = col;
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
                map.putIfAbsent(node.col, new ArrayList<>());
                map.get(node.col).add(node.treeNode.val);
                
                if (node.treeNode.left != null) queue.offer(new Node(node.col - 1, node.treeNode.left));
                if (node.treeNode.right != null) queue.offer(new Node(node.col + 1, node.treeNode.right));
                
                min = Math.min(min, node.col);
                max = Math.max(max, node.col);
            }
        }        
        
        for (int col = min; col <= max; col++) {
            rst.add(map.get(col));
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
class Solution {
    class Node {
        int val, level;
        public Node (int val, int level) {
            this.val = val;
            this.level = level;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();
        if (root == null) return rst;
        
        Map<Integer, List<Node>> map = new HashMap<>();
        map.putIfAbsent(0, new ArrayList<>());
        map.get(0).add(new Node(root.val, 0));
        dfs(map, root, 0, 0);
        
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        
        for (int key : keys) {
            List<Node> nodes = map.get(key);
            nodes.sort(Comparator.comparing(a -> a.level));
            List<Integer> col = new ArrayList<>();
            for (Node node : nodes) {
                col.add(node.val);
            }
            rst.add(col);
        }
        
        return rst;
    }
    
    public void dfs(Map<Integer, List<Node>> map, TreeNode node, int col, int level) {
        if (node == null) return;
        if (node.left != null) {
            map.putIfAbsent(col - 1, new ArrayList<>());
            map.get(col - 1).add(new Node(node.left.val, level + 1));
        }
        if (node.right != null) {
            map.putIfAbsent(col + 1, new ArrayList<>());
            map.get(col + 1).add(new Node(node.right.val, level + 1));
        }
        
        dfs(map, node.left, col - 1, level + 1);
        dfs(map, node.right, col + 1, level + 1);
    }
}
```