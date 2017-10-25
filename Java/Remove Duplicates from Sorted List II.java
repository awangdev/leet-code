斩草除根。
多个node，check node.next ?= node.next.next
```
/*
26% Accepted
Given a sorted linked list, delete all nodes that have duplicate numbers, 
leaving only distinct numbers from the original list.

Example
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

Tags Expand 
Linked List

Thinking process:
Create a dummyHead
User a pointer node to run through the list
Similar to Remove Duplicates from Sorted List I, but checking next and next.next
If there is a match on head.next && head.next.next, delete head.next node and link to next ones until a different node appear
return dummyHead.next
*/


/**
 * Definition for ListNode
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
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of the linked list
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }   
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode node = dummyHead;
    
        while (node.next != null && node.next.next != null) {
            if (node.next.val == node.next.next.val) {
                int duplicatedVal = node.next.val;
                while (node.next != null && node.next.val == duplicatedVal) {
                    node.next = node.next.next;
                }
            } else {
                node = node.next;
            }
        }
        
        return dummyHead.next;
    }
}


```