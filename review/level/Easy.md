 
 
 
## Easy (182)
**0. [Convert Integer A to Integer B.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Integer%20A%20to%20Integer%20B.java)**      Level: Easy      Tags: [Bit Manipulation]
      
把Integer A 转换成 Integer B 需要改变多少bits?

#### Bit Manipulation
- a^b 显示出bit format里面有不同binary code的数位.
- 每次 (a^b)>>i 移动i位之后, 再 & 1时其实是指留下这一位的数字.
- count 
- 其实用到了 ^ 找不同的bit, >> 移位, &1 mask



---

**1. [Closest Number in Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Number%20in%20Sorted%20Array.java)**      Level: Easy      Tags: [Binary Search]
      
- Binary Search 的一种变型, LintCode无法再跑一边.
- 考虑mid-1, mid+1.
- 一旦没有mid = target.index。 那么target最终就narrow down在(mid-1,mid) 或者(mid,mid+1)   



---

**2. [Missing Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Missing%20Number.java)**      Level: Easy      Tags: [Array, Bit Manipulation, Math]
      
给一串unique数字, 数字取自 [0 ~ n], 无序, 找第一个skipped的数字.

#### Swap 
- 跟First Missing Positive 非常像, 只有一行代码的区别.
- swap 所有的数字, 到自己的correct position
- 最后一个for loop找到错位的index, 也就是缺的数字.

#### Bit Manipulation
- XOR will only retain bits that are different 1 ^ 0 = 1, but 0^0, 1^1 == 0
- Use that feature, 把所有value都和index XOR了
- 剩下的多余的数字, 其实是那个index无法被XOR消掉, 也就是那个缺的number value.
- 注意: 题目告诉数字是 [0 ~ n], 然而缺一个数字, 那么在[0 ~ n - 1] 里面, 最大的数字(不管缺没缺), 一定是 n = nums.length.

#### HastSet
- 全存, 找missing
- O(n) space, 不合题意

#### sorting
- sort, 找1st missing
- O(n log n) 太慢, 不合题意



---

**3. [Reverse String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20String.java)**      Level: Easy      Tags: [String, Two Pointers]
      
Similar to Reverse Integer.
可以用StringBuffer, 也可以two pointer reverse head/tail



---

