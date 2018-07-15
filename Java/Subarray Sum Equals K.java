M
1531625267
tags: Array, Hash Table, PreSum
time: O(n)
space: O(n)

#### Hash Table
- Hash Table two sum 思想, but `save frequency of current preSum`
- From the orignal presum solution: `target = preSum[j] - preSum[i - 1]`
- `k = sum - portion`, and reversely, `portion = sum - k`
- We know the value of sum and k, so portion will be requested
- portion is just previously calcualted sum; track its frequency using `map<preSumValue, frequency>`
- map.get(portion) = the # of possible ways to reach k
- O(n) time, O(n) space

#### PreSum, O(n^2)
- move from starting point i = [0 ~ n -1] and define range = [i ~ j]
- use presum to verify k: `preSum[j] - preSum[i - 1]`
- O(n^2): `1 + 2 + 3 + 4 ... + n ~= O(n^2)`


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

        Map<Integer, Integer> preSumFreq = new HashMap<>();
        int n = nums.length, sum = 0, count = 0;
        preSumFreq.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (preSumFreq.containsKey(sum - k)) {
                count += preSumFreq.get(sum - k);
            }
            preSumFreq.put(sum, preSumFreq.getOrDefault(sum, 0) + 1);
        }
        
        return count;
    }
}

// PreSum, 
// time O(n^2), space O(n)
class Solution {
    public int subarraySum(int[] nums, int k) {
        // check input
        if (nums == null || nums.length == 0) return 0;
        
        // double for loop to calculate k
        int n = nums.length, count = 0;
        int[] preSum = calcPreSum(nums);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == 0) count += preSum[j] == k ? 1 : 0;
                else count += (preSum[j] - preSum[i - 1]) == k ? 1 : 0;
            }
        }
        
        return count;
    }
    
    private int[] calcPreSum(int[] nums) {
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] += preSum[i - 1] + nums[i];
        }
        return preSum;
    }
}



```