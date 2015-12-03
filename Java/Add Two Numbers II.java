方向相反。巧用stack.
```
/*
You have two numbers represented by a linked list, where each node contains a single digit. 
The digits are stored in forward order, such that the 1's digit is at the head of the list. 
Write a function that adds the two numbers and returns the sum as a linked list.

Example
Given 6->1->7 + 2->9->5. That is, 617 + 295.

Return 9->1->2. That is, 912.

Tags Expand 
Linked List High Precision
*/

/*
	Thoughts: Different from Add Two Numbers I, which is in reverse order.
			6	1	7
			2	9	5
			8	10	12

put the 2 linked list in 2 stacks. process the reversed list.
Save into another result stack.
At the end, return the actual order.
O(n)
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
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2 
     */
    public ListNode addLists2(ListNode l1, ListNode l2) {
    	if (l1 == null && l2 == null) {
    		return null;
    	} else if (l1 == null || l2 == null) {
    		return l1 == null ? l2 : l1;
    	}

    	Stack<ListNode> result = new Stack<ListNode>();
    	Stack<ListNode> s1 = new Stack<ListNode>();
    	Stack<ListNode> s2 = new Stack<ListNode>();

    	while (l1 != null) {
    		s1.push(l1);
    		l1 = l1.next;
    	}

    	while (l2 != null) {
    		s2.push(l2);
    		l2 = l2.next;
    	}

    	int carrier = 0;
    	while(!s1.isEmpty() || !s2.isEmpty()){
    		int sum = 0;
    		if (!s1.isEmpty() && !s2.isEmpty()) {
    			sum += s1.pop().val + s2.pop().val;
    		} else if (!s1.isEmpty()) {
    			sum += s1.pop().val;
    		} else {
    			sum += s2.pop().val;
    		}
    		result.push(new ListNode((sum + carrier) % 10));//2, 1, 9
    		carrier = (sum + carrier) / 10; // 12/10 = 1; 11/10 = 1; (8+1)/ 10 = 0
    	}
    	if (carrier == 1) {
    		result.push(new ListNode(carrier));
    	}

    	//return results:
    	ListNode node = new ListNode(0);
    	ListNode dummy = node;
    	while (!result.isEmpty()) {//219
    		node.next = result.pop();
    		node = node.next;
    	}

    	return dummy.next;
    }  
}

```