M
tags: Sort, Two Pointers, Array

```
/*
Given two array of integers(the first array is array A, the second array is array B), now we are going to find a element in array A which is A[i], and another element in array B which is B[j], so that the difference between A[i] and B[j] (|A[i] - B[j]|) is as small as possible, return their smallest difference.


Example
For example, given array A = [3,6,7,4], B = [2,8,9,3], return 0

Challenge
O(n log n) time

Tags Expand 
Two Pointers LintCode Copyright Sort Array
*/

/*
	Thoughts:
	Sort A, B. O(nLogn)
	Use smaller array to binarh in longer array. n * logn

*/


public class Solution {
    /**
     * @param A, B: Two integer arrays.
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
    	if (A == null || A.length == 0 || B == null || B.length == 0) {
    		return 0;
    	}
    	if (A.length > B.length) {
    		int[] temp = A;
    		A = B;
    		B = temp;
    	}
    	Arrays.sort(A);
    	Arrays.sort(B);
    	int diff = Integer.MAX_VALUE;	

    	for (int i = 0; i < A.length; i++) {//10
    		int start = 0;
    		int end = B.length - 1;
    		int mid;
    		//Small enhancement
    		if (B[start] >= A[A.length - 1]) {
            	return B[start] - A[A.length - 1];
            }
            if (A[start] >= B[B.length - 1]) {
            	return A[start] - B[B.length - 1];
            }
    		while (start + 1 < end) {
    			mid = start + (end - start)/2;
    			if (B[mid] == A[i]) {
    				return 0;
    			} else if (mid - 1 >= 0 && B[mid - 1] < A[i] && A[i] < B[mid]) {
    				diff = Math.min(diff, Math.min(A[i] - B[mid - 1], B[mid] - A[i]));
    				break;
    			} else if (mid + 1 < B.length - 1 && B[mid] < A[i] && A[i] < B[mid + 1]) {
    				diff = Math.min(diff, Math.min(A[i] - B[mid], B[mid + 1] - A[i]));
    				break;
    			} else if (B[mid] > A[i]) {
    				end = mid;
    			} else {
    				start = mid;
    			}
    		
    		}//end while
        	if (start + 1 >= end) {
        	    int min = Math.min(Math.abs(A[i] - B[start]), Math.abs(A[i] - B[end]));
				diff = Math.min(diff, min);
			}
    	}//end for
    	return diff;
    }
}









```