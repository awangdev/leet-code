 
 
 
## Segment Tree (17)
**0. [Interval Minimum Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Minimum%20Number.java)**      Level: Medium      Tags: [Binary Search, Divide and Conquer, Lint, Segment Tree]
      
给一串数字 int[], 然后一个query Interval[], 每个interval是 [start, end], 找query 区间里的最小值.

#### Segment Tree
- SegtmentTree, methods: Build, Query. 这题是在SegmentTreeNode里面存min.
- 类似的有存:max, sum, min



---

**1. [Count of Smaller Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Number.java)**      Level: Medium      Tags: [Binary Search, Lint, Segment Tree]
      
给一串数字, array size = n. 给一串query: 每个query是一个数, 目的找 count# items smaller than query element.

#### Segment Tree
- 和平时的segment tree问题不同。 [0 ～ n] 代表实际数字: based on real value的segment tree.
- Modify时，把array里面的value带进去，找到特定的位子, 然后count + 1. 
- 最终在SegmentTree leaf上面全是array里面实际的数字。
- node.count: 在node range里面的有多少个数字

##### right use of modify()
- build() 只是 empty segment tree, 没有property
- modify() 需要: 1. 找到left, update count+=1; 2. aggregate all parent when after returning
- 所以每一个modify 都是在整个path上所有的node上 + count

##### query trick
- 在query前，给进去的start和end是： 0 ~ value-1.   
- `value-1`: 找比自己所在range小1的range（那么自然而然地就不包括自己了），这样就找到了smaller number.   

##### About other basic segment tree setup
- [那么其他做过的SegmentTree是怎么样呢？]   
- 那些构成好的SegmentTree(找min,max,sum)也有一个Array。但是构成Tree时候，随Array的index而构架。   
- 也就是说，假如有Array[x,y,....]:在leaf,会有[0,0] with value = x. [1,1] with value = y. 
- [但是这题]   
- 构成时，是用actual value.也就是比如Array[x,y,....]会产生leaf:[x,x]with value = ..; [y,y]with value =...    
- 其实很容易看穿:   
- 若给出一个固定的array构成 SegmentTree，那估计很简单：按照index从0~array.lengh，leaf上就是[0,0] with value = x.
- 若题目让构造一个空心SegmentTree, `based on value 0 ~ n-1 (n <= 10000)`, 然后把一个Array的value modify 进去。   
- 这样八成是另外一种咯。



---

**2. [Segment Tree Modify.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Modify.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Lint, Segment Tree]
      
给一个segmentTree, node里面存max. 写一个modify function: modify(node, index, value).

#### Segment Tree, Divide and Conquer
- Recursively 在segment tree里面找index, update it with value.   
- 每个iteration，很可能（要么左手，要么右手）max就变了。所以每次都left.max and right.max compare一下
- 最后轮回到头顶，头顶一下包括头顶，就全部都是max了



---

**3. [Segment Tree Query II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query%20II.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Lint, Segment Tree]
      
#### Segment Tree
- 和 Segment Tree Query I 以及其他Segment Tree类似: 这个SegmentTreeNode return count of elements in range
- 这个题目考了validate input source：input 的start,end可能超出root[start,end]。   
- 那么第一步就要先clear一下: 1. 完全不在range就return 0. 2. 有range重合就规整到root的range.




---

**4. [Interval Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Sum.java)**      Level: Medium      Tags: [Binary Search, Lint, Segment Tree]
      
给一串数字 int[], 然后一个query Interval[], 每个interval是 [start, end], 找query 区间里的sum.

#### Segment Tree + Binary Search
- 其实是segment tree 每个node上面加个sum
- 记得Segment Tree methods: Build, Query
- Note: 存在SegmentTreeNode里面的是sum.  其他题目可能是min,max,count ... or something else.



---

**5. [Interval Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Sum%20II.java)**      Level: Hard      Tags: [Binary Search, Lint, Segment Tree]
      
SegmentTree大集合. Methods: `build, query, modify`. 不难。只是要都记得不犯错.

#### Segment Tree
- build: recursively build children based on index-mid and link to curr node
- query: recursively try to find `node.start == targetStart && node.end == targetEnd`. Compare with node.mid
- modify: recursively try to find `node.start == targetStart && node.end == targetEnd`; modify and recursively assign upper interval with updated interval property.



---

**6. [[lint]. Segment Tree Build II.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Segment%20Tree%20Build%20II.java)**      Level: Medium      Tags: [Binary Tree, Divide and Conquer, Lint, Segment Tree]
      
给一个array, 建造segment tree structure, 

每个treeNode 里面存这个range里的 max value, return root node.

