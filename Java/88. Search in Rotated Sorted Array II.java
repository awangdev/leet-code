M
tags: Array, Binary Search
time: O(logn), worst O(n)
space: O(1)

#### Binary Search
- Also most identical to `33. Search in Rotated Sorted Array`:
    - find where nums[mid] lands by comparing to nums[start]. i.e., if nums[mid] < nums[start], on right half of the array
    - when `nums[mid] == nums[start]`: duplicate. Shift by start++
- the worst case of `nums[mid] == nums[start]` willl cause O(n),
- but if duplicate is not entire array, should be O(logn)

```
/*
LeetCode
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?


*/


class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return true;
            if (nums[start] < nums[mid]) { //Land in 1st continous section
                if (nums[start] <= target && target <= nums[mid]) end = mid;
                else start = mid;
            } else if (nums[start] > nums[mid]) { //Land in 2nd continous section
                if (nums[mid] <= target && target <= nums[end]) start = mid;
                else end = mid;
            } else start++; // nums[start] == nums[mid], duplicate val, shift it to end
        }
        return nums[start] == target || nums[end] == target;
    }
}

```