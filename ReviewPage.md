# Review Page

This page summarize the solutions of all problems. For thoughts,ideas written in English, refer to deach individual solution. 
New problems will be automatically updated once added.

**0. [Add and Search Word.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20and%20Search%20Word.java)**      Level: Medium
      
Trie结构, prefix tree的变形： '.'可以代替任何字符，那么就要iterate这个node所有的children.

节点里面有char, isEnd, HashMap<Character, TrieNode>   
Build trie = Insert word:没node就加，有node就移动。   
Search word:没有node就报错. 到结尾return true   

这题因为'.'可以代替任何possible的字符，没一种都是一个新的path，所以recursive做比较好些。    
(iterative就要queue了,麻烦点)



---
**1. [Add Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Binary.java)**      Level: Easy
      
方法一:土办法没技术，把binary换成数字，加起来，再换成binary。如果input很大，那么很可能int,long都hold不住。不保险。

方法二:一般方法，string化为charArray,然后逐位加起，最后记得处理多余的一个carry on




---
**2. [Add Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Digits.java)**      Level: Easy
      
方法1: 普通做法就是按照题意, 把每个digit加起来. O(n)

方法2: 找数学规律, 每过9个数字, 取mod就会开始重复, 所以给所有数字取mod 就可以间接找到答案.



---
**3. [Add Two Numbers II.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Two%20Numbers%20II.java)**      Level: Medium
      
LinkedList并没有反过来，那么自己反：   
   方向相反。巧用stack.

做加法都一样：   
   1. carrier
   2. carrier = (rst + carrier) / 10
   3. rst = (rst + carrier) % 10



---
**4. [Add Two Numbers.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Two%20Numbers.java)**      Level: Easy
      
LinkedList都已经反转好了，直接做。

遍历两个l1,l2把carry-on处理好，每次生成一个新node，最后检查carry-on。

跟Add Binary的理解方式一模一样。




---
**5. [Alien Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/Alien%20Dictionary.java)**      Level: Hard
      
Not Done yet。 Topological sort.



---
**6. [Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/Anagrams.java)**      Level: Medium
      

1. HashMap 的做法. sort每个string, 存进HashMap, 重复的就是anagrams,最后输出。   
   toCharArray
   Arrays.sort
   Stirng.valueOf(char[])
   时间n*L*O(logL),L是最长string的长度。

2. Arrays.toString(arr)的做法。arr是int[26], assuming only have 26 lowercase letters.    
Count occurrance, 然后convert to String，作为map的key.
Time complexity: nO(L)

3. 另一种做法：http://www.jiuzhang.com/solutions/anagrams/   
   1. take each string, count the occurrance of the 26 letters. save in int[]count.   
   2. hash the int[] count and output a unique hash value.   
      hash = hash * a + num   
      a = a * b.   
   3. save to hashmap in the same way as we do. 

这一步把for s: strs 里面的时间复杂度降到了O(L). L = s.length().   
Need to work on the getHash() function.

时间变成n*O(L). Better.




---
**7. [Balanced Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Balanced%20Binary%20Tree.java)**      Level: Medium
      
1. DFS using depth marker: 每个depth都存一下。然后如果有不符合条件的，存为-1.
   一旦有 <0 或者差值大于1， 就全部返回Integer.MIN_VALUE. Integer.MIN_VALUE比较极端, 确保结果的正确性。
   最后比较返回结果是不是<0. 是<0，那就false.
   Traverse 整个tree, O(n)

2. Only calculate depth using maxDepth function. Same concept as in 1, but cost more traversal efforts.



---
**8. [Binary Representation.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Representation.java)**      Level: Hard
      
首先要分两半解决，断点是'.': str.split("\\.");

Integer那一半好弄，whie loop里： num%2, num/2。

Decimal那边复杂点.
   bit == 1的数学条件：当下num * 2 >= 1。 更新: num = num * 2 - 1;
   bit == 0的数学条件： num * 2 < 1. 更新: num = num * 2

注意：num是 double, 小数在 （num = num * 2 -1）的公式下可能无限循环. 因此check: num重复性，以及binary code < 32 bit.

(所以题目也才有了32BIT的要求！)



---
**9. [Binary Tree Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Inorder%20Traversal.java)**      Level: Easy
      
法一:      
Recursive: Divide and Conquer, with helper(dfs) method

法二:   
Stack: 
Add left nodes all the way   
Print curr   
Move to right, add right if possible.   
  
注意stack.pop()在加完left-most child 的后，一定要curr = curr.right.

若不右移，很可能发生窘境：    
curr下一轮还是去找自己的left-most child，不断重复curr and curr.left, 会infinite loop, 永远在左边上下上下。




---
**10. [Binary Tree Level Order Traversal II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Level%20Order%20Traversal%20II.java)**      Level: Medium
      
方法1:
跟Binary Tree Level Order Traversal一样,只不过存result一直存在存在0位.

方法2(略复杂, 不需要):
普通BFS，用一个queue，加上一个queue.size()来交替换行. 
或者多用一个queue来存下个level的nodes

方法3:
DFS, 根据level来append每个list
rst里面add(0,...)每次都add在list开头



---
**11. [Binary Tree Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Level%20Order%20Traversal.java)**      Level: Medium
      
方法1. 最普通,Non-recursive: BFS, queue, 用个queue.size()来end for loop:换行。   
   或者用两个queue. 当常规queue empty，把backup queue贴上去。

方法2. Recursive with dfs:   
   每个level都应该有个ArrayList. 那么用一个int level来查看：是否每一层都有了相应的ArrayList。   
   如果没有，就加上一层。    
   之后每次都通过DFS在相应的level上面加数字。




---
**12. [Binary Tree Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence.java)**      Level: Medium
      
屌炸天的4行代码。Divide and Conquer

主要想法：    
Recursive用好。首先在这个level比一比，可否成。   
不成的话，另立门户, count = 1。    
然后左右开弓。再把结果拿过来比较一下就好了。



---
**13. [Binary Tree Maximum Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum%20II.java)**      Level: Medium
      
比Binary Tree Maximum Path Sum I 简单许多. 因为条件给的更多：at least 1 node + have to start from root => have to have root.

方法1:   
维持一个global或者recursive里的sum。traversal entire tree via DFS. 简单明了。


方法2:   
Single path: either left or right.   
If the path sum < 0, just skip it.   



---
**14. [Binary Tree Maximum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum.java)**      Level: Medium
      
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
**15. [Binary Tree Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Path%20Sum.java)**      Level: Easy
      
Binary Tree的一个基本题。   
遍历到底，比较sum vs. target。   
注意divide的情况。要把遍历的例子写写。   

LeetCode: Path Sum II



---
**16. [Binary Tree Paths.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Paths.java)**      Level: Easy
      
方法1：   
Recursive:分叉。Helper。

方法2，Iterative:    
   非递归练习了一下   
   因为要每次切短list, 所以再加了一个Stack 来存level   




---
**17. [Binary Tree Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Postorder%20Traversal.java)**      Level: Easy
      
最prefer 2 stack的做法：   
   stack1和stack2合作。倒水。记这个做法。。。挺神奇的。

Divide and Conquer 的recursive方法也非常明了！

注意，这些binary tree traversal的题目，常常有多个做法:recursive or iterative



---
**18. [Binary Tree Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Preorder%20Traversal.java)**      Level: Easy
      
Preorder 写写， stack   
1. Divide and conquer   
2. Stack(NON-recursive) push curr, push right, push left.   
3. recursive with helper method   



---
**19. [Binary Tree Right Side View.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Right%20Side%20View.java)**      Level: Medium
      
最右:即level traversal每一行的最末尾.   

BFS，用queue.size()来出发saving result.



---
**20. [Binary Tree Serialization.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Serialization.java)**      Level: Medium
      
方法1: BFS. Non-recursive, using queue. 想法直观。level-order traversal. save到一个string里面就好。

方法2: DFS. Recursive. 需要一点思考。basically divide and conquer. 但是代码相对来说短。



---
**21. [Binary Tree Zigzag Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Zigzag%20Level%20Order%20Traversal.java)**      Level: Medium
      
简单的level traversal.根据level奇数偶数而add到不同位子.