#### Segemnt Tree
- 给的是Array. 注意找区间内的max, assign给区间. 其余和普通的segment tree build一样   
- 注意, segment tree是根据array index range 排位: 根据index in [0, array.length - 1]割开区间, break到底
- 最终start==end做结尾
- 这道题要trackmax, 那么在leaf node assign max=A[start] or A[end]
- 往上,parent一层的max:就是比较左右孩子,其实都是在两个sub-tree里面比较sub-tree的max。   

- Devide and Conquer
- 先分，找到left/right，比较max,在create current node,再append到当前node上面。
- 实际上是depth-first, 自底向上建立起的。



---

**7. [[lint]. Segment Tree Query.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Segment%20Tree%20Query.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Lint, Segment Tree]
      
给了segment Tree, node里面有Max value, 找[start,end]里面的max

#### Segment Tree, Divide and Conquer
- 根据[start,end]跟 mid of (root.start, root.end) 做比较:
	- 1) [start,end] on LEFT of mid
	- 2) [start, end] on RIGHT of mid
	- 3) [start, end] includes mid: break into 2 queries
		- query [leftNode, start, node.left.end]
		- query [rightNode, node.right.start, end]



---

**8. [[lint]. Segment Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Segment%20Tree%20Build.java)**      Level: Medium      Tags: [Binary Tree, Divide and Conquer, Lint, Segment Tree]
      
给一个区间[startIndex, endIndex], 建造segment tree structure, return root node.

#### Segment Tree
- Usage
  - which of these intervals contain a given point
  - which of these points are in a given interval
- Recursively build the binary tree
  - 左孩子：（A.left, (A.left+A.rigth)/2）   
  - 右孩子：（(A.left+A.rigth)/2＋1， A.right）   



---

**9. [327. Count of Range Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/327.%20Count%20of%20Range%20Sum.java)**      Level: Hard      Tags: [BIT, Divide and Conquer, Merge Sort, PreSum, Segment Tree]
      

TODO: Write the code + merge function

#### Divide and Conquer + PreSum + MergeSort
- https://leetcode.com/problems/count-of-range-sum/discuss/77990/Share-my-solution
- 1) build preSum[n+1]: then sum range [i,j]= preSum[j+1] - preSum[i]
- 2) Divide and Conquer: 
    - 先考虑[start, mid] range里的 ran sum result
    - 再考虑[mid, end] range里面的结果
    - 最后考虑[low, high]总体的结果
