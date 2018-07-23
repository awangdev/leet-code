M
1525661850
tags: Linked List

reverse 一个 linked list 中  [m ~ n] 的一部分.

#### Reverse linked list
- 在基本的reverse linked list 上面 多了一层: 找到front node,  接下来的 [m ~ n] node 需要被reverse
- 只需要reverse中间的部分.
- Reverse的时候: 用一个dummyNode, 这道题里面, 其实就用 nodeFront, 那么 dummy.next 就是整个reversed list.

##### 注意
- 一定要Mark开头的那个mth node, 最后用它接上 剩下node tail. 不然后面的node会断掉

#### Previous notes
- 遍历到M前，
- 存一下那个点，
- 从M开始， for loop， reverse [m~n]。 然后把三段链接在一起。


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
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
Thoughts:
- Find last node before m
- reverse mth node because it will be the end of the reversed list
- reverse [m ~ n] nodes
- link the 3 parts: front, reversed list, and the tail

*/

public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m >= n) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        
        for (int i = 1; i < m; i++) {
            node = node.next;
        }
        ListNode nodeFront = node;
        ListNode mNode = node.next; //This is the mth node and will be the tail of reversed list. Reserve it for post-linking

        node = node.next;
        for (int i = m; i <= n; i++) {
            ListNode temp = node.next;
            node.next = nodeFront.next;
            nodeFront.next = node;
            node = temp;
        }
        // the previous mth node is now at tail, and should link the very next node in the list.
        mNode.next = node;

        return dummy.next;
    }
}



/** 
Previous notes:
0. Use dummy node to store head
1. Find mNode, store the front nodes
2. Reverse from mNode to nNode
3. Link front, middle, end nodes together
Note, when doing reverse, always:
    - reversedList = 1st item
    - postNode = 2nd item   
    - store 3rd item in temp: temp = postNode.next
**/


public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m >= n) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        
        for (int i = 1; i < m; i++) {
            node = node.next;
        }
        ListNode nodeFront = node;
        
        ListNode mNode = node.next; //This isthe mth node. Reserve it for post-processing
        node = mNode.next; // (m+1)th node
        ListNode reversedList = mNode;
        
        for (int countN = m; countN < n; countN++) {
            ListNode temp = node.next;
            node.next = reversedList;
            reversedList = node;
            node = temp;
        }
        // List front and reversed list
        nodeFront.next = reversedList;
        // the current node is the tail
        mNode.next = node;
        
        return dummy.next;
    }
}

```