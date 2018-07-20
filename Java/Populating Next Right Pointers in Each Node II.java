M
1532062919
tags: Tree, DFS
time: O(n)
space: O(1)

给一个binary tree, 用constant space link 所有所有node.next to same level next node.

#### DFS
- 用constant space 也就是不可以BFS, 但是mention了用dfs stack space没问题 (提示啊!)
- 1. link leftChild -> rightChild
- 2. resolve root.rightMost child -> first possible root.next.left/right child
- 3. dfs connect(rightChild), connect(leftChild)
- Each level should be fully linked from left side, so every reach to parent will have valid path or end.

#### Trick
- 1. 处理 nextNode -> next -> next ...的case: 找到第一个有child的next node才可以罢休. 这个case很容易miss
- 2. 我们的假设是, 上一个level的所有node都应该是linked, 那么在dfs时候, 就应该先connect(root.right). 右孩子的全处理完毕, 那么trick1才可以施行.

```
/**
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
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
Example:

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

*/

/*
DFS: 
1. link leftChild -> rightChild
2. resolve root.rightMost child -> first possible root.next's left/right child
3. dfs connect(rightChild), connect(leftChild)
Each level should be fully linked from left side, so every reach to parent will have valid path or end.
*/
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;
        
        // link children, if applicable
        if (root.left != null) {
            root.left.next = root.right; 
        }
        
        // resolve root's right-most children, with neighbor
        TreeLinkNode rightMostNode = root.right != null ? root.right : root.left;
        TreeLinkNode nextNode = root.next;
        while (nextNode != null) {
            if (nextNode.left != null || nextNode.right != null) {
                rightMostNode.next = nextNode.left != null ? nextNode.left : nextNode.right;
                break;
            }
            nextNode = nextNode.next;
        }
        
        connect(root.right);
        connect(root.left);
    }
}


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
 
 // Previous solution, think too much : )
 /*
 DFS to traverse the tree.
 Also BFS using next pointer. clear node's children level per visit
 Populating Next Right Pointers in Each Node I 里面依赖parent.next.left来作链接，但现在这个parent.next.left很可能也是Null.
1. 于是需要移动parent去找children level的next node。    
2. 并且每次在一个level, 要用BFS的思想把所有parent 过一遍，也就是把parent 正下方的children全部用.next链接起来    
    原因: 到下一层children变成parent, 他们需要彼此之间的connection, grand children才可以相互连接。
Note: runtime O(n * 2^log(n) ) = O(n^2), not good.
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