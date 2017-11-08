M

用Priorityqueue来排列所有list的leading node.

记得k lists 需要是已经sort好的。   

时间：n*O(logk)   
PriorityQueue: logk   

这个题目可以有好几个衍生：   
   比如，如果k很大，一个机器上放不下所有的k list怎么办？ 
   比如，如果Merge起来的很长，一个机器上放不下怎么办？

Note:
1. 不要忘记customized priority需要一个customized new Comparator<T>()
2. Given list 里面也可能有null node, 不要忘记查.

```

/*

Merge k sorted linked lists and return it as one sorted list.

Analyze and describe its complexity.


Example
Given lists:

[
  2->4->null,
  null,
  -1->null
],
return -1->2->4->null.

Tags Expand 
Divide and Conquer Linked List Priority Queue Heap Uber Google Twitter LinkedIn Airbnb Facebook


*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // Initialize the priority queue with customized comparator
        final PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
            public int compare (final ListNode a, final ListNode b) {
                return a.val - b.val;
            }
        });
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.offer(lists[i]);
            }
        }

        if (queue.isEmpty()) {
            return null;
        }
        // Append the priority queue with all items
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            if (node.next != null) {
                queue.offer(node.next);
            }
            head.next = node;
            head = head.next;
        }
        return dummy.next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


/*
    12.10.2015 recap
    Use queue to store the head of k lists. 
    First init with all heads.
    Because the ListNode always has a link to its next sibiling, so it's easy to add that sibling back to queue.
    time: m * Log(k)
*/
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {  
        if (lists == null || lists.size() == 0) {
            return null;
        }
        PriorityQueue<ListNode> queue = 
        new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b){
                return a.val - b.val;
            }
        });
        
        //populate queue with k lists' header
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                queue.offer(lists.get(i));
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (!queue.isEmpty()) {
            ListNode curr = queue.poll();
            node.next = curr;
            
            if (curr.next != null) {
                queue.offer(curr.next);
            }
             
            node = node.next;   
        }
        
        return dummy.next;
    }
}


```