 
 
 
## Hash Table (86)
**0. [Frog Jump.java](https://github.com/awangdev/LintCode/blob/master/Java/Frog%20Jump.java)**      Level: Hard      Tags: [DP, Hash Table]
      
Frog jump 的题目稍微需要理解: 每个格子可以 jump k-1, k, k+1 steps, 而k取决于上一步所跳的步数. 默认 0->1 一定是跳了1步.

注意: int[] stones 里面是stone所在的unit (不是可以跳的步数, 不要理解错).

#### DP
- 原本想按照corrdiante dp 来做, 但是发现很多问题, 需要track 不同的 possible previous starting spot.
- 根据jiuzhang答案: 按照定义, 用一个 map of <stone, Set<possible # steps to reach stone>>
- 每次在处理一个stone的时候, 都根据他自己的 set of <previous steps>, 来走下三步: k-1, k, or k+1 steps.
- 每次走一步, 查看 stone + step 是否存在; 如果存在, 就加进 next position: `stone+step`的 hash set 里面

##### 注意init
- `dp.put(stone, new HashSet<>())` mark 每个stone的存在
- `dp.get(0).add(0)` init condition, 用来做 dp.put(1, 1)

##### 思想
- 最终做下来思考模式, 更像是BFS的模式: starting from (0,0), add all possible ways 
- 然后again, try next stone with all possible future ways ... etc



---

**1. [Perfect Rectangle.java](https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Rectangle.java)**      Level: Hard      Tags: [Design, Geometry, Hash Table]
      
看的list of coordinates 是否能组成perfect rectangle, 并且不允许overlap area.

#### 画图发现特点
- 特点1: 所有给出的点(再找出没有specify的对角线点), 如果最后组成perfect rectangle, 都应该互相消除, 最后剩下4个corner
- 特点2: 找到所有点里面的min/max (x,y), 最后组成的 maxArea, 应该跟过程中accumulate的area相等
- 特点1确保中间没有空心的部分, 保证所有的重合点都会互相消除, 最后剩下4个顶点
- 特点2确保没有重合: 重合的area会最终超出maxArea



---

**2. [Line Reflection.java](https://github.com/awangdev/LintCode/blob/master/Java/Line%20Reflection.java)**      Level: Medium      Tags: [Hash Table, Math]
      

给一串点, 找是否有一个所有点中间的, 跟y-axis平行的中线.

#### Hash Table
- 1. store in `Map<y, set<x>>`, 2. iterate over map, check head,tail against the mid point
- 很好的细节题目:
- 1. 除以2, 需要存double
- 2. (问面试官)可以有重复的点! 所以track `set<x>`
- 3. 处理 left==right时候, 就当做两个点来处理.
- 4. 存进set里面没有sort, 但是最后做check的时候, 需要sort list
- 时间: visit all nodes 两遍,  O(n)



---

**3. [Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Consecutive%20Sequence.java)**      Level: Hard      Tags: [Array, Hash Table, Union Find]
      
给一串数字, unsorted, 找这串数字里面的连续元素序列长度 (consecutive序列, 是数字连续, 并不是说要按照原order)

#### HashSet
- 要想看连续元素, 必须要num++, num--这样搜索
- 1. 需要O(1)找到元素
- 2. 需要简单快速找到 num - 1, num + 1.
- 如果用min,max开array, 耗费空间
- 用HashSet来存, 用set.contains() 来查找 num - 1, num + 1 存在与否
- for loop. O(n) 
- 里面的while loop 一般不会有O(n); 一旦O(n), 也意味着set 清零, for loop也不会有更多 inner while 的衍生.
- overall O(n) 时间复杂度


#### Union Find
- 最终是要把相连的元素算一下总长, 其实也就是把元素group起来, 相连的group在一起, 于是想到UnionFind
- 这里用到了一个`int[] size` 来帮助处理 `合并的时候parent是哪个`的问题: 永远往group大的union里去
- main function 里面, 有一个map来track, 每个元素, 只处理1遍.
- union的内容: current number - 1, current number + 1
- https://www.jianshu.com/p/e6b955ca208f

##### 特点
- Union Find 在index上做好像更加容易
- 其他union find function: `boolean connected(a,b){return find(a) == find(b)}`



---

**4. [Rearrange String k Distance Apart.java](https://github.com/awangdev/LintCode/blob/master/Java/Rearrange%20String%20k%20Distance%20Apart.java)**      Level: Hard      Tags: [Greedy, Hash Table, Heap]
      
给一个string, 全是lowercase letter, 要求重新排列: 然后每个unique的character要有k distance apart.

跟Task Scheduler有点像, 只不过Task那道题里面还可以用其他方法求count, 这道题要求出排列结果

#### PriorityQueue + HashTable
- PriorityQueue排序+分布排列的一个经典用法.
- Count frequency and store in pq.
- Consume element of pq for k rounds, each time pick one element from queue.
- Exception: if k still has content but queue is consumed: cannot complete valid string, return "";
- space, O(n) extra space in sb, O(26) constant space with pq.
- time: O(n) to add all items



---

**5. [Remove Duplicate Letters.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicate%20Letters.java)**      Level: Hard      Tags: [Greedy, Hash Table, Stack]
      
#### Hash Table, Greedy
- count[] = int[256], 不需要 `c-'a'`
- boolean visited[]: 一旦一个字母固定了位置后, 再次遇到时候, 直接跳过用过的character
- 如果tail字母可以变小, 那就delete掉tail, 重新接上新字母 (前提条件: 去掉的字母后面还会再出现, set visited[tail] = false)
- Space: O(1) count[], visited[].
- Time: Go through all letters O(n)

#### Stack
- Use stack instead of stringBuffer: keep append/remove last added item
- However, stringBuffer appears to be faster than stack.



---

**6. [ColorGrid.java](https://github.com/awangdev/LintCode/blob/master/Java/ColorGrid.java)**      Level: Medium      Tags: [Design, Hash Table]
      
#### basic implementation
- 用HashMap， 理解题目规律，因为重复的计算可以被覆盖，所以是个优化题。没有什么太大的悬念和意义.
- 消灭重合点:       
- 如果process当下col, 其实要减去过去所有加过的row的交接点。。。     
- 再分析，就是每次碰到row 取一个单点, sumRow += xxx。       
- 然后process当下col时候， sum += colValue * N - sumRow. 就等于把交叉所有row（曾经Process过的row）的点减去了。很方便。
- 最后read in 是O(P),  process也是O(P).




---

**7. [Maximal Rectangle.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Rectangle.java)**      Level: Hard      Tags: [Array, DP, Hash Table, Stack]
      
#### 方法1: monotonous stack
分解开来, 其实是'Largest Rectangle in Histogram', 只不过这里要自己model heights.
一个2D array里面的rectangle, 最终也是用height * width做出来的.
巧妙在于, 把每一行当做底边, 算出这个底边, 到顶部的height: 
- 如果底边上的一个value==0, 那么算作没有height(以这个底边做rectangle, value==0的位置是空中楼阁, 不能用)
- 如果底边上的value==1, 那么就把上面的height加下来, 做成histogram

如果看具体实例, 有些row似乎是白算的, 但是没有办法, 这是一个搜索的过程, 最终会比较出最优解.

#### 方法2: DP
Coordinate DP?



---

**8. [LFU Cache.java](https://github.com/awangdev/LintCode/blob/master/Java/LFU%20Cache.java)**      Level: Hard      Tags: [Design, Hash Table]
      
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

**9. [Encode and Decode TinyURL.java](https://github.com/awangdev/LintCode/blob/master/Java/Encode%20and%20Decode%20TinyURL.java)**      Level: Medium      Tags: [Hash Table, Math]
      
其实想到了切入点, 是个可难可简单的题目. 这里的encode就是想办法把url存起来, 然后给个 key.
那么具体怎么做这个key, 简单就可以用一个map, 然后counting. 复杂一点就可以做random letter/number组成key.



---

**10. [Rehashing.java](https://github.com/awangdev/LintCode/blob/master/Java/Rehashing.java)**      Level: Medium      Tags: [Hash Table]
      
给一个Hash Table, 是用 linked list 做的. 问题是: capacity太小, collision太多的情况下, 需要double capacity 然后rehash.

#### Hash Table
- 明白hashCode() function的意义: 拿到hashKey的时候, 用hashKey%capacity 来做hash code
- hashcode就是hash map里面的index
- 明白collision handling 的方式, 和如何double capacity而rehashing
- 都是基本操作, 概念实现



---

**11. [4Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/4Sum.java)**      Level: Medium      Tags: [Hash Table]
      
#### Based on 2sum
- 1. 利用2Sum的原理，把4Sum分为连个2Sum。左一个pair,右一个pair，每个pair里面放2个数字。   
- 2. 以一个点，i，作为分界口，也要列举出所有i之前的pair,作为基础。   
- 3. 再尝试从所有i+1后面,找合适的2nd pair。   
- Time: O(n^2 * x), where x = # of candidates, still slow
- 可以用HashSet<List>, 可以直接比较list里面每一个元素, 保证set不重复.
- Previous Notes: 在造class Pair时候，要做@override的function: hashCode(), equals(Object d). 平时不太想得起来用。
- 参见 http://lifexplorer.me/leetcode-3sum-4sum-and-k-sum/    

#### Based on 3Sum
- 3Sum外面再加一层. 参考3Sum. 时间O(n^3)。 但此方法在k-sum时候，无疑过于费时间. O(n^k)



---

**12. [Contiguous Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Contiguous%20Array.java)**      Level: Medium      Tags: [Hash Table]
      
TODO: how aout without chaning the input nums?



---

**13. [Palindrome Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Pairs.java)**      Level: Hard      Tags: [Hash Table, String, Trie]
      
Obvious的做法是全部试一遍, 判断, 变成 O(n^2) * O(m) = O(mn^2). O(m): isPalindrome() time.

当然不行了, 那就看是O(nlogN), 还是O(n)?

#### 方法1: Hash Table + Palindrome的性质. 复合型.
O(mn)

##### 思路
- 每一个word, 都可以拆分成 front + mid + end. 如果这个word + 其他word可以组成palindrome,那就是说
- 砍掉 (mid+end), front.reverse() 应该存在 words[] 里面.
- 砍掉 (front+mid), end.reverse() 应该存在 words[] 里面.
- 我们用HashMap存所有的<word, index>, 然后reverse, 找配对就好.

##### Corner case
- 如果有 empty string "", 那么它跟任何一个palindrome word, 都可以配对, 并且根据位置前后变换, 凑成2份 distinct indexes.
- 这样就有了那个 `if (reverseEnd.equals("")) {...}` 的logic.
- 注意: 虽然在处理砍头/砍尾的两个 for loop里面都在根据 empty string 重复记录, 
  但因为 "" 自己本身不能作为起点, 所以overall只会在被其他palindrome配对时记录一次.


#### 方法2: Trie
还要做一下那.



---

**14. [Longest Substring Without Repeating Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20Without%20Repeating%20Characters.java)**      Level: Medium      Tags: [Hash Table, String, Two Pointers]
      
方法1:
Two Pointers
双指针: 从start开始遍历, 但是第一步是while loop来推进end; 直到推不动end, 然后start++
巧妙点: 因为end是外围variable, 在start的loop上, end不会重置;[star ~ end] 中间不需要重复计算.
最终可以O(n);

Previous verison of two pointers:
用两个pointer, head和i.
注意：head很可能被退回到很早的地方，比如abbbbbba,当遇到第二个a，head竟然变成了 head = 0+1 = 1.      
当然这是不对的，所以head要确保一直增长，不回溯

方法2:
   HashMap<Char, Integer>: <character, last occurance index>
   一旦有重复, rest map.
   没有重复时候, 不断map.put(), 然后求max值

问题: 每次reset map之后就开始从新从一个最早的index计算, 最坏情况是O(n^2):
'abcdef....xyza'




---

**15. [Fraction to Recurring Decimal.java](https://github.com/awangdev/LintCode/blob/master/Java/Fraction%20to%20Recurring%20Decimal.java)**      Level: Medium      Tags: [Hash Table, Math]
      
TODO: no need of hashMap, just use set to store the existing

不难想到处理除法：考虑正负，考虑小数点前后。主要是小数点以后的要着重考虑。
很容易忽略的是integer的益处。


---

**16. [Majority Number III.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Number%20III.java)**      Level: Medium      Tags: [Hash Table, Linked List]
      
TODO: 
1. hash table solution not passing
2. Find O(n) solution

#### Hash Table
- 与其他Majority Number一样。
- 出现次数多余1/k，就要分成k份count occurance.用HashMap。 存在的+1；不存在map里的，分情况:    
- 若map.size() == k,说明candidate都满了，要在map里把所有现存的都-1；
- 若map.size() < k, 说明该加新candidate，那么map.put(xxx, 1);
- 最后在HashMap里找出所留下的occurance最大的那个数。
- 但这样的worst case是 O(nk)



---

**17. [Design Search Autocomplete System.java](https://github.com/awangdev/LintCode/blob/master/Java/Design%20Search%20Autocomplete%20System.java)**      Level: Hard      Tags: [Design, Hash Table, MinHeap, PriorityQueue, Trie]
      

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

**18. [Maximum Size Subarray Sum Equals k.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Size%20Subarray%20Sum%20Equals%20k.java)**      Level: Medium      Tags: [Hash Table, PreSum, Subarray]
      

#### Map<preSumValue, index>
- use `Map<preSum value, index>` to store inline preSum and its index.
- 1. Build presum incline
- 2. Use map to cache current preSum value and its index: `Map<preSum value, index>`
- 3. Each iteration: calculate possible preSum candidate that prior target sequence. ex: `(preSum - k)`
- 4. Use the calculated preSum candidate to find index
- 5. Use found index to calculate for result. ex: calculate range.



---

**19. [Submatrix Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Submatrix%20Sum.java)**      Level: Medium      Tags: [Array, Hash Table, PreSum]
      
给一个int[][] matrix, 找一个sub matrix, where the sum == 0.

#### PreSum的思想
- 算出一个右下角点(i,j)到(0,0)的大小: 上一块 + 左一块 + curr node - overlap area
- preSum[i][j]: sum from (0,0) to (i-1,j-1)
- same approach as `subarray sum`: use hashmap to store diff->index; if diff re-appears, that means sum of 0 has occurred
- sequence of calculation: 1. iterate over start row. 2. iterate over end row. 3. iterate over col number (this is where hashmap is stored based on)
- the iteration over col is like a screening: find previous sum and determine result
- Note: 其实并没有真的去找 `== 0` 的解答,而是根据特性来判断 `剩下的/后来加上的一定是0`



---

**20. [Unique Word Abbreviation.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Word%20Abbreviation.java)**      Level: Medium      Tags: [Design, Hash Table]
      

给一个string[] dict, 和一个word. 

每个word都可以缩写成固定的abbreviation `<first letter><number><last letter>`(详细看原题)

检查input word是否满足unique

#### HashMap<string, Set>
- 简单算出abbreviatioin
- 检查abbr是否存在; 如果存在, 是不是input word本身.



---

**21. [Brick Wall.java](https://github.com/awangdev/LintCode/blob/master/Java/Brick%20Wall.java)**      Level: Medium      Tags: [Hash Table]
      

给一面墙, 每一行是一行bricks. 用一条vertical line 扫描, 会vertically割开brink. 找到割开最少brick的那条线的x index.

#### Hash Table
- Find the vertical line (x-coordinate of the grid), where most gaps are found.
- Each gap has (x,y) coordinate
- Create `map<x-coordinate, #occurrance>`, and maintain a max occurance. 
- 计算: x-coordinate: `x = 0; x += brick[i] width`
- Eventually: min-crossed bricks = wall.lenght - maxOccurrance 

##### 思想
- 分析题意, 找到题目的目标
- 虽然Map自己不能有sort的规律, 但是可以maintain global variable



---

**22. [HashWithCustomizedClass(LinkedList).java](https://github.com/awangdev/LintCode/blob/master/Java/HashWithCustomizedClass(LinkedList).java)**      Level: Medium      Tags: [Hash Table]
      
练习HashMap with customized class. functions: get(), put(), getRandom() 

#### Hash Table
- store map as array: `Entry<K,V>[] table;`
- store entry as linked list: `public Entry(K key, V value, Entry<K,V> next)`
- compute hashKey: `Math.abs(key.hashCode()) % this.capacity`
- Handle collision:
- 1. Check if duplicate (matching key), if so, replace and return
- 2. Check through the linked list, find find duplicate (matching key), replace and return.
- 3. If no duplicate, add the entry to the tail
- Find item: compute hashKey -> find linked list -> iterate over list to find a matching key.



---

**23. [Word Ladder II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Ladder%20II.java)**      Level: Hard      Tags: [Array, BFS, Backtracking, DFS, Hash Table, String]
      
给一串string, start word, end word. 找到所有从 startWord -> endWord的最短路径list. 

变化方式: mutate 1 letter at a time.

#### BFS + Reverse Search
- 用BFS找最短路径.
- 问题: how to effectively store the path, if the number of paths are really large? 
- If we store Queue<List<String candidates>>: all possibilities will very large and not maintainable
- 用BFS做出一个反向structure, 然后再reverse search

##### BFS Prep Step
- BFS 找到所有start string 可以走到的地方 s, 放在一个overall structure里面: 注意, 存的方式 Map<s, list of sources>
- BFS时候每次都变化1step, 所以记录一次distance, 其实就是最短路径candidate (止步于此)
- 1. 反向mutation map: `destination/end string -> all source candidates` using queue: `Mutation Map`
- Mutation Map<s, List<possible src>>: list possible source strings to mutate into target key string.
- 2. 反向distance map: `destination/end string -> shortest distance to reach dest`
- Distance Map<s, possible/shortest distance>: shortest distance from to mutate into target key string.
- BFS prep step 并没解决问题, 甚至都没有用到end string. 我们要用BFS建成的反向mapping structure, 做search

##### Search using DFS
- 从结尾end string 开始扫, 找所有可以reach的candidate && only visit candidate that is 1 step away
- dfs 直到找到start string.

##### Bi-directional BFS: Search using BFS
- reversed structure 已经做好了, 现在做search 就可以: 也可以选用bfs.
- `Queue<List<String>>` to store candidates, searching from end-> start



---

**24. [Group Shifted Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Group%20Shifted%20Strings.java)**      Level: Medium      Tags: [Hash Table, String]
      

#### Convert to orginal string
- shit by offset. `int offset = s.charAt(0) - 'a';`
- increase if less than 'a': `if (newChar < 'a') newChar += 26;`

#### Previous notes
- 相同shift规则的string, 能被推算到同一个零起始点，就是共同减去一个char,最后就相等。以此作为key，用HashMap。一目了然。
- 记得根据题目意思，一开始要String[] sort一下。



---

**25. [Path Sum IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20IV.java)**      Level: Medium      Tags: [DFS, Hash Table, Tree]
      
给一串3-digit 的数组. 每个数字的表达一个TreeNode, 3 digit分别代表: depth.position.value

这串数字已经从小到大排列. 求: 所有可能的 root->leaf path 的所有可能的 path sum 总和. 

#### DFS, Hash Table
- 因为`前两个digit可以uniquely identify`一个node, 所以可以把前两个digit作为key, 定位node.
- 特点: 比如考虑root, 有 n 个leaf, 就会加 n 遍root, 因为有 n 个 unique path嘛.
- 实现: 每个node, 上来先把curr value加进sum; 只要有child, 到这个node位置的以上path sum 就要被重加一次.
- format: depth.position.value. (on same level, position may not be continuous)
- approach: map each number into: <depth.position, value>, and dfs. 
- Start from dfs(map, rootKey, sum):
- 1. add node value to sum
- 2. compute potential child.
- 3. check child existence, if exist, add sum to result (for both left/right child). Check existence using the map.
- 4. also, if child exist, dfs into next level
- Space, time O(n)



---

**26. [[lint]. Longest Words.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Longest%20Words.java)**      Level: Easy      Tags: [Hash Table, Lint, String]
      
给一串String, 找到最长的长度, 把最长的String全都return

#### Hash Table
- <Integer,List<String>>
- 存最长值, 最后map.get(max) 



---

**27. [[lint]. Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Anagrams.java)**      Level: Medium      Tags: [Array, Hash Table, Lint]
      

把anagram找到并output

#### HashMap
- 存在int[26], Arrays.toString(arr) 就是 string key: character frequency map
- anagram都有一样的key, 存进hashmap<string, list of anagrams>
- output anagrams

#### HashMap + Sort
- HashMap 的做法. sort每个string, 存进HashMap, 重复的就是anagrams,最后输出。   
- toCharArray
- Arrays.sort
- Stirng.valueOf(char[])
- 时间n*L*O(logL),L是最长string的长度。

#### Previous Notes
- Arrays.toString(arr)的做法。arr是int[26], assuming only have 26 lowercase letters.    
- Count occurrance, 然后convert to String，作为map的key.
- Time complexity: nO(L)
- 另一种做法：http://www.jiuzhang.com/solutions/anagrams/   
- 1. take each string, count the occurrance of the 26 letters. save in int[]count.   
- 2. hash the int[] count and output a unique hash value; hash = hash * a + num; a = a * b.   
- 3. save to hashmap in the same way as we do. 
- 这一步把for s: strs 里面的时间复杂度降到了O(L). L = s.length().   
- Need to work on the getHash() function.
- 时间变成n*O(L). Better.




---

**28. [[lint]. Lowest Common Ancestor II.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Lowest%20Common%20Ancestor%20II.java)**      Level: Easy      Tags: [Hash Table, Lint, Tree]
      
给一个Binary Tree root, 以及两个node A, B. 特点: node里面存了parent pointer. 找 lowest common ancestor


#### Hash Set
- 这个题有个奇葩的地方, 每个node还有一个parent, 所以可以自底向上.
- save visited in hashset. 第一个duplicate, 就是A B 的 lowest common ancestor

#### Save in lists
- 自底向上。利用parent往root方向返回
- 把所有parent存下来, 然后在两个list里面找最后一个 common node

#### 注意
- 无法从root去直接搜target node 而做成两个list. 因为根本不是Binary Search Tree！




---

**29. [[lint]. Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Subarray%20Sum.java)**      Level: Easy      Tags: [Array, Hash Table, Lint, PreSum, Subarray]
      

给一串数字, 找其中的一个subarray的 [start, end] index, 条件: subarary sum == 0.

#### Hash Table
- `subarray sum equals k` 的简单版: k = 0
    - 求preSum, 然后不断check `map.containsKey(preSum - k)`. 
    - 如果 `priorSum = preSum - k == 0`, 说明 [priorSum.index + 1, curr index] 就是我们要找的这一段

#### Previous notes, same preSum + map solution
- 分析出，如果sum[0~a]=x, 然后sum[0~b]=x, 说明sum[a+1 ~ b] == 0
- 用hashMap存每个sum[0~i]的值和index i. 如果有重复，就找到了一组sum为0的数组.



---

**30. [[tool]. Hash Function.java](https://github.com/awangdev/LintCode/blob/master/Java/[tool].%20Hash%20Function.java)**      Level: Easy      Tags: [Hash Table, Lint]
      

In general, there is no universal recipe to stick to when it comes to implementing hashCode().
https://www.baeldung.com/java-hashcode

#### Hash Function
- 解释Hash怎么做. 
- Hash function例子：    
- hashcode("abcd") = (ascii(a) * 33^3 + ascii(b) * 33^2 + ascii(c) *33^1 + ascii(d)*33^0) % HASH_SIZE 
- 用到的参数比如: magic number 33, HASH_SIZE.

- Hash的意义是：给一个string key, 转换成数字，从而把size变得更小。    
- 真实的implementation还要处理collision, 可能需要design hash function 等等。


##### 每一步都%HASH_SIZE的原因
- hashRst = hashRst * 33 + (int)(key[i]);       
- hashRst = hashRst % HASH_SIZE;       
- 原因是，hashRst会变得太大，所以不能算完和 再 %...



---

**31. [36. Valid Sudoku.java](https://github.com/awangdev/LintCode/blob/master/Java/36.%20Valid%20Sudoku.java)**      Level: Easy      Tags: [Enumeration, Hash Table]
      

#### Hash Set
- 用HashSet存visited row/col/block.
    - 在nest for loop里面validate row,col,and block.     
    - Special: validate block要利用i 和 j 增长的规律   
- i, j are [0~n) can build block boundary in a for loop:
    - `int c = 3 * (i % 3) + j % 3;` //make use of how i and j increases
    - `int r = 3 * (i / 3) + j / 3;`

#### A bit Slower approach
- 单独做block validation: validate block的时候虽然看到了4层for. 其实也就是n^2
- 可能代码稍微复杂一点



---

**32. [359. Logger Rate Limiter.java](https://github.com/awangdev/LintCode/blob/master/Java/359.%20Logger%20Rate%20Limiter.java)**      Level: Easy      Tags: [Design, Hash Table]
      

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

**33. [347. Top K Frequent Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/347.%20Top%20K%20Frequent%20Elements.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue]
      

给一串数字, 找到top k frequent element, 并且time complexity 要比nLogN要好

#### Method1: Bucket Sort. HashMap + bucket List[]
- Use HashMap to store <num, freq>
- Bucket `List<Integer>[]`: stores <count, list unique element with that count>
    - Size of the data structure will be uniqe item size.
    - The bucket[i] stores item at frequency i
- Simply loop from bucket.length -> 0, when bucket[i] not null, add to result.
- Solid O(n)


#### Method2: PriorityQueue, MinHeap
- Use regualr priorityQueue to sort by frequency ascendingly
- the queue.peek() record has lowest frequency, which is replacable
- Always only maintain k elements in the queue, so sorting is O(logk)
- IMPORTANT: remember to `rst.add(0, x)` for desired ordering
- time faster than maxHeap: O(nlogk)
- option1: just use `map<num, freq>`; option2: use `class Record {int num; int freq}`

#### MaxHeap Attempt. INCORRECT
- 题目有提醒: 必须beetter than O(nLog(n)).
- max heap approach stores all nodes: it is wrong
    - even though freq count size m < n, but it can be m == n. ALL unique. 
    - then it is O(nlogN) again.
- therefore, storing all items into pq is INCORRECT.



---

**34. [953. Verifying an Alien Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/953.%20Verifying%20an%20Alien%20Dictionary.java)**      Level: Easy      Tags: [Hash Table]
      

#### Hash Table
- mark the char position
- check adjacent words
- Optimization
    - a) If s1 equals s2, just return true, no need to continue.
    - b) if s2 (app) is a substring of s1(apple), just return false.




---

**35. [1213. Intersection of Three Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/1213.%20Intersection%20of%20Three%20Sorted%20Arrays.java)**      Level: Easy      Tags: [Hash Table, Two Pointers]
      

Very similar to 349.Intersection of Two Arrays.

#### Hash Table
- Use set to check
- Verify duplicates at end rst

#### Two Pointers
- similar to Intersection of Two Sorted Arrays
- Start from front/back, process 1 item at a time
    - if match, move all pointers 
- Optoin1: check from back
- Optoin2: check from frotn



---

**36. [76. Minimum Window Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/76.%20Minimum%20Window%20Substring.java)**      Level: Hard      Tags: [Hash Table, Sliding Window, String, Two Pointers]
      

基本思想:
- 用个char[]存string的frequency.
- 2 pointer: 
    - move `end` to find a valid window; 
    - once valid inwindow found: now move `start` to narrow down to minimum window.
    - once window invalid, continue moving `end` and repeat last 2 steps
- HashMap的做法比char[]写起来要复杂一点, 但是更generic

#### Method0: Sliding Window + freq[256] + counter
- Almost identical approach as in `438. Find All Anagrams in a String` 
- use sliding window template:
    - 1) extend right pointer and reduce char count
    - 2) process when count == 0
    - 3) contract/shrink left side
- special on the `3) step`:
    - there is no hard length limit in this problem: in fact, the goal is to find the shortest length
    - `3) step` now apperas in the `while(counter == 0)` loop
    - shrink the left side of the window as long as counter == 0, until we break the `counter==0` balance.
- time: O(n) one pass
- space: O(1), freq[256] can be ignored.


#### Method1: init a valid freq map; maintain with counter
- Two Pointers, use 1 char freq map + counter to determine valid state
- Inspired by: https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
- Idea: use freqMap and counter to maintain a valid substring range, use two pointers to iterate; reduce to `counter==0` which is the valid substring state.
- Steps:
    - 1) build valid freq count map based on target string
    - 2) use end index [0~n) to find valid char and reduce counter to find valid range
    - 3) count==0 gives valid range: process; then `map[s.charAt(start++)]++ == 0` to break the peace
- Explain `if (map[s.charAt(start++)]++ == 0) counter++`: 
    - when `count != 0`, `map[s.charAt(end++)]--` reduces freq regardless of what char it visits (it can be ANY char, rather than T characters)
    - when `count == 0`, `map[s.charAt(start++)]++` increases freq regardless of what char that is.
        - if `map[s.charAt(start)] == 0`: it is a T character being reduced to 0 previously (so we can break the balance on this char)
        - YES, map has other index that has 0 freq: however, `start` ONLY covers indexes that `end` has stepped through :)
- time: O(n)
- space: O(1)
- much faster than method2: skip the O(256*n) comparison logic.
- Note: from the concept, it is the reversed thinking of method2.

#### Method2: build valid map on the fly and compare. Two Pointers, Use 2 Char freq map
- Use 2 char freq maps: source/target.
    - target map: fixed freq map, used for comparision
    - source map: attempt to build a valid freq map on the fly
- two pointers: 
    - use index `start=[0, n)` as start index of source candidate
    - have a end pointer that will attempt to as far as possible to find 1st valid sequence
- time: have double while loop, but still O(n), why?
    - end pointer will at most reach full length n, only once
    - start pointer iterate source strichtly once O(n)
    - overall, it will be O(n)
- space: O(1), only used a constant char[256]
- Option2: use map, a bit more generic



---

**37. [244. Shortest Word Distance II.java](https://github.com/awangdev/LintCode/blob/master/Java/244.%20Shortest%20Word%20Distance%20II.java)**      Level: Medium      Tags: [Array, Design, Hash Table, Two Pointers]
      

#### Map
- Prep: 存Map<word, index list>
- Process: 相继从两个 index list 里面拿出 p1,p2
    - 根据index的大小, 移动双指针: try to move the pointers closer; always calculate diff
- Optionally: if one list is much larger, do binary search on the larger list



---

**38. [987. Vertical Order Traversal of a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/987.%20Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree.java)**      Level: Medium      Tags: [BFS, Binary Tree, DFS, Hash Table, Tree]
      
space: O(n)

Very similar to `314. Binary Tree Vertical Order Traversal` with 1 special condition: if 2 nodes at same (offset, level):
sort it by its value 

#### Method1: DFS
- the special requirement causes: we have to track exact position of nodes
- Using `Node {int offset, level, val}` and `Map<offset, Map<level, List<Val>>>`:
    - set all nodes to its correct position
    - output all together
- the `max/min` offset allows us to loop over the map in a ordered manner (save efforts of sorting)
- time: O(n) to mark all nodes at correct spot, but `O(nlogn)` to sort the vertical array
- space: O(n), mark all nodes in the nested map

#### Method2: BFS + Hash table
- A (offset, level) has 2 nodes: use nested `Map<offset, Map<level, List<Val>>>` to track nodes
- Also need a `class Node{int offset; TreeNode node}` to build queue: 
    - need `offset`: queue at each level cannot derive level index
    - need `TreeNode`: `Node` extends original `TreeNode` so we can queue it.
- lots code to write due to the `class Node` for BFS



---

**39. [204. Count Primes.java](https://github.com/awangdev/LintCode/blob/master/Java/204.%20Count%20Primes.java)**      Level: Easy      Tags: [Hash Table, Math]
      
计数: 所有小于n的prime number.

#### prime number定义
- >=2的没有除自己和1以外公约数的数。   
- 还有另外一个定义方法: 这个n,有没有小于n的一个i, 而达到： i * i + # of i = n. 如果有，那就不是 prime   

#### Steps
- 一个boolean长条，存isPrime[]。 然后从i=2， 全部变true.
- hash key: the number itself
- 然后利用这个因子的性质，非prime满足条件： self*self, self * self + self ... etc.     
- 所以就check每一个j, j+i, j+i+i, 然后把所有non-prime全部mark成false.     
- 最后，数一遍还剩下的true个数就好了   



---

**40. [496. Next Greater Element I.java](https://github.com/awangdev/LintCode/blob/master/Java/496.%20Next%20Greater%20Element%20I.java)**      Level: Easy      Tags: [Hash Table, Stack]
      

#### stack
- use stack to hold all elements
    - keep poping if `stack.peek() < num`
    - use map to record (top, num)
- time O(n), run through base once and sub-sequence once
- space O(n), stack, map

#### HashMap
- O(n) space, O(n^2) time worst case



---

**41. [694. Number of Distinct Islands.java](https://github.com/awangdev/LintCode/blob/master/Java/694.%20Number%20of%20Distinct%20Islands.java)**      Level: Medium      Tags: [DFS, Hash Table]
      

#### DFS + HashSet
- DFS can find # of island, just like `200.Number of Islands`, aim to count total
- We need to map same-shap land
    - One approach: print the **footprint** starting from coordinate (0, 0)
    - Another approach: print the actual island in its boundary, like a QR code. (too hard to code, skip)
- Footprint approach: 
    - 1. always assume a newly found islands starts from (0, 0)
    - 2. take 4 direction from init pos and keep printing the footprint
    - 3. Since we always visit nodes from top->right->nextrow, we always visit top-left cornor of a new island, and the footprint will be identical
        - Otherwises, if needed, we can sort the footprint and output the hash
- time: O(n), visit all
- space: O(n), store footprint, and dfs stacks worst case visit all nodes 



---

**42. [136. Single Number.java](https://github.com/awangdev/LintCode/blob/master/Java/136.%20Single%20Number.java)**      Level: Easy      Tags: [Bit Manipulation, Hash Table]
      
Bit XOR: 当两个bit不同时，return 1. 
题目正要消光所有重复出现的数儿留下出现一次的那个.



---

**43. [1048. Longest String Chain.java](https://github.com/awangdev/LintCode/blob/master/Java/1048.%20Longest%20String%20Chain.java)**      Level: Medium      Tags: [Bucket Sort, DP, Hash Table, Sort]
      

#### Hash table, DP
- store `Map<Word, Longest Chain Length>`
- sort all words, try from short to long: short word will be calculated first to serve later words as candidate
- time: O(nlogn)
- space: O(n)

#### Hash Table, Bucket Sort,DP
- store `Bucket: List[17] of words`, given word size limit [0 ~ 16]
- time: O(n)
- space: O(n)



---

**44. [299. Bulls and Cows.java](https://github.com/awangdev/LintCode/blob/master/Java/299.%20Bulls%20and%20Cows.java)**      Level: Medium      Tags: [Hash Table]
      

#### Solution1: use int[10] to count frequency
1. check match chars
1. check unmatched chars by counting and offset their frequency
    - count++ on secret chars: if secretCount is ever < 0 => `char g` has match, then cows++
    - count-- on guess chars: if guessCount is ever >0 => `char s` has match, then cows++

#### Solution2: Use hashmap to count
- Improvement: since all digit, use int[10] to count



---

**45. [266. Palindrome Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/266.%20Palindrome%20Permutation.java)**      Level: Easy      Tags: [Hash Table]
      

给String, 看permutation是否能是palindrome

#### Hash, or ASCII array
- count char occurrance
    - 只可以接受一个odd # appearance.
- 考虑所有 256 ASCII code, 如果还要拓展, 就用HashMap<Character, Integer>
- 注意, 不能assume lower case letter. 应该至少是所有ASCII code



---

**46. [311. Sparse Matrix Multiplication.java](https://github.com/awangdev/LintCode/blob/master/Java/311.%20Sparse%20Matrix%20Multiplication.java)**      Level: Medium      Tags: [Hash Table]
      

给两个matrics, 做乘积. 注意, 是sparse matrix (特点: 很多0).

#### Hash Table
- Recall matric multiplication rules: result[i][j] = sum(A-row[i] * B-col[j])
- `sparse matric: lots positions are zero`
- 平白地写matric multiplication 没有意义, 重点就是optimization:
- `optimization`: for A-zero-row, and B-zero-col, there is no need to calculate, just return 0.
- 1. Find A-zero-rows and store in setA, same for setB
- 2. during multiplication, reduce time complexity.
- Base: O(mnk), where `m = A.row`, `n = B.col`, `k = A.col = B.row`

#### Matrices
- 乘法规则: result[i][j] = sum(A-row[i] * B-col[j])
- A column size == B row size. 并且: 计算顺序是iterate over A column size



---

**47. [140. Word Break II.java](https://github.com/awangdev/LintCode/blob/master/Java/140.%20Word%20Break%20II.java)**      Level: Hard      Tags: [Backtracking, DFS, DP, Hash Table, Memoization]
      

找出所有 word break variations, given dictionary. (`Word Break I` only checks possibility)

利用 memoization: `Map<prefix, List<suffix variations>>`

#### DFS + Memoization, pick a prefix, and find a list of suffix candidates
- IMPORANT, Memoization: `Map<prefix, List<suffix variations>>` to build substring segments. Reduces repeated calculation if the substring has been tried.
- Realize the input s expands into a tree of possible prefixes.
- Find list of candidates from subproblem, and cross-match
- DFS returns List<String> segments of target s: every for loop takes a prefix substring, and append with all suffix (result of dfs)
- Time O(n!). Worst case, permutation of unique letters: `s= 'abcdef....'`, and `dict=[a,b,c,d,e,f...]`

#### Method2: DFS on suffix + memo of failed cases, like in WordBreakI
- DFS on string: find a valid prefix, dfs on the suffix, building individual candidate in list till substring exhaust. 
- improvement:
    - use memo to record failed case (solved the timeout issue explained below)
    - use min/max to as boundary for dict check.
- core code is short; helper code is slightly longer

#### Method3: Regular DPs, kinda too slow
- 两个DP一起用, 解决了timeout的问题: when a invalid case 'aaaaaaaaa' occurs, isValid[] stops dfs from occuring
- 1. isWord[i][j], subString(i,j)是否存在dict中？
- 2. 用isWord加快 isValid[i]: [i ～ end]是否可以从dict中找到合理的解？      
- 从末尾开始查看i：因为我们需要测试isWord[i][j]时候，j>i, 而我们观察的是[i,j]这区间；       
- j>i的部分同样需要考虑，我们还需要知道isValid[0～j+1]。 所以isValid[x]这次是表示[x, end]是否valid的DP。     
- i 从 末尾到0, 可能是因为考虑到isWord[i][j]都是在[0~n]之内，所以倒过来数，坐标比较容易搞清楚。     
- (回头看Word Break I， 也有坐标反转的做法)
- 3. dfs 利用 isValid 和isWord做普通的DFS。

#### Timeout Note
- Regarding regular solution: 如果不做memoization或者dp, 'aaaaa....aaa' will repeatedly calculate same substring
- Regarding double DP solution: 在Word Break里面用了set.contains(...), 在isValid里面，i 从0开始. 但是, contains()本身是O(n); intead,用一个isWord[i][j]，就O(1)判断了i~j是不是存在dictionary



---

**48. [349. Intersection of Two Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/349.%20Intersection%20of%20Two%20Arrays.java)**      Level: Easy      Tags: [Binary Search, Hash Table, Sort, Two Pointers]
      

#### Set
- 用到hashset找unique && duplicate: O(m+n)

#### Binary Search
- 可以用binary search 找数字. 
- Note:binary search一定需要array sorted: nLog(m)



---

**49. [245. Shortest Word Distance III.java](https://github.com/awangdev/LintCode/blob/master/Java/245.%20Shortest%20Word%20Distance%20III.java)**      Level: Medium      Tags: [Array, Design, Hash Table, Two Pointers]
      

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

**50. [771. Jewels and Stones.java](https://github.com/awangdev/LintCode/blob/master/Java/771.%20Jewels%20and%20Stones.java)**      Level: Easy      Tags: [Hash Table]
      

- 给J 和 S两个string. J里的character是unique 的珠宝, S 里面的character包含珠宝和石头. 找S里面有多少珠宝
- Basic HashSet



---

**51. [727. Minimum Window Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/727.%20Minimum%20Window%20Subsequence.java)**      Level: Hard      Tags: [DP, Hash Table, Sliding Window, String, Two Pointers]
      

#### Sliding Window
- DIFFERENT from sliding window for substring (`76. Minimum Window Substring`)
    - because this problem rquries keeping the order of characters from the target string
    - Use a `backtrack mechanism` based on target matching to find closest left starting point to right
- Simple two pointers:
    - 1) move sIndex and tIndex: find all T chars in S, in order.
    - 2) backtrack tIndex to 0; backtrack sIndex to initial char match
    - 3) record potential min result
- Be VERY careful about pointer and index.
- time: O(n^2), backtrack n steps
- Since it requires **order of substring**, `freqMap+counter+twoPointers` approach is NOT applicable

#### DP
- TODO



---

**52. [387. First Unique Character in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/387.%20First%20Unique%20Character%20in%20a%20String.java)**      Level: Easy      Tags: [Hash Table, String]
      

#### Count appearance with int[256]
- 按照题意, 找到第一个 first index == last index的字母.

#### Count appearance with hashmap (more scalable)
- 用hashmap存字母的index, 有些重复字母的index就会是个list. 
- 找到单一index, 结合成list, sort, return list.get(0)
- slow due 



---

**53. [146. LRU Cache.java](https://github.com/awangdev/LintCode/blob/master/Java/146.%20LRU%20Cache.java)**      Level: Medium      Tags: [Design, Doubly Linked List, Hash Table, Linked List]
      

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

**54. [246. Strobogrammatic Number.java](https://github.com/awangdev/LintCode/blob/master/Java/246.%20Strobogrammatic%20Number.java)**      Level: Easy      Tags: [Enumeration, Hash Table, Math, Two Pointers]
      

根据题意枚举, 再根据规则basic implementation

#### HashTable + Two Pointer
- compare left/right

#### Alter input
- flip number (6 and 9), and then reverse the string, see if the string is the same.
- takes more




---

**55. [463. Island Perimeter.java](https://github.com/awangdev/LintCode/blob/master/Java/463.%20Island%20Perimeter.java)**      Level: Easy      Tags: [Hash Table]
      
#### Brutle, Count Blocks and Walls
- 每个格子 +4 个墙;
- 每个shared的墙要减去: 从每个island走去另外一个, 都-1 (最终没面墙, -2)

#### Hash Table
- 把每个block相连的block全部存在以当下block为key的list里面. 那么这里需要把2D坐标, 转化成一个index.
- 最后得到的map, 所有的key-value应该都有value-key的反向mapping, 那么就可以消除干净, 每一次消除就是一个shared wall.
- 一点点optimization: DFS去找所有的island, 如果island的图非常大, 而island本身不大,那么适合optimize.
- 但是整体代码过于复杂. 不建议写.




---

**56. [170. Two Sum III - Data structure design.java](https://github.com/awangdev/LintCode/blob/master/Java/170.%20Two%20Sum%20III%20-%20Data%20structure%20design.java)**      Level: Easy      Tags: [Design, Hash Table, Memoization]
      

#### Hash Table, Memo
- Use Map<number, count > to store the inputs
- Iterate over map to find the pair
- Use Set<int> memo to store the success cases for fast return
- time: O(n), loop over all elements in map
- space: O(n), store all elements in map & memoization set



---

**57. [981. Time Based Key-Value Store.java](https://github.com/awangdev/LintCode/blob/master/Java/981.%20Time%20Based%20Key-Value%20Store.java)**      Level: Medium      Tags: [Binary Search, Hash Table, TreeMap]
      

#### Method1: Binary Search
- use hash to store <key, list of values>
- binary serach on list of values

#### Method2: TreeMap
- use hash to store <key, TreeMap<Timestamp, Value>>
- treemap.floorKey(timestamp) finds the top item below certain timestamp



---

**58. [202. Happy Number.java](https://github.com/awangdev/LintCode/blob/master/Java/202.%20Happy%20Number.java)**      Level: Easy      Tags: [Hash Table, Math]
      

Basic Implementation of the requirements.

用HashSet存查看过的数值。若重复，return false.



---

**59. [380. Insert Delete GetRandom O(1).java](https://github.com/awangdev/LintCode/blob/master/Java/380.%20Insert%20Delete%20GetRandom%20O(1).java)**      Level: Medium      Tags: [Array, Design, Hash Table]
      

#### Hash Table, Swap when removing
- Map: `map<val, index>, Lis: tracks `index->value`
- list maintain 用来 insert/remove/random operations.
- Remove: swap input valueIndex & tialIndex = list.size() -1.
    - list.remove(object) 应该是要O(logn) 做一个search的.
    - list.remove(list.size() - 1) is cheapter



---

**60. [560. Subarray Sum Equals K.java](https://github.com/awangdev/LintCode/blob/master/Java/560.%20Subarray%20Sum%20Equals%20K.java)**      Level: Medium      Tags: [Array, Hash Table, PreSum, Subarray]
      

给一串数字, 找其中的 # of subarray的 where subararySum == k.

#### Method1: Hash Table to sture presum (like in 2 sum problem)
- Approach#4 of https://leetcode.com/problems/subarray-sum-equals-k/solution/
- Hash Table two sum 思想, but to save frequency of current sum: `preSumCount<sum, count>`
    - for loop 从左开始积累 `preSumCount<sum, count>`
    - derive `priorSum = sum - k`: 看看前面有多少此种sum, `preSumCount.get(priorSum)`
        - `# ways to reach priorSum` gives # of ways for that `priorSum + k = curr Sum`
        - therefore, count += preSumCount.get(priorSum)
- O(n) time, O(n) space
- Note: 如果需要实际index, 可以存 `Map<Integer, List<Index>>`

#### Method2: Calculate each individual range, with PreSum
- presum: socalled `cummulative sum`
- move from starting point i = [0 ~ n -1] and test each `range = [i ~ j]`
- use presum to verify k: `preSum[j + 1] - preSum[i]`
- time: O(n^2): `1 + 2 + 3 + 4 ... + n ~= O(n^2)`




---

**61. [219. Contains Duplicate II.java](https://github.com/awangdev/LintCode/blob/master/Java/219.%20Contains%20Duplicate%20II.java)**      Level: Easy      Tags: [Array, Hash Table]
      

Unsorted array, 找出是否有duplicate elemenets: 必要条件是, 这两个element的index i,j 的大小最多相差k.

#### HashSet
- 很巧妙地根据k range地条件
    - 把HashSet里面的值控制在[i - k, i]
    - 每次不断地往set里面加新元素, 从set里减去末尾index的元素
- 而set.add(x)如果遇到重复, 会return false.
- 一旦在这个length k 的 range里面, 有重复, 就符合条件. 
- Time O(n)

#### HashTable<value, List of duplicates>
- Time O(nm), m = # of duplicates. 太慢
- 记录每个element value的index in the list
- 一旦有重复element重复, 就把整个list of indexes 端出来, 查看有没有符合条件的: (index - i) <= k


#### 这两种做法的区别很有艺术感觉
- 方法1是限定选拔的candidate, 不合格就去掉, 那么一旦有符合条件的(duplicates), 那么一定中, 剩下的就不看了.
- 方法2是把符合条件的index找出来, 集中处理, 但是所有candidate都会选出来
- 就好像招人一样: 一种是遇到好的就停止; 第二种是看过所有人, 从中选拔最好的. 显然第一种更快.




---

**62. [205. Isomorphic Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/205.%20Isomorphic%20Strings.java)**      Level: Easy      Tags: [Hash Table]
      

#### HashMap
- check 2 failture cases:
    - same key, value not matching
    - two key maps to same value



---

**63. [314. Binary Tree Vertical Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/314.%20Binary%20Tree%20Vertical%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Hash Table, Tree]
      

给一个Binary Tree, traverse所有node, 按照vertial order 排列成output: List<List> 

重点是: col里面有排序, lower level的排在前面; 如果node遇到collision在同一个位置: 根据他们的相对位置 先放left, 再放right

#### BFS
- 1) level-traverse all nodes, 2) add node to appropriate col list(using map)
- For final output: 
    - Use min/max to track map keys, since the keys are continous
    - Map does not provide random access; unless map key is marked with sequence i = [min, max]
- Since each vertical list is appended level by level: no need to sort during output. FAST
- time: O(n), visit all nodes
- spac: O(n) to store

#### DFS
- Regular DFS to traverse all nodes, and add node to appropriate col list (using map)
- Problem: DFS does not provide natural ordering for nodes on a row.
    - Left side may have a super deep Right child branch, which will be added to col list first (since left side is visisted first)
    - It is wrong because right branch may have nodes in same column but at higher level
    - To Solve: preserve `level` for all nodes in same column
- Need to sort the final list, and slow: visit all nodes O(n) + O(KMlogM), K = # of cols, M = # of items in col
- Time: O(nLogN). O(n) + O(KMlogM), K = # of cols, M = # of items in col; in extrem, it can be a vertical line of nodes, then sort: O(nLogN)
- Space: O(n)




---

**64. [242. Valid Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/242.%20Valid%20Anagram.java)**      Level: Easy      Tags: [Hash Table, Sort]
      

#### int[26]

#### HashMap<Character, Integer>



---

**65. [340. Longest Substring with At Most K Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/340.%20Longest%20Substring%20with%20At%20Most%20K%20Distinct%20Characters.java)**      Level: Hard      Tags: [Hash Table, LinkedHashMap, Sliding Window, String, Two Pointers]
      

- Method1 and Method2 are identical to `159. Longest Substring with At Most Two Distinct Characters`. 
- However, time complexity for Method2 in increases to O(nk). https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/solution/
- we want to do better than that (Method3)

#### Method1: Slinding Window, Two Pointers: move 1 element at a time
- Typical slinding window: the goal is to keep a distinct char size/window of size 2.
- use a map<char, count> to track; map.size() is the window size. Follow the template
    - 1) move right pointer, and update freq count map
    - 2) if map.size() == k, process and record max len
    - 3) if map.size() > k, maintain window size: drop curr left char, update map
- return max
- time: O(n)
- space: O(k)

#### Method2: Sliding window, Two Pointer: truncate the entire block at a time
- record last occurance index in map<char, index>
    - when size goes over limit, find last occurance of left-most element
    - set left = leftMostIndex + 1. 
    - This truncates entire block before the last occurance of left-most element
- sliding window 的切割: 用hashmap 存 last occurrance of char index; 
- map.remove(c) 之后, 就等于彻底切掉了那一段; 那么 map.get(c) + 1 也就是新的 left window border
- time: O(nk) to find the left-most element
- space: O(k)

#### Method3: Sliding window + LinkedHashMap
- https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/solution/
- as mentioned above, Method2 uses O(nk), because it takes O(k) to find head item that was inserted first
    - meanwhile, we still need the hash map feature to get/put/remove last occurance of a char with O(1)
- Solution: use a LinkedHashMap: 
    - `map.entrySet().iterator()` maintains the insertion order!
- Special handling:
    - since we want the `lastOccurMap` to preserve laset insertion order
    - we need to `remove` the char every time before put.
- time: O(n)
- space: O(k)




---

**66. [217. Contains Duplicate.java](https://github.com/awangdev/LintCode/blob/master/Java/217.%20Contains%20Duplicate.java)**      Level: Easy      Tags: [Array, Hash Table]
      


无序数组, 找是否有重复element, return true/false.

#### HashSet
- No brain: HashSet.
- Time O(n), Space O(n)

#### Sort, Binary Search
- Arrays.sort(x): Time O(nLogN), Space O(1)
- 排序后, 重复数会排在一起, 然后 binary search



---

**67. [1146. Snapshot Array.java](https://github.com/awangdev/LintCode/blob/master/Java/1146.%20Snapshot%20Array.java)**      Level: Medium      Tags: [Array, Hash Table, TreeMap]
      


#### Hash Table, TreeMap; atomic save
- store efficiently: use List<Map<snapId, val>>. only preserve changed itemd
- if no match, find last modifed item based on snapId, use TreeMap.floorEntry
    - map.floorEntry(id) return the item.key lower or equal to id
- Utilize a `buffer: Map<Integer, Integer>` and perform atomic save



---

**68. [1. Two Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/1.%20Two%20Sum.java)**      Level: Easy      Tags: [Array, Hash Table]
      

#### HashMap<value, index>
- 相对暴力简洁: 找到一个value, 存一个index
- 若在HashMap里面 match 到结果, 就return HashMap里存的index. 
- O(n) space && time.

#### Sort array, two pointer
- 前后++, --搜索. Sort 用时O(nlogn).     
- 1. 第一步 two pointer 找 value.       
- 2. 注意，要利用额外的空间保留original array， 用来时候找index. (此处不能用HashMap，因为以value 为key，但value可能重复)      
- O(n) space, O(nlogn) time.    




---

**69. [139. Word Break.java](https://github.com/awangdev/LintCode/blob/master/Java/139.%20Word%20Break.java)**      Level: Medium      Tags: [DP, Hash Table, Sequence DP]
      

给一个String word, 和一个字典, 检查是否word可以被劈开, 而所有substring都应该是dictionary里面的words.

#### Sequence DP
- Bottom-up, test simply case. Sequence DP.
- true/false problem, think about dp
    - 子问题: 前i个字母, 是否可以有valid break
    - check: 1) dp[j] &&  2) `if substring(j, i) valid`, for all j = [0 ~ i]
    - dp = new boolean[n + 1]; dp[0] = true;
    - test: `dp[i] |= dp[j] == true && word[j, n] in dict`. 
    - Need iterate over i = [0 ~ n], also j = [0, i]
    - When there is a way to make dp[i] == true, then break the [j ~ i] loop, move on to test dp[i++]
- Use set dict: `dict.contains()`
- Improvement: O(n) to figure out max length, so we can skip some substring[j~i] dict.contains()
- overall O(n^2) time since the double for loop

#### DFS
- Top-Down, break into small problems: Check front subString, and put the rest substring into dfs to test
- Memoization: for tested failed substring, record and do NOT test them again.
- Same Improvement as in DP: use max/min length of dict words as boundary



---

**70. [105. Construct Binary Tree from Preorder and Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/105.%20Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Hash Table, Tree]
      

如题

#### DFS ApproachA:
- use preorder to find root, one index at a time (global index)
- use the root to divide and conquer inorder int[] to 2 sides;
    - root.left = dfs(left); root.right = dfs(right)
    - end stage: start == end index, create a node
- can use a map to store inorder <val, index> for O(1) find

#### DFS
- 和Construct from Inorder && Postorder 想法一样。
- 写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。
- 跟Convert Sorted Array to Binary Tree类似, 找到对应的index, 然后:
    - node.left = dfs(...), node.right = dfs(...)
- Divide and Conquer
    - optimize on finding `mid node`: given value, find mid of inorder:
- Instead of searching linearly, just store inorder sequence in `map <value -> index>`, O(1)
- IMPORATANT: the mid from inorder sequence will become the main baseline to tell range: 
- `range of subTree = (mid - inStart)`
- sapce: O(n), time: O(n) access



---

**71. [274.H-Index.java](https://github.com/awangdev/LintCode/blob/master/Java/274.H-Index.java)**      Level: Medium      Tags: [Bucket Sort, Hash Table, Sort]
      

找到h-index, 给的citation int[] 并不是sorted. h-index 的definition 具体看题目.

#### Sort, find h from end
- 例子写出来，发现可以sort以后按照定义搜索一遍。 nlogn.
- 搜索一遍时候可以优化，用binary search. 但是没意义，因为array.sort已经用了nlogn
- 题目给的规则, 从小到大排序后: 剩下的 paper `n-h`, 全部要 <= h 个 citation.
- time O(nlogn), search O(n)

##### 正向思考
- 从i = 0 开始找第一个 `citations[i] >= h`, 就是第一个符合 h-index 规则的paper, return h

##### 反向思考
- 如果从 h = n, 每次h--; 那么 `x = n - h` 就是从 `[0 ~ n)` 开始找第一个 `dictations[x] >= h`, 就是结果
- 同时, `dictations[x-1]` 就是最后一个(dictation最多的)其余的paper.

#### Couting Sort / Bucket Sort
- O(n)
- Bucket sort的思想(更像是counting sort?): 过一遍 input, 把dictation value 作为 index, 分布在bucket[index]上++
- bucket[x] 是 count when # of citation == x. 
- 如果 x 大于 n的时候, 就超出了index范围, 但是刚好这个问题可以包容, 把这样的情况记位在bucket[n]就可以
- 巧妙: `sum += bucket[h]` where `h = [n ~ 0]` 利用了h-index的definition:
- #of papers (sum of bucket[n]...bucket[0]) has more than h cidations 
- 这里运用到了bucket sort的思想, 但是并不是sorting, 而h-index的定义运用的很巧妙.
- Read more about actual bucket sort: https://en.wikipedia.org/wiki/Bucket_sort

#### More about Counting Sort
1. Application: for number/character range
1. Steps:
	- Find range, define countArray
	- Count element and record in the array
	- PreSum the countArray
	- Start from beginning of the array, `print & decrese count` to produce the sorted elements




---

**72. [350. Intersection of Two Arrays II.java](https://github.com/awangdev/LintCode/blob/master/Java/350.%20Intersection%20of%20Two%20Arrays%20II.java)**      Level: Easy      Tags: [Binary Search, Hash Table, Sort, Two Pointers]
      

#### HashMap
- Map of nums1: <num, # appearance>
- check nums2 against nums1 map
- time:O(n + m)
- space:O(n + m)

#### Binary Search
- 



---

**73. [49. Group Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/49.%20Group%20Anagrams.java)**      Level: Medium      Tags: [Hash Table, String]
      

给一串string, return list of list, 把anagram 放在一起.

#### Hash Table, key 是 character frequency
- 存anagram
- 用 character frequency 来做unique key
    - 用固定长度的char[26] arr 存每个字母的frequency; 然后再 new string(arr).   
    - 因为每个位子上的frequency的变化，就能构建一个unique的string
- O(nk), k = max word length

#### Hash Table, key 是 sorted string (too slow)
- 和check anagram 想法一样：转化并sort char array，用来作为key。
- 把所有anagram 存在一起。注意结尾Collections.sort().
- O(NKlog(K)), N = string[] length, k = longest word length    




---

**74. [720. Longest Word in Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/720.%20Longest%20Word%20in%20Dictionary.java)**      Level: Easy      Tags: [Hash Table, Trie]
      

给串word[], 找最长的Word, 满足条件: 这个Word可以从 word[] 里面一个字母一个字母被build出来.

如果多种答案, respect smallest lexicographical order.

#### Sort, HashSet
- 先排序(lexicographically), 排序以后才能逐个看是否partial string已经存在
    - 用 set.contains(substring(0, n - 1)) 来查看上一步的 substring 是否存在
    - 如果找到, 因为已经按照字母表排序, 找到的这个肯定是这个长度里面最符合的解答.
- 然后brutally找下一个更大的.
- Sort O(n log n), O(n) set space

#### Trie
- 可以先sort words Array: 
    - 长 string 排在前;
    - 相等length, 按照dictionary order 排序
- 全部放入Trie. Trie.insert()
    - 针对sorted words array, 从最长的开始找 Trie.startWith.
    - 一旦找到, 就是符合题意的, 直接return.
- 注意: startWith 必须每一个node都是 isEnd, 才满足'逐个字母拼出' 的条件.
- Time: build Trie O(mn) + sort:O(nlogn) => O(nlogn)
- Space: O(mn)

#### 从最长的word开始做
- 按大小排序 -> 从最大的开始做contains()的比较 -> 结果再按照字母表顺序(lexicographically) sort一下.
- 但是Collections.sort()了两次, 而且再list.contains(), 比较慢




---

**75. [438. Find All Anagrams in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/438.%20Find%20All%20Anagrams%20in%20a%20String.java)**      Level: Medium      Tags: [Hash Table, Sliding Window, Two Pointers]
      

跟 Permutation in String 很像. 给短string p， 长string s.

找所有p的 anagram (permutation) 在s 里面的起始index.

#### Method1: Sliding Window, Hash Table. 
- A creative way of using anagram char count `hash[c] >= 0` to determine if the curr c is a target char of the deesired anagram.
    - because we always reduce hash[c]-- for all characters
    - so only the anagram chars would be `hash[c] >= 0` after reducing.
- https://leetcode.com/problems/minimum-window-substring/discuss/26808/here-is-a-10-line-template-that-can-solve-most-substring-problems
- Slinding window always has left/right pointer:
    - 1) at any given time move 1 index at a time: expand right window, process rsult, shrink left window
    - 2) one of the basic goal is to maintain fixed window size
- algo:
    - calc char freq of the target p, and store in a hash[256]; it will be used to distinguish anagram chars: `hash[c] >= 0` indicates a anagram char
    - expand right window: move right to expand the window; ONLY when meeting a anagram char, count--
    - process result: if count reduces to 0, one anagram is found
    - shrink left window: if (right - left) == p.length(), drop curr left char, and move forward
- how could we rely on only just `count == 0`? 
    - the hidden pre-condition is `right - left must already be p.length()`, which is validaterd in prev iteration
- time: O(n)
- space: O(1)

#### Method2: HashTable
- count character apperance -> hash table, here just a int[26]
    - use a window to record count++ and count--, in order to compare with countP
- prep the countP takes O(m) time
- time: O(n) + O(m)
- space: O(n)





---

**76. [632. Smallest Range Covering Elements from K Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/632.%20Smallest%20Range%20Covering%20Elements%20from%20K%20Lists.java)**      Level: Hard      Tags: [Hash Table, Sliding Window, Two Pointers]
      

#### Method1: Sliding Window
- First sort all of the items together by actual val using `Node {int val, int row}`
- Slinding window goal: 
    - 1) use right to find range that touches all rows, 
    - 2) use left to shrink the range
- Sliding Window Template
    - move right pointer
    - Counts[i] = # of elements used in left/right range
        - when counts[i] == 0, countUnique++; the number of row/list being included
    - when count == row size: 
        - processing & save shorter range by using left/right Pointers
        - move left pointer; when counts[i] == 0, countUnique--
- time: O(nlogn) for initial sort and then O(n) to process
- space: O(n)
- What is hard here? To think of the idea of counting one usage of each row: 
    - when each all rows are used at least 1 time
    - calculate the min dist

#### Method2 PQ?, Similar to merging k array
- https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/discuss/104893/Java-Code-using-PriorityQueue.-similar-to-merge-k-array
- https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/solution/



---

**77. [138. Copy List with Random Pointer.java](https://github.com/awangdev/LintCode/blob/master/Java/138.%20Copy%20List%20with%20Random%20Pointer.java)**      Level: Medium      Tags: [Hash Table, Linked List]
      

deep copy linked list. linked list 上有random pointer to other nodes.

#### HashMap, Linked List, time, space: O(n)
- Basic Implementation of copy linked list:
    - use a iterator node to iterate over the list: 遍历head.next .... null.    
    - use a dummy node to hold reference to the iterator node.
- Map<original, new node>: 1. avoid creating same node; 2. return the new node if existing
    - 每一步都check map里面有没有head. 没有? 加上
    - 每一步都check map里面有没有head.random. 没有? 加上
- Note, there is a way to skip the extra map O(n): https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43491/A-solution-with-constant-space-complexity-O(1)-and-linear-time-complexity-O(N)
    - However, creating a deep clone of the list is already O(n) extra space, so it is NOT effectively O(1) w/o map
    - It may be beneficial, if we can not hold all nodes in memory, then the approach w/o map is more applicable.



---

**78. [159. Longest Substring with At Most Two Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/159.%20Longest%20Substring%20with%20At%20Most%20Two%20Distinct%20Characters.java)**      Level: Medium      Tags: [Hash Table, Sliding Window, String, Two Pointers]
      

如题.

#### Method1: Slinding Window, Two Pointers: move 1 element at a time
- Typical slinding window: the goal is to keep a distinct char size/window of size 2.
- use a map<char, count> to track; map.size() is the window size. Follow the template
    - 1) move right pointer, and update freq count map
    - 2) if map.size() == 2, process and record max len
    - 3) if map.size() > 2, maintain window size: drop curr left char, update map
- return max
- time: O(n)
- space: O(1)

#### Method2: Sliding window, Two Pointer: truncate the entire block at a time
- record last occurance index in map<char, index>
    - when size goes over limit, find last occurance of left-most element
    - set left = leftMostIndex + 1. 
    - This truncates entire block before the last occurance of left-most element
- sliding window 的切割: 用hashmap 存 last occurrance of char index; 
- map.remove(c) 之后, 就等于彻底切掉了那一段; 那么 map.get(c) + 1 也就是新的 left window border
- time: O(n) 
- space: O(1)



---

**79. [760. Find Anagram Mappings.java](https://github.com/awangdev/LintCode/blob/master/Java/760.%20Find%20Anagram%20Mappings.java)**      Level: Easy      Tags: [Hash Table]
      

- HashMap 存index list
- 遍历一遍数组A, 列举出所有元素



---

**80. [692. Top K Frequent Words.java](https://github.com/awangdev/LintCode/blob/master/Java/692.%20Top%20K%20Frequent%20Words.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue, Trie]
      

给一串String. 找到top k frequent words.

#### Method1: Bucket Sort
- 1) Build frequency map, 2) use frequency map to build freq bucket
- Loop from largest bucket freq -> 0, and output.
- Time: Solid O(n)
- Space: O(n)

#### Method2: PriorityQueue - Min Heap
- O(n) space of map, O(nlogk) to build queue.
- limit minHeap queue size to k: add to queue if found suitable item; always reduce queue if size > k

#### Method3: PriorityQueue - Max Heap
- 用HashMap存frequency, 用ArrayList存lists of words
- create一个Node class, 然后用PriorityQueue.   
- PriorityQueue里面用到了 String.compareTo(another String).巧妙。
- time: PQ uses O(nlogn), overall O(nlogn)
- slower, because the maxHeap needs to add all candidates

#### Trie && MinHeap屌炸天   
- 可以做一下
- http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/


#### Deleted Attempt: HashMap + collections.sort()
- 用HashMap存frequency, 用ArrayList存lists of words。最后返回从尾部向前数的k个。   
- 注意排序时Collection.sort()的cost是O(nLogk)
- not efficient




---

**81. [94. Binary Tree Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/94.%20Binary%20Tree%20Inorder%20Traversal.java)**      Level: Easy      Tags: [Hash Table, Stack, Tree]
      

Inorder traverse Binary Tree

#### Method1: DFS
- option1: dfs + rst list to carry results
- option2: Divide and Conquer, 在自己的基础上recursive, 不用helper function
- O(n) time

#### Method2: Iterative, Stack inorder traversal
- 1) Add root.leftPath all the way to leaf, 2) process curr 3) Move to right if applicable 4) add all right.leftPath
- O(n) time, O(h) space




---

**82. [767. Reorganize String.java](https://github.com/awangdev/LintCode/blob/master/Java/767.%20Reorganize%20String.java)**      Level: Medium      Tags: [Greedy, Hash Table, Heap, Sort, String]
      


We want to exhaust largest population and merge like merging k list.
Problem: largest population may result in them being adjacent. How to resolve?

1) process and check at the end, or, 2) sanitize first and process assume correct input

#### Method1: K(k=2) seats apart problem (w/o sanitization)
- Aggregate map<char, count>, and sort the entry with priority queue.(Optionally, can use object `Letter {char c, int count}`)
- Naturally: we want to prioritize the largest population and exhaust it first, so we want to keep it in the a buffer queue
    - it is a queue, first in first out
    - monitor queue size k = 2, so that it holds off the just last-processed letter for 1 unit of time
    - the buffer then sends the last-process item to the main priority queue (pq will sort it again)
- Error handling: largest population may have extra letter
    - the main PQ has already exhausted
    - but the largest-population-letter will end up stuck in the buffer queue
    - it will never be picked up again so the final result sb will be shorter than orignal string: that is the error case
- Option0. Similar to `621. Task Scheduler`:
    - use a buffer to hold potential letter to add back, but NOT ADD BACK YET, until k slots have been filled.
- time: O(m), m = # of unique letters
- space: O(nmLogm), n = length, pq sorting requires mlogm, we will visit all n nodes.

#### Method2: HashMap<Num, # occurance>, Sort (Sanitize input)
- put all in map<char, count>
    - Sanitize the input: if certain popular char count is over (n + 1)/2, then it should fail right away, just return empty map.
    - Once the input is sanitized, when building results, we can be greedy and consume most popular char and then the rest 
- Int[2] can be used store char and count 
    - PriorityQueue can sort int[]. Okay to not specific length of int[] when defining pq.
    - Alternatively, can use a Letter {char c, int count} to represent





---

**83. [721. Accounts Merge.java](https://github.com/awangdev/LintCode/blob/master/Java/721.%20Accounts%20Merge.java)**      Level: Medium      Tags: [DFS, Hash Table, Union Find]
      
- time O(mn)

给一串account in format `[[name, email1, email2, email3], [name2, email,..]]`. 

要求把所有account merge起来 (可能多个record记录了同一个人, by common email)


#### Method1: Union Find
- 构建 `Map<email, email parent>`, 然后再反向整合: parent -> list of email
- init with <email, email> for all emails
- 因为不同account可能串email, 那么把所有email union的时候, 不同account 的email也会被串起来
- 最终: 所有的email都被union起来, 指向一个各自union的 parent email
- UnionFind 的 parent map 可以反向输出所有  child under parent.
- 同时要维护一个 <email -> account name> 的map, 最终用来输出.

#### Method2: Hash Table solution, passed but very slow
- Definitely need iterate over accounts: merge them by email.
- Account object {name, list of email}
- map<email, account>
- 1. iterate over accounts
- 2. find if 'account' exist;  if does, add emails
- 3. if not, add account to list and to map. map all emails to accounts.
- output -> all accounts, and sort emails
- space O(mn): m row, n = emails
- time O(mn)

### DFS
- TODO



---

**84. [149. Max Points on a Line.java](https://github.com/awangdev/LintCode/blob/master/Java/149.%20Max%20Points%20on%20a%20Line.java)**      Level: Hard      Tags: [Array, Geometry, Hash Table, Math]
      

给list of (x,y) coordinates. Determine  # of points on the same line

#### Observation
- If given n points, we can calculate all possible slopes. O(n^2) times
- For the two dots that generates the same slope, these dots could be on **parallel** slopes
- figure out how to prune the parallel dots

#### Trick: prune parallel dots using greatest common divider
- GCD: greatest common divider
- Devide the x and y by their greatest common divider, such that x and y can be reduced to minimum value
- All other x and y can be reduced to such condition as well
- track the final reduced (x,y) in a map: they are the key to the count
- No need to use Map<Integer, Map<Integer, Integer>> to perform 2 level mapping; just `map<String, Integer>`, where the key is "x@y"



---

**85. [739. Daily Temperatures.java](https://github.com/awangdev/LintCode/blob/master/Java/739.%20Daily%20Temperatures.java)**      Level: Medium      Tags: [Hash Table, Monotonous Stack, Stack]
      

#### Method1: Monotonous Stack
- Goal: given a index i, want right-side closest & higer number
- Draw example: right-most number at base, and builds up monotonous stack (mountain shape)
    - add smaller item on top of stack
    - keep popping if peek is higher than incoming
- space: O(n), time:O(n)

#### Method2: `Map <fixed value(temperature), Index>`, kinda of like bucket sort
- Refernece: https://leetcode.com/problems/daily-temperatures/solution/
- From right side: 
    - 1) record tempIndex[currTemp] = i; 
    - 2) Brutle find smallest temp index in range [currTemp + 1, 100] and record as result


---

