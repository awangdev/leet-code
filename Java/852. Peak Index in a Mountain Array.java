E
tags: Binary Search
time: O(logn)
space: O(1)

#### Binary Search
- binary search to find A[i-1] < A[i] < A[i+1]
    - if [mid-1] < [mid+1], on left slope, start = mid
    - if [mid-1] > [mid+1], on right slope, end = mid
- init: start == 1, end = n - 2;

```
/**
Let's call an array A a mountain if the following properties hold:

A.length >= 3
There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].

Example 1:

Input: [0,1,0]
Output: 1
Example 2:

Input: [0,2,1,0]
Output: 1
Note:

3 <= A.length <= 10000
0 <= A[i] <= 10^6
A is a mountain, as defined above.
 */

/*
- binary search to find A[i-1] < A[i] < A[i+1]
- init: start == 1, end = n - 2;
- if [mid-1] < [mid+1], on left slope, start = mid
- if [mid-1] > [mid+1], on right slope, end = mid
*/
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int start = 1, end = A.length - 2;
        int mid = start + (end - start)/2;
        while (start + 1 < end) {
            mid = start + (end - start)/2;
            if (A[mid-1] < A[mid] && A[mid] > A[mid+1]) return mid;
            else if (A[mid-1] < A[mid+1]) start = mid;
            else if (A[mid-1] > A[mid+1]) end = mid;
        }
        return A[start] < A[end] ? end : start;
    }
}

```