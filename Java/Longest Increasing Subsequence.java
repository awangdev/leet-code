M
1516863852

方法1:
[0 ~ i], 0<i<n: 以i为结尾, 每个不同的i会得出的max-length. 所以每个结尾i都要被考虑一遍.
dp[i]: represent the max length aggregated up to index i.

Previous Note:
i位和之前的0~i-1 都远关系。复杂一点。
每次都考虑o~i的所有情况。所以double for loop


方法2:
O(n log n)? 还没有做. 是否for loop里面用binary search?

```

/**
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 */

/*
Thoughts:
Follow up based on LCIS: it does not have to be continuous, which means there can be bottoms between peeks.
In given example, starting from value 2, we can find [2, 5, 3] -> max= 2
It does not matter if the [2, 5] or [2, 3] is picked, since the results are equal.
However, if we have [2, 5, 3, '4'], it matters that if '5' or '3' was picked. Therefore, making sure [2,3] was picked is cretical.

Therefore, the goal is to set end index i [0~n], aim all nums[j] <= nums[i], j < i, see what's the max length.
Use the nums[i] as low-bound to find increasing chain.

Of course, we want to keep the max found from each ending-i, as i goes rom [0~n], so use DP.

dp[i]: represent the max length aggregated up to index i.
init: dp[0] = 0 at each j iteration.
return max + 1 since base-0 was used.

for loop over i
    for loop over j=0, j < i, j++

O(n^2)
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int[] dp = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[0] = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = dp[i] > dp[j] + 1 ? dp[i] : dp[j] + 1;
                }
            }
            max = Math.max(dp[i], max);
        }
        return max + 1;
    }
}


// LintCode
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