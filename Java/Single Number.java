/*
62% Accepted
Given 2*n + 1 numbers, every numbers occurs twice except one, find it.

Example
Given [1,2,2,1,3,4,3], return 4

Challenge
One-pass, constant extra space

Tags Expand 
Greedy

Manipulate bits:
Thinking process:
One-pass and constant extra space.
since all numbers appears twice, consider them as in bits format. Two identical number XOR will be zero. If we XOR everything double-numbers together, it will be zero. At the end, we use o XOR our target number, the result is actually the target number.
Very smart trick to use bits.
In order to compare from index 0 to the end, we need to extract index 0 first as result before for loop. And start for loop at i = 1.
*/

public class Solution {
	/**
	 *@param A : an integer array
	 *return : a integer 
	 */
	public int singleNumber(int[] A) {
        if (A == null || A.length == 0) {
           return 0;
        }
        int rst = A[0];
        for (int i = 1; i < A.length; i++) {
            rst = rst ^ A[i];
        }
        return rst;
	}
}



