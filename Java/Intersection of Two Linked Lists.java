E
1525664839
tags: Linked List

给两个 linked list, 问从哪个node开始, 两个 linked list 开始有重复?

#### Basics
- 长短list，找重合点
- 长度不同的话，切掉长的list那个的extra length
- 那么起点一样后，重合点就会同时到达
- Time O(n) * 2, constant space

```
/*
Write a program to find the node at which the intersection of two singly linked lists begins.

Example
The following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.

Note
If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Challenge
Your code should preferably run in O(n) time and use only O(1) memory.

Tags Expand 
Linked List
*/
// Count list length for headA, headB
// Shift the longer list and compare
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
    		return null;
    	}
    	int countA = calcListLength(headA);
    	int countB = calcListLength(headB);
    	int diff = Math.abs(countA - countB);
        
        if (countA > countB) {
            headA = shiftNode(headA, diff);
        } else {
            headB = shiftNode(headB, diff);
        }

        while (headA != null && headB != null) {
    		if (headA == headB) {
    			return headA;
    		}
    		headA = headA.next;
    		headB = headB.next;
    	}
    	
    	return null;
    }
    
    private int calcListLength(ListNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }
    
    private ListNode shiftNode(ListNode node, int shift) {
        while (shift != 0) {
    		shift--;
    		node = node.next;
    	}
        return node;
    }
}

/*
	Thoughts:
	If a and b share some part, if cut off the extra header of b (seen in above example), they should start at same index and touch c1 at same time.
	So traverse a, b, and calculate countA, countB => dif = countB - countA.
	cut off the extra, then start iterating to find c1.

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
    /**
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode 
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	if (headA == null || headB == null) {
    		return null;
    	}
    	int countA = 0;
    	int countB = 0;
    	int diff = 0;
    	ListNode node = headA;
    	while (node != null) {
    		countA++;
    		node = node.next;
    	}
    	node = headB;
    	while (node != null) {
    		countB++;
    		node = node.next;
    	}
    	diff = Math.abs(countA - countB);
    	node = (countA > countB) ? headA : headB;
    	while (diff != 0) {
    		diff--;
    		node = node.next;
    	}
    	ListNode nodeA = (countA > countB) ? node : headA;
    	ListNode nodeB = (countA > countB) ? headB : node;
    	while (nodeA != null && nodeB != null) {
    		if (nodeA == nodeB) {
    			return nodeA;
    		}
    		nodeA = nodeA.next;
    		nodeB = nodeB.next;
    	}
    	
    	return null;
    }  
}

```