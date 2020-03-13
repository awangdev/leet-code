M
tags: Array, Binary Search
time: O(logn)
space: O(1)

#### Binary Search
- 关键点, 是找到 [mid]是在左边/还是右边的continous increasing subarray: 比较 `A[start] < A[mid]`
- 在两个section 里面分别讨论 target 的位置     
    - 1. `nums[start] < nums[mid]`: start是从index=0开始的, 那就说明 `mid在前半段`
    - 2. `nums[start] > nums[mid]`: start是从index=0开始的, 那就说明 `mid在后半段`
- Binary search template: 
    - 1) `start + 1 < end` (adjacent indexes)
    - 2) start/end = mid, 
    - 3) compare start and end individually

#### binary search break point, 然后继续binary search target
- 1. binay search break point     
- 2. binary search target      
- 注意等号，在判断target在前半段还是后半段：if (A[p1] <= target && target <= A[breakPoint])      



```
/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
*/



/*
    Just 1 binary search: this is the better solution
    //Observation: 
    //1. There is only one break point
    //2. There has to be a side that's continous, either first section or second section.
    //3. Need to locate that continous section, then check if target is part of the continous section

*/
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            if (nums[start] < nums[mid]) { //Land in 1st continous section
                if (nums[start] <= target && target <= nums[mid]) end = mid;
                else start = mid;
            } else { //Land in 2nd continous section
                if (nums[mid] <= target && target <= nums[end]) start = mid;
                else end = mid;
            }
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        
        return -1;
    }
}



// Solution2: Find the break point, then decide which section to binary search
public class Solution {
    
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int p1 = 0;
        int p2 = A.length - 1;
        int start = p1;
        int end = p2;
        int mid = start + (end - start)/2;
        //find break point
        int breakPoint = 0;
        while (start + 1 < end) {
            mid = start + (end - start)/2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] >= A[p1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[start] > A[end]) {
            breakPoint = start;
        } else {
            breakPoint = end;
        }

        if (A[p1] <= target && target <= A[breakPoint]) {
            start = p1;
            end = breakPoint;
        } else {
            start = breakPoint + 1;
            end = p2;
        }

        //search for target
        while (start + 1 < end) {
            mid = start + (end - start)/2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        } 

        if (A[start] == target) {
            return start;
        } else if (A[end] == target) {
            return end;
        } 
        return -1;
    }
}




```