Table of Contents
=================

   * [Table of Contents](#table-of-contents)
   * [Heap](#heap)
   * [Stack](#stack)
      * [Functions](#functions)
      * [基本用法](#基本用法)
      * [Monotonous Stack](#monotonous-stack)
      * [Expample](#expample)
   * [Queue](#queue)
      * [Functions](#functions-1)
      * [思想](#思想)
   * [Linked List](#linked-list)
   * [Tree](#tree)
   * [Binary Search Tree](#binary-search-tree)
      * [TreeSet](#treeset)
   * [Binary Tree](#binary-tree)
      * [Type of Tree](#type-of-tree)
         * [Balenced bianry tree](#balenced-bianry-tree)
         * [Complete binary tree](#complete-binary-tree)
         * [Full Binary Tree](#full-binary-tree)
         * [Perfect Binary Tree](#perfect-binary-tree)
      * [Binary Tree Traversal](#binary-tree-traversal)
   * [Min-Heap &amp;&amp; Max-Heap](#min-heap--max-heap)
      * [Insert](#insert)
      * [Extract Minimum Element](#extract-minimum-element)
   * [Expression Tree](#expression-tree)
      * [Example](#example)
      * [Build Tree](#build-tree)
   * [Trie](#trie)
      * [applications](#applications)
      * [why not using a hashmap to store string](#why-not-using-a-hashmap-to-store-string)
      * [Model](#model)
      * [sample peoblems](#sample-peoblems)
      * [考点](#考点)
   * [Binary Indexed Tree](#binary-indexed-tree)
   * [Segment Tree](#segment-tree)
   * [Graph](#graph)
      * [Adjacency List](#adjacency-list)
         * [Example](#example-1)
      * [Adjacency Matrices](#adjacency-matrices)
      * [Graph Search](#graph-search)
         * [DFS](#dfs)
         * [BFS](#bfs)
         * [Bidirectional Search](#bidirectional-search)
   * [Union Find](#union-find)
      * [UnionFind基础操作](#unionfind基础操作)
      * [UnionFind follow up](#unionfind-follow-up)
   * [Deque](#deque)
   * [Topological Sort](#topological-sort)
      * [Topological Sort - BFS](#topological-sort---bfs)
      * [Topological Sort - DFS](#topological-sort---dfs)
   * [Array](#array)
   * [Two Pointers](#two-pointers)
   * [Binary Search](#binary-search)
      * [Basics](#basics)
      * [二分思想](#二分思想)
   * [Sort](#sort)
      * [Quick Sort](#quick-sort)
   * [Collections](#collections)
      * [methods](#methods)
      * [ArrayList](#arraylist)
   * [Hash](#hash)
   * [Map](#map)
   * [Hash Table](#hash-table)
      * [HashTable](#hashtable)
      * [HashSet](#hashset)
   * [Basics](#basics-1)
      * [Sum, Presum](#sum-presum)
      * [Math](#math)
         * [Math Functions](#math-functions)
      * [Numbers](#numbers)
      * [String](#string)
      * [Bit Manipulation](#bit-manipulation)
   * [DP](#dp)
      * [判断](#判断)
      * [四个步骤](#四个步骤)
         * [确定状态](#确定状态)
         * [转移方程](#转移方程)
         * [初始条件/边界情况](#初始条件边界情况)
         * [计算顺序](#计算顺序)
      * [Technique](#technique)
      * [基本原理](#基本原理)
      * [分类](#分类)
         * [网格坐标](#网格坐标)
            * [特殊](#特殊)
         * [序列](#序列)
            * [特点](#特点)
            * [性质](#性质)
            * [特点](#特点-1)
            * [关键点](#关键点)
            * [序列 状态](#序列状态)
         * [划分](#划分)
            * [性质](#性质-1)
            * [经验](#经验)
            * [解决方法](#解决方法)
         * [博弈类](#博弈类)
         * [背包类](#背包类)
            * [多种问法](#多种问法)
            * [方法策略](#方法策略)
            * [放入的物品没有顺序](#放入的物品没有顺序)
         * [区间类(Interval DP)](#区间类interval-dp)
            * [特点](#特点-2)
            * [三把斧](#三把斧)
            * [难点](#难点)
         * [Bitwise Operation DP](#bitwise-operation-dp)
         * [优化](#优化)
      * [Minimax/MaxiMin](#minimaxmaximin)
      * [Optimization problems](#optimization-problems)
      * [Double Sequence](#double-sequence)
      * [存状态](#存状态)
   * [记忆化搜索 Memoization DP](#记忆化搜索-memoization-dp)
      * [什么时候用记忆化搜索](#什么时候用记忆化搜索)
      * [特点](#特点-3)
      * [缺点](#缺点)
   * [Search](#search)
      * [Breadth-first Search](#breadth-first-search)
      * [Depth-first Search](#depth-first-search)
   * [Backtracking](#backtracking)
   * [Reservoir Sampling](#reservoir-sampling)
   * [Geometry](#geometry)
   * [Brainteaser](#brainteaser)
   * [Red Black Tree](#red-black-tree)
   * [Approach](#approach)
      * [Greedy](#greedy)
      * [Divide and Conquer](#divide-and-conquer)
      * [Recursion](#recursion)
         * [特征](#特征)
   * [Design](#design)
   * [BigO](#bigo)
      * [Specials](#specials)
   * [Data Structure](#data-structure)
   * [Problem Sets](#problem-sets)
      * [permutation](#permutation)
         * [原理](#原理)
         * [Example](#example-2)
      * [Two Pointer](#two-pointer)
      * [Min/Max Heap](#minmax-heap)
      * [回文串 Palindrome](#回文串-palindrome)
      * [Stack](#stack-1)
      * [Windows Problem](#windows-problem)
      * [Sweep Line](#sweep-line)
   * [Pain Point](#pain-point)
      * [Basics](#basics-2)
      * [Edge case](#edge-case)
      * [Advanced](#advanced)

# Heap

# Stack

## Functions
- peek(), pop(), push()
- Stack<object> stack = new Stack<>();

## 基本用法
- 用来暂且保存有效信息
- 翻转stack
- stack优化DFS, 变成非递归
- Stack can be implemented with LinkedList, adding/removing from same side of the list

## Monotonous Stack
- 找每个元素左边或者右边 第一个比它自身小/大的元素
- 用单调栈来维护
- 维护monotonous stack 是题目需要, 而不是stack本身性质. 比如, 题目需要stack.peek() O(1), 加上需要单调递增/递减的性质, 就用起来了stack.
```
// Use monotonous stack to build minimum binary tree
while (!stack.isEmpty() && node.val <= stack.peek().val) {
    node.left = stack.pop();
}
if (!stack.isEmpty()) {//build right node of the tree
    stack.peek().right = node;
}
stack.push(node);
```
```
// Plain monotonous stack template:
item = someItem; // ex: item in for loop
while (!stack.isEmpty() && (item.property compareTo stack.peek().property)) {
    topItem = stack.pop();
    // Do something with the topItem
}
stack.push(item);
```

## Expample
- Maximum Binary Tree, Largest Rectangle In Histogram, Maximal Rectangle (in 2D array)
- Expression Tree (Minimum Binary Tree)


# Queue
## Functions
- peek(), poll(), add()/offer(), remove(object)
- queue = new LinkedList<...>()
- PriorityQueue: new Comparator 很重要
- Queue 可以用 LinkedList 实现. Add from the last/end of the list; Return/remove from the head of the list. 

## 思想
- 看到Min/Max就要想到heap. 如果给出的数组没有排序, 先排序, 然后用heap. PrioirtyQueue是用Binary Heap做出来的 (看Binary Tree section)
- 看到median 想到heap

# Linked List
- No concept of size(), it's all pointers: node.next.next
- how to set head/dummy, and return dummy.next as result.
- iterate over linked list
- Don't get mixed up with Java LinkedList. Here we are talking about linked list concept, not the Java data structure LinkedList

## 操作
- Reverse Linked List: 不断地在开头加上新node. 比较方便的方式: 用一个dummy node, 然后把reversed list 存在dummy.next
- Reverse linked list 注意: 有时候一开始的1st node, 最后还有tail 要接在上面; 所以先把1st node可以额外存下来, 用来接 tail
- 找到mid node: 快慢指针(slow = head; fast=head.next); 快指针每次走2步; 快指针到底的时候, slow指针就是mid

# Tree
- A simple version of graph
- CAN NOT have cycle
- Can have a list of children
- 一般不会用Tree class 来实现tree, 一般都是用 TreeNode root as reference
- leaf: very end, 没孩子


# Binary Search Tree
- If BST not given, can use TreeSet
- All left nodes are less than current node.val; all right nodes are greater than curr node.val
- Use DFS to traverse: divide and conquer. Similarly, usually can convert the DFS solution to interative solution.
- Use stack to traverse iteratively

## TreeSet
- 如果BST treenode没给, 可以用TreeSet
- TreeSet还是一个set, 存values, 而好处是可以用 treeSet.ceiling(x)找到 最小的大于x的值
- 其实就是这个value/node的parent

# Binary Tree
- 一定要问清楚, 是Binary Tree (双孩子而已), 还是 Binary Search Tree. 非常重要!!!
- 一个child tree的nodes总量是 2 ^ h - 1; 那么一个child tree + root 的nodes 总量就是 2 ^ h了.

## Type of Tree

### Balenced bianry tree
- has the minimum posible maximum height(depth) for left nodes; for given leaf nodes, the leaf nodes will be placed at greatest height possible.
- More like 'not terriably imbalanced'; NOT super balanced like 'perfect binary tree'
- can support O(logn) times for insert and find

### Complete binary tree
- all levels are filled, except maybe the last level. 最后一个level可能是缺node的(不是说最右下角缺node, 别忘了!)
- Nodes are filled from left to right on the last level

### Full Binary Tree
- node has 0 or 2 children
- NO node will have 1 child

### Perfect Binary Tree
- Both Complete and Full
- Have all nodes
- Last level has maximum nodes
- DO NOT assume tree is pefect tree; very rare in life/interview

## Binary Tree Traversal
- preorder, inorder, post-order
- inorder more often
- draw the map
- can implement with dfs, bfs


# Min-Heap && Max-Heap
- min-heap && max-heap has same concept
- min-heap is complete binary tree (right-most elements on last level may not be filled)
- each node is smaller than it's children
- root is the minimum value of tree
- Maintaing min-heap is about swaping node values
- Insert/extract min value both take O(logn) time

## Insert
- insert at bottom right-most spot
- swap with parent if value not fitting min-heap
- swapping until min value reaches root
- O(logn) to bubble up to top

## Extract Minimum Element
- extract root value (easy)
- set the the root value to be bottom-right-most element, also remove that bottom element
- bubble down the root value if not fitting min-heap
- overal efforts to bubble down: O(logn)

# Expression Tree
- Binary tree, used to evaluate certain expression
- All leaf nodes of the expression tree have a number/string value.
- All non-leaf node of the expression tree have an operation string value.

## Example
```
Example
For the expression (2*6-(23+7)/(1+2)) 
which can be represented by ["2" "*" "6" "-" "(" "23" "+" "7" ")" "/" "(" "1" "+" "2" ")"]. 
The expression tree will be like

                 [ - ]
             /          \
        [ * ]              [ / ]
      /     \           /         \
    [ 2 ]  [ 6 ]      [ + ]        [ + ]
                     /    \       /      \
                   [ 23 ][ 7 ] [ 1 ]   [ 2 ] .

```

## Build Tree
- Use Monotonous Stack to build Minimum Binary Tree
- Use weight to associate parentheses, signs, numbers
- Write getWeight(base, String s) function to calculate the weight.

# Trie
- 一个字母一个字母查找，快速判断前缀
- Prefix Tree
- n-ary tree
- Can tell if string is a valid prefix in O(K) time, k = str.length

## applications
- Autocomplete
- Spell check
- IP routing
- T9 text prediction (old NOKIA phone)
- solve world game

## why not using a hashmap to store string
- Trie can help find all strings with prefix
- Trie can validate a list of words
- Trie can enumerate a data set of strings in lexicographical order
- Trie saves space because of the prefix
- Trie can potentially faster than hashMap, when there are lots collisions for the map.

## Model
- children map: Map<Character, TrieNode>. Also can use char[26], but it's more scalable to us a map.
- always have isEnd which marks a end of a particular string

## sample peoblems
- insert
- search
- exist of prefix
- node when the prefix end

## 考点
- 一个一个字母遍历
- 需要节约空间
- 查找前缀

# Binary Indexed Tree

# Segment Tree

# Red Black Tree
## 基本知识
- one kind of self-balancing binary search tree
- Each node of the binary tree has an extra bit, and that bit is often interpreted as the color (red or black) of the node
- search O(logn), n = total # of nodes in the tree
- deletion/insertion/tree rearrangement && coloring: O(logn)

## 特点
- Each node is either red or black.
- The root is black. (This rule is sometimes omitted)
- All leaves (NIL) are black.
- If a node is red, then both its children are black.
- Every path from a given node to any of its descendant NIL nodes contains the same number of black nodes.

## 引申特点
- the path from the root to the farthest leaf is no more than twice as long as the path from the root to the nearest leaf.

## 用途
- offer worst-case guarantees for insertion time, deletion time, and search time
- used in time-sensitive application, real-time application
- valuble building block in other data structures
- valuble in functional programming: most common `persistent data structure`


# B-Tree
## 基本知识
- https://en.wikipedia.org/wiki/B-tree
- 是generalization of Binary Search Tree. 每个node可以有超过2个children
- 'B' stands for nothing: actually noboday knows lol.
- Search/Insert/Delete O(log n)
- 每个children node 里面都是range of keys, 那么就不需要太多self-balancing operation
- 可能会浪费一些空间, 因为并不是每一个child node里面都full
- 根据root node 来分割child node 里面的range
```
            [7,               16]
          /           |         \
  [1,2,5,6]        [9, 12]       [18, 21]
```
- 每个node的这些keys, 其实就是是seperation values, 他们决定了subTree 如何divide.
- 也就是说, 如果 有 3 个subTree, 至少需要2个root key, 这样才能分成三份, 如上example.
- 通常: 大家会选 [d, 2d] keys, d = minimum number of keys
- 那么就至少有 (d + 1) 个subTree, 那么这个tree minimum degree 就是 (d+1). 也就是(d+1)个edge嘛, 简单.

# AVL Tree
## 基本知识
- The sub-trees of every node differ in height by at most one.
- Every sub-tree is an AVL tree

### 更多细节特点
- 1) All leaves are at same level. root一层, leaf一层.
- 2) A B-Tree is defined by the term minimum degree ‘t’. The value of t depends upon disk block size.
- 3) Every node except root must contain at least t-1 keys. Root may contain minimum 1 key.
- 4) All nodes (including root) may contain at most 2t – 1 keys.
- 5) Number of children of a node is equal to the number of keys in it plus 1.
- 6) All keys of a node are sorted in increasing order. The child between two keys k1 and k2 contains all keys in range from k1 and k2.
- 7) B-Tree grows and shrinks from root which is unlike Binary Search Tree. Binary Search Trees grow downward and also shrink from downward.
- 8) Like other balanced Binary Search Trees, time complexity to search, insert and delete is O(Logn).

## 用途
- Storage system than read/write large blocks of data.
- Commonly used in database, filesystem

## 优点
- keeps keys in sorted order for sequential traversing
- uses a hierarchical index to minimize the number of disk reads
- uses partially full blocks to speed insertions and deletions
- keeps the index balanced with a recursive algorithm

# Graph
- Tree is a type of graph: connected graph, without cycles
- Graph is a collection of nodes, with edges between (some of, not all of) them
- It can be presented as ** map of Nodes **
- Each node should be able to lead to a list of children nodes
- Can be directed (one-way street), or undirected (two-way street)
- May contain multiple isolated subgraphs.
- If there is a path between every pair of ndoes, it's a 'connected graph'
- graph can have cycle
- If no cycle, it's called 'acyclic'
- Two popular ways to store graph: Adjacency List, Adjacency Matrices

## Adjacency List
- The graph can be presented with a graph class, which is just a list of node, a map of nodes
- Or it can me an array (or a hash table) of lists (arrays, arraylists, linked lists ... etc)
- each node has it's own propety, and a list of adjacent nodes
- This graph can be directed, or undirected (modeled by adjacent node list in each Node)

### Example
```

class Graph {
	public Node[] nodes;
}

class Node {
	public String name; // property of the node
	public Node[] children; // children nodes
}
```

## Adjacency Matrices
- matrix[i][j] = true, indicate an edge from node i t node j

## Graph Search
- DFS: start at root, explore each branch competely (go deep first)
- BFS: start at root, explore each neighbor before going to the children (go wide first)

### DFS
- is preferred when visiting **every node** in the graph, a bit simplier to write
- IMPORTANT: in graph, must mark node **'visited'**, otherwise can be infinite loop

### BFS
- Better when finding **shortest path** (or any path) betwen 2 nodes
- Use a queue, of course

### Bidirectional Search
- Find shortest path between a source and destination node
- Running 2 simultaneous BFS
- when two searches collide, a path if found
- It's faster than one BFS
- Regular BFS time O(k^d), k = nodes at one level, d = seach for d times
- Bidirectional search time: O(k^(d/2)). Faster by K^(d/2)


# Union Find
- 集合合并，查找元素在集合里面
- Find and Union functions
- Time Complexity: log(n)
- 在UnionFind function里维护不同的状态, expose with public helper functions

## UnionFind基础操作
- 查询两个元素是否在同一个集合内
- 合并两个元素所在的集合

## UnionFind follow up
- 查询某个元素所在集合的元素个数
- 查询当前集合的个数

# Deque
- linear collection that supports insertion and removal at both ends. Pronounced 'deck'
- It's a queue && stack
- new ArrayDeque<Integer>()
- head/top: offerFirst(), pollFirst(), peekFirst()
- tail/bottom: offerLast(), pollLast(), peekLast() 
- 双端queue: 维护一个候选可能的最大值集合
- ex: Sliding WIndow Maximum




# Topological Sort
- Sort vertices in a graph
- Run DFS, output reverse of finishing times of vertices (stored in a list) 
- G graph has a cycle? If there is a back edge, there is a cycle
- Job Schedule/ Course Schedule: should not have cycle, so acyclic

## Topological Sort - BFS
- https://www.youtube.com/watch?v=_LuIvEi_kZk
- Can be used to return the topologically sorted list
- The final sorted list will not include the element on a cycle; these vertices won't reduce to in-degree 0.
- 给一个graph of nodes
- 目标是根据edge 的 direction, 把这个graph 里面的 node sort 一个list
- 如果有cycle, 这个item就不会被放在最后的list 里面. 比如: 如果两个课互相是dependency, 就变成了cyclic dependency.
- 注意, 有些有cycle的题目里面要求直接fail掉, 因为有了cycle, topological sort的结果是缺element的, 也许对某个场景来说就是没有意义的.
- 相比DFS, BFS 更容易理解和walk through


## Topological Sort - DFS
- https://youtu.be/AfSk24UTFS8?t=42m
- 还是要先构建好 edges = map<node, list of node>; 或者edges = List[arraylist];
- dfs到底, 再没有其他child node的时候, 把这个node加进stack (垫底)
- 最终按照stack的顺序, pop()出来的顺序 (reverse) 就是正确的topological sort
- 这个比BFS有时候要灵活一些: 可以detect cycle on the fly, 而BFS的做法会到最后再结算cycle是否存在




# Array
- Arrays.asList([1,2,3]);
- Partial sort: Arrays.sort(arr, 0, arr.length())
- Copy: Arrays.copyOf(arr, arr.length())

# Two Pointers

# Binary Search

## Basics
- 记得二分的template
- 往往不会有个数组让我们二分, 但是同样是要去找满足某个条件的最大/最小值
- 二分是个思想, 不是简单的array二分公式.
- 有时候在index上二分, mid是index; 但是有时候, 会在数值上二分, 那么mid就是value, 忌讳不要死板地套用nums[mid]

## 二分思想
- 按值二分，需要怎么二分性
- 找到可能的解的范围
- 猜答案
- 检验答案 (match/if/else ...)
- 调整搜索范围
- Find Peak Element II

# Sort

## 常用
- Merge Sort. Runtime: O(nlogn) average/worst. Memory: depends.
- Quick Sort. Runtime: O(nlogn) average, O(n^2) worst. Memory: O(nlogn).
- Heap Sort
- Radix Sort. Runtime: O(nk)
- Insertion Sort
- Bubble Sort. Runtime: O(n^2) average/worst. Memory: O(1)
- Selection Sort. Runtime: O(n^2) average/worst. Memory: O(1)

### Merge Sort

### Heap Sort


### Quick Sort
- pick random pivot, all elements smaller sits before pivot, and all elements larger sits after the pivot
- while loop (and two inner while loop) to find the 2 indexes to swap, comparing with pivot
- use the left pointer to partition the array, and keeps sorting left part and right part

#### Quick Select
- quick sort 的变形
- A selection algorithm to find the k-th smallest element in an unordered list.
- 与quickSort不同在于, 每次只要在一半list里面recurring, 所以把O(logn)的时间复杂度降到O(n)
- still worst case O(n^2)
- 用 end index作为pivot, 每次根据nums[pivot]排序之后, 在 < low的位置, 都小于 nums[pivot]
- 把nums[pivot]换到low的位置, 那么换好之后, 所有在low前面的值, 都是小于 nums[pivot]了
- 那么其实 nums[low]也就是 kth 最小值
- 主要步骤: partition, dfs, only recur on one part of the array

```
private int quickSelect(int[] nums, int start, int end, int target) {
    int index = partition(nums, start, end);
    if (index == target) {
        return nums[index];
    } else if (index < target) {
        return quickSelect(nums, index + 1, end, target);
    } else {
        return quickSelect(nums, start, index - 1, target);
    }
}

private int partition(int[] nums, int low, int high) {
    int pivot = high; // end
    int pivotValue = nums[pivot];

    while (low < high) {
        while (low < high && nums[low] < pivotValue) {
            low++;
        }
        while (low < high && nums[high] >= pivotValue) {
            high--;
        }
        swap(nums, low, high);
    }
    
    swap(nums, low, pivot);

    return low;
}
```

## Comparator for Arrays, Collections
```
public int compare(x, y) {
	return x - y; // or x.compareTo(y)
}
```


# Collections

## methods
- Collections.sort()
- Sort sub list: Collections.sort(list.subList(x, y)). [x, y), exclusive at index y.

## ArrayList
- Integer[] array = {1, 2, 3};
- new ArrayList(Arrays.asList(array))
- O(1) insertion on average. 
- Underneath, the insertion cause the arrayList to doubles the size and copy all contents to a new array. Over time, the cost is 1 + 2 + 4 + ... n/8 + n/4 + n/2 = O(n), over time
- Therefore, the average insertion time is O(1)

# Hash

# Map

# Hash Table

## HashTable
- Ex: when searching unsorted array(if you try to avoid sort O(nlogN)), probably can index with HashMap
- Can be used to store character/string frequency

## HashSet
- contains: O(1)
- set.add(...) returns false if there is duplicate. This operation won't change the existing set.
- Build HashSet<List> set, and the set will automatically compare the equivalence of the lists within at each list element level.

# Basics

## Sum, Presum
- Presum: sum of [0 ~ i] items
- 找 sum[i, j] = preSum[i] - preSum[j - 1];
- 根据题目具体情况 (ex: Copy Books), sum[i, j] 也可以倒序加出来, 存在int  sum 里面

## Math
- 转换成string
- % mod, 除法
- Integer.MAX_VALUE, Integer.MIN_VALUE; if overflow, use long
- Integer.valueOf(number), where number is int

### Math Functions
- Math.pow(x, 3) = x ^ 3; Math.pow(x, 1/3) = x ^ (1/3)


### Numbers
- Long a = 10; a.intValue() => int
- Integer: Integer.parseInt("123")

### Probability theory

### Combinatorics
- ex: study n-choose-k problems

## String
### Functions
- s.toCharArray()
- String.valueOf(charArrary)
- String.compareTo( "XXX" ): 排序按照字典(lexicographically)顺序
- Character.isDigit(x)
- split: str.split("\\ "); 需要用 "\\" regular expression
- s.trim() 去掉 space

### StringBuffer
- sb = new StringBuffer()
- sb.replace(i, j, "replacement string")
- sb.reverse(), sb.append(), sb.deleteCharAt(), sb.length(), sb.setCharAt(index, char)
- sb.insert(0, xxx): 在开头加element

### 其他
- 遇到找string的相关问题: 考虑string的重复性

## Bit Manipulation
- Bit OR |, AND &, XOR ^
- Bit shift: <<, >>
- A << 1: binary of A shifted left for 1 bit, which result in value x 2
- A >> 1: divide by integer 2. Note: decimals are ignored in the result.
- bit shift is a lot faster than reqular 'times' operation.
- 32 bit number: leading bit = 1, negative numbjer; leading bit = 0, positive number.
- '>>' add leading '1' if the 32 bit number originally has leading '1'.
- Java/python: logical shift >>>, always add leading '0' regardless of the sign of the 32-bit number. That is, it may turn a negative number to positive, if the leading bit is originally '1'
- Because with '( )', make sure to surround the desired operation
- & 0000 = clean up; | ABC = assign ABC
- A^B=C, then A = B^C
- bits可以用来表示不同的状态, 比如2bit可以表示4种状态: 00, 01, 10, 11
- Math.pow(2, h) = 2 << (h - 1); 2 << 1就是把所有bits往左移动一位, 也就是 * 2 
- Also, 1 << h = 2 ^ h; 1 << h 就是 2 * 2 * 2* ....乘h次.
- bit operation should be in parentheses

# DP
## 判断
- 计数: how many. 加法原理
- 求最大/最小值: min/max
- 求存在性: 能否, AND/OR dp=true/false
- 主要cover 递推(recurrence)的DP, 从小到大计算

## 四个步骤
- 确定状态
- 转移方程
- 初始条件/边界情况
- 计算顺序

### 确定状态
- 最后一步: 遍历最后一步可以取的情况
- 化成子问题: 切掉最后一块, 剩下的问题跟原问题应该是一样的

### 转移方程
- 数学表达式来判断 dp[x]的结果
- 纸面上工作结束

### 初始条件/边界情况
- 非常重要, 必须有初始条件, 才可以让dp计算出正确结果.

### 计算顺序
- 递推: 从小到大, 从 dp[0], dp[1] 开始
- 记忆化: 从大到小, 先算一遍 dp[n-1]

## Technique
- 滚动数组
- 记忆化搜索

## 基本原理
- 加法原理: 无重复, 无遗漏
- dp = new int[n] 还是 dp = new int[n + 1]: 看角标是否表示坐标, 还是前i items的状态

## 分类
- 网格坐标 * 20%
- 序列类 * 20%
- 划分类 * 20%
- 区间类(interval DP)
- 背包类
- 双序列
- 博弈
- combos
- Bitwise Operation动态规划

### 网格坐标
- 可能是网格, 或者是序列
- dp[i]: 以第i个元素结尾的某种性质
- dp[i][j]: 到格子(i,j)的路径的某种性质.
- dp index [i][j] = coordinate (i,j), 坐标小标就是grid下标
- 2D的初始条件: f[0][0]
- 边界: i = 0, j = 0,  第一行和第一列
- 计算顺序: 比如第一行, 第二行...etc. 目的: 为了保证, 需要用到的状态, 都已经算到了.
- Example: Unique Path I, II
- 可以出print path的题目

#### 特殊
- 最长序列型动态规划: 恰恰是坐标类
- 以i点结束, 很可能可以考虑坐标型
- Longest Increasing Subsequence (祖师爷级别老题目)


### 序列

#### 特点
- 变种多
- 没有固定模板

#### 性质
- dp[i]种, 表示 '前i个元素a[0], a[1] ... a[i - 1] 的某种性质. (坐标类:dp[i]就代表a[i]结尾的性质)
- dp[0] 表示空序列的性质 (坐标类: dp[0]表示以a[0]结尾的性质)
- 题目中遇到 前i个, '并且': 序列 + 状态

#### 特点
- 可以选择让: DP[i]存的是以 1-based index的状态, 求前i个.
- 那么, 需要知道dp[n] 的状态, 但是最大坐标是[n-1], 所以int[n+1].
- 初始化(简单): dp[0]往往是有特殊状态的: 0 index有时候初始化就是0
- 边界情况(简单): 0 是虚拟的位, 所以大多数时候, 就留成0, 不太有所谓.

#### 关键点
- 不关心前面n-1是怎么组合出的状态, 但是可以先确定末尾的状态
- 及时思考的时候从结尾, 在代码写的时候, 其实是模拟末尾是i = [0 ~ n] 的情况, 一圈圈计算.
- 最后再给出 dp[n] 的末尾解答. 

#### 序列+状态
- 当思考动态规划最后一步时, 这一步的选择依赖于 前一步 的某种状态: 一维 + 状态
- 序列+状态:如果n-1步有多种状态, 且n步也有多种可能, 添加一种状态记录, 变成2D dp[sequence index][状态]
- Example: Paint House


### 划分

#### 性质
- 给长度是N的序列或者字符串, 要求分成若干段
- 段数不限, 或者指定K段
- 每一段满足一定性质
- 类似于序列型动态规划, 但是通常要加上 段数信息
- 一般用 dp[i][j]记录, 前i个元素(元素是 0 ~ i - 1) 分成 j 段的性质, 比如, 最小cost

#### 经验
- '分/ partion/ several segment', 求最值 => 划分型动态规划
- 不管怎么划, 总有最后一段! 根据题目给出的条件, 从末尾划刀
- 也可能是看dp[i] 前 i 个item的状态 [0, i - 1]
- Example: Decode Ways

#### 解决方法
- 最后一步 => 考虑最后一段
- 枚举最后一段的起点
- 如果不指定段数, 用dp[i]表示前i个元素分段后的最值/可行性/ways. Perfect Squares, Palindrome Partition II
- 如果指定段数, 用dp[i][j]表示前i个元素分成j段后的最值/可行性/ways. Copy Books

### 博弈类
- 常常问: 先手必胜的情况
- 必胜状态/必败状态的分析
- 通常从第一步分析, 从简单的来分析 (唯一一个需要考虑第一步的  )
- 必胜: 在当下局面走出一步, 让对手无路可逃. 只要对手有一种必败的路线, 就是我必胜.

### 背包类

#### 多种问法
- 前i个物品能不能拼出重量W. 是 可行性 问题: OR , AND
- 填一个什么包, 有一个条件, 重量不超过M
- 不撑爆背包的前提下:
- 装下最多重量物品
- 装下最大价值的物品
- 有多少种方式带走满满一书包物品
- 注意: 如果同一种物品可以拿无限次, 那么dp[i][w] 表示: 前i 种 物品, 装weight w 的性质.

#### 方法策略 
- 还有几个物品
- 还剩多少跟**总承重**有关
- 用总承重M的大小来开数组. 
- 不管几维数组, 总有一维是总承重M
- 比如: dp[i][w] = 能否用前i个物品, 拼出重量W (true/false)

#### 放入的物品没有顺序
- 放入的物品没有顺序: 最后一个背包内的物品是哪个
- 放入的物品有顺序: 
- 1. 最后一个背包内的物品是哪个 
- 2. 最后一个物品有没有进背包

### 区间类(Interval DP)
- 要么砍头, 要么砍尾
- dp[i][j] 表示 [i~j]之间的状态
- 求一段区间的解: max/min/count
- 转移方程通过区间更新: len = x
- 从大到小的更新

#### 特点
- 给定一个序列/字符串, 进行操作
- 最右一步会把 序列/字符串 去头/去尾
- 剩下的是一个区间 [i, j]
- 状态自然定义为dp[i][j], 表示面对子序列 [i, ....., j]时的最优性质
- 坐标型的下标模式
- 切割后的三种情况: 首字母不相关, 末尾字母不相干, 首字母和末尾字母都相干.

#### 三把斧
- 中间劈开
- 砍断首或尾
- Range区间作为iteration的根本

#### 难点
- 计算顺序: 不能按照i的顺序去算!!! 掐头/去尾的时候, 有 [i+1], 也有[i], 所以不能按照i
- 应该: 按照 **区间长度从小到大** 的顺序去算:
- 按照区间: 长度, 长度, 长度!
```
for (len = ..; len <= n; len++) {
	for (int i = 0; i <= n; i++) {
		int j = i + len - 1;
		...
		...
	}
}
```

### Bitwise Operation DP


### 优化
- 空间优化: 1. Rolling array (easy) . 2. 观察dp的计算顺序, 看双行的左右计算方向, 找是否有舍弃不用的格子, 可以降维
- 考虑空间降维优化时, 不必要死记硬背, 画一画, 看有没有优化的可能
- 时间优化: 分析重复计算, 争取减少.


## Minimax/MaxiMin



## Optimization problems
- memoization && subproblems
- Fibonacci
- Shortest paths
- guessing && DAG View

## Double Sequence
- Sequence problem, have dp[] length of n + 1.
- Look at last index for clues
- Usually can start for loop at index = 0, and we handle the init conditions within the for loop (ex: assign particular value or skip i=0 rows)
- Rolling array (curr, prev pointer) to optimize space; note the rolling dimension should be apply at the top-for loop.

## 存状态
- 复杂的dp, 有时候会需要存一个状态: 拿/不拿, 放1/0, 输/赢
- 通常加上一个维度


# 记忆化搜索 Memoization DP
- 本质是DP, 所有DP也都是为了解决重复计算
- 计算 f(0, N-1), 递归, recursive: 你要求什么, 直接上!
- 走过的老路, 就不要走了. f(i, j) 结束后, 存在数组 f[i][j]里.
- **从大到小**搜索, **自顶向下**, 其实是暴利解决的思路, 只是在深入到底的的过程中存了状态, 不需要重复计算.
- 时间复杂度跟递推的DP一样: O((n^2)/2)

## 什么时候用记忆化搜索
- 1. 状态转移特别麻烦，不是顺序性; 
- 2. 初始化状态不是很容易找到; 
- 3. 从大到小
- 区间搜索(Interval dp), 适合用 memoization, 因为计算顺序稍微比较难

## 特点
- 需要开全局变量
- 需要初始化: Mark算过的, 和没算过的
- 递归里面: 第一行, 一定要记得查visited, 记忆化搜索的精髓. 算过了, 直接返回

## 缺点
- 不能空间优化, 所有值必须记下来

## 时间空间复杂度的节省
- 比如一个binary tree, 全部traverse 下来, 有将近 O(2^n) nodes; 如果存结果, 可能只需要visit unique的 O(n) nodes

# Search

## Breadth-first Search
Track queue size, use the queue as in rotation

## Depth-first Search
- backtracking: do not repeatly visit a node
- DFS traverse O(n)

# Backtracking
- Finding all (or some) solutions to some computational problems, notebaly constraint satisfaction problems
- It attemps to build/find all candidates and abandon partial candidate when the candidates appears not to be suitable(backtracking, backing off from wrong candidates)


# Reservoir Sampling

# Geometry

# Brainteaser

# Operating system
- processes
- threads
- concurrency issues
- locks
- mutexes
- semaphores
- monitors
- deadlock, livelock and how to avoid
- what resources a process and a thread needs. 
- how context switching works, how it's initiated by the operating system and underlying hardware. 
- scheduling and the fundamentals of "modern" concurrency constructs.

# Threads
## Two approaches
- Implement the java.lang.Runnable interface
- Extend the java.lang.Thread class

# Approach

## Greedy

## Divide and Conquer

## Recursion
- Recursion 至少用O(n) space, n = depth of recursive call.
- 所有recursive problem 都可以 interatively 解决, 但是有可能代码更复杂
- ex: dfs
- always find the entry point or terminating point
- watch out for the return or result of recursed function

### 特征
- "compute nth ...", "list the first n ...", "compute all ..." 常常是recursive solution.
- space inefficient. 占用空间, at least O(n)

# Design

# BigO
## Specials
- logN Runtime: when space gets halved each time, it indicates runtime of O(logN)
- Recursive Runtime: When the recursive call makes x branches, the runtime is likely to be O(x ^ N).  [it's not always]
- Note: the base x of the recursive runtime DOES MATTER! 2^n is very different from 8^n = 2^3n

# Data Structure
- Union Find
- Trie
- Heap: PriorityQueue[Java]. Made of: Binary Heap
- Hash: HashMap[Java]. Made of: HashMap
- TreeMap: TreeMap[Java]. Balanced Binary Tree
- TreeSet: TreeSet[Java]. Balanced Binary Tree

# Problem Sets

## permutation
### 原理
- Permutation的核心: 对于某一个index, 取, 或者不取
- DFS的时候, 注意, 可能要从 index = 0 重新开始permutate
- 用 boolean visited[] 来track, 上一轮visited过得index

### Example
- Find small string's permudation in large string: time O(n), using a HashSet of missingString: when the set.length == 0, that is a match of small string.


## Two Pointer

## Min/Max Heap
- 见到需要维护一个集合的最小值/最大值的时候要想到用堆
- 第k小的元素，Heap用来维护当前候选集合
- 见到数组要想到先排序

## 回文串 Palindrome
- 奇数串, 中间有个unique字符
- 偶数, 中间开始有两个相同的字符
- 找到所有的回文串, 只需要 O(n^2): isPalin[i][j]表示 S[i...j] 是否是palindrome

## Stack
- Monotonous stack的运用: 找左边和右边第一个比它大的元素
- 递归转非递归

## Windows Problem
- 加一个数
- 删一个数

## Sweep Line
- 见到区间需要排序就可以考虑扫描线
- 事件往往是以区间的形式存在
- 区间两端代表事件的开始和结束
- 需要排序
- Meeting Room I, II


# Pain Point
- For any array access, make sure to check the boundary!!!

## NP-Complete problems

## Basics
- Coding, speeding, readability
- Data structure: given a problem, how to model it into data structure? What type of class interface to use? 这个很重要, 如果不能很好地model一个问题, 后面就不知道该怎么写, 一定懵逼.
- Algorithm: How do we solve the problem.
- communication: think, and communicate ideas
- : colon | ; semicolon | ! exclamation mark | { curly bracket } | [ square bracket] | ( parentheses )

## Edge case
- consider edge case
- 解释edge case的原因; 也可以throw exception呀

## Advanced
- 写出的function, 加入要解决更大scale的问题, 比如说call 10k 遍, 是否有冗余计算或者空间? 如何优化?




