/*
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

Example
Given the string = "abcdzdcab", return "cdzdc".

Challenge
O(n2) time is acceptable. Can you do it in O(n) time.

Tags Expand 
String
*/
/*
	O(n) way, not done yet
*/


/*
	O(n^2)
	Thoughts:
	Like Palindrome Partioning II, try to use isPal[i][j] to verify each string (i,j). 
	If string(i,j) is valid, note down the (i,j) portion and find the longest.
	This is a standard O(n^2) process
*/
public class Solution {
    /**
     * @param s input string
     * @return the longest palindromic substring
     */
    public String longestPalindrome(String s) {
    	if (s == null || s.length() == 0) {
    		return s;
    	}
    	boolean isPal[][] = new boolean[s.length()][s.length()];
    	String maxStr = "";
    	for (int j = 0; j < s.length(); j++) {
    		for (int i = 0; i <= j; i++) {
    			if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPal[i + 1][j - 1])) {
    				isPal[i][j] = true;
    				maxStr = maxStr.length() > s.substring(i, j + 1).length() ? maxStr : s.substring(i, j + 1);
    			}
    		}
    	}//end for j
    	return maxStr;
    }
}
