M
tags: DP, Backpack

#### Backpack DP
- the problem turns into: can we find a subset of items that sum up to target sum?
- create `boolean dp[j]` to represent if we can sum up to j, where j = sum value
  - want to try out all items in num, 

#### DFS
- use dfs to find a subset of items that sum up to target sum?

```
/*
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.
 

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

*/

class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2)  return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        sum /= 2;
        
        // dp
        boolean[] dp = new boolean[sum + 1];  // dp[j] = false by default
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = sum; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[sum];
    }
}

// DFS correct solution, but times out when [1, 1, 1, ,,,,,,,1, 100] case.
class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2)  return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        return dfs(nums, 0, sum / 2);
    }
    
    public boolean dfs(int[] nums, int index, int target) {
        if (target < 0) return false;
        if (target == 0) return true;
        for (int i = index; i < nums.length; i++) {
            if (dfs(nums, i + 1, target - nums[i])) return true;
        }
        return false;
    }
}
// Still times out
class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2)  return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        sum /= 2;
        Arrays.sort(nums);
        return dfs(nums, 0, sum);
    }
    
    public boolean dfs(int[] nums, int index, int target) {
        if (index == nums.length) return false;
        if (target == nums[index]) return true;
        if (target < nums[index]) return false;
        return dfs(nums, index + 1, target - nums[index]) 
            || dfs(nums, index + 1, target);
    }
}

// However C++ solution passes!!
class Solution {
public:
    bool canPartition(vector<int>& nums) {
        int sum = 0;
        for(int i =0;i<nums.size();i++){
            sum+= nums[i];
        }
        if(sum%2) return false;
        sum /= 2;
        sort(nums.rbegin(),nums.rend());
        return helper(nums, sum, 0);
    }
    bool helper(vector<int>& nums, int sum, int index){
        if(index == nums.size()) return false;
        if(sum == nums[index]) return true;
        if(sum < nums[index]) return false;
        return helper(nums,sum-nums[index],index+1) || helper(nums,sum,index+1);
    }
};
```