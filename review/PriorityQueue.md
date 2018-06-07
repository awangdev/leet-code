 
 
 
## PriorityQueue (5)
**0. [Top K Frequent Words.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Words.java)**      Level: Medium      Tags: [Hash Table, Heap, PriorityQueue, Trie]
      

#### PriorityQueue
- 用HashMap存frequency, 用ArrayList存lists of words
- create一个Node class, 然后用PriorityQueue.   
- PriorityQueue里面用到了 String.compareTo(another String).巧妙。
- time: PQ uses O(nlogn), overall O(nlogn)

#### Just HashMap + collections.sort()
- 用HashMap存frequency, 用ArrayList存lists of words。最后返回从尾部向前数的k个。   
- 注意排序时Collection.sort()的cost是O(nLogk)
- not efficient


#### Trie && MinHeap屌炸天   
- 可以做一下
- http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/



---

**1. [Merge k Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20k%20Sorted%20Lists.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, Linked List, PriorityQueue]
      

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

**2. [Merge k Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20k%20Sorted%20Arrays.java)**      Level: Medium      Tags: [Heap, PriorityQueue]
      

#### Priority Queue
- 由Merge k sorted list启发。用PriorityQueue,存那k个首发element
- PriorityQueue需要存储单位: 自己建一个Class Node 存val, x, y index.    
- 因为array里没有 'next' pointer，只能存x,y来推next element



---

**3. [Top K Frequent Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Elements.java)**      Level: Medium      Tags: [Hash Table, Heap, PriorityQueue]
      

给一串数字, 找到top k frequent element, 并且time complexity 要比nLogN要好

#### PriorityQueue
- 题目有提醒: 必须beetter than O(nLog(n)), 也就是说明要O(n)
- 首先想到就是PriorityQueue, 并且不能queue.offer on the fly
- 那么就先count, O(n), using HashMap
- 再priorityQueue, (mLog(m)), m是unique 数字的总量
- 最终find top k, O(k)
- Overall time: O(n) + O(mLogm) + O(k) => O(n), if m is small enough



---

**4. [Insert Interval.java](https://github.com/awangdev/LintCode/blob/master/Java/Insert%20Interval.java)**      Level: Hard      Tags: [Array, PriorityQueue, Sort]
      

#### Sweep Line
- Interval 拆点，PriorityQueue排点
- Merge时用count==0作判断点
- 注意, 一定要compare curr `p.x == queue.peek().x` 确保重合的点全部被process: `count+=p.x`
- PriorityQueue: O(logN). 扫n点, 总共：O(nLogn)


#### Basic Implementation
- 这里已经给了sorted intervals by start point.
- 直接找到可以insert newInterval的位子. Insert
- 然后loop to merge entire interval array
- 因为给的是个list, 所以方便`intervals.remove(i)`
- remove之前都会重新assgin `pre.end`, 确保被remove的node.end 被capture
- O(n) 

#### 另外
- 因为interval已经sort, 本想用Binary Search O(logn). 
- 但是找到interval insert position 最后 merge还是要用 O(n), 所以不必要 binary Search



---

