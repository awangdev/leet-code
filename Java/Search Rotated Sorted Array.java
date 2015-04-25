/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Example
For [4, 5, 1, 2, 3] and target=1, return 2

For [4, 5,1, 2, 3] and target=0, return -1

Tags Expand 
Binary Search Array Sorted Array
*/

public class Solution {
    /** 
     *@param A : an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer
     */
    //Observation: 
    //1. There is only one break point
    //2. There has to be a side that's continous, either first section or second section.
    //3. Need to locate that continous section, then check if target is part of the continous section
    public int search(int[] A, int target) {
        // write your code here
        if (A.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = A.length - 1;
        int mid;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {//Check central point
                return mid;
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
        
        if (A[start] == target) {
            return start;
        } else if (A[end] == target) {
            return end;
        } else {
            return -1;
        }
    }
}

