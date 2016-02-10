i位和之前的0~i-1 都远关系。复杂一点。
每次都考虑o~i的所有情况。所以double for loop
```
/*
Given a sequence of integers, find the longest increasing subsequence (LIS).

You code should return the length of the LIS.

Example
For [5, 4, 1, 2, 3], the LIS  is [1, 2, 3], return 3

For [4, 2, 4, 5, 3, 7], the LIS is [4, 4, 5, 7], return 4

Challenge
Time complexity O(n^2) or O(nlogn)

Clarification
What's the definition of longest increasing subsequence?

    * The longest increasing subsequence problem is to find a subsequence of a given sequence in which the subsequence's elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible. This subsequence is not necessarily contiguous, or unique.  

    * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem

Tags Expand 
Binary Search LintCode Copyright Dynamic Programming
*/

/*
	Thoughts:
	dp[i] depends on not only dp[i-1], but also [i-1] ...[0].
	So it has to be double-for loop.
	Each sub-for loop on i, traverse 0 ~ j(j<=i) to find largest number to put on dp[i]
	fn:
		dp[i] = dp[i] > dp[j] + 1 ? dp[i] : dp[j] + 1
	init:
		dp[i] initally all = 1. (i = 0 ~ n). If no other number meets the requirement, at least it has itself.
	Result:
		dp[n - 1]

	Note: nums[j] <= nums[i] is the 'increasing' requirement
	dp[j] + 1 means: best we can do at dp[j] + 1, is this better than what we already have on dp[i]?	

*/


public class Solution {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return 0;
    	}
    	int n = nums.length;
    	int[] dp  = new int[n];
    	int max = 0;
    	for (int i = 0; i < n; i++) {
    		dp[i] = 1;
    		for (int j = 0; j < i; j++) {
    			if (nums[j] <= nums[i]){
    				dp[i] = dp[i] > dp[j] + 1 ? dp[i] : dp[j] + 1;
    			}
    		}
    		if (dp[i] > max) {
    		    max = dp[i];
    		}
    	}
    	return max;
    }
}


















```