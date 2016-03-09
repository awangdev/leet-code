M

Basic Implementation, 其中用了一下HashMap:  

遍历head.next .... null.    
每一步都check map里面有没有head。没有？加上。    
每一步都check map里面有没有head.random。没有？加上。

```
/*

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Hide Company Tags Microsoft Uber
Hide Tags Hash Table Linked List
Hide Similar Problems (M) Clone Graph

LeetCode: Hard
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
        RandomListNode node = new RandomListNode(0);
        RandomListNode dummy = node;
        
        //HashMap to mark node
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        
        while(head != null) {
            //process head. (we already know head!=null)
            if (!map.containsKey(head)) {
                map.put(head, new RandomListNode(head.label));
            }
            node.next = map.get(head);
            //process head.random
            if (head.random != null) {
                if(!map.containsKey(head.random)) {
                    map.put(head.random, new RandomListNode(head.random.label));
                }
                node.next.random = map.get(head.random);
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

//Same soluton as above, but split populating map && deep copy, which is not as efficient as above
//Save all possible nodes into HashMap<oldNodeAddress, newNodeAddress>
 //Deep copy the list, before adding any node, check map
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        //Populate map
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode node = head;
        RandomListNode newNode;
        while(node != null) {
            if (!map.containsKey(node)) {
                newNode = new RandomListNode(node.label);
                map.put(node, newNode);
            }
            if (node.random != null && !map.containsKey(node.random)) {
                newNode = new RandomListNode(node.random.label);
                map.put(node.random, newNode);
            }
            node = node.next;
        }
        //Deep copy
        node = head;
        newNode = new RandomListNode(0);
        RandomListNode dummy = newNode;
        while (node != null) {
            newNode.next = map.get(node);
            if (node.random != null)
                newNode.next.random = map.get(node.random);
            newNode = newNode.next;
            
            node = node.next;
        }
        
        return dummy.next;
    }
}

```