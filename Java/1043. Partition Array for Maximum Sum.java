M
tags: Memoization, DFS, DP, Graph
time: O(n), calc memo[n]
space: O(n)

#### Top-Down DFS + Memoization
- Pick a subset (max-size k), and produce sub problem to solve by dfs
- NOTE: no need to change actual index value. That makes this problem easier (no need to record the choice path)
- time: O(n), calc memo[n]
- space: O(n), memo + stack depth

```
/*
Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning.

 

Example 1:

Input: A = [1,15,7,9,2,5,10], K = 3
Output: 84
Explanation: A becomes [15,15,15,9,10,10,10]
 

Note:

1 <= K <= A.length <= 500
0 <= A[i] <= 10^6
*/

class Solution {
    Integer[] memo;
    public int maxSumAfterPartitioning(int[] A, int K) {
        if (A == null || A.length == 0) return 0;

        int n = A.length;
        memo = new Integer[n];
        return dfs(A, K, 0);
    }
    
    private int dfs(int[] A, int k, int index) {
        if (index >= A.length) return 0;
        if (memo[index] != null) return memo[index];
        
        int local = Integer.MIN_VALUE, max = Integer.MIN_VALUE;
        for (int i = index; i < A.length && i < index + k; i++) {
            local = Math.max(local, A[i]);
            max = Math.max(max, local * (i - index + 1) + dfs(A, k, i + 1));
        }
        memo[index] = max;
        return max;
    }
}
```