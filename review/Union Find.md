 
 
 
## Union Find (15)
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

**1. [Connecting Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Connecting%20Graph.java)**      Level: Medium      Tags: [Union Find]
      

没有跑过这个程序, 是一个UnionFind的简单实现.
Document了每个环节的计算原理/思想.



---

**2. [Connecting Graph II.java](https://github.com/awangdev/LintCode/blob/master/Java/Connecting%20Graph%20II.java)**      Level: Medium      Tags: [Union Find]
      

Lint还不能跑, 全部按照题意和答案document的.



---

**3. [Connecting Graph III.java](https://github.com/awangdev/LintCode/blob/master/Java/Connecting%20Graph%20III.java)**      Level: Medium      Tags: [Union Find]
      

还是UnionFind的变形, 这次是算有剩下多少个union. 其实非常简单, 维持一个全局变量count:
一开始count=n, 因为全是散装elements;  每次union, count--.



---

**4. [Number of Islands.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Islands.java)**      Level: Medium      Tags: [BFS, DFS, Matrix DFS, Union Find]
      

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

**5. [Number of Islands II.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Islands%20II.java)**      Level: Hard      Tags: [Union Find]
      

给一个island grid[][], and list of operations to fill a particualr (x,y) position.

count # of remaining island after each operation.

#### Union Find, model with int[]
- 把board转换成1D array， 就可以用union-find来判断了. 
- 用int[] father 的unionFind, 需要转换2D position into 1D index. 这样比较clean
- 判断时，是在四个方向各走一步，判断是否是同一个Land.
- 每走一次operator，都会count++. 若发现是同一个island, count--
- count的加减, 都放在了UnionFind自己的function里面, 方便tracking, 给几个helper function就对了.
- Time: O(k * log(mn))

#### Union Find, model with Hashmap 
- 用HashMap的Union-find.


#### Note:
- Proof of UnionFind log(n) time: https://en.wikipedia.org/wiki/Proof_of_O(log*n)_time_complexity_of_union%E2%80%93find



---

**6. [Surrounded Regions.java](https://github.com/awangdev/LintCode/blob/master/Java/Surrounded%20Regions.java)**      Level: Medium      Tags: [BFS, DFS, Matrix DFS, Union Find]
      

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

**7. [Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Consecutive%20Sequence.java)**      Level: Hard      Tags: [Array, Hash Table, Union Find]
      

给一串数字, unsorted, 找这串数字里面的连续元素序列长度 (consecutive序列, 是数字连续, 并不是说要按照原order)

#### HashSet
- 要想看连续元素, 必须要num++, num--这样搜索
- 1. 需要O(1)找到元素
- 2. 需要简单快速找到 num - 1, num + 1.
- 如果用min,max开array, 耗费空间
- 用HashSet来存, 用set.contains() 来查找 num - 1, num + 1 存在与否
- for loop. O(n) 
- 里面的while loop 一般不会有O(n); 一旦O(n), 也意味着set 清零, for loop也不会有更多 inner while 的衍生.
- overall O(n) 时间复杂度


#### Union Find
- 最终是要把相连的元素算一下总长, 其实也就是把元素group起来, 相连的group在一起, 于是想到UnionFind
- 这里用到了一个`int[] size` 来帮助处理 `合并的时候parent是哪个`的问题: 永远往group大的union里去
- main function 里面, 有一个map来track, 每个元素, 只处理1遍.
- union的内容: current number - 1, current number + 1
- https://www.jianshu.com/p/e6b955ca208f

##### 特点
- Union Find 在index上做好像更加容易
- 其他union find function: `boolean connected(a,b){return find(a) == find(b)}`



---

**8. [Number of Connected Components in an Undirected Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      

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

**9. [Find the Weak Connected Component in the Directed Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Weak%20Connected%20Component%20in%20the%20Directed%20Graph.java)**      Level: Medium      Tags: [Union Find]
      

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

**10. [Accounts Merge.java](https://github.com/awangdev/LintCode/blob/master/Java/Accounts%20Merge.java)**      Level: Medium      Tags: [DFS, Hash Table, Hash Table, Union Find]
      

给一串account in format `[[name, email1, email2, email3], [name2, email,..]]`. 

要求把所有account merge起来 (可能多个record记录了同一个人, by common email)


#### Union Find
- 构建 `Map<email, email parent>`, 然后再反向整合: parent -> list of email
- init with <email, email> for all emails
- 因为不同account可能串email, 那么把所有email union的时候, 不同account 的email也会被串起来
- 最终: 所有的email都被union起来, 指向一个各自union的 parent email
- UnionFind 的 parent map 可以反向输出所有  child under parent.
- 同时要维护一个 <email -> account name> 的map, 最终用来输出.

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

**11. [Bricks Falling When Hit.java](https://github.com/awangdev/LintCode/blob/master/Java/Bricks%20Falling%20When%20Hit.java)**      Level: Hard      Tags: [Union Find]
      

给一个matrix of 1 and 0, `1` 代表brick. 连着ceiling的brick就不会drop. 给一串coordinate hits[][], 记录每次take down 1 brick 后, 会drop多少个.

#### UnionFind
- 1. 我们知道大部分的brick可能都是连着ceiling, 所以每次正向检查都traverse all and timeout
- 2. 能否用union, 把connect都装在一起, 然后drop brick的时候把连着的都drop掉? 难: 因为还是要check所有brick当下的status.
- 受其他人的解答启发, 由于是计算count,我们可以`反向考虑`: 
- 把hit-brick全部mark=2 (就当舍弃不算), 观察整个局面的最后一步, 先把所有还连着ceiling的brick算一下总数, 统计在unionFind的 全部统计在count[0] 里面. 
- 剩下的不连着ceiling的也就是一个个isolated island
- 做法: 把hit-brick 一个个加回去, 然后再做一次union, 看看最终连到ceiling的有多少个. 增加的count, 就是正向思考时 dropped brick 数量!

##### Union Find 变种
- 还是用数字index做union find, 但是把每一个index都+1, 右移一位, 而[0]留下来做特殊用途:
- 用union at 0来 统计总共的remain count of ceiling-connected bricks, where `x = 0`. 
- 如果在其他其他题目种, 条件可能就不是`x=0`, 但也可以用这个 union index = 0 来做一个root的统计
- 关键: 把最后一个hit brick加回去, 然后再重新union一下这个hit-brick周围: count增加的变化, 不就是缺少hit-brick时候掉下去的数量.


#### DFS (timeout)
- 考虑每个hit的四周, 全部traverse, 没有连着ceiling就全部: 
- 比如是 200 x 200 的 全部是1的matrix, 任何一次traverse都要到顶; 重复计算, 所以timeout
- 算法是没错, 但是不efficient.
- 想要减少重复计算, 但是又不能提前计算: grid在不断变化. 所以看能不能把连着ceiling的都group起来, 可以O(1)快速check?




---

**12. [Redundant Connection.java](https://github.com/awangdev/LintCode/blob/master/Java/Redundant%20Connection.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Tree, Union Find]
      

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

**13. [Graph Valid Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Graph%20Valid%20Tree.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Union Find]
      

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

**14. [Redundant Connection II.java](https://github.com/awangdev/LintCode/blob/master/Java/Redundant%20Connection%20II.java)**      Level: Hard      Tags: [DFS, Graph, Tree, Union Find]
      

#### Union Find
- 讨论3种情况
- http://www.cnblogs.com/grandyang/p/8445733.html



---

