/*
22% 通过
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

样例
标签 Expand 
Linked List Divide and Conquer Heap

Thinking process:
Add the head of all k list into a priority queue
Since the priority queue is auto-sorted, every time takes 1 node out and add to result
Need to memorize the comparator and how to use priority queue. (heap)
Note: This solution passes lintCode for 1 time, but failed other times. (timeout)
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
    private Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>(){
        public int compare(ListNode left, ListNode right) {
            if (left == null) {
                return 1;
            } else if (right == null){
                return -1;
            }
            return left.val - right.val;
        }
    };
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {  
        if (lists == null || lists.size() == 0) {
            return null;
        }
        Queue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), ListNodeComparator);
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                queue.add(lists.get(i));
            }
        }  
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (!queue.isEmpty()) {
            ListNode addon = queue.poll();
            node.next = addon;
            node = addon;
            if (addon.next != null) {
                queue.add(addon.next);
            }
        }
        return dummy.next;
    }
}

