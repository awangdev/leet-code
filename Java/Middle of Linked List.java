快慢指针

不在乎slow是不是到底，因为fast肯定先到。
确保fast, fast.next不是Null就好


return slow
```
/*
Find the middle node of a linked list.

Have you met this question in a real interview? Yes
Example
Given 1->2->3, return the node with value 2.

Given 1->2, return the node with value 1.

Tags Expand 
Linked List

*/
/*
    Thoughts:
    Practice LinkedList, fast and slow pointer
*/

public class Solution {
    /**
     * @param head: the head of linked list.
     * @return: a middle node of the linked list
     */
    public ListNode middleNode(ListNode head) { 
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast!= null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}


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
```