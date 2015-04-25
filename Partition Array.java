/*
Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

All elements < k are moved to the left
All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.

Example
If nums=[3,2,2,1] and k=2, a valid answer is 1.

Note
You should do really partition in array nums instead of just counting the numbers of integers smaller than k.

If all elements in nums are smaller than k, then return nums.length

Challenge
Can you partition the array in-place and in O(n)?

Tags Expand 
Two Pointers Sort Array

Thoughts:
Two pointer sort, still similar to quick sort.
Move small to left and large to right.
When the two pinter meets, that's crossing point about pivot k

*/
public class Solution {
    /** 
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return helper(nums, 0, nums.length - 1, k);
    }

    public void swap(int[] nums, int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
    
    public int helper(int[] nums, int start, int end, int pivot) {
        int low = start;
        int high = end;
        while (low <= high) {
            while(low <= high && nums[low] < pivot) {
                low++;
            }
            while(low <= high && nums[high] >= pivot) {
                high--;
            }
            if (low <= high) {
                swap(nums, low, high);
                low++;
                high--;
            }
        }
        return low;
    }
}










