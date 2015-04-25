/*
28% Accepted
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0

Tags Expand 
Binary Search Array Sorted Array
*/

public class Solution {
    /** 
     * param A : an integer sorted array
     * param target :  an integer to be inserted
     * return : an integer
     */
    public int searchInsert(ArrayList<Integer> A, int target) {
        // write your code here
        int start = 0;
        int end = A.size() - 1;
        int mid;
        if (A == null || A.size() == 0 || target <= A.get(0)) {
            return 0; 
        }
        //find the last number less than target
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A.get(mid) == target) {//Since no duplicates, return when found
                return mid;
            } else if (A.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            } 
        }
        //Always 2 elements left to check, first:start, second: end
        if (A.get(end) == target) {
            return end;
        }
        if (A.get(end) < target) {
            return end + 1;
        }
        if (A.get(start) == target) {
            return start;
        } 
        return start + 1;
        
    }
}

