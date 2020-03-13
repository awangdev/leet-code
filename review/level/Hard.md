 
 
 
## Hard (106)
**0. [Jump Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game%20II.java)**      Level: Hard      Tags: [Array, Coordinate DP, DP, Greedy]
      

给一串数字 是可以跳的距离. goal: 跳到最后的index 所可能用的最少次数.

#### Method1: Greedy
- maintain the `farest can go`, and use it the target for i increse towards. Why?
    - 1) when I know the `farest can go`, in fact it is just currently 1 step away.
    - 2) why to iterate from curr `i to farset`? In range [i, farest], we will calc the new `maxRange`
    - 3) once `i` reaches `farset`, update `farest = maxRange`
- greedy concept: once we know the farest we can reach at the moment, it is just 1 step away :)
- On average should be jumpping through the array without looking back
- time: average O(n)
- Impl:
    - 图解 http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
    - track the farest point
    - whenver curr index reachest the farest point, that means we are making a nother move, so count++
    - there seems to have one assumption: must have a solution. Otherwise, count will be wrong number. 
    - 其实跟第一个greedy的思维模式是一模一样的.


#### Method2: DP
- DP[i]: 在i点记录，走到i点上的最少jump次数
- dp[i] = Math.min(dp[i], dp[j] + 1);
- condition (j + nums[j] >= i)
- 注意使用 dp[i] = Integer.MAX_VALUE做起始值, 来找min
- time: O(n^2), slow, and timesout



---

**1. [Convert Expression to Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Expression%20to%20Polish%20Notation.java)**      Level: Hard      Tags: [Binary Tree, DFS, Expression Tree, Stack]
      
给一串字符, 用来表示公式expression. 把这个expression转换成 Polish Notation (PN).

