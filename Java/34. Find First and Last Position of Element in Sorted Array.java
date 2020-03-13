M
tags: Array, Binary Search
time: O(logn)
space: O(1)

#### Binary Search
- need search left bound & right bound.
- use input parameter `direction` to binary search function
- Option0: simplification, inspired by `278. First Bad Version - Method1: Check is-NOT-BadVersion`
    - 1) if found match, but NOT sure it is desired boundary, just leave it and keep going
    - 2) check the final results after `binary search while loop` completes
    - WHY? code is easier to read in this way.

```
/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
*/

// Option0: Simplification
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        return new int[]{
            binarySearch(nums, target, true),    
            binarySearch(nums, target, false)
        };
    }
    
    private int binarySearch(int[] nums, int target, boolean isLeft) {
        int n = nums.length, start = 0, end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2, val = nums[mid];
            if (val == target) {
                if (isLeft) end = mid;
                else start = mid;
            } else if (val < target) start = mid;
            else end = mid;
        }
        if (nums[start] == target && nums[end] == target) return isLeft ? start : end;
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }
}

// Option1: original, check end state in middle
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        return new int[]{
            binarySearch(nums, target, true),    
            binarySearch(nums, target, false)
        };
    }
    
    private int binarySearch(int[] nums, int target, boolean isLeft) {
        int n = nums.length, start = 0, end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2, val = nums[mid];
            if (val == target) {
                if (isLeft && mid - 1 >= 0 && nums[mid - 1] == target) end = mid;
                else if (!isLeft && mid + 1 <= n - 1 && nums[mid + 1] == target) start = mid;
                else return mid;
            } else if (val < target) start = mid;
            else end = mid;
        }
        if (nums[start] == target && nums[end] == target) return isLeft ? start : end;
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }
}
```