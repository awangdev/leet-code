 
 
 
## Edge Case (2)
**0. [686. Repeated String Match.java](https://github.com/awangdev/LintCode/blob/master/Java/686.%20Repeated%20String%20Match.java)**      Level: Easy      Tags: [Basic Implementation, Edge Case, String]
      
Track: 纸上分析edge case.
Validation helps speed it up.



---

**1. [41. First Missing Positive.java](https://github.com/awangdev/LintCode/blob/master/Java/41.%20First%20Missing%20Positive.java)**      Level: Hard      Tags: [Analysis, Array, Edge Case]
      

给一串无序数字, 有负数: 找这个array里面第一个 missing的 positive integer

missing positive integer 其实是以 [1, n] 来做比较的.

#### Array分析, index 技巧
- 用while loop, 不断地尝试把 number 送到该放的地方
- 如果 index = nums[i] 超过了nums.length, 当然就不移动了
- 注意: 检查 val != nums[val], avoid infinitely loop
- 检验: nums[i] 是否等于 i, 如果不对, 就找到了结果

#### Edge Case
1. 如果nums==null, 其实missing positive integer 自然而然是 1
1. 有可能这串数字里没有断开的integer, 但是最大的integer在首位 (因为index超标, 无法被放到正确的地方)
    - 这种时候, n被放在 index 0, 其实就是说, 下一个integer应该是 n + 1
1. 最终, 如果array本来就是完全sorted, 也不缺, 还符合角标的条件, 那么唯一下一个就是array范围外的第一个positive number: n



---

