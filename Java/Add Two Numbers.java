M
1519711343

LinkedList都已经反转好了，直接做.
遍历两个l1,l2把carry-on处理好，每次生成一个新node，最后检查carry-on.

跟Add Binary的理解方式一模一样.

注意:
Linked List 没有天然size.
用DummyNode(-1).next来hold住结果.


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

 /*
Thoughts:
The list has been reversed, just add them up.
Append one more ListNode if there is an carry.
*/
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        int carry = 0;
        ListNode node = new ListNode(-1);
        ListNode head = node;
        // Add l1 and l2
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            sum = sum % 10;
            node.next = new ListNode(sum);
            node = node.next;
        }

        // Finish adding carry
        if (carry != 0) {
            node.next = new ListNode(carry);
        }

        return head.next;
    }
}

// Previous solution
public class Solution {
    /**
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2 
     */
    public ListNode addLists(ListNode l1, ListNode l2) {
        ListNode rst = new ListNode(0);
        ListNode dummy = rst;
        int carrier = 0;
        //while
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                carrier += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carrier += l2.val;
                l2 = l2.next;
            }
            rst.next = new ListNode(carrier % 10);
            carrier = carrier / 10;
            rst = rst.next;
        }
        //check the carrier
        if (carrier == 1) {
            rst.next = new ListNode(1);
        }
        return dummy.next;
    }
}

```