用Priorityqueue来排列所有list的头头。
非常正规的。

记得k lists 需要是已经sort好的。

时间：n*O(logk)
PriorityQueue: logk
这个题目可以有好几个衍生：

比如，如果k很大，一个机器上放不下所有的k list怎么办？ 
比如，如果Merge起来的很长，一个机器上放不下怎么办？
```
/*
22% 通过
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

样例
标签 Expand 
Linked List Divide and Conquer Heap

*/

/*
    12.10.2015 recap
    Use queue to store the head of k lists. 
    First init with all heads.
    Because the ListNode always has a link to its next sibiling, so it's easy to add that sibling back to queue.
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