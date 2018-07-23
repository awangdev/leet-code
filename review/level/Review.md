 
 
 
## Review (11)
**0. [Count of Smaller Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Number.java)**      Level: Review      Tags: [Binary Search, Segment Tree]
      

和平时的segment tree问题不同。 0 ～ n-1代表实际数字。是造一个based on real value的segment tree.
Modify时，把array里面的value带进去，找到特定的位子（leaf）,然后count+1. 

最终在SegmentTree leaf上面全是array里面实际的数字。

trick:   
在query前，给进去的start和end是： 0 ~ value-1.   
value-1就是说，找比自己所在range小1的range（那么自然而然地就不包括自己了），这样就找到了smaller number.   


[那么其他做过的SegmentTree是怎么样呢？]   
那些构成好的SegmentTree(找min,max,sum)也有一个Array。但是构成Tree时候，随Array的index而构架。   
也就是说，假如有Array[x,y,....]:在leaf,会有[0,0] with value = x. [1,1] with value = y. 

[但是这题]   
构成时，是用actual value.也就是比如Array[x,y,....]会产生leaf:[x,x]with value = ..; [y,y]with value =...    

其实很容易看穿:   
若给出一个固定的array构成 SegmentTree，那估计很简单：按照index从0~array.lengh，leaf上就是[0,0] with value = x.

若题目让构造一个空心SegmentTree, based on value 0 ~ n-1 (n <= 10000), 然后把一个Array的value modify 进去。   
这样八成是另外一种咯。



---

