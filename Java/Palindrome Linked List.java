E
1525645137
tags: Linked List, Two Pointers

#### Reverse Linked List
- Palindrome概念很简单, 但是要在Linkde List random access坐标, 是很难得: 所以需要把一半 ListNode 翻转
- reverse linked list: 遍历接开头
- 用快慢指正找到mid point
- Time O(n), 而且不需要用额外的空间(只是调换半个list的内部顺序), 所以空间O(1)

#### Previous Note
- Palindrome都是要两边回溯相等
- linkedlist不能reverse iterating， 那么就reverse the list, 从中间开花作比较。

```
/*
Implement a function to check if a linked list is a palindrome.

Example
Given 1->2->1, return true

Challenge
Could you do it in O(n) time and O(1) space?

Tags Expand 
Linked List
*/

/*
    Thoughts: 
    Reverse the 2nd half of the list, save it to right node
    Then compare left and right.
    THey should be the same.
    
    How do we know it's odd or even number of nodes?
        (   
        At the end, if F.next == null, even.
        If F == null, odd
        Well, need a global bool isEven variable
        - If odd: let = [0 , mid], right = [mid, end]
        - If even: left = [0,mid], right = [mid +1, end]
        )
    The odd/even actually does not matter: regardless which middle is returned, always reverse [mid.next, end]
        - p1 has 1 extra element, p2 will loop to null first. That's okay.
        - p1 and p2 size same, p2 will loop till end, null.
        That is, if somewhere they break, and p2 is not loopped to null yet, that means, the palindrome fails
    1. find mid.
    2. reverse 2nd half of the list.
    3. while loop to compare the node if they are all equal. if not false.
    
    border case: if head == null, or head.next == null, just true.
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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //Find middle
        ListNode mid = findMiddle(head);
        //Reverse and return right side
        ListNode rightNode = reverse(mid.next);
        mid.next = null;
        ListNode leftNode = head;
        while (leftNode != null && rightNode != null) {
            if (leftNode.val != rightNode.val) {
                return false;
            }
            leftNode = leftNode.next;
            rightNode = rightNode.next;
        }
        
        return true;
    }
    
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode reversedList = dummy;
        while (head != null) {
            //store head.next for iteration usage
            ListNode temp = head.next;
            //insert head into the reservedList, at starting spot.
            head.next = reversedList.next;
            reversedList.next = head;
            //Move to next node
            head = temp;
        }
        return dummy.next;
    } 
}




```