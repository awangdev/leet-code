 
 
 
## BFS (54)
**0. [Binary Tree Level Order Traversal II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Level%20Order%20Traversal%20II.java)**      Level: Medium      Tags: [BFS, Tree]
      
如题, 但是output要倒序.

#### BFS
- 跟Binary Tree Level Order Traversal一样,只不过存result一直存在存在0位.


#### DFS
- 根据level来append每个list
- rst里面add(0,...)每次都add在list开头



---

**1. [Walls and Gates.java](https://github.com/awangdev/LintCode/blob/master/Java/Walls%20and%20Gates.java)**      Level: Medium      Tags: [BFS, DFS]
      
给一个room 2D grid. 里面有墙-1, 门0, 还有empty space INF(Math.MAX_VALUE). 

对每个empty space而言, fill it with dist to nearest gate.

#### DFS
- Form empty room: it can reach different gate, but each shortest length will be determined by the 4 directions. 
- Option1(NOT applicable). DFS on INF, mark visited, summerize results of 4 directions. 
- hard to resue: we do not know the direction in cached result dist[i][j]
- Option2. DFS on gate, and each step taken to each direction will +1 on the spot: distance from one '0'; 
- Through dfs from all zeros, update each spot with shorter dist
- Worst time: O(mn), where entre rooms[][] are gates. It takes O(mn) to complete the iteration. Other gates be skipped by `if (rooms[x][y] <= dist) return;`

#### BFS
- Exact same concept. Init with `Queue<int[]> queue = new LinkedList<int[]>()`



---

**2. [The Maze.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Maze.java)**      Level: Medium      Tags: [BFS, DFS]
      
#### BFS
- BFS on coordinates
- always attempt to move to end of border
- use boolean[][] visited to alingn with BFS solution in Maze II, III, where it uses Node[][] to store state on each item.



---

**3. [Perfect Squares.java](https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Squares.java)**      Level: Medium      Tags: [BFS, DP, Math, Partition DP]
      
给一个数字n, 找到这个数字 最少能用多少个 平方数组成. 

平方数比如: 1, 4, 9, 16 ... etc

#### Partition DP
- 遇到最值, 想到DP.
- 看到分割字眼, 想到分割型 DP. 
- 思考, 如果 j * j = 9, 那么 j = 3 就是最少的一步; 但是如果是10呢? 就会分割成1 + 9 = 1 + j * j 
- 考虑最后的数字: 要是12割个1出来, 剩下11怎么考虑? 割个4出来,剩下8怎么考虑?
- partion的方式: 在考虑dp[i - x]的时候,  x 不是1, 而是 x = j*j.
- 就变成了dp = Min{dp[i - j^2] + 1}

#### 时间复杂度
- 乍一看是O(n*sqrt(n)). 实际也是. 但如何推导?
- 考虑上限: 把小的数字变成大的 推导上限; 考虑下限: 把数字整合归小, 找到下限.
- 考虑sqrt(1) + sqrt(2) + ....sqrt(n):找这个的upper bound and lower bound.
- 最后发现它的两边是 A*n*sqrt(n) <= actual time complexity <= B*n*sqrt(n)
- 那么就是O(n*sqrt(n))啦

#### BFS
- minus all possible (i*i) and calculate the remain
- if the remain is new, add to queue (use a hashset to mark calculated item)
- find shortest path / lowest level number

#### Previous Notes
- 一开始没clue.看了一下提示
- １.　第一步想到了，从数学角度，可能是从最大的perfect square number开始算起。
- ２.　然后想法到了dp， 假设最后一步用了最大的maxSqrNum, 那么就在剩下的 dp[i - maxSqrNum^2] +１　不就好了？
- ３.　做了，发现有个问题．．．最后一步选不选maxSqrNum?  比如12就是个例子。
- 然后就根据提示，想到BFS。顺的。 把1～maxSqrNum 都试一试。找个最小的。
- 看我把12拆分的那个example. 那很形象的就是BFS了。
- 面试时候，如果拆分到这个阶段不确定，那跟面试官陶瓷一下，说不定也就提示BFS了。



---

**4. [Redundant Connection.java](https://github.com/awangdev/LintCode/blob/master/Java/Redundant%20Connection.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Tree, Union Find]
      
#### unionFind
- keyword: tree has no `cycle`.
- 一旦两个node在edge中出现, 并且parent相同, 说明这两个node不union, 也在同一个tree里面, 所以可以break them.

#### Graph, DFS
- Add graph using adjacent list, and verify cycle alone the way
- IMPORTANT: use `pre` node in dfs to prevent backward dfs
- similar to `Graph Valid Tree` where it validates cycle and also needs to validate if all nodes are connected

#### BFS
- same concept as DFS, find first redundant edge that alreay exists in graph map.



---

**5. [Minimum Height Trees.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Height%20Trees.java)**      Level: Medium      Tags: [BFS, Graph]
      
#### Graph + BFS
- Build graph `map<node, list of node>`
- BFS to find the shortest path: when the neibhbor has the curr node as the only one neighbor, it is leaf.
- record shortest path in Map<Integer, List<Integer>> as result
- TODO: code it up.

#### Previous Solution
- removing leaf && edge



---

**6. [Subsets II.java](https://github.com/awangdev/LintCode/blob/master/Java/Subsets%20II.java)**      Level: Medium      Tags: [Array, BFS, Backtracking, DFS]
      

给一串integers(may have duplicates), 找到所有可能的subset. result里面不能有重复.

#### DFS
- DFS, 找准需要pass along的几个数据结构. 先`sort input`, 然后DFS
- Using for loop approach: 每个dfs call是一种可能性，直接add into result.     
- 为了除去duplicated result, skip used item at current level: `if (i > depth && nums[i] == nums[i - 1]) continue;`
- sort O(nlogn), subset: O(2^n)
- space O(2^n), save results

#### BFS
- Regular BFS, 注意考虑如果让one level to generate next level
- skip duplicate: `if (i > endIndex && nums[i] == nums[i - 1]) continue;`
- 1. 用queue来存每一次的candidate indexes
- 2. 每一次打开一层candiates, add them all to result
- 3. 并且用每一轮的candidates, populate next level, back into queue.
- srot O(nlogn), subset: O(2^n)
- should be same O(2^n). slower than dfs

#### Previous notes:
- 在DFS种skip duplicate candidates, 基于sorted array的技巧：    
- 一旦for loop里面的i!=index，并且nums[i] == nums[i-1],
- 说明x=nums[i-1]已经在curr level 用过，不需要再用一次: [a,x1,x2]，x1==x2    
- i == index -> [a,x1]    
- i == index + 1 -> [a,x2]. 我们要skip这一种
- 如果需要[a,x1,x2]怎么办？ 其实这一种在index变化时，会在不同的两个dfs call 里面涉及到。

#### 注意
- 不能去用result.contains(), 这本身非常costly O(nlogn)
- 几遍是用 list.toString() 其实也是O(n) iteration, 其实也是增加了check的时间, 不建议




---

**7. [Word Ladder.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Ladder.java)**      Level: Medium      Tags: [BFS]
      
给一串string[], 需要找shortest distance to change from wordA -> wordB. (限制条件细节见原题)

#### BFS
- 通常, 给一个graph(这道题可以把beginWord看成一个graph的起始node), 找shortest path用BFS
- 在start string基础上，string的每个字母都遍历所有26个字母
- visited 过的 从wordList里去掉
- time: word length m, there can be n candidates => O(mn)
- 但是总是exceed time limit on LeetCode. However, it passes LintCode:
- 原因是 LeetCode给的是list,  list.contains(), list.remove()  都是 O(logn) time!!!
- convert to set first.

#### Trie
- timeout, overkill



---

**8. [Invert Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Invert%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      
#### DFS
- 简单处理swap
- recursively swap children

#### BFS
- BFS with Queue
- 每次process一个node, swap children; 然后把child加进queue里面
- 直到queue process完



---

**9. [Number of Connected Components in an Undirected Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      
给一个数字n代表n nodes, marked from 1 ~ n, 和一串undirected edge int[][]. 

count这个graph里面有多少个独立的component.

#### Union Find
- 跟Graph Valid Tree 几乎一模一样
- 建造简单的parent[] union find
- 每个edge都union.
- **注意** union 的时候, 只需要union if rootA != rootB

#### DFS
- build graph as adjacent list: Map<Integer, List<Integer>>
- dfs for all nodes of the graph, and mark visited node
- count every dfs trip and that will be the total unions



---

**10. [Find the Connected Component in the Undirected Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Connected%20Component%20in%20the%20Undirected%20Graph.java)**      Level: Medium      Tags: [BFS, DFS]
      
给一个undirected graph, return 所有的component. (这道题找不到了)  

#### BFS
- BFS遍历，把每个node的neighbor都加进来. 
- 一定注意要把visit过的node Mark一下。因为curr node也会是别人的neighbor，会无限循环。      
- Component的定义：所有Component内的node必须被串联起来via path (反正这里是undirected, 只要链接上就好)     
- 这道题：其实component在input里面都已经给好了，所有能一口气visit到的，全部加进queue里面，他们就是一个component里面的了。     
- 而我们这里不需要判断他们是不是Component

#### DFS
- DFS 应该也可以 visit all nodes, mark visited.



---

**11. [Surrounded Regions.java](https://github.com/awangdev/LintCode/blob/master/Java/Surrounded%20Regions.java)**      Level: Medium      Tags: [BFS, DFS, Matrix DFS, Union Find]
      
给一个2D board, 里面是 'X' 和 'O'. 把所有被X包围的area都涂成'X'. 

从四个边的edge出发, 像感染僵尸病毒一样扩散, 把靠边的node全部mark, 然后将还是'O'的改成X, 最后回复marks -> 'O'

#### Union Find
- UnionFind里面这次用到了一个rank的概念, 需要review. rank[] 也就是在tracking每一个node所在union的size.
- 目的是: always并到大的union里面
- note: 将2D coordinate (x,y) 转换成1D: index = x * n + y

#### DFS: mark all invalid 'O'
- Reversed thinking: find surrounded nodes, how about filter out border nodes && their connections?
- Need to traverse all the border nodes, consider dfs, visit all.
- loop over border: find any 'O', and dfs to find all connected nodes, mark them as 'M'
- time: O(mn) loop over all nodes to replace remaining 'O' with 'X'

#### DFS: mark all valid 'O'
- More like a graph problem: traverse all 'O' spots, and mark as visited int[][] with area count [1 -> some number]
- Run dfs as top->bottom: mark area count and dsf into next level
- End condition: if any 'O' reaches border, mark the global map<count, false>
- keep dfs untill all connected nodes are visited.
- At the end, O(mn) loop over the matrix and mark 'X' for all the true area from map.
- Practice: write code to verify

### BFS
- TODO



---

**12. [Shortest Distance from All Buildings.java](https://github.com/awangdev/LintCode/blob/master/Java/Shortest%20Distance%20from%20All%20Buildings.java)**      Level: Hard      Tags: [BFS]
      
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

**13. [The Maze II.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Maze%20II.java)**      Level: Medium      Tags: [BFS, DFS, PriorityQueue]
      
#### BFS
- if already found a good/shorter route, skip
- `if (distMap[node.x][node.y] <= node.dist) continue;`
- This always terminates the possibility to go return to original route, because the dist will be double/higher



---

**14. [Trapping Rain Water II.java](https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water%20II.java)**      Level: Hard      Tags: [BFS, Heap, MinHeap, PriorityQueue]
      
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

**15. [Sliding Puzzle.java](https://github.com/awangdev/LintCode/blob/master/Java/Sliding%20Puzzle.java)**      Level: Hard      Tags: [BFS, Graph]
      


---

**16. [Complete Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Complete%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, Tree]
      
A complete binary tree is a binary tree in which every level, except possibly the last,

is completely filled, and all nodes are as far left as possible

#### BFS
- 当出现了第一次有 null children的node的时候, 说明到了leaf level, mark flag = true;
- 自此以后，queue再不该有node再有child; queue后面出现的node的left/right child应该都是null
- 否则就是有问题, return false;




---

**17. [Word Ladder II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Ladder%20II.java)**      Level: Hard      Tags: [Array, BFS, Backtracking, DFS, Hash Table, String]
      
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

**18. [The Maze III.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Maze%20III.java)**      Level: Hard      Tags: [BFS, DFS, PriorityQueue]
      
#### BFS
- 跟 Maze I, II 类似, 用一个 Node[][] 来存每一个(x,y)的state.
- Different from traditional BFS(shortest path): `it terminates BFS when good solution exists (distance), but will finish all possible routes`
- 1. `Termination condition`: if we already have a good/better solution on nodeMap[x][y], no need to add a new one
- 2. Always cache the node if passed the test in step1
- 3. Always offer the moved position as a new node to queue (as long as it fits condition)
- 4. Finally the item at nodeMap[target.x][target.y] will have the best solution.



---

**19. [Bus Routes.java](https://github.com/awangdev/LintCode/blob/master/Java/Bus%20Routes.java)**      Level: Hard      Tags: [BFS]
      


---

**20. [[tool]. Topological Sorting.java](https://github.com/awangdev/LintCode/blob/master/Java/[tool].%20Topological%20Sorting.java)**      Level: Medium      Tags: [BFS, DFS, Lint, Topological Sort]
      

#### Topological Sort BFS
- indegree tracking: Track all neighbors/childrens. 把所有的children都存在 inDegree<label, indegree count>里面
- Process with a queue: 先把所有的root加一遍(indegree == 0)，可能多个root。并且全部加到queue里面。
- BFS with Queue:
- Only when map.get(label) == 0, add into queue && rst. (indegree剪完了, 就是root啦)
- inDegree在这里就 count down indegree, 确保在后面出现的node, 一定最后process.


#### Basics about graph
- 几个graph的condition：   
- 1. 可能有多个root
- 2. directed node, 可以direct backwards.

TODO:
- build`Map<DirectedGraphNode, Integer> inDegree = new HashMap<>();` and include the root itself
- that is more traditional indegree building



---

**21. [102. Binary Tree Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/102.%20Binary%20Tree%20Level%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

如题.

#### Method1: BFS
- 最普通,Non-recursive: BFS, queue, 用个queue.size()来end for loop:换行。   
- 或者用两个queue. 当常规queue empty，把backup queue贴上去

#### Method2: DFS
- 每个level都应该有个ArrayList. 那么用一个int level来查看：是否每一层都有了相应的ArrayList。   
- 如果没有，就加上一层。    
- 之后每次都通过DFS在相应的level上面加数字。




---

**22. [269. Alien Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/269.%20Alien%20Dictionary.java)**      Level: Hard      Tags: [BFS, Backtracking, DFS, Graph, Topological Sort]
      

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

**23. [301. Remove Invalid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/301.%20Remove%20Invalid%20Parentheses.java)**      Level: Hard      Tags: [BFS, DFS, DP]
      
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

**24. [111. Minimum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/111.%20Minimum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

#### BFS
- Shortest path; minimum depth: 想到BFS, check level by level, BFS更能确保更快找到结果
- depth definition: reach to a leaf node, where node.left == null && node.right == null
- BFS using queue, track level.

#### DFS
- Divide and Conquer to find min depth. 
    - if one of child is null, return the other child depth + 1
    - Pick the min of the two child depth + 1
- need to visit all nodes




---

**25. [207. Course Schedule.java](https://github.com/awangdev/LintCode/blob/master/Java/207.%20Course%20Schedule.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS, Graph, Topological Sort]
      

- 一堆课用int[2] pair 来表示. [1, 0] 表示要上课1的话, 必须先把课0上了. 
- 每一个数字都是一个ndoe, 题目问是否能把所有的课排了
- input是 numOfCourses, 还有这个prerequisites [[]]
- Concept of Indegree: `# of incoming node that depends on me`. It is a graph of `arrows pointing inward to me` structure.

##### Indegree 原理
- Remember: **indegree是周围的node到我这里的次数count**.
    - Note: 如果有cycle, 这个node上面会多一些inDegree, 也就无法清0, 它也无法进入 queue && sorted list. 
    - 如果周围所有node的连线, 都意义切除后, 我的indegree还不等于0, 那么肯定有某些node间接地有重复连线, 也就是有cycle
- Topological problem: almost always care about cycle case (if detecting cycle is not goal)

#### BFS, Topological Sort
- Two structures:
    - 1) build inDegreeEdges: `List[] inDegreeEdges`: list of incoming nodes that depends on `node i`, 
    - 2) build dependencyCount: `int[] dependencyCount`, count # of braches that curr node depends on
- any dependencyCount[node]==0, means this node is now a leaf, add to queue
- Topological Sort Process, Kahn algorithem:
- topologically process: 
    1) add leaf node to queue, get ready to process; 
    2) process leafNode, like cutting of leaf
    3) if any child node dependencyCount == 0, it is a leaf node now: add this node to queue.

#### DFS
- this problem aims for deteching cycle, not output final list. Simply: visit all nodes and verify cycle
- Option1: array of indegree lists, List[]
    - 用 visited int[] 来确认是否有cycle. 1 means `visited`, -1 means `visted from last dfs level`
        - Deteching `-1`: 说明这个node在上一级或者以上的同一个dfs path里面已经走过, 那么证明有cycle, return false.
        - dfs on curr node indegree dependencies; if all passes w/o failing, set visited[i] = 1
    - Similarly, can use `HashMap<Integer, List<Integer>> map` to replace List[], but exact same idea.
- Optoin2: use a struct `class Node {Boolean visiting; Map<Integer, Node> inDegreeMap}` to be more generic
- topo sort may output the sort order: 1) at DFS bottom level, put record to a `stack`, 2) rst.insert(0, curr record)

#### Notes
- 还有 List[] arrayOfList = new ArrayList[]; 这样的操作啊, 代替了map<integer, integerList>. Though: map may be more flexible
- 是topological sort的题目。一般都是给有dependency的东西排序。    
    - 最终都会到一个sink/leaf node，no further dependency, 在那个点截止
- 画个图的话, prerequisite都是指向那个sink/leaf node
- when building the inDegreeMap/inDegreeEdge: we use sink/leaf node as key/index, which pionts back to inDegree/parent nodes
- BFS: when all braches/dependency count are reduced to 0, then it is now a leaf node, ready to be used.
- DFS Insert Order: rst.insert(0, node); Assume we want leaf/node at index 0 in final output:
    - the very bottom-node **depends on everybody**
    - any visited node should be added to 0 index of the list, so it will be at tail later



---

**26. [987. Vertical Order Traversal of a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/987.%20Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree.java)**      Level: Medium      Tags: [BFS, Binary Tree, DFS, Hash Table, Tree]
      
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

**27. [429. N-ary Tree Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/429.%20N-ary%20Tree%20Level%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, Tree]
      

#### BFS
- use queue to hold each level. O(n)



---

**28. [199. Binary Tree Right Side View.java](https://github.com/awangdev/LintCode/blob/master/Java/199.%20Binary%20Tree%20Right%20Side%20View.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

给一个binary tree, 从右边看过来, return all visible nodes

#### BFS
- 最右: 即level traversal每一行的最末尾.   
- BFS, queue 来存每一行的内容, save end node into list
- time: O(n) visit all nodes
- space: O(n) worst case unbalanced tree to have n nodes in final results


#### DFS
- Use Map<Level, Integer> to override the result at each level
- dfs: 
    - dfs(node.left) and then dfs(node.right) because we want to log right side last
- record global max depth for iteration purpose
- time: O(n) visit all nodes
- space: O(n) worst case unbalanced tree to have n stacks (and n nodes in final results)




---

**29. [1203. Sort Items by Groups Respecting Dependencies.java](https://github.com/awangdev/LintCode/blob/master/Java/1203.%20Sort%20Items%20by%20Groups%20Respecting%20Dependencies.java)**      Level: Hard      Tags: [BFS, DFS, Graph, Topological Sort]
      

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

**30. [515. Find Largest Value in Each Tree Row.java](https://github.com/awangdev/LintCode/blob/master/Java/515.%20Find%20Largest%20Value%20in%20Each%20Tree%20Row.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

#### Method1: DFS
- faster than BFS, using less space if not couting final rst: stack size, O(logn)
- time: O(n), visit all

#### Method2: BFS with queue
- loop over queue level and record max




---

**31. [1161. Maximum Level Sum of a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/1161.%20Maximum%20Level%20Sum%20of%20a%20Binary%20Tree.java)**      Level: Medium      Tags: [BFS, DFS, Graph]
      


#### BFS
- simply calc each level sum with BFS
- top-level is processed first, since we go from top level -> deeper level
    - only update result if sum is truly > global MAX.



---

**32. [1091. Shortest Path in Binary Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/1091.%20Shortest%20Path%20in%20Binary%20Matrix.java)**      Level: Medium      Tags: [BFS]
      


#### BFS
- find shortest path using queue
- time/space: O(n^2), n = grid length
- why SKIP `boolean visited[i][j]`? after a position grid[i][j] is used: 
    - 1) the curr path will not return to (i, j)
    - 2) other route that may eventually reach (i, j) need not to be recorded,
        - because the other route is already longer than the curr path
    - therefore, we just simply block the visited node by `grid[x][y] = 1`
        - note: block it right after it is added to the queue, so other nodes at same level will not attempt this visited node.



---

**33. [339. Nested List Weight Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/339.%20Nested%20List%20Weight%20Sum.java)**      Level: Easy      Tags: [BFS, DFS, NestedInteger]
      

给一串integers, list里面可能有nest list. 算总的sum. 规则, 如果是nested list, 每深一个depth, sum要乘以depth.

#### DFS
- New interface to understand: object contains integer or object
    - Visit all && sum, consider dfs.
    - 简单的处理nested structure, dfs增加depth.
- time: visit all nodes eventually, O(n), space O(n)

#### BFS
- bfs, queue, 处理queue.size() for a level
- use a level variable to track levels
- slower since it uses extra space, worst case O(n) of all items



---

**34. [1197. Minimum Knight Moves.java](https://github.com/awangdev/LintCode/blob/master/Java/1197.%20Minimum%20Knight%20Moves.java)**      Level: Medium      Tags: [BFS]
      

#### BFS
- `from starting point, find min steps to reach certain point`: think of BFS
    - similar: shortest path, shortest distance
- bfs: minimum steps, enumerate the possible moves
    - move closer to x or y (test 8 possible directions)
    - add possible moves in queue
- use visited to cache visited coordinates
- time: O(8^n), # of BFS branches
- space: O(8^n), # of BFS branche nodes



---

**35. [1306. Jump Game III.java](https://github.com/awangdev/LintCode/blob/master/Java/1306.%20Jump%20Game%20III.java)**      Level: Medium      Tags: [BFS, Graph]
      

### Method1: BFS
- Find possibility to reach certain point, we can BFS: faster to find shortest candidate
- use queue to hold left, right candidates
- use set to record visited

### Method2: DFS
- attemp all nodes, use set to record visited.
- time: O(n)
- space: O(n)



---

**36. [297. Serialize and Deserialize Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/297.%20Serialize%20and%20Deserialize%20Binary%20Tree.java)**      Level: Hard      Tags: [BFS, DFS, Deque, Design, Divide and Conquer, Tree]
      

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

**37. [46. Permutations.java](https://github.com/awangdev/LintCode/blob/master/Java/46.%20Permutations.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS, Permutation]
      

#### Method1-Option1: Recursive Backtracking, with queue to maintain the remaining list
- Best of all recursive approaches
- iterate over nums: pick or not pick
- reduce remaining item in next level:
    - option1: use queue, restrict queue size; backtrack append to queue
    - option2: remove element before passing into next level; backtrack: insert back
- time O(n!): visit all possible outcome
    - T(n) = n * T(n-1) + O(1)

Method1-Option2: Recursive Backtracking, with `list.contains()` to avoid reuse of index
- A bit worse than option1, uses more time:
    - list.contains() cost O(logn). Technically, it is O(n^n), plus the `contains` is nlogn time
    - also, each dfs, it has to iterate over entire nums list

Method1-Option3: Recursive Backtracking, with `visited[]` to avoid reuse of index
- Use visited[] to track, still causes for(over n items), not efficient

#### Method2-Option1: Iterative, Build permutation by insertion
- Best of all iterative approaches
- Each time pick 1 NEW element and find places to insert into candidate list:
    - 1. 一个一个element加进去
    - 2. 每一次把rst里面的每个list拿出来, 创建成新list, 然后选位置加上new element
    - 3. 加新元素的时候, 要在list的每个位置insert, 最终也要在原始的list末尾加上new element
- 还是O(n!), 因为rst insert O(n!)个permutations
- Better than the Option2/Option3 (`BFS+Queue`), because this solution does not need to check duplicates

#### Method2-Option2: Iterative, use Queue to hold candidate list
- 用个queue，每次poll()出来的list, 把在nums里面能加的挨个加一遍
- Time O(n!)
- Slow: checking candidate.contains() is O(logn) each time

#### Method2-Option3: Iterative, use queue to candidate list, and calculate remain list on the fly
- Almost same as Method2-Option2, but it builds remainingCandidate list on the fly list.removeall(xyz): O(n)
- Even slower than Method2-Option2



---

**38. [200. Number of Islands.java](https://github.com/awangdev/LintCode/blob/master/Java/200.%20Number%20of%20Islands.java)**      Level: Medium      Tags: [BFS, DFS, Matrix DFS, Union Find]
      

给一个2Dmatrix, 里面是1和0, 找#of island.

#### Method1, DFS
- visit all nodes connected with the starting node
    - double for loop, test all starting nodes
    - val == 1: 1) count++; 2)DFS from this (i,j);
    - Mark visited (x,y) = '0'
- time: O(n), visit all nodes
- space: O(n), stack

#### Method2, Union Find
- 可以用union-find， 就像Number of island II 一样.
    - 只不过这个不Return list, 而只是# of islands
    - Union Find is independent from the problem: it models the union status of integers.
    - Return the total # of unions (which is # of islands)
- in reality: it is a bit slow.
- time: visit all nodes just once, O(n). Union Find will visit all nodes once and union them
- space: O(n), union find takes O(n) space
- 记住UnionFind的模板和几个变化(Connecting Graph I, II, III), 最后归总的代码写起来就比较简单. 

#### Method3: BFS
- use queue to hold 1 island, keep adding 4-direction islands; mark visited with '0' 
- check entire board for any remaining one.



---

**39. [144. Binary Tree Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/144.%20Binary%20Tree%20Preorder%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Stack, Tree]
      

#### Recursive, DFS, Divide and conquer
- 加root, left, then right. Obvious
- Option1: recursive on preorderTraversal. the dfs function returns List
- Option2: pass in rst, and write a void dfs.

#### Iterative
- 先加root, 然后push上需要末尾process的在stack垫底(root.right), 然后push root.left
- Stack: push curr, push right, push left.   



---

**40. [100. Same Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/100.%20Same%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

给两个 binary tree, 看两个tree是否identical.

#### Method1: DFS
- DFS. 确定leaf条件, && with all dfs(sub1, sub2).
- 这里无论如何都要走过所有的node, 所以dfs更加合适, 好写.

#### Method2: BFS with 2 queues
- 两个queue存每个tree的所有current level node. Check equality, check queue size.
- Populate next level by nodes at current level.



---

**41. [78. Subsets.java](https://github.com/awangdev/LintCode/blob/master/Java/78.%20Subsets.java)**      Level: Medium      Tags: [Array, BFS, Backtracking, Bit Manipulation, DFS]
      

给一串unique integers, 找到所有可能的subset. result里面不能有重复.

#### DFS
- dfs的两种路子: 1. pick&&skip dfs, 2. for loop dfs
- 1. pick&&skip dfs: 取或者不取 + backtracking. 当level/index到底，return 一个list. Bottom-up, reach底部, 才生产第一个solution.
- 2. for loop dfs: for loop + backtracking. 记得：做subset的时候, 每个dfs recursive call是一种独特可能，先加进rst.  top-bottom: 有一个solution, 就先加上.
- Time&&space: subset means independent choice of either pick&&not pick. You pick n times: `O(2^n)`, 3ms
- space: O(2^n) results

#### Bit Manipulation
- n = nums.length, 那么在每一个index, 都是 pick / not pick: 0/1
- 考虑subset index 0/1的bit map: range 的就是 [0000...00 ~ 2^n-1]
- 每一个bitmap就能展现出一个subset的内容: all the 1 represents picked indexes
- 做法:
- 1. 找出Range
- 2. 遍历每一个bitmap candidate
- 3. 对每一个integer 的 bit representation 遍历, 如果是1, add to list
- time: O(2^n * 2^n) = O(4^n), still 3ms, fast.

#### Iterative, BFS
- BFS, 注意考虑如果让one level to generate next level
    - 1. maintain a list of Indexe to store candidate indexes.
    - 2. 每一次打开一层candiates, add them all to result
    - 3. 并且用每一轮的candidates, populate next level, back into queue.
- should be same O(2^n), but actual run time 7ms, slower
- O(n) space



---

**42. [210. Course Schedule II.java](https://github.com/awangdev/LintCode/blob/master/Java/210.%20Course%20Schedule%20II.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Topological Sort]
      

- `207. Course Schedule` has more notes
- 一堆课用int[2] pair 来表示. [1, 0] 表示要上课1的话, 必须先把课0上了. 
- 每一个数字都是一个ndoe, 题目求这个最后排好的课的list
- 如果排不好, 就给个空就好
- input是 numOfCourses, 还有这个prerequisites [[]]


#### Topological Sort, Indegree, BFS
- 用`List[] edges; edges[i] = new ArrayList<>();` 来表示graph: 就是每个node, to all its neighbors
- 每个没有 inDegree==0 node, 都是可以加进 final list里面的. 比如一开始找到的那些 inDegree = 0的 node
- 注意, 如果 prerequisites = [], 那么就是说这些课都independent, 开个int[0 ~ n-1]的数组并赋值就好.
- 如果有cycle, 严格意义上就做不了topological sort, 也无法涵盖所有nodes,  那么return [ ]

#### DFS
- 根据 Course Schedule 里面的DFS 修改
- 维持visited int[]全局变量
- 维持sortedList int[] 全局变量, 注意加进去的时候是 add(0, node) 加在开头这样
- 每次到一个node的children全部DFS走完之后, 就可以把他加进final list里面
- 如果有cycle, 也就是dfs return false的时候, 这个题目判定排课失败, return new int[] { }



---

**43. [314. Binary Tree Vertical Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/314.%20Binary%20Tree%20Vertical%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Hash Table, Tree]
      

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

**44. [103. Binary Tree Zigzag Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/103.%20Binary%20Tree%20Zigzag%20Level%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, Stack, Tree]
      
    
#### Queue
- 简单的level traversal.根据level奇数偶数而add到不同位子.
- Option1: based on level % 2, insert to front/end of list
- Option2: based on level, insert right/left of node into queue



---

**45. [261. Graph Valid Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/261.%20Graph%20Valid%20Tree.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      
给一个数字n代表n nodes, marked from 1 ~ n, 和一串undirected edge int[][]. 

检查这些edge是否能合成一个 valid tree

#### Method1: Union Find
- 复习Union-Find的另外一个种形式, track union size: tree does not have cycle, so eventually union size should == 1
    - 1. 查找2个元素是不是在一个union里面。如果不在，false. 如果在，那就合并成一个set, 共享parent.   
    - 2. 验证cycle: `find(x) == find(y) => cycle`
        - ideally, this edges[i] should be the very first time x and y node connect;
        - however, if they have been grouped together under same ancestor before, there exist a feedback loop (cycle) between them.
- `father[x]`: element x (index x) stores its root ancestor
- 注意: 结尾要检查, 是否只剩下1个union: Tree必须连接到所有给出的node.
- 另一个union-find, 用hashmap的:
- http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/
- Deep Dive into UnionFind: https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf

#### Method2: Build Graph in adjacency list format: `Map<Integer, Set<Integer>>` and DFS
- Very similar to `Redundant Connection`
- Create adjacency list graph: Map<Integer, List<Integer>>
- 检查: 
- 1. 是否有cycle using dfs, check boolean[] visited
- 2. 是否所有的node全部链接起来: validate if all edge connected: # of visited node should match graph size
- IMPORTANT: use `pre` node to avoid linking backward/infinite loop such as (1)->(2), and (2)->(1)

#### Method3: BFS on adjacency list graph
- traverse through adjacency list graph: `Map<Integer, List<Integer>>` 
- 1. validate cycle with set: if revisit same node
    - avoid infinite loop: remove backward mapping from child node to parent node
- 2. validate check set size for connected



---

**46. [133. Clone Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/133.%20Clone%20Graph.java)**      Level: Medium      Tags: [BFS, DFS, Graph]
      

给一个graph node, 每个node有list of neighbors. 复制整个graph, return new head node.
       
实现起来就好像在crawl urls.

#### 思想
- Use HashMap to mark cloned nodes: `map<oldNode, newNode>`
    - 1) make new curr node; 
    - 2) clone all neibhors and add them
- Use the map to avoid visited nodes
- time: O(n). visit all nodes
- space: O(n). Technically only travels n levels/stacks to circle all nodes (undirected & connected)

#### DFS
- Given graph node obj `{val, list of neighbor}`: copy the node and all neighbors
- Mark visited using map<oldNode, newNode>
- for loop on the each one of the neighbors: map copy, record in map, and further dfs
- once dfs completes, add newNeighbor as neighbor of the new node (get to it via map)
- 主要思想是: 一旦复制过了, 不必要重新复制

#### BFS
- Copy the root node, then copy all the neighbors. 
- Mark copied node in map.
- Use queue to contain the newly added neighbors. Need to work on them in the future.



---

**47. [743. Network Delay Time.java](https://github.com/awangdev/LintCode/blob/master/Java/743.%20Network%20Delay%20Time.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Heap, PQ]
      

quesiton: sorting by travel delay/time will find better answer earlier?

#### Method1: BFS with PQ on graph
- `Dijkstras algorithm` is based on repeatedly making the candidate move that has the least distance travelled.
- PQ: pick close node to vist, and add siblings back to PQ
- avoid visited
- time: O(nLogn), visit n nodes, each time insert to heap takes O(logn) time
- space: O(n)

#### Method2: DFS with Sort
- 1) build graph map, 2) traverse map, 3) prioritize short delay nodes first
- use a map`<node, timeElapsed>` globally track dealy to nodes; compare all at the end



---

**48. [1123. Lowest Common Ancestor of Deepest Leaves.java](https://github.com/awangdev/LintCode/blob/master/Java/1123.%20Lowest%20Common%20Ancestor%20of%20Deepest%20Leaves.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

#### Method1: DFS, top-down
- key concetpL the `common ancester of deppest leaves` must have its `two branch being same depth`. problem sovled.
- dfs on both branch
- if returned depth equals & equal to max depth, record common ancestor
- time: O(n) traversal 1 pass
- space: O(n) dfs worst case depth

#### Method2: bottom-up, find leaves first and traverse backwards
- 1) find leaf nodes, and store backward map to root (DFS/ BFS both work)
- 2) use leaf nodes to find way backwards till common node is found; return
- time: O(n) but two passes
- space: O(n) dsf + map storage
- this approach is more brutle and uses exrtra spaces



---

**49. [399. Evaluate Division.java](https://github.com/awangdev/LintCode/blob/master/Java/399.%20Evaluate%20Division.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      
#### DFS
- build map of `x#y -> val` to store values[i] and 1/values[i]
- build map of `x -> list children`
- dfs to traverse the graph

#### BFS
- BFS should also work: build graph and valueMap
- for each starting item, add all next candidate to queue
- mark visited, loop until end item is found



---

**50. [785. Is Graph Bipartite.java](https://github.com/awangdev/LintCode/blob/master/Java/785.%20Is%20Graph%20Bipartite.java)**      Level: Medium      Tags: [BFS, DFS, Garph]
      

#### DFS marking each node with a state
- `bipartite` require each node to be in exact 1 party, which means it only has 1 state
- DFS to mark node with one state; and mark its edges as reversed state
  - If any node state has been assigned by different from desired one, return false.

#### BFS, Queue
- Use `Boolean states[i]` to represent visted & state
- Try all nodes with for loop, and skip visited nodes (similar validation rules as in dfs)
- In `int next : graph[curr]`, test next level first before adding.



---

**51. [101. Symmetric Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/101.%20Symmetric%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

检查tree是否symmetric

注意Symmetric Binary Tree的例子和定义: 是镜面一样的对称. 并不是说左右两个sub-tree相等。

#### Method1: DFS
- Recursively check symmetrically相对应的Node.  
- 每个node的children都和镜面另外一边相对的node的children刚好成镜面反射位置。

#### Method2: interative with queue 
- put left or right children in pair

#### Method3: iterative with Stack
- stack1: 左手边sub-tree先加left, 再加right child; 
- stack2: 右手边sub-tree先加right child, 再加left child。   
- process时，若symmetric，所有stack里面出来的node会一一对应。



---

**52. [671. Second Minimum Node In a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/671.%20Second%20Minimum%20Node%20In%20a%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, Tree]
      

#### BFS
- min tree: parent node is the min of left/right child
- BFS to traverse the tree and find 1st non-root smallest val
- Improvement area: when `node.val >= nextMin`, no need to dive into node children since it is a min Tree.

#### DFS
- Find left and right val: 
    - if left/right equals root.val, that means the left or right sub children could have larger number
    - Therefore DFS into left or right
- compare and return min(left, right)



---

**53. [254. Factor Combinations.java](https://github.com/awangdev/LintCode/blob/master/Java/254.%20Factor%20Combinations.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS]
      


#### Method1: DFS
- build candidate into dfs: treat each list candidate as success, add to rst
- remove last item from the candidate, try to add factor to it, and supply it with remain element
- backtrack after dfs


#### Method2: BFS
- Check if the number can be devided by [2, sqrt(n)], return a list of possible factors. Only check till `Math.sqrt(n)`
  - build suffixes: use the factor to devide last element of list and replace last element
  - build candidate: replace last element of the queue item with new list of suffixes; add to rst
  - add success item back to queue: in case last element can be simplified
- **remove dupilcates**: since we start factor from [2, sqrt(n)], the final factor list should be **ascending**!!
- time: O(x), x is the # of results
- space: O(y), y is all ongoing candidates in queue



---

