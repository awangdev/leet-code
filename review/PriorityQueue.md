 
 
 
## PriorityQueue (7)
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
- 这里已经给了 **sorted** intervals by start point.
- 直接找到可以insert newInterval的位子. Insert
- 然后loop to merge entire interval array
- 因为给的是个list, 所以方便`intervals.remove(i)`
- remove之前都会重新assgin `pre.end`, 确保被remove的node.end 被capture
- O(n) 

#### 另外
- 因为interval已经sort, 本想用Binary Search O(logn). 
- 但是找到interval insert position 最后 merge还是要用 O(n), 所以不必要 binary Search



---

**5. [Merge Intervals.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Intervals.java)**      Level: Medium      Tags: [Array, PriorityQueue, Sort, Sweep Line]
      

给一串int[Interval] (unsorted), 把所以Interval merge起来.

#### Sweep Line with Priority Queue
- O(nlogn) time (PriorityQueue), O(n) space     
- 扫描线+Count无敌手。注意start end把interval给合起来。   
- count==0的时候，就是每次start end双数抵消的时候，就应该是一个interval的开头/结尾。写个例子就知道了。   
- 记得怎么写comparator. New way: new PriorityQueue<>(Comparator.comparing(p -> p.val));
- 在 LeetCode里面，Sweep Line比方法2要快很多.

#### Sort Interval 
- Sort by interval.start之后，试着跑一遍，按照merge的需求，把需要merge的地方续好，然后减掉多余的interval就好。
- sort by Interval.start: `intervals.sort(Comparator.comparing(interval -> interval.start)); // O(nlogn)`
- Related example: Insert Interval
- 用两个相连的Interval: curr, next
- 如果 curr.end覆盖了 next.start: 需要merge. 那么比较一下 curr.end vs. next.end    
- 一旦merge, 需要remove被覆盖的 next interval: `list.remove(i+1)`
- 若没有重合，就继续iteration
- time O(nlogn), space O(1)

#### Sort Intervals and append end logically
- Sort intervals: O(nlogn), extra space O(n) when creating rst list
- 找到结尾 interval, 满足条件就可以save
- 如果不到return的条件, 就继续延伸 interval.end



---

**6. [Task Scheduler.java](https://github.com/awangdev/LintCode/blob/master/Java/Task%20Scheduler.java)**      Level: Medium      Tags: [Array, Enumeration, Greedy, PriorityQueue, Queue]
      

#### Array, count frequency, enumerate
- Enumerate to understand: 1. we can module the tasks in module/section; 2. Only need sum the intervals/slots, not return actual layout
- Perfect condition, all letters appear identical # times: just line them up separate in order.
- Real case: task appears different times
- 1. Place maxCount task as header followed with n slots: define (maxCount-1) sections
- 2. For tasks with less # than maxCount# can fill the (maxCount-1) sections; what about the tail section?
- 3. Any task with same maxTask#, of if prior sections all filled, will fill the tail section
- To count overall slots/intervals, come up with this equation:
- 1. Fixed sections: `(maxCount - 1) * (n + 1)`
- 2. Plus all repeating maxCount tasks: calculate by couting identical maxCount of them
- 3. Exception: if the first (max - 1) sections are all filled completely, and we still have extra task (ex: when n is not large enough), then just return tasks.length
- time O(1), space O(1)

#### PriorityQueue
- 正面去做: 
- summerize 每个task出现的次数, 然后qp sort Task object, count 大的靠前
- 起始每个section: k slots = n + 1
- 目标是穷尽 k, 或者 穷尽 pq (poll k times, but will save it back to queue if Task # > 0)
- 如果qp 真的穷尽, break, return count
- 不然, count + remain of k
- extra space O(x), time O(n) + constant time O(xlogx), where x = 26



---

