M
tags: Two Pointers, Slow Fast Pointer, Cycle Detection, Linked List
time: O(n)
space: O(1)

#### Slow Fast Pointers
- find slow/fast to detect the meeting point
- find begin node of the cycle: traverse from head, also move slow; utill head/slow meets slow

```
/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Note: Do not modify the linked list.

 

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.


Example 2:

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.


Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.


 

Follow-up:
Can you solve it without using extra space?
*/

/*
- find slow/fast to detect the meeting point
- use a new `finder` pointer traverse from head, also move slow; utill it meets slow
*/
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return findBegin(head, slow);
        }
        return null;
    }
    
    private ListNode findBegin(ListNode head, ListNode slow) {
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}
```