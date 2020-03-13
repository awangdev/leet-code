 
 
 
## BST (23)
**0. [Inorder Successor in BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Inorder%20Successor%20in%20BST.java)**      Level: Medium      Tags: [BST, Tree]
      
找 Inorder traversal规则里的下一个.

主要想法是考虑: 
    1. 如果 node.right == null, 找上一个unprocessed node alone the inorder traversal path
    2. 如果 node.right != null, successor 一定在这个node.right那个subtree里面
最后竟然可以简化成几行, 非常全面的BST问题: 有search, 有对inorder traversal的理解, 还有坑.

#### Short Recursive and Iterative without Stack
- Previous solution, we use stack to hold previous cached/unprocessed items: but do we need use catch to hold them?
- If moving left: `p.val < root.val`, then root (parent of left child) is a successor candidate, so save `rst = root`.
- If moving right or equal: `p.val >= root.val`, the successor has nothing to do with curr node, so just directly dive into root.right.
- Both iterative and recursive solution can be simplified as such.


#### Previous Iterative + stack
- Iteratively search
- Still need stack to store previously unprocessed items along the path

#### Previous Recursive + Stack
- 画inorder图，发现规律.每个node的后继node(successor)有几种情况:   
- 1. node.right 是个leaf到底了。那么就return.   
- 2. set rightNode = node.right， 但发现rightNode has a lot left children to leaf.   
- 3. 比如, node.right == null， 也就是node自己是leaf，要回头看山顶找Inorder traversal规则里的下一个。   
- 发现：其实就是每层都把路过的curr node放在stack里，最上面的，就是当下改return的那个successor:) Done.



---

**1. [Unique Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DP, Tree]
      
Not quite clear.
根据左右分割而总结出了原理, 每次分割, 左右两边都会有一定数量的permutation, 总体上的情况数量当然是相乘.
然后每一个不同的分割点都加一遍:
f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

然后把数学公式转换成DP的方程, 有点玄学的意思啊! 不好想.



---

**2. [Minimum Absolute Difference in BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Absolute%20Difference%20in%20BST.java)**      Level: Easy      Tags: [BST]
      
BST: inorder-traversal: 先left node(adding to stack till left leav), 再process stack.peek (mid node), 再 add rightNode && dive to rightNode.left leaf



---

