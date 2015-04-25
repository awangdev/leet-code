/*
Maximum Subarray Show Result My Submissions

35% Accepted
Given an array of integers, find a contiguous subarray which has the largest sum.

Note
The subarray should contain at least one number

Example
For example, given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6.

Challenge
Can you do it in time complexity O(n)?

Tags Expand 
Array SubArray Greedy Enumeration LintCode Copyright


Thinking proces:
Store the sum in a array.
Normally, sum[i] = sum[i - 1] + nums[i].
However, if sum[i - 1] is a nagetive number, that means sum[i - 1] won't do any good for later sum but only decrease the sum. 
In this case, when sums[i - 1] < 0, we don't add it.
*/


public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int[] sums = new int[nums.size()];
        sums[0] = nums.get(0);
        int maxSum = sums[0];
        for (int i = 1; i < sums.length; i++) {
            if (sums[i - 1] < 0) {
                sums[i] = nums.get(i);
            } else {
                sums[i] = sums[i - 1] + nums.get(i);
            }
             maxSum = Math.max(maxSum, sums[i]);
        }
        return maxSum;
    }
}

