M
1525239157
tags: DP, Double Sequence DP, String, Sequence DP

#### Double Sequence DP
- 两个string, 找最值: longest common string length
- 序列型, 并且是双序列, 找两个序列 (两维的某种性质)
- dp[i][j]: 对于 A 的前i个字母, 对于 B 的前j个字母, 找最长公共substring的长度
- dp = new int[m + 1][n + 1]
- dp[i][j] = dp[i - 1][j - 1] + 1; only if A.charAt(i - 1) == B.charAt(j - 1)
- 注意track max, 最后return
- space O(n^2), time(n^2)

##### Rolling array
- 空间优化, [i] 只有和 [i - 1] 相关, 空间优化成 O(n)

#### String
- 找所有A的substring, 然后B.contains()
- track max substring length
- O(n^2) time

```
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

*/

/**
Thoughts: double sequence DP
dp[i][j]: for first i chars in A, and first j chars in B, what's the longest common substring length?

init:
if i == 0 || j == 0, no common substring, dp[i][j] = 0;

function:
dp[i][j] = dp[i - 1][j - 1] + 1; only if A.charAt(i - 1) == B.charAt(j - 1)
otherwise, dp[i][j] = 0; start over

Track max
*/
public class Solution {
    public int longestCommonSubstring(String A, String B) {
    	if (A == null || B == null || A.length() == 0 || B.length() == 0) {
    		return 0;
    	}
    	int m = A.length();
    	int n = B.length();
    	int[][] dp = new int[m + 1][n + 1];
    	int max = 0;
    	for (int i = 0; i <= m; i++) {
    		for(int j = 0; j <= n; j++) {
    		    if (i == 0 || j == 0) {
    		        dp[i][j] = 0;
    		        continue;
    		    }
    			if (A.charAt(i - 1) == B.charAt(j - 1)) {
    				dp[i][j] = dp[i - 1][j - 1] + 1;
    			} else {
    				dp[i][j] = 0;
    			}
        		max = Math.max(max, dp[i][j]);
    		}
    	}
    	return max;
    }
}

// Rolling array, space O(n)
public class Solution {
    public int longestCommonSubstring(String A, String B) {
    	if (A == null || B == null || A.length() == 0 || B.length() == 0) {
    		return 0;
    	}
    	int m = A.length();
    	int n = B.length();
    	int[][] dp = new int[2][n + 1];
    	int max = 0;
    	for (int i = 0; i <= m; i++) {
    		for(int j = 0; j <= n; j++) {
    		    if (i == 0 || j == 0) {
    		        dp[i % 2][j] = 0;
    		        continue;
    		    }
    			if (A.charAt(i - 1) == B.charAt(j - 1)) {
    				dp[i % 2][j] = dp[(i - 1) % 2][j - 1] + 1;
    			} else {
    				dp[i % 2][j] = 0;
    			}
        		max = Math.max(max, dp[i % 2][j]);
    		}
    	}
    	return max;
    }
}





/**
String: find all possible substring, try all of them
O(n^2)
 */
public class Solution {
    public int longestCommonSubstring(String A, String B) {
    	if (A == null || B == null || A.length() == 0 || B.length() == 0) {
    		return 0;
    	}
    	int n = A.length();
    	
    	int max = 0;
    	for (int i = 0; i < n; i++) {
    		for(int j = 0; j <= i; j++) {
    		    String subStr = A.substring(j, i + 1);
        		if (B.contains(subStr)) {
        		    max = Math.max(max, subStr.length());
        		}
    		}
    	}
    	return max;
    }
}

/**
Previous notes
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



```