M
tags: Array, Hash Table, PreSum, Subarray
time: O(n)
space: O(n)

给一串数字, 找其中的 # of subarray的 where subararySum == k.

#### Method1: Hash Table to sture presum (like in 2 sum problem)
- Approach#4 of https://leetcode.com/problems/subarray-sum-equals-k/solution/
- Hash Table two sum 思想, but to save frequency of current sum: `preSumCount<sum, count>`
    - for loop 从左开始积累 `preSumCount<sum, count>`
    - derive `priorSum = sum - k`: 看看前面有多少此种sum, `preSumCount.get(priorSum)`
        - `# ways to reach priorSum` gives # of ways for that `priorSum + k = curr Sum`
        - therefore, count += preSumCount.get(priorSum)
- O(n) time, O(n) space
- Note: 如果需要实际index, 可以存 `Map<Integer, List<Index>>`

#### Method2: Calculate each individual range, with PreSum
- presum: socalled `cummulative sum`
- move from starting point i = [0 ~ n -1] and test each `range = [i ~ j]`
- use presum to verify k: `preSum[j + 1] - preSum[i]`
- time: O(n^2): `1 + 2 + 3 + 4 ... + n ~= O(n^2)`


```
/*
Given an array of integers and an integer k, 
you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/

//O(n) space, O(n) time
class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> preSumCount = new HashMap<>();
        int n = nums.length, sum = 0, count = 0;
        preSumCount.put(0, 1);
        for (int num : nums) {
            sum += num;
            int priorSum = sum - k;
            if (preSumCount.containsKey(priorSum)) { // # ways to sum up to priorSum
                count += preSumCount.get(priorSum);
            }
            preSumCount.put(sum, preSumCount.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}

// Method2: PreSum, but still calculate each individual range
// time O(n^2), space O(n)
class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        
        // double for loop to calculate k
        int n = nums.length, count = 0;
        int[] preSum = calcPreSum(nums);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == 0) count += preSum[j + 1] == k ? 1 : 0;
                else count += (preSum[j + 1] - preSum[i]) == k ? 1 : 0; // range [i, j]
            }
        }
        
        return count;
    }
    
    private int[] calcPreSum(int[] nums) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] += preSum[i - 1] + nums[i - 1];
        }
        return preSum;
    }
}


```