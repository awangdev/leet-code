 
 
 
## Math (45)
**0. [Missing Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Missing%20Number.java)**      Level: Easy      Tags: [Array, Bit Manipulation, Math]
      
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

**1. [Linked List Cycle II.java](https://github.com/awangdev/LintCode/blob/master/Java/Linked%20List%20Cycle%20II.java)**      Level: Medium      Tags: [Linked List, Math, Two Pointers]
      
LinkedList 里面有 cycle, 找到cycle的起始点(第一个重复出现的element).

#### Slow, fast Pointer
- 快慢指针, O(1)space.
- 1. 确认有cycle后 2. 数学问题:找到开头.
- 当head == slow.next时候， head就是cycle starting point.
- 也就是说，当slow 移动到了那个回溯点，slow.next那个点就刚好是head的那个点...

#### 证明
- 1. 假设慢指针走t步, 快指针走快一倍, 也就是2t.
- 2. 我们假设cycle的长度是Y, 而进入cycle之前的长度为X.
- 3. 假设慢指针走了m圈cycle, 而快指针走了n圈cycle之后, 两个pointer相遇.
- 4. 最终在Y cycle里面的K点相遇, 也就是两个指针都在这最后一圈里面走了K 步.
- 那么:
- t = X + mY + K
- 2t = X + nY + K
- 整合公式: X + K = (n - 2m)Y
- 这里的m和n不过是整数的跑圈数, 也就是说X和K加在一起, 总归是结束cycle. X 和 K 互补
- 结论: 当slow/fast 指针在K点相遇后, 再走X步, 就到了cycle的起点, 也就是题目要求的起点.

#### Hash Table, O(n) space




---

**2. [Trailing Zeros.java](https://github.com/awangdev/LintCode/blob/master/Java/Trailing%20Zeros.java)**      Level: Easy      Tags: [Math]
      


---

**3. [Plus One.java](https://github.com/awangdev/LintCode/blob/master/Java/Plus%20One.java)**      Level: Easy      Tags: [Array, Math]
      
简单的实现, 加1, 进位. 唯一取巧的地方, 最后如果要多一位, 一定是10000...这个模式, 可以走捷径, 直接来个+1size的array, 然后第一位=1.
注意,转换成long也不合理,用太多memory.


---

**4. [Line Reflection.java](https://github.com/awangdev/LintCode/blob/master/Java/Line%20Reflection.java)**      Level: Medium      Tags: [Hash Table, Math]
      

给一串点, 找是否有一个所有点中间的, 跟y-axis平行的中线.

#### Hash Table
- 1. store in `Map<y, set<x>>`, 2. iterate over map, check head,tail against the mid point
- 很好的细节题目:
- 1. 除以2, 需要存double
- 2. (问面试官)可以有重复的点! 所以track `set<x>`
- 3. 处理 left==right时候, 就当做两个点来处理.
- 4. 存进set里面没有sort, 但是最后做check的时候, 需要sort list
- 时间: visit all nodes 两遍,  O(n)



---

**5. [Perfect Squares.java](https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Squares.java)**      Level: Medium      Tags: [BFS, DP, Math, Partition DP]
      
给一个数字n, 找到这个数字 最少能用多少个 平方数组成. 

平方数比如: 1, 4, 9, 16 ... etc

#### Partition DP
- 遇到最值, 想到DP.
- 看到分割字眼, 想到分割型 DP. 
- 思考, 如果 j * j = 9, 那么 j = 3 就是最少的一步; 但是如果是10呢? 就会分割成1 + 9 = 1 + j * j 
- 考虑最后的数字: 要是12割个1出来, 剩下11怎么考虑? 割个4出来,剩下8怎么考虑?
- partion的方式: 在考虑dp[i - x]的时候,  x 不是1, 而是 x = j*j.
- 就变成了dp = Min{dp[i - j^2] + 1}

#### 时间复杂度
- 乍一看是O(n*sqrt(n)). 实际也是. 但如何推导?
- 考虑上限: 把小的数字变成大的 推导上限; 考虑下限: 把数字整合归小, 找到下限.
- 考虑sqrt(1) + sqrt(2) + ....sqrt(n):找这个的upper bound and lower bound.
- 最后发现它的两边是 A*n*sqrt(n) <= actual time complexity <= B*n*sqrt(n)
- 那么就是O(n*sqrt(n))啦

#### BFS
- minus all possible (i*i) and calculate the remain
- if the remain is new, add to queue (use a hashset to mark calculated item)
- find shortest path / lowest level number

#### Previous Notes
- 一开始没clue.看了一下提示
- １.　第一步想到了，从数学角度，可能是从最大的perfect square number开始算起。
- ２.　然后想法到了dp， 假设最后一步用了最大的maxSqrNum, 那么就在剩下的 dp[i - maxSqrNum^2] +１　不就好了？
- ３.　做了，发现有个问题．．．最后一步选不选maxSqrNum?  比如12就是个例子。
- 然后就根据提示，想到BFS。顺的。 把1～maxSqrNum 都试一试。找个最小的。
- 看我把12拆分的那个example. 那很形象的就是BFS了。
- 面试时候，如果拆分到这个阶段不确定，那跟面试官陶瓷一下，说不定也就提示BFS了。



---

**6. [Permutation Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Permutation%20Sequence.java)**      Level: Medium      Tags: [Backtracking, Math]
      
TODO: what about regular DFS/backtracking to compute the kth? dfs(rst, list, candiate list, k)

k是permutation的一个数位。而permutation是有规律的。

也就是说，可以根据k的大小来判断每一个数位的字符（从最大数位开始，因为默认factorio从最大数位开始变化）。

于是先求出n!， 然后 k/n!就可以推算出当下这一个数位的字符。然后分别把factorio 和 k减小。

另外, 用一个boolean[] visited来确保每个数字只出现一次。

这个方法比计算出每个permutation要efficient许多。




---

**7. [Orderly Queue.java](https://github.com/awangdev/LintCode/blob/master/Java/Orderly%20Queue.java)**      Level: Hard      Tags: [Math, String]
      



---

**8. [Encode and Decode TinyURL.java](https://github.com/awangdev/LintCode/blob/master/Java/Encode%20and%20Decode%20TinyURL.java)**      Level: Medium      Tags: [Hash Table, Math]
      
其实想到了切入点, 是个可难可简单的题目. 这里的encode就是想办法把url存起来, 然后给个 key.
那么具体怎么做这个key, 简单就可以用一个map, 然后counting. 复杂一点就可以做random letter/number组成key.



---

**9. [Ugly Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Ugly%20Number.java)**      Level: Medium      Tags: [Math]
      
LeetCode: 判断数字是否是ugly number. (definition: factor only have 2, 3, 5)

#### Math
- 看是否可以整除. 
- 看整除最终结果是否== 1

LintCode: 找kth ugly number, 应该与 Ugly Number II是一样的

- 方法1: PriorityQueue排序。用ArrayList check 新的ugly Number是否出现过。
- 方法1-1：(解释不通，不可取)用PriorityQueue排序。神奇的3，5，7走位：按照题目答案的出发，定了3，5，7以什么规律出现。但是题目并没有特殊表明。
- 方法2: DP . Not Done yet.




---

**10. [Fraction to Recurring Decimal.java](https://github.com/awangdev/LintCode/blob/master/Java/Fraction%20to%20Recurring%20Decimal.java)**      Level: Medium      Tags: [Hash Table, Math]
      
TODO: no need of hashMap, just use set to store the existing

不难想到处理除法：考虑正负，考虑小数点前后。主要是小数点以后的要着重考虑。
很容易忽略的是integer的益处。


---

**11. [Friends Of Appropriate Ages.java](https://github.com/awangdev/LintCode/blob/master/Java/Friends%20Of%20Appropriate%20Ages.java)**      Level: Medium      Tags: [Array, Math]
      
#### Array, Math
- 这个问题更在于问题本身的分析 (而且还有多余条件); 最终的for loop 也比较不standard.
- People younger than 15 cannot make requests due to the first rule.
- From the age of 15, people can make requests to the same age: a[i] * (a[i] - 1) requests.
- People can make requests to younger people older than 0.5 * i + 7: a[j] * a[i] requests.
- The third rule is redundant as the condition is already covered by the second rule.
- TODO: the approach.



---

**12. [Power of Two.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Two.java)**      Level: Easy      Tags: [Bit Manipulation, Math]
      
跟powerOfThree一样: 可以loop, check mod; 也可以用binary search找合适的数字.



---

**13. [Number of Digit One.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Digit%20One.java)**      Level: Hard      Tags: [Math]
      
Pure math problem, not quite representative

Explanation
https://leetcode.com/problems/number-of-digit-one/discuss/64381/4+-lines-O(log-n)-C++JavaPython



---

**14. [Cracking the Safe.java](https://github.com/awangdev/LintCode/blob/master/Java/Cracking%20the%20Safe.java)**      Level: Hard      Tags: [DFS, Greedy, Math]
      
#### Greedy, Iterative
- For 2 passwords, the shortest situation is both passwords overlap for n-1 chars.
- We can use a window to cut out last (n-1) substring and append with new candidate char from [k-1 ~ 0]
- Track the newly formed string; if new, add the new char to overall result
- Note: this operation will run for k^n times: for all spots of [0 ~ n - 1] each spot tries all k values [k-1 ~ 0]
- Same concept as dfs

#### DFS
- Same concept: use window to cut out tail, and append with new candidate
- do this for k^n = Math.pow(k, n) times



---

**15. [Ugly Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Ugly%20Number%20II.java)**      Level: Medium      Tags: [DP, Enumeration, Heap, Math, PriorityQueue]
      

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

**16. [Power of Three.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Three.java)**      Level: Easy      Tags: [Math]
      
方法1:
Power of 3:  3 ^ x == n ?
意思是 n / 3 一直除, 最后是可以等于1的, 那么就有了 n/=3, check n%3, 最后看结果是不是整除到1的做法. 用while loop.

方法2:
如果n是power of 3, 那么 3 ^ x的这个 x一定是个比n小的数字. 那么可以在 0 ~ n 之间做binary serach, 但是就比较慢.

方法3:
巧妙的想法.最大的3^x integer是 3^19. 那么找到这个数, 一定可以被n整除. 一步到位.



---

**17. [Basic Calculator.java](https://github.com/awangdev/LintCode/blob/master/Java/Basic%20Calculator.java)**      Level: Hard      Tags: [Binary Tree, Expression Tree, Math, Minimum Binary Tree, Stack]
      
给一个expression String, 要evaluate这个expression的值.

Expression string 里面包括 +, -, 整数, 开合括号, 还有space.

#### Expression Tree
- Expression Tree是一个 weight-based的 min-tree 
- 基于 运算符号 + 数字的 tree: 数字永远在leaf, 然后符号是tree node,  括号不出现在tree里面
- 用 monotonuous stack 来构建这个tree

##### Thinking points
- Understand Expression Tree
- Use stack to build the expression tree + understand the weight system
- Use post-order traversal to evaluate the tree
- 注意, input里面的数字不会是single digit, 所以需要一个buffer存number string
- 整个题目的做法, 可以参照 `Expression Evaluation`



---

**18. [Strobogrammatic Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Strobogrammatic%20Number%20II.java)**      Level: Medium      Tags: [DFS, Enumeration, Math, Sequence DFS]
      
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

**19. [Add Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/Add%20Digits.java)**      Level: Easy      Tags: [Math]
      
方法1: 普通做法就是按照题意, double-while loop把数字加起来. 第一层循环是O(n), 然后第二层循环就少很多数位, overall O(n)

方法2: 找数学规律, 每过9个数字, 取mod就会开始重复, 所以给所有数字取mod 就可以间接找到答案. O(1)



---

**20. [Pow(x, n).java](https://github.com/awangdev/LintCode/blob/master/Java/Pow(x,%20n).java)**      Level: Medium      Tags: [Binary Search, Math]
      
傻做就O(n), 要更好就考虑O(logN).
减少重复计算, 一切两半.

注意:
- n/2的奇数偶数
- n的正负
- n == 0的情况, x == 0, x == 1 的情况.


---

**21. [Number Of Corner Rectangles.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20Of%20Corner%20Rectangles.java)**      Level: Medium      Tags: [DP, Math]
      
具体看题目: count # of valid rectangles (four corner are 1) in a grid[][].

#### basic thinking + Math
- Fix two rows and count matching columns
- Calculate number rectangles with `combination` concept:
- total number of combinations of pick 2 points randomly: count * (count - 1) / 2

#### DP
- TODO. HOW?

#### Brutle
- O(m^2 * n^2), times out



---

**22. [Excel Sheet Column Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Excel%20Sheet%20Column%20Number.java)**      Level: Easy      Tags: [Math]
      
#### Math
- 26位的运算, 根据10位运算去思考
- 'A' - 'A' = 0. 所以 char - 'A' + 1 = 题目里的对应数位
- 或者: 26位运算和10位一样:num += 每位的digit * Math.pow(26, 数位号)




---

**23. [360. Sort Transformed Array.java](https://github.com/awangdev/LintCode/blob/master/Java/360.%20Sort%20Transformed%20Array.java)**      Level: Medium      Tags: [Math, Two Pointers]
      

#### Two Pointers, Math
- Being able to analys the a*x^2 + b*x graph and find the `peak` or `valley`
- Math basics: x^2 dominates the overall curve so it is up to a to determine:
    - `valley`: if a < 0, both sides will be small and center will be large. Prioritize larger value.
    - `peak`: if a > 0, center will be small and both sides will be large. Prioritize smaller value.
    - starting index being 0 or n-1, is driven by `a`



---

**24. [415. Add Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/415.%20Add%20Strings.java)**      Level: Easy      Tags: [Basic Implementation, Math, String]
      

#### Two Pointer 
- Use i, j to process from end of 2 strings
- handle edge case for i, j
    - if i < 0, its num = 0 (since we are doing sum, blindly setting 0 is okay)
- Note: `sb.insert(0, x)` is much slower than doing a final `sb.reverse()`

#### If manually convertin to int[]
1. when converting to int[], remember to reverse string.
1. when converting to int[], remember to reserve extra space for carry



---

**25. [7. Reverse Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/7.%20Reverse%20Integer.java)**      Level: Easy      Tags: [Math]
      

#### 方法1
每次加上x%10，然后x不断减小～0
注意处理MAX_VALUE, MIN_VALUE
符号不重要, 直接处理, 也会保留.

#### 方法2
转换成String 然后 reverse
Space O(n), time O(n)



---

**26. [204. Count Primes.java](https://github.com/awangdev/LintCode/blob/master/Java/204.%20Count%20Primes.java)**      Level: Easy      Tags: [Hash Table, Math]
      
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

**27. [509. Fibonacci Number.java](https://github.com/awangdev/LintCode/blob/master/Java/509.%20Fibonacci%20Number.java)**      Level: Easy      Tags: [DP, Math, Memoization]
      
#### Memoization
- fib[n] = fibonacci(n - 1) + fibonacci(n - 2);

#### DP array.
- 滚动数组, 简化DP

#### recursively calculate
- recursively calculate fib(n - 1) + fib(n - 2). 公式没问题, 但是时间太长, timeout.




---

**28. [67. Add Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/67.%20Add%20Binary.java)**      Level: Easy      Tags: [Math, String, Two Pointers]
      
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

**29. [168. Excel Sheet Column Title.java](https://github.com/awangdev/LintCode/blob/master/Java/168.%20Excel%20Sheet%20Column%20Title.java)**      Level: Easy      Tags: [Math]
      

#### 基本换算
- 26位, 像10位一样去思考
- 从末尾开始, mod %26 可以拿到 末尾数字 remain = n % 26
- 特殊: remain = 0 的时候, 也就是说n是26的倍数, 末尾应该是 'Z'
- 记录'Z'了之后, n--




---

**30. [9. Palindrome Number.java](https://github.com/awangdev/LintCode/blob/master/Java/9.%20Palindrome%20Number.java)**      Level: Easy      Tags: [Math]
      
#### Reverse half of the number
- build reversed integer 直到midpoint, where x <= reverse
- 如果input双数: x == reverse
- 如果input单数 (而且x>reverse): x == reverse/10

#### Consider palindrome
- optionA: compare digit by digit
- optionB: reverse half of the string/int, and compare with other half.






---

**31. [43. Multiply Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/43.%20Multiply%20Strings.java)**      Level: Medium      Tags: [Math, String]
      

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

**32. [367. Valid Perfect Square.java](https://github.com/awangdev/LintCode/blob/master/Java/367.%20Valid%20Perfect%20Square.java)**      Level: Easy      Tags: [Binary Search, Math]
      

#### Binary找sqrt
- binary search template: mid+1, mid-1, `start <= end`
- define index as long. 



---

**33. [246. Strobogrammatic Number.java](https://github.com/awangdev/LintCode/blob/master/Java/246.%20Strobogrammatic%20Number.java)**      Level: Easy      Tags: [Enumeration, Hash Table, Math, Two Pointers]
      

根据题意枚举, 再根据规则basic implementation

#### HashTable + Two Pointer
- compare left/right

#### Alter input
- flip number (6 and 9), and then reverse the string, see if the string is the same.
- takes more




---

**34. [319. Bulb Switcher.java](https://github.com/awangdev/LintCode/blob/master/Java/319.%20Bulb%20Switcher.java)**      Level: Medium      Tags: [Brainteaser, Math]
      

#### Brainteaser
- https://leetcode.com/problems/bulb-switcher/discuss/77104/Math-solution..

#### Brutle:
- if just impl, it take O(n^2):
- repating: some pos are toggled mutiple times: if we know total times, easy to determin each pos.
- loop over [2, n], count times on each index



---

**35. [12. Integer to Roman.java](https://github.com/awangdev/LintCode/blob/master/Java/12.%20Integer%20to%20Roman.java)**      Level: Medium      Tags: [Basic Implementation, Math, String]
      

#### String, Basic implementation
- Parse each digit based on rules
- 1) parse: analyze the situations


---

**36. [202. Happy Number.java](https://github.com/awangdev/LintCode/blob/master/Java/202.%20Happy%20Number.java)**      Level: Easy      Tags: [Hash Table, Math]
      

Basic Implementation of the requirements.

用HashSet存查看过的数值。若重复，return false.



---

**37. [69. Sqrt(x).java](https://github.com/awangdev/LintCode/blob/master/Java/69.%20Sqrt(x).java)**      Level: Easy      Tags: [Binary Search, Math]
      
#### sqrt(int x)
- 理解题意, 从[0, x]找一个可以m*m=x的值.
- 注意, 如果找不到, 最后问考官该return一个什么值：按道理，因为return int, 会取整，那么return一个平方最close to x就可以.
- 注意 mid 用 long, 因为很可能超过最大int.

#### sqrt(double x)
- 二分float number, 应该用精度来定义结尾.
- 还是二分, 但是判断条件变成: while ( end - start > eps)
- eps = 1e-12,也就是精度到1e-12



---

**38. [2. Add Two Numbers.java](https://github.com/awangdev/LintCode/blob/master/Java/2.%20Add%20Two%20Numbers.java)**      Level: Medium      Tags: [Linked List, Math]
      

LinkedList都已经反转好了，直接做. 跟Add Binary的理解方式一模一样.

#### Math, Linked List
- reverse order helps calculation
    - add additional carry to end
    - not same length: align on left
- traverse till both ends
- 遍历两个l1,l2把carry-on处理好，每次生成一个新node，最后检查carry-on.




---

**39. [273. Integer to English Words.java](https://github.com/awangdev/LintCode/blob/master/Java/273.%20Integer%20to%20English%20Words.java)**      Level: Hard      Tags: [Enumeration, Math, String]
      

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

**40. [523. Continuous Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/523.%20Continuous%20Subarray%20Sum.java)**      Level: Medium      Tags: [Coordinate DP, DP, Math, PreSum, Subarray]
      

给一个非负数的数列和数字k(可正负, 可为0). 找到连续子序列(长度超过2), 使得这个subarray的sum 是 k的倍数. 问: 是否可能?

#### Method1: Validate Mod Result
- Check if mod result exist in earlier preSum
- Utilize `Pigeonhole principle` to optimize:
    - 1) put positive integers into k slots
    - 2) when # of integers > 2*k, then there must be a range sum that is multipler of k
    - more illustration here: https://leetcode.com/problems/continuous-subarray-sum/solution/
- Draw the presum and try to take mod of each presum and save to set, we realize
    - 1) assume a mod result = 7, and we mark it in the set
    - 2) some time later, after summing up more values, (7 + x + y ...+ z) % k == 7
        - it means `(x + y ...+ z) % k == 0`
        - There is a `整除` exist; return true
- Meanwhile, if we want to record the list of indexes, we can use a Map rather than set.
- Note: if all we do to the presum is to % k, therefore `preSum % k` can represent `presum` in some cases.
- time: O(n)
- space: O(k), size restrited by mod result of `%k`


#### Method2: DP, PreSum
- PreSum[]: 
    - 1) cal preSum array
    - 2) preSum(i, j) = continuous range sum
    - 3) determine if `preSum(i, j) % k == 0`
- time: O(n^2)
- DP (坐标型. specifically, preSum[])
    - 记录在0 ~ i点(包括nums[i], 以nums[i]结尾)的sum, 坐标型动态规划.
    - dp[i] = dp[i - 1] + nums[i];

#### Method3: 直接算结果
- 从sum = 每次[i ~ j]的所有情况
- time: O(n^2)
- space: O(1)



---

**41. [65. Valid Number.java](https://github.com/awangdev/LintCode/blob/master/Java/65.%20Valid%20Number.java)**      Level: Hard      Tags: [Enumeration, Math, String]
      

分析edge case, 和各种情况, 然后判别是否是valid number

#### 情况总结
- 遇到 `.`, `e`, `+/-`, `int`的几种不同情况
- 分别遇到的顺序不同时候, 结果也不同.
- 这道题更多是分析情况, 然后把edge case enumerate出来, 算法的意义比较少.



---

**42. [8. String to Integer (atoi).java](https://github.com/awangdev/LintCode/blob/master/Java/8.%20String%20to%20Integer%20(atoi).java)**      Level: Medium      Tags: [Math, String]
      

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

**43. [149. Max Points on a Line.java](https://github.com/awangdev/LintCode/blob/master/Java/149.%20Max%20Points%20on%20a%20Line.java)**      Level: Hard      Tags: [Array, Geometry, Hash Table, Math]
      

给list of (x,y) coordinates. Determine  # of points on the same line

#### Observation
- If given n points, we can calculate all possible slopes. O(n^2) times
- For the two dots that generates the same slope, these dots could be on **parallel** slopes
- figure out how to prune the parallel dots

#### Trick: prune parallel dots using greatest common divider
- GCD: greatest common divider
- Devide the x and y by their greatest common divider, such that x and y can be reduced to minimum value
- All other x and y can be reduced to such condition as well
- track the final reduced (x,y) in a map: they are the key to the count
- No need to use Map<Integer, Map<Integer, Integer>> to perform 2 level mapping; just `map<String, Integer>`, where the key is "x@y"



---

**44. [13. Roman to Integer.java](https://github.com/awangdev/LintCode/blob/master/Java/13.%20Roman%20to%20Integer.java)**      Level: Easy      Tags: [Math, String]
      

#### String 
- 熟悉罗马字母规则     
- 1. 'I V X L C D M' 分别代表的数字     
- 2. 列举combo的情况，需要从原sum里面减掉多加的部分: 'IV, IX'减2, 'XL, XC'减20, 'CD, CM'减200. 
- Leading `I(1*2)`, `X(10*2)`, `C(100*2)` causes double counting 

https://en.wikipedia.org/wiki/Roman_numerals

#### use map to store combinations




---

