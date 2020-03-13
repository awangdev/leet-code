 
 
 
## Binary Indexed Tree (4)
**0. [308. Range Sum Query 2D - Mutable.java](https://github.com/awangdev/LintCode/blob/master/Java/308.%20Range%20Sum%20Query%202D%20-%20Mutable.java)**      Level: Hard      Tags: [Binary Indexed Tree, Segment Tree]
      

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

**1. [493. Reverse Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/493.%20Reverse%20Pairs.java)**      Level: Medium      Tags: [BST, Binary Indexed Tree, Divide and Conquer, Merge Sort, Segment Tree]
      
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

**2. [315. Count of Smaller Numbers After Self.java](https://github.com/awangdev/LintCode/blob/master/Java/315.%20Count%20of%20Smaller%20Numbers%20After%20Self.java)**      Level: Hard      Tags: [BST, Binary Indexed Tree, Binary Search, Divide and Conquer, Segment Tree]
      

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

**3. [307. Range Sum Query - Mutable.java](https://github.com/awangdev/LintCode/blob/master/Java/307.%20Range%20Sum%20Query%20-%20Mutable.java)**      Level: Medium      Tags: [Binary Indexed Tree, Segment Tree]
      

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

