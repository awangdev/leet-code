M
1526788922
tags: Linked List, Two Pointers

给一个single linked list, 右移k steps. k non-negative.

#### Linked List basics
- 记得用dummy.next来存head.
- 特殊: 这里k可能大于list总长. 写一写linked node 移动的步数, 然后 k = k % n.
- 找到newTail, newHead, 然后利用dummy, 换位子

```

/* 
Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL

*/

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // edge case
        if (head == null || head.next == null || k <= 0) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // find length n, store endNode
        ListNode node = head;
        int n = 1;
        while (node.next != null) {
            n++;
            node = node.next;
        }
        ListNode endNode = node; // node.next == null

        // mod k, find (n - k - 1)th node
        k = k % n;
        int step = n - k - 1;
        ListNode newTail = head; // newTail.next will be the newHead
        while (step > 0) {
            newTail = newTail.next;
            step--;
        }
        
        // Re-link
        endNode.next = dummy.next; // link endNode -> original head
        dummy.next = newTail.next; // update dummy.next = newHead, which is just newTail.next
        newTail.next = null; // cut tail.next

        return dummy.next;
    }
}


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

```