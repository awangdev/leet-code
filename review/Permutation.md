 
 
 
## Permutation (1)
**0. [Shuffle an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Shuffle%20an%20Array.java)**      Level: Medium
      

像shuffle music 一样, 做一套shuffle array的functions: 

shuffle() 给出random的permutation

reset() 给出最初的nums

#### Permutation
- Permutation 实际上就是在list/array/... 上面给元素换位置
- 硬换位置, 每次换的位置不同, 用random来找到要换的index
- 维持同一个random seed
- O(n)

##### Note
- compute all permutations 太慢, 不可行.



---

