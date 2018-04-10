 
 
 
## Tree (20)
**0. [Unique Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree.java)**      Level: Medium
      

Not quite clear.
根据左右分割而总结出了原理, 每次分割, 左右两边都会有一定数量的permutation, 总体上的情况数量当然是相乘.
然后每一个不同的分割点都加一遍:
f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

然后把数学公式转换成DP的方程, 有点玄学的意思啊! 不好想.



---
**1. [Trim a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Trim%20a%20Binary%20Search%20Tree.java)**      Level: Easy
      

方法1:
适合复习BST. 用DFS对待每个node. 注意BST的特征: 所有left nodes都小于当下node, 所有right nodes都大于当下node.

根据题意用[L,R]切割.如果node.val<L, 直接连node带左边全丢掉, return node.right. 处理R也是一样.
分制是, DFS leftNode, rightNode. 然后接在node.left, node.right.

方法2: 用迭代, 还没有写.



---
**2. [Binary Search Tree Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Search%20Tree%20Iterator.java)**      Level: Medium
      

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
**3. [Same Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Same%20Tree.java)**      Level: Easy
      

DFS. 确定leaf条件, && with all sub problems.

如果用BFS: 两个queue存每个tree的所有current level node. Check equality, check queue size.
Populate next level by nodes at current level.



---
**4. [Convert Sorted Array to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree.java)**      Level: Easy
      

Binary Search Tree特点: 左边的node都比右边的node小. 
如果要height相差<1, 必须左右sub tree均分. 做DFS.



---
**5. [Binary Tree Maximum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum.java)**      Level: Review
      

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
**6. [Construct Binary Tree from Inorder and Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Preorder%20Traversal.java)**      Level: Medium
      

和Construct from Inorder && Postorder 想法一样。

写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。



---
**7. [Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum.java)**      Level: Easy
      

确定好结尾的条件, DFS

写一写: root == null => false 对parent nodes的影响. 这里发现没影响, 所以可以简化成用1个functionDFS.




---
**8. [Balanced Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Balanced%20Binary%20Tree.java)**      Level: Medium
      

1. DFS using depth marker: 每个depth都存一下。然后如果有不符合条件的，存为-1.
   一旦有 <0 或者差值大于1， 就全部返回Integer.MIN_VALUE. Integer.MIN_VALUE比较极端, 确保结果的正确性。
   最后比较返回结果是不是<0. 是<0，那就false.
   Traverse 整个tree, O(n)

2. Only calculate depth using maxDepth function. Same concept as in 1, but cost more traversal efforts.



---
**9. [Populating Next Right Pointers in Each Node.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node.java)**      Level: Medium
      

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
**10. [Validate Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Validate%20Binary%20Search%20Tree.java)**      Level: Medium
      

查看每个parent-child关系。同时把root level上面传下来max,min界限定住。

Note: min/max需要时long type. 
如果题目真的给node.val = Integer.MAX_VALUE, 我们需要能够与之比较, long就可以.



---
**11. [Max Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Tree.java)**      Level: Medium
      

#### Monotonous Stack
用到bottom->top递减的stack: 最底下的root维持成最大的element.
过程当中, 一旦遇到currNode.val > stack.peek(), 就意味着需要把这个currNode放在 stack的底层位置.
也就是说, 遇到这个条件, process, pop()所有 currNode.val > stack.peek(), 最后把currNode加进去.

maxTree题目本身的要求是: 大的在最中间, 左右两边的subTree也要是maxTree:
- Monotonous Stack在这里帮助 keep/track of max value, 但是left/right tree的logic是MaxTree独有的.
- left/right node的assignment是根据题目要求: 中间最大值分开后, 左边的是左边subTree, 右边的作为右边subTree.

#### Previous notes
Should memorize MaxTree. 依次类推，会做Min-Tree, Expression Tree

Stack里，最大的值在下面。利用此性质，有这样几个step:
1   
把所有小于curr node的，全Pop出来, while loop, keep it going.    
最后pop出的这个小于Curr的node：它同时也是stack里面pop出来小于curr的最大的一个，最接近curr大小。（因为这个stack最大值靠下面）    
把这个最大的小于curr的node放在curr.left.    

2   
那么，接下去stack里面的一定是大于curr：   
那就变成curr的left parent. set stack.peek().right = curr.

3   
结尾：stack底部一定是最大的那个，也就是max tree的头。





---
**12. [Binary Tree Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Inorder%20Traversal.java)**      Level: Easy
      

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
**13. [Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20II.java)**      Level: Easy
      

Binary Tree的一个基本题: 找到所有满足条件的path

- 遍历到底，比较sum vs. target
- 注意divide的情况。要把遍历的例子写写



---
**14. [Binary Tree Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Postorder%20Traversal.java)**      Level: Medium
      

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
**15. [Closest Binary Search Tree Value.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Binary%20Search%20Tree%20Value.java)**      Level: Easy
      

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
**16. [Count Complete Tree Nodes.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20Complete%20Tree%20Nodes.java)**      Level: Medium
      

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
**17. [Binary Tree Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Preorder%20Traversal.java)**      Level: Easy
      

#### Recursive
- 加root, left, then right. Obvious
- Divide and conquer
- 其实也不需要helper function

#### Iterative
- 先加root, 然后push上需要末尾process的在stack垫底(root.right), 然后push root.left
- Stack: push curr, push right, push left.   



---
**18. [Complete Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Complete%20Binary%20Tree.java)**      Level: Easy
      

#### BFS
- 当出现了第一次有 null children的node的时候, 说明到了leaf level, mark flag = true;
- 自此以后，queue再不该有node再有child; queue后面出现的node的left/right child应该都是null
- 否则就是有问题, return false;

#### DFS
- Count left-most-leaf depth
- Count right-most-leaf depth
- 如果两个depth不一样, 就 false
- LintCode跑不了




---
**19. [House Robber III.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20III.java)**      Level: Medium
      

Houses被arrange成了binary tree, 规则还是一样, 连续相连的房子不能同时抄.

求Binary Tree neighbor max 能抄多少.

#### DFS
- 判断当下的node是否被采用，用一个boolean来表示. 
- 如果curr node被采用，那么下面的child一定不能被采用.
- 如果curr node不被采用，那么下面的children有可能被采用，但也可能略过，所以这里用Math.max() 比较一下两种可能有的dfs结果。
- dfs重复计算:每个root都有4种dive in的可能性, 假设level高度是h, 那么时间O(4^(h)), where h = logN, 也就是O(n^2)

#### DP 
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
