 
 
 
## Medium (310)
**0. [Majority Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Number%20II.java)**      Level: Medium      Tags: [Enumeration, Greedy]
      
#### Array
- 分三份：a b c考虑
- 若a: countA++; 或b: countB++
- 或c:countA--, countB--
- 注意: 按照if statement的顺序, valA&&countA 比valB&&countB有优先性
- 最后出现的两个count>0的a和b,自然是potentially大于1/3的。其中有一个大于1/3.
- 比较countA和countB哪个大，就return哪一个。



---

**1. [Search a 2D Matrix II.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20a%202D%20Matrix%20II.java)**      Level: Medium      Tags: [Binary Search, Divide and Conquer]
      
给matrix, 每一行sorted, 每一列从上往下sorted, 找target是否存在

#### Binary Search
- 根据给定的性质, 其实点选的极端一点: x = 最下面的row, y = 当下一行里面最小的left position. 
- (x,y)在左下角
- 在此情况下, 只能往一个方向运行: 如果小于target, y++; 如果大于target, 那么只能x--
- 每次操作, 都是删掉一行, 或者一列, 再也不需要回头看
- `while (x >= 0 && y < col) {}` 确保不会跑脱
- 同样的方式: 可以从右上角(0, col - 1) 开始, 代码稍微改一改

#### Divide and Conquer?
- TODO



---

**2. [Missing Ranges.java](https://github.com/awangdev/LintCode/blob/master/Java/Missing%20Ranges.java)**      Level: Medium      Tags: [Array]
      
#### Basic Implementation
- O(n)
- 两个pointer， 每次计较prev和curr之间的部分.
- 然后prev = curr，向前移动一格
- TODO: check the edge case and make sure max/min of int are checked



---

**3. [Inorder Successor in BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Inorder%20Successor%20in%20BST.java)**      Level: Medium      Tags: [BST, Tree]
      
找 Inorder traversal规则里的下一个.

主要想法是考虑: 
    1. 如果 node.right == null, 找上一个unprocessed node alone the inorder traversal path
    2. 如果 node.right != null, successor 一定在这个node.right那个subtree里面
最后竟然可以简化成几行, 非常全面的BST问题: 有search, 有对inorder traversal的理解, 还有坑.

#### Short Recursive and Iterative without Stack
- Previous solution, we use stack to hold previous cached/unprocessed items: but do we need use catch to hold them?
- If moving left: `p.val < root.val`, then root (parent of left child) is a successor candidate, so save `rst = root`.
- If moving right or equal: `p.val >= root.val`, the successor has nothing to do with curr node, so just directly dive into root.right.
- Both iterative and recursive solution can be simplified as such.


#### Previous Iterative + stack
- Iteratively search
- Still need stack to store previously unprocessed items along the path

#### Previous Recursive + Stack
- 画inorder图，发现规律.每个node的后继node(successor)有几种情况:   
- 1. node.right 是个leaf到底了。那么就return.   
- 2. set rightNode = node.right， 但发现rightNode has a lot left children to leaf.   
- 3. 比如, node.right == null， 也就是node自己是leaf，要回头看山顶找Inorder traversal规则里的下一个。   
- 发现：其实就是每层都把路过的curr node放在stack里，最上面的，就是当下改return的那个successor:) Done.



---

**4. [Backpack VI.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20VI.java)**      Level: Medium      Tags: [Backpack DP, DP]
      
给一个数组nums, 全正数, 无重复数字; 找: # of 拼出m的方法.

nums 里的数字, 可以重复使用. 不同的order可以算作不同的拼法.

#### Backpack DP
- dp[i] 表示: # of ways to fill weight i
- 1维: dp[w]: fill weigth w 有多少种方法. 前面有多少种可能性, 就sum多少个:
- dp[w] = sum{dp[w - nums[i]]}, i = 0~n

##### 分析
- 拼背包时, 可以有重复item, 所以考虑'最后被放入的哪个unique item' 就没有意义了.
- 背包问题, 永远和weight分不开关系.
- 这里很像coin chagne: 考虑最后被放入的东西的value/weigth, 而不考虑是哪个.






---

