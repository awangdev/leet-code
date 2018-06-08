H
tags: Design, Hash Table

#### Hash Table
- 具体看thoughts, 几种不同的方式使用map
- `regular object map`: map of <key, item>, where `item : {int val; int count}`
- `frequency list map`: map of <frequency count, List<item>>, where the list preserves `recency`
- `item location in frequency map`: map of <key, int location index in list>:
- index relative to the item in a particular list, not tracking which list here
- Track constant capacity, and minimum frequency
- Every get(): update all 3 data structures
- Every put(): update all 3 data structures, with optional removal (if over capacity)
- TODO: code it up

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

```