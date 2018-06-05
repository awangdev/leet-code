M
tags: Linked List

基本方法: O(n) sapce, time
遍历。
遇到duplicate(可能多个),  while直到node.next不是duplicate.
接下去,既然不是duplicate,那就add 进 set


如果不用extra memory, do it in place:
那就要sort linked list. 用merge sort.

复习merge sort:
1. find middle.
2. recursively: right = sort(mid.next); left = sort(head).
3. within sort(), at the end call merge(left, right)
```
/*
Remove Duplicates from Unsorted List

Write code to remove duplicates from an unsorted linked list.

Example
Given 1->3->2->1->4.

Return 1->3->2->4

Challenge
(hard) How would you solve this problem if a temporary buffer is not allowed? In this case, you don't need to keep the order of nodes.
Tags Expand 
Linked List
*/

/*
	Thoughts:
	Basic: use a hashSet to store node. Skip duplicate
*/
/**
 * Definition for ListNode
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
     * @param head: The first node of linked list.
     * @return: head node
     */
    public ListNode removeDuplicates(ListNode head) { 
    	if (head == null) {
    		return head;
    	}
    	HashSet<Integer> set = new HashSet<Integer>();
    	ListNode node = head;
    	set.add(node.val);
    	while (node != null) {
    		while (node.next != null && set.contains(node.next.val)) {
    			node.next = node.next.next; 
    		}
    		if (node.next != null) {
    			set.add(node.next.val);
    		}
    		node = node.next;
    	}
    	return head;
    }  
}


//No extra buffer: merge Sort the linked list, then loop it through

public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: head node
     */
    public ListNode removeDuplicates(ListNode head) { 
    	if (head == null) {
    		return head;
    	}
    	ListNode newHead = sort(head);
    	ListNode dummy = newHead;
    	while (newHead != null) {
    		if (newHead.next != null && newHead.val == newHead.next.val) {
    			newHead.next = newHead.next.next;
    		}
    		newHead = newHead.next;
    	}
    	return dummy;
    }  
    //Merge Sort's code

	/*
		Merge sort, using findMidle and merge.
	*/
	public ListNode sort(ListNode node) {
		if (node == null || node.next == null) {
			return node;
		}
		ListNode mid = findMiddle(node);
		ListNode right = sort(mid.next);
		mid.next = null;
		ListNode left = sort(node);
		return merge(left, right);
	}


    /*
    	Two pointer slow, fast
    	When fast pointer reaches end, return mid;
    */
    public ListNode findMiddle(ListNode node){
    	ListNode slow = node;
    	ListNode fast = node.next;
    	while (fast != null && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	return slow;
    }

    /*
		Merge the two list based on value
    */
	public ListNode merge(ListNode n1, ListNode n2) {
		ListNode node = new ListNode(0);
		ListNode dummy = node;
		while (n1 != null && n2 != null) {
			if (n1.val < n2.val) {
				node.next = n1;
				n1 = n1.next;
			} else {
				node.next = n2;
				n2 = n2.next;
			}
			node = node.next;
		}
		if (n1 != null) {
			node.next = n1;
		}
		if (n2 != null) {
			node.next = n2;
		}
		return dummy.next;
	}

	
}



```