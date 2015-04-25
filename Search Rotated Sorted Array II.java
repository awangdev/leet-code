/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.

Example
Tags Expand 
Binary Search Sorted Array Array

Thinking process:
This seems results in O(n) when allowing duplicates. Other than that, it's quite similar to Search In Rotated Sorted Array I.
*/

public class Solution {
    /** 
     * param A : an integer ratated sorted array and duplicates are allowed
     * param target :  an integer to be search
     * return : a boolean 
     */
    public boolean search(int[] A, int target) {
           // write your code here
        if (A.length == 0) {
            return false;
        }
        
        int start = 0;
        int end = A.length - 1;
        int mid;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {//Check central point
                return true;
            }
            if (A[start] < A[mid]){//1st section is continous
                if (A[start] <= target && target <= A[mid]) {//target in 1st section?
                    end = mid;
                } else {
                    start = mid;
                }
            } else {//2nd section is continous
                if (A[mid] <= target && target <= A[end]) {//target in 2nd section?
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }//While
        
        return (A[start] == target)  ||  (A[end] == target);
    }
}


