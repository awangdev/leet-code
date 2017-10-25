E

小的放前。每次比head大小。   
while过后，把没完的list一口气接上。   

一开始建一个node用来跑路, 每次都存node.next = xxx。存一个dummy。用来return dummy.next.

```
/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example
Given 1->3->8->11->15->null, 2->null , return 1->2->3->8->11->15->null

Tags Expand 
Linked List

Thinking process:
1. Merge sorted list, compare before add to node.next
2. when any of l1 or l2 is null, break out.
3. add the non-null list at the end of node.
4. return dummy.next.

*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        final ListNode head = new ListNode(0);
        ListNode node = head;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }

        if (l1 != null) {
            node.next = l1;
        } else if (l2 != null) {
            node.next = l2;
        }
        return head.next;
    }
}

/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Tags: Linked List
Similar Problems: (H) Merge k Sorted Lists, (E) Merge Sorted Array, (M) Sort List, (M) Shortest Word Distance II

*/

/*
Thouhts:
Loop throgh both list. Make sure to check the border cases
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode node = new ListNode(0);
        ListNode dummy = node;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                node.next = l2;
                break;
            } else if (l2 == null) {
                node.next = l1;
                break;
            } else {
                if (l1.val < l2.val) {
                    node.next = l1;
                    l1 = l1.next;
                } else {
                    node.next = l2;
                    l2 = l2.next;
                }
                node = node.next;
            }
        }//end while
        return dummy.next;
    }
}

```