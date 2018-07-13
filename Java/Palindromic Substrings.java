M
1531469053
tags: String, DP

根据题意, count # of palindromic substring. (不同index截取出来的substring算不同的情况)

#### isPalin[][]
- build boolean[][] to check isPalin[i][j] with DP concept
- check all candidates isPalin[][]
- O(n^2)

#### odd/even split check
https://leetcode.com/problems/palindromic-substrings/discuss/105689/Java-solution-8-lines-extendPalindrome

```
/*
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted 
as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.
*/

class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length(), count = 0;
        boolean[][] isPalin = buildPalin(s);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) count += isPalin[i][j] ? 1 : 0;
        }

        return count;
    }
    
    private boolean[][] buildPalin(String s) {
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];
        // init:
        for (int i = 0; i < n; i++) isPalin[i][i] = true;
        // Calc:
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) { // index [i, j]
                isPalin[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPalin[i + 1][j - 1]);
            }
        }
        return isPalin;
    }
}
```