M
tags: Binary Tree, DFS
time: O(n)
space: O(n), stacks

给一个binary tree, 把tree做成 linked list的形式, in-place.

#### Method1: DFS return tail node
- DFS to return the tail of the flattened list
- Careful handling the null child. Choose one and both works:
    - Option1: put non-null as right child and continue dfs
    - Option2: put non-null as left child and continue dfs

#### Method2: void DFS
- latten the tree, no extra space.
    - 1. Set right node in buffer, and connect: `root.right = root.left`, DFS flatten(root.right) 
    - 3. 移花接木: traverse to tail of the current root.right and attach the buffer node.
    - 3. flatten the remaining of buffer

##### 注意
- 顺序一定要清楚, 不能写错, 写几个example可以看出来
- 移动的那些node, 要把node.left = null, 清扫干净

```
/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to 
the next node of a pre-order traversal.

Challenge
Do it in-place without any extra memory.

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
// Method1, Option1: DFS by returning and attaching tail,  move non-null child to right side
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;
        dfs(root);
    }
    
    public TreeNode dfs(TreeNode node) {
        if (node.left == null && node.right == null) return node;
        if (node.right == null) { // when one of them is null, attach remaining to right side
            node.right = node.left;
            node.left = null;
        }
        TreeNode temp = node.right;  // buffer
        
        if (node.left != null) { // DFS to find tail, and attach right to tail
            TreeNode tail = dfs(node.left);
            node.right = node.left;
            node.left = null;
            tail.right = temp;
        }

        // DFS the rest right
        return dfs(temp);
    }
}

// Method1, Option2: DFS by returning and attaching tail, move non-null child to left
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;
        dfs(root);
    }
    
    public TreeNode dfs(TreeNode node) {
        if (node.left == null && node.right == null) return node;
        if (node.left == null) { // node.right != null, then swich right to left, so the logic below can work
            node.left = node.right;
            node.right = null;
        }
        
        TreeNode temp = node.right; 
        
        TreeNode tail = dfs(node.left);
        node.right = node.left;
        node.left = null;
        
        if (temp == null) return tail; // do not test null right

        tail.right = temp;
        return dfs(temp);
    }
}

/*
Method2: 
1. Move root.left to root.rigthMost child (while loop find leaf)
2. Take notion of the first root.right, and flatten(xx) on it.
3. On each level, if not left child, flatten right child.
*/
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;
        if (root.left == null) {
            flatten(root.right);
        } else {
            // Reserve right sub tree
            TreeNode rightNode = root.right;
            
            // Move left child to right side, cut off original left child
            root.right = root.left;
            root.left = null;

            // Flatten the new right child
            flatten(root.right);

            // Append previous right child to end of flatten tree
            TreeNode node = root;
            while (node.right != null) {
                node = node.right;
            }
            node.right = rightNode; // connect
            flatten(root.right);
        }
    }
}


```