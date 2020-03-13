 
 
 
## Permutation (5)
**0. [Shuffle an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Shuffle%20an%20Array.java)**      Level: Medium      Tags: [Permutation]
      
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

**1. [Palindrome Permutation II.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Permutation%20II.java)**      Level: Medium      Tags: [Backtracking, Permutation]
      
TODO: need to review permutation

permutation的综合题：    
1. validate Input 是不是可以做palindromic permutation. 这个就是（Palindrome Permutation I）   
2. 顺便存一下permutation string的前半部分和中间的single character(if any)    
3. DFS 做unique permutation: given input有duplicate characters。       



---

**2. [1053. Previous Permutation With One Swap.java](https://github.com/awangdev/LintCode/blob/master/Java/1053.%20Previous%20Permutation%20With%20One%20Swap.java)**      Level: Medium      Tags: [Array, Greedy, Permutation]
      

#### Analyze Permutation behavior
- concept similar to `31. Next Permutation`
- 1) first pass: find the one that is in incorrect order
- 2) second pass: find the right spot to swap



---

**3. [31. Next Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/31.%20Next%20Permutation.java)**      Level: Medium      Tags: [Array, Permutation]
      

#### Permutation Behavior
- Great write up: https://leetcode.com/problems/next-permutation/solution/
- next lexicographically permutation: `smallest` but `larger than curr` permutation:
     - find first low point from right [low]
     - find the slight larger [high] to swap with [low]
     - make sure right side of low is eventually the smallest
- Analyze the use cases, to find next low permutation, 2 major steps:
    - 1) Find `first low/drop candidate` from right
    - 2) Find `first high where nums[high] > nums[low]` from right
    - 3) swap(low, high). 
        - By now, [low, n-1] forms a greater permutation
        - but it is not the smallest, because right side [low + 1, n - 1] is descending
    - 4) reverse(low + 1, n-1) to create ascending slopt on right of low (smallest next lexicographically permutation)
- Corner case: if input array is decending (1st low not found), reverse it all together O(n)
- time: O(n) visit all indexes
- space: O(1) not using additional
- Similar question: `1053. Previous Permutation With One Swap`




---

**4. [46. Permutations.java](https://github.com/awangdev/LintCode/blob/master/Java/46.%20Permutations.java)**      Level: Medium      Tags: [BFS, Backtracking, DFS, Permutation]
      

#### Method1-Option1: Recursive Backtracking, with queue to maintain the remaining list
- Best of all recursive approaches
- iterate over nums: pick or not pick
- reduce remaining item in next level:
    - option1: use queue, restrict queue size; backtrack append to queue
    - option2: remove element before passing into next level; backtrack: insert back
- time O(n!): visit all possible outcome
    - T(n) = n * T(n-1) + O(1)

Method1-Option2: Recursive Backtracking, with `list.contains()` to avoid reuse of index
- A bit worse than option1, uses more time:
    - list.contains() cost O(logn). Technically, it is O(n^n), plus the `contains` is nlogn time
    - also, each dfs, it has to iterate over entire nums list

Method1-Option3: Recursive Backtracking, with `visited[]` to avoid reuse of index
- Use visited[] to track, still causes for(over n items), not efficient

#### Method2-Option1: Iterative, Build permutation by insertion
- Best of all iterative approaches
- Each time pick 1 NEW element and find places to insert into candidate list:
    - 1. 一个一个element加进去
    - 2. 每一次把rst里面的每个list拿出来, 创建成新list, 然后选位置加上new element
    - 3. 加新元素的时候, 要在list的每个位置insert, 最终也要在原始的list末尾加上new element
- 还是O(n!), 因为rst insert O(n!)个permutations
- Better than the Option2/Option3 (`BFS+Queue`), because this solution does not need to check duplicates

#### Method2-Option2: Iterative, use Queue to hold candidate list
- 用个queue，每次poll()出来的list, 把在nums里面能加的挨个加一遍
- Time O(n!)
- Slow: checking candidate.contains() is O(logn) each time

#### Method2-Option3: Iterative, use queue to candidate list, and calculate remain list on the fly
- Almost same as Method2-Option2, but it builds remainingCandidate list on the fly list.removeall(xyz): O(n)
- Even slower than Method2-Option2



---

