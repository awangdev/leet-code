/*
Given a linked list, remove the nth node from the end of list and return its head.

Note
The minimum number of nodes in list is n.

Example
Given linked list: 1->2->3->4->5->null, and n = 2.
After removing the second node from the end, the linked list becomes 1->2->3->5->null.
Challenge
O(n) time

Tags Expand 
Two Pointers Linked List

Thinking process:
Very similar to 'Nth to last node'. Except, have a pre pointer to keep track of the previous node of 'nth to last'.
Also have a dummy.next to store the beginning of the list;
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
 */ 
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @param n: An integer.
     * @return: The head of linked list.
     */
    ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 0) {
            return null;
        }
        int count = 0;
        ListNode dummy = new ListNode(0);
        ListNode pre = new ListNode(0);
        pre.next = head;
        dummy = pre;
        ListNode node = head;
        while (node != null && count < n) {
            node = node.next;
            count++;
        }
        while (node != null) {
            node = node.next;
            head = head.next;
            pre = pre.next;
        }
        pre.next = head.next;
        return dummy.next;   
    }
}