- NOTE: should write merge() function, but that is minor, just use `Arrays.sort(nums, start, end)`, OJ passed
- Every mergeSort() has a for loop => O(n log n)
- 如何 count range?
    - 这里比较特别的一个做法: 找一个 [low, mid]里面的i, mid 之后的preSum作比较 (解释源自: https://blog.csdn.net/qq508618087/article/details/51435944)
    - 即在右边数组找到两个边界, 设为`m, n`, 
    - 其中m是在右边数组中第一个使得`sum[m] - sum[i] >= lower`的位置, 
    - n是第一个使得`sum[n] - sum[i] > upper`的位置, 
    - 这样`n-m`就是与左边元素i所构成的位于`[lower, upper]`范围的区间个数. 

##### 神奇的重点: 为什么要 merge and sort
- 边界[lower, higher] 在 sorted array 好作比较, 一旦过界, 就可以停止计算, 减少不必要计算.
- 上面这个n,m的做法可行的前提: preSum[]里面前后两个 range[low, mid], [mid, high]已经sorted了
    - 也就是说, 在recursively mergeSort()的时候, 真的需要merge sorted 2 partitions
    - 也许会问: 能不能sort呢, sort不久打乱了顺序? 对,打乱的是preSum[]的顺序.
    - 但是不要紧: 很巧妙的, 分治的时候, 前半段/后半段 都在原顺序保留的情况下 分开process完了, 最后才merge
- 在做m,n 的range的时候, 原理如下, 比如preSum被分成这么两段: `[A,B,C]`, `[D,E,F]`
    - 每一个preSum value `A` 在跟 preSum[i] 作比较的时候 `A - preSum < lower`, 都是单一作比较, 不牵扯到 B, C
    - 因此, `[A, B, C]` 是否保留一开始 preSum的顺序在此时不重要
- 此时最重要的是, `[A,B,C]`以及排序好, 那么在于 `lower` boundary 作比较的时候, 一旦过界, 就可以停止计算(减少不必要的计算)


#### BIT
- TODO?

#### Segment Tree
- This segment tree approach(https://leetcode.com/problems/count-of-range-sum/discuss/77987/Java-SegmentTree-Solution-36ms) 
    - does not build segment tree based on given nums index
    - it is built on sorted preSum array.
- regular segment tree based on nums array does not work:
    - segment tree based on input array is good for: search/query by index
    - is NOT good at: given range sum/value, find indexes
    - why? segment tree is built based on index division, not by range value division.



---

**10. [308. Range Sum Query 2D - Mutable.java](https://github.com/awangdev/LintCode/blob/master/Java/308.%20Range%20Sum%20Query%202D%20-%20Mutable.java)**      Level: Hard      Tags: [Binary Indexed Tree, Segment Tree]
      

#### Segment Tree
- Same concept as turning an array into a binary segment tree,
    - HOWEVER, this is a 4-nary segmenet tree
- Reference. 307 Range Sum Query
- Range Query concept:
    - Using the input range, sum up everything in the range
    - sometimes the input range cover multiple segments, then dive into the segments (still use original range)
    - once we found a bounded segment (completely surrounded by input range), return segment value.
- Handling end stage, there are two approaches:
    - ApproachA: check at beginning of recursive call (i.e in `build()`, `updateNode()`, `rangeQuery()`).
        - pro: calling recursive function blindly; code is easy.
        - con: be really clear about termination state, and catch it.
    - ApproachB: check & come up with correct query condition before recursive call
        - pro: input to recursive function is assumed to be correct
        - con: sometimes really hard to write the conditions before recursive call; code is hard.



---

**11. [850. Rectangle Area II.java](https://github.com/awangdev/LintCode/blob/master/Java/850.%20Rectangle%20Area%20II.java)**      Level: Hard      Tags: [Segment Tree, Sweep Line]
      

#### Sweep Line + Merge Interval concept
- Inspired by: https://leetcode.com/problems/rectangle-area-ii/discuss/137941/Java-TreeMap-solution-inspired-by-Skyline-and-Meeting-Room
- First consider regular sweep line and realize problem: each vertical line has multiple block segments
    - Easy: take a list of vertical dots, and calculate the height diff
    - We can use a TreeMap with y-coordinate as key, so to `natural sort by y-coordinate`
- Trick: can NOT remove used y coordinate from map, because the rectangle may continue to expand to right side.
- apply simple equation to calc area: `(long)preY * (p.x - preX)`
- time: 
    - sort initial queue: O(nlogn)
    - process queue: O(n)
        - TreeMap insertion: O(logn)
        - TreeMap traversal: O(n)
    - overall, process queue can be O(n^2)
- space: O(n)

#### Sweep Line concept, bottom->top sweep
- https://leetcode.com/problems/rectangle-area-ii/discuss/137914/C%2B%2BPython-Discretization-and-O(NlogN)

#### Segment Tree
- TODO lol



---

**12. [493. Reverse Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/493.%20Reverse%20Pairs.java)**      Level: Medium      Tags: [BST, Binary Indexed Tree, Divide and Conquer, Merge Sort, Segment Tree]
      
给一串数字, count total reverse pair `nums[i] > 2*nums[j]`, i < j

This problem can be solved with Merge sort concept, BST, Segment Tree and Binary Indexed Tree. Good for learning/review.

#### Merge Sort, Divide and Conquer
- Using merge sort concept (NOT merge sort impl).
- One very simply desire: if we want to know # elements between [i, j] such that `nums[i] > 2*nums[j]`, it would be so great if array is **sorted**! 
    - If sorted,  fix index i, keep j++ for all `nums[i]/2.0 > nums[j]`
    - We CANNOT just sort entire array. WHY? Because it distrupts the value of curr index i, and the restriction is: `find matching elements on right side of curr index i`
    - BUT, what about just sort `right side of i`, and make sure the subproblem (i+1, end) is solved first?
- 灵感: use merge sort concept.divide and conquer [i ~ n] into 2 sections:
    - 1) solve subProblem(start,mid) & subProblem(mid+1, end). sort the sub array so that it can be used recursively at parent level.
    - 2) solve the curr pblem: for all [i, mid], check against [mid+1, end].
- Question1: does it cover all use cases?
    - First, subProblem(start,mid) & subProblem(mid+1, end) recursively solves its own range
    - Last, the only range is the current level problem check `[i, mid]` against its entire right side range: `[mid+1, end]`. DONE. all covered.
- Question2: what it is okay for `subProblem(start,mid) & subProblem(mid+1, end)` partially sort the array?
    - that is the goal: 1) we want the right side range to be sorted; 2) left range is sorted but it does not matter since we treat [start, mid] as 1 group
- use classic while loop `while(j<=e && nums[i]/2.0 > nums[j])` to count pairs


