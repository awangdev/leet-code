/*
Give you an integer array (index from 0 to n-1, where n is the size of this array)，find the longest increasing continuous subsequence in this array. (The definition of the longest increasing continuous subsequence here can be from right to left or from left to right)

Example
For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.

For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.

Note
O(n) time and O(1) extra space.

Tags Expand 
Dynamic Programming Array

Thoughts:
1. Use a hashmap for records <key, value>: <maxLength, endIndex>
2. Reverse search for the same int[] A.
	In 1,2 keep track of maxLength1. maxLength2
3. Compare maxLength, and use the largest

Note:
After for loop, need to catch the very last comparison, to get result of track ;)
*/

//[5,4,2,1,3]
public class Solution {
    /**
     * @param A an array of Integer
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
    	if (A == null || A.length == 0) {
    		return -1;
    	}
    	int maxLength1 = 0;
    	int maxLength2 = 0;
    	int track = 1;
    	for (int i = 1; i < A.length; i++) {
    		if (A[i] > A[i - 1]) {
    			track++;
    		} else {
    			maxLength1 = track > maxLength1 ? track : maxLength1;
    			track = 1;
    		}
    	}
    	maxLength1 = track > maxLength1 ? track : maxLength1;
    	track = 1;
    	for (int i = A.length - 2; i >= 0; i--) {
    		if (A[i] > A[i + 1]) {
    			track++;
    		} else {
    			maxLength2 = track > maxLength2 ? track : maxLength2;
    			track = 1;
    		}
    	}
    	maxLength2 = track > maxLength2 ? track : maxLength2;
    	return maxLength1 > maxLength2 ? maxLength1 : maxLength2;
    }
}

