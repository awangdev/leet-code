 
 
 
## Graph (11)
**0. [Evaluate Division.java](https://github.com/awangdev/LintCode/blob/master/Java/Evaluate%20Division.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      

#### DFS
- build map of `x#y -> val` to store values[i] and 1/values[i]
- build map of `x -> list children`
- dfs to traverse the graph

#### BFS
- BFS should also work: build graph and valueMap
- for each starting item, add all next candidate to queue
- mark visited, loop until end item is found



---

**1. [Minimum Height Trees.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Height%20Trees.java)**      Level: Medium      Tags: [BFS, Graph]
      

#### Graph + BFS
- Build graph `map<node, list of node>`
- BFS to find the shortest path: when the neibhbor has the curr node as the only one neighbor, it is leaf.
- record shortest path in Map<Integer, List<Integer>> as result
- TODO: code it up.

#### Previous Solution
- removing leaf && edge



---

**2. [Course Schedule.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS, Graph, Topological Sort]
      

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

**3. [Course Schedule II.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule%20II.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Topological Sort]
      

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

**4. [Number of Connected Components in an Undirected Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      

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

**5. [Alien Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/Alien%20Dictionary.java)**      Level: Hard      Tags: [BFS, Backtracking, DFS, Graph, Topological Sort]
      

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

**6. [Clone Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Clone%20Graph.java)**      Level: Medium      Tags: [BFS, DFS, Graph]
      

给一个graph node, 每个node有list of neighbors. 复制整个graph, return new head node.
       
实现起来就好像在crawl urls.

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

**7. [Sliding Puzzle.java](https://github.com/awangdev/LintCode/blob/master/Java/Sliding%20Puzzle.java)**      Level: Hard      Tags: [BFS, Graph]
      



---

**8. [Redundant Connection.java](https://github.com/awangdev/LintCode/blob/master/Java/Redundant%20Connection.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Tree, Union Find]
      

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

**9. [Graph Valid Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Graph%20Valid%20Tree.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      

给一个数字n代表n nodes, marked from 1 ~ n, 和一串undirected edge int[][]. 

检查这些edge是否能合成一个 valid tree

#### Union Find
- 复习Union-Find的另外一个种形式, track union size: tree does not have cycle, so eventually union size should == 1
- 1. 查找2个元素是不是在一个union里面。如果不在，false. 如果在，那就合并成一个set,共享parent.   
- 2. 验证cycle: `find(x) == find(y) => cycle`: new index has been visited before
- 存储的关键都是：元素相对的index上存着他的root parent.    
- 注意: 结尾要检查, 是否只剩下1个union: Tree必须连接到所有给出的node.
- 另一个union-find, 用hashmap的:
- http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/

#### DFS
- Very similar to `Redundant Connection`
- Create adjacent list graph: Map<Integer, List<Integer>>
- 检查: 
- 1. 是否有cycle using dfs, check boolean[] visited
- 2. 是否所有的node全部链接起来: validate if all edge connected: # of visited node should match graph size
- IMPORTANT: use `pre` node to avoid linking backward/infinite loop such as (1)->(2), and (2)->(1)

#### BFS
- (还没做, 可以写一写)
- 也是检查: 1. 是否有cycle, 2. 是否所有的node全部链接起来



---

**10. [Redundant Connection II.java](https://github.com/awangdev/LintCode/blob/master/Java/Redundant%20Connection%20II.java)**      Level: Hard      Tags: [DFS, Graph, Tree, Union Find]
      

#### Union Find
- 讨论3种情况
- http://www.cnblogs.com/grandyang/p/8445733.html



---

