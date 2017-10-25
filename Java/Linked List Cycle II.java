HashMap很简单就做了。


O(1)要首先break while loop when there is a slow==fast
然后，然后就有个我不懂得地方：

当head == slow.next时候， head就是cycle starting point.
也就是说，当slow 移动到了那个回溯点，slow.next那个点就刚好是head的那个点...

这个可能要写一写，装一装，证明证明才行...不是特别清楚。
```
/*
Given a linked list, return the node where the cycle begins.

If there is no cycle, return null.

Example
Given -21->10->4->5, tail connects to node index 1，return 10

Challenge
Follow up:

Can you solve it without using extra space?

Tags Expand 
Two Pointers Linked List

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
/*

    Thoughts: 12.10.2015
    Note sure why Lintcode says it's hard. Now try to do both:
    1. HashMap
    2. Slow/Fast pointer
    
    Note: The linkNode is not necessary unique. They can have diffeent values, but as long as they are
    not on same physical address, no cycle.
    That is: it's all about index, rather than the value.

    SO: if we do hashmap, we are hasing the entire ListNode object, so whenever there is duplicate, 
    that is a match.

    Rather: if we do slow/fast pointer, 
*/

//HashMap
public class Solution {
    public ListNode detectCycle(ListNode head) {  
        if (head == null) {
            return null;
        }

        HashMap<ListNode, Integer> map = new HashMap<ListNode, Integer>();//Does not matter of the value
        while (head != null) {
            if (map.containsKey(head)) {
                return head;
            } else {
                map.put(head, head.val);
            }
            head = head.next;
        }
        
        return null;
    }
}


//Slow/Fast Pointer
//http://www.jiuzhang.com/solutions/linked-list-cycle-ii/
/*
	1. just like in Linked List Cycle. Keep looking. If found a slow==fast, break the 1st while loop.

	2. At that moment, the slow is not the cycle starting point. We need to look for it
	There must be some proof within the 2nd step, which i dont know. SO, need sort of remember it:
		When head == slow.next, then head is the cycle starting point ....
		(not exactly sure why. I guess this is why it's a hard question)
	
*/
public class Solution {
    public ListNode detectCycle(ListNode head) {  
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
        	if (fast == null || fast.next == null) {
        		return null;
        	}
        	slow = slow.next;
        	fast = fast.next.next;
        }

        while (head != slow.next) {
        	slow = slow.next;
        	head = head.next;
        }

        return head;
    }
}

```