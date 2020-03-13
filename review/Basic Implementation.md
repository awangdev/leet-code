 
 
 
## Basic Implementation (18)
**0. [Count and Say.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20and%20Say.java)**      Level: Easy      Tags: [Basic Implementation, String]
      
介绍一种count数字的方法, 然后每一行读出上一行的结果, 一行一行推算. 问nth行是啥样?

#### Basic Implementation
- 主要是题意很难理解, 非常misleading, 等到看明白题目, 其实没有什么算法要求.
- Count duplicates and print



---

**1. [Cosine Similarity.java](https://github.com/awangdev/LintCode/blob/master/Java/Cosine%20Similarity.java)**      Level: Easy      Tags: [Basic Implementation]
      
根据 Cosine Similarity 的公式, basic implementation



---

**2. [Next Closest Time.java](https://github.com/awangdev/LintCode/blob/master/Java/Next%20Closest%20Time.java)**      Level: Medium      Tags: [Basic Implementation, Enumeration, String]
      
给一个时间string"12:09", 用里面的4个integer组合成其他时间string, 目标找最小的next time.

如果组合出的time string 在input time之前, 默认 + 24 hours.

#### String
- enumerate all candidates and filter to keep the correct ones
- String.compareTo(string) -> gives lexicographical comparision



---

**3. [788. Rotated Digits.java](https://github.com/awangdev/LintCode/blob/master/Java/788.%20Rotated%20Digits.java)**      Level: Easy      Tags: [Basic Implementation, String]
      

#### Basic Implementation of the rules
- [3,4,7] -> cannot rotate, failures. Must NOT have. set1
- 2,5,6,9 -> good candidates. Must have 1. set2
- [0,1,8] -> goes back to itself. can have
- loop over [1, N], count=int[10] appearance.
    - set1 meet 0
    - set2 meet at least 1
    


---

**4. [849. Maximize Distance to Closest Person.java](https://github.com/awangdev/LintCode/blob/master/Java/849.%20Maximize%20Distance%20to%20Closest%20Person.java)**      Level: Easy      Tags: [Array, Basic Implementation, Two Pointers]
      

给一排座位, 一个人去坐: 找离两边的人都最远的地方(中间点), return 跟旁边人的最大distance

是Exam Room 的同种概念, 简单化题目: 这里只考虑一个人就好了

#### Basic Implementation, Two Pointers: start/end
- start/end point, 然后比较大小记录dist
    - 注意1: 如果第一个座位没有人, 特殊处理, dist = [0 ~ end]
    - 注意2: 如果最后一个座位没有人, 特殊处理: dist = [n - 1 - start];
- 其余: `dist = Math.max(dist, (end - start) / 2)`
- 相关题目: 几乎同样概念 `Binary Gap`, 升级复杂版`Exam Room`




---

**5. [408. Valid Word Abbreviation.java](https://github.com/awangdev/LintCode/blob/master/Java/408.%20Valid%20Word%20Abbreviation.java)**      Level: Easy      Tags: [Basic Implementation, String]
      
tricky: find integer within a string
edge case: leading '0' should not be allow in such abbr.



---

**6. [415. Add Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/415.%20Add%20Strings.java)**      Level: Easy      Tags: [Basic Implementation, Math, String]
      

#### Two Pointer 
- Use i, j to process from end of 2 strings
- handle edge case for i, j
    - if i < 0, its num = 0 (since we are doing sum, blindly setting 0 is okay)
- Note: `sb.insert(0, x)` is much slower than doing a final `sb.reverse()`

#### If manually convertin to int[]
1. when converting to int[], remember to reverse string.
1. when converting to int[], remember to reserve extra space for carry



---

**7. [1108. Defanging an IP Address.java](https://github.com/awangdev/LintCode/blob/master/Java/1108.%20Defanging%20an%20IP%20Address.java)**      Level: Easy      Tags: [Basic Implementation, String]
      


---

**8. [383. Ransom Note.java](https://github.com/awangdev/LintCode/blob/master/Java/383.%20Ransom%20Note.java)**      Level: Easy      Tags: [Basic Implementation, String]
      
count chars in int[256]



---

**9. [686. Repeated String Match.java](https://github.com/awangdev/LintCode/blob/master/Java/686.%20Repeated%20String%20Match.java)**      Level: Easy      Tags: [Basic Implementation, Edge Case, String]
      
Track: 纸上分析edge case.
Validation helps speed it up.



---

**10. [485. Max Consecutive Ones.java](https://github.com/awangdev/LintCode/blob/master/Java/485.%20Max%20Consecutive%20Ones.java)**      Level: Easy      Tags: [Array, Basic Implementation]
      

- preserve max
- 清零count 



---

**11. [824. Goat Latin.java](https://github.com/awangdev/LintCode/blob/master/Java/824.%20Goat%20Latin.java)**      Level: Easy      Tags: [Basic Implementation, String]
      



---

**12. [119. Pascal's Triangle II.java](https://github.com/awangdev/LintCode/blob/master/Java/119.%20Pascal's%20Triangle%20II.java)**      Level: Easy      Tags: [Array, Basic Implementation]
      

简单处理 list. code is very similar to Pascal triangle I.

- 注意 `list = Arrays.asList(x, y, z ...)` 给fixed-size list, 不能直接 list.add().
- Use `new ArrayList<>(Arrays.asList(...))` to wrap it up.




---

**13. [443. String Compression.java](https://github.com/awangdev/LintCode/blob/master/Java/443.%20String%20Compression.java)**      Level: Easy      Tags: [Basic Implementation, String]
      


---

**14. [12. Integer to Roman.java](https://github.com/awangdev/LintCode/blob/master/Java/12.%20Integer%20to%20Roman.java)**      Level: Medium      Tags: [Basic Implementation, Math, String]
      

#### String, Basic implementation
- Parse each digit based on rules
- 1) parse: analyze the situations


---

**15. [893. Groups of Special-Equivalent Strings.java](https://github.com/awangdev/LintCode/blob/master/Java/893.%20Groups%20of%20Special-Equivalent%20Strings.java)**      Level: Easy      Tags: [Basic Implementation, String]
      
Mark # of characters can be useful to print string signature



---

**16. [118. Pascal's Triangle.java](https://github.com/awangdev/LintCode/blob/master/Java/118.%20Pascal's%20Triangle.java)**      Level: Easy      Tags: [Array, Basic Implementation, List]
      



---

**17. [1033. Moving Stones Until Consecutive.java](https://github.com/awangdev/LintCode/blob/master/Java/1033.%20Moving%20Stones%20Until%20Consecutive.java)**      Level: Easy      Tags: [Basic Implementation, Sort]
      

#### Analyze to understand
- put 3 elements into array, sort and follow below rules:
- min: 
    - if 3 elements consecutive, 0 move.
    - if only 1 pair of the two elemnets consecutive or if they have 1 slot in between, it needs exactly 1 move
    - otherwise, at most 2 moves
- max: # of open slots between them (high - low + 1) - n, where n = 3
- Follow up: `1040. Moving Stones Until Consecutive` is more interesting with special rulese (cannot move to `ending spot`), and it uses sliding window concept



---