**5. [Total Occurrence of Target.java](https://github.com/awangdev/LintCode/blob/master/Java/Total%20Occurrence%20of%20Target.java)**      Level: Medium      Tags: []
      
想法很简单。写起来有点长。
找total number of occurance. 首先找first occurance, 再找last occurance.



---

**6. [House Robber III.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20III.java)**      Level: Medium      Tags: [DFS, DP, Status DP, Tree]
      
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

**7. [Binary Tree Maximum Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum%20II.java)**      Level: Medium      Tags: [DFS, Tree]
      
找到从max path sum from root. 条件: 至少有一个node.

#### DFS
- 比Binary Tree Maximum Path Sum I 简单许多. 因为条件给的更多：at least 1 node + have to start from root
- root一定用到
- 3种情况: curr node, curr+left, curr+right
- 因为一定包括root, 说以从 `dfs(root, sum=0)` 开始, 每个level先加root, sum += root.val



---

**8. [Backpack V.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20V.java)**      Level: Medium      Tags: [Backpack DP, DP]
      
#### Backpack DP
- 与背包1不同: 这里不是check可能性(OR)或者最多能装的size是多少; 而是计算有多少种正好fill的可能性.
- dp[i][w]: 用前i本书, 正好fill到 w weight的可能性.
- 对于末尾, 还是两种情况:
- 1. i-1位置没有加bag
- 2. i-1位置加了bag
- 两种情况可以fill满w的情况加起来, 就是我们要的结果.
- 如常: dp[n + 1][w + 1]
- 重点: dp[0][0] 表示0本书装满weight=0的包, 这里我们必须 dp[0][0] = 1, 给后面的 dp function 做base
- Space, time: O(MN)
- Rolling array, 空间优化, 滚动数组. Space: O(M)

#### 降维打击, 终极优化
- 分析row(i-1)的规律, 发现所有row(i)的值, 都跟row(i-1)的左边element相关, 而右边element是没用的.
- 所以可以被override.
- Space: O(M), 真*一维啊!
- Time: O(MN)



---

**9. [Restore IP Addresses.java](https://github.com/awangdev/LintCode/blob/master/Java/Restore%20IP%20Addresses.java)**      Level: Medium      Tags: [Backtracking, DFS, String]
      
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

**10. [Linked List Cycle II.java](https://github.com/awangdev/LintCode/blob/master/Java/Linked%20List%20Cycle%20II.java)**      Level: Medium      Tags: [Linked List, Math, Two Pointers]
      
LinkedList 里面有 cycle, 找到cycle的起始点(第一个重复出现的element).

#### Slow, fast Pointer
- 快慢指针, O(1)space.
- 1. 确认有cycle后 2. 数学问题:找到开头.
- 当head == slow.next时候， head就是cycle starting point.
- 也就是说，当slow 移动到了那个回溯点，slow.next那个点就刚好是head的那个点...

#### 证明
- 1. 假设慢指针走t步, 快指针走快一倍, 也就是2t.
- 2. 我们假设cycle的长度是Y, 而进入cycle之前的长度为X.
- 3. 假设慢指针走了m圈cycle, 而快指针走了n圈cycle之后, 两个pointer相遇.
- 4. 最终在Y cycle里面的K点相遇, 也就是两个指针都在这最后一圈里面走了K 步.
- 那么:
- t = X + mY + K
- 2t = X + nY + K
- 整合公式: X + K = (n - 2m)Y
- 这里的m和n不过是整数的跑圈数, 也就是说X和K加在一起, 总归是结束cycle. X 和 K 互补
- 结论: 当slow/fast 指针在K点相遇后, 再走X步, 就到了cycle的起点, 也就是题目要求的起点.

#### Hash Table, O(n) space




---

**11. [Unique Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DP, Tree]
      
Not quite clear.
根据左右分割而总结出了原理, 每次分割, 左右两边都会有一定数量的permutation, 总体上的情况数量当然是相乘.
然后每一个不同的分割点都加一遍:
f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

然后把数学公式转换成DP的方程, 有点玄学的意思啊! 不好想.



---

**12. [Largest Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Number.java)**      Level: Medium      Tags: [Sort]
      
给一串数字, 非负数, 把所有数字串联起来, 组成最大数字.

因为结果很大, 所以用string表示 

#### Sort, Comparator
- 考虑 more significant spot 应该拿到更大的值
- 如果sort number,  comparator 会比较难写: 每个digit的weight不同, 要分别讨论个位数和多位数.
- goal: 让较大的组合数排在前面, 让较小的组合数排在后面
- 不如: 组合两种情况, 用String比较一下大小 (也可以用 integer来比较组合数, 但是为保险不超Integer.MAX_VALUE, 这里比较String)
- String.compareTo() 是按照 lexicographically, 字典顺序排列的
- 利用compareTo, 来倒序排列 string, 刚好就得到我们要的结果.
- O(nlogn), 排序



---

**13. [Triangles.java](https://github.com/awangdev/LintCode/blob/master/Java/Triangles.java)**      Level: Medium      Tags: [Array, Coordinate DP, DFS, DP, Memoization]
      
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

**14. [Summary Ranges.java](https://github.com/awangdev/LintCode/blob/master/Java/Summary%20Ranges.java)**      Level: Medium      Tags: [Array]
      
给一串sorted list, 中间有缺数字, return 所有数字的range string (example 看题目)

#### Basic implementation
- 用一个list as the buffer to store candidates
- when: 1. end of nums; 2. not continuous integer => convert list to result



---

**15. [Single Number III.java](https://github.com/awangdev/LintCode/blob/master/Java/Single%20Number%20III.java)**      Level: Medium      Tags: [Bit Manipulation]
      
TODO: wut?


---

**16. [Fast Power.java](https://github.com/awangdev/LintCode/blob/master/Java/Fast%20Power.java)**      Level: Medium      Tags: [DFS, Divide and Conquer]
      
如题: Calculate the a^n % b where a, b and n are all 32bit integers.

#### Divide and Conquer
- a^n可以被拆解成(a*a*a*a....*a)， 是乘机形式，而%是可以把每一项都mod一下的。所以就拆开来take mod.
- 这里用个二分的方法，recursively二分下去，直到n/2为0或者1，然后分别对待. 
- 注意1: 二分后要conquer，乘积可能大于Integer.MAX_VALUE, 所以用个long.
- 注意2: 要处理n%2==1的情况，二分时候自动省掉了一份 a，要乘一下。




---

**17. [Total Hamming Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Total%20Hamming%20Distance.java)**      Level: Medium      Tags: [Bit Manipulation]
      

给出Hamming Distance定义(bit format时候有多少binary diff), 求一串数字的hamming distance总和.

#### Bit Manipulation
- Bit题: 考验 bit >>, mask & 1, 还有对题目的理解能力
- Put integers in binary, and compare each column:
- for each `1`, ask: how many are different from me? all the `0`
- `# of diffs at each bit-column = #ofZero * #ofOne `
- 1. countZero[], countOne[]; 2. loop over nums and populate the two array

##### 注意雷点
- 问清楚: 10^9 < 2^31, we are okay with 32 bits
- `最终的hamming distance 要从 [1 ~ 32] 哪个bit开始算起`? 取决于 `最长`的那个binary format: 但不用先去找bit length
- 在做countZero, countOne时候, 都做32-bit; 最终做乘积的时候, 如果 `1` 或者 `0` 个数为零, 乘积自然为0.




---

**18. [Two Lists Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Lists%20Sum.java)**      Level: Medium      Tags: [Linked List]
      
给两个Linked list, sum up and 合成新的list



---

**19. [Flatten 2D Vector.java](https://github.com/awangdev/LintCode/blob/master/Java/Flatten%202D%20Vector.java)**      Level: Medium      Tags: [Design]
      
Implement an iterator to flatten a 2d vector.

Just move pointers carefully with next(), hashNext()

#### Basic Implementation using x, y corrdinate
- 就是把2D list里面的element全部遍历一遍。
- 跟一个nxn的matrix遍历，是没区别的拉; 所有来个x,y，把2d list跑一变。

#### Always return item at index 0, and remove from list?
- list 方便remove, 考虑吧reduce input vector (就像给的是linked list 一样)



---

**20. [Find the Weak Connected Component in the Directed Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Weak%20Connected%20Component%20in%20the%20Directed%20Graph.java)**      Level: Medium      Tags: [Union Find]
      
遍历 weak connected graph, 将结果存在 List<List<Node>>种.

#### Union Find
- 跟传统的UnionFind有两点不同:
- 1. 用 Map<Integer, Integer> 代替 int[], 因为没有给出 graph node label的 boundary.
- 2. find(x)时候, 没有去update `parent[x]/map.put(x, ..)`. 因为我们最终需要找到这个path.
- 无法用传统dfs: directed node 无法point到上一个点; 必须用`存parent的方式把所有node遍历掉`

#### Identify这是个union-find问题
- 看到了weak component的形式： 一个点指向所有，那么所有的点都有一个公共的parent，然后就是要找出这些点。    
- 为何不能从一个点出发，比如A，直接print它所有的neighbors呢:
- 如果轮到了B点，那因为是directed,它也不知道A的情况，也不知道改如何继续加，或者下手。    
- 所以，要把所有跟A有关系的点，或者接下去和A的neighbor有关系的点，都放进union-find里面，让这些点有Common parents.     
- 最后output的想法：    
- 做一个 map <parent ID, list>。    
- 之前我们不是给每个num都存好了parent了嘛。    
- 每个num都有个parent, 然后不同的parent就创造一个不同的list。   
- 最后，把Map里面所有的list拿出来就好了。    



---

**21. [Interval Minimum Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Minimum%20Number.java)**      Level: Medium      Tags: [Binary Search, Divide and Conquer, Lint, Segment Tree]
      
给一串数字 int[], 然后一个query Interval[], 每个interval是 [start, end], 找query 区间里的最小值.

#### Segment Tree
- SegtmentTree, methods: Build, Query. 这题是在SegmentTreeNode里面存min.
- 类似的有存:max, sum, min



---

**22. [Stone Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Stone%20Game.java)**      Level: Medium      Tags: [DP]
      
这个DP有点诡异. 需要斟酌。
NOT DONE YET


---

**23. [Longest Increasing Continuous subsequence II.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence%20II.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP, Memoization]
      
#### Coordinate DP
- due to access permission, not test
- dp[i][j]: longest continuous subsequence length at coordinate (i, j)
- dp[i][j] should come from (i-1,j) and (i, j-1).
- dp[0][0] = 1
- condition: from up/left, must be increasing
- return dp[m-1][n-1]

#### Memoization
- O(mn) space for dp and flag.
- O(mn) runtime because each spot will be marked once visited. 
- 这个题目的简单版本一个array的例子：从简单题目开始想DP会简单一点。每个位置，都是从其他位置（上下左右）来的dpValue +　１.　如果啥也没有的时候，init state 其实都是1， 就一个数字，不增不减嘛。




---

**24. [Line Reflection.java](https://github.com/awangdev/LintCode/blob/master/Java/Line%20Reflection.java)**      Level: Medium      Tags: [Hash Table, Math]
      

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

**25. [Find Minimum in Rotated Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Minimum%20in%20Rotated%20Sorted%20Array.java)**      Level: Medium      Tags: [Array, Binary Search]
      
画图, 最小值被rotate之后, 变成array中间的最低谷.
并且, 左边山坡的最小值, 大于右边山坡的最大值. 
以此来给binary search做判断.

O(nlogn)



---

**26. [Binary Tree Longest Consecutive Sequence II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence%20II.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Double Recursive, Tree]
      
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

**27. [Connecting Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Connecting%20Graph.java)**      Level: Medium      Tags: [Union Find]
      
没有跑过这个程序, 是一个UnionFind的简单实现.
Document了每个环节的计算原理/思想.



---

**28. [Count of Smaller Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Number.java)**      Level: Medium      Tags: [Binary Search, Lint, Segment Tree]
      
给一串数字, array size = n. 给一串query: 每个query是一个数, 目的找 count# items smaller than query element.

#### Segment Tree
- 和平时的segment tree问题不同。 [0 ～ n] 代表实际数字: based on real value的segment tree.
- Modify时，把array里面的value带进去，找到特定的位子, 然后count + 1. 
- 最终在SegmentTree leaf上面全是array里面实际的数字。
- node.count: 在node range里面的有多少个数字

##### right use of modify()
- build() 只是 empty segment tree, 没有property
- modify() 需要: 1. 找到left, update count+=1; 2. aggregate all parent when after returning
- 所以每一个modify 都是在整个path上所有的node上 + count

##### query trick
- 在query前，给进去的start和end是： 0 ~ value-1.   
- `value-1`: 找比自己所在range小1的range（那么自然而然地就不包括自己了），这样就找到了smaller number.   

##### About other basic segment tree setup
- [那么其他做过的SegmentTree是怎么样呢？]   
- 那些构成好的SegmentTree(找min,max,sum)也有一个Array。但是构成Tree时候，随Array的index而构架。   
- 也就是说，假如有Array[x,y,....]:在leaf,会有[0,0] with value = x. [1,1] with value = y. 
- [但是这题]   
- 构成时，是用actual value.也就是比如Array[x,y,....]会产生leaf:[x,x]with value = ..; [y,y]with value =...    
- 其实很容易看穿:   
- 若给出一个固定的array构成 SegmentTree，那估计很简单：按照index从0~array.lengh，leaf上就是[0,0] with value = x.
- 若题目让构造一个空心SegmentTree, `based on value 0 ~ n-1 (n <= 10000)`, 然后把一个Array的value modify 进去。   
- 这样八成是另外一种咯。



---

**29. [Flip Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Flip%20Game%20II.java)**      Level: Medium      Tags: [Backtracking, DFS, DP]
      
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

**30. [Binary Tree Level Order Traversal II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Level%20Order%20Traversal%20II.java)**      Level: Medium      Tags: [BFS, Tree]
      
如题, 但是output要倒序.

#### BFS
- 跟Binary Tree Level Order Traversal一样,只不过存result一直存在存在0位.


#### DFS
- 根据level来append每个list
- rst里面add(0,...)每次都add在list开头



---

**31. [Walls and Gates.java](https://github.com/awangdev/LintCode/blob/master/Java/Walls%20and%20Gates.java)**      Level: Medium      Tags: [BFS, DFS]
      
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

**32. [Decode String.java](https://github.com/awangdev/LintCode/blob/master/Java/Decode%20String.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Stack]
      
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

**33. [The Maze.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Maze.java)**      Level: Medium      Tags: [BFS, DFS]
      
#### BFS
- BFS on coordinates
- always attempt to move to end of border
- use boolean[][] visited to alingn with BFS solution in Maze II, III, where it uses Node[][] to store state on each item.



---

**34. [Palindromic Substrings.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindromic%20Substrings.java)**      Level: Medium      Tags: [DP, String]
      
根据题意, count # of palindromic substring. (不同index截取出来的substring算不同的情况)

#### isPalin[][]
- build boolean[][] to check isPalin[i][j] with DP concept
- check all candidates isPalin[][]
- O(n^2)

#### odd/even split check
https://leetcode.com/problems/palindromic-substrings/discuss/105689/Java-solution-8-lines-extendPalindrome



---

**35. [Perfect Squares.java](https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Squares.java)**      Level: Medium      Tags: [BFS, DP, Math, Partition DP]
      
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

**36. [Word Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search.java)**      Level: Medium      Tags: [Array, Backtracking, DFS]
      

#### DFS, Backtracking:
- 找到开头的字母, 然后recursively DFS 去把word串到底:
- 每到一个字母, 朝四个方向走, 之中一个true就可以.
- Note:每次到一个字母，mark一下'#'. 4个path recurse回来后，mark it back.

#### Note: other ways of marking visited:
- 用一个boolean visited[][]
- Use hash map, key = x@y




---

**37. [Backpack II.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20II.java)**      Level: Medium      Tags: [Backpack DP, DP]
      
给i本书, 每本书有自己的重量 int[] A, 每本书有value int[] V

背包有自己的大小M, 看最多能放多少value的书?

#### Backpack DP
- 做了Backpack I, 这个就如出一辙, 只不过: dp存的不是max weight, 而是 value的最大值.
- 想法还是，选了A[i - 1] 或者没选A[i - 1]时候不同的value值.
- 时间空间O(mn)
- Rolling Array, 空间O(m)

#### Previous DP Solution
- 如果无法达到的w, 应该mark as impossible. 一种简单做法是mark as -1 in dp. 
- 如果有负数value, 就不能这样, 而是要开一个can[i][w]数组, 也就是backpack I 的原型.
- 这样做似乎要多一些代码, 好像并不是非常需要




---

**38. [Update Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Update%20Bits.java)**      Level: Medium      Tags: [Bit Manipulation]
      
熟悉bits的一些trick:
- ~0 = -1 = 111111...11111111 (32-bit)
- Create mask by shifting right >>>, and shifting left
- Reverse to get 0000...11110000 format mask
- & 0000 = clean up; | ABC = assign ABC



---

**39. [Triangle Count.java](https://github.com/awangdev/LintCode/blob/master/Java/Triangle%20Count.java)**      Level: Medium      Tags: [Array]
      
其实也就是3sum的变形, 或者而说2sum的变形. 主要用2 pointers来做.
注意, 在选index时候每次定好一个 [0 ~ i], 在这里面找点start, end, 然后i 来组成triangle.
Note巧妙点:
在此之中, 如果一种start/end/i 符合, 那么从这个[start~end]中, 大于start的都可以, 所以我们count+= end-start.
反而言之, <end的其他index, 就不一定能符合nums[start] + nums[end] > nums[i]



---

**40. [Permutation Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutation%20Sequence.java)**      Level: Medium      Tags: [Backtracking, Math]
      
TODO: what about regular DFS/backtracking to compute the kth? dfs(rst, list, candiate list, k)

k是permutation的一个数位。而permutation是有规律的。

也就是说，可以根据k的大小来判断每一个数位的字符（从最大数位开始，因为默认factorio从最大数位开始变化）。

于是先求出n!， 然后 k/n!就可以推算出当下这一个数位的字符。然后分别把factorio 和 k减小。

另外, 用一个boolean[] visited来确保每个数字只出现一次。

这个方法比计算出每个permutation要efficient许多。




---

**41. [House Robber II.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20II.java)**      Level: Medium      Tags: [DP, Sequence DP, Status DP]
      
和House Robber I 类似, 搜刮房子, 相邻不能动. 特点是: 现在nums排成了圈, 首尾相连.

#### Sequence DP
- dp[i][status]: 在 status=[0,1] 情况下, 前i个 房子拿到的 max rob gain. status=0, 1st house robbed; status=1, 1st house skipped
- 根据dp[i-1]是否被rob来讨论dp[i]: dp[i] = Math.max(dp[i-1], dp[i - 2] + nums[i - 1]);
- 特别的是，末尾的last house 和 first house相连. 这里就需要分别讨论两种情况: 第一个房子被搜刮, 或者第一个房子没被搜刮
- be careful with edge case nums = [0], only with 1 element.
- Time,space: O(n)

#### 两个状态
- 是否搜刮了第一个房子, 分出两个branch, 可以看做两种状态.
- 可以考虑用两个DP array; 也可以加一dp维度, 补充这个状态.
- 连个维度表示的是2种状态(1st house being robbed or not); 这两种状态是平行世界的两种状态, 互不相关.

#### Rolling array
- 与House Robber I一样, 可以用%2 来操作rolling array, space reduced to O(1)



---

**42. [Letter Combinations of a Phone Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Letter%20Combinations%20of%20a%20Phone%20Number.java)**      Level: Medium      Tags: [Backtracking, String]
      
方法1: Iterative with BFS using queue.

方法2: Recursively adding chars per digit



---

**43. [Minimum Size Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Size%20Subarray%20Sum.java)**      Level: Medium      Tags: [Array, Binary Search, Subarray, Two Pointers]
      

给一串positive integer, 找最短的subarray sum, where the sum >= s

#### Two pointer
- 全部是positive integer, 那么preSum一定是增长的.
- 那其实就用two pointer: `start=0, end=0` 不断往前移动. 策略: 
- 1. end++ until a solution where sum >= s is reached
- 2. 然后移动start; 记录每个solution, Math.min(min, end - start);
- 3. 然后再移动end，往下找
- Note: 虽然一眼看上去是nested loop.但是分析后，发现其实就是按照end pointer移动的Loop。start每次移动一格。总体上，还是O(n)

#### Binary Search
- O(nlogn) NOT DONE.

#### Double For loop
- O(n^2), inefficient



---

**44. [Maximum Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Binary%20Tree.java)**      Level: Medium      Tags: [Stack, Tree]
      
给一串数字, 做一个 maximum binary tree: 最顶上的root最大; 左child也是一个max tree, 右child也必须是max tree.

#### Monotonous Stack
- 用到bottom->top递减的stack: 最底下的root维持成最大的element.
- 过程当中, 一旦遇到currNode.val > stack.peek(), 就意味着需要把这个currNode放在 stack的底层位置.
- 也就是说, 遇到这个条件, process, pop()所有 currNode.val > stack.peek(), 最后把currNode加进去.

- maxTree题目本身的要求是: 大的在最中间, 左右两边的subTree也要是maxTree:
- Monotonous Stack在这里帮助 keep/track of max value, 但是left/right tree的logic是MaxTree独有的.
- left/right node的assignment是根据题目要求: 中间最大值分开后, 左边的是左边subTree, 右边的作为右边subTree.

#### Previous notes
- Should memorize MaxTree. 依次类推，会做Min-Tree, Expression Tree
- Stack里，最大的值在下面。利用此性质，有这样几个step:

##### Step1
- 把所有小于curr node的，全Pop出来, while loop, keep it going.    
- 最后pop出的这个小于Curr的node：它同时也是stack里面pop出来小于curr的最大的一个，最接近curr大小。（因为这个stack最大值靠下面）    
- 把这个最大的小于curr的node放在curr.left.    

##### Step2   
- 那么，接下去stack里面的一定是大于curr：   
- 那就变成curr的left parent. set stack.peek().right = curr.

##### Step3   
- 结尾：stack底部一定是最大的那个，也就是max tree的头。





---

**45. [ColorGrid.java](https://github.com/awangdev/LintCode/blob/master/Java/ColorGrid.java)**      Level: Medium      Tags: [Design, Hash Table]
      
#### basic implementation
- 用HashMap， 理解题目规律，因为重复的计算可以被覆盖，所以是个优化题。没有什么太大的悬念和意义.
- 消灭重合点:       
- 如果process当下col, 其实要减去过去所有加过的row的交接点。。。     
- 再分析，就是每次碰到row 取一个单点, sumRow += xxx。       
- 然后process当下col时候， sum += colValue * N - sumRow. 就等于把交叉所有row（曾经Process过的row）的点减去了。很方便。
- 最后read in 是O(P),  process也是O(P).




---

**46. [Construct Binary Tree from Inorder and Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Tree]
      
#### DFS, Divide and Conquer
- 写个Inorder和Postorder的例子。利用他们分left/right subtree的规律解题。
- Postorder array 的末尾， 就是当下层的root.   
- 在Inorder array 里面找到这个root,就刚好把左右两边分割成left/right tree。
- 这题比较tricky地用了一个helper做recursive。 特别要注意处理index的变化, precisely考虑开头结尾
- runtime: O(n), visit && build all nodes

#### Improvement
- `findMid(arr)` can be replaced with a map<value, index>, no need execute O(n) search at runtime



---

**47. [Backpack.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack.java)**      Level: Medium      Tags: [Backpack DP, DP]
      
给i本书, 每本书有自己的重量 int[] A, 背包有自己的大小M, 看最多能放多少重量的书?

#### Backpack DP 1
- 简单直白的思考 dp[i][m]: 前i本书, 背包大小为M的时候, 最多能装多种的书?
- **注意**: 背包问题, 重量weight一定要是一维.
- dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + A[i - 1]);
- 每一步都track 最大值
- 最后return dp[n][m]
- 时间空间  O(mn)
- Rolling array, 空间O(m)

#### Backpack DP 2
- true/false求解, 稍微曲线救国: 重点是, 最后, 按照weight从大到小遍历, 第一个遇到true的, index就是最大值.  
- 考虑: 用i个item (可跳过地取), 是否能装到weight w?
- 需要从'可能性'的角度考虑, 不要搞成单一的最大值问题.
- 1. 背包可装的物品大小和总承重有关.
- 2. 不要去找dp[i]前i个物品的最大总重, 找的不是这个. 
    dp[i]及时找到可放的最大sum, 但是i+1可能有更好的值, 把dp[i+1]变得更大更合适.

##### 做法
- boolean[][] dp[i][j]表示: 有前i个item, 用他们可否组成size为j的背包? true/false.
- (反过来考虑了，不是想是否超过size j, 而是考虑是否能拼出exact size == j)
- **注意**: 虽然dp里面一直存在i的位置, 实际上考虑的是在i位置的时候, 看前i-1个item.

##### 多项式规律
- 1. picked A[i-1]: 就是A[i-1]被用过, weight j 应该减去A[i-1]. 那么dp[i][j]就取决于dp[i-1][j-A[i-1]]的结果.
- 2. did not pick A[i-1]: 那就是说, 没用过A[i-1], 那么dp[i][j]就取决于上一行d[i-1][j]
- dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]]

##### 结尾
- 跑一遍dp 最下面一个row. 从末尾开始找, 最末尾的一个j (能让dp[i][j] == true)的, 就是最多能装的大小 :)   
- 时间，空间都是：O(mn)




---

**48. [Longest Common Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Subsequence.java)**      Level: Medium      Tags: [DP, Double Sequence DP, Sequence DP]
      
给两个string, A, B. 找这两个string里面的LCS: 最长公共字符长度 (不需要是continuous substring)

#### Double Sequence DP
- 设定dp长度为(n+1), 因为dp[i]要用来表示前i个(ith)时候的状态, 所以长度需要时i+1才可以在i位置, hold住i.
- 双序列: 两个sequence之间的关系, 都是从末尾字符看起, 分析2种情况:
- 1. A最后字符不在common sequence 或者 B最后字符不在common sequence.
- 2. A/B最后字符都在common sequence. 总体count + 1.



---

**49. [Peeking Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/Peeking%20Iterator.java)**      Level: Medium      Tags: [Design]
      
#### Use concept pre cache
- 找一个cache来存next()的值, 也就是: next value的值提前存在cache里面
- 因此peek()的时候, 就可以直接return cache, 而不用做 itt.next()
- 然后每次真的next()的时候, 里取下一个itt.next()维护这个cache

#### Previous notes
- 再一次理解错题意. peek() 就是头顶，但是不一定是最大值啊。
- 总是把PEEK想成了最大值，然后用2 STACK做了最大值的cache，练的一手好双stack，可惜错了。




---

**50. [QuickSort.java](https://github.com/awangdev/LintCode/blob/master/Java/QuickSort.java)**      Level: Medium      Tags: [Quick Sort, Sort]
      
implement quick sort.

#### Quick Sort
- 首先partition. 返还一个partition的那个中间点的位置: 这个时候, 所有小于nums[partitionIndex] 都应该在 partitionIndex左边
- 然后劈开两半
- 前后各自 quick sort, recursively
- 注意：在partition里面, 比较的时候nums[start] < pivot, nums[end]>pivot, 如果写成了 <= 会 stack overflow.
- Time O(nlogn), Space: O(1)



---

**51. [Redundant Connection.java](https://github.com/awangdev/LintCode/blob/master/Java/Redundant%20Connection.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Tree, Union Find]
      
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

**52. [Rotate List.java](https://github.com/awangdev/LintCode/blob/master/Java/Rotate%20List.java)**      Level: Medium      Tags: [Linked List, Two Pointers]
      
给一个single linked list, 右移k steps. k non-negative.

#### Linked List basics
- 记得用dummy.next来存head.
- 特殊: 这里k可能大于list总长. 写一写linked node 移动的步数, 然后 k = k % n.
- 找到newTail, newHead, 然后利用dummy, 换位子



---

**53. [Swap Nodes in Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Swap%20Nodes%20in%20Pairs.java)**      Level: Medium      Tags: [Linked List]
      
#### enumurate 
基本原理, 写出来, 然后连线:
pre -> A -> B -> C -> ...
需要一个虚拟 preNode做起始node, 不然无法把后面的node换到开头.

#### 注意
用dummy = pre作为head前一格.
用 `pre.next == null && pre.next.next` 来check是否为NULL.
pre.next.next 保证了至少有一次swap.



---

**54. [Combinations.java](https://github.com/awangdev/LintCode/blob/master/Java/Combinations.java)**      Level: Medium      Tags: [Backtracking, Combination, DFS]
      
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

#### DFS, Backtracking
- for loop, recursive (dfs).
- 每个item用一次, 下一个level dfs(index + 1)
- Combination DFS. 画个图想想. 每次从1~n里面pick一个数字i
- 因为下一层不能重新回去 [0~i]选，所以下一层recursive要从i+1开始选。



---

**55. [Sort List.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20List.java)**      Level: Medium      Tags: [Divide and Conquer, Linked List, Merge Sort, Sort]
      
#### Merge sort
- 1. find middle. 快慢指针
- 2. Sort: 切开两半，先sort前半, 如果先sort了mid.next~end, sort后，中间点mid.next == null，再sort前半段
- 3. Merge:  假设given list A, B 已经是sorted, 然后按照大小，混合。
- 要recursively call sortList() on partial list.

#### Quick sort
- 想做可以看讲义：http://www.jiuzhang.com/solutions/sort-list/
- 但是quick sort不建议用在list上面。
- 排列list, merge sort可能更可行和合理。原因分析在下面， 以及： http://www.geeksforgeeks.org/why-quick-sort-preferred-for-arrays-and-merge-sort-for-linked-lists/



---

**56. [Find Peak Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element.java)**      Level: Medium      Tags: [Array, Binary Search]
      
binary search. 
Goal: find peak, where both sides are descending
最左边, 最右边是Integer.MIN_VALUE时候, 也能构成中间数mid是peak的条件.

Note:
没有必要特别check (mid-1)<0或者(mid+1)>=n.
证明:
1. 最左端: 当start=0, end = 2 => mid = 1, mid-1 = 0;
2. 最右端: 当end = n - 1, start = n - 3; mid = (start+end)/2 = n - 2; 
那么mid + 1 = n - 2 + 1 = n - 1 < n 是理所当然的



---

**57. [Gray Code.java](https://github.com/awangdev/LintCode/blob/master/Java/Gray%20Code.java)**      Level: Medium      Tags: [Backtracking]
      
TODO:
1. backtracking, using set to perform contains()
2. BFS: use queue to keep the mutations

题目蛋疼，目前只接受一种结果。

BackTracking + DFS:   
   Recursive helper里每次flip一个 自己/左边/右边. Flip过后还要恢复原样.遍历所有.

曾用法（未仔细验证）：   
基本想法就是从一个点开始往一个方向走，每次flip一个bit, 碰壁的时候就回头走。



---

**58. [Encode and Decode TinyURL.java](https://github.com/awangdev/LintCode/blob/master/Java/Encode%20and%20Decode%20TinyURL.java)**      Level: Medium      Tags: [Hash Table, Math]
      
其实想到了切入点, 是个可难可简单的题目. 这里的encode就是想办法把url存起来, 然后给个 key.
那么具体怎么做这个key, 简单就可以用一个map, 然后counting. 复杂一点就可以做random letter/number组成key.



---

**59. [Game of Life.java](https://github.com/awangdev/LintCode/blob/master/Java/Game%20of%20Life.java)**      Level: Medium      Tags: [Array]
      
#### basic
- 简单的implementation, 把count function写清楚就好.
- time: O(mn), extra space: O(mn)
- 注意结尾要一个个board[i][j]copy

#### follow up
unlimited border? 可能需要分割board. 用大框分割, 每次换行的时候, 重复计算2行就好了. see details below.

#### improvement: do it in place!
- time: O(mn), extra space: O(1)
- bit manipulation: 用第二个bit来存previous value.
- 因为我们只考虑 0 和 1 而已, 所以可以这样取巧. 但是并不scalable.
- 如果需要存multiple state, 可能需要移动更多位, 或者用一个 state map
- 注意 bit manipulation 的细节: <<1, >>1, 还有 mast的用法: |, & 




---

**60. [Compare Version Numbers.java](https://github.com/awangdev/LintCode/blob/master/Java/Compare%20Version%20Numbers.java)**      Level: Medium      Tags: [String]
      
给两串version number,  由数字和'.' 组成. 比较先后顺序. 

If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

#### divide and conquer 
- 用 str.split("\\.") 分割string
- Convert成integer, 逐个击破

#### 注意
- '1.0' 和 '0' 是相等的
- 如果可以假设version integer都是valid, 直接Integer.parseInt()就可以了
- 不然的话, 可以compare string



---

**61. [Ugly Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Ugly%20Number.java)**      Level: Medium      Tags: [Math]
      
LeetCode: 判断数字是否是ugly number. (definition: factor only have 2, 3, 5)

#### Math
- 看是否可以整除. 
- 看整除最终结果是否== 1

LintCode: 找kth ugly number, 应该与 Ugly Number II是一样的

- 方法1: PriorityQueue排序。用ArrayList check 新的ugly Number是否出现过。
- 方法1-1：(解释不通，不可取)用PriorityQueue排序。神奇的3，5，7走位：按照题目答案的出发，定了3，5，7以什么规律出现。但是题目并没有特殊表明。
- 方法2: DP . Not Done yet.




---

**62. [Rehashing.java](https://github.com/awangdev/LintCode/blob/master/Java/Rehashing.java)**      Level: Medium      Tags: [Hash Table]
      
给一个Hash Table, 是用 linked list 做的. 问题是: capacity太小, collision太多的情况下, 需要double capacity 然后rehash.

#### Hash Table
- 明白hashCode() function的意义: 拿到hashKey的时候, 用hashKey%capacity 来做hash code
- hashcode就是hash map里面的index
- 明白collision handling 的方式, 和如何double capacity而rehashing
- 都是基本操作, 概念实现



---

**63. [Longest Common Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Substring.java)**      Level: Medium      Tags: [DP, Double Sequence DP, Sequence DP, String]
      
#### Double Sequence DP
- 两个string, 找最值: longest common string length
- 序列型, 并且是双序列, 找两个序列 (两维的某种性质)
- dp[i][j]: 对于 A 的前i个字母, 对于 B 的前j个字母, 找最长公共substring的长度
- dp = new int[m + 1][n + 1]
- dp[i][j] = dp[i - 1][j - 1] + 1; only if A.charAt(i - 1) == B.charAt(j - 1)
- 注意track max, 最后return
- space O(n^2), time(n^2)

##### Rolling array
- 空间优化, [i] 只有和 [i - 1] 相关, 空间优化成 O(n)

#### String
- 找所有A的substring, 然后B.contains()
- track max substring length
- O(n^2) time



---

**64. [Rotate Image.java](https://github.com/awangdev/LintCode/blob/master/Java/Rotate%20Image.java)**      Level: Medium      Tags: [Array, Enumeration]
      
#### 找公式规律
- 找到个转角度的规律公式: r = c; c = (w - r)
- 用temp variable, swap in place.



---

**65. [Combination Sum IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20IV.java)**      Level: Medium      Tags: [Array, Backpack DP, DP]
      
给一串数字candidates (no duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 可以用任意多次.

#### Backpack DP
- 计数问题, 可以想到DP. 其实就是Backpack VI.
- 从x个数字里面找candidate(可以重复用同一个数字), 来sum up to target. 找: # of ways to form the sequence.
- Backpack VI: 给一个数组nums, 全正数, 无重复数字; 找: # of 拼出m的方法
- dp[i]: # of ways to build up to target i
- consider last step: 如果上一步取的是 candidate A, 那么就该加到dp[i]:
- dp[i] += dp[i - A]
- 要找overall dp[i], 就做一个for loop: dp[i] = sum{dp[i - num]}, where for (num: nums)
- Time: O(mn). m = size of nums, n = target
- If we optimize dp for loop, 需要Sort nums. O(mlogm). will efficient 如果m是constant或者relatively small. Overall: O(n)

#### DFS, backtracking
- 尽管思考方式是对的, 但是 times out
- 可以重复使用数字的时候, 比如用1 来拼出 999, 这里用1就可以走999 dfs level, 不efficient



---

**66. [Number of Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Longest%20Increasing%20Subsequence.java)**      Level: Medium      Tags: [Coordinate DP, DP]
      

给一串 unsorted sequence, 找到长 increasing subsequence 的个数!

#### Coordinate DP
- 需要能够判断综合题, 分清楚情况和套路: combination of `longest subsequence` and `ways to do`, as well as global variable. 
- len[i] (我们平时的dp[i]): 在前i个元素中, 最长的 increasing subsequence length;
- count[i]: 在前i个元素中, 并且以 len[i]这个长度为准的 subsequence的 count. 或者: 在前i个元素中, ways to reach longest increasing subsequence.
- `len[i] == len[j] + 1`: same length, but different sequence, so add all `count[i] += count[j]`
- `len[i] < len[j] + 1`: 这就是更长的情况找到了, 那么有多少次 count[j] 有多少, count[i] 就有多少. 仔细想sequence: 长度增长了, 但是ways to reach i 没有增长.
- 同样的判断需要用在 maxLen 和 maxFreq上:
- 如果没有增长 maxLen 不变, maxFreq上面需要 +=count[i] (同一种长度, 多了更多的做法)
- 如果maxLen 变长, maxFreq 也就是采用了 count[i] = count[j]
- TODO: Is rolling array possible?

#### 相关
- 都是 Coordiate DP, DP的鼻祖家族:
- Longest Increasing Subsequence (跟这道题的一部分一模一样)
- Longest Continuous Increasing Subsequence (连续, 只check dp[i - 1])
- Longest Increasing Continuous Subsequence I, II (Lintcode, II 是matrix)



---

**67. [4Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/4Sum.java)**      Level: Medium      Tags: [Hash Table]
      
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

**68. [Populating Next Right Pointers in Each Node.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      
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

**69. [Space Replacement.java](https://github.com/awangdev/LintCode/blob/master/Java/Space%20Replacement.java)**      Level: Medium      Tags: [String]
      


---

**70. [Contiguous Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Contiguous%20Array.java)**      Level: Medium      Tags: [Hash Table]
      
TODO: how aout without chaning the input nums?



---

**71. [Reverse Linked List II .java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Linked%20List%20II%20.java)**      Level: Medium      Tags: [Linked List]
      
reverse 一个 linked list 中  [m ~ n] 的一部分.

#### Reverse linked list
- 在基本的reverse linked list 上面 多了一层: 找到front node,  接下来的 [m ~ n] node 需要被reverse
- 只需要reverse中间的部分.
- Reverse的时候: 用一个dummyNode, 这道题里面, 其实就用 nodeFront, 那么 dummy.next 就是整个reversed list.

##### 注意
- 一定要Mark开头的那个mth node, 最后用它接上 剩下node tail. 不然后面的node会断掉

#### Previous notes
- 遍历到M前，
- 存一下那个点，
- 从M开始， for loop， reverse [m~n]。 然后把三段链接在一起。




---

**72. [Minimum Height Trees.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Height%20Trees.java)**      Level: Medium      Tags: [BFS, Graph]
      
#### Graph + BFS
- Build graph `map<node, list of node>`
- BFS to find the shortest path: when the neibhbor has the curr node as the only one neighbor, it is leaf.
- record shortest path in Map<Integer, List<Integer>> as result
- TODO: code it up.

#### Previous Solution
- removing leaf && edge



---

**73. [Longest Substring Without Repeating Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20Without%20Repeating%20Characters.java)**      Level: Medium      Tags: [Hash Table, String, Two Pointers]
      
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

**74. [Fraction to Recurring Decimal.java](https://github.com/awangdev/LintCode/blob/master/Java/Fraction%20to%20Recurring%20Decimal.java)**      Level: Medium      Tags: [Hash Table, Math]
      
TODO: no need of hashMap, just use set to store the existing

不难想到处理除法：考虑正负，考虑小数点前后。主要是小数点以后的要着重考虑。
很容易忽略的是integer的益处。


---

**75. [Wiggle Sort.java](https://github.com/awangdev/LintCode/blob/master/Java/Wiggle%20Sort.java)**      Level: Medium      Tags: [Array, Sort]
      
方法1:
排序, nLog(n). 然后把直线上坡变成层叠山峰, 需要每隔几个(题目中是每隔2位)就做个swap 造成高低不平.
Note: 每隔山峰之间是相互没有关系的, 所以每次只要操心 [i], [i-1]两个位置就好了.

方法2:
O(n)
看好奇数偶数位的规律, 然后根据题目给出的规律, 跑一遍, 每次只关注两个位置: 把不合适的[i], [i-1]调换位置就好了.

方法3:
跟法2一样, 只是更巧妙一点罢了:
第一遍想太多. 其实做一个fall-through就能把问题解决，原因是因为：
这样的fall-through每次在乎两个element，可以一口气搞定，无关乎再之前的elements。
特别的一点：flag来巧妙的掌控山峰和低谷的变化。又是神奇的一幕啊！
这样子的奇观，见过就要知道了，没见过的时候有点摸不着头脑。



---

**76. [Reverse Words in a String II.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Words%20in%20a%20String%20II.java)**      Level: Medium      Tags: [String]
      
#### In-place reverse
- reverse用两回. 全局reverse。局部:遇到空格reverse
- 注意ending index: `i == str.length - 1`, 结尾点即使没有' '也要给reverse一下最后一个词




---

**77. [Reorder List.java](https://github.com/awangdev/LintCode/blob/master/Java/Reorder%20List.java)**      Level: Medium      Tags: [Linked List]
      
给一个Linked list, reorder: 从head/tail 两个方向 向中间进发, re-order like: one node at a time,

#### Linked List 功能大集合
- reverse list, find mid of list, merge two list
- 先find mid, 然后把 mid.next reverse了, 最后merge 两段.
- 注意, 用完mid.next之后, 一定要 mid.next = null, 不然merge会出问题



---

**78. [Friends Of Appropriate Ages.java](https://github.com/awangdev/LintCode/blob/master/Java/Friends%20Of%20Appropriate%20Ages.java)**      Level: Medium      Tags: [Array, Math]
      
#### Array, Math
- 这个问题更在于问题本身的分析 (而且还有多余条件); 最终的for loop 也比较不standard.
- People younger than 15 cannot make requests due to the first rule.
- From the age of 15, people can make requests to the same age: a[i] * (a[i] - 1) requests.
- People can make requests to younger people older than 0.5 * i + 7: a[j] * a[i] requests.
- The third rule is redundant as the condition is already covered by the second rule.
- TODO: the approach.



---

**79. [Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Subsequence.java)**      Level: Medium      Tags: [Binary Search, Coordinate DP, DP, Memoization]
      

无序数组, 找最长的上升(不需要连续)数组 的长度. 先做O(n^2), 然后可否O(nLogN)?

#### DP, double for loop, O(n^2)
- 找subsequence: 不需要continous, 可以skip candidate
- 考虑nums[i]结尾的时候, 在[0, i), dp[i - 1] 里count有多少小于nums[i]
- dp[i]: 到i为止 (对于所有 j in [0, i], 记录max length of increasing subsequence
- max需要在全局维护: nums是无序的, nums[i]也可能是一个很小的值, 所以末尾dp[i]并不是全局的max, 而只是对于nums[i]的max.
- 正因此, 每个nums[i]都要和每个nums[j] 作比较, j < i.
- dp[i] = Maht.max(dp[i], dp[j] + 1); j = [0 , i - 1]
- 时间复杂度  O(n^2)

#### O(nLogN)
- 维持一个list of increasing sequence
- 这个list其实是一个base-line, 记录着最低的increasing sequence.
- 当我们go through all nums的时候, 如果刚好都是上升, 直接append
- 如果不上升, 应该去list里面, 找到最小的那个刚好大于new num的数字, 把它换成num
- 这样就完成了baseline. 举个例子, 比如替换的刚好是在list最后一个element, 等于就是把peak下降了, 那么后面其他的数字就可能继续上升.
- '维护baseline就是一个递增的数列' 的证明, 还没有仔细想.



---

**80. [Majority Number III.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Number%20III.java)**      Level: Medium      Tags: [Hash Table, Linked List]
      
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

**81. [Search Range in Binary Search Tree .java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20Range%20in%20Binary%20Search%20Tree%20.java)**      Level: Medium      Tags: [BST, Binary Tree]
      
给一个BST, integer range (k1, k2), 找range 里面所有的integer.

#### BST
- 等于dfs遍历了所有k1<= x <= k2的x node。
- dfs left, process root, then dfs right
- 这里, 把 left/right/match的情况全部cover了，然后把k1,k2的边框限制好，中间就全部遍历了。



---

**82. [Subsets II.java](https://github.com/awangdev/LintCode/blob/master/Java/Subsets%20II.java)**      Level: Medium      Tags: [Array, BFS, Backtracking, DFS]
      

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

**83. [One Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/One%20Edit%20Distance.java)**      Level: Medium      Tags: [String]
      
如果S, T只用一个operation就能变成相等, return true.

#### Edit: 删除，增加，和替换
- 换完之后，理论上换成的String 就应该全等
- for loop, 一旦找到不一样的char, 就判断那三种可能性: insert/delete/replace
- insert/delete 对于2个string来说, 效果是类似的
- O(n)



---

**84. [Segment Tree Modify.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Modify.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Lint, Segment Tree]
      
给一个segmentTree, node里面存max. 写一个modify function: modify(node, index, value).

#### Segment Tree, Divide and Conquer
- Recursively 在segment tree里面找index, update it with value.   
- 每个iteration，很可能（要么左手，要么右手）max就变了。所以每次都left.max and right.max compare一下
- 最后轮回到头顶，头顶一下包括头顶，就全部都是max了



---

**85. [Container With Most Water.java](https://github.com/awangdev/LintCode/blob/master/Java/Container%20With%20Most%20Water.java)**      Level: Medium      Tags: [Array, Two Pointers]
      
#### Two Pointers
- 木桶理论。盛水的最高取决于最低的那面墙。
- 左右两墙，往中间跑动。
- 另:若一面墙已经小于另外一面，就要移动，换掉矮墙（可能下一面更高，或更低)
- 但决不能换掉当下的高墙，因为低墙已经limit的盛水的上限，若高墙移动，导致两墙之间距离减少，就注定水量更少了。（弄啥来，不能缺心眼啊）



---

**86. [Word Ladder.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Ladder.java)**      Level: Medium      Tags: [BFS]
      
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

**87. [Single Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Single%20Number%20II.java)**      Level: Medium      Tags: [Bit Manipulation]
      
一串数字里面, 所有数字都重复三次, 除了一个数字. 找到这个数字, linear time, without extrace space (constant space)

TODO: bits



---

**88. [Kth Smallest Element in a BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Element%20in%20a%20BST.java)**      Level: Medium      Tags: [BST, DFS, Stack, Tree]
      
#### Iterative + stack: inorder traversal
- 很容想到Inorder-binary-search-tree Traversal
- Iterative 稍微难想点：先把最左边的add， pop() stack， 加上右边（如果存在）； 下一个轮回，如果又左孩子，又是一顿加。

#### Recursive + DFS
- 然后稍微优化一下，确保rst.size() == k 时候，就可以return了
- check leaf => dfs left => add root => dfs right



---

**89. [Coins in a Line II.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20II.java)**      Level: Medium      Tags: [Array, DP, Game Theory, Memoization, MiniMax]
      
给一串coins, 用values[]表示; 每个coin有自己的value. 先手/后手博弈,
每次只能 按照从左到右的顺序, 拿1个或者2个棋子, 最后看谁拿的总值最大.

MiniMax的思考方法很神奇, 最后写出来的表达式很简单

#### DP, Game Theory 自考过程比较长
- 跟Coins in a line I 不一样: 每个coin的value不同.
- 用到MiniMax的思想, 这里其实是MaxiMin. Reference: http://www.cnblogs.com/grandyang/p/5864323.html
- Goal: 使得player拿到的coins value 最大化.
- 设定dp[i]: 从index i 到 index n的最大值. 所以dp[0]就是我们先手在[0 ~ n]的最大取值 
- 于此同时, 你的对手playerB也想最大化, 而你的选择又不得不被对手的选择所牵制.
- 用MaxiMin的思想, 我们假设一个当下的状态, 假想对手playerB会做什么反应(从对手角度, 如何让我输)
- 在劣势中(对手让我输的目标下)找到最大的coins value sum


##### 推算表达式
- Reference里面详细介绍了表达式如何推到出来, 简而言之:
- 如果我选了i, 那么对手就只能选(i+1), (i+2) 两个位置, 而我在对方掌控时的局面就是min(dp[i+2], dp[i+3])
- 如果我选了i和(i+1), 那么对手就只能选(i+2), (i+3) 两个位置, 而我在对方掌控时的局面就是min(dp[i+3], dp[i+4])
- 大家都是可选1个或者2个coins
- 目标是maximize上面两个最坏情况中的最好结果

##### 简化表达式
- 更加简化一点: 如果我是先手, dp[i]代表我的最大值.
- 取决于我拿了[i], 还是[i] + [i+1], 对手可能是dp[i + 1], 或者是dp[i+2]
- 其实dp[i] = Math.max(sum - dp[i + 1], sum - dp[i + 2]);
- 这里的sum[i] = [i ~ n] 的sum, 减去dp[i+1], 剩下就是dp[i]的值没错了

##### Initialization
- 这个做法是从最后往前推的, 注意initialize dp末尾的值.
- dp = new int[n + 1]; dp[n] = 0; // [n ~ n]啥也不选的时候, 为0.
- sum = new int[n + 1]; sum[n] = 0; // 啥也不选的时候, 自然等于0
- 然后记得initialize (n-1), (n-2)

##### 时间/空间
Time O(n)
Space O(n): dp[], sum[]



---

**90. [Partition List.java](https://github.com/awangdev/LintCode/blob/master/Java/Partition%20List.java)**      Level: Medium      Tags: [Linked List, Two Pointers]
      
#### Linked List
- linked list 不能像partitioin array一样从两边遍历
- 把小于value的加在前半段, 把 >= value的加在后半段
- 做法很普通: 建造两个list, midTail pointer, post pointer
- 把满足条件（<x, >=x）的数字分别放到两个list里面
- 记得用dummyNode track head.
- 最终midTail.next = post链接起来。



---

**91. [Wood Cut.java](https://github.com/awangdev/LintCode/blob/master/Java/Wood%20Cut.java)**      Level: Medium      Tags: [Binary Search]
      
二分的思想: 判断的是一个 validate() function, 而不是简单的'=='

不需要sort! 为什么呢? 因为我们不是在given array上面二分, 我们是根据最大值在[0, max]上二分.

Overall time: O(nLogM), where M = largest wood length



---

**92. [Connecting Graph III.java](https://github.com/awangdev/LintCode/blob/master/Java/Connecting%20Graph%20III.java)**      Level: Medium      Tags: [Union Find]
      
还是UnionFind的变形, 这次是算有剩下多少个union. 其实非常简单, 维持一个全局变量count:
一开始count=n, 因为全是散装elements;  每次union, count--.



---

**93. [Remove Duplicates from Unsorted List.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicates%20from%20Unsorted%20List.java)**      Level: Medium      Tags: [Linked List]
      
基本方法: O(n) sapce, time
遍历。
遇到duplicate(可能多个),  while直到node.next不是duplicate.
接下去,既然不是duplicate,那就add 进 set


如果不用extra memory, do it in place:
那就要sort linked list. 用merge sort.

复习merge sort:
1. find middle.
2. recursively: right = sort(mid.next); left = sort(head).
3. within sort(), at the end call merge(left, right)


---

**94. [Maximum Size Subarray Sum Equals k.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Size%20Subarray%20Sum%20Equals%20k.java)**      Level: Medium      Tags: [Hash Table, PreSum, Subarray]
      

#### Map<preSumValue, index>
- use `Map<preSum value, index>` to store inline preSum and its index.
- 1. Build presum incline
- 2. Use map to cache current preSum value and its index: `Map<preSum value, index>`
- 3. Each iteration: calculate possible preSum candidate that prior target sequence. ex: `(preSum - k)`
- 4. Use the calculated preSum candidate to find index
- 5. Use found index to calculate for result. ex: calculate range.



---

**95. [The Smallest Difference.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Smallest%20Difference.java)**      Level: Medium      Tags: [Array, Sort, Two Pointers]
      


---

**96. [Unique Binary Search Tree II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree%20II.java)**      Level: Medium      Tags: [BST, DP, Divide and Conquer, Tree]
      
给一个数字n, 找到以(1...n)为node的所有unique BST.

#### BST
- 根据BST规则, divide and conquer
- 取一个value, 然后分两半(start, value - 1), (value + 1, end) 分别dfs
- 然后左右两边的结果cross match

#### DP? Memoization?



---

**97. [Encode and Decode Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Encode%20and%20Decode%20Strings.java)**      Level: Medium      Tags: [String]
      
如题.

#### String
- 'word.length()#word' 这样encode, 可以避免遇到#
- 基于我们自己定的规律, 在decode的里面不需要过多地去check error input, assume所有input都是规范的.    
- decode就是找"#",然后用"#"前的数字截取后面的string.




---

**98. [Remove Duplicates from Sorted List II.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicates%20from%20Sorted%20List%20II.java)**      Level: Medium      Tags: [Linked List]
      
从Linked list 里面摘掉重复元素: 只要重复过, 全部都删掉; 重复出现过得元素一个不留.

#### Linked List
- sorted list, 重复元素都在一起
- 运用 dummyHead: 如果要去掉所有重复元素, 就要有个dummyHead作为局外人在开头牵线
- 只要发现一个 node.val == node.next.val, 就记下这个duplicated val, move forward, 过掉所有重复过的元素
- 思想:
- 用第二个 inner while loop, 把所有的重复元素都处理干净, 然后再move forward
- 优点: outter while loop 不需要考虑太多case, 在inner loop 都把主要的business logic 解决了.

##### 注意DummyHead 的使用
- 当我们有了DummyHead 作为Linked List 的局外线头, 其实可以选择每次遇到duplicate, 就把更加后面的元素 强行assign 给 dummyHead.next 
- 下面还尝试过一种做法: 但是需要考虑的edge case 太多了: 不断移动node, 知道不重复, assign prev.next = node. 
- 这样的做法比较直白, 但是需要考虑很多edge case, 而且并没有很好利用到 dummy head, 注意规避.

##### Previous Note
- 斩草除根。
- 多个node，check node.next ?= node.next.next




---

**99. [Number of Connected Components in an Undirected Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      
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

**100. [Submatrix Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Submatrix%20Sum.java)**      Level: Medium      Tags: [Array, Hash Table, PreSum]
      
给一个int[][] matrix, 找一个sub matrix, where the sum == 0.

#### PreSum的思想
- 算出一个右下角点(i,j)到(0,0)的大小: 上一块 + 左一块 + curr node - overlap area
- preSum[i][j]: sum from (0,0) to (i-1,j-1)
- same approach as `subarray sum`: use hashmap to store diff->index; if diff re-appears, that means sum of 0 has occurred
- sequence of calculation: 1. iterate over start row. 2. iterate over end row. 3. iterate over col number (this is where hashmap is stored based on)
- the iteration over col is like a screening: find previous sum and determine result
- Note: 其实并没有真的去找 `== 0` 的解答,而是根据特性来判断 `剩下的/后来加上的一定是0`



---

**101. [Zigzag Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/Zigzag%20Iterator.java)**      Level: Medium      Tags: [BST]
      
这个题目相对简单. 做的时候我先考虑起来k条怎么办. 那么用个map把index和每个listmark一下就好了。
每次next(), 相应的list的头拿下来就好。
然后就跑圈呗，每次刷一个list头。不难。只要把几个variable维护清楚就行。


---

**102. [Find the Connected Component in the Undirected Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Connected%20Component%20in%20the%20Undirected%20Graph.java)**      Level: Medium      Tags: [BFS, DFS]
      
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

**103. [Number of Airplane in the sky.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Airplane%20in%20the%20sky.java)**      Level: Medium      Tags: [Array, Interval, PriorityQueue, Sort, Sweep Line]
      
#### Sweep Line
- 把Interval拆分成数轴上的Point 
- 起飞mark 1   
- 降落mark -1     
- 用PriorityQueue排序， loop through queue, 计算(起飞+降落)值可能有的max。

#### 注意
- 同时起飞和降落，就是 1 - 1 = 0. 所以在while loop里面有第二个while loop，    
- 当坐标x重合时，在这里做完所有x点的加减，然后再比较 max。     
- 这避免了错误多count，或者少count



---

**104. [Surrounded Regions.java](https://github.com/awangdev/LintCode/blob/master/Java/Surrounded%20Regions.java)**      Level: Medium      Tags: [BFS, DFS, Matrix DFS, Union Find]
      
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

**105. [Unique Word Abbreviation.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Word%20Abbreviation.java)**      Level: Medium      Tags: [Design, Hash Table]
      

给一个string[] dict, 和一个word. 

每个word都可以缩写成固定的abbreviation `<first letter><number><last letter>`(详细看原题)

检查input word是否满足unique

#### HashMap<string, Set>
- 简单算出abbreviatioin
- 检查abbr是否存在; 如果存在, 是不是input word本身.



---

**106. [Ugly Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Ugly%20Number%20II.java)**      Level: Medium      Tags: [DP, Enumeration, Heap, Math, PriorityQueue]
      

#### DP
- curr index is based on previous calculation: the min of all 3 previous factors
- O(n)

#### PriorityQueue, DP
- 非常brutle的。
- 每次把dp[i-1]拿出来，不管三七二十一，分别乘以2,3,5. 出来的结果放进priority queue做比较。
- 最后时间是n*log(n*3)
- 注意：use long, use HashSet确保没有重复
- O(nlogn)




---

**107. [Add Two Numbers II.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Two%20Numbers%20II.java)**      Level: Medium      Tags: [Linked List]
      
Singly-linked list需要reverse, 用stack.
最终结果要恢复成input list 那样的sequence方向, 用stack一个个pop()刚好就可以做到.

加法都一样:
   1. sum = carry
   2. carry = sum / 10
   3. sum = sum % 10;



---

**108. [Sort Colors II.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Colors%20II.java)**      Level: Medium      Tags: [Partition, Quick Sort, Sort, Two Pointers]
      
Sort Color的普通版, sort all k colors in colors array.

Details 参见: https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Color.java

#### Quick Sort
- O(nk)



---

**109. [Segment Tree Query II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query%20II.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Lint, Segment Tree]
      
#### Segment Tree
- 和 Segment Tree Query I 以及其他Segment Tree类似: 这个SegmentTreeNode return count of elements in range
- 这个题目考了validate input source：input 的start,end可能超出root[start,end]。   
- 那么第一步就要先clear一下: 1. 完全不在range就return 0. 2. 有range重合就规整到root的range.




---

**110. [Brick Wall.java](https://github.com/awangdev/LintCode/blob/master/Java/Brick%20Wall.java)**      Level: Medium      Tags: [Hash Table]
      

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

**111. [Shuffle an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Shuffle%20an%20Array.java)**      Level: Medium      Tags: [Permutation]
      
像shuffle music 一样, 做一套shuffle array的functions: 

shuffle() 给出random的permutation

reset() 给出最初的nums

#### Permutation
- Permutation 实际上就是在list/array/... 上面给元素换位置
- 硬换位置, 每次换的位置不同, 用random来找到要换的index
- 维持同一个random seed
- O(n)

##### Note
- compute all permutations 太慢, 不可行.



---

**112. [My Calendar I.java](https://github.com/awangdev/LintCode/blob/master/Java/My%20Calendar%20I.java)**      Level: Medium      Tags: [Array, TreeMap]
      
Given a list of interval as calendar items. Check if newly added calendar item is overlapping.

Understand it is only checking time, but not requiring to insert into right spot. No need to overthink.

#### Simply O(n) check on array
- number of test cases is small, like 1000, so less concern about the time complexity
- simply loop over the list of intervals, and check if any overlapping.
- where to insert does not really matter: every time we are just checking for overlaopping, not merging any range
- **IMPORTANT**: if interval over lapping, they will have this property `Math.max(s1, s2) < Math.min(e1, e2)`. This will help detect the overlapping very easily.
- O(n^2) runtime, with simple code. But somehow this approach is faster than the TreeMap solution: maybe the test cause causes avg O(n)?

#### TreeMap
- One constraint from the simply array solution: it always cost O(n) to find the potential overlapping interval
- We can manually sort and always manually try to find the correct element via binary search, or we could store the interval in a treeMap<startKey, endValue>, where the intervals are sorted by `start`.
- As result, all we need to do for book(start, end) is to find the next element ceiling(start), last element floor(start), and check for overlapping
- This approach also saves the custom data structure
- Overall cost O(nlogn)

##### About TreeMap
- always with key sorted ascendingly 
- more costly than regular HashMap because of the sorting. Building treemap of n items: O(nlogn)

#### Sweep line
- use `Point{int start, end; boolean start}` to mark start/end of class. Add to pq.
- Adding new item to pq, sort, and check if overlapping occurs by counting started classes
- If started classes > 1, that means we overlapped.
- Every time it could consume all classes to find the overlap, O(n^2).
- Not quite need to sort or insert at correct point, and this solution requires longer code. Not quite worthy it for a simple problem.




---

**113. [Evaluate Reverse Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Evaluate%20Reverse%20Polish%20Notation.java)**      Level: Medium      Tags: [Stack]
      

给一个 RPN string list, 根据这个list, 计算结果.

#### Stack
- stack 里面 存数字
- 每次遇到operator, 都拿前2个数字计算
- 计算结果存回到stack里面, 方便下一轮使用.
- Time,Space O(n)




---

**114. [Counting Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Counting%20Bits.java)**      Level: Medium      Tags: [Bit Manipulation, Bitwise DP, DP]
      
给一个数组, 算里面有多少bit 1. 

#### Bitwise DP
- 对于每一个数字, 其实很简单就能算出来: 每次 >>1, 然后 & 1 就可以count 1s. Time: 一个数字可以 >>1 O(logN) 次
- 现在要对[0 ~ num] 都计算, 也就是N个数字, 时间复杂度: O(nLogN).
- 用DP来优化, 查找过的number的1s count, 存下来在 dp[number]里面.
- 计算你顺序从 0 -> num, count过的数字就可以重复利用.
- Bit题目 用num的数值本身表示DP的状态.
- 这里, dp[i] 并不是和 dp[i-1]有逻辑关系; 而是dp[i] 和dp[i>>1], 从binary representation看出有直接关系.



---

**115. [Sort Letters by Case.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Letters%20by%20Case.java)**      Level: Medium      Tags: [Partition, Sort, String, Two Pointers]
      
给一串字符(ASCII 大写, 小写字母), 要求sort 小写字母, 在大写字母前面. 

字母间的前后顺序无所谓, 也不需要preserve original order .

跟sort color分成相似.

#### Partition + Two pointers
- 其实就是quick sort里面的partition function的简化版
- Two pointers, 找一个 pivot 'a' 来区分大写小写字母
- ASCII code 里面 大写字母在小写字母前面, 数字更小
- 然后 while, move start++, end--,
- 每一轮都swap

#### Two pointers
- 直接用两个 pointer left/right 标记开头结尾
- 每次遇到 `>= 'a'` 就是小写字母, swap(chars, i, left);
- 每次遇到 `< 'a'` 就是大写字母, swap(chars, i, right);
- 注意: 每次处理完left swap, 任由for loop i++, 因为确定 [0 left] 都是准确的
- 每次处理完 right swap, 我们不确定从 right index 换过来的是不是正确的, 所以 i--, 跟for loop 的 i++抵消.
- 写 while loop 的 solution看起来更容易理解.



---

**116. [Two Sum II - Input array is sorted.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Sum%20II%20-%20Input%20array%20is%20sorted.java)**      Level: Medium      Tags: [Array, Binary Search, Two Pointers]
      
升序array, 找2SUM.

#### Two pointers
- 排序好的array. Two pointer移动start和end，核查sum.
- 注意sum用long.
- O(n) time

#### Binary Search, 因为已经排好序了啊
- 定住一个valueA, 然后在剩下的里面 binary serach 找 (target - valueB)
- for loop O(n), binary search O(logn)
- overall time: O(nLogN), 就不写了



---

**117. [Insertion Sort List.java](https://github.com/awangdev/LintCode/blob/master/Java/Insertion%20Sort%20List.java)**      Level: Medium      Tags: [Linked List, Sort]
      
input一串数字, 需要出sorted output. 每次insert一个数字时, 都要放到正确的sorted的位置

每次insertion的时候, 都从input里面减掉这个数字

#### Linked List
- 把list里面每个元素都拿出来，scan and insert一遍
- Time O(n^2), worst case, 每次放入n个数字里面的element, 刚好都是最大的
- 所以每次要traverse n nodes, 然后走n次

##### 思考方法
- 如果已经有个sorted list, insert一个element进去。怎么做？
- while 里面每个元素都小于 curr, keep going
- 一旦curr在某个点小了，加进去当下这个空隙。



---

**118. [Interval Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Sum.java)**      Level: Medium      Tags: [Binary Search, Lint, Segment Tree]
      
给一串数字 int[], 然后一个query Interval[], 每个interval是 [start, end], 找query 区间里的sum.

#### Segment Tree + Binary Search
- 其实是segment tree 每个node上面加个sum
- 记得Segment Tree methods: Build, Query
- Note: 存在SegmentTreeNode里面的是sum.  其他题目可能是min,max,count ... or something else.



---

**119. [Strobogrammatic Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Strobogrammatic%20Number%20II.java)**      Level: Medium      Tags: [DFS, Enumeration, Math, Sequence DFS]
      
TODO: 
1. use list, iterative? keep candidates and populating
2. clean up the dfs code, a bit messy
3. edge case of "0001000" is invalid, right?

#### DFS
- A bit like BFS solution: find inner list, and then combine with outter left/right sides.
- find all solutions, DFS will be easier to write than iterative/BFS
- when n = 1, there can be list of candidates at bottom of the tree, so bottom->up is better
- bottom->up, dfs till leaf level, and return candidates.
- each level, pair with all the candidates
- 其实就是剥皮，一层一层，是一个central-depth-first的，钻到底时候，return n=1,或者n=2的case，然后开始backtracking。
- 难的case先不handle.到底之后来一次overall scan.
- every level have 5 choices of digital pairs to add on sides. Need to do for n-2 times. 
- Time complexity: O(5^n)



---

**120. [The Maze II.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Maze%20II.java)**      Level: Medium      Tags: [BFS, DFS, PriorityQueue]
      
#### BFS
- if already found a good/shorter route, skip
- `if (distMap[node.x][node.y] <= node.dist) continue;`
- This always terminates the possibility to go return to original route, because the dist will be double/higher



---

**121. [Convert Sorted List to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20List%20to%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Linked List]
      
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

**122. [Subarray Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20Closest.java)**      Level: Medium      Tags: [PreSum, PriorityQueue, Sort, Subarray]
      

给一串数字, 找subarray的首尾index, 条件: subarray最接近0.

#### PreSum + index in class
- Can be a 2D array, or a `class Point` to store preSum + index
- Sort preSum: smaller (有可能负数) 靠前, 大数字靠后
- 比较preSum种相连接的两个节点, 找差值min; 因为最接近的两个preSum节点的差值肯定是最小
- min所在的两个节点的index, 就是result candidate: 这两个index可能再原nums里面相差很远
- time O(nlogn), sort
- space: O(n)

#### 为何没有用 map<preSum, index> ?
- 因为map虽然能存 preSum + index, 但是无法有效排序
- 所以用一个class来存这两个信息, 然后合理排序



---

**123. [Best Time to Buy and Sell Stock with Cooldown.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Cooldown.java)**      Level: Medium      Tags: [DP]
      
Sequence DP
跟StockIII很像. 分析好HaveStock && NoStock的状态, 然后看最后一步.



---

**124. [Convert Binary Search Tree to Sorted Doubly Linked List (extra space).java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List%20(extra%20space).java)**      Level: Medium      Tags: [Linked List, Stack, Tree]
      

给一个BST, convert成 sorted doubly DoublyListNode.

#### Inorder Traversal, Linked List
- 会iterative traverse Binary Search Tree（Stack && handle left-dig-down）
- create Doubly-ListNode, 注意用一个dNode作为tail node of the list

##### Iterative inorder traversal
- 在check right node的事后，    
- 不论right == null or != null, 每次都要强行move to right.    
- 如果不node = node.right,     
- 很可能发生窘境：       
- node always  = stack.top(), 然后stack.top()一直是一开始把left 全部遍历的内容。所以就会infinite loop, 永远在左边上下上下。      



---

**125. [Kth Largest Element in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element%20in%20an%20Array.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, MinHeap, PriorityQueue, Quick Sort]
      
kth largest in array

#### PriorityQueue, MinHeap
- Need to maintain k large elements, where the smallest will be compared and dropped if applicable: 
- Maintain k elements with min value: consider using minHeap
- add k base elements first
- Maintain MinHeap: only allow larger elements (which will squzze out the min value)
- Remove peek() of queue if over size
- O(nlogk)


#### Quick Sort
- 用Quick Sort 里面partion的一部分
- sort结束后是ascending的, 那么 n - k 就是第k大. 
- partion的结果是那个low, 去找 low==nums.size() - k， 也就是倒数第K个。    
- 没找到继续partion recursively.
- sort的过程是排一个从小到大的list. (同样的代码还可以好xth smallest，mid变成x就好)
- Steps:
- 每个iteration, 找一个pivot,然后从low,和high都和pivot作比较。    
- 找到一个low>pivot, high<pivot, 也就可以swap了。    
- 得到的low就是当下的partion point了
- Overall O(nlogN), average O(n) for this problem.



---

**126. [HashWithCustomizedClass(LinkedList).java](https://github.com/awangdev/LintCode/blob/master/Java/HashWithCustomizedClass(LinkedList).java)**      Level: Medium      Tags: [Hash Table]
      
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

**127. [Smallest Subtree with all the Deepest Nodes.java](https://github.com/awangdev/LintCode/blob/master/Java/Smallest%20Subtree%20with%20all%20the%20Deepest%20Nodes.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      

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

**128. [Kth Smallest Element in a Sorted Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Element%20in%20a%20Sorted%20Matrix.java)**      Level: Medium      Tags: [Binary Search, Heap]
      

给一个sorted matrix, 找kth smallest number (not distinct)

Related: `Kth Largest Element in an Array`

#### PriorityQueue
- 和Merge K sorted Array/ List 类似：使用PriorityQueue。
- 因为Array的element无法直接找到next,所以用一个class Node 存value, x,y positions.
- Initial O(n) time, also find k O(k), sort O(logn) => O(n + klogn)
- 变型: Kth Largest in N Arrays

#### Binary Search
- we know where the boundary is start/end are the min/max value. 
- locate the kth smallest item (x, y) by cutt off partition in binary fasion: 
- find mid-value, and count # of items < mid-value based on the ascending matrix
- O(nlogn)




---

**129. [Combination Sum III.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20III.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      
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

**130. [Best Time to Buy and Sell Stock with Transaction Fee.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Transaction%20Fee.java)**      Level: Medium      Tags: [Array, DP, Greedy, Sequence DP, Status DP]
      

跟Stock II 一样, 买卖无限, 需先买在卖. 附加条件: 每个sell transaction要加一笔fee.

#### Sequence DP
- 与StockII一样, dp[i]: represents 前i天的最大profit.
- sell 的时候, 才完成了一次transaction, 需要扣fee; 而买入不扣fee.
- model sell on dp[i] day (which depends on dp[i-1]) and each day can be sell/buy => add status to dp[i][status]
- status[0] buy on this day, status[1] sell on this day
- dp[i][0] = Math.max(dp[i-1][0], dp[i - 1][0] - prices[i]);
- dp[i][1] = Math.max(dp[i-1][1], dp[i - 1][1] + prices[i] - fee);
- init: dp[0][0,1] = 0; dp[1][1] = 0; dp[1][0] = - prices;
- return dp[n][1]



---

**131. [Pow(x, n).java](https://github.com/awangdev/LintCode/blob/master/Java/Pow(x,%20n).java)**      Level: Medium      Tags: [Binary Search, Math]
      
傻做就O(n), 要更好就考虑O(logN).
减少重复计算, 一切两半.

注意:
- n/2的奇数偶数
- n的正负
- n == 0的情况, x == 0, x == 1 的情况.


---

**132. [Maximum Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray%20II.java)**      Level: Medium      Tags: [Array, DP, Greedy, PreSum, Sequence DP, Subarray]
      
给一串数组, 找数组中间 两个不交互的 subarray 数字之和的最大值

#### DP
- 考虑两个方向的dp[i]: 包括i在内的subarray max sum. 
- dp[i] 的特点是: 如果上一个 dp[i - 1] + nums[i - 1] 小于 nums[i-1], 那么就舍弃之前, 从头再来:
- dp[i] = Math.max(dp[i - 1] + nums.get(i - 1), nums.get(i - 1));
- 缺点: 无法track全局max, 需要记录max.
- 因为我们现在要考虑从左边/右边来的所有max, 所以要记录maxLeft[] 和 maxRight[] 
- maxLeft[i]: 前i个元素的最大sum是多少 (不断递增); maxRight反之, 从右边向左边
- 最后比较maxLeft[i] + maxRight[i] 最大值
- Space, Time O(n)
- Rolling array, reduce some space, but can not reduce maxLeft/maxRight

#### preSum, minPreSum
- preSum是[0, i] 每个数字一次加起来的值
- 如果维持一个minPreSum, 就是记录[0, i]sum的最小值(因为有可能有负数)
- preSum - minPreSum 就是在 [0, i]里, subarray的最大sum值
- 把这个最大subarray sum 记录在array, left[] 里面
- right[] 是一样的道理
- enumerate一下元素的排列顺位, 最后 max = Math.max(max, left[i] + right[i + 1])



---

**133. [Sort Colors.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Colors.java)**      Level: Medium      Tags: [Array, Partition, Quick Sort, Sort, Two Pointers]
      
给一串数字 nums, 数字代表颜色[0,1,2]; 要求 sort nums, 数字最终按照大小排列. 

虽然叫sort color, 其实就是sort 这些 numbers, 只不过抽象了一下.

#### partition array, the base of quick sort
- partition the array by pivot k = {0, 1, 2}
- 每一次partition都return starting point of the current partition
- 然后根据下一个 color, 去还没有sort 干净的那个部分, 再sort一下就好
- time O(kn), where k = 0 => O(n)
- 这里只是partion, 并不需要recursively quick sort, 所以结果是简单的O(n)

#### One pass
- have two pointers, left/right
- start tracks red, end tracks blue. Swap red/blue to right position, and left++ or right--.
- leave white as is and it will be sorted automatically
- be very careful with index i: when swapping with index right, we do not know what is nums[right], so need to re-calculate index i .
- O(n)
- Note: this one pass solution does not work if there are more than 3 colors. Need to use the regular quick sorty.

#### Counting sort
- TODO: count occurance and reassign array



---

**134. [Predict the Winner.java](https://github.com/awangdev/LintCode/blob/master/Java/Predict%20the%20Winner.java)**      Level: Medium      Tags: [DP, MiniMax]
      
Detailed in `Coins in a Line III`



---

**135. [Connecting Graph II.java](https://github.com/awangdev/LintCode/blob/master/Java/Connecting%20Graph%20II.java)**      Level: Medium      Tags: [Union Find]
      
Lint还不能跑, 全部按照题意和答案document的.



---

**136. [Contains Duplicate III.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate%20III.java)**      Level: Medium      Tags: [BST]
      
给一个unsorted array, 问, 是否有两个element, value相差最大为t,  而两个element的index 相差最大为k.

Note: 虽然题目名字是Contains Duplicate, 但其实要找的两个element不是duplicate, 而是Math.abs(value1 - value2) <= t.

#### TreeSet
- TreeSet还是一个set, 我们用来装已经visit过得item
- 如果window大小超过K, 那么把nums[i - k - 1] 去掉, 并且加上新的element
- 这里有个公式推算: (Math.abs(A-B) <= t) =>>>>> (-t <= A - B <= t) =>>>>>> A >= B - t, A <= B + t
- 也就是说, 如果对于 B = nums[i], 来说, 能找到一个target A, 满足上面的公式, 那么就可以 return true.
- Time O(nLogk), treeSet的大小不会超过k,  而 treeSet.ceiling(), treeSet.add(), treeSet.remove() 都是 O(logK)
- Space O(k)

#### Note
- 与Contains Duplicate II 类似概念. TreeSet有BST 因此可以直接用, 而不用自己构建BST
- 简化题目里面的重要条件 Math.abs(A-B) <= t 而推断出 A >= B - t, A <= B + t
- 并且需要需要用 TreeSet.ceiling(x): return number greater or equal to x. 这个用法要记住吧, 没别的捷径.



---

**137. [Spiral Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Spiral%20Matrix.java)**      Level: Medium      Tags: [Array, Enumeration]
      
从(0,0)坐标, 走完spiral matrix, 把结果存在list里.

#### DX, DY
- Basic implementation, array, enumeration
- 写一下position前进的方向: RIGHT->DOWN->LEFT->UP
- 用一个direction status 确定方向
- 写一个compute direction function 改变方向 `(direction + 1) % 4`
- `boolean[][] visited` 来track走过的地方



---

**138. [Next Closest Time.java](https://github.com/awangdev/LintCode/blob/master/Java/Next%20Closest%20Time.java)**      Level: Medium      Tags: [Basic Implementation, Enumeration, String]
      
给一个时间string"12:09", 用里面的4个integer组合成其他时间string, 目标找最小的next time.

如果组合出的time string 在input time之前, 默认 + 24 hours.

#### String
- enumerate all candidates and filter to keep the correct ones
- String.compareTo(string) -> gives lexicographical comparision



---

**139. [Group Shifted Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Group%20Shifted%20Strings.java)**      Level: Medium      Tags: [Hash Table, String]
      

#### Convert to orginal string
- shit by offset. `int offset = s.charAt(0) - 'a';`
- increase if less than 'a': `if (newChar < 'a') newChar += 26;`

#### Previous notes
- 相同shift规则的string, 能被推算到同一个零起始点，就是共同减去一个char,最后就相等。以此作为key，用HashMap。一目了然。
- 记得根据题目意思，一开始要String[] sort一下。



---

**140. [Coins in a Line.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line.java)**      Level: Medium      Tags: [DP, Game Theory, Greedy]
      
拿棋子游戏, 每个人可以拿1个或者2个, 拿走最后一个子儿的输. 问: 根据给的棋子输, 是否能确定先手的输赢?

Game Theory: 如果我要赢, 后手得到的局面一定要'有输的可能'.

#### DP, Game Theory
- 要赢, 必须保证对手拿到棋盘时, 在所有他可走的情况中, '有可能败', 那就足够.
- 设计dp[i]:表示我面对i个coins的局面时是否能赢, 取决于我拿掉1个,或者2个时, 对手是不是会可能输?
- dp[i] = !dp[i - 1] || !dp[i-2]
- 时间: O(n), 空间O(n)
- 博弈问题, 常从'我的第一步'角度分析, 因为此时局面最简单.

#### Rolling Array
空间优化O(1). Rolling array, %2



---

**141. [Binary Tree Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      
找到binary tree 里的最长 consecutive sequence.

#### DFS
- Divide and Conquer. dfs
- 分开 看左边/右边
- 如果左边满足连续递增的规则, dfs (depth + 1), 如果不满足规则, dfs(depth = 1)
- 右边也是一样
- 对结果跟max作比较, return



---

**142. [The Spiral Matrix II.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Spiral%20Matrix%20II.java)**      Level: Medium      Tags: [Array]
      
#### Move forward till end
- Similar concept as `The Maze`: keep walking until hit wall, turn back
- fix direction `dx[direction % 4]`



---

**143. [Number Of Corner Rectangles.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20Of%20Corner%20Rectangles.java)**      Level: Medium      Tags: [DP, Math]
      
具体看题目: count # of valid rectangles (four corner are 1) in a grid[][].

#### basic thinking + Math
- Fix two rows and count matching columns
- Calculate number rectangles with `combination` concept:
- total number of combinations of pick 2 points randomly: count * (count - 1) / 2

#### DP
- TODO. HOW?

#### Brutle
- O(m^2 * n^2), times out



---

**144. [Queue Reconstruction by Height.java](https://github.com/awangdev/LintCode/blob/master/Java/Queue%20Reconstruction%20by%20Height.java)**      Level: Medium      Tags: [Greedy]
      
别无他法, 只能写一遍例子, 找规律,然后greedy. 
需要写一遍发现的规律比如: 从h大的开始排列, 先放入k小的. 写comparator的时候要注意正确性.
如果要sort, 并且灵活insert:用arrayList. 自己做一个object.
最后做那个'matchCount'的地方要思路清晰, 找到最正确的spot, 然后greedy insert.

O(n) space, O(nLog(n)) time, because of sorting.

可能有简化的余地, 代码有点太长.
比如试一试不用额外空间?



---

**145. [Minimum Swaps To Make Sequences Increasing.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Swaps%20To%20Make%20Sequences%20Increasing.java)**      Level: Medium      Tags: [Coordinate DP, DP, Status DP]
      

#### DP
- 特点: 上一步可能是swaped也可能是fixed
- 考虑A,B之间的现状: `A[i] > A[i - 1] && B[i] > B[i - 1]` 或者 `A[i] > B[i - 1] && B[i] > A[i - 1]`
- 问题: 如何把这个状态变成合理的strick-increasing状态?
- `A[i] > A[i - 1] && B[i] > B[i - 1]`: 1. 已经合理, 也不动.  2. [i], [i-1] 全部都swap
- `A[i] > B[i - 1] && B[i] > A[i - 1]`, 交错开来, 所以调换[i], 或者[i-1]: 1. 换[i-1]. 2. 换[i]
- 注意因为求min, 所以init value应该是 Integer.MAX_VALUE;



---

**146. [Interleaving Positive and Negative Numbers.java](https://github.com/awangdev/LintCode/blob/master/Java/Interleaving%20Positive%20and%20Negative%20Numbers.java)**      Level: Medium      Tags: [Two Pointers]
      
给一串数组 有正负数. 重新排列, 让数组里面 正数 和 负数 相隔开. 原来的order无所谓

#### Two pointer
- 需要知道正负的位置, 所以排序 O(nlogN)
- 考虑: 正数多还是负数多的问题, 举栗子就看出来端倪了
- 然后Two Pointer, swap 
- Time O(nlogn), space O(n)

#### extra space
- 用extra O(n) space, 把正负分成两个list
- 然后分别按照index填回去
- time O(n). space O(n)
- 但是就么有用到Two pointer了



---

**147. [Path Sum IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20IV.java)**      Level: Medium      Tags: [DFS, Hash Table, Tree]
      
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

**148. [Target Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Target%20Sum.java)**      Level: Medium      Tags: [DFS, DP]
      
// 如何想到从中间initialize



---

**149. [Partition Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Partition%20Array.java)**      Level: Medium      Tags: [Array, Quick Sort, Sort, Two Pointers]
      
给一串数字, 和 int k. 根据k的值partition array, 找到第一个i, nums[i] >= k.

#### Two Pointer
- Quick sort的基础. 
- Partition Array根据pivot把array分成两半。
- 从array两边开始缩进。while loop到遍历完。非常直白的implement。
- 注意low/high,或者叫start/end不要越边界
- O(n)
- 注意: 这里第二个inner while `while(low <= high && nums[high] >= pivot) {..}` 采用了 `nums[high] >= pivot`
- 原因是题目要找第一个nums[i] >= k, 也就是说, 即便是nums[i]==k也应该swap到前面去
- 这个跟quick sort 原题有一点点不一样.




---

**150. [Maximum XOR of Two Numbers in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20XOR%20of%20Two%20Numbers%20in%20an%20Array.java)**      Level: Medium      Tags: [Bit Manipulation, Trie]
      
比较难想到. 利用到XOR性质A^B=C, then A=B^C.
1. 枚举可能的A, 2. 然后一个个猜.

1. 枚举A: 因为求MAX肯定是找leading-1最多的数字, 那么枚举A从(1000000...000)开始, 
每次多一位取1或者0
2. 因为枚举A的时候是按照每个bit来, 那么B和C也要以同样数位出现.
这里吧B和C变成了prefix的形式, 放在了set里面. 
跟2sum用hashmap的思想类似, 每次用枚举的 A^B=C, 看看结果C是否已经在set里面. 
如果在, 证明枚举的A可能被B^C得出, 那么就找到了一种情况.

还用到一些技巧: 
mask = (1 << i); // i位mask
mask = mask | (1 << i); // prefix mask



---

**151. [Search for a Range.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20for%20a%20Range.java)**      Level: Medium      Tags: [Array, Binary Search]
      
给sorted array, 有重复数字, 找跟target重合所在的range.

#### Binary Search
- 2个while loop
- 找first/last occurance
- TODO: Can the code be simplified?




---

**152. [Palindrome Permutation II.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Permutation%20II.java)**      Level: Medium      Tags: [Backtracking, Permutation]
      
TODO: need to review permutation

permutation的综合题：    
1. validate Input 是不是可以做palindromic permutation. 这个就是（Palindrome Permutation I）   
2. 顺便存一下permutation string的前半部分和中间的single character(if any)    
3. DFS 做unique permutation: given input有duplicate characters。       



---

**153. [Populating Next Right Pointers in Each Node II.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node%20II.java)**      Level: Medium      Tags: [DFS, Tree]
      

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

**154. [Search a 2D Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20a%202D%20Matrix.java)**      Level: Medium      Tags: [Array, Binary Search]
      
给2D matrix, 每行sorted, 每行的首位都大于上一行的末尾. goal: find target from matrix

#### 2D matrix 转1D array
- 一行一行是从小到大, sorted, 连续的, 可以看做1D sorted array
- Binary Search



---

**155. [[lint]. Merge k Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Merge%20k%20Sorted%20Arrays.java)**      Level: Medium      Tags: [Heap, MinHeap, PriorityQueue]
      

Same as merge k sorted list, use priorityQueue

#### Priority Queue
- 由Merge k sorted list启发。用PriorityQueue,存那k个首发element
- PriorityQueue需要存储单位: 自己建一个Class Node 存val, x, y index.    
- 因为array里没有 'next' pointer，只能存x,y来推next element
- Not sure why `new PriorityQueue<>(Comparator.comparing(a -> a.val));` is slower



---

**156. [[lint]. Segment Tree Build II.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Segment%20Tree%20Build%20II.java)**      Level: Medium      Tags: [Binary Tree, Divide and Conquer, Lint, Segment Tree]
      
给一个array, 建造segment tree structure, 

每个treeNode 里面存这个range里的 max value, return root node.

#### Segemnt Tree
- 给的是Array. 注意找区间内的max, assign给区间. 其余和普通的segment tree build一样   
- 注意, segment tree是根据array index range 排位: 根据index in [0, array.length - 1]割开区间, break到底
- 最终start==end做结尾
- 这道题要trackmax, 那么在leaf node assign max=A[start] or A[end]
- 往上,parent一层的max:就是比较左右孩子,其实都是在两个sub-tree里面比较sub-tree的max。   

- Devide and Conquer
- 先分，找到left/right，比较max,在create current node,再append到当前node上面。
- 实际上是depth-first, 自底向上建立起的。



---

**157. [[lint]. Product of Array Exclude Itself.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Product%20of%20Array%20Exclude%20Itself.java)**      Level: Medium      Tags: [Array, Lint]
      



---

**158. [[lint]. Segment Tree Query.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Segment%20Tree%20Query.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Lint, Segment Tree]
      
给了segment Tree, node里面有Max value, 找[start,end]里面的max

#### Segment Tree, Divide and Conquer
- 根据[start,end]跟 mid of (root.start, root.end) 做比较:
	- 1) [start,end] on LEFT of mid
	- 2) [start, end] on RIGHT of mid
	- 3) [start, end] includes mid: break into 2 queries
		- query [leftNode, start, node.left.end]
		- query [rightNode, node.right.start, end]



---

**159. [[lint]. Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Anagrams.java)**      Level: Medium      Tags: [Array, Hash Table, Lint]
      

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

**160. [[lint]. 3 Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%203%20Sum%20Closest.java)**      Level: Medium      Tags: [Array, Lint, Two Pointers]
      
3Sum 的一种简单形式, 并且都没有找index, value, 而只是找个sum罢了.

double for loop。 2Sum只能用土办法 left/right 2 pointers。 O(n^2)

注意：check closest时候用long, 以免int不够用



---

**161. [[lint]. Heapify.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Heapify.java)**      Level: Medium      Tags: [HashHeap, Heap, Lint, MinHeap]
      
Turn unsorted array into a min-heap array, where for each A[i], 

A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].

#### Heap
- Heap用的不多. 得用一下, 才好理解. 通常default 的PriorityQueue就是给了一个现成的min-heap:
- 所有后面的对应element都比curr element 小。
- Heapify里面的**siftdown**的部分:
- 只能从for(i = n/2-1 ~ 0)， 而不能从for(i = 0 ~ n/2 -1): 必须中间开花，向上跑的时候才能确保脚下是符合heap规则的

#### Heapify/SiftDown做了什么?
- 确保在heap datastructure里面curr node下面的两个孩子，以及下面所有的node都遵循一个规律
- 比如在这里，若是min-heap,就是后面的两孩子都要比自己大。若不是，就要swap。    

#### min-heap的判断规律:
- for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].
- siftdown时：在curr node和两个child里面小的比较。如果的确curr < child, 搞定，break while.   
- 但若curr 并不比child小，那么就要换位子，而且继续从child的位子往下面盘查。    



---

**162. [[lint]. 2 Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%202%20Sum%20II.java)**      Level: Medium      Tags: [Array, Binary Search, Lint, Two Pointers]
      
与 2sum II - input array is sorted类似. 都是sort array, 然后two pointer.

LintCode的题. 注意找的是greater/bigger than target。

由于给定条件允许O(nLogn):   
   sort
   two pointer

while里面two pointer移动。每次如果num[left]+num[right] > target，那么其中所有num[left++]的加上num[right]都>target.   
也就是,num[right]不动，计算加入挪动left能有多少组，那就是: right-left这么多。 全部加到count上去。     
然后right--.换个right去和前面的left部分作比较。



---

**163. [[lint]. Segment Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Segment%20Tree%20Build.java)**      Level: Medium      Tags: [Binary Tree, Divide and Conquer, Lint, Segment Tree]
      
给一个区间[startIndex, endIndex], 建造segment tree structure, return root node.

#### Segment Tree
- Usage
  - which of these intervals contain a given point
  - which of these points are in a given interval
- Recursively build the binary tree
  - 左孩子：（A.left, (A.left+A.rigth)/2）   
  - 右孩子：（(A.left+A.rigth)/2＋1， A.right）   



---

**164. [[tool]. MergeSort.java](https://github.com/awangdev/LintCode/blob/master/Java/[tool].%20MergeSort.java)**      Level: Medium      Tags: [Lint, Merge Sort, Sort]
      

#### Merge Sort
- Divide and conquer, recursively
- 先从中间分段, merge sort 左边 (dfs), merge sort 右边
- 最后merge起来
- merge的时候因为是做int[], 所以没办法必须要O(n) space
- Time O(nlogn), Space O(n)



---

**165. [[tool]. UnionFind.java](https://github.com/awangdev/LintCode/blob/master/Java/[tool].%20UnionFind.java)**      Level: Medium      Tags: [Lint, Union Find]
      

#### Method1: Union Find with Array
- union(), find()
- Path Compresion: store skip father after found, which makes find O(1)

#### Method2: Union Find with HashMap





---

**166. [[tool]. Topological Sorting.java](https://github.com/awangdev/LintCode/blob/master/Java/[tool].%20Topological%20Sorting.java)**      Level: Medium      Tags: [BFS, DFS, Lint, Topological Sort]
      

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

**167. [102. Binary Tree Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/102.%20Binary%20Tree%20Level%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

如题.

#### Method1: BFS
- 最普通,Non-recursive: BFS, queue, 用个queue.size()来end for loop:换行。   
- 或者用两个queue. 当常规queue empty，把backup queue贴上去

#### Method2: DFS
- 每个level都应该有个ArrayList. 那么用一个int level来查看：是否每一层都有了相应的ArrayList。   
- 如果没有，就加上一层。    
- 之后每次都通过DFS在相应的level上面加数字。




---

**168. [347. Top K Frequent Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/347.%20Top%20K%20Frequent%20Elements.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue]
      

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

**169. [142. Linked List Cycle II.java](https://github.com/awangdev/LintCode/blob/master/Java/142.%20Linked%20List%20Cycle%20II.java)**      Level: Medium      Tags: [Cycle Detection, Linked List, Slow Fast Pointer, Two Pointers]
      

#### Slow Fast Pointers
- find slow/fast to detect the meeting point
- find begin node of the cycle: traverse from head, also move slow; utill head/slow meets slow



---

**170. [360. Sort Transformed Array.java](https://github.com/awangdev/LintCode/blob/master/Java/360.%20Sort%20Transformed%20Array.java)**      Level: Medium      Tags: [Math, Two Pointers]
      

#### Two Pointers, Math
- Being able to analys the a*x^2 + b*x graph and find the `peak` or `valley`
- Math basics: x^2 dominates the overall curve so it is up to a to determine:
    - `valley`: if a < 0, both sides will be small and center will be large. Prioritize larger value.
    - `peak`: if a > 0, center will be small and both sides will be large. Prioritize smaller value.
    - starting index being 0 or n-1, is driven by `a`



---

**171. [22. Generate Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/22.%20Generate%20Parentheses.java)**      Level: Medium      Tags: [Backtracking, DFS, Sequence DFS, String]
      

#### DFS
- start with empty string, need to go top->bottom
- 取或者不取`(`, `)`
- rule: open parentheses >= close parentheses
- Note: 在DFS时 pass a reference (StringBuffer) and maintain, instead of passing object (String) and re-create every time
- time: O(2^n), pick/not pick, the decision repat for all nodes at every level
- time: T(n) = 2 * T(n - 1) + O(1) = O(2^n)
- space: < than 2^n results = O(2^n)

#### bottom->up DFS
- figure out n=1, n=2 => build n=3, and n=4
- dfs(n-1) return a list of candidates
- add a pair of `()` to the candidates: either in front, at end, or contain the candidates



---

**172. [236. Lowest Common Ancestor of a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/236.%20Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree.java)**      Level: Medium      Tags: [DFS, Tree]
      

给一个Binary Tree root, 以及两个node p, q. 找 p 和 q 的 lowest common ancestor

#### DFS
- 因为是 binary tree, 所以直接盲目搜索搜索path不efficient, use extra space and waste time
- 巧用DFS来找每一个node的common ancestor. Need the assumption: 1. unique nodes across tree; 2. must have a solution
  - Base Case: 当root == null, p or q is found (`root == p || root == q`)，那么就return the root as LCA
  - 三种情况:
    - 1. leftLCA and rightLCA all found: `each path has found one of p and q node as LCA`. Therefore, curr root is the lowest ancestor
    - 2. One of leftLCA and rightLCA is found: return whichever one found
    - 3. both LCAs are null, return null
- Worst case, visit all nodes to find p q at last level, last two leaves: time O(n), stack space O(n)



---

**173. [1053. Previous Permutation With One Swap.java](https://github.com/awangdev/LintCode/blob/master/Java/1053.%20Previous%20Permutation%20With%20One%20Swap.java)**      Level: Medium      Tags: [Array, Greedy, Permutation]
      

#### Analyze Permutation behavior
- concept similar to `31. Next Permutation`
- 1) first pass: find the one that is in incorrect order
- 2) second pass: find the right spot to swap



---

**174. [56. Merge Intervals.java](https://github.com/awangdev/LintCode/blob/master/Java/56.%20Merge%20Intervals.java)**      Level: Medium      Tags: [Array, PriorityQueue, Sort, Sweep Line]
      


给一串int[Interval] (unsorted), 把所以Interval merge起来.

#### Method1: Sweep Line with Priority Queue
- O(nlogn) time (PriorityQueue), O(n) space     
    - 1. 扫描线+Count: when `count==0`, startFlags==endFlags. 是interval的开头/结尾 (write an example)
    - 2. Note: remember to merge points on same sweep line position
- Comparator: `new PriorityQueue<>(Comparator.comparing(p -> p.val))`;

#### Method2: Sort Intervals and append end logically
- Sort intervals: O(nlogn), extra space O(n) when creating rst list
    - `Arrays.sort(intervals, Comparator.comparing(i -> i[0]));`
    - 找到结尾 interval, 满足条件就可以save
    - 如果不到return的条件, 就继续延伸 interval.end

#### Method3: Sort Interval, Remove overlaop interval & modify interval
- Less applicable when input is `int[][] intervals`, but more applicable when we have `List<int[]> intervals`
- Related example: Insert Interval
- Sort fist, loop over and merge, cut off overlapped interval. 
    - sort by Interval.start: `intervals.sort(Comparator.comparing(interval -> interval.start)); // O(nlogn)`
    - 用两个相连的Interval: curr, next
    - 如果 curr.end覆盖了 next.start: 需要merge. 那么比较一下 curr.end vs. next.end    
    - 一旦merge, 需要remove被覆盖的 next interval: `list.remove(i+1)`
    - 若没有重合，就继续iteration
- time O(nlogn), space O(1)



---

**175. [986. Interval List Intersections.java](https://github.com/awangdev/LintCode/blob/master/Java/986.%20Interval%20List%20Intersections.java)**      Level: Medium      Tags: [Two Pointers]
      



#### Method1: Merge Interval
- There can be 1 overlapping on any interval, calculate the inner intersection: lo(A[i][0], B[j][0]), hi(A[i][1], B[j][1])
    - if low <= hi, a valid intersection exist; add
    - also, if A[i][1] < B[j][1]; that is A[i].end < B[j].end, then i++; otherwise j++
        - because the further-away `end` has been used, so move on.
- O(n)

#### Method2: Sweep line
- code is much more complex (pq, Point, process code... etc) than method1
- we can use point to track open/close, also want to specify if point belongs to A/B
- mark 2 global parameters: aOpen, bOpen.
    - process when A/B close, record if (aOpen, bOpen) has overlaop
    - clear up corresponding global parameter after A/B closes
- sort all pointers in priority queue by index
- Point: {boolean isOpen; int index}
- process the queue and remember to clean up all items on same index
- time: O(nlogn)
- space: O(n)




---

**176. [244. Shortest Word Distance II.java](https://github.com/awangdev/LintCode/blob/master/Java/244.%20Shortest%20Word%20Distance%20II.java)**      Level: Medium      Tags: [Array, Design, Hash Table, Two Pointers]
      

#### Map
- Prep: 存Map<word, index list>
- Process: 相继从两个 index list 里面拿出 p1,p2
    - 根据index的大小, 移动双指针: try to move the pointers closer; always calculate diff
- Optionally: if one list is much larger, do binary search on the larger list



---

**177. [80.Remove Duplicates from Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/80.Remove%20Duplicates%20from%20Sorted%20Array%20II.java)**      Level: Medium      Tags: [Array, Two Pointers]
      
给一个sorted array, 把重复的去掉: 也就是把不重复的按照顺序贴上来, array末尾多余的位置无所谓.

最多可重复出元素的数量不超过2个. return unique item 的长度.

#### Basic 
- sorted array, 重复元素都在一起
- 跟 `Remove Duplicates from Sorted Array` 几乎一模一样, 只不过unique index现在可以 validate 2 位
- 其余一模一样, use index to track unique item; skip if duplicated for more than 2 times
- O(n) time, O(1) space
- 这里也可以真的用2个pointers 写while loop, 但是没有必要, 只是单纯地走一个for loop其实就足够.

#### Follow up: k duplicates, Two Pointers
- when index i and i-1 are diff, use count=1 to start
- in while loop, keep count++ until count==k
- reset when next diff comes in



---

**178. [5. Longest Palindromic Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/5.%20Longest%20Palindromic%20Substring.java)**      Level: Medium      Tags: [DP, String]
      

给一个string, 找到最长的palindrome substring.

Related: Longest Palindromic Subsequence, Palindrome Partioning II

O(n^2) is not too hard to think of. How about O(n)?

#### Method1: DP of interval
- Very similar to `216. Longest Palindromic Subsequence`, but this problem requires solid substring(i+1, j-1) to be palindromic
- Similarly: process i = n-1, from end so [i + 1, j] is always ready to consume
- boolean dp[i][j] to mark range (i, j) as palindrome or not.
- 在计算 dp[i][j]的时候, isPalin[i+1][j-1]应该已经计算过了.
- time: O(n^2) dp
- space: O(n^2)

#### String, Palindrome definition
- 从中间劈开, 遍历i: 从n个不同的点劈开: 每次劈开都看是否可以从劈开出作为palindromic的中点延伸
- palindrome两种情况: odd, even palindrome
- Worst case: 整个string都是相同字符，time complexity变成： 1 + 2 +３　＋　．．．　＋n = O(n^2)



#### O(n) 
- TODO
- https://www.felix021.com/blog/read.php?2040



---

**179. [1007. Minimum Domino Rotations For Equal Row.java](https://github.com/awangdev/LintCode/blob/master/Java/1007.%20Minimum%20Domino%20Rotations%20For%20Equal%20Row.java)**      Level: Medium      Tags: [Array, Greedy]
      


#### Method1: Count all occurrance, and count on overlap indexes
- when there is a value that can cover entire row of size n
    - it must be: `n = countA[i] + countB[i] - overlap[i]`
- Code easy to write and read
- time: O(n)
- space: O(1)

#### Method2: Negative count
- Observation: if A[0] works, no need to check B[0].
- Because if both A[0] and B[0] exist in all dominoes,
    - when you swap A[0] in a whole row,
    - you will swap B[0] in a whole at the same time.
    - The result of trying A[0] and B[0] will be the same.
- time: O(n)
- space: O(1)

#### Method3: positive count Match
- there should exist 1 numbers, that can appear in (A[i], B[i]).
- failure case: there exist at least 1 index, that does not have the common number
- maximum case: there can be 2 numbers, that both will make it work.
- findCommon2, and count them:
    - set.add(A[0], A[B]),
    - if any new one does not exist in set, remove it from set
        - if set is empty() , return -1
- use the 2 numbers from set to do a sweep and count in A, O(n), return the less appearance one.
- time: O(n)
- space: O(1)



---

**180. [207. Course Schedule.java](https://github.com/awangdev/LintCode/blob/master/Java/207.%20Course%20Schedule.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS, Graph, Topological Sort]
      

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

**181. [987. Vertical Order Traversal of a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/987.%20Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree.java)**      Level: Medium      Tags: [BFS, Binary Tree, DFS, Hash Table, Tree]
      
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

**182. [429. N-ary Tree Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/429.%20N-ary%20Tree%20Level%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, Tree]
      

#### BFS
- use queue to hold each level. O(n)



---

**183. [275. H-Index II.java](https://github.com/awangdev/LintCode/blob/master/Java/275.%20H-Index%20II.java)**      Level: Medium      Tags: [Binary Search]
      

找到h-index, 给的citation int[] 已经sorted. h-index 的definition 具体看题目.

Aim to find the lowest index mid, which maximize h = n - mid

#### Binary Search
- H-index的一个简单版, 已经sorted(从小到大), 找target value
- 按定义, 找最后一个 `dictations[mid] >= h`, where `h = n - mid`
- O(logn)



---

**184. [694. Number of Distinct Islands.java](https://github.com/awangdev/LintCode/blob/master/Java/694.%20Number%20of%20Distinct%20Islands.java)**      Level: Medium      Tags: [DFS, Hash Table]
      

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

**185. [152. Maximum Product Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/152.%20Maximum%20Product%20Subarray.java)**      Level: Medium      Tags: [Array, DP, PreProduct, Subarray]
      

从一组数列(正负都有)里面找一串连续的子序列, 而达到乘积product最大值.

#### Method1: DP, Two PreProduct array
- Continuous product can be positive/negative/zero
    - If nums[i] > 0, want prior largest product[i-1] * nums[i]
    - If nums[i] < 0, want prior smallest product[i-1] * nums[i]
    - If nums[i] == 0, product = 0
- `prior product[i-1]: 想到DP
    - 1. 正负数情况, 需要用两个 `PreProduct` array: minProduct[], maxProduct[]
    - 2. continuous prodct: it has to utilize curr nums[i]
        - 是跟nums[x]当下值比较的, 如果当下值更适合, 会舍去之前的continous product, 然后重新开始.
        - Use a global variable to hold overall result.
- Time/Space O (n)
- Space optimization, rolling array
    - maxProduct && minProduct 里面的 index i, 都只能 i - 1相关, 所以可以省去redundant operatoins
    - Time: O(n)
    - space: O(1)

#### Method2: hold `local max at index i` and `local min at index i`
- same concept as method1, but simplified: given that we always have to use nums[i], so only 1 result can be passed on
- FAST, simple to write and read
- time: O(n)
- space: O(1)

#### Failed attempt: `memo[i][j]` of continuous product from index i -> j
- working solution, BUT Time/Space complexity O(n^2) are too much



---

**186. [199. Binary Tree Right Side View.java](https://github.com/awangdev/LintCode/blob/master/Java/199.%20Binary%20Tree%20Right%20Side%20View.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

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

**187. [259. 3Sum Smaller.java](https://github.com/awangdev/LintCode/blob/master/Java/259.%203Sum%20Smaller.java)**      Level: Medium      Tags: [Array, Sort, Two Pointers]
      
1. Similar to 15. 3Sum, but simpler.
1. 只需要count triplet, 但是不需要save triplet, 而且还不需要handle duplicated triplets
1. 发现start, end满足条件时候，(end - start)就是所有 sum <target的情况了。
1. 而一旦 > target, 那么就end--
1. 两层循环, O(n2)



---

**188. [1008. Construct Binary Search Tree from Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/1008.%20Construct%20Binary%20Search%20Tree%20from%20Preorder%20Traversal.java)**      Level: Medium      Tags: [DFS, Tree]
      

#### Method1: Top Down DFS
- This approach highly relies on the preorder rules
    - we can use validation rules to navigate throug hteh preorder array
    - use a global index
- time:  O(n)




---

**189. [151. Reverse Words in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/151.%20Reverse%20Words%20in%20a%20String.java)**      Level: Medium      Tags: [String]
      

#### Method1: Split string by space, then flip 
- Option1: With `s.split(" ")`: No brain, and super fast
- Option2: With `s.split("\\s+")`, it skips space, but slow. Use sb.insert(0, xxx)
- trim() output
- Time, Space: O(n)

#### Method2: Flip entire, then individual, two pointer
- flip entire string, then flip each individual string
- Time, Space: O(n)



---

**190. [855. Exam Room.java](https://github.com/awangdev/LintCode/blob/master/Java/855.%20Exam%20Room.java)**      Level: Medium      Tags: [PriorityQueue, Sort, TreeMap, TreeSet]
      

#### Method1 :PriorityQueue
- Use priority queue to sort by customized class interval{int dist; int x, y;}. 
- Sort by larger distance and then sort by start index
- seat(): pq.poll() to find interval of largest distance. Split and add new intervals back to queue.
- leave(x): one seat will be in 2 intervals: remove both from pq, and merge to a new interval.
- 主方程写出来其实很好写, 就是 split + add interval, 然后 find + delete interval 而已. 最难的是构建data structure
- seat(): O(logn), leave(): O(n)
- `Trick: 构建虚拟 boundary`
    - 如果是开头的seat, 或者是结尾的seat, 比较难handle: 一开始坐在seat=0的时候, 没有interval啊!
    - Trick就是, 我们自己定义个虚拟的座位 `seat=-1`, `seat=N`
    - 一开始有一个 interval[-1, N] 然后就建立了boundary.
    - 从此以后, 每次split成小interval的时候:
    - 遇到 `interval[-1, y]`, distance就是 `(y - 0)`
    - 遇到 `interval[x, N]`, distance就是 `(N - 1 - x)`
    - 当然正常的interval dist 就是 `(y - x) / 2`
- distance 中间点
    - Interval.dist 我们其实做的是 distance的中间点 `(y - x) / 2`
    - 这里的dist是 `距离两边的距离` 而不是 x, y 之间的距离. 这里要特别注意.

#### Method2: TreeSet + TreeMap
- TreeSet<Interval>
- TreeMap<starting Pos, Interval>
- seat(): O(logn)
    - find largest dist with TreeSet.first()
    - break into 2 intervals; save to set and save to map
- leave(x): O(logn)
    - find the interval before starting point x using TreeMap.floorEntry()
    - merge and store back to set/map
- for test case it is slower than PQ, because it saves to 2 data structure



---

**191. [31. Next Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/31.%20Next%20Permutation.java)**      Level: Medium      Tags: [Array, Permutation]
      

#### Permutation Behavior
- Great write up: https://leetcode.com/problems/next-permutation/solution/
- next lexicographically permutation: `smallest` but `larger than curr` permutation:
     - find first low point from right [low]
     - find the slight larger [high] to swap with [low]
     - make sure right side of low is eventually the smallest
- Analyze the use cases, to find next low permutation, 2 major steps:
    - 1) Find `first low/drop candidate` from right
    - 2) Find `first high where nums[high] > nums[low]` from right
    - 3) swap(low, high). 
        - By now, [low, n-1] forms a greater permutation
        - but it is not the smallest, because right side [low + 1, n - 1] is descending
    - 4) reverse(low + 1, n-1) to create ascending slopt on right of low (smallest next lexicographically permutation)
- Corner case: if input array is decending (1st low not found), reverse it all together O(n)
- time: O(n) visit all indexes
- space: O(1) not using additional
- Similar question: `1053. Previous Permutation With One Swap`




---

**192. [518. Coin Change 2.java](https://github.com/awangdev/LintCode/blob/master/Java/518.%20Coin%20Change%202.java)**      Level: Medium      Tags: [Backpack DP, DP]
      

给串数字, target amount, 求总共多少种方式可以reach the amount.

#### DP
- O(MN): M, total target amount; N: size of coins
- 类似于: 网格dp, unique path 里面的2种走法: 从上到下, 从左到右
- 状态: dp[i]: sum of ways that coins can add up to i.
- Function: dp[j] += dp[j - coins[i]];
- Init: dp[0] = 1 for ease of calculation; other dp[i] = 0 by default
- note: 避免重复count, 所以 j = coins[i] as start
- 注意 coins 需要放在for loop 外面, 主导换coin的流程, 每个coin可以用无数次, 所以在每一个sum value上都尝试用一次每个coin

#### knapsack problem: backpack problem



---

**193. [515. Find Largest Value in Each Tree Row.java](https://github.com/awangdev/LintCode/blob/master/Java/515.%20Find%20Largest%20Value%20in%20Each%20Tree%20Row.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

#### Method1: DFS
- faster than BFS, using less space if not couting final rst: stack size, O(logn)
- time: O(n), visit all

#### Method2: BFS with queue
- loop over queue level and record max




---

**194. [253. Meeting Rooms II.java](https://github.com/awangdev/LintCode/blob/master/Java/253.%20Meeting%20Rooms%20II.java)**      Level: Medium      Tags: [Greedy, Heap, PriorityQueue, Sort, Sweep Line]
      

给一串数字pair, 代表会议的开始/结束时间. 找同时又多少个会议发生(需要多少件房间)

#### Method1: sort both start and end times
- Sort start times, and end times in 2 different arrays
- Loop over start time
    - when start[i] < end[endIndex], Count++, need more room
    - start[i] >= end[endIndex], done using some room, move to next end time, endIndex++ (like vacating a room)
- Note: we never decrese count because:
    - what ever count reaches, it is the max
    - since we keep moving endIndex, when start[i] >= end[endIndex], we will just reuse meeting room w/o count++
- time: O(nlogn)
- space: O(n)
- somehow, super fast, over 100%
- inspired by: https://leetcode.com/problems/meeting-rooms-ii/discuss/67855/Explanation-of-%22Super-Easy-Java-Solution-Beats-98.8%22-from-%40pinkfloyda

#### Method2: Sweep Line
- Use sweep line to process, track max count as max # of rooms needed
- 跟 Number of Airpline in the sky是同一道题
- time: O(nlogn)
- space: O(n)

#### Method3: 尝试了一下用一个sorted Array + HashMap
也还行，但是handle edge的时候,HashMap 要小心，因为相同时间start和end的map key 就会重复了。



---

**195. [1161. Maximum Level Sum of a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/1161.%20Maximum%20Level%20Sum%20of%20a%20Binary%20Tree.java)**      Level: Medium      Tags: [BFS, DFS, Graph]
      


#### BFS
- simply calc each level sum with BFS
- top-level is processed first, since we go from top level -> deeper level
    - only update result if sum is truly > global MAX.



---

**196. [221. Maximal Square.java](https://github.com/awangdev/LintCode/blob/master/Java/221.%20Maximal%20Square.java)**      Level: Medium      Tags: [Coordinate DP, DP]
      

只能往右边,下面走, 找面积最大的 square. 也就是找到变最长的 square.

#### DP
- 正方形, 需要每条边都是`一样长度`.
    - 以右下角为考虑点, 必须满足条件: left/up/diagonal的点都是1
    - 并且, 如果三个点分别都衍生向三个方向, 那么最长的 square 边就是他们之中的最短边 (受最短边限制)
- dp[i][j]: max square length when reached at (i, j), from the 3 possible directions
- dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
- init: 每个点都可能是边长1, 如果 matrix[i][j] == '1'
- Space, time O(mn)
- Rolling array: [i] 和 [i - 1] 之间的关系, 想到滚动数组优化 space, O(n) sapce.




---

**197. [131. Palindrome Partitioning.java](https://github.com/awangdev/LintCode/blob/master/Java/131.%20Palindrome%20Partitioning.java)**      Level: Medium      Tags: [Backtracking, DFS]
      

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

**198. [222. Count Complete Tree Nodes.java](https://github.com/awangdev/LintCode/blob/master/Java/222.%20Count%20Complete%20Tree%20Nodes.java)**      Level: Medium      Tags: [Binary Search, DFS, Tree]
      

Complete Tree就是说, 最后一个level可能是缺node的(不是说最右下角缺node, 别忘了!)

#### Method1: DFS + Optimization
- 每次看最左left depth和最右leaf depth 是不是一样
    - 如果一样, 直接 2 ^ h - 1就好
    - 不一样的话, 再DFS
- calculate `2^(h)`: 位运算, Math.pow(2, h) = 2 << (h - 1). 神奇!
    - 2 << 1就是把所有bits往左移动一位, 也就是 * 2 
- time: O(n) visit all nodes on 1 side
- space: O(h) visit all nodes on 1 side


#### Method2: Iteratively
- See details in comments inline. 要对tree非常理解
- binary tree one child tree nodes # = 2 ^ h - 1; 所以一个child tree + root = 2 ^ h

#### Method3: Binary Search
- NOT DONE, TODO: https://leetcode.com/problems/count-complete-tree-nodes/solution/



---

**199. [398. Random Pick Index.java](https://github.com/awangdev/LintCode/blob/master/Java/398.%20Random%20Pick%20Index.java)**      Level: Medium      Tags: [Reservior Sampling]
      

#### Reservior sampling
- Random choose: think about reservoir sampling. https://www.youtube.com/watch?v=A1iwzSew5QY
    - Use random generator rd.nextInt(x) pick integer between [0, x)
    - try all numbers, when target is met, we want to model reservoir sampling:
    - item was chosen out of i samples, and all other samples are failed.
- where we can use 'count' to represent the denominator/base to choose.
- `**HAVE TO finish all samples** to make sure equal opportunity`
- we can pick that last matched item as result
- `rd.nextInt(count++) == 0` make sure we are always picking num == 0 to meet definition of reservoir sampling.
- probability theory:
    - If multiply these probablities together to get the probability of one item being chosen with reservior sampling:
    - probability = 1/i * (1 - 1/i+1) * (1 - 1/i+2) ....(1 - 1/n) = 1/n




---

**200. [238. Product of Array Except Self.java](https://github.com/awangdev/LintCode/blob/master/Java/238.%20Product%20of%20Array%20Except%20Self.java)**      Level: Medium      Tags: [Array, PreProduct]
      

给一串数字, output rst[n], 每个index是 除了nums[i]以外 所有itemd的乘积.

#### Array, PreProduct
- 分析普通做法, 了结到用从左到右一遍O(n), 从右到左一遍 O(n) 就可以
- 注意carry的维护
- 第一轮:PreProduct (跟preSum的感觉有点像)
    - PreProduct[i] stores product from num[0] -> num[i-1] (skipping current num[i])
    - init preProduct[i] = 1, as base for product
    - 错过一位操作: always `preProduct[i] *= carry;` and `carry *= nums[i]`
- 第二轮: 从右边乘起, 每次在index i, 收到的carry都是 `nums[i+1] *....* nums[end]`
    - 第一轮的结果 * 第二轮的结果, 刚好在index i 缺少掉 nums[i]. 如题所愿.
- Time: O(n)



---

**201. [1060. Missing Element in Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/1060.%20Missing%20Element%20in%20Sorted%20Array.java)**      Level: Medium      Tags: [Binary Search]
      

#### Binary Search
- total missing nums = nums[curr] - nums[0] - curr
- edge case: if k > total missing nums, then just add the diff from nums[end]
- otherwise, find this `missing count == k` in the nums using binary search
- After binary search: `start + 1 == end`: 
    - re-calculate `count = nums[start] - nums[0] - start;`
    - output final num: `nums[start] + k - count;`
- Option1: always compare total missing nums count
- Option2: compare partial missing nums count (inspired by: https://leetcode.com/problems/missing-element-in-sorted-array/discuss/303444/Java-O(logN)-solution-Binary-Search)




---

**202. [1048. Longest String Chain.java](https://github.com/awangdev/LintCode/blob/master/Java/1048.%20Longest%20String%20Chain.java)**      Level: Medium      Tags: [Bucket Sort, DP, Hash Table, Sort]
      

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

**203. [299. Bulls and Cows.java](https://github.com/awangdev/LintCode/blob/master/Java/299.%20Bulls%20and%20Cows.java)**      Level: Medium      Tags: [Hash Table]
      

#### Solution1: use int[10] to count frequency
1. check match chars
1. check unmatched chars by counting and offset their frequency
    - count++ on secret chars: if secretCount is ever < 0 => `char g` has match, then cows++
    - count-- on guess chars: if guessCount is ever >0 => `char s` has match, then cows++

#### Solution2: Use hashmap to count
- Improvement: since all digit, use int[10] to count



---

**204. [1219. Path with Maximum Gold.java](https://github.com/awangdev/LintCode/blob/master/Java/1219.%20Path%20with%20Maximum%20Gold.java)**      Level: Medium      Tags: [Backtracking, DFS]
      


### DFS, Backtracking
- typical recursive visit all situation




---

**205. [62. Unique Path.java](https://github.com/awangdev/LintCode/blob/master/Java/62.%20Unique%20Path.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP]
      

2D array, 算走到最右下角，有多少种方式.

#### DP, 加法原理
- 计数DP: 2 ways to reach (i,j): dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    - non-overlapping: `dp[i - 1][j]`, `dp[i][j - 1]`
    - covers the only 2 possible way to reach (i,j)
- initialization: dp[i][0] = 1, dp[0][i] = 1
    - Of course, row i = 0, or col j = 0, there is only 1 way to reach
- time O(mn), space O(mn)

##### 滚动数组 Rolling Array
- [i] 只跟 [i - 1] 有关系, 用 curr/prev 建立滚动数组.
- space O(n) 优化空间

#### DFS + Memoization
- move from (0,0) towards (m, n)
- use Map<coordinate, steps> as memoization technique



---

**206. [1091. Shortest Path in Binary Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/1091.%20Shortest%20Path%20in%20Binary%20Matrix.java)**      Level: Medium      Tags: [BFS]
      


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

**207. [1110. Delete Nodes And Return Forest.java](https://github.com/awangdev/LintCode/blob/master/Java/1110.%20Delete%20Nodes%20And%20Return%20Forest.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      

#### Method1: DFS, divide and conquer
- dfs function: have toDelete set, and a result list
- dive deep into child node FIRST, and test if a removal is needed at bottom of tree
- if remove, add orphan and return null; otherwise, return itself
- time: O(n), visit all nodes
- space: O(logn), height of the tree

#### Method2: HashMap, DFS.
- traverse tree and create `map <val, parent>` to fast O(1) removal. O(n)
- set root into a rootSet
- after deleting a node A, the children of the node becomes 2 forests root
    - children should be marked in rootSet
    - also remove node A from rootSet (if appears)
- output: find all root in root set, traverse and output.
- This approach requires a dfs build of parentMap
    - it is same amount of efforts to do the regular dfs removal.
    - not a good solution
- time: O(n)
- space: O(n)



---

**208. [1249. Minimum Remove to Make Valid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/1249.%20Minimum%20Remove%20to%20Make%20Valid%20Parentheses.java)**      Level: Medium      Tags: [Stack, String]
      

#### Stack
- Goal: remove extra '(' or ')' so it is valid.
- Forward thinking: use stack to track '(' and ')', then keep appending partial string to output
- Backward thinking: use stack to filter out false indexes, and remove them in the end




---

**209. [15. 3Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/15.%203Sum.java)**      Level: Medium      Tags: [Array, Sort, Two Pointers]
      
#### sort array, for loop + two pointer
- 处理duplicate wthin triplets: 
    - 如果最外圈的移动点i重复, 一直顺到结尾的最后一个再用.
    - 如果是triplet内有重复, 用完start point, 移动到结尾.
- Note:
   - 1. 找 value triplets, 多个结果。注意，并非找index。    
   - 2. 要升序, 第一层for loop 从最后一个元素挑起, 保证了顺序。    
   - 3. 去掉duplicate: check用过的同样的数字，都跳掉。不需要用同样的数字再计算一边已有结果。
- 时间 O(n^2), 两个nested loop

#### For loop + 2Sum
- HashMap 2Sum. Remember to handle duplicates
   - 1. For loop 挑个数字A
   - 2. 2Sum 出一堆2个数字的结果
   - 3. Cross match 步骤1里面的A



---

**210. [311. Sparse Matrix Multiplication.java](https://github.com/awangdev/LintCode/blob/master/Java/311.%20Sparse%20Matrix%20Multiplication.java)**      Level: Medium      Tags: [Hash Table]
      

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

**211. [322. Coin Change.java](https://github.com/awangdev/LintCode/blob/master/Java/322.%20Coin%20Change.java)**      Level: Medium      Tags: [Backpack DP, DFS, DP, Memoization]
      

给一串不同数额的coins, 和total amount to spent. 求 最少 用多少个coin可以组合到这个amount. 每种coins个数不限量.


#### DP, Bottom -> UP 从小到大的顺序!
- define dp[x], 积累到amount x, 最少用多少个coin
- function: `dp[x] = Math.min(dp[x], dp[x - coinValue] + 1)`. two branches based on choosing coinValue or not
- initialization
    - dp[0], zero amount uses 0 coin. so dp[0] = 0
    - Utilize `Integer.MAX_VALUE` as default val for initialize dp[x]: 1) alert error stage; 2) easy comparison

#### Method2: Memoization, DFS, Top->Down
- create subproblem: (coins, amount - pickedCoin)
- memo[i] 依然表示: min # of coints to make amount i
- initialize memo[i] = Integer.MAX_VALUE
- 先选最后一步(遍历coins),  然后dfs做同样的操作
- 记录memo[amount] 如果已经给过value, 不要重复计算, 直接return.
- time: O(n * S), worst case it runs n coins for S(amount) iterations
- space: O(S)



---

**212. [55. Jump Game.java](https://github.com/awangdev/LintCode/blob/master/Java/55.%20Jump%20Game.java)**      Level: Medium      Tags: [Array, DP, Greedy]
      

给出步数，看能不能jump to end.

#### Greedy
- start from index = 0
    - Keep track of farest can go
    - 一旦 farest >= nums.length - 1, 也就是到了头, 就可以停止, return true.
    - 一旦 farest <= i, 也就是说, 在i点上, 已经走过了步数, 不能再往前跳, 于是 return false
- start from index = n - 1
    - greedy: start from end, and mark last index
    - loop from i = [n - 2 -> 0], where i + nums[i] should always >= last index
    - check if last == 0 when returning. It means: can we jump from index=0 to the end?
- time: O(n)
- space: O(1)

#### DP
- DP[i]: 在i点记录，i点之前的步数是否可以走到i点？ True of false.
- 其实j in [0~i)中间只需要一个能到达i 就好了
- Function: DP[i] = DP[j] && (j + A[j] >= i), for all j in [0 ~ i)
- Return: DP[dp.length - 1];
- time: O(n^2)
- space: O(n)




---

**213. [173. Binary Search Tree Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/173.%20Binary%20Search%20Tree%20Iterator.java)**      Level: Medium      Tags: [BST, Design, Stack, Tree]
      

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

**214. [875. Koko Eating Bananas.java](https://github.com/awangdev/LintCode/blob/master/Java/875.%20Koko%20Eating%20Bananas.java)**      Level: Medium      Tags: [Binary Search]
      


#### Binary Search
- Bianry serach on the min/max value range
- The mid value is calcualted with helper function `calc(piples, k)`
- find celing: `count += (i - 1) / k + 1`, faster than `Math.ceil(i / k)`
- time: O(logm) to find the best velocity, assume total range is m; O(n) for each `calc` call



---

**215. [19. Remove Nth Node From End of List.java](https://github.com/awangdev/LintCode/blob/master/Java/19.%20Remove%20Nth%20Node%20From%20End%20of%20List.java)**      Level: Medium      Tags: [Linked List, Two Pointers]
      

#### Two Pointer
- 1 end pointer to define the window based n steps
- 1 pre pointer to track the node before the targeting node
- when end reaches null, remove nth node: link pre and head.next 



---

**216. [134. Gas Station.java](https://github.com/awangdev/LintCode/blob/master/Java/134.%20Gas%20Station.java)**      Level: Medium      Tags: [Greedy]
      

给一串gas station array, 每个index里面有一定数量gas.

给一串cost array, 每个index有一个值, 是reach下一个gas station的油耗.

array的结尾地方, 再下一个点是开头, 形成一个circle route.

找一个index, 作为starting point: 让车子从这个点, 拿上油, 开出去, 还能开回到这个starting point

#### Greedy
- 不论从哪一个点开始, 都可以记录总油耗, `total = {gas[i] - cost[i]}`. 最后如果total < 0, 无论从哪开始, 必然都不能走回来
- 可以记录每一步的油耗积累, `remain += gas[i] - cost[i]`
- 一旦 remain < 0, 说明之前的starting point 不合适, 也就是说, 初始点肯定在后面的index. 重设: start = i + 1
- single for loop. Time: O(n)

#### NOT DP
- 看似有点像 House Robber II, 但是问题要求的是: 一个起始点的index
- 而不是求: 最后点可否走完/最值/计数



---

**217. [1197. Minimum Knight Moves.java](https://github.com/awangdev/LintCode/blob/master/Java/1197.%20Minimum%20Knight%20Moves.java)**      Level: Medium      Tags: [BFS]
      

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

**218. [493. Reverse Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/493.%20Reverse%20Pairs.java)**      Level: Medium      Tags: [BST, Binary Indexed Tree, Divide and Conquer, Merge Sort, Segment Tree]
      
给一串数字, count total reverse pair `nums[i] > 2*nums[j]`, i < j

This problem can be solved with Merge sort concept, BST, Segment Tree and Binary Indexed Tree. Good for learning/review.

#### Merge Sort, Divide and Conquer
- Using merge sort concept (NOT merge sort impl).
- One very simply desire: if we want to know # elements between [i, j] such that `nums[i] > 2*nums[j]`, it would be so great if array is **sorted**! 
    - If sorted,  fix index i, keep j++ for all `nums[i]/2.0 > nums[j]`
    - We CANNOT just sort entire array. WHY? Because it distrupts the value of curr index i, and the restriction is: `find matching elements on right side of curr index i`
    - BUT, what about just sort `right side of i`, and make sure the subproblem (i+1, end) is solved first?
- 灵感: use merge sort concept.divide and conquer [i ~ n] into 2 sections:
    - 1) solve subProblem(start,mid) & subProblem(mid+1, end). sort the sub array so that it can be used recursively at parent level.
    - 2) solve the curr pblem: for all [i, mid], check against [mid+1, end].
- Question1: does it cover all use cases?
    - First, subProblem(start,mid) & subProblem(mid+1, end) recursively solves its own range
    - Last, the only range is the current level problem check `[i, mid]` against its entire right side range: `[mid+1, end]`. DONE. all covered.
- Question2: what it is okay for `subProblem(start,mid) & subProblem(mid+1, end)` partially sort the array?
    - that is the goal: 1) we want the right side range to be sorted; 2) left range is sorted but it does not matter since we treat [start, mid] as 1 group
- use classic while loop `while(j<=e && nums[i]/2.0 > nums[j])` to count pairs


#### Segment tree
- TODO
- split the array into index-based segment tree, where each element is at leaf
- store min of range: use min to determine if certain range is needed for further query
- query for each element right side range (i + 1, end), where it recursively query&aggregate sub-range if meeting requirement `nums[i] > 2*nums[j]`
- only when target > subRange.min * 2: there are possible candidates, query further
- worst case O(n^2) when all tailing elements are meeting requirement.

#### BST
- TODO
- Build the BST based on node value. It will be not applicable if we search after entire tree is built (our goal is right range), so we need to build right elements, and search/count right after the elements is added
- Worst case is still O(n^2), if all added nodes are meeting requirement 
- search(tree, curr / 2.0)



#### O(n^2)
- check each one of them




---

**219. [1306. Jump Game III.java](https://github.com/awangdev/LintCode/blob/master/Java/1306.%20Jump%20Game%20III.java)**      Level: Medium      Tags: [BFS, Graph]
      

### Method1: BFS
- Find possibility to reach certain point, we can BFS: faster to find shortest candidate
- use queue to hold left, right candidates
- use set to record visited

### Method2: DFS
- attemp all nodes, use set to record visited.
- time: O(n)
- space: O(n)



---

**220. [277. Find the Celebrity.java](https://github.com/awangdev/LintCode/blob/master/Java/277.%20Find%20the%20Celebrity.java)**      Level: Medium      Tags: [Adjacency Matrix, Array, Graph, Greedy, Pruning]
      

有n个人, 其中有个人是celebrity, 注意必要条件 `Celeb knows nobody; Everyone else knows the celeb`. 找到celeb

Note: the relationship graph can be presented as an adjacency matrix, but graph is not directly used in this problem.

#### Pruning
- Given assumption: 1) `only 1 celebrity`, 2) person k, who knows nobody ahead of him or after him.
- if first pass finds candidate, `person k`, it means:
    - person [0, k-1] are not celebrity: they know a previous or current candidate
    - person k knows no one between [k + 1,  n): k+1 to n-1 can not be the celebrity either. 
    - person k is just the last standing possible celebrity
- second pass validation: we do not know if `knows(celeb, [0~k-1] )`. Do a final O(n) check
- time:O(n), space O(1)
- DO NOT: Brutle compare all -> all: O(n^2) handshakes.

##### 思考逻辑
- 先写出来[0 ~ n - 1], 最简单的方式 O(n^2) 检查, 记录每个人的状态.
    - 逐渐发现, 因为 celeb 谁都不会认识, 那么当任何candidate knows anyone, 他自身就不是celeb.
    - 我们可以greedy地, 一旦fail一个, 就立刻假设下一个是celeb candidate
- 最终还是要检查一遍, 避免错漏.
- 想一下happy case: 如果 celeb=0,  那么 know(celeb, i) 永远都是false, 然后 celeb一直保持0, 坚持到verify所有人.



---

**221. [46. Permutations.java](https://github.com/awangdev/LintCode/blob/master/Java/46.%20Permutations.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS, Permutation]
      

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

**222. [1094. Car Pooling.java](https://github.com/awangdev/LintCode/blob/master/Java/1094.%20Car%20Pooling.java)**      Level: Medium      Tags: [Greedy, Heap, PriorityQueue, Sort]
      

#### Method1: bucket sort
- define the bucket by index: the total distance is fixed [0, 1000]
- +/- capacities for each pos and save into the bucket
- go over the bucket and see if the total cap goes over input capacity
- O(n), trips size
- space: O(1), bucket size 1000 is constant
- `IMPORTANT`: before using PQ to sort, consider bucket sort:
    - if the boundary set and seems resonable? i.e., max size = `1000`
    - is the sorted items index based?

#### Method2: Priority Queue, sort distnace
- Like meeting room, merge interval
- process items on same index



---

**223. [245. Shortest Word Distance III.java](https://github.com/awangdev/LintCode/blob/master/Java/245.%20Shortest%20Word%20Distance%20III.java)**      Level: Medium      Tags: [Array, Design, Hash Table, Two Pointers]
      

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

**224. [1117. Building H2O.java](https://github.com/awangdev/LintCode/blob/master/Java/1117.%20Building%20H2O.java)**      Level: Medium      Tags: [Lock, Semaphore, Thread]
      

#### Meethod1: Use lock & counter to lock a thread based on counter
- when counter != 2 , it will execute hydrogen() two times so that 'H' will reach 2
- when count == 2, it will execute oxygen() once so that 'O' will reach 2

#### Method2: use Semaphore to manage the life cycle of 'H' and 'O'
- to start: H is at count 2 and O is at count 0. They need both be at 0 to be unlocked
- hydrogen(): 
  - `h.acquire()` will execute 2 times until H.count is reduced to 0
  - `o.release` will add O.count by 1 for 2 times
- oxygen(): 
  - `o.acquire(2)` can only occur when O.count == 2 due to the 2 calls in `hydrogen(..)`
  - `h.release(2)` will restore the H.count back to 2
- semaphore: https://www.geeksforgeeks.org/semaphore-in-java/



---

**225. [973. K Closest Points to Origin.java](https://github.com/awangdev/LintCode/blob/master/Java/973.%20K%20Closest%20Points%20to%20Origin.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, Sort]
      

#### PriorityQueue
- Create customized Point{} class
- Sort by distance
- Maintain queue size <= K

#### Divide and Conquer
- ?, select sort?



---

**226. [200. Number of Islands.java](https://github.com/awangdev/LintCode/blob/master/Java/200.%20Number%20of%20Islands.java)**      Level: Medium      Tags: [BFS, DFS, Matrix DFS, Union Find]
      

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

**227. [567. Permutation in String.java](https://github.com/awangdev/LintCode/blob/master/Java/567.%20Permutation%20in%20String.java)**      Level: Medium      Tags: [Sliding Window, Two Pointers]
      

#### Method1: Sliding window with left/right Pointers
- Sliding window template:
    - 1) Check right pointer and move right
    - 2) Move left when necessary
    - 3) Verify count == 0 & end state
    - Note: normally 2) and 3) are in reversed order; this problem is a bit different
- This is efficient when the number of characters is not limited to 26, the runtime is still O(m + n)
- time: O(m + n), m = s1 length, n = s2 length
- space: O(k), k = # of possible chars, 26 in this case

#### Method2: Two Pointer, but brutle verify freq count
- 如果做s1的permudation, 时间复杂度是O(n!) 肯定不可以
- 这里用HashTable的做法 (因为26字母, 所以用int[26]简化) 来记录window内的 character count
- 如果window内的character count 相等, 那么就是permudation
- 更进一步优化: 找两个map相互对应, 不如用一个 int[26]: s1对遇到的character做加法, s2对遇到的character做减法
- two pointer 运用在 n1, n2 的把控; 以及 s2.charAt(i - n1) 这一步
- time: (m + n)
    - However, if # of possible chars is more than 26
    - For example, `k unique characters`, then the runtime will become: O(m + nk)
    
- space: O(k), k = # of possible chars, 26 in this case



---

**228. [369. Plus One Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/369.%20Plus%20One%20Linked%20List.java)**      Level: Medium      Tags: [Linked List]
      

#### Reverse to make significant digit at tail
- Need add from the back and calculate carry
- Reverse list, so insignificant digit at head; calculate carry
- Reverse back when output



---

**229. [211. Add and Search Word - Data structure design.java](https://github.com/awangdev/LintCode/blob/master/Java/211.%20Add%20and%20Search%20Word%20-%20Data%20structure%20design.java)**      Level: Medium      Tags: [Backtracking, Design, Trie]
      

#### Trie, prefix tree.
- Trie Structure: `boolean isEnd`, `HashMap<Character, TrieNode> children`
    - trie.addWord: 没node就加，有node就移动
    - trie.search: 没node就return false，有node就移动
- Alternatively, the hash can be `TrieNode[26]` a fixed size array when applicable
    - I like map better for the simplicity to write (w/o converting char -> index)




---

**230. [43. Multiply Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/43.%20Multiply%20Strings.java)**      Level: Medium      Tags: [Math, String]
      

给两个integer String, 求乘积

#### String calculation, basic implementation
- let num1 = multipier, num2 = base. mutiply and save into int[m + n]. 
    - Loop over num1, each row num1[x] * num2, save to correct index (i + j + 1)
    - Note: skip leading '0' during output, but do not delete string "0"
    - time,space O(mn)
- Option1: Calculate carry on the fly
    - index `curr = i + j + 1`, left index `left = curr - 1`, since we start calculation from end of the array.
    - **we only touch right side of the array once**, so we can move the carry off from it, and carry to left index
    - code is concise
- Option2: save product first without calculating carry
    - save product in each int index
    - calculate carry on rst[] and `sb.insert(0, c)` (since we start from end of rst)
    - this is actaully faster than Option1, somehow.

#### Reverse, calculate from index 0, and reverse back
- 1. 数字‘123’， 在数组里面， index == 0 是 ‘1’。 但是我们平时习惯从最小位数开始乘积，就是末尾的'3'开始。
- 所以！翻转两个数字先！我去。这个是个大坑。
- 2. 乘积product，和移动Carrier都很普通。
- 3. ！！最后不能忘了再翻转。
- 4. 最后一个看坑。要是乘积是0，就返回‘0’。 但是这个其实可以在开头catch到没必要做到结尾catch。
- 用到几个StringBuffer的好东西: reverse(), sb.deleteCharAt(i)   
- 找数字，或者26个字母，都可以: s.charAt(i) - '0'; s.charAt(i) - 'a';



---

**231. [621. Task Scheduler.java](https://github.com/awangdev/LintCode/blob/master/Java/621.%20Task%20Scheduler.java)**      Level: Medium      Tags: [Array, Enumeration, Greedy, PriorityQueue, Queue]
      

#### PriorityQueue; Greedy
- 正面去做: 
    - count task出现的次数
    - 然后PQ sort Task object in descending order
- 每个section: k slots = n + 1. Same task being n slots apart, meaning one section has n + 1 slots.
    - 目标是穷尽 k, or 穷尽 pq (poll k times, but will save it back to queue if Task # > 0)
    - 如果qp 真的穷尽, break, return count
    - 不然, count += k, where k are just # of idle intervals
- time O(n) + constant time O(xlogx), where x = 26
- extra space O(x) ~ O(1)


#### Array, count frequency, enumerate
- Enumerate to understand: 
    - 1.module tasks in module/section; 
    - 2.Only need sum the intervals/slots, not return actual layout
    - Perfect case: all letters appear identical # times: just line them up separate in order.
    - Real case: task appears different times
- 1. Place maxCount task as header followed with n slots: define (maxCount-1) sections
- 2. For tasks with less # than maxCount# can fill the (maxCount-1) sections; what about the tail section?
- 3. Any task with same maxTask#, of if prior sections all filled, will fill the tail section
- To count overall slots/intervals, come up with this equation:
    - 1. Fixed sections: `(maxCount - 1) * (n + 1)`
    - 2. Plus all repeating maxCount tasks: calculate by couting identical maxCount of them
    - 3. Exception: if the first (max - 1) sections are all filled completely, and we still have extra task (ex: when n is not large enough), then just return tasks.length
- time O(n), space O(1)
- ??? Need to study



---

**232. [47. Permutations II.java](https://github.com/awangdev/LintCode/blob/master/Java/47.%20Permutations%20II.java)**      Level: Medium      Tags: [Backtracking, DFS]
      
space: O(n!)

给一串数组, 找出所有permutation数组. 注意: 给出的nums里面有重复数字, 而permutation的结果需要无重复.

Similar to 46. Permutations, but with dedup

#### Backtracking
- Sort the list, and on same level, if last element is the same as curr, skip this recursive call
- time O(n!)

#### Non-recursive, manuall swap
- Idea from: https://www.sigmainfy.com/blog/leetcode-permutations-i-and-ii.html
- 用到 sublist sort
- 用 swap function, 在原数组上调节出来新的permutation
- 注意: 每次拿到新的candidate, 都要把没有permutate的数位sort, 然后再开始swap.
- 这是为了确保, [j]和[j-1]在重复时候, 不用重新记录.



---

**233. [332. Reconstruct Itinerary.java](https://github.com/awangdev/LintCode/blob/master/Java/332.%20Reconstruct%20Itinerary.java)**      Level: Medium      Tags: [Backtracking, DFS, Graph]
      

#### DFS with backtcking
- Construct graph: `map<String, List<Destination>>`; sort the list of destinations.
- DFS:
    - with any curr city, go over the destination list: `graph.get(curr)`
    - add visit city to rst
    - remove visited city from the desitnation list
    - backtrack
- NOTE: 
    - 1) the graph allows cycle: revisiting same city. Do NOT assume no cycle
    - 2) it asks to us to treat `smaller lexical order city` with priority; however:
        - it does NOT mean visiting `smaller lexical order city` is THE correc anser
        - it can be a leaf sink node of the graph and does not provide correct trip plan
- time: O(n^n). n = # of cities. worst case, each city has (n-1) edges and need to try all combinations
- space: O(n^2), there can at most be n * (n - 1) edges



---

**234. [88. Search in Rotated Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/88.%20Search%20in%20Rotated%20Sorted%20Array%20II.java)**      Level: Medium      Tags: [Array, Binary Search]
      

#### Binary Search
- Also most identical to `33. Search in Rotated Sorted Array`:
    - find where nums[mid] lands by comparing to nums[start]. i.e., if nums[mid] < nums[start], on right half of the array
    - when `nums[mid] == nums[start]`: duplicate. Shift by start++
- the worst case of `nums[mid] == nums[start]` willl cause O(n),
- but if duplicate is not entire array, should be O(logn)



---

**235. [39. Combination Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/39.%20Combination%20Sum.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      

给一串数字candidates (no duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 可以用任意多次.


#### DFS, Backtracking
- 考虑input: 没有duplicate, 不需要sort
- 考虑重复使用的规则: 可以重复使用, 那么for loop里面dfs的时候, 使用curr index i
- the result is trivial, save success list into result.
- Time and Space complexity:
    - transform the analysis as for `40. Combination Sum II`
    - Since this problem allows reuse of elemenets, assume they exist in original input as duplicates
    - time: O(k * 2^n), k = avg rst length
    - space: O(k) stack depth, if not counting result size



---

**236. [144. Binary Tree Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/144.%20Binary%20Tree%20Preorder%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Stack, Tree]
      

#### Recursive, DFS, Divide and conquer
- 加root, left, then right. Obvious
- Option1: recursive on preorderTraversal. the dfs function returns List
- Option2: pass in rst, and write a void dfs.

#### Iterative
- 先加root, 然后push上需要末尾process的在stack垫底(root.right), 然后push root.left
- Stack: push curr, push right, push left.   



---

**237. [146. LRU Cache.java](https://github.com/awangdev/LintCode/blob/master/Java/146.%20LRU%20Cache.java)**      Level: Medium      Tags: [Design, Doubly Linked List, Hash Table, Linked List]
      

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

**238. [1040. Moving Stones Until Consecutive II.java](https://github.com/awangdev/LintCode/blob/master/Java/1040.%20Moving%20Stones%20Until%20Consecutive%20II.java)**      Level: Medium      Tags: [Array, Sliding Window]
      


#### Analyze to understand
- Make sure to sort array: we need to use the actual number range `A[j] - A[i]`, which requires the array to  be sorted
- we want to form a new array where A[n-1] - A[0] + 1 == n; order does not matter but all slots need to be filled consecutivly
- max moves: https://leetcode.com/problems/moving-stones-until-consecutive-ii/discuss/289357/c%2B%2B-with-picture
    - A interval will be automatically dropped between A[0] and A[1], if moving A[0] first
    - Same, a interval between A[n-2] and A[n-1] will be dropped when moving A[n-1] first
    - so largest possible move = firstItem + remaining range size - n items = 1 + (A[n-1] - A[1] + 1) - n = A[n-1] - A[1] -n + 2
        - or A[n-2] - A[0] - n + 2
- min moves: `Sliding Window`
    - use slinding window to assume a right pointer, to make sure A[right] - A[left] + 1 < n; otherwise, move left++
    - check # of included stones
    - calculate remaining, which is remaining moves
- Handle min move edge case:
    - Consecutive Array up to right = n - 1; need 2 moves to finish



---

**239. [307. Range Sum Query - Mutable.java](https://github.com/awangdev/LintCode/blob/master/Java/307.%20Range%20Sum%20Query%20-%20Mutable.java)**      Level: Medium      Tags: [Binary Indexed Tree, Segment Tree]
      

#### Segment Tree, devide and conquer
- sample problem for segment tree
- build(), update(), rangeQuery()
    - build and update are standard
    - rangeQuery: handle the range split check
- Null leaf node handling: NO, ideally it will not encounter null leaf.
    - in update/rangeQuery: when final state (`start==end`) is reached, the recursive call ends
    - there is no way for any node to dive futher into null child.
- Range Query concept:
    - Using the input range, sum up everything in the range
    - sometimes the input range cover multiple segments, then dive into the segments (still use original range)
    - once we found a bounded segment (completely surrounded by input range), return segment value.



---

**240. [319. Bulb Switcher.java](https://github.com/awangdev/LintCode/blob/master/Java/319.%20Bulb%20Switcher.java)**      Level: Medium      Tags: [Brainteaser, Math]
      

#### Brainteaser
- https://leetcode.com/problems/bulb-switcher/discuss/77104/Math-solution..

#### Brutle:
- if just impl, it take O(n^2):
- repating: some pos are toggled mutiple times: if we know total times, easy to determin each pos.
- loop over [2, n], count times on each index



---

**241. [12. Integer to Roman.java](https://github.com/awangdev/LintCode/blob/master/Java/12.%20Integer%20to%20Roman.java)**      Level: Medium      Tags: [Basic Implementation, Math, String]
      

#### String, Basic implementation
- Parse each digit based on rules
- 1) parse: analyze the situations


---

**242. [1267. Count Servers that Communicate.java](https://github.com/awangdev/LintCode/blob/master/Java/1267.%20Count%20Servers%20that%20Communicate.java)**      Level: Medium      Tags: [Array, Graph]
      

#### two axis array, cross-reference
- analyze problem, and realize we want to eliminate `isolated servers`
- count row[], count col[]
- cross-reference row[] and col[]: `row[i]==1 & col[j]==1` indicates a isolated server

### DFS brutle
- Unfortunately, this problems checks unconnected items, so dfs needs to brutlely check entire row or column
- Only add if `vertical + horizontal count` > 1
- time: O(mn) * O(m + n)



---

**243. [427. Construct Quad Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/427.%20Construct%20Quad%20Tree.java)**      Level: Medium      Tags: [Tree]
      

#### Basic Impl
- build tree recursively by definition
- O(n^2) time and space due to single visit to all nodes


---

**244. [981. Time Based Key-Value Store.java](https://github.com/awangdev/LintCode/blob/master/Java/981.%20Time%20Based%20Key-Value%20Store.java)**      Level: Medium      Tags: [Binary Search, Hash Table, TreeMap]
      

#### Method1: Binary Search
- use hash to store <key, list of values>
- binary serach on list of values

#### Method2: TreeMap
- use hash to store <key, TreeMap<Timestamp, Value>>
- treemap.floorKey(timestamp) finds the top item below certain timestamp



---

**245. [1026. Maximum Difference Between Node and Ancestor.java](https://github.com/awangdev/LintCode/blob/master/Java/1026.%20Maximum%20Difference%20Between%20Node%20and%20Ancestor.java)**      Level: Medium      Tags: [DFS, Tree]
      

#### Method1: Top-Down DFS
- cache parent max and min => produce current max and min
- pass the max and min to dfs
- compare and return the max of dfs(left), dfs(right)
- time: O(n)
- space: O(logn)
- easy to write, a bit hard to think of 

#### Method2: Bottom-up DFS
- pass up the local (min, max) as object `Val{max, min}`
- easy to think of, but more code to write



---

**246. [78. Subsets.java](https://github.com/awangdev/LintCode/blob/master/Java/78.%20Subsets.java)**      Level: Medium      Tags: [Array, BFS, Backtracking, Bit Manipulation, DFS]
      

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

**247. [380. Insert Delete GetRandom O(1).java](https://github.com/awangdev/LintCode/blob/master/Java/380.%20Insert%20Delete%20GetRandom%20O(1).java)**      Level: Medium      Tags: [Array, Design, Hash Table]
      

#### Hash Table, Swap when removing
- Map: `map<val, index>, Lis: tracks `index->value`
- list maintain 用来 insert/remove/random operations.
- Remove: swap input valueIndex & tialIndex = list.size() -1.
    - list.remove(object) 应该是要O(logn) 做一个search的.
    - list.remove(list.size() - 1) is cheapter



---

**248. [560. Subarray Sum Equals K.java](https://github.com/awangdev/LintCode/blob/master/Java/560.%20Subarray%20Sum%20Equals%20K.java)**      Level: Medium      Tags: [Array, Hash Table, PreSum, Subarray]
      

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

**249. [91. Decode Ways.java](https://github.com/awangdev/LintCode/blob/master/Java/91.%20Decode%20Ways.java)**      Level: Medium      Tags: [DP, Partition DP, String]
      

给出一串数字, 要翻译(decode)成英文字母. [1 ~ 26] 对应相对的英文字母. 求有多少种方法可以decode.

#### Method1: DP, Bottom-Up by calculating base case first
- 加法原理: 根据题意, 有 range = 1 的 [1, 9], range = 2 的 [10~26] 来作为partition.
- there can be 2 final states at dp[i]: single digit or double digit.
    - validate if `single digit`, dp[i] += dp[i - 1]. Last 1 digit can be decoded.
    - validate if `double digit`, dp[i] += dp[i - 2]. Last 2 digits can be decoded.
- note: 
    - get number from char: `c - '0'`
    - validatae single digit != '0', 因为'0' 不在条件之中(A-Z)
- Option1: dp[i] as # ways to decode at index i, including letter i
    - The validation is done on: 1) single digit i, or 2) double digit [i-1, i]
- Option2: Partition DP, dp[i] as # ways to decode for first i letters (excluding letter i)
    - 定义`dp[i] = 前i个digits最多有多少种decode的方法`: new dp[n + 1].
    - dp[i] += dp[i - x], where x = 1, 2
    - The validation is done on: 1) single digit [i-1], or 2) double digit [i-2, i-1]
    - Option2 is better in terms of clean coding. We assume `dp[0]=1` as 1 way to decode 0 digits.
        - No need to specially handle length == 1, because it is covered later
        - No need to manualy init the first 2-digit case as in Option1
        - Return of `dp[n]` is clean
- 引申: 这里只有两种partition的情况 range=1, range =2.  如果有更多partition的种类, 就可能多一层for loop做循环


#### Method2: DFS, Top-Down
- if single-digit is working, sum += dfs(s, i+1);
- if double-digit is working, sum += dfs(s, i+2);
- end case: i >= n, return 0; i == n - 1; i == n - 2
    - especially when i = n - 2, handle 2-digt edge case carefully:
        - 1) check if two-digit range [i, i+1] is valid
        - 2) check if single-digit [i] is valid; if so, += dfs(s, i + 1)
- memo[i]: # ways to decode from [i, n). init with `memo[i]=-1`



---

**250. [145. Binary Tree Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/145.%20Binary%20Tree%20Postorder%20Traversal.java)**      Level: Medium      Tags: [Stack, Tree, Two Stacks]
      

如题, POST-ORDER traversal.

LeetCode给了hard, 应该是觉得stack的做法比较难想到.

#### Method1: DFS, Recursive
- trivial, 先加left recursively, 再加right recursively, 然后组成头部.
- Option1 w/o helper; option2 with dfs helper.

#### Method2, Iterative, Stack
- Option1: reversely add to list
- 双stack的思想, 需要在图纸上画一画
    - 原本需要的顺序是: 先leftChild, rightChild, currNode.
    - 营造一个stack, reversely process: 先currNode, 再rightChild, 再leftChild
    - 这样出来的结果是reverse的, 那么翻转一下就可以了.
- reverse add: `list.add(0, x)`;
- 利用stack的特点
    - 每次加element进stack的时候, 想要在 bottom/后process的, 先加
    - 想要下一轮立刻process的, 最后push进stack.
- Option2: regular sequence add to stack: add curr, right, left
    - Use set to contain the processed children 
    - only process curr if its children is processed




---

**251. [210. Course Schedule II.java](https://github.com/awangdev/LintCode/blob/master/Java/210.%20Course%20Schedule%20II.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Topological Sort]
      

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

**252. [314. Binary Tree Vertical Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/314.%20Binary%20Tree%20Vertical%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Hash Table, Tree]
      

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

**253. [287. Find the Duplicate Number.java](https://github.com/awangdev/LintCode/blob/master/Java/287.%20Find%20the%20Duplicate%20Number.java)**      Level: Medium      Tags: [Array, Binary Search, Binary Search on Value, Cycle Detection, Slow Fast Pointer, Two Pointers]
      

#### Method1: Slow Fast Pointer
- Use LinkedList Cycle Concept:
    - Each element the array is like a `Node {int currIndex; int val;}`, where the `val` is also pointer to next Node
    - A node is like a portal; a pointer can: 1) visit a node by currIndex, 2) pick up newIndex = `nums[currIndex]`, then keep repeating step 1 and 2.
    - Important: since nums is immutable, the pointer footprint is unique/linear
    - Just like linked list. Therefore, use slow/fast pointer to detect cycle.
- https://leetcode.com/problems/find-the-duplicate-number/solution/
- it is now the same as `142. Linked List Cycle II`

#### Method2: Binary Search on value
- 注意不要思维定式: binary search `NOT on index`
    - `binary search on value`: [1, n]
    - O(logN)
- validate(nums, candidate): for loop to count number of `value <= candidate`
    - `count == candidate`: no duplicate from [1 ~ candidate]. 
    - `count < candidate`: missing element in [1~ candidate], so duplicates are in later range. start = mid;
    - `count > candidate`: there are duplicates in [1~ candidate]. end = mid;
- Time: O(nLogN)
- Space: O(1)



---

**254. [103. Binary Tree Zigzag Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/103.%20Binary%20Tree%20Zigzag%20Level%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, Stack, Tree]
      
    
#### Queue
- 简单的level traversal.根据level奇数偶数而add到不同位子.
- Option1: based on level % 2, insert to front/end of list
- Option2: based on level, insert right/left of node into queue



---

**255. [1057. Campus Bikes.java](https://github.com/awangdev/LintCode/blob/master/Java/1057.%20Campus%20Bikes.java)**      Level: Medium      Tags: [Bucket Sort, Greedy, PriorityQueue, Sort]
      

#### Method1: PriorityQueue
- PQ can be used to sort on multiple attributes
- follow the specified rules, and build all possible pairs of visits vs. bike. Pair {int dist, workerIndex, bikeIndex}
- PQ to sort them
    - first by dist
    - if same dist, sort by workerIndex
    - if same workderIndex, sort by bikeIndex
- process all candidates, and skip the ones (workers/bikes) visited 

#### Method2: Bucket Sort
- Similar to using PQ: the goal is to find: 1) min dist; 2) closer worker index, 3) closer bike index
- can use bucket sort to hold all possible distances [0 ~ 2000]: bucket[List of pairs]
    - do a hard iteration (ordered access from min dist). 
- time: O(mn), no need to sort
- space: O(mn)



---

**256. [261. Graph Valid Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/261.%20Graph%20Valid%20Tree.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      
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

**257. [64. Minimum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/64.%20Minimum%20Path%20Sum.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP]
      

#### DP
- Time, Space O(MN)
- 往右下角走, 计算最短的 path sum. 典型的坐标型.
- 注意: init 第一行的时候, 要accumulate dp[0][j - 1] + grid[i][j], 而不是单纯assign grid[i][j]
- Rolling Array
    - Time O(MN), Space O(N)
    - 1) 需要在同一个for loop里面完成initialization, 2) reuse dp[i][j]
    - Reason: dp[i % 2][j] 在被计算出来的时候, 马上在下一轮会被用. 被覆盖前不用,就白算
    - Option3 below initialize dp outside of loop: 看起来固然简单, 但是不方便空间优化

#### DFS (top-down) Thinking process
- Enumerate the possible 2 paths: go right, go down
- sub problem: dfs(i+1,j), dfs(i,j+1); pick the min of the two
- memoization: after the path from a point (i,j) to end is computed, record memo[i][j] to avoid repatative calculation
- time: O(mn), only visite and record memo[i][j] once.
- space: O(mn) extrem case of m=100000, n = 2; the stack height is O(mn)


---

**258. [229. Majority Element II.java](https://github.com/awangdev/LintCode/blob/master/Java/229.%20Majority%20Element%20II.java)**      Level: Medium      Tags: [Array, Moore Voting]
      

#### Two counters, Moore Voting
1. Moore voting: vote可以加减, 一旦为零, 换下一个candidate, 之前抵消掉的算作清零.
1. 一个array里面, 最多也只有2个数字 出现次数大于2次, 所以用A/B表示.
1. count overall apperance at the end for the two items: countA, countB
1. save to result as valA, valB
1. 有点 moore voting的意思: 
    - 当count == 0的时候, reset 
    - 两个candidate A/B都不等, 那么countA--, countB--
1. 最终重新计数, 然后比较出结局.
1. 注意: 按照if statement的顺序, valA&&countA 比valB&&countB有优先性



#### Sort + count
- O(nlogN)



---

**259. [2. Add Two Numbers.java](https://github.com/awangdev/LintCode/blob/master/Java/2.%20Add%20Two%20Numbers.java)**      Level: Medium      Tags: [Linked List, Math]
      

LinkedList都已经反转好了，直接做. 跟Add Binary的理解方式一模一样.

#### Math, Linked List
- reverse order helps calculation
    - add additional carry to end
    - not same length: align on left
- traverse till both ends
- 遍历两个l1,l2把carry-on处理好，每次生成一个新node，最后检查carry-on.




---

**260. [114. Flatten Binary Tree to Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/114.%20Flatten%20Binary%20Tree%20to%20Linked%20List.java)**      Level: Medium      Tags: [Binary Tree, DFS]
      

给一个binary tree, 把tree做成 linked list的形式, in-place.

#### Method1: DFS return tail node
- DFS to return the tail of the flattened list
- Careful handling the null child. Choose one and both works:
    - Option1: put non-null as right child and continue dfs
    - Option2: put non-null as left child and continue dfs

#### Method2: void DFS
- latten the tree, no extra space.
    - 1. Set right node in buffer, and connect: `root.right = root.left`, DFS flatten(root.right) 
    - 3. 移花接木: traverse to tail of the current root.right and attach the buffer node.
    - 3. flatten the remaining of buffer

##### 注意
- 顺序一定要清楚, 不能写错, 写几个example可以看出来
- 移动的那些node, 要把node.left = null, 清扫干净



---

**261. [1004. Max Consecutive Ones III.java](https://github.com/awangdev/LintCode/blob/master/Java/1004.%20Max%20Consecutive%20Ones%20III.java)**      Level: Medium      Tags: [Sliding Window, Two Pointers]
      


#### Sliding window + Left/Right Two Pointers
- https://leetcode.com/problems/max-consecutive-ones-iii/solution/
- Start with DFS thought, but realize redundant calculations: 
    - we never need to flip 2 indexes [A], [C] from 0 -> 1, if there is a [B] in middle that is 0 too
    - the flipped k zeroes must be consecutive too
- we can utilize two pointers to establish a window that captures k zeroes
    - always expend right pointer; if seeing an zero, k--
        - note: `len = right - left + 1` is the ongoing max length
    - when k < 0 (too many zeros), we need to slide the left side of the window to make sure:
        - keep window len 
        - potentially do k++ when A[left]==0
- goal: matain a max size of the window, until right  == n
- return (right - left). at this moment, right == n, so no need to (right - left + 1)



---

**262. [1146. Snapshot Array.java](https://github.com/awangdev/LintCode/blob/master/Java/1146.%20Snapshot%20Array.java)**      Level: Medium      Tags: [Array, Hash Table, TreeMap]
      


#### Hash Table, TreeMap; atomic save
- store efficiently: use List<Map<snapId, val>>. only preserve changed itemd
- if no match, find last modifed item based on snapId, use TreeMap.floorEntry
    - map.floorEntry(id) return the item.key lower or equal to id
- Utilize a `buffer: Map<Integer, Integer>` and perform atomic save



---

**263. [304. Range Sum Query 2D - Immutable.java](https://github.com/awangdev/LintCode/blob/master/Java/304.%20Range%20Sum%20Query%202D%20-%20Immutable.java)**      Level: Medium      Tags: [DP, PreSum]
      

#### Method1: rangeSum/caching
- build rangeSum[i][j]: square range sum from (0,0) to (i,j), O(mn) to init
- query: time O(1)

#### Method2: presum/caching
- build rowPreSum[i][j]: row i sum from [0 ~ j], O(mn) to init
- callign takes O(m); space O(mn)




---

**264. [23. Merge k Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/23.%20Merge%20k%20Sorted%20Lists.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, Linked List, Merge Sort, PriorityQueue]
      

给一个array of ListNode, 把所有node按照大小连成一条.

#### Method1: Divide and Conquer, Merge sort
- By Definition, merge sort: divide the list into 2 parts
- recursively merge them together.
- time complexity: O(nlogk) divide by log(k) times, each recursive call can work on n nodes.
- space: O(logk) stacks 

#### Method2: Priorityqueue
- Iterative, PQ来排列所有list的leading node.
- Note: k lists need to be sorted (luckily, already given)
- 时间：n*O(logk), where n = total node number, and PriorityQueue: logk, 
- Note:
    - 1. 不要忘记customized priority需要一个customized new Comparator<T>()
    - 2. Given list 里面也可能有null node, 不要忘记查.

#### Followup
- 如果k很大，一个机器上放不下所有的k list怎么办？ 
- 如果Merge起来的很长，一个机器上放不下怎么办？




---

**265. [208. Implement Trie (Prefix Tree).java](https://github.com/awangdev/LintCode/blob/master/Java/208.%20Implement%20Trie%20(Prefix%20Tree).java)**      Level: Medium      Tags: [Design, Trie]
      
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

**266. [516. Longest Palindromic Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/516.%20Longest%20Palindromic%20Subsequence.java)**      Level: Medium      Tags: [DFS, DP, Interval DP, Memoization]
      

给一个string s, 找最长的sub-sequence which is also palindrome.

注意！subsequence并不是substring, 是可以skip letter / non-continuous character sequence
    
#### Interval DP
- Use example to understand: for any given ending char, 3 cases of palindromes
    - a. ss[i, j] is a palindrome. dp[i+1][j-1] + 2 since the two indexes are counted, assume dp[i + 1][j - 1] is calculated
    - b. ss[i + 1, j] is a palindrome
    - c. ss[i, j - 1] is a palindrome
- time/space: O(n^2)
- **Option1: start processing substring from tail**
    - set: `i = [n-1 towards 0]`, `j = i + 1`
    - consider ss[i, j], ss[i + 1, j], ss[i, j - 1]
    - since i starts from n - 1 -> 0 and j = i + 1, these are calculated and ready to go: dp[i+1][j-1], dp[i+1][j] and dp[i][j-1]  
    - FAST: skipped the initialization
- **Option2: start processing substring from head**
    - 用[i][j]表示区间的首尾: 考虑3个情况: 砍头, 砍尾, 砍头并砍尾 (考虑首尾关系)
    - Iteration on len:
    - len = j - i + 1; 那么反推, 如果len已知, `j = len + i - 1`;
    - 注意考虑len == 1, len == 2是的特殊情况.


#### Memoization
#### DFS + Memoization
- consider sub problems with 3 major cases
    - a. ss[i, j] is a palindrome: dfs check ss[i + 1, j - 1]
    - b. ss[i + 1, j] maybe a palindrome: dfs check ss[i + 1, j]
    - c. ss[i, j - 1] maybe a palindrome: dfs check ss[i, j - 1]
- memo[i][j]: max palindrome length in range [i, j], if calculated, return directly
- Init memo[i][j] = -1 to track the progress, memoization
    - 注意: init dp[i][j]=-1, dfs的时候查dp[i][j] 是否算过
    - more about dfs: bottom-up, first dive deep into dfs(i+1,j-1) till the base cases.
- Space: O(n^2)
- Time: O(n^2)
- prepare dp[n][n]: O(n^2); dfs: visit all combinations of [i,j]: O(n^2)





---

**267. [430. Flatten a Multilevel Doubly Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/430.%20Flatten%20a%20Multilevel%20Doubly%20Linked%20List.java)**      Level: Medium      Tags: [DFS, Linked List]
      

#### DFS
- Depth-first:
    - 1) process curr.child, return tailChild
    - 2) connect tailChild.next = curr.next
- function: link(Node a, Node b);



---

**268. [63. Unique Paths II.java](https://github.com/awangdev/LintCode/blob/master/Java/63.%20Unique%20Paths%20II.java)**      Level: Medium      Tags: [Array, Coordinate DP, DP]
      

跟unique path的grid一样, 目标走到右下角, 但是grid里面可能有obstacle, 不能跨越. 求unique path 的count.

#### 坐标DP
- dp[i][j]: # of paths to reach grid[i][j]
- Bottom-up: at end, there are 2 ways to reach dp[i][j]
    - dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
- Handle obstacle (cause dp[i][j] to be 0).

#### 坐标DP
- dp[i][j]: # of paths to reach grid[i][j]
- Bottom-up: at end, there are 2 ways to reach dp[i][j]
    - dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
- Handle obstacle (cause dp[i][j] to be 0).



---

**269. [139. Word Break.java](https://github.com/awangdev/LintCode/blob/master/Java/139.%20Word%20Break.java)**      Level: Medium      Tags: [DP, Hash Table, Sequence DP]
      

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

**270. [105. Construct Binary Tree from Preorder and Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/105.%20Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Hash Table, Tree]
      

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

**271. [449. Serialize and Deserialize BST.java](https://github.com/awangdev/LintCode/blob/master/Java/449.%20Serialize%20and%20Deserialize%20BST.java)**      Level: Medium      Tags: [Tree]
      

#### DFS, Divide and Conquer, Preorder (utilizing BST)
- with BST, we can:
  - skip adding the null nodes into the serialized string: `String NULL = "#"`
  - In deserialization: use min/max boundary to check if queue.peek() can be added:
    - if not meeting BST condition, skip this dfs and let other call to consume the queue
- Faster because it shortens the serialized string
  

#### DFS, Divide and Conquer, Preorder (w/o using BST)
- Take reference in Serialize and Deserialize Binary Tree
- The approach works but does not utilize Binary Search Tree properties



---

**272. [274.H-Index.java](https://github.com/awangdev/LintCode/blob/master/Java/274.H-Index.java)**      Level: Medium      Tags: [Bucket Sort, Hash Table, Sort]
      

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

**273. [40. Combination Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/40.%20Combination%20Sum%20II.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      

给一串数字candidates (can have duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 只可以用一次.

#### DFS, Backtracking
- when the input has duplicates, and want to skip redundant items? 考虑重复使用的规则: 不可以重复使用
    - 1. sort.  考虑input: 有duplicate, 必须sort
    - 2. in for loop, skip same neighbor: `if (i > index && candidates[i] == candidates[i - 1]) continue;`
        - 因为在同一个level里面重复的数字在下一个dfs level里面是会被考虑到的, 这里必须skip
- the result is trivial, save success list into result.
- Time complexity: O(k * 2^n), k = average result length
    - 1) Assume on average, there are k elements in result 
    - 2) Since element can be used ONLY once, so the total # of solutions can be `C(n, k)`: `pick k out of n`
    - 3) Now let k be any number [0, n], so total # of solutions can be: `C(n,0) + C(n,1) + C(n,2) + ... C(n,n) = 2^n`
    - 4) Now: `the new ArrayList<>(list)` takes average O(k) time
    - Total: O(k * 2^n)
- Space: O(n), stack depth, if not counting results size



---

**274. [523. Continuous Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/523.%20Continuous%20Subarray%20Sum.java)**      Level: Medium      Tags: [Coordinate DP, DP, Math, PreSum, Subarray]
      

给一个非负数的数列和数字k(可正负, 可为0). 找到连续子序列(长度超过2), 使得这个subarray的sum 是 k的倍数. 问: 是否可能?

#### Method1: Validate Mod Result
- Check if mod result exist in earlier preSum
- Utilize `Pigeonhole principle` to optimize:
    - 1) put positive integers into k slots
    - 2) when # of integers > 2*k, then there must be a range sum that is multipler of k
    - more illustration here: https://leetcode.com/problems/continuous-subarray-sum/solution/
- Draw the presum and try to take mod of each presum and save to set, we realize
    - 1) assume a mod result = 7, and we mark it in the set
    - 2) some time later, after summing up more values, (7 + x + y ...+ z) % k == 7
        - it means `(x + y ...+ z) % k == 0`
        - There is a `整除` exist; return true
- Meanwhile, if we want to record the list of indexes, we can use a Map rather than set.
- Note: if all we do to the presum is to % k, therefore `preSum % k` can represent `presum` in some cases.
- time: O(n)
- space: O(k), size restrited by mod result of `%k`


#### Method2: DP, PreSum
- PreSum[]: 
    - 1) cal preSum array
    - 2) preSum(i, j) = continuous range sum
    - 3) determine if `preSum(i, j) % k == 0`
- time: O(n^2)
- DP (坐标型. specifically, preSum[])
    - 记录在0 ~ i点(包括nums[i], 以nums[i]结尾)的sum, 坐标型动态规划.
    - dp[i] = dp[i - 1] + nums[i];

#### Method3: 直接算结果
- 从sum = 每次[i ~ j]的所有情况
- time: O(n^2)
- space: O(1)



---

**275. [364. Nested List Weight Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/364.%20Nested%20List%20Weight%20Sum%20II.java)**      Level: Medium      Tags: [DFS, NestedInteger]
      

#### Method1: DFS
- Build a list of NestedInt
- DFS:
  - sum up integers in the list are integers
  - dfs on nested list
  - overallSum = sum * (depth+1)
  - End state: if no nested list (no more child dfs), return depth 1
- Parent level: sum up all ints and times the (depth+1)


#### Method2: BFS
- Using stack to flatten all nestedList, and process in the end
- Can actually use list, does not need to be stack.
- uses more memory



---

**276. [49. Group Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/49.%20Group%20Anagrams.java)**      Level: Medium      Tags: [Hash Table, String]
      

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

**277. [438. Find All Anagrams in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/438.%20Find%20All%20Anagrams%20in%20a%20String.java)**      Level: Medium      Tags: [Hash Table, Sliding Window, Two Pointers]
      

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

**278. [138. Copy List with Random Pointer.java](https://github.com/awangdev/LintCode/blob/master/Java/138.%20Copy%20List%20with%20Random%20Pointer.java)**      Level: Medium      Tags: [Hash Table, Linked List]
      

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

**279. [159. Longest Substring with At Most Two Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/159.%20Longest%20Substring%20with%20At%20Most%20Two%20Distinct%20Characters.java)**      Level: Medium      Tags: [Hash Table, Sliding Window, String, Two Pointers]
      

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

**280. [1043. Partition Array for Maximum Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/1043.%20Partition%20Array%20for%20Maximum%20Sum.java)**      Level: Medium      Tags: [DFS, DP, Graph, Memoization]
      

#### Top-Down DFS + Memoization
- Pick a subset (max-size k), and produce sub problem to solve by dfs
- NOTE: no need to change actual index value. That makes this problem easier (no need to record the choice path)
- time: O(n), calc memo[n]
- space: O(n), memo + stack depth



---

**281. [33. Search in Rotated Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/33.%20Search%20in%20Rotated%20Sorted%20Array.java)**      Level: Medium      Tags: [Array, Binary Search]
      

#### Binary Search
- 关键点, 是找到 [mid]是在左边/还是右边的continous increasing subarray: 比较 `A[start] < A[mid]`
- 在两个section 里面分别讨论 target 的位置     
    - 1. `nums[start] < nums[mid]`: start是从index=0开始的, 那就说明 `mid在前半段`
    - 2. `nums[start] > nums[mid]`: start是从index=0开始的, 那就说明 `mid在后半段`
- Binary search template: 
    - 1) `start + 1 < end` (adjacent indexes)
    - 2) start/end = mid, 
    - 3) compare start and end individually

#### binary search break point, 然后继续binary search target
- 1. binay search break point     
- 2. binary search target      
- 注意等号，在判断target在前半段还是后半段：if (A[p1] <= target && target <= A[breakPoint])      





---

**282. [133. Clone Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/133.%20Clone%20Graph.java)**      Level: Medium      Tags: [BFS, DFS, Graph]
      

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

**283. [743. Network Delay Time.java](https://github.com/awangdev/LintCode/blob/master/Java/743.%20Network%20Delay%20Time.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Heap, PQ]
      

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

**284. [636. Exclusive Time of Functions.java](https://github.com/awangdev/LintCode/blob/master/Java/636.%20Exclusive%20Time%20of%20Functions.java)**      Level: Medium      Tags: [Stack]
      

#### Stack
- Task time range: 
    - start range = next task timestamp - start.timestamp
    - end range = curr task timestamp - last task timestamp + 1; because end node is inclusive.
- How to think of using stack: a task cannot finish until end is met; a early task cannot stop until a later task ends
    - Alternatively, we can use a hashmap to track as well
- Keep track of the timestamp
- make sure to +1 when end node is met because end task is inclusive to this finishing task




---

**285. [692. Top K Frequent Words.java](https://github.com/awangdev/LintCode/blob/master/Java/692.%20Top%20K%20Frequent%20Words.java)**      Level: Medium      Tags: [Hash Table, Heap, MaxHeap, MinHeap, PriorityQueue, Trie]
      

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

**286. [426. Convert Binary Search Tree to Sorted Doubly Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/426.%20Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Linked List, Tree]
      

把 BST 转换成一个 sorted doubly linked list. (in-place)

#### Tree, In-order traversal, Divide and Conquer
- Regular convert BST to sored list: in-order traversal
    - Carefully doubly link node head, tail
    - traverse all nodes,  DFS 好做: `left, curr, right`
- Tail:
    - Assume head is found for sub tree, then `tail = head.left`
    - Link `LeftTail <-> Curr Root <-> RightHead`
    - Link `RightTail <-> LeftHead`
- In place:
    - 同 `Node {val, left, right}`, w/o new doubley linked list class
    - different from `Convert Binary Search Tree to Sorted Doubly Linked List (extra space)`



---

**287. [8. String to Integer (atoi).java](https://github.com/awangdev/LintCode/blob/master/Java/8.%20String%20to%20Integer%20(atoi).java)**      Level: Medium      Tags: [Math, String]
      

#### String 
- Handling use cases
- Parse steps:
    - 0. trim space
    - 1 parse operator
    - 2 trim leading zero
    - 3. get number string
- Validation:
    - 1. max length over max integer length
    - 2. exceed min/max value
- Alternatively, regular expression, but not applicable in interview: if (!str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")).  猛了一点



---

**288. [361. Bomb Enemy.java](https://github.com/awangdev/LintCode/blob/master/Java/361.%20Bomb%20Enemy.java)**      Level: Medium      Tags: [Coordinate DP, DP]
      

2D grid, 每个格子里面可能是 'W' wall, 'E' enemy, 或者是 '0' empty.

一个bomb可以往4个方向炸. 求在grid上面, 最大能炸掉多少个敌人.

#### Method1: Corrdinate DP
- Space, Time: O(MN)
- dp[i][j] 就是(i, j)上最多能炸掉的enemy数量
- dp[i][j] 需要从4个方向加起来, 也就是4个方向都要走一遍, 所以分割成 UP/Down/Left/Right 4个 int[][]
- 最后一步的时候求max
- 分方向考虑的方法很容易想到,但是四个方向移动的代码比较繁琐.
- 往上炸: 要从顶向下考虑
- 往下炸: 要从下向上考虑
- 熟练写2D array index 的变换.

#### Method2: Analyze, col count array:
- Inspired by: https://leetcode.com/problems/bomb-enemy/discuss/83387/Short-O(mn)-time-O(n)-space-solution
- Analyize the problem: need to add up 2 directions at any given point.
    - Notice 1: if I traverse row by row, each colSum at a specific col j is likely to be the same
        - Unless there is a Wall in last row, so we have to calclate the col sum starting from current row, below the Wall
    - Notice 2: for row it is the same:
        - If I in a new spot row[i][j], where (i-1) is Wall, i need to sum row from 0
- we will process each point:
    - process row by row and add up row sum
    - buffer col[j] in an array vertically so we can resue
    - make sure to recalculate row sum or col sum if last row index or last col index is Wall
- time: O(mn) traversal
- space: O(n) only use a column array



---

**289. [402. Remove K Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/402.%20Remove%20K%20Digits.java)**      Level: Medium      Tags: [Greedy, Monotonous Stack, Stack]
      

#### Monotonous Stack (Increasing)
- Greedy: Remove 1) earlier digits(数位靠前权值大), 2) large digits
- Keep a increasing stack that:
    - use stack.peek() to guard incoming digit
    - if peek is larger than incoming digit, continue `stack.pop()`
- Result: monotonous increasing stack. Print it in correct order.




---

**290. [98. Validate Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/98.%20Validate%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Tree]
      

验证是否是BST by definition

#### DFS
- 查看每个parent-child关系: 
    - leftchild < root < rightChild
    - all of left child < curr < all of right child
- 方法: 把root.val 传下来作为 max 或者 min, valid child in (min, max)
- BST 有两个极端: left-most-leaf is the smallest element, and right-most-leaf is largest
    - imagine we know the two extreme border: Long.MIN_VALUE, Long.MAX_VALUE
- min/max: long type to meet edge case: node.val = Integer.MAX_VALUE



---

**291. [1123. Lowest Common Ancestor of Deepest Leaves.java](https://github.com/awangdev/LintCode/blob/master/Java/1123.%20Lowest%20Common%20Ancestor%20of%20Deepest%20Leaves.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

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

**292. [921. Minimum Add to Make Parentheses Valid.java](https://github.com/awangdev/LintCode/blob/master/Java/921.%20Minimum%20Add%20to%20Make%20Parentheses%20Valid.java)**      Level: Medium      Tags: []
      


#### Method1: Stack
- use stack to verify the input/output of '(' and ')'
- time, space: O(n)

#### Method1: Simpilfy stack with open parentheses count
- time:(n), space: O(1)



---

**293. [399. Evaluate Division.java](https://github.com/awangdev/LintCode/blob/master/Java/399.%20Evaluate%20Division.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      
#### DFS
- build map of `x#y -> val` to store values[i] and 1/values[i]
- build map of `x -> list children`
- dfs to traverse the graph

#### BFS
- BFS should also work: build graph and valueMap
- for each starting item, add all next candidate to queue
- mark visited, loop until end item is found



---

**294. [785. Is Graph Bipartite.java](https://github.com/awangdev/LintCode/blob/master/Java/785.%20Is%20Graph%20Bipartite.java)**      Level: Medium      Tags: [BFS, DFS, Garph]
      

#### DFS marking each node with a state
- `bipartite` require each node to be in exact 1 party, which means it only has 1 state
- DFS to mark node with one state; and mark its edges as reversed state
  - If any node state has been assigned by different from desired one, return false.

#### BFS, Queue
- Use `Boolean states[i]` to represent visted & state
- Try all nodes with for loop, and skip visited nodes (similar validation rules as in dfs)
- In `int next : graph[curr]`, test next level first before adding.



---

**295. [767. Reorganize String.java](https://github.com/awangdev/LintCode/blob/master/Java/767.%20Reorganize%20String.java)**      Level: Medium      Tags: [Greedy, Hash Table, Heap, Sort, String]
      


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

**296. [71. Simplify Path.java](https://github.com/awangdev/LintCode/blob/master/Java/71.%20Simplify%20Path.java)**      Level: Medium      Tags: [Stack, String]
      

给一个path, simplify成最简单形式. 注意考虑edge case

#### Stack
- 理解unix path:
    - 1. `.` 代表current directory, 可以忽略. 
    - 2. `../` 表示previous level. 
    - 3. double slash 可以忽略.
    - 4. empty string 要output `/`
- parse by '/', and go over using stack
    - put [folder] in stack
    - ".." pop() 1 element of the stack, if anything
    - "." stays the same
- output stack reversely: connect with '/', skip tail



---

**297. [34. Find First and Last Position of Element in Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/34.%20Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array.java)**      Level: Medium      Tags: [Array, Binary Search]
      

#### Binary Search
- need search left bound & right bound.
- use input parameter `direction` to binary search function
- Option0: simplification, inspired by `278. First Bad Version - Method1: Check is-NOT-BadVersion`
    - 1) if found match, but NOT sure it is desired boundary, just leave it and keep going
    - 2) check the final results after `binary search while loop` completes
    - WHY? code is easier to read in this way.



---

**298. [721. Accounts Merge.java](https://github.com/awangdev/LintCode/blob/master/Java/721.%20Accounts%20Merge.java)**      Level: Medium      Tags: [DFS, Hash Table, Union Find]
      
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

**299. [698. Partition to K Equal Sum Subsets.java](https://github.com/awangdev/LintCode/blob/master/Java/698.%20Partition%20to%20K%20Equal%20Sum%20Subsets.java)**      Level: Medium      Tags: [DFS, DP, Recursion]
      

#### Method1: Brutle DFS to find subsets and return results
- Target = total / k, fixed.
- DFS: Pick one num and dfs with the remaining nums for subproblem
    - subproblem1: accumulate local sum to target
        - EndState: accumulatedSum == target, continue with below
    - subproblem2: after accumulatedSum == target, continue dfs with k-1
        - EndState: k == 0, return overall true
- Option1: DFS with nums, and boolean[] visited. Fast
- Option2: DFS with queue. 
  - Specially handling: dfs(size+1) to prevent `while(size-- >0)` from skipping last item at index 0


#### Method2: DP
- the problem aims to find true/false capability
- bit-masking. TODO. The DP approach is kinda hard-level
- https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/335668/DP-with-Bit-Masking-Solution-%3A-Best-for-Interviews



---

**300. [716. Max Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/716.%20Max%20Stack.java)**      Level: Medium      Tags: [Design, Doubly Linked List, Stack, TreeMap]
      

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

**301. [366. Find Leaves of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/366.%20Find%20Leaves%20of%20Binary%20Tree.java)**      Level: Medium      Tags: [DFS, Tree]
      

#### DFS: store nodes at the same depth
- the leaves are at depth 0 and the root is at highest depth
- dfs: the depth = index of the rst, start from depth = 0 at leaf
- end state: leaf node, add to rst, and return depth




---

**302. [156. Binary Tree Upside Down.java](https://github.com/awangdev/LintCode/blob/master/Java/156.%20Binary%20Tree%20Upside%20Down.java)**      Level: Medium      Tags: [DFS, Tree]
      

#### DFS
- Given that left & right nodes are always available in pair, at each level: 
  - perform dfs to find new root: return deepest left node as root
  - pick out curr left node and fix current level: 
    - add right node as left node
    - add root as right node



---

**303. [416. Partition Equal Subset Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/416.%20Partition%20Equal%20Subset%20Sum.java)**      Level: Medium      Tags: [Backpack, DP]
      
#### Backpack DP
- the problem turns into: can we find a subset of items that sum up to target sum?
- create `boolean dp[j]` to represent if we can sum up to j, where j = sum value
  - want to try out all items in num, 

#### DFS
- use dfs to find a subset of items that sum up to target sum?



---

**304. [611. Valid Triangle Number.java](https://github.com/awangdev/LintCode/blob/master/Java/611.%20Valid%20Triangle%20Number.java)**      Level: Medium      Tags: [Array, Two Pointers]
      

#### Method1: Fix max and backward counting
- Sort nums: O(nlogn)
- Set max value fixed on right side at k
    - set 2nd value from right index j
    - set last value at min index i
    - if `nums[i] + nums[j] > nums[k]`: with fixed j, i can pick from [i, j-1] combinations
        - then j--, to pick another j candidate
    - maintain a window [i,j]; if invalid, move i++
- time: O(n^2)
- Note: very similar to 3-sum, fixing 1 index and use 2 pointers to move window

#### Method2: Fix min and forward counting
- Sort nums: O(nlogn)
- Set min value at i
    - set 2nd value at j=i+1; and 3rd value at k=i+2
    - find max of k that fits into triangle
    - count all possible k candidates from [j+1, k]
    - then move j to a new candidate
- O(n^2)



---

**305. [341. Flatten Nested List Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/341.%20Flatten%20Nested%20List%20Iterator.java)**      Level: Medium      Tags: [Design, NestedInteger, Stack]
      

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

**306. [254. Factor Combinations.java](https://github.com/awangdev/LintCode/blob/master/Java/254.%20Factor%20Combinations.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS]
      


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

**307. [739. Daily Temperatures.java](https://github.com/awangdev/LintCode/blob/master/Java/739.%20Daily%20Temperatures.java)**      Level: Medium      Tags: [Hash Table, Monotonous Stack, Stack]
      

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

**308. [373. Find K Pairs with Smallest Sums.java](https://github.com/awangdev/LintCode/blob/master/Java/373.%20Find%20K%20Pairs%20with%20Smallest%20Sums.java)**      Level: Medium      Tags: [Heap, MaxHeap, MinHeap]
      

#### Method1: MinHeap wiht k size
- This approach follows the pattern of finding min pair: 
  - 1) only need to store k pairs
  - 2) always start from min of A list and min of B list
  - 3) pre-build k pairs honoring A list, and then pick the min pair, and start swapping with min of list B 
- First attemp all first k pairs from nums1[i] against nums2[0] <=k : O(k)
- Use queue to pull min node and save results
- Use the nums1 val from the min node, pair up with nums2[j], add back to queue to sort
- overall runtime: O(klogk)
- space: O(k)

#### Method2: MaxHeap with k size
- Brutle: build all pairs time O(mn), sort with maxHeap pq with k size, and find top k
- overall time: O(mnLogK)
- space: O(k)



---

**309. [215. Kth Largest Element in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/215.%20Kth%20Largest%20Element%20in%20an%20Array.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, MinHeap, PriorityQueue, Quick Select, Quick Sort]
      

kth largest in array

#### PriorityQueue, MinHeap
- Use minHeap to maintain PQ of k size and return PQ.peek()
    - Maintain MinHeap: only allow larger elements (which will squzze out the min value)
    - Remove peek() of queue if over size
- O(nlogk)

#### Quick Select, Quick Sort
- 用Quick Sort 里面partion的一部分: sort结束后是ascending的.
  - kth largest = (n - k)th smallest
  - in partioned array (quick sort), the portion before pivot are less than pivot
  - that is, the `pivot value` is the divider: anything after pivot is larger than it.
  - after `swap(nums, low, pivot)`: index low has the (n-k)th smallest, if `low = n-k`
- Steps:
  - each iteration: pick pivot,然后从low,和high都和pivot作比较
  - Find `low>pivot, high<pivot` to swap
  - The new low is the next partion point
- Time: average O(n), worst case O(n^2)
- space: O(1) extra spaces besides recursive stack



---

