M

Divide and Conquer   
用快慢pointer

找到mid。   
然后把root = mid.next     

然后开始sortedListToBST(mid.next.next); //后半段    
mid.next = null;//非常重要，要把后面拍过序的断掉    
sortedListToBST(head); //从头开始的前半段     


最后root.left, root.right merge一下。   

```
/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

Example
Tags Expand 
Recursion Linked List

Thinking Process:
Find the middle point of the list.
Left of the mid will be left-tree, right of the mid node will be right-tree.

*/

/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    public TreeNode sortedListToBST(ListNode head) {  
        if (head == null) {
            return null;
        } else if (head.next == null) {
            return new TreeNode(head.val);
        }
        
        ListNode mid = findMiddle(head);
        TreeNode root = new TreeNode(mid.next.val);
        TreeNode right = sortedListToBST(mid.next.next);
        mid.next = null;
        TreeNode left = sortedListToBST(head);
       
        root.left = left;
        root.right = right;
        return root;
    }
    
    
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}



```