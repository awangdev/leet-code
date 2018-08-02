 
 
 
## MaxHeap (2)
**0. [Top K Frequent Words.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Words.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue, Trie]
      
time: O(nlogk)
space: O(n)

给一串String. 找到top k frequent words.

#### PriorityQueue - Min Heap
- O(n) space of map, O(nlogk) to build queue.
- limit minHeap queue size to k: add to queue if found suitable item; always reduce queue if size > k

#### PriorityQueue - Max Heap
- 用HashMap存frequency, 用ArrayList存lists of words
- create一个Node class, 然后用PriorityQueue.   
- PriorityQueue里面用到了 String.compareTo(another String).巧妙。
- time: PQ uses O(nlogn), overall O(nlogn)
- slower, because the maxHeap needs to add all candidates

#### Trie && MinHeap屌炸天   
- 可以做一下
- http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/

#### HashMap + collections.sort()
- 用HashMap存frequency, 用ArrayList存lists of words。最后返回从尾部向前数的k个。   
- 注意排序时Collection.sort()的cost是O(nLogk)
- not efficient




---

**1. [Top K Frequent Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Elements.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue]
      
time: O(n)
space: O(n)

给一串数字, 找到top k frequent element, 并且time complexity 要比nLogN要好

#### HashMap + bucket List[]
- Use HashMap to store <num, freq>
- Reverse mapping <count, list unique element with that count> in a `bucket = new List[n]`. 
- Size of the data structure will be m <= n
- The bucket[count] preserves order from end of the array.
- Simply loop over the reversed map, we can find the top k items.
- Solid O(n)

#### PriorityQueue, MinHeap
- Use regualr priorityQueue to sort by frequency ascendingly
- the queue.peek() record has lowest frequency, which is replacable
- Always only maintain k elements in the queue, so sorting is O(logk)
- IMPORTANT: remember to `rst.add(0, x)` for desired ordering
- time faster than maxHeap: O(nlogk)

#### PriorityQueue, MaxHeap
- 题目有提醒: 必须beetter than O(nLog(n)), 也就是说明要O(n)
- 首先想到就是PriorityQueue, 并且不能queue.offer on the fly
- 那么就先count, O(n), using HashMap
- 再priorityQueue, (mLog(m)), m是unique 数字的总量
- 最终find top k, O(k)
- Overall time: O(n) + O(mLogm) + O(k) => O(n), if m is small enough




---

