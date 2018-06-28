E
1530161330
tags: DP, PreSum

给一串数字, 求sumRange.

#### PreSum
- 就是pre sum 的definition
- preSum也是dp[]一种最简易的形式把.
- dp[i], preSum[i]: 前(i-1)个元素的和.

```
/**
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.

 */

 class NumArray {
    int[] preSum = null;
    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        preSum = new int[n];
        preSum[0] = nums[0];
        for(int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
        if (preSum == null || i < 0 || j > preSum.length - 1) return -1;
        if (i == 0) return preSum[j];
        return preSum[j] - preSum[i - 1];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
```