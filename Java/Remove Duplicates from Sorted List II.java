M
1526353034
tags: Linked List

从Linked list 里面摘掉重复元素: 只要重复过, 全部都删掉; 重复出现过得元素一个不留.

#### Linked List
- sorted list, 重复元素都在一起
- 运用 dummyHead: 如果要去掉所有重复元素, 就要有个dummyHead作为局外人在开头牵线
- 只要发现一个 node.val == node.next.val, 就记下这个duplicated val, move forward, 过掉所有重复过的元素
- 思想:
- 用第二个 inner while loop, 把所有的重复元素都处理干净, 然后再move forward
- 优点: outter while loop 不需要考虑太多case, 在inner loop 都把主要的business logic 解决了.

##### 注意DummyHead 的使用
- 当我们有了DummyHead 作为Linked List 的局外线头, 其实可以选择每次遇到duplicate, 就把更加后面的元素 强行assign 给 dummyHead.next 
- 下面还尝试过一种做法: 但是需要考虑的edge case 太多了: 不断移动node, 知道不重复, assign prev.next = node. 
- 这样的做法比较直白, 但是需要考虑很多edge case, 而且并没有很好利用到 dummy head, 注意规避.

##### Previous Note
- 斩草除根。
- 多个node，check node.next ?= node.next.next


```
/*
26% Accepted
Given a sorted linked list, delete all nodes that have duplicate numbers, 
leaving only distinct numbers from the original list.

Example
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

*/


/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

/**

Thgouths:
- Create a dummyHead
- User a pointer node to run through the list
- Similar to Remove Duplicates from Sorted List I, but checking next and next.next
- If there is a match on head.next && head.next.next, delete head.next node and link to next ones until a different node appear
- return dummyHead.next
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }   
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode node = dummyHead; // node is the leading dummy head, node.next is the head
    
        while (node.next != null && node.next.next != null) {
            if (node.next.val == node.next.next.val) {
                int duplicatedVal = node.next.val;
                // When node.next.val == duplicatedVal, remove all of them, until a new unique node appears.
                while (node.next != null && node.next.val == duplicatedVal) {
                    node.next = node.next.next;
                }
            } else {
                node = node.next;
            }
        }
        
        return dummyHead.next;
    }
}


/**
直白做法, 太繁琐, 许多edgecase, 没有好好利用dummy head, 不可取.
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = new ListNode(-1);
        ListNode dummy = prev;
        ListNode node = head;
        int val = node.next.val;
        while (node != null) {
            // Skip duplicate until a new element
            while (node != null && node.val == val) {
                node = node.next;
            }
            
            if (node != null) {
                // Delect if new node is also duplicate, if so, move forward
                if (node.next != null && node.val == node.next.val) {
                    val = node.val;
                    node = node.next;
                } else {
                    // Attach new node, and move forward
                    prev.next = node;
                    prev = prev.next;
                    node = node.next;
                    // Re-assign val = node.next.val.
                    if (node != null && node.next != null) {
                        val = node.next.val;
                    } else {
                    // Reach end of list, end loop
                        prev.next = node;
                        prev = prev.next;
                        break;
                    }
                }
            }
        }
        
        // Prev.next always catches the last node, need to clean up the tail
        if (prev != null) {
            prev.next = null;
        }
        
        return dummy.next;
    }
}

```