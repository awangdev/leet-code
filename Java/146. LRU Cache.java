M
tags: Design, Linked List, Hash Table, Doubly Linked List
time: O(1)
space: O(1)

#### Double Linked List
- 用了一个特别的双向的ListNode，有了head和tail，这样就大大加快了速度
- 主要加快的就是那个‘更新排位’的过程，找到item hashmap O(1), 做减法换位也都是O(1)
- Overall O(1)
- 巧妙点
    - 1. head和tail特别巧妙：除掉头和尾，和加上头和尾，都O(1)
    - 2. remove node: 把node.pre和node.next 连起来, node就自然而然的断开不要了
- 一旦知道怎么解决了，就不是很特别，并不是难写的算法
- moveToHead()    
- insertHead()    
- remove()

#### Deque, less efficient
- Instead of building `Double Linked List`, utilize Java `Deque<E> queue = new LinkedList<>()`
- works but problem: `queue.remove(E)` is O(n)
- time: O(1) on average but much slower

```
/*
Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key 
if the key exists in the cache, otherwise return -1.

put(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently used item 
before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 ); //capacity 

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

*/
/*
It looks like we need to do some design, according to (http://www.cnblogs.com/yuzhangcmu/p/4113462.html). Though, the solution's concept is quite similar as attempt1.

1. The design uses HashMap, and 2-way LinkedList. Map<key, LinkNode>
2. Use two dummy node: head and tail. When add/remove nodes, we are add/remove nodes in between head and tail.
	So. head.next is our real 1st element
	andd tail.pre is our real last element

Note:
Be careful: when removing a node due to capacity issue, remember to remove both 1st node(head.next) and the corresponding entry in the map: map.remove(head.next.key)
*/

//Use double linked list to store value.
//Store key in hashmap<key, node> to find node easily
//Functions: insert in front, remove node, 
public class LRUCache {
    class DoubleLinkedListNode {
        int key, val;
        DoubleLinkedListNode next,prev;
        public DoubleLinkedListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    public int capacity;
    public HashMap<Integer, DoubleLinkedListNode> map;
    public DoubleLinkedListNode head, tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new DoubleLinkedListNode(-1, -1);
        this.tail = new DoubleLinkedListNode(-1, -1);
        head.next = tail;
        head.prev = tail;
        tail.next = head;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        DoubleLinkedListNode node = map.get(key);
        moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).val = value;
            moveToHead(map.get(key));
        } else {
            DoubleLinkedListNode node = new DoubleLinkedListNode(key, value);
            if (map.size() >= this.capacity) {
                DoubleLinkedListNode rm = tail.prev;
                remove(rm);
                map.remove(rm.key);
            }
            insertHead(node);
            map.put(key, node);
        }
    }

    public void moveToHead(DoubleLinkedListNode node) {
        remove(node);
        insertHead(node);
    }
    
    //Helper functions
    //Put node to front, where the latest item is at.
    public void insertHead(DoubleLinkedListNode node) {
        DoubleLinkedListNode next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    }

    // Find front and end, link them.
    public void remove(DoubleLinkedListNode node) {
        DoubleLinkedListNode front = node.prev;
        DoubleLinkedListNode end = node.next;
        front.next = end;
        end.prev = front;
    }  
}


// Deque Less Efficient: 
// Deque remove() loops over the list to remove, so it's inefficient
public class LRUCache {
    class Node {
        int key, val;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    public int capacity;
    public HashMap<Integer, Node> map;
    public Deque<Node> queue;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.queue = new LinkedList<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).val = value;
            moveToHead(map.get(key));
        } else {
            Node node = new Node(key, value);
            if (map.size() >= this.capacity) {
                Node rm = queue.pollLast();
                map.remove(rm.key);
            }
            queue.offerFirst(node);
            map.put(key, node);
        }
    }

    public void moveToHead(Node node) {
        queue.remove(node);
        queue.offerFirst(node);
    }
}

```