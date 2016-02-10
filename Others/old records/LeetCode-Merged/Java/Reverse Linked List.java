/*
Reverse a singly linked list.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?

Tags: Linked List
Similar Problems: (M) Reverse Linked List II, (M) Binary Tree Upside Down, (E) Palindrome Linked List

*/
/*
Thoughts:
Cut off everything from [2 ~ ] and save it in cutoff;
Append old reversed list to current head. Make itself as the new reversedList. Basically: append the 1st element to head of the reversedList, like a stack.
Save head = cutOff: basically moves on to next element.
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
    public ListNode reverseList(ListNode head) {
        if (head == null) {
        	return head;
        }
       	ListNode reversedList = null;
        while (head != null) {
        	ListNode cutOff = head.next;
        	head.next = reversedList;
        	reversedList = head;
        	head = cutOff;
        }
        return reversedList;
    }
}