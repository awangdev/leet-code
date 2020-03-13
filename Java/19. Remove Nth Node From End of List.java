M
tags: Linked List, Two Pointers
time: O(n)
space: O(1)

#### Two Pointer
- 1 end pointer to define the window based n steps
- 1 pre pointer to track the node before the targeting node
- when end reaches null, remove nth node: link pre and head.next 

```
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


*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
Thoughts:
Find starting piont of the window, and keep moving forward, until candidate reach end.
*/
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1), pre = new ListNode(0);
        pre.next = head;
        dummy = pre;

        // Establish window
        ListNode end = head; // end node
        while (n--> 0 && end != null) end = end.next;

        // move window
        while (end != null) {
            end = end.next;
            pre = pre.next;
            head = head.next;
        }
        // remove nth node
        pre.next = head.next;
        return dummy.next;
    }
}

```