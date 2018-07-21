H
1526021243
tags: Design, Linked List, Hash Table
time: O(1)
space: O(n)

Least Recently Used (LRU) cache: `get(key)`, `put(key, value)`

When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

#### Double Linked List
- 用了一个特别的双向的ListNode，有了`head`和`tail`，这样就大大加快了速度。     
- 主要加快的就是那个`更新排位`的过程，找到item hashmap O(1), 做减法换位也都是O(1)
- Overall O(1)

##### 巧妙点
- 1. head和tail特别巧妙：除掉头和尾，和加上头和尾，就都特别快。    
- 2. 用双向的pointer: pre和next, 当需要除掉任何一个node的时候，只要知道要除掉哪一个，     
- 直接把node.pre和node.next耐心连起来就好了，node就自然而然的断开不要了。     
- 一旦知道怎么解决了，就不是很特别，并不是难写的算法:    
- moveToHead()    
- insertHead()    
- remove()      

#### O(n) 检查重复
- timeout method, 天真的来了一个O(n) 的解法，结果果然timeout.     
- 一个map<key,value>存数值。一个queue<key>来存排位。     
- 每次有更新，就把最新的放在末尾；每次超过capaticity,就把大头干掉。很简单嘛，但是跑起来太久，失败了。     


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
            next = null;
            prev = null;
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
        if(!map.containsKey(key)) {
            return -1;
        }
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
    /*
        Put node to front, where the latest item is at.
     */
    public void insertHead(DoubleLinkedListNode node) {
        DoubleLinkedListNode next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    }

    /*
        Find front and end, link them.
     */
    public void remove(DoubleLinkedListNode node) {
        DoubleLinkedListNode front = node.prev;
        DoubleLinkedListNode end = node.next;
        front.next = end;
        end.prev = front;
    }  
}


/*
First Attempt: time exceeds

Thoughts:
Easy to implement: cache the least-added item. However, we are looking for the cache of 'leaset-recently-used item'.

'used' means the get() method:
whenever get, remove that key from the queue, and move that key to top.

Time Cost: O(n) on get(), set()


*/

public class LRUCache {
    private int cap;
    private HashMap<Integer, Integer> map;
    private LinkedList<Integer> queue;
    public LRUCache(int capacity) {
        this.cap = capacity;
        this.map = new HashMap<Integer, Integer>();
        this.queue = new LinkedList<Integer>();
    }
    
    public int get(int key) {
    	if (map.containsKey(key)) {
    		moveUsedToTop(key);
    		return map.get(key);
    	} else {
    		return -1;
    	}
    }
    
    public void set(int key, int value) {
    	if (map.containsKey(key)) {
    		moveUsedToTop(key);
    		map.put(key, value);
    	} else {
			if (queue.size() >= cap) {
        		map.remove(queue.poll());
        	}
	        queue.offer(key);
	        map.put(key, value);
    	}
        
    }
    //O(n)
    public void moveUsedToTop(int key) {
    	for (int i = 0; i < queue.size(); i++) {
			if (queue.get(i) == key) {
				queue.remove(i);
				queue.offer(key);
				break;
			}
		}
    }
}

//O(n) timeout
//Get: find target in arraylist, remove it, insert in front, return map.get(target)
//Set: if exist, find in arraylist, remove it, insert in front.
//     if not exist: check capacity: if full, remove last element and remove it from map.    Then, insert in front, and insert into map.
public class LRUCache {
    public ArrayList<Integer> list = new ArrayList<Integer>();
    public HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            int ind = list.indexOf(key);
            list.remove(ind);   
            list.add(0, key);
            return map.get(key);
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            int ind = list.indexOf(key);
            list.remove(ind);
            list.add(0, key);
            map.put(key, value);
        } else {
            list.add(0, key);
            map.put(key, value);
            if (list.size() > capacity) {
                int rm = list.get(list.size() - 1);
                list.remove(list.size() - 1);   
                map.remove(rm);
            }
        } 
       
    }
}

```