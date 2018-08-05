 
 
 
## MinHeap (9)
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

**1. [Trapping Rain Water II.java](https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water%20II.java)**      Level: Hard      Tags: [BFS, Heap, MinHeap, PriorityQueue]
      

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

**2. [Kth Largest Element in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element%20in%20an%20Array.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, MinHeap, PriorityQueue, Quick Sort]
      

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

**3. [Merge k Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20k%20Sorted%20Arrays.java)**      Level: Medium      Tags: [Heap, MinHeap, PriorityQueue]
      

Same as merge k sorted list, use priorityQueue

#### Priority Queue
- 由Merge k sorted list启发。用PriorityQueue,存那k个首发element
- PriorityQueue需要存储单位: 自己建一个Class Node 存val, x, y index.    
- 因为array里没有 'next' pointer，只能存x,y来推next element
- Not sure why `new PriorityQueue<>(Comparator.comparing(a -> a.val));` is slower



---

**4. [Heapify.java](https://github.com/awangdev/LintCode/blob/master/Java/Heapify.java)**      Level: Medium      Tags: [Heap, MinHeap]
      

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

**5. [Top K Frequent Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Elements.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue]
      
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

**6. [Find Median from Data Stream.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Median%20from%20Data%20Stream.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap]
      

#### 原理
- 把Input stream想成向上的山坡. 山坡中间那点，自然就是median.
- 前半段，作为maxHeap,关注点是PriorityQueue的峰点，也就是实际上的median.   
- 后半段，作为minHeap,正常的PriorityQueue。 开头是最小的。

#### 注意
- 这里要首先定好, 哪一个queue是多存一个element的. 这里选maxHeap: maxHeap.size() == minHeap.size() + 1 || minHeap.size()
- 必须先维护maxHeap里面有个元素, 否则null了会在比较大小时出问题.



---

**7. [Sliding Window Median.java](https://github.com/awangdev/LintCode/blob/master/Java/Sliding%20Window%20Median.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap]
      

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

**8. [Design Search Autocomplete System.java](https://github.com/awangdev/LintCode/blob/master/Java/Design%20Search%20Autocomplete%20System.java)**      Level: Hard      Tags: [Design, Hash Table, MinHeap, PriorityQueue, Trie]
      
time: input: O(x), where x = possible words, constructor: O(mn) m = max length, n = # of words
space: O(n^2), n = # of possible words, n = # of trie levels; mainlay saving the `Map<S, freq>`

Description is long, but in short: 做 search auto complete. 

Best problem to review Trie (prefix search), Top K frequent elements (Hash Map), and MinHeap (PriorityQueue)

Easier to revisit https://leetcode.com/problems/design-search-autocomplete-system/description/

#### 思考方向
- 做text的search, 毋庸置疑要用Prefix tree, trie.

##### Find all possible word/leaf, 两种方案:
- Trie造好之后, 做prefix search, 然后DFS/BFS return all leaf items. [high runtime complexity]
- 在TrieNode里面存所有的possible words. [high space usage]
- in memory space 应该不是大问题, 所以我们可以选择 store all possible words

##### Given k words, find top k frequent items. 肯定用MinHeap, 但也有两种方案:
- Store MinHeap with TrieNode: 因为会不断搜索新此条, 同样的prefix (尤其是在higher level), 会被多次搜索.
- [complexity: need to update heaps across all visited TrieNodes once new sentence is completed]
- Compute MinHeap on the fly: 当然我们不能每次都来一个DFS不然会很慢, 所以就必须要store list of possible candidates in TrieNode.
- 这里就用到了`Top K Frequent Words` 里面的 `Map<String, freq>`, 这样O(m) 构建 min-heap其实很方便.

##### Train the system
- 每次 `#` 后 标记一个词条被add进search history. 那么就要 `insert it into trie`.
- 这一条在最后遇到`#`再做就可以了, 非常简洁

#### Trie, PriorityQueue, HashMap
- Trie Prefix Search + maintain top k frequent items
- 



---

