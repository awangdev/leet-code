M
1520231597
tags: Linked List, Two Pointers, Math

LinkedList 里面有 cycle, 找到cycle的起始点(第一个重复出现的element).

#### Slow, fast Pointer
- 快慢指针, O(1)space.
- 1. 确认有cycle后 2. 数学问题:找到开头.
- 当head == slow.next时候， head就是cycle starting point.
- 也就是说，当slow 移动到了那个回溯点，slow.next那个点就刚好是head的那个点...

#### 证明
- 1. 假设慢指针走t步, 快指针走快一倍, 也就是2t.
- 2. 我们假设cycle的长度是Y, 而进入cycle之前的长度为X.
- 3. 假设慢指针走了m圈cycle, 而快指针走了n圈cycle之后, 两个pointer相遇.
- 4. 最终在Y cycle里面的K点相遇, 也就是两个指针都在这最后一圈里面走了K 步.
- 那么:
- t = X + mY + K
- 2t = X + nY + K
- 整合公式: X + K = (n - 2m)Y
- 这里的m和n不过是整数的跑圈数, 也就是说X和K加在一起, 总归是结束cycle. X 和 K 互补
- 结论: 当slow/fast 指针在K点相遇后, 再走X步, 就到了cycle的起点, 也就是题目要求的起点.

#### Hash Table, O(n) space


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
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
/*
http://fisherlei.blogspot.com/2013/11/leetcode-linked-list-cycle-ii-solution.html
Thougths:
Once the slow/fast pointer meets, it becomes a math problem.
O(n)
*/
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            if (slow == fast) break;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (slow != fast) return null;

        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}

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