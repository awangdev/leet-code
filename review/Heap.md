 
 
 
## Heap (22)
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

**1. [Rearrange String k Distance Apart.java](https://github.com/awangdev/LintCode/blob/master/Java/Rearrange%20String%20k%20Distance%20Apart.java)**      Level: Hard      Tags: [Greedy, Hash Table, Heap]
      
给一个string, 全是lowercase letter, 要求重新排列: 然后每个unique的character要有k distance apart.

跟Task Scheduler有点像, 只不过Task那道题里面还可以用其他方法求count, 这道题要求出排列结果

#### PriorityQueue + HashTable
- PriorityQueue排序+分布排列的一个经典用法.
- Count frequency and store in pq.
- Consume element of pq for k rounds, each time pick one element from queue.
- Exception: if k still has content but queue is consumed: cannot complete valid string, return "";
- space, O(n) extra space in sb, O(26) constant space with pq.
- time: O(n) to add all items



---

**2. [Ugly Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Ugly%20Number%20II.java)**      Level: Medium      Tags: [DP, Enumeration, Heap, Math, PriorityQueue]
      

#### DP
- curr index is based on previous calculation: the min of all 3 previous factors
- O(n)

#### PriorityQueue, DP
- 非常brutle的。
- 每次把dp[i-1]拿出来，不管三七二十一，分别乘以2,3,5. 出来的结果放进priority queue做比较。
- 最后时间是n*log(n*3)
- 注意：use long, use HashSet确保没有重复
- O(nlogn)




---

**3. [Trapping Rain Water II.java](https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water%20II.java)**      Level: Hard      Tags: [BFS, Heap, MinHeap, PriorityQueue]
      
给一个2Dmap, 每个position 有 height. 找Trapping water sum.


#### Min Heap
- 用PriorityQueue把选中的height排序,为走位, create class Cell (x,y, height).

##### 注意几个理论
- 1. 从matrix四周开始考虑，发现matrix能Hold住的水，取决于height低的block
- 2. 必须从外围开始考虑，因为水是被包裹在里面，外面至少需要现有一层
- 以上两点就促使我们用min-heap: 也就是natural order的PriorityQueue<Cell>.

##### Steps
- 1. process的时候，画个图也可以搞清楚: 就是四个方向都走走，用curr cell的高度减去周围cell的高度.
- 2. 若大于零，那么周围的cell就有积水: 因为cell已经是外围最低, 所以内部更低的, 一定有积水.
- 3. 每个visited的cell都要mark, avoid revisit
- 4. 根据4个方向的走位 `(mX, mY)` 创建新cell 加进queue里面: cell(mX, mY) 已经计算过积水后, 外围墙小时, `(mX, mY)`就会变成墙.
- 5. 因为做的是缩小一圈的新围墙, height = Math.max(cell.h, neighbor.h);
- 和trapping water I 想法一样。刚刚从外围，只是能加到跟外围cell高度一致的水平面。往里面，很可能cell高度变化。   
- 这里要附上curr cell 和 move-to cell的最大高度。

##### 为什么想到用Heap (min-heap - priorityQueue)
- 要找到bucket的最短板
- 每次需要最先处理最短的那条 (on top)

##### 为什么从外向里遍历
- 木桶理论, 包水, 是从外面包住里面
- 洋葱剥皮, 用完丢掉



---

**4. [Kth Largest Element in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element%20in%20an%20Array.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, MinHeap, PriorityQueue, Quick Sort]
      
kth largest in array

#### PriorityQueue, MinHeap
- Need to maintain k large elements, where the smallest will be compared and dropped if applicable: 
- Maintain k elements with min value: consider using minHeap
- add k base elements first
- Maintain MinHeap: only allow larger elements (which will squzze out the min value)
- Remove peek() of queue if over size
- O(nlogk)


#### Quick Sort
- 用Quick Sort 里面partion的一部分
- sort结束后是ascending的, 那么 n - k 就是第k大. 
- partion的结果是那个low, 去找 low==nums.size() - k， 也就是倒数第K个。    
- 没找到继续partion recursively.
- sort的过程是排一个从小到大的list. (同样的代码还可以好xth smallest，mid变成x就好)
- Steps:
- 每个iteration, 找一个pivot,然后从low,和high都和pivot作比较。    
- 找到一个low>pivot, high<pivot, 也就可以swap了。    
- 得到的low就是当下的partion point了
- Overall O(nlogN), average O(n) for this problem.



---

**5. [Kth Smallest Element in a Sorted Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Element%20in%20a%20Sorted%20Matrix.java)**      Level: Medium      Tags: [Binary Search, Heap]
      

给一个sorted matrix, 找kth smallest number (not distinct)

Related: `Kth Largest Element in an Array`

#### PriorityQueue
- 和Merge K sorted Array/ List 类似：使用PriorityQueue。
- 因为Array的element无法直接找到next,所以用一个class Node 存value, x,y positions.
- Initial O(n) time, also find k O(k), sort O(logn) => O(n + klogn)
- 变型: Kth Largest in N Arrays

#### Binary Search
- we know where the boundary is start/end are the min/max value. 
- locate the kth smallest item (x, y) by cutt off partition in binary fasion: 
- find mid-value, and count # of items < mid-value based on the ascending matrix
- O(nlogn)




---

**6. [[lint]. Merge k Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Merge%20k%20Sorted%20Arrays.java)**      Level: Medium      Tags: [Heap, MinHeap, PriorityQueue]
      

Same as merge k sorted list, use priorityQueue

#### Priority Queue
- 由Merge k sorted list启发。用PriorityQueue,存那k个首发element
- PriorityQueue需要存储单位: 自己建一个Class Node 存val, x, y index.    
- 因为array里没有 'next' pointer，只能存x,y来推next element
- Not sure why `new PriorityQueue<>(Comparator.comparing(a -> a.val));` is slower



---

**7. [[lint]. HashHeap.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20HashHeap.java)**      Level: Hard      Tags: [HashHeap, Heap, Lint]
      
非题.是从九章找来的HashHeap implementation.

#### HashHeap
- An efficient implementation of a priority queue. 
- The linear hash function monotonically maps keys to buckets, and each bucket is a heap
- https://xlinux.nist.gov/dads/HTML/hashheap.html



---

**8. [[lint]. Heapify.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Heapify.java)**      Level: Medium      Tags: [HashHeap, Heap, Lint, MinHeap]
      
Turn unsorted array into a min-heap array, where for each A[i], 

A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].

