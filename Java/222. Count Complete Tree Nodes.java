M
tags: Binary Search, Tree, DFS
time: O(n)
space: O(h)

Complete Tree就是说, 最后一个level可能是缺node的(不是说最右下角缺node, 别忘了!)

#### Method1: DFS + Optimization
- 每次看最左left depth和最右leaf depth 是不是一样
    - 如果一样, 直接 2 ^ h - 1就好
    - 不一样的话, 再DFS
- calculate `2^(h)`: 位运算, Math.pow(2, h) = 2 << (h - 1). 神奇!
    - 2 << 1就是把所有bits往左移动一位, 也就是 * 2 
- time: O(n) visit all nodes on 1 side
- space: O(h) visit all nodes on 1 side


#### Method2: Iteratively
- See details in comments inline. 要对tree非常理解
- binary tree one child tree nodes # = 2 ^ h - 1; 所以一个child tree + root = 2 ^ h

#### Method3: Binary Search
- NOT DONE, TODO: https://leetcode.com/problems/count-complete-tree-nodes/solution/

```
/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

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

Method2: Iteratively, sort of. Height calculation dfs is mandatory.
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