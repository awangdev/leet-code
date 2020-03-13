H
tags: String, DP, DFS, Memoization
time: O(n^2)
space: O(n^2)

#### Method1: DP, utilize `Longest Palindrome Subsequence`
- Transform the problem:
    - `removing at most k items to make it a palindrome`
    - that is: find the longest palindrome subsequence with length x, such that `n - x <= k`
- `516. Longest Palindromic Subsequence` utilizes Interval DP to find LPS length x
- at the end, perform n - x <= k?
- time: O(n^2) to find LPS
- space: O(n^2) for dp

#### Method2: DFS with Memo
- Either times out or too much space used
- time: O(n^2)
- space: O(n^2) or O(k*n^2)

```
/**
Given a string s and an integer k, find out if the given string is a K-Palindrome or not.

A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.

 

Example 1:

Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.
 

Constraints:

1 <= s.length <= 1000
s has only lowercase English letters.
1 <= k <= s.length
*/

// Method1: DP Find Longest Palindrome Subsequence length
class Solution {
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        int lps = findLpsLength(s);
        return Math.abs(lps - n) <= k;
    }
    
    private int findLpsLength(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--){
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i + 1][j - 1] + 2;
                else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }
}

/*
Method2:
- top-down approach: pick non-equal left/right point to skip, and recursively procceed
- dfs(s, left, right, k)
- use boolean memo[left][right] to cache calculated range to avoid repeating
- end state: 
    - if visited, return val
    - k == 0, return if left and right char equal
  
*/
// Option1: with 3d memo. exceed memory: O(n^k) space
class Solution {
    Boolean[][][] memo;
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        memo = new Boolean[n][n][k + 1];
        return dfs(s, 0, n - 1, k);
    }
    
    private boolean dfs(String s, int left, int right, int k) {
        if (left >= right) return true;
        if (memo[left][right][k] != null) return memo[left][right][k];
        
        char a = s.charAt(left), b = s.charAt(right);
        if (a == b) memo[left][right][k] = dfs(s, left + 1, right - 1, k);
        else if (k > 0) memo[left][right][k] = dfs(s, left + 1, right, k - 1) || dfs(s, left, right - 1, k - 1);
        else memo[left][right][k] = false;
        
        return memo[left][right][k];
    }
}


// Option2: with hashmap of custom key; timeout: O(n^2) time
class Solution {
    Map<String, Boolean> memo;
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        memo = new HashMap<>();
        return dfs(s, 0, n - 1, k);
    }
    
    private boolean dfs(String s, int left, int right, int k) {
        if (left >= right) return true;
        String key = getKey(left, right, k);
        if (memo.containsKey(key)) return memo.get(key);
        
        char a = s.charAt(left), b = s.charAt(right);
        if (a == b) memo.put(key, dfs(s, left + 1, right - 1, k));
        else if (k > 0) memo.put(key, dfs(s, left + 1, right, k - 1) || dfs(s, left, right - 1, k - 1));
        else memo.put(key, false);
        
        return memo.get(key);
    }
    
    private String getKey(int left, int right, int k) {
        return String.format("%s@%s@%s", left, right, k);
    }
}
```