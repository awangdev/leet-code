 
 
 
## String (19)
**0. [Judge Route Circle.java](https://github.com/awangdev/LintCode/blob/master/Java/Judge%20Route%20Circle.java)**      Level: Easy
      

简单的character checking. 各个方向, 加加减减.



---
**1. [First Unique Character in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/First%20Unique%20Character%20in%20a%20String.java)**      Level: Easy
      

方法1: 按照题意, 找到第一个 first index == last index的字母.

方法2: 用hashmap存字母的index, 有些重复字母的index就会是个list. 找到单一index, 结合成list, sort, return list.get(0)



---
**2. [Reverse Vowels of a String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Vowels%20of%20a%20String.java)**      Level: Easy
      

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
**3. [Decode Ways.java](https://github.com/awangdev/LintCode/blob/master/Java/Decode%20Ways.java)**      Level: Review
      

确定末尾的2种状态: single letter or combos. 然后计算出单个letter的情况, 和双数的情况
note: calculate number from characters, need to - '0' to get the correct integer mapping.
注意: check value != '0', 因为'0' 不在条件之中(A-Z)




---
**4. [Scramble String.java](https://github.com/awangdev/LintCode/blob/master/Java/Scramble%20String.java)**      Level: Hard
      

- 给两个string S, T. 检验他们是不是scramble string.
- scramble string 定义: string可以被分拆成binary tree的形式, 也就是切割成substring;
- 旋转了不是leaf的node之后, 形成新的substring, 这就是原来string的 scramble.


#### Range DP 区间型
- 降维打击, 分割, 按照长度来dp.
- dp[i][j][k]: 数组S从index i 开始, T从index j 开始, 长度为k的子串, 是否为scramble string

##### Break down
- 一切两半以后, 看两种情况: , 或者不rotate这两半. 对于这些substring, 各自验证他们是否scramble.
- 不rotate分割的两半: S[part1] 对应  T[part1] && S[part2] 对应  T[part2]. 
- rotate分割的两半: S[part1] 对应  T[part2] && S[part2] 对应  T[part1]. 

##### Initialization
- len == 1的时候, 其实无法旋转, 也就是看S,T的相对应的index是否字符相等.
- initialization非常非常重要. 很神奇, 这个initailization 打好了DP的基础, 后面一蹴而就, 用数学表达式就算出了结果.
- input s1, s2 在整个题目的主要内容里面, 几乎没有用到, 只是用在initialization时候.
- More details, 看解答



---
**5. [Interleaving String.java](https://github.com/awangdev/LintCode/blob/master/Java/Interleaving%20String.java)**      Level: Hard
      

双序列DP, 从最后点考虑.
拆分问题的末尾, 考虑和s1, s2 subsequence之间的关联.

求存在性, boolean




---
**6. [Letter Combinations of a Phone Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Letter%20Combinations%20of%20a%20Phone%20Number.java)**      Level: Medium
      

方法1: Iterative with BFS using queue.

方法2: Recursively adding chars per digit



---
**7. [Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Edit%20Distance.java)**      Level: Hard
      

两个字符串, A要变成B, 可以 insert/delete/replace, 找最小变化operation count

#### Double Sequence
- 考虑两个字符串变换的最后点: 需要insert/delete/replace? 分析每种情况, 然后列出表达式.
- 先calculate最坏的情况, 3种operation count + 1; 然后在比较match的情况.
- 注意, 在i或者j为0的时候, 变成另外一个数字的steps只能是全变.
- 第一步, 空间时间都是O(MN)
- 滚动数组优化, 空间O(N)

#### Search



---
**8. [Distinct Subsequences.java](https://github.com/awangdev/LintCode/blob/master/Java/Distinct%20Subsequences.java)**      Level: Hard
      

Double Sequence DP:
0. DP size (n+1): 找前nth的结果, 那么dp array就需要开n+1, 因为结尾要return dp[n][m]
1. 在for loop 里面initialize dp[0][j] dp[i][0]
2. Rolling array 优化成O(N): 如果dp[i][j]在for loop里面, 就很好替换 curr/prev



---
**9. [Regular Expression Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Regular%20Expression%20Matching.java)**      Level: Review
      



---
**10. [Wildcard Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java)**      Level: Hard
      

Double sequence DP. 与regular expression 很像.

注意1: 分析字符 ?, * 所代表的真正意义, 然后写出表达式.
注意2: 搞清楚initialization 的时候 dp[i][0] 应该always false.当p为empty string, 无论如何都match不了 (除非s="" as well)
    同时 dp[0][j]不一定是false. 比如s="",p="*" 就是一个matching.



---
**11. [Add Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Binary.java)**      Level: Easy
      

方法一:土办法没技术，把binary换成数字，加起来，再换成binary。如果input很大，那么很可能int,long都hold不住。不保险。

方法二:一般方法，string化为charArray,然后逐位加起，最后记得处理多余的一个carry on
注意: 需要从末尾加起来, 所以要用一个diff来帮助  shortArray[i-diff] 指向 shortArray的相对应index.



---
**12. [Longest Substring Without Repeating Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20Without%20Repeating%20Characters.java)**      Level: Medium
      

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
**13. [Minimum Window Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Window%20Substring.java)**      Level: Hard
      

基本思想: 用个char[]存string的frequency. 然后2pointer, end走到底, 不断validate.
符合的就process as result candidate.

HashMap的做法比char[]写起来要复杂一点, 但是更generic



---
**14. [Longest Substring with At Most K Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20with%20At%20Most%20K%20Distinct%20Characters.java)**      Level: Medium
      

大清洗 O(nk)   
map.size一旦>k，要把longest string最开头（marked by pointer:start）的那个char抹掉    
一旦某一个char要被清除，所以在这个char 的1st and last appearance之间的char都要被清洗from map




---
**15. [Palindrome Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Pairs.java)**      Level: Hard
      

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
**16. [Change to Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/Change%20to%20Anagram.java)**      Level: Easy
      

HackerRank里面的random 题目: 给一个string, 一切成两半, 看两半要变化多少个字符, 能变成anagram.

- 切两半成两个String A,B. 分别在int count[26]里面++, --.
- 记录 26个小写字母的频率. 如果全部抵消, 就是anagram.
- 注意: 最后count出来要除以2：字母不同，会在不同的字母位上加减count,那么就是刚好重复计算了一遍。所以除以二

- Note: HackerRank里要注意自己写: Scanner, import java.util, non-static method ...etc.



---
**17. [Compare Version Numbers.java](https://github.com/awangdev/LintCode/blob/master/Java/Compare%20Version%20Numbers.java)**      Level: Medium
      

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
**18. [Compare Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Compare%20Strings.java)**      Level: Easy
      

看StringA是不是包括所有 StringB的字符.

#### Basic Implementation
- 比较一下大小, null.
- 然后用int[]来count chars from A, count[x]++. 再对照chars in B, count[x]--
- 如果 count[c] < 0, 就 false.
- O(n)



---
