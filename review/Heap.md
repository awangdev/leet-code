 
 
 
## Heap (15)
**0. [Kth Smallest Element in a Sorted Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Element%20in%20a%20Sorted%20Matrix.java)**      Level: Medium      Tags: [Binary Search, Heap]
      
time: O(n + klogn)
space: O(n)

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

**1. [Meeting Rooms II.java](https://github.com/awangdev/LintCode/blob/master/Java/Meeting%20Rooms%20II.java)**      Level: Medium      Tags: [Greedy, Heap, PriorityQueue, Sort, Sweep Line]
      

给一串数字pair, 代表会议的开始/结束时间. 找同时又多少个会议发生(需要多少件房间)

#### PriorityQueue
- PriorityQueue + 一个Class来解决.Ｏ(nlogn)
- 跟 Number of Airpline in the sky是同一道题

#### 方法2: 尝试了一下用一个sorted Array + HashMap
也还行，但是handle edge的时候,HashMap 要小心，因为相同时间start和end的map key 就会重复了。



---

**2. [The Skyline Problem.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Skyline%20Problem.java)**      Level: Review      Tags: [Binary Indexed Tree, Divide and Conquer, Heap, PriorityQueue, Segment Tree, Sweep Line]
      

又叫做skyline. 用Sweep Line做的O(nLogN), 但是貌似还有很多做法: segement tree, hashheap, treeSet?

#### Sweep Line, Time O(nLogN), Space O(n)
- original reference http://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html?_sm_au_=isVmHvFmFs40TWRt
- 画图分析: 需要找到 non-overlaping height point at current index; also height needs to be different than prev height peek to be visible.
- 把所有点分出来， 每个点有index x, 再加上一个height.         
- 在这个list上排序，根据index和height. 注意用负数标记building start point height, 这样保证start在end 之前
- 用负数的height标记start: 在priority queue里面同一个x-pos比较 startPoint.height, endPoint.height 的时候, 因为end height是整数, 所以compare时会自动把start point放在end point前面
- 当然了, 如果两个 start point比较, 第二个point的负数超大的话(也就是height很高), 就会顺理compare return正数, 成章形成倒位
- 在processs时候用max-heap (reversed priorityqueue)，再iterate heightPoints 来存最大的height . 遇到peek,就是一个合理的解    
- heightQueue里面加一个0, 用来在结尾的时候做closure

#### Segment Tree
- 看了一些做法, segment tree写法很复杂, 估计在面试中难以用segment tree来写: https://www.cnblogs.com/tiezhibieek/p/5021202.html

#### HashHeap
- HashHeap template 可以考虑: https://www.jiuzhang.com/solution/building-outline/#tag-highlight-lang-java

Binary Indexed Tree?





---

**3. [Top K Frequent Words.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Words.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue, Trie]
      
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

**4. [Rearrange String k Distance Apart.java](https://github.com/awangdev/LintCode/blob/master/Java/Rearrange%20String%20k%20Distance%20Apart.java)**      Level: Hard      Tags: [Greedy, Hash Table, Heap]
      

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

**5. [HashHeap.java](https://github.com/awangdev/LintCode/blob/master/Java/HashHeap.java)**      Level: Hard      Tags: [HashHeap, Heap]
      

非题.是从九章找来的HashHeap implementation.



---

**6. [Trapping Rain Water II.java](https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water%20II.java)**      Level: Hard      Tags: [BFS, Heap, MinHeap, PriorityQueue]
      

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

**7. [Kth Largest Element in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element%20in%20an%20Array.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, MinHeap, PriorityQueue, Quick Sort]
      

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

**8. [Merge k Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20k%20Sorted%20Lists.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, Linked List, PriorityQueue]
      

给一个array of ListNode, 把所有node按照大小连成一条.

#### Priorityqueue
- Iterative, PQ来排列所有list的leading node.
- 记得k lists 需要是已经sort好的
- 时间：n*O(logk), where n = total node number, and PriorityQueue: logk, 
- Note:
- 1. 不要忘记customized priority需要一个customized new Comparator<T>()
- 2. Given list 里面也可能有null node, 不要忘记查.

#### Divide and Conquer
- always merge 2 list at a time
- 3 branches: 
- 1. start == end
- 2. start + 1 == end
- 3. or start + 1 < end (recursive and keep merging)
- T(k) = 2T(k/2) + O(mk), where m = longest list length
- time complexity: O(nklogk)
- TODO: write the recursive code.

#### Followup
- 如果k很大，一个机器上放不下所有的k list怎么办？ 
- 如果Merge起来的很长，一个机器上放不下怎么办？




---

**9. [Merge k Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20k%20Sorted%20Arrays.java)**      Level: Medium      Tags: [Heap, MinHeap, PriorityQueue]
      

Same as merge k sorted list, use priorityQueue

#### Priority Queue
- 由Merge k sorted list启发。用PriorityQueue,存那k个首发element
- PriorityQueue需要存储单位: 自己建一个Class Node 存val, x, y index.    
- 因为array里没有 'next' pointer，只能存x,y来推next element
- Not sure why `new PriorityQueue<>(Comparator.comparing(a -> a.val));` is slower



---

**10. [Heapify.java](https://github.com/awangdev/LintCode/blob/master/Java/Heapify.java)**      Level: Medium      Tags: [Heap, MinHeap]
      

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

**11. [Top K Frequent Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Elements.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue]
      
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

**12. [Ugly Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Ugly%20Number%20II.java)**      Level: Medium      Tags: [DP, Enumeration, Heap, Math, PriorityQueue]
      
time: O(n)
space: O(n)

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

**13. [Find Median from Data Stream.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Median%20from%20Data%20Stream.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap]
      

#### 原理
- 把Input stream想成向上的山坡. 山坡中间那点，自然就是median.
- 前半段，作为maxHeap,关注点是PriorityQueue的峰点，也就是实际上的median.   
- 后半段，作为minHeap,正常的PriorityQueue。 开头是最小的。

#### 注意
- 这里要首先定好, 哪一个queue是多存一个element的. 这里选maxHeap: maxHeap.size() == minHeap.size() + 1 || minHeap.size()
- 必须先维护maxHeap里面有个元素, 否则null了会在比较大小时出问题.



---

**14. [Sliding Window Median.java](https://github.com/awangdev/LintCode/blob/master/Java/Sliding%20Window%20Median.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap, Sliding Window]
      

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

