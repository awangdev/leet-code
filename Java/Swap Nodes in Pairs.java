swap总是confuse.
画三个block, 1,2,3. 连线。
```
/*
Swap Nodes in Pairs

Given a linked list, swap every two adjacent nodes and return its head.

Example
Given 1->2->3->4, you should return the list as 2->1->4->3.

Challenge
Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

Tags Expand 
Linked List
*/

/*
	Thoughts:
	1. swap
	2. move 2 steps, then swap again.
	3. becareful node.next == null, that's the end of list. no swapping.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class Solution {
    /**
     * @param head a ListNode
     * @return a ListNode
     */
    public ListNode swapPairs(ListNode head) {
    	if (head == null) {
    		return head;
    	}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		head = dummy;
    	while (head.next != null && head.next.next != null) {
    		ListNode n1 = head.next;
    		ListNode n2 = head.next.next;
    		
    		n1.next = n2.next;
    		n2.next = n1;
    		n1 = n2;

    		head = head.next.next;
    	}
    	return dummy.next;
    }
}

```