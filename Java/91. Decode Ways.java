M
tags: String, DP, Partition DP
time: O(n)
space: O(n)

给出一串数字, 要翻译(decode)成英文字母. [1 ~ 26] 对应相对的英文字母. 求有多少种方法可以decode.

#### Method1: DP, Bottom-Up by calculating base case first
- 加法原理: 根据题意, 有 range = 1 的 [1, 9], range = 2 的 [10~26] 来作为partition.
- there can be 2 final states at dp[i]: single digit or double digit.
    - validate if `single digit`, dp[i] += dp[i - 1]. Last 1 digit can be decoded.
    - validate if `double digit`, dp[i] += dp[i - 2]. Last 2 digits can be decoded.
- note: 
    - get number from char: `c - '0'`
    - validatae single digit != '0', 因为'0' 不在条件之中(A-Z)
- Option1: dp[i] as # ways to decode at index i, including letter i
    - The validation is done on: 1) single digit i, or 2) double digit [i-1, i]
- Option2: Partition DP, dp[i] as # ways to decode for first i letters (excluding letter i)
    - 定义`dp[i] = 前i个digits最多有多少种decode的方法`: new dp[n + 1].
    - dp[i] += dp[i - x], where x = 1, 2
    - The validation is done on: 1) single digit [i-1], or 2) double digit [i-2, i-1]
    - Option2 is better in terms of clean coding. We assume `dp[0]=1` as 1 way to decode 0 digits.
        - No need to specially handle length == 1, because it is covered later
        - No need to manualy init the first 2-digit case as in Option1
        - Return of `dp[n]` is clean
- 引申: 这里只有两种partition的情况 range=1, range =2.  如果有更多partition的种类, 就可能多一层for loop做循环


#### Method2: DFS, Top-Down
- if single-digit is working, sum += dfs(s, i+1);
- if double-digit is working, sum += dfs(s, i+2);
- end case: i >= n, return 0; i == n - 1; i == n - 2
    - especially when i = n - 2, handle 2-digt edge case carefully:
        - 1) check if two-digit range [i, i+1] is valid
        - 2) check if single-digit [i] is valid; if so, += dfs(s, i + 1)
- memo[i]: # ways to decode from [i, n). init with `memo[i]=-1`

```
/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/




/*
Method1, Option1:
Treating dp[i] as # ways to decode at index i, including letter i
Find total number of ways. Think of DP.
At last two letters: treat them as 2 separeate letters, or (if possible) treat them as combo.
dp[i]: # ways to decode at index i, including letter i
dp[i - 1]: # ways to decode s[0, i-1]
dp[i - 2]: # ways to decode s[0, i-2]
dp[i] = dp[i-1] + dp[i-2]
*/
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.equals("0")) return 0;
        if (s.length() == 1) return 1;

        int n = s.length();
        int[] dp = new int[n];
        dp[0] = s.charAt(0) != '0' ? 1 : 0;
        dp[1] = s.charAt(1) != '0' ? dp[0] : 0;
        dp[1] += check(s, 1) ? 1 : 0;
        
        for (int i = 2; i < n; i++) {
            if (s.charAt(i) != '0') dp[i] = dp[i - 1]; // possible to decode with just letter at i
            if (check(s, i)) dp[i] += dp[i - 2]; // possible to decode with (i-1, i)
        }
        return dp[n - 1];
    }
    
    private boolean check(String s, int i) {
        int twoDigit = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
        return twoDigit <= 26 && twoDigit >= 10;
    }
}

/*
Method1, Option2:
Partition DP: a substring represents meaning.
*/
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // 1 to help build dp, can be: no digit, means there is only 1 way to decode: no message
        dp[1] = s.charAt(0) != '0' ? 1 : 0; // only has s.charAt(0);
        for (int i = 2; i <= n; i++) {
            if (s.charAt(i - 1) != '0') dp[i] += dp[i - 1];
            if (check(s, i - 1)) dp[i] += dp[i - 2];
        }
        return dp[n];
    }

    private boolean check(String s, int i) {
        int twoDigit = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
        return twoDigit <= 26 && twoDigit >= 10;
    }
}


/*
Method2: DFS, Top-Down
- if single-digit is working, sum += dfs(s, i+1);
- if double-digit is working, sum += dfs(s, i+2);
- end case: i >= n, return 0; i == n - 1; i == n - 2
- memo[i]: # ways to decode from [i, n)

*/
class Solution {
    int[] memo;

    public int numDecodings(String s) {
        if (s == null) return 0;
        memo = new int[s.length()];
        for (int i = 0; i < s.length(); i++) memo[i] = -1;
        return dfs(s, 0);
    }

    private int dfs(String s, int i) {
        if (i >= s.length()) return 0;
        if (memo[i] != -1) return memo[i];

        if (i == s.length() - 1) return (s.charAt(i) != '0') ? 1 : 0;
        if (i == s.length() - 2) {
            int sum = check(s, i) ? 1 : 0;
            if (s.charAt(i) != '0') sum += dfs(s, i + 1);
            return sum;
        }

        int sum = (s.charAt(i) != '0') ? dfs(s, i + 1) : 0;
        sum += check(s, i) ? dfs(s, i + 2) : 0;
        
        memo[i] = sum;
        return memo[i];
    }
    
    private boolean check(String s, int i) {
        if (i + 1 >= s.length()) return false;
        int twoDigit = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
        return twoDigit <= 26 && twoDigit >= 10;
    }
}
```