**3. [K Empty Slots.java](https://github.com/awangdev/LintCode/blob/master/Java/K%20Empty%20Slots.java)**      Level: Hard      Tags: [Array, BST, TreeSet]
      
题目解析后: find 2 number, that: 1. k slots between the 2 number, 2. no slots taken between the two number.

#### BST
- BST structure not given, use TreeSet to build BST with each node
- Every time find last/next inorder element 
- `treeSet.lower(x)`, `treeSet.higher(x)`
- 一旦位置相隔(k + 1), 就满足题目条件
- O(nlogn), good enough

#### Track slots of days
- Reverse the array, save days index into days[], where the new index is slot.
- days[i]: at slot i, which day a flower will be planted
- O(n)
- Needs to understand: http://www.cnblogs.com/grandyang/p/8415880.html



---

**4. [Remove Node in Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Node%20in%20Binary%20Search%20Tree.java)**      Level: Hard      Tags: [BST]
      
方法1: Brutle一点。找到target和target的parent.    
把target remove时，把target的children nodes 重新排列组成新的BST: inorder traversal, build tree based on inorder traversal list.

方法2: 分析规律,先找到target和parent, 然后根据性质，把target remove时，移动children nodes, 保证还是BST。



---

**5. [Search Range in Binary Search Tree .java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20Range%20in%20Binary%20Search%20Tree%20.java)**      Level: Medium      Tags: [BST, Binary Tree]
      
给一个BST, integer range (k1, k2), 找range 里面所有的integer.

#### BST
- 等于dfs遍历了所有k1<= x <= k2的x node。
- dfs left, process root, then dfs right
- 这里, 把 left/right/match的情况全部cover了，然后把k1,k2的边框限制好，中间就全部遍历了。



---

**6. [Insert Node in a Binary Search Tree .java](https://github.com/awangdev/LintCode/blob/master/Java/Insert%20Node%20in%20a%20Binary%20Search%20Tree%20.java)**      Level: Easy      Tags: [BST]
      
往Binary Search Tree里面加东西，一定会找到一个合适的leaf加上去。

那么：就是说someNode.left or someNode.right是null时，就是insert node的地方。

找到那个someNode就按照正常的Binary Search Tree规律。



---

**7. [Kth Smallest Element in a BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Element%20in%20a%20BST.java)**      Level: Medium      Tags: [BST, DFS, Stack, Tree]
      
#### Iterative + stack: inorder traversal
- 很容想到Inorder-binary-search-tree Traversal
- Iterative 稍微难想点：先把最左边的add， pop() stack， 加上右边（如果存在）； 下一个轮回，如果又左孩子，又是一顿加。

#### Recursive + DFS
- 然后稍微优化一下，确保rst.size() == k 时候，就可以return了
- check leaf => dfs left => add root => dfs right



---

**8. [Unique Binary Search Tree II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree%20II.java)**      Level: Medium      Tags: [BST, DP, Divide and Conquer, Tree]
      
给一个数字n, 找到以(1...n)为node的所有unique BST.

#### BST
- 根据BST规则, divide and conquer
- 取一个value, 然后分两半(start, value - 1), (value + 1, end) 分别dfs
- 然后左右两边的结果cross match

#### DP? Memoization?



---

**9. [Zigzag Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/Zigzag%20Iterator.java)**      Level: Medium      Tags: [BST]
      
这个题目相对简单. 做的时候我先考虑起来k条怎么办. 那么用个map把index和每个listmark一下就好了。
每次next(), 相应的list的头拿下来就好。
然后就跑圈呗，每次刷一个list头。不难。只要把几个variable维护清楚就行。


---

**10. [Recover Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Recover%20Binary%20Search%20Tree.java)**      Level: Hard      Tags: [BST, DFS, Tree]
      
BST里面有2个node misplace, 要归为. 要求: O(1) extra space

#### Observation
- BST inorder traversal should give small -> large sequence
- misplaced means: a **large**->small item would occur, and later a large>**small** would occur. 
- The first large && second small item are the 2 candidates. Example
- [1, 5,  7, 10,    12, 15, 18]
- [1, 5, `15, 10`, `12,  7`, 18]

#### dfs, O(1) extra space
- traverse, and take note of the candidate
- at the end, swap value of the 2 candidates

#### O(n) space
- inorder traversal the nodes and save in array, find the 2 items misplanced and swap them
- But O(n) space should not be allowed




---

**11. [Convert Sorted List to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20List%20to%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Linked List]
      
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

**12. [Contains Duplicate III.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate%20III.java)**      Level: Medium      Tags: [BST]
      
给一个unsorted array, 问, 是否有两个element, value相差最大为t,  而两个element的index 相差最大为k.

Note: 虽然题目名字是Contains Duplicate, 但其实要找的两个element不是duplicate, 而是Math.abs(value1 - value2) <= t.

#### TreeSet
- TreeSet还是一个set, 我们用来装已经visit过得item
- 如果window大小超过K, 那么把nums[i - k - 1] 去掉, 并且加上新的element
- 这里有个公式推算: (Math.abs(A-B) <= t) =>>>>> (-t <= A - B <= t) =>>>>>> A >= B - t, A <= B + t
- 也就是说, 如果对于 B = nums[i], 来说, 能找到一个target A, 满足上面的公式, 那么就可以 return true.
- Time O(nLogk), treeSet的大小不会超过k,  而 treeSet.ceiling(), treeSet.add(), treeSet.remove() 都是 O(logK)
- Space O(k)

#### Note
- 与Contains Duplicate II 类似概念. TreeSet有BST 因此可以直接用, 而不用自己构建BST
- 简化题目里面的重要条件 Math.abs(A-B) <= t 而推断出 A >= B - t, A <= B + t
- 并且需要需要用 TreeSet.ceiling(x): return number greater or equal to x. 这个用法要记住吧, 没别的捷径.



---

**13. [Trim a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Trim%20a%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [BST, Tree]
      
方法1:
适合复习BST. 用DFS对待每个node. 注意BST的特征: 所有left nodes都小于当下node, 所有right nodes都大于当下node.

根据题意用[L,R]切割.如果node.val<L, 直接连node带左边全丢掉, return node.right. 处理R也是一样.
分制是, DFS leftNode, rightNode. 然后接在node.left, node.right.

方法2: 用迭代, 还没有写.



---

**14. [Max Sum of Rectangle No Larger Than K.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Sum%20of%20Rectangle%20No%20Larger%20Than%20K.java)**      Level: Hard      Tags: [Array, BST, Binary Search, DP, Queue, TreeSet]
      
给定一个非空的二维矩阵matrix与一个整数k，在矩阵内部寻找和不大于k的最大矩形和。

#### BST, Array, preSum
- 将问题reduce到: row of values, find 1st value >= target.
- 1. loop over startingRow; 2. loop over [startingRow, m - 1]; 3. Use TreeSet to track areas and find boundary defined by k.
- When building more rows/cols the rectangle, total sum could be over k: 
- when it happens, just need to find a new starting row or col, 
- where the rectangle area can reduce/remain <= k
- 找多余area的起始点: extraArea = treeSet.ceiling(totalSum - k). 也就是找 减去k 后 起始的/左边的area.
- 去掉这些左边的起始area, 剩下的就 <=k.    (num - extraArea)
- 为什么用TreeSet: area的大小无规律, 并且要找 >= 任意值 的第一个value. 给一串non-sorted数字, 找 >= target的数, 如果不写binary search, 那么用BST最合适
- O(m^2*nlogn)

#### 思想
- 从最基本的O(m^2*n^2) 考虑: 遍历 startingRow/startingCol
- rectangle? layer by layer? 可以想到Presum的思想, 大于需要的sum的时候, 减掉多余的部分
- 如何找到多余的area? 那么就是search: 把需要search的内容存起来, 可以想到用BST(TreeSet), 或者自己写Binary Search.



---

**15. [173. Binary Search Tree Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/173.%20Binary%20Search%20Tree%20Iterator.java)**      Level: Medium      Tags: [BST, Design, Stack, Tree]
      

#### BST in order traversal
- 用stack记录最小值, 放在top. O(h) space.
- 每次消耗TreeNode, 都看看rightNode(其实就是下一个最小的candidate), 并且一条龙stack叠上rightNode所有的left子孙.

#### Previous Notes:
- 用O(1)空间的做法：不存stack, 时刻update current为最小值。
- 找下一个最小值,
    - 如果current有right child: 和用stack时的iteration类似,那么再找一遍current.right的left-most child,就是最小值了。
    - 如果current没有right child: 那么就要找current node的右上parent, search in BinarySearchTree from root.
- 注意：
    - 一定要确保找到的parent满足parent.left == current.
    - 反而言之，如果current是parent的 right child, 那么下一轮就会重新process parent。
    - 但是有错:binary search tree里面parent是小于right child的，也就是在之前一步肯定visit过，如此便会死循环。




---

**16. [493. Reverse Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/493.%20Reverse%20Pairs.java)**      Level: Medium      Tags: [BST, Binary Indexed Tree, Divide and Conquer, Merge Sort, Segment Tree]
      
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

**17. [315. Count of Smaller Numbers After Self.java](https://github.com/awangdev/LintCode/blob/master/Java/315.%20Count%20of%20Smaller%20Numbers%20After%20Self.java)**      Level: Hard      Tags: [BST, Binary Indexed Tree, Binary Search, Divide and Conquer, Segment Tree]
      

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

**18. [270. Closest Binary Search Tree Value.java](https://github.com/awangdev/LintCode/blob/master/Java/270.%20Closest%20Binary%20Search%20Tree%20Value.java)**      Level: Easy      Tags: [BST, Binary Search, Tree]
      

给一个BST, 和一个double target, 走位找到最接近的number.

Concept: Iterate over all logN nodes in the BST and record the closest. Rather than finding the value at +/- 0.5 precision

#### Binary Search
- 记录找到过的closest
- Binary Search, 根据current node走位, until null leaf
- time: O(logn), space O(1) since no extra space used

#### DFS, Recursive
- when less than curr val, consider left
- when greater than curr val, consider right
- dfs到底, 然后每一层比较, 再return
- time: O(logn), space: O(logn)




---

**19. [938. Range Sum of BST.java](https://github.com/awangdev/LintCode/blob/master/Java/938.%20Range%20Sum%20of%20BST.java)**      Level: Easy      Tags: [BST, Recursion, Tree]
      
#### DFS based on BST rules
- sum up the matching L & R
    - Find (L,R) on left child
    - Find (L,R) on right child
    - Find (L,R) covering the root node
- space O(n), worst case O(logn), height of dfs.
- time O(n) to find all nodes between (L, R)

#### Iterative
- Using stack, or queue, list: any data structure (we are not doing ordered search)
- space O(n)
- time O(n)



---

**20. [426. Convert Binary Search Tree to Sorted Doubly Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/426.%20Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Linked List, Tree]
      

把 BST 转换成一个 sorted doubly linked list. (in-place)

#### Tree, In-order traversal, Divide and Conquer
- Regular convert BST to sored list: in-order traversal
    - Carefully doubly link node head, tail
    - traverse all nodes,  DFS 好做: `left, curr, right`
- Tail:
    - Assume head is found for sub tree, then `tail = head.left`
    - Link `LeftTail <-> Curr Root <-> RightHead`
    - Link `RightTail <-> LeftHead`
- In place:
    - 同 `Node {val, left, right}`, w/o new doubley linked list class
    - different from `Convert Binary Search Tree to Sorted Doubly Linked List (extra space)`



---

**21. [98. Validate Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/98.%20Validate%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Tree]
      

验证是否是BST by definition

#### DFS
- 查看每个parent-child关系: 
    - leftchild < root < rightChild
    - all of left child < curr < all of right child
- 方法: 把root.val 传下来作为 max 或者 min, valid child in (min, max)
- BST 有两个极端: left-most-leaf is the smallest element, and right-most-leaf is largest
    - imagine we know the two extreme border: Long.MIN_VALUE, Long.MAX_VALUE
- min/max: long type to meet edge case: node.val = Integer.MAX_VALUE



---

**22. [235. Lowest Common Ancestor of a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/235.%20Lowest%20Common%20Ancestor%20of%20a%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [BST, DFS, Tree]
      

给 binary search tree root, q node, p node. 找到p q 的lowest common ancestor

#### Find path with BST
- 利用 BST 的性质，可以直接搜到target node，而做成两个长度不一定相等的 path
- 然后很简单找到LCA 
- O(n) space, O(logn) time

#### DFS
- Beofre lasts common ancestor is found: p and q should follow same search pattern:
    - compare with root, then dfs(left) or dfs(right)
    - util p and q fall on two sides of root, then return root
    - 非常巧妙, 但是也比较局限; 稍微变条件, 就很难recursive.
- 几种情况:
    - 1. one of p, q 在leaf, 那么此时的root其实就是lowest common ancestor
    - 2. 如果p, q 在root的左右两边, 这就是分叉口, 那么root就是lowest common ancestor
    - 3. 如果p, q 在root的同一边 (左,右), 那么继续dfs
- O(logn) extra space: recursive stack space
- O(logn) time



---

