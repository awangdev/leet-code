 
 
 
## DFS (24)
**0. [Nested List Weight Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Nested%20List%20Weight%20Sum.java)**      Level: Easy
      

方法1: 简单的处理nested structure, dfs增加depth.
方法2: bfs, queue, 处理queue.size().



---
**1. [Same Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Same%20Tree.java)**      Level: Easy
      

DFS. 确定leaf条件, && with all sub problems.

如果用BFS: 两个queue存每个tree的所有current level node. Check equality, check queue size.
Populate next level by nodes at current level.



---
**2. [Convert Sorted Array to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree.java)**      Level: Easy
      

Binary Search Tree特点: 左边的node都比右边的node小. 
如果要height相差<1, 必须左右sub tree均分. 做DFS.



---
**3. [Binary Tree Maximum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum.java)**      Level: Review
      

LeetCode: H
用 PathSumType 比较特别. 没有 data structure的时候, 写起来比较繁琐.

第一次做有点难理解，复杂原因是：因为可能有负值啊。不能乱assume正数。   
   single path max 的计算是为了给后面的comboMax用的。
   如果single path max小于0，那没有什么加到parent上面的意义，所以就被再次刷为0.

combo的三种情况：(root可能小于0)   
   1. 只有left    
   2。 只有右边   
   3. root大于0，那么就left,right,curr全部加起来。

情况1和情况2取一个最大值，然后和情况三比较。做了两个Math.max(). 然后就有了这一层的comboMax


12.11.2015 recap:   
   So totally, 5 conditions:   
   (save in single)    
        left + curr.val OR right + curr.val   
   (save in combo:)    
   left, right, OR left + curr.val + right   





---
**4. [Construct Binary Tree from Inorder and Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Preorder%20Traversal.java)**      Level: Medium
      

和Construct from Inorder && Postorder 想法一样。

写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。



---
**5. [Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum.java)**      Level: Easy
      

确定好结尾的条件, DFS

写一写: root == null => false 对parent nodes的影响. 这里发现没影响, 所以可以简化成用1个functionDFS.




---
**6. [Balanced Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Balanced%20Binary%20Tree.java)**      Level: Medium
      

1. DFS using depth marker: 每个depth都存一下。然后如果有不符合条件的，存为-1.
   一旦有 <0 或者差值大于1， 就全部返回Integer.MIN_VALUE. Integer.MIN_VALUE比较极端, 确保结果的正确性。
   最后比较返回结果是不是<0. 是<0，那就false.
   Traverse 整个tree, O(n)

2. Only calculate depth using maxDepth function. Same concept as in 1, but cost more traversal efforts.



---
**7. [Populating Next Right Pointers in Each Node.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node.java)**      Level: Medium
      

方法1：   
题目要求DFS. 想清楚了如何在DFS level把几种情况都考虑了, 写起来很简单.
其实basic implementation, 每次处理next链接:
1. node.left.next = node.right
2. If node.next != null, link node.right.next = node.next.left;

方法2:   
不和题意，用了queue space，与Input成正比。太大。

BFS over Tree。 用Queue 和 queue.size()，老规矩。   
process每层queue时, 注意把next pointer加上去就好. 



---
**8. [Validate Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Validate%20Binary%20Search%20Tree.java)**      Level: Medium
      

查看每个parent-child关系。同时把root level上面传下来max,min界限定住。

Note: min/max需要时long type. 
如果题目真的给node.val = Integer.MAX_VALUE, 我们需要能够与之比较, long就可以.



---
**9. [Convert Sorted List to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20List%20to%20Binary%20Search%20Tree.java)**      Level: Medium
      

Divide and Conquer   
找到mid node

方法1:
用长度来定位mid, 每次找中间点做root, 然后前半段, 后半段分别dfs with length.

方法2: 用快慢pointer
Better: 不用traverse entire linked list

slowPointer = node;
fastPointer = node.next;

然后把root = mid.next     

然后开始sortedListToBST(mid.next.next); //后半段    
mid.next = null;//非常重要，要把后面拍过序的断掉    
sortedListToBST(head); //从头开始的前半段     

最后root.left, root.right merge一下。   



---
**10. [Flatten Binary Tree to Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Flatten%20Binary%20Tree%20to%20Linked%20List.java)**      Level: Medium
      

分析题意后, 按照题意: Flatten it with in-place order
1. reserve right child
2. DFS flatten部分
3. 移花接木
4. flatten 剩下的.



---
**11. [Binary Tree Paths.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Paths.java)**      Level: Easy
      

返回所有root-to-leaf path

#### 方法1：   
Recursive:分叉. dfs.

#### 方法2:
- Iterative, 非递归练习了一下   
- 因为要每次切短list, 所以再加了一个Stack 来存level   




---
**12. [Clone Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Clone%20Graph.java)**      Level: Medium
      

思想:
Use HashMap to mark cloned nodes.    
先能复制多少Node复制多少. 然后把neighbor 加上

方法一: DFS
1. copy the node
2. Mark 'added' using map(old, new)
3. for loop on the each one of the neighbors: map copy, record in map, and further dfs
4. once dfs completes, add newNeighbor as neighbor of the new node (get to it via map)
主要思想是: 一旦复制过了, 不必要重新复制

