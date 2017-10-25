M

方法1: 从中间劈开. 遍历i，从n个不同的点劈开：每次劈开都看是否可以从劈开出作为palindromic的中点延伸。   
   Worst case: 整个string都是相同字符，time complexity变成： 1 + 2 +３　＋　．．．　＋n = O(n^2)

方法2: 穷举double for loop. O(n^2)


```
/*
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

Example
Given the string = "abcdzdcab", return "cdzdc".

Challenge
O(n2) time is acceptable. Can you do it in O(n) time.

Hide Company Tags Amazon Microsoft Bloomberg
Hide Tags String
Hide Similar Problems (H) Shortest Palindrome (E) Palindrome Permutation


*/

/*
	O(n) way, not done yet
*/


/*
	02.16.2016 recap: Worst case still O(n^2)
	Find index i to split S into left and right. Check if from i's two sides can form a palindrom.
	If so, mark the longest, then keep increasing i.
*/
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
        	return s;
        }
        String rst = "";
        for (int i = 0; i < s.length(); i++) {
        	if (i - 1 >= 0 && s.charAt(i - 1) == s.charAt(i)) {
        		rst = checkPalindrom(s, i-1, i, rst);
        	}
        	if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
        		rst = checkPalindrom(s, i, i+1, rst);
        	}
        	if (i - 1 >= 0 && i + 1 < s.length() && s.charAt(i - 1) == s.charAt(i + 1)) {
        		rst = checkPalindrom(s, i-1, i+1, rst);
        	}
        }
        return rst;
    }

    public String checkPalindrom(String s, int start, int end, String rst) {
    	while (start - 1 >= 0 && end + 1 < s.length() && s.charAt(start - 1) == s.charAt(end + 1)) {
    		start--;
    		end++;
    	}
		if (rst.length() < s.substring(start, end + 1).length()) {
			rst = s.substring(start, end + 1);
		}
    	return rst;
    }
}

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



```