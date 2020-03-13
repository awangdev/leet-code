M
tags: Linked List, Math
time: O(max(m,n))
space: O(max(m,n))

LinkedList都已经反转好了，直接做. 跟Add Binary的理解方式一模一样.

#### Math, Linked List
- reverse order helps calculation
    - add additional carry to end
    - not same length: align on left
- traverse till both ends
- 遍历两个l1,l2把carry-on处理好，每次生成一个新node，最后检查carry-on.


```
/*
You have two numbers represented by a linked list, 
where each node contains a single digit. 
The digits are stored in reverse order, 
such that the 1's digit is at the head of the list. 
Write a function that adds the two numbers and returns the sum as a linked list.

Example
Given 7->1->6 + 5->9->2. That is, 617 + 295.

Return 2->1->9. That is 912.

Given 3->1->5 and 5->9->2, return 8->0->8.

Tags Expand 
Cracking The Coding Interview Linked List High Precision
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

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            node.next = new ListNode(carry);
            node = node.next;

            if (l1 != null) {
                node.val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                node.val += l2.val;
                l2 = l2.next;
            }

            carry = node.val / 10;
            node.val = node.val % 10;
        }
        
        if (carry != 0) node.next = new ListNode(carry);
        
        return dummy.next;
    }
}

```