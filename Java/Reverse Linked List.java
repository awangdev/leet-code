/*
Reverse a linked list.

Have you met this question in a real interview? Yes
Example
For linked list 1->2->3, the reversed linked list is 3->2->1

Challenge
Reverse it in-place and in one-pass

Tags Expand 
Linked List Facebook Uber
*/

public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        
        while (head != null) {
            //Take head out
            ListNode temp = head;
            //Head moves on
            head = head.next;
            //Cut the dummy list, insert temp in front
            temp.next = dummy.next;
            dummy.next = temp;
        }
        
        return dummy.next;
    }
}

//This is a more easy to 'apply and go' version.
public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode reversedList = null;
        while (head != null) {
            ListNode cutOffPart = head.next;
            head.next = reversedList;
            reversedList = head;
            head = cutOffPart;
        }
        return reversedList;
    }
}