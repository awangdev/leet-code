M
tags: Math, DP, Coordinate DP, Subarray, PreSum
time: O(n)
space: O(k)

给一个非负数的数列和数字k(可正负, 可为0). 找到连续子序列(长度超过2), 使得这个subarray的sum 是 k的倍数. 问: 是否可能?

#### Method1: Validate Mod Result
- Check if mod result exist in earlier preSum
- Utilize `Pigeonhole principle` to optimize:
    - 1) put positive integers into k slots
    - 2) when # of integers > 2*k, then there must be a range sum that is multipler of k
    - more illustration here: https://leetcode.com/problems/continuous-subarray-sum/solution/
- Draw the presum and try to take mod of each presum and save to set, we realize
    - 1) assume a mod result = 7, and we mark it in the set
    - 2) some time later, after summing up more values, (7 + x + y ...+ z) % k == 7
        - it means `(x + y ...+ z) % k == 0`
        - There is a `整除` exist; return true
- Meanwhile, if we want to record the list of indexes, we can use a Map rather than set.
- Note: if all we do to the presum is to % k, therefore `preSum % k` can represent `presum` in some cases.
- time: O(n)
- space: O(k), size restrited by mod result of `%k`


#### Method2: DP, PreSum
- PreSum[]: 
    - 1) cal preSum array
    - 2) preSum(i, j) = continuous range sum
    - 3) determine if `preSum(i, j) % k == 0`
- time: O(n^2)
- DP (坐标型. specifically, preSum[])
    - 记录在0 ~ i点(包括nums[i], 以nums[i]结尾)的sum, 坐标型动态规划.
    - dp[i] = dp[i - 1] + nums[i];

#### Method3: 直接算结果
- 从sum = 每次[i ~ j]的所有情况
- time: O(n^2)
- space: O(1)

```
/*
Given a list of non-negative numbers and a target integer k, 
write a function to check if the array has a continuous subarray of size 
at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
*/
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        k = k == 0 ? Integer.MAX_VALUE : Math.abs(k); // set k to positive for moding usage
        if (nums.length / 2 > k) return true; // Pigeonhole principle
        
        Set<Integer> set = new HashSet<>();
        int lastMod = 0;
        for (int num : nums) {
            int mod = (lastMod + num) % k;
            if (set.contains(mod)) return true;
            set.add(lastMod);
            lastMod = mod;
        }

        return false;
    }
}


/*
Thoughts:
If iterate over range[0 ~ n], the move the range by [1 ~ n] steps
Time will become O(n^2)

Utilize rangeSum[0 ~ i] and rangeSum[0 ~ j]
Calculate the diff

dp[i] = sum up to index i
dp[0]: nums[0]
dp[i] = dp[i - 1] + nums[i];

O(n^2)
*/

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int[] dp = new int[n]; // sum up to index i
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = dp[j] - dp[i] + nums[i];
                if (sum == k || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }
        }

        return false;
    }
}

/*
Thoughts:
If iterate over range[0 ~ n], the move the range by [1 ~ n] steps
Time will become O(n^2)
*/

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < n; j++) {
                sum += nums[j];
                if (sum == k || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }
        }

        return false;
    }
}
```