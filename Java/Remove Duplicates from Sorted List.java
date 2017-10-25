E

一旦node.next 和node是重复，跳


```
/*
40% 通过
Given a sorted linked list, delete all duplicates such that each element appear only once.

样例
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

标签 Expand 
Linked List

Thinking process:
check head null
Use dummy node to reserve head
while everything when head.next is not null
compare head.val == head.next.val? 
If so, head.next = head.next.next
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
1. Check head.
2. Remove next if next != null && next.val == node.val
3. Use node to move
*/
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode node = head;
        while (node != null) {
            while (node.next != null && node.val == node.next.val) {
                node.next = node.next.next;
            }
            node = node.next;
        }
        return head;
    }
}

public class Solution {
    /**
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of linked list
     */
    public static ListNode deleteDuplicates(ListNode head) { 
        if (head == null) {
            return head;
        }
        ListNode node = head;
        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }  
}




/*
Use two pointers:
http://gongxuns.blogspot.com/2012/12/leetcode-remove-duplicates-from-sorted_11.html
*/


```