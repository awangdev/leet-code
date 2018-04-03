 
 
 
## Array (44)
**0. [Plus One.java](https://github.com/awangdev/LintCode/blob/master/Java/Plus%20One.java)**      Level: Easy
      

简单的实现, 加1, 进位. 唯一取巧的地方, 最后如果要多一位, 一定是10000...这个模式, 可以走捷径, 直接来个+1size的array, 然后第一位=1.
注意,转换成long也不合理,用太多memory.


---
**1. [Wiggle Sort.java](https://github.com/awangdev/LintCode/blob/master/Java/Wiggle%20Sort.java)**      Level: Medium
      

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
**2. [2 Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum.java)**      Level: Easy
      

tutorial:https://www.youtube.com/watch?v=P8zBxoVY1oI&feature=youtu.be

解法1：相对暴力简洁, HashMap<value, index>，找到一个value, 存一个; 若在HashMap里面 match 到结果, 就return HashMap里存的index. O(n) space && time.

解法2：Sort array, two pointer 前后++,--搜索。Sort 用时O(nlogn).     
1. 第一步 two pointer 找 value.       
2. 注意，要利用额外的空间保留original array， 用来时候找index. (此处不能用HashMap，因为以value 为key，但value可能重复)      
O(n) space, O(nlogn) time.    




---
**3. [2 Sum II - Input array is sorted.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum%20II%20-%20Input%20array%20is%20sorted.java)**      Level: Medium
      

升序array, 找2SUM.

#### 方法1:
- 排序好的array. Two pointer移动start和end，核查sum.
- 注意sum用long.
- O(n) time

#### 方法2: Binary Search, 因为已经排好序了啊
- 定住一个valueA, 然后在剩下的里面 binary serach 找 (target - valueB)
- O(nLogN), 就不写了



---
**4. [2 Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum%20II.java)**      Level: Medium
      

与 2sum II - input array is sorted类似. 都是sort array, 然后two pointer.

LintCode的题. 注意找的是greater/bigger than target。

由于给定条件允许O(nLogn):   
   sort
   two pointer

while里面two pointer移动。每次如果num[left]+num[right] > target，那么其中所有num[left++]的加上num[right]都>target.   
也就是,num[right]不动，计算加入挪动left能有多少组，那就是: right-left这么多。 全部加到count上去。     
然后right--.换个right去和前面的left部分作比较。



---
**5. [Maximum Product Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Product%20Subarray.java)**      Level: Medium
      

从一组数列(正负都有)里面找一串连续的子序列, 而达到乘积product最大值.

#### DP
- 求最值, 想到DP. Time/Space O (n)
- 两个特别处: 
- 1. 正负数情况, 需要用两个DP array. 
- 2. continuous prodct 这个条件决定了在Math.min, Math.max的时候, 
- 是跟nums[x]当下值比较的, 如果当下值更适合, 会舍去之前的continous product, 然后重新开始.
- 这也就注定了需要一个global variable 来hold result.

#### Space optimization, rolling array
- maxProduct && minProduct 里面的 index i, 都只能 i - 1相关, 所以可以省去redundant operatoins
- Time: O(n), space: O(1)



---
**6. [3 Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/3%20Sum%20Closest.java)**      Level: Medium
      

3Sum 的一种简单形式, 并且都没有找index, value, 而只是找个sum罢了.

double for loop。 2Sum只能用土办法 left/right 2 pointers。 O(n^2)

注意：check closest时候用long, 以免int不够用



---
**7. [Triangle Count.java](https://github.com/awangdev/LintCode/blob/master/Java/Triangle%20Count.java)**      Level: Medium
      

其实也就是3sum的变形, 或者而说2sum的变形. 主要用2 pointers来做.
注意, 在选index时候每次定好一个 [0 ~ i], 在这里面找点start, end, 然后i 来组成triangle.
Note巧妙点:
在此之中, 如果一种start/end/i 符合, 那么从这个[start~end]中, 大于start的都可以, 所以我们count+= end-start.
反而言之, <end的其他index, 就不一定能符合nums[start] + nums[end] > nums[i]



---
**8. [3 Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/3%20Sum.java)**      Level: Medium
      

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
**9. [Longest Continuous Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Continuous%20Increasing%20Subsequence.java)**      Level: Easy
      

简单的DP:dp[i]存在i点时连续上升#. dp[i]时可能为0, 一旦断开.

方法1: dp[i], maintain max
方法2: 用一个数存current count,  maintain max. 也是DP, 稍微简化.



---
**10. [Unique Paths II.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Paths%20II.java)**      Level: Medium
      

跟unique path的grid一样, 目标走到右下角, 但是grid里面可能有obstacle, 不能跨越. 求unique path 的count.

#### 坐标DP
- dp[i][j]: # of paths to reach grid[i][j]
- dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
- 考虑最终结尾需要的状态:如何组成,写出公式.
- 公式中注意处理能跳掉的block, marked as 1. '到不了', 即为 0 path in dp[i][j].



---
**11. [Best Time to Buy and Sell Stock I.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20I.java)**      Level: Easy
      

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
**12. [Best Time to Buy and Sell Stock II.java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.java)**      Level: Easy
      

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
**13. [Best Time to Buy and Sell Stock III .java](https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III%20.java)**      Level: Hard
      

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
**14. [3 Sum Smaller.java](https://github.com/awangdev/LintCode/blob/master/Java/3%20Sum%20Smaller.java)**      Level: Medium
      

一般的O(n3)肯定不行。在此基础上优化。
发现j,k满足条件时候，(k - j)就是所有 sum <target的情况了。
而一旦>target, 又因为j不能后退，只能k--，那么问题就被锁定了. 这样可以做到O(n2)



---
**15. [Array Partition I.java](https://github.com/awangdev/LintCode/blob/master/Java/Array%20Partition%20I.java)**      Level: Easy
      

从结果出发, 只需要找到加法的结果，而不强调具体配对。
找到排列取单数位的规律，再考虑负数和正数的相同规律，即可找到排列求解的方法。




---
**16. [1-bit and 2-bit Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/1-bit%20and%202-bit%20Characters.java)**      Level: Easy
      

方法1:
Greedy.
从第一个bit开始数: 如果遇到1, 一定是跳两位;如果遇到0, 一定是跳一位.
loop to end, and see if index reaches the end.

方法2:
用DP硬做了一下: 
1. 如果i位是0, 那么前面dp[i-1]或者dp[i-2] true就够了.
2. 如果i位是1, 那么i-1位必须是1才满足规则, 并且 dp[i-2]需要true.



---
**17. [Non-decreasing Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Non-decreasing%20Array.java)**      Level: Easy
      

比较升序的时候, 必须要估计到 i-1, i, i+1三个数位.
写出来i-1, i+1之间的关系, 然后做合理的fix.

需要真的fix数组, 因为loop through做比较时会用到fix后的数字.



---
**18. [Max Consecutive Ones.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Consecutive%20Ones.java)**      Level: Easy
      

Basic. Math.max track结果。
记得在有对外操作的loop后，一定要把result object清理干净。



---
**19. [Find All Numbers Disappeared in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20All%20Numbers%20Disappeared%20in%20an%20Array.java)**      Level: Easy
      

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
**20. [Maximum Average Subarray I.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20I.java)**      Level: Easy
      

简单的求sum, 同时求max, 结尾求余数就好.


---
**21. [Largest Number At Least Twice of Others.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Number%20At%20Least%20Twice%20of%20Others.java)**      Level: Easy
      

找最大值, 和第二大的值, 看是否符合题意, 就行了.
分析题意, 最简单方法, 可以loop 两遍: 找最值; 作比较.
但其实, 举个反例: 有一个不满足, 就够反对这个'at least twice of alllll others'.



---
**22. [Toeplitz Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Toeplitz%20Matrix.java)**      Level: Easy
      

似乎没什么算法特点, 就是array基本运算, 然后分割成一个helper function去做重复计算, 剪短代码.
注意check MxN 的分界线.



---
**23. [Find Peak Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element.java)**      Level: Medium
      

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
**24. [Majority Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Element.java)**      Level: Easy
      

方法1: Vote 计数, vote++, vote--到最后剩下的就是winner. Time O(n), Space O(1)
Majority Number是指超半数. 超半数的数字, 最后都会至少有vote>=1: match current majority number，vote++；if not, vote--. 
注意：assume valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。

方法2: HashMap count occurance. Time, Space: O(n)

方法3: Bit manipulation. 还没有做.

Related Problems:
Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。

Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。



---
**25. [Construct Binary Tree from Inorder and Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Preorder%20Traversal.java)**      Level: Medium
      

和Construct from Inorder && Postorder 想法一样。

写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。



---
**26. [Minimum Size Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Size%20Subarray%20Sum.java)**      Level: Medium
      

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
**27. [Find Minimum in Rotated Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Minimum%20in%20Rotated%20Sorted%20Array.java)**      Level: Medium
      

画图, 最小值被rotate之后, 变成array中间的最低谷.
并且, 左边山坡的最小值, 大于右边山坡的最大值. 
以此来给binary search做判断.

O(nlogn)



---
**28. [Find Minimum in Rotated Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Minimum%20in%20Rotated%20Sorted%20Array%20II.java)**      Level: Hard
      

一个需要严谨思考的题目. 因为有duplicate, 会导致不断平移, 所以最终time complexity是O(n)
所以不如直接扫一遍, 给出答案.

但是还是写一个Binary Search的样子, 只不过worst结果是O(n)



---
**29. [Word Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Search.java)**      Level: Medium
      

Backtracking:
找到开头的字母, 然后recursively DFS 去把word串到底:
每到一个字母, 朝四个方向走, 之中一个true就可以.

Note:每次到一个字母，mark一下'#'. 4个path recurse回来后，mark it back.

Backtracking方法2:    
用一个boolean visited[][]





---
**30. [Trapping Rain Water.java](https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water.java)**      Level: Hard
      

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
**31. [Largest Rectangle in Histogram.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Rectangle%20in%20Histogram.java)**      Level: Hard
      

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
**32. [Find the Duplicate Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Duplicate%20Number.java)**      Level: Medium
      

- 注意不要思维定式: 以为mid是index
- 这里mid其实是binary search on value [1, n] 的一个value.
- 再次用到validate() function

Time: O(nLogN)



---
**33. [Game of Life.java](https://github.com/awangdev/LintCode/blob/master/Java/Game%20of%20Life.java)**      Level: Medium
      

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
**34. [Maximum Average Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20II.java)**      Level: Review
      

给int[] nums 和 window min size k. window size可以大于K. 找最大的连续数列average value.

- Binary Search的思想, 用在所要找的这个 average sum 上面. 大小是在[min, max]之中
- 找k的时候, 是>=k都可以, 巧用一个 min(preSum)的概念.
- 找k的时候, 画图, 可以看出来, 其实要的是 k window 里面的sum [x, i], 所以要用 sum[0, i] - sum[0, x]

需要仔细去读下面的notes.



---
**35. [Number of Airplane in the sky.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Airplane%20in%20the%20sky.java)**      Level: Medium
      

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
**36. [Unique Path.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Path.java)**      Level: Medium
      

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
**37. [Maximal Rectangle.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Rectangle.java)**      Level: Hard
      

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
**38. [Minimum Path Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Path%20Sum.java)**      Level: Medium
      

#### DP
- 往右下角走, 计算最短的 path sum. 典型的坐标型.
- 注意: init 第一行的时候, 要accumulate dp[0][j - 1] + grid[i][j], 而不是单纯assign grid[i][j]

##### rolling array
TODO




---
**39. [Coins in a Line II.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20II.java)**      Level: Medium
      

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
**40. [Coins in a Line III.java](https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20III.java)**      Level: Hard
      

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
**41. [Contains Duplicate.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate.java)**      Level: Easy
      

无序数组, 找是否有重复element, return true/false.

#### HashSet
- No brain: HashSet.
- Time O(n), Space O(n)

#### Sort, Binary Search
- Arrays.sort(x): Time O(nLogN), Space O(1)
- 排序后, 重复数会排在一起, 然后 binary search



---
**42. [Contains Duplicate II.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate%20II.java)**      Level: Easy
      

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
**43. [Jump Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Jump%20Game.java)**      Level: Medium
      

给出步数，看能不能jump to end.

#### DP
- DP[i]: 在i点记录，i点之前的步数是否可以走到i点？ True of false.
- 其实j in [0~i)中间只需要一个能到达i 就好了
- Function: DP[i] = DP[j] && (A[j] >= i - j), for all j in [0 ~ i)
- Return: DP[dp.length - 1];

#### Greedy
- Keep track of farest can go
- 一旦 farest >= nums.length - 1, 也就是到了头, 就可以停止, return true.
- 一旦 farest <= i, 也就是说, 在i点上, 已经走过了步数, 不能再往前跳, 于是 return false



---
