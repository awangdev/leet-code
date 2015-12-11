/*
31% Accepted
A linked list is given such that each node contains an additional random pointer 
which could point to any node in the list or null.

Return a deep copy of the list.

Example
Tags Expand 
Hash Table Linked List

*/	

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

/*
    Recap: 12.10.2015
    Iterative through the list. 
    Use a dummyHead and return dummyHead.next at the end.
    In each iteration, check if Head is already exist, or make a new one
    * use HashMap<oldNode, newNode> to mark if a node has been visited.
    deep copy the random node of head as well.
    
    border case: if head == null, return null
*/


public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        //creat node, used to link all nodes
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode node = dummy;
        RandomListNode newNode;
        
        //HashMap to mark node
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        
        while(head != null) {
            //process head. (we already know head!=null)
            if (!map.containsKey(head)) {
                map.put(head, new RandomListNode(head.label));
            }
            newNode = map.get(head);
            node.next = newNode;
            //process head.random
            if (head.random != null) {
                if(!map.containsKey(head.random)) {
                    map.put(head.random, new RandomListNode(head.random.label));
                }
                newNode = map.get(head.random);
                node.next.random = newNode;
            }
            node = node.next;
            head = head.next;
        }
        return dummy.next;
    }
}

/*
Thinking process:
1. Loop through the original list
2. Use a HashMap<old node, new node>. User the old node as a key and new node as value.
3. Doesn't matter of the order of node that being added into the hashMap.
    For example, node1 is added.
    node1.random, which is node 99, will be added into hashMap right after node1.
4. During the loop:
    If head exist in hashmap, get it; if not existed, create new node using head, add into hashMap
    If head.random exist, get it; if not, add a new node using head.random.

*/
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pre = dummy;
        RandomListNode newNode;
        while (head != null) {
            //Add new node 
            if (map.containsKey(head)) {
                newNode = map.get(head);
            } else {
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }
            //Add new node's random node
            if (head.random != null) {
                if (map.containsKey(head.random)) {
                    newNode.random = map.get(head.random);
                } else {
                    newNode.random = new RandomListNode(head.random.label);
                    map.put(head.random, newNode.random);
                }
            }
            //append and shift
            pre.next = newNode;
            pre = newNode;
            head = head.next;
        }
        return dummy.next;
    }
}

