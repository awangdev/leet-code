H
tags: Array, DP
time: O(n)
space: O(n)

#### DP, Divide and conquer
- split into 3 parts [0, i -1], [i, i + k -1]. [i + k, n - 1]
- NOTE: be very careful about index handling: 
    - `presum[i + 1] - presum[0]` gives inclusive range of `[0, i]`
- Use DP to record the starting position of max sum, 
- inspired by: https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/108231/C%2B%2BJava-DP-with-explanation-O(n)
    - 1) calculate preSum with range [0, n]
    - 2) calculate leftMaxIndex[], rightMaxIndex[]
    - 3) test middle range to find max solution
- Note: the test range for 1, 2, 3 always start with assumption that k has been consumed from one side
- Note: When need to record at max/min value change, we can check/assign it manually (rather than use a object to carry & sort)


```
/*
In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). 
If there are multiple answers, return the lexicographically smallest one.

Example:

Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 

Note:

nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).
*/

/*
- Divide and conquer: split into 3 parts [0, i -1], [i, i + k -1]. [i + k, n - 1]
- Use DP to record the starting position of max sum, inspired by: https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/108231/C%2B%2BJava-DP-with-explanation-O(n)
    - 1) calculate preSum with range [0, n]
    - 2) calculate leftMaxIndex[], rightMaxIndex[]
    - 3) test middle range to find best solution
- Note: the test range for 1, 2, 3 always start with assumption that k has been consumed from one side
*/

class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

        int n = nums.length, max = 0;
        int[] ans = new int[3];
        int[] presum = calcPresum(nums);
        int[] left = calcLeft(nums, presum, k);
        int[] right = calcRight(nums, presum, k);
        
        // process middle part
        for (int i = k; i <= n - 2 * k; i++) {
            int low = left[i - 1], high = right[i + k];
            int total = (presum[i + k] - presum[i])
                      + (presum[low + k] - presum[low])
                      + (presum[high + k] - presum[high]);
            if (total > max) {
                max = total;
                ans[0] = low;
                ans[1] = i;
                ans[2] = high;
            }
        }
        
        return ans;
    }
    
    private int[] calcLeft(int[] nums, int[] presum, int k) {
        int n = nums.length;
        int[] left = new int[n];
        for (int i = k, total = presum[k] - presum[0]; i < n; i++) {
            int sum = presum[i + 1] - presum[i + 1 - k]; // sum of range [i - k + 1, i], size = k
            if (sum > total) {
                left[i] = i + 1 - k;
                total = sum;
            } else left[i] = left[i - 1];
        }
        
        return left;
    }
    
    private int[] calcRight(int[] nums, int[] presum, int k) {
        int n = nums.length;
        int[] right = new int[n];
        right[n - k] = n - k;
        for (int i = n - k - 1, total = presum[n] - presum[n - k]; i>= 0; i--) {
            int sum = presum[i + k] - presum[i]; // sum of range [i, i + k - 1], size = k
            if (sum >= total) {
                right[i] = i;
                total = sum;
            } else right[i] = right[i + 1];
        }
        return right;
    }
    
    private int[] calcPresum(int[] nums) {
        int[] presum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) 
            presum[i] += presum[i - 1] + nums[i - 1];
        return presum;
    }
}
```