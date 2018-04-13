 
 
 
## Bit Manipulation (9)
**0. [Power of Two.java](https://github.com/awangdev/LintCode/blob/master/Java/Power%20of%20Two.java)**      Level: Easy
      

跟powerOfThree一样: 可以loop, check mod; 也可以用binary search找合适的数字.



---

**1. [Sum of Two Integers.java](https://github.com/awangdev/LintCode/blob/master/Java/Sum%20of%20Two%20Integers.java)**      Level: Easy
      

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

**2. [Swap Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Swap%20Bits.java)**      Level: Easy
      

简单, 但是很多知识点:
1. Hex 0xaaaaaaaa 是1010101....1010; 0x55555555 是01010101....0101
2. 可以用这两个hex取单数和负数. 如果需要取其他的pattern, 也可以做.
3. x很可能是negative number, 所以right-shift 要用logic shift, >>> 避免leading负数补位.



---

**3. [Update Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Update%20Bits.java)**      Level: Medium
      

熟悉bits的一些trick:
- ~0 = -1 = 111111...11111111 (32-bit)
- Create mask by shifting right >>>, and shifting left
- Reverse to get 0000...11110000 format mask
- & 0000 = clean up; | ABC = assign ABC



---

**4. [Maximum XOR of Two Numbers in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20XOR%20of%20Two%20Numbers%20in%20an%20Array.java)**      Level: Medium
      

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

**5. [Majority Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Element.java)**      Level: Easy
      

方法1: Vote 计数, vote++, vote--到最后剩下的就是winner. Time O(n), Space O(1)
Majority Number是指超半数. 超半数的数字, 最后都会至少有vote>=1: match current majority number，vote++；if not, vote--. 
注意：assume valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。

方法2: HashMap count occurance. Time, Space: O(n)

方法3: Bit manipulation. 还没有做.

Related Problems:
Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。

Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。



---

**6. [Convert Integer A to Integer B.java](https://github.com/awangdev/LintCode/blob/master/Java/Convert%20Integer%20A%20to%20Integer%20B.java)**      Level: Easy
      

把Integer A 转换成 Integer B 需要改变多少bits?

#### Bit Manipulation
- a^b 显示出bit format里面有不同binary code的数位.
- 每次 (a^b)>>i 移动i位之后, 再 & 1时其实是指留下这一位的数字.
- count 
- 其实用到了 ^ 找不同的bit, >> 移位, &1 mask



---

**7. [Count 1 in Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%201%20in%20Binary.java)**      Level: Easy
      

count 一个 32-bit number binary format 里面有多少1

#### Bit Manipulation
- shift >> i 
- apply mask & 1

#### Convert to string O(n) space
可以把integer -> string -> char array.



---

**8. [Counting Bits.java](https://github.com/awangdev/LintCode/blob/master/Java/Counting%20Bits.java)**      Level: Medium
      

给一个数组, 算里面有多少bit 1. 

#### Bitwise DP
- 对于每一个数字, 其实很简单就能算出来: 每次 >>1, 然后 & 1 就可以count 1s. Time: 一个数字可以 >>1 O(logN) 次
- 现在要对[0 ~ num] 都计算, 也就是N个数字, 时间复杂度: O(nLogN).
- 用DP来优化, 查找过的number的1s count, 存下来在 dp[number]里面.
- 计算你顺序从 0 -> num, count过的数字就可以重复利用.
- Bit题目 用num的数值本身表示DP的状态.
- 这里, dp[i] 并不是和 dp[i-1]有逻辑关系; 而是dp[i] 和dp[i>>1], 从binary representation看出有直接关系.



---

