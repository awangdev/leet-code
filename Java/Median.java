/*
Given a unsorted array with integers, find the median of it. 

A median is the middle number of the array after it is sorted. 

If there are even numbers in the array, return the N/2-th number after sorted.

Example
Given [4, 5, 1, 2, 3], return 3

Given [7, 9, 4, 5], return 5

Challenge
O(n) time.

Tags Expand 
LintCode Copyright Quick Sort Array


*/
/*
    Recap 12.09.2015.
    O(n) means just run through it. It's similar to Partition array: it tries to split the list into 2 parts, and find the pivot.
*/

/*
Thoughts:
Use standard quick sort, but the goal is to look for the middle point. 
1. Get middle point: remember to -1 because we are looking for position, rather than length.
2. Increase low pointer until find a point >= piviot
3. Decrease high pointer until find a point < poviot
4. Swap the low and high: this set the first value greather than pivot to the right, and first avlue less than pivot to the left.
5. after low and high pointer meets, swap low with the piviot: simply because pivot should be the break point of low and high
6. at the end, the low sould be the middle point, which is the point we are looking for. return corresponding recursive helper.

*/
public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: An integer denotes the middle number of the array.
     */
    public int median(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length % 2 == 0) {
            return helper(nums, 0, nums.length - 1, nums.length/2 - 1);
        } else {
            return helper(nums, 0, nums.length - 1, nums.length/2);
        }
    }
    
    public void swap(int[] nums, int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
    
    public int helper(int[] nums, int start, int end, int mid) {
        int pivot = end;
        int num = nums[pivot];
        int low = start;
        int high = end;
        while (low < high) {
            while(low < high && nums[low] < num) {
                low++;
            }
            while(low < high && nums[high] >= num) {
                high--;
            }
            swap(nums, low, high);
        }
        swap(nums, low, pivot);
        if (low == mid) {
            return nums[low];
        } else if (low < mid) {
            return helper(nums, low + 1, end, mid);
        } else {
            return helper(nums, start, low - 1, mid);
        }
    }
}

























