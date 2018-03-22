 
 
 
## BFS (6)
**0. [Perfect Squares.java](https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Squares.java)**      Level: Medium
      

分割型. 考虑最后的数字: 要是12割个1出来, 剩下11怎么考虑? 割个4出来,剩下8怎么考虑?
就变成了dp = Min{dp[i - j^2] + 1}

时间复杂度: 乍一看是O(n*sqrt(n)). 实际也是. 但如何推导?
考虑sqrt(1) + sqrt(2) + ....sqrt(n):找这个的upper bound and lower bound.
最后发现它的两边是 A*n*sqrt(n) <= actual time complexity <= B*n*sqrt(n)
那么就是O(n*sqrt(n))啦

Previous Notes:
一开始没clue.看了一下提示。

１.　第一步想到了，从数学角度，可能是从最大的perfect square number开始算起。
２.　然后想法到了dp， 假设最后一步用了最大的maxSqrNum, 那么就在剩下的 dp[i - maxSqrNum^2] +１　不就好了？
３.　做了，发现有个问题．．．最后一步选不选maxSqrNum?  比如12就是个例子。
	然后就根据提示，想到BFS。顺的。 把1～maxSqrNum 都试一试。找个最小的。
	看我把12拆分的那个example. 那很形象的就是BFS了。
	面试时候，如果拆分到这个阶段不确定，那跟面试官陶瓷一下，说不定也就提示BFS了。



---
**1. [Clone Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Clone%20Graph.java)**      Level: Medium
      

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
**2. [Number of Islands.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Islands.java)**      Level: Medium
      

方法1: 两个for loop brutle force。 DFS把每个跟1相关的都Mark一遍.生成一个island.

方法2:
可以用union-find， 就像Number of island II 一样。
只不过这个不Return list, 而只是# of islands
记住UnionFind的模板和几个变化(Connecting Graph I, II, III), 最后归总的代码写起来就比较简单.



---
**3. [Graph Valid Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Graph%20Valid%20Tree.java)**      Level: Medium
      

复习Union-Find的另外一个种形式。   
题目类型：查找2个元素是不是在一个set里面。如果不在，false. 如果在，那就合并成一个set,共享parent.   
存储的关键都是：元素相对的index上存着他的root parent.    

注意: 结尾要检查, 是否只剩下1个union. Tree必须连接到所有给出的node.

另一个union-find， 用hashmap的：http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/




---
**4. [Surrounded Regions.java](https://github.com/awangdev/LintCode/blob/master/Java/Surrounded%20Regions.java)**      Level: Review
      

从四个边的edge出发, 像感染僵尸病毒一样扩散, 把靠边的node全部mark, 然后将还是'O'的改成X, 最后回复marks -> 'O'

方法1:
UnionFind里面这次用到了一个rank的概念, 需要review

方法2,3:
DFS, BFS都好理解, 




---
**5. [Trapping Rain Water II.java](https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water%20II.java)**      Level: Hard
      

用PriorityQueue把选中的height排序。为走位，create class Cell (x,y, height).

#### 注意几个理论
1. 从matrix四周开始考虑，发现matrix能Hold住的水，取决于height低的block。
2. 必须从外围开始考虑，因为水是被包裹在里面，外面至少需要现有一层。

以上两点就促使我们用min-heap: 也就是natural order的PriorityQueue<Cell>.

#### 具体步骤
1. process的时候，画个图也可以搞清楚: 就是四个方向都走走，用curr cell的高度减去周围cell的高度.
   若大于零，那么周围的cell就有积水。
2. 每个visited的cell都要mark. 
3. 根据4个方向的走位, 创建新cell 加进queue里面. 因为做的是缩小一圈的新围墙, height = Math.max(cell.h, neighbor.h);

再多一句解释: 和trapping water I 想法一样。刚刚从外围，只是能加到跟外围cell高度一致的水平面。往里面，很可能cell高度变化。   
这里要附上curr cell 和 move-to cell的最大高度。

#### 为什么想到用Heap (min-heap - priorityQueue)
- 要找到bucket的最短板
- 每次需要最先处理最短的那条 (on top)


#### 为什么从外向里遍历
- 木桶理论, 包水, 是从外面包住里面
- 洋葱剥皮, 用完丢掉



---
