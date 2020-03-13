M
tags: DFS, Linked List
time: O(n)
space: O(1)

#### DFS
- Depth-first:
    - 1) process curr.child, return tailChild
    - 2) connect tailChild.next = curr.next
- function: link(Node a, Node b);

```

/*
You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.

 

Example 1:

Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]
Explanation:

The multilevel linked list in the input is as follows:



After flattening the multilevel linked list it becomes:


Example 2:

Input: head = [1,2,null,3]
Output: [1,3,2]
Explanation:

The input multilevel linked list is as follows:

  1---2---NULL
  |
  3---NULL
Example 3:

Input: head = []
Output: []
 

How multilevel linked list is represented in test case:

We use the multilevel linked list from Example 1 above:

 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
The serialization of each level is as follows:

[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:

[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
Merging the serialization of each level and removing trailing nulls we obtain:

[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]

*/

/*
- Depth-first: process curr.child, return tailChild, connect tailChild.next = curr.next
- function: link(Node a, Node b);
*/
class Solution {
    public Node flatten(Node head) {
       
        Node node = head;
        while (node != null) {
            if (node.child != null) {
                Node next = node.next;
                Node child = node.child;
                Node childTail = dfs(child);
                node.child = null;
                link(node, child);
                link(childTail, next);
                node = next;
            } else {
                node = node.next;
            }
        }
       
        return head;
    }
   
    private void link(Node a, Node b) {
        if (a != null) a.next = b;
        if (b != null) b.prev = a;
    }
   
    private Node dfs(Node head) {
        Node node = flatten(head); // flatten returns head
        while (node.next != null) {
            node = node.next;
        }
        return node; // return tail;
    }
}



```