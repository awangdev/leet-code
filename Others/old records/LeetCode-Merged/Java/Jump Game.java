LintCode做过。
用一个farest can go 来做个一个点的DP，记录可以跑到得最远的地方。
```
/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

Hide Tags Array Greedy

*/

public class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        int farest = 0;
        for (int i = 0; i < nums.length; i++) {
            farest = Math.max(farest, nums[i] + i);
            if (farest >= nums.length - 1) {
                return true;
            }
            if (farest <= i) {
                return false;
            }
        }
        return true;
    }
}
```