 
 
 
## Sliding Window (4)
**0. [Sliding Window Maximum.java](https://github.com/awangdev/LintCode/blob/master/Java/Sliding%20Window%20Maximum.java)**      Level: Hard      Tags: [Sliding Window]
      

妙：用deque数据结构（实际上采用LinkedList的形式）来做一个递减的queue.
每次把小于当前node的，全部剔除，剩下的，自然就是:最大的>第二大的>第三大的...ETC.
为啥可以不管不无地剔除？
因为我们只在乎最大值的存在；而任何小于当前（正要新就加进去的）值的，反正以后也成不了最大值，于是扔掉！


---

**1. [Sliding Window Median.java](https://github.com/awangdev/LintCode/blob/master/Java/Sliding%20Window%20Median.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap, Sliding Window]
      

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

**2. [Moving Average from Data Stream.java](https://github.com/awangdev/LintCode/blob/master/Java/Moving%20Average%20from%20Data%20Stream.java)**      Level: Easy      Tags: [Design, Queue, Sliding Window]
      

给一个interface, design一个structure, 能够计算moving window average.

#### Queue
- 读懂题目, 注意average 和 window 的处理.
- 简单的queue.size() comparison



---

**3. [Longest Substring with At Most Two Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20with%20At%20Most%20Two%20Distinct%20Characters.java)**      Level: Hard      Tags: [Hash Table, Sliding Window, String, Two Pointers]
      

如题.

#### Two Pointer + HashMap
- 原本想用 DP, 但是其实用 sliding window 的思想
- sliding window 的切割: 用hashmap 存 last occurrance of char index; 
- map.remove(c) 之后, 就等于彻底切掉了那一段; 那么 map.get(c) + 1 也就是新的 left window border



---

