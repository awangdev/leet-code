M
1518761203

binary search. 
Goal: find peak, where both sides are descending
最左边, 最右边是Integer.MIN_VALUE时候, 也能构成中间数mid是peak的条件.

```

/**
Leet
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Note:
Your solution should be in logarithmic complexity.

 */

/*
Thoughts:
Of course can O(n) go through all and find point B where A<B, B>C.
Goal: find such point with less than O(n) => O(logn)? => binary serach

if nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1], return mid
That is: mid is at peak.
Note:
if mid - 1 < 0, we can say that from (mid-1) to (mid), it's ascending: potentially mid is at peak
if mid + 1 >= n, we can say that from (mid) to (mid + 1), it's ascending: potentially mid is at peak

Binary Search:
start <= end
start = mid + 1
end = mid - 1
*/
class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        int n = nums.length;
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if ((mid - 1 < 0 || nums[mid] > nums[mid - 1]) && (mid + 1 >= n || nums[mid] > nums[mid + 1])) {
                return mid;
            } else if (mid - 1 >= 0 && nums[mid] < nums[mid - 1]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return mid;
    }
}

// Previous notes. Previous solution is incorrect now.
/*There is an integer array which has the following features:

    * The numbers in adjacent positions are different.

    * A[0] < A[1] && A[A.length - 2] > A[A.length - 1].

We define a position P is a peek if A[P] > A[P-1] && A[P] > A[P+1].

Find a peak in this array. Return the index of the peak.

Note
The array may contains multiple peeks, find any of them.

Example
[1, 2, 1, 3, 4, 5, 7, 6]

return index 1 (which is number 2)  or 6 (which is number 7)

Challenge
Time complexity O(logN)

Tags Expand 
Binary Search Array LintCode Copyright

Thinking Process:
画图
*/

class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        int start = 1;
        int end = A.length - 2;
        int mid;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            //Tricky: only when start< mid < mid + 1, we can set start = mid;
            //This is because we are cilmbing, so going up will finally find a peak
            } else if (A[mid] > A[start] && A[mid] < A[mid + 1]) {
                start = mid;
            } else {// this case A[start] > A[mid], so we climb backwards, all make sense
                end = mid;
            }
        }//while
        
        if (A[start] > A[start - 1] && A[start] > A[start + 1]) {
            return start;
        } else {
            return end;
        } 
        
    }
}


```