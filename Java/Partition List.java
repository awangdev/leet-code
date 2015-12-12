不能像partitioin array一样从两边遍历。

那就最普通的，建造两个list

把满足条件（<x, >=x）的数字分别放到两个list里面

记得用dummyNode track head.
最终pre.next = post链接起来。
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
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @param x: an integer
     * @return: a ListNode 
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }

        ListNode dummyPre = new ListNode(0);
        ListNode dummyPost = new ListNode(0);
        ListNode pre = dummyPre;
        ListNode post = dummyPost;

        while (head != null) {
            if (head.val < x) {
                pre.next = head;
                pre = pre.next;
            } else {
                post.next = head;
                post = post.next;
            }
             head = head.next;
        }
        
        post.next = null;
        pre.next = dummyPost.next;
        
        return dummyPre.next;
    }
}


```