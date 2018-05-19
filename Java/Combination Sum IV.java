M
1526760445
tags: Array, DP, Backpack DP

给一串数字candidates (no duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 可以用任意多次.

#### Backpack DP
- 计数问题, 可以想到DP. 其实就是Backpack VI.
- 从x个数字里面找candidate(可以重复用同一个数字), 来sum up to target. 找: # of ways to form the sequence.
- Backpack VI: 给一个数组nums, 全正数, 无重复数字; 找: # of 拼出m的方法
- dp[i]: # of ways to build up to target i
- consider last step: 如果上一步取的是 candidate A, 那么就该加到dp[i]:
- dp[i] += dp[i - A]
- 要找overall dp[i], 就做一个for loop: dp[i] = sum{dp[i - num]}, where for (num: nums)
- Time: O(mn). m = size of nums, n = target
- If we optimize dp for loop, 需要Sort nums. O(mlogm). will efficient 如果m是constant或者relatively small. Overall: O(n)

#### DFS, backtracking
- 尽管思考方式是对的, 但是 times out
- 可以重复使用数字的时候, 比如用1 来拼出 999, 这里用1就可以走999 dfs level, 不efficient

/*
Given an integer array with all positive numbers and no duplicates, 
find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?

*/

/*
same as the backpack problem
- use numbers in nums to sum up to target
- num can be used multiple times.

consider last step: 
nums[i - 1] was picked, or not picked to sum up to target
dp[w] = # ways to sum up to target
if last num[i - 1] picked, dp[target] += dp[target - nums[i - 1]]

dp[0] = 0;
dp[w] = sum (dp[w - num1], dp[W - num2], dp[w - num3])
*/

class Solution {
    public int combinationSum4(int[] nums, int target) {
        // check edge case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[target + 1];
        dp[0] = 1; // target == 0, 1 way to form, as base condition

        for (int i = 1; i <= target; i++) { // weight
            for (int num : nums) { // choose number
                if (i >= num)
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}

// Sort, and then skip any num larger than target i
class Solution {
    public int combinationSum4(int[] nums, int target) {
        // check edge case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int[] dp = new int[target + 1];
        dp[0] = 1; // target == 0, 1 way to form, as base condition

        for (int i = 1; i <= target; i++) { // weight
            for (int j = 0; j < nums.length && nums[j] <= i; j++) { // choose number
                dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }
}




/*
- backtracking times out
- use numbers in nums to sum up to target
- num can be used multiple times.
- dfs, for loop, each for loop, can use same index for next dfs level
- if target = num, return 1; other count += dfs(...)
- dfs: nums, index, target. Perform target - val
*/
class Solution {
    public int combinationSum4(int[] nums, int target) {
        // check edge case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return dfs(nums, 0, target);
    }
    
    private int dfs(int[] nums, int index, int target) {
        int count = 0;
        // for loop
        for (int i = index; i < nums.length; i++) {
            if (target == nums[i]) {
                count++;
            } else if (target > nums[i]){
                // dfs
                count += dfs(nums, index, target - nums[i]);
            }
        }
        return count;
    }
}