#### Heap
- Heap用的不多. 得用一下, 才好理解. 通常default 的PriorityQueue就是给了一个现成的min-heap:
- 所有后面的对应element都比curr element 小。
- Heapify里面的**siftdown**的部分:
- 只能从for(i = n/2-1 ~ 0)， 而不能从for(i = 0 ~ n/2 -1): 必须中间开花，向上跑的时候才能确保脚下是符合heap规则的

#### Heapify/SiftDown做了什么?
- 确保在heap datastructure里面curr node下面的两个孩子，以及下面所有的node都遵循一个规律
- 比如在这里，若是min-heap,就是后面的两孩子都要比自己大。若不是，就要swap。    

#### min-heap的判断规律:
- for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].
- siftdown时：在curr node和两个child里面小的比较。如果的确curr < child, 搞定，break while.   
- 但若curr 并不比child小，那么就要换位子，而且继续从child的位子往下面盘查。    



---

**9. [347. Top K Frequent Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/347.%20Top%20K%20Frequent%20Elements.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue]
      

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

**10. [253. Meeting Rooms II.java](https://github.com/awangdev/LintCode/blob/master/Java/253.%20Meeting%20Rooms%20II.java)**      Level: Medium      Tags: [Greedy, Heap, PriorityQueue, Sort, Sweep Line]
      

给一串数字pair, 代表会议的开始/结束时间. 找同时又多少个会议发生(需要多少件房间)

#### Method1: sort both start and end times
- Sort start times, and end times in 2 different arrays
- Loop over start time
    - when start[i] < end[endIndex], Count++, need more room
    - start[i] >= end[endIndex], done using some room, move to next end time, endIndex++ (like vacating a room)
- Note: we never decrese count because:
    - what ever count reaches, it is the max
    - since we keep moving endIndex, when start[i] >= end[endIndex], we will just reuse meeting room w/o count++
- time: O(nlogn)
- space: O(n)
- somehow, super fast, over 100%
- inspired by: https://leetcode.com/problems/meeting-rooms-ii/discuss/67855/Explanation-of-%22Super-Easy-Java-Solution-Beats-98.8%22-from-%40pinkfloyda

#### Method2: Sweep Line
- Use sweep line to process, track max count as max # of rooms needed
- 跟 Number of Airpline in the sky是同一道题
- time: O(nlogn)
- space: O(n)

#### Method3: 尝试了一下用一个sorted Array + HashMap
也还行，但是handle edge的时候,HashMap 要小心，因为相同时间start和end的map key 就会重复了。



---

**11. [1094. Car Pooling.java](https://github.com/awangdev/LintCode/blob/master/Java/1094.%20Car%20Pooling.java)**      Level: Medium      Tags: [Greedy, Heap, PriorityQueue, Sort]
      

#### Method1: bucket sort
- define the bucket by index: the total distance is fixed [0, 1000]
- +/- capacities for each pos and save into the bucket
- go over the bucket and see if the total cap goes over input capacity
- O(n), trips size
- space: O(1), bucket size 1000 is constant
- `IMPORTANT`: before using PQ to sort, consider bucket sort:
    - if the boundary set and seems resonable? i.e., max size = `1000`
    - is the sorted items index based?

#### Method2: Priority Queue, sort distnace
- Like meeting room, merge interval
- process items on same index



---

**12. [973. K Closest Points to Origin.java](https://github.com/awangdev/LintCode/blob/master/Java/973.%20K%20Closest%20Points%20to%20Origin.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, Sort]
      

#### PriorityQueue
- Create customized Point{} class
- Sort by distance
- Maintain queue size <= K

#### Divide and Conquer
- ?, select sort?



---

**13. [295. Find Median from Data Stream.java](https://github.com/awangdev/LintCode/blob/master/Java/295.%20Find%20Median%20from%20Data%20Stream.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap]
      

#### MaxHeap/MinHeap
- 把Input stream想成向上的山坡. 山坡中间那点，自然就是median.
- 前半段，作为maxHeap,关注点是PriorityQueue的峰点，也就是实际上的median.   
- 后半段，作为minHeap,正常的PriorityQueue。 开头是最小的。

#### 注意
- 这里要首先定好, 哪一个queue是多存一个element的. 这里选maxHeap: maxHeap.size() == minHeap.size() + 1 || minHeap.size()
- 必须先维护maxHeap里面有个元素, 否则null了会在比较大小时出问题.



---

**14. [239. Sliding Window Maximum.java](https://github.com/awangdev/LintCode/blob/master/Java/239.%20Sliding%20Window%20Maximum.java)**      Level: Hard      Tags: [Deque, Heap, Sliding Window]
      

#### Method1: Deque, Monotonous queue
- 维持monotonuous queue: `front is always at max` and the `tail end is min`. Always need to return the max end of queue.
- when adding new elements x: 
    - 1) start from small-end of the queue
    - 2) drop all smaller elements 
    - 3) append to the ending element that is larger than x.
    - This is to maintain a front->tail decreasing queue
- when sliding window: queue curr window 里面 最大的已经在max-end,  remove it if needed.
- 妙：用deque数据结构（实际上采用LinkedList的形式）来做一个`递减的queue`: better than using arraylist, since DeQueue(linked list) removes at O(1) cost
- 每次把小于当前node的，全部剔除，剩下的，自然就是:最大的>第二大的>第三大的...ETC.
- 我们只在乎最大值的存在；而任何小于当前（正要新就加进去的）值的，反正以后也成不了最大值，于是扔掉！
- Option1: sliding window template using right/left + while loop
    - 1) tailing the new number to max queue, if applicable
    - 2) process: record max
    - 3) contract/shrink left: remove top max if the topMax is the left-most val: rst[i - k + 1]