#### Expression Tree
- Expression Tree: Minimum Binary Tree (https://lintcode.com/en/problem/expression-tree-build/)
- 根据题意做出Expression Tree出来以后: 来个Pre-order-traversal 就能记录下 Polish Notation
- 本题没有给'ExpressionTreeNode', 所以把TreeNode就当做成我们需要的node, 里面扩展成有left/right child就可以了.
- Note: label需要是String. 虽然 Operator是长度为1的char, 但是数字可为多位



---

**2. [Frog Jump.java](https://github.com/awangdev/LintCode/blob/master/Java/Frog%20Jump.java)**      Level: Hard      Tags: [DP, Hash Table]
      
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

**3. [Sliding Window Median.java](https://github.com/awangdev/LintCode/blob/master/Java/Sliding%20Window%20Median.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap, Sliding Window]
      
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

**4. [Perfect Rectangle.java](https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Rectangle.java)**      Level: Hard      Tags: [Design, Geometry, Hash Table]
      
看的list of coordinates 是否能组成perfect rectangle, 并且不允许overlap area.

#### 画图发现特点
- 特点1: 所有给出的点(再找出没有specify的对角线点), 如果最后组成perfect rectangle, 都应该互相消除, 最后剩下4个corner
- 特点2: 找到所有点里面的min/max (x,y), 最后组成的 maxArea, 应该跟过程中accumulate的area相等
- 特点1确保中间没有空心的部分, 保证所有的重合点都会互相消除, 最后剩下4个顶点
- 特点2确保没有重合: 重合的area会最终超出maxArea



---

**5. [Binary Representation.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Representation.java)**      Level: Hard      Tags: [Bit Manipulation, String]
      
#### String
- 首先要分两半解决，断点是'.': str.split("\\.");
- Integer那一半好弄，whie loop里: num%2, num/2. 做一个 `parseInteger()` function
- Decimal那边复杂点. 做一个 `parseDecimal()` function:
- bit == 1的数学条件: 当下num * 2 >= 1。 更新: num = num * 2 - 1;
- bit == 0的数学条件: num * 2 < 1. 更新: num = num * 2

#### 注意
- num是 double, 小数在 `num = num * 2 - 1` 的公式下可能无限循环
- 因此check: num重复性，以及binary code < 32 bit.
- 所以题目也才有了32BIT的要求!



---

**6. [Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Consecutive%20Sequence.java)**      Level: Hard      Tags: [Array, Hash Table, Union Find]
      
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

**7. [Rearrange String k Distance Apart.java](https://github.com/awangdev/LintCode/blob/master/Java/Rearrange%20String%20k%20Distance%20Apart.java)**      Level: Hard      Tags: [Greedy, Hash Table, Heap]
      
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

**8. [Median of Two Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Median%20of%20Two%20Sorted%20Arrays.java)**      Level: Hard      Tags: [Array, Binary Search, DFS, Divide and Conquer]
      
著名的找两个sorted array的中位数. 中位数定义: 如果两个array总长为偶数, 取平均值.
题目要求在 log(m + n) 时间内解决

- 看到log(m+n), 就想到binary search, 或者是recursive 每次砍一半
- 两个sorted array 参差不齐, 肯定不能做简单的binary search

#### Divide and Conquer, recursive
- 这里有个数学排除思想: 考虑A, B各自的中间点.
- 如果A[mid] < B[mid], 那么 A[0 ~ mid - 1] 就不在 median的range里面, 可以排除. divide/conquer就这么来的.
- 具体逻辑看代码, 大致意思就是: 每次都取比较A 和 B [x + k / 2 - 1] 的位置, 然后做range 排除法
- end cases: 
- 1. 如果我们发现dfs()里面A或者B的start index溢出了, 那么就是最简单的case: midian一定在另外那个array里面
- 2. 如果 k == 1: 就是找A/B 里面的1st item, 那么做个 `Math.max(A[startA], B[startB])` 就可以
- 总共的数字长度是 (m + n) 而且每次都有一般的内容被删除, 那么time就是 O(log(m + n))




---

**9. [Remove Duplicate Letters.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicate%20Letters.java)**      Level: Hard      Tags: [Greedy, Hash Table, Stack]
      
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

**10. [Orderly Queue.java](https://github.com/awangdev/LintCode/blob/master/Java/Orderly%20Queue.java)**      Level: Hard      Tags: [Math, String]
      



---

**11. [Maximal Rectangle.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Rectangle.java)**      Level: Hard      Tags: [Array, DP, Hash Table, Stack]
      
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

**12. [Expression Evaluation.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Evaluation.java)**      Level: Hard      Tags: [Binary Tree, DFS, Expression Tree, Minimum Binary Tree, Stack]
      
给一个公式 expression, array of strings, 然后evaluate expression 结果.

#### DFS on Expression Tree
- 计算 expression 的值: 1. 建造 expression tree. 2. DFS计算结果
- Expression Tree: Minimum Binary Tree (https://lintcode.com/en/problem/expression-tree-build/)
- build好Min Tree以后，做PostTraversal. 
- Divde and Conquer: 先recursively找到 left和right的大小， 然后evaluate中间的符号
- Time, Space O(n), n = # expression nodes

#### Note
- 1. Handle数字时，若left&&right Child全Null,那必定是我们weight最大的数字node了。   
- 2. 若有个child是null,那就return另外一个node。    
- 3. prevent Integer overflow　during operation:过程中用个Long，最后结局在cast back to int.



---

**13. [LFU Cache.java](https://github.com/awangdev/LintCode/blob/master/Java/LFU%20Cache.java)**      Level: Hard      Tags: [Design, Hash Table]
      
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

**14. [Scramble String.java](https://github.com/awangdev/LintCode/blob/master/Java/Scramble%20String.java)**      Level: Hard      Tags: [DP, Interval DP, String]
      
- 给两个string S, T. 检验他们是不是scramble string.
- scramble string 定义: string可以被分拆成binary tree的形式, 也就是切割成substring;
- 旋转了不是leaf的node之后, 形成新的substring, 这就是原来string的 scramble.


#### Interval DP 区间型
- 降维打击, 分割, 按照长度来dp.
- dp[i][j][k]: 数组S从index i 开始, T从index j 开始, 长度为k的子串, 是否为scramble string

##### Break down
- 一切两半以后, 看两种情况: , 或者不rotate这两半. 对于这些substring, 各自验证他们是否scramble.
- 不rotate分割的两半: S[part1] 对应  T[part1] && S[part2] 对应  T[part2]. 
- rotate分割的两半: S[part1] 对应  T[part2] && S[part2] 对应  T[part1]. 

##### Initialization
- len == 1的时候, 其实无法旋转, 也就是看S,T的相对应的index是否字符相等.
- initialization非常非常重要. 很神奇, 这个initailization 打好了DP的基础, 后面一蹴而就, 用数学表达式就算出了结果.
- input s1, s2 在整个题目的主要内容里面, 几乎没有用到, 只是用在initialization时候.
- More details, 看解答



---

**15. [K Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/K%20Edit%20Distance.java)**      Level: Hard      Tags: [DP, Double Sequence DP, Sequence DP, Trie]
      
给一串String, target string, int k. 找string array里面所有的candidate: 变化K次, 能变成target.

#### Trie
TODO

#### Double Sequence DP
- Edit Distance的follow up.
- 其实就是改一下 minEditDistance的function, 带入K作比较罢了.
- 写起来跟Edit Distance 的主要逻辑是一模一样的.
- 但是LintCode 86% test case 时候timeout. 
- Time O(mnh), where h = words.length, 如果 n ~ m, Time 就几乎是 O(n^2), 太慢.



---

**16. [Word Search II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search%20II.java)**      Level: Hard      Tags: [Backtracking, DFS, Trie]
      
给一串words, 还有一个2D character matrix. 找到所有可以形成的words. 条件: 2D matrix 只可以相邻走位.

#### Trie, DFS
- 相比之前的implementation, 有一些地方可以优化:
- 1. Backtracking时候, 在board[][] 上面mark就可以, 不需要开一个visited[][]
- 2. 不需要implement trie的所有方程, 用不到: 这里只需要insert.
- 普通的trie题目会让你search a word, 但是这里是用一个board, 看board的每一个字母能不能走出个Word.
- 也就是: 这里的search是自己手动写, 不是传统的trie search() funcombination
- 3. TrieNode里面存在 end的时候存string word, 表示到底. 用完了 word = null, 刚好截断重复查找的问题.

##### 关于Trie
- Build Trie with target words: insert, search, startWith. Sometimes, just: `buildTree(words)` and return root.
- 依然要对board matrix做DFS。
- no for loop on words. 直接对board DFS:   
- 每一层,都会有个up-to-this-point的string. 在Trie里面check它是不是存在。以此判断。   
- 若不存在，就不必继续DFS下去了。
- Trie solution time complexity, much better:      
- build Trie:   n * wordMaxLength
- search: boardWidth * boardHeight * (4^wordMaxLength + wordMaxLength[Trie Search])


#### Regular DFS
- for loop on words: inside, do board DFS based on each word.     
- Time cpmplexity: word[].length * boardWidth * boardHeight * (4^wordMaxLength)

#### Previous Notes
- Big improvement: use boolean visited on TrieNode!     
- 不要用rst.contains(...), 因为这个是O(n) 在leetcode还是会timeout（lintcode竟然可以pass）!    
- 在Trie search() method 里面，凡是visit过的，mark一下。  




---

**17. [K Empty Slots.java](https://github.com/awangdev/LintCode/blob/master/Java/K%20Empty%20Slots.java)**      Level: Hard      Tags: [Array, BST, TreeSet]
      
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

**18. [Russian Doll Envelopes.java](https://github.com/awangdev/LintCode/blob/master/Java/Russian%20Doll%20Envelopes.java)**      Level: Hard      Tags: [Binary Search, Coordinate DP, DP]
      
俄罗斯套娃, 这里用envelope来表现. 给一串array, 每一个[x, y] 是envelope 长宽. [[5,4],[6,4],[6,7],[2,3]]. 

看用这些套娃, 可以最多套几个.

#### DP: 1D Coordinate
- envelopes没有顺序, 先排序 (主要根据第一个index排序)
- 然后观察: 排序过后, 就变成了1D的坐标动态规划.
- max number 取决于上一个成功Russian doll的 max value + 1
- 上一个index不知道, 所以遍历找上一个index. 
- 当下index i 的状态, 取决于前面index j 的状态, 所以遍历两个index.
- O(n^2)的DP, n = envelopes.length;

#### DP: 2D Coordinate
- 这个方法是自己想出来的, 但是时间复杂度太大, timeout
- 把envelop标记在2D grid上面, 然后像走机器人一样, 求到最右下角的最大 count max.
- count 当下能存在多少Russian doll
- 两种情况: 当下coordinate 没有target, 当下coordinate有target
- 当下coordinate 没有target: 如同机器人走法, Math.max(dp[i - 1][j], dp[i][j - 1])
- 当下coordinate 有target: dp[i - 1][j - 1] + dp[i][j]
- timeout: O(n^2), n = largest coordinate.




---

**19. [Kth Smallest Sum In Two Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Sum%20In%20Two%20Sorted%20Arrays.java)**      Level: Hard      Tags: []
      

用priority queue. 每次把最小的展开，移位。分别x+1,或者y+1:   
因为当下的Min里面x,y都是最小的。所以下一个最小的不是（x+1,y）,就是（x,y+1）。

每次就poll（）一个，放2个新candidate进去就好了。
注意，这样的做法会用重复，比如例子（7,4）会出现两次。用一个HashSet挡一下。

注意，HashSet的唯一性，用一个"x,y"的string就可以代为解决。



---

**20. [Backpack III.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20III.java)**      Level: Hard      Tags: [Backpack DP, DP]
      
给n种不同的物品, int[] A weight, int[] V value, 每种物品可以用无限次

问最大多少value可以装进size是 m 的包?

#### DP
- 可以无限使用物品, 就失去了last i, last unique item的意义: 因为可以重复使用.
- 所以可以转换一个角度:
- 1. 用i **种** 物品, 拼出w, 并且满足题目条件(max value). 这里因为item i可以无限次使用, 所以考虑使用了多少次K.
- 2. K虽然可以无限, 但是也被 k*A[i]所限制: 最大不能超过背包大小.
- dp[i][w]: 前i种物品, fill weight w 的背包, 最大价值是多少.
- dp[i][w] = max {dp[i - 1][w - k*A[i-1]] + kV[i-1]}, k >= 0
- Time O(nmk)
- 如果k = 0 或者 1, 其实就是 Backpack II: 拿或者不拿

#### 优化
- 优化时间复杂度, 画图发现:
- 所计算的 (dp[i - 1][j - k*A[i - 1]] + k * V[i - 1]) 
- 其实跟同一行的 dp[i][j-A[i-1]] 那个格子, 就多出了 V[i-1]
- 所以没必要每次都 loop over k times
- 简化: dp[i][j] 其中一个可能就是: dp[i][j - A[i - 1]] + V[i - 1]
- Time O(mn)

#### 空间优化到1维数组
- 根据上一个优化的情况, 画出 2 rows 网格
- 发现 dp[i][j] 取决于: 1. dp[i - 1][j], 2. dp[i][j - A[i - 1]]
- 其中: dp[i - 1][j] 是上一轮 (i-1) 的结算结果, 一定是已经算好, ready to be used 的
- 然而, 当我们 i++,j++ 之后, 在之前 row = i - 1, col < j的格子, 全部不需要.
- 降维简化: 只需要留着 weigth 这个 dimension, 而i这个dimension 可以省略: 
- (i - 1) row 不过是需要用到之前算出的旧value: 每一轮, j = [0 ~ m], 那么dp[j]本身就有记录旧值的功能.
- 变成1个一位数组
- 降维优化的重点: 看双行的左右计算方向
- Time(mn). Space(m)



---

**21. [Shortest Palindrome.java](https://github.com/awangdev/LintCode/blob/master/Java/Shortest%20Palindrome.java)**      Level: Hard      Tags: [KMP, String]
      
#### Divide by mid point, Brutle
- check (mid, mid+1), or (mid-1, mid+1).
- If the two position matches, that is a palindrome candidate
- 比较front string 是否是 end string 的substring
- O(n^2)
- timeout on last case: ["aaaaaa....aaaacdaaa...aaaaaa"]

#### KMP
- TODO



---

**22. [Palindrome Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Pairs.java)**      Level: Hard      Tags: [Hash Table, String, Trie]
      
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

**23. [Find Peak Element II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element%20II.java)**      Level: Hard      Tags: [Binary Search, DFS, Divide and Conquer]
      
2Dmatrix, 里面的value有一些递增, 递减的特点(细节比较长, 看原题). 目标是找到peak element

peak: 比周围4个方向的点value大

#### DFS

##### 基本原理
- 我们不可能一口气准确定位(x,y), 但是我们可以再一个row/col里面, 找到1D array的 peak.
- 根据这个点, 再往剩下两个方向移动
- 1. 在中间的一行i=midX, 找到peak所在的y.
- 2. 在中间的一列j=midY, 找到peak所在的x. (有可能强势override之前找到的y, 也就是放弃那一行的peak, 在midY上找peak)
- 3. 根据 (x,y) 的4个neighbor check (x,y)是不是 peak, 如果不是, 像更高的位置移动一格
- 4. 根据之前算的 midX, midY 把board分成4个象限, 在每一份里面再继续找
- 这个题目LintCode不给做了, 所以思路对的, 但是解答还没有再次验证.

##### 剪枝/切分象限
- 每次只是找到一个row/col里面的peak而已!
- 找到这个点, 就等于把board切成了两半.
- 然后, 再跟剩下的相邻的两个位置比较, 就知道了哪里更大, 就去哪里找peak, 也就是又切了第二刀.
- 切第二刀的时候, 也要把(x, y) 移到需要取的象限. 进行DFS
- 根据mid row 切割: 
- http://www.jiuzhang.com/solution/find-peak-element-ii/#tag-highlight-lang-java
- http://courses.csail.mit.edu/6.006/spring11/lectures/lec02.pdf

##### 时间复杂度
- 每一个level都减一半
- T(n) = n + T(n/2) = n + n/2 + n/4 + ... + 1 = n(1 + 1/2 + .... + 1/n) = 2n = O(n)

#### Binary Search
- TODO
- O(nLogN)



---

**24. [Remove Node in Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Node%20in%20Binary%20Search%20Tree.java)**      Level: Hard      Tags: [BST]
      
方法1: Brutle一点。找到target和target的parent.    
把target remove时，把target的children nodes 重新排列组成新的BST: inorder traversal, build tree based on inorder traversal list.

方法2: 分析规律,先找到target和parent, 然后根据性质，把target remove时，移动children nodes, 保证还是BST。



---

**25. [Redundant Connection II.java](https://github.com/awangdev/LintCode/blob/master/Java/Redundant%20Connection%20II.java)**      Level: Hard      Tags: [DFS, Graph, Tree, Union Find]
      
#### Union Find
- 讨论3种情况
- http://www.cnblogs.com/grandyang/p/8445733.html



---

**26. [Count of Smaller Number before itself.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Number%20before%20itself.java)**      Level: Hard      Tags: []
      
与Count of Smaller Number非常类似。以实际的value来构成segment tree，leaf上存（count of smaller number）。

Trick: 先Query，再modify.   
每次Query时候，A[i]都还没有加入到Segment Tree 里面，而A[i+1,...etc]自然也还没有加进去。   
那么就自然是coutning smaller number before itself.   
刁钻啊！   

另外注意：   
在modify里面：多Check了root.start <= index 和  index <= root.end。 过去都忽略了。以后可以把这个也写上。   
（其实是Make sense的，就是更加严格地check了index再 root.left 或者 root.right里面的站位）   



---

**27. [Number of Digit One.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Digit%20One.java)**      Level: Hard      Tags: [Math]
      
Pure math problem, not quite representative

Explanation
https://leetcode.com/problems/number-of-digit-one/discuss/64381/4+-lines-O(log-n)-C++JavaPython



---

**28. [Best Time to Buy and Sell Stock III.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III.java)**      Level: Hard      Tags: [Array, DP, Sequence DP]
      
比stock II 多了一个限制：只有2次卖出机会.

#### DP加状态
- 只卖2次, 把买卖分割成5个状态模块.
- 在状态index 0, 2, 4: 没有持有股票. 1. 一直在此状态, max profit不变; 2. 刚卖掉, dp[i][前状态] + profit
- 在状态index 1, 3: 持有股票. 1. 一直在此状态, daily profit. 2. 刚刚买进, 状态改变, 但是没有profit yet: dp[i][前状态]

##### Partial profit
- 把每天的partial profit (diff)加在一起, 最终的overall profit是一样的. 唯一更好的是, 不需要记录中间买入的时间点.
- 什么时候会积累profit呢? 
- 1. 原本就持有股票的, 如果毫无动作, 那么状态不变, 积累profit diff. 
- 2. 卖出了股票, 状态改变, 积累profit diff.
- 注意: 只有在状态index: 0, 2, 4, 也就是卖掉股票的时候, 才可以积累profit

##### Rolling Array
- [i] 只有和 [i-1] 打交道, reduce space
- O(1) space, O(n) time

#### 找峰头
- 找峰头；然后往下再找一个峰头。
- 怎么样在才能Optimize两次巅峰呢？从两边同时开始找Max！（棒棒的想法）
- leftProfit是从左往右，每个i点上的最大Profit。
- rightProfit是从i点开始到结尾，每个点上的最大profit.
- 那么在i点上，就是leftProfit，和右边rightProfit的分割点。在i点，leftProfit+rightProfit相加，找最大值。
- 三个O(n),还是O(n)



---

**29. [Design Search Autocomplete System.java](https://github.com/awangdev/LintCode/blob/master/Java/Design%20Search%20Autocomplete%20System.java)**      Level: Hard      Tags: [Design, Hash Table, MinHeap, PriorityQueue, Trie]
      

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

**30. [Distinct Subsequences.java](https://github.com/awangdev/LintCode/blob/master/Java/Distinct%20Subsequences.java)**      Level: Hard      Tags: [DP, String]
      
Double Sequence DP:
0. DP size (n+1): 找前nth的结果, 那么dp array就需要开n+1, 因为结尾要return dp[n][m]
1. 在for loop 里面initialize dp[0][j] dp[i][0]
2. Rolling array 优化成O(N): 如果dp[i][j]在for loop里面, 就很好替换 curr/prev



---

**31. [Robot Room Cleaner.java](https://github.com/awangdev/LintCode/blob/master/Java/Robot%20Room%20Cleaner.java)**      Level: Hard      Tags: [Backtracking, DFS]
      
#### DFS
- Different from regular dfs to visit all, the robot move() function need to be called, backtrack needs to move() manually and backtracking path shold not be blocked by visited positions
- IMPORTANT: Mark on the way in using set, but `backtrack directly without re-check against set`
- Mark coordinate 'x@y'
- Backtrack: turn 2 times to revert, move 1 step, and turn 2 times to revert back.
- Direction has to be up, right, down, left.
- `int [] dx = {-1, 0, 1, 0};`, `int[] dy = {0, 1, 0, -1};`



---

**32. [Subarray Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20II.java)**      Level: Hard      Tags: [Array, Binary Search, Two Pointers]
      


---

**33. [Ones and Zeroes.java](https://github.com/awangdev/LintCode/blob/master/Java/Ones%20and%20Zeroes.java)**      Level: Hard      Tags: [DP]
      
还是Double Sequence, 但是考虑第三种状态: 给的string array的用量.
所以开了3维数组.

如果用滚动数组优化空间, 需要把要滚动的那个for loop放在最外面, 而不是最里面.
当然, 这个第三位define在 dp[][][]的哪个位置, 问题都不大.

另外, 注意在外面calcualte zeros and ones, 节约时间复杂度.



---

**34. [Wildcard Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java)**      Level: Hard      Tags: [Backtracking, DP, Double Sequence DP, Greedy, Sequence DP, String]
      
Double sequence DP. 与regular expression 很像.

#### Double Sequence DP
- 分析字符 ?, * 所代表的真正意义, 然后写出表达式.
- 搞清楚initialization 的时候 dp[i][0] 应该always false. 当p为empty string, 无论如何都match不了 (除非s="" as well)
- 同时 dp[0][j]不一定是false. 比如s="",p="*" 就是一个matching.
- A. p[j] != '*'
    1. last index match => dp[i - 1][j - 1]
    2. last index == ?  => dp[i - 1][j - 1]
- B. p[j] == "*"
    1. * is empty => dp[i][j - 1]
    2. * match 1 or more chars => dp[i - 1][j]




---

**35. [Expression Add Operators.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Add%20Operators.java)**      Level: Hard      Tags: [Backtracking, DFS, Divide and Conquer, String]
      

给一个数字String, 数字来自`0-9`, 给3个操作符 `+`,`-`,`*`, 看如何拼凑, 可以做出结果target.

output 所有 expression

#### string dfs, use list to track steps (backtracking)
- 跟string相关, 写起来可能稍微繁琐一点
    - 数字有 dfs([1,2,3...]) 组合方法
    - operator有[`+`,`-`,`*`] 3种组合方法
- 注意1: 乘号要特殊处理, pass along 连乘的数字, 计算下一步乘积的时候, 要 sum - preProduct + product
- 注意2: '01' 这种数字要skip
- 注意3: 第一个选中数字不需要加操作符, 直接加进去
- Time: O(4^n)， Space: O(4^n)
- T(n) = 3 * T(n-1) + 3 * T(n-2) + 3 * T(n-3) + ... + 3 *T(1);
- T(n-1) = 3 * T(n-2) + 3 * T(n-3) + ... 3 * T(1);
- Thus T(n) = 4T(n-1) = 4^2 * T(n - 1) = .... O(4^n)

#### String dfs, use string as buffer
- 逻辑一样, 代码更短, 只不过不做list, 直接pass `buffer + "+" + curr`
- 因为每次都创建新string, 所以速度稍微慢一点. Time complexity 一样



---

**36. [Cracking the Safe.java](https://github.com/awangdev/LintCode/blob/master/Java/Cracking%20the%20Safe.java)**      Level: Hard      Tags: [DFS, Greedy, Math]
      
#### Greedy, Iterative
- For 2 passwords, the shortest situation is both passwords overlap for n-1 chars.
- We can use a window to cut out last (n-1) substring and append with new candidate char from [k-1 ~ 0]
- Track the newly formed string; if new, add the new char to overall result
- Note: this operation will run for k^n times: for all spots of [0 ~ n - 1] each spot tries all k values [k-1 ~ 0]
- Same concept as dfs

#### DFS
- Same concept: use window to cut out tail, and append with new candidate
- do this for k^n = Math.pow(k, n) times



---

**37. [Best Time to Buy and Sell Stock IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20IV.java)**      Level: Hard      Tags: [DP, Sequence DP]
      
有int[] price of stock, 最多做 k transactions.  求最大profit.

#### DP
- 根据StockIII, 不难发现StockIV就是把状态划分为2k+1份. 那么同样的代码, 移植.

##### 注意1: 
- 如果k很大, k>n/2, 那么长度为n的数组里面, 最多也只能n/2个transaction
- 那么题目简化为stockII, 给n数组, 无限次transaction.
- 注意, status的数量是 2k+1
- Time O(NK), Space O(2k+1) to store the status

##### 注意2: 
- 最后状态是'没有stock'的都该考虑, 做一个 for 循环比较max. 
- 当然, 来一个profit variable, 不断比较, 也是可以的.

#### 方法2
- (previous notes, 熟练第一种方法的思考就可以)
- 记得要理解：为什么 i-1天的卖了又买，可以和第 i 天的卖合成一次交易？    
- 因为每天交易的price是定的。所以卖了又买，等于没卖！这就是可以合并的原因。要对价格敏感啊少年。
- Inspired from here: http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html

##### 局部最优解 vs. 全局最优解：     
- local[i][j] = max(global[i – 1][j – 1] + diff, local[i – 1][j] + diff)    
- global[i][j] = max(global[i – 1][j], local[i][j])     
- local[i][j]: 第i天，当天一定进行第j次交易的profit     
- global[i][j]: 第i天，总共进行了j次交易的profit.

- local[i][j]和global[i][j]的区别是：local[i][j]意味着在第i天一定有交易（卖出）发生。    
- 当第i天的价格高于第i-1天（即diff > 0）时，那么可以把这次交易（第i-1天买入第i天卖出）跟第i-1天的交易（卖出）合并为一次交易，即local[i][j]=local[i-1][j]+diff；    
- 当第i天的价格不高于第i-1天（即diff<=0）时，那么local[i][j]=global[i-1][j-1]+diff，而由于diff<=0，所以可写成local[i][j]=global[i-1][j-1]。    
- (Note:在我下面这个solution里面没有省去 +diff）   

- global[i][j]就是我们所求的前i天最多进行k次交易的最大收益，可分为两种情况：    
- 如果第i天没有交易（卖出），那么global[i][j]=global[i-1][j]；     
- 如果第i天有交易（卖出），那么global[i][j]=local[i][j]。    





---

**38. [Find Minimum in Rotated Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Minimum%20in%20Rotated%20Sorted%20Array%20II.java)**      Level: Hard      Tags: [Array, Binary Search]
      
一个需要严谨思考的题目. 因为有duplicate, 会导致不断平移, 所以最终time complexity是O(n)
所以不如直接扫一遍, 给出答案.

但是还是写一个Binary Search的样子, 只不过worst结果是O(n)



---

**39. [Longest Valid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Valid%20Parentheses.java)**      Level: Hard      Tags: [Coordinate DP, Stack, String]
      
给一串string, 里面只有`(`, `)`. 找最长valid parentheses 的长度.

#### 1D Coordinate DP
- use dp[i] track local max, maintain global max
- int[] dp. dp[i]: longest valid string that ends on i.
- 结尾是 ')', 2种情况: 1. 刚好s[i-1]是'('; 2. s[i]的')'更前面的一个起始'(' 对应
- 注意, 结尾如果是'('属于不合理情况, 忽略.
- init: dp[0] = 0, 单个char不可能成型.
- 计算顺序: 从左到右, 找local max, maintain global max
- O(n) space, O(n) runtime

#### Stack
- Stack 里面存所有的open/close parentheses.
- 如果遇到stack.top()刚好开合结掉, 就stack.pop().
- 剩下的都是不合理的elements.
- 有点像negatively找 solution: `endIndex - 最后一个failedIndex(stack.pop()) - 1`, 应该就是最后一个succeeded string的长度
- 每次更新 endIndex 为stack.top(), 然后从stack继续找下一个failedIndex
- 所有的length作比较, 就可以找出最长length
- O(n) stack space, O(n) runtime. 应该比dp慢一点, 因为做了2遍O(n)




---

**40. [Expression Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Tree%20Build.java)**      Level: Hard      Tags: [Binary Tree, Expression Tree, Minimum Binary Tree, Stack]
      
给一串字符, 表示的是 公式 expression. 把公式变成expression tree

#### Monotonous Stack
- 和Max-tree一样，https://leetcode.com/problems/maximum-binary-tree
- 用到bottom->top递增的stack: 最底下的root维持成最小的element.
- 这个题目是Min-tree， 头上最小，Logic 和max-tree如出一辙   
- Space: O(n) 
- Time on average: O(n).

#### 特点
- TreeNode: 用一个并不是最终结果的TreeNode, 存weight, 用来排序
- 用base weight的概念权衡同一个层面的 符号, 数字 顺序
- 每一个character都是一个节点, 都有自己的weight. 用一个TreeNode来存weight value, 利用用weight来判断: 
- 1. (while loop) 如果node.val <= stack.peek().nodeValue, 把当前stack.peek() 变成 left child. 
- 2. (if condition) 如果stack有残余, 把当前node变成 stack.peek().rightChild 




---

**41. [Copy Books.java](https://github.com/awangdev/LintCode/blob/master/Java/Copy%20Books.java)**      Level: Hard      Tags: [Binary Search, DP, Partition DP]
      
给一串书pages[i], k个人, pages[i] 代表每本书的页数. k个人从不同的点同时开始抄书. 

问, 最快什么时候可以抄完?

#### Partition DP
- 第一步, 理解题目要求的问题: 前k个人copy完n本书, 找到最少的用时; 也可以翻译成: `n本书, 让k个人来copy, 也就是分割成k段`.
- 最后需要求出 dp[n][k]. 开: int[n+1][k+1]. 
- 原理:
- 1. 考虑最后一步: 在[0 ~ n - 1]本书里, 最后一个人可以选择copy 1 本, 2 本....n本, 每一种切割的方法的结果都不一样
- 2. 讨论第k个人的情况, 在 j = [0 ~ i] 循环. 而循环j时候最慢的情况决定 第k个人的结果(木桶原理): `Math.max(dp[j][k - 1], sum)`. 
- 3. 其中: `dp[j][k-1]` 是 [k-1]个人读完j本书的结果, 也就是著名的`上一步`. 这里循环考虑的是第k个人不同的j种上一步 : )
- 4. 循环的结果, 是要存在 dp[i][k] = Math.min(Math.max(dp[j][k - 1], sum[j, i]), loop over i, k, j = [i ~ 0])
- Time: O(kn^2), space O(nk)

##### Init
- Init: dp[0][0] = 0, 0个人0本书
- Integer.MAX_VALUE的运用:
- 当 i = 1, k = 1, 表达式: dp[i][k] = Math.min(dp[i][k], Math.max(dp[j][k - 1], sum));
- 唯一可行的情况就只有一种: i=0, k=0, 刚好 0 个人 copy 0 本书, dp[0][0] = 0.
- 其他情况, i = 1, k = 0, 0 个人读 1本书, 不可能发生: 所以用Integer.MAX_VALUE来冲破 Math.max, 维持荒谬值.
- 当 i=0, k=0 的情况被讨论时候, 上面的方程式才会按照实际情况计算出 dp[i][k]
- 这道题的init是非常重要而tricky的

##### 计算顺序
- k个人, 需要一个for loop; 
- k个人, 从copy1本书开始, 2, 3, ... n-1,所以 i=[1, n], 需要第二个for loop
- 在每一个i上, 切割的方式可以有[0 ~ i] 中, 我们要计算每一种的worst time

##### 滚动数组
- [k] 只有和 [k - 1] 相关
- Space: O(n)

#### Binary Search
- 根据: 每个人花的多少时间(time)来做binary search: 每个人花多久时间, 可以在K个人之内, 用最少的时间完成?
- time variable的范围不是index, 也不是page大小. 而是[minPage, pageSum]
- validation 的时候注意3种情况: 人够用 k>=0, 人不够所以结尾减成k<0, 还有一种是time(每个人最多花的时间)小于当下的页面, return -1
- O(nLogM). n = pages.length; m = sum of pages.




---

**42. [Shortest Distance from All Buildings.java](https://github.com/awangdev/LintCode/blob/master/Java/Shortest%20Distance%20from%20All%20Buildings.java)**      Level: Hard      Tags: [BFS]
      
给Walls and Gates很像, 不同的是, 这道题要选一个 coordinate, having shortest sum distance to all buildings (marked as 1).

#### BFS
- BFS 可以 mark shortest distance from bulding -> any possible spot.
- Try each building (marked as 1) -> BFS cover all 0. 
- time: O(n^2) * # of building; use new visited[][] to mark visited for each building.
- O(n^2) find smallest point/aggregation value.
- 注意, 这道题我们update grid[][] sum up with shortest path value from building.
- 最后找个min value 就好了, 甚至不用return coordinate.
- 分析过, 还没有写.



---

**43. [Longest Increasing Path in a Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Path%20in%20a%20Matrix.java)**      Level: Hard      Tags: [Coordinate DP, DFS, DP, Memoization, Topological Sort]
      
m x n 的matrix, 找最长增序的序列长度. 这里默认连续的序列.

- 接成圈是不行的, 所以visit过得 (x,y)就不能再去了.
- 斜角方向不能走, 只能走上下左右
- 无法按照坐标DP来做, 因为计算顺序4个方向都可以走.
- 最终要visit所有node, 所以用DFS搜索比较合适.

#### DFS, Memoization
- 简单版: longest path, only allow right/down direction: 
- `dp[x][y] = Math.max(dp[prevUpX][prevUpY], or dp[prevUpX][prevUpY] + 1)`; and compare the other direction as well
- This problem, just compare the direction from dfs result
- DFS太多重复计算; memoization (dp[][], visited[][]) 省去了重复计算
- initialize dp[x][y] = 1, (x,y) 自己也算path里的一格
- dfs(matrix, x, y): 每次检查(x,y)的4个neighbor (nx, ny), 如果他们到(x,y)是递增, 那么就考虑和比较:
- Maht.max(dp[x][y], dp[nx][ny] + 1); where dp[n][ny] = dfs(matrix, nx, ny)
- top level: O(mn), 尝试从每一个 (x,y) 出发
- O(m * n * k), where k is the longest path

#### Topological sort
还没有做



---

**44. [Interleaving String.java](https://github.com/awangdev/LintCode/blob/master/Java/Interleaving%20String.java)**      Level: Hard      Tags: [DP, String]
      
双序列DP, 从最后点考虑.
拆分问题的末尾, 考虑和s1, s2 subsequence之间的关联.

求存在性, boolean




---

**45. [Recover Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Recover%20Binary%20Search%20Tree.java)**      Level: Hard      Tags: [BST, DFS, Tree]
      
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

**46. [Basic Calculator.java](https://github.com/awangdev/LintCode/blob/master/Java/Basic%20Calculator.java)**      Level: Hard      Tags: [Binary Tree, Expression Tree, Math, Minimum Binary Tree, Stack]
      
给一个expression String, 要evaluate这个expression的值.

Expression string 里面包括 +, -, 整数, 开合括号, 还有space.

#### Expression Tree
- Expression Tree是一个 weight-based的 min-tree 
- 基于 运算符号 + 数字的 tree: 数字永远在leaf, 然后符号是tree node,  括号不出现在tree里面
- 用 monotonuous stack 来构建这个tree

##### Thinking points
- Understand Expression Tree
- Use stack to build the expression tree + understand the weight system
- Use post-order traversal to evaluate the tree
- 注意, input里面的数字不会是single digit, 所以需要一个buffer存number string
- 整个题目的做法, 可以参照 `Expression Evaluation`



---

**47. [Word Squares.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Squares.java)**      Level: Hard      Tags: [Backtracking, Trie]
      
可以开Trie class, 里面用到TrieNode. 开Trie(words) 可以直接initalize with for loop
TrieNode 里面可以有一个 List<String> startWith: 记录可以到达这个点的所有string: 有点像树形, ancestor形状的存储.

神操作:
根据square的性质, 如果选中了list of words, 设定 int prefixIndex = list.size().
取出list里面的所有word[prefixedIndex], 并且加在一起, 就是下一个word candidate的 prefix.

形象一点:
list = ["ball", "area"];
prefixIndex = list.size(); ball[prefixIndex] = 'l'; area[prefixIndex] = 'e';
//then
candidatePrefix = ball[prefixIndex] + area[prefixIndex] = "le";
这里就可以用到Trie的那个 findByPrefix function, 在每个点, 都存有所有这个点能产生的candidate.
这时, 试一试所有candidate: dfs

能想到这种倒转的结构来存prefix candidates 在 Trie里面, 这个想法非常值得思考.



---

**48. [k Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/k%20Sum.java)**      Level: Hard      Tags: [DP]
      
DP. 公式如何想到, 还需要重新理解.

dp[i][j][m]: # of possibilities such that from j elements, pick m elements and sum up to i. 
i: [0~target]

dp[i][j][m] = dp[i][j-1][m] + dp[i - A[j - 1]][j-1][m-1]
            (i not included)   (i included)



---

**49. [Coins in a Line III.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20III.java)**      Level: Hard      Tags: [Array, DP, Game Theory, Interval DP, Memoization]
      
LeetCode: Predict the Winner

还是2个人拿n个coin, coin可以有不同的value. 

只不过这次选手可以从任意的一头拿, 而不限制从一头拿. 算先手会不会赢?

#### Memoization + Search
- 跟Coins in a Line II 一样, MaxiMin的思想: 找到我的劣势中的最大值
- `dp[i][j] 代表在[i,j]区间上 选手最多能取的value 总和`
- 同样, sum[i][j]表示[i] 到 [j]间的value总和
- 对手的最差情况, 也就是先手的最好情况:
- dp[i][j] = sum[i][j] - Math.min(dp[i][j - 1], dp[i + 1][j]);
- 这里需要search, 画出tree可以看明白是如何根据取前后而分段的.

#### 博弈 + 区间DP, Interval DP
- 因为是看区间[i,j]的情况, 所以可以想到是区间 DP
- 这个方法需要复习, 跟数学表达式的推断相关联: S(x) = - S(y) + m. 参考下面的公式推导.
- dp[i][j]表示 从index(i) 到 index(j), 先手可以拿到的最大值与对手的数字差. 也就是S(x).
- 其中一个S(x) = dp[i][j] = a[i] - dp[i + 1][j]
- m 取在开头, m 取在末尾的两种情况:
- dp[i][j] = max{a[i] - dp[i + 1][j], a[j] - dp[i][j - 1]}
- len = 1, 积分就是values[i]
- 最后判断 dp[0][n] >= 0, 最大数字和之差大于0, 就赢.
- 时间/空间 O(n^2)

##### 公式推导
- S(x) = X - Y, 找最大数字和之差, 这里X和Y是选手X的总分, 选手Y的总分. 
- 对于选手X而言: 如果S(x)最大值大于0, 就是赢了; 如果最大值都小于0, 就一定是输了. 
- 选手Y: S(y)来表示 对于Y,  最大数字和之差. S(y) = Y - X
- 根据S(x) 来看, 如果从 数字和X里面, 拿出一个数字 m, 也就是 X = m + Xwithout(m)
- S(x) = m + Xwithout(m) - Y = m + (Xwithout(m) - Y). 
- 如果我们从全局里面索性去掉m, 那么 S(y'') = Y - Xwithout(m)
- 那么推算下来: S(x) = m + (Xwithout(m) - Y) = m - (Y - Xwithout(m)) = m - S(y'')
- 在这个问题里面, 我们model X 和 Y的时候, 其实都是 dp[i][j], 而区别在于先手/后手.
- 将公式套用, 某一个S(x) = a[i] - dp[i + 1][j],  也就是m=a[i], 而 S(y'') = dp[i + 1][j]

##### 注意
- 如果考虑计算先手[i, j]之间的最大值, 然后可能还需要两个数组, 最后用于比较先手和opponent的得分大小 => 那么就要多开维.
- 我们这里考虑的数字差, 刚好让人不需要计算先手的得分总值, 非常巧妙.
- Trick: 利用差值公式, 推导有点难想到.

##### 区间型动态规划
- 找出[i, j]区间内的性质: dp[i][j]下标表示区间范围 [i, j]
- 子问题: 砍头, 砍尾, 砍头砍尾
- loop应该基于区间的length
- template: 考虑len = 1, len = 2; 设定i的时候一定是 i <= n - len; 设定j的时候, j = len + i - 1;




---

**50. [Trapping Rain Water II.java](https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water%20II.java)**      Level: Hard      Tags: [BFS, Heap, MinHeap, PriorityQueue]
      
给一个2Dmap, 每个position 有 height. 找Trapping water sum.


#### Min Heap
- 用PriorityQueue把选中的height排序,为走位, create class Cell (x,y, height).

##### 注意几个理论
- 1. 从matrix四周开始考虑，发现matrix能Hold住的水，取决于height低的block
- 2. 必须从外围开始考虑，因为水是被包裹在里面，外面至少需要现有一层
- 以上两点就促使我们用min-heap: 也就是natural order的PriorityQueue<Cell>.

##### Steps
- 1. process的时候，画个图也可以搞清楚: 就是四个方向都走走，用curr cell的高度减去周围cell的高度.
- 2. 若大于零，那么周围的cell就有积水: 因为cell已经是外围最低, 所以内部更低的, 一定有积水.
- 3. 每个visited的cell都要mark, avoid revisit
- 4. 根据4个方向的走位 `(mX, mY)` 创建新cell 加进queue里面: cell(mX, mY) 已经计算过积水后, 外围墙小时, `(mX, mY)`就会变成墙.
- 5. 因为做的是缩小一圈的新围墙, height = Math.max(cell.h, neighbor.h);
- 和trapping water I 想法一样。刚刚从外围，只是能加到跟外围cell高度一致的水平面。往里面，很可能cell高度变化。   
- 这里要附上curr cell 和 move-to cell的最大高度。

##### 为什么想到用Heap (min-heap - priorityQueue)
- 要找到bucket的最短板
- 每次需要最先处理最短的那条 (on top)

##### 为什么从外向里遍历
- 木桶理论, 包水, 是从外面包住里面
- 洋葱剥皮, 用完丢掉



---

**51. [Bricks Falling When Hit.java](https://github.com/awangdev/LintCode/blob/master/Java/Bricks%20Falling%20When%20Hit.java)**      Level: Hard      Tags: [Union Find]
      
给一个matrix of 1 and 0, `1` 代表brick. 连着ceiling的brick就不会drop. 给一串coordinate hits[][], 记录每次take down 1 brick 后, 会drop多少个.

#### UnionFind
- 1. 我们知道大部分的brick可能都是连着ceiling, 所以每次正向检查都traverse all and timeout
- 2. 能否用union, 把connect都装在一起, 然后drop brick的时候把连着的都drop掉? 难: 因为还是要check所有brick当下的status.
- 受其他人的解答启发, 由于是计算count,我们可以`反向考虑`: 
- 把hit-brick全部mark=2 (就当舍弃不算), 观察整个局面的最后一步, 先把所有还连着ceiling的brick算一下总数, 统计在unionFind的 全部统计在count[0] 里面. 
- 剩下的不连着ceiling的也就是一个个isolated island
- 做法: 把hit-brick 一个个加回去, 然后再做一次union, 看看最终连到ceiling的有多少个. 增加的count, 就是正向思考时 dropped brick 数量!

##### Union Find 变种
- 还是用数字index做union find, 但是把每一个index都+1, 右移一位, 而[0]留下来做特殊用途:
- 用union at 0来 统计总共的remain count of ceiling-connected bricks, where `x = 0`. 
- 如果在其他其他题目种, 条件可能就不是`x=0`, 但也可以用这个 union index = 0 来做一个root的统计
- 关键: 把最后一个hit brick加回去, 然后再重新union一下这个hit-brick周围: count增加的变化, 不就是缺少hit-brick时候掉下去的数量.


#### DFS (timeout)
- 考虑每个hit的四周, 全部traverse, 没有连着ceiling就全部: 
- 比如是 200 x 200 的 全部是1的matrix, 任何一次traverse都要到顶; 重复计算, 所以timeout
- 算法是没错, 但是不efficient.
- 想要减少重复计算, 但是又不能提前计算: grid在不断变化. 所以看能不能把连着ceiling的都group起来, 可以O(1)快速check?




---

**52. [Burst Balloons.java](https://github.com/awangdev/LintCode/blob/master/Java/Burst%20Balloons.java)**      Level: Hard      Tags: [DP, Divide and Conquer, Interval DP, Memoization]
      
一排球, 每个球有value, 每次扎破一个, 就会积分: 左*中间*右 的值. 求, 怎么扎, 最大值?

TODO: Need more thoughts on why using dp[n + 2][n + 2] for memoization, but dp[n][n] for interval DP.

#### Interval DP
- 因为数组规律会变, 所以很难找'第一个burst的球'. 反之, 想哪一个是最后burst?
- 最后burst的那个变成一堵墙: 分开两边, 分开考虑, 加法原理; 最后再把中间的加上.
- dp[i][j] represent max value on range [i, j)
- Need to calculate dp[i][j] incrementally, starting from range size == 3 ---> n
- Use k to divide the range [i, j) and conquer each side.

##### Interval DP 三把斧:
- 中间劈开
- 砍断首或尾
- Range区间作为iteration的根本

##### Print the calculation process
- use pi[i][j] and print recursively.
- Print k, using pi[i][j]: max value taken at k

#### Memoization
- 其实会做之后挺好想的一个DP
- dp[i][j] =  balloons i~j 之间的 max. 
- 然后找哪个点开始burst? 设为x。
- For loop 所有的点作为x， 去burst。
- 每次burst都切成了三份：左边可以recusive 求左边剩下的部分的最大值 + 中间3项相乘 + 右边递归下去求最大值。
- Note: 这个是Memoization, 而不纯是DP
- 因为recursive了，其实还是搜索，但是memorize了求过的值，节省了Processing




---

**53. [Palindrome Partitioning II.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Partitioning%20II.java)**      Level: Hard      Tags: [DP, Partition DP]
      
给一个String s, 找出最少用多少cut, 使致 切割出的每一个substring, 都是palindrome

#### Partition DP
- Find minimum cut: 分割型DP
- dp[i]: 最少cut多少刀, 使得前 i 长度的string, 割出来都是palindrome
- 最终要得到 dp[n], 所以 int[n + 1]
- 移动切刀, 看在哪里切, index j in [0 ~ i]
- 考虑[j, i - 1] 是否是回文串, 如果是, 那么: dp[i]= min(dp[i], d[j] + 1).
- note: 估计遍历 j 的时候, 反过来遍历也可以.

#### 计算Palindrome的优化
- 利用palindrome的性质, 可以算出 boolean palindrome[i, j]的情况. 
- 找一个任意mid point:
- 1. 假设palindrome是奇数长度, 那么 mid 是单独的字符, 而两边的字符 [mid-1], [mid+1] 应该完全相等.
- 2. 假设palindrome是偶数长度, 那么 [mid] 和 [mid + 1] 这样位置的字符应该相等.
- 这样做出来 palindrome[i, j]: 从字符 i 到 字符 j 的 substring 是否是 palindrome
- 这样就给我们的问题合理降维, 目前是time: O(n^2). 
- 不然求一次palindrome, 就是n, 会变成O(n^3)

#### Previous Notes
- Double for loop 检查每种substring string (i~j). 若i,j相邻或者同点，那么肯定isPal；否则，i,j之间的（i+1, j-1）一定得isPal。
- 看上去，在检查i,j的时候，中间按的（i+1, j-1）怎么可能先知道？ 其实不然..在j慢慢长大的时候，所有的0~j的substring都检查过。所以isPal[i+1][j-1]一定是已经知道结果的。
- okay.那么假如以上任意一种情况成立，也就是说isPal[i][j] == true。那就要判断，切到第一层循环参数j的末尾点时，有多少种切法？
- 想法很顺：我们naturally会想到，把i之前的cut加上i~j之间发生的不就好了。
- 反正现在j不变，现在就看吧i定在哪里，cut[i - 1]是否更小/最小； 再在cut[i-1]基础上+1就完了。
- 当然，如果i==0, 而 i~j又是isPal,那没啥好谈的，不必切，0刀。
- 最终，刷到cut[s.length() - 1] 也就是最后一点。 return的理所应当。




---

**54. [Sliding Puzzle.java](https://github.com/awangdev/LintCode/blob/master/Java/Sliding%20Puzzle.java)**      Level: Hard      Tags: [BFS, Graph]
      


---

**55. [Interval Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Sum%20II.java)**      Level: Hard      Tags: [Binary Search, Lint, Segment Tree]
      
SegmentTree大集合. Methods: `build, query, modify`. 不难。只是要都记得不犯错.

#### Segment Tree
- build: recursively build children based on index-mid and link to curr node
- query: recursively try to find `node.start == targetStart && node.end == targetEnd`. Compare with node.mid
- modify: recursively try to find `node.start == targetStart && node.end == targetEnd`; modify and recursively assign upper interval with updated interval property.



---

**56. [Maximum Vacation Days.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Vacation%20Days.java)**      Level: Hard      Tags: [DP]
      


---

**57. [Convert Expression to Reverse Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Expression%20to%20Reverse%20Polish%20Notation.java)**      Level: Hard      Tags: [Binary Tree, DFS, Expression Tree, Stack]
      
给一串字符, 用来表示公式expression. 把这个expression转换成 Reverse Polish Notation (RPN).

#### Expression Tree
- Expression Tree: Minimum Binary Tree (https://lintcode.com/en/problem/expression-tree-build/)
- 根据题意做出Expression Tree出来以后: 来个Post-order-traversal 就能记录下 Reverse Polish Notation
- 本题没有给'ExpressionTreeNode', 所以把TreeNode就当做成我们需要的node, 里面扩展成有left/right child就可以了.



---

**58. [Word Ladder II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Ladder%20II.java)**      Level: Hard      Tags: [Array, BFS, Backtracking, DFS, Hash Table, String]
      
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

**59. [The Maze III.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Maze%20III.java)**      Level: Hard      Tags: [BFS, DFS, PriorityQueue]
      
#### BFS
- 跟 Maze I, II 类似, 用一个 Node[][] 来存每一个(x,y)的state.
- Different from traditional BFS(shortest path): `it terminates BFS when good solution exists (distance), but will finish all possible routes`
- 1. `Termination condition`: if we already have a good/better solution on nodeMap[x][y], no need to add a new one
- 2. Always cache the node if passed the test in step1
- 3. Always offer the moved position as a new node to queue (as long as it fits condition)
- 4. Finally the item at nodeMap[target.x][target.y] will have the best solution.



---

**60. [Bus Routes.java](https://github.com/awangdev/LintCode/blob/master/Java/Bus%20Routes.java)**      Level: Hard      Tags: [BFS]
      


---

**61. [Max Sum of Rectangle No Larger Than K.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Sum%20of%20Rectangle%20No%20Larger%20Than%20K.java)**      Level: Hard      Tags: [Array, BST, Binary Search, DP, Queue, TreeSet]
      
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

**62. [Largest Rectangle in Histogram.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Rectangle%20in%20Histogram.java)**      Level: Hard      Tags: [Array, Monotonous Stack, Stack]
      
给n个bar,组成柱状图histogram. 求在这一排柱状图里面可以找到的面积最大的长方形.

思考: 找长方形面积, 无非是找两个index, 然后底边长度 * height.

#### Monotonous Stack
- 重点是根据找Histogram里面rectangle的性质, 维持一个单调递增的Stack
- 在loop over indexes的时候:
- 如果高度>= previous peek(), 那么对于那个peek, 就意味着, 往下走, 一直走高嘛, 之前的peek总可以继续抄底
- 什么时候不能抄底了呢? 就是有一个下降趋势的时候
- 这时候并不是calculate所有前面的peek, 而是考虑 大于 current height的之前所有的peek.
- 把这些peek到 current height 前一格的rectangle全部找出来: stack.pop()
- 这个stack.pop()的过程里面, 其实没有算上 current height, 因为需要留到下一轮, 把current index加进stack 再说
- 为什么用stack? 因为需要知道连续递增的peek, stack.peek() O(1), 好用
  而其实不用stack, 也可以用其他方式记录所有height, 只不过要 O(n)去找peek不方便

#### 知识点
- 理解monotonous stack 是如何被维护的
- 维护monotonous stack 是题目需要, 而不是stack本身性质, 是一种借助 stack.peek() O(1)的巧妙用法.




---

**63. [[lint]. HashHeap.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20HashHeap.java)**      Level: Hard      Tags: [HashHeap, Heap, Lint]
      
非题.是从九章找来的HashHeap implementation.

#### HashHeap
- An efficient implementation of a priority queue. 
- The linear hash function monotonically maps keys to buckets, and each bucket is a heap
- https://xlinux.nist.gov/dads/HTML/hashheap.html



---

**64. [42. Trapping Rain Water.java](https://github.com/awangdev/LintCode/blob/master/Java/42.%20Trapping%20Rain%20Water.java)**      Level: Hard      Tags: [Array, Stack, Two Pointers]
      

这道题目的方法比较多.

#### Method1: Max wall from both sides
- Array: Left Max Wall vs Right Max Wall.
- 对于每个index而言, vertically 能存放的最大水柱, 就是靠 左 右 最高墙决定的: 
    - min(leftHighestWall, rightHighestWall) - currHeight.
- time: O(n)
- space: O(n)

#### Method2: Two Pointers
- Optimization from Method1: two pointer, 还是找左边最高和右边最高. O(1) space.
- 利用到了方法3里面的想法一样: 整个structure是被中间的最高bar 二分天下:
- always limited by the shorter wall: 左边按照maxLeft来计算, 右边按照maxRight来计算.
- time: O(n)
- space: O(1)

#### Method3: 2 Pointers, start from 2 sides
- 1. 找中间最高bar的index    
- 2. 两面往中心扫：每次加上（topBarIndex - currIndex）* (elevation from previous index).也就是每次加一个横条
- 3. 每次还要减去block自身的height
- time: O(n)
- space: O(1)

#### Method4: Stack
- 主要想法和方法3一致: 在山坡下坡的基础上, 一直用stack堆积bottom. 
- 最后遇到上升之前, 此时bottom可以用来跟stack之前堆积的所有下坡index做比较, 算跟他们高度相差的积水.
- 用了stack记录下坡, 然后用个while loop一挖到底的想法非常棒.
- time: O(n)
- space: O(n)




---

**65. [269. Alien Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/269.%20Alien%20Dictionary.java)**      Level: Hard      Tags: [BFS, Backtracking, DFS, Graph, Topological Sort]
      

给一个 array of strings: 假如这个array是按照一个新的字母排序表(alien dictionary)排出来的, 需要找到这个字母排序.

有可能有多重排序的方法, 给出一种就可以.

#### Graph, Topological Sort with InDegree count, BFS
- `Build graph`:
    - 上下两行string, 相对应的相同的index上, 如果字母不同, 就说明排在第一行的字母在字母表里更领先: form sequence between chars
    - form graph Map<Char, List of Chars>, for topological sort usage.
    - 也可以`List[26] edges` (Course Schedule problem)
- `Build InDegreeCountMap<Char, Count>`: based on the char diff of 2 words
    - 注意: indegree 是反向的 (跟 node to neighbors 相反的方式建立)
- `Topological Sort`, BFS:
    - 1) use queue to find `inDegree == 0` node. It is the letter that points to others, 排在字母表前面.
    - 2) reduce edges using Graph`map<Character, List<Character>>` (more generic than List[26], 26个字母的dictionary)
- Edge Case:
    - `inDegreeCountMap.size() != result.length()`: some nodes did not make it into result sequence
    - `cycle`: when inDegree of a one node would never reduce to 0, and will not be added to result
        - In this case, it will be treated as invalid input, and return ""
- space: O(n), n = # of graph edges 
- time: O(n)

#### DFS
- TODO
- 跟BFS建立 grpah 的过程一模一样
- DFS的不同在于: 用visited map 来标记走过的地方
- 走到leaf的时候, add to result: 但因为走到了底才add, 最终的顺序应该颠倒 (或者, sb.insert(0, x) 直接用颠倒的顺序add)



---

**66. [843. Guess the Word.java](https://github.com/awangdev/LintCode/blob/master/Java/843.%20Guess%20the%20Word.java)**      Level: Hard      Tags: [MiniMax]
      

TODO: revist time/space complexity

#### Minimax, find target, and use it to eliminate
- `擒贼先擒王`: find the candidate that has largest set of correlations with the rest candidates, and eliminate based on this candidate.
    - `approach A`: count the candidate that has 0 overlaps, find min of this poll
    - `approach B`: count the candidate that has largest # of connections
- cross-compare, count `match==0` <word, count>: find candidates that has 0 overlap with others
    - pick `min-count candidate A`: it is a candidate that has overlaps with most strings (since 0-match-count is lowest)
    - the above candidate will help to **eliminate** a largerset of overlapped candidates
    - guess A, return matchCount.
- filter set with matchCount: eliminateCandidate



---

**67. [76. Minimum Window Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/76.%20Minimum%20Window%20Substring.java)**      Level: Hard      Tags: [Hash Table, Sliding Window, String, Two Pointers]
      

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

**68. [301. Remove Invalid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/301.%20Remove%20Invalid%20Parentheses.java)**      Level: Hard      Tags: [BFS, DFS, DP]
      
给一个string, 里面有括号和其他字符. 以最少刀 剪出 valid string, 求所有这样的string.

这个题目有多种解法, 最强就是O(n) space and time

#### DFS and reduce input string
- Goal: identify invalid parentheses and remove (minimum removals)
- Step:
    - Detect the incorrect parentheses by tracking/counting (similar to validation of the parentheses string): `if(count<0)`
    - When invalid occurs: 
        - chance for correction. Remove the incorrect parentheses, one at a time
        - dfs on the rest of the s that has not been tested yet: start index from index i
    - Core edge cases:
        - Do not correct twice of the same parenthesis by checking [j-1] pos
        - Make sure to attempt correction of all possible parenthesis within tested range: because it outputs all results at the same level
        - return/finish once correction done
- Success case: 
    - a string s passed test: make sure it passes REVERSED string test!
    - Core Concept: `if a parenthese string is valid, the reverse of it should also be valid`
    - Test s with open='(', close=')' first; **reverse s**, and test it with open=')', close='('
- Minor details
    - only procceed to remove invalid parenthese when `count<0`, and also break && return dfs after the recursive calls.
    - The above 2 facts eliminates all the redundant results.
    - **Reverse string** before alternating open and close parentheses, so when **returning final result, it will return the correct order**.
- How does it guarantee minimum removals?
    - When seeing a chance to correct, it jumps into a for loop of DFS. It `return` after the for loop. This stops additional testing
    - When invalid occurs, correct it right away: minimum correction
- Complexity:
    - O(nk), k being the # of recursive calls. It takes n calls to finish a full string case.

#### BFS
- Similar to DFS, we wnat to test: 1) test input s valid, 2) remove 1 invalid parenthesis at a time, 3) process substring
- instead of testing all substrings (timeout), we want to establish rules to improve reprocess:
    - Test1: skip regular char. No need to test it.
    - Test2: if redundant paren, do 1 is enough. skip adjacent ones.
    - Test3: if last removed extra paren is '(', the next ')' must be a valid pair. LastRemoved char: pecial handling by using a struct: `class Node {String s, int index, char lastRemoved}`
- How to end tests? When there is data in rst, stop adding to queue.



---

**69. [1216. Valid Palindrome III.java](https://github.com/awangdev/LintCode/blob/master/Java/1216.%20Valid%20Palindrome%20III.java)**      Level: Hard      Tags: [DFS, DP, Memoization, String]
      

#### Method1: DP, utilize `Longest Palindrome Subsequence`
- Transform the problem:
    - `removing at most k items to make it a palindrome`
    - that is: find the longest palindrome subsequence with length x, such that `n - x <= k`
- `516. Longest Palindromic Subsequence` utilizes Interval DP to find LPS length x
- at the end, perform n - x <= k?
- time: O(n^2) to find LPS
- space: O(n^2) for dp

#### Method2: DFS with Memo
- Either times out or too much space used
- time: O(n^2)
- space: O(n^2) or O(k*n^2)



---

**70. [327. Count of Range Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/327.%20Count%20of%20Range%20Sum.java)**      Level: Hard      Tags: [BIT, Divide and Conquer, Merge Sort, PreSum, Segment Tree]
      

TODO: Write the code + merge function

#### Divide and Conquer + PreSum + MergeSort
- https://leetcode.com/problems/count-of-range-sum/discuss/77990/Share-my-solution
- 1) build preSum[n+1]: then sum range [i,j]= preSum[j+1] - preSum[i]
- 2) Divide and Conquer: 
    - 先考虑[start, mid] range里的 ran sum result
    - 再考虑[mid, end] range里面的结果
    - 最后考虑[low, high]总体的结果
- NOTE: should write merge() function, but that is minor, just use `Arrays.sort(nums, start, end)`, OJ passed
- Every mergeSort() has a for loop => O(n log n)
- 如何 count range?
    - 这里比较特别的一个做法: 找一个 [low, mid]里面的i, mid 之后的preSum作比较 (解释源自: https://blog.csdn.net/qq508618087/article/details/51435944)
    - 即在右边数组找到两个边界, 设为`m, n`, 
    - 其中m是在右边数组中第一个使得`sum[m] - sum[i] >= lower`的位置, 
    - n是第一个使得`sum[n] - sum[i] > upper`的位置, 
    - 这样`n-m`就是与左边元素i所构成的位于`[lower, upper]`范围的区间个数. 

##### 神奇的重点: 为什么要 merge and sort
- 边界[lower, higher] 在 sorted array 好作比较, 一旦过界, 就可以停止计算, 减少不必要计算.
- 上面这个n,m的做法可行的前提: preSum[]里面前后两个 range[low, mid], [mid, high]已经sorted了
    - 也就是说, 在recursively mergeSort()的时候, 真的需要merge sorted 2 partitions
    - 也许会问: 能不能sort呢, sort不久打乱了顺序? 对,打乱的是preSum[]的顺序.
    - 但是不要紧: 很巧妙的, 分治的时候, 前半段/后半段 都在原顺序保留的情况下 分开process完了, 最后才merge
- 在做m,n 的range的时候, 原理如下, 比如preSum被分成这么两段: `[A,B,C]`, `[D,E,F]`
    - 每一个preSum value `A` 在跟 preSum[i] 作比较的时候 `A - preSum < lower`, 都是单一作比较, 不牵扯到 B, C
    - 因此, `[A, B, C]` 是否保留一开始 preSum的顺序在此时不重要
- 此时最重要的是, `[A,B,C]`以及排序好, 那么在于 `lower` boundary 作比较的时候, 一旦过界, 就可以停止计算(减少不必要的计算)


#### BIT
- TODO?

#### Segment Tree
- This segment tree approach(https://leetcode.com/problems/count-of-range-sum/discuss/77987/Java-SegmentTree-Solution-36ms) 
    - does not build segment tree based on given nums index
    - it is built on sorted preSum array.
- regular segment tree based on nums array does not work:
    - segment tree based on input array is good for: search/query by index
    - is NOT good at: given range sum/value, find indexes
    - why? segment tree is built based on index division, not by range value division.



---

**71. [41. First Missing Positive.java](https://github.com/awangdev/LintCode/blob/master/Java/41.%20First%20Missing%20Positive.java)**      Level: Hard      Tags: [Analysis, Array, Edge Case]
      

给一串无序数字, 有负数: 找这个array里面第一个 missing的 positive integer

missing positive integer 其实是以 [1, n] 来做比较的.

#### Array分析, index 技巧
- 用while loop, 不断地尝试把 number 送到该放的地方
- 如果 index = nums[i] 超过了nums.length, 当然就不移动了
- 注意: 检查 val != nums[val], avoid infinitely loop
- 检验: nums[i] 是否等于 i, 如果不对, 就找到了结果

#### Edge Case
1. 如果nums==null, 其实missing positive integer 自然而然是 1
1. 有可能这串数字里没有断开的integer, 但是最大的integer在首位 (因为index超标, 无法被放到正确的地方)
    - 这种时候, n被放在 index 0, 其实就是说, 下一个integer应该是 n + 1
1. 最终, 如果array本来就是完全sorted, 也不缺, 还符合角标的条件, 那么唯一下一个就是array范围外的第一个positive number: n



---

**72. [308. Range Sum Query 2D - Mutable.java](https://github.com/awangdev/LintCode/blob/master/Java/308.%20Range%20Sum%20Query%202D%20-%20Mutable.java)**      Level: Hard      Tags: [Binary Indexed Tree, Segment Tree]
      

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

**73. [1203. Sort Items by Groups Respecting Dependencies.java](https://github.com/awangdev/LintCode/blob/master/Java/1203.%20Sort%20Items%20by%20Groups%20Respecting%20Dependencies.java)**      Level: Hard      Tags: [BFS, DFS, Graph, Topological Sort]
      

#### Topological Sort
- Realize we need to: 1) topo sort group, 2) topo sort items in the group. 
- Luckily, the candidates to be sorted are all integers: groupIds, or item ids. We can have 1 generic topo sort function
- Overall workflow
    - 1) group items to map <GroupId, List<items>>
    - 2) build group graph
    - 3) topo sort group -> return sorted group id list
    - 4) for each group: build item graph, topo sort items -> return sorted item list
    - 5) flatten and return results



---

**74. [1153. String Transforms Into Another String.java](https://github.com/awangdev/LintCode/blob/master/Java/1153.%20String%20Transforms%20Into%20Another%20String.java)**      Level: Hard      Tags: [Graph]
      

#### Graph
- analysis: 
    - 1) should not have mult-origin cases: 1 char maps to 1 char at maximum
    - 2) need a buffer char NOT exist in target to hold inter-media transformation 
        - check open char (out of 26 lower letter) that is NOT in target chars
- impl the validation rules
- more to read in https://leetcode.com/problems/string-transforms-into-another-string/discuss?currentPage=1&orderBy=most_votes&query=



---

**75. [850. Rectangle Area II.java](https://github.com/awangdev/LintCode/blob/master/Java/850.%20Rectangle%20Area%20II.java)**      Level: Hard      Tags: [Segment Tree, Sweep Line]
      

#### Sweep Line + Merge Interval concept
- Inspired by: https://leetcode.com/problems/rectangle-area-ii/discuss/137941/Java-TreeMap-solution-inspired-by-Skyline-and-Meeting-Room
- First consider regular sweep line and realize problem: each vertical line has multiple block segments
    - Easy: take a list of vertical dots, and calculate the height diff
    - We can use a TreeMap with y-coordinate as key, so to `natural sort by y-coordinate`
- Trick: can NOT remove used y coordinate from map, because the rectangle may continue to expand to right side.
- apply simple equation to calc area: `(long)preY * (p.x - preX)`
- time: 
    - sort initial queue: O(nlogn)
    - process queue: O(n)
        - TreeMap insertion: O(logn)
        - TreeMap traversal: O(n)
    - overall, process queue can be O(n^2)
- space: O(n)

#### Sweep Line concept, bottom->top sweep
- https://leetcode.com/problems/rectangle-area-ii/discuss/137914/C%2B%2BPython-Discretization-and-O(NlogN)

#### Segment Tree
- TODO lol



---

**76. [140. Word Break II.java](https://github.com/awangdev/LintCode/blob/master/Java/140.%20Word%20Break%20II.java)**      Level: Hard      Tags: [Backtracking, DFS, DP, Hash Table, Memoization]
      

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

**77. [51. N-Queens.java](https://github.com/awangdev/LintCode/blob/master/Java/51.%20N-Queens.java)**      Level: Hard      Tags: [Backtracking]
      

N-Queen 问题, 给数字n, 和 nxn board, 找到所有N-queens的答案.

#### Backtracking
- 用dfs找所有情况, 每一个iteration, 从找一行里挑合适的点, dfs
- 选中的点加进candidate list 里面, 记得要backtracking.
- 每一个candidate都需要validation, 检查 row, col, 2 diagnal 有没有queen
- Backtracking by replacement: each row has 1 queen, so just store it in int[] columns (CC book solution)

#### validate n queue at certain (x, y)
- 1. array 里面不能有 target row#
- 2. diagnal. 记得公式：
  - row1 - row2 == col1 - col2.     Diagnal elelment.fail
  - row1 - row2 == - (col1 - col2). Diagnal element. fail
- Draw a 3x3 board to test the 2 scanarios:
  - (0,0) and (3,3) are diagnal
  - (0,2) and (2,0) are diagnal




---

**78. [305. Number of Islands II.java](https://github.com/awangdev/LintCode/blob/master/Java/305.%20Number%20of%20Islands%20II.java)**      Level: Hard      Tags: [Union Find]
      

给一个island grid[][], and list of operations to fill a particualr (x,y) position.

count # of remaining island after each operation.

#### Union Find, model with int[]
- 把board转换成1D array， 就可以用union-find来判断了. 
- 用int[] father 的unionFind, 需要转换2D position into 1D index. 这样比较clean
- 判断时，是在四个方向各走一步，判断是否是同一个Land.
- 每走一次operator，都会count++. 若发现是同一个island, count--
- count的加减, 都放在了UnionFind自己的function里面, 方便tracking, 给几个helper function就对了.
- Time: O(k * log(mn))

#### Union Find, model with Hashmap 
- 用HashMap的Union-find.

#### Note:
- Proof of UnionFind log(n) time: https://en.wikipedia.org/wiki/Proof_of_O(log*n)_time_complexity_of_union%E2%80%93find



---

**79. [741. Cherry Pickup.java](https://github.com/awangdev/LintCode/blob/master/Java/741.%20Cherry%20Pickup.java)**      Level: Hard      Tags: [DFS, DP]
      

special hint: `r1 + c1 = constant t = r2 + c2`, if the two points are moving at same time.

#### DFS + Memo: TOP-DOWN
- Similar concept to Minimum Path Sum
- https://leetcode.com/problems/cherry-pickup/solution/
- realize r1 + c1 = r2 + c2. Knowing 3 parameters can uniquely identify the 4th.
- assume there are 2 people starting from origin, and the 2 people can go total 4 directions
    - perform DFS based on the 4 directions
    - concern: do they visit the same spot? possible. when that happens, make sure we do not double count the grid[i][j]
- when is the end state? 
    - then anyone, for example, (r1,c1) reaches end (n-1, n-1).
    - it means the other person also reaches end
- use memo: memo[r1][c1][r2], it records any given (r1, c1, r2, c2) state




---

**80. [297. Serialize and Deserialize Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/297.%20Serialize%20and%20Deserialize%20Binary%20Tree.java)**      Level: Hard      Tags: [BFS, DFS, Deque, Design, Divide and Conquer, Tree]
      

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

**81. [727. Minimum Window Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/727.%20Minimum%20Window%20Subsequence.java)**      Level: Hard      Tags: [DP, Hash Table, Sliding Window, String, Two Pointers]
      

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

**82. [158. Read N Characters Given Read4 II - Call multiple times.java](https://github.com/awangdev/LintCode/blob/master/Java/158.%20Read%20N%20Characters%20Given%20Read4%20II%20-%20Call%20multiple%20times.java)**      Level: Hard      Tags: [Enumeration, String]
      

Read N Character using `Read4(char[] buf)` 的加强版: 可以不断读 read(buf, n)

#### String 
- 注意String的index handle, 慢慢写edge case
- 理解题目意思: `read4(char[] buf)` 这样的 `populate input object` 的function稍微少一点. 
- 遇到时候, 仔细理解function用法, 不要慌乱. 其实思考方式很简单, 仔细handle string 还有 edge case就好了.
- alaternatively: use queue to hold so we do not need to worry about size



---

**83. [295. Find Median from Data Stream.java](https://github.com/awangdev/LintCode/blob/master/Java/295.%20Find%20Median%20from%20Data%20Stream.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap]
      

#### MaxHeap/MinHeap
- 把Input stream想成向上的山坡. 山坡中间那点，自然就是median.
- 前半段，作为maxHeap,关注点是PriorityQueue的峰点，也就是实际上的median.   
- 后半段，作为minHeap,正常的PriorityQueue。 开头是最小的。

#### 注意
- 这里要首先定好, 哪一个queue是多存一个element的. 这里选maxHeap: maxHeap.size() == minHeap.size() + 1 || minHeap.size()
- 必须先维护maxHeap里面有个元素, 否则null了会在比较大小时出问题.



---

**84. [315. Count of Smaller Numbers After Self.java](https://github.com/awangdev/LintCode/blob/master/Java/315.%20Count%20of%20Smaller%20Numbers%20After%20Self.java)**      Level: Hard      Tags: [BST, Binary Indexed Tree, Binary Search, Divide and Conquer, Segment Tree]
      

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

**85. [239. Sliding Window Maximum.java](https://github.com/awangdev/LintCode/blob/master/Java/239.%20Sliding%20Window%20Maximum.java)**      Level: Hard      Tags: [Deque, Heap, Sliding Window]
      

#### Method1: Deque, Monotonous queue
- 维持monotonuous queue: `front is always at max` and the `tail end is min`. Always need to return the max end of queue.
- when adding new elements x: 
    - 1) start from small-end of the queue
    - 2) drop all smaller elements 
    - 3) append to the ending element that is larger than x.
    - This is to maintain a front->tail decreasing queue
- when sliding window: queue curr window 里面 最大的已经在max-end,  remove it if needed.
- 妙：用deque数据结构（实际上采用LinkedList的形式）来做一个`递减的queue`: better than using arraylist, since DeQueue(linked list) removes at O(1) cost
- 每次把小于当前node的，全部剔除，剩下的，自然就是:最大的>第二大的>第三大的...ETC.
- 我们只在乎最大值的存在；而任何小于当前（正要新就加进去的）值的，反正以后也成不了最大值，于是扔掉！
- Option1: sliding window template using right/left + while loop
    - 1) tailing the new number to max queue, if applicable
    - 2) process: record max
    - 3) contract/shrink left: remove top max if the topMax is the left-most val: rst[i - k + 1]
- Option2: same concept, but use index `i` to mark right, and `i - k + 1` to mark left.
- time: O(n), one pass
- space: O(k), store the deque


#### Method2: Heap
- can always build a `class Node{index, val}`; and sort them with PQ of size k
- time: O(nlogK)
- space: O(k)
- this is not linear time, not as good as method1



---

**86. [10. Regular Expression Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/10.%20Regular%20Expression%20Matching.java)**      Level: Hard      Tags: [Backtracking, DP, Double Sequence DP, Sequence DP, String]
      
跟WildCard Matching 一样, 分清楚情况讨论 string p last char is '*' 还有并不是 '*'

IMPORTANT: '*' 需要有一个 prefix element [elm], so it becomes `[elm]*`. There 2 possible cases:
- [elm] repeats 0 times: move p, j + 2
- [elm] repeats 1 or more times: need s[i] == p[i], then move s, i+1

#### DFS, Top-Down, Break into sub problems.
- DFS on remaining of s and p. Analyze the different cases when next char == '*'
- End case: both i,j reached end true; or one of them reached end.
- The two different cases when given any index j on p, the  p[j+1]=='*'
    - TRUE:
        - ignore p[j, j+1], continue from p[j+2]
        - check if s[i]==p[j] or p[j]='.'; continue from s[i+1] and p
    - FALSE: check i,j, and move forward with s[i+1], p[j+1]
- If next p char != '*', check curr s[i] ?= p[i]
- Improvement with memo with 2D Booelan[][] memo: much faster
    - memo[i][j] records result the remaining strings: s.substring(i) compare with p.substring(j)
    - use `Boolean`: when memo[i][j] != null, return something!

#### DP, Sequence DP, Bottom-Up
- Two sequence, DP, find if possible to match.
- The '*' takes effect of preceding/prior element, so we can start matching from end.
- DP[i][j]: is it possible to match s[0 ~ i - 1] and p[0 ~ j - 1].
- Check last index of s and p, there can be a few possibilities:
    - 1. s[i-1]==p[j-1] and they are normal characters => && dp[i - 1][j - 1];
    - 2. p[j-1] == '.', match => dp[i - 1][j - 1]
    - 3. p[j-1] == '*':
        - a. ignore a* => |= dp[i][j - 2];
        - b. use a*    => |= dp[i - 1][j]; 
- init: dp[0][j] and dp[i][0] will all be false since there cannot be any match.




---

**87. [1106. Parsing A Boolean Expression.java](https://github.com/awangdev/LintCode/blob/master/Java/1106.%20Parsing%20A%20Boolean%20Expression.java)**      Level: Hard      Tags: [DFS, Stack, String]
      
#### Parse exp as sub problem
- Analyze the pattern: 1) single char, 2) with !, 3) with &, |
- Identify sub problem
    - Use stack to parse the data in "()", which is a sub problem to solve with recursive call
    - Handle &, | case: need to parse multiple
- Be comfortable with string parsing
- Slight improve: 
    - If see obvious result, directly return evaluation w/o further parsing
    - use memo to store evaluated exp

#### Evaluate inner exp and save back to Stack
- Use '(' and ')' to mark inner exp
- Evaluate the inner exp and save result back to Stack: the result will be 'f' or 't'
- This is slightly slow because:
    - It requires all stack items on top to be processed before reaching the operator
    - There is no room to optimize even there is simplification for specific operator



---

**88. [715. Range Module.java](https://github.com/awangdev/LintCode/blob/master/Java/715.%20Range%20Module.java)**      Level: Hard      Tags: [Segment Tree, TreeSet]
      

#### TreeSet
- start with considering array structure but operation are all O(n)
    - what if we can easily find range, and update
- TreeSet:
    - build a class `Interval {int start, end;}`
    - build a customized `compareTo` that sorts the interval by start at default, but sort by end if a.start==b.start
    - Query: TreeSet allow us to find element in O(logn)
    - Add Range: finding the starting pointing takes O(logn), but update can be worst to update O(n)
    - Remove Range: finding the starting pointing takes O(logn), but update can be worst to update O(n)



---

**89. [432. All One Data Structure.java](https://github.com/awangdev/LintCode/blob/master/Java/432.%20All%20One%20Data%20Structure.java)**      Level: Hard      Tags: [Design, Doubly Linked List]
      

#### Doubly Linked List
- IMPORTANT: the problem aims to put keys of same frequency in same node! This affects the design of node
- Main a class `Node {keySet, count, last/next pointers}`
- Each operation: 
  - 1) finds target node and extract the key
  - 2) calculate: count +/- 1
  - 3) find new spot to store the key (prior positions or later positions)
- Be careful when handling the cases in inc() and dec()



---

**90. [639. Decode Ways II.java](https://github.com/awangdev/LintCode/blob/master/Java/639.%20Decode%20Ways%20II.java)**      Level: Hard      Tags: [DP, Enumeration, Partition DP]
      

给出一串数字, 要翻译(decode)成英文字母. [1 ~ 26] 对应相对的英文字母. 求有多少种方法可以decode.

其中字符可能是 "*", 可以代表 [1 - 9]

#### DP
- 乘法原理, 加法原理
    - 跟decode way I 一样, 加法原理, 切割点时: 当下是取了 1 digit 还是 2 digits 来decode
    - 定义dp[i] = 前i个digits最多有多少种decode的方法. new dp[n + 1].
- 不同的情况是: 每一个partition里面, 如果有"*", 就会在自身延伸出很多不同的可能
- 那么: dp[i] = dp[i - 1] * (#variations of ss[i]) + dp[i - 2] * (#variations of ss[i,i+1])
- Enumeration: 
    - 具体分析 '*' 出现的位置, 枚举出数字, 基本功. 
    - 注意!!题目说 * in [1, 9].   (如果 0 ~ 9 会更难一些)
    - 枚举好以后, 其实这个题目的写法和思考过程都不难
- Mode:
    数字太大, 取mod来给最终结果: 其实在 10^9 + 7 这么大的 mod 下, 大部分例子是能通过的.


#### DFS + memoization
- DFS top-down approach is used to analyze the problem. The logic flow:
- 1) consider the case of 1 letter or 2 letters.
- 2) one letter:
    - [*]: + 9 * dfs(s, i + 1)
    - [0~9]: + dfs(s, i + 1)
- 3) two letters:
    - [_, *]: depends
    - [*, _]: depends
    - [*, *]: + 15 * dfs(s, i + 2)
- memo[i] records # of ways to decode from [i ~ n]
- space: O(n), Size of recursion tree can go upto n
- time: O(n), `memo array is filled exactly once`!!!



---

**91. [68. Text Justification.java](https://github.com/awangdev/LintCode/blob/master/Java/68.%20Text%20Justification.java)**      Level: Hard      Tags: [Enumeration, String]
      

按照规则 adjust text. 就是Word里面: 有一行太长, adjust word 中间的space, 然后保证每一行的total width 顶格.

还有一些细节规则, 看原题

#### String
- greedy approach: line up as many words as possible; once exceed the MaxLength, justify the list of words
- Steps
    - 1) Split & group
    - 2) Juststify a row of words
    - 3) clean up last row
- Calcualte bounded row length = `width + (list.size() - 1)`. `list.size()-1` = Minimum amount of slot/space used.
- Calculate max ave spaces ot insert into each slot = `totalExtraSpace/slot`
- `Juststify a row of words`: 
    - 1) take a list of words and assume minimum 1 space in-between words
    - 2) distribute the remaining spaces evenly to each slot
- Overall runtime: O(n) to go over all space
- Overall space O(maxWidth) for maxWidth amount of strings



---

**92. [340. Longest Substring with At Most K Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/340.%20Longest%20Substring%20with%20At%20Most%20K%20Distinct%20Characters.java)**      Level: Hard      Tags: [Hash Table, LinkedHashMap, Sliding Window, String, Two Pointers]
      

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

**93. [273. Integer to English Words.java](https://github.com/awangdev/LintCode/blob/master/Java/273.%20Integer%20to%20English%20Words.java)**      Level: Hard      Tags: [Enumeration, Math, String]
      

给一个小于 Integer.MAX_VALUE (2^31 - 1) 的数字, 转换成英语. (不需要加 'and')

#### String
- 基本implementation
- `分类讨论`: thounsand, million, billion.  `3个数字一格`.
- 用array枚举 token
- 运用 % 和 / 来找到每个分段的英语翻译
- 3-digit 的部分, 可以用一个helper funtion来找到结果, 每段的处理方法都是一样的
- Note:
    - StringBuffer 更有效率! `sb.insert(0, xxx)` append在sb前面
    - 注意加 " " 的时候, 如果多余, 要`trim()`
    - 注意, `小于20的数字, 有自己的特殊写法, 需要额外handle`
    - 这道题目就是要细致`耐心`, 几乎么有什么算法, 就是想要写的efficient并且正确, 需要很小心
- Thinking process:
    - `1 ~ 19`: [one, two ... nine, ten, eleven, ...., ninteen]
    - `20 ~ x0`: [twenty, thirty, fourty, ... ninety]
    - `x00`: hundred: 100
    - thousand: 10^3
    - million: 10^6
    - billion: 10^9
    - trillian: 10^12 way over 2^31, not needed
- plan: 
    - parse 3 digits at a time
    - convert the 3 digit to [xx hundred xx-ty x]
    - come up with a string[]
    - insert the thousands/million/billion to the string[]



---

**94. [218. The Skyline Problem.java](https://github.com/awangdev/LintCode/blob/master/Java/218.%20The%20Skyline%20Problem.java)**      Level: Hard      Tags: [BIT, Divide and Conquer, HashHeap, Heap, PriorityQueue, Segment Tree, Sweep Line]
      

#### Sweep Line, Time O(nLogN), Space O(n)
- Analysis (inspired by, but not same solution: https://leetcode.com/problems/the-skyline-problem/solution/)
    - If there are just 2 overlapping building (totally 4 points on x-axis), here is the outline process:
    - Process x coordinate from left->right, one at a time.
        - 1) compare all `on-going heights` and find max, add as new outline point
        - 2) Handling building end: if the position ends a building, need to remove this height from the list of `on-going heights`
    - Requires 2 heap:
        1) sort by x coordinates
        2) `on-going heights`: maintain a pq of ongoing heights
- Steps: 
    - original reference http://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html?_sm_au_=isVmHvFmFs40TWRt
    - 画图分析: 需要找到 non-overlaping height point at current index; also height needs to be different than prev height peek to be visible.
    - `on-going heights`: 用max-heap (reversed priorityqueue)，再iterate heightPoints 来存最大的height
    - NOTE: heightQueue里面加一个0, 用来在结尾的时候做closure
- time: initial sort O(nlogn) + calculate n * O(nlogn) [maxQueue sort]
- space: O(n)

#### Segment Tree
- 看了一些做法, segment tree写法很复杂, 估计在面试中难以用segment tree来写: https://www.cnblogs.com/tiezhibieek/p/5021202.html

#### HashHeap
- HashHeap template 可以考虑: https://www.jiuzhang.com/solution/building-outline/#tag-highlight-lang-java

Binary Indexed Tree?





---

**95. [52. N-Queens II.java](https://github.com/awangdev/LintCode/blob/master/Java/52.%20N-Queens%20II.java)**      Level: Hard      Tags: [Backtracking]
      

跟 N-Queens 一样, 不是找所有结果, 而是count多少结果.

#### Backtracking (with replacement)
- Each row has just 1 Queen value
- As CC book suggests, use `int[] columns` of length n to store all queen col positions for n rows
  - `int[] columns` is slightly easier to backtrack by updating certain index i with new col
  -  list will usualy has the add/remove pattern for backtracking

#### Backtracking
- 当list.size() == n 的时候，说明找到了一个Solution。
- 1. dfs function (List<Integer>, n)
- 2. validate function




---

**96. [65. Valid Number.java](https://github.com/awangdev/LintCode/blob/master/Java/65.%20Valid%20Number.java)**      Level: Hard      Tags: [Enumeration, Math, String]
      

分析edge case, 和各种情况, 然后判别是否是valid number

#### 情况总结
- 遇到 `.`, `e`, `+/-`, `int`的几种不同情况
- 分别遇到的顺序不同时候, 结果也不同.
- 这道题更多是分析情况, 然后把edge case enumerate出来, 算法的意义比较少.



---

**97. [632. Smallest Range Covering Elements from K Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/632.%20Smallest%20Range%20Covering%20Elements%20from%20K%20Lists.java)**      Level: Hard      Tags: [Hash Table, Sliding Window, Two Pointers]
      

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

**98. [745. Prefix and Suffix Search.java](https://github.com/awangdev/LintCode/blob/master/Java/745.%20Prefix%20and%20Suffix%20Search.java)**      Level: Hard      Tags: [Trie]
      

#### Chain `suffix # prefix`
- Build Trie for all combinations of `suffix#prefix`; all assigned with weight
- how does it make sure to return largest weight/index?
    - when we build trie, always update weight for the path nodes it goes through
    - yes, it overrides, but this problem does not care if some words are not found
- Time: 
    - build: go through all words O(n) * word length * 2 => O(n)
    - query: O(1) tree height is just at most 20.
- Space: O(N) store all words



---

**99. [124. Binary Tree Maximum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/124.%20Binary%20Tree%20Maximum%20Path%20Sum.java)**      Level: Hard      Tags: [DFS, DP, Tree, Tree DP]
      

找max path sum,  可以从任意treeNode 到任意 treeNode.

#### DFS
- IMPORTANT: DO NOT ASSUME positive integers
- Overall idea: write example and realize 2 cases at each node:
    - 1. `combo sum`: left + right + root
    - 2. `single path sum WITH curr node`: left/right + root
- DFS returns the path over curr node: a path needs to be continuous, so we cannot skip curr node.
- IMPORTANT, key discovery: if left/right single path over curr node is less than 0: reutrn 0. 
    - Parent path will simply drop this path, since we want **maximize** the path sum.
    - It is so IMPORTANT: when left or right becomes 0, when comparing with global combo path:
        - it automatically covers a special case: `single left/right path + node`, since one of left/right == 0!!!
- With the above understanding: what if I want to skip curr node and just want left/right path w/o curr node:
    - it is handled and compared with global in dfs(node.left) or dfs(node.right) automatically!
- time: O(n), go over whole tree
- space: O(logn), tree height.

#### DP的思想
- tree给我们2条branch, 每条branch就类似于 dp[i - 1], 这里类似于dp[left], dp[right] 这样
- 找到 dp[left], dp[right] 以后, 跟 curr node结合. 
- 因为是找max sum, 并且可以skip nodes, 所以需要全局变量max
- 每次dfs() return的一定是可以继续 `continuously link 的 path`, 所以return `one single path sum + curr value`.

#### DFS, PathSum object
- 用 PathSum 比较特别. 没有 data structure的时候, 写起来比较繁琐.
- 第一次做有点难理解，复杂原因是：因为可能有负值啊。不能乱assume正数。   
- single path max 的计算是为了给后面的comboMax用的。
- 如果single path max小于0，那没有什么加到parent上面的意义，所以就被再次刷为0.
- combo的三种情况：(root可能小于0)   
- 1. 只有left    
- 2. 只有right
- 3. root大于0，那么就left,right,curr全部加起来。
- 情况1和情况2取一个最大值，然后和情况三比较。做了两个Math.max(). 然后就有了这一层的comboMax



---

**100. [689. Maximum Sum of 3 Non-Overlapping Subarrays.java](https://github.com/awangdev/LintCode/blob/master/Java/689.%20Maximum%20Sum%20of%203%20Non-Overlapping%20Subarrays.java)**      Level: Hard      Tags: [Array, DP]
      

#### DP, Divide and conquer
- split into 3 parts [0, i -1], [i, i + k -1]. [i + k, n - 1]
- NOTE: be very careful about index handling: 
    - `presum[i + 1] - presum[0]` gives inclusive range of `[0, i]`
- Use DP to record the starting position of max sum, 
- inspired by: https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/108231/C%2B%2BJava-DP-with-explanation-O(n)
    - 1) calculate preSum with range [0, n]
    - 2) calculate leftMaxIndex[], rightMaxIndex[]
    - 3) test middle range to find max solution
- Note: the test range for 1, 2, 3 always start with assumption that k has been consumed from one side
- Note: When need to record at max/min value change, we can check/assign it manually (rather than use a object to carry & sort)




---

**101. [149. Max Points on a Line.java](https://github.com/awangdev/LintCode/blob/master/Java/149.%20Max%20Points%20on%20a%20Line.java)**      Level: Hard      Tags: [Array, Geometry, Hash Table, Math]
      

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

**102. [57. Insert Interval.java](https://github.com/awangdev/LintCode/blob/master/Java/57.%20Insert%20Interval.java)**      Level: Hard      Tags: [Array, PriorityQueue, Sort, Sweep Line]
      

#### Method1: Convert to list, insert, and merge list
- 这里已经给了 **sorted** intervals by start point;
    - 1) 直接找到可以insert newInterval的位子. Insert and convert to list
    - 2) Merge: Use `pre, curr` to iterate over list, and remove curr after merging
        - remove之前都会重新assgin `pre.end`, 确保被remove的node.end 被capture
    - 3) Convert back to int[][]
- time/space: O(n) 
- code is slightly better to read

#### Method2: Insert on the fly, and handle edge cases
- handle edge cases:
    - new interval is non-overlapping
        - 1) head
        - 2) tail
        - 3) in middle
    - new interval is overlapping:
        - 1) end index in existing interval; reuse the existing interval end to close new range
        - 2) end index in the gap of 2 intervals, use new interval.end to close the new range
- time, space: O(n)

#### Method3: Sweep Line
- Interval 拆点，PriorityQueue排点
- Merge时用count==0作判断点
- 注意, 一定要compare curr `p.x == queue.peek().x` 确保重合的点全部被process: `count+=p.x`
- PriorityQueue: O(logN). 扫n点, 总共：O(nLogn). SLOW.


#### 另外
- 因为interval已经sort, 本想用Binary Search O(logn). 
- 但是找到interval insert position 最后 merge还是要用 O(n), 所以不必要 binary Search



---

**103. [265. Paint House II.java](https://github.com/awangdev/LintCode/blob/master/Java/265.%20Paint%20House%20II.java)**      Level: Hard      Tags: [DP, Sequence DP, Status DP]
      

一排n个房子, 每个房子可涂成k种颜色, 涂每个房子的价钱不一样, 用costs[][]表示. 

costs[0][1]表示涂了index是0的房子, 用了color 1.

规则: 相邻的两个房子不能使同一种颜色

求: 最少的cost 

#### DP
- 跟Paint House I 几乎一模一样, 只不过paint color更多了: k colors.
    - 先考虑单纯地用dp[i]表示涂前 i 个房子的最小cost
    - 但是 dp[i] 和 dp[i-1] 两个index选什么颜色会互相影响, 难讨论, 于是加状态: 序列DP被加了状态变成2D. 
- 考    虑最后位, 而前一位i-1又被i位的颜色限制, 于是在考虑 min dp[i] 时候, 又多了一层iteration.
- 做dp[i][j]: # cost for 前 i 个房子, 所以要先pick (i-1) 房子的cost, 然后在找出 (i-2)房子的cost
- K种颜色 => O(NK^2)
- 如果不优化, 跟Paint House I 几乎是一模一样的代码
- Time O(NK^2), space(NK)
- Rolling array: reduce space to O(K)

#### 注意
- 序列型dp[i]表示'前i-1个'的结果. 所以dp最好设定为 int[n + 1] size. 
- 然而, 颜色在这里是状态, 所以保留在 j: [ 0~k)
- [[8]] 这样的edge case. 跑不进for loop, 所以特殊handle.

#### Optimization Solution
- Time: O(NK)
- 如果已知每次都要从cost里面选两个不同的最小cost,那么先把最小两个挑出来, 就不必有第三个for loop 找 min
- 每次在数列里面找: 除去自己之外的最小值, 利用最小值/次小值的思想
- 维持2个最值: 最小值/次小值. 
- 计算的时候, 如果除掉的不是最小值的index, 就给出最小值; 如果除掉的是最小值的index, 就给出次小值.
- Every loop: 1. calculate the two min vlaues for each i; 2. calcualte dp[i][j]
- 如何想到优化: 把表达式写出来, 然后看哪里可以优化
- 另外, 还是可以rolling array, reduce space complexity to O(K)



---

**104. [272. Closest Binary Search Tree Value II.java](https://github.com/awangdev/LintCode/blob/master/Java/272.%20Closest%20Binary%20Search%20Tree%20Value%20II.java)**      Level: Hard      Tags: [Stack, Tree]
      

#### Method1: Stack, DFS, Inorder Traversal
- find successors and predecessors using BST (both list will be sorted); in the end, we can easily get top k from the two sorted list
    - with BST: **inorder traversal gives us sorted predecessors
    - with BST: **reversed-inorder traversal gives us sorted successors
    - smallest on top of the stack
- time: O(n) visit all nodes, O(k) to output
- space overall: O(n) to store all nodes

#### Method2: BFS, brutle force
- Itereate over all nodes and maintain pq<TreeNode> (improvemenet point: how to avoid traversing entire tree?)
- prioritize nodes that are closer to target, so we may stop early when result reaches k candidates
- time: O(n*logn)
- kinds slow and not utilizing BST



---

**105. [72. Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/72.%20Edit%20Distance.java)**      Level: Hard      Tags: [DP, Double Sequence DP, Sequence DP, String]
      

两个字符串, A要变成B, 可以 insert/delete/replace, 找最小变化operation count

#### Double Sequence
- 考虑两个字符串的末尾index s[i], t[j]: 如果需要让这两个字符一样, 可能使用题目给出的三种operation: insert/delete/replace?
- 先calculate最坏的情况, 3种operation count + 1; 然后在比较match的情况.
- 注意, 在i或者j为0的时候, 变成另外一个数字的steps只能是全变.
- 第一步, 空间时间都是O(MN), O(MN)
- 滚动数组优化, 空间O(N)

##### Detail analysis
- insert: assume insert on s, `#ofOperation = (s[0 ~ i] to t[0 ~ j-1]) + 1;`
- delete: assume delete on t, `#ofOperatoin = (s[0 ~ i - 1] to t[0 ~ j]) + 1;`
- replace: replace both s and t, `#ofOperatoin = (s[0 ~ i - 1] to t[0 ~ j - 1]) + 1;`
- dp[i][j]代表了两个 sequence 互相之间的性质: s[0 ~ i] 转换成 s[0~j] 所需要的最少 operation count
- init: 当i==0, dp[0][j] = j; 每次都要 + j 个character; 同理, 当j==0, dp[i][0] = i;
- 而dp[i][j]有两种情况处理: `s[i] == t[j]` or `s[i] != t[j]`

##### 何时initialize
- 这种判断取决于经验: 如果知道initialization可以再 double for loop 里面一起做, 那么可以留着那么做
- 这样属于 `需要什么, initialize什么`
- 事后在做space optimization的时候, 可以轻易在 1st dimension 上做rolling array

#### Search
- 可以做, 但是不建议:这道题需要找 min count, 而不是search/find all solutions, 所以search会写的比较复杂, 牛刀杀鸡.



---

