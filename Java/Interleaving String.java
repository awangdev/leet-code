/*
Given three strings: s1, s2, s3, determine whether s3 is formed by the interleaving of s1 and s2.

Example
For s1 = "aabcc", s2 = "dbbca"

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
Challenge
O(n2) time or better

Tags Expand 
Longest Common Subsequence Dynamic Programming

Attempt2: DP[i][j]: boolean that if first S1(i) chars and first S2(j) chars can interleavign first S3(i + j)
Match one char by one char. We have 2 conditions: match s1 or s2 char, Let's do double-for-loop on s1 and s2
1. match s1: s3.charAt(i + j -1) == s1.charAt(i - 1) && DP[i - 1][j]; // makes sure DP[i-1][j] also works before adding s1[i-1] onto the match list
2. match s2: s3.charAt(i + j -1) == s2.charAt(j - 1) && DP[i][j - 1]// similar as above

Note:
Need to initiate the starting conditions with just s1, or just s2
Note2:
DP ususally start i == 1, and always use (i - 1) in the loop... this is all because we are trying to get DP[i][j], which are 1 index more than length
*/
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3 == null || (s1 == null && s2 == null) || s1.length() + s2.length() != s3.length()) {
        	return false;
        }
        boolean[][] DP = new boolean[s1.length() + 1][s2.length() + 1];
        DP[0][0] = true; // empty s1 and s2 would be a working case

        //with just s1:
        for (int i = 1; i <= s1.length(); i++) {
        	if (s3.charAt(i - 1) == s1.charAt(i - 1) && DP[i - 1][0]) {
        		DP[i][0] = true;
        	}
        }

        //with just s2:
        for (int j = 1; j <= s2.length(); j++) {
        	if (s3.charAt(j - 1) == s2.charAt(j - 1) && DP[0][j - 1]) {
        		DP[0][j] = true;
        	}
        }

        for (int i = 1; i <= s1.length(); i++) {
        	for (int j = 1; j <= s2.length(); j++) {
        		if ((s3.charAt(i + j - 1) == s1.charAt(i - 1) && DP[i - 1][j]) 
        			|| (s3.charAt(i + j - 1) == s2.charAt(j - 1) && DP[i][j - 1])) {
        			DP[i][j] = true;
        		}
        	}
        }

        return DP[s1.length()][s2.length()];
    }
}




/*

Attempt1, Incorrect: tho, magically passed 91% of lintcode, by coincidence
This solution could goes on and on with s1, and failed at certain point when j == 0 does not fit in.
s1 = "sdfjas;dfjoisdu"
s2 = "dfnakd"
s3 = "sdfjas;dfjoisdf..." // Failed at that 'f' in s3

Thoughts:
DP[mxn]: loop through S1.length and S2.length, record DP[k] = true or false.
DP[k] = (S1(0~i) + S2(0 ~ j)) is leading S3: index of (xxx) == 0.

*/
public class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3 == null || (s1 == null && s2 == null) || s1.length() + s2.length() != s3.length()) {
        	return false;
        }

        int i = 0; 
        int j = 0;
        String base = "";
        for (int k = 0; k < s1.length()*s2.length() - 1; k++) {
        	if (i < s1.length() || j < s2.length()) {
	        	if (i < s1.length() && s3.indexOf(base + s1.charAt(i)) == 0) {
	        		base += s1.charAt(i);
	        		i++;
	        	} else if (j < s2.length() && s3.indexOf(base + s2.charAt(j)) == 0) {
	        		base += s2.charAt(j);
	        		j++;
	        	} else {
	        		return false;
	        	}
        	}
        }
        return true;
    }
}