方法二: BFS
1. Copy the root node, then copy all the neighbors. 
2. Mark copied node in map.
3. Use queue to contain the newly added neighbors. Need to work on them in the future.

注意:
initialize map with (node, newNode)



---
**13. [Number of Islands.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Islands.java)**      Level: Medium
      

方法1: 两个for loop brutle force。 DFS把每个跟1相关的都Mark一遍.生成一个island.

方法2:
可以用union-find， 就像Number of island II 一样。
只不过这个不Return list, 而只是# of islands
记住UnionFind的模板和几个变化(Connecting Graph I, II, III), 最后归总的代码写起来就比较简单.



---
**14. [Graph Valid Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Graph%20Valid%20Tree.java)**      Level: Medium
      

复习Union-Find的另外一个种形式。   
题目类型：查找2个元素是不是在一个set里面。如果不在，false. 如果在，那就合并成一个set,共享parent.   
存储的关键都是：元素相对的index上存着他的root parent.    

注意: 结尾要检查, 是否只剩下1个union. Tree必须连接到所有给出的node.

另一个union-find， 用hashmap的：http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/




---
**15. [Surrounded Regions.java](https://github.com/awangdev/LintCode/blob/master/Java/Surrounded%20Regions.java)**      Level: Review
      

从四个边的edge出发, 像感染僵尸病毒一样扩散, 把靠边的node全部mark, 然后将还是'O'的改成X, 最后回复marks -> 'O'

方法1:
UnionFind里面这次用到了一个rank的概念, 需要review

方法2,3:
DFS, BFS都好理解, 




---
**16. [Word Search II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search%20II.java)**      Level: Hard
      

相比之前的implementation, 有一些地方可以优化:
1. Backtracking时候, 在board[][] 上面mark就可以, 不需要开一个visited[][]
2. 不需要implement trie的所有方程, 用不到: 这里只需要insert.
   普通的trie题目会让你search a word, 但是这里是用一个board, 看board的每一个字母能不能走出个Word.
   也就是: 这里的search是自己手动写, 不是传统的trie search() funcombination
3. TrieNode里面存在 end的时候存string word, 表示到底. 用完了 word = null, 刚好截断重复查找的问题.

Previous Notes:
Big improvement: use boolean visited on TrieNode!     
不要用rst.contains(...), 因为这个是O(n) 在leetcode还是会timeout（lintcode竟然可以pass）!    
在Trie search() method 里面，凡是visit过的，mark一下。  

Regular:   
for loop on words: inside, do board DFS based on each word.     
Time cpmplexity: word[].length * boardWidth * boardHeight * (4^wordMaxLength)

Build Trie with target words: insert, search, startWith.    
依然要对board matrix做DFS。

no for loop on words. 直接对board DFS:   
每一层,都会有个up-to-this-point的string. 在Trie里面check它是不是存在。以此判断。   
若不存在，就不必继续DFS下去了。

Trie solution time complexity, much better:      
build Trie:   n * wordMaxLength
search: boardWidth * boardHeight * (4^wordMaxLength + wordMaxLength[Trie Search])




---
**17. [Expression Expand.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Expand.java)**      Level: Medium
      


#### DFS
- 与Stack时需要考虑的一些function类似. 特别之处: **检查[ ]的结尾**
- 因为DFS时候, 括号里的substring会被保留着进入下一个level, 所以我们在base level要keep track of substring.
- 用int paren 来track 括号的开合, 当paren再次==0的时候 找到closure ']'

#### Stack
- Stack存 [ ] 里面的内容, detect 括号开头结尾: 结尾时process inner string
- 有很多需要注意的细节才能做对:
- Stack<Object> 也可以用, 每个地方要注意 cast. 存进去的需要是Object: String, Integer
- 几个 type check: instanceof String, Character.isDigit(x), Integer.valueOf(int num)
- 出结果时候, 不能轻易 sb.reverse().toString(): sb.reverse() 翻转了整个连在一起的string, 错.
- 用另一个Stack<String>作为buffer, 先把stack里面的内容倒出来 (pure), 但是每个item里面顺序不变.
- 最后再从buffer里面倒进StringBuffer.




---
**18. [Find Peak Element II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element%20II.java)**      Level: Hard
      

Should break down by mid row. More details:
http://www.jiuzhang.com/solution/find-peak-element-ii/#tag-highlight-lang-java
http://courses.csail.mit.edu/6.006/spring11/lectures/lec02.pdf

#### 方法1
##### 基本原理
我们不可能一口气准确定位(x,y), 但是我们可以再一个row/col里面, 找到1D array的 peak.
根据这个点, 再往剩下两个方向移动

1. 在中间的一行, 找到peak所在的y.

2. 在中间的一列, 找到peak所在的x. (有可能强势override之前找到的y, 也就是放弃那一行的peak, 在midY上找peak)

3. 猜一猜 (x,y) 是不是 peak, 如果不是, 像更高的位置移动一格

4. 根据之前算的 midX, midY 把board分成4个象限, 在每一份里面再继续找

##### 剪枝/切分象限
每次只是找到一个row/col里面的peak而已!

找到这个点, 就等于把board切成了两半.

然后, 再跟剩下的相邻的两个位置比较, 就知道了哪里更大, 就去哪里找peak, 也就是又切了第二刀.

切第二刀的时候, 也要把(x, y) 移到需要取的象限. 进行DFS

##### 时间复杂度
每一个level都减一半
T(n) = n + T(n/2) = n + n/2 + n/4 + ... + 1 = n(1 + 1/2 + .... + 1/n) = 2n = O(n)

#### 方法2
Binary Search
还没有写 : )
O(nLogN)



