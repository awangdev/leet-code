E

Hackerrank. 注意处理null case. 找第一个比curr node 大的 next node.

```

/*
You’re given the pointer to the head node of a sorted doubly linked list and an integer to insert into the list. Create a node and insert it into the appropriate position in the list. The head node might be NULL to indicate that the list is empty.

Input Format 
You have to complete the Node* SortedInsert(Node* head, int data) method which takes two arguments - the head of the sorted, doubly linked list and the value to insert. You should NOT read any input from stdin/console.

Output Format 
Create a node with the given data and insert it into the given list, making sure that the new list is also sorted. Then return the head node of the updated list. Do NOT print anything to stdout/console.

Sample Input

NULL , data = 2 
NULL <-- 2 <--> 4 <--> 6 --> NULL , data = 5

Sample Output

NULL <-- 2 --> NULL
NULL <-- 2 <--> 4 <--> 5 <--> 6 --> NULL
Explanation 
1. We have an empty list, 2 is inserted. 
2. Data 5 is inserted such as list remains sorted.
*/

/*
  Insert Node at the end of a linked list 
  head pointer input could be NULL as well for empty list
  Node is defined as 
  class Node {
     int data;
     Node next;
     Node prev;
  }
*/
//If head and tail both null, just insert to head.next
//Else, insert based on data
Node SortedInsert(Node head,int data) {
     Node node = new Node();
     node.data = data;
    if (head == null && head.next == null) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        return head;
    }

    Node fast = head;
    while (fast.next != null && node.data > fast.next.data) {
        fast = fast.next;
    }
    if (fast.next == null) {
        fast.next = node;
        node.prev = fast;
        node.next = null;
    } else {
        Node next = fast.next;
        fast.next = node;
        node.prev = fast;
        node.next = next;
        next.prev = node;
    }
    return head;    
}

```