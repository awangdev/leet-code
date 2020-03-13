M
tags: Array, Permutation
time: O(n)
space: O(1)

#### Permutation Behavior
- Great write up: https://leetcode.com/problems/next-permutation/solution/
- next lexicographically permutation: `smallest` but `larger than curr` permutation:
     - find first low point from right [low]
     - find the slight larger [high] to swap with [low]
     - make sure right side of low is eventually the smallest
- Analyze the use cases, to find next low permutation, 2 major steps:
    - 1) Find `first low/drop candidate` from right
    - 2) Find `first high where nums[high] > nums[low]` from right
    - 3) swap(low, high). 
        - By now, [low, n-1] forms a greater permutation
        - but it is not the smallest, because right side [low + 1, n - 1] is descending
    - 4) reverse(low + 1, n-1) to create ascending slopt on right of low (smallest next lexicographically permutation)
- Corner case: if input array is decending (1st low not found), reverse it all together O(n)
- time: O(n) visit all indexes
- space: O(1) not using additional
- Similar question: `1053. Previous Permutation With One Swap`


```
/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int n = nums.length;
        int low = findLow(nums);
        if (low == -1) reverse(nums, 0 , n - 1); // descending array, reverse all
        else {
            int high = findHigh(nums, low);
            swap(nums, low, high);
            reverse(nums, low + 1, n - 1); // reverse just right side to create min slop
        }
    }
    
    private int findLow(int[] nums) {
        int end = nums.length - 1;
        while (end > 0) {
            if (nums[end - 1] < nums[end]) return end - 1;
            end--;
        }
        return -1;
    }
    
    private int findHigh(int[] nums, int low) {
        int end = nums.length - 1;
        while (end > low) {
            if (nums[end] > nums[low]) return end;
            end--;
        }
        return -1;
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
    
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

```