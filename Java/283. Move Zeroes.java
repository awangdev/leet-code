E
tags: Array, Two Pointers
time: O(n)
space: O(1)

Move non-zero elements to front of array; preseve order.

#### Two Pointers
- Outside pointer that moves in certain condition. 
- Save appropirate elements

```
/*
Given an array nums, write a function to move all 0's to the end of it
while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/
/*
Pick non-zero and assign to front. Use a pointer to track
- Use pointer to move all elements to non-zero index.
- set remaining list -> 0
*/
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null) return;
        int index = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) nums[index++] = nums[i];
        }
        for (int i = index; i < n; i++) {
            nums[i] = 0;
        }
    }
}
```