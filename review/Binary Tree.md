 
 
 
## Binary Tree (13)
**0. [Flatten Binary Tree to Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Flatten%20Binary%20Tree%20to%20Linked%20List.java)**      Level: Medium      Tags: [Binary Tree, DFS]
      

给一个binary tree, 把tree做成 linked list的形式, in-place.

#### DFS
- 分析题意后, 按照题意: Flatten the tree, no extra space.
1. reserve right child
2. DFS flatten部分
3. 移花接木
4. flatten 剩下的.

##### 注意
- 顺序一定要清楚, 不能写错, 写几个example可以看出来
- 移动的那些node, 要把node.left = null, 清扫干净



---

**1. [Binary Tree Paths.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Paths.java)**      Level: Easy      Tags: [Backtracking, Binary Tree, DFS]
      

给一个binary tree, 返回所有root-to-leaf path

#### DFS, backtracking
- Find all paths, bfs/dfs all works. dfs will be simplier to write
- Recursive:分叉. dfs.
- top->bottom: enumerate current node into the list, carry to next level, and backtrack
- top->bottom is trivial to consider: path flows from top->bottom

#### DFS, bottom->up
- We can also take current node.left or node.right to generate list of results from the subproblem
- let dfs return list of string candidates, and we can run pair the list with currenet node, once they come back.
- TODO: can write code to practice

#### Iterative
- Iterative, 非递归练习了一下
- 因为要每次切短list, 所以再加了一个Stack 来存level
- 单这道题用dfs更简单, 因为找的就是从头到尾的path, 是dfs的pattern




---

**2. [Expression Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Tree%20Build.java)**      Level: Hard      Tags: [Binary Tree, Expression Tree, Minimum Binary Tree, Stack]
      

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

**3. [Expression Evaluation.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Evaluation.java)**      Level: Hard      Tags: [Binary Tree, DFS, Expression Tree, Minimum Binary Tree, Stack]
      

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

**4. [Convert Expression to Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Expression%20to%20Polish%20Notation.java)**      Level: Hard      Tags: [Binary Tree, DFS, Expression Tree, Stack]
      

给一串字符, 用来表示公式expression. 把这个expression转换成 Polish Notation (PN).

#### Expression Tree
- Expression Tree: Minimum Binary Tree (https://lintcode.com/en/problem/expression-tree-build/)
- 根据题意做出Expression Tree出来以后: 来个Pre-order-traversal 就能记录下 Polish Notation
- 本题没有给'ExpressionTreeNode', 所以把TreeNode就当做成我们需要的node, 里面扩展成有left/right child就可以了.
- Note: label需要是String. 虽然 Operator是长度为1的char, 但是数字可为多位



---

**5. [Convert Expression to Reverse Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Expression%20to%20Reverse%20Polish%20Notation.java)**      Level: Hard      Tags: [Binary Tree, DFS, Expression Tree, Stack]
      

给一串字符, 用来表示公式expression. 把这个expression转换成 Reverse Polish Notation (RPN).

#### Expression Tree
- Expression Tree: Minimum Binary Tree (https://lintcode.com/en/problem/expression-tree-build/)
- 根据题意做出Expression Tree出来以后: 来个Post-order-traversal 就能记录下 Reverse Polish Notation
- 本题没有给'ExpressionTreeNode', 所以把TreeNode就当做成我们需要的node, 里面扩展成有left/right child就可以了.



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

**7. [Segment Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Build.java)**      Level: Medium      Tags: [Binary Tree, Divide and Conquer, Segment Tree]
      

给一个区间[startIndex, endIndex], 建造segment tree structure, return root node.

#### Segment Tree definition
- Recursively build the binary tree
- 左孩子：（A.left, (A.left+A.rigth)/2）   
- 右孩子：（(A.left+A.rigth)/2＋1， A.right）   



---

**8. [Segment Tree Build II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Build%20II.java)**      Level: Medium      Tags: [Binary Tree, Divide and Conquer, Segment Tree]
      

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

**9. [Segment Tree Query.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Segment Tree]
      

给了segment Tree, node里面有Max value, 找[start,end]里面的max

#### Segment Tree, Divide and Conquer
- 根据[start,end]跟 mid of (root.start, root.end) 做比较:
- 简单的2个case: [start,end]全在mid左, 或者[start, end]全在mid右
- 稍微复杂的3rd case: [start, end]包含了mid, 那么就break into 2 queries
- [start, node.left.end], [node.right.start, end]



---

**10. [Segment Tree Modify.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Modify.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Segment Tree]
      

给一个segmentTree, node里面存max. 写一个modify function: modify(node, index, value).

#### Segment Tree, Divide and Conquer
- Recursively 在segment tree里面找index, update it with value.   
- 每个iteration，很可能（要么左手，要么右手）max就变了。所以每次都left.max and right.max compare一下
- 最后轮回到头顶，头顶一下包括头顶，就全部都是max了



---

**11. [Segment Tree Query II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query%20II.java)**      Level: Medium      Tags: [Binary Tree, DFS, Divide and Conquer, Segment Tree]
      

#### Segment Tree
- 和 Segment Tree Query I 以及其他Segment Tree类似: 这个SegmentTreeNode return count of elements in range
- 这个题目考了validate input source：input 的start,end可能超出root[start,end]。   
- 那么第一步就要先clear一下: 1. 完全不在range就return 0. 2. 有range重合就规整到root的range.




---

**12. [Search Range in Binary Search Tree .java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20Range%20in%20Binary%20Search%20Tree%20.java)**      Level: Medium      Tags: [BST, Binary Tree]
      

给一个BST, integer range (k1, k2), 找range 里面所有的integer.

#### BST
- 等于dfs遍历了所有k1<= x <= k2的x node。
- dfs left, process root, then dfs right
- 这里, 把 left/right/match的情况全部cover了，然后把k1,k2的边框限制好，中间就全部遍历了。



---

