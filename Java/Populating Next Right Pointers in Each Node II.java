H

非perfect tree, 也就是有random的null children. DFS＋BFS


Populating Next Right Pointers in Each Node I 里面依赖parent.next.left来作链接，但现在这个parent.next.left很可能也是Null.

1. 于是需要移动parent去找children level的next node。    
2. 并且每次在一个level, 要用BFS的思想把所有parent 过一遍，也就是把parent 正下方的children全部用.next链接起来    
    原因: 到下一层children变成parent, 他们需要彼此之间的connection, grand children才可以相互连接。


Note: runtime O(n * 2^log(n) ) = O(n^2), not good.

```
/*
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
Hide Company Tags Microsoft Bloomberg Facebook
Hide Tags Tree Depth-first Search
Hide Similar Problems (M) Populating Next Right Pointers in Each Node

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
 DFS to traverse the tree.
 Also BFS using next pointer. clear node's children level per visit
 */
 public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        dfs(root);
    }
    //Clear connection problems on each level: because the lower children level will relay on parent's level connection.
    public void dfs(TreeLinkNode node) {
        if (node == null) {
            return;
        }
        TreeLinkNode parent = node;
        while (parent != null) {
            //Connect left-> right in normal case
            if (parent.left != null) {
                parent.left.next = parent.right;
            }
            //Left || right: one of needs to use addRight(node) method to build the .next bridge.
            if (parent.left != null && parent.left.next == null) {//Fact: parent.right = null, from last step
                parent.left.next = addRight(parent);
            } else if (parent.right != null && parent.right.next == null) {
                parent.right.next = addRight(parent);
            }
            parent = parent.next;
        }

        dfs(node.left);
        dfs(node.right);
    }
    
    //Always take parentNode, and try to return the very first available node at child level
    public TreeLinkNode addRight(TreeLinkNode node) {
        while (node != null) {
            if (node.next == null) {
                return null;
            }
            if (node.next.left != null) {
                return node.next.left;
            }
            if (node.next.right != null) {
                return node.next.right;
            }
            node = node.next;
        }
        return null;
    }

}
 

```