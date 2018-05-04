E
1525413625
tags: Array, Two Pointers

给两个排好序的数组, merge. 其中一个数组nums1有多余的位置

#### Basics
- A够长，那么可以从A的尾部开始加新元素。     
- 注意，从尾部，是大数字优先排末尾的.  

```
/*
Given two sorted integer arrays nums1 and nums2, 
merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) 
to hold additional elements from nums2. 

The number of elements initialized in nums1 and nums2 are m and n respectively.

Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
Hide Company Tags Bloomberg Facebook
Hide Tags Array Two Pointers
Hide Similar Problems (E) Merge Two Sorted Lists

*/
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return;
        }
        int end = m + n - 1;
        m--;
        n--;
        for (int i = end; i >= 0; i--) {
            if (m >= 0 && n >= 0) {
                nums1[i] = nums1[m] >= nums2[n] ? nums1[m--] : nums2[n--];
            } else {
                nums1[i] = m >= 0 ? nums1[m--] : nums2[n--];
            }
        }
    }
}

/*
Given enough space in nums1, and given size of the two array, 
we can start labling the last element, since it's empty-safe indexes anyway.
*/
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return;
        }
        int pos1 = m - 1;
        int pos2 = n - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            //Handle remaining of nums1 or nums2
            if (pos1 < 0 || pos2 < 0) {
                nums1[i] = pos1 < 0 ? nums2[pos2--] : nums1[pos1--];
            } else {
                if (nums1[pos1] >= nums2[pos2]) {
                    nums1[i] = nums1[pos1--];
                } else {
                    nums1[i] = nums2[pos2--];
                }
            }
        }
    }
}

/*Recap 02.17.2015*/
//merge from m + n position
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) {
            return;
        }
        int ind1 = m - 1;
        int ind2 = n - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            if ((ind1 >= 0 && ind2 >= 0 && nums1[ind1] >= nums2[ind2]) 
                || (ind1 >= 0 && ind2 < 0)) {
                nums1[i] = nums1[ind1];
                ind1--;
            } else if ((ind1 >= 0 && ind2 >= 0 && nums1[ind1] < nums2[ind2]) 
                || (ind1 < 0 && ind2 >= 0)) {
                nums1[i] = nums2[ind2];
                ind2--;
            }
        }
    }
}

/*
Thinking process:
1. start from the end, track back. End index = m + n;
2. when ever check a position, need to do index-1, because index=m+n is the size of array.
3. Make sure to clean up the second array B.
*/
class Solution {
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        int index = m + n;
        while (m > 0 && n > 0) {
            if (A[m - 1] > B[n - 1]) {
                A[--index] = A[--m];
            } else {
                A[--index] = B[--n];
            }
        }//While
        
        while (n > 0) {
            A[--index] = B[--n];
        }
    }
}


```