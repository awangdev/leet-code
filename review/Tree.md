 
 
 
## Tree (50)
**0. [Inorder Successor in Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Inorder%20Successor%20in%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, Tree]
      

#### Iterative
- TODO, perform search in the BST

#### Recursive
- 画inorder图，发现规律.每个node的后继node(successor)有几种情况:   
- 1. node.right 是个leaf到底了。那么就return.   
- 2. set rightNode = node.right， 但发现rightNode has a lot left children to leaf.   
- 3. 比如, node.right == null， 也就是node自己是leaf，要回头看山顶找Inorder traversal规则里的下一个。   
- 发现：其实就是每层都把路过的curr node放在stack里，最上面的，就是当下改return的那个successor:) Done.



---

**1. [Unique Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DP, Tree]
      

Not quite clear.
根据左右分割而总结出了原理, 每次分割, 左右两边都会有一定数量的permutation, 总体上的情况数量当然是相乘.
然后每一个不同的分割点都加一遍:
f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

然后把数学公式转换成DP的方程, 有点玄学的意思啊! 不好想.



---

**2. [Trim a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Trim%20a%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [BST, Tree]
      

方法1:
适合复习BST. 用DFS对待每个node. 注意BST的特征: 所有left nodes都小于当下node, 所有right nodes都大于当下node.

根据题意用[L,R]切割.如果node.val<L, 直接连node带左边全丢掉, return node.right. 处理R也是一样.
分制是, DFS leftNode, rightNode. 然后接在node.left, node.right.

方法2: 用迭代, 还没有写.



---

**3. [Binary Search Tree Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Search%20Tree%20Iterator.java)**      Level: Medium      Tags: [BST, Design, Stack, Tree]
      

画一下, BST in order traversal. 用stack记录最小值, 放在top. O(h) space.
每次消耗TreeNode, 都看看rightNode(其实就是下一个最小的candidate), 并且一条龙stack叠上rightNode所有的left子孙.

Previous Notes:
用O(h)空间的做法：

理解binary search tree inorder traversal的规律：
   先找left.left.left ....left 到底，这里是加进stack.
   然后考虑parent,然后再right.

例如这题：
   stack里面top，也就是tree最左下角的node先考虑,取名rst.
   其实这个rst拿出来以后, 它也同时是最底层left null的parent，算考虑过了最底层的parent。
   最后就考虑最底层的parent.right, 也就是rst.right.

注意:
   next()其实有个while loop, 很可能是O(h).题目要求average O(1),所以也是okay的.


用O(1)空间的做法：不存stack, 时刻update current为最小值。

找下一个最小值,如果current有right child：   
   和用stack时的iteration类似,那么再找一遍current.right的left-most child,就是最小值了。
   
如果current没有right child:    
    那么就要找current node的右上parent, search in BinarySearchTree from root.

注意：
   一定要确保找到的parent满足parent.left == current.
   反而言之，如果current是parent的 right child, 那么下一轮就会重新process parent。
   但是有错:binary search tree里面parent是小于right child的，也就是在之前一步肯定visit过，如此便会死循环。




---

**4. [Same Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Same%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      

给两个 binary tree, 看两个tree是否identical.

#### DFS
- DFS. 确定leaf条件, && with all dfs(sub1, sub2).
- 这里无论如何都要走过所有的node, 所以dfs更加合适, 好写.

#### BFS
- 两个queue存每个tree的所有current level node. Check equality, check queue size.
- Populate next level by nodes at current level.



---

**5. [Convert Sorted Array to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [DFS, Divide and Conquer, Tree]
      

如题, build balanced BST from sorted array

#### DFS
- Binary Search Tree特点: 左边的node都比右边的node小. 
- height balance, subtree height 相差<1, 必须左右sub tree均分. 做DFS(num, start, end)
- 在每一个level, 找到中间点, 然后分割2半, 继续dfs
- Divide and Conquer
- time/space: O(n), visit all nodes, no redundant visits.



---

**6. [Construct Binary Tree from Inorder and Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Preorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Hash Table, Tree]
      

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

**7. [Balanced Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Balanced%20Binary%20Tree.java)**      Level: Medium      Tags: [DFS, Tree]
      

给一个binary tree, 看是否是height-balanced

#### DFS
- DFS using depth marker: 每个depth都存一下。然后如果有不符合条件的，存为-1.
- 一旦有 <0 或者差值大于1， 就全部返回Integer.MIN_VALUE. Integer.MIN_VALUE比较极端, 确保结果的正确性。
- 最后比较返回结果是不是<0. 是<0，那就false.
- Traverse 整个tree, O(n)


#### DFS, maxDepth function
- Same concept as in 1, but cost more traversal efforts.



---

**8. [Populating Next Right Pointers in Each Node.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      

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

**9. [Validate Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Validate%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DFS, Divide and Conquer, Tree]
      

如题, 验证是否是BST.

#### DFS
- 查看每个parent-child关系: leftchild < root < rightChild
- 方法: 把root.val 传下来作为 max 或者 min, 然后检查children

##### Note: 
- min/max需要时long type. 
- 如果题目真的给node.val = Integer.MAX_VALUE, 我们需要能够与之比较, long就可以.



---

**10. [Maximum Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Binary%20Tree.java)**      Level: Medium      Tags: [Stack, Tree]
      

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

**11. [Binary Tree Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Inorder%20Traversal.java)**      Level: Easy      Tags: [Hash Table, Stack, Tree]
      

Inorder traverse Binary Tree

#### Recursive
- 在自己的基础上recursive, 不用helper function
- Divide and Conquer, with helper(dfs) method
- O(n) time, no extra space

#### Iterative: Stack
- Add left nodes all the way   
- Print curr   
- Move to right, add right if possible
- O(n) time, O(h) space
  
注意stack.pop()在加完left-most child 的后，一定要curr = curr.right.

若不右移, 很可能发生窘境:
curr下一轮还是去找自己的left-most child，不断重复curr and curr.left, 会infinite loop, 永远在左边上下上下。

#### HashMap
? How?



---

**12. [Binary Tree Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Postorder%20Traversal.java)**      Level: Medium      Tags: [Stack, Tree, Two Stacks]
      

如题, POST-ORDER traversal.

LeetCode给了hard, 应该是觉得stack的做法比较难想到.

#### Recursive
trivial, 先加left recursively, 再加right recursively, 然后组成头部.

#### Stack
- 双stack的思想, 需要在图纸上画一画
- 原本需要的顺序是: 先leftChild, rightChild, currNode.
- 营造一个stack, reversely process: 先currNode, 再rightChild, 再leftChild
- 这样出来的结果是reverse的, 那么翻转一下就可以了.
- v1做的时候用了stack1, stack2, 因为根据这个双stack的思想而来
- v2简化, 可以放在一个stack里面, 每次record result 的时候: rst.add(0, item);

##### 利用stack的特点
- 每次加element进stack的时候, 想要在 bottom/后process的, 先加
- 想要下一轮立刻process的, 最后push进stack.

##### 注意
这些binary tree traversal的题目.常常有多个做法:recursive or iterative



---

**13. [Closest Binary Search Tree Value.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Binary%20Search%20Tree%20Value.java)**      Level: Easy      Tags: [BST, Binary Search, Tree]
      

给一个BST, 和一个double target, 走位找到最接近的number.

#### Recursive
- when less than curr val, consider left
- when greater than curr val, consider right
- dfs到底, 然后每一层比较, 再return

#### Binary Search
- 记录找到过的closest
- Binary Search, 根据current node走位,
- 找到 node.val == target, 或者走位走完, return closest



---

**14. [Count Complete Tree Nodes.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20Complete%20Tree%20Nodes.java)**      Level: Medium      Tags: [Binary Search, Tree]
      

Complete Tree就是说, 最后一个level可能是缺node的(不是说最右下角缺node, 别忘了!)

#### DFS + Optimization
- 每次看最左left depth和最右leaf depth 是不是一样, 如果一样, 直接 2 ^ h - 1就好
- 不一样的话, 再DFS

##### Trick
- 直接DFS会timeout, O(n), 其实可以optimize
- to pass the test with O(h^2), 位运算: Math.pow(2, h) = 2 << (h - 1). 神奇!
- 2 << 1就是把所有bits往左移动一位, 也就是 * 2 

#### Iteratively
- See details in comments inline. 要对tree非常理解
- binary tree one child tree nodes # = 2 ^ h - 1; 所以一个child tree + root = 2 ^ h



---

**15. [Binary Tree Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Preorder%20Traversal.java)**      Level: Easy      Tags: [BFS, DFS, Stack, Tree]
      

#### Recursive
- 加root, left, then right. Obvious
- Divide and conquer
- 其实也不需要helper function

#### Iterative
- 先加root, 然后push上需要末尾process的在stack垫底(root.right), 然后push root.left
- Stack: push curr, push right, push left.   



---

**16. [Complete Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Complete%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, Tree]
      

A complete binary tree is a binary tree in which every level, except possibly the last,

is completely filled, and all nodes are as far left as possible

#### BFS
- 当出现了第一次有 null children的node的时候, 说明到了leaf level, mark flag = true;
- 自此以后，queue再不该有node再有child; queue后面出现的node的left/right child应该都是null
- 否则就是有问题, return false;




---

**17. [House Robber III.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20III.java)**      Level: Medium      Tags: [DFS, DP, Status DP, Tree]
      

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

**18. [Invert Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Invert%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

#### DFS
- 简单处理swap
- recursively swap children

#### BFS
- BFS with Queue
- 每次process一个node, swap children; 然后把child加进queue里面
- 直到queue process完



---

**19. [Maximum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      

给一个binary tree, 找最深depth

#### DFS
- 这里要走过所有的node, 所以dfs非常合适
- Divide and conquer. 
- 维持一个最大值: Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
- 注意check root == null

#### Note
- BFS is doable as well, but a bit more code to write: tracks largest level we reach



---

**20. [Minimum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

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

**21. [Symmetric Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Symmetric%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

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

**22. [Tweaked Identical Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Tweaked%20Identical%20Binary%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      

检查binary tree是否 identical. 

特点: subtree如果是有旋转的, 只要tree node value相等, 就可以算是identical

#### DFS
- 在DFS的基础上, 比对左左,左右,右左,右右



---

**23. [Merge Two Binary Trees.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Two%20Binary%20Trees.java)**      Level: Easy      Tags: [DFS, Tree]
      

#### DFS
- 基础binary tree traversal. 注意对null child的判断



---

**24. [Subtree.java](https://github.com/awangdev/LintCode/blob/master/Java/Subtree.java)**      Level: Easy      Tags: [DFS, Tree]
      

给一个binary tree s, 和一个binary tree t, 检查t是不是s的subtree.

#### DFS
- 跟 identical binary tree的写法很像
- 只有 current s.val = t.val 的时候才需要compare same tree.
- 其他情况, 继续recursively isSubtree
- 注意：即使找到T1 == T2, 但很可能只是数字相同（这里不是binary search tree!!）, 而children不同
- 所以同时要继续recursively isSubtree(T1.left, T2) ...etc.



---

**25. [Lowest Common Ancestor of a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree.java)**      Level: Medium      Tags: [DFS, Tree]
      

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

**26. [Lowest Common Ancestor II.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor%20II.java)**      Level: Easy      Tags: [Hash Table, Tree]
      

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

**27. [Lowest Common Ancestor of a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor%20of%20a%20Binary%20Search%20Tree.java)**      Level: Medium      Tags: [BST, DFS, Tree]
      

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

**28. [Binary Tree Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Level%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

如题.

#### BFS
- 最普通,Non-recursive: BFS, queue, 用个queue.size()来end for loop:换行。   
- 或者用两个queue. 当常规queue empty，把backup queue贴上去

#### DFS
- 每个level都应该有个ArrayList. 那么用一个int level来查看：是否每一层都有了相应的ArrayList。   
- 如果没有，就加上一层。    
- 之后每次都通过DFS在相应的level上面加数字。




---

**29. [Binary Tree Level Order Traversal II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Level%20Order%20Traversal%20II.java)**      Level: Medium      Tags: [BFS, Tree]
      

如题, 但是output要倒序.

#### BFS
- 跟Binary Tree Level Order Traversal一样,只不过存result一直存在存在0位.


#### DFS
- 根据level来append每个list
- rst里面add(0,...)每次都add在list开头



---

**30. [Binary Tree Longest Consecutive Sequence II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence%20II.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Double Recursive, Tree]
      

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

**31. [Binary Tree Maximum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum.java)**      Level: Hard      Tags: [DFS, DP, Tree, Tree DP]
      

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

**32. [Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum.java)**      Level: Easy      Tags: [DFS, Tree]
      

给一个inputSum, 然后dfs, 找到是否有一条path, 得出的path sum 跟 inputSum 一样.

#### DFS
- 确定好结尾的条件: is leaf && val == sum
- 每一层减掉node.val, 然后dfs.
- 写一写: root == null => false 对parent nodes的影响. 这里发现没影响, 所以可以简化成用1个functionDFS.




---

**33. [Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20II.java)**      Level: Easy      Tags: [Backtracking, DFS, Tree]
      

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

**34. [Path Sum III.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20III.java)**      Level: Easy      Tags: [DFS, Double Recursive, Tree]
      

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

**35. [Binary Tree Right Side View.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Right%20Side%20View.java)**      Level: Medium      Tags: [BFS, DFS, Tree]
      

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

**36. [Binary Tree Maximum Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum%20II.java)**      Level: Medium      Tags: [DFS, Tree]
      

找到从max path sum from root. 条件: 至少有一个node.

#### DFS
- 比Binary Tree Maximum Path Sum I 简单许多. 因为条件给的更多：at least 1 node + have to start from root
- root一定用到
- 3种情况: curr node, curr+left, curr+right
- 因为一定包括root, 说以从 `dfs(root, sum=0)` 开始, 每个level先加root, sum += root.val



---

**37. [Binary Tree Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      

找到binary tree 里的最长 consecutive sequence.

#### DFS
- Divide and Conquer. dfs
- 分开 看左边/右边
- 如果左边满足连续递增的规则, dfs (depth + 1), 如果不满足规则, dfs(depth = 1)
- 右边也是一样
- 对结果跟max作比较, return



---

**38. [Serilization and Deserialization Of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Serilization%20and%20Deserialization%20Of%20Binary%20Tree.java)**      Level: Hard      Tags: [BFS, DFS, Design, Divide and Conquer, Tree]
      

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

**39. [Unique Binary Search Tree II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree%20II.java)**      Level: Medium      Tags: [BST, DP, Divide and Conquer, Tree]
      

给一个数字n, 找到以(1...n)为node的所有unique BST.

#### BST
- 根据BST规则, divide and conquer
- 取一个value, 然后分两半(start, value - 1), (value + 1, end) 分别dfs
- 然后左右两边的结果cross match

#### DP? Memoization?



---

**40. [Kth Smallest Element in a BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Element%20in%20a%20BST.java)**      Level: Medium      Tags: [BST, DFS, Stack, Tree]
      

#### Iterative + stack: inorder traversal
- 很容想到Inorder-binary-search-tree Traversal
- Iterative 稍微难想点：先把最左边的add， pop() stack， 加上右边（如果存在）； 下一个轮回，如果又左孩子，又是一顿加。

#### Recursive + DFS
- 然后稍微优化一下，确保rst.size() == k 时候，就可以return了
- check leaf => dfs left => add root => dfs right



---

**41. [Construct Binary Tree from Inorder and Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Tree]
      

#### DFS, Divide and Conquer
- 写个Inorder和Postorder的例子。利用他们分left/right subtree的规律解题。
- Postorder array 的末尾， 就是当下层的root.   
- 在Inorder array 里面找到这个root,就刚好把左右两边分割成left/right tree。
- 这题比较tricky地用了一个helper做recursive。 特别要注意处理index的变化, precisely考虑开头结尾
- runtime: O(n), visit && build all nodes

#### Improvement
- `findMid(arr)` can be replaced with a map<value, index>, no need execute O(n) search at runtime



---

**42. [Recover Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Recover%20Binary%20Search%20Tree.java)**      Level: Hard      Tags: [BST, DFS, Tree]
      

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

**43. [Path Sum IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20IV.java)**      Level: Medium      Tags: [DFS, Hash Table, Tree]
      

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

**44. [Smallest Subtree with all the Deepest Nodes.java](https://github.com/awangdev/LintCode/blob/master/Java/Smallest%20Subtree%20with%20all%20the%20Deepest%20Nodes.java)**      Level: Medium      Tags: [DFS, Divide and Conquer, Tree]
      
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

**45. [Convert Binary Search Tree to Sorted Doubly Linked List (in place).java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List%20(in%20place).java)**      Level: Medium      Tags: [BST, DFS, Linked List, Tree]
      
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

**46. [Convert Binary Search Tree to Sorted Doubly Linked List (extra space).java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Binary%20Search%20Tree%20to%20Sorted%20Doubly%20Linked%20List%20(extra%20space).java)**      Level: Medium      Tags: [Linked List, Stack, Tree]
      
time: O(n)
space: O(n)

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

**47. [Binary Tree Zigzag Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Zigzag%20Level%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, Stack, Tree]
      
time: O(n)
space: O(n)
    
#### Queue
- 简单的level traversal.根据level奇数偶数而add到不同位子.
- Option1: based on level % 2, insert to front/end of list
- Option2: based on level, insert right/left of node into queue



---

**48. [Binary Tree Vertical Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Vertical%20Order%20Traversal.java)**      Level: Medium      Tags: [BFS, DFS, Hash Table, Tree]
      
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

**49. [Populating Next Right Pointers in Each Node II.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node%20II.java)**      Level: Medium      Tags: [DFS, Tree]
      
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

