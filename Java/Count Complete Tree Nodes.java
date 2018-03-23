M
1521783524
tags: Binary Search, Tree

Complete Tree就是说, 最后一个level可能是缺node的(不是说最右下角缺node, 别忘了!)

#### DFS + Optimization
- 每次看最左left depth和最右leaf depth 是不是一样, 如果一样, 直接 2 ^ h - 1就好
- 不一样的话, 再DFS

##### Trick
- 直接DFS会timeout, O(n), 其实可以optimize
- to pass the test with O(h^2), 位运算: Math.pow(2, h) = 2 << (h - 1). 神奇!
- 2 << 1就是把所有bits往左移动一位, 也就是 * 2 

#### Iteratively
- See details in comments inline. 要对tree非常理解
- binary tree one child tree nodes # = 2 ^ h - 1; 所以一个child tree + root = 2 ^ h

```
/*
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled,
and all nodes in the last level are as far left as possible. 
It can have between 1 and 2h nodes inclusive at the last level h.

https://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees
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
Thoughts:
Complate tree: last level may not be complete

Can dfs to traverse the tree: countNodes(root.left) + countNodes(root.right) + 1;
However, time exceeds: we don't really need to go through all.

Check if the tree of current root is perfect binary tree. If so, Pow(2, height), otherwise, dfs
Time: O(h^2)

Note: Using Math.pow(2, x) will time out.
*/
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = 0;
        int rightDepth = 0;
        TreeNode node = root;
        while (node != null) {
            leftDepth++;
            node = node.left;
        }
        node = root;
        while (node != null) {
            rightDepth++;
            node = node.right;
        }
        if (leftDepth == rightDepth) {
            return (2 << (leftDepth - 1)) - 1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}


/*
Sampe approach, in different format:
 */
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getLeftHeight(root) + 1;
        int rightDepth = getRightHeight(root) + 1;
        
        if (leftDepth == rightDepth) {
            return (2 << (leftDepth - 1)) - 1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
    
    private int getLeftHeight(TreeNode node) {
        int count = 0;
        while (node.left != null) {
            node = node.left;
            count++;
        }
        return count;
    }
    
    private int getRightHeight(TreeNode node) {
        int count = 0;
        while (node.right != null) {
            node = node.right;
            count++;
        }
        return count;
    }
}



/*
Thoughts:
Iteratively, sort of. Height calculation dfs is mandatory.
Calculate left-child depth
If leftChild.left depth === rightChild.left depth: 
- We can count all left nodes + root, which is precisely: 1 << h = 2 << (h - 1) = 2 ^ h.
- Then move to root.right, and h--
If leftChild.left depth > rightChild.left depth, that indicates right has a missing left-leaf.
- If draw this out on paper, this actually completely cuts off all right node's bottom level
- We can count all right nodes + root, which is precisely: 1 << (h - 1) = 2 ^ (h - 1); // last level was wiped out
- Then move to root.left, and h--

Note: when root==null, height shall be -1 for edge case purpose.
*/
class Solution {
    private int leftMostLeafDepth(TreeNode root) {
        if (root == null)
            return -1;
        int height = 0;
        while (root.left != null) {
            height++;
            root = root.left;
        }
        return height;
    }
    
    public int countNodes(TreeNode root) {
        int nodes = 0;
        int h = leftMostLeafDepth(root); // h: leftLeafDepthForRoot
        while (root != null) {
            int LeafHeightForRightChild = leftMostLeafDepth(root.right);
            if (LeafHeightForRightChild == h - 1) {
                // Cut off all left children + root
                nodes += 1 << h; // 2 ^ h
                root = root.right;
            } else {
                nodes += 1 << (h - 1); // 2 ^ (h - 1)
                root = root.left;
            }
            h--; // Proceed to next level
        }
        return nodes;
    }
}
```