M
1531969906
tags: DP, Coordinate DP
time: O(n^2)
time: O(n)

给一串 unsorted sequence, 找到长 increasing subsequence 的个数!

#### Coordinate DP
- 需要能够判断综合题, 分清楚情况和套路: combination of `longest subsequence` and `ways to do`, as well as global variable. 
- len[i] (我们平时的dp[i]): 在前i个元素中, 最长的 increasing subsequence length;
- count[i]: 在前i个元素中, 并且以 len[i]这个长度为准的 subsequence的 count. 或者: 在前i个元素中, ways to reach longest increasing subsequence.
- `len[i] == len[j] + 1`: same length, but different sequence, so add all `count[i] += count[j]`
- `len[i] < len[j] + 1`: 这就是更长的情况找到了, 那么有多少次 count[j] 有多少, count[i] 就有多少. 仔细想sequence: 长度增长了, 但是ways to reach i 没有增长.
- 同样的判断需要用在 maxLen 和 maxFreq上:
- 如果没有增长 maxLen 不变, maxFreq上面需要 +=count[i] (同一种长度, 多了更多的做法)
- 如果maxLen 变长, maxFreq 也就是采用了 count[i] = count[j]
- TODO: Is rolling array possible?

#### 相关
- 都是 Coordiate DP, DP的鼻祖家族:
- Longest Increasing Subsequence (跟这道题的一部分一模一样)
- Longest Continuous Increasing Subsequence (连续, 只check dp[i - 1])
- Longest Increasing Continuous Subsequence I, II (Lintcode, II 是matrix)

```
/**
Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, 
and there are 5 subsequences' length is 1, so output 5.


 */

// Coordiate DP 
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length, maxLen = 0, maxFreq = 0;
        int[] len = new int[n];
        int[] count = new int[n];
        
        // process len
        for (int i = 0; i < n; i++) {
            len[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (len[i] == len[j] + 1) {
                        count[i] += count[j];
                    } else if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            if (maxLen == len[i]) {
                maxFreq += count[i];
            } else if (maxLen < len[i]) {
                maxLen = len[i];
                maxFreq = count[i];
            }
        }
        
        return maxFreq;
    }
}

// 做成了sequence dp, 其实没必要
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length, maxLen = 0, maxFreq = 0;
        int[] len = new int[n + 1]; // len[0] = 0
        int[] count = new int[n + 1];
        
        // process len
        for (int i = 1; i <= n; i++) {
            len[i] = 1;
            count[i] = 1;
            for (int j = 1; j < i; j++) {
                if (nums[j - 1] < nums[i - 1]) {
                    if (len[i] == len[j] + 1) {
                        count[i] += count[j];
                    } else if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            if (maxLen == len[i]) {
                maxFreq += count[i];
            } else if (maxLen < len[i]) {
                maxLen = len[i];
                maxFreq = count[i];
            }
        }
        
        return maxFreq;
    }
}

```