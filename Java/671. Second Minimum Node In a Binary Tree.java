E
tags: Tree, BFS
time: O(n)
space: O(n) leaf nodes

#### BFS
- min tree: parent node is the min of left/right child
- BFS to traverse the tree and find 1st non-root smallest val
- Improvement area: when `node.val >= nextMin`, no need to dive into node children since it is a min Tree.

#### DFS
- Find left and right val: 
    - if left/right equals root.val, that means the left or right sub children could have larger number
    - Therefore DFS into left or right
- compare and return min(left, right)

```
/*
Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:

Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
 

Example 2:

Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.
*/


/*
Method1: Brutle Force, BFS
- min tree: parent node is the min of left/right child
- 2nd mini will be < root/min
- BFS to find 1st non-root smallest val
*/
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        Integer nextMin = null;
        int min = root.val;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                TreeNode node = queue.poll();
                // optimization: no need to work on this subtree
                if (nextMin != null && node.val >= nextMin) continue; 
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                if (node.val != min) {
                    nextMin = nextMin == null ? node.val : Math.min(nextMin, node.val);
                }
            }
        }

        return nextMin != null ? nextMin : -1;
    }
}

// Method2: DFS
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        if (root.left == null && root.right == null) return -1;
        
        int left = root.left.val, right = root.right.val;
        if (root.val == left) left = findSecondMinimumValue(root.left);
        if (root.val == right) right = findSecondMinimumValue(root.right);
        
        if (left != -1 && right != -1) return Math.min(left, right);
        if (left != -1) return left;
        return right;
    }
}
```