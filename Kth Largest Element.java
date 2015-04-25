/*
Find K-th largest element in an array.

Example
In array [9,3,2,4,8], the 3rd largest element is 4

In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.

Note
You can swap elements in the array

Challenge
O(n) time, O(1) space

Tags Expand 
Quick Sort Sort

Thoughts:
Almost the same as the Median problem: the only difference is, this one is not looking for the middle point, but for
the last kth element. 
Using the same quick sort code with minor modifications, and we can solve this problem.
*/


class Solution {
    //param k : description of k
    //param numbers : array of numbers
    //return: description of return
    public int kthLargestElement(int k, ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        return helper(nums, 0, nums.size() - 1, nums.size() - k);
    }
    
    public void swap( ArrayList<Integer>nums, int x, int y){
        int temp = nums.get(x);
        nums.set(x, nums.get(y));
        nums.set(y, temp);
    }
    
    public int helper( ArrayList<Integer> nums, int start, int end, int mid) {
        int pivot = end;
        int num = nums.get(pivot);
        int low = start;
        int high = end;
        while (low < high) {
            while(low < high && nums.get(low) < num) {
                low++;
            }
            while(low < high && nums.get(high) >= num) {
                high--;
            }
            swap(nums, low, high);
        }
        swap(nums, low, pivot);
        if (low == mid) {
            return nums.get(low);
        } else if (low < mid) {
            return helper(nums, low + 1, end, mid);
        } else {
            return helper(nums, start, low - 1, mid);
        }
    }
};


























