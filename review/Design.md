 
 
 
## Design (27)
**0. [Sliding Window Median.java](https://github.com/awangdev/LintCode/blob/master/Java/Sliding%20Window%20Median.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap, Sliding Window]
      
Data Stream Median 的同理题目: 不只是不断增加的Sequence, 而且要remove item (保持一个window size)

#### MaxHeap, MinHeap
- Median还是用min-heap 和 max-heap. Time(logN)
- 加/减: prioirtyQueue, log(n)
- findMedian: O(1)
- 加一个数, 减一个数。
- 加减时看好，是从前面的maxheap里面抽，还是从后面的minHeap里面抽。
- 抽完balance一下

#### 注意
- 用maxHeap, minHeap时候, 习惯选择让maxHeap多一个数字:
- 左边的maxHeap总有 x+1或者x个数字
- 后边minHeap应该一直有x个数字



---

**1. [Perfect Rectangle.java](https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Rectangle.java)**      Level: Hard      Tags: [Design, Geometry, Hash Table]
      
看的list of coordinates 是否能组成perfect rectangle, 并且不允许overlap area.

#### 画图发现特点
- 特点1: 所有给出的点(再找出没有specify的对角线点), 如果最后组成perfect rectangle, 都应该互相消除, 最后剩下4个corner
- 特点2: 找到所有点里面的min/max (x,y), 最后组成的 maxArea, 应该跟过程中accumulate的area相等
- 特点1确保中间没有空心的部分, 保证所有的重合点都会互相消除, 最后剩下4个顶点
- 特点2确保没有重合: 重合的area会最终超出maxArea



---

**2. [Flatten 2D Vector.java](https://github.com/awangdev/LintCode/blob/master/Java/Flatten%202D%20Vector.java)**      Level: Medium      Tags: [Design]
      
Implement an iterator to flatten a 2d vector.

Just move pointers carefully with next(), hashNext()

#### Basic Implementation using x, y corrdinate
- 就是把2D list里面的element全部遍历一遍。
- 跟一个nxn的matrix遍历，是没区别的拉; 所有来个x,y，把2d list跑一变。

#### Always return item at index 0, and remove from list?
- list 方便remove, 考虑吧reduce input vector (就像给的是linked list 一样)



---

**3. [Implement Stack using Queues.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack%20using%20Queues.java)**      Level: Easy      Tags: [Design, Stack]
      
如题.

#### Queue, 倒水
- 两个Queue,交互倒水
- 用一个Temp做swap

##### 做法1
- 逻辑在push里面:
- 1. x 放q2。
- 2. q1全部offer/append到q2.
- 3. 用一个Temp做swap q1, q2.
- q1的头，就一直是最后加进去的值.


##### 做法2
- 逻辑在top()/pop()里, 每次换水，查看末尾项.




---

**4. [ColorGrid.java](https://github.com/awangdev/LintCode/blob/master/Java/ColorGrid.java)**      Level: Medium      Tags: [Design, Hash Table]
      
#### basic implementation
- 用HashMap， 理解题目规律，因为重复的计算可以被覆盖，所以是个优化题。没有什么太大的悬念和意义.
- 消灭重合点:       
- 如果process当下col, 其实要减去过去所有加过的row的交接点。。。     
- 再分析，就是每次碰到row 取一个单点, sumRow += xxx。       
- 然后process当下col时候， sum += colValue * N - sumRow. 就等于把交叉所有row（曾经Process过的row）的点减去了。很方便。
- 最后read in 是O(P),  process也是O(P).




---

**5. [Peeking Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/Peeking%20Iterator.java)**      Level: Medium      Tags: [Design]
      
#### Use concept pre cache
- 找一个cache来存next()的值, 也就是: next value的值提前存在cache里面
- 因此peek()的时候, 就可以直接return cache, 而不用做 itt.next()
- 然后每次真的next()的时候, 里取下一个itt.next()维护这个cache

#### Previous notes
- 再一次理解错题意. peek() 就是头顶，但是不一定是最大值啊。
- 总是把PEEK想成了最大值，然后用2 STACK做了最大值的cache，练的一手好双stack，可惜错了。




---

**6. [LFU Cache.java](https://github.com/awangdev/LintCode/blob/master/Java/LFU%20Cache.java)**      Level: Hard      Tags: [Design, Hash Table]
      
#### Hash Table
- 具体看thoughts, 几种不同的方式使用map
- `regular object map`: map of <key, item>, where `item : {int val; int count}`
- Use a Map<frequency count, doubly-linked node> to track the frequency
- Track constant capacity, and minimum frequency
- Every get(): update all frequency map as well
- Every put(): update all frequency map as well, with optional removal (if over capacity)

- Original post: http://www.cnblogs.com/grandyang/p/6258459.html
- TODO: one doubly linked list might be good enough to replace below:
- `frequency list map`: map of <frequency count, List<item>>, where the list preserves `recency`
- `item location in frequency map`: map of <key, int location index in list>:
- index relative to the item in a particular list, not tracking which list here



---

**7. [Singleton.java](https://github.com/awangdev/LintCode/blob/master/Java/Singleton.java)**      Level: Easy      Tags: [Design]
      
让一个class 是 singleton



---

**8. [Min Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/Min%20Stack.java)**      Level: Easy      Tags: [Design, Stack]
      
双Stack：一个正常stack，另一个minStack存当下level最小值. 注意维护minStack的变化

另外. 如果要maxStack，也是类似做法



---

**9. [Design Search Autocomplete System.java](https://github.com/awangdev/LintCode/blob/master/Java/Design%20Search%20Autocomplete%20System.java)**      Level: Hard      Tags: [Design, Hash Table, MinHeap, PriorityQueue, Trie]
      

Description is long, but in short: 做 search auto complete. 

Best problem to review Trie (prefix search), Top K frequent elements (Hash Map), and MinHeap (PriorityQueue)

Easier to revisit https://leetcode.com/problems/design-search-autocomplete-system/description/

#### 思考方向
- 做text的search, 毋庸置疑要用Prefix tree, trie.

##### Find all possible word/leaf, 两种方案:
- Trie造好之后, 做prefix search, 然后DFS/BFS return all leaf items. [high runtime complexity]
- 在TrieNode里面存所有的possible words. [high space usage]
- in memory space 应该不是大问题, 所以我们可以选择 store all possible words

##### Given k words, find top k frequent items. 肯定用MinHeap, 但也有两种方案:
- Store MinHeap with TrieNode: 因为会不断搜索新此条, 同样的prefix (尤其是在higher level), 会被多次搜索.
- [complexity: need to update heaps across all visited TrieNodes once new sentence is completed]
- Compute MinHeap on the fly: 当然我们不能每次都来一个DFS不然会很慢, 所以就必须要store list of possible candidates in TrieNode.
- 这里就用到了`Top K Frequent Words` 里面的 `Map<String, freq>`, 这样O(m) 构建 min-heap其实很方便.

##### Train the system
- 每次 `#` 后 标记一个词条被add进search history. 那么就要 `insert it into trie`.
- 这一条在最后遇到`#`再做就可以了, 非常简洁

#### Trie, PriorityQueue, HashMap
- Trie Prefix Search + maintain top k frequent items
- 



---

**10. [Unique Word Abbreviation.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Word%20Abbreviation.java)**      Level: Medium      Tags: [Design, Hash Table]
      

给一个string[] dict, 和一个word. 

每个word都可以缩写成固定的abbreviation `<first letter><number><last letter>`(详细看原题)

检查input word是否满足unique

#### HashMap<string, Set>
- 简单算出abbreviatioin
- 检查abbr是否存在; 如果存在, 是不是input word本身.



---

**11. [Implement Queue using Stacks.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Queue%20using%20Stacks.java)**      Level: Easy      Tags: [Design, Stack]
      
#### 双Stack
画图, 知道最后maintain的stack是那个 reverseStack: pop(), peek(), empty() 都在这个stack上, 无需变换.
push()里面做stack和reverseStack的来回倾倒.
相比老的code, 在PUSH里面做倾倒, 更容易读.

#### Previous notes
双Stack. 一个是等于是queue，一个是backfillStack.
Tricky: 是在pop()和peek()的时候backfill, 并且要等到stack用完再backfill.
写一下例子就知道，如果提早backfill，stack.peek()就不是queue的head了.




---

**12. [359. Logger Rate Limiter.java](https://github.com/awangdev/LintCode/blob/master/Java/359.%20Logger%20Rate%20Limiter.java)**      Level: Easy      Tags: [Design, Hash Table]
      

#### HashMap<Message, Timestamp>
- map: avoid duplicate message, records timestamp for validation
- time: O(1)
- space: O(n)

#### Queue + Set
- 1) keep a trimmed queue and set (all tasks to be within 10 secs); 
- 2) use set to O(1) check if incoming message exists.
- time: O(x), trimQueue()
- space: O(n)



---

**13. [244. Shortest Word Distance II.java](https://github.com/awangdev/LintCode/blob/master/Java/244.%20Shortest%20Word%20Distance%20II.java)**      Level: Medium      Tags: [Array, Design, Hash Table, Two Pointers]
      

#### Map
- Prep: 存Map<word, index list>
- Process: 相继从两个 index list 里面拿出 p1,p2
    - 根据index的大小, 移动双指针: try to move the pointers closer; always calculate diff
- Optionally: if one list is much larger, do binary search on the larger list



---

**14. [173. Binary Search Tree Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/173.%20Binary%20Search%20Tree%20Iterator.java)**      Level: Medium      Tags: [BST, Design, Stack, Tree]
      

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

**15. [297. Serialize and Deserialize Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/297.%20Serialize%20and%20Deserialize%20Binary%20Tree.java)**      Level: Hard      Tags: [BFS, DFS, Deque, Design, Divide and Conquer, Tree]
      

Serialize and Deserialize Binary Tree

#### DFS, Divide and Conquer, Preorder
- inorder and postorder does NOT work: it is hard to find mid point, since the tree is not balanced or complete
- Serilize: Divide and conquer, Pre-order traversal to link all nodes together
    - build the string data: use '#' to represent null child. 
    - the preorder string, can be parsed apart by `split(',')`    
- Deserialize
    - Use a queue to process 1 node at a time. dfs on remaining of the queue
    - first node from the list is always the head
    - '#' will be a null child: this should break & return dfs
    - queue is shared, so dfs(right child) will happen after dfs(left child) completes
- Note:
    - Append multiple stirngs with `sb.append(x).append(y)`
    - If want to process 1 item at a time from head of the list: make it a queue and poll()

#### BFS, Non-recursive
- serialize: preorder using queue:
    - start with root
    - process curr node, then: queue.offer(leftNode),queue.offer(rightNode)
    - while(!queue.isEmpty())
- deserialize:
    - split into str[] to process
    - since serialization ensures 2 children added (including null), we assume:
        - the sequence of parent, left child, right child.
        - use queue to reproduce the preorder sequence as we process each index of str[]
    - Queue will not be empty until all index reaches end of str[], so no need to worry about queue emptiness



---

**16. [245. Shortest Word Distance III.java](https://github.com/awangdev/LintCode/blob/master/Java/245.%20Shortest%20Word%20Distance%20III.java)**      Level: Medium      Tags: [Array, Design, Hash Table, Two Pointers]
      

跟243/244不同: 这里允许list里面有重复的word.

#### Method1: Two Pointers, one pass
- Follow up of 243. Shortested Word Distance
- 特别handle `word == word1 == word2` case:
    - p1 and p2 will always be the same
    - when `word == word1 == word2`, simply calculate distance using the `old p1 or p2` with `curr index i`
- The rest impl aligns with 243.

#### Method2: Hash Table
- when `word1==word2`, make usre to skip `p1==p2` by increasing i or j
- The rest impl aligns with 244
- Time: still O(n), but slower than Method1: 2 passes
- Space: uses extra space O(n) to hold all indexes



---

**17. [211. Add and Search Word - Data structure design.java](https://github.com/awangdev/LintCode/blob/master/Java/211.%20Add%20and%20Search%20Word%20-%20Data%20structure%20design.java)**      Level: Medium      Tags: [Backtracking, Design, Trie]
      

#### Trie, prefix tree.
- Trie Structure: `boolean isEnd`, `HashMap<Character, TrieNode> children`
    - trie.addWord: 没node就加，有node就移动
    - trie.search: 没node就return false，有node就移动
- Alternatively, the hash can be `TrieNode[26]` a fixed size array when applicable
    - I like map better for the simplicity to write (w/o converting char -> index)




---

**18. [295. Find Median from Data Stream.java](https://github.com/awangdev/LintCode/blob/master/Java/295.%20Find%20Median%20from%20Data%20Stream.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap]
      

#### MaxHeap/MinHeap
- 把Input stream想成向上的山坡. 山坡中间那点，自然就是median.
- 前半段，作为maxHeap,关注点是PriorityQueue的峰点，也就是实际上的median.   
- 后半段，作为minHeap,正常的PriorityQueue。 开头是最小的。

#### 注意
- 这里要首先定好, 哪一个queue是多存一个element的. 这里选maxHeap: maxHeap.size() == minHeap.size() + 1 || minHeap.size()
- 必须先维护maxHeap里面有个元素, 否则null了会在比较大小时出问题.



---

**19. [146. LRU Cache.java](https://github.com/awangdev/LintCode/blob/master/Java/146.%20LRU%20Cache.java)**      Level: Medium      Tags: [Design, Doubly Linked List, Hash Table, Linked List]
      

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

**20. [170. Two Sum III - Data structure design.java](https://github.com/awangdev/LintCode/blob/master/Java/170.%20Two%20Sum%20III%20-%20Data%20structure%20design.java)**      Level: Easy      Tags: [Design, Hash Table, Memoization]
      

#### Hash Table, Memo
- Use Map<number, count > to store the inputs
- Iterate over map to find the pair
- Use Set<int> memo to store the success cases for fast return
- time: O(n), loop over all elements in map
- space: O(n), store all elements in map & memoization set



---

**21. [432. All One Data Structure.java](https://github.com/awangdev/LintCode/blob/master/Java/432.%20All%20One%20Data%20Structure.java)**      Level: Hard      Tags: [Design, Doubly Linked List]
      

#### Doubly Linked List
- IMPORTANT: the problem aims to put keys of same frequency in same node! This affects the design of node
- Main a class `Node {keySet, count, last/next pointers}`
- Each operation: 
  - 1) finds target node and extract the key
  - 2) calculate: count +/- 1
  - 3) find new spot to store the key (prior positions or later positions)
- Be careful when handling the cases in inc() and dec()



---

**22. [380. Insert Delete GetRandom O(1).java](https://github.com/awangdev/LintCode/blob/master/Java/380.%20Insert%20Delete%20GetRandom%20O(1).java)**      Level: Medium      Tags: [Array, Design, Hash Table]
      

#### Hash Table, Swap when removing
- Map: `map<val, index>, Lis: tracks `index->value`
- list maintain 用来 insert/remove/random operations.
- Remove: swap input valueIndex & tialIndex = list.size() -1.
    - list.remove(object) 应该是要O(logn) 做一个search的.
    - list.remove(list.size() - 1) is cheapter



---

**23. [346. Moving Average from Data Stream.java](https://github.com/awangdev/LintCode/blob/master/Java/346.%20Moving%20Average%20from%20Data%20Stream.java)**      Level: Easy      Tags: [Design, Queue, Sliding Window]
      

给一个interface, design一个structure, 能够计算moving window average.

#### Queue
- 读懂题目, 注意average 和 window 的处理.
- 简单的queue.size() comparison
- Note: if we it is calculate moving-window-product, better to use deque :)
- Sliding window?
    - It has the spirit of slinding window: 1) maintain a range; 2) check range size `if (queue.size() > size)`
    - Though, the solution must use a data structure to store data; it is not the traditional sliding window type of `left/right` pointer problem



---

**24. [208. Implement Trie (Prefix Tree).java](https://github.com/awangdev/LintCode/blob/master/Java/208.%20Implement%20Trie%20(Prefix%20Tree).java)**      Level: Medium      Tags: [Design, Trie]
      
Implement Tire, 也即是 Prefix Tree. 做三个function: insert, search, startWith

#### Trie
- Trie Structure: 
    - trace the char to children node: Map<char, TrieNode>
    - boolean isEnd to indicate if there is string end with this node
    - `TrieNode {boolean isEnd; Map<Character, TrieNode> children}`.
- No need to store letter c in TrieNode
- HashMap构建Trie. Trie三个Method:
- 1. Inset: 加 word   
- 2. Search: 找word    
- 3. StartWith: 找prefix    

##### 特点
- 只有两条children的是binary tree. 那么多个children就是Trie
- 那么没有left/right pointer怎么找孩子？   
- 用HashMap，以child的label为Key，value就是child node。 HashMap走位   

##### 其他
- node里的char在这是optional. 只要在每个TrieNode里面用map存储向下分布的children就好了.  
- 另外有种题目，比如是跟其他种类的search相关，在结尾要return whole string，就可以在node里存一个up-to-this-point的String。

##### Previous Note
- 如果是遇到一个一个字查询的题，可以考虑一下。
- 构建TrieNode的时候要注意：如何找孩子？如果是个map的话，其实就挺好走位的。
- 而且，每个node里面的 char 或者string有时候用处不大，
- 可以为空。但是有些题目，比如在结尾要return一些什么String，就可以在end string那边存一个真的String。





---

**25. [716. Max Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/716.%20Max%20Stack.java)**      Level: Medium      Tags: [Design, Doubly Linked List, Stack, TreeMap]
      

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

**26. [341. Flatten Nested List Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/341.%20Flatten%20Nested%20List%20Iterator.java)**      Level: Medium      Tags: [Design, NestedInteger, Stack]
      

#### Method1: Stack holds items from back of the list
- Option1: always set integer on top of the stack everywhere
    - if not, poping stack until the top is integer
    - code is easy
- Option2: in hasNext(), faltten the list in stack

#### Method2: DFS preprocessing.
- 用queue to store all items. Kinda hack. Defeat the purpose of the problem.
- Super fast to query next(), however, needs to holds everything in memory
- O(n)



---

