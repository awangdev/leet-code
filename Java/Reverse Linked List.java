E

建立新list。每次把newList append 在current node的后面。   
用head来循环所有node。

```
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

//Use empty node, add to tail, append empty node to next node. keep going like that
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newList = null;

        while (head != null) {
            ListNode temp = head.next;
            head.next = newList;
            newList = head;
            head = temp;
        }
        return newList;
    }
}
```