- Option2: same concept, but use index `i` to mark right, and `i - k + 1` to mark left.
- time: O(n), one pass
- space: O(k), store the deque


#### Method2: Heap
- can always build a `class Node{index, val}`; and sort them with PQ of size k
- time: O(nlogK)
- space: O(k)
- this is not linear time, not as good as method1



---

**15. [23. Merge k Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/23.%20Merge%20k%20Sorted%20Lists.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, Linked List, Merge Sort, PriorityQueue]
      

给一个array of ListNode, 把所有node按照大小连成一条.

#### Method1: Divide and Conquer, Merge sort
- By Definition, merge sort: divide the list into 2 parts
- recursively merge them together.
- time complexity: O(nlogk) divide by log(k) times, each recursive call can work on n nodes.
- space: O(logk) stacks 

#### Method2: Priorityqueue
- Iterative, PQ来排列所有list的leading node.
- Note: k lists need to be sorted (luckily, already given)
- 时间：n*O(logk), where n = total node number, and PriorityQueue: logk, 
- Note:
    - 1. 不要忘记customized priority需要一个customized new Comparator<T>()
    - 2. Given list 里面也可能有null node, 不要忘记查.

#### Followup
- 如果k很大，一个机器上放不下所有的k list怎么办？ 
- 如果Merge起来的很长，一个机器上放不下怎么办？




