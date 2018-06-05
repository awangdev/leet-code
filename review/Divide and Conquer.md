 
 
 
## Divide and Conquer (25)
**0. [Kth Largest Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element.java)**      Level: Review      Tags: [Divide and Conquer, Heap, Quick Sort]
      

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

**1. [Majority Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Element.java)**      Level: Easy      Tags: [Array, Bit Manipulation, Divide and Conquer]
      

#### Vote 计数
- vote++, vote--到最后剩下的就是winner. Time O(n), Space O(1)
- Majority Number是指超半数. 超半数的数字, 最后都会至少有vote>=1: match current majority number，vote++；if not, vote--. 
- 注意：assume valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。

#### HashMap count occurance
- Time, Space: O(n)

#### Bit manipulation
- TODO

#### Related Problems
- Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。
- Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。



---

**2. [Convert Sorted Array to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [DFS, Divide and Conquer, Tree]
      

如题

#### DFS
- Binary Search Tree特点: 左边的node都比右边的node小. 
- 如果要height相差<1, 必须左右sub tree均分. 做DFS(num, start, end)
- 在每一个level, 找到中间点, 然后分割2办, 继续dfs
- Divide and Conquer



---

**3. [Construct Binary Tree from Inorder and Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Preorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Tree]
      

如题

#### DFS
- 和Construct from Inorder && Postorder 想法一样。
- 写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。
- 跟Convert Sorted Array to Binary Tree类似, 找到对应的index, 然后:
- node.left = dfs(...), node.right = dfs(...)
- Divide and Conquer



---

**4. [Validate Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Validate%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Tree]
      

如题, 验证是否是BST.

#### DFS
- 查看每个parent-child关系: leftchild < root < rightChild
- 方法: 把root.val 传下来作为 max 或者 min, 然后检查children

##### Note: 
- min/max需要时long type. 
- 如果题目真的给node.val = Integer.MAX_VALUE, 我们需要能够与之比较, long就可以.



---

**5. [Convert Sorted List to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20List%20to%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Linked List]
      

如题, 把一个sorted singly linked list 转换成一个 height balanced BST

#### DFS
- Divide and Conquer   
- 找到mid node
- 然后分割两半, 分别dfs做各自两个subtree: node.left,node.right
- 用长度来定位mid, 每次找中间点做root, 然后前半段, 后半段分别dfs with length.
- 用快慢pointer 找到mid. Better: 不用traverse entire linked list

#### Details
- slowPointer = node;
- fastPointer = node.next;
- 然后把root = mid.next     
- 然后开始sortedListToBST(mid.next.next); //后半段    
- mid.next = null;//非常重要，要把后面拍过序的断掉    
- sortedListToBST(head); //从头开始的前半段     
- 最后root.left, root.right merge一下。   



---

**6. [Expression Expand.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Expand.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Stack]
      

给一个expression string. 里面包括数字, 字母, 括号. 其中数字代表括号里面的内容重复几次.

括号里面可以是String, 也可能是expression.

目的: 把expression展开成一个正常的String.

#### DFS
- 与Stack时需要考虑的一些function类似. 特别之处: **检查[ ]的结尾**
- 因为DFS时候, 括号里的substring会被保留着进入下一个level, 所以我们在base level要keep track of substring.
- 用int paren 来track 括号的开合, 当paren再次==0的时候 找到closure ']'

#### Stack
- Stack存 [ ] 里面的内容, detect 括号开头结尾: 结尾时process inner string
- 有很多需要注意的细节才能做对:
- Stack<Object> 也可以用, 每个地方要注意 cast. 存进去的需要是Object: String, Integer
- 几个 type check: instanceof String, Character.isDigit(x), Integer.valueOf(int num)
- 出结果时候, 不能轻易 sb.reverse().toString(): sb.reverse() 翻转了整个连在一起的string, 错.
- 用另一个Stack<String>作为buffer, 先把stack里面的内容倒出来 (pure), 但是每个item里面顺序不变.
- 最后再从buffer里面倒进StringBuffer.




---

**7. [Find Peak Element II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element%20II.java)**      Level: Hard      Tags: [Binary Search, DFS, Divide and Conquer]
      

2Dmatrix, 里面的value有一些递增, 递减的特点(细节比较长, 看原题). 目标是找到peak element

#### DFS

##### 基本原理
- 我们不可能一口气准确定位(x,y), 但是我们可以再一个row/col里面, 找到1D array的 peak.
- 根据这个点, 再往剩下两个方向移动
- 1. 在中间的一行i=midX, 找到peak所在的y.
- 2. 在中间的一列j=midY, 找到peak所在的x. (有可能强势override之前找到的y, 也就是放弃那一行的peak, 在midY上找peak)
- 3. 根据 (x,y) 的4个neighbor check (x,y)是不是 peak, 如果不是, 像更高的位置移动一格
- 4. 根据之前算的 midX, midY 把board分成4个象限, 在每一份里面再继续找
- 这个题目LintCode不给做了, 所以思路对的, 但是解答还没有再次验证.

##### 剪枝/切分象限
- 每次只是找到一个row/col里面的peak而已!
- 找到这个点, 就等于把board切成了两半.
- 然后, 再跟剩下的相邻的两个位置比较, 就知道了哪里更大, 就去哪里找peak, 也就是又切了第二刀.
- 切第二刀的时候, 也要把(x, y) 移到需要取的象限. 进行DFS
- 根据mid row 切割: 
- http://www.jiuzhang.com/solution/find-peak-element-ii/#tag-highlight-lang-java
- http://courses.csail.mit.edu/6.006/spring11/lectures/lec02.pdf

##### 时间复杂度
- 每一个level都减一半
- T(n) = n + T(n/2) = n + n/2 + n/4 + ... + 1 = n(1 + 1/2 + .... + 1/n) = 2n = O(n)

#### Binary Search
- TODO
- O(nLogN)



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

**9. [Burst Balloons.java](https://github.com/awangdev/LintCode/blob/master/Java/Burst%20Balloons.java)**      Level: Hard      Tags: [DP, Divide and Conquer, Interval DP, Memoization]
      

一排球, 每个球有value, 每次扎破一个, 就会积分: 左*中间*右 的值. 求, 怎么扎, 最大值?

TODO: Need more thoughts on why using dp[n + 2][n + 2] for memoization, but dp[n][n] for interval DP.

#### Interval DP
- 因为数组规律会变, 所以很难找'第一个burst的球'. 反之, 想哪一个是最后burst?
- 最后burst的那个变成一堵墙: 分开两边, 分开考虑, 加法原理; 最后再把中间的加上.
- dp[i][j] represent max value on range [i, j)
- Need to calculate dp[i][j] incrementally, starting from range size == 3 ---> n
- Use k to divide the range [i, j) and conquer each side.

##### Interval DP 三把斧:
- 中间劈开
- 砍断首或尾
- Range区间作为iteration的根本

##### Print the calculation process
- use pi[i][j] and print recursively.
- Print k, using pi[i][j]: max value taken at k

#### Memoization
- 其实会做之后挺好想的一个DP
- dp[i][j] =  balloons i~j 之间的 max. 
- 然后找哪个点开始burst? 设为x。
- For loop 所有的点作为x， 去burst。
- 每次burst都切成了三份：左边可以recusive 求左边剩下的部分的最大值 + 中间3项相乘 + 右边递归下去求最大值。
- Note: 这个是Memoization, 而不纯是DP
- 因为recursive了，其实还是搜索，但是memorize了求过的值，节省了Processing




---

**10. [Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray.java)**      Level: Easy      Tags: [Array, DFS, DP, Divide and Conquer, Sequence DP]
      

给一串数组, 找数组中间 subarray 数字之和的最大值

#### Sequence DP
- dp[i]: 前i个element, 包括element i 在内的 continous subsequence 的最大sum是多少?
- 因为continous sequence, 所以不满足条件的时候, 会断: track overall max,
- init dp[0] = 0; max = MIN_VALUE 因为有负数
- Time, space O(n)
- Rolling array, space O(1)

#### Divide and Conquer
- 找一个mid piont, 考虑3种情况: 只要左边, 只要右边, cross-mid
- left/rigth 的case, 直接 dfs
- corss-mid case: continuous sum max from left + continous sum max from right + mid
- continuous sum max from one direction:


---

**11. [Binary Tree Longest Consecutive Sequence II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence%20II.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Double Recursive, Tree]
      

找到binary tree 里的最长 consecutive sequence. Sequence可以递增递减, Sequence顺序可以回溯parent.

#### DFS
- Similar to Binary Tree Longest Consecutive Sequence I
- 只不过可以递增递减, 还有连接上parent的方向.
- 对于任何一个节点, 都可能: 
- 1. 自己跟两个child链接, 成为一个sequence
- 2. 左边孩子, 右边孩子各自是一个consecutive sequence, 但是不跟root相连
- main function 一开始就divide成这三份, 然后dfs
- dfs take diff == 1, diff == -1, 来做递增递减的校对.
- dfs rules:
- 1. if node == null, leaf depth = 0
- 2. if not consecutive, reset the depth = 0 (same for both left child, and right child)
- 3. compare the leftDepth && rightDepth to find the maximum
- 4. diff is the same in the same dfs loop to maintain consistant increase/decrease

##### 注意
- dfs的结果很可能是0, 如果没有任何结果, 那么上一层的caller depth = dfs() + 1 = 1
- 那么回归到root, dfs的结果很可能就是1.
- 可能会问: 那么在tree里面的partial sequence (不连接到root)的被忽略了?
- 这里 `longestConsecutive(root.left)` 就很重要了
- 这一步特地忽略掉了root, 然后走下去一层: 因为是recursive, 所以还会继续divde && conquer
- 最后, 任何一层的孩子都会被照顾到.



---

**12. [Binary Tree Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      

找到binary tree 里的最长 consecutive sequence.

#### DFS
- Divide and Conquer. dfs
- 分开 看左边/右边
- 如果左边满足连续递增的规则, dfs (depth + 1), 如果不满足规则, dfs(depth = 1)
- 右边也是一样
- 对结果跟max作比较, return



---

**13. [Unique Binary Search Tree II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree%20II.java)**      Level: Medium      Tags: [BST, DP, Divide and Conquer, Tree]
      

给一个数字n, 找到以(1...n)为node的所有unique BST.

#### BST
- 根据BST规则, divide and conquer
- 取一个value, 然后分两半(start, value - 1), (value + 1, end) 分别dfs
- 然后左右两边的结果cross match

#### DP? Memoization?



---

**14. [Segment Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Build.java)**      Level: Medium      Tags: [Binary Tree, Divide and Conquer, Segment Tree]
      

给一个区间[startIndex, endIndex], 建造segment tree structure, return root node.

#### Segment Tree definition
- Recursively build the binary tree
- 左孩子：（A.left, (A.left+A.rigth)/2）   
- 右孩子：（(A.left+A.rigth)/2＋1， A.right）   



---

**15. [Segment Tree Build II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Build%20II.java)**      Level: Medium      Tags: [Binary Tree, Divide and Conquer, Segment Tree]
      

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

**16. [Segment Tree Query.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Segment Tree]
      

给了segment Tree, node里面有Max value, 找[start,end]里面的max

#### Segment Tree, Divide and Conquer
- 根据[start,end]跟 mid of (root.start, root.end) 做比较:
- 简单的2个case: [start,end]全在mid左, 或者[start, end]全在mid右
- 稍微复杂的3rd case: [start, end]包含了mid, 那么就break into 2 queries
- [start, node.left.end], [node.right.start, end]



---

**17. [Segment Tree Modify.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Modify.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Segment Tree]
      

给一个segmentTree, node里面存max. 写一个modify function: modify(node, index, value).

#### Segment Tree, Divide and Conquer
- Recursively 在segment tree里面找index, update it with value.   
- 每个iteration，很可能（要么左手，要么右手）max就变了。所以每次都left.max and right.max compare一下
- 最后轮回到头顶，头顶一下包括头顶，就全部都是max了



---

**18. [Segment Tree Query II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query%20II.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Segment Tree]
      

#### Segment Tree
- 和 Segment Tree Query I 以及其他Segment Tree类似: 这个SegmentTreeNode return count of elements in range
- 这个题目考了validate input source：input 的start,end可能超出root[start,end]。   
- 那么第一步就要先clear一下: 1. 完全不在range就return 0. 2. 有range重合就规整到root的range.




---

**19. [Count of Smaller Numbers After Self.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Numbers%20After%20Self.java)**      Level: Review      Tags: [BST, Binary Indexed Tree, Binary Search, Divide and Conquer, Segment Tree]
      

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

**20. [Fast Power.java](https://github.com/awangdev/LintCode/blob/master/Java/Fast%20Power.java)**      Level: Medium      Tags: [DFS, Divide and Conquer]
      

如题: Calculate the a^n % b where a, b and n are all 32bit integers.

#### Divide and Conquer
- a^n可以被拆解成(a*a*a*a....*a)， 是乘机形式，而%是可以把每一项都mod一下的。所以就拆开来take mod.
- 这里用个二分的方法，recursively二分下去，直到n/2为0或者1，然后分别对待. 
- 注意1: 二分后要conquer，乘积可能大于Integer.MAX_VALUE, 所以用个long.
- 注意2: 要处理n%2==1的情况，二分时候自动省掉了一份 a，要乘一下。




---

**21. [Interval Minimum Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Minimum%20Number.java)**      Level: Medium      Tags: [Binary Search, Divide and Conquer, Segment Tree]
      

给一串数字 int[], 然后一个query Interval[], 每个interval是 [start, end], 找query 区间里的最小值.

#### Segment Tree
- SegtmentTree, methods: Build, Query. 这题是在SegmentTreeNode里面存min.
- 类似的有存:max, sum, min



---

**22. [Merge k Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20k%20Sorted%20Lists.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, Linked List, PriorityQueue]
      

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

**23. [Search a 2D Matrix II.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20a%202D%20Matrix%20II.java)**      Level: Medium      Tags: [Binary Search, Divide and Conquer]
      

给matrix, 每一行sorted, 每一列从上往下sorted, 找target是否存在

#### Binary Search
- 根据给定的性质, 其实点选的极端一点: x = 最下面的row, y = 当下一行里面最小的left position. 
- (x,y)在左下角
- 在此情况下, 只能往一个方向运行: 如果小于target, y++; 如果大于target, 那么只能x--
- 每次操作, 都是删掉一行, 或者一列, 再也不需要回头看
- `while (x >= 0 && y < col) {}` 确保不会跑脱
- 同样的方式: 可以从右上角(0, col - 1) 开始, 代码稍微改一改

#### Divide and Conquer?
- TODO



---

**24. [Sort List.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20List.java)**      Level: Medium      Tags: [Divide and Conquer, Linked List, Merge Sort, Sort]
      

#### Merge sort
- 1. find middle. 快慢指针
- 2. Sort: 切开两半，先sort前半, 如果先sort了mid.next~end, sort后，中间点mid.next == null，再sort前半段
- 3. Merge:  假设given list A, B 已经是sorted, 然后按照大小，混合。
- 要recursively call sortList() on partial list.

#### Quick sort
- 想做可以看讲义：http://www.jiuzhang.com/solutions/sort-list/
- 但是quick sort不建议用在list上面。
- 排列list, merge sort可能更可行和合理。原因分析在下面， 以及： http://www.geeksforgeeks.org/why-quick-sort-preferred-for-arrays-and-merge-sort-for-linked-lists/



---

