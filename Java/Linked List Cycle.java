O(1) sapce: 用快慢指针。一个跑.next, 一个跑.next.next。 总有一次，fast会因为cycle而追上slow。
那个时候其实slow.val = fast.val.

O(n):用HashMap，一直add elements.  如果有重复，那么很显然是有Cycle le 
```
/*
50% Accepted
Given a linked list, determine if it has a cycle in it.



Example
Challenge
Follow up:
Can you solve it without using extra space?

Tags Expand 
Linked List Two Pointers

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
    REDO 12.10.2015
    Rulese for fast/slow pointer:
    1. move slow and fast,
    2. See if the object address matches

    IMPORTANT: do not compare the value, because they didn't say value has to be unique.
    Even with same value, the ListNode object can be different at different index.
*/
    
public class Solution {
    public boolean hasCycle(ListNode head) {  
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {//Compare their reference address
            if (fast == null || fast.next == null) {//travel till the end
                return false;//no cycle
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //If slow==fast and breaks the while loop, then there must be a cycle
        return true;
    }
}


/*
Thinking process:
If there is a cycle, then slow node and fast node will meet at some point.


However, we cannot simply compare slow.val == fast.val.
This is because, there can be different link node with same value, but they are stored on different index, 
so they should not be cycle.

The below passes the test case in lintcode, which is wrong.

public class Solution {
    public boolean hasCycle(ListNode head) {  
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow.val == fast.val) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
*/

```