**1. [Heapify.java](https://github.com/awangdev/LintCode/blob/master/Java/Heapify.java)**      Level: Review      Tags: [Heap]
      

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
- siftdown时：在curr node和两个son里面小的比较。如果的确curr < son, 搞定，break while.   
- 但若curr 并不比son小，那么就要换位子，而且继续从son的位子往下面盘查。    



---

**2. [Kth Largest Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element.java)**      Level: Review      Tags: [Divide and Conquer, Heap, Quick Sort]
      

#### Quick Sort
- 用Quick Sort 里面partion的一部分。     
- partion的结果是那个low, 去找 low==nums.size() - k， 也就是倒数第K个。    
- 没找到继续partion recursively.
- sort的过程是排一个从小到大的list. (同样的代码还可以好xth smallest，mid变成x就好)
- Steps:
- 每个iteration, 找一个pivot,然后从low,和high都和pivot作比较。    
- 找到一个low>pivot, high<pivot, 也就可以swap了。    
- 得到的low就是当下的partion point了
- Overall O(nlogN), average O(n) for this problem.

#### Heap
- Learn how to build min-heap or max-heap to solve this problem.
- O(n + kLogN)




---

**3. [Maximum Subarray III.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray%20III.java)**      Level: Review      Tags: []
      


---

**4. [Valid Perfect Square.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Perfect%20Square.java)**      Level: Review      Tags: [Binary Search, Math]
      

Binary找sqrt. 基本 mid+1, mid-1 template.
注意: define index as long. 



---

**5. [Regular Expression Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Regular%20Expression%20Matching.java)**      Level: Review      Tags: [Backtracking, DP, Double Sequence DP, Sequence DP, String]
      



---

**6. [Minimum Window Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Window%20Substring.java)**      Level: Review      Tags: [Hash Table, String, Two Pointers]
      

H.
给String S, T. 找min window of S, 并且这个substring contains all chars of T. Complexity O(n)

#### Hash Table
基本思想: 用个char[]存string的frequency. 然后2pointer, end走到底, 不断validate.
符合的就process as result candidate.

HashMap的做法比char[]写起来要复杂一点, 但是更generic



---

**7. [Maximum Average Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20II.java)**      Level: Review      Tags: [Array, Binary Search, PreSum]
      

给int[] nums 和 window min size k. window size可以大于K. 找最大的连续数列average value.

- Binary Search的思想, 用在所要找的这个 average sum 上面. 大小是在[min, max]之中
- 找k的时候, 是>=k都可以, 巧用一个 min(preSum)的概念.
- 找k的时候, 画图, 可以看出来, 其实要的是 k window 里面的sum [x, i], 所以要用 sum[0, i] - sum[0, x]

需要仔细去读下面的notes.



---

**8. [Building Outline.java](https://github.com/awangdev/LintCode/blob/master/Java/Building%20Outline.java)**      Level: Review      Tags: [Binary Indexed Tree, Divide and Conquer, Heap, Segment Tree, Sweep Line]
      

又叫做skyline. 用Sweep Line做的O(nLogN), 但是貌似还有很多做法: segement tree, hashheap, treeSet?

#### 方法1: Sweep Line, Time O(nLogN), Space O(n)
original reference http://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html?_sm_au_=isVmHvFmFs40TWRt

sweep line:
- 把所有点分出来， 每个点有index x, 再加上一个height.         
- 在这个list上排序，根据index和height. 注意用负数标记building start point height, 这样保证start在end 之前
- 用负数的height标记start: 在priority queue里面同一个x-pos比较 startPoint.height, endPoint.height 的时候, 因为end height是整数, 所以compare时会自动把start point放在end point前面
- 当然了, 如果两个 start point比较, 第二个point的负数超大的话(也就是height很高), 就会顺理compare return正数, 成章形成倒位
- 在processs时候用max-heap (reversed priorityqueue)，再iterate heightPoints 来存最大的height . 遇到peek,就是一个合理的解    
- heightQueue里面加一个0, 用来在结尾的时候做closure

#### 方法2: Segment Tree
REVIEW

Binary Indexed Tree?

HashHeap?



---

**9. [Count of Smaller Numbers After Self.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Numbers%20After%20Self.java)**      Level: Review      Tags: [BST, Binary Indexed Tree, Binary Search, Divide and Conquer, Segment Tree]
      

给一串数字nums[], 求一个新数组result, where result[i] = # of smaller items on right of nums[i]

#### Binary Search
- sort and insert 进一个新list, 新的list是sorted
- 从末尾 i = n-1 遍历nums[]
- 每一次insert nums[i] 进list的位置, 就是# of smaller items on right side of nums[i]
- 每次记录下result[i]
- **问题**: 这里的binary search 是用 `end = list.size(); while(start<end){...}`做的, 可否换成用`end=list.size() - 1`?

#### Binary Indexed Tree
- TODO, have code

#### Segment Tree
- TODO, it seems too complicated for this problem.



---

**10. [Remove Invalid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Invalid%20Parentheses.java)**      Level: Review      Tags: [BFS, DFS, DP]
      

给一个string, 里面有括号和其他字符. 以最少刀 剪出 valid string, 求所有这样的string.

这个题目有多种解法, 最强就是O(n) space and time

#### DFS and reduce input string
- in dfs: remove the incorrect parentheses one at a time
- detect the incorrect parentheses by tracking/counting (similar to validation of the parentheses string): `if(count<0)`
- once detected, remove the char from middle of s, and dfs on the rest of the s that has not been tested yet.

##### Core concept: reverse test
- `if a parenthese string is valid, the reverse of it should also be valid`
- Test s with open='(', close=')' first; **reverse s**, and test it with open=')', close='('

##### Minor details
- only procceed to remove invalid parenthese when `count<0`, and also break && return dfs after the recursive calls.
- The above 2 facts eliminates all the redundant results.
- Reverse string before alternating open and close parentheses, so when returning final result, it will return the correct order.
- Open questions: how does it guarantee minimum removals?

##### Backtracking
- 如果用stringbuffer, 那么久不会每次create new string, 但是需要maintain这个string buffer, 就会backtracking

##### Complexity
- Seems to be O(n), but need to derive

#### BFS
TODO

#### DP



---

