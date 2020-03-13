 
 
 
## Doubly Linked List (3)
**0. [146. LRU Cache.java](https://github.com/awangdev/LintCode/blob/master/Java/146.%20LRU%20Cache.java)**      Level: Medium      Tags: [Design, Doubly Linked List, Hash Table, Linked List]
      

#### Double Linked List
- 用了一个特别的双向的ListNode，有了head和tail，这样就大大加快了速度
- 主要加快的就是那个‘更新排位’的过程，找到item hashmap O(1), 做减法换位也都是O(1)
- Overall O(1)
- 巧妙点
    - 1. head和tail特别巧妙：除掉头和尾，和加上头和尾，都O(1)
    - 2. remove node: 把node.pre和node.next 连起来, node就自然而然的断开不要了
- 一旦知道怎么解决了，就不是很特别，并不是难写的算法
- moveToHead()    
- insertHead()    
- remove()

#### Deque, less efficient
- Instead of building `Double Linked List`, utilize Java `Deque<E> queue = new LinkedList<>()`
- works but problem: `queue.remove(E)` is O(n)
- time: O(1) on average but much slower



---

**1. [432. All One Data Structure.java](https://github.com/awangdev/LintCode/blob/master/Java/432.%20All%20One%20Data%20Structure.java)**      Level: Hard      Tags: [Design, Doubly Linked List]
      

#### Doubly Linked List
- IMPORTANT: the problem aims to put keys of same frequency in same node! This affects the design of node
- Main a class `Node {keySet, count, last/next pointers}`
- Each operation: 
  - 1) finds target node and extract the key
  - 2) calculate: count +/- 1
  - 3) find new spot to store the key (prior positions or later positions)
- Be careful when handling the cases in inc() and dec()



---

**2. [716. Max Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/716.%20Max%20Stack.java)**      Level: Medium      Tags: [Design, Doubly Linked List, Stack, TreeMap]
      

#### Two Stack
- one to keep regular elements
- one to repat the max at current stack level
- time: O(n) for popMax() and O(1) for the rest operations
- space: O(n)

#### TreeMap
- Reference: https://leetcode.com/problems/max-stack/solution/
- Use TreeMap to store <Int, List of Nodes>, which gives: **O(logN) insert, delete and find MAX**
- Key reason to use `DoubleLinkedList` is to perform O(1) removal for `popMax()`
- The problem becomes finding the target value & remove from DoubleLinkedList
- time: O(1) for popMax() and O(logN) for the rest
- space: O(n)



---

