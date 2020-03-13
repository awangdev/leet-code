
Table of Contents
=================

   * [Table of Contents](#table-of-contents)
   * [基础知识](#基础知识)
      * [1. 套路](#1-套路)
      * [2. Java Collections](#2-java-collections)
         * [Collections.sort()](#collectionssort)
         * [ArrayList](#arraylist)
      * [3. String](#3-string)
         * [Instinct](#instinct)
         * [String Functions](#string-functions)
         * [Characters](#characters)
         * [StringBuffer](#stringbuffer)
      * [4. Customized Data Model](#4-customized-data-model)
      * [5. BigO](#5-bigo)
      * [6. Time Complexity of graph/dfs block](#6-time-complexity-of-graphdfs-block)
      * [7. Math](#7-math)
         * [Math Functions](#math-functions)
         * [Numbers](#numbers)
         * [Probability theory](#probability-theory)
         * [Combinatorics](#combinatorics)
         * [subset time&amp;&amp;space](#subset-timespace)
      * [8. Bit Manipulation](#8-bit-manipulation)
      * [9. Threads](#9-threads)
   * [Basic Data Structure](#basic-data-structure)
      * [1. Array](#1-array)
         * [Honorable Problems](#honorable-problems)
      * [2. Hash Table](#2-hash-table)
         * [HashTable](#hashtable)
         * [HashSet](#hashset)
         * [HashMap](#hashmap)
      * [3. Heap](#3-heap)
         * [Insert](#insert)
         * [Extract Minimum Element](#extract-minimum-element)
         * [如何想到用 Min/Max Heap](#如何想到用-minmax-heap)
         * [Example](#example)
      * [4. Stack](#4-stack)
         * [Functions](#functions)
         * [基本用法](#基本用法)
         * [Monotonous Stack](#monotonous-stack)
         * [Example](#example-1)
      * [5. Queue](#5-queue)
         * [Functions](#functions-1)
      * [6. Linked List](#6-linked-list)
         * [考法](#考法)
      * [7. Deque](#7-deque)
   * [Advanced Data Structure (A): I am groot, a tree](#advanced-data-structure-a-i-am-groot-a-tree)
      * [1. Types of Tree, Basic knowledge](#1-types-of-tree-basic-knowledge)
         * [Definition](#definition)
         * [Balanced bianry tree](#balanced-bianry-tree)
         * [Complete binary tree](#complete-binary-tree)
         * [Full Binary Tree](#full-binary-tree)
         * [Perfect Binary Tree](#perfect-binary-tree)
      * [2. Traversal](#2-traversal)
         * [Binary Tree Traversal](#binary-tree-traversal)
         * [Inorder Traversal](#inorder-traversal)
      * [3. Binary Search Tree (BST)](#3-binary-search-tree-bst)
         * [Is it Binary Tree!?](#is-it-binary-tree)
         * [TreeSet](#treeset)
         * [Traversal](#traversal)
      * [4. Expression Tree](#4-expression-tree)
         * [Example](#example-2)
         * [Build Tree](#build-tree)
      * [5. Trie](#5-trie)
         * [用法/考点](#用法考点)
         * [Compare with HashMap](#compare-with-hashmap)
         * [Code Model/ Sample Functions](#code-model-sample-functions)
      * [6. Binary Indexed Tree](#6-binary-indexed-tree)
      * [7. Segment Tree](#7-segment-tree)
         * [基本知识](#基本知识)
         * [functions](#functions-2)
         * [用法](#用法)
      * [8. Red Black Tree](#8-red-black-tree)
            * [基本知识](#基本知识-1)
            * [特点](#特点)
            * [引申特点](#引申特点)
            * [用法](#用法-1)
      * [9. B-Tree](#9-b-tree)
         * [基本知识](#基本知识-2)
      * [10. AVL Tree](#10-avl-tree)
         * [基本知识](#基本知识-3)
         * [更多细节特点](#更多细节特点)
         * [用途](#用途)
         * [优点](#优点)
   * [Advanced Data Structure (B): Graph](#advanced-data-structure-b-graph)
      * [构建Graph](#构建graph)
      * [Popular algorithms](#popular-algorithms)
         * [Adjacency List](#adjacency-list)
         * [Adjacency Matrices](#adjacency-matrices)
      * [Graph Search](#graph-search)
         * [DFS](#dfs)
         * [BFS](#bfs)
         * [Bidirectional Search](#bidirectional-search)
   * [Basic Algorithms](#basic-algorithms)
      * [1. Two Pointers](#1-two-pointers)
      * [2. Binary Search](#2-binary-search)
         * [Template](#template)
         * [中二思想](#中二思想)
      * [3. Sort](#3-sort)
         * [常用](#常用)
         * [Merge Sort](#merge-sort)
         * [Heap Sort](#heap-sort)
         * [Quick Sort](#quick-sort)
         * [Quick Select](#quick-select)
         * [Comparator for Arrays, Collections](#comparator-for-arrays-collections)
      * [4. BFS/DFS Search](#4-bfsdfs-search)
         * [Breadth-first Search](#breadth-first-search)
         * [Depth-first Search](#depth-first-search)
            * [for loop dfs vs. pick&amp;&amp;skip dfs](#for-loop-dfs-vs-pickskip-dfs)
            * [void dfs](#void-dfs)
            * [regular primitive dfs](#regular-primitive-dfs)
            * [object dfs](#object-dfs)
            * [Tree DFS](#tree-dfs)
      * [5. Backtracking](#5-backtracking)
      * [6. Greedy](#6-greedy)
         * [When to use](#when-to-use)
         * [Examples](#examples)
      * [7. Divide and Conquer](#7-divide-and-conquer)
      * [8. Recursion](#8-recursion)
   * [Advanced Algorithm (A): Union Find](#advanced-algorithm-a-union-find)
      * [Basics](#basics)
         * [UnionFind基础操作](#unionfind基础操作)
         * [UnionFind follow up](#unionfind-follow-up)
   * [Advanced Algorithm (B): Topological Sort](#advanced-algorithm-b-topological-sort)
      * [建立Graph   InDegree](#建立graph--indegree)
      * [Topological Sort - BFS](#topological-sort---bfs)
      * [Topological Sort - DFS](#topological-sort---dfs)
   * [Advanced Algorithm (C): Bucket Sort](#advanced-algorithm-c-bucket-sort)
   * [Advanced Algorithm (D): DP, Dynamic Programming](#advanced-algorithm-d-dp-dynamic-programming)
      * [1. Basics](#1-basics)
         * [判断](#判断)
         * [四个步骤:](#四个步骤)
               * [确定状态](#确定状态)
               * [转移方程](#转移方程)
               * [初始条件/边界情况](#初始条件边界情况)
               * [计算顺序](#计算顺序)
         * [Technique](#technique)
         * [基本原理](#基本原理)
      * [2. 分类](#2-分类)
         * [a. 网格坐标 (Coordinate)](#a-网格坐标-coordinate)
         * [b. 序列 (Sequence)](#b-序列-sequence)
            * [特点](#特点-1)
            * [性质](#性质)
            * [关键点](#关键点)
            * [序列加状态](#序列加状态)
         * [c. 划分(Partition)](#c-划分partition)
            * [性质](#性质-1)
            * [经验](#经验)
            * [解决方法](#解决方法)
         * [d. 博弈类 (Game Theory)](#d-博弈类-game-theory)
         * [e. 背包类 (Backpack)](#e-背包类-backpack)
            * [多种问法](#多种问法)
            * [方法策略](#方法策略)
            * [放入的物品没有顺序](#放入的物品没有顺序)
         * [f. 区间类(Interval DP)](#f-区间类interval-dp)
            * [特点](#特点-2)
            * [三把斧](#三把斧)
            * [难点](#难点)
         * [g. Bitwise Operation DP](#g-bitwise-operation-dp)
      * [3. DP典型罗列](#3-dp典型罗列)
         * [Minimax/MaxiMin](#minimaxmaximin)
         * [Optimization problems](#optimization-problems)
         * [Double Sequence](#double-sequence)
         * [存状态](#存状态)
      * [4. 记忆化搜索 Memoization DP](#4-记忆化搜索-memoization-dp)
         * [什么时候用记忆化搜索](#什么时候用记忆化搜索)
         * [特点](#特点-3)
         * [缺点](#缺点)
         * [时间空间复杂度的节省](#时间空间复杂度的节省)
   * [Advanced Algorithm (E): Sweep Line](#advanced-algorithm-e-sweep-line)
   * [问题类型分类](#问题类型分类)
      * [1. Combinatorics 组合](#1-combinatorics-组合)
         * [DFS 思想](#dfs-思想)
         * [DFS 注意](#dfs-注意)
      * [2. Permutation 排列](#2-permutation-排列)
         * [原理](#原理)
         * [Backtracking DFS (Recursive)](#backtracking-dfs-recursive)
         * [插入法 (iterative)](#插入法-iterative)
      * [3. 回文串 Palindrome](#3-回文串-palindrome)
      * [4. Windows Problem](#4-windows-problem)
      * [5. Sum, PrefixSum](#5-sum-prefixsum)
   * [知识储备](#知识储备)
      * [0. Brainteaser](#0-brainteaser)
      * [1. Operating system](#1-operating-system)
      * [2. Java Garbage Collections](#2-java-garbage-collections)
         * [Heap](#heap)
         * [Garbage Collection Roots](#garbage-collection-roots)
         * [Example](#example-3)
         * [Marking and Sweeping Carbage](#marking-and-sweeping-carbage)
      * [3. Pain Point](#3-pain-point)
      * [4. NP-Complete problems](#4-np-complete-problems)
         * [wiki](#wiki)
         * [Knapsack](#knapsack)
         * [Travelling salesman](#travelling-salesman)
      * [5. Java Design Pattern](#5-java-design-pattern)
   * [Uknowns](#uknowns)
      * [Geometry](#geometry)
      * [Advanced Algorithm (F): Reservoir Sampling](#advanced-algorithm-f-reservoir-sampling)



# 基础知识
## 1. 套路
- **Code accuracy, speed, test, maintanability**
- **Try to solve new problem: don't try to link with old problems**
- **Analyze**
  - Draw examples & walk through use cases:
      - edge cases? ask if we want to return empty to throw exception? [Don't over do. Maybe your code already handles it]
  - Does the example assemble certain pattern? sort, search, O(1) lookup?
  - Does the example requires `graph` relationship: i.e., `from x to y`.
  - Summarize a rough idea of how to approach/solve
- **Time Complexity it ASAP**
   - Analyze BigO of the proposed solution before jumping into coding
- **Data Modeling**: given a vague problem, model it with data structure. Rule of thumb: 
   1. keep it simple. (i.e. `int[2]{x,y}` can be sufficient a lot times) 
   2. don't be shy from creating an class/object (CC does it all the time)
- **List a few approaches/algorithms** 
   1. DO NOT dive into 1 approach too fast
   2. Also DO NOT come up with too many redundant approaches
   3. Keep in mind: we just want to compare approach, and pick a better one.
- **Communication**
   1. FIRST: share & illustrate workflow on side
   2. `: colon`, `; semicolon`, `! exclamation mark`, `{curly bracket}`, `[square bracket]`, `(parentheses)`
- **Instincts**
  1. O(1) read/write: `hash`; edge/relationship: `graph`; enumeration: `BFS or DFS` and sometimes with `memo`
  1. When solving max/min num, possibility ... with recursive, keep in mind about `memo` and `dp`
  1. Shortest path: likely BFS
  1. Besides regular list, map; keep queue & stack in mind.
  1. If the function is designed for large scale problem (calling it 100k times): any resource to optimize?
  1. Precompute resource: if a server machine (w/ storage, cache, or enough memory) exists, it indicates: 1) precompute, 2) use the result/resource repeatedly.
- **mistakes**
   - When you mean to say `table`, don't say `database`
   - When you mean you want to store the data in ordered structure (like a TreeMap in java, basically BST): DON'T say you want to sort it.
   - When people ask for concurrency and transtional, that leads to SQL; keep in mind to rethink if that is the database you want to choose.

## 2. Java Collections
### Collections.sort()
- Sort sub list: `Collections.sort(list.subList(x, y))`. [x, y), exclusive at index y.

### ArrayList
- Create list from array: `new ArrayList(Arrays.asList(1, 2, 3))`
- Remove range: `list.removeRange[x, y)`
- List Insertion Time O(1) on average
   1. When list fills up: the insertion cause the arrayList to: 1) doubles the size, 2) copy all contents to a new array. 
   2. Overall: the insertion cost is 1 + 2 + 4 + ... n/8 + n/4 + n/2 = O(n)
   3. Therefore, the average insertion time is O(n)/ n = O(1)

## 3. String
### Instinct
- Analyze string pattern and parse (i.e. `(A|B)&(C|D)`, letter order)
- Pay attention to repeated strings
### String Functions
- lexicographically字典order排序: `String.compareTo("XYZ")`
- split by: `str.split("\\ ")`, 需要用 "\\" regular expression
- remove space: `s.trim()`
### Characters
   1. `s.toCharArray()`
   2. count characters with int[256](skip `c-'a'`)
   3. `String.valueOf(charArrary)`
   4. `Character.isDigit(x)`
### StringBuffer
- regular: `sb.reverse()`, `sb.append()`, `sb.length()`
- index manipulation: `sb.deleteCharAt(i)`, `sb.setCharAt(i, c)`, `sb.insert(0, "xyz")`
- replace range of string: `sb.replace(i, j, "replacement string")`
### Sliding Window
- refert to the algorithm section for sliding window

### Palindromic String/Subsequence
- 516. Longest Palindromic Subsequence. Use DP to calc subsequence
- 5. Longest Palindromic Substring. Use same DP template, but restrict by substring rules.
- template
```
// 1) init
int/boolean dp[][] = new int/boolean[n][n];
// 2) process from back with `i = n -1`, `j = i + 1`
for (int i = n - 1; i >= 0; i--) {
   // 3) aways init dp[i][j]: single char usually has default property.
   dp[i][i] = xxx;
   for (int j = i + 1; j < n; j++) {
      // 4) handle match, or not match cases
      if (s.charAt(i) == s.charAt(j)) {
         // utilize dp[i + 1][j - 1], 
         // sometimes dp[i][j - 1] or dp[i + 1][j]
      } else {
         // do someting else if not matching
      }
   }
}
```

## 4. Customized Data Model
- **common use case**: in dfs or other algorithm, need to pass around more data than a single premitive variable
- **example**: `Binary Tree Maximum Path Sum`: need to track single path, and combinded path. 把每个node的status, 存在一个 customized object 里面, pass around
```
private class PathSum {
    int singlePathMax;
    int combinedPathMax;
    PathSum(int singlePathMax, int combinedPathMax) {
        this.singlePathMax = singlePathMax;
        this.combinedPathMax = combinedPathMax;
    }
}
```

## 5. BigO
- **O(logN)**: when space/runtime cut in halved iteration, it indicates runtime of O(logN)
- **O(x ^ N)**: Recursive Runtime, when the recursive call makes x branches, the runtime is likely to be O(x ^ N).  [it's not always]
  - Important: the base x of the recursive runtime DOES MATTER! 2^n is very different from 8^n = 2^3n
- With recursion: if we use `memoization` to stores n results and return at revisiting
  - overall runtime can be O(N)
  - we only calculate the specific N node and N's children nodes exactly ONCE.

## 6. Time Complexity of graph/dfs
- dfs of graph: 每个level, 每个node衍生出 x 个 child, 然后不断衍生 n 遍截止: 整个structure的所有node总和: `x^n`
- 如果是个binary tree, 每个node出 x = 2 child, 然后 height = n, 那么node总量就是 `2^n`
- 从公式角度: 
   - 假设T(n)是每一个level的node总数
   - T(n) = x * T(n-1), 每一个node衍生 x children
   - 那么 T(n) = x * (x * T(n - 2)) = .... = x^(n - 1) * T(1)
   - => BigO time will be `O(x^n)`
- Again, sometimes with `memoization`, we can reduce runtime to `O(n)` in certain problems

## 7. Math
- 转换成character: `'0' + num`
- % mod, 除法
- Integer.MAX_VALUE, Integer.MIN_VALUE; if overflow, use long
- Integer.valueOf(number), where number is int

### Math Functions
- Math.pow(x, 3) = x ^ 3; Math.pow(x, 1/3) = x ^ (1/3)

### Numbers
- Long a = 10; a.intValue() => int
- Integer: Integer.parseInt("123")
- Sometimes it's good to use `Integer[] var` because `var[i] can be null`
- parse binary string into integer: Integer.parseInt("010", 2) = 2;

### Probability theory

### Combinatorics
- a selection of items from a collection, and order does not matter
- The complexity is `O(C(n,k)): O(min(n^k, n^(n-k)))`

### subset time&&space
- independent choice of either pick&&not pick. You pick n times: `O(2^n)`


## 8. Bit Manipulation
- Bit OR |, AND &, XOR ^
- Bit shift: <<, >>; the result of shift has to be stored : `a = a >> 1`;
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

## 9. Threads
Two approaches:
- Implement the java.lang.Runnable interface
- Extend the java.lang.Thread class



# Basic Data Structure
## 1. Array
- Sorted? value boundary?
- 遇到需要排序: Arrays.sort()
- Arrays.asList([1,2,3]);
- Partial sort: Arrays.sort(arr, 0, arr.length())
- Copy: Arrays.copyOf(arr, arr.length())
- Arrays.toString(int[] arr) => string representation: "[1,2,3,4]"
- 见到数组要想到: 是否可以/需要先排序?

### Honorable Problems
- Median of two sorted arrays: find kth element of two sorted array where k = n/2. Recursive findKth(A[],indexA, B[], indexB, k)

## 2. Hash Table
### HashTable
- Ex: when searching unsorted array(if you try to avoid sort O(nlogN)), probably can index with HashMap
- Can be used to store character/string frequency
- Hash: HashMap[Java]. Made of: HashMap
- TreeMap: TreeMap[Java]. Balanced Binary Tree
- TreeSet: TreeSet[Java]. Balanced Binary Tree

### HashSet
- contains: O(1)
- set.add(...) returns false if there is duplicate. This operation won't change the existing set.
- Build HashSet<List> set, and the set will automatically compare the equivalence of the lists within at each list element level.

### HashMap
- Use iterator: `Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();`
- `iter.hasNext()` and `iter.next();`

## 3. Heap 
- min-heap && max-heap has same concept
- min-heap is complete binary tree (right-most elements on last level may not be filled)
- each node is smaller than it's children
- root is the minimum value of tree
- Maintaing min-heap is about swaping node values
- Insert/extract min value both take O(logn) time
- PriorityQueue[Java]. Made of: Binary Heap (看Binary Tree section)
   - PQ can be used to sort on multiple attributes (i.e., `Pair {int dist, workerIndex, bikeIndex}` in 1057. Campus Bike). 

### Insert
- insert at bottom right-most spot
- swap with parent if value not fitting min-heap
- swapping until min value reaches root
- O(logn) to bubble up to top

### Extract Minimum Element
- extract root value (easy)
- set the the root value to be bottom-right-most element, also remove that bottom element
- bubble down the root value if not fitting min-heap
- overal efforts to bubble down: O(logn)

### 如何想到用 Min/Max Heap
- 见到需要维护一个集合的最小值/最大值的时候要想到用堆 (看到Min/Max就要想到heap)
- 看到median 想到heap
- 第k小的元素，Heap用来维护当前候选集合
- 如果给出的数组没有排序, 先排序, 然后用heap. 

### Example
- Given n items, find first k smallest items (`k closest point to the origin`):
- you would think about putting all n items into a min-heap(priorityQueue), and output top k by polling. That will be O(nlogn)
- Could do better: 1. use max-heap to store first k items; 2. if any value less than max, replace max with new value.
- Result is: always keep the smaller items in the max-heap, and replace the head/max.
- therefore, building the queue is like O(nlogk), saved space and time


## 4. Stack

### Functions
- peek(), pop(), push()

### 基本用法
- store something on top and you may remove soon
- 翻转stack
- stack优化DFS, 变成非递归
- Stack can be implemented with LinkedList, adding/removing from same side of the list
- Monotonous stack的运用: 找左边和右边第一个比它大的元素
- 递归转非递归

### Monotonous Stack
- 找每个元素左边或者右边 第一个比它自身小/大的元素
- 用单调栈来维护
- 维护monotonous stack 是题目需要, 而不是stack本身性质. 
- 比如, 题目需要stack.peek() O(1), 加上需要单调递增/递减的性质, 就用起来了stack.
```
// Use monotonous stack to build minimum binary tree
// 1. Create that new node
TreeNode ndoe = new TreeNode(val); 
// 2. The stack is monotonous, so loop all items >= node.val, and set as left child.
// monotonous: continuous increasing or decreasing, so the loop will end at some point.
while (!stack.isEmpty() && node.val <= stack.peek().val) { 
    node.left = stack.pop();
}
// 3. The item left in stack is < node.val, so node should be a child.
if (!stack.isEmpty()) {//build right node of the tree
    stack.peek().right = node;
}
// 4. Every new node needs to be tested. push to stack.
stack.push(node);

// End result: parentheses will not be in the tree
             [ - ]
         /          \
    [ * ]              [ / ]
  /     \           /         \
[ 2 ]  [ 6 ]      [ + ]        [ + ]
                 /    \       /      \
               [ 23 ][ 7 ] [ 1 ]   [ 2 ] .
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

### Example
- Maximum Binary Tree, Largest Rectangle In Histogram, Maximal Rectangle (in 2D array)
- Expression Tree (Minimum Binary Tree)


## 5. Queue
### Functions
- peek(), poll(), add()/offer(), remove(object)
- queue = new LinkedList<...>()
- PriorityQueue: new Comparator 很重要
- PriorityQueue 用完的item(top poll item 可能用完后 attribute有所变化): **如果还要继续用, 那就把item add回queue里**.
- Queue 可以用 LinkedList 实现. Add from the last/end of the list; Return/remove from the head of the list. 

## 6. Linked List
- No concept of size(), it's all pointers: node.next.next
- how to set head/dummy, and return dummy.next as result.
- iterate over linked list
- Don't get mixed up with Java LinkedList. Here we are talking about linked list concept, not the Java data structure LinkedList

### 考法
- Reverse Linked List: 不断地在开头加上新node. 比较方便的方式: 用一个dummy node, 然后把reversed list 存在dummy.next
- Reverse linked list 注意: 有时候一开始的1st node, 最后还有tail 要接在上面; 所以先把1st node可以额外存下来, 用来接 tail
- 找到mid node: 快慢指针(slow = head; fast=head.next); 快指针每次走2步; 快指针到底的时候, slow指针就是mid
- merge two linked list: 用一个dummy head, 然后不断轮流加next (取决于merge的规则, 可能链接方法不同)

## 7. Deque
- linear collection that supports insertion and removal at both ends. Pronounced 'deck'
- It's a queue && stack
- new ArrayDeque<Integer>()
- head/top: offerFirst(), pollFirst(), peekFirst()
- tail/bottom: offerLast(), pollLast(), peekLast() 
- 双端queue: 维护一个候选可能的最大值集合
- ex: Sliding WIndow Maximum

## 8. TreeMap and TreeSet
### TreeMap
- https://www.tutorialspoint.com/Difference-between-TreeMap-HashMap-and-LinkedHashMap-in-Java
- TreeMap is implemented as a self-balanced tree, but basic operations (lookup,insertion) are all O(logn)
- log(n) search to find floor(key), ceiling(key)
   - ceilingKey(K key): Returns the least key greater than or equal to the given key, or null if there is no such key.
   - floorKey(K key): Returns the greatest key less than or equal to the given key, or null if there is no such key.

### TreeSet
- https://dzone.com/articles/hashset-vs-treeset-vs
- TreeSet is implemented as tree, basic operation is O(logn)
- Useful for using higher(x), lower(x) function
   - 	tailSet(E fromElement): Returns a view of the portion of this set whose elements are greater than or equal to fromElement.



# Advanced Data Structure (A): I am groot, a tree

## 1. Types of Tree, Basic knowledge

### Definition
- A simple version of graph
- CAN NOT have cycle
- Can have a list of children
- 一般不会用Tree class 来实现tree, 一般都是用 TreeNode root as reference
- leaf: very end, 没孩子

### Algorithm Approaches
- Top-Down Recursive (1026, 1008): 
   - parent/root node carries some values and pass down to sub recursive functions
   - sometimes it is easy to write: pass value as dfs parameters
- Bottom-Up Recursive: 
   - find certain value, or certain set of value from the root -> up
   - like segment tree updates from bottom

### Balanced bianry tree
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

## 2. Traversal

### Binary Tree Traversal
- preorder, inorder, post-order
- inorder more often
- draw the map
- can implement with dfs, bfs

### Inorder Traversal
- DFS: check leaf => dfs left => process root => dfs right
- stack: in while loop: deep dive to left leaf => stack.pop() => `node = node.right`
```
stack.push(root);
TreeNode node = root;
while(!stack.isEmpty()) {
   //Left first
   while (node != null && node.left != null) { 
       stack.add(node.left);
       node = node.left;
   }
   //Process left/curr
   node = stack.pop();
   
   // do something with node

   node = node.right; // VERY IMPORTANT
   if (node != null) {
       stack.push(node);
   }
}
```
- `node = node.right` is critical, otherwise it'll be in infinite loop
- alternatively: we could set left = null, but that's disruptive to original structure, not recommended.



## 3. Binary Search Tree (BST)
- If BST not given, can use TreeSet
- All left nodes are less than current node.val; all right nodes are greater than curr node.val
- Use DFS to traverse: divide and conquer. Similarly, usually can convert the DFS solution to interative solution.
- Use stack to traverse iteratively

### Is it Binary Tree!?
- 一定要问清楚, 是Binary Tree (双孩子而已), 还是 Binary Search Tree. 非常重要!!!
- 一个child tree的nodes总量是 2 ^ h - 1; 那么一个child tree + root 的nodes 总量就是 2 ^ h了.

### TreeSet
- 如果BST treenode没给, 可以用TreeSet
- TreeSet还是一个set, 存values, 而好处是可以用 `treeSet.ceiling(x)` 找到 最小 >= x的值
- 同样, 找 <= x 的value, 用 `treeSet.floor(x)`
- strict less or greater: `treeSet.lower(x)`, `treeSet.higher(x)`
- time O(nlogn)

### Traversal
- Inorder BST traversal: 从小到大排序的ouput

## 4. Expression Tree
- Binary tree, used to evaluate certain expression
- All leaf nodes of the expression tree have a number/string value.
- All non-leaf node of the expression tree have an operation string value.

### Example
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
- Expression Evaluation, Expression Tree Build
- Basic Calculator

### Build Tree
- Use Monotonous Stack to build Minimum Binary Tree
- Use weight to associate parentheses, signs, numbers
- Write getWeight(base, String s) function to calculate the weight.

## 5. Trie
- 一个字母一个字母查找，快速判断前缀
- Prefix Tree
- n-ary tree
- Can tell if string is a valid prefix in O(K) time, k = str.length
- example: Word Search II

### 用法/考点
- Autocomplete
- Spell check
- IP routing
- T9 text prediction (old NOKIA phone)
- solve world game
- 一个一个字母遍历
- 需要节约空间
- 查找前缀

### Compare with HashMap
- Trie can help find all strings with prefix
- Trie can validate a list of words
- Trie can enumerate a data set of strings in lexicographical order
- Trie saves space because of the prefix
- Trie can potentially faster than hashMap, when there are lots collisions for the map.

### Code Model/ Sample Functions
- children map: Map<Character, TrieNode>. Also can use char[26], but it's more scalable to us a map.
- always have isEnd which marks a end of a particular string
- insert
- search
- exist of prefix
- node when the prefix end

## 6. Binary Indexed Tree
- some clue here: https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/

## 7. Segment Tree
### 基本知识
- Also called, Interval Tree. Top-Down的一个tree (https://en.wikipedia.org/wiki/Segment_tree, https://en.wikipedia.org/wiki/Interval_tree)
   - Build: O(n). 做出来是一个balanced的tree (因而是logn查找)
   - Update: O(logn)
   - Range Query: O(logn + k)
- range query: 想到segment tree
- NOT working for: "增加元素", segment tree在build之后无法变更
```
public class SegmentTreeNode {
    public int start, end, max;
    public SegmentTreeNode left, right;
    public SegmentTreeNode(int start, int end, int max) {
        this.start = start;
        this.end = end;
        this.max = max; // sum, max, min
        this.left = this.right = null;
    }
}
/*
Given [3,2,1,4]. The segment tree will be:

                        [0,  3] (max = 4)
                  /                          \
        [0,  1] (max = 3)              [2, 3]  (max = 4)
            /        \                      /         \
[0, 0](max = 3)  [1, 1](max = 2)    [2, 2](max = 1)   [3, 3] (max = 4)
*/
```
- Each segment tree node needs to possess some value to be useful. 

### functions
- build: divide and conquer; each step record necessary value: `count`, `min`, `max`, `sum`
- query: `mid = (root.start + root.end) / 2`, and compare target start/end with mid; call query() recursively

### 用法
- which of these intervals contain a given point
- which of these points are in a given interval
- track max in the range
- track count of nodes in the range

## 8. Red Black Tree
#### 基本知识
- one kind of self-balancing binary search tree
- Each node of the binary tree has an extra bit, and that bit is often interpreted as the color (red or black) of the node
#- search O(logn), n = total # of nodes in the tree
- deletion/insertion/tree rearrangement && coloring: O(logn)

#### 特点
- Each node is either red or black.
- The root is black. (This rule is sometimes omitted)
- All leaves (NIL) are black.
- If a node is red, then both its children are black.
- Every path from a given node to any of its descendant NIL nodes contains the same number of black nodes.

#### 引申特点
- the path from the root to the farthest leaf is no more than twice as long as the path from the root to the nearest leaf.

#### 用法
- offer worst-case guarantees for insertion time, deletion time, and search time
- used in time-sensitive application, real-time application
- valuble building block in other data structures
- valuble in functional programming: most common `persistent data structure`


## 9. B-Tree
### 基本知识
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

## 10. AVL Tree
### 基本知识
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

### 用途
- Storage system than read/write large blocks of data.
- Commonly used in database, filesystem

### 优点
- keeps keys in sorted order for sequential traversing
- uses a hierarchical index to minimize the number of disk reads
- uses partially full blocks to speed insertions and deletions
- keeps the index balanced with a recursive algorithm

# Advanced Data Structure (B): Graph
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

## 构建Graph
- 建立edges: 1. 创建所有node -> neighbor list; 2. 把满足条件的neighbor 填进去(每个题目不同)
   - adjacency list graph: `Map<Integer, List<Integer>>`
- 如果是做Topological Sort, 还要建立 `inDegree`: `Map<node, Integer>`, 或者就是 `int[]`

## Popular algorithms
- Topological sort: figure out if cycle exist (course schedule), output order (course schedule II, Alien Dictinary)
- Union Find: count unions (Number of islands I, II)
- Given a matrix, or some structure: consider graph data structure, and convert into graph `Map<node, neighbors>`

### Adjacency List
- The graph can be presented with a graph class, which is just a list of node, a map of nodes
- Or it can me an array (or a hash table) of lists (arrays, arraylists, linked lists ... etc)
- each node has it's own propety, and a list of adjacent nodes
- This graph can be directed, or undirected (modeled by adjacent node list in each Node)

Example:
- 261. Graph Valid Tree
- 很多题目里面都是用 `Map<node, neighbors>`, 或者 `List[] edges` 来表示graph
- Course Schedule, Alien Dictionary
```

class Graph {
   public Node[] nodes;
}

class Node {
   public String name; // property of the node
   public Node[] children; // children nodes
}
```
- Also, adjacent list:
```
// Use Hashmap
// Number of Connected Components in an Undirected Graph
private Map<Integer, List<Integer>> buildGraph(int n, int[][] edges) {
     Map<Integer, List<Integer>> graph = new HashMap<>();
     for (int i = 0; i < n; i++) {
         if (!graph.containsKey(i)) {
             graph.put(i, new ArrayList<>());
         }
     }
     for (int[] edge: edges) {
         graph.get(edge[0]).add(edge[1]);
         graph.get(edge[1]).add(edge[0]);
     }
     return graph;
 }
```

### Adjacency Matrices
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

# Basic Algorithms
## 1. Two Pointers
- Two pointers in array
- Two pointers in Linked List

### Two Pointers from both sides, narrowing to middle

### Start catching up to End pointer
- End moves early/faster
- Start will catch up to end
- Ex: 76. Minimum Window Substring

### Source Pointer and Target Pointer
- Find target `subsequence` in source by comparing sIndex and tIndex
- Backtrack both pointers: tIndex to 0, sIndex to 1st valid matched char
- Ex: 727. Minimum Window Subsequence

### Sliding Window
- refer to the sliding window section

## 2. Binary Search
### Template
- 二分的template
- 按值二分，需要怎么二分性
- Steps: 
   - 1. 找到可能的解的mid;
   - 2. 猜答案; 
   - 3. 检验答案 (match/if/else ...); 
   - 4. 调整搜索范围
   - *** 画图, 标记mid的位置, 帮助自己写 if/else ***
- Find Peak Element II

### 中二思想
- 往往不会有个数组让我们二分, 但是同样是要去找满足某个条件的最大/最小值
- 二分是个思想, 不是简单的array二分公式. 忌讳不要死板地套用nums[mid]
   - 有时候在index上二分, `mid是index`
   - 有时候, 会在数值上二分, `mid就是value`


## 3. Sort
### 常用
- Merge Sort. Runtime: O(nlogn) average/worst. Memory: depends.
- Quick Sort. Runtime: O(nlogn) average, O(n^2) worst. Memory: O(nlogn).
- Bucket Sort.
- Heap Sort
- Radix Sort. Runtime: O(nk)
- Insertion Sort
- Bubble Sort. Runtime: O(n^2) average/worst. Memory: O(1)
- Selection Sort. Runtime: O(n^2) average/worst. Memory: O(1)

### Merge Sort
- Can be used on Linked List, Array, divide and conquer

### Bucket Sort
- Usually there is a bounded range so that we can create an bounded array
   - 1) Iterate over the input, and put item to its corresponding bucket index
   - 2) Iterate over the bucket to process the sorted items by index.

### Quick Sort
- pick random pivot, all elements smaller sits before pivot, and all elements larger sits after the pivot
- while loop (and two inner while loop) to find the 2 indexes to swap, comparing with pivot
- use the left pointer to partition the array, and keeps sorting left part and right part
- Usually not used on Linked List, harder to perform partition

### Quick Select
- quick sort 的变形
- A selection algorithm to find the k-th smallest element in an unordered list.
- 与quickSort不同在于, 每次只要在一半list里面recurring, 所以把O(nlogn)的时间复杂度降到O(n); worst case O(n^2)
- 主要步骤: partition, dfs, only recur on one part of the array
   - 1) 用 end index作为pivot, 每次根据nums[pivot]排序之后, 在 < low的位置, 都小于 nums[pivot]
   - 2) 把nums[pivot]换到low的位置, 那么换好之后, 所有在low前面的值, 都是小于 nums[pivot]了
   - 3) 那么其实 nums[low]也就是 kth 最小值

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

### Comparator for Arrays, Collections
```
public int compare(x, y) {
  return x - y; // or x.compareTo(y)
}
```

## 4. BFS/DFS Search

### Breadth-first Search
- Track queue size, use the queue as in rotation
- When use BFS or DFS? In the background, DFS is built over stack memory (for each function call), which is limited (10k iterations), if space complexity is too high, then it's not sufficient.
- BFS is iterative, so all the space used will be in heap memory: as much memory you can add

### Depth-first Search
- backtracking: do not repeatly visit a node
- DFS traverse O(n)
- 注意结束condition

#### for loop dfs vs. pick&&skip dfs
- 1. pick&&skip dfs: 取或者不取 + backtracking. 当level/index到底，return 一个list.
- 2. for loop dfs: for loop + backtracking. (ex: Combination Sum, Subset)

#### void dfs
- Pass around result object, and build into it
- `result` 是一个object (usually list)
- 每个dfs里面, 都会填充这个result object.

#### regular primitive dfs
- usually object = int, string
- 每次dfs都会 build on 这个return value

#### Object DFS
- take front part, and dfs(remaining), where usually rst = dfs(remaining) = list<object>
- cross-match front X rst list
- Note1: dfs returns partial solution, and the highest level builds the final result: `return dfs(...0, .)`
- Note2: there may not be backtracking, because there isn't any result or partial result parameter passing around
- optimization: the 'remaining' part can be cached/memoized so we don't make redundant calculation
- p: Word Break II, where `suffix -> rst list` are cached to avoid extreme case

#### Tree DFS
- 有时候要考虑: 包括root, 不包括root. 有个technique: 
- 1. 写一个helper function dfs(always deal with root, never skip)
- 2. 在main function 自己身上recursive: call dfs(..), mainFunc(skip root)
- ex: `Path Sum III`, `Binary Tree Longest Consecutive Sequence II`

## 5. Backtracking
- Finding all (or some) solutions to some computational problems, notebaly constraint satisfaction problems
- It attemps to build/find all candidates and abandon partial candidate when the candidates appears not to be suitable(backtracking, backing off from wrong candidates)
- 尽量不要改变source data, 否则会变得难track
- 注意! 在for loop 和 end condition 里面改变 buffer object (ex: list),  一定都要backtracking: ex: `list.remove(list.size() - 1)`

## 6. Greedy
- follows the problem sovling approach of making the locally optimal choice at each stage with the hope of finding a global optimum.
- pro: simply, quick, easy to program
- cons: only locally optimal decision, **NOT** globally applicable.

### When to use
- 1. Greedy - choice property: a global optimum can be formed by selecting a local optimum. (making a best choice at the moment, leading to global optimum)
- 2. Optimal Substructure: an optimal solution to be problem contains an optimal solution to subproblems.
- Note: the second point is very similar to sub-problem in DP, **BUT** greedy algorithm never re-consider the processed choices (main diff from DP).

### Examples
- these examples can be found on GeekForGeeks
- Activity Selection
- Huffman Coding
- Job Sequencing
- Fractional Knapsack (backpack)
- Prim's Minimum Spanning Tree

## 7. Divide and Conquer

## 8. Recursion
- Recursion 至少用O(n) space, n = depth of recursive call. Each recursive call takes a space in stack(limited)
- 所有recursive problem 都可以 interatively 解决, 但是有可能代码更复杂
- ex: dfs
- always find the entry point or terminating point
- watch out for the return or result of recursed function

特征:
- "compute nth ...", "list the first n ...", "compute all ..." 常常是recursive solution.
- space inefficient. 占用空间, at least O(n)

## 9. Sliding Window
- Sliding window aims to establish a window with certain balance (i.e. `counter==0`); 
   - use right pointer to expand the window and reach the balance
   - use left pointer to contract/shrink the window; sometimes to break the balance
- In any `sliding window` based problem we have two pointers. 
   - `Right pointer`: expand the current window
   - `Left pointer`: shrink/contract a given window.
- At any point in time only one of these pointers move and the other one remains fixed.
- Example
   - Sliding window to keep simply keep window size: `1004. Max Consecutive Ones III`
   - Sliding window to maintain valid anagram char count: `438. Find All Anagrams in a String`
   - Sliding window till matching point; backtrack all the way to find smallest window (closest left starting point to right)
      - `727. Minimum Window Subsequence`: subsequence requires keeping the order of target chars
- Template
   - 1) Move `right pointer`, one step at a time. 
      - Sometime, need to reduce `char counter` (if applicable). `counter==0` is usually used to mark a valid candidate
      - always reduce freq[c]-- to mark a character being used
   - 2) when a valid case is build (i.e., `counter==0`) process as needed
   - 3) shrink/contract the window by moving `left pointer`. 
      - sometimes this occurs within the `2) step` if applicable;
      - but the `window shrink` remains
- Given a string and need to find a substring of it which satisfy some restrictions
- https://leetcode.com/problems/minimum-window-substring/discuss/26808/here-is-a-10-line-template-that-can-solve-most-substring-problems
```
int findSubstring(string s){
      vector<int> map(128,0);
      int counter; // check whether the substring is valid
      int begin=0, end=0; //two pointers, one point to tail and one  head
      int d; //the length of substring

      for() { /* initialize the hash map here */ }

      while(end<s.size()){

         if(map[s[end++]]-- ?){  /* modify counter here */ }

         while(/* counter condition */){ 
               
               /* update d here if finding minimum*/

               //increase begin to make it invalid/valid again
               
               if(map[s[begin++]]++ ?){ /*modify counter here*/ }
         }  

         /* update d here if finding maximum*/
      }
      return d;
  }
```

# Advanced Algorithm (A): Union Find
- Intro: https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
- 集合合并，查找元素在集合里面
- Find and Union functions
- Time Complexity: log(n), https://en.wikipedia.org/wiki/Proof_of_O(log*n)_time_complexity_of_union%E2%80%93find
- 在UnionFind function里维护不同的状态, expose with public helper functions
- 似乎对index进行union find 操作 比较方便 (而并不是给实际value union find. index不重复, 而value会重复)

## Basics
### UnionFind基础操作
- 查询两个元素是否在同一个集合内. connected()
- 合并两个元素所在的集合

### UnionFind follow up
- 查询某个元素所在集合的元素个数
- 查询当前集合的个数

# Advanced Algorithm (B): Topological Sort
- Sort vertices in a graph
- Run DFS, output reverse of finishing times of vertices (stored in a list) 
- G graph has a cycle? If there is a back edge, there is a cycle
- Job Schedule/ Course Schedule: should not have cycle, so acyclic

## 建立Graph + InDegree
- Graph可能是 `map<node, list of neighbors>`, 也可能是 `List[]`
- InDegree在graph建立好的基础上, 建立起来: `map<node, integer>`, 或者简单的`int[]`
- 记住: InDegree跟graph里面的node, 利用neibor -> node 的反向关系建立起来的:
```
private Map<Character, Integer> buildInDegree(Map<Character, List<Character>> graph) {
     Map<Character, Integer> inDegree = new HashMap<>();

     // Build baseline
     for (char c : graph.keySet()) {
         inDegree.put(c, 0);
     }
     
     // Aggregate inDegree
     for (char c: graph.keySet()) {
         for (char edgeNode : graph.get(c)) {
             inDegree.put(edgeNode, inDegree.get(edgeNode) + 1);
         }
     }
     
     return inDegree;
 }
```

## Topological Sort - BFS
- https://www.youtube.com/watch?v=_LuIvEi_kZk
- Can be used to return the topologically sorted list
- The final sorted list will not include the element on a cycle; these vertices won't reduce to in-degree 0.
- 给一个graph of nodes
- 目标是根据edge 的 direction, 把这个graph 里面的 node sort 一个list
- 如果有cycle, 这个item就不会被放在最后的list 里面. 比如: 如果两个课互相是dependency, 就变成了cyclic dependency.
- 注意, 有些有cycle的题目里面要求直接fail掉, 因为有了cycle, topological sort的结果是缺element的, 也许对某个场景来说就是没有意义的.
- 相比DFS, BFS 更容易理解和walk through
- sort 的关键是最后用Queue, reduce inDegree, inDegree == 0 的node, 就是符合条件的candidate
```
// Example of Alien Dictionary:
// Build queue with 0 indegree
for (char c : inDegree.keySet()) {
   if (inDegree.get(c) == 0) {
       queue.offer(c);
   }
}

StringBuffer sb = new StringBuffer();
// IMPORTANT:
while (!queue.isEmpty()) {
   char c = queue.poll();
   sb.append(c);
   
   for (char edgeNode : graph.get(c)) {
       inDegree.put(edgeNode, inDegree.get(edgeNode) - 1);
       if (inDegree.get(edgeNode) == 0) {
           queue.offer(edgeNode);
       }
   }
}
```

## Topological Sort - DFS
- https://youtu.be/AfSk24UTFS8?t=42m
- 还是要先构建好 edges = map<node, list of node>; 或者edges = List[arraylist];
- dfs到底, 再没有其他child node的时候, 把这个node加进stack (垫底)
- 最终按照stack的顺序, pop()出来的顺序 (reverse) 就是正确的topological sort
- 这个比BFS有时候要灵活一些: 可以detect cycle on the fly, 而BFS的做法会到最后再结算cycle是否存在


# Advanced Algorithm (C): Bucket Sort
- Bucket sort is simple: rather than sorting the data, assume boundary of the dataset, and put data into bucket slot
- IMPORTANT: bucket slots may be null; make sure to double check null when consuming bucket



# Advanced Algorithm (D): DP, Dynamic Programming
## 1. Basics
### 判断
- `计数`: how many. 加法原理
- `求最大/最小值`: min/max
- `求存在性`: 能否, AND/OR dp=true/false
- 4 Steps: 1) 确定状态, 2) 转移方程, 3) 初始条件/边界情况, 4) 计算顺序

##### 确定状态
- What to save in DP[]
   - consider last choice: 遍历最后一步可以取的情况
   - sub problem: 切掉最后一块, 剩下的问题跟原问题应该是一样的

##### 转移方程
- 数学表达式来判断 dp[x]的结果
- 纸面上工作结束

##### 初始条件/边界情况
- 非常重要, 必须有初始条件, 才可以让dp计算出正确结果.

##### 计算顺序
- 递推: 从小到大, 从 dp[0], dp[1] 开始
- 记忆化: 从大到小, 先算一遍 dp[n-1]

### Technique
- 滚动数组
- 记忆化搜索

### 基本原理
- 加法原理: `无重复(no overlap last step)`, `无遗漏(cover all possible last steps)`
- dp = new int[n] 还是 dp = new int[n + 1]: 看角标是否表示坐标, 还是前i items的状态

## 2. 分类
- 网格坐标 * 20%
- 序列类 * 20%
- 划分类 * 20%
- 区间类(interval DP)
- 背包类
- 双序列
- 博弈
- combos
- Bitwise Operation动态规划

### a. 网格坐标 (Coordinate)
- 可能是网格, 或者是序列
- `dp[i]: 以第i个元素结尾的某种性质`
- `dp[i][j]: 到格子(i,j)的路径的某种性质`.
- dp index [i][j] = coordinate (i,j), 坐标小标就是grid下标
- 2D的初始条件: f[0][0]
- 边界: i = 0, j = 0,  第一行和第一列
- 计算顺序: 比如第一行, 第二行...etc. 目的: 为了保证, 需要用到的状态, 都已经算到了.
- Example: Unique Path I, II
- 可以出print path的题目

Special case: 
- 最长序列型动态规划: 恰恰是坐标类
- 以i点结束, 很可能可以考虑坐标型
- Longest Increasing Subsequence (祖师爷级别老题目)


### b. 序列 (Sequence)

#### 特点
- 变种多
- 没有固定模板
- 可以选择让: DP[i]存的是以 1-based index的状态, 求前i个.
- 那么, 需要知道dp[n] 的状态, 但是最大坐标是[n-1], 所以int[n+1].
- 初始化(简单): dp[0]往往是有特殊状态的: 0 index有时候初始化就是0
- 边界情况(简单): 0 是虚拟的位, 所以大多数时候, 就留成0, 不太有所谓.

#### 性质
- `dp[i]种, 表示 '前i个元素a[0], a[1] ... a[i - 1] 的某种性质`. (坐标类:dp[i]就代表a[i]结尾的性质)
- dp[0] 表示空序列的性质 (坐标类: dp[0]表示以a[0]结尾的性质)
- 题目中遇到 前i个, '并且': 序列 + 状态

#### 关键点
- 不关心前面n-1是怎么组合出的状态, 但是可以先确定末尾的状态
- 及时思考的时候从结尾, 在代码写的时候, 其实是模拟末尾是i = [0 ~ n] 的情况, 一圈圈计算.
- 最后再给出 dp[n] 的末尾解答. 

#### 序列加状态
- 当思考动态规划最后一步时, 这一步的选择依赖于 前一步 的某种状态: 一维 + 状态
- 序列+状态:如果n-1步有多种状态, 且n步也有多种可能, 添加一种状态记录, 变成2D dp[sequence index][状态]
- Example: Paint House


### c. 划分(Partition)

#### 性质
- 给长度是N的序列或者字符串, 要求分成若干段
- 段数不限, 或者指定K段
- 每一段满足一定性质
- 类似于序列型动态规划, 但是通常要加上 段数信息
- 一般用 `dp[i][j]记录, 前i个元素(元素是 0 ~ i - 1) 分成 j 段的性质`, 比如, 最小cost
- 划分j段的时候, j的大小跟i肯定是不同的scope的, j是分段, i可能就是普通的index

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

### d. 博弈类 (Game Theory)
- 常常问: 先手必胜的情况
- 必胜状态/必败状态的分析
- 通常从第一步分析, 从简单的来分析 (唯一一个需要考虑第一步的  )
- 必胜: 在当下局面走出一步, 让对手无路可逃. 只要对手有一种必败的路线, 就是我必胜.

### e. 背包类 (Backpack)

#### 多种问法
- 前i个物品能不能拼出重量W. 是 可行性 问题: OR , AND
- 填一个什么包, 有一个条件, 重量不超过M
- 不撑爆背包的前提下:
- 装下最多重量物品
- 装下最大价值的物品
- 有多少种方式带走满满一书包物品
- 注意: 如果同一种物品可以拿无限次, 那么`dp[i][w] 表示: 前i 种 物品, 装weight w 的性质`.

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

### f. 区间类(Interval DP)
- 要么砍头, 要么砍尾
- `dp[i][j] 表示 [i~j]之间的状态`, i, j 都是index!
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

### g. Bitwise Operation DP


## 3. DP典型罗列
- 空间优化: 1. Rolling array (easy) . 2. 观察dp的计算顺序, 看双行的左右计算方向, 找是否有舍弃不用的格子, 可以降维
- 考虑空间降维优化时, 不必要死记硬背, 画一画, 看有没有优化的可能
- 时间优化: 分析重复计算, 争取减少.


### Minimax/MaxiMin

### Optimization problems
- memoization && subproblems
- Fibonacci
- Shortest paths
- guessing && DAG View

### Double Sequence
- Sequence problem, have dp[] length of n + 1.
- Look at last index for clues
- Usually can start for loop at index = 0, and we handle the init conditions within the for loop (ex: assign particular value or skip i=0 rows)
- Rolling array (curr, prev pointer) to optimize space; note the rolling dimension should be apply at the top-for loop.

### 存状态
- 复杂的dp, 有时候会需要存一个状态: 拿/不拿, 放1/0, 输/赢
- 通常加上一个维度


## 4. 记忆化搜索 Memoization DP
- 本质是DP, 所有DP也都是为了解决重复计算
- 计算 f(0, N-1), 递归, recursive: 你要求什么, 直接上!
- 走过的老路, 就不要走了. f(i, j) 结束后, 存在数组 f[i][j]里.
- **从大到小**搜索, **自顶向下**, 其实是暴利解决的思路, 只是在深入到底的的过程中存了状态, 不需要重复计算.
- 时间复杂度跟递推的DP一样: O((n^2)/2)

### 什么时候用记忆化搜索
- 1. 状态转移特别麻烦，不是顺序性; 
- 2. 初始化状态不是很容易找到; 
- 3. 从大到小
- 区间搜索(Interval dp), 适合用 memoization, 因为计算顺序稍微比较难
- recursive function calls with slow runtime: think about memoization.

### 特点
- 需要开全局变量
- 需要初始化: Mark算过的, 和没算过的
- 递归里面: 第一行, 一定要记得查visited, 记忆化搜索的精髓. 算过了, 直接返回

### 缺点
- 不能空间优化, 所有值必须记下来

### 时间空间复杂度的节省
- 比如一个binary tree, 全部traverse 下来, 有将近 O(2^n) nodes; 如果存结果, 可能只需要visit unique的 O(n) nodes


# Advanced Algorithm (E): Sweep Line
- 见到区间需要排序就可以考虑扫描线
- 事件往往是以区间的形式存在
- 区间两端代表事件的开始和结束
- 需要排序
- Meeting Room I, II


# 


# 问题类型分类
## 1. Combinatorics 组合
- ex: study n-choose-k problems
- 两种方法: Backtracking 或者 插入法

### DFS 思想
- 在每个index上面都要面临: pick/not pick的选择
- 每次pick以后, 就生成一条新的routine, from this index
- 下一个level的dfs从这个index开始, 对后面(或者当下/if allow index reuse) 进行同样的 pick/not pick 的选择
- 注意1: 每个level dfs 里面, for loop 里会有 end condition: 就不必要dfs下去了.
- 注意2: Backtracking在success case && dfs case 后都要做, 因为backtrack 是为了之前上一层dfs.

### DFS 注意
- input 有duplicate的时候, 必须sort
- 考虑candidate重复使用的规则(不可以重复使用):
- 1. for loop里面dfs的时候, 使用curr index + 1
- 2. for loop里面, 同一个level, 同一个数字, 不能重复使用: `(i > index && candidates[i] == candidates[i - 1]) continue`
- 因为在同一个level里面重复的数字在下一个dfslevel里面是会被考虑到的, 这里必须skip (这个就记住吧)
- 考虑candidate重复使用的规则(可以重复使用): 那么for loop里面dfs的时候, 使用curr index

## 2. Permutation 排列
- Great Example: 44. Permutations. It has a few types of recursive and iterative approach
### 原理
- Permutation的核心: 对于某一个index, 取, 或者不取
- DFS的时候, 注意, 可能要从 index = 0 重新开始permutate
- 用 boolean visited[] 来track, 上一轮visited过得index
- 用两种方式做permutation: Backtracking(dfs, recursive), 或者是插入法(iterative)
- Find small string's permudation in large string: time O(n), using a HashSet of missingString: when the set.length == 0, that is a match of small string.

### Backtracking DFS (Recursive)
- 把candidates 做成一个 remaining list
- 取/不取, 并且从 remaining list 里面去掉, 继续下一层dfs

### 插入法 (iterative)
- 1. 一个一个element加进去
- 2. 每一次把rst里面的每个list拿出来, 创建成新list, 然后选位置加上new element
- 3. 加新元素的时候, 要在list的每个位置insert, 最终也要在原始的list末尾加上new element


## 3. 回文串 Palindrome
- 奇数串, 中间有个unique字符
- 偶数, 中间开始有两个相同的字符
- 找到所有的回文串, 只需要 O(n^2): isPalin[i][j]表示 S[i...j] 是否是palindrome

## 4. Windows Problem
- 加一个数
- 删一个数

## 5. Sum, PrefixSum
- PrefixSum: sum of [0 ~ i] items
- 找 sum[i, j] = preSum[i] - preSum[j - 1];
- 根据题目具体情况 (ex: Copy Books), sum[i, j] 也可以倒序加出来, 存在int  sum 里面
- ex: Subarray Sum Closest



# 知识储备

## 0. Brainteaser
- TO FILL:

## 1. Operating system
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

## 2. Java Garbage Collections
- https://www.dynatrace.com/resources/ebooks/javabook/how-garbage-collection-works/
- downside: garbade collection adapts to all kinds of complex situations, which makes it hard to optimize -> performance problem.

### Heap
- operating system allocates heap memory to JVM, where JVM uses the heap to store/removes objects.
- As long as a object is referenced, JVM will not delete the object
- There exist a `first reference` in the tree: `Garbage Collection Roots`, GC roots.

### Garbage Collection Roots
- Four types of Garbage Collection roots:
- 1. Local variable: kept alive by the stack of a thread. This is not a real object virtual reference and thus is not visible. For all intents and purposes, local variables are GC roots.
- 2. Active Java threads: live object, and are GC root.
- 3. Static variables: referenced by their classes, can be removed when classes are garbage-collected.
- 4. JNI references: java objects that the native code has created as part of JNI call.

### Example
- A typical java application has these GC roots:
- local varialbes in main method
- the main thread
- static variables of the main class

### Marking and Sweeping Carbage
- two simple steps in garbage collection:
- 1. traverses all object references, starting with GC roots, and mark all found objects as live
- 2. all heap memory that is NOT occupied by marked objects are reclaimed (cleaned up for reuse, marked as free)
- This resolves the classic memory leak: unreachable but not deleted objects
- However, this does not solve this memory leak: developer forgot to `dereference` object, which will always be treated as live.
- My thoughts: Java objects should be used and not forgotten; also, we can set object to `NULL` if no longer used but still referenced.
- Setting to `NULL` will simply make the object elligible for garbage collection.


## 3. Pain Point
- For any array access, make sure to check the boundary!!!
- subsequence: not continuous, can skip candidate!
- subarray: continous!

## 4. NP-Complete problems
### wiki
- https://en.wikipedia.org/wiki/NP-completeness

### Knapsack
- Backpack problem: Given a set of items, each with a weight and a value, determine the number of each item to include in a collection so that the total weight is less than or equal to a given limit and the total value is as large as possible.
- https://en.wikipedia.org/wiki/Knapsack_problem

### Travelling salesman
- Given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city and returns to the origin city
- https://en.wikipedia.org/wiki/Travelling_salesman_problem


## 5. Java Design Pattern
- https://www.journaldev.com/1827/java-design-patterns-example-tutorial
- Singleton: create a static instance of class that can be accessed globally without recreation.
- Factory: Used when a super class has multiple sub-classes and we may return one of the sub-classes on demand. Example: returning different client based on region

## API Rate Limiting
- https://nordicapis.com/everything-you-need-to-know-about-api-rate-limiting/

# Uknowns
## Geometry
??

## Advanced Algorithm (F): Reservoir Sampling
??