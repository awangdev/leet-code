 
 
 
## Easy (127)
**0. [Fibonacci.java](https://github.com/awangdev/LintCode/blob/master/Java/Fibonacci.java)**      Level: Easy
      
方法1: DP array.

方法1.1: 滚动数组, 简化DP。

方法2: recursively calculate fib(n - 1) + fib(n - 2). 公式没问题, 但是时间太长, timeout.




---

**1. [Group Shifted Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Group%20Shifted%20Strings.java)**      Level: Easy
      
相同shift规则的string, 能被推算到同一个零起始点，就是共同减去一个char,最后就相等。以此作为key，用HashMap。一目了然。

记得根据题目意思，一开始要String[] sort一下。



---

**2. [Hamming Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Hamming%20Distance.java)**      Level: Easy
      
bit: XOR, &, shift>>



---

**3. [Happy Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Happy%20Number.java)**      Level: Easy
      
Basic Implementation of the requirements.

用HashSet存查看过的数值。若重复，return false.



---

**4. [Hash Function.java](https://github.com/awangdev/LintCode/blob/master/Java/Hash%20Function.java)**      Level: Easy
      
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

**5. [HashWithArray.java](https://github.com/awangdev/LintCode/blob/master/Java/HashWithArray.java)**      Level: Easy
      



---

**6. [Heaters.java](https://github.com/awangdev/LintCode/blob/master/Java/Heaters.java)**      Level: Easy
      
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

**7. [Identical Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Identical%20Binary%20Tree.java)**      Level: Easy
      
Divide, && 每种情况（左右一一对应)    
注意 null states




---

**8. [Implement Stack by Two Queues.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack%20by%20Two%20Queues.java)**      Level: Easy
      
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

**9. [Implement Stack using Queues.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack%20using%20Queues.java)**      Level: Easy
      
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

**10. [Implement Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack.java)**      Level: Easy
      
stack 后入先出. 
Data Structure: ArrayList 
return/remove ArrayList的末尾项。



---

**11. [IndexMatch.java](https://github.com/awangdev/LintCode/blob/master/Java/IndexMatch.java)**      Level: Easy
      
有序, 假设有这样的数字:target.        
target 左边的数字，一定不比index大，target右边的数字，一定比index大。     
这样可以binary search.O(logn)



---

**12. [Insert Interval.java](https://github.com/awangdev/LintCode/blob/master/Java/Insert%20Interval.java)**      Level: Easy
      

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

**13. [Insert Node in a Binary Search Tree .java](https://github.com/awangdev/LintCode/blob/master/Java/Insert%20Node%20in%20a%20Binary%20Search%20Tree%20.java)**      Level: Easy
      

往Binary Search Tree里面加东西，一定会找到一个合适的leaf加上去。

那么：就是说someNode.left or someNode.right是null时，就是insert node的地方。

找到那个someNode就按照正常的Binary Search Tree规律。



---

**14. [Intersection of Two Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Arrays.java)**      Level: Easy
      
方法1: 用到hashset找unique && duplicate: O(m+n)
方法2: 可以用binary search 找数字. Note:binary search一定需要array sorted: nLog(m)



---

**15. [Intersection of Two Linked Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Linked%20Lists.java)**      Level: Easy
      
长短list，找重合点。
长度不同的话，切掉长的list那个的extra length。 那么起点一样后，重合点就会同时到达。




---

**16. [Invert Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Invert%20Binary%20Tree.java)**      Level: Easy
      
non-recursive: BFS with queue。 或者regular recurisve - divide and conquer.



---

**17. [Isomorphic Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Isomorphic%20Strings.java)**      Level: Easy
      
HashMap 来确认match。有几种情况考虑:

1. Match. 就是map.containsKey, map.containsValue, and char1 == char2. Perfect.

2. Either Key not exist, or Value not exit. False;

3. Both key and Value exist, but map.get(char1) != char2.  Miss-match. False.

4. None of Key or Value exist in HashMap. Then add the match.



---

**18. [Jewels and Stones.java](https://github.com/awangdev/LintCode/blob/master/Java/Jewels%20and%20Stones.java)**      Level: Easy
      
1524017454

给J 和 S两个string. J里的character是unique 的珠宝, S 里面的character包含珠宝和石头. 找S里面有多少珠宝

#### Basic HashSet



---

**19. [Longest Univalue Path.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Univalue%20Path.java)**      Level: Easy
      
弄明白path的意义: 连接node的edge.
要找MAX, 可以在class scope里面定义一个max variable.

用minimum amount of code来概括几种不同的情况: left == root, right == root, or left == root == right.



---

**20. [Longest Word in Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Word%20in%20Dictionary.java)**      Level: Easy
      
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

**21. [Lowest Common Ancestor II.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor%20II.java)**      Level: Easy
      
这个题有个奇葩的地方，每个node还有一个parent。 所以可以自底向上.

1. 曾经做的hashset的优化，找到的都存hashset. exist就return那个duplicate.


2. 普通做法：2 lists。
   自底向上。利用parent往root方向返回。   

注意：无法从root去直接搜target node 而做成两个list. 因为根本不是Binary Search Tree！




---

**22. [Lowest Common Ancestor.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor.java)**      Level: Easy
      
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

**23. [Matrix Zigzag Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Matrix%20Zigzag%20Traversal.java)**      Level: Easy
      
分析4个step:right, left-bottom,down,right-up    
implement时注意index.有点耐心



---

**24. [Max Area of Island.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Area%20of%20Island.java)**      Level: Easy
      
虽然Easy, 但用到DFS最基本的想法.
1. dive deep
2. mark VISITED
3. sum it up

更要注意, 要从符合条件value==1的地方开始dfs.
对于什么island都没有的情况，area应该位0， 而不是Integer.MIN_VALUE, 问清楚考官那小伙, 别写顺手。



---

**25. [Maximum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy
      
DFS: Divide and conquer. 维持一个最大值。



---

**26. [Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray.java)**      Level: Easy
      
方法1
比较像DP, 维持一个sums[i]: 从i向前位数, 所有正数的和. 一旦sums[i - 1]<0, 意味着sums[i-1]对maxSum没有好处,
那么就assign: sums[i]=nums[i]
这个做法比较中规中矩, makes sense

方法2(better)
想着用一用prefix sum. 把值一个个叠加。
然后presum[j] - presum[i- 1] 就是 (i,j)之间的和。



---

**27. [Merge Intervals.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Intervals.java)**      Level: Easy
      
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

**28. [Merge Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Sorted%20Array.java)**      Level: Easy
      
A够长，那么可以从A的尾部开始加新元素。     
注意，从尾部，是大数字优先排末尾的.  



---

**29. [Merge Two Binary Trees.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Two%20Binary%20Trees.java)**      Level: Easy
      
基础binary tree traversal. 注意对null child的判断



---

**30. [Merge Two Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Two%20Sorted%20Lists.java)**      Level: Easy
      
小的放前。每次比head大小。   
while过后，把没完的list一口气接上。   

一开始建一个node用来跑路, 每次都存node.next = xxx。存一个dummy。用来return dummy.next.



---

**31. [Minimum Absolute Difference in BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Absolute%20Difference%20in%20BST.java)**      Level: Easy
      

BST: inorder-traversal: 先left node(adding to stack till left leav), 再process stack.peek (mid node), 再 add rightNode && dive to rightNode.left leaf



---

**32. [MinimumDepthOfBinaryTree.java](https://github.com/awangdev/LintCode/blob/master/Java/MinimumDepthOfBinaryTree.java)**      Level: Easy
      
Divide and Conquery一个最小值. 注意处理Leaf的null, 用Integer.MAX_VALUE代替，这样可以避免错误counting.



---

**33. [Palindrome Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Permutation.java)**      Level: Easy
      
注意，条件里面没说是否全是lower case letter



---

**34. [Pascal's Triangle II.java](https://github.com/awangdev/LintCode/blob/master/Java/Pascal's%20Triangle%20II.java)**      Level: Easy
      
简单处理array list.



---

**35. [Permutation Index.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutation%20Index.java)**      Level: Easy
      
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

**36. [QuickSort.java](https://github.com/awangdev/LintCode/blob/master/Java/QuickSort.java)**      Level: Easy
      
代码是不难的. 

首先partition. 返还一个partition的那个中间点的位置。
然后劈开两半。
前后各自 quick sort, recursively

注意：在partition里面, 比较的时候nums[start] < pivot, nums[end]>pivot, 如果写成了 <= 会 stack overflow.


但是： 在partition array那个题目里面，第二个 nums[end] >= pivot, 是要去加上这个 ‘=’的



---

**37. [Remove Duplicates from Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicates%20from%20Sorted%20Array.java)**      Level: Easy
      
Remove Duplicate from Array 不同于remove from linked list.

LinkedList里面我们是最好不要动node.val的，直接把node去掉。
而array我们很难直接把node去掉，又不能用新array，那么就要：

把不重复的element一个个放到最前面。


这个思想跟merge two sorted array （其中一个后续非常长的array可以放下arr1,arr2） 类似。
就是找个不会事后mess up，不会去动得index,把满足条件的element 填进去。这样保证了in place.

* 有个反向思维：remove duplicate,实际上也是找unique elements, and insert into original array



---

**38. [Remove Duplicates from Sorted List.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicates%20from%20Sorted%20List.java)**      Level: Easy
      
一旦node.next 和node是重复，跳




---

**39. [Reshape the Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Reshape%20the%20Matrix.java)**      Level: Easy
      
读例子理解题意.
理清counter case. Basic implementation



---

**40. [Reverse Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Linked%20List.java)**      Level: Easy
      
建立新list。每次把newList append 在current node的后面。   
用head来循环所有node。



---

**41. [Reverse String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20String.java)**      Level: Easy
      
Similar to Reverse Integer.
可以用StringBuffer, 也可以two pointer reverse head/tail



---

**42. [Roman to Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/Roman%20to%20Integer.java)**      Level: Easy
      
熟悉罗马字母规则     
1. 'I V X L C D M' 分别代表的数字     
2. 列举combo的情况，需要从原sum里面减掉多加的部分： 'IV, IX'减2, 'XL, XC'减20, 'CD, CM'减200. 

https://en.wikipedia.org/wiki/Roman_numerals



---

**43. [Shortest Word Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Shortest%20Word%20Distance.java)**      Level: Easy
      
找short distance, wordB可以在wordA的前后；而同一时间，只需要计算一个最近的up to date的distance。
greedy不断变更A/B index再做比较即可。



---

**44. [Single Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Single%20Number.java)**      Level: Easy
      
Bit XOR: 当两个bit不同时，return 1. 
题目正要消光所有重复出现的数儿留下出现一次的那个.



---

**45. [String Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/String%20Permutation.java)**      Level: Easy
      
把#of occurrences 存进HashMap, 第一个string 做加法，第二个string做减法。最后看是否有不等于0的作判断。



---

**46. [String to Integer(atoi).java](https://github.com/awangdev/LintCode/blob/master/Java/String%20to%20Integer(atoi).java)**      Level: Easy
      
方法1: 问清情况，一点一点把case都涉及到。

方法2: 用regular expression。if (!str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")).  猛了一点



---

**47. [Strobogrammatic Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Strobogrammatic%20Number.java)**      Level: Easy
      
根据题意枚举, 再根据规则basic implementation



---

**48. [Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum.java)**      Level: Easy
      
分析出，如果sum[0~a]=x, 然后sum[0~b]=x, 说明sum(a ~ b] = 0.    

    这样理解后，用hashMap存每个sum[0~i]的值和index i. 如果有重复，就找到了一组sum为0的数组。



---

**49. [Subtree.java](https://github.com/awangdev/LintCode/blob/master/Java/Subtree.java)**      Level: Easy
      
找到potential subtree, 比较Children.

一点注意：即使找到T1 == T2, 但很可能只是数字相同（这里不是binary search tree!!），而children不同。所以同时要继续recursively isSubtree(T1.left, T2) ...etc.



---

**50. [Symmetric Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Symmetric%20Binary%20Tree.java)**      Level: Easy
      
Symmetric Tree

注意Symmetric Binary Tree的例子和定义: 是镜面一样的对称. 并不是说左右两个sub-tree相等。

方法1: Recursively check symmetrically相对应的Node.  每个node的children都和镜面另外一边相对的node的children刚好成镜面反射位置。

方法2: 用stack. 左手边sub-tree先加left,再加right child; 右手边sub-tree先加right child, 再加left child。   
process时，若symmetric，所有stack里面出来的node会一一对应。



---

**51. [Tweaked Identical Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Tweaked%20Identical%20Binary%20Tree.java)**      Level: Easy
      
Recursive 比对左左,左右,右左，右右



---

**52. [Two Strings Are Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Strings%20Are%20Anagrams.java)**      Level: Easy
      
方法1:char ascii 用count[256]   
坑：不要想象这个是个26letter lowercase. may not be true.

方法2: 若是其他字符encoding, 而不只是utf16-encoding (java char)?   
那么就继续用string去做



---

**53. [Valid Palindrome.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Palindrome.java)**      Level: Easy
      
tutorial:https://www.youtube.com/watch?v=2hNK0Yz53LQ&list=PLZn-UvluQZuNedn1hDzTmNLE8MQWXjKVb

过滤alphanumeric，其他字母掠过



---

**54. [Valid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Parentheses.java)**      Level: Easy
      
剥皮过程。解铃还须系铃人   
左边的外皮'{['在stack底部   
右边的外皮应该和stack顶上的左外皮一一对应 





---

**55. [Valid Sudoku.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Sudoku.java)**      Level: Easy
      
用HashSet存visited value.

方法1: 在nest for loop里面validate row,col,and block.     
validate block要利用i 和 j 增长的规律。    
说白了，i && j是按照0~n增长的index, 具体怎么用是可以flexible的。这个方法在同一个nest for loop解决所有运算。

方法2: 单独做block validation: validate block的时候虽然看到了4层for.其实也就是n^2.



---

**56. [Word Pattern.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Pattern.java)**      Level: Easy
      
每个char代表一个pattern。用HashMap<char, str>.
但不够，如果a也match dog, b也match dog, 纠错了。比如pattern = "abba", str = "dog dog dog dog"。
因此第二个HashMap<str, char> 反过来。
确保pattern和str一一对应。



---

**57. [Find Anagram Mappings.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Anagram%20Mappings.java)**      Level: Easy
      

比较简单, 用HashMap 存index list. 最后再遍历一遍数组A, 列举出所有元素.
O(n)



---

**58. [Judge Route Circle.java](https://github.com/awangdev/LintCode/blob/master/Java/Judge%20Route%20Circle.java)**      Level: Easy
      

简单的character checking. 各个方向, 加加减减.



---

**59. [Island Perimeter.java](https://github.com/awangdev/LintCode/blob/master/Java/Island%20Perimeter.java)**      Level: Easy
      

最简单的方法: 每个格子4个墙;每个shared的墙要-2 (墙是两面, -1 * 2)
最后合计结果就好.

不必想太多用HashMap做.但是也可以思考一下:
- 把每个block相连的block全部存在以当下block为key的list里面. 那么这里需要把2D坐标, 转化成一个index.
- 最后得到的map, 所有的key-value应该都有value-key的反向mapping, 那么久可以消除干净, 每一次消除就是一个shared wall.
- 一点点optimization: DFS去找所有的island, 如果island的图非常大, 而island本身不打,那么适合optimize.
  但是整体代码过于复杂. 不建议写.




---

**60. [First Unique Character in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/First%20Unique%20Character%20in%20a%20String.java)**      Level: Easy
      

方法1: 按照题意, 找到第一个 first index == last index的字母.

方法2: 用hashmap存字母的index, 有些重复字母的index就会是个list. 找到单一index, 结合成list, sort, return list.get(0)



---

**61. [Power of Three.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Three.java)**      Level: Easy
      

方法1:
Power of 3:  3 ^ x == n ?
意思是 n / 3 一直除, 最后是可以等于1的, 那么就有了 n/=3, check n%3, 最后看结果是不是整除到1的做法. 用while loop.

方法2:
如果n是power of 3, 那么 3 ^ x的这个 x一定是个比n小的数字. 那么可以在 0 ~ n 之间做binary serach, 但是就比较慢.

方法3:
巧妙的想法.最大的3^x integer是 3^19. 那么找到这个数, 一定可以被n整除. 一步到位.



---

**62. [Plus One.java](https://github.com/awangdev/LintCode/blob/master/Java/Plus%20One.java)**      Level: Easy
      

简单的实现, 加1, 进位. 唯一取巧的地方, 最后如果要多一位, 一定是10000...这个模式, 可以走捷径, 直接来个+1size的array, 然后第一位=1.
注意,转换成long也不合理,用太多memory.


---

**63. [Power of Two.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Two.java)**      Level: Easy
      

跟powerOfThree一样: 可以loop, check mod; 也可以用binary search找合适的数字.



---

**64. [Reverse Vowels of a String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Vowels%20of%20a%20String.java)**      Level: Easy
      

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

**65. [Guess Number Higher or Lower.java](https://github.com/awangdev/LintCode/blob/master/Java/Guess%20Number%20Higher%20or%20Lower.java)**      Level: Easy
      

binary search 公式



---

**66. [2 Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum.java)**      Level: Easy
      

tutorial:https://www.youtube.com/watch?v=P8zBxoVY1oI&feature=youtu.be

解法1：相对暴力简洁, HashMap<value, index>，找到一个value, 存一个; 若在HashMap里面 match 到结果, 就return HashMap里存的index. O(n) space && time.

解法2：Sort array, two pointer 前后++,--搜索。Sort 用时O(nlogn).     
1. 第一步 two pointer 找 value.       
2. 注意，要利用额外的空间保留original array， 用来时候找index. (此处不能用HashMap，因为以value 为key，但value可能重复)      
O(n) space, O(nlogn) time.    




---

**67. [Trim a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Trim%20a%20Binary%20Search%20Tree.java)**      Level: Easy
      

方法1:
适合复习BST. 用DFS对待每个node. 注意BST的特征: 所有left nodes都小于当下node, 所有right nodes都大于当下node.

根据题意用[L,R]切割.如果node.val<L, 直接连node带左边全丢掉, return node.right. 处理R也是一样.
分制是, DFS leftNode, rightNode. 然后接在node.left, node.right.

方法2: 用迭代, 还没有写.



---

**68. [Array Partition I.java](https://github.com/awangdev/LintCode/blob/master/Java/Array%20Partition%20I.java)**      Level: Easy
      

从结果出发, 只需要找到加法的结果，而不强调具体配对。
找到排列取单数位的规律，再考虑负数和正数的相同规律，即可找到排列求解的方法。




---

**69. [1-bit and 2-bit Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/1-bit%20and%202-bit%20Characters.java)**      Level: Easy
      

方法1:
Greedy.
从第一个bit开始数: 如果遇到1, 一定是跳两位;如果遇到0, 一定是跳一位.
loop to end, and see if index reaches the end.

方法2:
用DP硬做了一下: 
1. 如果i位是0, 那么前面dp[i-1]或者dp[i-2] true就够了.
2. 如果i位是1, 那么i-1位必须是1才满足规则, 并且 dp[i-2]需要true.



---

**70. [Non-decreasing Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Non-decreasing%20Array.java)**      Level: Easy
      

比较升序的时候, 必须要估计到 i-1, i, i+1三个数位.
写出来i-1, i+1之间的关系, 然后做合理的fix.

需要真的fix数组, 因为loop through做比较时会用到fix后的数字.



---

**71. [Max Consecutive Ones.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Consecutive%20Ones.java)**      Level: Easy
      

Basic. Math.max track结果。
记得在有对外操作的loop后，一定要把result object清理干净。



---

**72. [Find All Numbers Disappeared in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20All%20Numbers%20Disappeared%20in%20an%20Array.java)**      Level: Easy
      

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

**73. [Maximum Average Subarray I.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20I.java)**      Level: Easy
      

简单的求sum, 同时求max, 结尾求余数就好.


---

**74. [Largest Number At Least Twice of Others.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Number%20At%20Least%20Twice%20of%20Others.java)**      Level: Easy
      

找最大值, 和第二大的值, 看是否符合题意, 就行了.
分析题意, 最简单方法, 可以loop 两遍: 找最值; 作比较.
但其实, 举个反例: 有一个不满足, 就够反对这个'at least twice of alllll others'.



---

**75. [Toeplitz Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Toeplitz%20Matrix.java)**      Level: Easy
      

似乎没什么算法特点, 就是array基本运算, 然后分割成一个helper function去做重复计算, 剪短代码.
注意check MxN 的分界线.



---

**76. [Sum of Two Integers.java](https://github.com/awangdev/LintCode/blob/master/Java/Sum%20of%20Two%20Integers.java)**      Level: Easy
      

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

**77. [Swap Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Swap%20Bits.java)**      Level: Easy
      

简单, 但是很多知识点:
1. Hex 0xaaaaaaaa 是1010101....1010; 0x55555555 是01010101....0101
2. 可以用这两个hex取单数和负数. 如果需要取其他的pattern, 也可以做.
3. x很可能是negative number, 所以right-shift 要用logic shift, >>> 避免leading负数补位.



---

**78. [Intersection of Two Arrays II.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Arrays%20II.java)**      Level: Easy
      

方法1:
用HashMap: 存一个nums1, 再拿nums2 check against map. 时间/空间:O(n)

方法2:
Binary search? 需要array sorted. 否则时间O(nlogn)不值得.
[没做完, 有错]



---

**79. [Majority Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Element.java)**      Level: Easy
      

方法1: Vote 计数, vote++, vote--到最后剩下的就是winner. Time O(n), Space O(1)
Majority Number是指超半数. 超半数的数字, 最后都会至少有vote>=1: match current majority number，vote++；if not, vote--. 
注意：assume valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。

方法2: HashMap count occurance. Time, Space: O(n)

方法3: Bit manipulation. 还没有做.

Related Problems:
Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。

Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。



---

**80. [Nested List Weight Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Nested%20List%20Weight%20Sum.java)**      Level: Easy
      

方法1: 简单的处理nested structure, dfs增加depth.
方法2: bfs, queue, 处理queue.size().



---

**81. [Same Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Same%20Tree.java)**      Level: Easy
      

DFS. 确定leaf条件, && with all sub problems.

如果用BFS: 两个queue存每个tree的所有current level node. Check equality, check queue size.
Populate next level by nodes at current level.



---

**82. [Convert Sorted Array to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree.java)**      Level: Easy
      

Binary Search Tree特点: 左边的node都比右边的node小. 
如果要height相差<1, 必须左右sub tree均分. 做DFS.



---

**83. [Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum.java)**      Level: Easy
      

确定好结尾的条件, DFS

写一写: root == null => false 对parent nodes的影响. 这里发现没影响, 所以可以简化成用1个functionDFS.




---

**84. [Add Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Binary.java)**      Level: Easy
      

方法一:土办法没技术，把binary换成数字，加起来，再换成binary。如果input很大，那么很可能int,long都hold不住。不保险。

方法二:一般方法，string化为charArray,然后逐位加起，最后记得处理多余的一个carry on
注意: 需要从末尾加起来, 所以要用一个diff来帮助  shortArray[i-diff] 指向 shortArray的相对应index.



---

**85. [Add Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Digits.java)**      Level: Easy
      

方法1: 普通做法就是按照题意, double-while loop把数字加起来. 第一层循环是O(n), 然后第二层循环就少很多数位, overall O(n)

方法2: 找数学规律, 每过9个数字, 取mod就会开始重复, 所以给所有数字取mod 就可以间接找到答案. O(1)



---

**86. [Valid Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Anagram.java)**      Level: Easy
      

HashMap



---

**87. [Binary Tree Paths.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Paths.java)**      Level: Easy
      

返回所有root-to-leaf path

#### 方法1：   
Recursive:分叉. dfs.

#### 方法2:
- Iterative, 非递归练习了一下   
- 因为要每次切短list, 所以再加了一个Stack 来存level   




---

**88. [Linked List Cycle.java](https://github.com/awangdev/LintCode/blob/master/Java/Linked%20List%20Cycle.java)**      Level: Easy
      

O(1) sapce: 用快慢指针。一个跑.next, 一个跑.next.next。 总有一次，fast会因为cycle而追上slow。
那个时候其实slow.val = fast.val.

O(n) space: 用HashMap，一直add elements.  如果有重复，那么很显然是有Cycle


---

**89. [Min Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/Min%20Stack.java)**      Level: Easy
      

双Stack：一个正常stack，另一个minStack存当下level最小值. 注意维护minStack的变化

另外. 如果要maxStack，也是类似做法



---

**90. [Implement Queue using Stacks.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Queue%20using%20Stacks.java)**      Level: Easy
      

#### 双Stack
画图, 知道最后maintain的stack是那个 reverseStack: pop(), peek(), empty() 都在这个stack上, 无需变换.
push()里面做stack和reverseStack的来回倾倒.
相比老的code, 在PUSH里面做倾倒, 更容易读.

#### Previous notes
双Stack. 一个是等于是queue，一个是backfillStack.
Tricky: 是在pop()和peek()的时候backfill, 并且要等到stack用完再backfill.
写一下例子就知道，如果提早backfill，stack.peek()就不是queue的head了.




---

**91. [Reverse Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Integer.java)**      Level: Easy
      

#### 方法1
每次加上x%10，然后x不断减小～0
注意处理MAX_VALUE, MIN_VALUE
符号不重要, 直接处理, 也会保留.

#### 方法2
转换成String 然后 reverse
Space O(n), time O(n)



---

**92. [Sqrt(x).java](https://github.com/awangdev/LintCode/blob/master/Java/Sqrt(x).java)**      Level: Easy
      

#### s- qrt(int x)
- 理解题意, 从[0, x]找一个可以m*m=x的值.
- 注意, 如果找不到, 最后问考官该return一个什么值：按道理，因为return int, 会取整，那么return一个平方最close to x就可以.
- 注意 mid 用 long, 因为很可能超过最大int.

#### sqrt(double x)
- 二分float number, 应该用精度来定义结尾.
- 还是二分, 但是判断条件变成: while ( end - start > eps)
- eps = 1e-12,也就是精度到1e-12



---

**93. [First Bad Version.java](https://github.com/awangdev/LintCode/blob/master/Java/First%20Bad%20Version.java)**      Level: Easy
      

Binary Search

根据isBadVersion的性质，判断还如何end=mid or start=mid.     
isBadVersion 是有方向的嘛，一个点错了，后面全错。



---

**94. [Meeting Rooms.java](https://github.com/awangdev/LintCode/blob/master/Java/Meeting%20Rooms.java)**      Level: Easy
      

- 注意接头点要考虑所有开会结会的情况，不要恰巧漏掉相接的点
- 开会的是超人。瞬间移动接上下一个会议

#### 方法1:
找是否有overlap. priorityQueue 按照start time排序好以后, 比较current和peek: current.end > peek.start?

#### 方法2: Sweep line
- class Point{pos, flag}, PriorityQueue排序。计算count
- 跟 Number of Airplanes in the Sky 是一个类型的题目





---

**95. [Binary Tree Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Inorder%20Traversal.java)**      Level: Easy
      

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

**96. [Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20II.java)**      Level: Easy
      

Binary Tree的一个基本题: 找到所有满足条件的path

- 遍历到底，比较sum vs. target
- 注意divide的情况。要把遍历的例子写写



---

**97. [Change to Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/Change%20to%20Anagram.java)**      Level: Easy
      

HackerRank里面的random 题目: 给一个string, 一切成两半, 看两半要变化多少个字符, 能变成anagram.

- 切两半成两个String A,B. 分别在int count[26]里面++, --.
- 记录 26个小写字母的频率. 如果全部抵消, 就是anagram.
- 注意: 最后count出来要除以2：字母不同，会在不同的字母位上加减count,那么就是刚好重复计算了一遍。所以除以二

- Note: HackerRank里要注意自己写: Scanner, import java.util, non-static method ...etc.



---

**98. [Classical Binary Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Classical%20Binary%20Search.java)**      Level: Easy
      

#### Binary Search Template
- while: start + 1 < end
- mid = start + (end - start) / 2;
- 根据mid作比较
- 末尾double check start, end.




---

**99. [Climbing Stairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Climbing%20Stairs.java)**      Level: Easy
      

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

**100. [Closest Binary Search Tree Value.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Binary%20Search%20Tree%20Value.java)**      Level: Easy
      

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

**101. [Binary Tree Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Preorder%20Traversal.java)**      Level: Easy
      

#### Recursive
- 加root, left, then right. Obvious
- Divide and conquer
- 其实也不需要helper function

#### Iterative
- 先加root, 然后push上需要末尾process的在stack垫底(root.right), 然后push root.left
- Stack: push curr, push right, push left.   



---

**102. [Closest Number in Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Number%20in%20Sorted%20Array.java)**      Level: Easy
      

- Binary Search 的一种变型, LintCode无法再跑一边.
- 考虑mid-1, mid+1.
- 一旦没有mid = target.index。 那么target最终就narrow down在(mid-1,mid) 或者(mid,mid+1)   



---

**103. [Complete Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Complete%20Binary%20Tree.java)**      Level: Easy
      

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

**104. [Compare Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Compare%20Strings.java)**      Level: Easy
      

看StringA是不是包括所有 StringB的字符.

#### Basic Implementation
- 比较一下大小, null.
- 然后用int[]来count chars from A, count[x]++. 再对照chars in B, count[x]--
- 如果 count[c] < 0, 就 false.
- O(n)



---

**105. [Contains Duplicate.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate.java)**      Level: Easy
      

无序数组, 找是否有重复element, return true/false.

#### HashSet
- No brain: HashSet.
- Time O(n), Space O(n)

#### Sort, Binary Search
- Arrays.sort(x): Time O(nLogN), Space O(1)
- 排序后, 重复数会排在一起, 然后 binary search



---

**106. [Contains Duplicate II.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate%20II.java)**      Level: Easy
      

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

**107. [Nim Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Nim%20Game.java)**      Level: Easy
      

#### Brainteaser
- 著名Nim游戏
- 写一些，发现n=4,5,6,7,8...etc之后的情况有规律性: 谁先手拿到4就输了.
- 最终很简单n%4!=0就可以了,  time, space O(1)

#### DP
- 正规地找规律做, 就跟 coins in a line 一样, 按照先手后手来做
- 可以rolling array 优化空间
- Time O(n), 当然啦, 这个题目这样会timeout, 可以使用brainteaser的做法写出结果.



---

**108. [Convert Integer A to Integer B.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Integer%20A%20to%20Integer%20B.java)**      Level: Easy
      

把Integer A 转换成 Integer B 需要改变多少bits?

#### Bit Manipulation
- a^b 显示出bit format里面有不同binary code的数位.
- 每次 (a^b)>>i 移动i位之后, 再 & 1时其实是指留下这一位的数字.
- count 
- 其实用到了 ^ 找不同的bit, >> 移位, &1 mask



---

**109. [Cosine Similarity.java](https://github.com/awangdev/LintCode/blob/master/Java/Cosine%20Similarity.java)**      Level: Easy
      

根据 Cosine Similarity 的公式, basic implementation



---

**110. [Count 1 in Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%201%20in%20Binary.java)**      Level: Easy
      

count 一个 32-bit number binary format 里面有多少1

#### Bit Manipulation
- shift >> i 
- apply mask & 1

#### Convert to string O(n) space
可以把integer -> string -> char array.



---

**111. [Count and Say.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20and%20Say.java)**      Level: Easy
      

介绍一种count数字的方法, 然后每一行读出上一行的结果, 一行一行推算. 问nth行是啥样?

#### Basic Implementation
- 主要是题意很难理解, 非常misleading, 等到看明白题目, 其实没有什么算法要求.
- Count duplicates and print



---

**112. [Paint House.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House.java)**      Level: Easy
      

要paint n个房子, 还有 nx3的cost[][]. 求最少用多少cost paint 所有房子.

#### Sequence DP
- 求知道dp[n]的min cost, 但是不知道最后一个房子选什么颜色
- 那么就遍历最后一个房子(i - 1)的颜色
- 选中最后一个房子的颜色同时, 来选择 (i - 2)的颜色, 来找出最低的cost
- 考虑DP最后一个位置的情况. 发现给出了一些特殊条件, 需要附带在DP[i]上,
- 那么就定义二维数组

#### Rolling Array
- 观察发现 index[i] 只跟 [i-1] 相关, 所以2位就足够, %2


---

**113. [Longest Continuous Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Continuous%20Increasing%20Subsequence.java)**      Level: Easy
      

找连续的持续上升子序列的长度.

#### Coordinate DP
- 1D coordinate, dp 的角标, 就是代表 index i 的状态
- 求最值, dp[i] = 在index i位置的最长子序列
- 如果 nums[i] > nums[i - 1], dp[i] = dp[i - 1] + 1
- 如果没有持续上升, 那么dp[i] = 1, 重头来过
- maintain max

#### Basic
- 用一个数存current count,  maintain max



---

**114. [House Robber.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber.java)**      Level: Easy
      

搜刮房子, 相邻的不能碰. 每个房子里有value, 求max.

#### Sequence DP
- 看最后结尾状态的前一个或前两个的情况，再综合考虑当下的
- 思考的适合搞清楚当下的和之前的情况的关系
- Sequence DP, new dp[n + 1];

#### Rolling Array
- [i]'只和前两个位子 [i-1], [i - 2]'相关
- 用%2来标记 [i], [i - 1], [i - 2]三个位置.
- 其他滚动时惯用curr/prev来表示坐标, 这里%2虽然抽象, 但是更加实用.




---

**115. [Best Time to Buy and Sell Stock I.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20I.java)**      Level: Easy
      

给个array of stock prices, 限制能交易(买/买)一轮, 问如何找到最大profit.

#### 理解意思是关键
- 每天都就交易价格，n天只让买卖一次，那就找个最低价买进，找个最高价卖出
- 记录每天最小值Min是多少. O(n)
- 每天都算和当下的Min买卖，profit最大多少.

#### DP
- Find min value for first i items, new dp[n + 1].
- 然后用当天的price做减法算max profit.
- Time, Space: O(n)
- 更进一步, 用一个min来表示min[i], 因为计算中只需要当下的min.

#### Rolling array
- index i only depend on [i - 2]
- Space O(n)

#### Brutle Failed
- 每天都试着买进，然后之后的每一天尝试卖出. double for loop, O(n^2). timeout.
- 其中很多都是没必要的计算：[7, 1, 5, 3, 6, 4]。 if we know buyin with 1 is cheapest, we don't need to buyin at 5, 3, 6, 4 later on; we'll only sell on higher prices.



---

**116. [Best Time to Buy and Sell Stock II.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.java)**      Level: Easy
      

和Stock I 的区别：可以买卖多次，求总和的最大盈利.

#### 几种其他不同的思路:
- Greedy, 每次有相邻的diff符合profit条件, 就卖了, 最后把所有的diff加在一起. 计算delta, 其实简单粗暴, 也还不错.
- 如下, 从低谷找peek, sell.
- DP. (old dp solution BuyOn[], SellOn[])
- DFS计算所有(timeout).Improvement on DFS -> DP -> calculate sellOn[i] and buyOn[i], and then return buyOn[i]. 有点难想, 但是代码简单, 也是O(n)

#### Greedy
- 画图, 因为可以无限买卖, 所以只要有上升, 就卖
- 所有卖掉的, 平移加起来, 其实就是overall best profit
- O(n)

#### 找涨幅最大的区间，买卖：
- 找到低谷，买进:peek = start + 1 时候，就是每次往前走一步;若没有上涨趋势，继续往低谷前进。
- 涨到峰顶，卖出:一旦有上涨趋势，进一个while loop，涨到底, 再加个profit.
- profit += prices[peek - 1] - prices[start]; 挺特别的。
- 当没有上涨趋势时候，peek-1也就是start, 所以这里刚好profit += 0.

#### DP
- 想知道前i天的最大profit, 那么用sequence DP
- 当天的是否能卖, 取决于昨天是否买进, 也就是昨天买了或者卖了的状态: 加状态, 2D DP
- 如果今天是卖的状态, 那么昨天: 要么买进了, 今天 +price 卖出; 要么昨天刚卖, 今天不可能再卖, profit等同.
- 如果今天是买的状态, 那么昨天: 要么卖掉了, 今天 -price 买入; 要么昨天刚卖, 今天不可能再买, profit等同.

#### Rolling Array
- [i] 和 [i - 1] 相关联, roll




---

**117. [Find All Anagrams in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20All%20Anagrams%20in%20a%20String.java)**      Level: Easy
      

跟 Permutation in String 很像. 给短string p， 长string s.

找所有p的 anagram (permutation) 在s 里面的起始index.

#### HashTable
- count character apperance 就想到hashtable
- 注意countS, countP 的技巧: 作比较只需要O(26)
- Overall timeO(n)
- 小心不要用一个int[] count 来技术 查0, 复杂度是O(n)



---

**118. [Count Primes.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20Primes.java)**      Level: Easy
      

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

**119. [Delete Node in a Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Delete%20Node%20in%20a%20Linked%20List.java)**      Level: Easy
      

Given Singlely linked list, 删除一个任意node (不能是head node)

#### Basic
- update node.val
- Link curr.next to curr.next.next



---

**120. [Excel Sheet Column Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Excel%20Sheet%20Column%20Number.java)**      Level: Easy
      

#### Math
- 26位的运算, 根据10位运算去思考
- 'A' - 'A' = 0. 所以 char - 'A' + 1 = 题目里的对应数位
- 或者: 26位运算和10位一样:num += 每位的digit * Math.pow(26, 数位号)




---

**121. [Excel Sheet Column Title.java](https://github.com/awangdev/LintCode/blob/master/Java/Excel%20Sheet%20Column%20Title.java)**      Level: Easy
      

#### 基本换算
- 26位
- 从末尾开始, mod %26 可以拿到 末尾数字 remain = n % 26
- 特殊: remain = 0 的时候, 也就是说n是26的倍数, 末尾应该是 'Z'
- 记录'Z'了之后, n--




---

**122. [Flip Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Flip%20Game.java)**      Level: Easy
      

#### String
- 可以用 sb.replace(i, j, "replacement string")
- 简单按 window=2 来扫描
- 原来只需要从'++'转到'--'的情况
- O(n)



---

**123. [Implement strStr().java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20strStr().java)**      Level: Easy
      

给两个string A, B, 找一个 B 在 A 种的起始位置.

#### Two Pointer
- 找到B在A中的起始位置, 然后看一下从这个点开始的substring是否等于B就可以了
- 还挺多坑的, 这些可以帮助优化:
- 1. 当B是“”的时候，也就是能在A的其实位置找到B....index = 0.
- 2. edge condition: 如果 haystack.length() < needle.length() 的话, 必须错, return -1
- 3. 如果在某个index, A后面剩下的长度, 比B的长度短, 也是误解, return -1



---

**124. [Last Position of Target.java](https://github.com/awangdev/LintCode/blob/master/Java/Last%20Position%20of%20Target.java)**      Level: Easy
      

给一个sorted integer array, 找target出现的最后的index. array 里有重复数字

有重复,不是末尾点，继续binary search



---

**125. [Length of Last Word.java](https://github.com/awangdev/LintCode/blob/master/Java/Length%20of%20Last%20Word.java)**      Level: Easy
      

给一个String, 里面有lower case character 和 ' '. 找最后一个单个word的长度

#### basics
- 从末尾找' ', 找到了计算长度
- 记得要s.trim(), 把首尾的space去掉



---

**126. [Longest Increasing Continuous subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence.java)**      Level: Easy
      

https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/

O(n)跑2遍for.
O(1)是用了两个int来存：每次到i点时，i点满足条件或不满足条件所有的longestIncreasingContinuousSubsequence.
特点：返跑一回，ans还是继续和left轮的ans作比较；求的所有情况的最大值嘛。



---

