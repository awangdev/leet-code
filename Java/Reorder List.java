M
1528091290
tags: Linked List

给一个Linked list, reorder: 从head/tail 两个方向 向中间进发, re-order like: one node at a time,

#### Linked List 功能大集合
- reverse list, find mid of list, merge two list
- 先find mid, 然后把 mid.next reverse了, 最后merge 两段.
- 注意, 用完mid.next之后, 一定要 mid.next = null, 不然merge会出问题

```

/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

Thinking Process:
Similar to sort list: 
find middle.
reverse last section
merge(head, mid) alternatively by using index % 2.
Append whatever left from the 2 lists
Note: re-order in place, does not necessarily mean you can create any variable. 
As long as the variable is O(1), it should be fine.
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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode mid = findMiddle(head);
        ListNode tail = reverse(mid.next);
        mid.next = null;

        merge(head, tail);
    }
    
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode reversedList = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = reversedList;
            reversedList = head;
            head = temp;
        }
        return reversedList;
    }

    private void merge(ListNode headA, ListNode headB) {
        ListNode dummy = new ListNode(0);
        while (headA != null && headB != null) {
            dummy.next = headA;
            headA = headA.next;
            dummy = dummy.next;
            
            dummy.next = headB;
            headB = headB.next;
            dummy = dummy.next;
        }
        if (headA != null) {
            dummy.next = headA;
        } else if (headB != null) {
            dummy.next = headB;
        }
    }    
}
```