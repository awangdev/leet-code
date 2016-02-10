/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
Credits:
Special thanks to @stellari for adding this problem and creating all test cases.

Tags: Linked List

*/
/*
Thgouts:
Check length of A,B
Start comparing from same distance from end.
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
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null && headB == null) {
        	return headA;
        }
        //Get size:
        ListNode node = headA;
        int sizeA = 0;
        while(node != null) {
            sizeA++;
            node = node.next;
        }
        node = headB;
        int sizeB = 0;
        while(node != null) {
            sizeB++;
            node = node.next;
        }
        
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        int lenDiff = (sizeA >= sizeB ? 1 : -1) * (sizeA - sizeB);
        node = sizeA >= sizeB ? headA : headB;
        while (lenDiff > 0) {
        	node = node.next;
        	lenDiff--;
        }
        if (sizeA >= sizeB) {
            nodeA = node;
        } else {
            nodeB = node;
        }
        while (nodeA != null) {
        	if (nodeA.val == nodeB.val) {
        		return nodeA;
        	} else {
        		nodeA = nodeA.next;
        		nodeB = nodeB.next;
        	}
        }//END while
        return null;
    }
}














