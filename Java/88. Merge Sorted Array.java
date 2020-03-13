E
tags: Array, Two Pointers
time: O(n)
space: O(1)

给两个排好序的数组, merge. 其中一个数组nums1有多余的位置

#### Basics
- A够长，那么可以从A的尾部开始加新元素: 从尾部，是大数字优先排末尾的.  
- Deal with remaining:
    - When A values are used up, put remian of B into it
    - When B values are finished, there is nothing todo. The remain of A is already in place.

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


/*
Thinking process:
1. start from the end, track back. End index = m + n;
2. when ever check a position, need to do index-1, because index=m+n is the size of array.
3. Make sure to clean up the second array B.
*/
class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        // write your code here
        int index = m + n - 1;
        while (m > 0 && n > 0) {
            if (A[m - 1] > B[n - 1]) {
                A[index--] = A[--m];
            } else {
                A[index--] = B[--n];
            }
        }//While
        
        // Put remain of B
        while (n > 0) {
            A[index--] = B[--n];
        }
    }
}

// Some variations of the same concept:

// similar, two pointer moving, with using combined end `int index = m + n - 1;`
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        m--; n--;
        while (m >= 0 || n >= 0) {
            if (m >= 0 && n >= 0) {
                nums1[m + n + 1] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
            } else if (m < 0) { // n > 0
                nums1[n] = nums2[n--];
            } else if (n < 0) {
                break;
            }
        }
    }
}


// two pointer, with end index, and for loop
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


```
