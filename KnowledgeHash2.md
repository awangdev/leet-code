Table of Contents
=================

* [Heap](#heap)
* [Stack](#stack)
* [Queue](#queue)
* [Linked List](#linked-list)
* [Tree](#tree)
   * [Binary Search Tree](#binary-search-tree)
* [Union Find](#union-find)
   * [UnionFind基础操作](#unionfind基础操作)
   * [UnionFind follow up](#unionfind-follow-up)
* [Trie](#trie)
   * [applications:](#applications)
   * [why not using a hashmap to store string?](#why-not-using-a-hashmap-to-store-string)
   * [Model](#model)
   * [sample peoblems](#sample-peoblems)
   * [考点](#考点)
* [Graph](#graph)
* [Array](#array)
   * [Array](#array-1)
* [Sort](#sort)
   * [Quick Sort](#quick-sort)
* [Collections](#collections)
   * [methods](#methods)
   * [ArrayList](#arraylist)
* [Hash](#hash)
   * [HashSet](#hashset)
* [Basics](#basics)
   * [Math](#math)
   * [String](#string)
   * [Bit Manipulation](#bit-manipulation)
* [DP](#dp)
   * [Dynamic Programming](#dynamic-programming)
* [Double Sequence](#double-sequence)
* [Search](#search)
   * [Breadth-first Search](#breadth-first-search)
   * [Depth-first Search](#depth-first-search)
* [Backtracking](#backtracking)
* [Fancy](#fancy)
* [Approach](#approach)
   * [Recursion](#recursion)
* [Data Structure](#data-structure)
* [Problem Sets](#problem-sets)
   * [Two Pointer](#two-pointer)
   * [Min/Max Heap](#minmax-heap)

### Heap

### Stack
- Functions: peek(), pop(), push()
- Stack<XXX> stack = new Stack<>();

### Queue
- Functions: peek(), poll(), add()/offer()
- queue = new LinkedList<...>()
- PriorityQueue: new Comparator 很重要
- 看到Min/Max就要想到heap. 如果给出的数组没有排序, 先排序, 然后用heap. PrioirtyQueue是用Binary Heap做出来的

### Linked List
- No concept of size(), it's all pointers: node.next.next
- how to set head/dummy, and return dummy.next as result.
- iterate over linked list
- Don't get mixed up with Java LinkedList. Here we are talking about linked list concept, not the Java data structure LinkedList

### Tree

##### Binary Search Tree
- If BST not given, can use TreeSet
- All left nodes are less than current node.val; all right nodes are greater than curr node.val
- Use DFS to traverse: divide and conquer. Similarly, usually can convert the DFS solution to interative solution.
- Use stack to traverse iteratively

Binary Indexed Tree

Segment Tree

### Union Find
- 集合合并，查找元素在集合里面
- Find and Union functions
- Time Complexity: log(n)
- 在UnionFind function里维护不同的状态, expose with public helper functions

##### UnionFind基础操作
- 查询两个元素是否在同一个集合内
- 合并两个元素所在的集合

##### UnionFind follow up
- 查询某个元素所在集合的元素个数
- 查询当前集合的个数


### Trie
一个字母一个字母查找，快速判断前缀

##### applications: 
- Autocomplete
- Spell check
- IP routing
- T9 text prediction (old NOKIA phone)
- solve world game

##### why not using a hashmap to store string?
- Trie can help find all strings with prefix
- Trie can enumerate a data set of strings in lexicographical order
- Trie saves space because of the prefix
- Trie can potentially faster than hashMap, when there are logs collisions for the map.

##### Model
- children map: Map<Character, TrieNode>. Also can use char[26], but it's more scalable to us a map.
- always have isEnd which marks a end of a particular string

##### sample peoblems
- insert
- search
- exist of prefix
- node when the prefix end

##### 考点
- 一个一个字母遍历
- 需要节约空间
- 查找前缀


### Graph

Graph

Topological Sort





### Array

##### Array
- Arrays.asList([1,2,3]);

Two Pointers

Binary Search

### Sort

##### Quick Sort

### Collections

##### methods
Collections.sort()

##### ArrayList
Integer[] array = {1, 2, 3};
new ArrayList(Arrays.asList(array))

### Hash

Map

Hash Table

##### HashSet
- contains: O(1)
- set.add(...) returns false if there is duplicate. This operation won't change the existing set.
- Build HashSet<List> set, and the set will automatically compare the equivalence of the lists within at each list element level.

### Basics

##### Math
- 转换成string
- % mod, 除法
- Integer.MAX_VALUE, Integer.MIN_VALUE; if overflow, use long


##### String
- s.toCharArray()
- String.valueOf(charArrary)
- sb = new StringBuffer()
- sb.reverse(), sb.append(), sb.deleteCharAt(), sb.length(), sb.setCharAt(index, char)

##### Bit Manipulation
- Bit OR |, AND &, XOR ^
- Bit shift: <<, >>
- A << 1: binary of A shifted left for 1 bit, which result in value x 2
- A >> 1: divide by integer 2. Note: decimals are ignored in the result.
- bit shift is a lot faster than reqular 'times' operation.
- 32 bit number: leading bit = 1, negative numbjer; leading bit = 0, positive number.
- >> add leading '1' if the 32 bit number originally has leading '1'.
- Java/python: logical shift >>>, always add leading '0' regardless of the sign of the 32-bit number. That is, it may turn a negative number to positive, if the leading bit is originally '1'
- Because with '( )', make sure to surround the desired operation
- & 0000 = clean up; | ABC = assign ABC
- A^B=C, then A = B^C

### DP

##### Dynamic Programming
Optimization problems:
- memoization && subproblems
- Fibonacci
- Shortest paths
- guessing && DAG View

### Double Sequence
- Sequence problem, have dp[] length of n + 1.
- Look at last index for clues
- Usually can start for loop at index = 0, and we handle the init conditions within the for loop (ex: assign particular value or skip i=0 rows)
- Rolling array (curr, prev pointer) to optimize space; note the rolling dimension should be apply at the top-for loop.


### Search


##### Breadth-first Search
Track queue size, use the queue as in rotation

##### Depth-first Search


### Backtracking ###
- Finding all (or some) solutions to some computational problems, notebaly constraint satisfaction problems
- It attemps to build/find all candidates and abandon partial candidate when the candidates appears not to be suitable(backtracking, backing off from wrong candidates)



### Fancy

Memoization

Minimax

Reservoir Sampling

Geometry

Brainteaser



### Approach

Greedy

Divide and Conquer

##### Recursion
- ex: dfs
- always find the entry point or terminating point
- watch out for the return or result of recursed function

Design



#### Data Structure
- Union Find
- Trie

### Problem Sets

##### Two Pointer

##### Min/Max Heap
- 见到需要维护一个集合的最小值/最大值的时候要想到用堆
- 第k小的元素，Heap用来维护当前候选集合
- 见到数组要想到先排序








