M
1533138747
tags: Linked List, Divide and Conquer, Heap, PriorityQueue

给一个array of ListNode, 把所有node按照大小连成一条.

#### Priorityqueue
- Iterative, PQ来排列所有list的leading node.
- 记得k lists 需要是已经sort好的
- 时间：n*O(logk), where n = total node number, and PriorityQueue: logk, 
- Note:
- 1. 不要忘记customized priority需要一个customized new Comparator<T>()
- 2. Given list 里面也可能有null node, 不要忘记查.

#### Divide and Conquer
- always merge 2 list at a time
- 3 branches: 
- 1. start == end
- 2. start + 1 == end
- 3. or start + 1 < end (recursive and keep merging)
- T(k) = 2T(k/2) + O(mk), where m = longest list length
- time complexity: O(nklogk)
- TODO: write the recursive code.

#### Followup
- 如果k很大，一个机器上放不下所有的k list怎么办？ 
- 如果Merge起来的很长，一个机器上放不下怎么办？


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

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6

Tags Expand

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
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparing(node -> node.val));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.offer(lists[i]);
            }
        }
        if (queue.isEmpty()) return null;
        
        // Append the priority queue with all items
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            if (node.next != null) {
                queue.offer(node.next);
            }
            head.next = node; // link
            head = head.next;
        }
        return dummy.next;
    }
}


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