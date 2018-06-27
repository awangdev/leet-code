M
1530027760
tags: String, DP

给一个string, 找到最长的palindrome substring.

Related: Longest Palindromic Subsequence, Palindrome Partioning II

O(n^2) is not too hard to think of. How about O(n)?

#### String, Palindrome definition
- 从中间劈开, 遍历i: 从n个不同的点劈开: 每次劈开都看是否可以从劈开出作为palindromic的中点延伸
- palindrome两种情况: odd, even palindrome
- Worst case: 整个string都是相同字符，time complexity变成： 1 + 2 +３　＋　．．．　＋n = O(n^2)

#### DP: isPalin[][]
- 穷举double for loop. O(n^2)
- boolean isPalin[i][j], 每次确认有palindrome就记录下来true / false
- 穷举的for loop计算顺序: end point j, and stat point i = [0, j]
- 在计算 isPalin[i][j]的时候, isPalin[i+1][j-1]应该已经计算过了.
- double for loop: O(n^2). slower, because it guarantees O(n^2) due to the for loop

#### O(n) 
- TODO
- https://www.felix021.com/blog/read.php?2040

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

/*
	O(n^2), but time exceeded
	Thoughts:
	Like Palindrome Partioning II, try to use isPal[i][j] to verify each string (i,j). 
	If string(i,j) is valid, note down the (i,j) portion and find the longest.
	This is a standard O(n^2) process
*/
public class Solution {
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