M

想法很简单。写起来有点长。
找total number of occurance. 首先找first occurance, 再找last occurance.

```
/*
Total Occurrence of Target

Given a target number and an integer array sorted in ascending order. Find the total number of occurrences of target in the array.

Example
Given [1, 3, 3, 4, 5] and target = 3, return 2.

Given [2, 2, 3, 4, 6] and target = 4, return 1.

Given [1, 2, 3, 4, 5] and target = 6, return 0.

Challenge
Time complexity in O(logn)

Tags Expand 
Binary Search
*/

/*
	Thought:
	Similar to find last occurance of the target. Now: find the occurance, jump out. Find front, end occurance index.
*/
public class Solution {
    /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public int totalOccurrence(int[] A, int target) {
    	if (A == null || A.length == 0) {
    		return 0;
    	}
    	int start = 0;
    	int end = A.length - 1;
    	int mid = start + (end - start)/2;
		//Find first occurance
    	int first = 0;
    	int last = 0;
    	while (start + 1 < end){
    		mid = start + (end - start)/2;
    		if (A[mid] == target) {
				if (mid - 1 >= 0 && A[mid - 1] == target) {
					end = mid;
				} else {
					break;
				}
			} else if (A[mid] < target) {
				start = mid;
			} else {
				end = mid;
			}
    	}
    	if (A[start] == target) {
    		first = start;
    	} else if (A[mid] == target){
    		first = mid;
    	} else if (A[end] == target){
    		first = end;
    	} else {
    		return 0;
    	}
    	//If no 2nd occurance, just return 
    	if (mid + 1 < A.length && A[mid + 1] != target) {
    		return 1;
    	}

    	//Find last occurance
    	start = first;
    	last = start + 1;
    	end = A.length - 1;
    	while (start + 1 < end){
    		mid = start + (end - start)/2;
    		if (A[mid] == target) {
				if (mid + 1 < A.length && A[mid + 1] == target) {
					start = mid;
				} else {
					break;
				}
			} else if (A[mid] < target) {
				start = mid;
			} else {
				end = mid;
			}
    	}

    	if (A[end] == target) {
    		last = end;
    	} else if (A[mid] == target){
    		last = mid;
    	} else if (A[start] == target) {
    		last = start;
    	} 
    	return last - first + 1;
    }
}

```