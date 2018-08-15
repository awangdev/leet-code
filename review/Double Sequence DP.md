 
 
 
## Double Sequence DP (6)
**0. [Longest Common Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Subsequence.java)**      Level: Medium      Tags: [DP, Double Sequence DP, Sequence DP]
      

给两个string, A, B. 找这两个string里面的LCS: 最长公共字符长度 (不需要是continuous substring)

#### Double Sequence DP
- 设定dp长度为(n+1), 因为dp[i]要用来表示前i个(ith)时候的状态, 所以长度需要时i+1才可以在i位置, hold住i.
- 双序列: 两个sequence之间的关系, 都是从末尾字符看起, 分析2种情况:
- 1. A最后字符不在common sequence 或者 B最后字符不在common sequence.
- 2. A/B最后字符都在common sequence. 总体count + 1.



---

**1. [Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Edit%20Distance.java)**      Level: Hard      Tags: [DP, Double Sequence DP, Sequence DP, String]
      
time: O(MN)
Space: O(N)

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

**2. [K Edit Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/K%20Edit%20Distance.java)**      Level: Hard      Tags: [DP, Double Sequence DP, Sequence DP, Trie]
      

给一串String, target string, int k. 找string array里面所有的candidate: 变化K次, 能变成target.

#### Trie
TODO

#### Double Sequence DP
- Edit Distance的follow up.
- 其实就是改一下 minEditDistance的function, 带入K作比较罢了.
- 写起来跟Edit Distance 的主要逻辑是一模一样的.
- 但是LintCode 86% test case 时候timeout. 
- Time O(mnh), where h = words.length, 如果 n ~ m, Time 就几乎是 O(n^2), 太慢.



---

**3. [Longest Common Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Substring.java)**      Level: Medium      Tags: [DP, Double Sequence DP, Sequence DP, String]
      

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

**4. [Regular Expression Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Regular%20Expression%20Matching.java)**      Level: Hard      Tags: [Backtracking, DP, Double Sequence DP, Sequence DP, String]
      

跟WildCard Matching 一样, 分清楚情况讨论 string p last char is '*' 还有并不是 '*'

这里的区别是, '*' 需要有一个preceding element, 那么:
- repeat 0 times
- repeat 1 times: need s[i-1] match with prior char p[i-2]



---

**5. [Wildcard Matching.java](https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java)**      Level: Hard      Tags: [Backtracking, DP, Double Sequence DP, Greedy, Sequence DP, String]
      

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

