 
 
 
## Tree (69)
**0. [Inorder Successor in BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Inorder%20Successor%20in%20BST.java)**      Level: Medium      Tags: [BST, Tree]
      
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

**1. [House Robber III.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20III.java)**      Level: Medium      Tags: [DFS, DP, Status DP, Tree]
      
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

**2. [Binary Tree Maximum Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum%20II.java)**      Level: Medium      Tags: [DFS, Tree]
      
找到从max path sum from root. 条件: 至少有一个node.

#### DFS
- 比Binary Tree Maximum Path Sum I 简单许多. 因为条件给的更多：at least 1 node + have to start from root
- root一定用到
- 3种情况: curr node, curr+left, curr+right
- 因为一定包括root, 说以从 `dfs(root, sum=0)` 开始, 每个level先加root, sum += root.val



---

**3. [Unique Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DP, Tree]
      
Not quite clear.
根据左右分割而总结出了原理, 每次分割, 左右两边都会有一定数量的permutation, 总体上的情况数量当然是相乘.
然后每一个不同的分割点都加一遍:
f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

然后把数学公式转换成DP的方程, 有点玄学的意思啊! 不好想.



---

**4. [Two Sum IV - Input is a BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Sum%20IV%20-%20Input%20is%20a%20BST.java)**      Level: Easy      Tags: [Tree]
      
HashSet to store visited items. Same old 2 sum trick.



---

**5. [Binary Tree Longest Consecutive Sequence II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence%20II.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Double Recursive, Tree]
      
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

**6. [Subtree of Another Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Subtree%20of%20Another%20Tree.java)**      Level: Easy      Tags: [DFS, Divide and Conquer, Tree]
      
#### Tree 
- Traverse tree: left, right
- Concept of partial compare vs. whole compare



---

**7. [Binary Tree Level Order Traversal II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Level%20Order%20Traversal%20II.java)**      Level: Medium      Tags: [BFS, Tree]
      
如题, 但是output要倒序.

#### BFS
- 跟Binary Tree Level Order Traversal一样,只不过存result一直存在存在0位.


#### DFS
- 根据level来append每个list
- rst里面add(0,...)每次都add在list开头



---

**8. [Maximum Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Binary%20Tree.java)**      Level: Medium      Tags: [Stack, Tree]
      
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

**9. [Construct Binary Tree from Inorder and Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Tree]
      
#### DFS, Divide and Conquer
- 写个Inorder和Postorder的例子。利用他们分left/right subtree的规律解题。
- Postorder array 的末尾， 就是当下层的root.   
- 在Inorder array 里面找到这个root,就刚好把左右两边分割成left/right tree。
- 这题比较tricky地用了一个helper做recursive。 特别要注意处理index的变化, precisely考虑开头结尾
- runtime: O(n), visit && build all nodes

#### Improvement
- `findMid(arr)` can be replaced with a map<value, index>, no need execute O(n) search at runtime



---

