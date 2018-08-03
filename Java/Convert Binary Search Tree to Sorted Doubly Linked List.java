M
1531694102
tags: Linked List, Tree, DFS, BST
time: O(n)
space: O(1)

题目描述起来有点复杂, 简而言之: 把 BST 转换成一个 sorted doubly linked list. (in-place)

#### Tree, In-order traversal
- 平时做过convert BST to sored list: 画一下就理解, 其实就是in-order traversal
- 只不过做的时候要小心地 doubly link them
- 理解之后就简单了, traverse all nodes,  DFS 好做: `left, curr, right`

##### 题目特殊特点
- 自始至终用了同一个 `Node {val, left, right}`, 而并不是开一个新的doubley linked list class
- extra space 的问题, 是因为它需要create new DoublyLinkedNode class: different from `Convert Binary Search Tree to Sorted Doubly Linked List (extra space)`
- 要求in-place: 不能重新create new node

```
/*
LeetCode: https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/description/
Convert a BST to a sorted circular doubly-linked list in-place. 
Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:

We want to transform this BST into a circular doubly linked list. 
Each node in a doubly linked list has a predecessor and successor. 
For a circular doubly linked list, the predecessor of the first element is the last element, 
and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. 
The "head" symbol means the node it points to is the smallest element of the linked list.
 
Specifically, we want to do the transformation in place. After the transformation, 
the left pointer of the tree node should point to its predecessor, 
and the right pointer should point to its successor. 
We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. 
The solid line indicates the successor relationship, 
while the dashed line means the predecessor relationship.

*/


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
/*
dfs, L, R,
Link L(head, tail) -> curr -> R(head, tail)
Return head in dfs
In main func: return head, smallest element
*/
class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        if (root.left == null && root.right == null) {
            link(root, root);
            return root;
        }
        
        // link them
        Node leftHead = treeToDoublyList(root.left); // left head
        Node rightHead = treeToDoublyList(root.right); // right head
        Node leftTail = leftHead != null ? leftHead.left : null;
        Node rightTail = rightHead != null ? rightHead.left : null;

        Node head = null;
        if (rightHead == null) {
            head = leftHead;
            link(leftTail, root);
            link(root, leftHead);
        } else if (leftHead == null){
            head = root;
            link(root, rightHead);
            link(rightTail, root);
        } else { // both leftHead,rightHead exist
            head = leftHead;
            link(leftTail, root);
            link(root, rightHead);
            link(rightTail, leftHead);
        }
        
        return head;
    }
    
    private void link(Node nodeA, Node nodeB) {
        nodeA.right = nodeB;
        nodeB.left = nodeA;
    }
}
```