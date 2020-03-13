H
tags: Design, Doubly Linked List
time: O(1)
space: O(n)

#### Doubly Linked List
- IMPORTANT: the problem aims to put keys of same frequency in same node! This affects the design of node
- Main a class `Node {keySet, count, last/next pointers}`
- Each operation: 
  - 1) finds target node and extract the key
  - 2) calculate: count +/- 1
  - 3) find new spot to store the key (prior positions or later positions)
- Be careful when handling the cases in inc() and dec()

```
/*
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".

*/

class AllOne {
    class Node {
        Node last, next;
        Set<String> keys = new HashSet<>();
        int count;
        public Node(int count, String key) {
            this.count = count;
            this.keys.add(key);
        }
    }
    Map<String, Node> map;
    Node head, tail; // Doubly Linked List
    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
        head = new Node(-1, "");
        tail = new Node(-1, "");
        head.next = tail;
        tail.last = head;
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        // Find node to process; use head if not exist
        Node node = map.getOrDefault(key, head);
        int count = removeKey(key) + 1;
        
        // Insert new node or populate existing node
        if (node.next.count != count) insert(node, node.next, new Node(count, key));
        else node.next.keys.add(key);
        map.put(key, node.next);
        
        // Clean up
        if (node.keys.isEmpty()) remove(node);
    }
    
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!map.containsKey(key)) return;
        
        Node node = map.get(key);
        int count = removeKey(key) - 1;
        if (count == 0) { // reduce to 0, delete
            map.remove(key);
        } else { // Insert new node or populate existing node
            if (node.last.count != count) insert(node.last, node, new Node(count, key));
            else node.last.keys.add(key);
            map.put(key, node.last);
        }
        
        if (node.keys.isEmpty()) remove(node);
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (map.isEmpty()) return "";
        return tail.last.keys.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (map.isEmpty()) return "";
        return head.next.keys.iterator().next();
    }
    

    // Helper Functions
        
    private void remove(Node node) {
        node.last.next = node.next;
        node.next.last = node.last;
    }
    
    private int removeKey(String key) {
        if (!map.containsKey(key)) return 0;
        Node node = map.get(key);
        node.keys.remove(key);
        return node.count;
    }
    
    private void insert(Node last, Node next, Node newNode) {
        newNode.next = next;
        next.last = newNode;
        last.next = newNode;
        newNode.last = last;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
```