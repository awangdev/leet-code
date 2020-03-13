 
 
 
## Moore Voting (2)
**0. [169. Majority Element.java](https://github.com/awangdev/LintCode/blob/master/Java/169.%20Majority%20Element.java)**      Level: Easy      Tags: [Array, Bit Manipulation, Divide and Conquer, Moore Voting, Sort]
      

#### HashMap count occurance
- Time, Space: O(n)


#### Moore Voting Algorithm 投票消减
- 前提: input必须valid, 比较罕见少用. Moore Voting Algorithm: https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
- 与当下candidate相同, vote++. 与之不同, vote--.
- Majority Number是指超半数, 多1个就行: 消减至最后, 会至少有vote>=1.
- 那么: vote++, vote--到最后剩下的就是winner. 
- 这个方法比较greedy, 前提是: valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。
- time: O(n), space O(1)

#### Sort
- sort entire nums array
- assume there is a solution, then nums[n/2] must be that majority num
- time O(nlogn)

#### Divide and Conquer
1. Recursive approach
1. For ange rangeA & rangeB, rangeA has majorElementA and rangeB has majorElementB
    - majorElementA = majorElementB, of course this element will be the major number for whole range
    - if majorElementA != majorElementB, then need to count both elements in whole range
    - of course the larger occurance will be the major num

#### Bit manipulation
- TODO

#### Related Problems
- Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。
- Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。



---

**1. [229. Majority Element II.java](https://github.com/awangdev/LintCode/blob/master/Java/229.%20Majority%20Element%20II.java)**      Level: Medium      Tags: [Array, Moore Voting]
      

#### Two counters, Moore Voting
1. Moore voting: vote可以加减, 一旦为零, 换下一个candidate, 之前抵消掉的算作清零.
1. 一个array里面, 最多也只有2个数字 出现次数大于2次, 所以用A/B表示.
1. count overall apperance at the end for the two items: countA, countB
1. save to result as valA, valB
1. 有点 moore voting的意思: 
    - 当count == 0的时候, reset 
    - 两个candidate A/B都不等, 那么countA--, countB--
1. 最终重新计数, 然后比较出结局.
1. 注意: 按照if statement的顺序, valA&&countA 比valB&&countB有优先性



#### Sort + count
- O(nlogN)



---

