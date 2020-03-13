 
 
 
## Monotonous Stack (3)
**0. [Largest Rectangle in Histogram.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Rectangle%20in%20Histogram.java)**      Level: Hard      Tags: [Array, Monotonous Stack, Stack]
      
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

**1. [402. Remove K Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/402.%20Remove%20K%20Digits.java)**      Level: Medium      Tags: [Greedy, Monotonous Stack, Stack]
      

#### Monotonous Stack (Increasing)
- Greedy: Remove 1) earlier digits(数位靠前权值大), 2) large digits
- Keep a increasing stack that:
    - use stack.peek() to guard incoming digit
    - if peek is larger than incoming digit, continue `stack.pop()`
- Result: monotonous increasing stack. Print it in correct order.




---

**2. [739. Daily Temperatures.java](https://github.com/awangdev/LintCode/blob/master/Java/739.%20Daily%20Temperatures.java)**      Level: Medium      Tags: [Hash Table, Monotonous Stack, Stack]
      

#### Method1: Monotonous Stack
- Goal: given a index i, want right-side closest & higer number
- Draw example: right-most number at base, and builds up monotonous stack (mountain shape)
    - add smaller item on top of stack
    - keep popping if peek is higher than incoming
- space: O(n), time:O(n)

#### Method2: `Map <fixed value(temperature), Index>`, kinda of like bucket sort
- Refernece: https://leetcode.com/problems/daily-temperatures/solution/
- From right side: 
    - 1) record tempIndex[currTemp] = i; 
    - 2) Brutle find smallest temp index in range [currTemp + 1, 100] and record as result


---