**4. [Trailing Zeros.java](https://github.com/awangdev/LintCode/blob/master/Java/Trailing%20Zeros.java)**      Level: Easy      Tags: [Math]
      


---

**5. [Word Pattern.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Pattern.java)**      Level: Easy      Tags: []
      
每个char代表一个pattern。用HashMap<char, str>.
但不够，如果a也match dog, b也match dog, 纠错了。比如pattern = "abba", str = "dog dog dog dog"。
因此第二个HashMap<str, char> 反过来。
确保pattern和str一一对应。



---

**6. [Two Sum IV - Input is a BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Sum%20IV%20-%20Input%20is%20a%20BST.java)**      Level: Easy      Tags: [Tree]
      
HashSet to store visited items. Same old 2 sum trick.



---

**7. [Count 1 in Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%201%20in%20Binary.java)**      Level: Easy      Tags: [Bit Manipulation]
      
count 一个 32-bit number binary format 里面有多少1

#### Bit Manipulation
- shift >> i 
- apply mask & 1

#### Convert to string O(n) space
可以把integer -> string -> char array.



---

**8. [Hamming Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Hamming%20Distance.java)**      Level: Easy      Tags: []
      
bit: XOR, &, shift>>



---

**9. [Plus One.java](https://github.com/awangdev/LintCode/blob/master/Java/Plus%20One.java)**      Level: Easy      Tags: [Array, Math]
      
简单的实现, 加1, 进位. 唯一取巧的地方, 最后如果要多一位, 一定是10000...这个模式, 可以走捷径, 直接来个+1size的array, 然后第一位=1.
注意,转换成long也不合理,用太多memory.


---

**10. [Paint Fence.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20Fence.java)**      Level: Easy      Tags: [DP, Sequence DP]
      

#### DP
- 最多2个fence 颜色相同
- 假设i是和 i-1不同，那么结果就是 (k-1)*dp[i - 1]
- 假设i是何 i-1相同，那么根据条件，i-1和i-2肯定不同。那么所有的结果就是(k-1)*dp[i-2]
- dp[i]: count # of ways to paint 前i个 fence
- 加法原理
- time, space: O(n)
- rolling array: space O(1)

#### Previous Notes
- 这题目很有意思. 一开始分析的太复杂, 最后按照这个哥们的想法（http://yuanhsh.iteye.com/blog/2219891） 的来做，反而简单了许多。
- 设定T（n）的做法，最后题目化简以后就跟Fibonacci number一样一样的。详细分析如下。
- 做完，还是觉得如有神。本来是个Easy题，想不到，就是搞不出。




---

**11. [Minimum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Subarray.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Subarray]
      

给一串数组, unsorted, can have negative/positive num. 找数组中间 subarray 数字之和的最小值

#### DP
- 看到 min value, 至少考虑dp:
- Consider last num: min sum will be (preMinSum + curr, or curr)
- Use preMinSum to cache previouly calcualted min sum, also compare with +curr.
- Have a global min to track: because the preMinSum can be dis-continuous. 
- 也可以写成 dp[i] 但是没什么必要



---

**12. [Binary Gap.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Gap.java)**      Level: Easy      Tags: [Bit Manipulation]
      

#### Bit Manipulation
- 理解Binary Gap的描述
- 简单的 `>>`, `&1`, track start and end point 就好了



---

**13. [Subtree of Another Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Subtree%20of%20Another%20Tree.java)**      Level: Easy      Tags: [DFS, Divide and Conquer, Tree]
      
#### Tree 
- Traverse tree: left, right
- Concept of partial compare vs. whole compare



---

**14. [Maximum Average Subarray I.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20I.java)**      Level: Easy      Tags: [Array, Subarray]
      

简单的求sum of fixed window k, 同时求max avg, 结尾求余数就好.



---

**15. [IndexMatch.java](https://github.com/awangdev/LintCode/blob/master/Java/IndexMatch.java)**      Level: Easy      Tags: []
      
有序, 假设有这样的数字:target.        
target 左边的数字，一定不比index大，target右边的数字，一定比index大。     
这样可以binary search.O(logn)



---

**16. [Count and Say.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20and%20Say.java)**      Level: Easy      Tags: [Basic Implementation, String]
      
介绍一种count数字的方法, 然后每一行读出上一行的结果, 一行一行推算. 问nth行是啥样?

#### Basic Implementation
- 主要是题意很难理解, 非常misleading, 等到看明白题目, 其实没有什么算法要求.
- Count duplicates and print



---

**17. [Reshape the Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Reshape%20the%20Matrix.java)**      Level: Easy      Tags: []
      
读例子理解题意.
理清counter case. Basic implementation



---

**18. [O(1) Check Power of 2.java](https://github.com/awangdev/LintCode/blob/master/Java/O(1)%20Check%20Power%20of%202.java)**      Level: Easy      Tags: [Bit Manipulation]
      


---

**19. [Backspace String Compare.java](https://github.com/awangdev/LintCode/blob/master/Java/Backspace%20String%20Compare.java)**      Level: Easy      Tags: [Stack, Two Pointers]
      


---

**20. [Implement Stack using Queues.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack%20using%20Queues.java)**      Level: Easy      Tags: [Design, Stack]
      
如题.

#### Queue, 倒水
- 两个Queue,交互倒水
- 用一个Temp做swap

##### 做法1
- 逻辑在push里面:
- 1. x 放q2。
- 2. q1全部offer/append到q2.
- 3. 用一个Temp做swap q1, q2.
- q1的头，就一直是最后加进去的值.


##### 做法2
- 逻辑在top()/pop()里, 每次换水，查看末尾项.




---

**21. [Minimum Absolute Difference in BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Absolute%20Difference%20in%20BST.java)**      Level: Easy      Tags: [BST]
      
BST: inorder-traversal: 先left node(adding to stack till left leav), 再process stack.peek (mid node), 再 add rightNode && dive to rightNode.left leaf



---

**22. [HashWithArray.java](https://github.com/awangdev/LintCode/blob/master/Java/HashWithArray.java)**      Level: Easy      Tags: []
      



---

**23. [Flood Fill.java](https://github.com/awangdev/LintCode/blob/master/Java/Flood%20Fill.java)**      Level: Easy      Tags: [DFS]
      
Same as MS Paint

#### DFS 
- track `boolean[][] visited`, validate before dfs



---

**24. [Subtree.java](https://github.com/awangdev/LintCode/blob/master/Java/Subtree.java)**      Level: Easy      Tags: [DFS, Tree]
      
给一个binary tree s, 和一个binary tree t, 检查t是不是s的subtree.

#### DFS
- 跟 identical binary tree的写法很像
- 只有 current s.val = t.val 的时候才需要compare same tree.
- 其他情况, 继续recursively isSubtree
- 注意：即使找到T1 == T2, 但很可能只是数字相同（这里不是binary search tree!!）, 而children不同
- 所以同时要继续recursively isSubtree(T1.left, T2) ...etc.



---

**25. [Cosine Similarity.java](https://github.com/awangdev/LintCode/blob/master/Java/Cosine%20Similarity.java)**      Level: Easy      Tags: [Basic Implementation]
      
根据 Cosine Similarity 的公式, basic implementation



---

**26. [Longest Increasing Continuous subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence.java)**      Level: Easy      Tags: [Array, Coordinate DP, DP]
      
https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/

O(n)跑2遍for.
O(1)是用了两个int来存：每次到i点时，i点满足条件或不满足条件所有的longestIncreasingContinuousSubsequence.
特点：返跑一回，ans还是继续和left轮的ans作比较；求的所有情况的最大值嘛。



---

**27. [Max Area of Island.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Area%20of%20Island.java)**      Level: Easy      Tags: [Array, DFS]
      
#### DFS
- 虽然Easy, 但用到DFS最基本的想法.
- 1. dive deep
- 2. mark VISITED
- 3. sum it up
- Time: worst O(mn), traverse all possible nodes

- 更要注意, 要从符合条件value==1的地方开始dfs.
- 对于什么island都没有的情况，area应该位0， 而不是Integer.MIN_VALUE, 问清楚考官那小伙, 别写顺手。



---

**28. [Singleton.java](https://github.com/awangdev/LintCode/blob/master/Java/Singleton.java)**      Level: Easy      Tags: [Design]
      
让一个class 是 singleton



---

**29. [Permutation Index.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutation%20Index.java)**      Level: Easy      Tags: []
      
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

**30. [Convert Sorted Array to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [DFS, Divide and Conquer, Tree]
      
如题, build balanced BST from sorted array

#### DFS
- Binary Search Tree特点: 左边的node都比右边的node小. 
- height balance, subtree height 相差<1, 必须左右sub tree均分. 做DFS(num, start, end)
- 在每一个level, 找到中间点, 然后分割2半, 继续dfs
- Divide and Conquer
- time/space: O(n), visit all nodes, no redundant visits.



---

**31. [[tool] Quick Select - Median.java](https://github.com/awangdev/LintCode/blob/master/Java/[tool]%20Quick%20Select%20-%20Median.java)**      Level: Easy      Tags: [Array, Lint, Quick Select, Quick Sort, Two Pointers]
      

给一串无序数组, 找到median(sort之后 位置在中间的数字).

#### Quick Select
- 跟`kth largest element in an Array`的 template一样.
- quickSelect 可以找到 kth 最小的元素
    - 利用这个原理, 找这个kth最小值, 然后如果 == target index, 就找到了我们的median
- 主要步骤: 
    - 1. partition
    - 2. check end state `pivot index ?= target index`
    - 3. recursive call one part of the array 
- time: 与quickSort不同在于, 每次只要在一半list里面recurring, 所以把O(logn)的时间复杂度降到O(n)
    - n + n/2 + n/4 + n/8 + ....+ 1 = O(2n) = O(n)
- space: O(logn), based on recursive stacks




---

**32. [Swap Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Swap%20Bits.java)**      Level: Easy      Tags: [Bit Manipulation]
      
简单, 但是很多知识点:
1. Hex 0xaaaaaaaa 是1010101....1010; 0x55555555 是01010101....0101
2. 可以用这两个hex取单数和负数. 如果需要取其他的pattern, 也可以做.
3. x很可能是negative number, 所以right-shift 要用logic shift, >>> 避免leading负数补位.



---

**33. [Power of Two.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Two.java)**      Level: Easy      Tags: [Bit Manipulation, Math]
      
跟powerOfThree一样: 可以loop, check mod; 也可以用binary search找合适的数字.



---

**34. [Min Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/Min%20Stack.java)**      Level: Easy      Tags: [Design, Stack]
      
双Stack：一个正常stack，另一个minStack存当下level最小值. 注意维护minStack的变化

另外. 如果要maxStack，也是类似做法



---

**35. [Tweaked Identical Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Tweaked%20Identical%20Binary%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      
检查binary tree是否 identical. 

特点: subtree如果是有旋转的, 只要tree node value相等, 就可以算是identical

#### DFS
- 在DFS的基础上, 比对左左,左右,右左,右右



---

**36. [Insert Node in a Binary Search Tree .java](https://github.com/awangdev/LintCode/blob/master/Java/Insert%20Node%20in%20a%20Binary%20Search%20Tree%20.java)**      Level: Easy      Tags: [BST]
      
往Binary Search Tree里面加东西，一定会找到一个合适的leaf加上去。

那么：就是说someNode.left or someNode.right是null时，就是insert node的地方。

找到那个someNode就按照正常的Binary Search Tree规律。



---

**37. [Heaters.java](https://github.com/awangdev/LintCode/blob/master/Java/Heaters.java)**      Level: Easy      Tags: []
      
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

**38. [Classical Binary Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Classical%20Binary%20Search.java)**      Level: Easy      Tags: [Binary Search]
      
#### Binary Search Template
- while: start + 1 < end
- mid = start + (end - start) / 2;
- 根据mid作比较
- 末尾double check start, end.




---

**39. [Invert Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Invert%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      
#### DFS
- 简单处理swap
- recursively swap children

#### BFS
- BFS with Queue
- 每次process一个node, swap children; 然后把child加进queue里面
- 直到queue process完



---

**40. [Matrix Zigzag Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Matrix%20Zigzag%20Traversal.java)**      Level: Easy      Tags: []
      
分析4个step:right, left-bottom,down,right-up    
implement时注意index.有点耐心



---

**41. [Implement Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack.java)**      Level: Easy      Tags: [Stack]
      
随便用一个data structure, implement stack.

#### Stack, 先入, 后出
- ArrayList: return/remove ArrayList的末尾项。
- 2 Queues



---

**42. [Merge Two Binary Trees.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Two%20Binary%20Trees.java)**      Level: Easy      Tags: [DFS, Tree]
      
#### DFS
- 基础binary tree traversal. 注意对null child的判断



---

**43. [Power of Three.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Three.java)**      Level: Easy      Tags: [Math]
      
方法1:
Power of 3:  3 ^ x == n ?
意思是 n / 3 一直除, 最后是可以等于1的, 那么就有了 n/=3, check n%3, 最后看结果是不是整除到1的做法. 用while loop.

方法2:
如果n是power of 3, 那么 3 ^ x的这个 x一定是个比n小的数字. 那么可以在 0 ~ n 之间做binary serach, 但是就比较慢.

方法3:
巧妙的想法.最大的3^x integer是 3^19. 那么找到这个数, 一定可以被n整除. 一步到位.



---

**44. [Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20II.java)**      Level: Easy      Tags: [Backtracking, DFS, Tree]
      
给一个inputSum, 然后dfs, 找到所有path, 满足: path sum 跟 inputSum 一样.

#### DFS, Backtracking
- 用remaining sum 来检测是否满足 input path sum 条件
- 满足的时候add to result list
- 两种backtracking:
- 1. backtrack 当下node, 加进list, 然后dfs. dfs结束后删掉之前加进去的元素. 非常clean.
- 2. backtrack 下一个dfs level增加的value. dfs return 之后, 删掉list里面的末尾元素: 但是删掉的dfs余下的value.
- 第一种backtrack更加好掌握.

#### Previous Notes:
- Binary Tree的一个基本题: 找到所有满足条件的path
- 遍历到底，比较sum vs. target
- 注意divide的情况。要把遍历的例子写写



---

**45. [Two Strings Are Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Strings%20Are%20Anagrams.java)**      Level: Easy      Tags: []
      
方法1:char ascii 用count[256]   
坑：不要想象这个是个26letter lowercase. may not be true.

方法2: 若是其他字符encoding, 而不只是utf16-encoding (java char)?   
那么就继续用string去做



---

**46. [[HackerRank]. Change to Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/[HackerRank].%20Change%20to%20Anagram.java)**      Level: Easy      Tags: [String]
      
HackerRank里面的random 题目: 给一个string, 一切成两半, 看两半要变化多少个字符, 能变成anagram.

- 切两半成两个String A,B. 分别在int count[26]里面++, --.
- 记录 26个小写字母的频率. 如果全部抵消, 就是anagram.
- 注意: 最后count出来要除以2：字母不同，会在不同的字母位上加减count,那么就是刚好重复计算了一遍。所以除以二

- Note: HackerRank里要注意自己写: Scanner, import java.util, non-static method ...etc.



---

**47. [Implement Queue using Stacks.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Queue%20using%20Stacks.java)**      Level: Easy      Tags: [Design, Stack]
      
#### 双Stack
画图, 知道最后maintain的stack是那个 reverseStack: pop(), peek(), empty() 都在这个stack上, 无需变换.
push()里面做stack和reverseStack的来回倾倒.
相比老的code, 在PUSH里面做倾倒, 更容易读.

#### Previous notes
双Stack. 一个是等于是queue，一个是backfillStack.
Tricky: 是在pop()和peek()的时候backfill, 并且要等到stack用完再backfill.
写一下例子就知道，如果提早backfill，stack.peek()就不是queue的head了.




---

**48. [Guess Number Higher or Lower.java](https://github.com/awangdev/LintCode/blob/master/Java/Guess%20Number%20Higher%20or%20Lower.java)**      Level: Easy      Tags: [Binary Search]
      
binary search 公式



---

**49. [Partition Array by Odd and Even.java](https://github.com/awangdev/LintCode/blob/master/Java/Partition%20Array%20by%20Odd%20and%20Even.java)**      Level: Easy      Tags: [Array, Two Pointers]
      
- 更正常的start/end partition pointer类似: when condition meet, swap
- Clean up TODO



---

**50. [Add Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Digits.java)**      Level: Easy      Tags: [Math]
      
方法1: 普通做法就是按照题意, double-while loop把数字加起来. 第一层循环是O(n), 然后第二层循环就少很多数位, overall O(n)

方法2: 找数学规律, 每过9个数字, 取mod就会开始重复, 所以给所有数字取mod 就可以间接找到答案. O(1)



---

**51. [Last Position of Target.java](https://github.com/awangdev/LintCode/blob/master/Java/Last%20Position%20of%20Target.java)**      Level: Easy      Tags: [Binary Search]
      
给一个sorted integer array, 找target出现的最后的index. array 里有重复数字

有重复,不是末尾点，继续binary search



---

**52. [Path Sum III.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20III.java)**      Level: Easy      Tags: [DFS, Double Recursive, Tree]
      
count所有存在的 path sum == target sum. 可以从任意点开始. 但是只能parent -> child .

#### DFS
- 对所给的input sum 做减法, 知道 sum 达到一个目标值截止
- 因为可以从任意点开始, 所以当sum达标时候, 需要继续recursive, 从而找到所有情况 (有正负数, sum可能继续增加/减少)
- 经典的 helper dfs recursive + self recursive
- 1. helper dfs recursive 处理包括root的情况
- 2. self recursive 来引领  skip root的情况.

#### 特点
- 与 `Binary Tree Longest Consecutive Sequence II` 在recursive的做法上很相似: 
- 利用dfs做包括root的recursive computation
- 利用这个function自己, 做`不包括root的recursive computation`



---

**53. [Complete Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Complete%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, Tree]
      
A complete binary tree is a binary tree in which every level, except possibly the last,

is completely filled, and all nodes are as far left as possible

#### BFS
- 当出现了第一次有 null children的node的时候, 说明到了leaf level, mark flag = true;
- 自此以后，queue再不该有node再有child; queue后面出现的node的left/right child应该都是null
- 否则就是有问题, return false;




---

**54. [Sum of Two Integers.java](https://github.com/awangdev/LintCode/blob/master/Java/Sum%20of%20Two%20Integers.java)**      Level: Easy      Tags: [Bit Manipulation]
      
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

**55. [Search Insert Position.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20Insert%20Position.java)**      Level: Easy      Tags: []
      
一般的binary search.
在结尾判断该return 哪个position。


---

**56. [Longest Univalue Path.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Univalue%20Path.java)**      Level: Easy      Tags: []
      
弄明白path的意义: 连接node的edge.
要找MAX, 可以在class scope里面定义一个max variable.

用minimum amount of code来概括几种不同的情况: left == root, right == root, or left == root == right.



---

**57. [Trim a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Trim%20a%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [BST, Tree]
      
方法1:
适合复习BST. 用DFS对待每个node. 注意BST的特征: 所有left nodes都小于当下node, 所有right nodes都大于当下node.

根据题意用[L,R]切割.如果node.val<L, 直接连node带左边全丢掉, return node.right. 处理R也是一样.
分制是, DFS leftNode, rightNode. 然后接在node.left, node.right.

方法2: 用迭代, 还没有写.



---

**58. [Excel Sheet Column Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Excel%20Sheet%20Column%20Number.java)**      Level: Easy      Tags: [Math]
      
#### Math
- 26位的运算, 根据10位运算去思考
- 'A' - 'A' = 0. 所以 char - 'A' + 1 = 题目里的对应数位
- 或者: 26位运算和10位一样:num += 每位的digit * Math.pow(26, 数位号)




---

**59. [String Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/String%20Permutation.java)**      Level: Easy      Tags: []
      
把#of occurrences 存进HashMap, 第一个string 做加法，第二个string做减法。最后看是否有不等于0的作判断。



---

**60. [Nim Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Nim%20Game.java)**      Level: Easy      Tags: [Brainteaser, DP, Game Theory]
      
#### Brainteaser
- 著名Nim游戏
- 写一些，发现n=4,5,6,7,8...etc之后的情况有规律性: 谁先手拿到4就输了.
- 最终很简单n%4!=0就可以了,  time, space O(1)

#### DP
- 正规地找规律做, 就跟 coins in a line 一样, 按照先手后手来做
- 可以rolling array 优化空间
- Time O(n), 当然啦, 这个题目这样会timeout, 可以使用brainteaser的做法写出结果.



---

**61. [[lint]. Nth to Last Node in List.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Nth%20to%20Last%20Node%20in%20List.java)**      Level: Easy      Tags: [Linked List, Lint]
      
#### Linked List
- 先找到nth node
- 然后head开始跑
- node 到底，而head ~ node刚好是 n 距离。所以head就是要找的last nth



---

**62. [[lint]. Compare Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Compare%20Strings.java)**      Level: Easy      Tags: [Lint, String]
      
看StringA是不是包括所有 StringB的字符. Anagram

#### Basic Implementation
- 比较一下大小, null.
- 然后用int[]来count chars from A, count[x]++. 再对照chars in B, count[x]--
- 如果 count[c] < 0, 就 false.
- O(n)



---

**63. [[lint]. Longest Words.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Longest%20Words.java)**      Level: Easy      Tags: [Hash Table, Lint, String]
      
给一串String, 找到最长的长度, 把最长的String全都return

#### Hash Table
- <Integer,List<String>>
- 存最长值, 最后map.get(max) 



---

**64. [[lint]. Unique Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Unique%20Characters.java)**      Level: Easy      Tags: [Array, Lint, String]
      
determine if characters are unique in string

#### HashSet
- space O(n), time O(n)

#### char[]
- space O(n), time O(nlogn)

#### no additional data structure
- double for loop:  O(n^2)




---

**65. [[lint]. Lowest Common Ancestor II.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Lowest%20Common%20Ancestor%20II.java)**      Level: Easy      Tags: [Hash Table, Lint, Tree]
      
给一个Binary Tree root, 以及两个node A, B. 特点: node里面存了parent pointer. 找 lowest common ancestor


#### Hash Set
- 这个题有个奇葩的地方, 每个node还有一个parent, 所以可以自底向上.
- save visited in hashset. 第一个duplicate, 就是A B 的 lowest common ancestor

#### Save in lists
- 自底向上。利用parent往root方向返回
- 把所有parent存下来, 然后在两个list里面找最后一个 common node

#### 注意
- 无法从root去直接搜target node 而做成两个list. 因为根本不是Binary Search Tree！




---

**66. [[lint]. Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Subarray%20Sum.java)**      Level: Easy      Tags: [Array, Hash Table, Lint, PreSum, Subarray]
      

给一串数字, 找其中的一个subarray的 [start, end] index, 条件: subarary sum == 0.

#### Hash Table
- `subarray sum equals k` 的简单版: k = 0
    - 求preSum, 然后不断check `map.containsKey(preSum - k)`. 
    - 如果 `priorSum = preSum - k == 0`, 说明 [priorSum.index + 1, curr index] 就是我们要找的这一段

#### Previous notes, same preSum + map solution
- 分析出，如果sum[0~a]=x, 然后sum[0~b]=x, 说明sum[a+1 ~ b] == 0
- 用hashMap存每个sum[0~i]的值和index i. 如果有重复，就找到了一组sum为0的数组.



---

**67. [[lint]. Recover Rotated Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Recover%20Rotated%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Lint]
      
rotate的意思，是有个点断开，把一边的array节选出来放在另外一边。
Rotate三步：
rotate前半
rotate后半
rotate全部

注意先找到断点。


---

**68. [[tool]. Hash Function.java](https://github.com/awangdev/LintCode/blob/master/Java/[tool].%20Hash%20Function.java)**      Level: Easy      Tags: [Hash Table, Lint]
      

In general, there is no universal recipe to stick to when it comes to implementing hashCode().
https://www.baeldung.com/java-hashcode

#### Hash Function
- 解释Hash怎么做. 
- Hash function例子：    
- hashcode("abcd") = (ascii(a) * 33^3 + ascii(b) * 33^2 + ascii(c) *33^1 + ascii(d)*33^0) % HASH_SIZE 
- 用到的参数比如: magic number 33, HASH_SIZE.

- Hash的意义是：给一个string key, 转换成数字，从而把size变得更小。    
- 真实的implementation还要处理collision, 可能需要design hash function 等等。


##### 每一步都%HASH_SIZE的原因
- hashRst = hashRst * 33 + (int)(key[i]);       
- hashRst = hashRst % HASH_SIZE;       
- 原因是，hashRst会变得太大，所以不能算完和 再 %...



---

**69. [36. Valid Sudoku.java](https://github.com/awangdev/LintCode/blob/master/Java/36.%20Valid%20Sudoku.java)**      Level: Easy      Tags: [Enumeration, Hash Table]
      

#### Hash Set
- 用HashSet存visited row/col/block.
    - 在nest for loop里面validate row,col,and block.     
    - Special: validate block要利用i 和 j 增长的规律   
- i, j are [0~n) can build block boundary in a for loop:
    - `int c = 3 * (i % 3) + j % 3;` //make use of how i and j increases
    - `int r = 3 * (i / 3) + j / 3;`

#### A bit Slower approach
- 单独做block validation: validate block的时候虽然看到了4层for. 其实也就是n^2
- 可能代码稍微复杂一点



---

**70. [359. Logger Rate Limiter.java](https://github.com/awangdev/LintCode/blob/master/Java/359.%20Logger%20Rate%20Limiter.java)**      Level: Easy      Tags: [Design, Hash Table]
      

#### HashMap<Message, Timestamp>
- map: avoid duplicate message, records timestamp for validation
- time: O(1)
- space: O(n)

#### Queue + Set
- 1) keep a trimmed queue and set (all tasks to be within 10 secs); 
- 2) use set to O(1) check if incoming message exists.
- time: O(x), trimQueue()
- space: O(n)



---

**71. [198. House Robber.java](https://github.com/awangdev/LintCode/blob/master/Java/198.%20House%20Robber.java)**      Level: Easy      Tags: [DP, Sequence DP, Status DP]
      

搜刮房子, 相邻的不能碰. 每个房子里有value, 求max.

#### Sequence DP
- dp[i]: 前i个房子拿到的max gain
- 看最后结尾状态的前一个或前两个的情况，再综合考虑当下的
- 搞清楚当下[i]的和之前[i-x]的情况的关系: 不可以连着house, 那么就直接考虑 dp[i-2]的情况
- Sequence DP, new dp[n + 1];
- Rolling Array
    - [i]'只和前两个位子 [i-1], [i - 2]'相关
    - 用%2来标记 [i], [i - 1], [i - 2]三个位置.
    - 其他滚动时惯用curr/prev来表示坐标, 这里%2虽然抽象, 但是更加实用.

#### Method2: Status DP
- dp[i] depends on nums[i-1] or nums[i-2] based on the state at (i-1)
    - use dp[n][2] to store dp[i] and stages
    - dp[0][0] = 0; dp[0][1] = nums[0]
- calculation
    - dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]). The prior house can be either state.
    - dp[i][1] = dp[i - 1][0] + nums[i]. The prior house must be `NOT ROBBED`.
- return `Math.max(dp[n - 1][0], dp[n - 1][1])`



---

**72. [21. Merge Two Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/21.%20Merge%20Two%20Sorted%20Lists.java)**      Level: Easy      Tags: [Linked List]
      

如题

#### Basics
- 小的放前。每次比head大小 
- while过后，把没完的list一口气接上。   
- 一开始建一个node用来跑路, 每次都存node.next = xxx。存一个dummy。用来return dummy.next.



---

**73. [788. Rotated Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/788.%20Rotated%20Digits.java)**      Level: Easy      Tags: [Basic Implementation, String]
      

#### Basic Implementation of the rules
- [3,4,7] -> cannot rotate, failures. Must NOT have. set1
- 2,5,6,9 -> good candidates. Must have 1. set2
- [0,1,8] -> goes back to itself. can have
- loop over [1, N], count=int[10] appearance.
    - set1 meet 0
    - set2 meet at least 1
    


---

**74. [237. Delete Node in a Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/237.%20Delete%20Node%20in%20a%20Linked%20List.java)**      Level: Easy      Tags: [Linked List]
      
Given Singlely linked list, 删除一个任意node (不能是head node)

#### Basic
- update node.val
- Link curr.next to curr.next.next



---

**75. [448. Find All Numbers Disappeared in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/448.%20Find%20All%20Numbers%20Disappeared%20in%20an%20Array.java)**      Level: Easy      Tags: [Array, Bucket Sort]
      

#### Method1: Bucket Sort concept, set val to its correct position
- Given: values are [1,n], so val can represent index. Therefore, set val to its correct position
- 小心handle i:
    - value是 1-based
    - 每次换位, 需要`i--`, 重新省察`nums[i]`

#### Method2: 做标记 (negative number, or super large number)
- Option1: use negative number to mark visited:
    - 很巧妙地运用了标记的方法, 标记成负数，证明visit过。
    - Preserve原数的负数，这样可以继续用此负数的绝对值来寻找原数所该被定的位置。非常巧妙！
- Option2: use large number (larger than n)
    - 跟方法2类似，也是做标记，这一次是加上一个大于n的数（因为题目给了n的border），最后check一下就好。为不超Integer.MAX_VALUE, 每次加n前，取余数。
    - 做标记的方法固然快，但是相对来说比较hacky，在常规的代码中，估计不会用到.




---

**76. [849. Maximize Distance to Closest Person.java](https://github.com/awangdev/LintCode/blob/master/Java/849.%20Maximize%20Distance%20to%20Closest%20Person.java)**      Level: Easy      Tags: [Array, Basic Implementation, Two Pointers]
      

给一排座位, 一个人去坐: 找离两边的人都最远的地方(中间点), return 跟旁边人的最大distance

是Exam Room 的同种概念, 简单化题目: 这里只考虑一个人就好了

#### Basic Implementation, Two Pointers: start/end
- start/end point, 然后比较大小记录dist
    - 注意1: 如果第一个座位没有人, 特殊处理, dist = [0 ~ end]
    - 注意2: 如果最后一个座位没有人, 特殊处理: dist = [n - 1 - start];
- 其余: `dist = Math.max(dist, (end - start) / 2)`
- 相关题目: 几乎同样概念 `Binary Gap`, 升级复杂版`Exam Room`




---

**77. [408. Valid Word Abbreviation.java](https://github.com/awangdev/LintCode/blob/master/Java/408.%20Valid%20Word%20Abbreviation.java)**      Level: Easy      Tags: [Basic Implementation, String]
      
tricky: find integer within a string
edge case: leading '0' should not be allow in such abbr.



---

**78. [415. Add Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/415.%20Add%20Strings.java)**      Level: Easy      Tags: [Basic Implementation, Math, String]
      

#### Two Pointer 
- Use i, j to process from end of 2 strings
- handle edge case for i, j
    - if i < 0, its num = 0 (since we are doing sum, blindly setting 0 is okay)
- Note: `sb.insert(0, x)` is much slower than doing a final `sb.reverse()`

#### If manually convertin to int[]
1. when converting to int[], remember to reverse string.
1. when converting to int[], remember to reserve extra space for carry



---

**79. [83. Remove Duplicates from Sorted List.java](https://github.com/awangdev/LintCode/blob/master/Java/83.%20Remove%20Duplicates%20from%20Sorted%20List.java)**      Level: Easy      Tags: [Linked List]
      
从Linked list 里面摘掉重复元素, 只留下unique元素.

#### Linked List
- sorted list, 重复元素都在一起
- 知道如何构建Linked List.
- 一点遇到重复元素: node.val == node.next.val, 就去掉.
- 用一个dummy node 来跑路
- 注意:
- 只有当没有重复的时候, 才node = node.next; 
- 有重复的时候, 当后面第三个元素被提上来之后, 还是可能跟当下元素重复, 所以不能前移node.
- ex: A -> A -> A
- while loop 里面check node 和 node.next 比较好, 这样ending position会非常清晰



---

**80. [1108. Defanging an IP Address.java](https://github.com/awangdev/LintCode/blob/master/Java/1108.%20Defanging%20an%20IP%20Address.java)**      Level: Easy      Tags: [Basic Implementation, String]
      


---

**81. [1021. Remove Outermost Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/1021.%20Remove%20Outermost%20Parentheses.java)**      Level: Easy      Tags: [Stack]
      
#### Stack
- use stack to hold potential pair
- when stack is empty: detect outtermost element, dont add to final result
- time: O(n), space O(n)

#### Count occurance
- solution from discussion, time O(n), space O(1)
- save space, but less scalable: think about if there are 100 different pairs, then the couting will be a bit complex to handle.



---

**82. [766. Toeplitz Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/766.%20Toeplitz%20Matrix.java)**      Level: Easy      Tags: [Array]
      

#### Check diagonal
- 似乎没什么算法特点, 就是array基本运算, 然后分割成一个helper function去做重复计算, 剪短代码.
- 注意check MxN 的分界线.

#### Simpler Solution
- the goal is to check [i][j] == [i+1][j+1] for every i and j.



---

**83. [953. Verifying an Alien Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/953.%20Verifying%20an%20Alien%20Dictionary.java)**      Level: Easy      Tags: [Hash Table]
      

#### Hash Table
- mark the char position
- check adjacent words
- Optimization
    - a) If s1 equals s2, just return true, no need to continue.
    - b) if s2 (app) is a substring of s1(apple), just return false.




---

**84. [1213. Intersection of Three Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/1213.%20Intersection%20of%20Three%20Sorted%20Arrays.java)**      Level: Easy      Tags: [Hash Table, Two Pointers]
      

Very similar to 349.Intersection of Two Arrays.

#### Hash Table
- Use set to check
- Verify duplicates at end rst

#### Two Pointers
- similar to Intersection of Two Sorted Arrays
- Start from front/back, process 1 item at a time
    - if match, move all pointers 
- Optoin1: check from back
- Optoin2: check from frotn



---

**85. [383. Ransom Note.java](https://github.com/awangdev/LintCode/blob/master/Java/383.%20Ransom%20Note.java)**      Level: Easy      Tags: [Basic Implementation, String]
      
count chars in int[256]



---

**86. [252. Meeting Rooms.java](https://github.com/awangdev/LintCode/blob/master/Java/252.%20Meeting%20Rooms.java)**      Level: Easy      Tags: [PriorityQueue, Sort, Sweep Line]
      

- 注意接头点要考虑所有开会结会的情况，不要恰巧漏掉相接的点
- 开会的是超人。瞬间移动接上下一个会议

#### Method1: sort input and compare if curr.end & next.start overlaps
- sort: `Arrays.sort(intervals, Comparator.comparing(i -> i[0]))`
- time: O(nlogn), space: O(1)

#### Method2: Sweep line
- class Point{pos, flag}, PriorityQueue排序。计算count
- 跟 Number of Airplanes in the Sky 是一个类型的题目
- time: O(nlogn), space O(n)
- Not necessary for this problem, since it requires extra space with pq.



---

**87. [665. Non-decreasing Array.java](https://github.com/awangdev/LintCode/blob/master/Java/665.%20Non-decreasing%20Array.java)**      Level: Easy      Tags: [Array]
      

- 比较升序的时候, 必须要估计到 `i-1, i, i+1` 三个数位.
- 写出来`i-1, i, i+1`之间的关系, 然后做合理的fix.
    1. reduce nums[i+1] to fix
    1. raise nums[i+1] to fix
- 需要真的fix数组, 因为loop through做比较时会用到fix后的数字.




---

**88. [293. Flip Game.java](https://github.com/awangdev/LintCode/blob/master/Java/293.%20Flip%20Game.java)**      Level: Easy      Tags: [String]
      
#### String
- 可以用 sb.replace(i, j, "replacement string")
- 简单按 window=2 来扫描
- 原来只需要从'++'转到'--'的情况
- O(n)



---

**89. [686. Repeated String Match.java](https://github.com/awangdev/LintCode/blob/master/Java/686.%20Repeated%20String%20Match.java)**      Level: Easy      Tags: [Basic Implementation, Edge Case, String]
      
Track: 纸上分析edge case.
Validation helps speed it up.



---

**90. [111. Minimum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/111.%20Minimum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

#### BFS
- Shortest path; minimum depth: 想到BFS, check level by level, BFS更能确保更快找到结果
- depth definition: reach to a leaf node, where node.left == null && node.right == null
- BFS using queue, track level.

#### DFS
- Divide and Conquer to find min depth. 
    - if one of child is null, return the other child depth + 1
    - Pick the min of the two child depth + 1
- need to visit all nodes




---

**91. [7. Reverse Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/7.%20Reverse%20Integer.java)**      Level: Easy      Tags: [Math]
      

#### 方法1
每次加上x%10，然后x不断减小～0
注意处理MAX_VALUE, MIN_VALUE
符号不重要, 直接处理, 也会保留.

#### 方法2
转换成String 然后 reverse
Space O(n), time O(n)



---

**92. [303. Range Sum Query - Immutable.java](https://github.com/awangdev/LintCode/blob/master/Java/303.%20Range%20Sum%20Query%20-%20Immutable.java)**      Level: Easy      Tags: [DP, PreSum]
      

给一串数字, 求sumRange.

#### PreSum
- pre sum 的definition
- preSum也是dp[]一种最简易的形式把.
- dp[i], preSum[i]: 前(i-1)个元素的和.



---

**93. [674. Longest Continuous Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/674.%20Longest%20Continuous%20Increasing%20Subsequence.java)**      Level: Easy      Tags: [Array, Coordinate DP, DP, Sliding Window]
      

找连续的持续上升子序列的长度.

#### Sliding window
- update the window start index;
    - `left` in sliding window
    - update when we need to start a new range: `nums[i-1] >= nums[i]` 
- calculate the max distance `i - widowStart + 1`
- O(n) time and O(1) space

#### Simple Array solution
- size++ when meeting condition `nums[i] > nums[i - 1]`
- otherwise, reset size = 1
- track max all the way

#### Coordinate DP
- 1D coordinate, dp 的角标, 就是代表 index i 的状态
- 求最值, dp[i] = 在index i位置的最长子序列
    - 如果 nums[i] > nums[i - 1], dp[i] = dp[i - 1] + 1
    - 如果没有持续上升, 那么dp[i] = 1, 重头来过
- maintain max




---

**94. [485. Max Consecutive Ones.java](https://github.com/awangdev/LintCode/blob/master/Java/485.%20Max%20Consecutive%20Ones.java)**      Level: Easy      Tags: [Array, Basic Implementation]
      

- preserve max
- 清零count 



---

**95. [896. Monotonic Array.java](https://github.com/awangdev/LintCode/blob/master/Java/896.%20Monotonic%20Array.java)**      Level: Easy      Tags: [Array]
      
basic implementation



---

**96. [26.Remove Duplicates from Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/26.Remove%20Duplicates%20from%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Two Pointers]
      
给一个sorted array, 把重复的去掉: 也就是把不重复的按照顺序贴上来, array末尾多余的位置无所谓.

return unique item 的长度.

#### Two Pointers
- sorted array, 重复元素都在一起
- Two pointers 其实也可以是一个 for loop pointer, 另一个 dynamic variable.
- track unique index
- skip duplicated items
- O(n)

#### 思考模式:
- Remove Duplicate from Array 不同于remove from linked list.
- LinkedList里面我们是最好不要动node.val的，直接把node去掉。
- 而array我们很难直接把node去掉，又不能用新array，那么就要：
- 把不重复的element一个个放到最前面。
- 这个思想跟merge two sorted array （其中一个后续非常长的array可以放下arr1,arr2） 类似。
- 就是找个不会事后mess up，不会去动得index,把满足条件的element 填进去。这样保证了in place.
- *反向思维*：remove duplicate, 实际上也是找unique elements, and insert into original array



---

**97. [204. Count Primes.java](https://github.com/awangdev/LintCode/blob/master/Java/204.%20Count%20Primes.java)**      Level: Easy      Tags: [Hash Table, Math]
      
计数: 所有小于n的prime number.

#### prime number定义
- >=2的没有除自己和1以外公约数的数。   
- 还有另外一个定义方法: 这个n,有没有小于n的一个i, 而达到： i * i + # of i = n. 如果有，那就不是 prime   

#### Steps
- 一个boolean长条，存isPrime[]。 然后从i=2， 全部变true.
- hash key: the number itself
- 然后利用这个因子的性质，非prime满足条件： self*self, self * self + self ... etc.     
- 所以就check每一个j, j+i, j+i+i, 然后把所有non-prime全部mark成false.     
- 最后，数一遍还剩下的true个数就好了   



---

**98. [58. Length of Last Word.java](https://github.com/awangdev/LintCode/blob/master/Java/58.%20Length%20of%20Last%20Word.java)**      Level: Easy      Tags: [String]
      
给一个String, 里面有lower case character 和 ' '. 找最后一个单个word的长度

#### basics
- 从末尾找' ', 找到了计算长度
- 记得要s.trim(), 把首尾的space去掉



---

**99. [496. Next Greater Element I.java](https://github.com/awangdev/LintCode/blob/master/Java/496.%20Next%20Greater%20Element%20I.java)**      Level: Easy      Tags: [Hash Table, Stack]
      

#### stack
- use stack to hold all elements
    - keep poping if `stack.peek() < num`
    - use map to record (top, num)
- time O(n), run through base once and sub-sequence once
- space O(n), stack, map

#### HashMap
- O(n) space, O(n^2) time worst case



---

**100. [717. 1-bit and 2-bit Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/717.%201-bit%20and%202-bit%20Characters.java)**      Level: Easy      Tags: [Array]
      
理解题目: 
1. single-bit always starts with '0', two-bits always start with '1'.
1. Therefore there is ONLY 1 way to reach end.

#### 方法1
Greedy.
从第一个bit开始: 如果 % 2 == 1, 一定是跳两位; 如果0, 一定是跳一位.
loop to end, and see if index reaches the end.

#### 方法2
用DP硬做了一下: 
1. 如果i位是0, 那么前面dp[i-1]或者dp[i-2] true就够了.
2. 如果i位是1, 那么i-1位必须是1才满足规则, 并且 dp[i-2]需要true.



---

**101. [53. Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/53.%20Maximum%20Subarray.java)**      Level: Easy      Tags: [Array, DFS, DP, Divide and Conquer, PreSum, Sequence DP, Subarray]
      

给一串数组, unsorted, can have negative/positive num. 找数组中间 subarray 数字之和的最大值

#### PreSum
- 想着用一用prefix sum. 把值一个个叠加
- 然后presum[j] - presum[i- 1] 就是 (i,j)之间的和
- O(n^2), not as sufficient


#### Sequence DP
- dp[i]: last element(或包括前i个element), 可能组成的 subarray 的最大sum.
    - dp[i] = Math.max(dp[i-1]+lastElement, lastElement(drop dp[i-1]))
- init: 
    - dp = int[n + 1], 
    - dp[0]: first 0 items, does not have any sum
- 因为continous sequence, 所以不满足条件的时候, 会断. 
    - need to take curr num regardless => can drop prev max in dp[i]
- track overall max 
- init dp[0] = 0; max = MIN_VALUE 因为有负数
- Time, space O(n)
- Rolling array, space O(1)

#### Divide and Conquer, DFS
- 找一个mid piont, 考虑3种情况: 1) 只要左边, 2) 只要右边, 3) cross-mid
- left/rigth case: 直接 dfs
- corss-mid case: continuous sum max from left + continous sum max from right + mid
- continuous sum max from one direction:
- Worst case O(n^2): visit all nodes O(n); in dfs: calculates continuous sum (including mid), which is also O(n)


---

**102. [977. Squares of a Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/977.%20Squares%20of%20a%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Two Pointers]
      

#### Two Pointers
- negative index i, positive index j



---

**103. [824. Goat Latin.java](https://github.com/awangdev/LintCode/blob/master/Java/824.%20Goat%20Latin.java)**      Level: Easy      Tags: [Basic Implementation, String]
      



---

**104. [405. Convert a Number to Hexadecimal.java](https://github.com/awangdev/LintCode/blob/master/Java/405.%20Convert%20a%20Number%20to%20Hexadecimal.java)**      Level: Easy      Tags: [Bit Manipulation]
      
#### Unsigned Shift, Mask
- Move pointer: move digit after process 4 bits. 
    - `>>>` Unsigned right shift
    - always fills 0 irrespective of the sign of the number
- Mas: `num & 0xf` = `num & 15`



---

**105. [509. Fibonacci Number.java](https://github.com/awangdev/LintCode/blob/master/Java/509.%20Fibonacci%20Number.java)**      Level: Easy      Tags: [DP, Math, Memoization]
      
#### Memoization
- fib[n] = fibonacci(n - 1) + fibonacci(n - 2);

#### DP array.
- 滚动数组, 简化DP

#### recursively calculate
- recursively calculate fib(n - 1) + fib(n - 2). 公式没问题, 但是时间太长, timeout.




---

**106. [136. Single Number.java](https://github.com/awangdev/LintCode/blob/master/Java/136.%20Single%20Number.java)**      Level: Easy      Tags: [Bit Manipulation, Hash Table]
      
Bit XOR: 当两个bit不同时，return 1. 
题目正要消光所有重复出现的数儿留下出现一次的那个.



---

**107. [257. Binary Tree Paths.java](https://github.com/awangdev/LintCode/blob/master/Java/257.%20Binary%20Tree%20Paths.java)**      Level: Easy      Tags: [Backtracking, Binary Tree, DFS]
      


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

**108. [543. Diameter of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/543.%20Diameter%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [Tree]
      

找longest path (include or not include root)

跟Binary Tree Maximum Path Sum 的想法一样: 处理single path, 或者combined path (do not include curr root)

#### Singlepath and CombinedPath
- Option1: Use local single path max & global combined max
    - Since the local combined diameter is used for comparision, but not used for specific calculation
    - calculate path length (diameter), understand:
        - for single path: child single path value + 1 (curr node)
        - for combined path including curr node: left child single + right child path 
- Option2: record local combined and single path for each iteration
    - `int[]{combinedPath, singlePath}`;
    - single path: pick single path + 1: `singlePath = Math.max(left[1] , right[1]) + 1`;
    - combined path `combinedPath = Math.max(Math.max(left[0], right[0]), left[1] + right[1] + 1)`, find max from:
        - 1) complete left child combined path
        - 2) complete right child combined path
        - 3) combined path with curr root
    - Note: we treat a single node itself with diameter of 1, so we want to -1 in final result
        - problem statement wants the path length (not # of nodes or depth)



---

**109. [67. Add Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/67.%20Add%20Binary.java)**      Level: Easy      Tags: [Math, String, Two Pointers]
      
#### Two Pointers
- 注意加法结果的位置.
- Use two pointers i, j to track the 2 strings
- Add when i and j are applicable. While (i >= 0 || j >= 0)
- `StringBuffer.insert(0, x);`
- handle carry

#### reverse
- Reverse string -> Convert to Integer List, add up -> Convert back to string
- pointer 从前向后, 所以只需要 1个pointer.
- 操作复杂, 如上, 证明可以解决. 没必要reverse.

#### Incorrect: convert to Integer
把binary换成数字作加法. 如果input很大，那么很可能int,long都hold不住。不保险。



---

**110. [557. Reverse Words in a String III.java](https://github.com/awangdev/LintCode/blob/master/Java/557.%20Reverse%20Words%20in%20a%20String%20III.java)**      Level: Easy      Tags: [String]
      
给一个String, 里面的Word被single space split开来, 目的是reverse里面所有的Word, 但preserve Word 和 space order.

#### Reverse function
- Reverse Words in a String II 的降级版, 去掉第一个overall reverse就好了



---

**111. [203. Remove Linked List Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/203.%20Remove%20Linked%20List%20Elements.java)**      Level: Easy      Tags: [Linked List]
      
从linked list 里面去掉所有的 target

#### Basics
- 如果match: node.next = head.next;
- 如果不match, node 和 head 一起移动



---

**112. [266. Palindrome Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/266.%20Palindrome%20Permutation.java)**      Level: Easy      Tags: [Hash Table]
      

给String, 看permutation是否能是palindrome

#### Hash, or ASCII array
- count char occurrance
    - 只可以接受一个odd # appearance.
- 考虑所有 256 ASCII code, 如果还要拓展, 就用HashMap<Character, Integer>
- 注意, 不能assume lower case letter. 应该至少是所有ASCII code



---

**113. [339. Nested List Weight Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/339.%20Nested%20List%20Weight%20Sum.java)**      Level: Easy      Tags: [BFS, DFS, NestedInteger]
      

给一串integers, list里面可能有nest list. 算总的sum. 规则, 如果是nested list, 每深一个depth, sum要乘以depth.

#### DFS
- New interface to understand: object contains integer or object
    - Visit all && sum, consider dfs.
    - 简单的处理nested structure, dfs增加depth.
- time: visit all nodes eventually, O(n), space O(n)

#### BFS
- bfs, queue, 处理queue.size() for a level
- use a level variable to track levels
- slower since it uses extra space, worst case O(n) of all items



---

**114. [189. Rotate Array.java](https://github.com/awangdev/LintCode/blob/master/Java/189.%20Rotate%20Array.java)**      Level: Easy      Tags: [Array, Rotation]
      
#### Rotate array in place
- rotate all
- rotate 2 sides: < k or >= 


#### Rotate by buffer the k array



---

**115. [119. Pascal's Triangle II.java](https://github.com/awangdev/LintCode/blob/master/Java/119.%20Pascal's%20Triangle%20II.java)**      Level: Easy      Tags: [Array, Basic Implementation]
      

简单处理 list. code is very similar to Pascal triangle I.

- 注意 `list = Arrays.asList(x, y, z ...)` 给fixed-size list, 不能直接 list.add().
- Use `new ArrayList<>(Arrays.asList(...))` to wrap it up.




---

**116. [206. Reverse Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/206.%20Reverse%20Linked%20List.java)**      Level: Easy      Tags: [Linked List]
      
#### Iterative
- Linked List的基本操作: 每次insert在开头
- 用head来循环所有node
- 不需要额外空间
- Time O(n), Space O(1)

#### Recursive with a helper function
- source node: head
- target node: new head



---

**117. [168. Excel Sheet Column Title.java](https://github.com/awangdev/LintCode/blob/master/Java/168.%20Excel%20Sheet%20Column%20Title.java)**      Level: Easy      Tags: [Math]
      

#### 基本换算
- 26位, 像10位一样去思考
- 从末尾开始, mod %26 可以拿到 末尾数字 remain = n % 26
- 特殊: remain = 0 的时候, 也就是说n是26的倍数, 末尾应该是 'Z'
- 记录'Z'了之后, n--




---

**118. [104. Maximum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/104.%20Maximum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      
给一个binary tree, 找最深depth

#### DFS
- 这里要走过所有的node, 所以dfs非常合适
- Divide and conquer. 
- 维持一个最大值: Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
- 注意check root == null

#### Note
- BFS is doable as well, but a bit more code to write: tracks largest level we reach



---

**119. [349. Intersection of Two Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/349.%20Intersection%20of%20Two%20Arrays.java)**      Level: Easy      Tags: [Binary Search, Hash Table, Sort, Two Pointers]
      

#### Set
- 用到hashset找unique && duplicate: O(m+n)

#### Binary Search
- 可以用binary search 找数字. 
- Note:binary search一定需要array sorted: nLog(m)



---

**120. [443. String Compression.java](https://github.com/awangdev/LintCode/blob/master/Java/443.%20String%20Compression.java)**      Level: Easy      Tags: [Basic Implementation, String]
      


---

**121. [844. Backspace String Compare.java](https://github.com/awangdev/LintCode/blob/master/Java/844.%20Backspace%20String%20Compare.java)**      Level: Easy      Tags: [Stack, Two Pointers]
      

#### Method1: Two pointers to backtack from end of string
- time: O(n)
- space: O(1)

#### Method2: Stack
- need to remove entity just added 
- use stack to hold array content; pop if # is found



---

**122. [9. Palindrome Number.java](https://github.com/awangdev/LintCode/blob/master/Java/9.%20Palindrome%20Number.java)**      Level: Easy      Tags: [Math]
      
#### Reverse half of the number
- build reversed integer 直到midpoint, where x <= reverse
- 如果input双数: x == reverse
- 如果input单数 (而且x>reverse): x == reverse/10

#### Consider palindrome
- optionA: compare digit by digit
- optionB: reverse half of the string/int, and compare with other half.






---

**123. [771. Jewels and Stones.java](https://github.com/awangdev/LintCode/blob/master/Java/771.%20Jewels%20and%20Stones.java)**      Level: Easy      Tags: [Hash Table]
      

- 给J 和 S两个string. J里的character是unique 的珠宝, S 里面的character包含珠宝和石头. 找S里面有多少珠宝
- Basic HashSet



---

**124. [141. Linked List Cycle.java](https://github.com/awangdev/LintCode/blob/master/Java/141.%20Linked%20List%20Cycle.java)**      Level: Easy      Tags: [Cycle Detection, Linked List, Slow Fast Pointer, Two Pointers]
      

#### Method1: Two Pointer: Slow Fast Pointer
- Imagine two runners running on a track at different speed. What happens when the track is actually a circle?
- https://leetcode.com/problems/linked-list-cycle/solution/
- O(1) sapce: 用快慢指针, `start=head.next`, `end=head.next.next`
- Fast pointer will eventually catch up to slow pointer

#### Method1: Hash Table
- O(n) space: 用HashMap，一直add elements.  如果有重复，那么很显然是有Cycle



---

**125. [680. Valid Palindrome II.java](https://github.com/awangdev/LintCode/blob/master/Java/680.%20Valid%20Palindrome%20II.java)**      Level: Easy      Tags: [String]
      
#### Palindrome String
- delete an index: 有两种情况
- 用一个boolean parameter来表现state. 如果有其他status, state可以变成 String/enum



---

**126. [70. Climbing Stairs.java](https://github.com/awangdev/LintCode/blob/master/Java/70.%20Climbing%20Stairs.java)**      Level: Easy      Tags: [DP, Memoization, Sequence DP]
      
每一步可以走1步或者2步, 求总共多少种方法爬完梯子.

#### Recursive + Memoization
- 递归很好写, 但是重复计算, timeout. time: O(2^n)
- O(2^n): each n can spawn 2 dfs child, at next level, it will keep spawn. Total 2^n nodes will spawn.
- 用全局变量int[] memo 帮助减少重复计算
- O(n) time, space

#### DP
- 加法原理, 最后一步被前两种走法决定: dp[i] = dp[i - 1] + dp[i - 2]
- 基础sequence DP, int[] dp = int[n + 1];
- DP[]存的是以 1-based index的状态
- dp[i]: count # of ways to finish 前i个 台阶
- 需要知道dp[n] 的状态, 但是最大坐标是[n-1], 所以int[n+1]
    - dp[0]往往是有特殊状态的. 这里, dp[0]: 1种方式可以原地不动
    - dp[1]=1, 1种方式到达index=1, 
    - 之后的`dp[2] = dp[0]+dp[1]`: 就是dp[0]的走法 or dp[1]的走法
- O(n) space, time

#### 序列DP, 滚动数组
- [i] only associates with [i-2], [i-1].
- %2
- O(1) space



---

**127. [747. Largest Number At Least Twice of Others.java](https://github.com/awangdev/LintCode/blob/master/Java/747.%20Largest%20Number%20At%20Least%20Twice%20of%20Others.java)**      Level: Easy      Tags: [Array]
      

多种简单操作:
- O(n) solution: 找最大值, 和第二大的值, 看是否符合题意, 就行了.
- O(2n) 最简单方法: 可以loop 两遍: 找最值; 作比较.
- O(2n) 举反例: 有一个不满足, 就够反对这个'at least twice of alllll others'.



---

**128. [561. Array Partition I.java](https://github.com/awangdev/LintCode/blob/master/Java/561.%20Array%20Partition%20I.java)**      Level: Easy      Tags: [Array]
      

给串数字, size=2n, 找pairs, 然后需要sum of min(pair) 最大.

(a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

#### Sort, basics
- 从结果出发, 只需要找到加法的结果，而不强调具体配对.
- 写一写example发现规律: 升序排列会让 `高位的min(pair)` 最大化, 于是`一言不合先排列`
- 找到排列取单数位的规律，再考虑负数和正数的相同规律，即可找到排列求解的方法。
- sort, O(nlogn)




---

**129. [387. First Unique Character in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/387.%20First%20Unique%20Character%20in%20a%20String.java)**      Level: Easy      Tags: [Hash Table, String]
      

#### Count appearance with int[256]
- 按照题意, 找到第一个 first index == last index的字母.

#### Count appearance with hashmap (more scalable)
- 用hashmap存字母的index, 有些重复字母的index就会是个list. 
- 找到单一index, 结合成list, sort, return list.get(0)
- slow due 



---

**130. [345. Reverse Vowels of a String.java](https://github.com/awangdev/LintCode/blob/master/Java/345.%20Reverse%20Vowels%20of%20a%20String.java)**      Level: Easy      Tags: [String, Two Pointers]
      
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

**131. [367. Valid Perfect Square.java](https://github.com/awangdev/LintCode/blob/master/Java/367.%20Valid%20Perfect%20Square.java)**      Level: Easy      Tags: [Binary Search, Math]
      

#### Binary找sqrt
- binary search template: mid+1, mid-1, `start <= end`
- define index as long. 



---

**132. [270. Closest Binary Search Tree Value.java](https://github.com/awangdev/LintCode/blob/master/Java/270.%20Closest%20Binary%20Search%20Tree%20Value.java)**      Level: Easy      Tags: [BST, Binary Search, Tree]
      

给一个BST, 和一个double target, 走位找到最接近的number.

Concept: Iterate over all logN nodes in the BST and record the closest. Rather than finding the value at +/- 0.5 precision

#### Binary Search
- 记录找到过的closest
- Binary Search, 根据current node走位, until null leaf
- time: O(logn), space O(1) since no extra space used

#### DFS, Recursive
- when less than curr val, consider left
- when greater than curr val, consider right
- dfs到底, 然后每一层比较, 再return
- time: O(logn), space: O(logn)




---

**133. [28. Implement strStr().java](https://github.com/awangdev/LintCode/blob/master/Java/28.%20Implement%20strStr().java)**      Level: Easy      Tags: [String, Two Pointers]
      
给两个string A, B, 找一个 B 在 A 种的起始位置.

#### Two Pointer
- 找到B在A中的起始位置, 然后看一下从这个点开始的substring是否等于B就可以了
- 还挺多坑的, 这些可以帮助优化:
- 1. 当B是“”的时候，也就是能在A的其实位置找到B....index = 0.
- 2. edge condition: 如果 haystack.length() < needle.length() 的话, 必须错, return -1
- 3. 如果在某个index, A后面剩下的长度, 比B的长度短, 也是误解, return -1



---

**134. [852. Peak Index in a Mountain Array.java](https://github.com/awangdev/LintCode/blob/master/Java/852.%20Peak%20Index%20in%20a%20Mountain%20Array.java)**      Level: Easy      Tags: [Binary Search]
      

#### Binary Search
- binary search to find A[i-1] < A[i] < A[i+1]
    - if [mid-1] < [mid+1], on left slope, start = mid
    - if [mid-1] > [mid+1], on right slope, end = mid
- init: start == 1, end = n - 2;



---

**135. [110. Balanced Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/110.%20Balanced%20Binary%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      
给一个binary tree, 看是否是height-balanced

#### DFS with end state
- DFS using depth marker: 每个depth都存一下。然后如果有不符合条件的，存为-1.
- 一旦有 <0 或者差值大于1， 就全部返回Integer.MIN_VALUE. Integer.MIN_VALUE比较极端, 确保结果的正确性。
- 最后比较返回结果是不是<0. 是<0，那就false.
- Traverse 整个tree, O(n)


#### DFS, maxDepth function
- Same concept as above solution, but cost more traversal efforts
- 试图计算所有情况



---

**136. [246. Strobogrammatic Number.java](https://github.com/awangdev/LintCode/blob/master/Java/246.%20Strobogrammatic%20Number.java)**      Level: Easy      Tags: [Enumeration, Hash Table, Math, Two Pointers]
      

根据题意枚举, 再根据规则basic implementation

#### HashTable + Two Pointer
- compare left/right

#### Alter input
- flip number (6 and 9), and then reverse the string, see if the string is the same.
- takes more




---

**137. [100. Same Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/100.%20Same%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

给两个 binary tree, 看两个tree是否identical.

#### Method1: DFS
- DFS. 确定leaf条件, && with all dfs(sub1, sub2).
- 这里无论如何都要走过所有的node, 所以dfs更加合适, 好写.

#### Method2: BFS with 2 queues
- 两个queue存每个tree的所有current level node. Check equality, check queue size.
- Populate next level by nodes at current level.



---

**138. [88. Merge Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/88.%20Merge%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Two Pointers]
      

给两个排好序的数组, merge. 其中一个数组nums1有多余的位置

#### Basics
- A够长，那么可以从A的尾部开始加新元素: 从尾部，是大数字优先排末尾的.  
- Deal with remaining:
    - When A values are used up, put remian of B into it
    - When B values are finished, there is nothing todo. The remain of A is already in place.



---

**139. [112. Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/112.%20Path%20Sum.java)**      Level: Easy      Tags: [DFS, Tree]
      
给一个inputSum, 然后dfs, 找到是否有一条path, 得出的path sum 跟 inputSum 一样.

#### DFS
- 确定好结尾条件: `is leaf` && `val == sum`.
- 每一层减掉node.val, 然后dfs.
- 写一写: `root == null => false` 对parent nodes的影响. 这里发现没影响, 所以可以简化成用1个functionDFS.




---

**140. [463. Island Perimeter.java](https://github.com/awangdev/LintCode/blob/master/Java/463.%20Island%20Perimeter.java)**      Level: Easy      Tags: [Hash Table]
      
#### Brutle, Count Blocks and Walls
- 每个格子 +4 个墙;
- 每个shared的墙要减去: 从每个island走去另外一个, 都-1 (最终没面墙, -2)

#### Hash Table
- 把每个block相连的block全部存在以当下block为key的list里面. 那么这里需要把2D坐标, 转化成一个index.
- 最后得到的map, 所有的key-value应该都有value-key的反向mapping, 那么就可以消除干净, 每一次消除就是一个shared wall.
- 一点点optimization: DFS去找所有的island, 如果island的图非常大, 而island本身不大,那么适合optimize.
- 但是整体代码过于复杂. 不建议写.




---

**141. [170. Two Sum III - Data structure design.java](https://github.com/awangdev/LintCode/blob/master/Java/170.%20Two%20Sum%20III%20-%20Data%20structure%20design.java)**      Level: Easy      Tags: [Design, Hash Table, Memoization]
      

#### Hash Table, Memo
- Use Map<number, count > to store the inputs
- Iterate over map to find the pair
- Use Set<int> memo to store the success cases for fast return
- time: O(n), loop over all elements in map
- space: O(n), store all elements in map & memoization set



---

**142. [122. Best Time to Buy and Sell Stock II.java](https://github.com/awangdev/LintCode/blob/master/Java/122.%20Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Status DP]
      

和Stock I 的区别：可以买卖多次，求总和的最大盈利.

#### 几种不同的思路
1. Greedy, 每次有相邻的diff符合profit条件, 就卖了, 最后把所有的diff加在一起. 计算delta, 其实简单粗暴, 也还不错.
2. 从低谷找peek, sell.
3. DP. (old dp solution BuyOn[], SellOn[])
4. DFS计算所有(timeout).Improvement on DFS -> DP -> calculate sellOn[i] and buyOn[i], and then return buyOn[i]. 有点难想, 但是代码简单, 也是O(n)

#### 1. Greedy
- 画图, 因为可以无限买卖, 所以只要有上升, 就有profit
- 所有卖掉的, 平移加起来, 其实就是overall best profit
- 当天卖光, 再买进.
- O(n) time

#### 2. 找涨幅最大的区间, 买卖
- 找到低谷，买进:peek = start + 1 时候，就是每次往前走一步;若没有上涨趋势，继续往低谷前进。
- 涨到峰顶，卖出:一旦有上涨趋势，进一个while loop，涨到底, 再加个profit.
- profit += prices[peek - 1] - prices[start]; 挺特别的。
- 当没有上涨趋势时候，peek-1也就是start, 所以这里刚好profit += 0.

#### 3. DP, sequence dp + status
- 想知道前i天的最大profit, 那么用sequence DP: 
- dp[i]: represents 前i天的最大profit
- 当天的是否能卖, 取决于昨天是否买进, 也就是 `昨天买了或者卖了的状态`: 加状态, dp[i][0], dp[i][1]
- `买`的状态 `dp[i][0]` = 
    - 1. 今天买入, 昨天sell后的dp[i-1][1]值 - 今天的价格price[i];
    - 2. 今天不买, compare with 昨天buy的dp[i-1][0]值.
- `卖`的状态 `dp[i][1]` = 
    - 1. 今天卖出, 昨天buy的 dp[i-1][0]值 + price[i]; 
    - 2. 今天不卖, compare with 昨天sell后的 dp[i-1][1]值.
- 注意init: 
    - dp[0][0] = dp[0][1] = 0; // day 0 buy/sell: no profit regardless of buy/sell status
    - dp[1][1] = 0; // day 1 sell: haven't bought, so just 0 profit.
    - dp[1][0] = - prices[0]; // day 1 buy: just cost of prices[0]
- Note: `int[][] dp = new int[n+1][2]`以后, index是 1-based. for loop 注意使用 `prices[i-1]`
- O(n) time, O(n) space

##### Rolling Array
- [i] 和 [i - 1] 相关联, roll




---

**143. [14. Longest Common Prefix.java](https://github.com/awangdev/LintCode/blob/master/Java/14.%20Longest%20Common%20Prefix.java)**      Level: Easy      Tags: [String]
      
找一串String里面最长的公共prefix.

#### Sort, compare string
- Sort O(nlogn)
- first and last string should share common prefix
- 这里假设题目要求的是所有string的公共 prefix, 而不是部分strings

#### Brutle
- Nested loop, 每一次比较所有string 同位是否相等
- 相等，append string. 不等，return.
- O(mn)



---

**144. [243. Shortest Word Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/243.%20Shortest%20Word%20Distance.java)**      Level: Easy      Tags: [Array, Two Pointers]
      


#### Two Pointers
- Use 2 pointers to record **most recent** pos of word1 and word2
    - move pointer i [0 ~ n) and keep refreshing pos1 and pos2
    - both pos1 and pos2 will be as adjacent as possible since they both moving towards same direction
- keep recalculating best distance when either word is matched
- 而同一时间，只需要计算一个最近的curr distance: greedy不断变更A/B index, 做比较




---

**145. [414. Third Maximum Number.java](https://github.com/awangdev/LintCode/blob/master/Java/414.%20Third%20Maximum%20Number.java)**      Level: Easy      Tags: [Array, PriorityQueue]
      
#### pq
- 注意special case: `when less than 3 items, return maximum`
- PQ是natural order: remain peek() will be the 3rd maximum




---

**146. [20. Valid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/20.%20Valid%20Parentheses.java)**      Level: Easy      Tags: [Stack, String]
      

#### Stack
- 剥皮过程。解铃还须系铃人   
- 左边的外皮'{['在stack底部   
- 右边的外皮应该和stack顶上的左外皮一一对应 



---

**147. [893. Groups of Special-Equivalent Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/893.%20Groups%20of%20Special-Equivalent%20Strings.java)**      Level: Easy      Tags: [Basic Implementation, String]
      
Mark # of characters can be useful to print string signature



---

**148. [169. Majority Element.java](https://github.com/awangdev/LintCode/blob/master/Java/169.%20Majority%20Element.java)**      Level: Easy      Tags: [Array, Bit Manipulation, Divide and Conquer, Moore Voting, Sort]
      

#### HashMap count occurance
- Time, Space: O(n)


#### Moore Voting Algorithm 投票消减
- 前提: input必须valid, 比较罕见少用. Moore Voting Algorithm: https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
- 与当下candidate相同, vote++. 与之不同, vote--.
- Majority Number是指超半数, 多1个就行: 消减至最后, 会至少有vote>=1.
- 那么: vote++, vote--到最后剩下的就是winner. 
- 这个方法比较greedy, 前提是: valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。
- time: O(n), space O(1)

#### Sort
- sort entire nums array
- assume there is a solution, then nums[n/2] must be that majority num
- time O(nlogn)

#### Divide and Conquer
1. Recursive approach
1. For ange rangeA & rangeB, rangeA has majorElementA and rangeB has majorElementB
    - majorElementA = majorElementB, of course this element will be the major number for whole range
    - if majorElementA != majorElementB, then need to count both elements in whole range
    - of course the larger occurance will be the major num

#### Bit manipulation
- TODO

#### Related Problems
- Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。
- Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。



---

**149. [234. Palindrome Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/234.%20Palindrome%20Linked%20List.java)**      Level: Easy      Tags: [Linked List, Two Pointers]
      

#### Reverse Linked List
- Palindrome概念很简单: 两边回溯相等. However: 
    - 1) cannot random access index on linkded list
    - 2) cannot reverse iterating linked list
- solution: reverse linked list: 遍历接开头
    - 1) 用快慢指正找到mid point
    - 2) reverse 2nd half
    - 3) compare leftList and rightList
- Time O(n), 而且不需要用额外的空间(只是调换半个list的内部顺序), 所以空间O(1)



---

**150. [202. Happy Number.java](https://github.com/awangdev/LintCode/blob/master/Java/202.%20Happy%20Number.java)**      Level: Easy      Tags: [Hash Table, Math]
      

Basic Implementation of the requirements.

用HashSet存查看过的数值。若重复，return false.



---

**151. [69. Sqrt(x).java](https://github.com/awangdev/LintCode/blob/master/Java/69.%20Sqrt(x).java)**      Level: Easy      Tags: [Binary Search, Math]
      
#### sqrt(int x)
- 理解题意, 从[0, x]找一个可以m*m=x的值.
- 注意, 如果找不到, 最后问考官该return一个什么值：按道理，因为return int, 会取整，那么return一个平方最close to x就可以.
- 注意 mid 用 long, 因为很可能超过最大int.

#### sqrt(double x)
- 二分float number, 应该用精度来定义结尾.
- 还是二分, 但是判断条件变成: while ( end - start > eps)
- eps = 1e-12,也就是精度到1e-12



---

**152. [876. Middle of Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/876.%20Middle%20of%20Linked%20List.java)**      Level: Easy      Tags: [Linked List]
      
找Linked List的中间node

#### 快慢指针
- 不在乎slow是不是到底，因为fast肯定先到。
- 确保fast, fast.next不是Null就好



---

**153. [219. Contains Duplicate II.java](https://github.com/awangdev/LintCode/blob/master/Java/219.%20Contains%20Duplicate%20II.java)**      Level: Easy      Tags: [Array, Hash Table]
      

Unsorted array, 找出是否有duplicate elemenets: 必要条件是, 这两个element的index i,j 的大小最多相差k.

#### HashSet
- 很巧妙地根据k range地条件
    - 把HashSet里面的值控制在[i - k, i]
    - 每次不断地往set里面加新元素, 从set里减去末尾index的元素
- 而set.add(x)如果遇到重复, 会return false.
- 一旦在这个length k 的 range里面, 有重复, 就符合条件. 
- Time O(n)

#### HashTable<value, List of duplicates>
- Time O(nm), m = # of duplicates. 太慢
- 记录每个element value的index in the list
- 一旦有重复element重复, 就把整个list of indexes 端出来, 查看有没有符合条件的: (index - i) <= k


#### 这两种做法的区别很有艺术感觉
- 方法1是限定选拔的candidate, 不合格就去掉, 那么一旦有符合条件的(duplicates), 那么一定中, 剩下的就不看了.
- 方法2是把符合条件的index找出来, 集中处理, 但是所有candidate都会选出来
- 就好像招人一样: 一种是遇到好的就停止; 第二种是看过所有人, 从中选拔最好的. 显然第一种更快.




---

**154. [205. Isomorphic Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/205.%20Isomorphic%20Strings.java)**      Level: Easy      Tags: [Hash Table]
      

#### HashMap
- check 2 failture cases:
    - same key, value not matching
    - two key maps to same value



---

**155. [346. Moving Average from Data Stream.java](https://github.com/awangdev/LintCode/blob/master/Java/346.%20Moving%20Average%20from%20Data%20Stream.java)**      Level: Easy      Tags: [Design, Queue, Sliding Window]
      

给一个interface, design一个structure, 能够计算moving window average.

#### Queue
- 读懂题目, 注意average 和 window 的处理.
- 简单的queue.size() comparison
- Note: if we it is calculate moving-window-product, better to use deque :)
- Sliding window?
    - It has the spirit of slinding window: 1) maintain a range; 2) check range size `if (queue.size() > size)`
    - Though, the solution must use a data structure to store data; it is not the traditional sliding window type of `left/right` pointer problem



---

**156. [938. Range Sum of BST.java](https://github.com/awangdev/LintCode/blob/master/Java/938.%20Range%20Sum%20of%20BST.java)**      Level: Easy      Tags: [BST, Recursion, Tree]
      
#### DFS based on BST rules
- sum up the matching L & R
    - Find (L,R) on left child
    - Find (L,R) on right child
    - Find (L,R) covering the root node
- space O(n), worst case O(logn), height of dfs.
- time O(n) to find all nodes between (L, R)

#### Iterative
- Using stack, or queue, list: any data structure (we are not doing ordered search)
- space O(n)
- time O(n)



---

**157. [242. Valid Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/242.%20Valid%20Anagram.java)**      Level: Easy      Tags: [Hash Table, Sort]
      

#### int[26]

#### HashMap<Character, Integer>



---

**158. [217. Contains Duplicate.java](https://github.com/awangdev/LintCode/blob/master/Java/217.%20Contains%20Duplicate.java)**      Level: Easy      Tags: [Array, Hash Table]
      


无序数组, 找是否有重复element, return true/false.

#### HashSet
- No brain: HashSet.
- Time O(n), Space O(n)

#### Sort, Binary Search
- Arrays.sort(x): Time O(nLogN), Space O(1)
- 排序后, 重复数会排在一起, 然后 binary search



---

**159. [796. Rotate String.java](https://github.com/awangdev/LintCode/blob/master/Java/796.%20Rotate%20String.java)**      Level: Easy      Tags: [String]
      
给两个String, 看A rotate之后 能不能变成B

#### LeetCode
- Basics
- StringBuffer.deleteCharAt(xx), StringBuffer.append(xx)
- O(n)


#### LintCode
- Different problem: 给一个char[], 要rotate offset times.
- *三步rotate*
- 有个坑：offset可能很长，那么要%length，才能得到真正需要rotate的部分。
- Note: rotate 一个 full length之后，是string 不变



---

**160. [1041. Robot Bounded In Circle.java](https://github.com/awangdev/LintCode/blob/master/Java/1041.%20Robot%20Bounded%20In%20Circle.java)**      Level: Easy      Tags: [String]
      
简单的character checking. 各个方向, 加加减减.



---

**161. [157. Read N Characters Given Read4.java](https://github.com/awangdev/LintCode/blob/master/Java/157.%20Read%20N%20Characters%20Given%20Read4.java)**      Level: Easy      Tags: [Enumeration, String]
      
Read4 题目. 理解题目: 是有个input object buff, 会被populated with data.

#### String in char[] format
- 理解题目: 其实就是track `可以read多少bytes by read4() response`
- 另外一个有用的function `System.arraycopy(src, srcIndex, dest, destIndex, length)`
- Edge Case:
    - When there is not enough space to the result buffer, `i + 3 > n`, then only copy what we can: `Math.min(n - i, count)`
    - `count < 4` meaning there is not enough content to read, break



---

**162. [121. Best Time to Buy and Sell Stock.java](https://github.com/awangdev/LintCode/blob/master/Java/121.%20Best%20Time%20to%20Buy%20and%20Sell%20Stock.java)**      Level: Easy      Tags: [Array, DP, Sequence DP]
      
给个array of stock prices, 限制能交易(买/买)一轮, 问如何找到最大profit.

#### min[n]
- 每天都就交易价格，n天只让买卖一次，那就找个最低价买进，找个最高价卖出
- 记录每天最小值Min是多少. O(n)
- 每天都算和当下的Min买卖，profit最大多少.

#### DP
- Find min value for first i items, new dp[n + 1].
- dp[i]: 前i天, prices最小的price是多少: min cost of first i days
- 然后用当天的price做减法dp[i]算max profit.
- Time, Space: O(n)
- 更进一步, 用一个min来表示min[i], 因为计算中只需要当下的min.

#### Rolling array
- index i only depend on [i - 2]
- Space O(n)

#### Brutle Failed
- 每天都试着买进，然后之后的每一天尝试卖出. double for loop, O(n^2). timeout.
    - 其中很多都是没必要的计算：[7, 1, 5, 3, 6, 4]
    - if we know buyin with 1 is cheapest, we dont need to buyin at 5, 3, 6, 4 later on;
    - only need to sell on higher prices.



---

**163. [605. Can Place Flowers.java](https://github.com/awangdev/LintCode/blob/master/Java/605.%20Can%20Place%20Flowers.java)**      Level: Easy      Tags: [Array, Greedy]
      

#### Array
- just check flowerbed[i-1] & flowerbed[i+1] and count



---

**164. [1. Two Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/1.%20Two%20Sum.java)**      Level: Easy      Tags: [Array, Hash Table]
      

#### HashMap<value, index>
- 相对暴力简洁: 找到一个value, 存一个index
- 若在HashMap里面 match 到结果, 就return HashMap里存的index. 
- O(n) space && time.

#### Sort array, two pointer
- 前后++, --搜索. Sort 用时O(nlogn).     
- 1. 第一步 two pointer 找 value.       
- 2. 注意，要利用额外的空间保留original array， 用来时候找index. (此处不能用HashMap，因为以value 为key，但value可能重复)      
- O(n) space, O(nlogn) time.    




---

**165. [118. Pascal's Triangle.java](https://github.com/awangdev/LintCode/blob/master/Java/118.%20Pascal's%20Triangle.java)**      Level: Easy      Tags: [Array, Basic Implementation, List]
      



---

**166. [283. Move Zeroes.java](https://github.com/awangdev/LintCode/blob/master/Java/283.%20Move%20Zeroes.java)**      Level: Easy      Tags: [Array, Two Pointers]
      

Move non-zero elements to front of array; preseve order.

#### Two Pointers
- Outside pointer that moves in certain condition. 
- Save appropirate elements



---

**167. [1033. Moving Stones Until Consecutive.java](https://github.com/awangdev/LintCode/blob/master/Java/1033.%20Moving%20Stones%20Until%20Consecutive.java)**      Level: Easy      Tags: [Basic Implementation, Sort]
      

#### Analyze to understand
- put 3 elements into array, sort and follow below rules:
- min: 
    - if 3 elements consecutive, 0 move.
    - if only 1 pair of the two elemnets consecutive or if they have 1 slot in between, it needs exactly 1 move
    - otherwise, at most 2 moves
- max: # of open slots between them (high - low + 1) - n, where n = 3
- Follow up: `1040. Moving Stones Until Consecutive` is more interesting with special rulese (cannot move to `ending spot`), and it uses sliding window concept



---

**168. [125. Valid Palindrome.java](https://github.com/awangdev/LintCode/blob/master/Java/125.%20Valid%20Palindrome.java)**      Level: Easy      Tags: [String, Two Pointers]
      
验证string是不是 palindrome. 只考虑 alphanumeric, 其他字符可以忽略

#### Two Pointers
- Time O(n), Space O(1).
- 普通方法, 两边check, 速度相比较regular expression更快. leetcode 4ms.
- Use helper functions.

#### Check Palindrome
- 前后两个指针, 往中间移动, 查看是否字母重合

#### 过滤 alphanumeric
- 可以用 ASCII code 来手动过滤, 只要 '0' ~ '9', 'a' ~ 'z', 'A' - 'Z' 之间的
- 也可以用 regular expression: match 所有这些字母, 是 [a-zA-Z0-9]
- 那凡是不是这些字母的 match, 就是取反: "[^a-zA-Z0-9]". 测试: https://regex101.com/



---

**169. [160. Intersection of Two Linked Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/160.%20Intersection%20of%20Two%20Linked%20Lists.java)**      Level: Easy      Tags: [Linked List]
      
给两个 linked list, 问从哪个node开始, 两个 linked list 开始有重复?

#### Basics
- 长短list，找重合点
- 长度不同的话，切掉长的list那个的extra length
- 那么起点一样后，重合点就会同时到达
- Time O(n) * 2, constant space



---

**170. [724. Find Pivot Index.java](https://github.com/awangdev/LintCode/blob/master/Java/724.%20Find%20Pivot%20Index.java)**      Level: Easy      Tags: [Array, PreSum]
      

#### PreSum
- want to find `nums[i - 1] == nums[n - 1] - nums[i]`, given: 
    - preSum[i], sum from [0, i] inclusive
    - preSum[j] - preSum[i] = [i+1, j] inclusive
- O(n) to build preSum
- O(n) to find pivot



---

**171. [350. Intersection of Two Arrays II.java](https://github.com/awangdev/LintCode/blob/master/Java/350.%20Intersection%20of%20Two%20Arrays%20II.java)**      Level: Easy      Tags: [Binary Search, Hash Table, Sort, Two Pointers]
      

#### HashMap
- Map of nums1: <num, # appearance>
- check nums2 against nums1 map
- time:O(n + m)
- space:O(n + m)

#### Binary Search
- 



---

**172. [720. Longest Word in Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/720.%20Longest%20Word%20in%20Dictionary.java)**      Level: Easy      Tags: [Hash Table, Trie]
      

给串word[], 找最长的Word, 满足条件: 这个Word可以从 word[] 里面一个字母一个字母被build出来.

如果多种答案, respect smallest lexicographical order.

#### Sort, HashSet
- 先排序(lexicographically), 排序以后才能逐个看是否partial string已经存在
    - 用 set.contains(substring(0, n - 1)) 来查看上一步的 substring 是否存在
    - 如果找到, 因为已经按照字母表排序, 找到的这个肯定是这个长度里面最符合的解答.
- 然后brutally找下一个更大的.
- Sort O(n log n), O(n) set space

#### Trie
- 可以先sort words Array: 
    - 长 string 排在前;
    - 相等length, 按照dictionary order 排序
- 全部放入Trie. Trie.insert()
    - 针对sorted words array, 从最长的开始找 Trie.startWith.
    - 一旦找到, 就是符合题意的, 直接return.
- 注意: startWith 必须每一个node都是 isEnd, 才满足'逐个字母拼出' 的条件.
- Time: build Trie O(mn) + sort:O(nlogn) => O(nlogn)
- Space: O(mn)

#### 从最长的word开始做
- 按大小排序 -> 从最大的开始做contains()的比较 -> 结果再按照字母表顺序(lexicographically) sort一下.
- 但是Collections.sort()了两次, 而且再list.contains(), 比较慢




---

**173. [760. Find Anagram Mappings.java](https://github.com/awangdev/LintCode/blob/master/Java/760.%20Find%20Anagram%20Mappings.java)**      Level: Easy      Tags: [Hash Table]
      

- HashMap 存index list
- 遍历一遍数组A, 列举出所有元素



---

**174. [1170. Compare Strings by Frequency of the Smallest Character.java](https://github.com/awangdev/LintCode/blob/master/Java/1170.%20Compare%20Strings%20by%20Frequency%20of%20the%20Smallest%20Character.java)**      Level: Easy      Tags: [Array, String]
      

#### Method1: letter frequency map, kinda bucket sort
- Goal: find word count that fits into `f(queries[i]) < f(W)`
- What if: we can store the f(W) as preSum, then goal: `rst[i] = preSum[end] - preSum[queryWordCount]`
    - count(W) and store in count[i]
    - calc preSum
    - processs queries array
- kinda bucket sort: 
    - 1) we know the boundary of the word length, so we can create `bucket`
    - 2) `function count(w)` can produce a value that sort a word into a specific bucket slot
        - extend: the bucket can store keys that links back to the word (if there are follow up questions)
- time: O(m + n)
- space: O(m + n)

#### Method2: No brain solution, basic impl based on the desc, w/o search. 
- time: 
    - O(nm) to count all words, O(nlogn) to sort the wordCount
    - O(nm) to to count all queries
    - O(n^2) to perform the match 
- space: O(n)



---

**175. [94. Binary Tree Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/94.%20Binary%20Tree%20Inorder%20Traversal.java)**      Level: Easy      Tags: [Hash Table, Stack, Tree]
      

Inorder traverse Binary Tree

#### Method1: DFS
- option1: dfs + rst list to carry results
- option2: Divide and Conquer, 在自己的基础上recursive, 不用helper function
- O(n) time

#### Method2: Iterative, Stack inorder traversal
- 1) Add root.leftPath all the way to leaf, 2) process curr 3) Move to right if applicable 4) add all right.leftPath
- O(n) time, O(h) space




---

**176. [278. First Bad Version.java](https://github.com/awangdev/LintCode/blob/master/Java/278.%20First%20Bad%20Version.java)**      Level: Easy      Tags: [Binary Search]
      

#### Method1: Check is-NOT-BadVersion
- simply binary Search: if not bad, assign `start = mid+1`

#### Method2: Check ifBadVersion
- 根据isBadVersion的性质，判断还如何end=mid or start=mid.     
- A bit more code to handle



---

**177. [101. Symmetric Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/101.%20Symmetric%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

检查tree是否symmetric

注意Symmetric Binary Tree的例子和定义: 是镜面一样的对称. 并不是说左右两个sub-tree相等。

#### Method1: DFS
- Recursively check symmetrically相对应的Node.  
- 每个node的children都和镜面另外一边相对的node的children刚好成镜面反射位置。

#### Method2: interative with queue 
- put left or right children in pair

#### Method3: iterative with Stack
- stack1: 左手边sub-tree先加left, 再加right child; 
- stack2: 右手边sub-tree先加right child, 再加left child。   
- process时，若symmetric，所有stack里面出来的node会一一对应。



---

**178. [13. Roman to Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/13.%20Roman%20to%20Integer.java)**      Level: Easy      Tags: [Math, String]
      

#### String 
- 熟悉罗马字母规则     
- 1. 'I V X L C D M' 分别代表的数字     
- 2. 列举combo的情况，需要从原sum里面减掉多加的部分: 'IV, IX'减2, 'XL, XC'减20, 'CD, CM'减200. 
- Leading `I(1*2)`, `X(10*2)`, `C(100*2)` causes double counting 

https://en.wikipedia.org/wiki/Roman_numerals

#### use map to store combinations




---

**179. [671. Second Minimum Node In a Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/671.%20Second%20Minimum%20Node%20In%20a%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, Tree]
      

#### BFS
- min tree: parent node is the min of left/right child
- BFS to traverse the tree and find 1st non-root smallest val
- Improvement area: when `node.val >= nextMin`, no need to dive into node children since it is a min Tree.

#### DFS
- Find left and right val: 
    - if left/right equals root.val, that means the left or right sub children could have larger number
    - Therefore DFS into left or right
- compare and return min(left, right)



---

**180. [235. Lowest Common Ancestor of a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/235.%20Lowest%20Common%20Ancestor%20of%20a%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [BST, DFS, Tree]
      

给 binary search tree root, q node, p node. 找到p q 的lowest common ancestor

#### Find path with BST
- 利用 BST 的性质，可以直接搜到target node，而做成两个长度不一定相等的 path
- 然后很简单找到LCA 
- O(n) space, O(logn) time

#### DFS
- Beofre lasts common ancestor is found: p and q should follow same search pattern:
    - compare with root, then dfs(left) or dfs(right)
    - util p and q fall on two sides of root, then return root
    - 非常巧妙, 但是也比较局限; 稍微变条件, 就很难recursive.
- 几种情况:
    - 1. one of p, q 在leaf, 那么此时的root其实就是lowest common ancestor
    - 2. 如果p, q 在root的左右两边, 这就是分叉口, 那么root就是lowest common ancestor
    - 3. 如果p, q 在root的同一边 (左,右), 那么继续dfs
- O(logn) extra space: recursive stack space
- O(logn) time



---

**181. [256. Paint House.java](https://github.com/awangdev/LintCode/blob/master/Java/256.%20Paint%20House.java)**      Level: Easy      Tags: [DP, Sequence DP, Status DP]
      

要paint n个房子, 还有 nx3的cost[][]. 求最少用多少cost paint 所有房子.

#### Sequence DP
- 求dp[i]的min cost, depends on the color of dp[n-1]
  - 选中最后一个房子的颜色同时, 根据dp[i - 1]的颜色/cost + cost[i-1], 来找出最低的cost
- Need to have status with dp array: int[index][color status]
  - dp[i][j]: 前i个house 刷成 j 号颜色的最小cost.
  - dp[0][j] = 0: 0th house, no cost
- 计算顺序: 从每一个house开始算起 [0 ~ n], first for loop
- time: O(nm), m = # of colors
- space: O(nm)

#### Rolling Array
- 观察发现 index[i] 只跟 [i-1] 相关, 所以2位就足够, %2
- space:O(1)



---

