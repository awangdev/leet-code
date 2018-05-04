M

遍历到M前，
存一下那个点，
从M开始， for loop， reverse [m~n]。 然后把三段链接在一起。


```
/*
28% Accepted
Reverse a linked list from position m to n.

Note
Given m, n satisfy the following condition: 1 = m = n = length of list.

Example
Given 1->2->3->4->5->NULL, m = 2 and n = 4, return 1->4->3->2->5->NULL.

Challenge
Reverse it in-place and in one-pass

Tags Expand 
Linked List

Thinking process:
0. Use dummy node to store head
1. Find mNode, store the front nodes
2. Reverse from mNode to nNode
3. Link front, middle, end nodes together
Note, when doing reverse, always:
    - reversedList = 1st item
    - postNode = 2nd item   
    - store 3rd item in temp: temp = postNode.next
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
     * @param ListNode head is the head of the linked list 
     * @oaram m and n
     * @return: The head of the reversed ListNode
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m >= n) {
            return head;
        }

        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        head = dummyNode;
        ListNode nodeFront = null;
    
        
        for (int countM = 1; countM < m; countM++) {
            if (head == null) {
                return head;
            }
            head = head.next;
        }
        nodeFront = head;
        ListNode mNode = head.next; //Head is Mth node. Reserve it
        ListNode reversedList = mNode;
        ListNode postNode = mNode.next;
        for (int countN = m; countN < n; countN++) {
            ListNode temp = postNode.next;
            postNode.next = reversedList;
            reversedList = postNode;
            postNode = temp;
        }
        //List front, middle and end section
        nodeFront.next = reversedList;
        mNode.next = postNode;
        
        return dummyNode.next;
    }
}



```