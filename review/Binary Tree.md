 
 
 
## Binary Tree (14)
**0. [Convert Expression to Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Expression%20to%20Polish%20Notation.java)**      Level: Hard      Tags: [Binary Tree, DFS, Expression Tree, Stack]
      
给一串字符, 用来表示公式expression. 把这个expression转换成 Polish Notation (PN).

#### Expression Tree
- Expression Tree: Minimum Binary Tree (https://lintcode.com/en/problem/expression-tree-build/)
- 根据题意做出Expression Tree出来以后: 来个Pre-order-traversal 就能记录下 Polish Notation
- 本题没有给'ExpressionTreeNode', 所以把TreeNode就当做成我们需要的node, 里面扩展成有left/right child就可以了.
- Note: label需要是String. 虽然 Operator是长度为1的char, 但是数字可为多位



---

**1. [Expression Evaluation.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Evaluation.java)**      Level: Hard      Tags: [Binary Tree, DFS, Expression Tree, Minimum Binary Tree, Stack]
      
给一个公式 expression, array of strings, 然后evaluate expression 结果.

#### DFS on Expression Tree
- 计算 expression 的值: 1. 建造 expression tree. 2. DFS计算结果
- Expression Tree: Minimum Binary Tree (https://lintcode.com/en/problem/expression-tree-build/)
- build好Min Tree以后，做PostTraversal. 
- Divde and Conquer: 先recursively找到 left和right的大小， 然后evaluate中间的符号
- Time, Space O(n), n = # expression nodes

#### Note
- 1. Handle数字时，若left&&right Child全Null,那必定是我们weight最大的数字node了。   
- 2. 若有个child是null,那就return另外一个node。    
- 3. prevent Integer overflow　during operation:过程中用个Long，最后结局在cast back to int.



---

**2. [Search Range in Binary Search Tree .java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20Range%20in%20Binary%20Search%20Tree%20.java)**      Level: Medium      Tags: [BST, Binary Tree]
      
给一个BST, integer range (k1, k2), 找range 里面所有的integer.

#### BST
- 等于dfs遍历了所有k1<= x <= k2的x node。
- dfs left, process root, then dfs right
- 这里, 把 left/right/match的情况全部cover了，然后把k1,k2的边框限制好，中间就全部遍历了。



---

**3. [Segment Tree Modify.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Modify.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Lint, Segment Tree]
      
给一个segmentTree, node里面存max. 写一个modify function: modify(node, index, value).

#### Segment Tree, Divide and Conquer
- Recursively 在segment tree里面找index, update it with value.   
- 每个iteration，很可能（要么左手，要么右手）max就变了。所以每次都left.max and right.max compare一下
- 最后轮回到头顶，头顶一下包括头顶，就全部都是max了



---

**4. [Expression Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Tree%20Build.java)**      Level: Hard      Tags: [Binary Tree, Expression Tree, Minimum Binary Tree, Stack]
      
给一串字符, 表示的是 公式 expression. 把公式变成expression tree

#### Monotonous Stack
- 和Max-tree一样，https://leetcode.com/problems/maximum-binary-tree
- 用到bottom->top递增的stack: 最底下的root维持成最小的element.
- 这个题目是Min-tree， 头上最小，Logic 和max-tree如出一辙   
- Space: O(n) 
- Time on average: O(n).

#### 特点
- TreeNode: 用一个并不是最终结果的TreeNode, 存weight, 用来排序
- 用base weight的概念权衡同一个层面的 符号, 数字 顺序
- 每一个character都是一个节点, 都有自己的weight. 用一个TreeNode来存weight value, 利用用weight来判断: 
- 1. (while loop) 如果node.val <= stack.peek().nodeValue, 把当前stack.peek() 变成 left child. 
- 2. (if condition) 如果stack有残余, 把当前node变成 stack.peek().rightChild 




---

**5. [Segment Tree Query II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query%20II.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Lint, Segment Tree]
      
#### Segment Tree
- 和 Segment Tree Query I 以及其他Segment Tree类似: 这个SegmentTreeNode return count of elements in range
- 这个题目考了validate input source：input 的start,end可能超出root[start,end]。   
- 那么第一步就要先clear一下: 1. 完全不在range就return 0. 2. 有range重合就规整到root的range.




---

**6. [Basic Calculator.java](https://github.com/awangdev/LintCode/blob/master/Java/Basic%20Calculator.java)**      Level: Hard      Tags: [Binary Tree, Expression Tree, Math, Minimum Binary Tree, Stack]
      
给一个expression String, 要evaluate这个expression的值.

Expression string 里面包括 +, -, 整数, 开合括号, 还有space.

#### Expression Tree
- Expression Tree是一个 weight-based的 min-tree 
- 基于 运算符号 + 数字的 tree: 数字永远在leaf, 然后符号是tree node,  括号不出现在tree里面
- 用 monotonuous stack 来构建这个tree

##### Thinking points
- Understand Expression Tree
- Use stack to build the expression tree + understand the weight system
- Use post-order traversal to evaluate the tree
- 注意, input里面的数字不会是single digit, 所以需要一个buffer存number string
- 整个题目的做法, 可以参照 `Expression Evaluation`



---

**7. [Convert Expression to Reverse Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Expression%20to%20Reverse%20Polish%20Notation.java)**      Level: Hard      Tags: [Binary Tree, DFS, Expression Tree, Stack]
      
给一串字符, 用来表示公式expression. 把这个expression转换成 Reverse Polish Notation (RPN).

#### Expression Tree
- Expression Tree: Minimum Binary Tree (https://lintcode.com/en/problem/expression-tree-build/)
- 根据题意做出Expression Tree出来以后: 来个Post-order-traversal 就能记录下 Reverse Polish Notation
- 本题没有给'ExpressionTreeNode', 所以把TreeNode就当做成我们需要的node, 里面扩展成有left/right child就可以了.



---

**8. [[lint]. Segment Tree Build II.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Segment%20Tree%20Build%20II.java)**      Level: Medium      Tags: [Binary Tree, Divide and Conquer, Lint, Segment Tree]
      
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

**9. [[lint]. Segment Tree Query.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Segment%20Tree%20Query.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Lint, Segment Tree]
      
给了segment Tree, node里面有Max value, 找[start,end]里面的max

#### Segment Tree, Divide and Conquer
- 根据[start,end]跟 mid of (root.start, root.end) 做比较:
	- 1) [start,end] on LEFT of mid
	- 2) [start, end] on RIGHT of mid
	- 3) [start, end] includes mid: break into 2 queries
		- query [leftNode, start, node.left.end]
		- query [rightNode, node.right.start, end]



---

**10. [[lint]. Segment Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Segment%20Tree%20Build.java)**      Level: Medium      Tags: [Binary Tree, Divide and Conquer, Lint, Segment Tree]
      
给一个区间[startIndex, endIndex], 建造segment tree structure, return root node.

#### Segment Tree
- Usage
  - which of these intervals contain a given point
  - which of these points are in a given interval
- Recursively build the binary tree
  - 左孩子：（A.left, (A.left+A.rigth)/2）   
  - 右孩子：（(A.left+A.rigth)/2＋1， A.right）   



---

**11. [987. Vertical Order Traversal of a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/987.%20Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree.java)**      Level: Medium      Tags: [BFS, Binary Tree, DFS, Hash Table, Tree]
      
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

**12. [257. Binary Tree Paths.java](https://github.com/awangdev/LintCode/blob/master/Java/257.%20Binary%20Tree%20Paths.java)**      Level: Easy      Tags: [Backtracking, Binary Tree, DFS]
      


给一个binary tree, 返回所有root-to-leaf path

#### DFS, backtracking
- Find all paths, bfs/dfs all works. dfs will be simplier to write
- Recursive:分叉. dfs.
- top->bottom: enumerate current node into the list, carry to next level, and backtrack
- top->bottom is trivial to consider: path flows from top->bottom
- time: visit all n nodes
- space: to hold all paths, O(nlogn)
    - O((n-1)/2) = O(n) nodes at leaf
    - O(logn) depth

#### DFS, bottom->up
- We can also take current node.left or node.right to generate list of results from the subproblem
- let dfs return list of string candidates, and we can run pair the list with currenet node, once they come back.
- TODO: can write code to practice

#### Iterative
- Iterative, 非递归练习了一下
- 因为要每次切短list, 所以再加了一个Stack 来存level
- 单这道题用dfs更简单, 因为找的就是从头到尾的path, 是dfs的pattern




---

**13. [114. Flatten Binary Tree to Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/114.%20Flatten%20Binary%20Tree%20to%20Linked%20List.java)**      Level: Medium      Tags: [Binary Tree, DFS]
      

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

