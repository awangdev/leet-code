M
tags: DP, Recursion, DFS
time: O(k^(n-k) * k!)
space: O(n)

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

```
/*
Given an array of integers nums and a positive integer k, 
find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

 

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 

Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
*/


/*
Brutle DFS to find subsets and return results
- Target = sum / k, fixed.
- DFS: Pick one num and dfs with the remaining nums for subproblem
    - subproblem1: accumulate sum to target
        - EndState: accumulatedSum == target, continue below
    - subproblem2: once accumulatedSum == target, continue with k-1
        - EndState: k == 0, return overall true
*/
// DFS Option1: use array, boolean[] visited
class Solution {
    int[] nums;
    boolean[] visited;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int total = 0, n = nums.length;
        for (int num : nums) total += num;
        if (k <= 0 || total % k != 0) return false;

        this.nums = nums;
        this.visited = new boolean[n];
        return dfs(0, k, 0, total / k);
    }
    
    private boolean dfs(int start, int k, int sum, int target) {
        if (k == 0) return true; // exhaust all numbers
        if (sum == target) return dfs(0, k - 1, 0, target); // move on to next subset; start=0 to reprocess all candidates
        
        for (int i = start; i < nums.length; i++) { // i = start, skip examined candidates in next dfs level
          if (visited[i]) continue;
          visited[i] = true;
          if (sum + nums[i] <= target && dfs(i + 1, k, sum + nums[i], target)) return true;
          visited[i] = false;
        }
        
        return false;
    }
}
// DFS Option2: Use Queue
// dfs(size+1): prevent `while(size-- >0)` from skipping last item at index 0
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        
        int sum = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int num : nums) {
            sum += num;
            queue.offer(num);
        }
        if (k <= 0 || sum % k != 0) return false;
        
        return dfs(queue, queue.size(), k, 0, sum / k);
    }
    
    private boolean dfs(Queue<Integer> queue, int size, int k, int sum, int target) {
        if (k == 0) return true; // exhaust all numbers
        if (sum == target) return dfs(queue, queue.size(), k - 1, 0, target);
        
        while (size-- > 0) {
            int num = queue.poll();
            if (sum + num <= target && dfs(queue, size+1, k, sum + num, target)) return true;
            queue.offer(num);
        }
        return false;
    }
}

```