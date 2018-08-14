 
 
 
## Enumeration (15)
**0. [Majority Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Number%20II.java)**      Level: Medium      Tags: [Enumeration, Greedy]
      

#### Array
- 分三份：a b c考虑
- 若a: countA++; 或b: countB++
- 或c:countA--, countB--
- 注意: 按照if statement的顺序, valA&&countA 比valB&&countB有优先性
- 最后出现的两个count>0的a和b,自然是potentially大于1/3的。其中有一个大于1/3.
- 比较countA和countB哪个大，就return哪一个。



---

**1. [Rotate Image.java](https://github.com/awangdev/LintCode/blob/master/Java/Rotate%20Image.java)**      Level: Medium      Tags: [Array, Enumeration]
      

#### 找公式规律
- 找到个转角度的规律公式: r = c; c = (w - r)
- 用temp variable, swap in place.



---

**2. [Valid Sudoku.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Sudoku.java)**      Level: Easy      Tags: [Enumeration, Hash Table]
      

#### Hash Set
- 用HashSet存visited value.
- 在nest for loop里面validate row,col,and block.     
- validate block要利用i 和 j 增长的规律。    
- 说白了，i && j是按照0~n增长的index, 具体怎么用是可以flexible的。这个方法在同一个nest for loop解决所有运算。
- `int c = 3 * (i % 3) + j % 3;` //make use of how i and j increases
- `int r = 3 * (i / 3) + j / 3;`

#### A bit Slower approach
- 单独做block validation: validate block的时候虽然看到了4层for. 其实也就是n^2
- 可能代码稍微复杂一点



---

**3. [Decode Ways II.java](https://github.com/awangdev/LintCode/blob/master/Java/Decode%20Ways%20II.java)**      Level: Hard      Tags: [DP, Enumeration, Partition DP]
      

给出一串数字, 要翻译(decode)成英文字母. [1 ~ 26] 对应相对的英文字母. 求有多少种方法可以decode.

其中字符可能是 "*", 可以代表 [1 - 9]

#### DP
- 乘法原理
- 跟decode way I 一样, 加法原理, 切割点时: 当下是取了 1 digit 还是 2 digits 来decode
- 定义dp[i] = 前i个digits最多有多少种decode的方法. new dp[n + 1].
- 不同的情况是: 每一个partition里面, 如果有"*", 就会在自身延伸出很多不同的可能
- 那么: dp[i] = dp[i - 1] * (#variations of ss[i]) + dp[i - 2] * (#variations of ss[i,i+1])

##### 特点
- 枚举的能力: 具体分析 '*' 出现的位置, 枚举出数字, 基本功. 
- 注意!!题目说 * in [1, 9].   (如果 0 ~ 9 会更难一些)
- 理解取MOD的原因: 数字太大, 取mod来给最终结果: 其实在 10^9 + 7 这么大的 mod 下, 大部分例子是能通过的.
- 枚举好以后, 其实这个题目的写法和思考过程都不难




---

**4. [Next Closest Time.java](https://github.com/awangdev/LintCode/blob/master/Java/Next%20Closest%20Time.java)**      Level: Medium      Tags: [Basic Implementation, Enumeration, String]
      

给一个时间string"12:09", 用里面的4个integer组合成其他时间string, 目标找最小的next time.

如果组合出的time string 在input time之前, 默认 + 24 hours.

#### String
- enumerate all candidates and filter to keep the correct ones
- String.compareTo(string) -> gives lexicographical comparision



---

**5. [Spiral Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Spiral%20Matrix.java)**      Level: Medium      Tags: [Array, Enumeration]
      

从(0,0)坐标, 走完spiral matrix, 把结果存在list里.

#### DX, DY
- Basic implementation, array, enumeration
- 写一下position前进的方向: RIGHT->DOWN->LEFT->UP
- 用一个direction status 确定方向
- 写一个compute direction function 改变方向 `(direction + 1) % 4`
- `boolean[][] visited` 来track走过的地方



---

**6. [Strobogrammatic Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Strobogrammatic%20Number%20II.java)**      Level: Medium      Tags: [DFS, Enumeration, Math, Sequence DFS]
      

TODO: 
1. use list, iterative? keep candidates and populating
2. clean up the dfs code, a bit messy
3. edge case of "0001000" is invalid, right?

#### DFS
- A bit like BFS solution: find inner list, and then combine with outter left/right sides.
- find all solutions, DFS will be easier to write than iterative/BFS
- when n = 1, there can be list of candidates at bottom of the tree, so bottom->up is better
- bottom->up, dfs till leaf level, and return candidates.
- each level, pair with all the candidates
- 其实就是剥皮，一层一层，是一个central-depth-first的，钻到底时候，return n=1,或者n=2的case，然后开始backtracking。
- 难的case先不handle.到底之后来一次overall scan.
- every level have 5 choices of digital pairs to add on sides. Need to do for n-2 times. 
- Time complexity: O(5^n)



---

**7. [Task Scheduler.java](https://github.com/awangdev/LintCode/blob/master/Java/Task%20Scheduler.java)**      Level: Medium      Tags: [Array, Enumeration, Greedy, PriorityQueue, Queue]
      

#### Array, count frequency, enumerate
- Enumerate to understand: 1. we can module the tasks in module/section; 2. Only need sum the intervals/slots, not return actual layout
- Perfect condition, all letters appear identical # times: just line them up separate in order.
- Real case: task appears different times
- 1. Place maxCount task as header followed with n slots: define (maxCount-1) sections
- 2. For tasks with less # than maxCount# can fill the (maxCount-1) sections; what about the tail section?
- 3. Any task with same maxTask#, of if prior sections all filled, will fill the tail section
- To count overall slots/intervals, come up with this equation:
- 1. Fixed sections: `(maxCount - 1) * (n + 1)`
- 2. Plus all repeating maxCount tasks: calculate by couting identical maxCount of them
- 3. Exception: if the first (max - 1) sections are all filled completely, and we still have extra task (ex: when n is not large enough), then just return tasks.length
- time O(1), space O(1)

#### PriorityQueue
- 正面去做: 
- summerize 每个task出现的次数, 然后qp sort Task object, count 大的靠前
- 起始每个section: k slots = n + 1
- 目标是穷尽 k, 或者 穷尽 pq (poll k times, but will save it back to queue if Task # > 0)
- 如果qp 真的穷尽, break, return count
- 不然, count + remain of k
- extra space O(x), time O(n) + constant time O(xlogx), where x = 26



---

**8. [Valid Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Number.java)**      Level: Hard      Tags: [Enumeration, Math, String]
      
time: O(n)

分析edge case, 和各种情况, 然后判别是否是valid number

#### 情况总结
- 遇到 `.`, `e`, `+/-`, `int`的几种不同情况
- 分别遇到的顺序不同时候, 结果也不同.
- 这道题更多是分析情况, 然后把edge case enumerate出来, 算法的意义比较少.



---

**9. [Ugly Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Ugly%20Number%20II.java)**      Level: Medium      Tags: [DP, Enumeration, Heap, Math, PriorityQueue]
      
time: O(n)
space: O(n)

#### DP
- curr index is based on previous calculation: the min of all 3 previous factors
- O(n)

#### PriorityQueue, DP
- 非常brutle的。
- 每次把dp[i-1]拿出来，不管三七二十一，分别乘以2,3,5. 出来的结果放进priority queue做比较。
- 最后时间是n*log(n*3)
- 注意：use long, use HashSet确保没有重复
- O(nlogn)




---

**10. [Read N Characters Given Read4.java](https://github.com/awangdev/LintCode/blob/master/Java/Read%20N%20Characters%20Given%20Read4.java)**      Level: Easy      Tags: [Enumeration, String]
      

Read4 题目. 理解题目: 是有个input object buff, 会被populated with data.

#### String in char[] format
- 理解题目: 其实就是track `可以read多少bytes by read4() response`
- 另外一个有用的function `System.arraycopy(src, srcIndex, dest, destIndex, length)`



---

**11. [Integer to English Words.java](https://github.com/awangdev/LintCode/blob/master/Java/Integer%20to%20English%20Words.java)**      Level: Hard      Tags: [Enumeration, Math, String]
      

给一个小于 Integer.MAX_VALUE (2^31 - 1) 的数字, 转换成英语. (不需要加 'and')

#### String
- 基本implementation
- `分类讨论`: thounsand, million, billion.  `3个数字一格`.
- 用array枚举 token
- 运用 % 和 / 来找到每个分段的英语翻译
- 3-digit 的部分, 可以用一个helper funtion来找到结果, 每段的处理方法都是一样的

#### 注意
- StringBuffer 更有效率! `sb.insert(0, xxx)` append在sb前面
- 注意加 " " 的时候, 如果多余, 要`trim()`
- 注意, 小于20的数字, 有自己的特殊写法, 需要额外handle
- 这道题目就是要细致`耐心`, 几乎么有什么算法, 就是想要写的efficient并且正确, 需要很小心




---

**12. [Text Justification.java](https://github.com/awangdev/LintCode/blob/master/Java/Text%20Justification.java)**      Level: Hard      Tags: [Enumeration, String]
      

按照规则 adjust text. 就是Word里面: 有一行太长, adjust word 中间的space, 然后保证每一行的total width 顶格.

还有一些细节规则, 看原题

#### String
- Summing space = `width + (size-1)`. maintain: 1. list of candidates, 2. width of actual words
- calculate space in between: `remain/(size - 1)`
- overall for loop; clean up list: 1. over size; 2. last item
- 一点也不难, 但是要小心: deal with list of string的时候, 注意处理干净sum size of list<string>, 就行了.
- `干净处理space`: 只处理 (n-1) items, 然后最后一个拿到for loop 外面, 特殊处理.

#### Notes
- Clarification, observation:
- can start with greedy approach to stack as many words as possible
- once exceed the length, pop the top, and justify the added words (untouched words tracked by index)
- left justify: given list/stack of words with size t, overall remaining space length m, 
- deal with last line with special care: just fill one space, and fill the rest of the row with space
- Does not seem very complicated, but need additional care of calculating the amount of space needed.
- Overall runtime: O(n) to go over all space
- Overall space O(maxWidth) for maxWidth amount of strings



---

**13. [Read N Characters Given Read4 II - Call multiple times.java](https://github.com/awangdev/LintCode/blob/master/Java/Read%20N%20Characters%20Given%20Read4%20II%20-%20Call%20multiple%20times.java)**      Level: Hard      Tags: [Enumeration, String]
      

Read N Character using `Read4(char[] buf)` 的加强版: 可以不断读 read(buf, n)

#### String 
- 注意String的index handle, 慢慢写edge case
- 理解题目意思: `read4(char[] buf)` 这样的 `populate input object` 的function稍微少一点. 
- 遇到时候, 仔细理解function用法, 不要慌乱. 其实思考方式很简单, 仔细handle string 还有 edge case就好了.



---

**14. [Strobogrammatic Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Strobogrammatic%20Number.java)**      Level: Easy      Tags: [Enumeration, Hash Table, Math]
      

根据题意枚举, 再根据规则basic implementation

#### Alter input

#### HashTable + Two Pointer



---

