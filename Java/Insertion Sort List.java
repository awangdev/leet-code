想明白原理就好做了：
基本上就是正常的想法：已经有个sorted list, insert一个element进去。怎么做？
    while 里面每个元素都小于 curr, keep going
    一旦curr在某个点小了，加进去当下这个空隙。
这个题目也就是：把list里面每个元素都拿出来，scan and insert一遍！

```
/*
Sort a linked list using insertion sort.

Example
Given 1->3->2->0->null, return 0->1->2->3->null.

Tags Expand 
Sort Linked List
*/


/*
    Recap. 12.10.2015
    http://www.cnblogs.com/springfor/p/3862468.html

    Assumed we have a sorted list: now we pick each element and insert into that sorted list.
    This is insertion.
    
    If we are constly picking from (o ~ n) of this list itself, it becomes Insertion sort.
    
    1. make a sortedList, Now we need to have pre,curr,next for wapping, whenever we find the correct curr
    2. a pre node pointer [used to store the list each time to check where to insert curr], 
    3. curr is the node being check very round  
    4. next is simply used for wapping, not much other usage
*/
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: The head of linked list.
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode sortedListHead = new ListNode(0);//dummy head
        ListNode pre,curr,next;
        curr = head;
        
        while (curr != null) {// insert every single curr into sorted list
            next = curr.next; //prepare for insertion && swapping.
            pre = sortedListHead;//the list to scan
            while (pre.next != null && pre.next.val <= curr.val) {
                //as long as pre and its front are sorted ascending, keep going
                pre = pre.next;
            }
            //when pre.next == null , or curr is less than a node in pre.next, we want to insert curr before that pre.next node
            curr.next = pre.next;
            pre.next = curr;
            
            curr = next;//use the original next, instead of the new curr.next
        }//end while
        
        return sortedListHead.next;
    }
}



/*
Thoughts:
Look at head pointer, which is the current element we focus on.
If it's greater than the next pointer value, we move on. Use a while loop to check the entire
list every time with a new head.
If the head.val is less/equal than the next.val, we stop the at this next pointer, then cut it,
insert head.
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
     * @return: The head of linked list.
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        while (head != null) {
            ListNode node = dummy;
            while (node.next != null && node.next.val < head.val) {
                node = node.next;
            }
            ListNode temp = head.next;
            head.next = node.next;
            node.next = head;
            head = temp;
        }
        return dummy.next;
    }
}

```