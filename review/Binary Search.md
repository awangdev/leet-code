 
 
 
## Binary Search (20)
**0. [Guess Number Higher or Lower.java](https://github.com/awangdev/LintCode/blob/master/Java/Guess%20Number%20Higher%20or%20Lower.java)**      Level: Easy
      

binary search 公式



---
**1. [2 Sum II - Input array is sorted.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum%20II%20-%20Input%20array%20is%20sorted.java)**      Level: Medium
      

升序array, 找2SUM.

#### 方法1:
- 排序好的array. Two pointer移动start和end，核查sum.
- 注意sum用long.
- O(n) time

#### 方法2: Binary Search, 因为已经排好序了啊
- 定住一个valueA, 然后在剩下的里面 binary serach 找 (target - valueB)
- O(nLogN), 就不写了



---
**2. [2 Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum%20II.java)**      Level: Medium
      

与 2sum II - input array is sorted类似. 都是sort array, 然后two pointer.

LintCode的题. 注意找的是greater/bigger than target。

由于给定条件允许O(nLogn):   
   sort
   two pointer

while里面two pointer移动。每次如果num[left]+num[right] > target，那么其中所有num[left++]的加上num[right]都>target.   
也就是,num[right]不动，计算加入挪动left能有多少组，那就是: right-left这么多。 全部加到count上去。     
然后right--.换个right去和前面的left部分作比较。



---
**3. [Longest Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Subsequence.java)**      Level: Medium
      

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
**4. [Copy Books.java](https://github.com/awangdev/LintCode/blob/master/Java/Copy%20Books.java)**      Level: Hard
      

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
**5. [Valid Perfect Square.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Perfect%20Square.java)**      Level: Review
      

Binary找sqrt. 基本 mid+1, mid-1 template.
注意: define index as long. 



---
**6. [Intersection of Two Arrays II.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Arrays%20II.java)**      Level: Easy
      

方法1:
用HashMap: 存一个nums1, 再拿nums2 check against map. 时间/空间:O(n)

方法2:
Binary search? 需要array sorted. 否则时间O(nlogn)不值得.
[没做完, 有错]



---
**7. [Find Peak Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element.java)**      Level: Medium
      

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
**8. [Pow(x,n).java](https://github.com/awangdev/LintCode/blob/master/Java/Pow(x,n).java)**      Level: Medium
      

傻做就O(n), 要更好就考虑O(logN).
减少重复计算, 一切两半.

注意:
- n/2的奇数偶数
- n的正负
- n == 0的情况, x == 0, x == 1 的情况.


---
**9. [Minimum Size Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Size%20Subarray%20Sum.java)**      Level: Medium
      

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
**10. [Kth Smallest Number in Sorted Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Smallest%20Number%20in%20Sorted%20Matrix.java)**      Level: Medium
      

方法1:
和Merge K sorted Array/ List 类似：使用PriorityQueue。

因为Array的element无法直接找到next,所以用一个class Node 存value, x,y positions.

方法2:
Binary Search
https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85182/my-solution-using-binary-search-in-c


变型: Kth Largest in N Arrays


---
**11. [Find Minimum in Rotated Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Minimum%20in%20Rotated%20Sorted%20Array.java)**      Level: Medium
      

画图, 最小值被rotate之后, 变成array中间的最低谷.
并且, 左边山坡的最小值, 大于右边山坡的最大值. 
以此来给binary search做判断.

O(nlogn)



---
**12. [Find Minimum in Rotated Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Minimum%20in%20Rotated%20Sorted%20Array%20II.java)**      Level: Hard
      

一个需要严谨思考的题目. 因为有duplicate, 会导致不断平移, 所以最终time complexity是O(n)
所以不如直接扫一遍, 给出答案.

但是还是写一个Binary Search的样子, 只不过worst结果是O(n)



---
**13. [Find Peak Element II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element%20II.java)**      Level: Hard
      

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
**14. [Sqrt(x).java](https://github.com/awangdev/LintCode/blob/master/Java/Sqrt(x).java)**      Level: Easy
      

#### s- qrt(int x)
- 理解题意, 从[0, x]找一个可以m*m=x的值.
- 注意, 如果找不到, 最后问考官该return一个什么值：按道理，因为return int, 会取整，那么return一个平方最close to x就可以.
- 注意 mid 用 long, 因为很可能超过最大int.

#### sqrt(double x)
- 二分float number, 应该用精度来定义结尾.
- 还是二分, 但是判断条件变成: while ( end - start > eps)
- eps = 1e-12,也就是精度到1e-12



---
**15. [First Bad Version.java](https://github.com/awangdev/LintCode/blob/master/Java/First%20Bad%20Version.java)**      Level: Easy
      

Binary Search

根据isBadVersion的性质，判断还如何end=mid or start=mid.     
isBadVersion 是有方向的嘛，一个点错了，后面全错。



---
**16. [Wood Cut.java](https://github.com/awangdev/LintCode/blob/master/Java/Wood%20Cut.java)**      Level: Medium
      

二分的思想: 判断的是一个 validate() function, 而不是简单的'=='

不需要sort! 为什么呢? 因为我们不是在given array上面二分, 我们是根据最大值在[0, max]上二分.

Overall time: O(nLogM), where M = largest wood length



---
**17. [Find the Duplicate Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20the%20Duplicate%20Number.java)**      Level: Medium
      

- 注意不要思维定式: 以为mid是index
- 这里mid其实是binary search on value [1, n] 的一个value.
- 再次用到validate() function

Time: O(nLogN)



---
**18. [Maximum Average Subarray II.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Average%20Subarray%20II.java)**      Level: Review
      

给int[] nums 和 window min size k. window size可以大于K. 找最大的连续数列average value.

- Binary Search的思想, 用在所要找的这个 average sum 上面. 大小是在[min, max]之中
- 找k的时候, 是>=k都可以, 巧用一个 min(preSum)的概念.
- 找k的时候, 画图, 可以看出来, 其实要的是 k window 里面的sum [x, i], 所以要用 sum[0, i] - sum[0, x]

需要仔细去读下面的notes.



---
**19. [Classical Binary Search.java](https://github.com/awangdev/LintCode/blob/master/Java/Classical%20Binary%20Search.java)**      Level: Easy
      

#### Binary Search Template
- while: start + 1 < end
- mid = start + (end - start) / 2;
- 根据mid作比较
- 末尾double check start, end.




---
