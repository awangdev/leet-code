 
 
 
## Review (9)
**0. [Maximum Subarray III.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray%20III.java)**      Level: Review
      


---

**1. [Valid Perfect Square.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Perfect%20Square.java)**      Level: Review
      

Binary找sqrt. 基本 mid+1, mid-1 template.
注意: define index as long. 



---

**2. [Regular Expression Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Regular%20Expression%20Matching.java)**      Level: Review
      



---

**3. [Word Break II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Break%20II.java)**      Level: Review
      

两个DP一起用.解决了timeout的问题     
1. isWord[i][j], subString(i,j)是否存在dict中？

2. 用isWord加快 isValid[i]: [i ～ end]是否可以从dict中找到合理的解？      
	从末尾开始查看i：因为我们需要测试isWord[i][j]时候，j>i, 而我们观察的是[i,j]这区间；       
	j>i的部分同样需要考虑，我们还需要知道isValid[0～j+1]。 所以isValid[x]这次是表示[x, end]是否valid的DP。     
	i 从 末尾到0, 可能是因为考虑到isWord[i][j]都是在[0~n]之内，所以倒过来数，坐标比较容易搞清楚。     
	(回头看Word Break I， 也有坐标反转的做法)

3. dfs 利用 isValid 和isWord做普通的DFS。

Note:
在Word Break里面用了set.contains(...), 在isValid里面，i 从0开始。 但是，contains()本身是O(n).     
在这道题里面应该是因为word dictionary太大，加上nest for, 变成O(n^3)所以timeout.

istead,用一个isWord[i][j]，就O(1)判断了i~j是不是存在dictionary里面。



---

**4. [Graph Valid Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Graph%20Valid%20Tree.java)**      Level: Review
      

M 

给一个数字n代表n nodes, marked from 1 ~ n, 和一串undirected edge int[][]. 检查这些edge是否能合成一个 valid tree

#### Union Find
- 复习Union-Find的另外一个种形式, track union size
- 题目类型：查找2个元素是不是在一个union里面。如果不在，false. 如果在，那就合并成一个set,共享parent.   
- 存储的关键都是：元素相对的index上存着他的root parent.    
- 注意: 结尾要检查, 是否只剩下1个union: Tree必须连接到所有给出的node.
- 另一个union-find, 用hashmap的:
- http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/

#### DFS
- (还没做, 可以写一写)
- 检查: 1. 是否有cycle, 2. 是否所有的node全部链接起来

#### BFS
- (还没做, 可以写一写)
- 也是检查: 1. 是否有cycle, 2. 是否所有的node全部链接起来



---

**5. [Surrounded Regions.java](https://github.com/awangdev/LintCode/blob/master/Java/Surrounded%20Regions.java)**      Level: Review
      

给一个2D board, 里面是 'X' 和 'O'. 把所有被X包围的area都涂成'X'. 

从四个边的edge出发, 像感染僵尸病毒一样扩散, 把靠边的node全部mark, 然后将还是'O'的改成X, 最后回复marks -> 'O'

#### Union Find
- UnionFind里面这次用到了一个rank的概念, 需要review. rank[] 也就是在tracking每一个node所在union的size.
- 目的是: always并到大的union里面
- note: 将2D coordinate (x,y) 转换成1D: index = x * n + y

#### DFS
- TODO

#### BFS
- TODO




---

**6. [Maximum Average Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20II.java)**      Level: Review
      

给int[] nums 和 window min size k. window size可以大于K. 找最大的连续数列average value.

- Binary Search的思想, 用在所要找的这个 average sum 上面. 大小是在[min, max]之中
- 找k的时候, 是>=k都可以, 巧用一个 min(preSum)的概念.
- 找k的时候, 画图, 可以看出来, 其实要的是 k window 里面的sum [x, i], 所以要用 sum[0, i] - sum[0, x]

需要仔细去读下面的notes.



---

**7. [Building Outline.java](https://github.com/awangdev/LintCode/blob/master/Java/Building%20Outline.java)**      Level: Review
      

又叫做skyline. 用Sweep Line做的O(nLogN), 但是貌似还有很多做法: segement tree, hashheap, treeSet?

#### 方法1: Sweep Line, Time O(nLogN), Space O(n)
original reference http://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html?_sm_au_=isVmHvFmFs40TWRt

sweep line:
- 把所有点分出来， 每个点有index x, 再加上一个height.         
- 在这个list上排序，根据index和height. 注意用负数标记building start point height, 这样保证start在end 之前
- 用负数的height标记start: 在priority queue里面同一个x-pos比较 startPoint.height, endPoint.height 的时候, 因为end height是整数, 所以compare时会自动把start point放在end point前面
- 当然了, 如果两个 start point比较, 第二个point的负数超大的话(也就是height很高), 就会顺理compare return正数, 成章形成倒位
- 在processs时候用max-heap (reversed priorityqueue)，再iterate heightPoints 来存最大的height . 遇到peek,就是一个合理的解    
- heightQueue里面加一个0, 用来在结尾的时候做closure

#### 方法2: Segment Tree
REVIEW

Binary Indexed Tree?

HashHeap?



---

**8. [Flip Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Flip%20Game%20II.java)**      Level: Review
      

#### Backtracking
- curr player 每走一步, 就生成一种新的局面, dfs on this
- 等到dfs结束, 不论成功与否, 都要backtracking
- curr level: 把"++" 改成 "--"; backtrack的时候, 改回 '--'
- 换成boolean[] 比 string/stringBuilder要快很多, 因为不需要重新生成string.
- ++ 可以走 (n - 1)个位置: 
- T(N) = T(1) + T(2) + T(3) + ... + T(N-2) + T(N - 1)
- => T(N) = 2T(N-1) = 2 * 2 * T(N - 2) = ... = (2^n)T(1) = O(2 ^ n)

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



---

