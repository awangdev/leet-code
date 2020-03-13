 
 
 
## Recursion (2)
**0. [938. Range Sum of BST.java](https://github.com/awangdev/LintCode/blob/master/Java/938.%20Range%20Sum%20of%20BST.java)**      Level: Easy      Tags: [BST, Recursion, Tree]
      
#### DFS based on BST rules
- sum up the matching L & R
    - Find (L,R) on left child
    - Find (L,R) on right child
    - Find (L,R) covering the root node
- space O(n), worst case O(logn), height of dfs.
- time O(n) to find all nodes between (L, R)

#### Iterative
- Using stack, or queue, list: any data structure (we are not doing ordered search)
- space O(n)
- time O(n)



---

**1. [698. Partition to K Equal Sum Subsets.java](https://github.com/awangdev/LintCode/blob/master/Java/698.%20Partition%20to%20K%20Equal%20Sum%20Subsets.java)**      Level: Medium      Tags: [DFS, DP, Recursion]
      

#### Method1: Brutle DFS to find subsets and return results
- Target = total / k, fixed.
- DFS: Pick one num and dfs with the remaining nums for subproblem
    - subproblem1: accumulate local sum to target
        - EndState: accumulatedSum == target, continue with below
    - subproblem2: after accumulatedSum == target, continue dfs with k-1
        - EndState: k == 0, return overall true
- Option1: DFS with nums, and boolean[] visited. Fast
- Option2: DFS with queue. 
  - Specially handling: dfs(size+1) to prevent `while(size-- >0)` from skipping last item at index 0


#### Method2: DP
- the problem aims to find true/false capability
- bit-masking. TODO. The DP approach is kinda hard-level
- https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/335668/DP-with-Bit-Masking-Solution-%3A-Best-for-Interviews



---

