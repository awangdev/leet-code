M 
tags: Linked List
time: O(n)
space: O(1)

#### Reverse to make significant digit at tail
- Need add from the back and calculate carry
- Reverse list, so insignificant digit at head; calculate carry
- Reverse back when output

```
/*
Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example :

Input: [1,2,3]
Output: [1,2,4]
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
Head: 1, 2, 3
- Need add from the back and calculate carry
- Reverse list, so head: 3,2,1; add from head and carry down
- Reverse back when output

*/
class Solution {
    public ListNode plusOne(ListNode head) {
        ListNode reversedHead = reverse(head);
        
        int carry = 1;
        ListNode node = reversedHead;
        while (node != null && carry != 0) {
            int sum = node.val + carry;
            node.val = sum % 10;
            carry = sum / 10;
            
            node = node.next;
        }
        
        node = reverse(reversedHead);
        if (carry > 0) {
            ListNode temp = node;
            node = new ListNode(carry);
            node.next = temp;
        }
        
        return node;
    }
    
    private ListNode reverse(ListNode node) {
        ListNode dummy = new ListNode(0);
        
        while(node != null) {
            ListNode temp = node.next;
            node.next = dummy.next;
            dummy.next = node;
            node = temp;// move pointer
        }
        return dummy.next;    
    }
}
```