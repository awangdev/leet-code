H

方法1：O(logn)
    还是把它先当做正常的sorted list开始搜。    
    但是在比较的时候，多比较一个A[start] < A[mid]?     
    在1 和 2 里面分别讨论 target 的位置     
        1. A[start] < A[mid] ?     
            说明在前半段    
            - start<target<mid     
            - target > mid      
        2. A[start] > A[mid]     
            说明 start 还在前半段，而mid在后半段     
            - mid < target < end     
            - target < mid     

   

方法2：O(logn)     
    1. binay search break point     
    2. binary search target      
    注意等号，在判断target在前半段还是后半段：if (A[p1] <= target && target <= A[breakPoint])      



```
/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Example
For [4, 5, 1, 2, 3] and target=1, return 2

For [4, 5, 1, 2, 3] and target=0, return -1

Tags Expand 
Binary Search Array Sorted Array
*/



/*
    Just 1 binary search: this is the better solution
    //Observation: 
    //1. There is only one break point
    //2. There has to be a side that's continous, either first section or second section.
    //3. Need to locate that continous section, then check if target is part of the continous section

*/
public class Solution { 
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

/*
    Easy solution:
    Find the break point, then decide which section to binary search
*/
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