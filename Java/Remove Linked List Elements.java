如果match. parent.next = node.next. 
如果不match, parent 和 node 一起移动
```
/*
Remove all elements from a linked list of integers that have value val.

Have you met this question in a real interview? Yes
Example
Given 1->2->3->3->4->5->3, val = 3, you should return the list as 1->2->4->5

Tags Expand 
Linked List
*/

/*
Thoughts:
While loop through. Maintain a parent, so it can be used to skip current node.
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
    /**
     * @param head a ListNode
     * @param val an integer
     * @return a ListNode
     */
    public ListNode removeElements(ListNode head, int val) {
    	if (head == null) {
    		return head;
    	}
    	ListNode parent = new ListNode(0);
    	parent.next = head;
    	ListNode dummy = parent;
    	while (head != null) {
    		if (head.val == val) {
    			parent.next = head.next;
    		} else {
    			parent = parent.next;
    		}
    		head = head.next;
    	}
    	return dummy.next;
    }
}

```