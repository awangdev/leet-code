H
tags: Stack, Tree
time: O(n)
space: O(n)

#### Method1: Stack, DFS, Inorder Traversal
- find successors and predecessors using BST (both list will be sorted); in the end, we can easily get top k from the two sorted list
    - with BST: **inorder traversal gives us sorted predecessors
    - with BST: **reversed-inorder traversal gives us sorted successors
    - smallest on top of the stack
- time: O(n) visit all nodes, O(k) to output
- space overall: O(n) to store all nodes

#### Method2: BFS, brutle force
- Itereate over all nodes and maintain pq<TreeNode> (improvemenet point: how to avoid traversing entire tree?)
- prioritize nodes that are closer to target, so we may stop early when result reaches k candidates
- time: O(n*logn)
- kinds slow and not utilizing BST

```
/*
Given a non-empty binary search tree and a target value, 
find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
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
Method1: DFS + Stack
- find successors and predecessors using BST (both list will be sorted); in the end, we can easily get top k from the two sorted list
    - with BST: inorder traversal gives us sorted predecessors
    - with BST: reversed-inorder traversal gives us sorted successors
- time: O(n) visit all nodes, O()
- space overall: O(n) to store all nodes
*/
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<Integer> predecessors = new Stack<>(), successors = new Stack<>();
        inorder(predecessors, root, target);
        reverseInorder(successors, root, target);
        
        List<Integer> list = new ArrayList<>();
        while(k-- > 0) {
            if (predecessors.isEmpty()) list.add(successors.pop());
            else if (successors.isEmpty()) list.add(predecessors.pop());
            else {
                int valA = predecessors.peek(), valB = successors.peek();
                if(Math.abs(valA - target) < Math.abs(valB - target)) list.add(predecessors.pop());
                else list.add(successors.pop());
            }
        }
        return list;
    }

    // produce sorted predecessors (smallest on top of stack)
    private void inorder(Stack<Integer> stack, TreeNode node, double target) {
        if (node == null) return;
        inorder(stack, node.left, target);
        if (node.val > target) return;
        stack.push(node.val);
        inorder(stack, node.right, target);
    }
    
    // produce sorted successors (smallest on top of stack)
    private void reverseInorder(Stack<Integer> stack, TreeNode node, double target) {
        if (node == null) return;
        reverseInorder(stack, node.right, target);
        if (node.val <= target) return;
        stack.push(node.val);
        reverseInorder(stack, node.left, target);
    }
}

/*
Method2: BFS
- eventuall we have to itereate over all nodes and maintain pq of size k.
- can prioritize nodes that are closer to target, so we may stop early when result reaches k
- time: O(n*logn)
- kinds slow
*/
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        PriorityQueue<TreeNode> queue = buildQueue(k, target);
        PriorityQueue<TreeNode> rst = buildQueue(k, target);
        
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            rst.offer(node);
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            
            while(rst.size() == k) break;
        }
        
        List<Integer> list = new ArrayList<>();
        while (list.size() != k) list.add(rst.poll().val);
        
        return list;
    }
    
        
    private PriorityQueue<TreeNode> buildQueue(int k, double target) {
        return new PriorityQueue<TreeNode>(k, new Comparator<TreeNode>() {
            public int compare(TreeNode a, TreeNode b) {
                if (Math.abs(a.val - target) - Math.abs(b.val - target) <= 0) return -1;
                return 1;
            }
        });
    }
}
```