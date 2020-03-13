 
 
 
## MaxHeap (5)
**0. [Sliding Window Median.java](https://github.com/awangdev/LintCode/blob/master/Java/Sliding%20Window%20Median.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap, Sliding Window]
      
Data Stream Median 的同理题目: 不只是不断增加的Sequence, 而且要remove item (保持一个window size)

#### MaxHeap, MinHeap
- Median还是用min-heap 和 max-heap. Time(logN)
- 加/减: prioirtyQueue, log(n)
- findMedian: O(1)
- 加一个数, 减一个数。
- 加减时看好，是从前面的maxheap里面抽，还是从后面的minHeap里面抽。
- 抽完balance一下

#### 注意
- 用maxHeap, minHeap时候, 习惯选择让maxHeap多一个数字:
- 左边的maxHeap总有 x+1或者x个数字
- 后边minHeap应该一直有x个数字



---

**1. [347. Top K Frequent Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/347.%20Top%20K%20Frequent%20Elements.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue]
      

给一串数字, 找到top k frequent element, 并且time complexity 要比nLogN要好

#### Method1: Bucket Sort. HashMap + bucket List[]
- Use HashMap to store <num, freq>
- Bucket `List<Integer>[]`: stores <count, list unique element with that count>
    - Size of the data structure will be uniqe item size.
    - The bucket[i] stores item at frequency i
- Simply loop from bucket.length -> 0, when bucket[i] not null, add to result.
- Solid O(n)


#### Method2: PriorityQueue, MinHeap
- Use regualr priorityQueue to sort by frequency ascendingly
- the queue.peek() record has lowest frequency, which is replacable
- Always only maintain k elements in the queue, so sorting is O(logk)
- IMPORTANT: remember to `rst.add(0, x)` for desired ordering
- time faster than maxHeap: O(nlogk)
- option1: just use `map<num, freq>`; option2: use `class Record {int num; int freq}`

#### MaxHeap Attempt. INCORRECT
- 题目有提醒: 必须beetter than O(nLog(n)).
- max heap approach stores all nodes: it is wrong
    - even though freq count size m < n, but it can be m == n. ALL unique. 
    - then it is O(nlogN) again.
- therefore, storing all items into pq is INCORRECT.



---

**2. [295. Find Median from Data Stream.java](https://github.com/awangdev/LintCode/blob/master/Java/295.%20Find%20Median%20from%20Data%20Stream.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap]
      

#### MaxHeap/MinHeap
- 把Input stream想成向上的山坡. 山坡中间那点，自然就是median.
- 前半段，作为maxHeap,关注点是PriorityQueue的峰点，也就是实际上的median.   
- 后半段，作为minHeap,正常的PriorityQueue。 开头是最小的。

#### 注意
- 这里要首先定好, 哪一个queue是多存一个element的. 这里选maxHeap: maxHeap.size() == minHeap.size() + 1 || minHeap.size()
- 必须先维护maxHeap里面有个元素, 否则null了会在比较大小时出问题.



---

**3. [692. Top K Frequent Words.java](https://github.com/awangdev/LintCode/blob/master/Java/692.%20Top%20K%20Frequent%20Words.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue, Trie]
      

给一串String. 找到top k frequent words.

#### Method1: Bucket Sort
- 1) Build frequency map, 2) use frequency map to build freq bucket
- Loop from largest bucket freq -> 0, and output.
- Time: Solid O(n)
- Space: O(n)

#### Method2: PriorityQueue - Min Heap
- O(n) space of map, O(nlogk) to build queue.
- limit minHeap queue size to k: add to queue if found suitable item; always reduce queue if size > k

#### Method3: PriorityQueue - Max Heap
- 用HashMap存frequency, 用ArrayList存lists of words
- create一个Node class, 然后用PriorityQueue.   
- PriorityQueue里面用到了 String.compareTo(another String).巧妙。
- time: PQ uses O(nlogn), overall O(nlogn)
- slower, because the maxHeap needs to add all candidates

#### Trie && MinHeap屌炸天   
- 可以做一下
- http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/


#### Deleted Attempt: HashMap + collections.sort()
- 用HashMap存frequency, 用ArrayList存lists of words。最后返回从尾部向前数的k个。   
- 注意排序时Collection.sort()的cost是O(nLogk)
- not efficient




---

**4. [373. Find K Pairs with Smallest Sums.java](https://github.com/awangdev/LintCode/blob/master/Java/373.%20Find%20K%20Pairs%20with%20Smallest%20Sums.java)**      Level: Medium      Tags: [Heap, MaxHeap, MinHeap]
      

#### Method1: MinHeap wiht k size
- This approach follows the pattern of finding min pair: 
  - 1) only need to store k pairs
  - 2) always start from min of A list and min of B list
  - 3) pre-build k pairs honoring A list, and then pick the min pair, and start swapping with min of list B 
- First attemp all first k pairs from nums1[i] against nums2[0] <=k : O(k)
- Use queue to pull min node and save results
- Use the nums1 val from the min node, pair up with nums2[j], add back to queue to sort
- overall runtime: O(klogk)
- space: O(k)

#### Method2: MaxHeap with k size
- Brutle: build all pairs time O(mn), sort with maxHeap pq with k size, and find top k
- overall time: O(mnLogK)
- space: O(k)



---