---

**16. [218. The Skyline Problem.java](https://github.com/awangdev/LintCode/blob/master/Java/218.%20The%20Skyline%20Problem.java)**      Level: Hard      Tags: [BIT, Divide and Conquer, HashHeap, Heap, PriorityQueue, Segment Tree, Sweep Line]
      

#### Sweep Line, Time O(nLogN), Space O(n)
- Analysis (inspired by, but not same solution: https://leetcode.com/problems/the-skyline-problem/solution/)
    - If there are just 2 overlapping building (totally 4 points on x-axis), here is the outline process:
    - Process x coordinate from left->right, one at a time.
        - 1) compare all `on-going heights` and find max, add as new outline point
        - 2) Handling building end: if the position ends a building, need to remove this height from the list of `on-going heights`
    - Requires 2 heap:
        1) sort by x coordinates
        2) `on-going heights`: maintain a pq of ongoing heights
- Steps: 
    - original reference http://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html?_sm_au_=isVmHvFmFs40TWRt
    - 画图分析: 需要找到 non-overlaping height point at current index; also height needs to be different than prev height peek to be visible.
    - `on-going heights`: 用max-heap (reversed priorityqueue)，再iterate heightPoints 来存最大的height
    - NOTE: heightQueue里面加一个0, 用来在结尾的时候做closure
- time: initial sort O(nlogn) + calculate n * O(nlogn) [maxQueue sort]
- space: O(n)

#### Segment Tree
- 看了一些做法, segment tree写法很复杂, 估计在面试中难以用segment tree来写: https://www.cnblogs.com/tiezhibieek/p/5021202.html

#### HashHeap
- HashHeap template 可以考虑: https://www.jiuzhang.com/solution/building-outline/#tag-highlight-lang-java

Binary Indexed Tree?





---

