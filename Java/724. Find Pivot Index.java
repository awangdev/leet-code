E
tags: Array, PreSum
time: O(n)
space: O(1)

#### PreSum
- want to find `nums[i - 1] == nums[n - 1] - nums[i]`, given: 
    - preSum[i], sum from [0, i] inclusive
    - preSum[j] - preSum[i] = [i+1, j] inclusive
- O(n) to build preSum
- O(n) to find pivot

```
/*
Given an array of integers nums, write a method that returns the "pivot" index of this array.

We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.

If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.

Example 1:

Input: 
nums = [1, 7, 3, 6, 5, 6]
Output: 3
Explanation: 
The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
Also, 3 is the first index where this occurs.
 

Example 2:

Input: 
nums = [1, 2, 3]
Output: -1
Explanation: 
There is no index that satisfies the conditions in the problem statement.
*/

/*
preSum[i], sum from [0, i] inclusive
preSum[j] - preSum[i] = [i+1, j] inclusive
- O(n) to build preSum
- O(n) to find pivot
*/
class Solution {
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        // build pre sum
        for (int i = 1; i < n; i++) nums[i] += nums[i - 1];

        if (nums[n-1] - nums[0] == 0) return 0;// edge case
        
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] == nums[n - 1] - nums[i]) return i;
        }
        
        return -1;
    }
}
```