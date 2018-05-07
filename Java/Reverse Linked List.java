E
1525646182
tags: Linked List

#### Reverse List
- Linked List的基本操作: 每次insert在开头
- 用head来循环所有node
- 不需要额外空间
- Time O(n), Space O(1)

```
/*
Reverse a linked list.

Example
For linked list 1->2->3, the reversed linked list is 3->2->1

Challenge
Reverse it in-place and in one-pass

Tags Expand 
Linked List Facebook Uber
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// This is cleaner, and save all reversed result in dummy.next
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // dummy 保持不动, 不断把新的node insert到 dummy.next
        ListNode dummy = new ListNode(-1);
        while (head != null) {
            ListNode temp = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = temp;
        }
        return dummy.next;
    }
}


/**
Alternatively, we can start from using head as first node of the reversed list.
NOTE: have to mark the headNode, and mark headdNode.next = null at the end.
*/
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode headNode = head;
        ListNode reversedList = head;
        head = head.next;
        while (head != null) {
            ListNode temp = head.next;
            head.next = reversedList;
            reversedList = head;
            head = temp;
        }
        headNode.next = null;
        return reversedList;
    }
}

```