**17. [743. Network Delay Time.java](https://github.com/awangdev/LintCode/blob/master/Java/743.%20Network%20Delay%20Time.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Heap, PQ]
      

quesiton: sorting by travel delay/time will find better answer earlier?

#### Method1: BFS with PQ on graph
- `Dijkstras algorithm` is based on repeatedly making the candidate move that has the least distance travelled.
- PQ: pick close node to vist, and add siblings back to PQ
- avoid visited
- time: O(nLogn), visit n nodes, each time insert to heap takes O(logn) time
- space: O(n)

#### Method2: DFS with Sort
- 1) build graph map, 2) traverse map, 3) prioritize short delay nodes first
- use a map`<node, timeElapsed>` globally track dealy to nodes; compare all at the end



---

**18. [692. Top K Frequent Words.java](https://github.com/awangdev/LintCode/blob/master/Java/692.%20Top%20K%20Frequent%20Words.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue, Trie]
      

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

**19. [767. Reorganize String.java](https://github.com/awangdev/LintCode/blob/master/Java/767.%20Reorganize%20String.java)**      Level: Medium      Tags: [Greedy, Hash Table, Heap, Sort, String]
      


We want to exhaust largest population and merge like merging k list.
Problem: largest population may result in them being adjacent. How to resolve?

1) process and check at the end, or, 2) sanitize first and process assume correct input

#### Method1: K(k=2) seats apart problem (w/o sanitization)
- Aggregate map<char, count>, and sort the entry with priority queue.(Optionally, can use object `Letter {char c, int count}`)
- Naturally: we want to prioritize the largest population and exhaust it first, so we want to keep it in the a buffer queue
    - it is a queue, first in first out
    - monitor queue size k = 2, so that it holds off the just last-processed letter for 1 unit of time
    - the buffer then sends the last-process item to the main priority queue (pq will sort it again)
- Error handling: largest population may have extra letter
    - the main PQ has already exhausted
    - but the largest-population-letter will end up stuck in the buffer queue
    - it will never be picked up again so the final result sb will be shorter than orignal string: that is the error case
- Option0. Similar to `621. Task Scheduler`:
    - use a buffer to hold potential letter to add back, but NOT ADD BACK YET, until k slots have been filled.
- time: O(m), m = # of unique letters
- space: O(nmLogm), n = length, pq sorting requires mlogm, we will visit all n nodes.

#### Method2: HashMap<Num, # occurance>, Sort (Sanitize input)
- put all in map<char, count>
    - Sanitize the input: if certain popular char count is over (n + 1)/2, then it should fail right away, just return empty map.
    - Once the input is sanitized, when building results, we can be greedy and consume most popular char and then the rest 
- Int[2] can be used store char and count 
    - PriorityQueue can sort int[]. Okay to not specific length of int[] when defining pq.
    - Alternatively, can use a Letter {char c, int count} to represent





---

**20. [373. Find K Pairs with Smallest Sums.java](https://github.com/awangdev/LintCode/blob/master/Java/373.%20Find%20K%20Pairs%20with%20Smallest%20Sums.java)**      Level: Medium      Tags: [Heap, MaxHeap, MinHeap]
      

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

**21. [215. Kth Largest Element in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/215.%20Kth%20Largest%20Element%20in%20an%20Array.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, MinHeap, PriorityQueue, Quick Select, Quick Sort]
      

kth largest in array

#### PriorityQueue, MinHeap
- Use minHeap to maintain PQ of k size and return PQ.peek()
    - Maintain MinHeap: only allow larger elements (which will squzze out the min value)
    - Remove peek() of queue if over size
- O(nlogk)

#### Quick Select, Quick Sort
- 用Quick Sort 里面partion的一部分: sort结束后是ascending的.
  - kth largest = (n - k)th smallest
  - in partioned array (quick sort), the portion before pivot are less than pivot
  - that is, the `pivot value` is the divider: anything after pivot is larger than it.
  - after `swap(nums, low, pivot)`: index low has the (n-k)th smallest, if `low = n-k`
- Steps:
  - each iteration: pick pivot,然后从low,和high都和pivot作比较
  - Find `low>pivot, high<pivot` to swap
  - The new low is the next partion point
- Time: average O(n), worst case O(n^2)
- space: O(1) extra spaces besides recursive stack



---

