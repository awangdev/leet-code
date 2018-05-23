M
1519715027
tags: Tree, DFS

给一个特殊的binary tree, treeNode里面有一个 next pointer.

写一个function, 把所有node都更同level的node 连在一起. 最右边的node.next = NULL

#### DFS
- 题目要求DFS. 想清楚了如何在DFS level把几种情况都考虑了, 写起来很简单.
- 对于一个root来说, 只有几个点可以顾忌到: root.left, root.right, root.next. 
- 想办法把这三个方向的点, 能连起来的都连起来:
- 1. node.left.next = node.right
- 2. If node.next != null, link node.right.next = node.next.left;
- 然后在dfs(root.left), dfs(root.right)

#### BFS
- 不和题意，用了queue space，与Input成正比。太大。
- BFS over Tree。 用Queue 和 queue.size()，老规矩。   
- process每层queue时, 注意把next pointer加上去就好. 

```
/*
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. 
If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree 
(ie, all leaves are at the same level, and every parent has two children).

For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
Hide Tags Tree Depth-first Search
Hide Similar Problems (H) Populating Next Right Pointers in Each Node II (M) Binary Tree Right Side View

*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
/*
Thoughts:
BFS with queue will be trival, but 2^(logN) = N, so O(n) space can't do.
DFS: at each level, carefully link:
1. node.left.next = node.right
2. If node.next != null, link node.right.next = node.next.left;
*/
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null || root.left == null || root.right == null) {
            return;
        }
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
    }
}

// Previous solution. Actuall no need of a helper dfs.
//DFS. Basic implementation according to problem.
 public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        root.left.next = root.right;
        dfs(root.left);
        dfs(root.right);
    }
    
    public void dfs(TreeLinkNode node) {
        if (node == null || node.left == null || node.right == null) {
            return;
        }
        node.left.next = node.right;
        if (node.next != null)
            node.right.next = node.next.left;
        dfs(node.left);
        dfs(node.right);
    }
}



// # of leaf nodes: 2 ^ (height) ~= 2 ^ (logN) = N => used O(N) space
//BFS, However, 不和题意。 point each node to the next in queue. if none, add null
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.offer(root);
        int size = 0;
        
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                } else {
                    node.next = null;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            size = queue.size();
        }
    }
}
```