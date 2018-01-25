### 1D

Heap

#### Stack
- Functions: peek(), pop(), push()
- Stack<XXX> stack = new Stack<>();

#### Queue
- Functions: peek(), poll(), add()/offer()
- Linked List
- PriorityQueue: new Comparator 很重要



### Tree

Tree

Depth-first Search

Breadth-first Search

#### Binary Search Tree
- If BST not given, can use TreeSet
- All left nodes are less than current node.val; all right nodes are greater than curr node.val
- Use DFS to traverse: divide and conquer. Similarly, usually can convert the DFS solution to interative solution.


Binary Indexed Tree

Segment Tree

Union Find

Trie



### Graph

Graph

Topological Sort





### Array

#### Array
- Arrays.asList([1,2,3]);

Two Pointers

Binary Search

Sort

### Collections

#### methods
Collections.sort()

#### ArrayList
Integer[] array = {1, 2, 3};
new ArrayList(Arrays.asList(array))


### Hash

Map

Hash Table

#### HashSet
- contains: O(1)
- set.add(...) returns false if there is duplicate. This operation won't change the existing set.
- Build HashSet<List> set, and the set will automatically compare the equivalence of the lists within at each list element level.

### Basics

#### Math
- 转换成string
- % mod, 除法
- Integer.MAX_VALUE, Integer.MIN_VALUE; if overflow, use long


#### String
- s.toCharArray()
- String.valueOf(charArrary)
- sb = new StringBuffer()
- sb.reverse(), sb.append(), sb.deleteCharAt(), sb.length(), sb.setCharAt(index, char)

#### Bit Manipulation
- Bit OR |, AND &, XOR ^
- Bit shift: <<, >>


### DP

#### Dynamic Programming
Optimization problems:
- memoization && subproblems
- Fibonacci
- Shortest paths
- guessing && DAG View




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

#### Recursion
- ex: dfs
- always find the entry point or terminating point
- watch out for the return or result of recursed function

Design

















