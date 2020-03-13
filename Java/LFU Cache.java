H
tags: Design, Hash Table

#### Hash Table
- 具体看thoughts, 几种不同的方式使用map
- `regular object map`: map of <key, item>, where `item : {int val; int count}`
- Use a Map<frequency count, doubly-linked node> to track the frequency
- Track constant capacity, and minimum frequency
- Every get(): update all frequency map as well
- Every put(): update all frequency map as well, with optional removal (if over capacity)

- Original post: http://www.cnblogs.com/grandyang/p/6258459.html
- TODO: one doubly linked list might be good enough to replace below:
- `frequency list map`: map of <frequency count, List<item>>, where the list preserves `recency`
- `item location in frequency map`: map of <key, int location index in list>:
- index relative to the item in a particular list, not tracking which list here

```
/*
Design and implement a data structure for Least Frequently Used (LFU) cache. 
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key 
if the key exists in the cache, otherwise return -1.

put(key, value) - Set or insert the value if the key is not already present. 
When the cache reaches its capacity, it should invalidate the least frequently used item 
before inserting a new item. For the purpose of this problem, 
when there is a tie (i.e., two or more keys that have the same frequency), 
the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 ); // capacity

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

*/


/*
Goal: 
get(key): value(+ int), -1
put(key, val).
if at cap, remove least frequent (smallest count); if count equal, remove old key
option1. pq, to store sort the order (frequency, recency). O(logn) for insertion. NOT OKAY
option2:
- hashmap to find value by key quickly,
- frequency map to track <frequency #, list of items>
- freqPosition map to track <key, item position in frequency list>
- int minFrequency to track the lowest frequency (to remove when reached capacity)
- constant capacity

Option2 gives O(1) get(); put() avg O(1)

Get:
Find key from map, retrieve existing frequency and find it on freqMap, move the item to a new frequency++ on freqMap, update freqPosition

Put:
If exist, simply update the value for the key, return.
If not exist:
- (optional)if over capacity: find freqMap[minFreq]'s least frequent item, remove item.key from map, remove item.key freqPosition map, remove the item from freqMap (first item in the list)
- it's new item with freq==1, add to hashmap, add to frequency map, and add the freq list position to freqPosition.

*/

class LFUCache {
    class Node {
        int key, value, count;
        Node next, prev;
        public Node(int key, int value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
        }
    }
    int minFreq, capacity;
    Map<Integer, Node> map;
    // INCORRECT,  Use doubly-linked list to replace below structures
    Map<Integer, List<Node>> freqMap; // list: [head:oldest]
    Map<Integer, Integer> freqPosMap; //TODO: 

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.map = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.freqPosMap = new HashMap<>();
    }

    public int get(int key) {
        System.out.println("start get key:" + key);
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        
        // update freqMap
        freqMap.get(node.count).remove((int)(freqPosMap.get(key)));
        node.count += 1;
        if (!freqMap.containsKey(node.count)) {
            freqMap.put(node.count, new ArrayList<>());
        }
        freqMap.get(node.count).add(node);
        
        // update freqPosMap:
        freqPosMap.put(key, freqMap.get(node.count).size() - 1);

        // update minFreq
        if (freqMap.get(minFreq).size() == 0) minFreq++;
        //minFreq = Math.min(minFreq, node.count);
        
        System.out.println("after get key:" + key);
        printmap();
        
        return node.value;
    }

    public void put(int key, int value) {
        System.out.println("start put key:" + key + " value:" + value);
        if (capacity <= 0) {
            return;
        }
        if (map.containsKey(key)) {
            map.get(key).value = value;
            return;
        }
        // clean up the queue
        if (map.size() >= capacity) {
            Node node = freqMap.get(minFreq).get(0);
            map.remove(node.key);
            freqMap.get(minFreq).remove(0);
            freqPosMap.remove(node.key);
        }

        Node newNode = new Node(key, value, 1);
        map.put(key, newNode);
        if (!freqMap.containsKey(newNode.count)) {
            freqMap.put(newNode.count, new ArrayList<>());
        }
        freqMap.get(newNode.count).add(newNode);
        freqPosMap.put(key, freqMap.get(newNode.count).size() - 1);
        minFreq = 1;
        System.out.println("after put key:" + key + " value:" + value);
        printmap();
    }
    
    private void printmap() {
        System.out.println("map");
        for (Node node : map.values()) {
            System.out.println("key: " + node.key + " value:" + node.value);
        }
        System.out.println("freqMap");
        for (Map.Entry<Integer, List<Node>> entry: freqMap.entrySet()) {
            System.out.println(entry.getKey() + " list size:" + entry.getValue().size());
        }
        System.out.println("freqPosmap");
        for (Map.Entry<Integer, Integer> entry: freqPosMap.entrySet()) {
            System.out.println("key:" + entry.getKey() + " pos: " + entry.getValue());
        }
        System.out.println("min freq list");
        for (Node node : freqMap.get(minFreq)){
            System.out.print(node.key + "->");
        }
        System.out.println();
        System.out.println();
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

```