---
**19. [House Robber III.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20III.java)**      Level: Hard
      

Houses被arrange成了binary tree, 规则还是一样, 连续相连的房子不能同时抄.
求Binary Tree neighbor max 能抄多少.

#### DFS
- 判断当下的node是否被采用，用一个boolean来表示. 
- 如果curr node被采用，那么下面的child一定不能被采用.
- 如果curr node不被采用，那么下面的children有可能被采用，但也可能略过，所以这里用Math.max() 比较一下两种可能有的dfs结果。
- dfs重复计算:每个root都有4种dive in的可能性, 假设level高度是h, 那么时间O(4^(h)), where h = logN, 也就是O(n^2)

#### DP 
- 并不是单纯的DP, 是在发现DFS很费劲后, 想能不能代替一些重复计算?
- 在DFS的基础上, 在每一个level上面来一个dp.
- 基本思想是dfs解法一致: 取root找最大值, 或者不取root找最大值
- Optimization: DP里面, 一口气找leftDP[]会dfs到最底层, 然后自下向上做计算
- 这个过程里面, 再也不用回去visit most-left-leaf了, 算过一遍就完事.
- 然而, 普通没有dp的dfs, 在算完visited的情况下的dfs, 还要重新dfs一遍!visited的情况.
- Space O(h), time O(n), 或者说是O(2^h), where h = log(n)



---
**20. [Longest Increasing Path in a Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Path%20in%20a%20Matrix.java)**      Level: Hard
      

m x n 的matrix, 找最长增序的序列长度. 这里默认连续的序列.

- 接成圈是不行的, 所以visit过得 (x,y)就不能再去了.
- 斜角方向不能走, 只能走上下左右

#### DP, DFS
- DFS太多重复计算; memoization (dp[][], visited[][]) 省去了重复计算
- initialize dp[x][y] = 1, (x,y) 自己也算path里的一格
- O(m * n * k), where k is the longest path

#### Topological sort
还没有做



---
**21. [Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20II.java)**      Level: Easy
      

Binary Tree的一个基本题: 找到所有满足条件的path

- 遍历到底，比较sum vs. target
- 注意divide的情况。要把遍历的例子写写



---
**22. [Course Schedule.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule.java)**      Level: Medium
      

- 一堆课用int[2] pair 来表示. [1, 0] 表示要上课1的话, 必须先把课0上了. 
- 每一个数字都是一个ndoe, 题目问是否能把所有的课排了
- input是 numOfCourses, 还有这个prerequisites [[]]

#### Topological Sort
- 给一个graph of nodes
- 目标是根据edge 的 direction, 把这个graph 里面的 node sort 一个list
- 如果有cycle, 这个item就不会被放在最后的list 里面. 
- 比如: 如果两个课互相是dependency, 就变成了cyclic dependency, 这样不好.

#### BFS
- Kahn algorithem:
- 先build一个graph map: <node, list of nodes >
- count in-degree:  inDegree就是每个node上面, 有多少个走进来的edge?
- 那些没有 in-coming-edge的, indegree 其实就 等于 0, 那么他们就应该在final result list里面
- 对这些 indegree == 0 的 nodes BFS
- 模拟visit每个ndoe, 如果visit过了, 这个node上的 indegree--, 然后如果最终 indegree == 0, 这个node就成功进入final list.
- Note: 如果有cycle, indegree是不会变成0的, 它也无法进入最终list.

#### DFS
- 这道题没有要求作出final list, 相对简单, 只要visit每个nodes, 最后确认没有cycle就好了
- 用 visited int[] 来确认是否有cycle. 1 代表 paretNode visited, -1 代表在DFS上一行的标记
- 如果遇到-1, 说明这个node在上一级或者以上的同一个dfs path里面已经走过, 那么证明有cycle, return false.
- 真的topo sort会在DFS的底端, 把record放进一个stack, 最后reverse, 就是真的sort order.

#### Notes:
- 还有 List[] arrayOfList = new ArrayList[]; 这样的操作啊, 代替了map<integer, integerList>

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
**23. [Course Schedule II.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule%20II.java)**      Level: Medium
      

- 一堆课用int[2] pair 来表示. [1, 0] 表示要上课1的话, 必须先把课0上了. 
- 每一个数字都是一个ndoe, 题目求这个最后排好的课的list
- 如果排不好, 就给个空就好
- input是 numOfCourses, 还有这个prerequisites [[]]

做法跟Course Schedule I 非常像, 可以参考.

#### BFS
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
