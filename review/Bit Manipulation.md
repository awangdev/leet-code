 
 
 
## Bit Manipulation (16)
**0. [O(1) Check Power of 2.java](https://github.com/awangdev/LintCode/blob/master/Java/O(1)%20Check%20Power%20of%202.java)**      Level: Easy      Tags: [Bit Manipulation]
      



---

**1. [Single Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Single%20Number%20II.java)**      Level: Medium      Tags: [Bit Manipulation]
      

一串数字里面, 所有数字都重复三次, 除了一个数字. 找到这个数字, linear time, without extrace space (constant space)

TODO: bits



---

**2. [Single Number III.java](https://github.com/awangdev/LintCode/blob/master/Java/Single%20Number%20III.java)**      Level: Medium      Tags: [Bit Manipulation]
      

TODO: wut?


---

**3. [Power of Two.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Two.java)**      Level: Easy      Tags: [Bit Manipulation, Math]
      

跟powerOfThree一样: 可以loop, check mod; 也可以用binary search找合适的数字.



---

**4. [Sum of Two Integers.java](https://github.com/awangdev/LintCode/blob/master/Java/Sum%20of%20Two%20Integers.java)**      Level: Easy      Tags: [Bit Manipulation]
      

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

**5. [Swap Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Swap%20Bits.java)**      Level: Easy      Tags: [Bit Manipulation]
      

简单, 但是很多知识点:
1. Hex 0xaaaaaaaa 是1010101....1010; 0x55555555 是01010101....0101
2. 可以用这两个hex取单数和负数. 如果需要取其他的pattern, 也可以做.
3. x很可能是negative number, 所以right-shift 要用logic shift, >>> 避免leading负数补位.



---

**6. [Update Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Update%20Bits.java)**      Level: Medium      Tags: [Bit Manipulation]
      

熟悉bits的一些trick:
- ~0 = -1 = 111111...11111111 (32-bit)
- Create mask by shifting right >>>, and shifting left
- Reverse to get 0000...11110000 format mask
- & 0000 = clean up; | ABC = assign ABC



---

**7. [Maximum XOR of Two Numbers in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20XOR%20of%20Two%20Numbers%20in%20an%20Array.java)**      Level: Medium      Tags: [Bit Manipulation, Trie]
      

比较难想到. 利用到XOR性质A^B=C, then A=B^C.
1. 枚举可能的A, 2. 然后一个个猜.

1. 枚举A: 因为求MAX肯定是找leading-1最多的数字, 那么枚举A从(1000000...000)开始, 
每次多一位取1或者0
2. 因为枚举A的时候是按照每个bit来, 那么B和C也要以同样数位出现.
这里吧B和C变成了prefix的形式, 放在了set里面. 
跟2sum用hashmap的思想类似, 每次用枚举的 A^B=C, 看看结果C是否已经在set里面. 
如果在, 证明枚举的A可能被B^C得出, 那么就找到了一种情况.

还用到一些技巧: 
mask = (1 << i); // i位mask
mask = mask | (1 << i); // prefix mask



---

**8. [Majority Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Element.java)**      Level: Easy      Tags: [Array, Bit Manipulation, Divide and Conquer]
      

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

**9. [Convert Integer A to Integer B.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Integer%20A%20to%20Integer%20B.java)**      Level: Easy      Tags: [Bit Manipulation]
      

把Integer A 转换成 Integer B 需要改变多少bits?

#### Bit Manipulation
- a^b 显示出bit format里面有不同binary code的数位.
- 每次 (a^b)>>i 移动i位之后, 再 & 1时其实是指留下这一位的数字.
- count 
- 其实用到了 ^ 找不同的bit, >> 移位, &1 mask



---

**10. [Count 1 in Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%201%20in%20Binary.java)**      Level: Easy      Tags: [Bit Manipulation]
      

count 一个 32-bit number binary format 里面有多少1

#### Bit Manipulation
- shift >> i 
- apply mask & 1

#### Convert to string O(n) space
可以把integer -> string -> char array.



---

**11. [Counting Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Counting%20Bits.java)**      Level: Medium      Tags: [Bit Manipulation, Bitwise DP, DP]
      

给一个数组, 算里面有多少bit 1. 

#### Bitwise DP
- 对于每一个数字, 其实很简单就能算出来: 每次 >>1, 然后 & 1 就可以count 1s. Time: 一个数字可以 >>1 O(logN) 次
- 现在要对[0 ~ num] 都计算, 也就是N个数字, 时间复杂度: O(nLogN).
- 用DP来优化, 查找过的number的1s count, 存下来在 dp[number]里面.
- 计算你顺序从 0 -> num, count过的数字就可以重复利用.
- Bit题目 用num的数值本身表示DP的状态.
- 这里, dp[i] 并不是和 dp[i-1]有逻辑关系; 而是dp[i] 和dp[i>>1], 从binary representation看出有直接关系.



---

**12. [Missing Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Missing%20Number.java)**      Level: Easy      Tags: [Array, Bit Manipulation, Math]
      

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

**13. [Binary Representation.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Representation.java)**      Level: Hard      Tags: [Bit Manipulation, String]
      

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

**14. [Subset.java](https://github.com/awangdev/LintCode/blob/master/Java/Subset.java)**      Level: Medium      Tags: [Array, BFS, Backtracking, Bit Manipulation, DFS]
      
time: O(2^n)
space: O(2^n)

给一串unique integers, 找到所有可能的subset. result里面不能有重复.

#### DFS
- dfs的两种路子: 1. pick&&skip dfs, 2. for loop dfs
- 1. pick&&skip dfs: 取或者不取 + backtracking. 当level/index到底，return 一个list. Bottom-up, reach底部, 才生产第一个solution.
- 2. for loop dfs: for loop + backtracking. 记得：做subset的时候, 每个dfs recursive call是一种独特可能，先加进rst.  top-bottom: 有一个solution, 就先加上.
- Time&&space: subset means independent choice of either pick&&not pick. You pick n times: `O(2^n)`, 3ms

#### Bit Manipulation
- n = nums.length, 那么在每一个index, 都是 pick / not pick: 0/1
- 考虑subset index 0/1的bit map: range 的就是 [0000...00 ~ 2^n-1]
- 每一个bitmap就能展现出一个subset的内容: all the 1 represents picked indexes
- 做法:
- 1. 找出Range
- 2. 遍历每一个bitmap candidate
- 3. 对每一个integer 的 bit representation 遍历, 如果是1, add to list
- time: O(2^n * 2^n) = O(4^n), still 3ms, fast.

#### Iterative, BFS
- Regular BFS, 注意考虑如果让one level to generate next level
- 1. 用queue来存每一次的candidate indexes
- 2. 每一次打开一层candiates, add them all to result
- 3. 并且用每一轮的candidates, populate next level, back into queue.
- should be same O(2^n), but actual run time 7ms, slower





---

**15. [Total Hamming Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/Total%20Hamming%20Distance.java)**      Level: Medium      Tags: [Bit Manipulation]
      
time: O(n)
space: O(1), 32-bit array

给出Hamming Distance定义(bit format时候有多少binary diff), 求一串数字的hamming distance总和.

#### Bit Manipulation
- Bit题: 考验 bit >>, mask & 1, 还有对题目的理解能力
- Put integers in binary, and compare each column:
- for each `1`, ask: how many are different from me? all the `0`
- `# of diffs at each bit-column = #ofZero * #ofOne `
- 1. countZero[], countOne[]; 2. loop over nums and populate the two array

##### 注意雷点
- 问清楚: 10^9 < 2^31, we are okay with 32 bits
- `最终的hamming distance 要从 [1 ~ 32] 哪个bit开始算起`? 取决于 `最长`的那个binary format: 但不用先去找bit length
- 在做countZero, countOne时候, 都做32-bit; 最终做乘积的时候, 如果 `1` 或者 `0` 个数为零, 乘积自然为0.




---

