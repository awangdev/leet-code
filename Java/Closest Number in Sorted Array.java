E
1522011280
tags: Binary Search

- Binary Search 的一种变型, LintCode无法再跑一边.
- 考虑mid-1, mid+1.
- 一旦没有mid = target.index。 那么target最终就narrow down在(mid-1,mid) 或者(mid,mid+1)   

```
/*
LintCode
Given a target number and an integer array A sorted in ascending order, 
find the index i in A such that A[i] is closest to the given target.

Return -1 if there is no element in the array.

Example
Given [1, 2, 3] and target = 2, return 1.

Given [1, 4, 6] and target = 3, return 1.

Given [1, 4, 6] and target = 5, return 1 or 2.

Given [1, 3, 3, 4] and target = 2, return 0 or 1 or 2.

Note
There can be duplicate elements in the array, and we can return any of the indices with same value.

Challenge
O(logn) time complexity.

Tags Expand 
Binary Search
*/
/*
    Thoughts:
    Do binary search. 
    Check the state of A[mid] < target < A[mid + 1] or A[mid - 1] < target < A[mid]
        return the index that creates smallest diff.
*/

public class Solution {
    public int closestNumber(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (mid - 1 >= 0 && A[mid - 1] <= target && target < A[mid]) {
                return (target - A[mid - 1]) < (A[mid] - target) ? (mid - 1) : mid; 
            } else if (mid + 1 < A.length && A[mid] < target && target <= A[mid + 1]) {
                return (target - A[mid]) < (A[mid + 1] - target) ? mid : mid + 1;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return (target - A[start]) < (A[end] - target) ? start : end;
    }
}

```