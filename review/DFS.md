 
 
 
## DFS (85)
**0. [Subtree of Another Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Subtree%20of%20Another%20Tree.java)**      Level: Easy      Tags: [DFS, Divide and Conquer, Tree]
      



---

**1. [Word Break II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Break%20II.java)**      Level: Hard      Tags: [Backtracking, DFS, DP, Memoization]
      

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

**2. [Nested List Weight Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Nested%20List%20Weight%20Sum.java)**      Level: Easy      Tags: [BFS, DFS]
      

给一串integers, list里面可能有nest list. 算总的sum. 规则, 如果是nested list, 每深一个depth, sum要乘以depth.

#### DFS
- New interface to understand: object contains integer or object
- Visit all && sum, consider dfs.
- bottom->up is easier: pick nested object and execute dfs, which returns sum of it, add with (level value * weight).
- 简单的处理nested structure, dfs增加depth.
- time: visit all nodes eventually, O(n), space O(n)
- Note1: not multiplying on overall level sum. Only multiply level with single value at this level.
- Note2:top->bottom is not necessary: there is not need of passing added object into next level.

#### BFS
- bfs, queue, 处理queue.size().
- use a level variable to track levels



---

**3. [Same Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Same%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      

给两个 binary tree, 看两个tree是否identical.

#### DFS
- DFS. 确定leaf条件, && with all dfs(sub1, sub2).
- 这里无论如何都要走过所有的node, 所以dfs更加合适, 好写.

#### BFS
- 两个queue存每个tree的所有current level node. Check equality, check queue size.
- Populate next level by nodes at current level.



---

**4. [Convert Sorted Array to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [DFS, Divide and Conquer, Tree]
      

如题, build balanced BST from sorted array

#### DFS
- Binary Search Tree特点: 左边的node都比右边的node小. 
- height balance, subtree height 相差<1, 必须左右sub tree均分. 做DFS(num, start, end)
- 在每一个level, 找到中间点, 然后分割2半, 继续dfs
- Divide and Conquer
- time/space: O(n), visit all nodes, no redundant visits.



---

**5. [Construct Binary Tree from Inorder and Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Preorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Hash Table, Tree]
      

如题

#### DFS
- 和Construct from Inorder && Postorder 想法一样。
- 写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。
- 跟Convert Sorted Array to Binary Tree类似, 找到对应的index, 然后:
- node.left = dfs(...), node.right = dfs(...)
- Divide and Conquer
- optimize on finding mid node: given value, find mid of inorder. Instead of searching linearly, just store map <value -> index>, O(1)
- sapce: O(n), time: O(n) access



---

**6. [Balanced Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Balanced%20Binary%20Tree.java)**      Level: Medium      Tags: [DFS, Tree]
      

给一个binary tree, 看是否是height-balanced

#### DFS
- DFS using depth marker: 每个depth都存一下。然后如果有不符合条件的，存为-1.
- 一旦有 <0 或者差值大于1， 就全部返回Integer.MIN_VALUE. Integer.MIN_VALUE比较极端, 确保结果的正确性。
- 最后比较返回结果是不是<0. 是<0，那就false.
- Traverse 整个tree, O(n)


#### DFS, maxDepth function
- Same concept as in 1, but cost more traversal efforts.



---

**7. [Populating Next Right Pointers in Each Node.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      

给一个特殊的binary tree, treeNode里面有一个 next pointer.

写一个function, 把所有node都更同level的node 连在一起. 最右边的node.next = NULL

#### DFS + Divide and Conquer
- 题目要求DFS. 想清楚了如何在DFS level把几种情况都考虑了, 写起来很简单. NOT BFS, because requires O(1) space
- 对于一个root来说, 只有几个点可以顾忌到: root.left, root.right, root.next. 
- 想办法把这三个方向的点, 能连起来的都连起来:
- 1. `node.left.next = node.right`
- 2. If `node.next != null`, link `node.right.next = node.next.left`;
- 然后在dfs(root.left), dfs(root.right)
- Time: visit && connect all nodes, O(n)

#### BFS
- 不和题意，用了queue space，与Input成正比。太大。
- BFS over Tree。 用Queue 和 queue.size()，老规矩。   
- process每层queue时, 注意把next pointer加上去就好. 



---

**8. [Validate Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Validate%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Tree]
      

如题, 验证是否是BST.

#### DFS
- 查看每个parent-child关系: leftchild < root < rightChild
- 方法: 把root.val 传下来作为 max 或者 min, 然后检查children
- min/max 非常重要! 不然无法判断跟更上一些层的关系

##### Note: 
- min/max需要时long type. 
- 如果题目真的给node.val = Integer.MAX_VALUE, 我们需要能够与之比较, long就可以.



---

**9. [Convert Sorted List to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20List%20to%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Linked List]
      

如题, 把一个sorted singly linked list 转换成一个 height balanced BST

#### DFS
- Divide and Conquer   
- 找到mid node
- 然后分割两半, 分别dfs做各自两个subtree: node.left,node.right
- 用长度来定位mid, 每次找中间点做root, 然后前半段, 后半段分别dfs with length.
- 用快慢pointer 找到mid. Better: 不用traverse entire linked list

#### Details
- slowPointer = node;
- fastPointer = node.next;
- 然后把root = mid.next     
- 然后开始sortedListToBST(mid.next.next); //后半段    
- mid.next = null;//非常重要，要把后面拍过序的断掉    
- sortedListToBST(head); //从头开始的前半段     
- 最后root.left, root.right merge一下。   



---

**10. [Flatten Binary Tree to Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Flatten%20Binary%20Tree%20to%20Linked%20List.java)**      Level: Medium      Tags: [Binary Tree, DFS]
      

给一个binary tree, 把tree做成 linked list的形式, in-place.

#### DFS
- 分析题意后, 按照题意: Flatten the tree, no extra space.
1. reserve right child
2. DFS flatten部分
3. 移花接木
4. flatten 剩下的.

##### 注意
- 顺序一定要清楚, 不能写错, 写几个example可以看出来
- 移动的那些node, 要把node.left = null, 清扫干净



---

**11. [Binary Tree Paths.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Paths.java)**      Level: Easy      Tags: [Backtracking, Binary Tree, DFS]
      

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

**12. [Clone Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Clone%20Graph.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Hash Table]
      

给一个graph node, 每个node有list of neighbors. 复制整个graph, return new head node.

#### 思想
- Use HashMap to mark cloned nodes.    
- 先能复制多少Node复制多少. 然后把neighbor 加上
- Use `map<oldNode, newNode>` to mark visited

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

**13. [Number of Islands.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Islands.java)**      Level: Medium      Tags: [BFS, DFS, Matrix DFS, Union Find]
      

给一个2Dmatrix, 里面是1和0, 找#of island.

#### DFS
- More or less like a graph problem: visit all nodes connected with the starting node.
- top level 有一个 double for loop, 查看每一个点.
- 每当遇到1, count+1, 然后DFS helper function 把每个跟这个当下island 相关的都Mark成 '0'
- 这样确保每个visited 过得island都被清扫干净
- O(mn) time, visit all nodes

#### Union Find
- 可以用union-find， 就像Number of island II 一样.
- 只不过这个不Return list, 而只是# of islands
- Union Find is independent from the problem: it models the union status of integers.
- 记住UnionFind的模板和几个变化(Connecting Graph I, II, III), 最后归总的代码写起来就比较简单.



---

**14. [Surrounded Regions.java](https://github.com/awangdev/LintCode/blob/master/Java/Surrounded%20Regions.java)**      Level: Medium      Tags: [BFS, DFS, Matrix DFS, Union Find]
      

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

**15. [Word Search II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search%20II.java)**      Level: Hard      Tags: [Backtracking, DFS, Trie]
      

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

**16. [Word Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search.java)**      Level: Medium      Tags: [Array, Backtracking, DFS]
      

#### DFS, Backtracking:
- 找到开头的字母, 然后recursively DFS 去把word串到底:
- 每到一个字母, 朝四个方向走, 之中一个true就可以.
- Note:每次到一个字母，mark一下'#'. 4个path recurse回来后，mark it back.
- time: O(n^2) * O(3^m)?  where m = word length

#### Note: other ways of marking visited:
- 用一个boolean visited[][]
- Use hash map, key = x@y




---

**17. [Decode String.java](https://github.com/awangdev/LintCode/blob/master/Java/Decode%20String.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Stack]
      

给一个expression string. 里面包括数字, 字母, 括号. 其中数字代表括号里面的内容重复几次.

括号里面可以是String, 也可能是expression.

目的: 把expression展开成一个正常的String.


#### Stack, Iteratively
- Process inner item first: last come, first serve, use stack.
- Record number globally and only use it when '[' is met.
- Stack存 [ ] 里面的内容, detect 括号开头结尾: 结尾时process inner string
- 有很多需要注意的细节才能做对:
- Stack<Object> 也可以用, 每个地方要注意 cast. 存进去的需要是Object: String, Integer
- 几个 type check: instanceof String, Character.isDigit(x), Integer.valueOf(int num)
- 出结果时候: `sb.insert(0, stack.pop())`


#### DFS
- Bottom->up: find deepest inner string first and expand from inside of `[ ]`
- 与Stack时需要考虑的一些function类似. 特别之处: **检查`[ ]`的结尾**
- 因为DFS时候, 括号里的substring会被保留着进入下一个level, 所以我们在base level要keep track of substring.
- 用int paren 来track 括号的开合, 当paren再次==0的时候 找到closure ']'
- 其他时候, 都要继续 append to substring




---

**18. [Find Peak Element II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element%20II.java)**      Level: Hard      Tags: [Binary Search, DFS, Divide and Conquer]
      

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

**19. [Longest Increasing Path in a Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Path%20in%20a%20Matrix.java)**      Level: Hard      Tags: [Coordinate DP, DFS, DP, Memoization, Topological Sort]
      

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

**20. [Course Schedule.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS, Graph, Topological Sort]
      

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

**21. [Course Schedule II.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule%20II.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Topological Sort]
      

- 一堆课用int[2] pair 来表示. [1, 0] 表示要上课1的话, 必须先把课0上了. 
- 每一个数字都是一个ndoe, 题目求这个最后排好的课的list
- 如果排不好, 就给个空就好
- input是 numOfCourses, 还有这个prerequisites [[]]
- 做法跟Course Schedule I 非常像, 可以参考.

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

**22. [Binary Tree Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Preorder%20Traversal.java)**      Level: Easy      Tags: [BFS, DFS, Stack, Tree]
      

#### Recursive
- 加root, left, then right. Obvious
- Divide and conquer
- 其实也不需要helper function

#### Iterative
- 先加root, 然后push上需要末尾process的在stack垫底(root.right), 然后push root.left
- Stack: push curr, push right, push left.   



---

**23. [House Robber III.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20III.java)**      Level: Medium      Tags: [DFS, DP, Status DP, Tree]
      

Houses被arrange成了binary tree, 规则还是一样, 连续相连的房子不能同时抄.

求Binary Tree neighbor max 能抄多少.

#### DFS
- 判断当下的node是否被采用，用一个boolean来表示. 
- 如果curr node被采用，那么下面的child一定不能被采用.
- 如果curr node不被采用，那么下面的children有可能被采用，但也可能略过，所以这里用Math.max() 比较一下两种可能有的dfs结果。
- dfs重复计算:每个root都有4种dive in的可能性, 假设level高度是h, 那么时间O(4^(h)), where h = logN, 也就是O(n^2)

#### DP, DFS
- 并不是单纯的DP, 是在发现DFS很费劲后, 想能不能代替一些重复计算?
- 基本思想是dfs解法一致: 取root找最大值, 或者不取root找最大值
- 在root上DFS, 不在dfs进入前分叉; 每一个level按照状态来存相应的值: dp[0] root not picked, dp[1] root picked.
- Optimization: DP里面, 一口气找leftDP[]会dfs到最底层, 然后自下向上做计算
- 这个过程里面, 因为没有在外面给dfs()分叉, 计算就不会重叠, 再也不用回去visit most-left-leaf了, 算过一遍就完事.
- 然而, 普通没有dp的dfs, 在算完visited的情况下的dfs, 还要重新dfs一遍!visited的情况.
- Space O(h), time O(n), 或者说是O(2^h), where h = log(n)

#### DP 特点
- 不为状态而分叉dfs
- 把不同状态model成dp
- 每一个dfs都return一个based on status的 dp array.
- 等于一次性dfs计算到底, 然后back track, 计算顶部的每一层.
- DP 并不一定要是以n为base的. 也可以是局部的去memorize状态->value.



---

**24. [Expression Evaluation.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Evaluation.java)**      Level: Hard      Tags: [Binary Tree, DFS, Expression Tree, Minimum Binary Tree, Stack]
      

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

**25. [Convert Expression to Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Expression%20to%20Polish%20Notation.java)**      Level: Hard      Tags: [Binary Tree, DFS, Expression Tree, Stack]
      

给一串字符, 用来表示公式expression. 把这个expression转换成 Polish Notation (PN).

#### Expression Tree
- Expression Tree: Minimum Binary Tree (https://lintcode.com/en/problem/expression-tree-build/)
- 根据题意做出Expression Tree出来以后: 来个Pre-order-traversal 就能记录下 Polish Notation
- 本题没有给'ExpressionTreeNode', 所以把TreeNode就当做成我们需要的node, 里面扩展成有left/right child就可以了.
- Note: label需要是String. 虽然 Operator是长度为1的char, 但是数字可为多位



---

**26. [Convert Expression to Reverse Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Expression%20to%20Reverse%20Polish%20Notation.java)**      Level: Hard      Tags: [Binary Tree, DFS, Expression Tree, Stack]
      

给一串字符, 用来表示公式expression. 把这个expression转换成 Reverse Polish Notation (RPN).

#### Expression Tree
- Expression Tree: Minimum Binary Tree (https://lintcode.com/en/problem/expression-tree-build/)
- 根据题意做出Expression Tree出来以后: 来个Post-order-traversal 就能记录下 Reverse Polish Notation
- 本题没有给'ExpressionTreeNode', 所以把TreeNode就当做成我们需要的node, 里面扩展成有left/right child就可以了.



---

**27. [Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray.java)**      Level: Easy      Tags: [Array, DFS, DP, Divide and Conquer, PreSum, Sequence DP, Subarray]
      
time: O(n)
space: O(n), O(1) rolling array

给一串数组, unsorted, can have negative/positive num. 找数组中间 subarray 数字之和的最大值

#### Sequence DP
- dp[i]: 前i个element,包括 last element (i-1), 可能组成的 subarray 的最大sum.
- init: dp = int[n + 1], dp[0]: first 0 items, does not have any sum
- 因为continous sequence, 所以不满足条件的时候, 会断. That is: need to take curr num, regardless => can drop prev max in dp[i]
- track overall max 
- init dp[0] = 0; max = MIN_VALUE 因为有负数
- Time, space O(n)
- Rolling array, space O(1)

#### Divide and Conquer, DFS
- 找一个mid piont, 考虑3种情况: 只要左边, 只要右边, cross-mid
- left/rigth 的case, 直接 dfs
- corss-mid case: continuous sum max from left + continous sum max from right + mid
- continuous sum max from one direction:


---

**28. [Invert Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Invert%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

#### DFS
- 简单处理swap
- recursively swap children

#### BFS
- BFS with Queue
- 每次process一个node, swap children; 然后把child加进queue里面
- 直到queue process完



---

**29. [Maximum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      

给一个binary tree, 找最深depth

#### DFS
- 这里要走过所有的node, 所以dfs非常合适
- Divide and conquer. 
- 维持一个最大值: Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
- 注意check root == null

#### Note
- BFS is doable as well, but a bit more code to write: tracks largest level we reach



---

**30. [Minimum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

#### DFS
- Divide and Conquery一个最小值. 
- 注意处理Leaf的null: null leaf 出现的时候, 就忽略这个leaf, 直接return算有leaf
- 另一种count的方法: 用Integer.MAX_VALUE代替 null leaf，这样可以避免错误counting. (不能直接recursive)
- 这个无论如何都要走所有node, 所以dfs应该比较适合.

#### BFS
- Shortest path; minimum depth: 想到BFS, check level by level, BFS更能确保更快找到结果
- depth definition: reach to a leaf node, where node.left == null && node.right == null
- BFS using queue, track level.



---

**31. [Symmetric Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Symmetric%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

检查tree是否symmetric

注意Symmetric Binary Tree的例子和定义: 是镜面一样的对称. 并不是说左右两个sub-tree相等。

#### DFS
- Recursively check symmetrically相对应的Node.  
- 每个node的children都和镜面另外一边相对的node的children刚好成镜面反射位置。

#### Stack
- stack1: 左手边sub-tree先加left, 再加right child; 
- stack2: 右手边sub-tree先加right child, 再加left child。   
- process时，若symmetric，所有stack里面出来的node会一一对应。



---

**32. [Tweaked Identical Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Tweaked%20Identical%20Binary%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      

检查binary tree是否 identical. 

特点: subtree如果是有旋转的, 只要tree node value相等, 就可以算是identical

#### DFS
- 在DFS的基础上, 比对左左,左右,右左,右右



---

**33. [Merge Two Binary Trees.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Two%20Binary%20Trees.java)**      Level: Easy      Tags: [DFS, Tree]
      

#### DFS
- 基础binary tree traversal. 注意对null child的判断



---

**34. [Subtree.java](https://github.com/awangdev/LintCode/blob/master/Java/Subtree.java)**      Level: Easy      Tags: [DFS, Tree]
      

给一个binary tree s, 和一个binary tree t, 检查t是不是s的subtree.

#### DFS
- 跟 identical binary tree的写法很像
- 只有 current s.val = t.val 的时候才需要compare same tree.
- 其他情况, 继续recursively isSubtree
- 注意：即使找到T1 == T2, 但很可能只是数字相同（这里不是binary search tree!!）, 而children不同
- 所以同时要继续recursively isSubtree(T1.left, T2) ...etc.



---

**35. [Lowest Common Ancestor of a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree.java)**      Level: Medium      Tags: [DFS, Tree]
      

给一个Binary Tree root, 以及两个node p, q. 找 p 和 q 的 lowest common ancestor

#### DFS
- 因为是 binary tree, 所以直接盲目搜索搜索path不efficient, use extra space and waste time
- 巧用DFS来找每一个node的common ancestor. 
- Need the assumption: 1. unique nodes across tree; 2. must have a solution
- 当root == null或者 p q 任何一个在findLCA底部被找到了(root== A || root == B)，那么就return 这个root.     
- 三种情况:
- 1. A,B都找到，那么这个level的node就是其中一层的ancestor: 其实，最先recursively return到的那个，就是最底的LCA parent.   
- 2. A 或者 B 找到，那就还没有公共parent, return 非null得那个。   
- 3. A B 都null, 那就找错了没有呗, return null
- Worst case, visit all nodes to find p q at last level, last two leaves: time/space O(n)



---

**36. [Lowest Common Ancestor of a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor%20of%20a%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DFS, Tree]
      

给 binary search tree root, q node, p node. 找到p q 的lowest common ancestor

#### Find path with BST
- 利用 BST 的性质，可以直接搜到target node，而做成两个长度不一定相等的list
- 然后很简单找到LCA 
- O(n) space, O(logn) time

#### DFS
- Brutly寻找p和q的common ancestor, 然后recursively drive left/right
- 非常巧妙, 但是也比较局限; 稍微变条件, 就很难recursive.
- 几种情况:
- 1. one of p, q 在leaf, 那么此时的root其实就是lowest common ancestor
- 2. 如果p, q 在root的左右两边, 这就是分叉口, 那么root就是lowest common ancestor
- 3. 如果p,q 在root的同一边 (左,右), 那么继续dfs
- O(1) extra space, O(logn) time



---

**37. [Binary Tree Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Level%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

如题.

#### BFS
- 最普通,Non-recursive: BFS, queue, 用个queue.size()来end for loop:换行。   
- 或者用两个queue. 当常规queue empty，把backup queue贴上去

#### DFS
- 每个level都应该有个ArrayList. 那么用一个int level来查看：是否每一层都有了相应的ArrayList。   
- 如果没有，就加上一层。    
- 之后每次都通过DFS在相应的level上面加数字。




---

**38. [Binary Tree Longest Consecutive Sequence II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence%20II.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Double Recursive, Tree]
      

找到binary tree 里的最长 consecutive sequence. Sequence可以递增递减, Sequence顺序可以回溯parent.

#### DFS, Divide and Conquer
- Similar to Binary Tree Longest Consecutive Sequence I
- 只不过可以递增递减, 还有连接上parent的方向.
- 对于任何一个节点, 都可能: 
- 1. 自己跟两个child链接, 成为一个sequence
- 2. 左边孩子, 右边孩子各自是一个consecutive sequence, 但是不跟root相连
- main function 一开始就divide成这三份, 然后dfs
- dfs take diff == 1, diff == -1, 来做递增递减的校对.
- dfs rules:
- 1. if node == null, leaf depth = 0
- 2. if not consecutive, reset the depth = 0 (same for both left child, and right child)
- 3. compare the leftDepth && rightDepth to find the maximum
- 4. diff is the same in the same dfs loop to maintain consistant increase/decrease

##### 注意
- dfs的结果很可能是0, 如果没有任何结果, 那么上一层的caller depth = dfs() + 1 = 1
- 那么回归到root, dfs的结果很可能就是1.
- 可能会问: 那么在tree里面的partial sequence (不连接到root)的被忽略了?
- 这里 `longestConsecutive(root.left)` 就很重要了
- 这一步特地忽略掉了root, 然后走下去一层: 因为是recursive, 所以还会继续divde && conquer
- 最后, 任何一层的孩子都会被照顾到.

##### Double Recursive functions
- 用两种recursive的方式handle skip root node的情况
- Recursive using dfs(), basically build child + parent
- Recursive using main function, but with value of child node: skipping root



---

**39. [Binary Tree Maximum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum.java)**      Level: Hard      Tags: [DFS, DP, Tree, Tree DP]
      

找max path sum,  可以从任意treeNode 到任意 treeNode.

#### Kinda, Tree DP
- 两个情况: 1. combo sum: left+right+root; 2. single path sum
- Note1: the path needs to be continuous, curr node cannot be skipped
- Note2: what about I want to skip curr node: handled by lower level of dfs(), where child branch max was compared.
- Note3: skip left/right child branch sum, by comparing with 0. 小于0的, 没必要记录

#### DP的思想
- tree给我们2条branch, 每条branch就类似于 dp[i - 1], 这里类似于dp[left], dp[right] 这样
- 找到 dp[left], dp[right] 以后, 跟 curr node结合. 
- 因为是找max sum, 并且可以skip nodes, 所以需要全局变量max
- 每次dfs() return的一定是可以继续 `continuously link 的 path`, 所以return `one single path sum + curr value`.

#### DFS, PathSum object
- that just solves everything


---

**40. [Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum.java)**      Level: Easy      Tags: [DFS, Tree]
      

给一个inputSum, 然后dfs, 找到是否有一条path, 得出的path sum 跟 inputSum 一样.

#### DFS
- 确定好结尾的条件: is leaf && val == sum
- 每一层减掉node.val, 然后dfs.
- 写一写: root == null => false 对parent nodes的影响. 这里发现没影响, 所以可以简化成用1个functionDFS.




---

**41. [Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20II.java)**      Level: Easy      Tags: [Backtracking, DFS, Tree]
      

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

**42. [Path Sum III.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20III.java)**      Level: Easy      Tags: [DFS, Double Recursive, Tree]
      

count所有存在的 path sum == target sum. 可以从任意点开始. 但是只能parent -> child .

#### DFS
- 对所给的input sum 做减法, 知道 sum 达到一个目标值截止
- 因为可以从任意点开始, 所以当sum达标时候, 需要继续recursive, 从而找到所有情况 (有正负数, sum可能继续增加/减少)
- 经典的 helper dfs recursive + self recursive
- 1. helper dfs recursive 处理包括root的情况
- 2. self recursive 来引领  skip root的情况.

#### 特点
- 与 `Binary Tree Longest Consecutive Sequence II` 在recursive的做法上很相似: 
- 利用dfs做包括root的recursive computation
- 利用这个function自己, 做不包括root的recursive computation



---

**43. [Combinations.java](https://github.com/awangdev/LintCode/blob/master/Java/Combinations.java)**      Level: Medium      Tags: [Backtracking, Combination, DFS]
      

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

#### DFS, Backtracking
- for loop, recursive (dfs).
- 每个item用一次, 下一个level dfs(index + 1)
- Combination DFS. 画个图想想. 每次从1~n里面pick一个数字i
- 因为下一层不能重新回去 [0~i]选，所以下一层recursive要从i+1开始选。



---

**44. [Binary Tree Right Side View.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Right%20Side%20View.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

给一个binary tree, 从右边看过来, return all visible nodes

#### BFS
- 最右:即level traversal每一行的最末尾.   
- BFS, queue 来存每一行的内容, save end node into list

#### DFS
- Use Map<Level, Integer> 来存每一个level的结果
- dfs function 里, 如果 input depth 不存在, 就add to map.
- dfs function 里面先: dfs(node.right), 然后 dfs(node.left)
- 由于always depth search on right side, 所以map会被right branch populate; 然后才是 leftChild.right




---

**45. [Binary Tree Maximum Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum%20II.java)**      Level: Medium      Tags: [DFS, Tree]
      

找到从max path sum from root. 条件: 至少有一个node.

#### DFS
- 比Binary Tree Maximum Path Sum I 简单许多. 因为条件给的更多：at least 1 node + have to start from root
- root一定用到
- 3种情况: curr node, curr+left, curr+right
- 因为一定包括root, 说以从 `dfs(root, sum=0)` 开始, 每个level先加root, sum += root.val



---

**46. [Binary Tree Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      

找到binary tree 里的最长 consecutive sequence.

#### DFS
- Divide and Conquer. dfs
- 分开 看左边/右边
- 如果左边满足连续递增的规则, dfs (depth + 1), 如果不满足规则, dfs(depth = 1)
- 右边也是一样
- 对结果跟max作比较, return



---

**47. [Number of Connected Components in an Undirected Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      

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

**48. [Serilization and Deserialization Of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Serilization%20and%20Deserialization%20Of%20Binary%20Tree.java)**      Level: Hard      Tags: [BFS, DFS, Deque, Design, Divide and Conquer, Tree]
      

Serialize and Deserialize Binary Tree

#### DFS, Divide and Conquer
##### Serilize
- Divide and conquer: Pre-order traversal to link all nodes together
- build the string data: use '#' to represent null child. 
- the preorder string, can be parsed apart by `split(',')`

##### Deserialize
- Use a list (here we use `Deque` for the ease of get/remove in 1 function: remove()) 
- to take all parts of the parsed sring data: dfs on the Deque
- first node from the list is always the head
- '#' will be a null child: this should break dfs
- Deque is a global variable, so dfs(right child) will happen after dfs(left child) completes

#### DFS, Recursive [previous note]
- serilize: divide and conquer, pre-order traversal
- deserialize: 稍微复杂, 用dfs. 每次要truncate input string: 
- 一直dfs找left child, 接着right child until leaf is found.
- 用一个StringBuffer来hold string, 因为string 是primitive, 我们这里需要pass reference

#### BFS, Non-recursive
- using queue. 想法直观。level-order traversal. save到一个string里面就好。
- 遇到null child, 不是直接忽略, 而是assign一个Integer.MIN_VALUE, 然后 mark as '#'
- BFS需要track queue size, 每一次只process特定数量的nodes



---

**49. [Segment Tree Query.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Segment Tree]
      

给了segment Tree, node里面有Max value, 找[start,end]里面的max

#### Segment Tree, Divide and Conquer
- 根据[start,end]跟 mid of (root.start, root.end) 做比较:
- 简单的2个case: [start,end]全在mid左, 或者[start, end]全在mid右
- 稍微复杂的3rd case: [start, end]包含了mid, 那么就break into 2 queries
- [start, node.left.end], [node.right.start, end]



---

**50. [Segment Tree Modify.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Modify.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Segment Tree]
      

给一个segmentTree, node里面存max. 写一个modify function: modify(node, index, value).

#### Segment Tree, Divide and Conquer
- Recursively 在segment tree里面找index, update it with value.   
- 每个iteration，很可能（要么左手，要么右手）max就变了。所以每次都left.max and right.max compare一下
- 最后轮回到头顶，头顶一下包括头顶，就全部都是max了



---

**51. [Segment Tree Query II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query%20II.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Segment Tree]
      

#### Segment Tree
- 和 Segment Tree Query I 以及其他Segment Tree类似: 这个SegmentTreeNode return count of elements in range
- 这个题目考了validate input source：input 的start,end可能超出root[start,end]。   
- 那么第一步就要先clear一下: 1. 完全不在range就return 0. 2. 有range重合就规整到root的range.




---

**52. [Fast Power.java](https://github.com/awangdev/LintCode/blob/master/Java/Fast%20Power.java)**      Level: Medium      Tags: [DFS, Divide and Conquer]
      

如题: Calculate the a^n % b where a, b and n are all 32bit integers.

#### Divide and Conquer
- a^n可以被拆解成(a*a*a*a....*a)， 是乘机形式，而%是可以把每一项都mod一下的。所以就拆开来take mod.
- 这里用个二分的方法，recursively二分下去，直到n/2为0或者1，然后分别对待. 
- 注意1: 二分后要conquer，乘积可能大于Integer.MAX_VALUE, 所以用个long.
- 注意2: 要处理n%2==1的情况，二分时候自动省掉了一份 a，要乘一下。




---

**53. [Find the Connected Component in the Undirected Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Connected%20Component%20in%20the%20Undirected%20Graph.java)**      Level: Medium      Tags: [BFS, DFS]
      

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

**54. [Kth Smallest Element in a BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Element%20in%20a%20BST.java)**      Level: Medium      Tags: [BST, DFS, Stack, Tree]
      

#### Iterative + stack: inorder traversal
- 很容想到Inorder-binary-search-tree Traversal
- Iterative 稍微难想点：先把最左边的add， pop() stack， 加上右边（如果存在）； 下一个轮回，如果又左孩子，又是一顿加。

#### Recursive + DFS
- 然后稍微优化一下，确保rst.size() == k 时候，就可以return了
- check leaf => dfs left => add root => dfs right



---

**55. [Permutations.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutations.java)**      Level: Medium      Tags: [Backtracking, DFS, Permutation]
      

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

**56. [Restore IP Addresses.java](https://github.com/awangdev/LintCode/blob/master/Java/Restore%20IP%20Addresses.java)**      Level: Medium      Tags: [Backtracking, DFS, String]
      

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

**57. [Topological Sorting.java](https://github.com/awangdev/LintCode/blob/master/Java/Topological%20Sorting.java)**      Level: Medium      Tags: [BFS, DFS, Topological Sort]
      

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

**58. [Median of two Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Median%20of%20two%20Sorted%20Arrays.java)**      Level: Hard      Tags: [Array, Binary Search, DFS, Divide and Conquer]
      

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

#### Binary Search
TODO:



---

**59. [Expression Add Operators.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Add%20Operators.java)**      Level: Hard      Tags: [Backtracking, DFS, Divide and Conquer, String]
      

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

**60. [Construct Binary Tree from Inorder and Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Tree]
      

#### DFS, Divide and Conquer
- 写个Inorder和Postorder的例子。利用他们分left/right subtree的规律解题。
- Postorder array 的末尾， 就是当下层的root.   
- 在Inorder array 里面找到这个root,就刚好把左右两边分割成left/right tree。
- 这题比较tricky地用了一个helper做recursive。 特别要注意处理index的变化, precisely考虑开头结尾
- runtime: O(n), visit && build all nodes

#### Improvement
- `findMid(arr)` can be replaced with a map<value, index>, no need execute O(n) search at runtime



---

**61. [Generate Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Generate%20Parentheses.java)**      Level: Medium      Tags: [Backtracking, DFS, Sequence DFS, String]
      

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

**62. [Strobogrammatic Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Strobogrammatic%20Number%20II.java)**      Level: Medium      Tags: [DFS, Math, Sequence DFS]
      

TODO: 
1. use list, iterative? keep candidates and populating
2. clean up the dfs code, a bit messy
3. edge case of "0001000" is invalid, right?

#### DFS
- find all solutions, DFS will be easier to write than iterative/BFS
- when n = 1, there can be list of candidates at bottom of the tree, so bottom->up is better
- bottom->up, dfs till leaf level, and return candidates.
- each level, pair with all the candidates
- 其实就是剥皮，一层一层，是一个central-depth-first的，钻到底时候，return n=1,或者n=2的case，然后开始backtracking。
- 难的case先不handle.到底之后来一次overall scan.
- every level have 5 choices of digital pairs to add on sides. Need to do for n-2 times. 
- Time complexity: O(5^n)



---

**63. [Alien Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/Alien%20Dictionary.java)**      Level: Hard      Tags: [BFS, Backtracking, DFS, Graph, Topological Sort]
      

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

**64. [Flip Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Flip%20Game%20II.java)**      Level: Medium      Tags: [Backtracking, DFS, DP]
      

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

**65. [Max Area of Island.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Area%20of%20Island.java)**      Level: Easy      Tags: [Array, DFS]
      

#### DFS
- 虽然Easy, 但用到DFS最基本的想法.
- 1. dive deep
- 2. mark VISITED
- 3. sum it up
- Time: worst O(mn), traverse all possible nodes

- 更要注意, 要从符合条件value==1的地方开始dfs.
- 对于什么island都没有的情况，area应该位0， 而不是Integer.MIN_VALUE, 问清楚考官那小伙, 别写顺手。



---

**66. [Palindrome Partitioning.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Partitioning.java)**      Level: Medium      Tags: [Backtracking, DFS]
      

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

**67. [Graph Valid Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Graph%20Valid%20Tree.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      

给一个数字n代表n nodes, marked from 1 ~ n, 和一串undirected edge int[][]. 

检查这些edge是否能合成一个 valid tree

#### Union Find
- 复习Union-Find的另外一个种形式, track union size: if tree, means no cycle, so eventually union size should == 1
- 题目类型：查找2个元素是不是在一个union里面。如果不在，false. 如果在，那就合并成一个set,共享parent.   
- 存储的关键都是：元素相对的index上存着他的root parent.    
- 注意: 结尾要检查, 是否只剩下1个union: Tree必须连接到所有给出的node.
- 另一个union-find, 用hashmap的:
- http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/

#### DFS
- Create adjacent list graph: Map<Integer, List<Integer>>
- 检查: 
- 1. 是否有cycle using dfs, check boolean[] visited
- 2. 是否所有的node全部链接起来: validate if all edge connected: # of visited node should match graph size

#### BFS
- (还没做, 可以写一写)
- 也是检查: 1. 是否有cycle, 2. 是否所有的node全部链接起来



---

**68. [Recover Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Recover%20Binary%20Search%20Tree.java)**      Level: Hard      Tags: [BST, DFS, Tree]
      

BST里面有2个node misplace, 要归为. 要求: O(1) extra space

#### Observation
- BST inorder traversal should give small -> large sequence
- misplaced means: a **large**->small item would occur, and later a large>**small** would occur. 
- The first large && second small item are the 2 candidates.

#### dfs, O(1) extra space
- traverse, and take note of the candidate
- at the end, swap value of the 2 candidates

#### O(n) space
- inorder traversal the nodes and save in array, find the 2 items misplanced and swap them
- But O(n) space should not be allowed




---

**69. [Longest Palindromic Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Palindromic%20Subsequence.java)**      Level: Medium      Tags: [DFS, DP, Interval DP, Memoization]
      

给一个string s, 找最长的sub-sequence which is also palindrome.

注意！subsequence并不是substring, 是可以skip letter / non-continuous character sequence
    
#### Interval DP
- 用[i][j]表示区间的首尾
- 考虑3个情况: 砍头, 砍尾, 砍头并砍尾 (考虑首尾关系)
- Iteration一定是以i ~ j 之间的len来看的. 
- len = j - i + 1; 那么反推, 如果len已知, j = len + i -1;
- 注意考虑len == 1, len == 2是的特殊情况.
- time/space: O(n^2)

#### Memoization
- 同样的方式model dp[i][j]: range [i, j] 之间的  max palindromic length
- 三种情况: 
- 1. 首尾match 继而 dfs[i+1, j-1]
- 2. 首尾不match,dfs[i+1,j] 
- 3. 首尾不match,dfs[i,j-1] 
- 注意: init dp[i][j]=-1, dfs的时候查dp[i][j] 是否算过
- more about dfs: bottom-up, first dive deep into dfs(i+1,j-1) till the base cases.
- time/space: O(n^2)
- prepare dp[n][n]: O(n^2); dfs: visit all combinations of [i,j]: O(n^2)




---

**70. [Triangles.java](https://github.com/awangdev/LintCode/blob/master/Java/Triangles.java)**      Level: Medium      Tags: [Array, Coordinate DP, DFS, DP, Memoization]
      

给一个list<list<Integer>> triangle, 细节原题. 找 min path sum from root.

#### DFS + Memoization
- 其实跟给一个2D matrix没有什么区别, 可以做dfs, memoization.
- initialize memo: pathSum[i][j] = MAX_VALUE; 计算过的path省略
- Bottom-top: 先dfs到最深的path, 然后逐步网上返回
- `OR 原理: min(pathA, pathB) + currNode`
- 浪费一点空间, pathSum[n][n]. space: O(n^2), where n = triangle height
- 时间:O(n^2). Visit all nodes once: 1 + 2 + 3 + .... n = n^2

#### DP
- 跟dfs的原理很像, `OR 原理: min(pathA, pathB) + currNode`
- init dp[n-1][j] = node values
- build from bottom -> top: dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
- 跟传统的coordinate dp有所不同, inner for loop 是需要计算 j <= i, 原因是triangle的性质.
- 空间: dp[n][n]. space: O(n^2)
- 时间:O(n^2). Visit all nodes once: 1 + 2 + 3 + .... n = n^2

#### DP + O(n) space 
- Based on the DP solution: the calculation always depend on `next row` for col at `j` and `j + 1`
- 既然只depend on next row, 可以用rolling array来处理: reduce to O(n) space.
- Further: 可以降维, 把第一维彻底去掉, 变成 dp[n]
- 同样是double for loop, 但是只在乎column changes: `dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);`  



---

**71. [Remove Invalid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Invalid%20Parentheses.java)**      Level: Review      Tags: [BFS, DFS, DP]
      

给一个string, 里面有括号和其他字符. 以最少刀 剪出 valid string, 求所有这样的string.

这个题目有多种解法, 最强就是O(n) space and time

#### DFS and reduce input string
- in dfs: remove the incorrect parentheses one at a time
- detect the incorrect parentheses by tracking/counting (similar to validation of the parentheses string): `if(count<0)`
- once detected, remove the char from middle of s, and dfs on the rest of the s that has not been tested yet.

##### Core concept: reverse test
- `if a parenthese string is valid, the reverse of it should also be valid`
- Test s with open='(', close=')' first; **reverse s**, and test it with open=')', close='('

##### Minor details
- only procceed to remove invalid parenthese when `count<0`, and also break && return dfs after the recursive calls.
- The above 2 facts eliminates all the redundant results.
- Reverse string before alternating open and close parentheses, so when returning final result, it will return the correct order.
- Open questions: how does it guarantee minimum removals?

##### Backtracking
- 如果用stringbuffer, 那么久不会每次create new string, 但是需要maintain这个string buffer, 就会backtracking

##### Complexity
- Seems to be O(n), but need to derive

#### BFS
TODO

#### DP



---

**72. [Path Sum IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20IV.java)**      Level: Medium      Tags: [DFS, Hash Table, Tree]
      

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

**73. [Subset.java](https://github.com/awangdev/LintCode/blob/master/Java/Subset.java)**      Level: Medium      Tags: [Array, BFS, Backtracking, Bit Manipulation, DFS]
      
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

**74. [Subsets II.java](https://github.com/awangdev/LintCode/blob/master/Java/Subsets%20II.java)**      Level: Medium      Tags: [Array, BFS, Backtracking, DFS]
      
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

**75. [Combination Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      
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

**76. [Combination Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20II.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      

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

**77. [Combination Sum III.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20III.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      

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

**78. [Smallest Subtree with all the Deepest Nodes.java](https://github.com/awangdev/LintCode/blob/master/Java/Smallest%20Subtree%20with%20all%20the%20Deepest%20Nodes.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      
time: O(n)
space: O(n)

给一个tree, 按照题意找最一个node满足: 
1. 这个node的subtree涵盖最深level的所有leaves. 
2. 这个node必须是能找到的最deep那个

条件2的需求是因为: root本身就是满足条件1的node, 还有很多Higher-level node也是如此, 所以要找那个deepest.


#### DFS on tree
- 分析题目, 思想是: 看到tree里面所有的leaves, 找到他们最deep的 common ancestor
- Maintain a map <Node, maxChildDepth>
- Recursively dfs: return deepest node that has all leaves by these comparisons:
- 1. If left,right child same depth, return root: they need common ancestor
- 2. If not same depth, return the one with larger depth
- 被传送去上一个level的, 永远都是subtree里面符合题意的: the node containing all leaf nodes
- Visit all nodes once O(n), space O(n)

#### BFS
- Find all leaves at deepest level
- Use map to track each node-parent
- Backtrack all nodes to find common ancestor



---

**79. [Convert Binary Search Tree to Sorted Doubly Linked List (in place).java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List%20(in%20place).java)**      Level: Medium      Tags: [BST, DFS, Linked List, Tree]
      
time: O(n)
space: O(1)

题目描述起来有点复杂, 简而言之: 把 BST 转换成一个 sorted doubly linked list.

#### Tree, In-order traversal
- 平时做过convert BST to sored list: 画一下就理解, 其实就是in-order traversal
- 只不过做的时候要小心地 doubly link them
- 理解之后就简单了, traverse all nodes,  DFS 好做: `left, curr, right`

##### 题目特殊特点
- 自始至终用了同一个 `Node {val, left, right}`, 而并不是开一个新的doubley linked list class
- extra space 的问题, 是因为它需要create new DoublyLinkedNode class: different from `Convert Binary Search Tree to Sorted Doubly Linked List (extra space)`
- 要求in-place: 不能重新create new node



---

**80. [Walls and Gates.java](https://github.com/awangdev/LintCode/blob/master/Java/Walls%20and%20Gates.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS]
      

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

**81. [Accounts Merge.java](https://github.com/awangdev/LintCode/blob/master/Java/Accounts%20Merge.java)**      Level: Medium      Tags: [DFS, Hash Table, Union Find]
      

给一串account in format `[[name, email1, email2, email3], [name2, email,..]]`. 

要求把所有account merge起来 (可能多个record记录了同一个人, by common email)

#### Union Find
- TODO


#### Hash Table solution, passed but very slow
- Definitely need iterate over accounts: merge them by email.
- Account object {name, list of email}
- map<email, account>
- 1. iterate over accounts
- 2. find if 'account' exist;  if does, add emails
- 3. if not, add account to list and to map. map all emails to accounts.
- output -> all accounts, and sort emails
- space O(mn): m row, n = emails
- time O(mn)



---

**82. [Target Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Target%20Sum.java)**      Level: Medium      Tags: [DFS, DP]
      

// 如何想到从中间initialize



---

**83. [Binary Tree Vertical Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Vertical%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Hash Table, Tree]
      
time: O(n)
space: O(n)

给一个Binary Tree, traverse所有node, 按照vertial order 排列成output: List<List> 

重点是: col里面有排序, 在higher level的排在前面; 如果node遇到collision在同一个位置: 根据他们的相对位置 先放left, 再放right

#### BFS
- 应该比较好想: naturally level-traverse all nodes, add node to appropriate col list
- Use min/max to track map keys, since the keys are continous

#### DFS
- 一开始很容易想到: enumerate一下, 先放curr node.val, 然后node.left.val, node.right.val. 非常简单
- 但是最简单的方法有错: assume所有left subtree都 排在right subtree. 但是: right subtree可能先有一个lower-left-branch,  appear in a column first.
- 所以还要preserve column list的order.
- 这里我们用了 `Map<col, Node>` 来track col, Node 里面用了 `node.level`来track level (其实再一个map也可以)
- 这样在结尾要sort,就会非常慢: Visit all nodes O(n) + O(logK) + O(KlogM), K = # of cols, M = # of items in col
- 应该也是可以optimize map keys的, 反正都是continuous key





---

**84. [Populating Next Right Pointers in Each Node II.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node%20II.java)**      Level: Medium      Tags: [DFS, Tree]
      
time: O(n)
space: O(1)

给一个binary tree, 用constant space link 所有所有node.next to same level next node.

#### DFS
- 用constant space 也就是不可以BFS, 但是mention了用dfs stack space没问题 (提示啊!)
- 1. link leftChild -> rightChild
- 2. resolve root.rightMost child -> first possible root.next.left/right child
- 3. dfs connect(rightChild), connect(leftChild)
- Each level should be fully linked from left side, so every reach to parent will have valid path or end.

#### Trick
- 1. 处理 nextNode -> next -> next ...的case: 找到第一个有child的next node才可以罢休. 这个case很容易miss
- 2. 我们的假设是, 上一个level的所有node都应该是linked, 那么在dfs时候, 就应该先connect(root.right). 右孩子的全处理完毕, 那么trick1才可以施行.



---

