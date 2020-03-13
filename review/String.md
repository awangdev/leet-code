 
 
 
## String (78)
**0. [Restore IP Addresses.java](https://github.com/awangdev/LintCode/blob/master/Java/Restore%20IP%20Addresses.java)**      Level: Medium      Tags: [Backtracking, DFS, String]
      
给一串数字, 检查是否是valid IP, 如果合理, 给出所有valid 的IP组合方式.

#### Backtracking
- 递归的终点:list.zie() == 3, 解决最后一段
- 递归在一个index上面, pass s.toCharArray()
- validate string要注意leading '0'
- 注意: 递归的时候可以用一个start/level/index来跑路
- 但是尽量不要去改变Input source， 会变得非常confusing.
- note: code有点messy, 因为要考虑IP的valid情况
- 那个'remainValid', 其实是一个对于remain substring的判断优化, 不成立的就不dfs了



---

**1. [Reverse String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20String.java)**      Level: Easy      Tags: [String, Two Pointers]
      
Similar to Reverse Integer.
可以用StringBuffer, 也可以two pointer reverse head/tail



---

**2. [Binary Representation.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Representation.java)**      Level: Hard      Tags: [Bit Manipulation, String]
      
#### String
- 首先要分两半解决，断点是'.': str.split("\\.");
- Integer那一半好弄，whie loop里: num%2, num/2. 做一个 `parseInteger()` function
- Decimal那边复杂点. 做一个 `parseDecimal()` function:
- bit == 1的数学条件: 当下num * 2 >= 1。 更新: num = num * 2 - 1;
- bit == 0的数学条件: num * 2 < 1. 更新: num = num * 2

#### 注意
- num是 double, 小数在 `num = num * 2 - 1` 的公式下可能无限循环
- 因此check: num重复性，以及binary code < 32 bit.
- 所以题目也才有了32BIT的要求!



---

**3. [Palindromic Substrings.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindromic%20Substrings.java)**      Level: Medium      Tags: [DP, String]
      
根据题意, count # of palindromic substring. (不同index截取出来的substring算不同的情况)

#### isPalin[][]
- build boolean[][] to check isPalin[i][j] with DP concept
- check all candidates isPalin[][]
- O(n^2)

#### odd/even split check
https://leetcode.com/problems/palindromic-substrings/discuss/105689/Java-solution-8-lines-extendPalindrome



---

**4. [Count and Say.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20and%20Say.java)**      Level: Easy      Tags: [Basic Implementation, String]
      
介绍一种count数字的方法, 然后每一行读出上一行的结果, 一行一行推算. 问nth行是啥样?

#### Basic Implementation
- 主要是题意很难理解, 非常misleading, 等到看明白题目, 其实没有什么算法要求.
- Count duplicates and print



---

**5. [Letter Combinations of a Phone Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Letter%20Combinations%20of%20a%20Phone%20Number.java)**      Level: Medium      Tags: [Backtracking, String]
      
方法1: Iterative with BFS using queue.

方法2: Recursively adding chars per digit



---

**6. [Orderly Queue.java](https://github.com/awangdev/LintCode/blob/master/Java/Orderly%20Queue.java)**      Level: Hard      Tags: [Math, String]
      



---

**7. [Scramble String.java](https://github.com/awangdev/LintCode/blob/master/Java/Scramble%20String.java)**      Level: Hard      Tags: [DP, Interval DP, String]
      
- 给两个string S, T. 检验他们是不是scramble string.
- scramble string 定义: string可以被分拆成binary tree的形式, 也就是切割成substring;
- 旋转了不是leaf的node之后, 形成新的substring, 这就是原来string的 scramble.


#### Interval DP 区间型
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

**8. [Compare Version Numbers.java](https://github.com/awangdev/LintCode/blob/master/Java/Compare%20Version%20Numbers.java)**      Level: Medium      Tags: [String]
      
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

**9. [Longest Common Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Substring.java)**      Level: Medium      Tags: [DP, Double Sequence DP, Sequence DP, String]
      
#### Double Sequence DP
- 两个string, 找最值: longest common string length
- 序列型, 并且是双序列, 找两个序列 (两维的某种性质)
- dp[i][j]: 对于 A 的前i个字母, 对于 B 的前j个字母, 找最长公共substring的长度
- dp = new int[m + 1][n + 1]
- dp[i][j] = dp[i - 1][j - 1] + 1; only if A.charAt(i - 1) == B.charAt(j - 1)
- 注意track max, 最后return
- space O(n^2), time(n^2)

##### Rolling array
- 空间优化, [i] 只有和 [i - 1] 相关, 空间优化成 O(n)

#### String
- 找所有A的substring, 然后B.contains()
- track max substring length
- O(n^2) time



---

**10. [Shortest Palindrome.java](https://github.com/awangdev/LintCode/blob/master/Java/Shortest%20Palindrome.java)**      Level: Hard      Tags: [KMP, String]
      
#### Divide by mid point, Brutle
- check (mid, mid+1), or (mid-1, mid+1).
- If the two position matches, that is a palindrome candidate
- 比较front string 是否是 end string 的substring
- O(n^2)
- timeout on last case: ["aaaaaa....aaaacdaaa...aaaaaa"]

#### KMP
- TODO



---

**11. [Space Replacement.java](https://github.com/awangdev/LintCode/blob/master/Java/Space%20Replacement.java)**      Level: Medium      Tags: [String]
      


---

**12. [Palindrome Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Pairs.java)**      Level: Hard      Tags: [Hash Table, String, Trie]
      
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

**13. [Longest Substring Without Repeating Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20Without%20Repeating%20Characters.java)**      Level: Medium      Tags: [Hash Table, String, Two Pointers]
      
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

**14. [Reverse Words in a String II.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Words%20in%20a%20String%20II.java)**      Level: Medium      Tags: [String]
      
#### In-place reverse
- reverse用两回. 全局reverse。局部:遇到空格reverse
- 注意ending index: `i == str.length - 1`, 结尾点即使没有' '也要给reverse一下最后一个词




---

**15. [One Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/One%20Edit%20Distance.java)**      Level: Medium      Tags: [String]
      
如果S, T只用一个operation就能变成相等, return true.

#### Edit: 删除，增加，和替换
- 换完之后，理论上换成的String 就应该全等
- for loop, 一旦找到不一样的char, 就判断那三种可能性: insert/delete/replace
- insert/delete 对于2个string来说, 效果是类似的
- O(n)



---

**16. [Distinct Subsequences.java](https://github.com/awangdev/LintCode/blob/master/Java/Distinct%20Subsequences.java)**      Level: Hard      Tags: [DP, String]
      
Double Sequence DP:
0. DP size (n+1): 找前nth的结果, 那么dp array就需要开n+1, 因为结尾要return dp[n][m]
1. 在for loop 里面initialize dp[0][j] dp[i][0]
2. Rolling array 优化成O(N): 如果dp[i][j]在for loop里面, 就很好替换 curr/prev



---

**17. [Encode and Decode Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Encode%20and%20Decode%20Strings.java)**      Level: Medium      Tags: [String]
      
如题.

#### String
- 'word.length()#word' 这样encode, 可以避免遇到#
- 基于我们自己定的规律, 在decode的里面不需要过多地去check error input, assume所有input都是规范的.    
- decode就是找"#",然后用"#"前的数字截取后面的string.




---

**18. [Wildcard Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java)**      Level: Hard      Tags: [Backtracking, DP, Double Sequence DP, Greedy, Sequence DP, String]
      
Double sequence DP. 与regular expression 很像.

#### Double Sequence DP
- 分析字符 ?, * 所代表的真正意义, 然后写出表达式.
- 搞清楚initialization 的时候 dp[i][0] 应该always false. 当p为empty string, 无论如何都match不了 (除非s="" as well)
- 同时 dp[0][j]不一定是false. 比如s="",p="*" 就是一个matching.
- A. p[j] != '*'
    1. last index match => dp[i - 1][j - 1]
    2. last index == ?  => dp[i - 1][j - 1]
- B. p[j] == "*"
    1. * is empty => dp[i][j - 1]
    2. * match 1 or more chars => dp[i - 1][j]




---

**19. [Expression Add Operators.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Add%20Operators.java)**      Level: Hard      Tags: [Backtracking, DFS, Divide and Conquer, String]
      

给一个数字String, 数字来自`0-9`, 给3个操作符 `+`,`-`,`*`, 看如何拼凑, 可以做出结果target.

output 所有 expression

#### string dfs, use list to track steps (backtracking)
- 跟string相关, 写起来可能稍微繁琐一点
    - 数字有 dfs([1,2,3...]) 组合方法
    - operator有[`+`,`-`,`*`] 3种组合方法
- 注意1: 乘号要特殊处理, pass along 连乘的数字, 计算下一步乘积的时候, 要 sum - preProduct + product
- 注意2: '01' 这种数字要skip
- 注意3: 第一个选中数字不需要加操作符, 直接加进去
- Time: O(4^n)， Space: O(4^n)
- T(n) = 3 * T(n-1) + 3 * T(n-2) + 3 * T(n-3) + ... + 3 *T(1);
- T(n-1) = 3 * T(n-2) + 3 * T(n-3) + ... 3 * T(1);
- Thus T(n) = 4T(n-1) = 4^2 * T(n - 1) = .... O(4^n)

#### String dfs, use string as buffer
- 逻辑一样, 代码更短, 只不过不做list, 直接pass `buffer + "+" + curr`
- 因为每次都创建新string, 所以速度稍微慢一点. Time complexity 一样



---

**20. [Longest Valid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Valid%20Parentheses.java)**      Level: Hard      Tags: [Coordinate DP, Stack, String]
      
给一串string, 里面只有`(`, `)`. 找最长valid parentheses 的长度.

#### 1D Coordinate DP
- use dp[i] track local max, maintain global max
- int[] dp. dp[i]: longest valid string that ends on i.
- 结尾是 ')', 2种情况: 1. 刚好s[i-1]是'('; 2. s[i]的')'更前面的一个起始'(' 对应
- 注意, 结尾如果是'('属于不合理情况, 忽略.
- init: dp[0] = 0, 单个char不可能成型.
- 计算顺序: 从左到右, 找local max, maintain global max
- O(n) space, O(n) runtime

#### Stack
- Stack 里面存所有的open/close parentheses.
- 如果遇到stack.top()刚好开合结掉, 就stack.pop().
- 剩下的都是不合理的elements.
- 有点像negatively找 solution: `endIndex - 最后一个failedIndex(stack.pop()) - 1`, 应该就是最后一个succeeded string的长度
- 每次更新 endIndex 为stack.top(), 然后从stack继续找下一个failedIndex
- 所有的length作比较, 就可以找出最长length
- O(n) stack space, O(n) runtime. 应该比dp慢一点, 因为做了2遍O(n)




---

**21. [Interleaving String.java](https://github.com/awangdev/LintCode/blob/master/Java/Interleaving%20String.java)**      Level: Hard      Tags: [DP, String]
      
双序列DP, 从最后点考虑.
拆分问题的末尾, 考虑和s1, s2 subsequence之间的关联.

求存在性, boolean




---

**22. [Sort Letters by Case.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Letters%20by%20Case.java)**      Level: Medium      Tags: [Partition, Sort, String, Two Pointers]
      
给一串字符(ASCII 大写, 小写字母), 要求sort 小写字母, 在大写字母前面. 

字母间的前后顺序无所谓, 也不需要preserve original order .

跟sort color分成相似.

#### Partition + Two pointers
- 其实就是quick sort里面的partition function的简化版
- Two pointers, 找一个 pivot 'a' 来区分大写小写字母
- ASCII code 里面 大写字母在小写字母前面, 数字更小
- 然后 while, move start++, end--,
- 每一轮都swap

#### Two pointers
- 直接用两个 pointer left/right 标记开头结尾
- 每次遇到 `>= 'a'` 就是小写字母, swap(chars, i, left);
- 每次遇到 `< 'a'` 就是大写字母, swap(chars, i, right);
- 注意: 每次处理完left swap, 任由for loop i++, 因为确定 [0 left] 都是准确的
- 每次处理完 right swap, 我们不确定从 right index 换过来的是不是正确的, 所以 i--, 跟for loop 的 i++抵消.
- 写 while loop 的 solution看起来更容易理解.



---

**23. [[HackerRank]. Change to Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/[HackerRank].%20Change%20to%20Anagram.java)**      Level: Easy      Tags: [String]
      
HackerRank里面的random 题目: 给一个string, 一切成两半, 看两半要变化多少个字符, 能变成anagram.

- 切两半成两个String A,B. 分别在int count[26]里面++, --.
- 记录 26个小写字母的频率. 如果全部抵消, 就是anagram.
- 注意: 最后count出来要除以2：字母不同，会在不同的字母位上加减count,那么就是刚好重复计算了一遍。所以除以二

- Note: HackerRank里要注意自己写: Scanner, import java.util, non-static method ...etc.



---

**24. [Word Ladder II.java](https://github.com/awangdev/LintCode/blob/master/Java/Word%20Ladder%20II.java)**      Level: Hard      Tags: [Array, BFS, Backtracking, DFS, Hash Table, String]
      
给一串string, start word, end word. 找到所有从 startWord -> endWord的最短路径list. 

变化方式: mutate 1 letter at a time.

#### BFS + Reverse Search
- 用BFS找最短路径.
- 问题: how to effectively store the path, if the number of paths are really large? 
- If we store Queue<List<String candidates>>: all possibilities will very large and not maintainable
- 用BFS做出一个反向structure, 然后再reverse search

##### BFS Prep Step
- BFS 找到所有start string 可以走到的地方 s, 放在一个overall structure里面: 注意, 存的方式 Map<s, list of sources>
- BFS时候每次都变化1step, 所以记录一次distance, 其实就是最短路径candidate (止步于此)
- 1. 反向mutation map: `destination/end string -> all source candidates` using queue: `Mutation Map`
- Mutation Map<s, List<possible src>>: list possible source strings to mutate into target key string.
- 2. 反向distance map: `destination/end string -> shortest distance to reach dest`
- Distance Map<s, possible/shortest distance>: shortest distance from to mutate into target key string.
- BFS prep step 并没解决问题, 甚至都没有用到end string. 我们要用BFS建成的反向mapping structure, 做search

##### Search using DFS
- 从结尾end string 开始扫, 找所有可以reach的candidate && only visit candidate that is 1 step away
- dfs 直到找到start string.

##### Bi-directional BFS: Search using BFS
- reversed structure 已经做好了, 现在做search 就可以: 也可以选用bfs.
- `Queue<List<String>>` to store candidates, searching from end-> start



---

**25. [Next Closest Time.java](https://github.com/awangdev/LintCode/blob/master/Java/Next%20Closest%20Time.java)**      Level: Medium      Tags: [Basic Implementation, Enumeration, String]
      
给一个时间string"12:09", 用里面的4个integer组合成其他时间string, 目标找最小的next time.

如果组合出的time string 在input time之前, 默认 + 24 hours.

#### String
- enumerate all candidates and filter to keep the correct ones
- String.compareTo(string) -> gives lexicographical comparision



---

**26. [Group Shifted Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/Group%20Shifted%20Strings.java)**      Level: Medium      Tags: [Hash Table, String]
      

#### Convert to orginal string
- shit by offset. `int offset = s.charAt(0) - 'a';`
- increase if less than 'a': `if (newChar < 'a') newChar += 26;`

#### Previous notes
- 相同shift规则的string, 能被推算到同一个零起始点，就是共同减去一个char,最后就相等。以此作为key，用HashMap。一目了然。
- 记得根据题目意思，一开始要String[] sort一下。



---

**27. [[lint]. Compare Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Compare%20Strings.java)**      Level: Easy      Tags: [Lint, String]
      
看StringA是不是包括所有 StringB的字符. Anagram

#### Basic Implementation
- 比较一下大小, null.
- 然后用int[]来count chars from A, count[x]++. 再对照chars in B, count[x]--
- 如果 count[c] < 0, 就 false.
- O(n)



---

**28. [[lint]. Longest Words.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Longest%20Words.java)**      Level: Easy      Tags: [Hash Table, Lint, String]
      
给一串String, 找到最长的长度, 把最长的String全都return

#### Hash Table
- <Integer,List<String>>
- 存最长值, 最后map.get(max) 



---

**29. [[lint]. Unique Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Unique%20Characters.java)**      Level: Easy      Tags: [Array, Lint, String]
      
determine if characters are unique in string

#### HashSet
- space O(n), time O(n)

#### char[]
- space O(n), time O(nlogn)

#### no additional data structure
- double for loop:  O(n^2)




---

**30. [788. Rotated Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/788.%20Rotated%20Digits.java)**      Level: Easy      Tags: [Basic Implementation, String]
      

#### Basic Implementation of the rules
- [3,4,7] -> cannot rotate, failures. Must NOT have. set1
- 2,5,6,9 -> good candidates. Must have 1. set2
- [0,1,8] -> goes back to itself. can have
- loop over [1, N], count=int[10] appearance.
    - set1 meet 0
    - set2 meet at least 1
    


---

**31. [22. Generate Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/22.%20Generate%20Parentheses.java)**      Level: Medium      Tags: [Backtracking, DFS, Sequence DFS, String]
      

#### DFS
- start with empty string, need to go top->bottom
- 取或者不取`(`, `)`
- rule: open parentheses >= close parentheses
- Note: 在DFS时 pass a reference (StringBuffer) and maintain, instead of passing object (String) and re-create every time
- time: O(2^n), pick/not pick, the decision repat for all nodes at every level
- time: T(n) = 2 * T(n - 1) + O(1) = O(2^n)
- space: < than 2^n results = O(2^n)

#### bottom->up DFS
- figure out n=1, n=2 => build n=3, and n=4
- dfs(n-1) return a list of candidates
- add a pair of `()` to the candidates: either in front, at end, or contain the candidates



---

**32. [408. Valid Word Abbreviation.java](https://github.com/awangdev/LintCode/blob/master/Java/408.%20Valid%20Word%20Abbreviation.java)**      Level: Easy      Tags: [Basic Implementation, String]
      
tricky: find integer within a string
edge case: leading '0' should not be allow in such abbr.



---

**33. [415. Add Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/415.%20Add%20Strings.java)**      Level: Easy      Tags: [Basic Implementation, Math, String]
      

#### Two Pointer 
- Use i, j to process from end of 2 strings
- handle edge case for i, j
    - if i < 0, its num = 0 (since we are doing sum, blindly setting 0 is okay)
- Note: `sb.insert(0, x)` is much slower than doing a final `sb.reverse()`

#### If manually convertin to int[]
1. when converting to int[], remember to reverse string.
1. when converting to int[], remember to reserve extra space for carry



---

**34. [1108. Defanging an IP Address.java](https://github.com/awangdev/LintCode/blob/master/Java/1108.%20Defanging%20an%20IP%20Address.java)**      Level: Easy      Tags: [Basic Implementation, String]
      


---

**35. [383. Ransom Note.java](https://github.com/awangdev/LintCode/blob/master/Java/383.%20Ransom%20Note.java)**      Level: Easy      Tags: [Basic Implementation, String]
      
count chars in int[256]



---

**36. [76. Minimum Window Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/76.%20Minimum%20Window%20Substring.java)**      Level: Hard      Tags: [Hash Table, Sliding Window, String, Two Pointers]
      

基本思想:
- 用个char[]存string的frequency.
- 2 pointer: 
    - move `end` to find a valid window; 
    - once valid inwindow found: now move `start` to narrow down to minimum window.
    - once window invalid, continue moving `end` and repeat last 2 steps
- HashMap的做法比char[]写起来要复杂一点, 但是更generic

#### Method0: Sliding Window + freq[256] + counter
- Almost identical approach as in `438. Find All Anagrams in a String` 
- use sliding window template:
    - 1) extend right pointer and reduce char count
    - 2) process when count == 0
    - 3) contract/shrink left side
- special on the `3) step`:
    - there is no hard length limit in this problem: in fact, the goal is to find the shortest length
    - `3) step` now apperas in the `while(counter == 0)` loop
    - shrink the left side of the window as long as counter == 0, until we break the `counter==0` balance.
- time: O(n) one pass
- space: O(1), freq[256] can be ignored.


#### Method1: init a valid freq map; maintain with counter
- Two Pointers, use 1 char freq map + counter to determine valid state
- Inspired by: https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
- Idea: use freqMap and counter to maintain a valid substring range, use two pointers to iterate; reduce to `counter==0` which is the valid substring state.
- Steps:
    - 1) build valid freq count map based on target string
    - 2) use end index [0~n) to find valid char and reduce counter to find valid range
    - 3) count==0 gives valid range: process; then `map[s.charAt(start++)]++ == 0` to break the peace
- Explain `if (map[s.charAt(start++)]++ == 0) counter++`: 
    - when `count != 0`, `map[s.charAt(end++)]--` reduces freq regardless of what char it visits (it can be ANY char, rather than T characters)
    - when `count == 0`, `map[s.charAt(start++)]++` increases freq regardless of what char that is.
        - if `map[s.charAt(start)] == 0`: it is a T character being reduced to 0 previously (so we can break the balance on this char)
        - YES, map has other index that has 0 freq: however, `start` ONLY covers indexes that `end` has stepped through :)
- time: O(n)
- space: O(1)
- much faster than method2: skip the O(256*n) comparison logic.
- Note: from the concept, it is the reversed thinking of method2.

#### Method2: build valid map on the fly and compare. Two Pointers, Use 2 Char freq map
- Use 2 char freq maps: source/target.
    - target map: fixed freq map, used for comparision
    - source map: attempt to build a valid freq map on the fly
- two pointers: 
    - use index `start=[0, n)` as start index of source candidate
    - have a end pointer that will attempt to as far as possible to find 1st valid sequence
- time: have double while loop, but still O(n), why?
    - end pointer will at most reach full length n, only once
    - start pointer iterate source strichtly once O(n)
    - overall, it will be O(n)
- space: O(1), only used a constant char[256]
- Option2: use map, a bit more generic



---

**37. [293. Flip Game.java](https://github.com/awangdev/LintCode/blob/master/Java/293.%20Flip%20Game.java)**      Level: Easy      Tags: [String]
      
#### String
- 可以用 sb.replace(i, j, "replacement string")
- 简单按 window=2 来扫描
- 原来只需要从'++'转到'--'的情况
- O(n)



---

**38. [686. Repeated String Match.java](https://github.com/awangdev/LintCode/blob/master/Java/686.%20Repeated%20String%20Match.java)**      Level: Easy      Tags: [Basic Implementation, Edge Case, String]
      
Track: 纸上分析edge case.
Validation helps speed it up.



---

**39. [1216. Valid Palindrome III.java](https://github.com/awangdev/LintCode/blob/master/Java/1216.%20Valid%20Palindrome%20III.java)**      Level: Hard      Tags: [DFS, DP, Memoization, String]
      

#### Method1: DP, utilize `Longest Palindrome Subsequence`
- Transform the problem:
    - `removing at most k items to make it a palindrome`
    - that is: find the longest palindrome subsequence with length x, such that `n - x <= k`
- `516. Longest Palindromic Subsequence` utilizes Interval DP to find LPS length x
- at the end, perform n - x <= k?
- time: O(n^2) to find LPS
- space: O(n^2) for dp

#### Method2: DFS with Memo
- Either times out or too much space used
- time: O(n^2)
- space: O(n^2) or O(k*n^2)



---

**40. [5. Longest Palindromic Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/5.%20Longest%20Palindromic%20Substring.java)**      Level: Medium      Tags: [DP, String]
      

给一个string, 找到最长的palindrome substring.

Related: Longest Palindromic Subsequence, Palindrome Partioning II

O(n^2) is not too hard to think of. How about O(n)?

#### Method1: DP of interval
- Very similar to `216. Longest Palindromic Subsequence`, but this problem requires solid substring(i+1, j-1) to be palindromic
- Similarly: process i = n-1, from end so [i + 1, j] is always ready to consume
- boolean dp[i][j] to mark range (i, j) as palindrome or not.
- 在计算 dp[i][j]的时候, isPalin[i+1][j-1]应该已经计算过了.
- time: O(n^2) dp
- space: O(n^2)

#### String, Palindrome definition
- 从中间劈开, 遍历i: 从n个不同的点劈开: 每次劈开都看是否可以从劈开出作为palindromic的中点延伸
- palindrome两种情况: odd, even palindrome
- Worst case: 整个string都是相同字符，time complexity变成： 1 + 2 +３　＋　．．．　＋n = O(n^2)



#### O(n) 
- TODO
- https://www.felix021.com/blog/read.php?2040



---

**41. [58. Length of Last Word.java](https://github.com/awangdev/LintCode/blob/master/Java/58.%20Length%20of%20Last%20Word.java)**      Level: Easy      Tags: [String]
      
给一个String, 里面有lower case character 和 ' '. 找最后一个单个word的长度

#### basics
- 从末尾找' ', 找到了计算长度
- 记得要s.trim(), 把首尾的space去掉



---

**42. [824. Goat Latin.java](https://github.com/awangdev/LintCode/blob/master/Java/824.%20Goat%20Latin.java)**      Level: Easy      Tags: [Basic Implementation, String]
      



---

**43. [151. Reverse Words in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/151.%20Reverse%20Words%20in%20a%20String.java)**      Level: Medium      Tags: [String]
      

#### Method1: Split string by space, then flip 
- Option1: With `s.split(" ")`: No brain, and super fast
- Option2: With `s.split("\\s+")`, it skips space, but slow. Use sb.insert(0, xxx)
- trim() output
- Time, Space: O(n)

#### Method2: Flip entire, then individual, two pointer
- flip entire string, then flip each individual string
- Time, Space: O(n)



---

**44. [67. Add Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/67.%20Add%20Binary.java)**      Level: Easy      Tags: [Math, String, Two Pointers]
      
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

**45. [557. Reverse Words in a String III.java](https://github.com/awangdev/LintCode/blob/master/Java/557.%20Reverse%20Words%20in%20a%20String%20III.java)**      Level: Easy      Tags: [String]
      
给一个String, 里面的Word被single space split开来, 目的是reverse里面所有的Word, 但preserve Word 和 space order.

#### Reverse function
- Reverse Words in a String II 的降级版, 去掉第一个overall reverse就好了



---

**46. [1249. Minimum Remove to Make Valid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/1249.%20Minimum%20Remove%20to%20Make%20Valid%20Parentheses.java)**      Level: Medium      Tags: [Stack, String]
      

#### Stack
- Goal: remove extra '(' or ')' so it is valid.
- Forward thinking: use stack to track '(' and ')', then keep appending partial string to output
- Backward thinking: use stack to filter out false indexes, and remove them in the end




---

**47. [443. String Compression.java](https://github.com/awangdev/LintCode/blob/master/Java/443.%20String%20Compression.java)**      Level: Easy      Tags: [Basic Implementation, String]
      


---

**48. [727. Minimum Window Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/727.%20Minimum%20Window%20Subsequence.java)**      Level: Hard      Tags: [DP, Hash Table, Sliding Window, String, Two Pointers]
      

#### Sliding Window
- DIFFERENT from sliding window for substring (`76. Minimum Window Substring`)
    - because this problem rquries keeping the order of characters from the target string
    - Use a `backtrack mechanism` based on target matching to find closest left starting point to right
- Simple two pointers:
    - 1) move sIndex and tIndex: find all T chars in S, in order.
    - 2) backtrack tIndex to 0; backtrack sIndex to initial char match
    - 3) record potential min result
- Be VERY careful about pointer and index.
- time: O(n^2), backtrack n steps
- Since it requires **order of substring**, `freqMap+counter+twoPointers` approach is NOT applicable

#### DP
- TODO



---

**49. [158. Read N Characters Given Read4 II - Call multiple times.java](https://github.com/awangdev/LintCode/blob/master/Java/158.%20Read%20N%20Characters%20Given%20Read4%20II%20-%20Call%20multiple%20times.java)**      Level: Hard      Tags: [Enumeration, String]
      

Read N Character using `Read4(char[] buf)` 的加强版: 可以不断读 read(buf, n)

#### String 
- 注意String的index handle, 慢慢写edge case
- 理解题目意思: `read4(char[] buf)` 这样的 `populate input object` 的function稍微少一点. 
- 遇到时候, 仔细理解function用法, 不要慌乱. 其实思考方式很简单, 仔细handle string 还有 edge case就好了.
- alaternatively: use queue to hold so we do not need to worry about size



---

**50. [43. Multiply Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/43.%20Multiply%20Strings.java)**      Level: Medium      Tags: [Math, String]
      

给两个integer String, 求乘积

#### String calculation, basic implementation
- let num1 = multipier, num2 = base. mutiply and save into int[m + n]. 
    - Loop over num1, each row num1[x] * num2, save to correct index (i + j + 1)
    - Note: skip leading '0' during output, but do not delete string "0"
    - time,space O(mn)
- Option1: Calculate carry on the fly
    - index `curr = i + j + 1`, left index `left = curr - 1`, since we start calculation from end of the array.
    - **we only touch right side of the array once**, so we can move the carry off from it, and carry to left index
    - code is concise
- Option2: save product first without calculating carry
    - save product in each int index
    - calculate carry on rst[] and `sb.insert(0, c)` (since we start from end of rst)
    - this is actaully faster than Option1, somehow.

#### Reverse, calculate from index 0, and reverse back
- 1. 数字‘123’， 在数组里面， index == 0 是 ‘1’。 但是我们平时习惯从最小位数开始乘积，就是末尾的'3'开始。
- 所以！翻转两个数字先！我去。这个是个大坑。
- 2. 乘积product，和移动Carrier都很普通。
- 3. ！！最后不能忘了再翻转。
- 4. 最后一个看坑。要是乘积是0，就返回‘0’。 但是这个其实可以在开头catch到没必要做到结尾catch。
- 用到几个StringBuffer的好东西: reverse(), sb.deleteCharAt(i)   
- 找数字，或者26个字母，都可以: s.charAt(i) - '0'; s.charAt(i) - 'a';



---

**51. [680. Valid Palindrome II.java](https://github.com/awangdev/LintCode/blob/master/Java/680.%20Valid%20Palindrome%20II.java)**      Level: Easy      Tags: [String]
      
#### Palindrome String
- delete an index: 有两种情况
- 用一个boolean parameter来表现state. 如果有其他status, state可以变成 String/enum



---

**52. [387. First Unique Character in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/387.%20First%20Unique%20Character%20in%20a%20String.java)**      Level: Easy      Tags: [Hash Table, String]
      

#### Count appearance with int[256]
- 按照题意, 找到第一个 first index == last index的字母.

#### Count appearance with hashmap (more scalable)
- 用hashmap存字母的index, 有些重复字母的index就会是个list. 
- 找到单一index, 结合成list, sort, return list.get(0)
- slow due 



---

**53. [345. Reverse Vowels of a String.java](https://github.com/awangdev/LintCode/blob/master/Java/345.%20Reverse%20Vowels%20of%20a%20String.java)**      Level: Easy      Tags: [String, Two Pointers]
      
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

**54. [10. Regular Expression Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/10.%20Regular%20Expression%20Matching.java)**      Level: Hard      Tags: [Backtracking, DP, Double Sequence DP, Sequence DP, String]
      
跟WildCard Matching 一样, 分清楚情况讨论 string p last char is '*' 还有并不是 '*'

IMPORTANT: '*' 需要有一个 prefix element [elm], so it becomes `[elm]*`. There 2 possible cases:
- [elm] repeats 0 times: move p, j + 2
- [elm] repeats 1 or more times: need s[i] == p[i], then move s, i+1

#### DFS, Top-Down, Break into sub problems.
- DFS on remaining of s and p. Analyze the different cases when next char == '*'
- End case: both i,j reached end true; or one of them reached end.
- The two different cases when given any index j on p, the  p[j+1]=='*'
    - TRUE:
        - ignore p[j, j+1], continue from p[j+2]
        - check if s[i]==p[j] or p[j]='.'; continue from s[i+1] and p
    - FALSE: check i,j, and move forward with s[i+1], p[j+1]
- If next p char != '*', check curr s[i] ?= p[i]
- Improvement with memo with 2D Booelan[][] memo: much faster
    - memo[i][j] records result the remaining strings: s.substring(i) compare with p.substring(j)
    - use `Boolean`: when memo[i][j] != null, return something!

#### DP, Sequence DP, Bottom-Up
- Two sequence, DP, find if possible to match.
- The '*' takes effect of preceding/prior element, so we can start matching from end.
- DP[i][j]: is it possible to match s[0 ~ i - 1] and p[0 ~ j - 1].
- Check last index of s and p, there can be a few possibilities:
    - 1. s[i-1]==p[j-1] and they are normal characters => && dp[i - 1][j - 1];
    - 2. p[j-1] == '.', match => dp[i - 1][j - 1]
    - 3. p[j-1] == '*':
        - a. ignore a* => |= dp[i][j - 2];
        - b. use a*    => |= dp[i - 1][j]; 
- init: dp[0][j] and dp[i][0] will all be false since there cannot be any match.




---

**55. [28. Implement strStr().java](https://github.com/awangdev/LintCode/blob/master/Java/28.%20Implement%20strStr().java)**      Level: Easy      Tags: [String, Two Pointers]
      
给两个string A, B, 找一个 B 在 A 种的起始位置.

#### Two Pointer
- 找到B在A中的起始位置, 然后看一下从这个点开始的substring是否等于B就可以了
- 还挺多坑的, 这些可以帮助优化:
- 1. 当B是“”的时候，也就是能在A的其实位置找到B....index = 0.
- 2. edge condition: 如果 haystack.length() < needle.length() 的话, 必须错, return -1
- 3. 如果在某个index, A后面剩下的长度, 比B的长度短, 也是误解, return -1



---

**56. [1106. Parsing A Boolean Expression.java](https://github.com/awangdev/LintCode/blob/master/Java/1106.%20Parsing%20A%20Boolean%20Expression.java)**      Level: Hard      Tags: [DFS, Stack, String]
      
#### Parse exp as sub problem
- Analyze the pattern: 1) single char, 2) with !, 3) with &, |
- Identify sub problem
    - Use stack to parse the data in "()", which is a sub problem to solve with recursive call
    - Handle &, | case: need to parse multiple
- Be comfortable with string parsing
- Slight improve: 
    - If see obvious result, directly return evaluation w/o further parsing
    - use memo to store evaluated exp

#### Evaluate inner exp and save back to Stack
- Use '(' and ')' to mark inner exp
- Evaluate the inner exp and save result back to Stack: the result will be 'f' or 't'
- This is slightly slow because:
    - It requires all stack items on top to be processed before reaching the operator
    - There is no room to optimize even there is simplification for specific operator



---

**57. [12. Integer to Roman.java](https://github.com/awangdev/LintCode/blob/master/Java/12.%20Integer%20to%20Roman.java)**      Level: Medium      Tags: [Basic Implementation, Math, String]
      

#### String, Basic implementation
- Parse each digit based on rules
- 1) parse: analyze the situations


---

**58. [14. Longest Common Prefix.java](https://github.com/awangdev/LintCode/blob/master/Java/14.%20Longest%20Common%20Prefix.java)**      Level: Easy      Tags: [String]
      
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

**59. [20. Valid Parentheses.java](https://github.com/awangdev/LintCode/blob/master/Java/20.%20Valid%20Parentheses.java)**      Level: Easy      Tags: [Stack, String]
      

#### Stack
- 剥皮过程。解铃还须系铃人   
- 左边的外皮'{['在stack底部   
- 右边的外皮应该和stack顶上的左外皮一一对应 



---

**60. [893. Groups of Special-Equivalent Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/893.%20Groups%20of%20Special-Equivalent%20Strings.java)**      Level: Easy      Tags: [Basic Implementation, String]
      
Mark # of characters can be useful to print string signature



---

**61. [91. Decode Ways.java](https://github.com/awangdev/LintCode/blob/master/Java/91.%20Decode%20Ways.java)**      Level: Medium      Tags: [DP, Partition DP, String]
      

给出一串数字, 要翻译(decode)成英文字母. [1 ~ 26] 对应相对的英文字母. 求有多少种方法可以decode.

#### Method1: DP, Bottom-Up by calculating base case first
- 加法原理: 根据题意, 有 range = 1 的 [1, 9], range = 2 的 [10~26] 来作为partition.
- there can be 2 final states at dp[i]: single digit or double digit.
    - validate if `single digit`, dp[i] += dp[i - 1]. Last 1 digit can be decoded.
    - validate if `double digit`, dp[i] += dp[i - 2]. Last 2 digits can be decoded.
- note: 
    - get number from char: `c - '0'`
    - validatae single digit != '0', 因为'0' 不在条件之中(A-Z)
- Option1: dp[i] as # ways to decode at index i, including letter i
    - The validation is done on: 1) single digit i, or 2) double digit [i-1, i]
- Option2: Partition DP, dp[i] as # ways to decode for first i letters (excluding letter i)
    - 定义`dp[i] = 前i个digits最多有多少种decode的方法`: new dp[n + 1].
    - dp[i] += dp[i - x], where x = 1, 2
    - The validation is done on: 1) single digit [i-1], or 2) double digit [i-2, i-1]
    - Option2 is better in terms of clean coding. We assume `dp[0]=1` as 1 way to decode 0 digits.
        - No need to specially handle length == 1, because it is covered later
        - No need to manualy init the first 2-digit case as in Option1
        - Return of `dp[n]` is clean
- 引申: 这里只有两种partition的情况 range=1, range =2.  如果有更多partition的种类, 就可能多一层for loop做循环


#### Method2: DFS, Top-Down
- if single-digit is working, sum += dfs(s, i+1);
- if double-digit is working, sum += dfs(s, i+2);
- end case: i >= n, return 0; i == n - 1; i == n - 2
    - especially when i = n - 2, handle 2-digt edge case carefully:
        - 1) check if two-digit range [i, i+1] is valid
        - 2) check if single-digit [i] is valid; if so, += dfs(s, i + 1)
- memo[i]: # ways to decode from [i, n). init with `memo[i]=-1`



---

**62. [68. Text Justification.java](https://github.com/awangdev/LintCode/blob/master/Java/68.%20Text%20Justification.java)**      Level: Hard      Tags: [Enumeration, String]
      

按照规则 adjust text. 就是Word里面: 有一行太长, adjust word 中间的space, 然后保证每一行的total width 顶格.

还有一些细节规则, 看原题

#### String
- greedy approach: line up as many words as possible; once exceed the MaxLength, justify the list of words
- Steps
    - 1) Split & group
    - 2) Juststify a row of words
    - 3) clean up last row
- Calcualte bounded row length = `width + (list.size() - 1)`. `list.size()-1` = Minimum amount of slot/space used.
- Calculate max ave spaces ot insert into each slot = `totalExtraSpace/slot`
- `Juststify a row of words`: 
    - 1) take a list of words and assume minimum 1 space in-between words
    - 2) distribute the remaining spaces evenly to each slot
- Overall runtime: O(n) to go over all space
- Overall space O(maxWidth) for maxWidth amount of strings



---

**63. [340. Longest Substring with At Most K Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/340.%20Longest%20Substring%20with%20At%20Most%20K%20Distinct%20Characters.java)**      Level: Hard      Tags: [Hash Table, LinkedHashMap, Sliding Window, String, Two Pointers]
      

- Method1 and Method2 are identical to `159. Longest Substring with At Most Two Distinct Characters`. 
- However, time complexity for Method2 in increases to O(nk). https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/solution/
- we want to do better than that (Method3)

#### Method1: Slinding Window, Two Pointers: move 1 element at a time
- Typical slinding window: the goal is to keep a distinct char size/window of size 2.
- use a map<char, count> to track; map.size() is the window size. Follow the template
    - 1) move right pointer, and update freq count map
    - 2) if map.size() == k, process and record max len
    - 3) if map.size() > k, maintain window size: drop curr left char, update map
- return max
- time: O(n)
- space: O(k)

#### Method2: Sliding window, Two Pointer: truncate the entire block at a time
- record last occurance index in map<char, index>
    - when size goes over limit, find last occurance of left-most element
    - set left = leftMostIndex + 1. 
    - This truncates entire block before the last occurance of left-most element
- sliding window 的切割: 用hashmap 存 last occurrance of char index; 
- map.remove(c) 之后, 就等于彻底切掉了那一段; 那么 map.get(c) + 1 也就是新的 left window border
- time: O(nk) to find the left-most element
- space: O(k)

#### Method3: Sliding window + LinkedHashMap
- https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/solution/
- as mentioned above, Method2 uses O(nk), because it takes O(k) to find head item that was inserted first
    - meanwhile, we still need the hash map feature to get/put/remove last occurance of a char with O(1)
- Solution: use a LinkedHashMap: 
    - `map.entrySet().iterator()` maintains the insertion order!
- Special handling:
    - since we want the `lastOccurMap` to preserve laset insertion order
    - we need to `remove` the char every time before put.
- time: O(n)
- space: O(k)




---

**64. [796. Rotate String.java](https://github.com/awangdev/LintCode/blob/master/Java/796.%20Rotate%20String.java)**      Level: Easy      Tags: [String]
      
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

**65. [1041. Robot Bounded In Circle.java](https://github.com/awangdev/LintCode/blob/master/Java/1041.%20Robot%20Bounded%20In%20Circle.java)**      Level: Easy      Tags: [String]
      
简单的character checking. 各个方向, 加加减减.



---

**66. [157. Read N Characters Given Read4.java](https://github.com/awangdev/LintCode/blob/master/Java/157.%20Read%20N%20Characters%20Given%20Read4.java)**      Level: Easy      Tags: [Enumeration, String]
      
Read4 题目. 理解题目: 是有个input object buff, 会被populated with data.

#### String in char[] format
- 理解题目: 其实就是track `可以read多少bytes by read4() response`
- 另外一个有用的function `System.arraycopy(src, srcIndex, dest, destIndex, length)`
- Edge Case:
    - When there is not enough space to the result buffer, `i + 3 > n`, then only copy what we can: `Math.min(n - i, count)`
    - `count < 4` meaning there is not enough content to read, break



---

**67. [273. Integer to English Words.java](https://github.com/awangdev/LintCode/blob/master/Java/273.%20Integer%20to%20English%20Words.java)**      Level: Hard      Tags: [Enumeration, Math, String]
      

给一个小于 Integer.MAX_VALUE (2^31 - 1) 的数字, 转换成英语. (不需要加 'and')

#### String
- 基本implementation
- `分类讨论`: thounsand, million, billion.  `3个数字一格`.
- 用array枚举 token
- 运用 % 和 / 来找到每个分段的英语翻译
- 3-digit 的部分, 可以用一个helper funtion来找到结果, 每段的处理方法都是一样的
- Note:
    - StringBuffer 更有效率! `sb.insert(0, xxx)` append在sb前面
    - 注意加 " " 的时候, 如果多余, 要`trim()`
    - 注意, `小于20的数字, 有自己的特殊写法, 需要额外handle`
    - 这道题目就是要细致`耐心`, 几乎么有什么算法, 就是想要写的efficient并且正确, 需要很小心
- Thinking process:
    - `1 ~ 19`: [one, two ... nine, ten, eleven, ...., ninteen]
    - `20 ~ x0`: [twenty, thirty, fourty, ... ninety]
    - `x00`: hundred: 100
    - thousand: 10^3
    - million: 10^6
    - billion: 10^9
    - trillian: 10^12 way over 2^31, not needed
- plan: 
    - parse 3 digits at a time
    - convert the 3 digit to [xx hundred xx-ty x]
    - come up with a string[]
    - insert the thousands/million/billion to the string[]



---

**68. [125. Valid Palindrome.java](https://github.com/awangdev/LintCode/blob/master/Java/125.%20Valid%20Palindrome.java)**      Level: Easy      Tags: [String, Two Pointers]
      
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

**69. [65. Valid Number.java](https://github.com/awangdev/LintCode/blob/master/Java/65.%20Valid%20Number.java)**      Level: Hard      Tags: [Enumeration, Math, String]
      

分析edge case, 和各种情况, 然后判别是否是valid number

#### 情况总结
- 遇到 `.`, `e`, `+/-`, `int`的几种不同情况
- 分别遇到的顺序不同时候, 结果也不同.
- 这道题更多是分析情况, 然后把edge case enumerate出来, 算法的意义比较少.



---

**70. [49. Group Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/49.%20Group%20Anagrams.java)**      Level: Medium      Tags: [Hash Table, String]
      

给一串string, return list of list, 把anagram 放在一起.

#### Hash Table, key 是 character frequency
- 存anagram
- 用 character frequency 来做unique key
    - 用固定长度的char[26] arr 存每个字母的frequency; 然后再 new string(arr).   
    - 因为每个位子上的frequency的变化，就能构建一个unique的string
- O(nk), k = max word length

#### Hash Table, key 是 sorted string (too slow)
- 和check anagram 想法一样：转化并sort char array，用来作为key。
- 把所有anagram 存在一起。注意结尾Collections.sort().
- O(NKlog(K)), N = string[] length, k = longest word length    




---

**71. [159. Longest Substring with At Most Two Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/159.%20Longest%20Substring%20with%20At%20Most%20Two%20Distinct%20Characters.java)**      Level: Medium      Tags: [Hash Table, Sliding Window, String, Two Pointers]
      

如题.

#### Method1: Slinding Window, Two Pointers: move 1 element at a time
- Typical slinding window: the goal is to keep a distinct char size/window of size 2.
- use a map<char, count> to track; map.size() is the window size. Follow the template
    - 1) move right pointer, and update freq count map
    - 2) if map.size() == 2, process and record max len
    - 3) if map.size() > 2, maintain window size: drop curr left char, update map
- return max
- time: O(n)
- space: O(1)

#### Method2: Sliding window, Two Pointer: truncate the entire block at a time
- record last occurance index in map<char, index>
    - when size goes over limit, find last occurance of left-most element
    - set left = leftMostIndex + 1. 
    - This truncates entire block before the last occurance of left-most element
- sliding window 的切割: 用hashmap 存 last occurrance of char index; 
- map.remove(c) 之后, 就等于彻底切掉了那一段; 那么 map.get(c) + 1 也就是新的 left window border
- time: O(n) 
- space: O(1)



---

**72. [1170. Compare Strings by Frequency of the Smallest Character.java](https://github.com/awangdev/LintCode/blob/master/Java/1170.%20Compare%20Strings%20by%20Frequency%20of%20the%20Smallest%20Character.java)**      Level: Easy      Tags: [Array, String]
      

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

**73. [8. String to Integer (atoi).java](https://github.com/awangdev/LintCode/blob/master/Java/8.%20String%20to%20Integer%20(atoi).java)**      Level: Medium      Tags: [Math, String]
      

#### String 
- Handling use cases
- Parse steps:
    - 0. trim space
    - 1 parse operator
    - 2 trim leading zero
    - 3. get number string
- Validation:
    - 1. max length over max integer length
    - 2. exceed min/max value
- Alternatively, regular expression, but not applicable in interview: if (!str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")).  猛了一点



---

**74. [767. Reorganize String.java](https://github.com/awangdev/LintCode/blob/master/Java/767.%20Reorganize%20String.java)**      Level: Medium      Tags: [Greedy, Hash Table, Heap, Sort, String]
      


We want to exhaust largest population and merge like merging k list.
Problem: largest population may result in them being adjacent. How to resolve?

1) process and check at the end, or, 2) sanitize first and process assume correct input

#### Method1: K(k=2) seats apart problem (w/o sanitization)
- Aggregate map<char, count>, and sort the entry with priority queue.(Optionally, can use object `Letter {char c, int count}`)
- Naturally: we want to prioritize the largest population and exhaust it first, so we want to keep it in the a buffer queue
    - it is a queue, first in first out
    - monitor queue size k = 2, so that it holds off the just last-processed letter for 1 unit of time
    - the buffer then sends the last-process item to the main priority queue (pq will sort it again)
- Error handling: largest population may have extra letter
    - the main PQ has already exhausted
    - but the largest-population-letter will end up stuck in the buffer queue
    - it will never be picked up again so the final result sb will be shorter than orignal string: that is the error case
- Option0. Similar to `621. Task Scheduler`:
    - use a buffer to hold potential letter to add back, but NOT ADD BACK YET, until k slots have been filled.
- time: O(m), m = # of unique letters
- space: O(nmLogm), n = length, pq sorting requires mlogm, we will visit all n nodes.

#### Method2: HashMap<Num, # occurance>, Sort (Sanitize input)
- put all in map<char, count>
    - Sanitize the input: if certain popular char count is over (n + 1)/2, then it should fail right away, just return empty map.
    - Once the input is sanitized, when building results, we can be greedy and consume most popular char and then the rest 
- Int[2] can be used store char and count 
    - PriorityQueue can sort int[]. Okay to not specific length of int[] when defining pq.
    - Alternatively, can use a Letter {char c, int count} to represent





---

**75. [71. Simplify Path.java](https://github.com/awangdev/LintCode/blob/master/Java/71.%20Simplify%20Path.java)**      Level: Medium      Tags: [Stack, String]
      

给一个path, simplify成最简单形式. 注意考虑edge case

#### Stack
- 理解unix path:
    - 1. `.` 代表current directory, 可以忽略. 
    - 2. `../` 表示previous level. 
    - 3. double slash 可以忽略.
    - 4. empty string 要output `/`
- parse by '/', and go over using stack
    - put [folder] in stack
    - ".." pop() 1 element of the stack, if anything
    - "." stays the same
- output stack reversely: connect with '/', skip tail



---

**76. [13. Roman to Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/13.%20Roman%20to%20Integer.java)**      Level: Easy      Tags: [Math, String]
      

#### String 
- 熟悉罗马字母规则     
- 1. 'I V X L C D M' 分别代表的数字     
- 2. 列举combo的情况，需要从原sum里面减掉多加的部分: 'IV, IX'减2, 'XL, XC'减20, 'CD, CM'减200. 
- Leading `I(1*2)`, `X(10*2)`, `C(100*2)` causes double counting 

https://en.wikipedia.org/wiki/Roman_numerals

#### use map to store combinations




---

**77. [72. Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/72.%20Edit%20Distance.java)**      Level: Hard      Tags: [DP, Double Sequence DP, Sequence DP, String]
      

两个字符串, A要变成B, 可以 insert/delete/replace, 找最小变化operation count

#### Double Sequence
- 考虑两个字符串的末尾index s[i], t[j]: 如果需要让这两个字符一样, 可能使用题目给出的三种operation: insert/delete/replace?
- 先calculate最坏的情况, 3种operation count + 1; 然后在比较match的情况.
- 注意, 在i或者j为0的时候, 变成另外一个数字的steps只能是全变.
- 第一步, 空间时间都是O(MN), O(MN)
- 滚动数组优化, 空间O(N)

##### Detail analysis
- insert: assume insert on s, `#ofOperation = (s[0 ~ i] to t[0 ~ j-1]) + 1;`
- delete: assume delete on t, `#ofOperatoin = (s[0 ~ i - 1] to t[0 ~ j]) + 1;`
- replace: replace both s and t, `#ofOperatoin = (s[0 ~ i - 1] to t[0 ~ j - 1]) + 1;`
- dp[i][j]代表了两个 sequence 互相之间的性质: s[0 ~ i] 转换成 s[0~j] 所需要的最少 operation count
- init: 当i==0, dp[0][j] = j; 每次都要 + j 个character; 同理, 当j==0, dp[i][0] = i;
- 而dp[i][j]有两种情况处理: `s[i] == t[j]` or `s[i] != t[j]`

##### 何时initialize
- 这种判断取决于经验: 如果知道initialization可以再 double for loop 里面一起做, 那么可以留着那么做
- 这样属于 `需要什么, initialize什么`
- 事后在做space optimization的时候, 可以轻易在 1st dimension 上做rolling array

#### Search
- 可以做, 但是不建议:这道题需要找 min count, 而不是search/find all solutions, 所以search会写的比较复杂, 牛刀杀鸡.



---

