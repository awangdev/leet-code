 
 
 
## Graph (4)
**0. [Clone Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Clone%20Graph.java)**      Level: Medium
      

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
**1. [Graph Valid Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Graph%20Valid%20Tree.java)**      Level: Medium
      

复习Union-Find的另外一个种形式。   
题目类型：查找2个元素是不是在一个set里面。如果不在，false. 如果在，那就合并成一个set,共享parent.   
存储的关键都是：元素相对的index上存着他的root parent.    

注意: 结尾要检查, 是否只剩下1个union. Tree必须连接到所有给出的node.

另一个union-find， 用hashmap的：http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/




---
**2. [Course Schedule.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule.java)**      Level: Medium
      

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
**3. [Course Schedule II.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule%20II.java)**      Level: Medium
      

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
