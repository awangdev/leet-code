 
 
 
## Backtracking (31)
**0. [Gray Code.java](https://github.com/awangdev/LintCode/blob/master/Java/Gray%20Code.java)**      Level: Medium      Tags: [Backtracking]
      

TODO:
1. backtracking, using set to perform contains()
2. BFS: use queue to keep the mutations

题目蛋疼，目前只接受一种结果。

BackTracking + DFS:   
   Recursive helper里每次flip一个 自己/左边/右边. Flip过后还要恢复原样.遍历所有.

曾用法（未仔细验证）：   
基本想法就是从一个点开始往一个方向走，每次flip一个bit, 碰壁的时候就回头走。



---

**1. [Palindrome Permutation II.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Permutation%20II.java)**      Level: Medium      Tags: [Backtracking, Permutation]
      

TODO: need to review permutation

permutation的综合题：    
1. validate Input 是不是可以做palindromic permutation. 这个就是（Palindrome Permutation I）   
2. 顺便存一下permutation string的前半部分和中间的single character(if any)    
3. DFS 做unique permutation: given input有duplicate characters。       



---

**2. [Permutation Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutation%20Sequence.java)**      Level: Medium      Tags: [Backtracking, Math]
      

TODO: what about regular DFS/backtracking to compute the kth? dfs(rst, list, candiate list, k)

k是permutation的一个数位。而permutation是有规律的。

也就是说，可以根据k的大小来判断每一个数位的字符（从最大数位开始，因为默认factorio从最大数位开始变化）。

于是先求出n!， 然后 k/n!就可以推算出当下这一个数位的字符。然后分别把factorio 和 k减小。

另外, 用一个boolean[] visited来确保每个数字只出现一次。

这个方法比计算出每个permutation要efficient许多。




---

**3. [Letter Combinations of a Phone Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Letter%20Combinations%20of%20a%20Phone%20Number.java)**      Level: Medium      Tags: [Backtracking, String]
      

方法1: Iterative with BFS using queue.

方法2: Recursively adding chars per digit



---

**4. [Regular Expression Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Regular%20Expression%20Matching.java)**      Level: Review      Tags: [Backtracking, DP, Double Sequence DP, Sequence DP, String]
      



---

**5. [Wildcard Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java)**      Level: Hard      Tags: [Backtracking, DP, Double Sequence DP, Greedy, Sequence DP, String]
      

Double sequence DP. 与regular expression 很像.

注意1: 分析字符 ?, * 所代表的真正意义, 然后写出表达式.
注意2: 搞清楚initialization 的时候 dp[i][0] 应该always false.当p为empty string, 无论如何都match不了 (除非s="" as well)
    同时 dp[0][j]不一定是false. 比如s="",p="*" 就是一个matching.



---

**6. [Word Break II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Break%20II.java)**      Level: Hard      Tags: [Backtracking, DFS, DP, Memoization]
      

#### DFS + Memoization
- Realize the input s expands into a tree of possible prefixes.
- We can do top->bottom(add candidate+backtracking) OR bottom->top(find list of candidates from subproblem, and cross-match)
- DFS on string: find a valid word, dfs on the suffix. [NO backtraking in the solution]
- DFS returns List<String>: every for loop takes a prefix substring, and append with all suffix (result of dfs)
- Memoization: `Map<substring, List<String>>`, which reduces repeated calculation if the substring has been tried.
- Time O(n!). Worst case, permutation of unique letters: `s= 'abcdef....'`, and `dict=[a,b,c,d,e,f...]`

#### Regular DPs
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

**7. [Binary Tree Paths.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Paths.java)**      Level: Easy      Tags: [Backtracking, Binary Tree, DFS]
      

给一个binary tree, 返回所有root-to-leaf path

#### DFS, backtracking
- Find all paths, bfs/dfs all works. dfs will be simplier to write
- Recursive:分叉. dfs.
- top->bottom: enumerate current node into the list, carry to next level, and backtrack
- top->bottom is trivial to consider: path flows from top->bottom

#### DFS, bottom->up
- We can also take current node.left or node.right to generate list of results from the subproblem
- let dfs return list of string candidates, and we can run pair the list with currenet node, once they come back.
- TODO: can write code to practice

#### Iterative
- Iterative, 非递归练习了一下
- 因为要每次切短list, 所以再加了一个Stack 来存level
- 单这道题用dfs更简单, 因为找的就是从头到尾的path, 是dfs的pattern




---

**8. [Add and Search Word.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20and%20Search%20Word.java)**      Level: Medium      Tags: [Backtracking, Design, Trie]
      

Trie结构, prefix tree的变形： '.'可以代替任何字符，那么就要iterate这个node所有的children.

节点里面有char, isEnd, HashMap<Character, TrieNode>   
Build trie = Insert word:没node就加，有node就移动。   
Search word:没有node就报错. 到结尾return true   

这题因为'.'可以代替任何possible的字符，没一种都是一个新的path，所以recursive做比较好些。    
(iterative就要queue了,麻烦点)



---

**9. [Word Search II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search%20II.java)**      Level: Hard      Tags: [Backtracking, DFS, Trie]
      

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

**10. [Word Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search.java)**      Level: Medium      Tags: [Array, Backtracking, DFS]
      

#### DFS, Backtracking:
- 找到开头的字母, 然后recursively DFS 去把word串到底:
- 每到一个字母, 朝四个方向走, 之中一个true就可以.
- Note:每次到一个字母，mark一下'#'. 4个path recurse回来后，mark it back.
- time: O(n^2) * O(3^m)?  where m = word length

#### Note: other ways of marking visited:
- 用一个boolean visited[][]
- Use hash map, key = x@y




---

**11. [Word Squares.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Squares.java)**      Level: Hard      Tags: [Backtracking, Trie]
      

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

**12. [Course Schedule.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS, Graph, Topological Sort]
      

- 一堆课用int[2] pair 来表示. [1, 0] 表示要上课1的话, 必须先把课0上了. 
- 每一个数字都是一个ndoe, 题目问是否能把所有的课排了
- input是 numOfCourses, 还有这个prerequisites [[]]

#### Topological Sort
- 给一个graph of nodes
- 至关重要: 用`List[] edges; edges[i] = new ArrayList<>();` 来表示graph: 就是每个node, to all its neighbors
- 目标是根据edge 的 direction, 把这个graph 里面的 node sort 一个list
- 如果有cycle, 这个item就不会被放在最后的list 里面. 
- 比如: 如果两个课互相是dependency, 就变成了cyclic dependency, 这样不好.


#### BFS
- Kahn algorithem:
- 先build一个graph map: <node, list of nodes >; or `List[] edges; edges[i] = new ArrayList<>();`
- count in-degree: inDegree就是每个node上面, **有多少个走进来的edge**?
- **IMPORTANT**: always initialize inDegree map/array with 0
- 那些没有 in-coming-edge的, indegree 其实就 等于 0, 那么他们就应该在final result list里面
- 对这些 indegree == 0 的 nodes BFS, add to queue.
- visit queue 上每个 node: count++, also add this curr node to sorted list
- Check all neighbors/edges of curr node: 如果visit过了, 这个node上的 indegree--
- 如果 indegree == 0, add this node to queue.

##### Indegree 原理
- Note: 如果有cycle, 这个node上面会多一些inDegree, 也就无法清0, 它也无法进入 queue && sorted list. 
- Remember: **indegree是周围的node到我这里的次数count**
- 如果周围所有node的连线, 都意义切除后, 我的indegree还不等于0, 那么肯定有某些node间接地有重复连线, 也就是有cycle
- Topological problem: almost always care about cycle case (if detecting cycle is not goal)

#### DFS
- 这道题没有要求作出final list, 相对简单, 只要visit每个nodes, 最后确认没有cycle就好了
- 用 visited int[] 来确认是否有cycle. 1 代表 paretNode visited, -1 代表在DFS上一行的标记
- 如果遇到-1, 说明这个node在上一级或者以上的同一个dfs path里面已经走过, 那么证明有cycle, return false.
- 走完一个node的所有neighbor, 都没有fail, 那么backtracking, set visited[i] = 1
- 真的topo sort会在DFS的底端, 把record放进一个stack, 最后reverse, 就是真的sort order.

#### Notes:
- 还有 List[] arrayOfList = new ArrayList[]; 这样的操作啊, 代替了map<integer, integerList>
- List[]的list, 其实是default  List<Object>

#### Previous notes
有点绕，但是做过一次就明白一点。    
是topological sort的题目。一般都是给有dependency的东西排序。    

最终都会到一个sink node， 再不会有向后的dependency, 在那个点截止。    
我就已这样子的点为map的key, 然后value是以这个node为prerequisite的 list of courses.    

画个图的话，prerequisite都是指向那个sink node， 然后我们在组成map的时候，都是从sink node 发散回来到dependent nodes.    

在DFS里面，我们是反向的， 然后，最先完全visited的那个node, 肯定是最左边的node了，它被mark的seq也是最高的。    

而我们的sink node，当它所有的支线都visit完了，seq肯定都已经减到最小了，也就是0，它就是第一个被visit的。   


最终结果：
每个有pre-requisit的node都trace上去（自底向上），并且都没有发现cycle.也就说明schedule可以用了。



---

**13. [Permutations II.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutations%20II.java)**      Level: Medium      Tags: [Backtracking]
      

给一串数组, 找出所有permutation数组. 注意: 给出的nums里面有重复数字, 而permutation的结果需要无重复.

#### Backtracking
- 排序, 
- Mark visited. 通过permutation规律查看是否排出了重复结果
- 并且要检查上一层recursive时有没有略过重复element
- time O(n!)

##### 背景1
- 在recursive call里面有for loop, 每次从i=0开始, 试着在当下list上加上nums里面的每一个。    
- 从i=0开始，所以会依次recursive每一个nums：
- 因此，例如i=2,肯定比i=3先被访问。也就是:取i=2的那个list permutation肯定先排出来。   

##### 背景2
- 重复的例子：给出Input[x, y1, y2], 假设y的值是一样的。那么，{x,y1,y2}和{x,y2,y1}是相同结果。

##### Note
- 综上，y1肯定比y2先被访问,{x,y1,y2}先出。 紧随其后，在另一个recursive循环里，{x,y2...}y2被先访问，跳过了y1。    
- 重点:规律在此，如果跳过y1，也就是visited[y1] == false, 而num[y2] == num[y1]，那么这就是一个重复的结果，没必要做，越过。
- 结果:那么，我们需要input像{x,y1,y2}这样数值放一起，那么必须排序。

#### Non-recursive, manuall swap
- Idea from: https://www.sigmainfy.com/blog/leetcode-permutations-i-and-ii.html
- 用到 sublist sort
- 用 swap function, 在原数组上调节出来新的permutation
- 注意: 每次拿到新的candidate, 都要把没有permutate的数位sort, 然后再开始swap.
- 这是为了确保, [j]和[j-1]在重复时候, 不用重新记录.

#### Queue
- 给一个visited queue
- 和queue在所有的地方一同populate. 
- 然后visited里面存得时visited indexes。 (Not efficient code. check again)



---

**14. [N-Queens.java](https://github.com/awangdev/LintCode/blob/master/Java/N-Queens.java)**      Level: Hard      Tags: [Backtracking]
      

N-Queen 问题, 给数字n, 和 nxn board, 找到所有N-queens的答案.

#### Backtracking
- 用dfs找所有情况, 每一个iteration, 从找一行里挑合适的点, dfs
- 选中的点加进candidate list 里面, 记得要backtracking.
- 每一个candidate都需要validation, 检查 row, col, 2 diagnal 有没有queen

#### validate n queue at certain (x, y)
- 1. array 里面不能有 target row#
- 2. diagnal. 记得公式：
- row1 - row2 == col1 - col2.     Diagnal elelment.fail
- row1 - row2 == - (col1 - col2). Diagnal element. fail
- Draw a 3x3 board to test the 2 scanarios:
- (0,0) and (3,3) are diagnal
- (0,2) and (2,0) are diagnal




---

**15. [N-Queens II.java](https://github.com/awangdev/LintCode/blob/master/Java/N-Queens%20II.java)**      Level: Hard      Tags: [Backtracking]
      

跟 N-Queens 一样, 不是找所有结果, 而是count多少结果.

#### Backtracking
- 当list.size() == n 的时候，说明找到了一个Solution。
- 1. dfs function (List<Integer>, n)
- 2. validate function



---

**16. [Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20II.java)**      Level: Easy      Tags: [Backtracking, DFS, Tree]
      

给一个inputSum, 然后dfs, 找到所有path, 满足: path sum 跟 inputSum 一样.

#### DFS, Backtracking
- 用remaining sum 来检测是否满足 input path sum 条件
- 满足的时候add to result list
- 两种backtracking:
- 1. backtrack 当下node, 加进list, 然后dfs. dfs结束后删掉之前加进去的元素. 非常clean.
- 2. backtrack 下一个dfs level增加的value. dfs return 之后, 删掉list里面的末尾元素: 但是删掉的dfs余下的value.
- 第一种backtrack更加好掌握.

#### Previous Notes:
- Binary Tree的一个基本题: 找到所有满足条件的path
- 遍历到底，比较sum vs. target
- 注意divide的情况。要把遍历的例子写写



---

**17. [Combinations.java](https://github.com/awangdev/LintCode/blob/master/Java/Combinations.java)**      Level: Medium      Tags: [Backtracking, Combination, DFS]
      

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

#### DFS, Backtracking
- for loop, recursive (dfs).
- 每个item用一次, 下一个level dfs(index + 1)
- Combination DFS. 画个图想想. 每次从1~n里面pick一个数字i
- 因为下一层不能重新回去 [0~i]选，所以下一层recursive要从i+1开始选。



---

**18. [Permutations.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutations.java)**      Level: Medium      Tags: [Backtracking, DFS, Permutation]
      

#### Recursive: Backtracking
- Given a remaining list: 取, 或者不取
- Improvement: maintain list (add/remove elements) instead of 'list.contains'
- time O(n!): visit all possible outcome
- T(n) = n * T(n-1) + O(1)

#### Iterative: Insertion
- 插入法:
- 1. 一个一个element加进去
- 2. 每一次把rst里面的每个list拿出来, 创建成新list, 然后选位置加上new element
- 3. 加新元素的时候, 要在list的每个位置insert, 最终也要在原始的list末尾加上new element
- 还是O(n!), 因为rst insert O(n!)个permutations
- 但是比dfs要快, 因该是因为 # of checks 少: 不需要check list.size(), 不需要maintain remaining list.

#### Previous Notes
- 用个queue，每次poll()出来的list, 把在nums里面能加的挨个加一遍
- Time O(n!)
- A bit slower, possibly because of the polling and saving the entire list every time




---

**19. [Restore IP Addresses.java](https://github.com/awangdev/LintCode/blob/master/Java/Restore%20IP%20Addresses.java)**      Level: Medium      Tags: [Backtracking, DFS, String]
      

给一串数字, 检查是否是valid IP, 如果合理, 给出所有valid 的IP组合方式.

#### Backtracking
- 递归的终点:list.zie() == 3, 解决最后一段
- 递归在一个index上面, pass s.toCharArray()
- validate string要注意leading '0'
- 注意: 递归的时候可以用一个start/level/index来跑路
- 但是尽量不要去改变Input source， 会变得非常confusing.
- note: code有点messy, 因为要考虑IP的valid情况
- 那个'remainValid', 其实是一个对于remain substring的判断优化, 不成立的就不dfs了



---

**20. [Expression Add Operators.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Add%20Operators.java)**      Level: Hard      Tags: [Backtracking, DFS, Divide and Conquer, String]
      

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

**21. [Generate Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Generate%20Parentheses.java)**      Level: Medium      Tags: [Backtracking, DFS, Sequence DFS, String]
      

#### DFS
- start with empty string, need to go top->bottom
- 取或者不取`(`, `)`
- rule: open parentheses >= close parentheses
- Note: 在DFS时 pass a reference (StringBuffer) and maintain, instead of passing object (String) and re-create every time
- time: O(2^n), pick/not pick, the decision repat for all nodes at every level
- T(n) = 2 * T(n - 1) + O(1)

#### bottom->up DFS
- figure out n=1, n=2 => build n=3, and n=4
- dfs(n-1) return a list of candidates
- add a pair of `()` to the candidates: either in front, at end, or contain the candidates



---

**22. [Alien Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/Alien%20Dictionary.java)**      Level: Hard      Tags: [BFS, Backtracking, DFS, Graph, Topological Sort]
      

给一个 array of strings: 假如这个array是按照一个新的字母排序表(alien dictionary)排出来的, 需要找到这个字母排序.

有可能有多重排序的方法, 给出一种就可以.

#### Graph
- 本质: 上下两行string, 相对应的相同的index上, 如果字母不同, 就说明排在第一行的字母在字母表里更领先
- 把 string array 变成topological sort的 graph: `map<char, list<char>>`
- 也可以`List[26] edges` (Course Schedule problem)
- Build edges: find char diff between two row, and store the order indication into graph
- 注意: indegree 永远是反向的 (跟 node to neighbors 相反的方式建立)

#### BFS
- topological sort 本身很好写, 但是要在题目中先了解到字母排序的本质
- 其实上面这个排序的本质很好想, 但是把它具体化成构建graph的代码, 会稍微有点难想到
- 算indegree, 然后用 BFS 来找到那些 inDegree == 0的 node
- 最先inDegree == 0的node, 就排在字母表前面.
- 下面的解法, 用了Graph: map<Character, List<Character>>, 而不是 List[26], 其实更加试用超过26个字母的dictionary.
- 如果 `inDegree.size() != result.length()`, there is nodes that did not make it into result. 
- ex: cycle nodes from input, where inDegree of a one node would never reduce to 0, and will not be added to result
- In this case, it will be treated as invalid input, and return ""

#### DFS
- 跟BFS建立 grpah 的过程一模一样
- DFS的不同在于: 用visited map 来标记走过的地方
- 走到leaf的时候, add to result: 但因为走到了底才add, 最终的顺序应该颠倒 (或者, sb.insert(0, x) 直接用颠倒的顺序add)



---

**23. [Flip Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Flip%20Game%20II.java)**      Level: Medium      Tags: [Backtracking, DFS, DP]
      

String 只包含 + , - 两个符号. 两个人轮流把consecutive连续的`++`, 翻转成 `--`.

如果其中一个人再无法翻转了, 另一个人就赢. 求: 给出string, 先手是否能赢.

#### Backtracking
- curr player 每走一步, 就生成一种新的局面, dfs on this
- 等到dfs结束, 不论成功与否, 都要backtracking
- curr level: 把"++" 改成 "--"; backtrack的时候, 改回 '--'
- 换成boolean[] 比 string/stringBuilder要快很多, 因为不需要重新生成string.
- ++ 可以走 (n - 1)个位置: 
- T(N) = (N - 2) * T(N - 2) = (N - 4) * (N - 2) * T(N - 4) ... = O(N!)

##### iterate based on "++"
- 做一个String s的 replica: string or stringBuilder
- 每次dfs后, 然后更替里面的字符 "+" => "-"
- 目的只是Mark已经用过的index
- 真正的dfs 还是在 original input string s 身上展开
- 每次都重新生成substring, 并不是很efficient

##### Game theory
- 保证p1能胜利，就必须保持所有p2的move都不能赢
- 或者说, 在知道棋的所有情况时, 只要p2有一种路子会输, p1就一定能走对路能赢.
- 同时，p1只要在可走的Move里面，有一个move可以赢就足够了。
- p1: player1, p2: player2

#### O(N^2) 的 DP
- 需要Game Theory的功底, Nim game. https://www.jiuzhang.com/qa/941/
- http://www.1point3acres.com/bbs/thread-137953-1-1.html
- TODO: https://leetcode.com/problems/flip-game-ii/discuss/73954/Theory-matters-from-Backtracking(128ms)-to-DP-(0ms)



---

**24. [Palindrome Partitioning.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Partitioning.java)**      Level: Medium      Tags: [Backtracking, DFS]
      

给个string s, partition(分段)后, 要确保每个partition都是palindrome. 

求所有partition palindrome组合. `list<list<string>>`

#### DFS
- 可以top->bottom: 遍历str, validate substring(start, i); if valid, add as candidate, and dfs; backtrack by remove candidate.
- 也可以bottom->up: 遍历str, validate substring(start, i); if valid, dfs(remaining str), return list of suffix; cross match with curr candidate.

#### DFS Top->Bottom
- 在遍历str的时候，考虑从每个curr spot 到 str 结尾，是能有多少种palindorme?
- 那就从curr spot当个字符开始算，开始backtracking.
- 如果所选不是palindrome， 那move on.
- 若所选的确是palindrome,　加到path里面，DFS去下个level，等遍历到了结尾，这就产生了一种分割成palindrome的串。
- 每次DFS结尾，要把这一层加的所选palindrome删掉，backtracking嘛

#### Optimization
- 可以再每一个dfs level 算 isPalindrome(S), 但是可以先把 boolean[][] isPalin 算出来, 每次O(1) 来用
- 注意: isPalin[i][j] 是 inclusive的, 所以用的时候要认准坐标
- Calculate isPalin[i][j]: pick mid point [0 ~ n]
- expand and validate palindrome at these indexes: `[mid, mid+1]` or `[mid-1][mid+1]`

#### Complexity
- Overall Space O(n^2): 存 isPlain[][]
- Time O(2^n), 每一层都在做 pick/not pick index i 的选择, 所以worst case 2^n. 
- 因为我们的isPalin[][]优化了palindrome的判断O(1), 所以overall Time: O(2^n)



---

**25. [Subset.java](https://github.com/awangdev/LintCode/blob/master/Java/Subset.java)**      Level: Medium      Tags: [Array, BFS, Backtracking, Bit Manipulation, DFS]
      
time: O(2^n)
space: O(2^n)

给一串unique integers, 找到所有可能的subset. result里面不能有重复.

#### DFS
- dfs的两种路子: 1. pick&&skip dfs, 2. for loop dfs
- 1. pick&&skip dfs: 取或者不取 + backtracking. 当level/index到底，return 一个list. Bottom-up, reach底部, 才生产第一个solution.
- 2. for loop dfs: for loop + backtracking. 记得：做subset的时候, 每个dfs recursive call是一种独特可能，先加进rst.  top-bottom: 有一个solution, 就先加上.
- Time&&space: subset means independent choice of either pick&&not pick. You pick n times: `O(2^n)`, 3ms

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
- Regular BFS, 注意考虑如果让one level to generate next level
- 1. 用queue来存每一次的candidate indexes
- 2. 每一次打开一层candiates, add them all to result
- 3. 并且用每一轮的candidates, populate next level, back into queue.
- should be same O(2^n), but actual run time 7ms, slower





---

**26. [Subsets II.java](https://github.com/awangdev/LintCode/blob/master/Java/Subsets%20II.java)**      Level: Medium      Tags: [Array, BFS, Backtracking, DFS]
      
time: O(2^n)
sapce: O(2^n)

给一串integers(may have duplicates), 找到所有可能的subset. result里面不能有重复.

#### DFS
- DFS, 找准需要pass along的几个数据结构. 先`sort input`, 然后DFS
- Using for loop approach: 每个dfs call是一种可能性，直接add into result.     
- 为了除去duplicated result, skip used item at current level: `if (i > depth && nums[i] == nums[i - 1]) continue;`
- sort O(nlogn), subset: O(2^n)
- space O(2^n), save results

#### Simplier BFS on selected candidates
- use rst = `set<List<String>>` to cache candidates, starting from []
- add one num at a time; use `rst.contains()` to O(1) check candidates
- save every result rst.

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

**27. [Combination Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      
time: O(n!)
space: O(n!)

给一串数字candidates (no duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 可以用任意多次.


#### DFS, Backtracking
- 考虑input: 没有duplicate, 不需要sort
- 考虑重复使用的规则: 可以重复使用, 那么for loop里面dfs的时候, 使用curr index i
- the result is trivial, save success list into result.

##### Time complexity for Combination (reuse-candidate)
- at each level dfs, we have the index as starting point: 
- if we are at `index=0, we can have n child dfs() options via for loop`; 
- if at `index=1, we will have (n-1) dfs options via for loop`. 
- Consider it as the pick/not-pick proble, where the difference is you can pick `x` times at each index rather than only 2 times. 
- Overall, we will multiply the # of possibilities: n * (n - 1) * (n - 2) ... * 1 = n! => `O(n!)`

##### Combination DFS 思想
- 在每个index上面都要面临: pick/not pick的选择
- 每次pick以后, 就生成一条新的routine, from this index
- 下一个level的dfs从这个index开始, 对后面(或者当下/if allow index reuse) 进行同样的 pick/not pick 的选择
- 注意1: 每个level dfs 里面, for loop 里会有 end condition: 就不必要dfs下去了.
- 注意2: Backtracking在success case && dfs case 后都要做, 因为backtrack 是为了之前上一层dfs.




---

**28. [Combination Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20II.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      

给一串数字candidates (can have duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 只可以用一次.

#### DFS, Backtracking
- when the input has duplicates, and want to skip redundant items? 
- 1. sort. 2. in for loop, skip same neighbor.
- 考虑input: 有duplicate, 必须sort
- 考虑重复使用的规则: 不可以重复使用
- 1. for loop里面dfs的时候, 使用curr index + 1
- 2. for loop里面, 同一个level, 同一个数字, 不能重复使用: `(i > index && candidates[i] == candidates[i - 1]) continue`
- 因为在同一个level里面重复的数字在下一个dfs level里面是会被考虑到的, 这里必须skip (这个就记住吧)
- the result is trivial, save success list into result.

##### Time complexity
- Which one?
- Time: every level has 1 less element to choose, worst case is: cannot find any solution over all combinations: O(m!)
- Time: Same as `subsetII`, pick/not=pick an item as we go, no reuse of item. Worst case: all unique items in the set. O(2^n)




---

**29. [Combination Sum III.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20III.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      

给一个integer k, 和一个target n. 

从positive数字[1 ~ 9], 找到所有unique的 组合(combination) int[], size = k, 要求每个combination的和 = n.

(隐藏条件, 需要clarify): 同一个candidate integer [1 ~ 9], 只可以用一次.

#### DFS, Backtracking
- 跟Combination Sum I, II 没什么太大区别, 只不过, 一定要用k个数字, 也就是一个for loop里面的特别条件
- 考虑input: 没有重复数字 [1 ~ 9]
- 考虑candidate重复利用: 不可以重复利用, next level dfs 时候, curr index + 1
- the result is trivial, save success list into result.

##### Time Complexity
- Which one?
- worst case: tried all numbers and cannot find: O(m!), m = 9, all possible integers in [1~9]
- C(n,k), n choose k problem : `n! / (k! * (n-k)!)` => ends up being `O(min(n^k, n^(n-k)))`



---

**30. [Walls and Gates.java](https://github.com/awangdev/LintCode/blob/master/Java/Walls%20and%20Gates.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS]
      

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
- TODO? why BFS better?



---

