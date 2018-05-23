M
1519792143
tags: Linked List, DFS, BST, Divide and Conquer

如题, 把一个sorted singly linked list 转换成一个 height balanced BST

#### DFS
- Divide and Conquer   
- 找到mid node
- 然后分割两半, 分别dfs做各自两个subtree: node.left,node.right
- 用长度来定位mid, 每次找中间点做root, 然后前半段, 后半段分别dfs with length.
- 用快慢pointer 找到mid. Better: 不用traverse entire linked list

#### Details
- slowPointer = node;
- fastPointer = node.next;
- 然后把root = mid.next     
- 然后开始sortedListToBST(mid.next.next); //后半段    
- mid.next = null;//非常重要，要把后面拍过序的断掉    
- sortedListToBST(head); //从头开始的前半段     
- 最后root.left, root.right merge一下。   

```
/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1.


Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

Example
Tags Expand 
Recursion Linked List
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
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
0. find length of elements
1. Pass length into dfs function, find mid element, use it as root.
2. Set left,right, repeat dfs
*/
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = head;
        int end = 0;
        while (node != null) {
            end++;
            node = node.next;
        }
        return dfs(head, end);
    }
    
    public TreeNode dfs(ListNode node, int end) {
        if (node == null || end < 0) {
            return null;
        }
        int mid = end / 2;
        int index = 0;
        ListNode dummy = new ListNode(-1);
        dummy.next = node;
        while (index < mid) {
            index++;
            node = node.next;
        }

        TreeNode root = new TreeNode(node.val);
        root.left = dfs(dummy.next, mid - 1);
        root.right = dfs(node.next, end - mid - 1);
        
        return root;
    }
}



/*
Previous notes:
Find the middle point of the list.
Left of the mid will be left-tree, right of the mid node will be right-tree.
*/
/*
Thoughts:
Slow/fast pointer to find mid
*/
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        } else if (head.next == null) { // be carefull in dfs
            return new TreeNode(head.val);
        }
        
        ListNode midNode = findMid(head);
        TreeNode root = new TreeNode(midNode.next.val);
        root.right = sortedListToBST(midNode.next.next);
        // Establish end point for remaining left list
        midNode.next = null;
        root.left = sortedListToBST(head);
        return root;
    }
    
    /*
    Always check if the fastNode can move forward for 2 steps
    */
    public ListNode findMid(ListNode node) {
        ListNode fastNode = node.next;
        while (fastNode.next != null && fastNode.next.next != null) {
            node = node.next;
            fastNode = fastNode.next.next;
        }
        return node;
    }
}



```