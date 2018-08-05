 
 
 
## Easy (148)
**0. [Group Shifted Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Group%20Shifted%20Strings.java)**      Level: Easy      Tags: []
      
相同shift规则的string, 能被推算到同一个零起始点，就是共同减去一个char,最后就相等。以此作为key，用HashMap。一目了然。

记得根据题目意思，一开始要String[] sort一下。



---

**1. [Hamming Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Hamming%20Distance.java)**      Level: Easy      Tags: []
      
bit: XOR, &, shift>>



---

**2. [Happy Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Happy%20Number.java)**      Level: Easy      Tags: []
      
Basic Implementation of the requirements.

用HashSet存查看过的数值。若重复，return false.



---

**3. [HashWithArray.java](https://github.com/awangdev/LintCode/blob/master/Java/HashWithArray.java)**      Level: Easy      Tags: []
      



---

**4. [Heaters.java](https://github.com/awangdev/LintCode/blob/master/Java/Heaters.java)**      Level: Easy      Tags: []
      
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

**5. [IndexMatch.java](https://github.com/awangdev/LintCode/blob/master/Java/IndexMatch.java)**      Level: Easy      Tags: []
      
有序, 假设有这样的数字:target.        
target 左边的数字，一定不比index大，target右边的数字，一定比index大。     
这样可以binary search.O(logn)



---

**6. [Insert Node in a Binary Search Tree .java](https://github.com/awangdev/LintCode/blob/master/Java/Insert%20Node%20in%20a%20Binary%20Search%20Tree%20.java)**      Level: Easy      Tags: [BST]
      

往Binary Search Tree里面加东西，一定会找到一个合适的leaf加上去。

那么：就是说someNode.left or someNode.right是null时，就是insert node的地方。

找到那个someNode就按照正常的Binary Search Tree规律。



---

**7. [Intersection of Two Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Arrays.java)**      Level: Easy      Tags: []
      
方法1: 用到hashset找unique && duplicate: O(m+n)
方法2: 可以用binary search 找数字. Note:binary search一定需要array sorted: nLog(m)



---

**8. [Intersection of Two Linked Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Linked%20Lists.java)**      Level: Easy      Tags: [Linked List]
      
1525664839

给两个 linked list, 问从哪个node开始, 两个 linked list 开始有重复?

#### Basics
- 长短list，找重合点
- 长度不同的话，切掉长的list那个的extra length
- 那么起点一样后，重合点就会同时到达
- Time O(n) * 2, constant space



---

**9. [Isomorphic Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Isomorphic%20Strings.java)**      Level: Easy      Tags: []
      
HashMap 来确认match。有几种情况考虑:

1. Match. 就是map.containsKey, map.containsValue, and char1 == char2. Perfect.

2. Either Key not exist, or Value not exit. False;

3. Both key and Value exist, but map.get(char1) != char2.  Miss-match. False.

4. None of Key or Value exist in HashMap. Then add the match.



---

**10. [Jewels and Stones.java](https://github.com/awangdev/LintCode/blob/master/Java/Jewels%20and%20Stones.java)**      Level: Easy      Tags: [Hash Table]
      
1524017454

给J 和 S两个string. J里的character是unique 的珠宝, S 里面的character包含珠宝和石头. 找S里面有多少珠宝

#### Basic HashSet



---

**11. [Longest Univalue Path.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Univalue%20Path.java)**      Level: Easy      Tags: []
      
弄明白path的意义: 连接node的edge.
要找MAX, 可以在class scope里面定义一个max variable.

用minimum amount of code来概括几种不同的情况: left == root, right == root, or left == root == right.



---

**12. [Matrix Zigzag Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Matrix%20Zigzag%20Traversal.java)**      Level: Easy      Tags: []
      
分析4个step:right, left-bottom,down,right-up    
implement时注意index.有点耐心



---

**13. [Minimum Absolute Difference in BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Absolute%20Difference%20in%20BST.java)**      Level: Easy      Tags: [BST]
      

BST: inorder-traversal: 先left node(adding to stack till left leav), 再process stack.peek (mid node), 再 add rightNode && dive to rightNode.left leaf



---

**14. [O(1) Check Power of 2.java](https://github.com/awangdev/LintCode/blob/master/Java/O(1)%20Check%20Power%20of%202.java)**      Level: Easy      Tags: [Bit Manipulation]
      



---

**15. [Partition Array by Odd and Even.java](https://github.com/awangdev/LintCode/blob/master/Java/Partition%20Array%20by%20Odd%20and%20Even.java)**      Level: Easy      Tags: [Array, Two Pointers]
      

- 更正常的start/end partition pointer类似: when condition meet, swap
- Clean up TODO



---

**16. [Pascal's Triangle II.java](https://github.com/awangdev/LintCode/blob/master/Java/Pascal's%20Triangle%20II.java)**      Level: Easy      Tags: []
      
简单处理array list.



---

**17. [Permutation Index.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutation%20Index.java)**      Level: Easy      Tags: []
      
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

**18. [Recover Rotated Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Recover%20Rotated%20Sorted%20Array.java)**      Level: Easy      Tags: [Array]
      

rotate的意思，是有个点断开，把一边的array节选出来放在另外一边。
Rotate三步：
rotate前半
rotate后半
rotate全部

注意先找到断点。


---

**19. [Reshape the Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Reshape%20the%20Matrix.java)**      Level: Easy      Tags: []
      
读例子理解题意.
理清counter case. Basic implementation



---

**20. [Reverse String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20String.java)**      Level: Easy      Tags: []
      
Similar to Reverse Integer.
可以用StringBuffer, 也可以two pointer reverse head/tail



---

**21. [Roman to Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/Roman%20to%20Integer.java)**      Level: Easy      Tags: []
      
熟悉罗马字母规则     
1. 'I V X L C D M' 分别代表的数字     
2. 列举combo的情况，需要从原sum里面减掉多加的部分： 'IV, IX'减2, 'XL, XC'减20, 'CD, CM'减200. 

https://en.wikipedia.org/wiki/Roman_numerals



---

**22. [Search Insert Position.java](https://github.com/awangdev/LintCode/blob/master/Java/Search%20Insert%20Position.java)**      Level: Easy      Tags: []
      
一般的binary search.
在结尾判断该return 哪个position。


---

**23. [Shortest Word Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Shortest%20Word%20Distance.java)**      Level: Easy      Tags: []
      
找short distance, wordB可以在wordA的前后；而同一时间，只需要计算一个最近的up to date的distance。
greedy不断变更A/B index再做比较即可。



---

**24. [Single Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Single%20Number.java)**      Level: Easy      Tags: []
      
Bit XOR: 当两个bit不同时，return 1. 
题目正要消光所有重复出现的数儿留下出现一次的那个.



---

**25. [String Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/String%20Permutation.java)**      Level: Easy      Tags: []
      
把#of occurrences 存进HashMap, 第一个string 做加法，第二个string做减法。最后看是否有不等于0的作判断。



---

**26. [String to Integer(atoi).java](https://github.com/awangdev/LintCode/blob/master/Java/String%20to%20Integer(atoi).java)**      Level: Easy      Tags: []
      
方法1: 问清情况，一点一点把case都涉及到。

方法2: 用regular expression。if (!str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")).  猛了一点



---

**27. [Strobogrammatic Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Strobogrammatic%20Number.java)**      Level: Easy      Tags: []
      
根据题意枚举, 再根据规则basic implementation



---

**28. [Trailing Zeros.java](https://github.com/awangdev/LintCode/blob/master/Java/Trailing%20Zeros.java)**      Level: Easy      Tags: [Math]
      



---

**29. [Two Strings Are Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Strings%20Are%20Anagrams.java)**      Level: Easy      Tags: []
      
方法1:char ascii 用count[256]   
坑：不要想象这个是个26letter lowercase. may not be true.

方法2: 若是其他字符encoding, 而不只是utf16-encoding (java char)?   
那么就继续用string去做



---

**30. [Valid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Parentheses.java)**      Level: Easy      Tags: []
      
剥皮过程。解铃还须系铃人   
左边的外皮'{['在stack底部   
右边的外皮应该和stack顶上的左外皮一一对应 





---

**31. [Valid Sudoku.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Sudoku.java)**      Level: Easy      Tags: []
      
用HashSet存visited value.

方法1: 在nest for loop里面validate row,col,and block.     
validate block要利用i 和 j 增长的规律。    
说白了，i && j是按照0~n增长的index, 具体怎么用是可以flexible的。这个方法在同一个nest for loop解决所有运算。

方法2: 单独做block validation: validate block的时候虽然看到了4层for.其实也就是n^2.



---

**32. [Word Pattern.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Pattern.java)**      Level: Easy      Tags: []
      
每个char代表一个pattern。用HashMap<char, str>.
但不够，如果a也match dog, b也match dog, 纠错了。比如pattern = "abba", str = "dog dog dog dog"。
因此第二个HashMap<str, char> 反过来。
确保pattern和str一一对应。



---

**33. [Find Anagram Mappings.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Anagram%20Mappings.java)**      Level: Easy      Tags: [Hash Table]
      

比较简单, 用HashMap 存index list. 最后再遍历一遍数组A, 列举出所有元素.
O(n)



---

**34. [Judge Route Circle.java](https://github.com/awangdev/LintCode/blob/master/Java/Judge%20Route%20Circle.java)**      Level: Easy      Tags: [String]
      

简单的character checking. 各个方向, 加加减减.



---

**35. [Island Perimeter.java](https://github.com/awangdev/LintCode/blob/master/Java/Island%20Perimeter.java)**      Level: Easy      Tags: [Hash Table]
      

最简单的方法: 每个格子4个墙;每个shared的墙要-2 (墙是两面, -1 * 2)
最后合计结果就好.

不必想太多用HashMap做.但是也可以思考一下:
- 把每个block相连的block全部存在以当下block为key的list里面. 那么这里需要把2D坐标, 转化成一个index.
- 最后得到的map, 所有的key-value应该都有value-key的反向mapping, 那么久可以消除干净, 每一次消除就是一个shared wall.
- 一点点optimization: DFS去找所有的island, 如果island的图非常大, 而island本身不打,那么适合optimize.
  但是整体代码过于复杂. 不建议写.




---

**36. [First Unique Character in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/First%20Unique%20Character%20in%20a%20String.java)**      Level: Easy      Tags: [Hash Table, String]
      

方法1: 按照题意, 找到第一个 first index == last index的字母.

方法2: 用hashmap存字母的index, 有些重复字母的index就会是个list. 找到单一index, 结合成list, sort, return list.get(0)



---

**37. [Power of Three.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Three.java)**      Level: Easy      Tags: [Math]
      

方法1:
Power of 3:  3 ^ x == n ?
意思是 n / 3 一直除, 最后是可以等于1的, 那么就有了 n/=3, check n%3, 最后看结果是不是整除到1的做法. 用while loop.

方法2:
如果n是power of 3, 那么 3 ^ x的这个 x一定是个比n小的数字. 那么可以在 0 ~ n 之间做binary serach, 但是就比较慢.

方法3:
巧妙的想法.最大的3^x integer是 3^19. 那么找到这个数, 一定可以被n整除. 一步到位.



---

**38. [Plus One.java](https://github.com/awangdev/LintCode/blob/master/Java/Plus%20One.java)**      Level: Easy      Tags: [Array, Math]
      

简单的实现, 加1, 进位. 唯一取巧的地方, 最后如果要多一位, 一定是10000...这个模式, 可以走捷径, 直接来个+1size的array, 然后第一位=1.
注意,转换成long也不合理,用太多memory.


---

**39. [Power of Two.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Two.java)**      Level: Easy      Tags: [Bit Manipulation, Math]
      

跟powerOfThree一样: 可以loop, check mod; 也可以用binary search找合适的数字.



---

**40. [Reverse Vowels of a String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Vowels%20of%20a%20String.java)**      Level: Easy      Tags: [String, Two Pointers]
      

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

**41. [Guess Number Higher or Lower.java](https://github.com/awangdev/LintCode/blob/master/Java/Guess%20Number%20Higher%20or%20Lower.java)**      Level: Easy      Tags: [Binary Search]
      

binary search 公式



---

**42. [Trim a Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Trim%20a%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [BST, Tree]
      

方法1:
适合复习BST. 用DFS对待每个node. 注意BST的特征: 所有left nodes都小于当下node, 所有right nodes都大于当下node.

根据题意用[L,R]切割.如果node.val<L, 直接连node带左边全丢掉, return node.right. 处理R也是一样.
分制是, DFS leftNode, rightNode. 然后接在node.left, node.right.

方法2: 用迭代, 还没有写.



---

**43. [Array Partition I.java](https://github.com/awangdev/LintCode/blob/master/Java/Array%20Partition%20I.java)**      Level: Easy      Tags: [Array]
      

给串数字, size=2n, 找pairs, 然后需要sum of min(pair) 最大.

(a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

#### Sort, basics
- 从结果出发, 只需要找到加法的结果，而不强调具体配对.
- 写一写example就能做
- 找到排列取单数位的规律，再考虑负数和正数的相同规律，即可找到排列求解的方法。
- sort, O(nlogn)




---

**44. [1-bit and 2-bit Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/1-bit%20and%202-bit%20Characters.java)**      Level: Easy      Tags: [Array]
      

方法1:
Greedy.
从第一个bit开始数: 如果遇到1, 一定是跳两位;如果遇到0, 一定是跳一位.
loop to end, and see if index reaches the end.

方法2:
用DP硬做了一下: 
1. 如果i位是0, 那么前面dp[i-1]或者dp[i-2] true就够了.
2. 如果i位是1, 那么i-1位必须是1才满足规则, 并且 dp[i-2]需要true.



---

**45. [Non-decreasing Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Non-decreasing%20Array.java)**      Level: Easy      Tags: [Array]
      

比较升序的时候, 必须要估计到 i-1, i, i+1三个数位.
写出来i-1, i+1之间的关系, 然后做合理的fix.

需要真的fix数组, 因为loop through做比较时会用到fix后的数字.



---

**46. [Max Consecutive Ones.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Consecutive%20Ones.java)**      Level: Easy      Tags: [Array]
      

Basic. Math.max track结果。
记得在有对外操作的loop后，一定要把result object清理干净。



---

**47. [Find All Numbers Disappeared in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20All%20Numbers%20Disappeared%20in%20an%20Array.java)**      Level: Easy      Tags: [Array]
      

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

**48. [Maximum Average Subarray I.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20I.java)**      Level: Easy      Tags: [Array, Subarray]
      
time: O(n)
space: O(1)

简单的求sum of fixed window k, 同时求max avg, 结尾求余数就好.



---

**49. [Largest Number At Least Twice of Others.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Number%20At%20Least%20Twice%20of%20Others.java)**      Level: Easy      Tags: [Array]
      

找最大值, 和第二大的值, 看是否符合题意, 就行了.
分析题意, 最简单方法, 可以loop 两遍: 找最值; 作比较.
但其实, 举个反例: 有一个不满足, 就够反对这个'at least twice of alllll others'.



---

**50. [Toeplitz Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Toeplitz%20Matrix.java)**      Level: Easy      Tags: [Array]
      

似乎没什么算法特点, 就是array基本运算, 然后分割成一个helper function去做重复计算, 剪短代码.
注意check MxN 的分界线.



---

**51. [Sum of Two Integers.java](https://github.com/awangdev/LintCode/blob/master/Java/Sum%20of%20Two%20Integers.java)**      Level: Easy      Tags: [Bit Manipulation]
      

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

**52. [Swap Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Swap%20Bits.java)**      Level: Easy      Tags: [Bit Manipulation]
      

简单, 但是很多知识点:
1. Hex 0xaaaaaaaa 是1010101....1010; 0x55555555 是01010101....0101
2. 可以用这两个hex取单数和负数. 如果需要取其他的pattern, 也可以做.
3. x很可能是negative number, 所以right-shift 要用logic shift, >>> 避免leading负数补位.



---

**53. [Intersection of Two Arrays II.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Arrays%20II.java)**      Level: Easy      Tags: [Binary Search, Hash Table, Sort, Two Pointers]
      

方法1:
用HashMap: 存一个nums1, 再拿nums2 check against map. 时间/空间:O(n)

方法2:
Binary search? 需要array sorted. 否则时间O(nlogn)不值得.
[没做完, 有错]



---

**54. [Majority Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Element.java)**      Level: Easy      Tags: [Array, Bit Manipulation, Divide and Conquer]
      

#### Vote 计数
- vote++, vote--到最后剩下的就是winner. Time O(n), Space O(1)
- Majority Number是指超半数. 超半数的数字, 最后都会至少有vote>=1: match current majority number，vote++；if not, vote--. 
- 注意：assume valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。

#### HashMap count occurance
- Time, Space: O(n)

#### Bit manipulation
- TODO

#### Related Problems
- Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。
- Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。



---

**55. [Nested List Weight Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Nested%20List%20Weight%20Sum.java)**      Level: Easy      Tags: [BFS, DFS]
      

给一串integers, list里面可能有nest list. 算总的sum. 规则, 如果是nested list, 每深一个depth, sum要乘以depth.

#### DFS
- New interface to understand: object contains integer or object
- Visit all && sum, consider dfs.
- bottom->up is easier: pick nested object and execute dfs, which returns sum of it, add with (level value * weight).
- 简单的处理nested structure, dfs增加depth.
- time: visit all nodes eventually, O(n), space O(n)
- Note1: not multiplying on overall level sum. Only multiply level with single value at this level.
- Note2:top->bottom is not necessary: there is not need of passing added object into next level.

#### BFS
- bfs, queue, 处理queue.size().
- use a level variable to track levels



---

**56. [Same Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Same%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      

给两个 binary tree, 看两个tree是否identical.

#### DFS
- DFS. 确定leaf条件, && with all dfs(sub1, sub2).
- 这里无论如何都要走过所有的node, 所以dfs更加合适, 好写.

#### BFS
- 两个queue存每个tree的所有current level node. Check equality, check queue size.
- Populate next level by nodes at current level.



---

**57. [Convert Sorted Array to Binary Search Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Sorted%20Array%20to%20Binary%20Search%20Tree.java)**      Level: Easy      Tags: [DFS, Divide and Conquer, Tree]
      

如题, build balanced BST from sorted array

#### DFS
- Binary Search Tree特点: 左边的node都比右边的node小. 
- height balance, subtree height 相差<1, 必须左右sub tree均分. 做DFS(num, start, end)
- 在每一个level, 找到中间点, 然后分割2半, 继续dfs
- Divide and Conquer
- time/space: O(n), visit all nodes, no redundant visits.



---

**58. [Add Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Binary.java)**      Level: Easy      Tags: [Math, String]
      

方法一:土办法没技术，把binary换成数字，加起来，再换成binary。如果input很大，那么很可能int,long都hold不住。不保险。

方法二:一般方法，string化为charArray,然后逐位加起，最后记得处理多余的一个carry on
注意: 需要从末尾加起来, 所以要用一个diff来帮助  shortArray[i-diff] 指向 shortArray的相对应index.



---

**59. [Add Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Digits.java)**      Level: Easy      Tags: [Math]
      

方法1: 普通做法就是按照题意, double-while loop把数字加起来. 第一层循环是O(n), 然后第二层循环就少很多数位, overall O(n)

方法2: 找数学规律, 每过9个数字, 取mod就会开始重复, 所以给所有数字取mod 就可以间接找到答案. O(1)



---

**60. [Valid Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Anagram.java)**      Level: Easy      Tags: [Hash Table, Sort]
      

HashMap



---

**61. [Binary Tree Paths.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Paths.java)**      Level: Easy      Tags: [Backtracking, Binary Tree, DFS]
      

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

**62. [Linked List Cycle.java](https://github.com/awangdev/LintCode/blob/master/Java/Linked%20List%20Cycle.java)**      Level: Easy      Tags: [Linked List, Two Pointers]
      

O(1) sapce: 用快慢指针。一个跑.next, 一个跑.next.next。 总有一次，fast会因为cycle而追上slow。
那个时候其实slow.val = fast.val.

O(n) space: 用HashMap，一直add elements.  如果有重复，那么很显然是有Cycle


---

**63. [Min Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/Min%20Stack.java)**      Level: Easy      Tags: [Design, Stack]
      

双Stack：一个正常stack，另一个minStack存当下level最小值. 注意维护minStack的变化

另外. 如果要maxStack，也是类似做法



---

**64. [Implement Queue using Stacks.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Queue%20using%20Stacks.java)**      Level: Easy      Tags: [Design, Stack]
      

#### 双Stack
画图, 知道最后maintain的stack是那个 reverseStack: pop(), peek(), empty() 都在这个stack上, 无需变换.
push()里面做stack和reverseStack的来回倾倒.
相比老的code, 在PUSH里面做倾倒, 更容易读.

#### Previous notes
双Stack. 一个是等于是queue，一个是backfillStack.
Tricky: 是在pop()和peek()的时候backfill, 并且要等到stack用完再backfill.
写一下例子就知道，如果提早backfill，stack.peek()就不是queue的head了.




---

**65. [Reverse Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Integer.java)**      Level: Easy      Tags: [Math]
      

#### 方法1
每次加上x%10，然后x不断减小～0
注意处理MAX_VALUE, MIN_VALUE
符号不重要, 直接处理, 也会保留.

#### 方法2
转换成String 然后 reverse
Space O(n), time O(n)



---

**66. [Sqrt(x).java](https://github.com/awangdev/LintCode/blob/master/Java/Sqrt(x).java)**      Level: Easy      Tags: [Binary Search, Math]
      

#### s- qrt(int x)
- 理解题意, 从[0, x]找一个可以m*m=x的值.
- 注意, 如果找不到, 最后问考官该return一个什么值：按道理，因为return int, 会取整，那么return一个平方最close to x就可以.
- 注意 mid 用 long, 因为很可能超过最大int.

#### sqrt(double x)
- 二分float number, 应该用精度来定义结尾.
- 还是二分, 但是判断条件变成: while ( end - start > eps)
- eps = 1e-12,也就是精度到1e-12



---

**67. [First Bad Version.java](https://github.com/awangdev/LintCode/blob/master/Java/First%20Bad%20Version.java)**      Level: Easy      Tags: [Binary Search]
      

Binary Search

根据isBadVersion的性质，判断还如何end=mid or start=mid.     
isBadVersion 是有方向的嘛，一个点错了，后面全错。



---

**68. [Meeting Rooms.java](https://github.com/awangdev/LintCode/blob/master/Java/Meeting%20Rooms.java)**      Level: Easy      Tags: [PriorityQueue, Sort, Sweep Line]
      

- 注意接头点要考虑所有开会结会的情况，不要恰巧漏掉相接的点
- 开会的是超人。瞬间移动接上下一个会议

#### 方法1:
找是否有overlap. priorityQueue 按照start time排序好以后, 比较current和peek: current.end > peek.start?

#### 方法2: Sweep line
- class Point{pos, flag}, PriorityQueue排序。计算count
- 跟 Number of Airplanes in the Sky 是一个类型的题目



---

**69. [Binary Tree Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Inorder%20Traversal.java)**      Level: Easy      Tags: [Hash Table, Stack, Tree]
      

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

**70. [Change to Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/Change%20to%20Anagram.java)**      Level: Easy      Tags: [String]
      

HackerRank里面的random 题目: 给一个string, 一切成两半, 看两半要变化多少个字符, 能变成anagram.

- 切两半成两个String A,B. 分别在int count[26]里面++, --.
- 记录 26个小写字母的频率. 如果全部抵消, 就是anagram.
- 注意: 最后count出来要除以2：字母不同，会在不同的字母位上加减count,那么就是刚好重复计算了一遍。所以除以二

- Note: HackerRank里要注意自己写: Scanner, import java.util, non-static method ...etc.



---

**71. [Classical Binary Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Classical%20Binary%20Search.java)**      Level: Easy      Tags: [Binary Search]
      

#### Binary Search Template
- while: start + 1 < end
- mid = start + (end - start) / 2;
- 根据mid作比较
- 末尾double check start, end.




---

**72. [Climbing Stairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Climbing%20Stairs.java)**      Level: Easy      Tags: [DP, Memoization, Sequence DP]
      

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
- dp[0]往往是有特殊状态的
- O(n) space, time

#### 序列DP, 滚动数组
- [i] only associates with [i-2], [i-1].
- %2
- O(1) space



---

**73. [Closest Binary Search Tree Value.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Binary%20Search%20Tree%20Value.java)**      Level: Easy      Tags: [BST, Binary Search, Tree]
      

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

**74. [Binary Tree Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Preorder%20Traversal.java)**      Level: Easy      Tags: [BFS, DFS, Stack, Tree]
      

#### Recursive
- 加root, left, then right. Obvious
- Divide and conquer
- 其实也不需要helper function

#### Iterative
- 先加root, 然后push上需要末尾process的在stack垫底(root.right), 然后push root.left
- Stack: push curr, push right, push left.   



---

**75. [Closest Number in Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Closest%20Number%20in%20Sorted%20Array.java)**      Level: Easy      Tags: [Binary Search]
      

- Binary Search 的一种变型, LintCode无法再跑一边.
- 考虑mid-1, mid+1.
- 一旦没有mid = target.index。 那么target最终就narrow down在(mid-1,mid) 或者(mid,mid+1)   



---

**76. [Complete Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Complete%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, Tree]
      

A complete binary tree is a binary tree in which every level, except possibly the last,

is completely filled, and all nodes are as far left as possible

#### BFS
- 当出现了第一次有 null children的node的时候, 说明到了leaf level, mark flag = true;
- 自此以后，queue再不该有node再有child; queue后面出现的node的left/right child应该都是null
- 否则就是有问题, return false;




---

**77. [Compare Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Compare%20Strings.java)**      Level: Easy      Tags: [String]
      

看StringA是不是包括所有 StringB的字符.

#### Basic Implementation
- 比较一下大小, null.
- 然后用int[]来count chars from A, count[x]++. 再对照chars in B, count[x]--
- 如果 count[c] < 0, 就 false.
- O(n)



---

**78. [Contains Duplicate.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate.java)**      Level: Easy      Tags: [Array, Hash Table]
      

无序数组, 找是否有重复element, return true/false.

#### HashSet
- No brain: HashSet.
- Time O(n), Space O(n)

#### Sort, Binary Search
- Arrays.sort(x): Time O(nLogN), Space O(1)
- 排序后, 重复数会排在一起, 然后 binary search



---

**79. [Contains Duplicate II.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate%20II.java)**      Level: Easy      Tags: [Array, Hash Table]
      

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

**80. [Nim Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Nim%20Game.java)**      Level: Easy      Tags: [Brainteaser, DP, Game Theory]
      

#### Brainteaser
- 著名Nim游戏
- 写一些，发现n=4,5,6,7,8...etc之后的情况有规律性: 谁先手拿到4就输了.
- 最终很简单n%4!=0就可以了,  time, space O(1)

#### DP
- 正规地找规律做, 就跟 coins in a line 一样, 按照先手后手来做
- 可以rolling array 优化空间
- Time O(n), 当然啦, 这个题目这样会timeout, 可以使用brainteaser的做法写出结果.



---

**81. [Convert Integer A to Integer B.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Integer%20A%20to%20Integer%20B.java)**      Level: Easy      Tags: [Bit Manipulation]
      

把Integer A 转换成 Integer B 需要改变多少bits?

#### Bit Manipulation
- a^b 显示出bit format里面有不同binary code的数位.
- 每次 (a^b)>>i 移动i位之后, 再 & 1时其实是指留下这一位的数字.
- count 
- 其实用到了 ^ 找不同的bit, >> 移位, &1 mask



---

**82. [Cosine Similarity.java](https://github.com/awangdev/LintCode/blob/master/Java/Cosine%20Similarity.java)**      Level: Easy      Tags: [Basic Implementation]
      

根据 Cosine Similarity 的公式, basic implementation



---

**83. [Count 1 in Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%201%20in%20Binary.java)**      Level: Easy      Tags: [Bit Manipulation]
      

count 一个 32-bit number binary format 里面有多少1

#### Bit Manipulation
- shift >> i 
- apply mask & 1

#### Convert to string O(n) space
可以把integer -> string -> char array.



---

**84. [Count and Say.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20and%20Say.java)**      Level: Easy      Tags: [Basic Implementation, String]
      

介绍一种count数字的方法, 然后每一行读出上一行的结果, 一行一行推算. 问nth行是啥样?

#### Basic Implementation
- 主要是题意很难理解, 非常misleading, 等到看明白题目, 其实没有什么算法要求.
- Count duplicates and print



---

**85. [Paint House.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20House.java)**      Level: Easy      Tags: [DP, Sequence DP, Status DP]
      
time: O(nm), m = # of colors
space: O(nm)

要paint n个房子, 还有 nx3的cost[][]. 求最少用多少cost paint 所有房子.

#### Sequence DP
- 求dp[i]的min cost, 但是不知道最后一个房子选什么颜色, 那么就遍历最后一个房子(i - 1)的颜色
- 选中最后一个房子的颜色同时, 根据dp[i - 1]的颜色/cost + cost[i-1], 来找出最低的cost
- 考虑DP最后一个位置的情况(颜色选择):需要附带颜色status在DP[i]上: 定义二维数组, 其中一位是status
- dp[i][j]: 前i个house 刷成 j 号颜色的最小cost.
- dp[0][j] = 0: 0th house, no cost
- 计算顺序: 从每一个house开始算起 [0 ~ n], first for loop
- 然后选ith 房子的 color, 再选(i-1)th 房子的color. Double for loop, skip same color

#### Rolling Array
- 观察发现 index[i] 只跟 [i-1] 相关, 所以2位就足够, %2



---

**86. [Longest Continuous Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Continuous%20Increasing%20Subsequence.java)**      Level: Easy      Tags: [Array, Coordinate DP, DP]
      

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

**87. [House Robber.java](https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber.java)**      Level: Easy      Tags: [DP, Sequence DP]
      
time: O(n)
space: O(n) or rolling array O(1)

搜刮房子, 相邻的不能碰. 每个房子里有value, 求max.

#### Sequence DP
- dp[i]: 前i个房子拿到的max gain
- 看最后结尾状态的前一个或前两个的情况，再综合考虑当下的
- 搞清楚当下[i]的和之前[i-x]的情况的关系: 不可以连着house, 那么就直接考虑 dp[i-2]的情况
- Sequence DP, new dp[n + 1];

#### Rolling Array
- [i]'只和前两个位子 [i-1], [i - 2]'相关
- 用%2来标记 [i], [i - 1], [i - 2]三个位置.
- 其他滚动时惯用curr/prev来表示坐标, 这里%2虽然抽象, 但是更加实用.




---

**88. [Find All Anagrams in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20All%20Anagrams%20in%20a%20String.java)**      Level: Easy      Tags: [Hash Table]
      

跟 Permutation in String 很像. 给短string p， 长string s.

找所有p的 anagram (permutation) 在s 里面的起始index.

#### HashTable
- count character apperance 就想到hashtable
- 注意countS, countP 的技巧: 作比较只需要O(26)
- Overall timeO(n)
- 小心不要用一个int[] count 来技术 查0, 复杂度是O(n)



---

**89. [Count Primes.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20Primes.java)**      Level: Easy      Tags: [Hash Table, Math]
      

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

**90. [Delete Node in a Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Delete%20Node%20in%20a%20Linked%20List.java)**      Level: Easy      Tags: [Linked List]
      

Given Singlely linked list, 删除一个任意node (不能是head node)

#### Basic
- update node.val
- Link curr.next to curr.next.next



---

**91. [Excel Sheet Column Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Excel%20Sheet%20Column%20Number.java)**      Level: Easy      Tags: [Math]
      

#### Math
- 26位的运算, 根据10位运算去思考
- 'A' - 'A' = 0. 所以 char - 'A' + 1 = 题目里的对应数位
- 或者: 26位运算和10位一样:num += 每位的digit * Math.pow(26, 数位号)




---

**92. [Excel Sheet Column Title.java](https://github.com/awangdev/LintCode/blob/master/Java/Excel%20Sheet%20Column%20Title.java)**      Level: Easy      Tags: [Math]
      

#### 基本换算
- 26位
- 从末尾开始, mod %26 可以拿到 末尾数字 remain = n % 26
- 特殊: remain = 0 的时候, 也就是说n是26的倍数, 末尾应该是 'Z'
- 记录'Z'了之后, n--




---

**93. [Flip Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Flip%20Game.java)**      Level: Easy      Tags: [String]
      

#### String
- 可以用 sb.replace(i, j, "replacement string")
- 简单按 window=2 来扫描
- 原来只需要从'++'转到'--'的情况
- O(n)



---

**94. [Implement strStr().java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20strStr().java)**      Level: Easy      Tags: [String, Two Pointers]
      

给两个string A, B, 找一个 B 在 A 种的起始位置.

#### Two Pointer
- 找到B在A中的起始位置, 然后看一下从这个点开始的substring是否等于B就可以了
- 还挺多坑的, 这些可以帮助优化:
- 1. 当B是“”的时候，也就是能在A的其实位置找到B....index = 0.
- 2. edge condition: 如果 haystack.length() < needle.length() 的话, 必须错, return -1
- 3. 如果在某个index, A后面剩下的长度, 比B的长度短, 也是误解, return -1



---

**95. [Last Position of Target.java](https://github.com/awangdev/LintCode/blob/master/Java/Last%20Position%20of%20Target.java)**      Level: Easy      Tags: [Binary Search]
      

给一个sorted integer array, 找target出现的最后的index. array 里有重复数字

有重复,不是末尾点，继续binary search



---

**96. [Length of Last Word.java](https://github.com/awangdev/LintCode/blob/master/Java/Length%20of%20Last%20Word.java)**      Level: Easy      Tags: [String]
      

给一个String, 里面有lower case character 和 ' '. 找最后一个单个word的长度

#### basics
- 从末尾找' ', 找到了计算长度
- 记得要s.trim(), 把首尾的space去掉



---

**97. [Longest Increasing Continuous subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence.java)**      Level: Easy      Tags: [Array, Coordinate DP, DP]
      

https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/

O(n)跑2遍for.
O(1)是用了两个int来存：每次到i点时，i点满足条件或不满足条件所有的longestIncreasingContinuousSubsequence.
特点：返跑一回，ans还是继续和left轮的ans作比较；求的所有情况的最大值嘛。



---

**98. [Maximum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Subarray.java)**      Level: Easy      Tags: [Array, DFS, DP, Divide and Conquer, PreSum, Sequence DP, Subarray]
      
time: O(n)
space: O(n), O(1) rolling array

给一串数组, unsorted, can have negative/positive num. 找数组中间 subarray 数字之和的最大值

#### Sequence DP
- dp[i]: 前i个element,包括 last element (i-1), 可能组成的 subarray 的最大sum.
- init: dp = int[n + 1], dp[0]: first 0 items, does not have any sum
- 因为continous sequence, 所以不满足条件的时候, 会断. That is: need to take curr num, regardless => can drop prev max in dp[i]
- track overall max 
- init dp[0] = 0; max = MIN_VALUE 因为有负数
- Time, space O(n)
- Rolling array, space O(1)

#### Divide and Conquer, DFS
- 找一个mid piont, 考虑3种情况: 只要左边, 只要右边, cross-mid
- left/rigth 的case, 直接 dfs
- corss-mid case: continuous sum max from left + continous sum max from right + mid
- continuous sum max from one direction:


---

**99. [Median.java](https://github.com/awangdev/LintCode/blob/master/Java/Median.java)**      Level: Easy      Tags: [Array, Quick Select, Quick Sort]
      

给一串无序数组, 找到median(sort之后 位置在中间的数字).

#### Quick Select
- 跟`kth largest element in an Array`的 template一样.
- 与quickSort不同在于, 每次只要在一半list里面recurring, 所以把O(logn)的时间复杂度降到O(n)
- quickSelect 可以找到 kth 最小的元素
- 利用这个原理, 找这个kth最小值, 然后如果 == target index, 就找到了我们的median
- quick select 的template要熟悉一下, 一下子可能想得到, 但写不出来
- 主要步骤: partition, dfs, only recur on one part of the array 




---

**100. [Merge Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Two Pointers]
      

给两个排好序的数组, merge. 其中一个数组nums1有多余的位置

#### Basics
- A够长，那么可以从A的尾部开始加新元素。     
- 注意，从尾部，是大数字优先排末尾的.  



---

**101. [Middle of Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Middle%20of%20Linked%20List.java)**      Level: Easy      Tags: [Linked List]
      

找Linked List的中间node

- 快慢指针
- 不在乎slow是不是到底，因为fast肯定先到。
- 确保fast, fast.next不是Null就好



---

**102. [Singleton.java](https://github.com/awangdev/LintCode/blob/master/Java/Singleton.java)**      Level: Easy      Tags: [Design]
      

让一个class 是 singleton



---

**103. [Remove Linked List Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Linked%20List%20Elements.java)**      Level: Easy      Tags: [Linked List]
      

从linked list 里面去掉所有的 target

#### Basics
- 如果match: node.next = head.next;
- 如果不match, node 和 head 一起移动



---

**104. [Fibonacci.java](https://github.com/awangdev/LintCode/blob/master/Java/Fibonacci.java)**      Level: Easy      Tags: [DP, Math, Memoization]
      

#### Memoization
- fib[n] = fibonacci(n - 1) + fibonacci(n - 2);

#### DP array.
- 滚动数组, 简化DP

#### recursively calculate
- recursively calculate fib(n - 1) + fib(n - 2). 公式没问题, 但是时间太长, timeout.




---

**105. [Palindrome Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Linked%20List.java)**      Level: Easy      Tags: [Linked List, Two Pointers]
      

#### Reverse Linked List
- Palindrome概念很简单, 但是要在Linkde List random access坐标, 是很难得: 所以需要把一半 ListNode 翻转
- reverse linked list: 遍历接开头
- 用快慢指正找到mid point
- Time O(n), 而且不需要用额外的空间(只是调换半个list的内部顺序), 所以空间O(1)

#### Previous Note
- Palindrome都是要两边回溯相等
- linkedlist不能reverse iterating， 那么就reverse the list, 从中间开花作比较。



---

**106. [Reverse Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Linked%20List.java)**      Level: Easy      Tags: [Linked List]
      

#### Reverse List
- Linked List的基本操作: 每次insert在开头
- 用head来循环所有node
- 不需要额外空间
- Time O(n), Space O(1)



---

**107. [Palindrome Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Permutation.java)**      Level: Easy      Tags: [Hash Table]
      

给String, 看permutation是否能是palindrome

#### Hash, or ASCII array
- count occurrance
- 只可以接受一个odd # appearance.
- 考虑所有 256 ASCII code, 如果还要拓展, 就用HashMap<Character, Integer>
- 注意, 不能assum lower case letter. 应该至少是所有ASCII code



---

**108. [Valid Palindrome.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Palindrome.java)**      Level: Easy      Tags: [String, Two Pointers]
      

验证string是不是 palindrome. 只考虑 alphanumeric, 其他字符可以忽略

#### Check Palindrome
- 前后两个指针, 往中间移动, 查看是否字母重合

#### 过滤 alphanumeric
- 可以用 ASCII code 来手动过滤, 只要 '0' ~ '9', 'a' ~ 'z', 'A' - 'Z' 之间的
- 也可以用 regular expression: match 所有这些字母, 是 [a-zA-Z0-9]
- 那凡是不是这些字母的 match, 就是取反: "[^a-zA-Z0-9]". 测试: https://regex101.com/



---

**109. [Implement Stack using Queues.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack%20using%20Queues.java)**      Level: Easy      Tags: [Design, Stack]
      

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

**110. [Implement Stack.java](https://github.com/awangdev/LintCode/blob/master/Java/Implement%20Stack.java)**      Level: Easy      Tags: [Stack]
      

随便用一个data structure, implement stack.

#### Stack, 先入, 后出
- ArrayList: return/remove ArrayList的末尾项。
- 2 Queues



---

**111. [Invert Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Invert%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

#### DFS
- 简单处理swap
- recursively swap children

#### BFS
- BFS with Queue
- 每次process一个node, swap children; 然后把child加进queue里面
- 直到queue process完



---

**112. [Maximum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      

给一个binary tree, 找最深depth

#### DFS
- 这里要走过所有的node, 所以dfs非常合适
- Divide and conquer. 
- 维持一个最大值: Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
- 注意check root == null

#### Note
- BFS is doable as well, but a bit more code to write: tracks largest level we reach



---

**113. [Minimum Depth of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Depth%20of%20Binary%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

#### DFS
- Divide and Conquery一个最小值. 
- 注意处理Leaf的null: null leaf 出现的时候, 就忽略这个leaf, 直接return算有leaf
- 另一种count的方法: 用Integer.MAX_VALUE代替 null leaf，这样可以避免错误counting. (不能直接recursive)
- 这个无论如何都要走所有node, 所以dfs应该比较适合.

#### BFS
- Shortest path; minimum depth: 想到BFS, check level by level, BFS更能确保更快找到结果
- depth definition: reach to a leaf node, where node.left == null && node.right == null
- BFS using queue, track level.



---

**114. [Symmetric Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Symmetric%20Tree.java)**      Level: Easy      Tags: [BFS, DFS, Tree]
      

检查tree是否symmetric

注意Symmetric Binary Tree的例子和定义: 是镜面一样的对称. 并不是说左右两个sub-tree相等。

#### DFS
- Recursively check symmetrically相对应的Node.  
- 每个node的children都和镜面另外一边相对的node的children刚好成镜面反射位置。

#### Stack
- stack1: 左手边sub-tree先加left, 再加right child; 
- stack2: 右手边sub-tree先加right child, 再加left child。   
- process时，若symmetric，所有stack里面出来的node会一一对应。



---

**115. [Tweaked Identical Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Tweaked%20Identical%20Binary%20Tree.java)**      Level: Easy      Tags: [DFS, Tree]
      

检查binary tree是否 identical. 

特点: subtree如果是有旋转的, 只要tree node value相等, 就可以算是identical

#### DFS
- 在DFS的基础上, 比对左左,左右,右左,右右



---

**116. [Merge Two Binary Trees.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Two%20Binary%20Trees.java)**      Level: Easy      Tags: [DFS, Tree]
      

#### DFS
- 基础binary tree traversal. 注意对null child的判断



---

**117. [Subtree.java](https://github.com/awangdev/LintCode/blob/master/Java/Subtree.java)**      Level: Easy      Tags: [DFS, Tree]
      

给一个binary tree s, 和一个binary tree t, 检查t是不是s的subtree.

#### DFS
- 跟 identical binary tree的写法很像
- 只有 current s.val = t.val 的时候才需要compare same tree.
- 其他情况, 继续recursively isSubtree
- 注意：即使找到T1 == T2, 但很可能只是数字相同（这里不是binary search tree!!）, 而children不同
- 所以同时要继续recursively isSubtree(T1.left, T2) ...etc.



---

**118. [Lowest Common Ancestor II.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor%20II.java)**      Level: Easy      Tags: [Hash Table, Tree]
      

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

**119. [Hash Function.java](https://github.com/awangdev/LintCode/blob/master/Java/Hash%20Function.java)**      Level: Easy      Tags: [Hash Table]
      

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

**120. [Merge Two Sorted Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Two%20Sorted%20Lists.java)**      Level: Easy      Tags: [Linked List]
      

如题

#### Basics
- 小的放前。每次比head大小 
- while过后，把没完的list一口气接上。   
- 一开始建一个node用来跑路, 每次都存node.next = xxx。存一个dummy。用来return dummy.next.



---

**121. [Missing Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Missing%20Number.java)**      Level: Easy      Tags: [Array, Bit Manipulation, Math]
      

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

**122. [Remove Duplicates from Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicates%20from%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Two Pointers]
      

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

**123. [Remove Duplicates from Sorted List.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicates%20from%20Sorted%20List.java)**      Level: Easy      Tags: [Linked List]
      

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

**124. [Longest Word in Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Word%20in%20Dictionary.java)**      Level: Easy      Tags: [Hash Table, Trie]
      

给串word[], 找最长的Word, 满足条件: 这个Word可以从 word[] 里面一个字母一个字母被build出来.

如果多种答案, respect smallest lexicographical order.

#### Sort, HashSet
- 先排序, 排序以后才能逐个看是否partial string已经存在
- 用 set.contains(substring(0, n - 1)) 来查看上一步的 substring 是否存在
- 如果找到, 因为已经按照字母表排序, 找到的这个肯定是这个长度里面最符合的解答.
- 然后brutally找下一个更大的.
- Sort O(n log n), O(n) set space

#### Trie
- 可以先sort words Array: 1. 长 string 排在前; 2. 相等length, 按照dictionary order 排序
- 全部放入Trie. Trie.insert()
- 针对sorted words array, 从最长的开始找 Trie.startWith.
- 一旦找到, 就是符合题意的, 直接return.
- 注意: startWith 必须每一个node都是 isEnd, 才满足'逐个字母拼出' 的条件.
- Time: build Trie O(mn) + sort:O(nlogn) => O(nlogn)
- Space: O(mn)

#### 
- 按大小排序 -> 从最大的开始做contains()的比较 -> 结果再按照字母表顺序(lexicographically) sort一下.
- 但是Collections.sort()了两次, 而且再list.contains(), 比较慢




---

**125. [Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum.java)**      Level: Easy      Tags: [DFS, Tree]
      

给一个inputSum, 然后dfs, 找到是否有一条path, 得出的path sum 跟 inputSum 一样.

#### DFS
- 确定好结尾的条件: is leaf && val == sum
- 每一层减掉node.val, 然后dfs.
- 写一写: root == null => false 对parent nodes的影响. 这里发现没影响, 所以可以简化成用1个functionDFS.




---

**126. [Path Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20II.java)**      Level: Easy      Tags: [Backtracking, DFS, Tree]
      

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

**127. [Path Sum III.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20III.java)**      Level: Easy      Tags: [DFS, Double Recursive, Tree]
      

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
- 利用这个function自己, 做不包括root的recursive computation



---

**128. [Rotate String.java](https://github.com/awangdev/LintCode/blob/master/Java/Rotate%20String.java)**      Level: Easy      Tags: [String]
      

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

**129. [Longest Common Prefix.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Prefix.java)**      Level: Easy      Tags: [String]
      

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

**130. [Reverse Words in a String III.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Words%20in%20a%20String%20III.java)**      Level: Easy      Tags: [String]
      

给一个String, 里面的Word被single space split开来, 目的是reverse里面所有的Word, 但preserve Word 和 space order.

#### Reverse function
- Reverse Words in a String II 的降级版, 去掉第一个overall reverse就好了



---

**131. [Merge Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/Merge%20Sorted%20Array%20II.java)**      Level: Easy      Tags: [Array]
      

如题, merge two sorted array into 新的 sorted array

- 长度已经固定. Basic Implementation
- 如果一个array足够大, merge into this array, 那么就是从末尾merge.



---

**132. [Nth to Last Node in List.java](https://github.com/awangdev/LintCode/blob/master/Java/Nth%20to%20Last%20Node%20in%20List.java)**      Level: Easy      Tags: [Linked List]
      

#### Linked List
- 先找到nth node
- 然后head开始跑
- node 到底，而head ~ node刚好是 n 距离。所以head就是要找的last nth



---

**133. [Two Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Sum.java)**      Level: Easy      Tags: [Array, Hash Table]
      

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

**134. [Max Area of Island.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Area%20of%20Island.java)**      Level: Easy      Tags: [Array, DFS]
      

#### DFS
- 虽然Easy, 但用到DFS最基本的想法.
- 1. dive deep
- 2. mark VISITED
- 3. sum it up
- Time: worst O(mn), traverse all possible nodes

- 更要注意, 要从符合条件value==1的地方开始dfs.
- 对于什么island都没有的情况，area应该位0， 而不是Integer.MIN_VALUE, 问清楚考官那小伙, 别写顺手。



---

**135. [Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum.java)**      Level: Easy      Tags: [Array, Hash Table, PreSum, Subarray]
      
time: O(n)
space: O(n)

给一串数字, 找其中的一个subarray的 [start, end] index, 条件: subarary sum == 0.

#### Hash Table
- `subarray sum equals k` 的简单版: k = 0
- 求preSum, 然后不断check `map.containsKey(preSum - k)`. 
- 如果 `priorSum = preSum - k == 0`, 说明 [priorSum.index + 1, curr index] 就是我们要找的这一段

#### Previous notes, same preSum + map solution
- 分析出，如果sum[0~a]=x, 然后sum[0~b]=x, 说明sum[a+1 ~ b] == 0
- 用hashMap存每个sum[0~i]的值和index i. 如果有重复，就找到了一组sum为0的数组.



---

**136. [Range Sum Query - Immutable.java](https://github.com/awangdev/LintCode/blob/master/Java/Range%20Sum%20Query%20-%20Immutable.java)**      Level: Easy      Tags: [DP, PreSum]
      

给一串数字, 求sumRange.

#### PreSum
- 就是pre sum 的definition
- preSum也是dp[]一种最简易的形式把.
- dp[i], preSum[i]: 前(i-1)个元素的和.



---

**137. [Longest Words.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Words.java)**      Level: Easy      Tags: [Hash Table, String]
      

给一串String, 找到最长的长度, 把最长的String全都return

#### Hash Table
- <Integer,List<String>>
- 存最长值, 最后map.get(max) 



---

**138. [Unique Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Characters.java)**      Level: Easy      Tags: [Array, String]
      

determine if characters are unique in string

#### HashSet
- space O(n), time O(n)

#### char[]
- space O(n), time O(nlogn)

#### no additional data structure
- double for loop:  O(n^2)




---

**139. [Binary Gap.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Gap.java)**      Level: Easy      Tags: [Bit Manipulation]
      
time: O(n), n = # of bits
space: O(1)

#### Bit Manipulation
- 理解Binary Gap的描述
- 简单的 `>>`, `&1`, track start and end point 就好了



---

**140. [Maximize Distance to Closest Person.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximize%20Distance%20to%20Closest%20Person.java)**      Level: Easy      Tags: [Array]
      
time: O(n)
space: O(1)

给一排座位, 一个人去坐: 找离两边的人都最远的地方(中间点), return 跟旁边人的最大distance

是Exam Room 的同种概念, 简单化题目: 这里只考虑一个人就好了

#### Basic Implementation, track start/end
- start/end point, 然后比较大小记录dist
- 注意1: 如果第一个座位没有人, 特殊处理, dist = [0 ~ end]
- 注意2: 如果最后一个座位没有人, 特殊处理: dist = [n - 1 - start];
- 其余: `dist = Math.max(dist, (end - start) / 2)`
- 相关题目: 几乎同样概念 `Binary Gap`, 升级复杂版`Exam Room`




---

**141. [Paint Fence.java](https://github.com/awangdev/LintCode/blob/master/Java/Paint%20Fence.java)**      Level: Easy      Tags: [DP, Sequence DP]
      
time: O(n)
space: O(n)

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

**142. [Best Time to Buy and Sell Stock.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock.java)**      Level: Easy      Tags: [Array, DP, Sequence DP]
      

给个array of stock prices, 限制能交易(买/买)一轮, 问如何找到最大profit.

#### 理解意思是关键
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
- 其中很多都是没必要的计算：[7, 1, 5, 3, 6, 4]。 if we know buyin with 1 is cheapest, we don't need to buyin at 5, 3, 6, 4 later on; we'll only sell on higher prices.



---

**143. [Best Time to Buy and Sell Stock II.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Status DP]
      
time: O(n)
space: O(1) greedy, O(n) dp

和Stock I 的区别：可以买卖多次，求总和的最大盈利.

#### 几种其他不同的思路:
- Greedy, 每次有相邻的diff符合profit条件, 就卖了, 最后把所有的diff加在一起. 计算delta, 其实简单粗暴, 也还不错.
- 如下, 从低谷找peek, sell.
- DP. (old dp solution BuyOn[], SellOn[])
- DFS计算所有(timeout).Improvement on DFS -> DP -> calculate sellOn[i] and buyOn[i], and then return buyOn[i]. 有点难想, 但是代码简单, 也是O(n)

#### Greedy
- 画图, 因为可以无限买卖, 所以只要有上升, 就有profit
- 所有卖掉的, 平移加起来, 其实就是overall best profit
- O(n)

#### 找涨幅最大的区间，买卖：
- 找到低谷，买进:peek = start + 1 时候，就是每次往前走一步;若没有上涨趋势，继续往低谷前进。
- 涨到峰顶，卖出:一旦有上涨趋势，进一个while loop，涨到底, 再加个profit.
- profit += prices[peek - 1] - prices[start]; 挺特别的。
- 当没有上涨趋势时候，peek-1也就是start, 所以这里刚好profit += 0.

#### DP, sequence dp + status
- 想知道前i天的最大profit, 那么用sequence DP: 
- dp[i]: represents 前i天的最大profit
- 当天的是否能卖, 取决于昨天是否买进, 也就是 `昨天买了或者卖了的状态`: 加状态, dp[i][0], dp[i][1]
- `买`的状态 `dp[i][0]` = 1. 今天买入, 昨天卖掉的dp[i-1][1]结果 - price[i]; 2. 今天不买, 跟昨天买的status dp[i-1][0] 结果 比较.
- `卖`的状态 `dp[i][1]` = 1. 今天卖出, 昨天买进的dp[i-1][0]结果 + price[i]; 2. 今天不卖, 跟昨天卖的status dp[i-1][1] 结果 比较.
- 注意init: 
- dp[0][0] = dp[0][1] = 0; // 0 days, 
- dp[1][0] = 0; // sell on 1st day, haven't bought, so just 0 profit.
- dp[1][0] = -prices[0]; // buy on 1st day, with cost of prices[0]

##### Rolling Array
- [i] 和 [i - 1] 相关联, roll




---

**144. [Minimum Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Subarray.java)**      Level: Easy      Tags: [Array, DP, Greedy, Sequence DP, Subarray]
      
time: O(m)
space: O(1)

给一串数组, unsorted, can have negative/positive num. 找数组中间 subarray 数字之和的最小值

#### DP
- 看到 min value, 至少考虑dp:
- Consider last num: min sum will be (preMinSum + curr, or curr)
- Use preMinSum to cache previouly calcualted min sum, also compare with +curr.
- Have a global min to track: because the preMinSum can be dis-continuous. 
- 也可以写成 dp[i] 但是没什么必要



---

**145. [Subtree of Another Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Subtree%20of%20Another%20Tree.java)**      Level: Easy      Tags: [DFS, Divide and Conquer, Tree]
      

#### Tree 
- Traverse tree: left, right
- Concept of partial compare vs. whole compare



---

**146. [Two Sum IV - Input is a BST.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Sum%20IV%20-%20Input%20is%20a%20BST.java)**      Level: Easy      Tags: [Tree]
      

HashSet to store visited items. Same old 2 sum trick.



---

**147. [Read N Characters Given Read4.java](https://github.com/awangdev/LintCode/blob/master/Java/Read%20N%20Characters%20Given%20Read4.java)**      Level: Easy      Tags: [String]
      

Read4 题目. 理解题目: 是有个input object buff, 会被populated with data.

#### String in char[] format
- 理解题目: 其实就是track `可以read多少bytes by read4() response`
- 另外一个有用的function `System.arraycopy(src, srcIndex, dest, destIndex, length)`



---

