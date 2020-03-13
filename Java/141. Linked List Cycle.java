E
tags: Linked List, Two Pointers, Slow Fast Pointer, Cycle Detection
time: O(n)
space: O(1)

#### Method1: Two Pointer: Slow Fast Pointer
- Imagine two runners running on a track at different speed. What happens when the track is actually a circle?
- https://leetcode.com/problems/linked-list-cycle/solution/
- O(1) sapce: 用快慢指针, `start=head.next`, `end=head.next.next`
- Fast pointer will eventually catch up to slow pointer

#### Method1: Hash Table
- O(n) space: 用HashMap，一直add elements.  如果有重复，那么很显然是有Cycle

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
// method 1: Fast/Slow pointer: fast pointer will eventually catch up with slow pointer
*/
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        
        ListNode slow = head, fast = head.next; // start at different spot
        while (slow != fast) {
            if (fast == null || fast.next == null) return false;
            slow = slow.next; // move 1 step
            fast = fast.next.next; // move 2 steps
        } // end while loop when slow==fast
        return true;
    }
}

// Method2: hash table
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        Set<ListNode> set = new HashSet<>();
        
        while (head != null) {
            if (!set.add(head)) return true;
            head = head.next;
        } 
        return false;
    }
}

```