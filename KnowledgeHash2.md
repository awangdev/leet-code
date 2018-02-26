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

#### Binary Search Tree
- If BST not given, can use TreeSet
- All left nodes are less than current node.val; all right nodes are greater than curr node.val
- Use DFS to traverse: divide and conquer. Similarly, usually can convert the DFS solution to interative solution.
- Use stack to traverse iteratively


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

#### Dynamic Programming
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


#### Breadth-first Search
Track queue size, use the queue as in rotation

#### Depth-first Search


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

