**10. [Subtree.java](https://github.com/awangdev/LintCode/blob/master/Java/Subtree.java)**      Level: Easy      Tags: [DFS, Tree]
      
给一个binary tree s, 和一个binary tree t, 检查t是不是s的subtree.

#### DFS
- 跟 identical binary tree的写法很像
- 只有 current s.val = t.val 的时候才需要compare same tree.
- 其他情况, 继续recursively isSubtree
- 注意：即使找到T1 == T2, 但很可能只是数字相同（这里不是binary search tree!!）, 而children不同
- 所以同时要继续recursively isSubtree(T1.left, T2) ...etc.



---

**11. [Redundant Connection.java](https://github.com/awangdev/LintCode/blob/master/Java/Redundant%20Connection.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Tree, Union Find]
      
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

**12. [Convert Sorted Array to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [DFS, Divide and Conquer, Tree]
      
如题, build balanced BST from sorted array

#### DFS
- Binary Search Tree特点: 左边的node都比右边的node小. 
- height balance, subtree height 相差<1, 必须左右sub tree均分. 做DFS(num, start, end)
- 在每一个level, 找到中间点, 然后分割2半, 继续dfs
- Divide and Conquer
- time/space: O(n), visit all nodes, no redundant visits.



---

**13. [Populating Next Right Pointers in Each Node.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      
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

**14. [Redundant Connection II.java](https://github.com/awangdev/LintCode/blob/master/Java/Redundant%20Connection%20II.java)**      Level: Hard      Tags: [DFS, Graph, Tree, Union Find]
      
#### Union Find
- 讨论3种情况
- http://www.cnblogs.com/grandyang/p/8445733.html



---

**15. [Tweaked Identical Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Tweaked%20Identical%20Binary%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      
检查binary tree是否 identical. 

特点: subtree如果是有旋转的, 只要tree node value相等, 就可以算是identical

#### DFS
- 在DFS的基础上, 比对左左,左右,右左,右右



---

**16. [Kth Smallest Element in a BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Element%20in%20a%20BST.java)**      Level: Medium      Tags: [BST, DFS, Stack, Tree]
      
#### Iterative + stack: inorder traversal
- 很容想到Inorder-binary-search-tree Traversal
- Iterative 稍微难想点：先把最左边的add， pop() stack， 加上右边（如果存在）； 下一个轮回，如果又左孩子，又是一顿加。

#### Recursive + DFS
- 然后稍微优化一下，确保rst.size() == k 时候，就可以return了
- check leaf => dfs left => add root => dfs right



---

**17. [Invert Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Invert%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      
#### DFS
- 简单处理swap
- recursively swap children

#### BFS
- BFS with Queue
- 每次process一个node, swap children; 然后把child加进queue里面
- 直到queue process完



---

**18. [Unique Binary Search Tree II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree%20II.java)**      Level: Medium      Tags: [BST, DP, Divide and Conquer, Tree]
      
给一个数字n, 找到以(1...n)为node的所有unique BST.

#### BST
- 根据BST规则, divide and conquer
- 取一个value, 然后分两半(start, value - 1), (value + 1, end) 分别dfs
- 然后左右两边的结果cross match

#### DP? Memoization?



---

**19. [Merge Two Binary Trees.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Two%20Binary%20Trees.java)**      Level: Easy      Tags: [DFS, Tree]
      
#### DFS
- 基础binary tree traversal. 注意对null child的判断



---

**20. [Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20II.java)**      Level: Easy      Tags: [Backtracking, DFS, Tree]
      
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

**21. [Recover Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Recover%20Binary%20Search%20Tree.java)**      Level: Hard      Tags: [BST, DFS, Tree]
      
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

**22. [Convert Binary Search Tree to Sorted Doubly Linked List (extra space).java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List%20(extra%20space).java)**      Level: Medium      Tags: [Linked List, Stack, Tree]
      

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

**23. [Smallest Subtree with all the Deepest Nodes.java](https://github.com/awangdev/LintCode/blob/master/Java/Smallest%20Subtree%20with%20all%20the%20Deepest%20Nodes.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      

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

**24. [Path Sum III.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20III.java)**      Level: Easy      Tags: [DFS, Double Recursive, Tree]
      
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
- 利用这个function自己, 做`不包括root的recursive computation`



---

**25. [Complete Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Complete%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, Tree]
      
A complete binary tree is a binary tree in which every level, except possibly the last,

is completely filled, and all nodes are as far left as possible

#### BFS
- 当出现了第一次有 null children的node的时候, 说明到了leaf level, mark flag = true;
- 自此以后，queue再不该有node再有child; queue后面出现的node的left/right child应该都是null
- 否则就是有问题, return false;




---

**26. [Binary Tree Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      
找到binary tree 里的最长 consecutive sequence.

#### DFS
- Divide and Conquer. dfs
- 分开 看左边/右边
- 如果左边满足连续递增的规则, dfs (depth + 1), 如果不满足规则, dfs(depth = 1)
- 右边也是一样
- 对结果跟max作比较, return



---

**27. [Trim a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Trim%20a%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [BST, Tree]
      
方法1:
适合复习BST. 用DFS对待每个node. 注意BST的特征: 所有left nodes都小于当下node, 所有right nodes都大于当下node.

根据题意用[L,R]切割.如果node.val<L, 直接连node带左边全丢掉, return node.right. 处理R也是一样.
分制是, DFS leftNode, rightNode. 然后接在node.left, node.right.

方法2: 用迭代, 还没有写.



---

**28. [Path Sum IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20IV.java)**      Level: Medium      Tags: [DFS, Hash Table, Tree]
      
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

**29. [Populating Next Right Pointers in Each Node II.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node%20II.java)**      Level: Medium      Tags: [DFS, Tree]
      

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

**30. [[lint]. Lowest Common Ancestor II.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Lowest%20Common%20Ancestor%20II.java)**      Level: Easy      Tags: [Hash Table, Lint, Tree]
      
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

**31. [102. Binary Tree Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/102.%20Binary%20Tree%20Level%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

如题.

#### Method1: BFS
- 最普通,Non-recursive: BFS, queue, 用个queue.size()来end for loop:换行。   
- 或者用两个queue. 当常规queue empty，把backup queue贴上去

#### Method2: DFS
- 每个level都应该有个ArrayList. 那么用一个int level来查看：是否每一层都有了相应的ArrayList。   
- 如果没有，就加上一层。    
- 之后每次都通过DFS在相应的level上面加数字。




---

**32. [236. Lowest Common Ancestor of a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/236.%20Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree.java)**      Level: Medium      Tags: [DFS, Tree]
      

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

**33. [111. Minimum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/111.%20Minimum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

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

**34. [987. Vertical Order Traversal of a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/987.%20Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree.java)**      Level: Medium      Tags: [BFS, Binary Tree, DFS, Hash Table, Tree]
      
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

**35. [429. N-ary Tree Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/429.%20N-ary%20Tree%20Level%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, Tree]
      

#### BFS
- use queue to hold each level. O(n)



---

**36. [199. Binary Tree Right Side View.java](https://github.com/awangdev/LintCode/blob/master/Java/199.%20Binary%20Tree%20Right%20Side%20View.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

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

**37. [1008. Construct Binary Search Tree from Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/1008.%20Construct%20Binary%20Search%20Tree%20from%20Preorder%20Traversal.java)**      Level: Medium      Tags: [DFS, Tree]
      

#### Method1: Top Down DFS
- This approach highly relies on the preorder rules
    - we can use validation rules to navigate throug hteh preorder array
    - use a global index
- time:  O(n)




---

**38. [515. Find Largest Value in Each Tree Row.java](https://github.com/awangdev/LintCode/blob/master/Java/515.%20Find%20Largest%20Value%20in%20Each%20Tree%20Row.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

#### Method1: DFS
- faster than BFS, using less space if not couting final rst: stack size, O(logn)
- time: O(n), visit all

#### Method2: BFS with queue
- loop over queue level and record max




---

**39. [222. Count Complete Tree Nodes.java](https://github.com/awangdev/LintCode/blob/master/Java/222.%20Count%20Complete%20Tree%20Nodes.java)**      Level: Medium      Tags: [Binary Search, DFS, Tree]
      

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

**40. [543. Diameter of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/543.%20Diameter%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [Tree]
      

找longest path (include or not include root)

跟Binary Tree Maximum Path Sum 的想法一样: 处理single path, 或者combined path (do not include curr root)

#### Singlepath and CombinedPath
- Option1: Use local single path max & global combined max
    - Since the local combined diameter is used for comparision, but not used for specific calculation
    - calculate path length (diameter), understand:
        - for single path: child single path value + 1 (curr node)
        - for combined path including curr node: left child single + right child path 
- Option2: record local combined and single path for each iteration
    - `int[]{combinedPath, singlePath}`;
    - single path: pick single path + 1: `singlePath = Math.max(left[1] , right[1]) + 1`;
    - combined path `combinedPath = Math.max(Math.max(left[0], right[0]), left[1] + right[1] + 1)`, find max from:
        - 1) complete left child combined path
        - 2) complete right child combined path
        - 3) combined path with curr root
    - Note: we treat a single node itself with diameter of 1, so we want to -1 in final result
        - problem statement wants the path length (not # of nodes or depth)



---

**41. [1110. Delete Nodes And Return Forest.java](https://github.com/awangdev/LintCode/blob/master/Java/1110.%20Delete%20Nodes%20And%20Return%20Forest.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      

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

**42. [173. Binary Search Tree Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/173.%20Binary%20Search%20Tree%20Iterator.java)**      Level: Medium      Tags: [BST, Design, Stack, Tree]
      

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

**43. [104. Maximum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/104.%20Maximum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      
给一个binary tree, 找最深depth

#### DFS
- 这里要走过所有的node, 所以dfs非常合适
- Divide and conquer. 
- 维持一个最大值: Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
- 注意check root == null

#### Note
- BFS is doable as well, but a bit more code to write: tracks largest level we reach



---

**44. [297. Serialize and Deserialize Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/297.%20Serialize%20and%20Deserialize%20Binary%20Tree.java)**      Level: Hard      Tags: [BFS, DFS, Deque, Design, Divide and Conquer, Tree]
      

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

**45. [270. Closest Binary Search Tree Value.java](https://github.com/awangdev/LintCode/blob/master/Java/270.%20Closest%20Binary%20Search%20Tree%20Value.java)**      Level: Easy      Tags: [BST, Binary Search, Tree]
      

给一个BST, 和一个double target, 走位找到最接近的number.

Concept: Iterate over all logN nodes in the BST and record the closest. Rather than finding the value at +/- 0.5 precision

#### Binary Search
- 记录找到过的closest
- Binary Search, 根据current node走位, until null leaf
- time: O(logn), space O(1) since no extra space used

#### DFS, Recursive
- when less than curr val, consider left
- when greater than curr val, consider right
- dfs到底, 然后每一层比较, 再return
- time: O(logn), space: O(logn)




---

**46. [144. Binary Tree Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/144.%20Binary%20Tree%20Preorder%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Stack, Tree]
      

#### Recursive, DFS, Divide and conquer
- 加root, left, then right. Obvious
- Option1: recursive on preorderTraversal. the dfs function returns List
- Option2: pass in rst, and write a void dfs.

#### Iterative
- 先加root, 然后push上需要末尾process的在stack垫底(root.right), 然后push root.left
- Stack: push curr, push right, push left.   



---

**47. [110. Balanced Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/110.%20Balanced%20Binary%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      
给一个binary tree, 看是否是height-balanced

#### DFS with end state
- DFS using depth marker: 每个depth都存一下。然后如果有不符合条件的，存为-1.
- 一旦有 <0 或者差值大于1， 就全部返回Integer.MIN_VALUE. Integer.MIN_VALUE比较极端, 确保结果的正确性。
- 最后比较返回结果是不是<0. 是<0，那就false.
- Traverse 整个tree, O(n)


#### DFS, maxDepth function
- Same concept as above solution, but cost more traversal efforts
- 试图计算所有情况



---

**48. [100. Same Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/100.%20Same%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

给两个 binary tree, 看两个tree是否identical.

#### Method1: DFS
- DFS. 确定leaf条件, && with all dfs(sub1, sub2).
- 这里无论如何都要走过所有的node, 所以dfs更加合适, 好写.

#### Method2: BFS with 2 queues
- 两个queue存每个tree的所有current level node. Check equality, check queue size.
- Populate next level by nodes at current level.



---

**49. [112. Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/112.%20Path%20Sum.java)**      Level: Easy      Tags: [DFS, Tree]
      
给一个inputSum, 然后dfs, 找到是否有一条path, 得出的path sum 跟 inputSum 一样.

#### DFS
- 确定好结尾条件: `is leaf` && `val == sum`.
- 每一层减掉node.val, 然后dfs.
- 写一写: `root == null => false` 对parent nodes的影响. 这里发现没影响, 所以可以简化成用1个functionDFS.




---

**50. [427. Construct Quad Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/427.%20Construct%20Quad%20Tree.java)**      Level: Medium      Tags: [Tree]
      

#### Basic Impl
- build tree recursively by definition
- O(n^2) time and space due to single visit to all nodes


---

**51. [1026. Maximum Difference Between Node and Ancestor.java](https://github.com/awangdev/LintCode/blob/master/Java/1026.%20Maximum%20Difference%20Between%20Node%20and%20Ancestor.java)**      Level: Medium      Tags: [DFS, Tree]
      

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

**52. [145. Binary Tree Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/145.%20Binary%20Tree%20Postorder%20Traversal.java)**      Level: Medium      Tags: [Stack, Tree, Two Stacks]
      

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

**53. [938. Range Sum of BST.java](https://github.com/awangdev/LintCode/blob/master/Java/938.%20Range%20Sum%20of%20BST.java)**      Level: Easy      Tags: [BST, Recursion, Tree]
      
#### DFS based on BST rules
- sum up the matching L & R
    - Find (L,R) on left child
    - Find (L,R) on right child
    - Find (L,R) covering the root node
- space O(n), worst case O(logn), height of dfs.
- time O(n) to find all nodes between (L, R)

#### Iterative
- Using stack, or queue, list: any data structure (we are not doing ordered search)
- space O(n)
- time O(n)



---

**54. [314. Binary Tree Vertical Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/314.%20Binary%20Tree%20Vertical%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Hash Table, Tree]
      

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

**55. [103. Binary Tree Zigzag Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/103.%20Binary%20Tree%20Zigzag%20Level%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, Stack, Tree]
      
    
#### Queue
- 简单的level traversal.根据level奇数偶数而add到不同位子.
- Option1: based on level % 2, insert to front/end of list
- Option2: based on level, insert right/left of node into queue



---

**56. [105. Construct Binary Tree from Preorder and Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/105.%20Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Hash Table, Tree]
      

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

**57. [449. Serialize and Deserialize BST.java](https://github.com/awangdev/LintCode/blob/master/Java/449.%20Serialize%20and%20Deserialize%20BST.java)**      Level: Medium      Tags: [Tree]
      

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

**58. [426. Convert Binary Search Tree to Sorted Doubly Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/426.%20Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Linked List, Tree]
      

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

**59. [94. Binary Tree Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/94.%20Binary%20Tree%20Inorder%20Traversal.java)**      Level: Easy      Tags: [Hash Table, Stack, Tree]
      

Inorder traverse Binary Tree

#### Method1: DFS
- option1: dfs + rst list to carry results
- option2: Divide and Conquer, 在自己的基础上recursive, 不用helper function
- O(n) time

#### Method2: Iterative, Stack inorder traversal
- 1) Add root.leftPath all the way to leaf, 2) process curr 3) Move to right if applicable 4) add all right.leftPath
- O(n) time, O(h) space




---

**60. [98. Validate Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/98.%20Validate%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Tree]
      

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

**61. [1123. Lowest Common Ancestor of Deepest Leaves.java](https://github.com/awangdev/LintCode/blob/master/Java/1123.%20Lowest%20Common%20Ancestor%20of%20Deepest%20Leaves.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

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

**62. [124. Binary Tree Maximum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/124.%20Binary%20Tree%20Maximum%20Path%20Sum.java)**      Level: Hard      Tags: [DFS, DP, Tree, Tree DP]
      

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

**63. [101. Symmetric Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/101.%20Symmetric%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

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

**64. [671. Second Minimum Node In a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/671.%20Second%20Minimum%20Node%20In%20a%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, Tree]
      

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

**65. [366. Find Leaves of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/366.%20Find%20Leaves%20of%20Binary%20Tree.java)**      Level: Medium      Tags: [DFS, Tree]
      

#### DFS: store nodes at the same depth
- the leaves are at depth 0 and the root is at highest depth
- dfs: the depth = index of the rst, start from depth = 0 at leaf
- end state: leaf node, add to rst, and return depth




---

**66. [235. Lowest Common Ancestor of a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/235.%20Lowest%20Common%20Ancestor%20of%20a%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [BST, DFS, Tree]
      

给 binary search tree root, q node, p node. 找到p q 的lowest common ancestor

#### Find path with BST
- 利用 BST 的性质，可以直接搜到target node，而做成两个长度不一定相等的 path
- 然后很简单找到LCA 
- O(n) space, O(logn) time

#### DFS
- Beofre lasts common ancestor is found: p and q should follow same search pattern:
    - compare with root, then dfs(left) or dfs(right)
    - util p and q fall on two sides of root, then return root
    - 非常巧妙, 但是也比较局限; 稍微变条件, 就很难recursive.
- 几种情况:
    - 1. one of p, q 在leaf, 那么此时的root其实就是lowest common ancestor
    - 2. 如果p, q 在root的左右两边, 这就是分叉口, 那么root就是lowest common ancestor
    - 3. 如果p, q 在root的同一边 (左,右), 那么继续dfs
- O(logn) extra space: recursive stack space
- O(logn) time



---

**67. [156. Binary Tree Upside Down.java](https://github.com/awangdev/LintCode/blob/master/Java/156.%20Binary%20Tree%20Upside%20Down.java)**      Level: Medium      Tags: [DFS, Tree]
      

#### DFS
- Given that left & right nodes are always available in pair, at each level: 
  - perform dfs to find new root: return deepest left node as root
  - pick out curr left node and fix current level: 
    - add right node as left node
    - add root as right node



---

**68. [272. Closest Binary Search Tree Value II.java](https://github.com/awangdev/LintCode/blob/master/Java/272.%20Closest%20Binary%20Search%20Tree%20Value%20II.java)**      Level: Hard      Tags: [Stack, Tree]
      

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

