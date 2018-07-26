M
1531902072
tags: Hash Table

TODO: how aout without chaning the input nums?

```
/*
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.
*/
/*
k = sum - i - 1
check if map.containsKey(preSum - k). If so, Math.max(max, i - map.get(preSum - k))
*/


class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        for (int i = 0; i < nums.length; i++) { 
            if (nums[i] == 0) nums[i] = -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int preSum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (map.containsKey(preSum)) {
                max = Math.max(max, i - map.get(preSum));
            }
            if (!map.containsKey(preSum)) {
                map.put(preSum, i);
            }
        }

        return max;
    }
}

// TODO: what if not reseting 0 -> -1, can we solve this?
// Also, inspired by Buy/Sell Stock https://leetcode.com/problems/contiguous-array/discuss/99655/Python-O(n)-Solution-with-Visual-Explanation
class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int preSum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            int k = preSum - i - 1;
            if (map.containsKey(preSum - k)) {
                max = Math.max(max, i - map.get(preSum - k));
            }
            if (!map.containsKey(preSum)) {
                map.put(preSum, i);
            }
        }

        return max;
    }
}

```