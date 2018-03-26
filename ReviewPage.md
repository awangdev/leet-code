# Review Page

This page summarize the solutions of all problems. For thoughts,ideas written in English, refer to deach individual solution. 
New problems will be automatically updated once added.

**0. [Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/Anagrams.java)**      Level: Medium
      

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
**1. [Binary Representation.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Representation.java)**      Level: Hard
      
首先要分两半解决，断点是'.': str.split("\\.");

Integer那一半好弄，whie loop里： num%2, num/2。

Decimal那边复杂点.
   bit == 1的数学条件：当下num * 2 >= 1。 更新: num = num * 2 - 1;
   bit == 0的数学条件： num * 2 < 1. 更新: num = num * 2

注意：num是 double, 小数在 （num = num * 2 -1）的公式下可能无限循环. 因此check: num重复性，以及binary code < 32 bit.

(所以题目也才有了32BIT的要求！)



---
**2. [Binary Tree Level Order Traversal II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Level%20Order%20Traversal%20II.java)**      Level: Medium
      
方法1:
跟Binary Tree Level Order Traversal一样,只不过存result一直存在存在0位.

方法2(略复杂, 不需要):
普通BFS，用一个queue，加上一个queue.size()来交替换行. 
或者多用一个queue来存下个level的nodes

方法3:
DFS, 根据level来append每个list
rst里面add(0,...)每次都add在list开头



---
**3. [Binary Tree Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Level%20Order%20Traversal.java)**      Level: Medium
      
方法1. 最普通,Non-recursive: BFS, queue, 用个queue.size()来end for loop:换行。   
   或者用两个queue. 当常规queue empty，把backup queue贴上去。

方法2. Recursive with dfs:   
   每个level都应该有个ArrayList. 那么用一个int level来查看：是否每一层都有了相应的ArrayList。   
   如果没有，就加上一层。    
   之后每次都通过DFS在相应的level上面加数字。




---
**4. [Binary Tree Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Longest%20Consecutive%20Sequence.java)**      Level: Medium
      
屌炸天的4行代码。Divide and Conquer

主要想法：    
Recursive用好。首先在这个level比一比，可否成。   
不成的话，另立门户, count = 1。    
然后左右开弓。再把结果拿过来比较一下就好了。



---
**5. [Binary Tree Maximum Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum%20II.java)**      Level: Medium
      
比Binary Tree Maximum Path Sum I 简单许多. 因为条件给的更多：at least 1 node + have to start from root => have to have root.

方法1:   
维持一个global或者recursive里的sum。traversal entire tree via DFS. 简单明了。


方法2:   
Single path: either left or right.   
If the path sum < 0, just skip it.   



---
**6. [Binary Tree Right Side View.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Right%20Side%20View.java)**      Level: Medium
      
最右:即level traversal每一行的最末尾.   

BFS，用queue.size()来出发saving result.



---
**7. [Binary Tree Serialization.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Serialization.java)**      Level: Medium
      
方法1: BFS. Non-recursive, using queue. 想法直观。level-order traversal. save到一个string里面就好。

方法2: DFS. Recursive. 需要一点思考。basically divide and conquer. 但是代码相对来说短。



---
**8. [Binary Tree Zigzag Level Order Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Zigzag%20Level%20Order%20Traversal.java)**      Level: Medium
      
简单的level traversal.根据level奇数偶数而add到不同位子.



