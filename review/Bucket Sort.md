 
 
 
## Bucket Sort (4)
**0. [448. Find All Numbers Disappeared in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/448.%20Find%20All%20Numbers%20Disappeared%20in%20an%20Array.java)**      Level: Easy      Tags: [Array, Bucket Sort]
      

#### Method1: Bucket Sort concept, set val to its correct position
- Given: values are [1,n], so val can represent index. Therefore, set val to its correct position
- 小心handle i:
    - value是 1-based
    - 每次换位, 需要`i--`, 重新省察`nums[i]`

#### Method2: 做标记 (negative number, or super large number)
- Option1: use negative number to mark visited:
    - 很巧妙地运用了标记的方法, 标记成负数，证明visit过。
    - Preserve原数的负数，这样可以继续用此负数的绝对值来寻找原数所该被定的位置。非常巧妙！
- Option2: use large number (larger than n)
    - 跟方法2类似，也是做标记，这一次是加上一个大于n的数（因为题目给了n的border），最后check一下就好。为不超Integer.MAX_VALUE, 每次加n前，取余数。
    - 做标记的方法固然快，但是相对来说比较hacky，在常规的代码中，估计不会用到.




---

**1. [1048. Longest String Chain.java](https://github.com/awangdev/LintCode/blob/master/Java/1048.%20Longest%20String%20Chain.java)**      Level: Medium      Tags: [Bucket Sort, DP, Hash Table, Sort]
      

#### Hash table, DP
- store `Map<Word, Longest Chain Length>`
- sort all words, try from short to long: short word will be calculated first to serve later words as candidate
- time: O(nlogn)
- space: O(n)

#### Hash Table, Bucket Sort,DP
- store `Bucket: List[17] of words`, given word size limit [0 ~ 16]
- time: O(n)
- space: O(n)



---

**2. [1057. Campus Bikes.java](https://github.com/awangdev/LintCode/blob/master/Java/1057.%20Campus%20Bikes.java)**      Level: Medium      Tags: [Bucket Sort, Greedy, PriorityQueue, Sort]
      

#### Method1: PriorityQueue
- PQ can be used to sort on multiple attributes
- follow the specified rules, and build all possible pairs of visits vs. bike. Pair {int dist, workerIndex, bikeIndex}
- PQ to sort them
    - first by dist
    - if same dist, sort by workerIndex
    - if same workderIndex, sort by bikeIndex
- process all candidates, and skip the ones (workers/bikes) visited 

#### Method2: Bucket Sort
- Similar to using PQ: the goal is to find: 1) min dist; 2) closer worker index, 3) closer bike index
- can use bucket sort to hold all possible distances [0 ~ 2000]: bucket[List of pairs]
    - do a hard iteration (ordered access from min dist). 
- time: O(mn), no need to sort
- space: O(mn)



---

**3. [274.H-Index.java](https://github.com/awangdev/LintCode/blob/master/Java/274.H-Index.java)**      Level: Medium      Tags: [Bucket Sort, Hash Table, Sort]
      

找到h-index, 给的citation int[] 并不是sorted. h-index 的definition 具体看题目.

#### Sort, find h from end
- 例子写出来，发现可以sort以后按照定义搜索一遍。 nlogn.
- 搜索一遍时候可以优化，用binary search. 但是没意义，因为array.sort已经用了nlogn
- 题目给的规则, 从小到大排序后: 剩下的 paper `n-h`, 全部要 <= h 个 citation.
- time O(nlogn), search O(n)

##### 正向思考
- 从i = 0 开始找第一个 `citations[i] >= h`, 就是第一个符合 h-index 规则的paper, return h

##### 反向思考
- 如果从 h = n, 每次h--; 那么 `x = n - h` 就是从 `[0 ~ n)` 开始找第一个 `dictations[x] >= h`, 就是结果
- 同时, `dictations[x-1]` 就是最后一个(dictation最多的)其余的paper.

#### Couting Sort / Bucket Sort
- O(n)
- Bucket sort的思想(更像是counting sort?): 过一遍 input, 把dictation value 作为 index, 分布在bucket[index]上++
- bucket[x] 是 count when # of citation == x. 
- 如果 x 大于 n的时候, 就超出了index范围, 但是刚好这个问题可以包容, 把这样的情况记位在bucket[n]就可以
- 巧妙: `sum += bucket[h]` where `h = [n ~ 0]` 利用了h-index的definition:
- #of papers (sum of bucket[n]...bucket[0]) has more than h cidations 
- 这里运用到了bucket sort的思想, 但是并不是sorting, 而h-index的定义运用的很巧妙.
- Read more about actual bucket sort: https://en.wikipedia.org/wiki/Bucket_sort

#### More about Counting Sort
1. Application: for number/character range
1. Steps:
	- Find range, define countArray
	- Count element and record in the array
	- PreSum the countArray
	- Start from beginning of the array, `print & decrese count` to produce the sorted elements




---

