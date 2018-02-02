E
1517552169

简单的求sum, 同时求max, 结尾求余数就好.
```
/*
Given an array consisting of n integers, find the contiguous subarray of given length k 
that has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].
*/

/*
Thoughts:
Given a k as range, find the max sum.
Brutle: use the box, calculate sum one index at a time, and find the max.
No need to add 4 numbers all the time, just minus the leading one and add the new one.

Note: use double to catch the sum.
*/
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        double sum = 0;
        // init the sum of k items
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        
        // calculate
        double max = sum;
        for (int i = k; i < nums.length; i++) {
            max = Math.max(sum, max);
            sum = sum - nums[i - k] + nums[i];
        }
        max = Math.max(sum, max);
        return max / k;
    }
}
```