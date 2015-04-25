/*
39% 通过
Reverse a linked list.

样例
For linked list 1->2->3, the reversed linked list is 3->2->1

挑战
Reverse it in-place and in one-pass

标签 Expand 
Linked List

Thinking process:
User a variable ‘newList’(or reversedList) to store the partial list. It will be the final result
this will first be null
then something will add newList to its tail
The ‘something’ is actually the ‘head’ during each cycle
save: newList = something
Every cycle, cut off head.next
Store head.next in a temporary node, which we will use later. Now, link a different head.next
append: head.next = newList
Like in ThinkingProcess(1), save: newList = head.
At this moment, we are about to get into next cycle, change the head to the cut-off part that saved in the temporary node.
Cycle until head is null. 
return newList
*/

/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
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



