 
 
 
## Segment Tree (12)
**0. [The Skyline Problem.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Skyline%20Problem.java)**      Level: Review      Tags: [Binary Indexed Tree, Divide and Conquer, Heap, PriorityQueue, Segment Tree, Sweep Line]
      

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

**1. [Segment Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Build.java)**      Level: Medium      Tags: [Binary Tree, Divide and Conquer, Lint, Segment Tree]
      

给一个区间[startIndex, endIndex], 建造segment tree structure, return root node.

#### Segment Tree definition
- Recursively build the binary tree
- 左孩子：（A.left, (A.left+A.rigth)/2）   
- 右孩子：（(A.left+A.rigth)/2＋1， A.right）   



---

**2. [Segment Tree Build II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Build%20II.java)**      Level: Medium      Tags: [Binary Tree, Divide and Conquer, Lint, Segment Tree]
      

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

**3. [Segment Tree Query.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Lint, Segment Tree]
      

给了segment Tree, node里面有Max value, 找[start,end]里面的max

#### Segment Tree, Divide and Conquer
- 根据[start,end]跟 mid of (root.start, root.end) 做比较:
- 简单的2个case: [start,end]全在mid左, 或者[start, end]全在mid右
- 稍微复杂的3rd case: [start, end]包含了mid, 那么就break into 2 queries
- [start, node.left.end], [node.right.start, end]



---

**4. [Segment Tree Modify.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Modify.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Lint, Segment Tree]
      

给一个segmentTree, node里面存max. 写一个modify function: modify(node, index, value).

#### Segment Tree, Divide and Conquer
- Recursively 在segment tree里面找index, update it with value.   
- 每个iteration，很可能（要么左手，要么右手）max就变了。所以每次都left.max and right.max compare一下
- 最后轮回到头顶，头顶一下包括头顶，就全部都是max了



---

**5. [Segment Tree Query II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query%20II.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Lint, Segment Tree]
      

#### Segment Tree
- 和 Segment Tree Query I 以及其他Segment Tree类似: 这个SegmentTreeNode return count of elements in range
- 这个题目考了validate input source：input 的start,end可能超出root[start,end]。   
- 那么第一步就要先clear一下: 1. 完全不在range就return 0. 2. 有range重合就规整到root的range.




---

**6. [Count of Smaller Numbers After Self.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Numbers%20After%20Self.java)**      Level: Hard      Tags: [BST, Binary Indexed Tree, Binary Search, Divide and Conquer, Segment Tree]
      

给一串数字nums[], 求一个新数组result, where result[i] = # of smaller items on right of nums[i]

#### Binary Search
- sort and insert 进一个新list, 新的list是sorted
- 从末尾 i = n-1 遍历nums[]
- 每一次insert nums[i] 进list的位置, 就是# of smaller items on right side of nums[i]
- 每次记录下result[i]
- **问题**: 这里的binary search 是用 `end = list.size(); while(start<end){...}`做的, 可否换成用`end=list.size() - 1`?


#### Segment Tree based on actual value
- Build segment tree based on min/max values of array: set each possible value into leaf
- query(min, target - 1): return count # of smaller items within range [min, target - 1]
- Very similar to `Count of Smaller Number`, where segment tree is built on actual value!!
- IMPORTANT: goal is to find elements on right -> elements processed from left-hand-side can be removed from segment tree
- Use `modify(root, target, -1)` to remove element count from segment tree. Reuse function
- time: `n * log(m)`, where m = Math.abs(max-min). log(m) is used to modify() the leaf element

##### Segment Tree solution - tricky part:
- negative nubmer works oddly with mid and generates endless loop in build(): `[-2, -1]` use case
- build entire segment tree based on [min, max], where min must be >= 0. 
- we can do this by adding Math.abs(min) onto both min/max, as well as +diff during accessing nums[i]



#### Binary Indexed Tree
- TODO, have code



---

**7. [Interval Minimum Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Minimum%20Number.java)**      Level: Medium      Tags: [Binary Search, Divide and Conquer, Lint, Segment Tree]
      

给一串数字 int[], 然后一个query Interval[], 每个interval是 [start, end], 找query 区间里的最小值.

#### Segment Tree
- SegtmentTree, methods: Build, Query. 这题是在SegmentTreeNode里面存min.
- 类似的有存:max, sum, min



---

**8. [Interval Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Sum.java)**      Level: Medium      Tags: [Binary Search, Lint, Segment Tree]
      

给一串数字 int[], 然后一个query Interval[], 每个interval是 [start, end], 找query 区间里的sum.

#### Segment Tree + Binary Search
- 其实是segment tree 每个node上面加个sum
- 记得Segment Tree methods: Build, Query
- Note: 存在SegmentTreeNode里面的是sum.  其他题目可能是min,max,count ... or something else.



---

**9. [Interval Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Sum%20II.java)**      Level: Hard      Tags: [Binary Search, Lint, Segment Tree]
      

SegmentTree大集合. Methods: `build, query, modify`. 不难。只是要都记得不犯错.

#### Segment Tree
- build: recursively build children based on index-mid and link to curr node
- query: recursively try to find `node.start == targetStart && node.end == targetEnd`. Compare with node.mid
- modify: recursively try to find `node.start == targetStart && node.end == targetEnd`; modify and recursively assign upper interval with updated interval property.



---

**10. [Count of Smaller Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Number.java)**      Level: Medium      Tags: [Binary Search, Lint, Segment Tree]
      

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

**11. [Reverse Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Pairs.java)**      Level: Medium      Tags: [Binary Indexed Tree, Binary Search Tree, Divide and Conquer, Merge Sort, Segment Tree]
      

给一串数字, count total reverse pair `nums[i] > 2*nums[j]`, i < j

This problem can be solved with Merge sort concept, BST, Segment Tree and Binary Indexed Tree. Good for learning/review.

#### Merge Sort
- Using merge sort concept, not exaclty merge sort implementation.
- One very simply concept: if we want to know how many elements between [i, j] are meeting requirements of `nums[i] > 2*nums[j]`, it would be really helpful, if the entire range is sorted:
- then we just need to keep one i index, and keep j++ for all elements meeting requirement `j<=e && nums[i]/2.0 > nums[j]`
- Then it comes to the sorting part: we cannot just directly sort entire array, because the restriction is `all elements on right side of curr element`. BUT, it is okay to sort `right side range` and compare with left side elements : )
- 灵感: use merge sort concept, divide and conquer:
- divide the elements from mid, compare each subarray
- sort once sub-array is completed (so that it can be used recursively at parent level)
- use classic while loop `while(j<=e && nums[i]/2.0 > nums[j])` to count pairs


#### Segment tree
- TODO
- split the array into index-based segment tree, where each element is at leaf
- store min of range: use max to determine if certain range is needed for further query
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

