/*
Given an unsorted integer array, find the first missing positive integer.

Example
Given [1,2,0] return 3, and [3,4,-1,1] return 2.

Challenge
Your algorithm should run in O(n) time and uses constant space.

Tags Expand 
Array

Thoughts:
It means: after it's sorted, what's the first missing postive int counted from 1 ---> more

1. Arrays.sort();
2. count = first non-zero element in A.
3. count +1, and see if maches the current A[i]?

NOTE:
Deal with negative and positive number separately
Watch out for redundant number: ask if the list has duplicated elements
*/


public class Solution {
    /**    
     * @param A: an array of integers
     * @return: an integer
     */
    public int firstMissingPositive(int[] A) {
    	if (A == null || A.length == 0) {
    		return 1;
    	}
    	Arrays.sort(A);
    	int count = -1;
    	for (int i = 0; i < A.length; i++) {
    		if (A[i] > 0) {
    			if (count < 0) {//process 1st positive element
	    			count = A[i];
	    			if (count != 1) {
	    				return 1;
	    			}
    			} 
    			else if (A[i] == A[i - 1]) {//watch out for duplicates
    				count--;
    			}
				else if(A[i] != count) {//if not match, kick out
					return count;
				}	
    			count++;
    		}
    	}
    	if (count < 0) {//if all negative, return 1
    		return 1;
    	}
    	return count;
    }
}