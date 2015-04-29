/*
Sort a linked list using insertion sort.

Example
Given 1->3->2->0->null, return 0->1->2->3->null.

Tags Expand 
Sort Linked List

Thoughts:
Look at head pointer, which is the current element we focus on.
If it's greater than the next pointer value, we move on. Use a while loop to check the entire
list every time with a new head.
If the head.val is less/equal than the next.val, we stop the at this next pointer, then cut it,
insert head.
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
     * @return: The head of linked list.
     */
    public ListNode insertionSortList(ListNode head) {
    	if (head == null) {
    		return null;
    	}
    	ListNode dummy = new ListNode(0);
    	while (head != null) {
    		ListNode node = dummy;
    		while (node.next != null && node.next.val < head.val) {
    			node = node.next;
    		}
    		ListNode temp = head.next;
    		head.next = node.next;
    		node.next = head;
    		head = temp;
    	}
    	return dummy.next;
    }
}
