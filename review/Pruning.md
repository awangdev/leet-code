 
 
 
## Pruning (1)
**0. [277. Find the Celebrity.java](https://github.com/awangdev/LintCode/blob/master/Java/277.%20Find%20the%20Celebrity.java)**      Level: Medium      Tags: [Adjacency Matrix, Array, Graph, Greedy, Pruning]
      

有n个人, 其中有个人是celebrity, 注意必要条件 `Celeb knows nobody; Everyone else knows the celeb`. 找到celeb

Note: the relationship graph can be presented as an adjacency matrix, but graph is not directly used in this problem.

#### Pruning
- Given assumption: 1) `only 1 celebrity`, 2) person k, who knows nobody ahead of him or after him.
- if first pass finds candidate, `person k`, it means:
    - person [0, k-1] are not celebrity: they know a previous or current candidate
    - person k knows no one between [k + 1,  n): k+1 to n-1 can not be the celebrity either. 
    - person k is just the last standing possible celebrity
- second pass validation: we do not know if `knows(celeb, [0~k-1] )`. Do a final O(n) check
- time:O(n), space O(1)
- DO NOT: Brutle compare all -> all: O(n^2) handshakes.

##### 思考逻辑
- 先写出来[0 ~ n - 1], 最简单的方式 O(n^2) 检查, 记录每个人的状态.
    - 逐渐发现, 因为 celeb 谁都不会认识, 那么当任何candidate knows anyone, 他自身就不是celeb.
    - 我们可以greedy地, 一旦fail一个, 就立刻假设下一个是celeb candidate
- 最终还是要检查一遍, 避免错漏.
- 想一下happy case: 如果 celeb=0,  那么 know(celeb, i) 永远都是false, 然后 celeb一直保持0, 坚持到verify所有人.



---