#### Segment tree
- TODO
- split the array into index-based segment tree, where each element is at leaf
- store min of range: use min to determine if certain range is needed for further query
- query for each element right side range (i + 1, end), where it recursively query&aggregate sub-range if meeting requirement `nums[i] > 2*nums[j]`
- only when target > subRange.min * 2: there are possible candidates, query further
- worst case O(n^2) when all tailing elements are meeting requirement.

#### BST
- TODO
- Build the BST based on node value. It will be not applicable if we search after entire tree is built (our goal is right range), so we need to build right elements, and search/count right after the elements is added
- Worst case is still O(n^2), if all added nodes are meeting requirement 
- search(tree, curr / 2.0)



#### O(n^2)
- check each one of them




---

**13. [315. Count of Smaller Numbers After Self.java](https://github.com/awangdev/LintCode/blob/master/Java/315.%20Count%20of%20Smaller%20Numbers%20After%20Self.java)**      Level: Hard      Tags: [BST, Binary Indexed Tree, Binary Search, Divide and Conquer, Segment Tree]
      

给一串数字nums[], 求一个新数组result, where result[i] = # of smaller items on right of nums[i]

#### Method1: Binary Search on processed list
- What if `the processed list is sorted`, so that I can BinarySeach for curr target?
    - process from end
    - binary search for `index to insert new element` in sorted ascending list
    - that index = # of smaller numbers; record it for final result
- time: O(nlogn)
- space: O(n)


#### Method2: Segment Tree based on actual value
- Segment Tree functions:
    - `Build`: construct segment tree based on min/max range: at leaf node, update count of numbers in range
    - `modify(SegmentTreeNode root, int value, int count)`: find leaft at with value, and update count for leaf & all parent nodes
    - `query(SegmentTreeNode root, int start, int end)`: return count # of numbers in range [start, end]
- Very similar to `Count of Smaller Number`, where segment tree is built on actual value!!
- IMPORTANT to drop processed number from left-hand-side: 
    - only find on remaining numbers. 
    - Utilize `modify(root, target, -1)` to erase element count & update the tree.
- time: `n * log(m)`, where m = Math.abs(max-min). log(m) is used to modify() the leaf element
- space: O(m)
- `Define the positive range`
    - negative nubmer division `rounds up towards 0` (this is a problem). (i.e. `(-2 - 1) / 2 = -1.5 = -1`), which causes range error.
    - We want the entire segment tree range to be ascending, and we want the mid = (start+end)/2 to round down.
    - Solution: 
        - build entire segment tree based on [min, max], where min must be >= 0. 
        - we can do this by adding Math.abs(min) onto both min/max, as well as +offset during accessing nums[i]



#### Method3: Binary Search Tree
- https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76580/9ms-short-Java-BST-solution-get-answer-when-building-BST
- Assume we have a BST, where each node has smallerCount and a val, for any new target, how to find smaller items?
    - 1) add the # of smaller count to current node
    - 2) compare:
        - if target < node.val, keep searching `countVisit(node.left, target)`
        - if target > node.val: 1) add currNode.smallerCount, 2) minus node.right.smallertCount (reduce double-counting), 3) plus `countVisit(node.right, target)`
    - remember to create left/right node before dfs countVisit into the sides.


#### Method4: Binary Indexed Tree



---

**14. [307. Range Sum Query - Mutable.java](https://github.com/awangdev/LintCode/blob/master/Java/307.%20Range%20Sum%20Query%20-%20Mutable.java)**      Level: Medium      Tags: [Binary Indexed Tree, Segment Tree]
      

#### Segment Tree, devide and conquer
- sample problem for segment tree
- build(), update(), rangeQuery()
    - build and update are standard
    - rangeQuery: handle the range split check
- Null leaf node handling: NO, ideally it will not encounter null leaf.
    - in update/rangeQuery: when final state (`start==end`) is reached, the recursive call ends
    - there is no way for any node to dive futher into null child.
- Range Query concept:
    - Using the input range, sum up everything in the range
    - sometimes the input range cover multiple segments, then dive into the segments (still use original range)
    - once we found a bounded segment (completely surrounded by input range), return segment value.



---

**15. [715. Range Module.java](https://github.com/awangdev/LintCode/blob/master/Java/715.%20Range%20Module.java)**      Level: Hard      Tags: [Segment Tree, TreeSet]
      

#### TreeSet
- start with considering array structure but operation are all O(n)
    - what if we can easily find range, and update
- TreeSet:
    - build a class `Interval {int start, end;}`
    - build a customized `compareTo` that sorts the interval by start at default, but sort by end if a.start==b.start
    - Query: TreeSet allow us to find element in O(logn)
    - Add Range: finding the starting pointing takes O(logn), but update can be worst to update O(n)
    - Remove Range: finding the starting pointing takes O(logn), but update can be worst to update O(n)



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

