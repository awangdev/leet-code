M
1528086917
tags: Linked List, Two Pointers

#### Linked List
- linked list 不能像partition array一样从两边遍历
- 把小于value的加在前半段, 把 >= value的加在后半段
- 做法很普通: 建造两个list, midTail pointer, post pointer
- 把满足条件（<x, >=x）的数字分别放到两个list里面
- 记得用dummyNode track head.
- 最终midTail.next = post链接起来。

```
/*
33% Accepted
Given a linked list and a value x, 
partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2->null and x = 3,
return 1->2->2->4->3->5->null.

Example
Tags Expand 
Linked List Two Pointers
*/

/*
Thinking process:
0. dummyPre, dummyPost to store the head of the 2 list
1. Append node.val < x to listPre
2. Append node.val >= x to listPost
3. Link them togeter
4. return dummyPre.next
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
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        ListNode dummyPost = new ListNode(0);
        ListNode midTail = dummy;
        ListNode post = dummyPost;

        while (head != null) {
            if (head.val < x) {
                midTail.next = head;
                midTail = midTail.next;
            } else {
                post.next = head;
                post = post.next;
            }
            head = head.next;
        }
        
        post.next = null;
        midTail.next = dummyPost.next;
        
        return dummy.next;
    }
}

```
