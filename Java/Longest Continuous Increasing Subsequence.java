E
1522812393
tags: Array, DP, Coordinate DP

找连续的持续上升子序列的长度.

#### Coordinate DP
- 1D coordinate, dp 的角标, 就是代表 index i 的状态
- 求最值, dp[i] = 在index i位置的最长子序列
- 如果 nums[i] > nums[i - 1], dp[i] = dp[i - 1] + 1
- 如果没有持续上升, 那么dp[i] = 1, 重头来过
- maintain max

#### Basic
- 用一个数存current count,  maintain max

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
'longest' => DP
dp[i]: represents the #of the on-going continuous increasing digits up to index i.
if (nums[i] > nums[i-1]), dp[i] = dp[i-1] + 1
init: dp[0] = 1. If just given 1 digit, itself will count a sequence, so dp[i] = 1; 

initialize: dp[0~n] = 1.
maintain a max.

Time, space: O(n) 
*/
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            if (nums[i] > nums[i - 1]) {
                // Below can just be dp[i] = dp[i - 1] + 1; since dp[i - 1] + 1 is always greater than 1
                dp[i] = Math.max(dp[i], dp[i - 1] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}


// Rolling Array
// Spce O(1)
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[2];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            dp[i % 2] = 1;
            if (nums[i] > nums[i - 1]) {
                dp[i % 2] = Math.max(dp[i % 2], dp[(i - 1) % 2] + 1);
            }
            max = Math.max(max, dp[i % 2]);
        }
        return max;
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