---
**9. [ColorGrid.java](https://github.com/awangdev/LintCode/blob/master/Java/ColorGrid.java)**      Level: Medium
      
用HashMap， 理解题目规律，因为重复的计算可以被覆盖，所以是个优化题。

消灭重合点:       
如果process当下col, 其实要减去过去所有加过的row的交接点。。。     
再分析，就是每次碰到row 取一个单点, sumRow += xxx。       
然后process当下col时候， sum += colValue * N - sumRow. 就等于把交叉所有row（曾经Process过的row）的点减去了。很方便。

最后read in 是O(P),  process也是O(P).




---
**10. [Combination Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20II.java)**      Level: Medium
      
还是DFS. 和Combination Sum I 类似.      
确保Helper是用i+1，下一层的数字, 不允许重复。



---
**11. [Combination Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum.java)**      Level: Medium
      
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
**12. [Combinations.java](https://github.com/awangdev/LintCode/blob/master/Java/Combinations.java)**      Level: Medium
      
Combination DFS。 画个图想想. 每次从1~n里面pick一个数字i

因为下一层不能重新回去 [0~i]选，所以下一层recursive要从i+1开始选。



---
**13. [Construct Binary Tree from Inorder and Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal.java)**      Level: Medium
      
写个Inorder和Postorder的例子。利用他们分left/right subtree的规律解题。

Postorder array 的末尾， 就是当下层的root.   
在Inorder array 里面找到这个root,就刚好把左右两边分割成left/right tree。

这题比较tricky地用了一个helper做recursive。 特别要注意处理index的变化, precisely考虑开头结尾

可惜有个不可避免的O(n) find element in array.



---
**14. [Container With Most Water.java](https://github.com/awangdev/LintCode/blob/master/Java/Container%20With%20Most%20Water.java)**      Level: Medium
      
类似木桶理论。盛水的最高取决于最低的那面墙。
左右两墙，往中间跑动。
另，若一面墙已经小于另外一面，就要移动，换掉矮墙（可能下一面更高，或更低）；但决不能换掉当下的高墙，因为低墙已经limit的盛水的上限，若高墙移动，导致两墙之间距离减少，就注定水量更少了。（弄啥来，不能缺心眼啊）


---
**15. [Convert Binary Search Tree to Doubly Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Binary%20Search%20Tree%20to%20Doubly%20Linked%20List.java)**      Level: Medium
      

会iterative traverse Binary Search Tree就好（Stack && handle left-dig-down）, 然后create Doubly-ListNode 时候注意就好.

注意inorder traversal在check right node的事后，    
不论right == null or != null, 每次都要强行move to right.    

如果不node = node.right,     
很可能发生窘境：       
node alays = stack.top(), 然后stack.top()一直是一开始把left 全部遍历的内容。所以就会infinite loop, 永远在左边上下上下。      



---
**16. [Convert Expression to Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Expression%20to%20Polish%20Notation.java)**      Level: Hard
      
还是Expression Tree (Min-Tree).

根据题意，Tree出来以后，来个Pre-order-traversal.

Note: label需要是String.虽然 Operator是长度为1的char, 但是数字可为多位。



---
**17. [Convert Expression to Reverse Polish Notation.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Expression%20to%20Reverse%20Polish%20Notation.java)**      Level: Hard
      
build expression tree。

这个里面把TreeNode就当做成我们需要的node,里面扩展成有left/right child的node.

建造Expression Tree,然后根据　Reverse Polish Notation 的定义，来个post-traversal就行了。



---
**18. [Convert Integer A to Integer B.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Integer%20A%20to%20Integer%20B.java)**      Level: Easy
      
Bit Manipulation

a^b 显示出bit format里面有不同binary code的数位.

每次 (a^b)>>i 移动i位之后，   再 & 1时其实是指留下这一位的数字.

count it up



---
**19. [Copy List with Random Pointer.java](https://github.com/awangdev/LintCode/blob/master/Java/Copy%20List%20with%20Random%20Pointer.java)**      Level: Medium
      
Basic Implementation, 其中用了一下HashMap:  

遍历head.next .... null.    
每一步都check map里面有没有head。没有？加上。    
每一步都check map里面有没有head.random。没有？加上。



---
**20. [Cosine Similarity.java](https://github.com/awangdev/LintCode/blob/master/Java/Cosine%20Similarity.java)**      Level: Easy
      
basic implementation



---
**21. [Count 1 in Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%201%20in%20Binary.java)**      Level: Easy
      
1. 可以把integer -> string -> char array.

2. 或者就 count += num << i & 1



---
**22. [Count and Say.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20and%20Say.java)**      Level: Easy
      
Basic implementation. Count duplicates and print



---
**23. [Count of Smaller Number before itself.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Number%20before%20itself.java)**      Level: Hard
      
与Count of Smaller Number非常类似。以实际的value来构成segment tree，leaf上存（count of smaller number）。

Trick: 先Query，再modify.   
每次Query时候，A[i]都还没有加入到Segment Tree 里面，而A[i+1,...etc]自然也还没有加进去。   
那么就自然是coutning smaller number before itself.   
刁钻啊！   

另外注意：   
在modify里面：多Check了root.start <= index 和  index <= root.end。 过去都忽略了。以后可以把这个也写上。   
（其实是Make sense的，就是更加严格地check了index再 root.left 或者 root.right里面的站位）   



---
**24. [Count of Smaller Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20of%20Smaller%20Number.java)**      Level: Medium
      
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
**25. [Count Primes.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20Primes.java)**      Level: Easy
      
什么是prime number: >=2的没有除自己和1以外公约数的数。   

还有另外一个定义方法!!    
这个n,有没有小于n的一个i,而达到： i*i + # of i = n. 如果有，那就不是 prime。     

方法很牛逼也很数学。没做的时候可能想不到。做了之后就觉得，哎，我去，有道理啊。   
简而言之：简历一个boolean长条，存isPrime[]。 然后从i=2， 全部变true.    
然后利用这个因子的性质，非prime满足条件： self*self, self * self + self ... etc.     
所以就check每一个j, j+i, j+i+i, 然后把所有non-prime全部mark成false.     
最后，数一遍还剩下的true个数就好了   



---
**26. [Delete Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/Delete%20Digits.java)**      Level: Medium
      
数位靠前的，权值更大. 所以硬来把靠前的相对更大的（跟following digit相比）去掉。



---
**27. [Delete Node in the Middle of Singly Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Delete%20Node%20in%20the%20Middle%20of%20Singly%20Linked%20List.java)**      Level: Easy
      
Just do it. Link curr.next to curr.next.next



---
**28. [Encode and Decode Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Encode%20and%20Decode%20Strings.java)**      Level: Medium
      
方法1:    
用数字+"#"+string来encode.    
基于我们自己定的规律, 在decode的里面不需要过多地去check error input, assume所有input都是规范的.    
decode就是找"#",然后用"#"前的数字截取后面的string.



Old Solution:    
Cast character into int. 串联起来, seperate by "LINE".   
handle empty list [], or just null: 要把Null特别mark一下为‘NULL’, 这样decode时才能check到。      adminadmin




---
**29. [ExcelSheetColumnNumber .java](https://github.com/awangdev/LintCode/blob/master/Java/ExcelSheetColumnNumber%20.java)**      Level: Easy
      
'A' - 'A' = 0. 所以 char - 'A' + 1 = 题目里的对应数位。      
26位运算和10位一样嘛，num += 每位的digit * Math.pow(26, 数位号)。




---
**30. [Expression Evaluation.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Evaluation.java)**      Level: Hard
      
Build Expression Tree的另外一个变形，依然Min Tree.

build好Min Tree以后，做PostTraversal. Divde and Conquer：   
先recursively找到 left和right的大小， 然后evaluate中间的符号。

Note:
1. Handle数字时，若left&&right Child全Null,那必定是我们weight最大的数字node了。   
2. 若有个child是null,那就return另外一个node。    
3. prevent Integer overflow　during operation:过程中用个Long，最后结局在cast back to int.



---
**31. [Expression Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Tree%20Build.java)**      Level: Hard
      
和Max-tree一样，感谢http://blog.welkinlan.com/2015/06/29/max-tree-lintcode-java/

这个题目是Min-tree， 头上最小，Logic 和max-tree如出一辙   

注意treeNode,为了帮助ExpressionTreeNode 排序。它加了一个weight based on expression，协助build Min-Tree 排序。

Space: O(n) 
Time on average: O(n).



---
**32. [Fast Power.java](https://github.com/awangdev/LintCode/blob/master/Java/Fast%20Power.java)**      Level: Medium
      
a^n可以被拆解成(a*a*a*a....*a)， 是乘机形式，而%是可以把每一项都mod一下的。所以就拆开来take mod.

这里用个二分的方法，recursively二分下去，直到n/2为0或者1，然后分别对待. 

注意1: 二分后要conquer，乘积可能大于Integer.MAX_VALUE, 所以用个long.

注意2: 要处理n%2==1的情况，二分时候自动省掉了一份，要乘一下。




---
**33. [Fibonacci.java](https://github.com/awangdev/LintCode/blob/master/Java/Fibonacci.java)**      Level: Easy
      
方法1: DP array.

方法1.1: 滚动数组, 简化DP。

方法2: recursively calculate fib(n - 1) + fib(n - 2). 公式没问题, 但是时间太长, timeout.




---
**34. [Find the Connected Component in the Undirected Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Connected%20Component%20in%20the%20Undirected%20Graph.java)**      Level: Medium
      
BFS遍历，把每个node的neighbor都加进来。    

一定注意要把visit过的node Mark一下。因为curr node也会是别人的neighbor，会无限循环。      

Component的定义：所有Component内的node必须被串联起来via path (反正这里是undirected, 只要链接上就好)     

这道题：其实component在input里面都已经给好了，所有能一口气visit到的，全部加进queue里面，他们就是一个component里面的了。     

而我们这里不需要判断他们是不是Component。   



---
**35. [Find the Weak Connected Component in the Directed Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Weak%20Connected%20Component%20in%20the%20Directed%20Graph.java)**      Level: Medium
      
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
**36. [Flatten 2D Vector.java](https://github.com/awangdev/LintCode/blob/master/Java/Flatten%202D%20Vector.java)**      Level: Medium
      
大概意思就是把2D list里面的element全部遍历一遍。
注意啊，一开始理解题意搞错：我以为是必须要排序正确，所以上来就PriorityQueue+HashMap搞得无比复杂。其实，这个跟一个nxn的matrix遍历，是没区别的拉。
所有来个x,y，把2d list跑一变。



---
**37. [Flip Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Flip%20Game%20II.java)**      Level: Medium
      
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
**38. [Flip Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Flip%20Game.java)**      Level: Easy
      
这个题目是很寂寞的. 2 pointer可以做, 在网上又搜了一下，貌似可以有很多牛逼的优化，我暂时还没去看。
很郁闷的就是条件不明，原来只需要从'++'转到'--'的情况，反过来没必要关注...搞了我半天啊


---
**39. [Fraction to Recurring Decimal.java](https://github.com/awangdev/LintCode/blob/master/Java/Fraction%20to%20Recurring%20Decimal.java)**      Level: Medium
      
不难想到处理除法：考虑正负，考虑小数点前后。主要是小数点以后的要着重考虑。
很容易忽略的是integer的益处。


---
**40. [Generate Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Generate%20Parentheses.java)**      Level: Medium
      
递归。
看thought.取或者不取(,  )

Note: 在DFS时, 可以pass object (String) and re-create every time; or pass a reference (StringBuffer) and maintain it



---
**41. [Gray Code.java](https://github.com/awangdev/LintCode/blob/master/Java/Gray%20Code.java)**      Level: Medium
      
题目蛋疼，目前只接受一种结果。

BackTracking + DFS:   
   Recursive helper里每次flip一个 自己/左边/右边. Flip过后还要恢复原样.遍历所有.

曾用法（未仔细验证）：   
基本想法就是从一个点开始往一个方向走，每次flip一个bit, 碰壁的时候就回头走。



---
**42. [Group Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/Group%20Anagrams.java)**      Level: Medium
      
方法一: 60%

和check anagram 想法一样：转化并sort char array，用来作为key。

把所有anagram 存在一起。注意结尾Collections.sort().

O(NKlog(K)), N = string[] length, k = longest word length    


优化：80% ~ 97%

用固定长度的char[26] arr 存每个字母的frequency; 然后再 new string(arr).   
因为每个位子上的frequency的变化，就能构建一个unique的string


错误的示范: 尝试先sort input strs[]，但是NlogN 其实效率更低. 13%




---
**43. [Group Shifted Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Group%20Shifted%20Strings.java)**      Level: Easy
      
相同shift规则的string, 能被推算到同一个零起始点，就是共同减去一个char,最后就相等。以此作为key，用HashMap。一目了然。

记得根据题目意思，一开始要String[] sort一下。



---
**44. [H-Index II.java](https://github.com/awangdev/LintCode/blob/master/Java/H-Index%20II.java)**      Level: Medium
      
H-index的一个优化。
binary search


---
**45. [H-Index.java](https://github.com/awangdev/LintCode/blob/master/Java/H-Index.java)**      Level: Medium
      
例子写出来，发现可以sort以后按照定义搜索一遍。 nlogn.
	当然，搜索一遍时候可以优化，用binary search. 但是没意义，因为array.sort已经用了nlogn

o(n)也可以，用bucket. 比较巧妙。




---
**46. [Hamming Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Hamming%20Distance.java)**      Level: Easy
      
bit: XOR, &, shift>>



---
**47. [Happy Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Happy%20Number.java)**      Level: Easy
      
Basic Implementation of the requirements.

用HashSet存查看过的数值。若重复，return false.



---
**48. [Hash Function.java](https://github.com/awangdev/LintCode/blob/master/Java/Hash%20Function.java)**      Level: Easy
      
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
**49. [HashHeap.java](https://github.com/awangdev/LintCode/blob/master/Java/HashHeap.java)**      Level: Hard
      
非题.是从九章找来的HashHeap implementation.



---
**50. [HashWithArray.java](https://github.com/awangdev/LintCode/blob/master/Java/HashWithArray.java)**      Level: Easy
      



---
**51. [HashWithCustomizedClass(LinkedList).java](https://github.com/awangdev/LintCode/blob/master/Java/HashWithCustomizedClass(LinkedList).java)**      Level: Medium
      
练习HashMap with customized class. 



---
**52. [Heapify.java](https://github.com/awangdev/LintCode/blob/master/Java/Heapify.java)**      Level: Medium
      
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
**53. [Heaters.java](https://github.com/awangdev/LintCode/blob/master/Java/Heaters.java)**      Level: Easy
      
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
**54. [Identical Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Identical%20Binary%20Tree.java)**      Level: Easy
      
Divide, && 每种情况（左右一一对应)    
注意 null states




---
**55. [Implement Stack by Two Queues.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack%20by%20Two%20Queues.java)**      Level: Easy
      
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
**56. [Implement Stack using Queues.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack%20using%20Queues.java)**      Level: Easy
      
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
**57. [Implement Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack.java)**      Level: Easy
      
stack 后入先出. 
Data Structure: ArrayList 
return/remove ArrayList的末尾项。



---
**58. [Implement Trie (Prefix Tree).java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Trie%20(Prefix%20Tree).java)**      Level: Medium
      
Trie自己不多用到。
如果是遇到一个一个字查询的题，可以考虑一下。
构建TrieNode的时候要注意：如何找孩子？如果是个map的话，其实就挺好走位的。
而且，每个node里面的 char 或者string有时候用处不大，
可以为空。但是有些题目，比如在结尾要return一些什么String，就可以在end string那边存一个真的String。




---
**59. [IndexMatch.java](https://github.com/awangdev/LintCode/blob/master/Java/IndexMatch.java)**      Level: Easy
      
有序, 假设有这样的数字:target.        
target 左边的数字，一定不比index大，target右边的数字，一定比index大。     
这样可以binary search.O(logn)



---
**60. [Inorder Successor in Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Inorder%20Successor%20in%20Binary%20Search%20Tree.java)**      Level: Medium
      

画inorder图，发现规律.每个node的后继node(successor)有几种情况:   
1. node.right 是个leaf到底了。那么就return.   
2. set rightNode = node.right， 但发现rightNode has a lot left children to leaf.   
3. 比如, node.right == null， 也就是node自己是leaf，要回头看山顶找Inorder traversal规则里的下一个。   
	发现：其实就是每层都把路过的curr node放在stack里，最上面的，就是当下改return的那个successor:) Done.



---
**61. [Insert Interval.java](https://github.com/awangdev/LintCode/blob/master/Java/Insert%20Interval.java)**      Level: Easy
      

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
**62. [Insert Node in a Binary Search Tree .java](https://github.com/awangdev/LintCode/blob/master/Java/Insert%20Node%20in%20a%20Binary%20Search%20Tree%20.java)**      Level: Easy
      

往Binary Search Tree里面加东西，一定会找到一个合适的leaf加上去。

那么：就是说someNode.left or someNode.right是null时，就是insert node的地方。

找到那个someNode就按照正常的Binary Search Tree规律。



---
**63. [Intersection of Two Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Arrays.java)**      Level: Easy
      
方法1: 用到hashset找unique && duplicate: O(m+n)
方法2: 可以用binary search 找数字. Note:binary search一定需要array sorted: nLog(m)



---
**64. [Intersection of Two Linked Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Linked%20Lists.java)**      Level: Easy
      
长短list，找重合点。
长度不同的话，切掉长的list那个的extra length。 那么起点一样后，重合点就会同时到达。




---
**65. [Interval Minimum Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Minimum%20Number.java)**      Level: Medium
      
SegtmentTree, methods: Build, Query. 这题是在SegmentTreeNode里面存min.

类似的有存:max, sum, min





---
**66. [Interval Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Sum%20II.java)**      Level: Hard
      
SegmentTree大集合。记得几个Methods: Build, Query, Modify. 不难。只是要都记得不犯错:)



---
**67. [Interval Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Interval%20Sum.java)**      Level: Medium
      
其实是segment tree 每个node上面加个sum。   

记得Segment Tree methods: Build, Query

Note: 存在SegmentTreeNode里面的是sum.  其他题目可能是min,max ... or something else.




---
**68. [Invert Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Invert%20Binary%20Tree.java)**      Level: Easy
      
non-recursive: BFS with queue。 或者regular recurisve - divide and conquer.



---
**69. [Isomorphic Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Isomorphic%20Strings.java)**      Level: Easy
      
HashMap 来确认match。有几种情况考虑:

1. Match. 就是map.containsKey, map.containsValue, and char1 == char2. Perfect.

2. Either Key not exist, or Value not exit. False;

3. Both key and Value exist, but map.get(char1) != char2.  Miss-match. False.

4. None of Key or Value exist in HashMap. Then add the match.



---
**70. [Jump Game II.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game%20II.java)**      Level: Hard
      
Greedy, 图解 http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html

维护一个range, 是最远我们能走的. 

index/i 是一步一步往前, 每次当 i <= range, 做一个while loop， 在其中找最远能到的地方 maxRange

然后更新 range = maxRange

其中step也是跟index是一样, 一步一步走.

最后check的condition是，我们最远你能走的range >= nums.length - 1, 说明以最少的Step就到达了重点。Good.




---
**71. [Kth Largest Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element.java)**      Level: Medium
      
用Quick Sort 里面partion的一部分。     
partion的结果是那个low, 去找 low==nums.size() - k， 也就是倒数第K个。    
没找到继续partion recursively.

sort的过程是排一个从小到大的list. (同样的代码还可以好xth smallest，mid变成x就好)

Quick Sort:   
每个iteration, 找一个pivot,然后从low,和high都和pivot作比较。    
找到一个low>pivot, high<pivot, 也就可以swap了。    
得到的low就是当下的partion point了




---
**72. [Kth Smallest Element in a BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Element%20in%20a%20BST.java)**      Level: Medium
      

很容想到Inorder-binary-search-tree Traversal
Recursive 不难，然后稍微优化一下，确保rst.size() == k 时候，就可以return了。
Iterative 稍微难想点：先把最左边的add， pop() stack， 加上右边（如果存在）； 下一个轮回，如果又左孩子，又是一顿加。



---
**73. [Kth Smallest Sum In Two Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Sum%20In%20Two%20Sorted%20Arrays.java)**      Level: Hard
      

用priority queue. 每次把最小的展开，移位。分别x+1,或者y+1:   
因为当下的Min里面x,y都是最小的。所以下一个最小的不是（x+1,y）,就是（x,y+1）。

每次就poll（）一个，放2个新candidate进去就好了。
注意，这样的做法会用重复，比如例子（7,4）会出现两次。用一个HashSet挡一下。

注意，HashSet的唯一性，用一个"x,y"的string就可以代为解决。



---
**74. [Longest Common Prefix.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Prefix.java)**      Level: Medium
      
Nested loop, 每一次比较所有string 同位是否相等。

相等，append string. 不等，return.



---
**75. [Longest Increasing Continuous subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence.java)**      Level: Easy
      
O(n)跑2遍for.
O(1)是用了两个int来存：每次到i点时，i点满足条件或不满足条件所有的longestIncreasingContinuousSubsequence.
特点：返跑一回，ans还是继续和left轮的ans作比较；求的所有情况的最大值嘛。


---
**76. [Longest Palindromic Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Palindromic%20Substring.java)**      Level: Medium
      
方法1: 从中间劈开. 遍历i，从n个不同的点劈开：每次劈开都看是否可以从劈开出作为palindromic的中点延伸。   
   Worst case: 整个string都是相同字符，time complexity变成： 1 + 2 +３　＋　．．．　＋n = O(n^2)

方法2: 穷举double for loop. O(n^2)




---
**77. [Longest Univalue Path.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Univalue%20Path.java)**      Level: Easy
      
弄明白path的意义: 连接node的edge.
要找MAX, 可以在class scope里面定义一个max variable.

用minimum amount of code来概括几种不同的情况: left == root, right == root, or left == root == right.



---
**78. [Longest Word in Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Word%20in%20Dictionary.java)**      Level: Easy
      
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
**79. [Lowest Common Ancestor II.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor%20II.java)**      Level: Easy
      
这个题有个奇葩的地方，每个node还有一个parent。 所以可以自底向上.

1. 曾经做的hashset的优化，找到的都存hashset. exist就return那个duplicate.


2. 普通做法：2 lists。
   自底向上。利用parent往root方向返回。   

注意：无法从root去直接搜target node 而做成两个list. 因为根本不是Binary Search Tree！




---
**80. [Lowest Common Ancestor of a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor%20of%20a%20Binary%20Search%20Tree.java)**      Level: Medium
      
方法1: 利用 BST的性质，可以直接搜到target node，而做成两个长度不一定相等的list。然后很简单找到LCA 
方法2: Brutly寻找p和q的common ancestor, 然后recursively drive left/right. 非常巧妙, 但是也比较局限; 稍微变条件, 就很难recursive.



---
**81. [Lowest Common Ancestor.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor.java)**      Level: Easy
      
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
**82. [LRU Cache.java](https://github.com/awangdev/LintCode/blob/master/Java/LRU%20Cache.java)**      Level: Hard
      

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
**83. [Majority Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Number%20II.java)**      Level: Medium
      
分三份：a b c考虑。若a, countA++, 或b, countB++，或c，countA--,countB--.

最后出现的两个count>0的a和b,自然是potentially大于1/3的。其中有一个大于1/3.

比较a和b哪个大，就return哪一个。



---
**84. [Majority Number III.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Number%20III.java)**      Level: Medium
      
与其他Majority Number一样。

出现次数多余1/k，就要分成k份count occurance.用HashMap。 存在的+1；不存在map里的，分情况:    
若map.size() == k,说明candidate都满了，要在map里把所有现存的都-1；
若map.size() < k, 说明该加新candidate，那么map.put(xxx, 1);

最后在HashMap里找出所留下的occurance最大的那个数。



---
**85. [Matrix Zigzag Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Matrix%20Zigzag%20Traversal.java)**      Level: Easy
      
分析4个step:right, left-bottom,down,right-up    
implement时注意index.有点耐心



---
**86. [Max Area of Island.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Area%20of%20Island.java)**      Level: Easy
      
虽然Easy, 但用到DFS最基本的想法.
1. dive deep
2. mark VISITED
3. sum it up

更要注意, 要从符合条件value==1的地方开始dfs.
对于什么island都没有的情况，area应该位0， 而不是Integer.MIN_VALUE, 问清楚考官那小伙, 别写顺手。



---
**87. [Maximum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy
      
DFS: Divide and conquer. 维持一个最大值。



---
**88. [Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray.java)**      Level: Easy
      
方法1
比较像DP, 维持一个sums[i]: 从i向前位数, 所有正数的和. 一旦sums[i - 1]<0, 意味着sums[i-1]对maxSum没有好处,
那么就assign: sums[i]=nums[i]
这个做法比较中规中矩, makes sense

方法2(better)
想着用一用prefix sum. 把值一个个叠加。
然后presum[j] - presum[i- 1] 就是 (i,j)之间的和。



---
**89. [Median of two Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Median%20of%20two%20Sorted%20Arrays.java)**      Level: Hard
      
Not done



---
**90. [Merge Intervals.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Intervals.java)**      Level: Easy
      
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
**91. [Merge k Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20k%20Sorted%20Arrays.java)**      Level: Medium
      
由Merge k sorted list启发。用PriorityQueue,存那k个首发element。

PriorityQueue需要存储单位。自己建一个Class Node 存val, x,y index.    
因为array里没有 'next' pointer，只能存x,y来推next element



---
**92. [Merge k Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20k%20Sorted%20Lists.java)**      Level: Medium
      
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
**93. [Merge Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Sorted%20Array.java)**      Level: Easy
      
A够长，那么可以从A的尾部开始加新元素。     
注意，从尾部，是大数字优先排末尾的.  



---
**94. [Merge Two Binary Trees.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Two%20Binary%20Trees.java)**      Level: Easy
      
基础binary tree traversal. 注意对null child的判断



---
**95. [Merge Two Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Two%20Sorted%20Lists.java)**      Level: Easy
      
小的放前。每次比head大小。   
while过后，把没完的list一口气接上。   

一开始建一个node用来跑路, 每次都存node.next = xxx。存一个dummy。用来return dummy.next.



---
**96. [Minimum Absolute Difference in BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Absolute%20Difference%20in%20BST.java)**      Level: Easy
      

BST: inorder-traversal: 先left node(adding to stack till left leav), 再process stack.peek (mid node), 再 add rightNode && dive to rightNode.left leaf



---
**97. [MinimumDepthOfBinaryTree.java](https://github.com/awangdev/LintCode/blob/master/Java/MinimumDepthOfBinaryTree.java)**      Level: Easy
      
Divide and Conquery一个最小值. 注意处理Leaf的null, 用Integer.MAX_VALUE代替，这样可以避免错误counting.



---
**98. [Multiply Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Multiply%20Strings.java)**      Level: Medium
      
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
**99. [Next Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/Next%20Permutation.java)**      Level: Medium
      
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
**100. [One Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/One%20Edit%20Distance.java)**      Level: Medium
      
理解Edit: 就是删除，增加，和替换。    
换完之后，理论上换成的String 就应该全等             
一旦找到不一样的char, 就判断那三种可能性      



---
**101. [Palindrome Permutation II.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Permutation%20II.java)**      Level: Medium
      
permutation的综合题：    
1. validate Input 是不是可以做palindromic permutation. 这个就是（Palindrome Permutation I）   
2. 顺便存一下permutation string的前半部分和中间的single character(if any)    
3. DFS 做unique permutation: given input有duplicate characters。       



---
**102. [Palindrome Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Permutation.java)**      Level: Easy
      
注意，条件里面没说是否全是lower case letter



---
**103. [Pascal's Triangle II.java](https://github.com/awangdev/LintCode/blob/master/Java/Pascal's%20Triangle%20II.java)**      Level: Easy
      
简单处理array list.



---
**104. [Peeking Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/Peeking%20Iterator.java)**      Level: Medium
      

再一次理解错题意. peek() 就是头顶，但是不一定是最大值啊。总是把PEEK想成了最大值，然后用2 STACK做了最大值的cache，练的一手好双stack，可惜错了。

回到原题，其实不难。找一个cache来存next()的值，然后每次next()里面维护这个cache就好。



---
**105. [Permutation Index.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutation%20Index.java)**      Level: Easy
      
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
**106. [Permutation Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutation%20Sequence.java)**      Level: Medium
      
k是permutation的一个数位。而permutation是有规律的。

也就是说，可以根据k的大小来判断每一个数位的字符（从最大数位开始，因为默认factorio从最大数位开始变化）。

于是先求出n!， 然后 k/n!就可以推算出当下这一个数位的字符。然后分别把factorio 和 k减小。

另外, 用一个boolean[] visited来确保每个数字只出现一次。

这个方法比计算出每个permutation要efficient许多。




---
**107. [Permutations II.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutations%20II.java)**      Level: Medium
      
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
**108. [Permutations.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutations.java)**      Level: Medium
      
Recursive Backtracking: 取，或者不取.
Improvement: maintain list (add/remove elements) instead of 'list.contains'

Iterative: 用个queue，每次poll()出来的list, 把在nums里面能加的挨个加一遍。 However, code is a bit massive.




---
**109. [Populating Next Right Pointers in Each Node II.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node%20II.java)**      Level: Hard
      
非perfect tree, 也就是有random的null children. DFS＋BFS


Populating Next Right Pointers in Each Node I 里面依赖parent.next.left来作链接，但现在这个parent.next.left很可能也是Null.

1. 于是需要移动parent去找children level的next node。    
2. 并且每次在一个level, 要用BFS的思想把所有parent 过一遍，也就是把parent 正下方的children全部用.next链接起来    
    原因: 到下一层children变成parent, 他们需要彼此之间的connection, grand children才可以相互连接。


Note: runtime O(n * 2^log(n) ) = O(n^2), not good.



---
**110. [QuickSort.java](https://github.com/awangdev/LintCode/blob/master/Java/QuickSort.java)**      Level: Easy
      
代码是不难的. 

首先partition. 返还一个partition的那个中间点的位置。
然后劈开两半。
前后各自 quick sort, recursively

注意：在partition里面, 比较的时候nums[start] < pivot, nums[end]>pivot, 如果写成了 <= 会 stack overflow.


但是： 在partition array那个题目里面，第二个 nums[end] >= pivot, 是要去加上这个 ‘=’的



---
**111. [Rehashing.java](https://github.com/awangdev/LintCode/blob/master/Java/Rehashing.java)**      Level: Medium
      



---
**112. [Remove Duplicates from Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicates%20from%20Sorted%20Array.java)**      Level: Easy
      
Remove Duplicate from Array 不同于remove from linked list.

LinkedList里面我们是最好不要动node.val的，直接把node去掉。
而array我们很难直接把node去掉，又不能用新array，那么就要：

把不重复的element一个个放到最前面。


这个思想跟merge two sorted array （其中一个后续非常长的array可以放下arr1,arr2） 类似。
就是找个不会事后mess up，不会去动得index,把满足条件的element 填进去。这样保证了in place.

* 有个反向思维：remove duplicate,实际上也是找unique elements, and insert into original array



---
**113. [Remove Duplicates from Sorted List.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicates%20from%20Sorted%20List.java)**      Level: Easy
      
一旦node.next 和node是重复，跳




---
**114. [Remove Invalid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Invalid%20Parentheses.java)**      Level: Hard
      
NOT DONE



---
**115. [Remove Node in Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Node%20in%20Binary%20Search%20Tree.java)**      Level: Hard
      

方法1: Brutle一点。找到target和target的parent.    
把target remove时，把target的children nodes 重新排列组成新的BST: inorder traversal, build tree based on inorder traversal list.

方法2: 分析规律,先找到target和parent, 然后根据性质，把target remove时，移动children nodes, 保证还是BST。



---
**116. [Reshape the Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Reshape%20the%20Matrix.java)**      Level: Easy
      
读例子理解题意.
理清counter case. Basic implementation



---
**117. [Reverse Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Linked%20List.java)**      Level: Easy
      
建立新list。每次把newList append 在current node的后面。   
用head来循环所有node。



---
**118. [Reverse String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20String.java)**      Level: Easy
      
Similar to Reverse Integer.
可以用StringBuffer, 也可以two pointer reverse head/tail



---
**119. [Reverse Words in a String II.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Words%20in%20a%20String%20II.java)**      Level: Medium
      
In-place reverse.

reverse用两回. 全局reverse。局部:遇到空格reverse。

注意：结尾点即使没有' '也要给reverse一下最后一个词。




---
**120. [Reverse Words in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Words%20in%20a%20String.java)**      Level: Medium
      
几种不同的方法flip：   
坑： 1. 结尾不能有空格。 2. 注意，如果Input是 ‘ ’的话，split以后就啥也没有了。check split以后 length == 0

另个题目Reverse Words in String (char[]) 可以in-place，因为条件说char[]里面是没有首尾空格,好做许多哟.



---
**121. [Roman to Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/Roman%20to%20Integer.java)**      Level: Easy
      
熟悉罗马字母规则     
1. 'I V X L C D M' 分别代表的数字     
2. 列举combo的情况，需要从原sum里面减掉多加的部分： 'IV, IX'减2, 'XL, XC'减20, 'CD, CM'减200. 

https://en.wikipedia.org/wiki/Roman_numerals



---
**122. [Rotate Image.java](https://github.com/awangdev/LintCode/blob/master/Java/Rotate%20Image.java)**      Level: Medium
      
找到个转角度的规律公式。用一个temp。in place.



---
**123. [Search Range in Binary Search Tree .java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20Range%20in%20Binary%20Search%20Tree%20.java)**      Level: Medium
      

等于遍历了所有k1<= x <= k2的x node。

如果是用Binary Search Tree搜索，那么一般是if (...) else {...}，也就是一条路走到底，直到找到target.

这里, 把 left/right/match的情况全部cover了，然后把k1,k2的边框限制好，中间就全部遍历了。



---
**124. [Search Rotated in Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20Rotated%20in%20Sorted%20Array.java)**      Level: Hard
      
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
**125. [Segment Tree Build II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Build%20II.java)**      Level: Medium
      

给的是Array。注意找区间内的max, assign给区间。   其余和普通的segment tree build一样   

给了array,但是并不根据array里的内容排位，而是依然根据index in [0, array.length - 1]割开区间，break到底，   
最终start==end。同时assign max=A[start] or A[end]

往上,parent一层的max:就是比较左右孩子,其实都是在两个sub-tree里面比较sub-tree的max。   

这就好做了：   
先分，找到left/right，比较max,在create current node,再append到当前node上面。

实际上是depth-first, 自底向上建立起的。



---
**126. [Segment Tree Build.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Build.java)**      Level: Medium
      
按定义：   
左孩子：（A.left, (A.left+A.rigth)/2）   
右孩子：（(A.left+A.rigth)/2＋1， A.right）   



---
**127. [Segment Tree Modify.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Modify.java)**      Level: Medium
      
Recursively 在segment tree里面找index, update it with value.   

每个iteration，很可能（要么左手，要么右手）max就变了。所以每次都left.max and right.max compare一下。   
最后轮回到头顶，头顶一下包括头顶，就全部都是max了。   

Divde and Conquer



---
**128. [Segment Tree Query II.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query%20II.java)**      Level: Medium
      
和 Segment Tree Query I 以及其他Segment Tree问题没啥区别。这个就是return个count。

这个题目考了validate input source：input 的start,end可能超出root[start,end]。   
那么第一步就要先clear一下。完全不在range就return 0. 有range重合就规整到root的range.





---
**129. [Segment Tree Query.java](https://github.com/awangdev/LintCode/blob/master/Java/Segment%20Tree%20Query.java)**      Level: Medium
      
给了segment Tree, node里面有Max value, 找[start,end]里面的max

[start,end]跟mid相比，可能：   
全在mid左   
全在mid右   
包含了mid： 这里要特别break into 2 query method   

按定义：   
mid = (root.start + root.end)/2



---
**130. [Shortest Word Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Shortest%20Word%20Distance.java)**      Level: Easy
      
找short distance, wordB可以在wordA的前后；而同一时间，只需要计算一个最近的up to date的distance。
greedy不断变更A/B index再做比较即可。



---
**131. [Single Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Single%20Number.java)**      Level: Easy
      
Bit XOR: 当两个bit不同时，return 1. 
题目正要消光所有重复出现的数儿留下出现一次的那个.



---
**132. [String Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/String%20Permutation.java)**      Level: Easy
      
把#of occurrences 存进HashMap, 第一个string 做加法，第二个string做减法。最后看是否有不等于0的作判断。



---
**133. [String to Integer(atoi).java](https://github.com/awangdev/LintCode/blob/master/Java/String%20to%20Integer(atoi).java)**      Level: Easy
      
方法1: 问清情况，一点一点把case都涉及到。

方法2: 用regular expression。if (!str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")).  猛了一点



---
**134. [Strobogrammatic Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Strobogrammatic%20Number%20II.java)**      Level: Medium
      
耗了一点时间。本以为需要DP一下，把做过的n存一下。后来发现，其实就是剥皮，一层一层，是一个central-depth-first的，钻到底时候，return n=1,或者n=2的case，然后开始backtracking。
难的case先不handle.到底之后来一次O(n) scan.
总共的时间起码是O(n/2) + O(n), 所以还是O(n)


---
**135. [Strobogrammatic Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Strobogrammatic%20Number.java)**      Level: Easy
      
根据题意枚举, 再根据规则basic implementation



---
**136. [Subarray Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20Closest.java)**      Level: Medium
      
?



---
**137. [Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum.java)**      Level: Easy
      
分析出，如果sum[0~a]=x, 然后sum[0~b]=x, 说明sum(a ~ b] = 0.    

    这样理解后，用hashMap存每个sum[0~i]的值和index i. 如果有重复，就找到了一组sum为0的数组。



---
**138. [Subset.java](https://github.com/awangdev/LintCode/blob/master/Java/Subset.java)**      Level: Medium
      
最基本的递归题目。   
坑：记得一开头sort一下 nums。 因为要升序。那么整体就是O(nlogn)

注意：用level/index来track到哪一步。最后一level就add into rst

方法1: subset的概念，取或者不取,backtracking. 当level/index到底，return 一个list.

方法2: 用for loop backtracking. 记得：每个dfs recursive call是一种独特可能，先加进rst。


recap:时间久了忘记dfs的两种路子. for loop dfs/backtracking vs. regular dfs



---
**139. [Subsets II.java](https://github.com/awangdev/LintCode/blob/master/Java/Subsets%20II.java)**      Level: Medium
      
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
**140. [Subtree.java](https://github.com/awangdev/LintCode/blob/master/Java/Subtree.java)**      Level: Easy
      
找到potential subtree, 比较Children.

一点注意：即使找到T1 == T2, 但很可能只是数字相同（这里不是binary search tree!!），而children不同。所以同时要继续recursively isSubtree(T1.left, T2) ...etc.



---
**141. [Symmetric Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Symmetric%20Binary%20Tree.java)**      Level: Easy
      
Symmetric Tree

注意Symmetric Binary Tree的例子和定义: 是镜面一样的对称. 并不是说左右两个sub-tree相等。

方法1: Recursively check symmetrically相对应的Node.  每个node的children都和镜面另外一边相对的node的children刚好成镜面反射位置。

方法2: 用stack. 左手边sub-tree先加left,再加right child; 右手边sub-tree先加right child, 再加left child。   
process时，若symmetric，所有stack里面出来的node会一一对应。



---
**142. [Top K Frequent Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Elements.java)**      Level: Medium
      
题目有提醒: 不能用O(nLog(n)) 也就是说明要Log(n): 首先想到就是PriorityQueue, 并且不能queue.offer on the fly.
那么就先count, O(n); 再priorityQueue, Log(k), k是unique 数字的总量. 



---
**143. [Top K Frequent Words.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Words.java)**      Level: Medium
      
方法1：Brutle force用HashMap存frequency, 用ArrayList存lists of words。最后返回从尾部向前数的k个。   
   注意排序时Collection.sort()的cost是O(nLogk)

方法1-1: 还是用HashMap,但create一个Node class, 然后用PriorityQueue.   
PriorityQueue里面用到了 String.compareTo(another String).巧妙。

方法2: Trie && MinHeap屌炸天   
   http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/



---
**144. [Topological Sorting.java](https://github.com/awangdev/LintCode/blob/master/Java/Topological%20Sorting.java)**      Level: Medium
      
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
**145. [Tweaked Identical Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Tweaked%20Identical%20Binary%20Tree.java)**      Level: Easy
      
Recursive 比对左左,左右,右左，右右



---
**146. [Two Strings Are Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Strings%20Are%20Anagrams.java)**      Level: Easy
      
方法1:char ascii 用count[256]   
坑：不要想象这个是个26letter lowercase. may not be true.

方法2: 若是其他字符encoding, 而不只是utf16-encoding (java char)?   
那么就继续用string去做



---
**147. [Ugly Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Ugly%20Number.java)**      Level: Medium
      
方法1: PriorityQueue排序。用ArrayList check 新的ugly Number是否出现过。

方法1-1：(解释不通，不可取)用PriorityQueue排序。神奇的3，5，7走位：按照题目答案的出发，定了3，5，7以什么规律出现。但是题目并没有特殊表明。

方法2: DP . Not Done yet.




---
**148. [Unique Binary Search Tree II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree%20II.java)**      Level: Medium
      



---
**149. [Valid Palindrome.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Palindrome.java)**      Level: Easy
      
tutorial:https://www.youtube.com/watch?v=2hNK0Yz53LQ&list=PLZn-UvluQZuNedn1hDzTmNLE8MQWXjKVb

过滤alphanumeric，其他字母掠过



---
**150. [Valid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Parentheses.java)**      Level: Easy
      
剥皮过程。解铃还须系铃人   
左边的外皮'{['在stack底部   
右边的外皮应该和stack顶上的左外皮一一对应 





---
**151. [Valid Sudoku.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Sudoku.java)**      Level: Easy
      
用HashSet存visited value.

方法1: 在nest for loop里面validate row,col,and block.     
validate block要利用i 和 j 增长的规律。    
说白了，i && j是按照0~n增长的index, 具体怎么用是可以flexible的。这个方法在同一个nest for loop解决所有运算。

方法2: 单独做block validation: validate block的时候虽然看到了4层for.其实也就是n^2.



---
**152. [Word Break.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Break.java)**      Level: Medium
      
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
**153. [Word Ladder II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Ladder%20II.java)**      Level: Hard
      


---
**154. [Word Ladder.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Ladder.java)**      Level: Medium
      
BFS Brutle: 在start string基础上，string的每个字母都遍历所有26个字母，换换。

方法2:    
用Trie。 理应更快. However implementation可能有点重复计算的地方，LeetCode timeout. 需要再做做。



---
**155. [Word Pattern.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Pattern.java)**      Level: Easy
      
每个char代表一个pattern。用HashMap<char, str>.
但不够，如果a也match dog, b也match dog, 纠错了。比如pattern = "abba", str = "dog dog dog dog"。
因此第二个HashMap<str, char> 反过来。
确保pattern和str一一对应。



---
**156. [Zigzag Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/Zigzag%20Iterator.java)**      Level: Medium
      

这个题目相对简单. 做的时候我先考虑起来k条怎么办. 那么用个map把index和每个listmark一下就好了。
每次next(), 相应的list的头拿下来就好。
然后就跑圈呗，每次刷一个list头。不难。只要把几个variable维护清楚就行。


---
**157. [Find Anagram Mappings.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Anagram%20Mappings.java)**      Level: Easy
      

比较简单, 用HashMap 存index list. 最后再遍历一遍数组A, 列举出所有元素.
O(n)



---
**158. [Judge Route Circle.java](https://github.com/awangdev/LintCode/blob/master/Java/Judge%20Route%20Circle.java)**      Level: Easy
      

简单的character checking. 各个方向, 加加减减.



---
**159. [Island Perimeter.java](https://github.com/awangdev/LintCode/blob/master/Java/Island%20Perimeter.java)**      Level: Easy
      

最简单的方法: 每个格子4个墙;每个shared的墙要-2 (墙是两面, -1 * 2)
最后合计结果就好.

不必想太多用HashMap做.但是也可以思考一下:
- 把每个block相连的block全部存在以当下block为key的list里面. 那么这里需要把2D坐标, 转化成一个index.
- 最后得到的map, 所有的key-value应该都有value-key的反向mapping, 那么久可以消除干净, 每一次消除就是一个shared wall.
- 一点点optimization: DFS去找所有的island, 如果island的图非常大, 而island本身不打,那么适合optimize.
  但是整体代码过于复杂. 不建议写.




---
**160. [First Unique Character in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/First%20Unique%20Character%20in%20a%20String.java)**      Level: Easy
      

方法1: 按照题意, 找到第一个 first index == last index的字母.

方法2: 用hashmap存字母的index, 有些重复字母的index就会是个list. 找到单一index, 结合成list, sort, return list.get(0)



---
**161. [Power of Three.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Three.java)**      Level: Easy
      

方法1:
Power of 3:  3 ^ x == n ?
意思是 n / 3 一直除, 最后是可以等于1的, 那么就有了 n/=3, check n%3, 最后看结果是不是整除到1的做法. 用while loop.

方法2:
如果n是power of 3, 那么 3 ^ x的这个 x一定是个比n小的数字. 那么可以在 0 ~ n 之间做binary serach, 但是就比较慢.

方法3:
巧妙的想法.最大的3^x integer是 3^19. 那么找到这个数, 一定可以被n整除. 一步到位.



---
**162. [Plus One.java](https://github.com/awangdev/LintCode/blob/master/Java/Plus%20One.java)**      Level: Easy
      

简单的实现, 加1, 进位. 唯一取巧的地方, 最后如果要多一位, 一定是10000...这个模式, 可以走捷径, 直接来个+1size的array, 然后第一位=1.
注意,转换成long也不合理,用太多memory.


---
**163. [Power of Two.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Two.java)**      Level: Easy
      

跟powerOfThree一样: 可以loop, check mod; 也可以用binary search找合适的数字.



---
**164. [Reverse Vowels of a String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Vowels%20of%20a%20String.java)**      Level: Easy
      

vowels: 元音字母. 要求reverse所有元音字母.

##### 方法1: two pointer.
- 前后两个指针, 在while loop里面跑.
- 注意 i<j, 一旦相遇, 就break.
- 找到合适的, 就做swap.
- StringBuffer可以 sb.setCharAt()记得用.
- O(n)
##### 方法2:
拿出所有vowels, 反过来放进去. O(n)



---
**165. [Guess Number Higher or Lower.java](https://github.com/awangdev/LintCode/blob/master/Java/Guess%20Number%20Higher%20or%20Lower.java)**      Level: Easy
      

binary search 公式



---
**166. [Encode and Decode TinyURL.java](https://github.com/awangdev/LintCode/blob/master/Java/Encode%20and%20Decode%20TinyURL.java)**      Level: Medium
      

其实想到了切入点, 是个可难可简单的题目. 这里的encode就是想办法把url存起来, 然后给个 key.
那么具体怎么做这个key, 简单就可以用一个map, 然后counting. 复杂一点就可以做random letter/number组成key.



---
**167. [Wiggle Sort.java](https://github.com/awangdev/LintCode/blob/master/Java/Wiggle%20Sort.java)**      Level: Medium
      

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
**168. [Queue Reconstruction by Height.java](https://github.com/awangdev/LintCode/blob/master/Java/Queue%20Reconstruction%20by%20Height.java)**      Level: Medium
      

别无他法, 只能写一遍例子, 找规律,然后greedy. 
需要写一遍发现的规律比如: 从h大的开始排列, 先放入k小的. 写comparator的时候要注意正确性.
如果要sort, 并且灵活insert:用arrayList. 自己做一个object.
最后做那个'matchCount'的地方要思路清晰, 找到最正确的spot, 然后greedy insert.

O(n) space, O(nLog(n)) time, because of sorting.

可能有简化的余地, 代码有点太长.
比如试一试不用额外空间?



---
**169. [2 Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum.java)**      Level: Easy
      

tutorial:https://www.youtube.com/watch?v=P8zBxoVY1oI&feature=youtu.be

解法1：相对暴力简洁, HashMap<value, index>，找到一个value, 存一个; 若在HashMap里面 match 到结果, 就return HashMap里存的index. O(n) space && time.

解法2：Sort array, two pointer 前后++,--搜索。Sort 用时O(nlogn).     
1. 第一步 two pointer 找 value.       
2. 注意，要利用额外的空间保留original array， 用来时候找index. (此处不能用HashMap，因为以value 为key，但value可能重复)      
O(n) space, O(nlogn) time.    




---
**170. [2 Sum II - Input array is sorted.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum%20II%20-%20Input%20array%20is%20sorted.java)**      Level: Medium
      

升序array, 找2SUM.

#### 方法1:
- 排序好的array. Two pointer移动start和end，核查sum.
- 注意sum用long.
- O(n) time

#### 方法2: Binary Search, 因为已经排好序了啊
- 定住一个valueA, 然后在剩下的里面 binary serach 找 (target - valueB)
- O(nLogN), 就不写了



---
**171. [2 Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum%20II.java)**      Level: Medium
      

与 2sum II - input array is sorted类似. 都是sort array, 然后two pointer.

LintCode的题. 注意找的是greater/bigger than target。

由于给定条件允许O(nLogn):   
   sort
   two pointer

while里面two pointer移动。每次如果num[left]+num[right] > target，那么其中所有num[left++]的加上num[right]都>target.   
也就是,num[right]不动，计算加入挪动left能有多少组，那就是: right-left这么多。 全部加到count上去。     
然后right--.换个right去和前面的left部分作比较。



---
**172. [Coin Change.java](https://github.com/awangdev/LintCode/blob/master/Java/Coin%20Change.java)**      Level: Medium
      

DP. 找对方程f[x], 积累到amount x最少用多少个coin: #coin是value, index是 [0~x].
子问题的关系是: 如果用了一个coin, 那么就应该是f[x - coinValue]那个位置的#coins + 1

注意initialization: 
处理边界, 一开始0index的时候, 用value0. 
中间利用Integer.MAX_VALUE来作比较, initialize dp[x]
注意, 一旦 Integer.MAX_VALUE + 1 就会变成负数. 这种情况会在coin=0的时候发生.

方法1: 用Integer.MAX_VALUE
方法2: 用-1, 稍微简洁一点, 每次比较dp[i]和 dp[i - coin] + 1, 然后save. 不必要做多次min比较.



---
**173. [Jump Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game.java)**      Level: Medium
      

给出步数，看能不能reach to end.

Status:
DP[i]: 在i点记录，i点之前的步数是否可以走到i点？ True of false.
    其实j in [0~i)中间只需要一个能到达i 就好了
Function:
DP[i] = DP[j] && (j + A[j]) ?= i, for all j in [0 ~ i)
Return:
    DP[dp.length - 1];



---
**174. [Maximum Product Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Product%20Subarray.java)**      Level: Medium
      

求最值, DP.
两个特别处:
1. 正负数情况, 需要用两个DP array. 
2. continuous prodct 这个条件决定了在Math.min, Math.max的时候, 
是跟nums[x]当下值比较的, 如果当下值更适合, 会舍去之前的continous product, 然后重新开始.
这也就注定了需要一个global variable 来hold result.



---
**175. [3 Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/3%20Sum%20Closest.java)**      Level: Medium
      

3Sum 的一种简单形式, 并且都没有找index, value, 而只是找个sum罢了.

double for loop。 2Sum只能用土办法 left/right 2 pointers。 O(n^2)

注意：check closest时候用long, 以免int不够用



---
**176. [Triangle Count.java](https://github.com/awangdev/LintCode/blob/master/Java/Triangle%20Count.java)**      Level: Medium
      

其实也就是3sum的变形, 或者而说2sum的变形. 主要用2 pointers来做.
注意, 在选index时候每次定好一个 [0 ~ i], 在这里面找点start, end, 然后i 来组成triangle.
Note巧妙点:
在此之中, 如果一种start/end/i 符合, 那么从这个[start~end]中, 大于start的都可以, 所以我们count+= end-start.
反而言之, <end的其他index, 就不一定能符合nums[start] + nums[end] > nums[i]



---
**177. [3 Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/3%20Sum.java)**      Level: Medium
      

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
**178. [4 Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/4%20Sum.java)**      Level: Medium
      

方法1：  
   1. 利用2Sum的原理，把4Sum分为连个2Sum。左一个pair,右一个pair，每个pair里面放2个数字。   
   2. 以一个点，i，作为分界口，也要列举出所有i之前的pair,作为基础。   
   3. 再尝试从所有i+1后面,找合适的2nd pair。   
 
   可以用HashSet<List>, 可以直接比较list里面每一个元素, 保证set不重复.
   Previous Notes: 在造class Pair时候，要做@override的function: hashCode(), equals(Object d). 平时不太想得起来用。
   参见 http://lifexplorer.me/leetcode-3sum-4sum-and-k-sum/    

方法2： 3Sum外面再加一层. 参考3Sum. 时间O(n^3)。 但此方法在k-sum时候，无疑过于费时间. O(n^k)



---
**179. [k Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/k%20Sum.java)**      Level: Hard
      

DP. 公式如何想到, 还需要重新理解.

dp[i][j][m]: # of possibilities such that from j elements, pick m elements and sum up to i. 
i: [0~target]

dp[i][j][m] = dp[i][j-1][m] + dp[i - A[j - 1]][j-1][m-1]
            (i not included)   (i included)



---
**180. [Longest Continuous Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Continuous%20Increasing%20Subsequence.java)**      Level: Easy
      

简单的DP:dp[i]存在i点时连续上升#. dp[i]时可能为0, 一旦断开.

方法1: dp[i], maintain max
方法2: 用一个数存current count,  maintain max. 也是DP, 稍微简化.



---
**181. [Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Subsequence.java)**      Level: Medium
      

无序数组, 找最长的上升(不需要连续)数组 的长度. 先做O(n^2), 然后可否O(nLogN)?

#### DP, double for loop, O(n^2)
- 考虑nums[i]的时候, 在[0, i) 里count有多少小于nums[i]
- 对于所有 i in [0, n), 最常的increasing序列有多少length?
- max需要在全局维护: nums是无序的, nums[i]也可能是一个很小的值, 所以末尾dp[i]并不是全局的max, 而只是对于nums[i]的max.
- 正因此, 每个nums[i]都要和每个nums[j] 作比较, j < i.
- 时间复杂度  O(n^2)


#### O(nLogN)
- 维持一个list of increasing sequence
- 这个list其实是一个base-line, 记录着最低的increasing sequence.
- 当我们go through all nums的时候, 如果刚好都是上升, 直接append
- 如果不上升, 应该去list里面, 找到最小的那个刚好大于new num的数字, 把它换成num
- 这样就完成了baseline. 举个例子, 比如替换的刚好是在list最后一个element, 等于就是把peak下降了, 那么后面其他的数字就可能继续上升.
- '维护baseline就是一个递增的数列' 的证明, 还没有仔细想.



---
**182. [Unique Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree.java)**      Level: Medium
      

Not quite clear.
根据左右分割而总结出了原理, 每次分割, 左右两边都会有一定数量的permutation, 总体上的情况数量当然是相乘.
然后每一个不同的分割点都加一遍:
f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

然后把数学公式转换成DP的方程, 有点玄学的意思啊! 不好想.



---
**183. [Trim a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Trim%20a%20Binary%20Search%20Tree.java)**      Level: Easy
      

方法1:
适合复习BST. 用DFS对待每个node. 注意BST的特征: 所有left nodes都小于当下node, 所有right nodes都大于当下node.

根据题意用[L,R]切割.如果node.val<L, 直接连node带左边全丢掉, return node.right. 处理R也是一样.
分制是, DFS leftNode, rightNode. 然后接在node.left, node.right.

方法2: 用迭代, 还没有写.



---
**184. [Unique Paths II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Paths%20II.java)**      Level: Medium
      

典型的坐标型DP. 考虑最终结尾需要的状态:如何组成,写出公式.
公式中注意处理能跳掉的block, '到不了', 即为 0 path.



---
**185. [Counting Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Counting%20Bits.java)**      Level: Medium
      

Bit题目 用num的数值本身表示DP的状态.
这里, dp[i] 并不是和 dp[i-1]有逻辑关系; 而是dp[i] 和dp[i>>1], 从binary representation看出有直接关系.



---
**186. [Bomb Enemy.java](https://github.com/awangdev/LintCode/blob/master/Java/Bomb%20Enemy.java)**      Level: Medium
      

分方向考虑的方法很容易想到,但是四个方向移动的代码比较繁琐.
往上炸: 要从顶向下考虑
往下炸: 要从下向上考虑

熟练写2D array index 的变换.



---
**187. [Paint House.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House.java)**      Level: Easy
      

考虑DP最后一个位置的情况. 发现给出了一些特殊条件, 需要附带在DP[i-1]上,
那么就定义二维数组



---
**188. [Decode Ways.java](https://github.com/awangdev/LintCode/blob/master/Java/Decode%20Ways.java)**      Level: Review
      

确定末尾的2种状态: single letter or combos. 然后计算出单个letter的情况, 和双数的情况
note: calculate number from characters, need to - '0' to get the correct integer mapping.
注意: check value != '0', 因为'0' 不在条件之中(A-Z)




---
**189. [House Robber.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber.java)**      Level: Easy
      

搜刮房子, 相邻的不能碰. 每个房子里有value, 求max.

#### Sequence DP
- 看最后结尾状态的前一个或前两个的情况，再综合考虑当下的
- 思考的适合搞清楚当下的和之前的情况的关系

#### Rolling Array
- [i]'只和前两个位子 [i-1], [i - 2]'相关
- 用%2来标记 [i], [i - 1], [i - 2]三个位置.
- 其他滚动时惯用curr/prev来表示坐标, 这里%2虽然抽象, 但是更加实用.




---
**190. [House Robber II.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20II.java)**      Level: Medium
      

和House Robber I 类似, 搜刮房子, 相邻不能动. 特点是: 现在nums排成了圈, 首尾相连.

#### Sequence DP
- 根据dp[i-1]是否被rob来讨论dp[i]: dp[i] = Math.max(dp[i-1], dp[i - 2] + nums[i - 1]);
- 特别的是，末尾的last house 和 first house相连. 这里就需要分别讨论两种情况: 第一个房子被搜刮, 或者第一个房子没被搜刮

#### 两个状态
- 是否搜刮了第一个房子, 分出两个branch, 可以看做两种状态.
- 可以考虑用两个DP array; 也可以加一dp维度, 补充这个状态.
- 连个维度表示的是2种状态(1st house being robbed or not); 这两种状态是平行世界的两种状态, 互不相关.

#### Rolling array
与House Robber I一样, 可以用%2 来操作rolling array



---
**191. [Best Time to Buy and Sell Stock I.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20I.java)**      Level: Easy
      

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
**192. [Best Time to Buy and Sell Stock II.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.java)**      Level: Easy
      

和Stock I 的区别：可以买卖多次，求总和的最大盈利。

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
**193. [Best Time to Buy and Sell Stock III .java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III%20.java)**      Level: Hard
      

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
**194. [Best Time to Buy and Sell Stock IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20IV.java)**      Level: Hard
      

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
**195. [Paint House II.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House%20II.java)**      Level: Review
      

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
**196. [3 Sum Smaller.java](https://github.com/awangdev/LintCode/blob/master/Java/3%20Sum%20Smaller.java)**      Level: Medium
      

一般的O(n3)肯定不行。在此基础上优化。
发现j,k满足条件时候，(k - j)就是所有 sum <target的情况了。
而一旦>target, 又因为j不能后退，只能k--，那么问题就被锁定了. 这样可以做到O(n2)



---
**197. [Array Partition I.java](https://github.com/awangdev/LintCode/blob/master/Java/Array%20Partition%20I.java)**      Level: Easy
      

从结果出发, 只需要找到加法的结果，而不强调具体配对。
找到排列取单数位的规律，再考虑负数和正数的相同规律，即可找到排列求解的方法。




---
**198. [1-bit and 2-bit Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/1-bit%20and%202-bit%20Characters.java)**      Level: Easy
      

方法1:
Greedy.
从第一个bit开始数: 如果遇到1, 一定是跳两位;如果遇到0, 一定是跳一位.
loop to end, and see if index reaches the end.

方法2:
用DP硬做了一下: 
1. 如果i位是0, 那么前面dp[i-1]或者dp[i-2] true就够了.
2. 如果i位是1, 那么i-1位必须是1才满足规则, 并且 dp[i-2]需要true.



---
**199. [Non-decreasing Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Non-decreasing%20Array.java)**      Level: Easy
      

比较升序的时候, 必须要估计到 i-1, i, i+1三个数位.
写出来i-1, i+1之间的关系, 然后做合理的fix.

需要真的fix数组, 因为loop through做比较时会用到fix后的数字.



---
**200. [Max Consecutive Ones.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Consecutive%20Ones.java)**      Level: Easy
      

Basic. Math.max track结果。
记得在有对外操作的loop后，一定要把result object清理干净。



---
**201. [Find All Numbers Disappeared in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20All%20Numbers%20Disappeared%20in%20an%20Array.java)**      Level: Easy
      

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
**202. [Maximum Average Subarray I.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20I.java)**      Level: Easy
      

简单的求sum, 同时求max, 结尾求余数就好.


---
**203. [Largest Number At Least Twice of Others.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Number%20At%20Least%20Twice%20of%20Others.java)**      Level: Easy
      

找最大值, 和第二大的值, 看是否符合题意, 就行了.
分析题意, 最简单方法, 可以loop 两遍: 找最值; 作比较.
但其实, 举个反例: 有一个不满足, 就够反对这个'at least twice of alllll others'.



---
**204. [Toeplitz Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Toeplitz%20Matrix.java)**      Level: Easy
      

似乎没什么算法特点, 就是array基本运算, 然后分割成一个helper function去做重复计算, 剪短代码.
注意check MxN 的分界线.



---
**205. [Backpack.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack.java)**      Level: Medium
      

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
**206. [Sum of Two Integers.java](https://github.com/awangdev/LintCode/blob/master/Java/Sum%20of%20Two%20Integers.java)**      Level: Easy
      

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
**207. [Swap Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Swap%20Bits.java)**      Level: Easy
      

简单, 但是很多知识点:
1. Hex 0xaaaaaaaa 是1010101....1010; 0x55555555 是01010101....0101
2. 可以用这两个hex取单数和负数. 如果需要取其他的pattern, 也可以做.
3. x很可能是negative number, 所以right-shift 要用logic shift, >>> 避免leading负数补位.



---
**208. [Update Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Update%20Bits.java)**      Level: Medium
      

熟悉bits的一些trick:
- ~0 = -1 = 111111...11111111 (32-bit)
- Create mask by shifting right >>>, and shifting left
- Reverse to get 0000...11110000 format mask
- & 0000 = clean up; | ABC = assign ABC



---
**209. [Maximum XOR of Two Numbers in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20XOR%20of%20Two%20Numbers%20in%20an%20Array.java)**      Level: Medium
      

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
**210. [Perfect Squares.java](https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Squares.java)**      Level: Medium
      

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
**211. [Palindrome Partitioning II.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Partitioning%20II.java)**      Level: Hard
      

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
**212. [Backpack V.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20V.java)**      Level: Medium
      

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
**213. [Backpack VI.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20VI.java)**      Level: Medium
      

拼背包时, 可以有重复item, 所以考虑'最后被放入的哪个unique item' 就没有意义了.
背包问题, 永远和weight分不开关系.
这里很像coin chagne: 考虑最后被放入的东西的value/weigth, 而不考虑是哪个.

1维: dp[w]: fill了weigth w 有多少种方法. 前面有多少种可能性, 就sum多少个:
dp[w] = sum{dp[w - nums[i]]}, i = 0~n





---
**214. [Copy Books.java](https://github.com/awangdev/LintCode/blob/master/Java/Copy%20Books.java)**      Level: Hard
      

#### 方法1: Binary Search
- 根据: 每个人花的多少时间(time)来做binary search: 每个人花多久时间, 可以在K个人之内, 用最少的时间完成?
- time variable的范围不是index, 也不是page大小. 而是[minPage, pageSum]
- validation 的时候注意3种情况: 人够用 k>=0, 人不够所以结尾减成k<0, 还有一种是time(每个人最多花的时间)小于当下的页面, return -1
- O(nLogM). n = pages.length; m = sum of pages.

#### 方法2: DP
k个人copy完i本书.
定义Integer.MAX_VALUE的地方需要注意.
Review: 为什么有i level的iteration? Chapter4.1



---
**215. [Valid Perfect Square.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Perfect%20Square.java)**      Level: Review
      

Binary找sqrt. 基本 mid+1, mid-1 template.
注意: define index as long. 



---
**216. [Intersection of Two Arrays II.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Arrays%20II.java)**      Level: Easy
      

方法1:
用HashMap: 存一个nums1, 再拿nums2 check against map. 时间/空间:O(n)

方法2:
Binary search? 需要array sorted. 否则时间O(nlogn)不值得.
[没做完, 有错]



---
**217. [Backpack II.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20II.java)**      Level: Medium
      

做了Backpack I, 这个就如出一辙, 只不过: dp存的不是w可否存成功true/false. dp存的是加上sum value的最大值.
想法还是，选了A[i - 1] 或者没选A[i - 1]时候不同的value值.

O(m)的做法:   
想想，的确我们只care 最后一行，所以一个存value的就够了.
注意：和bakcpackI的 O(m)一样的，j是倒序的。如果没有更好的j，就不要更新。就是这个道理.

注意: 如果无法达到的w, 应该mark as impossible. 一种简单做法是mark as -1 in dp. 
如果有负数value, 就不能这样, 而是要开一个can[i][w]数组, 也就是backpack I 的原型.




---
**218. [Backpack III.java](https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20III.java)**      Level: Review
      

可以无限使用物品, 就失去了last i, last unique item的意义: 因为可以重复使用.

1. 所以可以转换一个角度: 用i种物品, 拼出w, 并且满足题目条件(max value).
这里因为item i可以无限次使用, 所以要遍历使用了多少次K.

2. K虽然可以无限, 但是也被 k*A[i]所限制: 最大不能超过背包大小.

这样一来, 就close loop, 可以做了.

优化: Review
降维: 需要画图review
变成1个一位数组: 看双行的左右计算方向



---
**219. [Longest Palindromic Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Palindromic%20Subsequence.java)**      Level: Medium
      

区间型动态规划. 
1. 用[i][j]表示区间的首尾
2. 考虑3个情况: 砍头, 砍尾, 砍头并砍尾 (考虑首尾关系)
3. Iteration一定是以i ~ j 之间的len来看的. len = j - i + 1; 那么反推, 如果len已知, j = len + i -1;

注意考虑len == 1, len == 2是的特殊情况.



---
**220. [Scramble String.java](https://github.com/awangdev/LintCode/blob/master/Java/Scramble%20String.java)**      Level: Hard
      

区间型
降维打击
dp[i][j][w]: 从i点和j点开始, 各自走w距离, 得到的S和T是否是scramble string.

具体思考过程看Thoughts.

注意: input s1, s2 在整个题目的主要内容里面, 几乎没有用到, 只是用在initialization时候.
很神奇, 这个initailization 打好了DP的基础, 后面一蹴而就, 用数学表达式就算出了结果.



---
**221. [Binary Search Tree Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Search%20Tree%20Iterator.java)**      Level: Medium
      

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
**222. [Flatten Nested List Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/Flatten%20Nested%20List%20Iterator.java)**      Level: Medium
      

方法1: 用queue, 把需要的item全部打出来
方法2: 用stack, 把需要的item先存一行, 每次打开子序列时候, 全部加回stack.



---
**223. [Best Time to Buy and Sell Stock with Cooldown.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Cooldown.java)**      Level: Medium
      

Sequence DP
跟StockIII很像. 分析好HaveStock && NoStock的状态, 然后看最后一步.



---
**224. [Find Peak Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element.java)**      Level: Medium
      

binary search. 
Goal: find peak, where both sides are descending
最左边, 最右边是Integer.MIN_VALUE时候, 也能构成中间数mid是peak的条件.

Note:
没有必要特别check (mid-1)<0或者(mid+1)>=n.
证明:
1. 最左端: 当start=0, end = 2 => mid = 1, mid-1 = 0;
2. 最右端: 当end = n - 1, start = n - 3; mid = (start+end)/2 = n - 2; 
那么mid + 1 = n - 2 + 1 = n - 1 < n 是理所当然的



---
**225. [Longest Common Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Subsequence.java)**      Level: Medium
      

经典序列型.
设定dp长度为(n+1), 因为dp[i]要用来表示前i个(ith)时候的状态, 所以长度需要时i+1才可以在i位置, hold住i.

双序列: 两个sequence之间的关系, 都是从末尾字符看起, 分析3种情况:
1. A最后字符不在common sequence.
2. B最后字符不在common sequence.
3. A/B最后字符都在common sequence. 总体+1.



---
**226. [Interleaving String.java](https://github.com/awangdev/LintCode/blob/master/Java/Interleaving%20String.java)**      Level: Hard
      

双序列DP, 从最后点考虑.
拆分问题的末尾, 考虑和s1, s2 subsequence之间的关联.

求存在性, boolean




---
**227. [Letter Combinations of a Phone Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Letter%20Combinations%20of%20a%20Phone%20Number.java)**      Level: Medium
      

方法1: Iterative with BFS using queue.

方法2: Recursively adding chars per digit



---
**228. [Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Edit%20Distance.java)**      Level: Hard
      

两个字符串变话, 找最小值, two sequence DP.
考虑两个字符串变换的最后点: 相等, 互换, 还是缺少? 分析每种情况, 然后列出表达式.

注意, 在i或者j为0的时候, 变成另外一个数字的steps只能是全变.

第一步, 空间时间都是O(MN)
滚动数组优化, 空间O(N)



---
**229. [Distinct Subsequences.java](https://github.com/awangdev/LintCode/blob/master/Java/Distinct%20Subsequences.java)**      Level: Hard
      

Double Sequence DP:
0. DP size (n+1): 找前nth的结果, 那么dp array就需要开n+1, 因为结尾要return dp[n][m]
1. 在for loop 里面initialize dp[0][j] dp[i][0]
2. Rolling array 优化成O(N): 如果dp[i][j]在for loop里面, 就很好替换 curr/prev



---
**230. [Regular Expression Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Regular%20Expression%20Matching.java)**      Level: Review
      



---
**231. [Majority Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Element.java)**      Level: Easy
      

方法1: Vote 计数, vote++, vote--到最后剩下的就是winner. Time O(n), Space O(1)
Majority Number是指超半数. 超半数的数字, 最后都会至少有vote>=1: match current majority number，vote++；if not, vote--. 
注意：assume valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。

方法2: HashMap count occurance. Time, Space: O(n)

方法3: Bit manipulation. 还没有做.

Related Problems:
Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。

Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。



---
**232. [Wildcard Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java)**      Level: Hard
      

Double sequence DP. 与regular expression 很像.

注意1: 分析字符 ?, * 所代表的真正意义, 然后写出表达式.
注意2: 搞清楚initialization 的时候 dp[i][0] 应该always false.当p为empty string, 无论如何都match不了 (除非s="" as well)
    同时 dp[0][j]不一定是false. 比如s="",p="*" 就是一个matching.



---
**233. [Ones and Zeroes.java](https://github.com/awangdev/LintCode/blob/master/Java/Ones%20and%20Zeroes.java)**      Level: Hard
      

还是Double Sequence, 但是考虑第三种状态: 给的string array的用量.
所以开了3维数组.

如果用滚动数组优化空间, 需要把要滚动的那个for loop放在最外面, 而不是最里面.
当然, 这个第三位define在 dp[][][]的哪个位置, 问题都不大.

另外, 注意在外面calcualte zeros and ones, 节约时间复杂度.



---
**234. [Pow(x,n).java](https://github.com/awangdev/LintCode/blob/master/Java/Pow(x,n).java)**      Level: Medium
      

傻做就O(n), 要更好就考虑O(logN).
减少重复计算, 一切两半.

注意:
- n/2的奇数偶数
- n的正负
- n == 0的情况, x == 0, x == 1 的情况.


---
**235. [Word Break II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Break%20II.java)**      Level: Review
      

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
**236. [Nested List Weight Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Nested%20List%20Weight%20Sum.java)**      Level: Easy
      

方法1: 简单的处理nested structure, dfs增加depth.
方法2: bfs, queue, 处理queue.size().



---
**237. [Same Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Same%20Tree.java)**      Level: Easy
      

DFS. 确定leaf条件, && with all sub problems.

如果用BFS: 两个queue存每个tree的所有current level node. Check equality, check queue size.
Populate next level by nodes at current level.



---
**238. [Convert Sorted Array to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree.java)**      Level: Easy
      

Binary Search Tree特点: 左边的node都比右边的node小. 
如果要height相差<1, 必须左右sub tree均分. 做DFS.



---
**239. [Binary Tree Maximum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Maximum%20Path%20Sum.java)**      Level: Review
      

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
**240. [Construct Binary Tree from Inorder and Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Preorder%20Traversal.java)**      Level: Medium
      

和Construct from Inorder && Postorder 想法一样。

写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。



---
**241. [Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum.java)**      Level: Easy
      

确定好结尾的条件, DFS

写一写: root == null => false 对parent nodes的影响. 这里发现没影响, 所以可以简化成用1个functionDFS.




---
**242. [Add Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Binary.java)**      Level: Easy
      

方法一:土办法没技术，把binary换成数字，加起来，再换成binary。如果input很大，那么很可能int,long都hold不住。不保险。

方法二:一般方法，string化为charArray,然后逐位加起，最后记得处理多余的一个carry on
注意: 需要从末尾加起来, 所以要用一个diff来帮助  shortArray[i-diff] 指向 shortArray的相对应index.



---
**243. [Add Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Digits.java)**      Level: Easy
      

方法1: 普通做法就是按照题意, double-while loop把数字加起来. 第一层循环是O(n), 然后第二层循环就少很多数位, overall O(n)

方法2: 找数学规律, 每过9个数字, 取mod就会开始重复, 所以给所有数字取mod 就可以间接找到答案. O(1)



---
**244. [Add Two Numbers.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Two%20Numbers.java)**      Level: Medium
      

LinkedList都已经反转好了，直接做.
遍历两个l1,l2把carry-on处理好，每次生成一个新node，最后检查carry-on.

跟Add Binary的理解方式一模一样.

注意:
Linked List 没有天然size.
用DummyNode(-1).next来hold住结果.




---
**245. [Add Two Numbers II.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Two%20Numbers%20II.java)**      Level: Medium
      

Singly-linked list需要reverse, 用stack.
最终结果要恢复成input list 那样的sequence方向, 用stack一个个pop()刚好就可以做到.

加法都一样:
   1. sum = carry
   2. carry = sum / 10
   3. sum = sum % 10;



---
**246. [Balanced Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Balanced%20Binary%20Tree.java)**      Level: Medium
      

1. DFS using depth marker: 每个depth都存一下。然后如果有不符合条件的，存为-1.
   一旦有 <0 或者差值大于1， 就全部返回Integer.MIN_VALUE. Integer.MIN_VALUE比较极端, 确保结果的正确性。
   最后比较返回结果是不是<0. 是<0，那就false.
   Traverse 整个tree, O(n)

2. Only calculate depth using maxDepth function. Same concept as in 1, but cost more traversal efforts.



---
**247. [Valid Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Anagram.java)**      Level: Easy
      

HashMap



---
**248. [Populating Next Right Pointers in Each Node.java](https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node.java)**      Level: Medium
      

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
**249. [Validate Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Validate%20Binary%20Search%20Tree.java)**      Level: Medium
      

查看每个parent-child关系。同时把root level上面传下来max,min界限定住。

Note: min/max需要时long type. 
如果题目真的给node.val = Integer.MAX_VALUE, 我们需要能够与之比较, long就可以.



---
**250. [Convert Sorted List to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20List%20to%20Binary%20Search%20Tree.java)**      Level: Medium
      

Divide and Conquer   
找到mid node

方法1:
用长度来定位mid, 每次找中间点做root, 然后前半段, 后半段分别dfs with length.

方法2: 用快慢pointer
Better: 不用traverse entire linked list

slowPointer = node;
fastPointer = node.next;

然后把root = mid.next     

然后开始sortedListToBST(mid.next.next); //后半段    
mid.next = null;//非常重要，要把后面拍过序的断掉    
sortedListToBST(head); //从头开始的前半段     

最后root.left, root.right merge一下。   



---
**251. [Flatten Binary Tree to Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Flatten%20Binary%20Tree%20to%20Linked%20List.java)**      Level: Medium
      

分析题意后, 按照题意: Flatten it with in-place order
1. reserve right child
2. DFS flatten部分
3. 移花接木
4. flatten 剩下的.



---
**252. [Binary Tree Paths.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Paths.java)**      Level: Easy
      

返回所有root-to-leaf path

#### 方法1：   
Recursive:分叉. dfs.

#### 方法2:
- Iterative, 非递归练习了一下   
- 因为要每次切短list, 所以再加了一个Stack 来存level   




---
**253. [Minimum Size Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Size%20Subarray%20Sum.java)**      Level: Medium
      

方法1:
2 pointer, O(n). 找subarray, start 或 end pointer，每次一格这样移动.

好的策略: 
1. 先找一个solution, 定住end. 
2. 然后移动start; 记录每个solution if occurs
3. 然后再移动end，往下找。

Note: 虽然一眼看上去是nested loop.但是分析后，发现其实就是按照end pointer移动的Loop。start每次移动一格。总体上，还是O(n)

方法2:
Double for loop, base i 每次只+1, 所以最后O(n^2)

方法3:
Binary Search, O(nLogN)
Not done yet



---
**254. [Clone Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Clone%20Graph.java)**      Level: Medium
      

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
**255. [Longest Substring Without Repeating Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20Without%20Repeating%20Characters.java)**      Level: Medium
      

方法1:
Two Pointers
双指针: 从start开始遍历, 但是第一步是while loop来推进end; 直到推不动end, 然后start++
巧妙点: 因为end是外围variable, 在start的loop上, end不会重置;[star ~ end] 中间不需要重复计算.
最终可以O(n);

Previous verison of two pointers:
用两个pointer, head和i.
注意：head很可能被退回到很早的地方，比如abbbbbba,当遇到第二个a，head竟然变成了 head = 0+1 = 1.      
当然这是不对的，所以head要确保一直增长，不回溯

方法2:
   HashMap<Char, Integer>: <character, last occurance index>
   一旦有重复, rest map.
   没有重复时候, 不断map.put(), 然后求max值

问题: 每次reset map之后就开始从新从一个最早的index计算, 最坏情况是O(n^2):
'abcdef....xyza'




---
**256. [Minimum Window Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Window%20Substring.java)**      Level: Hard
      

基本思想: 用个char[]存string的frequency. 然后2pointer, end走到底, 不断validate.
符合的就process as result candidate.

HashMap的做法比char[]写起来要复杂一点, 但是更generic



---
**257. [Linked List Cycle.java](https://github.com/awangdev/LintCode/blob/master/Java/Linked%20List%20Cycle.java)**      Level: Easy
      

O(1) sapce: 用快慢指针。一个跑.next, 一个跑.next.next。 总有一次，fast会因为cycle而追上slow。
那个时候其实slow.val = fast.val.

O(n) space: 用HashMap，一直add elements.  如果有重复，那么很显然是有Cycle


---
**258. [Remove Nth Node From End of List.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Nth%20Node%20From%20End%20of%20List.java)**      Level: Medium
      

O(n), one pace, no extra space
找到窗口, 然后平移, 最后pre 和 head之间 skip一个node就好.



---
**259. [Longest Substring with At Most K Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20with%20At%20Most%20K%20Distinct%20Characters.java)**      Level: Medium
      

大清洗 O(nk)   
map.size一旦>k，要把longest string最开头（marked by pointer:start）的那个char抹掉    
一旦某一个char要被清除，所以在这个char 的1st and last appearance之间的char都要被清洗from map




---
**260. [Linked List Cycle II.java](https://github.com/awangdev/LintCode/blob/master/Java/Linked%20List%20Cycle%20II.java)**      Level: Medium
      

方法1:
快慢指针, O(1)space.

确认有cycle后, 其实是数学问题:
当head == slow.next时候， head就是cycle starting point.
也就是说，当slow 移动到了那个回溯点，slow.next那个点就刚好是head的那个点...

证明:
1. 假设慢指针走t步, 快指针走快一倍, 也就是2t.
2. 我们假设cycle的长度是Y, 而进入cycle之前的长度为X.
3. 假设慢指针走了m圈cycle, 而快指针走了n圈cycle之后, 两个pointer相遇.
4. 最终在Y cycle里面的K点相遇, 也就是两个指针都在这最后一圈里面走了K 步.
=> 
那么:
t = X + mY + K
2t = X + nY + K
整合公式:
X + K = (n - 2m)Y
这里的m和n不过是整数的跑圈数, 也就是说X和K加在一起, 总归是结束cycle. X 和 K 互补
=> 结论: 当slow/fast 指针在K点相遇后, 再走X步, 就到了cycle的起点, 也就是题目要求的起点.

方法2:
HashMap, O(n) space


---
**261. [Kth Smallest Number in Sorted Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Number%20in%20Sorted%20Matrix.java)**      Level: Medium
      

方法1:
和Merge K sorted Array/ List 类似：使用PriorityQueue。

因为Array的element无法直接找到next,所以用一个class Node 存value, x,y positions.

方法2:
Binary Search
https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85182/my-solution-using-binary-search-in-c


变型: Kth Largest in N Arrays


---
**262. [Find Minimum in Rotated Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Minimum%20in%20Rotated%20Sorted%20Array.java)**      Level: Medium
      

画图, 最小值被rotate之后, 变成array中间的最低谷.
并且, 左边山坡的最小值, 大于右边山坡的最大值. 
以此来给binary search做判断.

O(nlogn)



---
**263. [Find Minimum in Rotated Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Minimum%20in%20Rotated%20Sorted%20Array%20II.java)**      Level: Hard
      

一个需要严谨思考的题目. 因为有duplicate, 会导致不断平移, 所以最终time complexity是O(n)
所以不如直接扫一遍, 给出答案.

但是还是写一个Binary Search的样子, 只不过worst结果是O(n)



---
**264. [Connecting Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Connecting%20Graph.java)**      Level: Medium
      

没有跑过这个程序, 是一个UnionFind的简单实现.
Document了每个环节的计算原理/思想.



---
**265. [Connecting Graph II.java](https://github.com/awangdev/LintCode/blob/master/Java/Connecting%20Graph%20II.java)**      Level: Medium
      

Lint还不能跑, 全部按照题意和答案document的.



---
**266. [Connecting Graph III.java](https://github.com/awangdev/LintCode/blob/master/Java/Connecting%20Graph%20III.java)**      Level: Medium
      

还是UnionFind的变形, 这次是算有剩下多少个union. 其实非常简单, 维持一个全局变量count:
一开始count=n, 因为全是散装elements;  每次union, count--.



---
**267. [Number of Islands.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Islands.java)**      Level: Medium
      

方法1: 两个for loop brutle force。 DFS把每个跟1相关的都Mark一遍.生成一个island.

方法2:
可以用union-find， 就像Number of island II 一样。
只不过这个不Return list, 而只是# of islands
记住UnionFind的模板和几个变化(Connecting Graph I, II, III), 最后归总的代码写起来就比较简单.



---
**268. [Number of Islands II.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Islands%20II.java)**      Level: Hard
      

方法1: 
用int[] father 的unionFind, 需要转换2D position into 1D index.
count的加减, 都放在了UnionFind自己的function里面, 方便tracking, 给几个helper function就对了.
这样比较clean
Time: O(k * log(mn))

方法2: 
用HashMap的Union-find.

把board转换成1D array， 就可以用union-find来判断了。 判断时，是在四个方向各走一步，判断是否是同一个Land.
每走一次operator，都会count++. 若发现是同一个island, count--

Side Note:
Proof of UnionFind log(n) time: 
https://en.wikipedia.org/wiki/Proof_of_O(log*n)_time_complexity_of_union%E2%80%93find




---
**269. [Graph Valid Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Graph%20Valid%20Tree.java)**      Level: Medium
      

复习Union-Find的另外一个种形式。   
题目类型：查找2个元素是不是在一个set里面。如果不在，false. 如果在，那就合并成一个set,共享parent.   
存储的关键都是：元素相对的index上存着他的root parent.    

注意: 结尾要检查, 是否只剩下1个union. Tree必须连接到所有给出的node.

另一个union-find， 用hashmap的：http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/




---
**270. [Surrounded Regions.java](https://github.com/awangdev/LintCode/blob/master/Java/Surrounded%20Regions.java)**      Level: Review
      

从四个边的edge出发, 像感染僵尸病毒一样扩散, 把靠边的node全部mark, 然后将还是'O'的改成X, 最后回复marks -> 'O'

方法1:
UnionFind里面这次用到了一个rank的概念, 需要review

方法2,3:
DFS, BFS都好理解, 




---
**271. [Implement Trie.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Trie.java)**      Level: Medium
      

Tire, 也即是 Prefix Tree. 

HashMap构建Trie。 Trie三个Method:　   
1. Inset: 加 word   
2. Search: 找word    
3. StartWith: 找prefix    

只有两条children的是binary tree. 那么多个children就是Trie。 那么没有left/right pointer怎么找孩子？   
用HashMap，以child的label为Key，value就是child node。 HashMap走位   

Note:    
node里的char在这是optional. 只要在每个TrieNode里面用map存储向下分布的children就好了.  
另外有种题目，比如是跟其他种类的search相关，在结尾要return whole string，就可以在node里存一个up-to-this-point的String。





---
**272. [Add and Search Word.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20and%20Search%20Word.java)**      Level: Medium
      

Trie结构, prefix tree的变形： '.'可以代替任何字符，那么就要iterate这个node所有的children.

节点里面有char, isEnd, HashMap<Character, TrieNode>   
Build trie = Insert word:没node就加，有node就移动。   
Search word:没有node就报错. 到结尾return true   

这题因为'.'可以代替任何possible的字符，没一种都是一个新的path，所以recursive做比较好些。    
(iterative就要queue了,麻烦点)



---
**273. [Word Search II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search%20II.java)**      Level: Hard
      

相比之前的implementation, 有一些地方可以优化:
1. Backtracking时候, 在board[][] 上面mark就可以, 不需要开一个visited[][]
2. 不需要implement trie的所有方程, 用不到: 这里只需要insert.
   普通的trie题目会让你search a word, 但是这里是用一个board, 看board的每一个字母能不能走出个Word.
   也就是: 这里的search是自己手动写, 不是传统的trie search() funcombination
3. TrieNode里面存在 end的时候存string word, 表示到底. 用完了 word = null, 刚好截断重复查找的问题.

Previous Notes:
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
**274. [Word Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search.java)**      Level: Medium
      

Backtracking:
找到开头的字母, 然后recursively DFS 去把word串到底:
每到一个字母, 朝四个方向走, 之中一个true就可以.

Note:每次到一个字母，mark一下'#'. 4个path recurse回来后，mark it back.

Backtracking方法2:    
用一个boolean visited[][]





---
**275. [Word Squares.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Squares.java)**      Level: Hard
      

可以开Trie class, 里面用到TrieNode. 开Trie(words) 可以直接initalize with for loop
TrieNode 里面可以有一个 List<String> startWith: 记录可以到达这个点的所有string: 有点像树形, ancestor形状的存储.

神操作:
根据square的性质, 如果选中了list of words, 设定 int prefixIndex = list.size().
取出list里面的所有word[prefixedIndex], 并且加在一起, 就是下一个word candidate的 prefix.

形象一点:
list = ["ball", "area"];
prefixIndex = list.size(); ball[prefixIndex] = 'l'; area[prefixIndex] = 'e';
//then
candidatePrefix = ball[prefixIndex] + area[prefixIndex] = "le";
这里就可以用到Trie的那个 findByPrefix function, 在每个点, 都存有所有这个点能产生的candidate.
这时, 试一试所有candidate: dfs

能想到这种倒转的结构来存prefix candidates 在 Trie里面, 这个想法非常值得思考.



---
**276. [Trapping Rain Water.java](https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water.java)**      Level: Hard
      

这道题目的方法比较多.
#### 方法1
Array, 维持一个左手最高墙array, 右手最高强array.
对于每个index而言, vertically 能存放的最大水柱, 就是靠左右最高墙决定的:
min(leftHighestWall, rightHighestWall) - currHeight.

#### 方法2
方法1上面的优化, two pointer, 还是找左边最高和右边最高. O(1) space.
利用到了方法3里面的想法一样: 整个structure是被中间的最高bar 二分天下:
左边按照maxLeft来计算, 右边按照maxRight来计算.

#### 方法3
2 Pointers， 双面夹击:
1. 找中间最高bar的index    
2. 两面往中心扫：每次加上（topBarIndex - currIndex）* (elevation from previous index).也就是每次加一个横条。    
3. 每次还要减去block自身的height

#### 方法4
主要想法和方法3一致: 在山坡下坡的基础上, 一直用stack堆积bottom. 
最后遇到上升之前, 此时bottom可以用来跟stack之前堆积的所有下坡index做比较, 算跟他们高度相差的积水.
用了stack记录下坡, 然后用个while loop一挖到底的想法非常棒.




---
**277. [Trapping Rain Water II.java](https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water%20II.java)**      Level: Hard
      

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
**278. [Data Stream Median.java](https://github.com/awangdev/LintCode/blob/master/Java/Data%20Stream%20Median.java)**      Level: Hard
      

#### 原理
- 把Input stream想成向上的山坡. 山坡中间那点，自然就是median.
- 前半段，作为maxHeap,关注点是PriorityQueue的峰点，也就是实际上的median.   
- 后半段，作为minHeap,正常的PriorityQueue。 开头是最小的。

#### 注意
- 这里要首先定好, 哪一个queue是多存一个element的. 这里选maxHeap: maxHeap.size() == minHeap.size() + 1 || minHeap.size()
- 必须先维护maxHeap里面有个元素, 否则null了会在比较大小时出问题.



---
**279. [Sliding Window Median.java](https://github.com/awangdev/LintCode/blob/master/Java/Sliding%20Window%20Median.java)**      Level: Hard
      

Median还是用min-heap 和 max-heap. Time(logN)
加/减: prioirtyQueue, log(n)
findMedian: O(1)

#### 思想
- 加一个数, 减一个数。
- 加减时看好，是从前面的maxheap里面抽，还是从后面的minHeap里面抽。
- 抽完balance一下

#### 注意
用maxHeap, minHeap时候, 习惯选择让maxHeap多一个数字:
左边的maxHeap总有 x+1或者x个数字
后边minHeap应该一直有x个数字



---
**280. [Min Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/Min%20Stack.java)**      Level: Easy
      

双Stack：一个正常stack，另一个minStack存当下level最小值. 注意维护minStack的变化

另外. 如果要maxStack，也是类似做法



---
**281. [Implement Queue using Stacks.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Queue%20using%20Stacks.java)**      Level: Easy
      

#### 双Stack
画图, 知道最后maintain的stack是那个 reverseStack: pop(), peek(), empty() 都在这个stack上, 无需变换.
push()里面做stack和reverseStack的来回倾倒.
相比老的code, 在PUSH里面做倾倒, 更容易读.

#### Previous notes
双Stack. 一个是等于是queue，一个是backfillStack.
Tricky: 是在pop()和peek()的时候backfill, 并且要等到stack用完再backfill.
写一下例子就知道，如果提早backfill，stack.peek()就不是queue的head了.




---
**282. [Expression Expand.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Expand.java)**      Level: Medium
      


#### DFS
- 与Stack时需要考虑的一些function类似. 特别之处: **检查[ ]的结尾**
- 因为DFS时候, 括号里的substring会被保留着进入下一个level, 所以我们在base level要keep track of substring.
- 用int paren 来track 括号的开合, 当paren再次==0的时候 找到closure ']'

#### Stack
- Stack存 [ ] 里面的内容, detect 括号开头结尾: 结尾时process inner string
- 有很多需要注意的细节才能做对:
- Stack<Object> 也可以用, 每个地方要注意 cast. 存进去的需要是Object: String, Integer
- 几个 type check: instanceof String, Character.isDigit(x), Integer.valueOf(int num)
- 出结果时候, 不能轻易 sb.reverse().toString(): sb.reverse() 翻转了整个连在一起的string, 错.
- 用另一个Stack<String>作为buffer, 先把stack里面的内容倒出来 (pure), 但是每个item里面顺序不变.
- 最后再从buffer里面倒进StringBuffer.




---
**283. [Largest Rectangle in Histogram.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Rectangle%20in%20Histogram.java)**      Level: Hard
      

给n个bar,组成柱状图histogram. 求在这一排柱状图里面可以找到的面积最大的长方形.

思考: 找长方形面积, 无非是找两个index, 然后底边长度 * height.

#### Monotonous Stack
- 重点是根据找Histogram里面rectangle的性质, 维持一个单调递增的Stack
- 在loop over indexes的时候:
- 如果高度>= previous peek(), 那么对于那个peek, 就意味着, 往下走, 一直走高嘛, 之前的peek总可以继续抄底
- 什么时候不能抄底了呢? 就是有一个下降趋势的时候
- 这时候并不是calculate所有前面的peek, 而是考虑 大于 current height的之前所有的peek.
- 把这些peek到 current height 前一格的rectangle全部找出来: stack.pop()
- 这个stack.pop()的过程里面, 其实没有算上 current height, 因为需要留到下一轮, 把current index加进stack 再说
- 为什么用stack? 因为需要知道连续递增的peek, stack.peek() O(1), 好用
  而其实不用stack, 也可以用其他方式记录所有height, 只不过要 O(n)去找peek不方便

#### 知识点
- 理解monotonous stack 是如何被维护的
- 维护monotonous stack 是题目需要, 而不是stack本身性质, 是一种借助 stack.peek() O(1)的巧妙用法.




---
**284. [Max Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Tree.java)**      Level: Medium
      

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
**285. [Reverse Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Integer.java)**      Level: Easy
      

#### 方法1
每次加上x%10，然后x不断减小～0
注意处理MAX_VALUE, MIN_VALUE
符号不重要, 直接处理, 也会保留.

#### 方法2
转换成String 然后 reverse
Space O(n), time O(n)



---
**286. [Swap Nodes in Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Swap%20Nodes%20in%20Pairs.java)**      Level: Medium
      

#### enumurate 
基本原理, 写出来, 然后连线:
pre -> A -> B -> C -> ...
需要一个虚拟 preNode做起始node, 不然无法把后面的node换到开头.

#### 注意
用dummy = pre作为head前一格.
用 `pre.next == null && pre.next.next` 来check是否为NULL.
pre.next.next 保证了至少有一次swap.



---
**287. [Find Peak Element II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element%20II.java)**      Level: Hard
      

Should break down by mid row. More details:
http://www.jiuzhang.com/solution/find-peak-element-ii/#tag-highlight-lang-java
http://courses.csail.mit.edu/6.006/spring11/lectures/lec02.pdf

#### 方法1
##### 基本原理
我们不可能一口气准确定位(x,y), 但是我们可以再一个row/col里面, 找到1D array的 peak.
根据这个点, 再往剩下两个方向移动

1. 在中间的一行, 找到peak所在的y.

2. 在中间的一列, 找到peak所在的x. (有可能强势override之前找到的y, 也就是放弃那一行的peak, 在midY上找peak)

3. 猜一猜 (x,y) 是不是 peak, 如果不是, 像更高的位置移动一格

4. 根据之前算的 midX, midY 把board分成4个象限, 在每一份里面再继续找

##### 剪枝/切分象限
每次只是找到一个row/col里面的peak而已!

找到这个点, 就等于把board切成了两半.

然后, 再跟剩下的相邻的两个位置比较, 就知道了哪里更大, 就去哪里找peak, 也就是又切了第二刀.

切第二刀的时候, 也要把(x, y) 移到需要取的象限. 进行DFS

##### 时间复杂度
每一个level都减一半
T(n) = n + T(n/2) = n + n/2 + n/4 + ... + 1 = n(1 + 1/2 + .... + 1/n) = 2n = O(n)

#### 方法2
Binary Search
还没有写 : )
O(nLogN)



---
**288. [Sqrt(x).java](https://github.com/awangdev/LintCode/blob/master/Java/Sqrt(x).java)**      Level: Easy
      

#### s- qrt(int x)
- 理解题意, 从[0, x]找一个可以m*m=x的值.
- 注意, 如果找不到, 最后问考官该return一个什么值：按道理，因为return int, 会取整，那么return一个平方最close to x就可以.
- 注意 mid 用 long, 因为很可能超过最大int.

#### sqrt(double x)
- 二分float number, 应该用精度来定义结尾.
- 还是二分, 但是判断条件变成: while ( end - start > eps)
- eps = 1e-12,也就是精度到1e-12



---
**289. [First Bad Version.java](https://github.com/awangdev/LintCode/blob/master/Java/First%20Bad%20Version.java)**      Level: Easy
      

Binary Search

根据isBadVersion的性质，判断还如何end=mid or start=mid.     
isBadVersion 是有方向的嘛，一个点错了，后面全错。



---
**290. [Wood Cut.java](https://github.com/awangdev/LintCode/blob/master/Java/Wood%20Cut.java)**      Level: Medium
      

二分的思想: 判断的是一个 validate() function, 而不是简单的'=='

不需要sort! 为什么呢? 因为我们不是在given array上面二分, 我们是根据最大值在[0, max]上二分.

Overall time: O(nLogM), where M = largest wood length



---
**291. [Find the Duplicate Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Duplicate%20Number.java)**      Level: Medium
      

- 注意不要思维定式: 以为mid是index
- 这里mid其实是binary search on value [1, n] 的一个value.
- 再次用到validate() function

Time: O(nLogN)



---
**292. [Palindrome Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Pairs.java)**      Level: Hard
      

Obvious的做法是全部试一遍, 判断, 变成 O(n^2) * O(m) = O(mn^2). O(m): isPalindrome() time.

当然不行了, 那就看是O(nlogN), 还是O(n)?

#### 方法1: Hash Table + Palindrome的性质. 复合型.
O(mn)

##### 思路
- 每一个word, 都可以拆分成 front + mid + end. 如果这个word + 其他word可以组成palindrome,那就是说
- 砍掉 (mid+end), front.reverse() 应该存在 words[] 里面.
- 砍掉 (front+mid), end.reverse() 应该存在 words[] 里面.
- 我们用HashMap存所有的<word, index>, 然后reverse, 找配对就好.

##### Corner case
- 如果有 empty string "", 那么它跟任何一个palindrome word, 都可以配对, 并且根据位置前后变换, 凑成2份 distinct indexes.
- 这样就有了那个 `if (reverseEnd.equals("")) {...}` 的logic.
- 注意: 虽然在处理砍头/砍尾的两个 for loop里面都在根据 empty string 重复记录, 
  但因为 "" 自己本身不能作为起点, 所以overall只会在被其他palindrome配对时记录一次.


#### 方法2: Trie
还要做一下那.



---
**293. [Game of Life.java](https://github.com/awangdev/LintCode/blob/master/Java/Game%20of%20Life.java)**      Level: Medium
      

#### basic
- 简单的implementation, 把count function写清楚就好.
- time: O(mn), extra space: O(mn)
- 注意结尾要一个个board[i][j]copy

#### follow up
unlimited border? 可能需要分割board. 用大框分割, 每次换行的时候, 重复计算2行就好了. see details below.

#### improvement: do it in place!
- time: O(mn), extra space: O(1)
- bit manipulation: 用第二个bit来存previous value.
- 因为我们只考虑 0 和 1 而已, 所以可以这样取巧. 但是并不scalable.
- 如果需要存multiple state, 可能需要移动更多位, 或者用一个 state map
- 注意 bit manipulation 的细节: <<1, >>1, 还有 mast的用法: |, & 




---
**294. [Maximum Average Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20II.java)**      Level: Review
      

给int[] nums 和 window min size k. window size可以大于K. 找最大的连续数列average value.

- Binary Search的思想, 用在所要找的这个 average sum 上面. 大小是在[min, max]之中
- 找k的时候, 是>=k都可以, 巧用一个 min(preSum)的概念.
- 找k的时候, 画图, 可以看出来, 其实要的是 k window 里面的sum [x, i], 所以要用 sum[0, i] - sum[0, x]

需要仔细去读下面的notes.



---
**295. [Meeting Rooms.java](https://github.com/awangdev/LintCode/blob/master/Java/Meeting%20Rooms.java)**      Level: Easy
      

- 注意接头点要考虑所有开会结会的情况，不要恰巧漏掉相接的点
- 开会的是超人。瞬间移动接上下一个会议

#### 方法1:
找是否有overlap. priorityQueue 按照start time排序好以后, 比较current和peek: current.end > peek.start?

#### 方法2: Sweep line
- class Point{pos, flag}, PriorityQueue排序。计算count
- 跟 Number of Airplanes in the Sky 是一个类型的题目





---
**296. [Number of Airplane in the sky.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Airplane%20in%20the%20sky.java)**      Level: Medium
      

#### Sweep Line
- 把Interval拆分成数轴上的Point 
- 起飞mark 1   
- 降落mark -1     
- 用PriorityQueue排序， loop through queue, 计算(起飞+降落)值可能有的max。

#### 注意
- 同时起飞和降落，就是 1 - 1 = 0. 所以在while loop里面有第二个while loop，    
- 当坐标x重合时，在这里做完所有x点的加减，然后再比较 max。     
- 这避免了错误多count，或者少count



---
**297. [Meeting Rooms II.java](https://github.com/awangdev/LintCode/blob/master/Java/Meeting%20Rooms%20II.java)**      Level: Medium
      

给一串数字pair, 代表会议的开始/结束时间. 找同时又多少个会议发生(需要多少件房间)

#### 方法1
- PriorityQueue + 一个Class来解决.Ｏ(nlogn)
- 跟 Number of Airpline in the sky是同一道题

#### 方法2: 尝试了一下用一个sorted Array + HashMap
也还行，但是handle edge的时候,HashMap 要小心，因为相同时间start和end的map key 就会重复了。



---
**298. [Building Outline.java](https://github.com/awangdev/LintCode/blob/master/Java/Building%20Outline.java)**      Level: Review
      

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
**299. [Unique Path.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Path.java)**      Level: Medium
      

2D array, 算走到最右下角，有多少种方式.

##### DP
- 计数DP.注意方程式前两位置加在一起: 前两种情况没有overlap, 也不会缺情况.
- 注意initialization, 归1.
- 需要initialize的原因是,也是一个reminder: 在方程中会出现-1index
- Of course, row i = 0, or col j = 0, there is only 1 way to access
- time O(mn), space O(mn)

##### 滚动数组
- [i] 只跟 [i - 1] 有关系, 用 curr/prev 建立滚动数组.
- space O(n) 优化空间




---
**300. [Maximal Rectangle.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Rectangle.java)**      Level: Hard
      

#### 方法1: monotonous stack
分解开来, 其实是'Largest Rectangle in Histogram', 只不过这里要自己model heights.
一个2D array里面的rectangle, 最终也是用height * width做出来的.
巧妙在于, 把每一行当做底边, 算出这个底边, 到顶部的height: 
- 如果底边上的一个value==0, 那么算作没有height(以这个底边做rectangle, value==0的位置是空中楼阁, 不能用)
- 如果底边上的value==1, 那么就把上面的height加下来, 做成histogram

如果看具体实例, 有些row似乎是白算的, 但是没有办法, 这是一个搜索的过程, 最终会比较出最优解.

#### 方法2: DP
Coordinate DP?



---
**301. [Maximal Square.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Square.java)**      Level: Medium
      

只能往右边,下面走, 找面积最大的 square. 也就是找到变最长的 square.

#### DP
- 正方形, 需要每条边都是一样长度.
- 以右下角为考虑点, 必须满足条件: left/up/diagonal的点都是1
- 并且, 如果三个点分别都衍生向三个方向, 那么最长的 square 边就是他们之中的最短边 (受最短边限制)
- dp[i][j]: max square length when reached at (i, j), from the 3 possible directions
- dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
- Space, time O(mn)

##### init
每个点都可能是边长1, 如果 matrix[i][j] == '1'

##### 滚动数组
[i] 和 [i - 1] 之间的关系, 想到滚动数组优化 space, O(n) sapce.



---
**302. [Minimum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Path%20Sum.java)**      Level: Medium
      

#### DP
- 往右下角走, 计算最短的 path sum. 典型的坐标型.
- 注意: init 第一行的时候, 要accumulate dp[0][j - 1] + grid[i][j], 而不是单纯assign grid[i][j]

##### rolling array
TODO




---
**303. [House Robber III.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20III.java)**      Level: Hard
      

Houses被arrange成了binary tree, 规则还是一样, 连续相连的房子不能同时抄.
求Binary Tree neighbor max 能抄多少.

#### DFS
- 判断当下的node是否被采用，用一个boolean来表示. 
- 如果curr node被采用，那么下面的child一定不能被采用.
- 如果curr node不被采用，那么下面的children有可能被采用，但也可能略过，所以这里用Math.max() 比较一下两种可能有的dfs结果。
- dfs重复计算:每个root都有4种dive in的可能性, 假设level高度是h, 那么时间O(4^(h)), where h = logN, 也就是O(n^2)

#### DP 
- 并不是单纯的DP, 是在发现DFS很费劲后, 想能不能代替一些重复计算?
- 在DFS的基础上, 在每一个level上面来一个dp.
- 基本思想是dfs解法一致: 取root找最大值, 或者不取root找最大值
- Optimization: DP里面, 一口气找leftDP[]会dfs到最底层, 然后自下向上做计算
- 这个过程里面, 再也不用回去visit most-left-leaf了, 算过一遍就完事.
- 然而, 普通没有dp的dfs, 在算完visited的情况下的dfs, 还要重新dfs一遍!visited的情况.
- Space O(h), time O(n), 或者说是O(2^h), where h = log(n)



---
**304. [Longest Increasing Path in a Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Path%20in%20a%20Matrix.java)**      Level: Hard
      

m x n 的matrix, 找最长增序的序列长度. 这里默认连续的序列.

- 接成圈是不行的, 所以visit过得 (x,y)就不能再去了.
- 斜角方向不能走, 只能走上下左右

#### DP, DFS
- DFS太多重复计算; memoization (dp[][], visited[][]) 省去了重复计算
- initialize dp[x][y] = 1, (x,y) 自己也算path里的一格
- O(m * n * k), where k is the longest path

#### Topological sort
还没有做



---
**305. [Coins in a Line.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line.java)**      Level: Medium
      

拿棋子游戏, 每个人可以拿1个或者2个, 拿走最后一个子儿的输. 问: 根据给的棋子输, 是否能确定先手的输赢?

Game Theory: 如果我要赢, 后手得到的局面一定要'有输的可能'.

#### DP, Game Theory
- 要赢, 必须保证对手拿到棋盘时, 在所有他可走的情况中, '有可能败', 那就足够.
- 设计dp[i]:表示我面对i个coins的局面时是否能赢, 取决于我拿掉1个,或者2个时, 对手是不是会可能输?
- dp[i] = !dp[i - 1] || !dp[i-2]
- 时间: O(n), 空间O(n)
- 博弈问题, 常从'我的第一步'角度分析, 因为此时局面最简单.

#### Rolling Array
空间优化O(1). Rolling array, %2



---
**306. [Coins in a Line II.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20II.java)**      Level: Medium
      

给一串coins, 用values[]表示; 每个coin有自己的value. 先手/后手博弈,
每次只能拿1个或者2个棋子, 最后看谁拿的总值最大.

MiniMax的思考方法很神奇, 最后写出来的表达式很简单

##### DP, Game Theory 自考过程比较长
- 跟Coins in a line I 不一样: 每个coin的value不同.
- 用到MiniMax的思想, 这里其实是MaxiMin. Reference: http://www.cnblogs.com/grandyang/p/5864323.html
- Goal: 使得player拿到的coins value 最大化. 
- 于此同时, 你的对手playerB也想最大化, 而你的选择又不得不被对手的选择所牵制.
- 用MaxiMin的思想, 我们假设一个当下的状态, 假想对手playerB会做什么反应(从对手角度, 如何让我输)
- 在劣势中(对手让我输的目标下)找到最大的coins value sum
- 设定dp[i]: 从index i 到 index n的最大值. 所以dp[0]就是我们先手在[0 ~ n]的最大取值

##### 推算表达式
Reference里面详细介绍了表达式如何推到出来, 简而言之:
- 如果我选了i, 那么对手就只能选(i+1), (i+2) 两个位置, 而我在对方掌控时的局面就是min(dp[i+2], dp[i+3])
- 如果我选了i和(i+1), 那么对手就只能选(i+2), (i+3) 两个位置, 而我在对方掌控时的局面就是min(dp[i+3], dp[i+4])
- 大家都是可选1个或者2个coins
- 目标是maximize上面两个最坏情况中的最好结果

##### 简化表达式
- 更加简化一点: 如果我是先手, dp[i]代表我的最大值.
- 取决于我拿了[i], 还是[i] + [i+1], 对手可能是dp[i + 1], 或者是dp[i+2]
- 其实dp[i] = Math.max(sum - dp[i + 1], sum - dp[i + 2]);
- 这里的sum[i] = [i ~ n] 的sum, 减去dp[i+1], 剩下就是dp[i]的值没错了

##### Initialization
- 这个做法是从最后往前推的, 注意initialize dp末尾的值.
- dp = new int[n + 1]; dp[n] = 0; // [n ~ n]啥也不选的时候, 为0.
- sum = new int[n + 1]; sum[n] = 0; // 啥也不选的时候, 自然等于0
- 然后记得initialize (n-1), (n-2)

##### 时间/空间
Time O(n)
Space O(n): dp[], sum[]



---
**307. [Binary Tree Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Inorder%20Traversal.java)**      Level: Easy
      

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
**308. [Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20II.java)**      Level: Easy
      

Binary Tree的一个基本题: 找到所有满足条件的path

- 遍历到底，比较sum vs. target
- 注意divide的情况。要把遍历的例子写写



---
**309. [Binary Tree Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Postorder%20Traversal.java)**      Level: Medium
      

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
**310. [Change to Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/Change%20to%20Anagram.java)**      Level: Easy
      

HackerRank里面的random 题目: 给一个string, 一切成两半, 看两半要变化多少个字符, 能变成anagram.

- 切两半成两个String A,B. 分别在int count[26]里面++, --.
- 记录 26个小写字母的频率. 如果全部抵消, 就是anagram.
- 注意: 最后count出来要除以2：字母不同，会在不同的字母位上加减count,那么就是刚好重复计算了一遍。所以除以二

- Note: HackerRank里要注意自己写: Scanner, import java.util, non-static method ...etc.



---
**311. [Classical Binary Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Classical%20Binary%20Search.java)**      Level: Easy
      

#### Binary Search Template
- while: start + 1 < end
- mid = start + (end - start) / 2;
- 根据mid作比较
- 末尾double check start, end.




---
**312. [Climbing Stairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Climbing%20Stairs.java)**      Level: Easy
      

#### Recursive + Memoization
- 递归很好写, 但是重复计算, timeout. time: O(2^n)
- O(2^n): each n can spawn 2 dfs child, at next level, it will keep spawn. Total 2^n nodes will spawn.
- 用全局变量int[] memo 帮助减少重复计算
- O(n) time, space

#### DP
- 最后一步被前两种走法决定: dp[i] = dp[i - 1] + dp[i - 2]
- 基础sequence DP, int[] dp = int[n + 1];
- DP[]存的是以 1-based index的状态
- 需要知道dp[n] 的状态, 但是最大坐标是[n-1], 所以int[n+1]
- dp[0]往往是有特殊状态的
- O(n) space, time

#### 序列DP, 滚动数组
- [i] only associates with [i-2], [i-1].
- %2
- O(1) space



---
**313. [Coins in a Line III.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20III.java)**      Level: Hard
      

还是2个人拿n个coin, coin可以有不同的value. 只不过这次选手可以从任意的一头拿, 而不限制从一头拿. 算先手会不会赢?

#### Memoization + Search
- 跟Coins in a Line II 一样, MiniMax的思想: 找到我的掠视中的最大值
- dp[i][j] 代表在[i,j]区间上的先手最多能取的value 总和
- 同样, sum[i][j]表示[i] 到 [j]间的value总和
- dp[i][j] = sum[i][j] - Math.min(dp[i][j - 1], dp[i + 1][j]);
- 这里需要search, 画出tree可以看明白是如何根据取前后而分段的.

#### 博弈 + 区间DP
(这个方法需要复习, 跟数学表达式的推断相关联)
- S(x) = X - Y, 找最大数字差. 如果最大值都大于0, 就是赢了; 如果小于0, 就输了. 
- dp[i][j]表示 从index(i) 到 index(j), 先手可以拿到的最大值与对手的数字差. 也就是S(x) = X - Y.
- dp[i][j] = max{a[i] - dp[i + 1][j], a[j] - dp[i][j - 1]}
- 最后判断 dp[0][n] >= 0

#### 注意
- 如果考虑计算先手[i, j]之间的最大值, 然后可能还需要两个数组, 最后用于比较先手和opponent的得分大小 => 那么就要多开维.
- 我们这里考虑的数字差, 刚好让人不需要计算先手的得分总值, 非常巧妙.

#### 区间型动态规划
- 找出[i, j]区间内的性质: dp[i][j]下标表示区间范围 [i, j]
- 子问题: 砍头, 砍尾, 砍头砍尾
- loop应该基于区间的length
- template: 考虑len = 1, len = 2; 设定i的时候一定是 i <= n - len; 设定j的时候, j = len + i - 1;




---
**314. [Closest Binary Search Tree Value.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Binary%20Search%20Tree%20Value.java)**      Level: Easy
      

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
**315. [Compare Version Numbers.java](https://github.com/awangdev/LintCode/blob/master/Java/Compare%20Version%20Numbers.java)**      Level: Medium
      

给两串version number,  由数字和'.' 组成. 比较先后顺序. 

If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

#### divide and conquer 
- 用 str.split("\\.") 分割string
- Convert成integer, 逐个击破

#### 注意
- '1.0' 和 '0' 是相等的
- 如果可以假设version integer都是valid, 直接Integer.parseInt()就可以了
- 不然的话, 可以compare string



---
**316. [Count Complete Tree Nodes.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20Complete%20Tree%20Nodes.java)**      Level: Medium
      

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
**317. [Course Schedule.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule.java)**      Level: Medium
      

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
**318. [Course Schedule II.java](https://github.com/awangdev/LintCode/blob/master/Java/Course%20Schedule%20II.java)**      Level: Medium
      

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
**319. [Alien Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/Alien%20Dictionary.java)**      Level: Hard
      

给一个 array of strings:  假如这个array是按照一个新的字母排序表(alien dictionary)排出来的, 需要找到这个字母排序.

有可能有多重排序的方法, 给出一种就可以.

#### BFS
- topological sort 本身很好写, 但是要在题目中先了解到字母排序的本质
- 本质: 上下两行string, 相对应的相同的index上, 如果字母不同, 就说明排在第一行的字母在字母表里更领先
- 其实上面这个排序的本质很好想, 但是把它具体化成构建graph的代码, 会稍微有点难想到
- 把 string array 变成topological sort的 graph
- 算indegree, 然后用 BFS 来找到那些 inDegree == 0的 node
- 最先inDegree == 0的node, 就排在字母表前面.
- 下面的解法, 用了Graph: map<Character, List<Character>>, 而不是  List[26], 其实更加试用超过26个字母的dictionary.

#### DFS
- 跟BFS建立 grpah 的过程一模一样
- DFS的不同在于: 用visited map 来标记走过的地方
- 走到leaf的时候, add to result: 但因为走到了底才add, 最终的顺序应该颠倒 (或者, sb.insert(0, x) 直接用颠倒的顺序add)



---
**320. [Binary Tree Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Preorder%20Traversal.java)**      Level: Easy
      

#### Recursive
- 加root, left, then right. Obvious
- Divide and conquer
- 其实也不需要helper function

#### Iterative
- 先加root, 然后push上需要末尾process的在stack垫底(root.right), 然后push root.left
- Stack: push curr, push right, push left.   



---
**321. [Closest Number in Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Number%20in%20Sorted%20Array.java)**      Level: Easy
      

- Binary Search 的一种变型, LintCode无法再跑一边.
- 考虑mid-1, mid+1.
- 一旦没有mid = target.index。 那么target最终就narrow down在(mid-1,mid) 或者(mid,mid+1)   



---
**322. [Complete Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Complete%20Binary%20Tree.java)**      Level: Easy
      

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
**323. [Compare Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Compare%20Strings.java)**      Level: Easy
      

看StringA是不是包括所有 StringB的字符.

#### Basic Implementation
- 比较一下大小, null.
- 然后用int[]来count chars from A, count[x]++. 再对照chars in B, count[x]--
- 如果 count[c] < 0, 就 false.
- O(n)



---
**324. [Contains Duplicate.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate.java)**      Level: Easy
      

无序数组, 找是否有重复element, return true/false.

#### HashSet
- No brain: HashSet.
- Time O(n), Space O(n)

#### Sort, Binary Search
- Arrays.sort(x): Time O(nLogN), Space O(1)
- 排序后, 重复数会排在一起, 然后 binary search



---
**325. [Contains Duplicate II.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate%20II.java)**      Level: Easy
      

Unsorted array, 找出是否有duplicate elemenets: 必要条件是, 这两个element的index i,j 的大小最多相差k.

#### HashSet
- 很巧妙地根据k range地条件, 把HashSet里面的值控制在[i - k, i]
- 每次不断地往set里面加新元素, 从set里减去末尾index的元素
- 而set.add(x)如果遇到重复, 会return false.
- 一旦在这个length k 的 range里面, 有重复, 就符合条件. 
- Time O(n)

#### HashTable<value, List of duplicates>
- 记录每个element value的index in the list
- 一旦有重复element重复, 就把整个list of indexes 端出来, 查看有没有符合条件的: (index - i) <= k
- Time O(nm), m = # of duplicates

#### 这两种做法的区别很有艺术感觉
- 方法1是限定选拔的candidate, 不合格就去掉, 那么一旦有符合条件的(duplicates), 那么一定中, 剩下的就不看了.
- 方法2是把符合条件的index找出来, 集中处理, 但是所有candidate都会选出来
- 就好像招人一样: 一种是遇到好的就停止; 第二种是看过所有人, 从中选拔最好的. 显然第一种更快.




---
**326. [Contains Duplicate III.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate%20III.java)**      Level: Medium
      

给一个unsorted array, 问, 是否有两个element, value相差最大为t,  而两个element的index 相差最大为k.

Note: 虽然题目名字是Contains Duplicate, 但其实要找的两个element不是duplicate, 而是Math.abs(value1 - value2) <= t.

#### TreeSet
- TreeSet还是一个set, 我们用来装已经visit过得item
- 如果window大小超过K, 那么把nums[i - k - 1] 去掉, 并且加上新的element
- 这里有个公式推算: (Math.abs(A-B) <= t) =>>>>> (-t <= A - B <= t) =>>>>>> A >= B - t, A <= B + t
- 也就是说, 如果对于 B = nums[i], 来说, 能找到一个target A, 满足上面的公式, 那么就可以 return true.
- Time O(nLogk), treeSet的大小不会超过k,  而 treeSet.ceiling(), treeSet.add(), treeSet.remove() 都是 O(logK)
- Space O(k)

#### Note
- 与Contains Duplicate II 类似概念. TreeSet有BST 因此可以直接用, 而不用自己构建BST
- 简化题目里面的重要条件 Math.abs(A-B) <= t 而推断出 A >= B - t, A <= B + t
- 并且需要需要用 TreeSet.ceiling(x): return number greater or equal to x. 这个用法要记住吧, 没别的捷径.



---
**327. [Burst Balloons.java](https://github.com/awangdev/LintCode/blob/master/Java/Burst%20Balloons.java)**      Level: Hard
      

一排球, 每个球有value, 每次扎破一个, 就会积分: 左*中间*右 的值. 求, 怎么扎, 最大值?

TODO: Need more thoughts on why using dp[n + 2][n + 2] for memoization, but dp[n][n] for range DP.

#### Range DP
- 因为数组规律会变, 所以很难找'第一个burst的球'. 反之, 想哪一个是最后burst?
- 最后burst的那个变成一堵墙: 分开两边, 分开考虑, 加法原理; 最后再把中间的加上.
- dp[i][j] represent max value on range [i, j)
- Need to calculate dp[i][j] incrementally, starting from range size == 3 ---> n
- Use k to divide the range [i, j) and conquer each side.

##### Range DP 三把斧:
- 中间劈开
- 砍断首或尾
- Range区间作为iteration的根本

##### Print the calculation process
- use pi[i][j] and print recursively.
- Print k, using pi[i][j]: max value taken at k

#### Memoization
- 其实会做之后挺好想的一个DP
- dp[i][j] =  balloons i~j 之间的 max. 
- 然后找哪个点开始burst? 设为x。
- For loop 所有的点作为x， 去burst。
- 每次burst都切成了三份：左边可以recusive 求左边剩下的部分的最大值 + 中间3项相乘 + 右边递归下去求最大值。
- Note: 这个是Memoization, 而不纯是DP
- 因为recursive了，其实还是搜索，但是memorize了求过的值，节省了Processing




---
**328. [Nim Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Nim%20Game.java)**      Level: Easy
      

#### Brainteaser
- 著名Nim游戏
- 写一些，发现n=4,5,6,7,8...etc之后的情况有规律性: 谁先手拿到4就输了.
- 最终很简单n%4!=0就可以了,  time, space O(1)

#### DP
- 正规地找规律做, 就跟 coins in a line 一样, 按照先手后手来做
- 可以rolling array 优化空间
- Time O(n), 当然啦, 这个题目这样会timeout, 可以使用brainteaser的做法写出结果.



---
