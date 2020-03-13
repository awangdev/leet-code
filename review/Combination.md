 
 
 
## Combination (4)
**0. [Combinations.java](https://github.com/awangdev/LintCode/blob/master/Java/Combinations.java)**      Level: Medium      Tags: [Backtracking, Combination, DFS]
      
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

#### DFS, Backtracking
- for loop, recursive (dfs).
- 每个item用一次, 下一个level dfs(index + 1)
- Combination DFS. 画个图想想. 每次从1~n里面pick一个数字i
- 因为下一层不能重新回去 [0~i]选，所以下一层recursive要从i+1开始选。



---

**1. [Combination Sum III.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20III.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      
给一个integer k, 和一个target n. 

从positive数字[1 ~ 9], 找到所有unique的 组合(combination) int[], size = k, 要求每个combination的和 = n.

(隐藏条件, 需要clarify): 同一个candidate integer [1 ~ 9], 只可以用一次.

#### DFS, Backtracking
- 跟Combination Sum I, II 没什么太大区别, 只不过, 一定要用k个数字, 也就是一个for loop里面的特别条件
- 考虑input: 没有重复数字 [1 ~ 9]
- 考虑candidate重复利用: 不可以重复利用, next level dfs 时候, curr index + 1
- the result is trivial, save success list into result.

##### Time Complexity
- Which one?
- worst case: tried all numbers and cannot find: O(m!), m = 9, all possible integers in [1~9]
- C(n,k), n choose k problem : `n! / (k! * (n-k)!)` => ends up being `O(min(n^k, n^(n-k)))`



---

**2. [39. Combination Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/39.%20Combination%20Sum.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      

给一串数字candidates (no duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 可以用任意多次.


#### DFS, Backtracking
- 考虑input: 没有duplicate, 不需要sort
- 考虑重复使用的规则: 可以重复使用, 那么for loop里面dfs的时候, 使用curr index i
- the result is trivial, save success list into result.
- Time and Space complexity:
    - transform the analysis as for `40. Combination Sum II`
    - Since this problem allows reuse of elemenets, assume they exist in original input as duplicates
    - time: O(k * 2^n), k = avg rst length
    - space: O(k) stack depth, if not counting result size



---

**3. [40. Combination Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/40.%20Combination%20Sum%20II.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      

给一串数字candidates (can have duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 只可以用一次.

#### DFS, Backtracking
- when the input has duplicates, and want to skip redundant items? 考虑重复使用的规则: 不可以重复使用
    - 1. sort.  考虑input: 有duplicate, 必须sort
    - 2. in for loop, skip same neighbor: `if (i > index && candidates[i] == candidates[i - 1]) continue;`
        - 因为在同一个level里面重复的数字在下一个dfs level里面是会被考虑到的, 这里必须skip
- the result is trivial, save success list into result.
- Time complexity: O(k * 2^n), k = average result length
    - 1) Assume on average, there are k elements in result 
    - 2) Since element can be used ONLY once, so the total # of solutions can be `C(n, k)`: `pick k out of n`
    - 3) Now let k be any number [0, n], so total # of solutions can be: `C(n,0) + C(n,1) + C(n,2) + ... C(n,n) = 2^n`
    - 4) Now: `the new ArrayList<>(list)` takes average O(k) time
    - Total: O(k * 2^n)
- Space: O(n), stack depth, if not counting results size



---

