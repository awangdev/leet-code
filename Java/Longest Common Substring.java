/*
Given two strings, find the longest common substring.

Return the length of it.

Example
Given A = "ABCD", B = "CBCE", return 2.

Note
The characters in substring should occur continuously in original string. This is different with subsequence.

Challenge
O(n x m) time and memory.

Tags Expand 
LintCode Copyright Longest Common Subsequence Dynamic Programming

Thoughts:
1. Compare all i X j.
2. Use a D[i][j] to mark the amount of common substring based on D[i - 1][j -1]. Could be 0.
3. track max length

NOTE1: create 2D array that's [N + 1][M + 1] because we want to hold D[n][M] in the 2d array
NOTE2: be carefule with init index 0's

*/


public class Solution {
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
    	if (A == null || B == null || A.length() == 0 || B.length() == 0) {
    		return 0;
    	}   
    	int [][] D = new int[A.length() + 1][B.length() + 1];
    	int max = 0;
    	for (int i = 0; i <= A.length(); i++) {
    		for(int j = 0; j <= B.length(); j++) {
    		    if (i == 0 || j == 0) {
    		        D[i][j] = 0;
    		    } else {
        			if (A.charAt(i - 1) == B.charAt(j - 1)) {
        				D[i][j] = D[i - 1][j - 1] + 1;
        			} else {
        				D[i][j] = 0;
        			}
        			max = Math.max(max, D[i][j]);
    		    }
    		}
    	}
    	return max;
    }
}
