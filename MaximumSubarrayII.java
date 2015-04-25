/*
Given an array of integers, find two non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

Note
The subarray should contain at least one number

Example
For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2], they both have the largest sum 7.

Challenge
Can you do it in time complexity O(n) ?

Tags Expand 
Greedy Enumeration Array LintCode Copyright SubArray Forward-Backward Traversal

Thinking process:
Find frontSum: largest sum from index 0 till current at each index.
Find endSum: largest sum from end(endSum.length - 1) to current at each index.
Add them up: at any point i, leftSum + rightSum = largest 2 non-overlap sum.
			i
 		i		i
  	i				i
i						i




*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int[] frontSum = new int[nums.size()];
        int[] endSum = new int[nums.size()];
        int maxSum = 0;
        frontSum[0] = nums.get(0);
        //Init frontSum
        for (int i = 1; i < frontSum.length; i++) {
            if (frontSum[i - 1] < 0) {
                frontSum[i] = nums.get(i);
            } else {
                frontSum[i] = frontSum[i - 1] + nums.get(i);
            }
        }
        maxSum = frontSum[0];
        //Find max
        for (int i = 1; i < frontSum.length; i++) {
            if (frontSum[i] < maxSum) {
                frontSum[i] = maxSum;
            } else {
                maxSum = frontSum[i];
            }
        }
        
        //Init endSum
        endSum[endSum.length - 1] = nums.get(nums.size() - 1);
        for (int i = endSum.length - 2; i >= 0; i--) {
            if (endSum[i + 1] < 0) {
                endSum[i] = nums.get(i);
            } else {
                endSum[i] = endSum[i + 1] + nums.get(i);
            }
        }
        //Find max
        maxSum = endSum[endSum.length - 1];
        for (int i = endSum.length - 2; i >= 0; i--) {
            if (endSum[i] < maxSum) {
                endSum[i] = maxSum;
            } else {
                maxSum = endSum[i];
            }
        }
        //Calculate max Sum
        maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size() - 1; i++) {
            maxSum = Math.max(maxSum, frontSum[i] + endSum[i + 1]);
        }
        return maxSum;
    }
}