---
**22. [Building Outline.java](https://github.com/awangdev/LintCode/blob/master/Java/Building%20Outline.java)**      Level: Hard
      
又叫做skyline

看网上的解答做， 思路很漂亮。 （http://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html?_sm_au_=isVmHvFmFs40TWRt）

跟scan line的approach类似:      
1. 把所有点分出来， 每个点有index x, 再加上一个height.         
2. 在这个list上排序，根据index和height（注意用负数标记building start point,这样保证start在end 之前。）. 叫做 heightPoints            
3. 在processs时候用max-heap (reversed priorityqueue)，在ieteraete heightPoints 来存最大的height . 遇到peek,就是一个合理的解    
    处理1：因为start,end的height都存在了heightPoints里面，这里就是用来check end of bulding的，然后把height 从queue里面remove.
    处理2：重复x 上面的许多height?  priorityqueue给了我们最高，这okay了；那么其他的重复点，用一个int prev来mark之前做过的，一旦重复，跳过。

想法非常natural。 大题目，脑子乱。    
看了解答再去想，挺naturally doable的。



---
**23. [Change to Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/Change%20to%20Anagram.java)**      Level: Easy
      
简单的check int[26] 26个小写字母是否需要改变。若需要count+1. 

主要HackerRank里要注意自己写: Scanner, import java.util, non-static method ...etc.

注意: 最后count出来要除以2：字母不同，会在不同的字母位上加减count,那么就是刚好重复计算了一遍。所以除以二。



---
**24. [Classical Binary Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Classical%20Binary%20Search.java)**      Level: Easy
      
   while: start + 1 < end
   mid = start + (end - start) / 2;
   末尾double check start, end.




---
**25. [Climbing Stairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Climbing%20Stairs.java)**      Level: Easy
      
方法1: DP。爬坡到i点总共有的方法，取决于i-1点和i-2的情况。也就是DP(i-1) + DP(i-2).

还可以用滚动数组优化一点：因为用到的变量就只有i,i-1,i-2，可以被替代。
   注意要写好‘滚动’的代码。

方法2: DFS但是timeout



---
**26. [Clone Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Clone%20Graph.java)**      Level: Medium
      
Use HashMap to mark cloned nodes.    

先能复制多少Node复制多少。然后把neighbor 加上




---
**27. [Closest Binary Search Tree Value.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Binary%20Search%20Tree%20Value.java)**      Level: Easy
      
Binary Search. 记录找到过的closest. 直到tree leaf, 找完return



---
**28. [Closest Number in Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Number%20in%20Sorted%20Array.java)**      Level: Easy
      
跟Closest Binary Search Tree Vlaue类似：

Binary search. 考虑mid-1, mid+1.    
一旦没有mid = target.index。 那么target最终就narrow down在(mid-1,mid) 或者(mid,mid+1)   


---
**29. [ColorGrid.java](https://github.com/awangdev/LintCode/blob/master/Java/ColorGrid.java)**      Level: Medium
      
用HashMap， 理解题目规律，因为重复的计算可以被覆盖，所以是个优化题。

消灭重合点:       
如果process当下col, 其实要减去过去所有加过的row的交接点。。。     
再分析，就是每次碰到row 取一个单点, sumRow += xxx。       
然后process当下col时候， sum += colValue * N - sumRow. 就等于把交叉所有row（曾经Process过的row）的点减去了。很方便。

最后read in 是O(P),  process也是O(P).




---
**30. [Combination Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20II.java)**      Level: Medium
      
还是DFS. 和Combination Sum I 类似.      
确保Helper是用i+1，下一层的数字, 不允许重复。



---
**31. [Combination Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum.java)**      Level: Medium
      
递归，backtracking. 非常normal。需要先sort.    
记得求sum时候也pass 一个sum进去，backtracking一下sum也，这样就不必每次都sum the list了。   

题目里面所同一个元素可以用n次，但是，同一种solution不能重复出现。如何handle?

1. 用一个index （我们这里用了start）来mark每次recursive的起始点。
2. 每个recursive都从for loop里面的i开始，而i = start。 也就是，下一个iteration,这个数字会有机会被重复使用。
3. 同时，确定在同一个for loop里面，不同的Index上面相同的数字，不Process两遍。用一个prev 作为checker.

假如[x1, x2, y, z], where x1 == x2， 上面做法的效果: 
我们可能有这样的结果: x1,x1,x1,y,z    
但是不会有:x1,x2,x2,y,z   
两个solution从数字上是一样的，也就是duplicated solution, 要杜绝第二种。



---
**32. [Combinations.java](https://github.com/awangdev/LintCode/blob/master/Java/Combinations.java)**      Level: Medium
      
Combination DFS。 画个图想想. 每次从1~n里面pick一个数字i

因为下一层不能重新回去 [0~i]选，所以下一层recursive要从i+1开始选。



---
**33. [Compare Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Compare%20Strings.java)**      Level: Easy
      
比较一下大小, null.

然后用char[]来count chars from A. 再对照chars in B.



---
**34. [Complete Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Complete%20Binary%20Tree.java)**      Level: Easy
      
BFS   

Use a flag . 当出现了第一次有 null children的node的时候，   
说明complete tree的最低level出现了。    
自此以后，queue再不该有node再有child, queue后面出现的node的左右孩子应该都是null.    




---
**35. [Construct Binary Tree from Inorder and Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal.java)**      Level: Medium
      
写个Inorder和Postorder的例子。利用他们分left/right subtree的规律解题。

Postorder array 的末尾， 就是当下层的root.   
在Inorder array 里面找到这个root,就刚好把左右两边分割成left/right tree。

这题比较tricky地用了一个helper做recursive。 特别要注意处理index的变化, precisely考虑开头结尾

可惜有个不可避免的O(n) find element in array.



---
**36. [Construct Binary Tree from Inorder and Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Preorder%20Traversal.java)**      Level: Medium
      
和Construct from Inorder && Postorder 想法一样。

写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。



---
**37. [Container With Most Water.java](https://github.com/awangdev/LintCode/blob/master/Java/Container%20With%20Most%20Water.java)**      Level: Medium
      
类似木桶理论。盛水的最高取决于最低的那面墙。
左右两墙，往中间跑动。
另，若一面墙已经小于另外一面，就要移动，换掉矮墙（可能下一面更高，或更低）；但决不能换掉当下的高墙，因为低墙已经limit的盛水的上限，若高墙移动，导致两墙之间距离减少，就注定水量更少了。（弄啥来，不能缺心眼啊）


---
**38. [Contains Duplicate II.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate%20II.java)**      Level: Easy
      
方法1: HashTable<value, list of duplicates>, brutly check agains the list
方法2: 很巧妙地根据k range地条件, 把HashSet里面的值控制在[i - k, i]. 那么一旦match, 就符合条件. 

这两种做法很艺术:
一般的想法是把符合条件的index找出来, 集中处理
第二种做法是限定选拔的candidate, 不合格就去掉, 那么一旦有符合条件的(duplicates), 那么一定中.



---
**39. [Contains Duplicate III.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate%20III.java)**      Level: Medium
      
与Contains Duplicate II 类似概念. TreeSet有BST 因此可以直接用, 而不用自己构建BST
简化题目里面的重要条件 Math.abs(A-B) <= t 而推断出需要用 TreeSet.ceiling(x): return number greater or equal to x. 这个用法要记住吧, 没别的捷径.



---
**40. [Contains Duplicate.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate.java)**      Level: Easy
      
方法1: No brain: HashSet. O(n), 但是实际上比方法2 要慢.
方法2: 排序, 重复数会排在一起. Arrays.sort() time complexity nLog(n)



---
**41. [Convert Binary Search Tree to Doubly Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Binary%20Search%20Tree%20to%20Doubly%20Linked%20List.java)**      Level: Medium
      
会iterative traverse Binary Search Tree就好（Stack && handle left-dig-down）, 然后create Doubly-ListNode 时候注意就好.

注意inorder traversal在check right node的事后，    
不论right == null or != null, 每次都要强行move to right.    

如果不node = node.right,     
很可能发生窘境：       
node alays = stack.top(), 然后stack.top()一直是一开始把left 全部遍历的内容。所以就会infinite loop, 永远在左边上下上下。      



---
**42. [Convert Expression to Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Expression%20to%20Polish%20Notation.java)**      Level: Hard
      
还是Expression Tree (Min-Tree).

根据题意，Tree出来以后，来个Pre-order-traversal.

Note: label需要是String.虽然 Operator是长度为1的char, 但是数字可为多位。



---
**43. [Convert Expression to Reverse Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Expression%20to%20Reverse%20Polish%20Notation.java)**      Level: Hard
      
build expression tree。

这个里面把TreeNode就当做成我们需要的node,里面扩展成有left/right child的node.

建造Expression Tree,然后根据　Reverse Polish Notation 的定义，来个post-traversal就行了。



---
**44. [Convert Integer A to Integer B.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Integer%20A%20to%20Integer%20B.java)**      Level: Easy
      
Bit Manipulation

a^b 显示出bit format里面有不同binary code的数位.

每次 (a^b)>>i 移动i位之后，   再 & 1时其实是指留下这一位的数字.

count it up



---
**45. [Convert Sorted Array to Binary Search Tree With Minimal Height.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree%20With%20Minimal%20Height.java)**      Level: Easy
      
Binary Search的感觉. 中间一开两半, divde and conquer,左右各自recursive下去build left/right child.



---
**46. [Convert Sorted List to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20List%20to%20Binary%20Search%20Tree.java)**      Level: Medium
      
Divide and Conquer   
用快慢pointer

找到mid。   
然后把root = mid.next     

然后开始sortedListToBST(mid.next.next); //后半段    
mid.next = null;//非常重要，要把后面拍过序的断掉    
sortedListToBST(head); //从头开始的前半段     


最后root.left, root.right merge一下。   



---
**47. [Copy List with Random Pointer.java](https://github.com/awangdev/LintCode/blob/master/Java/Copy%20List%20with%20Random%20Pointer.java)**      Level: Medium
      
Basic Implementation, 其中用了一下HashMap:  

遍历head.next .... null.    
每一步都check map里面有没有head。没有？加上。    
每一步都check map里面有没有head.random。没有？加上。



---
**48. [Cosine Similarity.java](https://github.com/awangdev/LintCode/blob/master/Java/Cosine%20Similarity.java)**      Level: Easy
      
basic implementation



---
**49. [Count 1 in Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%201%20in%20Binary.java)**      Level: Easy
      
1. 可以把integer -> string -> char array.

2. 或者就 count += num << i & 1



---
**50. [Count and Say.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20and%20Say.java)**      Level: Easy
      
Basic implementation. Count duplicates and print



---
**51. [Count of Smaller Number before itself.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Number%20before%20itself.java)**      Level: Hard
      
与Count of Smaller Number非常类似。以实际的value来构成segment tree，leaf上存（count of smaller number）。

Trick: 先Query，再modify.   
每次Query时候，A[i]都还没有加入到Segment Tree 里面，而A[i+1,...etc]自然也还没有加进去。   
那么就自然是coutning smaller number before itself.   
刁钻啊！   

另外注意：   
在modify里面：多Check了root.start <= index 和  index <= root.end。 过去都忽略了。以后可以把这个也写上。   
（其实是Make sense的，就是更加严格地check了index再 root.left 或者 root.right里面的站位）   



---
**52. [Count of Smaller Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Number.java)**      Level: Medium
      
和平时的segment tree问题不同。 0 ～ n-1代表实际数字。是造一个based on real value的segment tree.
Modify时，把array里面的value带进去，找到特定的位子（leaf）,然后count+1. 

最终在SegmentTree leaf上面全是array里面实际的数字。

trick:   
在query前，给进去的start和end是： 0 ~ value-1.   
value-1就是说，找比自己所在range小1的range（那么自然而然地就不包括自己了），这样就找到了smaller number.   


[那么其他做过的SegmentTree是怎么样呢？]   
那些构成好的SegmentTree(找min,max,sum)也有一个Array。但是构成Tree时候，随Array的index而构架。   
也就是说，假如有Array[x,y,....]:在leaf,会有[0,0] with value = x. [1,1] with value = y. 

[但是这题]   
构成时，是用actual value.也就是比如Array[x,y,....]会产生leaf:[x,x]with value = ..; [y,y]with value =...    

其实很容易看穿:   
若给出一个固定的array构成 SegmentTree，那估计很简单：按照index从0~array.lengh，leaf上就是[0,0] with value = x.

若题目让构造一个空心SegmentTree, based on value 0 ~ n-1 (n <= 10000), 然后把一个Array的value modify 进去。   
这样八成是另外一种咯。



---
**53. [Count Primes.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20Primes.java)**      Level: Easy
      
什么是prime number: >=2的没有除自己和1以外公约数的数。   

还有另外一个定义方法!!    
这个n,有没有小于n的一个i,而达到： i*i + # of i = n. 如果有，那就不是 prime。     

方法很牛逼也很数学。没做的时候可能想不到。做了之后就觉得，哎，我去，有道理啊。   
简而言之：简历一个boolean长条，存isPrime[]。 然后从i=2， 全部变true.    
然后利用这个因子的性质，非prime满足条件： self*self, self * self + self ... etc.     
所以就check每一个j, j+i, j+i+i, 然后把所有non-prime全部mark成false.     
最后，数一遍还剩下的true个数就好了   



---
**54. [Course Schedule II.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule%20II.java)**      Level: Medium
      
详细的中文分析，看Course Schedule I



---
**55. [Course Schedule.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule.java)**      Level: Medium
      
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
**56. [Data Stream Median.java](https://github.com/awangdev/LintCode/blob/master/Java/Data%20Stream%20Median.java)**      Level: Hard
      
把Input stream想成向上的山坡。山坡中间那点，自然就是median.

前半段，作为maxHeap,关注点是PriorityQueue的峰点，也就是实际上的median.   

后半段，作为minHeap,正常的PriorityQueue。 开头是最小的。

Note:题目定义meadian = A[(n-1)/2],也就是说maxHeap需要和minHeap长度相等，或者多一个element,最后可以直接poll() and return.



---
**57. [Delete Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/Delete%20Digits.java)**      Level: Medium
      
数位靠前的，权值更大. 所以硬来把靠前的相对更大的（跟following digit相比）去掉。



---
**58. [Delete Node in the Middle of Singly Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Delete%20Node%20in%20the%20Middle%20of%20Singly%20Linked%20List.java)**      Level: Easy
      
Just do it. Link curr.next to curr.next.next



---
**59. [Encode and Decode Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Encode%20and%20Decode%20Strings.java)**      Level: Medium
      
方法1:    
用数字+"#"+string来encode.    
基于我们自己定的规律, 在decode的里面不需要过多地去check error input, assume所有input都是规范的.    
decode就是找"#",然后用"#"前的数字截取后面的string.



Old Solution:    
Cast character into int. 串联起来, seperate by "LINE".   
handle empty list [], or just null: 要把Null特别mark一下为‘NULL’, 这样decode时才能check到。      adminadmin




---
**60. [ExcelSheetColumnNumber .java](https://github.com/awangdev/LintCode/blob/master/Java/ExcelSheetColumnNumber%20.java)**      Level: Easy
      
'A' - 'A' = 0. 所以 char - 'A' + 1 = 题目里的对应数位。      
26位运算和10位一样嘛，num += 每位的digit * Math.pow(26, 数位号)。




---
**61. [Expression Evaluation.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Evaluation.java)**      Level: Hard
      
Build Expression Tree的另外一个变形，依然Min Tree.

build好Min Tree以后，做PostTraversal. Divde and Conquer：   
先recursively找到 left和right的大小， 然后evaluate中间的符号。

Note:
1. Handle数字时，若left&&right Child全Null,那必定是我们weight最大的数字node了。   
2. 若有个child是null,那就return另外一个node。    
3. prevent Integer overflow　during operation:过程中用个Long，最后结局在cast back to int.



---
**62. [Expression Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Tree%20Build.java)**      Level: Hard
      
和Max-tree一样，感谢http://blog.welkinlan.com/2015/06/29/max-tree-lintcode-java/

这个题目是Min-tree， 头上最小，Logic 和max-tree如出一辙   

注意treeNode,为了帮助ExpressionTreeNode 排序。它加了一个weight based on expression，协助build Min-Tree 排序。

Space: O(n) 
Time on average: O(n).



---
**63. [Fast Power.java](https://github.com/awangdev/LintCode/blob/master/Java/Fast%20Power.java)**      Level: Medium
      
a^n可以被拆解成(a*a*a*a....*a)， 是乘机形式，而%是可以把每一项都mod一下的。所以就拆开来take mod.

这里用个二分的方法，recursively二分下去，直到n/2为0或者1，然后分别对待. 

注意1: 二分后要conquer，乘积可能大于Integer.MAX_VALUE, 所以用个long.

注意2: 要处理n%2==1的情况，二分时候自动省掉了一份，要乘一下。




---
**64. [Fibonacci.java](https://github.com/awangdev/LintCode/blob/master/Java/Fibonacci.java)**      Level: Easy
      
方法1: DP array.

方法1.1: 滚动数组, 简化DP。

方法2: recursively calculate fib(n - 1) + fib(n - 2). 公式没问题, 但是时间太长, timeout.




---
**65. [Find the Connected Component in the Undirected Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Connected%20Component%20in%20the%20Undirected%20Graph.java)**      Level: Medium
      
BFS遍历，把每个node的neighbor都加进来。    

一定注意要把visit过的node Mark一下。因为curr node也会是别人的neighbor，会无限循环。      

Component的定义：所有Component内的node必须被串联起来via path (反正这里是undirected, 只要链接上就好)     

这道题：其实component在input里面都已经给好了，所有能一口气visit到的，全部加进queue里面，他们就是一个component里面的了。     

而我们这里不需要判断他们是不是Component。   



---
**66. [Find the Weak Connected Component in the Directed Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Weak%20Connected%20Component%20in%20the%20Directed%20Graph.java)**      Level: Medium
      
Identify这是个union-find问题还挺巧妙。    
看到了weak component的形式： 一个点指向所有，那么所有的点都有一个公共的parent，然后就是要找出这些点。    

为何不能从一个点出发，比如A，直接print它所有的neighbors呢？     
	不行，如果轮到了B点，那因为是directed,它也不知道A的情况，也不知道改如何继续加，或者下手。    

所以，要把所有跟A有关系的点，或者接下去和A的neighbor有关系的点，都放进union-find里面，让这些点有Common parents.     

最后output的想法：    
做一个 map <parent ID, list>。    
之前我们不是给每个num都存好了parent了嘛。    
每个num都有个parent, 然后不同的parent就创造一个不同的list。   
最后，把Map里面所有的list拿出来就好了。    



---
**67. [Flatten 2D Vector.java](https://github.com/awangdev/LintCode/blob/master/Java/Flatten%202D%20Vector.java)**      Level: Medium
      
大概意思就是把2D list里面的element全部遍历一遍。
注意啊，一开始理解题意搞错：我以为是必须要排序正确，所以上来就PriorityQueue+HashMap搞得无比复杂。其实，这个跟一个nxn的matrix遍历，是没区别的拉。
所有来个x,y，把2d list跑一变。



---
**68. [Flatten Binary Tree to Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Flatten%20Binary%20Tree%20to%20Linked%20List.java)**      Level: Easy
      
Not Done




---
**69. [Flip Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Flip%20Game%20II.java)**      Level: Medium
      
12.06.2015 recap:
注意：不要乱改input s. recursive call 需要用原始的input s.

这个题目李特是屌炸天的。
我飞了九牛二虎之力（路子对），但是代码写的七荤八素，好长好长好长好长的。
结果正解，三四行就搞定了。真是心有不甘啊。
想法如下：
保证p1能胜利，就必须保持所有p2的move都不能赢。
同时，p1只要在可走的Move里面，有一个move可以赢就足够了。
（题目里面用一个for loop + 只要 满足条件就return true来表达 OR的意思：p1不同的路子，赢一种就行了）
p1: player1
p2: player2



---
**70. [Flip Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Flip%20Game.java)**      Level: Easy
      
这个题目是很寂寞的. 2 pointer可以做, 在网上又搜了一下，貌似可以有很多牛逼的优化，我暂时还没去看。
很郁闷的就是条件不明，原来只需要从'++'转到'--'的情况，反过来没必要关注...搞了我半天啊


---
**71. [Fraction to Recurring Decimal.java](https://github.com/awangdev/LintCode/blob/master/Java/Fraction%20to%20Recurring%20Decimal.java)**      Level: Medium
      
不难想到处理除法：考虑正负，考虑小数点前后。主要是小数点以后的要着重考虑。
很容易忽略的是integer的益处。


---
**72. [Generate Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Generate%20Parentheses.java)**      Level: Medium
      
递归。
看thought.取或者不取(,  )

Note: 在DFS时, 可以pass object (String) and re-create every time; or pass a reference (StringBuffer) and maintain it



---
**73. [Graph Valid Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Graph%20Valid%20Tree.java)**      Level: Medium
      
复习Union-Find的另外一个种形式。   
题目类型：查找2个元素是不是在一个set里面。如果不在，false. 如果在，那就合并成一个set,共享parent.   
存储的关键都是：元素相对的index上存着他的root parent.    

另一个union-find， 用hashmap的：http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/




---
**74. [Gray Code.java](https://github.com/awangdev/LintCode/blob/master/Java/Gray%20Code.java)**      Level: Medium
      
题目蛋疼，目前只接受一种结果。

BackTracking + DFS:   
   Recursive helper里每次flip一个 自己/左边/右边. Flip过后还要恢复原样.遍历所有.

曾用法（未仔细验证）：   
基本想法就是从一个点开始往一个方向走，每次flip一个bit, 碰壁的时候就回头走。



---
**75. [Group Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/Group%20Anagrams.java)**      Level: Medium
      
方法一: 60%

和check anagram 想法一样：转化并sort char array，用来作为key。

把所有anagram 存在一起。注意结尾Collections.sort().

O(NKlog(K)), N = string[] length, k = longest word length    


优化：80% ~ 97%

用固定长度的char[26] arr 存每个字母的frequency; 然后再 new string(arr).   
因为每个位子上的frequency的变化，就能构建一个unique的string


错误的示范: 尝试先sort input strs[]，但是NlogN 其实效率更低. 13%




---
**76. [Group Shifted Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Group%20Shifted%20Strings.java)**      Level: Easy
      
相同shift规则的string, 能被推算到同一个零起始点，就是共同减去一个char,最后就相等。以此作为key，用HashMap。一目了然。

记得根据题目意思，一开始要String[] sort一下。



---
**77. [H-Index II.java](https://github.com/awangdev/LintCode/blob/master/Java/H-Index%20II.java)**      Level: Medium
      
H-index的一个优化。
binary search


---
**78. [H-Index.java](https://github.com/awangdev/LintCode/blob/master/Java/H-Index.java)**      Level: Medium
      
例子写出来，发现可以sort以后按照定义搜索一遍。 nlogn.
	当然，搜索一遍时候可以优化，用binary search. 但是没意义，因为array.sort已经用了nlogn

o(n)也可以，用bucket. 比较巧妙。




---
**79. [Hamming Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Hamming%20Distance.java)**      Level: Easy
      
bit: XOR, &, shift>>



---
**80. [Happy Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Happy%20Number.java)**      Level: Easy
      
Basic Implementation of the requirements.

用HashSet存查看过的数值。若重复，return false.



---
**81. [Hash Function.java](https://github.com/awangdev/LintCode/blob/master/Java/Hash%20Function.java)**      Level: Easy
      
解释Hash怎么道理。Hash function例子：    
hashcode("abcd") = (ascii(a) * 33^3 + ascii(b) * 33^2 + ascii(c) *33^1 + ascii(d)*33^0) % HASH_SIZE 

用到的参数比如: magic number 33, HASH_SIZE.

Hash的用法是：给一个string key, 转换成数字，从而把size变得更小。    
真实的implementation还要处理collision, 可能需要design hash function 等等。


每一步都：     
hashRst = hashRst * 33 + (int)(key[i]);       
hashRst = hashRst % HASH_SIZE;       
原因是，hashRst会变得太大，所以不能算完再%...



---
**82. [HashHeap.java](https://github.com/awangdev/LintCode/blob/master/Java/HashHeap.java)**      Level: Hard
      
非题.是从九章找来的HashHeap implementation.



---
**83. [HashWithArray.java](https://github.com/awangdev/LintCode/blob/master/Java/HashWithArray.java)**      Level: Easy
      



---
**84. [HashWithCustomizedClass(LinkedList).java](https://github.com/awangdev/LintCode/blob/master/Java/HashWithCustomizedClass(LinkedList).java)**      Level: Medium
      
练习HashMap with customized class. 



---
**85. [Heapify.java](https://github.com/awangdev/LintCode/blob/master/Java/Heapify.java)**      Level: Medium
      
Heap用的不多. 得用一下, 才好理解。   
通常default 的PriorityQueue就是给了一个现成的min-heap：所有后面的对应element都比curr element 小。

Heapify里面的siftdown的部分：
	只能从for(i = n/2-1 ~ 0)， 而不能从for(i = 0 ~ n/2 -1): 必须中间开花，向上跑的时候才能确保脚下是符合heap规则的

Heapify/SiftDown做了什么？    
确保在heap datastructure里面curr node下面的两个孩子，以及下面所有的node都遵循一个规律。   
比如在这里，若是min-heap,就是后面的两孩子都要比自己大。若不是，就要swap。    

还是要记一下min-heap的判断规律:for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].

siftdown时：在curr node和两个son里面小的比较。如果的确curr < son, 搞定，break while.   
但若curr 并不比son小，那么就要换位子，而且继续从son的位子往下面盘查。    



---
**86. [Heaters.java](https://github.com/awangdev/LintCode/blob/master/Java/Heaters.java)**      Level: Easy
      
第一步：
生题型, 理解题意需要时间：
从字面和画图而言, 就是定住房子一个个过，房子左右的distance需要足够达到heater. 目标是招尽可能小的radius, 所以house和heater紧贴着是必要的.
在for loop里面定下house，把heater当作一个区间移动, 达到的第一个合适区间，这就是当下最小的理想radius，取这个值跟既定radius作比较。
比较之后，继续移动house，再试着移动heater区间去match。

第二步：
Binary Search

注意！
题目没有说given array是否sort, 我们必须sort才能够区间移动或者binary search.
TODO:
http://www.cnblogs.com/grandyang/p/6181626.html



---
**87. [House Robber III.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20III.java)**      Level: Hard
      
由于无法用简单的方法构造DP array, 所以采取了普通的DFS。

The catch:    
判断当下的node是否被采用，用一个boolean来表示. 

1. 如果curr node被采用，那么下面的child一定不能被采用。
2. 如果curr node不被采用，那么下面的children有可能被采用，但也可能略过，所以这里用Math.max() 比较一下两种可能有的dfs结果。




---
**88. [Identical Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Identical%20Binary%20Tree.java)**      Level: Easy
      
Divide, && 每种情况（左右一一对应)    
注意 null states




---
**89. [Implement Queue using Stacks.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Queue%20using%20Stacks.java)**      Level: Easy
      
双Stack. 一个是等于是queue，一个是backfillStack.
Tricky: 是在pop()和peek()的时候backfill, 并且要等到stack用完再backfill.
写一下例子就知道，如果提早backfill，stack.peek()就不是queue的head了.




---
**90. [Implement Stack by Two Queues.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack%20by%20Two%20Queues.java)**      Level: Easy
      
两个Queue,交互倒水
用一个Temp做swap

做法1:
逻辑在top()/pop()里, 每次换水，查看末尾项.

做法2:
逻辑在push里面:
1. x 放q2。
2. q1全部offer/append到q2.
3. 用一个Temp做swap q1, q2.
q1的头，就一直是最后加进去的值.


---
**91. [Implement Stack using Queues.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack%20using%20Queues.java)**      Level: Easy
      
两个Queue,交互倒水
用一个Temp做swap

做法1:
逻辑在top()/pop()里, 每次换水，查看末尾项.

做法2:
逻辑在push里面:
1. x 放q2。
2. q1全部offer/append到q2.
3. 用一个Temp做swap q1, q2.
q1的头，就一直是最后加进去的值.



---
**92. [Implement Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack.java)**      Level: Easy
      
stack 后入先出. 
Data Structure: ArrayList 
return/remove ArrayList的末尾项。



---
**93. [Implement Trie (Prefix Tree).java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Trie%20(Prefix%20Tree).java)**      Level: Medium
      
Trie自己不多用到。
如果是遇到一个一个字查询的题，可以考虑一下。
构建TrieNode的时候要注意：如何找孩子？如果是个map的话，其实就挺好走位的。
而且，每个node里面的 char 或者string有时候用处不大，
可以为空。但是有些题目，比如在结尾要return一些什么String，就可以在end string那边存一个真的String。




---
**94. [Implement Trie.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Trie.java)**      Level: Medium
      
Tire, 也即是 Prefix Tree. 

HashMap构建Trie。 Trie三个Method:　   
1. Inset: 加 word   
2. Search: 找word    
3. StartWith: 找prefix    

只有两条children的是binary tree. 那么多个children就是Trie。 那么没有left/right pointer怎么找孩子？   
用HashMap，以child的label为Key，value就是child node。 HashMap走位   

Note:    
node里的char在这是optional。    
另外有种题目，比如是跟其他种类的search相关，在结尾要return whole string，就可以在node里存一个up-to-this-point的String。





---
**95. [IndexMatch.java](https://github.com/awangdev/LintCode/blob/master/Java/IndexMatch.java)**      Level: Easy
      
有序, 假设有这样的数字:target.        
target 左边的数字，一定不比index大，target右边的数字，一定比index大。     
这样可以binary search.O(logn)



---
**96. [Inorder Successor in Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Inorder%20Successor%20in%20Binary%20Search%20Tree.java)**      Level: Medium
      
画inorder图，发现规律.每个node的后继node(successor)有几种情况:   
1. node.right 是个leaf到底了。那么就return.   
2. set rightNode = node.right， 但发现rightNode has a lot left children to leaf.   
3. 比如, node.right == null， 也就是node自己是leaf，要回头看山顶找Inorder traversal规则里的下一个。   
	发现：其实就是每层都把路过的curr node放在stack里，最上面的，就是当下改return的那个successor:) Done.



---
**97. [Insert Interval.java](https://github.com/awangdev/LintCode/blob/master/Java/Insert%20Interval.java)**      Level: Easy
      

方法1：Scan Line    
Interval 拆点，PriorityQueue排点。     
Merge时用count==0作判断点。    

PriorityQueue: O(logN). 扫n点，总共：O(nLogn)    


方法2：   
O(n) 直接找到可以insert newInterval的位子. Insert。  这里已经给了sorted intervals by start point. 所以O(n)

然后loop to merge entire interval array

另外: 因为interval已经sort, 本想用Binary Search O(logn). 但是找到interval insert position， merge还是要用 O(n)。      
比如刚好newInterval cover entire  list....

 



---
**98. [Insert Node in a Binary Search Tree .java](https://github.com/awangdev/LintCode/blob/master/Java/Insert%20Node%20in%20a%20Binary%20Search%20Tree%20.java)**      Level: Easy
      
往Binary Search Tree里面加东西，一定会找到一个合适的leaf加上去。

那么：就是说someNode.left or someNode.right是null时，就是insert node的地方。

找到那个someNode就按照正常的Binary Search Tree规律。



---
**99. [Intersection of Two Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Arrays.java)**      Level: Easy
      
方法1: 用到hashset找unique && duplicate: O(m+n)
方法2: 可以用binary search 找数字. Note:binary search一定需要array sorted: nLog(m)



---
**100. [Intersection of Two Linked Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Linked%20Lists.java)**      Level: Easy
      
长短list，找重合点。
长度不同的话，切掉长的list那个的extra length。 那么起点一样后，重合点就会同时到达。




---
**101. [Interval Minimum Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Minimum%20Number.java)**      Level: Medium
      
SegtmentTree, methods: Build, Query. 这题是在SegmentTreeNode里面存min.

类似的有存:max, sum, min





---
**102. [Interval Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Sum%20II.java)**      Level: Hard
      
SegmentTree大集合。记得几个Methods: Build, Query, Modify. 不难。只是要都记得不犯错:)



---
**103. [Interval Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Sum.java)**      Level: Medium
      
其实是segment tree 每个node上面加个sum。   

记得Segment Tree methods: Build, Query

Note: 存在SegmentTreeNode里面的是sum.  其他题目可能是min,max ... or something else.




---
**104. [Invert Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Invert%20Binary%20Tree.java)**      Level: Easy
      
non-recursive: BFS with queue。 或者regular recurisve - divide and conquer.



---
**105. [Isomorphic Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Isomorphic%20Strings.java)**      Level: Easy
      
HashMap 来确认match。有几种情况考虑:

1. Match. 就是map.containsKey, map.containsValue, and char1 == char2. Perfect.

2. Either Key not exist, or Value not exit. False;

3. Both key and Value exist, but map.get(char1) != char2.  Miss-match. False.

4. None of Key or Value exist in HashMap. Then add the match.



---
**106. [Jump Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game%20II.java)**      Level: Hard
      
Greedy, 图解 http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html

维护一个range, 是最远我们能走的. 

index/i 是一步一步往前, 每次当 i <= range, 做一个while loop， 在其中找最远能到的地方 maxRange

然后更新 range = maxRange

其中step也是跟index是一样, 一步一步走.

最后check的condition是，我们最远你能走的range >= nums.length - 1, 说明以最少的Step就到达了重点。Good.




---
**107. [Kth Largest Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element.java)**      Level: Medium
      
用Quick Sort 里面partion的一部分。     
partion的结果是那个low, 去找 low==nums.size() - k， 也就是倒数第K个。    
没找到继续partion recursively.

sort的过程是排一个从小到大的list. (同样的代码还可以好xth smallest，mid变成x就好)

Quick Sort:   
每个iteration, 找一个pivot,然后从low,和high都和pivot作比较。    
找到一个low>pivot, high<pivot, 也就可以swap了。    
得到的low就是当下的partion point了




---
**108. [Kth Smallest Number in Sorted Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Number%20in%20Sorted%20Matrix.java)**      Level: Medium
      
和Merge K sorted Array/ List 类似：使用PriorityQueue。

因为Array的element无法直接找到next,所以用一个class Node 存value, x,y positions.



---
**109. [Kth Smallest Sum In Two Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Sum%20In%20Two%20Sorted%20Arrays.java)**      Level: Hard
      

用priority queue. 每次把最小的展开，移位。分别x+1,或者y+1:   
因为当下的Min里面x,y都是最小的。所以下一个最小的不是（x+1,y）,就是（x,y+1）。

每次就poll（）一个，放2个新candidate进去就好了。
注意，这样的做法会用重复，比如例子（7,4）会出现两次。用一个HashSet挡一下。

注意，HashSet的唯一性，用一个"x,y"的string就可以代为解决。



---
**110. [Longest Common Prefix.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Prefix.java)**      Level: Medium
      
Nested loop, 每一次比较所有string 同位是否相等。

相等，append string. 不等，return.



---
**111. [Longest Increasing Continuous subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence.java)**      Level: Easy
      
O(n)跑2遍for.
O(1)是用了两个int来存：每次到i点时，i点满足条件或不满足条件所有的longestIncreasingContinuousSubsequence.
特点：返跑一回，ans还是继续和left轮的ans作比较；求的所有情况的最大值嘛。


---
**112. [Longest Palindromic Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Palindromic%20Substring.java)**      Level: Medium
      
方法1: 从中间劈开. 遍历i，从n个不同的点劈开：每次劈开都看是否可以从劈开出作为palindromic的中点延伸。   
   Worst case: 整个string都是相同字符，time complexity变成： 1 + 2 +３　＋　．．．　＋n = O(n^2)

方法2: 穷举double for loop. O(n^2)




---
**113. [Longest Substring with At Most K Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20with%20At%20Most%20K%20Distinct%20Characters.java)**      Level: Medium
      
大清洗 O(nk)   
map.size一旦>k，要把longest string最开头（marked by pointer:start）的那个char抹掉    
一旦某一个char要被清除，所以在这个char 的1st and last appearance之间的char都要被清洗from map





---
**114. [Longest Substring Without Repeating Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20Without%20Repeating%20Characters.java)**      Level: Medium
      

方法2:用两个pointer, head和i.    
   HashMap<Char, Integer>: <character, last occurance index>
   head从index 0 开始。若没有重复char, 每次只有for loop的i++。每次取substring[head,i]作为最新的string.
   一旦有重复，那么意味着，从重复的老的那个index要往后加一格开始。所以head = map.get(i) +１.


注意：head很可能被退回到很早的地方，比如abbbbbba,当遇到第二个a，head竟然变成了 head = 0+1 = 1.      
当然这是不对的，所以head要确保一直增长，不回溯。


方法1：只要有non-existing char就count++. 一旦有重复char:   
   i = 新出现重复Char的位置.
   重新init HashMap, count.

这个方法每次都把map打碎重来, 是可以的，也没什么不好。就是在for里面改i，自己觉得不太顺.方法二可能顺一点。

   



---
**115. [Longest Univalue Path.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Univalue%20Path.java)**      Level: Easy
      
弄明白path的意义: 连接node的edge.
要找MAX, 可以在class scope里面定义一个max variable.

用minimum amount of code来概括几种不同的情况: left == root, right == root, or left == root == right.



---
**116. [Longest Word in Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Word%20in%20Dictionary.java)**      Level: Easy
      
方法1:
按大小排序 -> 从最大的开始做contains()的比较 -> 结果再按照字母表顺序(lexicographically) sort一下.
但是Collections.sort()了两次, 而且再list.contains(), 比较慢

方法2:
先排序, 以最简单的size==1以及set.contains()找match.
如果找到, 因为已经按照字母表排序, 找到的这个肯定是这个长度里面最符合的解答.
然后brutally找下一个更大的.
法2比法1好, 因为只用了一次sort, nLog(n). 然后其余都是O(1)的contains.
法1有两个sort(), 然后在list上面contains(), 所以比较耗时.

方法3:
应该可以有一个用Trie的方式做的, 还没有考虑.




---
**117. [Lowest Common Ancestor II.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor%20II.java)**      Level: Easy
      
这个题有个奇葩的地方，每个node还有一个parent。 所以可以自底向上.

1. 曾经做的hashset的优化，找到的都存hashset. exist就return那个duplicate.


2. 普通做法：2 lists。
   自底向上。利用parent往root方向返回。   

注意：无法从root去直接搜target node 而做成两个list. 因为根本不是Binary Search Tree！




---
**118. [Lowest Common Ancestor of a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor%20of%20a%20Binary%20Search%20Tree.java)**      Level: Medium
      
方法1: 利用 BST的性质，可以直接搜到target node，而做成两个长度不一定相等的list。然后很简单找到LCA 
方法2: Brutly寻找p和q的common ancestor, 然后recursively drive left/right. 非常巧妙, 但是也比较局限; 稍微变条件, 就很难recursive.



---
**119. [Lowest Common Ancestor.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor.java)**      Level: Easy
      
普通的Binary Tree，node child 自顶向下蔓延。

方法1：O(1) sapce O(h). Recursive. 循环的截点是：      
当root == null或者 A B 任何一个在findLCA底部被找到了(root== A || root == B)，那么就return 这个root.     

三种情况：   
1. A,B都找到，那么这个level的node就是其中一层的parent。其实，最先recursively return到的那个，就是最底的LCA parent.   
2. A 或者 B 找到，那就还没有公共parent,return 非null得那个。   
3. A B 都null, 那就找错了没有呗, return null


//无法找到target element, 因为不是Binary Search Tree    
//[Not Working]：O(n) space O(h) time。把两条线binary search出来。找第一个不同的parent. 代码长。 Iterative       



---
**120. [LRU Cache.java](https://github.com/awangdev/LintCode/blob/master/Java/LRU%20Cache.java)**      Level: Hard
      

timeout method, 天真的来了一个O(n) 的解法，结果果然timeout.     
一个map<key,value>存数值。一个queue<key>来存排位。     
每次有更新，就把最新的放在末尾；每次超过capaticity,就把大头干掉。很简单嘛，但是跑起来太久，失败了。     

于是就来了第二个做法。其实还是跟方法一是类似的。     
用了一个特别的双向的LinkNode，有了head和tail，这样就大大加快了速度。     
主要加快的就是那个‘更新排位’的过程，过去我是O(n),现在O(1)就好了。    

巧妙点：     
1. head和tail特别巧妙：除掉头和尾，和加上头和尾，就都特别快。    
2. 用双向的pointer: pre和next, 当需要除掉任何一个node的时候，只要知道要除掉哪一个，     
直接把node.pre和node.next耐心连起来就好了，node就自然而然的断开不要了。     

一旦知道怎么解决了，就不是很特别，并不是难写的算法:    
moveToHead()    
insertHead()    
remove()      



---
**121. [Majority Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Number%20II.java)**      Level: Medium
      
分三份：a b c考虑。若a, countA++, 或b, countB++，或c，countA--,countB--.

最后出现的两个count>0的a和b,自然是potentially大于1/3的。其中有一个大于1/3.

比较a和b哪个大，就return哪一个。



---
**122. [Majority Number III.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Number%20III.java)**      Level: Medium
      
与其他Majority Number一样。

出现次数多余1/k，就要分成k份count occurance.用HashMap。 存在的+1；不存在map里的，分情况:    
若map.size() == k,说明candidate都满了，要在map里把所有现存的都-1；
若map.size() < k, 说明该加新candidate，那么map.put(xxx, 1);

最后在HashMap里找出所留下的occurance最大的那个数。



---
**123. [Majority Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Number.java)**      Level: Easy
      
Majority Number是指超半数。任何超半数，都可以用0和1count：是某个number，+1；不是这个number,-1. 

注意：assume valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。

Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。

Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。



---
**124. [Matrix Zigzag Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Matrix%20Zigzag%20Traversal.java)**      Level: Easy
      
分析4个step:right, left-bottom,down,right-up    
implement时注意index.有点耐心



---
**125. [Max Area of Island.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Area%20of%20Island.java)**      Level: Easy
      
虽然Easy, 但用到DFS最基本的想法.
1. dive deep
2. mark VISITED
3. sum it up

更要注意, 要从符合条件value==1的地方开始dfs.
对于什么island都没有的情况，area应该位0， 而不是Integer.MIN_VALUE, 问清楚考官那小伙, 别写顺手。



---
**126. [Max Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Tree.java)**      Level: Hard
      
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
**127. [Maximal Square.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Square.java)**      Level: Medium
      
DP问题

从边长为2的正方形看起，看左上角的那个点。   
如何确定是个正方形？首先看左上点是不是1，然后看右边，右下，下面的点是不是1.   

DP就是根据这个特征想出来。dp[i,j]: 从右下往左上推算，包括当前点在内的所能找到的最大边长。   
   注意dp[i,j]被右边，右下，下面三点的最短点所限制。这就是fn. 

Init：   
   把右边，下边两个边缘init一遍，存matrix在这两条边上的值，代表的意思也就是dp[i][j]在这些点上的初始值:变成为1 or 0.  



---
**128. [Maximum Average Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20II.java)**      Level: Review
      


---
**129. [Maximum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy
      
DFS: Divide and conquer. 维持一个最大值。



---
**130. [Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray.java)**      Level: Easy
      
方法1
比较像DP, 维持一个sums[i]: 从i向前位数, 所有正数的和. 一旦sums[i - 1]<0, 意味着sums[i-1]对maxSum没有好处,
那么就assign: sums[i]=nums[i]
这个做法比较中规中矩, makes sense

方法2(better)
想着用一用prefix sum. 把值一个个叠加。
然后presum[j] - presum[i- 1] 就是 (i,j)之间的和。



---
**131. [Median of two Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Median%20of%20two%20Sorted%20Arrays.java)**      Level: Hard
      
Not done



---
**132. [Meeting Rooms II.java](https://github.com/awangdev/LintCode/blob/master/Java/Meeting%20Rooms%20II.java)**      Level: Medium
      

方法1:PriorityQueue + 一个Class来解决。Ｏ(nlogn)

方法2:这里有尝试了一下用一个sorted Array + HashMap： 也还行，但是handle edge的时候,HashMap 要小心，因为相同时间start和end的map key 就会重复了。



---
**133. [Meeting Rooms.java](https://github.com/awangdev/LintCode/blob/master/Java/Meeting%20Rooms.java)**      Level: Easy
      
方法1: 找是否有overlap. priorityQueue 按照start time排序好以后, 比较current和peek: current.end > peek.start?
方法2: Scan line, class Point{pos, flag}, PriorityQueue排序。计算count     

注意接头点要考虑所有开会结会的情况，不要恰巧漏掉相接的点。     
开会的是超人。瞬间移动接上下一个会议。



---
**134. [Merge Intervals.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Intervals.java)**      Level: Easy
      
方法1：O(nlogn)         
扫描线+Count无敌手。注意start end把interval给合起来。   
count==0的时候，就是每次start end双数抵消的时候，就应该是一个interval的开头/结尾。写个例子就知道了。   

空间：O(2n) -> O(n)   
时间,priorityqueue: O(nlogn)   

记得怎么写comparator    

在 LeetCode里面，Scan line比方法2要快很多.

方法2：    
Collections.sort() on interval.start之后，试着跑一遍，按照merge的需求，把需要merge的地方续好，然后减掉多余的interval就好。

(不知为何LeetCode把Merge Interval, Insert Interval 标为Hard)

Collections.sort(..., new comparator): sort by Interval.start.

画两个相连的Interval， prev, curr:
prev只有 prev.end覆盖了 curr.start， 才需要merge. 那么比较一下, marege.     
记得如果merge, 一定要list.remove(i), 并且i--， 因为改变了List的大小。

若没有重合，就继续iteration: prev = curr. move on.



---
**135. [Merge k Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20k%20Sorted%20Arrays.java)**      Level: Medium
      
由Merge k sorted list启发。用PriorityQueue,存那k个首发element。

PriorityQueue需要存储单位。自己建一个Class Node 存val, x,y index.    
因为array里没有 'next' pointer，只能存x,y来推next element



---
**136. [Merge k Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20k%20Sorted%20Lists.java)**      Level: Medium
      
用Priorityqueue来排列所有list的leading node.

记得k lists 需要是已经sort好的。   

时间：n*O(logk)   
PriorityQueue: logk   

这个题目可以有好几个衍生：   
   比如，如果k很大，一个机器上放不下所有的k list怎么办？ 
   比如，如果Merge起来的很长，一个机器上放不下怎么办？

Note:
1. 不要忘记customized priority需要一个customized new Comparator<T>()
2. Given list 里面也可能有null node, 不要忘记查.



---
**137. [Merge Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Sorted%20Array.java)**      Level: Easy
      
A够长，那么可以从A的尾部开始加新元素。     
注意，从尾部，是大数字优先排末尾的.  



---
**138. [Merge Two Binary Trees.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Two%20Binary%20Trees.java)**      Level: Easy
      
基础binary tree traversal. 注意对null child的判断



---
**139. [Merge Two Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Two%20Sorted%20Lists.java)**      Level: Easy
      
小的放前。每次比head大小。   
while过后，把没完的list一口气接上。   

一开始建一个node用来跑路, 每次都存node.next = xxx。存一个dummy。用来return dummy.next.



---
**140. [Min Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/Min%20Stack.java)**      Level: Easy
      
双Stack：一个正常stack，另一个minStack存当下level最小值. 注意维护minStack的变化

另外. 如果要maxStack，也是类似做法



---
**141. [Minimum Absolute Difference in BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Absolute%20Difference%20in%20BST.java)**      Level: Easy
      
BST: inorder-traversal: 先left node(adding to stack till left leav), 再process stack.peek (mid node), 再 add rightNode && dive to rightNode.left leaf



---
**142. [Minimum Size Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Size%20Subarray%20Sum.java)**      Level: Medium
      
2 pointer, O(n). 找subarray, start 或 end pointer，每次一格这样移动.

好的策略: 先找一个solution, 定住end, 然后移动start; 记录每个solution if occurs； 然后再移动end，往下找。

Note: 虽然一眼看上去是nested loop.但是分析后，发现其实就是按照end pointer移动的Loop。start每次移动一格。总体上，还是O(n)


Note done the O(nlogn) yet



---
**143. [Minimum Window Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Window%20Substring.java)**      Level: Hard
      
LeetCode Hard    
LintCode M, 测试有问题，即使做错也能过.

基本思想: 用个map存target的<char, int frequency>。 然后在搜索s的时候，遇到Match， frequency--.    
一旦map里面的frequency都被减为0, 就说明找到candidate.

有好几个trick：考虑start，前指针怎么移动；考虑start在candidate首字母没有多余前，不能移动；考虑candidate出现的情况...

复习时，回去看别人网站和自己的thoughts



---
**144. [MinimumDepthOfBinaryTree.java](https://github.com/awangdev/LintCode/blob/master/Java/MinimumDepthOfBinaryTree.java)**      Level: Easy
      
Divide and Conquery一个最小值. 注意处理Leaf的null, 用Integer.MAX_VALUE代替，这样可以避免错误counting.



---
**145. [Multiply Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Multiply%20Strings.java)**      Level: Medium
      
想法不难。turn into int[], 然后每个位子乘积，然后余数carrier移位。

但是做起来有很多坑。适合面试黑。    

1. 数字‘123’， 在数组里面， index == 0 是 ‘1’。 但是我们平时习惯从最小位数开始乘积，就是末尾的'3'开始。
	所以！翻转两个数字先！我去。这个是个大坑。

2. 乘积product，和移动Carrier都很普通。

3. ！！最后不能忘了再翻转。

4. 最后一个看坑。要是乘积是0，就返回‘0’。 但是这个其实可以在开头catch到没必要做到结尾catch。

用到几个StringBuffer的好东西:   
reverse（）；    
sb.deleteCharAt(i)   

找数字，或者26个字母，都可以：    
s.charAt(i) - '0'; //数字    
s.charAt(i) - 'a'; //字母   



---
**146. [Next Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/Next%20Permutation.java)**      Level: Medium
      
需斟酌。

Permutation的规律:     
1. 从小的数字开始变化因为都是从小的数字开始recursive遍历。    
2. 正因为1的规律，所以找大的断点数字要从末尾开始： 确保swap过后的permutation依然是 前缀固定时 当下最小的。    

steps:    
1. 找到最后一个上升点，k      
2. 从后往前，找到第一个比k大的点, bigIndex      
3. swap k &&　bigIndex     
4. 最后反转 (k+1，end)      




---
**147. [Nim Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Nim%20Game.java)**      Level: Easy
      
著名Nim游戏。
写一些，发现n=4,5,6,7,8...etc之后的情况有规律性: 谁先手拿到4就输了.
最终很简单n%4!=0就可以了



---
**148. [Number of Airplane in the sky.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Airplane%20in%20the%20sky.java)**      Level: Medium
      
把Interval拆分成数轴上的Point：    
起飞mark 1   
降落mark -1     
用PriorityQueue排序， loop through queue, 计算(起飞+降落)值可能有的max。

注意:
同时起飞和降落，就是 1 - 1 = 0. 所以在while loop里面有第二个while loop，    
当坐标x重合时，在这里做完所有x点的加减，然后再比较 max。     
这避免了错误多count，或者少count



---
**149. [Number of Islands II.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Islands%20II.java)**      Level: Hard
      
用HashMap的Union-find.

把board转换成1D array， 就可以用union-find来判断了。 判断时，是在四个方向各走一步，判断是否是同一个Land.

每走一次operator，都会count++. 若发现是同一个island, count--



---
**150. [Number of Islands.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Islands.java)**      Level: Medium
      
方法1: 两个for loop brutle force。 DFS把每个跟1相关的都Mark一遍.生成一个island.

方法2:
可以用union-find， 就像Number of island II 一样。
只不过这个不Return list, 而只是# of islands



---
**151. [One Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/One%20Edit%20Distance.java)**      Level: Medium
      
理解Edit: 就是删除，增加，和替换。    
换完之后，理论上换成的String 就应该全等             
一旦找到不一样的char, 就判断那三种可能性      



---
**152. [Palindrome Permutation II.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Permutation%20II.java)**      Level: Medium
      
permutation的综合题：    
1. validate Input 是不是可以做palindromic permutation. 这个就是（Palindrome Permutation I）   
2. 顺便存一下permutation string的前半部分和中间的single character(if any)    
3. DFS 做unique permutation: given input有duplicate characters。       



---
**153. [Palindrome Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Permutation.java)**      Level: Easy
      
注意，条件里面没说是否全是lower case letter



---
**154. [Pascal's Triangle II.java](https://github.com/awangdev/LintCode/blob/master/Java/Pascal's%20Triangle%20II.java)**      Level: Easy
      
简单处理array list.



---
**155. [Permutation Index.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutation%20Index.java)**      Level: Easy
      
和Permutation Sequence相反的题目。思想类似。

题目为Easy，琢磨很久，分析：    
每个数位的数字，都是跳过了小于这数字开头的多种可能。

举例【6，5，2】吧。我们找6，5，2是permudation里面的第几个。     
正常排序，也就是permutation的第一个，应该是【2，5，6】      
如果要从首位，2，变成6，要跨过多少可能性呢？     
很简单，就是问：小于6的数字有多少个呢？（2，5）.每个数字变成head，都有各自的一套变化，都有(n-1)!种可能。

本题做法：每个（n-1）!加起来。　Note:(n-1) means, 开头的数字(2,5)各带出多少种排列，也就是不就是(n-1)!嘛。
	这一步，计算数量很简单: (有几个小于6的数字) ×(除去head剩下有多少个数字)!

以上	，都是为了把６推上皇位，而牺牲的条数。

那么把６推上去以后，还有接下去的呢。

接下去要看５，２.    
６确定，后面ｐｅｒｍｕｄａｔｉｏｎ可变的情况有可能是【６，５，２】，那还可能是【６，２，５】呢。

Same process, 看ｇｉｖｅｎ　数组的第二位５，算它接下去：     
１.　有几个数字小于５呢？     
２.　除去５，还有几个数字可以　ｆａｃｔｏｒｉａｌ呢？     
３. 一样的。第一步就结果乘以第二步。      

最后接下去要看最后一个元素2了。


6,5,2全看过了以后，加起来。     
就是【6，5，2】上位，所踏过的所有小命啊！

我这解释太生动了。因为耗费了好长时间思考...



---
**156. [Permutation Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutation%20Sequence.java)**      Level: Medium
      
k是permutation的一个数位。而permutation是有规律的。

也就是说，可以根据k的大小来判断每一个数位的字符（从最大数位开始，因为默认factorio从最大数位开始变化）。

于是先求出n!， 然后 k/n!就可以推算出当下这一个数位的字符。然后分别把factorio 和 k减小。

另外, 用一个boolean[] visited来确保每个数字只出现一次。

这个方法比计算出每个permutation要efficient许多。




---
**157. [Permutations II.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutations%20II.java)**      Level: Medium
      
方法1:
Mark visited. 并且要检查上一层recursive时有没有略过重复element. 并且要排序，通过permutation规律查看是否排出了重复结果。

背景1:在recursive call里面有for loop, 每次从i=0开始, 试着在当下list上加上nums里面的每一个。    
从i=0开始，所以会依次recursive每一个nums：因此，例如i=2,肯定比i=3先被访问。也就是:取i=2的那个list permutation肯定先排出来。   

背景2:重复的例子：给出Input[x, y1, y2], 假设y的值是一样的。那么，{x,y1,y2}和{x,y2,y1}是相同结果。

综上，y1肯定比y2先被访问,{x,y1,y2}先出。 紧随其后，在另一个recursive循环里，{x,y2...}y2被先访问，跳过了y1。    
重点:规律在此，如果跳过y1，也就是visited[y1] == false, 而num[y2] == num[y1]，那么这就是一个重复的结果，没必要做，越过。

结果:那么，我们需要input像{x,y1,y2}这样数值放一起，那么必须排序。


方法2:
一个办法就是给一个visited queue。 和queue在所有的地方一同populate. 然后visited里面存得时visited indexes。 (Not efficient code. check again)



---
**158. [Permutations.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutations.java)**      Level: Medium
      
Recursive Backtracking: 取，或者不取.
Improvement: maintain list (add/remove elements) instead of 'list.contains'

Iterative: 用个queue，每次poll()出来的list, 把在nums里面能加的挨个加一遍。 However, code is a bit massive.




---
**159. [Populating Next Right Pointers in Each Node II.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node%20II.java)**      Level: Hard
      
非perfect tree, 也就是有random的null children. DFS＋BFS


Populating Next Right Pointers in Each Node I 里面依赖parent.next.left来作链接，但现在这个parent.next.left很可能也是Null.

1. 于是需要移动parent去找children level的next node。    
2. 并且每次在一个level, 要用BFS的思想把所有parent 过一遍，也就是把parent 正下方的children全部用.next链接起来    
    原因: 到下一层children变成parent, 他们需要彼此之间的connection, grand children才可以相互连接。


Note: runtime O(n * 2^log(n) ) = O(n^2), not good.



---
**160. [Populating Next Right Pointers in Each Node.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node.java)**      Level: Medium
      
方法1：   
题目要求DFS。   
其实basic implementation. 每次处理node.left.next = node.right; node.right.next = node.next.left;


方法2:   
不和题意，用了queue space，与Input成正比。太大。

BFS over Tree。 用Queue 和 queue.size()，老规矩。   
process每层queue时, 注意把next pointer加上去就好. 



---
**161. [QuickSort.java](https://github.com/awangdev/LintCode/blob/master/Java/QuickSort.java)**      Level: Easy
      
代码是不难的. 

首先partition. 返还一个partition的那个中间点的位置。
然后劈开两半。
前后各自 quick sort, recursively

注意：在partition里面, 比较的时候nums[start] < pivot, nums[end]>pivot, 如果写成了 <= 会 stack overflow.


但是： 在partition array那个题目里面，第二个 nums[end] >= pivot, 是要去加上这个 ‘=’的



---
**162. [Rehashing.java](https://github.com/awangdev/LintCode/blob/master/Java/Rehashing.java)**      Level: Medium
      



---
**163. [Remove Duplicates from Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicates%20from%20Sorted%20Array.java)**      Level: Easy
      
Remove Duplicate from Array 不同于remove from linked list.

LinkedList里面我们是最好不要动node.val的，直接把node去掉。
而array我们很难直接把node去掉，又不能用新array，那么就要：

把不重复的element一个个放到最前面。


这个思想跟merge two sorted array （其中一个后续非常长的array可以放下arr1,arr2） 类似。
就是找个不会事后mess up，不会去动得index,把满足条件的element 填进去。这样保证了in place.

* 有个反向思维：remove duplicate,实际上也是找unique elements, and insert into original array



---
**164. [Remove Duplicates from Sorted List.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicates%20from%20Sorted%20List.java)**      Level: Easy
      
一旦node.next 和node是重复，跳




---
**165. [Remove Node in Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Node%20in%20Binary%20Search%20Tree.java)**      Level: Hard
      
方法1: Brutle一点。找到target和target的parent.    
把target remove时，把target的children nodes 重新排列组成新的BST: inorder traversal, build tree based on inorder traversal list.

方法2: 分析规律,先找到target和parent, 然后根据性质，把target remove时，移动children nodes, 保证还是BST。



---
**166. [Reshape the Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Reshape%20the%20Matrix.java)**      Level: Easy
      
读例子理解题意.
理清counter case. Basic implementation



---
**167. [Reverse Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Integer.java)**      Level: Easy
      
方法1: 转换成String 然后 reverse
方法2: 每次加上x%10，然后x不断减小～0



---
**168. [Reverse Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Linked%20List.java)**      Level: Easy
      
建立新list。每次把newList append 在current node的后面。   
用head来循环所有node。



---
**169. [Reverse String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20String.java)**      Level: Easy
      
Similar to Reverse Integer.
可以用StringBuffer, 也可以two pointer reverse head/tail



---
**170. [Reverse Words in a String II.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Words%20in%20a%20String%20II.java)**      Level: Medium
      
In-place reverse.

reverse用两回. 全局reverse。局部:遇到空格reverse。

注意：结尾点即使没有' '也要给reverse一下最后一个词。




---
**171. [Reverse Words in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Words%20in%20a%20String.java)**      Level: Medium
      
几种不同的方法flip：   
坑： 1. 结尾不能有空格。 2. 注意，如果Input是 ‘ ’的话，split以后就啥也没有了。check split以后 length == 0

另个题目Reverse Words in String (char[]) 可以in-place，因为条件说char[]里面是没有首尾空格,好做许多哟.



---
**172. [reverseInteger.java](https://github.com/awangdev/LintCode/blob/master/Java/reverseInteger.java)**      Level: Easy
      



---
**173. [Roman to Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/Roman%20to%20Integer.java)**      Level: Easy
      
熟悉罗马字母规则     
1. 'I V X L C D M' 分别代表的数字     
2. 列举combo的情况，需要从原sum里面减掉多加的部分： 'IV, IX'减2, 'XL, XC'减20, 'CD, CM'减200. 

https://en.wikipedia.org/wiki/Roman_numerals



---
**174. [Rotate Image.java](https://github.com/awangdev/LintCode/blob/master/Java/Rotate%20Image.java)**      Level: Medium
      
找到个转角度的规律公式。用一个temp。in place.



---
**175. [Search Range in Binary Search Tree .java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20Range%20in%20Binary%20Search%20Tree%20.java)**      Level: Medium
      
等于遍历了所有k1<= x <= k2的x node。

如果是用Binary Search Tree搜索，那么一般是if (...) else {...}，也就是一条路走到底，直到找到target.

这里, 把 left/right/match的情况全部cover了，然后把k1,k2的边框限制好，中间就全部遍历了。



---
**176. [Search Rotated in Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20Rotated%20in%20Sorted%20Array.java)**      Level: Hard
      
方法1：O(logn)
    还是把它先当做正常的sorted list开始搜。    
    但是在比较的时候，多比较一个A[start] < A[mid]?     
    在1 和 2 里面分别讨论 target 的位置     
        1. A[start] < A[mid] ?     
            说明在前半段    
            - start<target<mid     
            - target > mid      
        2. A[start] > A[mid]     
            说明 start 还在前半段，而mid在后半段     
            - mid < target < end     
            - target < mid     

   

方法2：O(logn)     
    1. binay search break point     
    2. binary search target      
    注意等号，在判断target在前半段还是后半段：if (A[p1] <= target && target <= A[breakPoint])      





---
**177. [Segment Tree Build II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Build%20II.java)**      Level: Medium
      

给的是Array。注意找区间内的max, assign给区间。   其余和普通的segment tree build一样   

给了array,但是并不根据array里的内容排位，而是依然根据index in [0, array.length - 1]割开区间，break到底，   
最终start==end。同时assign max=A[start] or A[end]

往上,parent一层的max:就是比较左右孩子,其实都是在两个sub-tree里面比较sub-tree的max。   

这就好做了：   
先分，找到left/right，比较max,在create current node,再append到当前node上面。

实际上是depth-first, 自底向上建立起的。



---
**178. [Segment Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Build.java)**      Level: Medium
      
按定义：   
左孩子：（A.left, (A.left+A.rigth)/2）   
右孩子：（(A.left+A.rigth)/2＋1， A.right）   



---
**179. [Segment Tree Modify.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Modify.java)**      Level: Medium
      
Recursively 在segment tree里面找index, update it with value.   

每个iteration，很可能（要么左手，要么右手）max就变了。所以每次都left.max and right.max compare一下。   
最后轮回到头顶，头顶一下包括头顶，就全部都是max了。   

Divde and Conquer



---
**180. [Segment Tree Query II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query%20II.java)**      Level: Medium
      
和 Segment Tree Query I 以及其他Segment Tree问题没啥区别。这个就是return个count。

这个题目考了validate input source：input 的start,end可能超出root[start,end]。   
那么第一步就要先clear一下。完全不在range就return 0. 有range重合就规整到root的range.





---
**181. [Segment Tree Query.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query.java)**      Level: Medium
      
给了segment Tree, node里面有Max value, 找[start,end]里面的max

[start,end]跟mid相比，可能：   
全在mid左   
全在mid右   
包含了mid： 这里要特别break into 2 query method   

按定义：   
mid = (root.start + root.end)/2



---
**182. [Shortest Word Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Shortest%20Word%20Distance.java)**      Level: Easy
      
找short distance, wordB可以在wordA的前后；而同一时间，只需要计算一个最近的up to date的distance。
greedy不断变更A/B index再做比较即可。



---
**183. [Single Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Single%20Number.java)**      Level: Easy
      
Bit XOR: 当两个bit不同时，return 1. 
题目正要消光所有重复出现的数儿留下出现一次的那个.



---
**184. [Sqrt(x).java](https://github.com/awangdev/LintCode/blob/master/Java/Sqrt(x).java)**      Level: Easy
      
理解题意, 从[0, x]找一个可以m*m=x的值.
注意, 如果找不到, 最后问考官该return一个什么值：按道理，因为return int, 会取整，那么return一个平方最close to x就可以.
注意 mid 用 long, 因为很可能超过最大int.



---
**185. [String Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/String%20Permutation.java)**      Level: Easy
      
把#of occurrences 存进HashMap, 第一个string 做加法，第二个string做减法。最后看是否有不等于0的作判断。



---
**186. [String to Integer(atoi).java](https://github.com/awangdev/LintCode/blob/master/Java/String%20to%20Integer(atoi).java)**      Level: Easy
      
方法1: 问清情况，一点一点把case都涉及到。

方法2: 用regular expression。if (!str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")).  猛了一点



---
**187. [Strobogrammatic Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Strobogrammatic%20Number%20II.java)**      Level: Medium
      
耗了一点时间。本以为需要DP一下，把做过的n存一下。后来发现，其实就是剥皮，一层一层，是一个central-depth-first的，钻到底时候，return n=1,或者n=2的case，然后开始backtracking。
难的case先不handle.到底之后来一次O(n) scan.
总共的时间起码是O(n/2) + O(n), 所以还是O(n)


---
**188. [Strobogrammatic Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Strobogrammatic%20Number.java)**      Level: Easy
      
根据题意枚举, 再根据规则basic implementation



---
**189. [Subarray Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20Closest.java)**      Level: Medium
      
?



---
**190. [Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum.java)**      Level: Easy
      
分析出，如果sum[0~a]=x, 然后sum[0~b]=x, 说明sum(a ~ b] = 0.    

    这样理解后，用hashMap存每个sum[0~i]的值和index i. 如果有重复，就找到了一组sum为0的数组。



---
**191. [Subset.java](https://github.com/awangdev/LintCode/blob/master/Java/Subset.java)**      Level: Medium
      
最基本的递归题目。   
坑：记得一开头sort一下 nums。 因为要升序。那么整体就是O(nlogn)

注意：用level/index来track到哪一步。最后一level就add into rst

方法1: subset的概念，取或者不取,backtracking. 当level/index到底，return 一个list.

方法2: 用for loop backtracking. 记得：每个dfs recursive call是一种独特可能，先加进rst。


recap:时间久了忘记dfs的两种路子. for loop dfs/backtracking vs. regular dfs



---
**192. [Subsets II.java](https://github.com/awangdev/LintCode/blob/master/Java/Subsets%20II.java)**      Level: Medium
      
递归：找准需要pass along的几个数据结构。    

和SubsetI类似，先sort input, 然后递归。但是input可能有duplicates. 

Using for loop approach: 每个dfs call是一种可能性，直接add into result.     
为了除去duplicated result, 如果在递归里面用rst.contains(),就是O(n), which makes overall O(n^2). 

这里有个基于sorted array的技巧：    
因为我们有mark index。 一旦for loop里面的i!=index，并且nums[i] == nums[i-1],说明x=nums[i-1]已经用过，不需要再用一次：     
[a,x1,x2]，x1==x2    
i == index -> [a,x1]    
i == index + 1 -> [a,x2]. 我们要skip这一种。

如果需要[a,x1,x2]怎么办？ 其实这一种在index变化时，会在不同的两个dfs call 里面涉及到。


Iterative: 写一写，用个Queue. Not recommended, Again, rst.contains() cost too much.



---
**193. [Subtree.java](https://github.com/awangdev/LintCode/blob/master/Java/Subtree.java)**      Level: Easy
      
找到potential subtree, 比较Children.

一点注意：即使找到T1 == T2, 但很可能只是数字相同（这里不是binary search tree!!），而children不同。所以同时要继续recursively isSubtree(T1.left, T2) ...etc.



---
**194. [Symmetric Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Symmetric%20Binary%20Tree.java)**      Level: Easy
      
Symmetric Tree

注意Symmetric Binary Tree的例子和定义: 是镜面一样的对称. 并不是说左右两个sub-tree相等。

方法1: Recursively check symmetrically相对应的Node.  每个node的children都和镜面另外一边相对的node的children刚好成镜面反射位置。

方法2: 用stack. 左手边sub-tree先加left,再加right child; 右手边sub-tree先加right child, 再加left child。   
process时，若symmetric，所有stack里面出来的node会一一对应。



---
**195. [Top K Frequent Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Elements.java)**      Level: Medium
      
题目有提醒: 不能用O(nLog(n)) 也就是说明要Log(n): 首先想到就是PriorityQueue, 并且不能queue.offer on the fly.
那么就先count, O(n); 再priorityQueue, Log(k), k是unique 数字的总量. 



---
**196. [Top K Frequent Words.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Words.java)**      Level: Medium
      
方法1：Brutle force用HashMap存frequency, 用ArrayList存lists of words。最后返回从尾部向前数的k个。   
   注意排序时Collection.sort()的cost是O(nLogk)

方法1-1: 还是用HashMap,但create一个Node class, 然后用PriorityQueue.   
PriorityQueue里面用到了 String.compareTo(another String).巧妙。

方法2: Trie && MinHeap屌炸天   
   http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/



---
**197. [Topological Sorting.java](https://github.com/awangdev/LintCode/blob/master/Java/Topological%20Sorting.java)**      Level: Medium
      
比较特别的BFS.

几个graph的condition：   
1. 可能有多个root
2. directed node, 可以direct backwards.

Steps:    
Track all neighbors/childrens. 把所有的children都存在map<label, count>里面
先把所有的root加一遍，可能多个root。并且全部加到queue里面。

然后以process queue, do BFS:   
Only when map.get(label) == 0, add into queue && rst.    
这用map track apperance, 确保在后面出现的node, 一定最后process.




---
**198. [Trapping Rain Water II.java](https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water%20II.java)**      Level: Hard
      
用PriorityQueue把选中的height排序。为走位，create class Cell {x,y, height}.

注意几个理论：
1. 从matrix四周开始考虑，发现matrix能Hold住的水，取决于height低的block。
2. 必须从外围开始考虑，因为水是被包裹在里面，外面至少需要现有一层。

以上两点就促使我们用min-heap: 也就是natural order的PriorityQueue<Cell>.

process的时候，画个图也可以搞清楚，就是四个方向都走走，用curr cell的高度减去周围cell的高度。 若大于零，那么就有积水。

每个visited的cell都要mark. 去到4个方向的cell,加进queue里面继续process. 

这里，有一点，和trapping water I 想法一样。刚刚从外围，只是能加到跟外围cell高度一致的水平面。往里面，很可能cell高度变化。   
这里要附上curr cell 和 move-to cell的最大高度。



---
**199. [Tweaked Identical Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Tweaked%20Identical%20Binary%20Tree.java)**      Level: Easy
      
Recursive 比对左左,左右,右左，右右



---
**200. [Two Strings Are Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Strings%20Are%20Anagrams.java)**      Level: Easy
      
方法1:char ascii 用count[256]   
坑：不要想象这个是个26letter lowercase. may not be true.

方法2: 若是其他字符encoding, 而不只是utf16-encoding (java char)?   
那么就继续用string去做



---
**201. [Ugly Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Ugly%20Number.java)**      Level: Medium
      
方法1: PriorityQueue排序。用ArrayList check 新的ugly Number是否出现过。

方法1-1：(解释不通，不可取)用PriorityQueue排序。神奇的3，5，7走位：按照题目答案的出发，定了3，5，7以什么规律出现。但是题目并没有特殊表明。

方法2: DP . Not Done yet.




---
**202. [Valid Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Anagram.java)**      Level: Easy
      
HashMap



---
**203. [Valid Palindrome.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Palindrome.java)**      Level: Easy
      
tutorial:https://www.youtube.com/watch?v=2hNK0Yz53LQ&list=PLZn-UvluQZuNedn1hDzTmNLE8MQWXjKVb

过滤alphanumeric，其他字母掠过



---
**204. [Valid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Parentheses.java)**      Level: Easy
      
剥皮过程。解铃还须系铃人   
左边的外皮'{['在stack底部   
右边的外皮应该和stack顶上的左外皮一一对应 





---
**205. [Valid Sudoku.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Sudoku.java)**      Level: Easy
      
用HashSet存visited value.

方法1: 在nest for loop里面validate row,col,and block.     
validate block要利用i 和 j 增长的规律。    
说白了，i && j是按照0~n增长的index, 具体怎么用是可以flexible的。这个方法在同一个nest for loop解决所有运算。

方法2: 单独做block validation: validate block的时候虽然看到了4层for.其实也就是n^2.



---
**206. [Validate Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Validate%20Binary%20Search%20Tree.java)**      Level: Medium
      
查看每个parent-child关系。同时把root level上面传下来max,min界限定住。



---
**207. [Word Break II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Break%20II.java)**      Level: Hard
      
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
**208. [Word Break.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Break.java)**      Level: Medium
      
DP

方法1:（attempt3 code）
state,rst[i]: 从[0～i] inclusive的string是否可以在dict中break开来找到？      
function: rst[i] = true if (rst[i - j] && set.contains(s.substring(i - j, i))); j in[0~i]     
1. rst[i - j] 记录的是[0, i-j]这一段是否可以break后在dict找到。     
2. 若true，再加上剩下所有[i-j, i]都能在dict找到，那么rst[i] = rst[0, i - j] && rst[i-j, i] == true

优化：找dict里面最长string, 限制j的增大。

(attempt4 code)    
与Word BreakII用同样的DP。
valid[i]: 记录从i到valid array末尾是否valid.



---
**209. [Word Ladder II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Ladder%20II.java)**      Level: Hard
      


---
**210. [Word Ladder.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Ladder.java)**      Level: Medium
      
BFS Brutle: 在start string基础上，string的每个字母都遍历所有26个字母，换换。

方法2:    
用Trie。 理应更快. However implementation可能有点重复计算的地方，LeetCode timeout. 需要再做做。



---
**211. [Word Pattern.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Pattern.java)**      Level: Easy
      
每个char代表一个pattern。用HashMap<char, str>.
但不够，如果a也match dog, b也match dog, 纠错了。比如pattern = "abba", str = "dog dog dog dog"。
因此第二个HashMap<str, char> 反过来。
确保pattern和str一一对应。



---
**212. [Word Search II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search%20II.java)**      Level: Hard
      
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
**213. [Word Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search.java)**      Level: Medium
      
Backtracking:
比较 Brutle。找到开头的字母，然后投入一个recursive找字母的工程：每到一个字母，朝四个方向走。他们之中，有一个true就可以。

Note:每次到一个字母，mark一下'#'. 4个path recurse回来后，mark it back.

Backtracking方法2:    
用一个boolean visited[][]





---
**214. [Find Anagram Mappings.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Anagram%20Mappings.java)**      Level: Easy
      
比较简单, 用HashMap 存index list. 最后再遍历一遍数组A, 列举出所有元素.
O(n)



---
**215. [Judge Route Circle.java](https://github.com/awangdev/LintCode/blob/master/Java/Judge%20Route%20Circle.java)**      Level: Easy
      
简单的character checking. 各个方向, 加加减减.



---
**216. [Island Perimeter.java](https://github.com/awangdev/LintCode/blob/master/Java/Island%20Perimeter.java)**      Level: Easy
      
最简单的方法: 每个格子4个墙;每个shared的墙要-2 (墙是两面, -1 * 2)
最后合计结果就好.

不必想太多用HashMap做.但是也可以思考一下:
- 把每个block相连的block全部存在以当下block为key的list里面. 那么这里需要把2D坐标, 转化成一个index.
- 最后得到的map, 所有的key-value应该都有value-key的反向mapping, 那么久可以消除干净, 每一次消除就是一个shared wall.
- 一点点optimization: DFS去找所有的island, 如果island的图非常大, 而island本身不打,那么适合optimize.
  但是整体代码过于复杂. 不建议写.




---
**217. [First Unique Character in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/First%20Unique%20Character%20in%20a%20String.java)**      Level: Easy
      
方法1: 按照题意, 找到第一个 first index == last index的字母.

方法2: 用hashmap存字母的index, 有些重复字母的index就会是个list. 找到单一index, 结合成list, sort, return list.get(0)



---
**218. [Power of Three.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Three.java)**      Level: Easy
      
方法1:
Power of 3:  3 ^ x == n ?
意思是 n / 3 一直除, 最后是可以等于1的, 那么就有了 n/=3, check n%3, 最后看结果是不是整除到1的做法. 用while loop.

方法2:
如果n是power of 3, 那么 3 ^ x的这个 x一定是个比n小的数字. 那么可以在 0 ~ n 之间做binary serach, 但是就比较慢.

方法3:
巧妙的想法.最大的3^x integer是 3^19. 那么找到这个数, 一定可以被n整除. 一步到位.



---
**219. [Plus One.java](https://github.com/awangdev/LintCode/blob/master/Java/Plus%20One.java)**      Level: Easy
      
简单的实现, 加1, 进位. 唯一取巧的地方, 最后如果要多一位, 一定是10000...这个模式, 可以走捷径, 直接来个+1size的array, 然后第一位=1.
注意,转换成long也不合理,用太多memory.


---
**220. [Power of Two.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Two.java)**      Level: Easy
      
跟powerOfThree一样: 可以loop, check mod; 也可以用binary search找合适的数字.



---
**221. [Reverse Vowels of a String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Vowels%20of%20a%20String.java)**      Level: Easy
      
方法1: two pointer. 前后两个指针, 在while loop里面跑. 注意 i<j, 一旦相遇, 就break. 找到合适的, 就做swap.
StringBuffer可以 sb.setCharAt()记得用.

方法2: 拿出所有vowels, 反过来放进去. O(n)



---
**222. [Guess Number Higher or Lower.java](https://github.com/awangdev/LintCode/blob/master/Java/Guess%20Number%20Higher%20or%20Lower.java)**      Level: Easy
      
binary search 公式



---
**223. [Encode and Decode TinyURL.java](https://github.com/awangdev/LintCode/blob/master/Java/Encode%20and%20Decode%20TinyURL.java)**      Level: Medium
      
其实想到了切入点, 是个可难可简单的题目. 这里的encode就是想办法把url存起来, 然后给个 key.
那么具体怎么做这个key, 简单就可以用一个map, 然后counting. 复杂一点就可以做random letter/number组成key.



---
**224. [Wiggle Sort.java](https://github.com/awangdev/LintCode/blob/master/Java/Wiggle%20Sort.java)**      Level: Medium
      
方法1:
排序, nLog(n). 然后把直线上坡变成层叠山峰, 需要每隔几个(题目中是每隔2位)就做个swap 造成高低不平.
Note: 每隔山峰之间是相互没有关系的, 所以每次只要操心 [i], [i-1]两个位置就好了.

方法2:
O(n)
看好奇数偶数位的规律, 然后根据题目给出的规律, 跑一遍, 每次只关注两个位置: 把不合适的[i], [i-1]调换位置就好了.

方法3:
跟法2一样, 只是更巧妙一点罢了:
第一遍想太多. 其实做一个fall-through就能把问题解决，原因是因为：
这样的fall-through每次在乎两个element，可以一口气搞定，无关乎再之前的elements。
特别的一点：flag来巧妙的掌控山峰和低谷的变化。又是神奇的一幕啊！
这样子的奇观，见过就要知道了，没见过的时候有点摸不着头脑。



---
**225. [Queue Reconstruction by Height.java](https://github.com/awangdev/LintCode/blob/master/Java/Queue%20Reconstruction%20by%20Height.java)**      Level: Medium
      
别无他法, 只能写一遍例子, 找规律,然后greedy. 
需要写一遍发现的规律比如: 从h大的开始排列, 先放入k小的. 写comparator的时候要注意正确性.
如果要sort, 并且灵活insert:用arrayList. 自己做一个object.
最后做那个'matchCount'的地方要思路清晰, 找到最正确的spot, 然后greedy insert.

O(n) space, O(nLog(n)) time, because of sorting.

可能有简化的余地, 代码有点太长.
比如试一试不用额外空间?



---
**226. [2 Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum.java)**      Level: Easy
      [Link](https://www.youtube.com/watch?v=P8zBxoVY1oI&feature=youtu.be)
解法1：相对暴力简洁, HashMap<value, index>，找到一个value, 存一个; 若在HashMap里面 match 到结果, 就return HashMap里存的index. O(n) space && time.

解法2：Sort array, two pointer 前后++,--搜索。Sort 用时O(nlogn).     
1. 第一步 two pointer 找 value.       
2. 注意，要利用额外的空间保留original array， 用来时候找index. (此处不能用HashMap，因为以value 为key，但value可能重复)      
O(n) space, O(nlogn) time.    




---
**227. [2 Sum II - Input array is sorted.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum%20II%20-%20Input%20array%20is%20sorted.java)**      Level: Medium
      
排序好的array. Two pointer移动start和end，核查sum.
注意sum用long.



---
**228. [2 Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum%20II.java)**      Level: Medium
      

LintCode的题. 注意找的是greater/bigger than target。

由于给定条件允许O(nLogn):   
   sort
   two pointer

while里面two pointer移动。每次如果num[left]+num[right] > target，那么其中所有num[left++]的加上num[right]都>target.   
也就是,num[right]不动，计算加入挪动left能有多少组，那就是: right-left这么多。 全部加到count上去。     
然后right--.换个right去和前面的left部分作比较。



---
**229. [Coin Change.java](https://github.com/awangdev/LintCode/blob/master/Java/Coin%20Change.java)**      Level: Medium
      
DP. 找对方程f[x], 积累到amount x最少用多少个coin: #coin是value, index是 [0~x].
子问题的关系是: 如果用了一个coin, 那么就应该是f[x - coinValue]那个位置的#coins + 1

注意initialization: 
处理边界, 一开始0index的时候, 用value0. 
中间利用Integer.MAX_VALUE来作比较, initialize dp[x]
注意, 一旦 Integer.MAX_VALUE + 1 就会变成负数. 这种情况会在coin=0的时候发生.

方法1: 用Integer.MAX_VALUE
方法2: 用-1, 稍微简洁一点, 每次比较dp[i]和 dp[i - coin] + 1, 然后save. 不必要做多次min比较.



---
**230. [Unique Path.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Path.java)**      Level: Medium
      
可以DP.计数DP.
注意方程式前两位置加在一起: 前两种情况没有overlap, 也不会缺情况.
注意initialization, 归1.
需要initialize的原因是,也是一个reminder: 在方程中会出现-1index



---
**231. [Jump Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game.java)**      Level: Medium
      
给出步数，看能不能reach to end.

Status:
DP[i]: 在i点记录，i点之前的步数是否可以走到i点？ True of false.
    其实j in [0~i)中间只需要一个能到达i 就好了
Function:
DP[i] = DP[j] && (j + A[j]) ?= i, for all j in [0 ~ i)
Return:
    DP[dp.length - 1];



---
**232. [Maximum Product Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Product%20Subarray.java)**      Level: Medium
      
求最值, DP.
两个特别处:
1. 正负数情况, 需要用两个DP array. 
2. continuous prodct 这个条件决定了在Math.min, Math.max的时候, 
是跟nums[x]当下值比较的, 如果当下值更适合, 会舍去之前的continous product, 然后重新开始.
这也就注定了需要一个global variable 来hold result.



---
**233. [3 Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/3%20Sum%20Closest.java)**      Level: Medium
      
3Sum 的一种简单形式, 并且都没有找index, value, 而只是找个sum罢了.

double for loop。 2Sum只能用土办法 left/right 2 pointers。 O(n^2)

注意：check closest时候用long, 以免int不够用



---
**234. [Triangle Count.java](https://github.com/awangdev/LintCode/blob/master/Java/Triangle%20Count.java)**      Level: Medium
      
其实也就是3sum的变形, 或者而说2sum的变形. 主要用2 pointers来做.
注意, 在选index时候每次定好一个 [0 ~ i], 在这里面找点start, end, 然后i 来组成triangle.
Note巧妙点:
在此之中, 如果一种start/end/i 符合, 那么从这个[start~end]中, 大于start的都可以, 所以我们count+= end-start.
反而言之, <end的其他index, 就不一定能符合nums[start] + nums[end] > nums[i]



---
**235. [3 Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/3%20Sum.java)**      Level: Medium
      
方法1:
sort array, for loop + two pointer. O(n)
处理duplicate wthin triplets: 
如果最外圈的移动点i重复, 一直顺到结尾的最后一个再用.
如果是triplet内有重复, 用完start point, 移动到结尾.

Previous notes:
注意:   
   1. 找 value triplets, 多个结果。注意，并非找index。    
   2. 要升序, 第一层for loop 从最后一个元素挑起, 保证了顺序。    
   3. 去掉duplicate: check用过的同样的数字，都跳掉。不需要用同样的数字再计算一边已有结果。

步骤:   
   1. For loop 挑个数字A.    
   2. 2Sum 出一堆2个数字的结果    
   3. Cross match 步骤1里面的A.   

时间 O(n^2), 两个nested loop

另外, 还是可以用HashMap来做2Sum。稍微短点。还是要注意handle duplicates.




---
**236. [4 Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/4%20Sum.java)**      Level: Medium
      
方法1：  
   1. 利用2Sum的原理，把4Sum分为连个2Sum。左一个pair,右一个pair，每个pair里面放2个数字。   
   2. 以一个点，i，作为分界口，也要列举出所有i之前的pair,作为基础。   
   3. 再尝试从所有i+1后面,找合适的2nd pair。   
 
   可以用HashSet<List>, 可以直接比较list里面每一个元素, 保证set不重复.
   Previous Notes: 在造class Pair时候，要做@override的function: hashCode(), equals(Object d). 平时不太想得起来用。
   参见 http://lifexplorer.me/leetcode-3sum-4sum-and-k-sum/    

方法2： 3Sum外面再加一层. 参考3Sum. 时间O(n^3)。 但此方法在k-sum时候，无疑过于费时间. O(n^k)



---
**237. [k Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/k%20Sum.java)**      Level: Hard
      
DP. 公式如何想到, 还需要重新理解.

dp[i][j][m]: # of possibilities such that from j elements, pick m elements and sum up to i. 
i: [0~target]

dp[i][j][m] = dp[i][j-1][m] + dp[i - A[j - 1]][j-1][m-1]
            (i not included)   (i included)



---
**238. [Trapping Rain Water.java](https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water.java)**      Level: Hard
      
方法1:
主要想法和方法2一致: 在山坡下坡的基础上, 一直用stack堆积bottom. 
最后遇到上升之前, 此时bottom可以用来跟stack之前堆积的所有下坡index做比较, 算跟他们高度相差的积水.
用了stack记录下坡, 然后用个while loop一挖到底的想法非常棒.

方法2:
2 Pointers， 双面夹击：   
1. 找中间最高bar的index    
2. 两面往中心扫：每次加上（topBarIndex - currIndex）* (elevation from previous index).也就是每次加一个横条。    
3. 每次还要减去block自身的height。



---
**239. [Longest Continuous Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Continuous%20Increasing%20Subsequence.java)**      Level: Easy
      
简单的DP:dp[i]存在i点时连续上升#. dp[i]时可能为0, 一旦断开.

方法1: dp[i], maintain max
方法2: 用一个数存current count,  maintain max. 也是DP, 稍微简化.



---
**240. [Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Subsequence.java)**      Level: Medium
      
方法1:
[0 ~ i], 0<i<n: 以i为结尾, 每个不同的i会得出的max-length. 所以每个结尾i都要被考虑一遍.
dp[i]: represent the max length aggregated up to index i.

Previous Note:
i位和之前的0~i-1 都远关系。复杂一点。
每次都考虑o~i的所有情况。所以double for loop


方法2:
O(n log n)? 还没有做. 是否for loop里面用binary search?



---
**241. [Unique Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree.java)**      Level: Medium
      
Not quite clear.
根据左右分割而总结出了原理, 每次分割, 左右两边都会有一定数量的permutation, 总体上的情况数量当然是相乘.
然后每一个不同的分割点都加一遍:
f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

然后把数学公式转换成DP的方程, 有点玄学的意思啊! 不好想.



---
**242. [Trim a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Trim%20a%20Binary%20Search%20Tree.java)**      Level: Easy
      
方法1:
适合复习BST. 用DFS对待每个node. 注意BST的特征: 所有left nodes都小于当下node, 所有right nodes都大于当下node.

根据题意用[L,R]切割.如果node.val<L, 直接连node带左边全丢掉, return node.right. 处理R也是一样.
分制是, DFS leftNode, rightNode. 然后接在node.left, node.right.

方法2: 用迭代, 还没有写.



---
**243. [Unique Paths II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Paths%20II.java)**      Level: Medium
      
典型的坐标型DP. 考虑最终结尾需要的状态:如何组成,写出公式.
公式中注意处理能跳掉的block, '到不了', 即为 0 path.



---
**244. [Counting Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Counting%20Bits.java)**      Level: Medium
      
Bit题目 用num的数值本身表示DP的状态.
这里, dp[i] 并不是和 dp[i-1]有逻辑关系; 而是dp[i] 和dp[i>>1], 从binary representation看出有直接关系.



---
**245. [Bomb Enemy.java](https://github.com/awangdev/LintCode/blob/master/Java/Bomb%20Enemy.java)**      Level: Medium
      
分方向考虑的方法很容易想到,但是四个方向移动的代码比较繁琐.
往上炸: 要从顶向下考虑
往下炸: 要从下向上考虑

熟练写2D array index 的变换.



---
**246. [Paint House.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House.java)**      Level: Easy
      
考虑DP最后一个位置的情况. 发现给出了一些特殊条件, 需要附带在DP[i-1]上,
那么就定义二维数组



---
**247. [Decode Ways.java](https://github.com/awangdev/LintCode/blob/master/Java/Decode%20Ways.java)**      Level: Review
      
确定末尾的2种状态: single letter or combos. 然后计算出单个letter的情况, 和双数的情况
note: calculate number from characters, need to - '0' to get the correct integer mapping.
注意: check value != '0', 因为'0' 不在条件之中(A-Z)




---
**248. [House Robber.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber.java)**      Level: Easy
      
最基本的dp。      
看最后结尾状态的前一个或前两个的情况，再综合考虑当下的。      
思考的适合搞清楚当下的和之前的情况的关系。    
滚动数组的优化，就是确定了是这类“只和前一两个位子“相关的Fn而推出的。   



---
**249. [House Robber II.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20II.java)**      Level: Medium
      
和House Robber I 类似,  DP. 根据dp[i-1]是否被rob来讨论dp[i]: dp[i] = Math.max(dp[i-1], dp[i - 2] + nums[i - 1]);

特别的是，末尾的last house 和 first house相连。这里就需要分别讨论两种情况:    
1. 第一个房子被rob    
2. 第一个房子没被rob   

分出两个branch, 可以看做两种状态. 
可以考虑用两个DP array, 也可以加一维度, 补充这个状态.



---
**250. [Best Time to Buy and Sell Stock I.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20I.java)**      Level: Easy
      
理解意思是关键:
   每天都就交易价格，n天只让买卖一次，那就找个最低价买进，找个最高价卖出。
   记录每天最小值Min是多少。O(n)
   每天都算和当下的Min买卖，profit最大多少.

这里就可以DP, memorize the min[i]: the minimum among [0 ~ i]; 然后用当天的price做减法算max.
更进一步, 用一个min来表示min[i], 因为计算中只需要当下的min.


Brutle:
每天都试着买进，然后之后的每一天尝试卖出. double for loop, O(n^2). timeout.
其中很多都是没必要的计算：[7, 1, 5, 3, 6, 4]。 if we know buyin with 1 is cheapest, we don't need to buyin at 5, 3, 6, 4 later on; we'll only sell on higher prices.



---
**251. [Best Time to Buy and Sell Stock II.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.java)**      Level: Easy
      

这道题有几种其他不同的思路:
1. Greedy, 每次有相邻的diff符合profit条件, 就卖了, 最后把所有的diff加在一起. 计算delta, 其实简单粗暴, 也还不错.
2. 如下, 从低谷找peek, sell.
3. 繁琐一点的DP. BuyOn[], SellOn[] 从末尾看起
4. DFS计算所有(timeout).Improvement on DFS -> DP -> calculate sellOn[i] and buyOn[i], and then return buyOn[i]. 有点难想, 但是代码简单, 也是O(n)

找涨幅最大的区间，买卖：
找到低谷，买进:peek = start + 1 时候，就是每次往前走一步;若没有上涨趋势，继续往低谷前进。
涨到峰顶，卖出:一旦有上涨趋势，进一个while loop，涨到底, 再加个profit.

profit += prices[peek - 1] - prices[start]; 挺特别的。
当没有上涨趋势时候，peek-1也就是start, 所以这里刚好profit += 0.

O(n)



---
**252. [Best Time to Buy and Sell Stock III .java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III%20.java)**      Level: Hard
      
比stock II 多了一个限制：只有2次卖出机会。

方法1:
DP加状态: 只卖2次, 把买卖分割成5个状态模块.
在模块index 0, 2, 4: 没有持有股票. 1. 一直在此状态, max profit不变; 2. 刚卖掉, dp[i][前状态] + profit
在模块index 1, 3: 持有股票. 1. 一直在此状态, daily profit. 2. 刚刚买进, 状态改变, 但是没有profit yet: dp[i][前状态]

注意: 把每天的partial profit (diff)加在一起, 最终的overall profit是一样的. 唯一更好的是, 不需要记录中间买入的时间点.

方法2:
也就是：找峰头；然后往下再找一个峰头。

怎么样在才能Optimize两次巅峰呢？

从两边同时开始找Max！（棒棒的想法）

   leftProfit是从左往右，每个i点上的最大Profit。
   rightProfit是从i点开始到结尾，每个点上的最大profit.
   那么在i点上，就是leftProfit，和右边rightProfit的分割点。在i点，leftProfit+rightProfit相加，找最大值。

三个O(n),还是O(n)



---
**253. [Best Time to Buy and Sell Stock IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20IV.java)**      Level: Hard
      
方法1:
DP. 根据StockIII, 不难发现StockIV就是把状态划分为2k+1份. 那么同样的代码, 移植.
注意1: 如果k很大, k>n/2, 那么长度为n的数组里面, 最多也只能n/2个transaction, 
	  那么题目简化为stockII, 给n数组, 无限次transaction.
注意2: n可以用滚动数组(prev/now)代替.
注意3: 最后状态是'没有stock'的都该考虑, 做一个 for 循环比较max. 当然, 来一个profit variable,
		不断比较, 也是可以的.

方法2:
记得要理解： 为什么 i-1天的卖了又买，可以和第 i 天的卖合成一次交易？    
   因为每天交易的price是定的。所以卖了又买，等于没卖！这就是可以合并的原因。要对价格敏感啊少年。

Inspired from here:    
http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html

局部最优解 vs. 全局最优解：     
   local[i][j] = max(global[i – 1][j – 1] + diff, local[i – 1][j] + diff)    
   global[i][j] = max(global[i – 1][j], local[i][j])     

local[i][j]: 第i天，当天一定进行第j次交易的profit     
global[i][j]: 第i天，总共进行了j次交易的profit.

local[i][j]和global[i][j]的区别是：local[i][j]意味着在第i天一定有交易（卖出）发生。    
   当第i天的价格高于第i-1天（即diff > 0）时，那么可以把这次交易（第i-1天买入第i天卖出）跟第i-1天的交易（卖出）合并为一次交易，即local[i][j]=local[i-1][j]+diff；    
   当第i天的价格不高于第i-1天（即diff<=0）时，那么local[i][j]=global[i-1][j-1]+diff，而由于diff<=0，所以可写成local[i][j]=global[i-1][j-1]。    
   (Note:在我下面这个solution里面没有省去 +diff）   

global[i][j]就是我们所求的前i天最多进行k次交易的最大收益，可分为两种情况：    
   如果第i天没有交易（卖出），那么global[i][j]=global[i-1][j]；     
   如果第i天有交易（卖出），那么global[i][j]=local[i][j]。    





---
**254. [Paint House II.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House%20II.java)**      Level: Review
      
一般的DP被加了状态变成2D. 
考虑最后位, 而前一位i-1又被i位的颜色限制, 于是在考虑 min dp[i] 时候, 又多了一层iteration.
所以变成了O(NK^2)

注意: 
1. 序列型dp[i]表示'前i-1个'的结果. 所以dp最好设定为 int[n + 1] size. 
    然而, 颜色在这里是状态, 所以保留在 j: [ 0~k)
2. [[8]] 这样的edge case. 跑不进for loop, 所以特殊handle.

Optimization:
如果已知每次都要从cost里面选两个不同的最小cost,那么先把最小挑出来, 就不必有第三个for loop
O(NK)



---
**255. [3 Sum Smaller.java](https://github.com/awangdev/LintCode/blob/master/Java/3%20Sum%20Smaller.java)**      Level: Medium
      
一般的O(n3)肯定不行。在此基础上优化。
发现j,k满足条件时候，(k - j)就是所有 sum <target的情况了。
而一旦>target, 又因为j不能后退，只能k--，那么问题就被锁定了. 这样可以做到O(n2)



---
**256. [Array Partition I.java](https://github.com/awangdev/LintCode/blob/master/Java/Array%20Partition%20I.java)**      Level: Easy
      
从结果出发, 只需要找到加法的结果，而不强调具体配对。
找到排列取单数位的规律，再考虑负数和正数的相同规律，即可找到排列求解的方法。




---
**257. [1-bit and 2-bit Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/1-bit%20and%202-bit%20Characters.java)**      Level: Easy
      
方法1:
Greedy.
从第一个bit开始数: 如果遇到1, 一定是跳两位;如果遇到0, 一定是跳一位.
loop to end, and see if index reaches the end.

方法2:
用DP硬做了一下: 
1. 如果i位是0, 那么前面dp[i-1]或者dp[i-2] true就够了.
2. 如果i位是1, 那么i-1位必须是1才满足规则, 并且 dp[i-2]需要true.



---
**258. [Non-decreasing Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Non-decreasing%20Array.java)**      Level: Easy
      
比较升序的时候, 必须要估计到 i-1, i, i+1三个数位.
写出来i-1, i+1之间的关系, 然后做合理的fix.

需要真的fix数组, 因为loop through做比较时会用到fix后的数字.



---
**259. [Max Consecutive Ones.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Consecutive%20Ones.java)**      Level: Easy
      
Basic. Math.max track结果。
记得在有对外操作的loop后，一定要把result object清理干净。



---
**260. [Find All Numbers Disappeared in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20All%20Numbers%20Disappeared%20in%20an%20Array.java)**      Level: Easy
      
方法1:
换到正确的位置。
需要小心handle i, 因为不知道换到nums[i]上的是什么，所以必须原地清理干净 方能move on.

方法2:
做标记！
很巧妙地运用了标记的方法, 标记成负数，证明visit过。
Preserve原数的负数，这样可以继续用此负数的绝对值来寻找原数所该被定的位置。非常巧妙！

方法3:
做标记！
跟方法2类似，也是做标记，这一次是加上一个大于n的数（因为题目给了n的border），最后check一下就好。为不超Integer.MAX_VALUE, 每次加n前，取余数。

做标记的方法固然快，但是相对来说比较hacky，在常规的代码中，估计不会用到.




---
**261. [Maximum Average Subarray I.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20I.java)**      Level: Easy
      
简单的求sum, 同时求max, 结尾求余数就好.


---
**262. [Largest Number At Least Twice of Others.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Number%20At%20Least%20Twice%20of%20Others.java)**      Level: Easy
      
找最大值, 和第二大的值, 看是否符合题意, 就行了.
分析题意, 最简单方法, 可以loop 两遍: 找最值; 作比较.
但其实, 举个反例: 有一个不满足, 就够反对这个'at least twice of alllll others'.



---
**263. [Toeplitz Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Toeplitz%20Matrix.java)**      Level: Easy
      
似乎没什么算法特点, 就是array基本运算, 然后分割成一个helper function去做重复计算, 剪短代码.
注意check MxN 的分界线.



---
**264. [Backpack.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack.java)**      Level: Medium
      
考虑: 用i个item (可跳过地取), 是否能装到weight w?
需要从'可能性'的角度考虑, 不要搞成单一的最大值问题.

1. 背包可装的物品大小和总承重有关.
2. 不要去找dp[i]前i个物品的最大总重, 找的不是这个. 
    dp[i]及时找到可放的最大sum, 但是i+1可能有更好的值, 把dp[i+1]变得更大更合适.

boolean[][] dp[i][j]表示: 有前i个item, 用他们可否组成size为j的背包? true/false.
(反过来考虑了，不是想是否超过size j, 而是考虑是否能拼出exact size == j)
**注意**: 虽然dp里面一直存在i的位置, 实际上考虑的是在i位置的时候, 看前i-1个item.

多项式规律:
1. picked A[i-1]: 就是A[i-1]被用过, weight j 应该减去A[i-1]. 那么dp[i][j]就取决于dp[i-1][j-A[i-1]]的结果.
2. did not pick A[i-1]: 那就是说, 没用过A[i-1], 那么dp[i][j]就取决于上一行d[i-1][j]
dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]]

结尾:
跑一遍dp 最下面一个row. 从末尾开始找, 最末尾的一个j (能让dp[i][j] == true)的, 就是最多能装的大小 :)   

时间，空间都是：O(mn)




---
**265. [A+B.java](https://github.com/awangdev/LintCode/blob/master/Java/A+B.java)**      Level: Easy
      
a^b 是: 不完全加法.
a&b 是: 所有可能的进位. a&b<<1是向左边进位的形态.

Goal: 先a^b裸加, 算出进位; 再把结果和进位裸加, 再算出这一轮的进位; 再..裸价, 算进位....直到进位数==0. 

那么就，首先记录好进位的数字：carry. 然后 a^b 不完全加法一次。然后b用来放剩下的carry, 每次移动一位，继续加，知道b循环为0为止。

在第一回 a ^ b 之后, b 的本身意义就消失. 接下去应该给parameter重新命名.
sum = a ^ b; // sum without adding carries
nextCarry = (a & b) << 1;

用其他variable name 取代 a, b 会更好理解一点.

Bit Operation    
Steps: 
   a & b: 每bit可能出现的进位数       
   a ^ b: 每bit在此次操作可能留下的值，XOR 操作         
   每次左移余数1位，然后存到b, 再去跟a做第一步。loop until b == 0    

(http://www.meetqun.com/thread-6580-1-1.html)



---
**266. [Swap Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Swap%20Bits.java)**      Level: Easy
      
简单, 但是很多知识点:
1. Hex 0xaaaaaaaa 是1010101....1010; 0x55555555 是01010101....0101
2. 可以用这两个hex取单数和负数. 如果需要取其他的pattern, 也可以做.
3. x很可能是negative number, 所以right-shift 要用logic shift, >>> 避免leading负数补位.



---
**267. [Update Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Update%20Bits.java)**      Level: Medium
      
熟悉bits的一些trick:
- ~0 = -1 = 111111...11111111 (32-bit)
- Create mask by shifting right >>>, and shifting left
- Reverse to get 0000...11110000 format mask
- & 0000 = clean up; | ABC = assign ABC



---
**268. [Maximum XOR of Two Numbers in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20XOR%20of%20Two%20Numbers%20in%20an%20Array.java)**      Level: Medium
      
比较难想到. 利用到XOR性质A^B=C, then A=B^C.
1. 枚举可能的A, 2. 然后一个个猜.

1. 枚举A: 因为求MAX肯定是找leading-1最多的数字, 那么枚举A从(1000000...000)开始, 
每次多一位取1或者0
2. 因为枚举A的时候是按照每个bit来, 那么B和C也要以同样数位出现.
这里吧B和C变成了prefix的形式, 放在了set里面. 
跟2sum用hashmap的思想类似, 每次用枚举的 A^B=C, 看看结果C是否已经在set里面. 
如果在, 证明枚举的A可能被B^C得出, 那么就找到了一种情况.

还用到一些技巧: 
mask = (1 << i); // i位mask
mask = mask | (1 << i); // prefix mask



---
**269. [Coins in a Line.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line.java)**      Level: Medium
      
如果我是先手, 每次只能拿1个,或者2个coins, 我如何赢?
只要保证对手在剩下的棋子中挑的时候'有可能败', 那就足够.
设计dp[i]:表示我面对i个coins的局面时是否能赢, 取决于我拿掉1个,或者2个时, 对手是不是会可能输?
所以:
dp[i] = !dp[i - 1] || !dp[i-2]
时间: O(n), 空间O(n)
博弈问题, 常从'我的第一步'角度分析, 因为此时局面最简单.

优化:
空间优化O(1)



---
**270. [Perfect Squares.java](https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Squares.java)**      Level: Medium
      
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
**271. [Palindrome Partitioning II.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Partitioning%20II.java)**      Level: Hard
      
Find minimum cut: 分割型DP
考虑[j, i - 1] 是否是回文串, 如果是, 那么: dp[i]= min{d[j] + 1}.

利用palindrome的性质, 可以算出 boolean palindrome[i, j]的情况. 
这样就给我们的问题合理降维, 目前是time: O(n^2). 
不然求一次palindrome, 就是n, 会变成O(n^3)

Previous Notes:
Double for loop 检查每种substring string (i~j). 若i,j相邻或者同点，那么肯定isPal；否则，i,j之间的（i+1, j-1）一定得isPal。
看上去，在检查i,j的时候，中间按的（i+1, j-1）怎么可能先知道？ 其实不然..在j慢慢长大的时候，所有的0~j的substring都检查过。所以isPal[i+1][j-1]一定是已经知道结果的。

okay.那么假如以上任意一种情况成立，也就是说isPal[i][j] == true。那就要判断，切到第一层循环参数j的末尾点时，有多少种切法？
想法很顺：我们naturally会想到，把i之前的cut加上i~j之间发生的不就好了。
反正现在j不变，现在就看吧i定在哪里，cut[i - 1]是否更小/最小； 再在cut[i-1]基础上+1就完了。
    当然，如果i==0, 而 i~j又是isPal,那没啥好谈的，不必切，0刀。

最终，刷到cut[s.length() - 1] 也就是最后一点。 return的理所应当。


---
**272. [Backpack V.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20V.java)**      Level: Medium
      
与背包1不同: 这里不是check可能性(OR)或者最多能装的size是多少; 而是计算有多少种正好fill的可能性.

对于末尾, 还是两种情况:
1. i-1位置没有加bag
2. i-1位置加了bag

两种情况可以fill满w的情况加起来, 就是我们要的结果.

如常: dp[n + 1][w + 1]

方法1:
Space: O(MN)
Time: O(MN)

方法2:
空间优化, 滚动数组
Space: O(M) * 2 = O(M)
Time: O(MN)

方法3:
降维打击, 终极优化: 分析row(i-1)的规律, 发现所有row(i)的值, 都跟row(i-1)的左边element相关, 而右边element是没用的.
所以可以被override.
Space: O(M), 真*一维啊!
Time: O(MN)



---
**273. [Backpack VI.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20VI.java)**      Level: Medium
      
拼背包时, 可以有重复item, 所以考虑'最后被放入的哪个unique item' 就没有意义了.
背包问题, 永远和weight分不开关系.
这里很像coin chagne: 考虑最后被放入的东西的value/weigth, 而不考虑是哪个.

1维: dp[w]: fill了weigth w 有多少种方法. 前面有多少种可能性, 就sum多少个:
dp[w] = sum{dp[w - nums[i]]}, i = 0~n





---
**274. [Copy Books.java](https://github.com/awangdev/LintCode/blob/master/Java/Copy%20Books.java)**      Level: Review
      
k个人copy完i本书.
定义Integer.MAX_VALUE的地方需要注意.
Review: 为什么有i level的iteration? Chapter4.1



---
**275. [Valid Perfect Square.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Perfect%20Square.java)**      Level: Review
      
Binary找sqrt. 基本 mid+1, mid-1 template.
注意: define index as long. 



---
**276. [Intersection of Two Arrays II.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Arrays%20II.java)**      Level: Easy
      
方法1:
用HashMap: 存一个nums1, 再拿nums2 check against map. 时间/空间:O(n)

方法2:
Binary search? 需要array sorted. 否则时间O(nlogn)不值得.
[没做完, 有错]



---
**277. [First Bad Version.java](https://github.com/awangdev/LintCode/blob/master/Java/First%20Bad%20Version.java)**      Level: Easy
      
Binary Search

根据isBadVersion的性质，判断还如何end=mid or start=mid.     
isBadVersion 是有方向的嘛，一个点错了，后面全错。



---
**278. [Backpack II.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20II.java)**      Level: Medium
      
做了Backpack I, 这个就如出一辙, 只不过: dp存的不是w可否存成功true/false. dp存的是加上sum value的最大值.
想法还是，选了A[i - 1] 或者没选A[i - 1]时候不同的value值.

O(m)的做法:   
想想，的确我们只care 最后一行，所以一个存value的就够了.
注意：和bakcpackI的 O(m)一样的，j是倒序的。如果没有更好的j，就不要更新。就是这个道理.

注意: 如果无法达到的w, 应该mark as impossible. 一种简单做法是mark as -1 in dp. 
如果有负数value, 就不能这样, 而是要开一个can[i][w]数组, 也就是backpack I 的原型.




---
**279. [Backpack III.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20III.java)**      Level: Review
      
可以无限使用物品, 就失去了last i, last unique item的意义: 因为可以重复使用.

1. 所以可以转换一个角度: 用i种物品, 拼出w, 并且满足题目条件(max value).
这里因为item i可以无限次使用, 所以要遍历使用了多少次K.

2. K虽然可以无限, 但是也被 k*A[i]所限制: 最大不能超过背包大小.

这样一来, 就close loop, 可以做了.

优化: Review
降维: 需要画图review
变成1个一位数组: 看双行的左右计算方向



---
**280. [Longest Palindromic Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Palindromic%20Subsequence.java)**      Level: Medium
      
区间型动态规划. 
1. 用[i][j]表示区间的首尾
2. 考虑3个情况: 砍头, 砍尾, 砍头并砍尾 (考虑首尾关系)
3. Iteration一定是以i ~ j 之间的len来看的. len = j - i + 1; 那么反推, 如果len已知, j = len + i -1;

注意考虑len == 1, len == 2是的特殊情况.



---
**281. [Burst Balloons.java](https://github.com/awangdev/LintCode/blob/master/Java/Burst%20Balloons.java)**      Level: Hard
      
Range DP.
因为数组规律会变, 所以很难找'第一个burst的球'. 反之, 想哪一个是最后burst?
最后burst的那个变成一堵墙: 分开两边, 分开考虑, 加法原理; 最后再把中间的加上.

Range DP 三把斧:
1. 中间劈开
2. 砍断首或尾
3. Range区间作为iteration的根本

Note: print the process. use pi[i][j] and print recursively.
Print k, using pi[i][j]: max value taken at k


其实会做之后挺好想的一个DP。
dp[i][j] =  balloons i~j 之间的sum. 然后找哪个点开始burst? 设为x。
For loop 所有的点作为x， 去burst。
每次burst都切成了三份：左边可以recusive 求左边剩下的部分的最大值 + 中间3项相乘 + 右边递归下去求最大值。


这个是momorization, 而不纯是DP
因为recursive了，其实还是搜索，但是memorize了求过的值，节省了Processing



---
**282. [Scramble String.java](https://github.com/awangdev/LintCode/blob/master/Java/Scramble%20String.java)**      Level: Hard
      
区间型
降维打击
dp[i][j][w]: 从i点和j点开始, 各自走w距离, 得到的S和T是否是scramble string.

具体思考过程看Thoughts.

注意: input s1, s2 在整个题目的主要内容里面, 几乎没有用到, 只是用在initialization时候.
很神奇, 这个initailization 打好了DP的基础, 后面一蹴而就, 用数学表达式就算出了结果.



---
**283. [Binary Search Tree Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Search%20Tree%20Iterator.java)**      Level: Medium
      
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
**284. [Flatten Nested List Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/Flatten%20Nested%20List%20Iterator.java)**      Level: Medium
      
方法1: 用queue, 把需要的item全部打出来
方法2: 用stack, 把需要的item先存一行, 每次打开子序列时候, 全部加回stack.



---
**285. [Coins in a Line III.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20III.java)**      Level: Hard
      
博弈 + 区间. 
S(x) = X - Y, 找最大数字差. 如果最大值都大于0, 就是赢了; 如果小于0, 就输了. 
dp[i][j]表示 从index(i) 到 index(j), 先手可以拿到的最大值与对手的数字差. 也就是S(x) = X - Y.
dp[i][j] = max{a[i] - dp[i + 1][j], a[j] - dp[i][j - 1]}

最后判断 dp[0][n] >= 0

区间型动态规划:
找出[i, j]区间内的性质.
子问题: 砍头, 砍尾, 砍头砍尾
loop应该基于区间的length
template: 考虑len = 1, len = 2; 设定i的时候一定是 i <= n - len; 设定j的时候, j = len + i - 1;

注意: 如果考虑计算先手[i, j]之间的最大值, 然后可能还需要两个数组, 最后用于比较先手和opponent的得分大小 => 那么就要多开维.
我们这里考虑的数字差, 刚好让人不需要计算先手的得分总值, 非常巧妙.



---
**286. [Best Time to Buy and Sell Stock with Cooldown.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Cooldown.java)**      Level: Medium
      
Sequence DP
跟StockIII很像. 分析好HaveStock && NoStock的状态, 然后看最后一步.



---
**287. [Find Peak Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element.java)**      Level: Medium
      
binary search. 
Goal: find peak, where both sides are descending
最左边, 最右边是Integer.MIN_VALUE时候, 也能构成中间数mid是peak的条件.



---
**288. [Longest Common Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Subsequence.java)**      Level: Medium
      
经典序列型.
设定dp长度为(n+1), 因为dp[i]要用来表示前i个(ith)时候的状态, 所以长度需要时i+1才可以在i位置, hold住i.

双序列: 两个sequence之间的关系, 都是从末尾字符看起, 分析3种情况:
1. A最后字符不在common sequence.
2. B最后字符不在common sequence.
3. A/B最后字符都在common sequence. 总体+1.



---
**289. [Interleaving String.java](https://github.com/awangdev/LintCode/blob/master/Java/Interleaving%20String.java)**      Level: Hard
      
双序列DP, 从最后点考虑.
拆分问题的末尾, 考虑和s1, s2 subsequence之间的关联.

求存在性, boolean




---
**290. [Letter Combinations of a Phone Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Letter%20Combinations%20of%20a%20Phone%20Number.java)**      Level: Medium
      
方法1: Iterative with BFS using queue.

方法2: Recursively adding chars per digit



---
**291. [Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Edit%20Distance.java)**      Level: Hard
      
两个字符串变话, 找最小值, two sequence DP.
考虑两个字符串变换的最后点: 相等, 互换, 还是缺少? 分析每种情况, 然后列出表达式.

注意, 在i或者j为0的时候, 变成另外一个数字的steps只能是全变.

第一步, 空间时间都是O(MN)
滚动数组优化, 空间O(N)



---
**292. [Distinct Subsequences.java](https://github.com/awangdev/LintCode/blob/master/Java/Distinct%20Subsequences.java)**      Level: Hard
      
Double Sequence DP:
0. DP size (n+1): 找前nth的结果, 那么dp array就需要开n+1, 因为结尾要return dp[n][m]
1. 在for loop 里面initialize dp[0][j] dp[i][0]
2. Rolling array 优化成O(N): 如果dp[i][j]在for loop里面, 就很好替换 curr/prev



---
**293. [Regular Expression Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Regular%20Expression%20Matching.java)**      Level: Review
      


---
