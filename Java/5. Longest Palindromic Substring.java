M
tags: String, DP
time: O(n^2)
space: O(n^2)

给一个string, 找到最长的palindrome substring.

Related: Longest Palindromic Subsequence, Palindrome Partioning II

O(n^2) is not too hard to think of. How about O(n)?

#### Method1: DP of interval
- Very similar to `216. Longest Palindromic Subsequence`, but this problem requires solid substring(i+1, j-1) to be palindromic
- Similarly: process i = n-1, from end so [i + 1, j] is always ready to consume
- boolean dp[i][j] to mark range (i, j) as palindrome or not.
- 在计算 dp[i][j]的时候, isPalin[i+1][j-1]应该已经计算过了.
- time: O(n^2) dp
- space: O(n^2)

#### String, Palindrome definition
- 从中间劈开, 遍历i: 从n个不同的点劈开: 每次劈开都看是否可以从劈开出作为palindromic的中点延伸
- palindrome两种情况: odd, even palindrome
- Worst case: 整个string都是相同字符，time complexity变成： 1 + 2 +３　＋　．．．　＋n = O(n^2)



#### O(n) 
- TODO
- https://www.felix021.com/blog/read.php?2040

```
/*
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

Example
Given the string = "abcdzdcab", return "cdzdc".

Challenge
O(n2) time is acceptable. Can you do it in O(n) time.

Hide Company Tags Amazon Microsoft Bloomberg
Hide Tags String
Hide Similar Problems (H) Shortest Palindrome (E) Palindrome Permutation


*/
// Method1: DP of interval
public class Solution {
    public String longestPalindrome(String s) {
    	if (s == null || s.length() <= 1) return s;
        int n = s.length();
    	boolean dp[][] = new boolean[n][n];
    	String str = String.valueOf(s.charAt(n - 1));
    	for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = true;
    		for (int j = i + 1; j < n; j++) {
    			if (s.charAt(i) == s.charAt(j) && (i + 1 == j || dp[i + 1][j - 1])) {
    				dp[i][j] = true;
    				str = str.length() <= (j - i + 1) ? s.substring(i, j + 1) : str;
    			}
    		}
    	}
    	return str;
    }
}


// O(n^2)
public class Solution {
	private int start, maxLen;

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
        	return s;
        }
        for (int i = 0; i < s.length() - 1; i++) {
        	findMaxLen(s, i, i); // odd middle point i
			findMaxLen(s, i, i + 1); // even s(i) == s(i+1)
        }
        return s.substring(start, start + maxLen);
    }

    public void findMaxLen(String s, int i, int j) {
    	while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
    		i--;
    		j++;
    	}
		//Note: i and j has moved apart 1 extra step after while loop
		if (maxLen < j - i - 1) {
			maxLen = j - i - 1;
			start = i + 1;
		}
    }
}



```