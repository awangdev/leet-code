E
1525238120
tags: Binary Search

给一个sorted integer array, 找target出现的最后的index. array 里有重复数字

有重复,不是末尾点，继续binary search

```

/*
Find the last position of a target number in a sorted array. 
Return -1 if target does not exist.

Example
Given [1, 2, 2, 4, 5, 5].

For target = 2, return 2.

For target = 5, return 5.

For target = 6, return -1.

Tags Expand 
Binary Search
*/

/*
	Thoughts:
	Regular binary search for it.
	found condition: A[mid] == target && A[mid + 1] != target

*/	
public class Solution {
    /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public int lastPosition(int[] A, int target) {
    	if (A == null || A.length == 0) {
    		return -1;
    	}
    	int start = 0;
    	int end = A.length - 1;
    	int mid;

    	while(start + 1 < end) {
    		mid = start + (end - start)/2;
    		if (A[mid] == target) {
    			if (mid + 1 < A.length && A[mid + 1] == target) {
    				start = mid;
    			} else {
    				return mid;
    			}
    		} else if (A[mid] < target) {
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	
    	if (A[end] == target) {
    		return end;
    	} else if (A[start] == target) {
    		return start;
    	}
    	
    	return -1;
    }
}

```