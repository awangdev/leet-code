M
tags: Linked List, Divide and Conquer, Heap, PriorityQueue, Merge Sort
time: O(nlogk)
space: O(logk)

给一个array of ListNode, 把所有node按照大小连成一条.

#### Method1: Divide and Conquer, Merge sort
- By Definition, merge sort: divide the list into 2 parts
- recursively merge them together.
- time complexity: O(nlogk) divide by log(k) times, each recursive call can work on n nodes.
- space: O(logk) stacks 

#### Method2: Priorityqueue
- Iterative, PQ来排列所有list的leading node.
- Note: k lists need to be sorted (luckily, already given)
- 时间：n*O(logk), where n = total node number, and PriorityQueue: logk, 
- Note:
    - 1. 不要忘记customized priority需要一个customized new Comparator<T>()
    - 2. Given list 里面也可能有null node, 不要忘记查.

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

/*
Method1: Merge sort, faster
- Divide the and merge sub list, with start, end index; output listNode
- merge the 2 list node and output
- end case: start==end, return single node
- time: divide by log(k)times, each time merge n nodes => O(nlogk)
*/
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }
    
    private ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        
        int mid = start + (end - start) / 2;
        ListNode listA = merge(lists, start, mid);
        ListNode listB = merge(lists, mid + 1, end);
        
        ListNode node = new ListNode(0);
        ListNode dummy = node;

        while (listA != null && listB != null) {
            if (listA.val <= listB.val) {
                node.next = listA;
                listA = listA.next;
            } else {
                node.next = listB;
                listB = listB.next;
            }
            node = node.next;
        }
        
        if (listA == null) node.next = listB;
        if (listB == null) node.next = listA;
        
        return dummy.next;
    }
    
    private void next(ListNode node, ListNode target) {
        node.next = target;
        target = target.next;
    }
}


/*
Method2: PriorityQueue
- Put candidates into pq, to return small one first. sort: O(logk)
- skip null list once it is exhausted
- use quee to hold listNode

overall time: O(nlogk), n total nodes
space: O(k)
*/

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Initialize the priority queue with customized comparator
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparing(node -> node.val));
        for (ListNode node : lists) {
            if (node != null) queue.offer(node);
        }
        
        // Append the priority queue with all items
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            if (node.next != null) queue.offer(node.next);
            head.next = node; // link
            head = head.next;
        }
        return dummy.next;
    }
}




```