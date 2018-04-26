M
1522810454
tags: String, DP, Partition DP

给出一串数字, 要翻译(decode)成英文字母. [1 ~ 26] 对应相对的英文字母. 求有多少种方法可以decode.

#### Partition DP
- 确定末尾的2种状态: single letter or combos. 然后计算出单个letter的情况, 和双数的情况
- 定义dp[i] = 前i个digits最多有多少种decode的方法. new dp[n + 1].
- 加法原理: 把不同的情况, single-digit, double-digit 的情况加起来
- note: calculate number from characters, need to - '0' to get the correct integer mapping.
- 注意: check value != '0', 因为'0' 不在条件之中(A-Z)


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
Partition DP: a substring represents meaning.
*/
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        char[] ss = s.toCharArray();
        int[] dp = new int[n + 1];
        dp[0] = 1; // 1 to help build dp, can be: no digit, means there is only 1 way to decode: no message
        dp[1] = s.charAt(0) != '0' ? 1 : 0; // only has s.charAt(0);
        for (int i = 2; i <= n; i++) {
            int oneDigit = ss[i - 1] - '0';
            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
            }
            int twoDigit = (ss[i - 1] - '0') + 10 * (ss[i - 2] - '0');
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
     }
}



/*
Thoughts:
Find total number of ways. Think of DP.
At last two letters: treat them as 2 separeate letters, or (if possible) treat them as combo.
dp[i]: # ways to decode at index i.
dp[i - 1]: # ways to decode s[0, i-1]
dp[i - 2]: # ways to decode s[0, i-2]
dp[i] = dp[i-1] + dp[i-2]

init: 
dp[0] = decode s[0,0]: 0
dp[1] = decode s[0, 1]: 1
dp[2] = decode s[0, 2]: 1 or 2
*/
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.equals("0")) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        
        for (int i = 2; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];    
            }
            
            int twoDigit = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (twoDigit <= 26 && twoDigit >= 10) {
                dp[i] += dp[i - 2];   
            }
        }
        return dp[n];
    }
}
```