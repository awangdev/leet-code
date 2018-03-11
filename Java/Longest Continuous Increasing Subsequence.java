E
1516861466
tags: Array, DP

简单的DP:dp[i]存在i点时连续上升#. dp[i]时可能为0, 一旦断开.

方法1: dp[i], maintain max
方法2: 用一个数存current count,  maintain max. 也是DP, 稍微简化.

```
/*
https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/
与LintCode的 LICS类似.

Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).

Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
Note: Length of the array will not exceed 10,000.
 */


/*
Thoughts:
'longest', could it be DP?
dp[i]: represents the #of the on-going continuous increasing digits up to index i.
if (nums[i] > nums[i-1]), dp[i] = dp[i-1] + 1
else dp[i] = 0;

initialize: dp[0~n] = 0.
maintain a max.
At result, max + 1, since base-0 was used at initialization.

O(n) time, space
*/
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0;
        final int[] dp = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            max = Math.max(dp[i], max);
        }
        return max + 1;
    }
}
/*
Thoughts:
Simplify dp solution: using just 1 integer to hold current count
*/
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0;
        int curr = 0;
        for (int i = 1; i < nums.length; i++) {
            curr = nums[i] > nums[i - 1] ? curr + 1 : 0;
            max = Math.max(curr, max);
        }
        return max + 1;
    }
}

```