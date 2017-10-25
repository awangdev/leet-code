/*
Medium Find Minimum in Rotated Sorted Array II My Submissions

40% Accepted
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.

Example
Given [4,4,5,6,7,0,1,2] return 0

Tags Expand 
Binary Search Divide and Conqueri

Thinking process:
It seems using binary search will leads to O(n), so just use a for loop with O(n)
*/

public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < num.length; i++) {
            if (min > num[i]) {
                min = num[i];
            }
        }
        return min;
    }
}

