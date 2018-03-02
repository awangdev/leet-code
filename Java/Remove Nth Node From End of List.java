M
1520009626
tags: Linked List, Two Pointers

O(n), one pace, no extra space
找到窗口, 然后平移, 最后pre 和 head之间 skip一个node就好.

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
        if (head == null) {
            return null;
        }
        int count = 0;
        ListNode dummy = new ListNode(-1);
        ListNode pre = new ListNode(0);
        pre.next = head;
        dummy = pre;
        ListNode endNode = head; // end node
        // Establish window
        while (count < n) {
            endNode = endNode.next;
            count++;
        }
        while (endNode != null) {
            endNode = endNode.next;
            pre = pre.next;
            head = head.next;
        }
        pre.next = head.next;// skips node between pre and head
        return dummy.next;
    }
}

/*

Previous Notes
Very similar to 'Nth to last node'. Except, have a pre pointer to keep track of the previous node of 'nth to last'.
Also have a dummy.next to store the beginning of the list;
 */
```