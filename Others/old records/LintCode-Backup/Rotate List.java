
/* Given a list, rotate the list to the right by k places, where k is non-negative.

Example
Given 1->2->3->4->5->null and k=2
return 4->5->1->2->3->null
Tags Expand 
Basic Implementation Linked List

Thining process:
Two pointers.
First pointer move k steps.
Then 2 pointers start moving together. When 1st pointer reaches the end, then 2nd pointer should be in middle.
Let 2nd pointer be head, and move original head to tail of the list

*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        //Check length
        int length = 0;
        ListNode dummy = head;
        while(dummy != null) {
            dummy = dummy.next;
            length++;
        }
        k = k % length;
        //Store dummy as 1 node before tail
        dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        for (int i = 0; i < k; i++) {
            head = head.next;
        }
        //Move 2 pointers. When head reaches end, tail.next will be at the newHead
        ListNode tail = dummy;
        while (head.next != null) {
            head = head.next;
            tail = tail.next;
        }
        head.next = dummy.next;//Link old Head to the end, form circle
        dummy.next = tail.next;//Link tail.next as new head. tail should be end point.
        tail.next = null;//add null to end point tail
        return dummy.next;
    }
}
