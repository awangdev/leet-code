 
 
 
## Topological Sort (6)
**0. [Longest Increasing Path in a Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Path%20in%20a%20Matrix.java)**      Level: Hard      Tags: [Coordinate DP, DFS, DP, Memoization, Topological Sort]
      
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

**1. [[tool]. Topological Sorting.java](https://github.com/awangdev/LintCode/blob/master/Java/[tool].%20Topological%20Sorting.java)**      Level: Medium      Tags: [BFS, DFS, Lint, Topological Sort]
      

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

**2. [269. Alien Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/269.%20Alien%20Dictionary.java)**      Level: Hard      Tags: [BFS, Backtracking, DFS, Graph, Topological Sort]
      

给一个 array of strings: 假如这个array是按照一个新的字母排序表(alien dictionary)排出来的, 需要找到这个字母排序.

有可能有多重排序的方法, 给出一种就可以.

#### Graph, Topological Sort with InDegree count, BFS
- `Build graph`:
    - 上下两行string, 相对应的相同的index上, 如果字母不同, 就说明排在第一行的字母在字母表里更领先: form sequence between chars
    - form graph Map<Char, List of Chars>, for topological sort usage.
    - 也可以`List[26] edges` (Course Schedule problem)
- `Build InDegreeCountMap<Char, Count>`: based on the char diff of 2 words
    - 注意: indegree 是反向的 (跟 node to neighbors 相反的方式建立)
- `Topological Sort`, BFS:
    - 1) use queue to find `inDegree == 0` node. It is the letter that points to others, 排在字母表前面.
    - 2) reduce edges using Graph`map<Character, List<Character>>` (more generic than List[26], 26个字母的dictionary)
- Edge Case:
    - `inDegreeCountMap.size() != result.length()`: some nodes did not make it into result sequence
    - `cycle`: when inDegree of a one node would never reduce to 0, and will not be added to result
        - In this case, it will be treated as invalid input, and return ""
- space: O(n), n = # of graph edges 
- time: O(n)

#### DFS
- TODO
- 跟BFS建立 grpah 的过程一模一样
- DFS的不同在于: 用visited map 来标记走过的地方
- 走到leaf的时候, add to result: 但因为走到了底才add, 最终的顺序应该颠倒 (或者, sb.insert(0, x) 直接用颠倒的顺序add)



---

**3. [207. Course Schedule.java](https://github.com/awangdev/LintCode/blob/master/Java/207.%20Course%20Schedule.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS, Graph, Topological Sort]
      

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

**4. [1203. Sort Items by Groups Respecting Dependencies.java](https://github.com/awangdev/LintCode/blob/master/Java/1203.%20Sort%20Items%20by%20Groups%20Respecting%20Dependencies.java)**      Level: Hard      Tags: [BFS, DFS, Graph, Topological Sort]
      

#### Topological Sort
- Realize we need to: 1) topo sort group, 2) topo sort items in the group. 
- Luckily, the candidates to be sorted are all integers: groupIds, or item ids. We can have 1 generic topo sort function
- Overall workflow
    - 1) group items to map <GroupId, List<items>>
    - 2) build group graph
    - 3) topo sort group -> return sorted group id list
    - 4) for each group: build item graph, topo sort items -> return sorted item list
    - 5) flatten and return results



---

**5. [210. Course Schedule II.java](https://github.com/awangdev/LintCode/blob/master/Java/210.%20Course%20Schedule%20II.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Topological Sort]
      

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

