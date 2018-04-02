H
1522220157
tags: Trie, DP, Double Sequence DP

给一串String, target string, int k. 找string array里面所有的candidate: 变化K次, 能变成target.

#### Trie
TODO

#### Double Sequence DP
- Edit Distance的follow up.
- 其实就是改一下 minEditDistance的function, 带入K作比较罢了.
- 写起来跟Edit Distance 的主要逻辑是一模一样的.
- 但是LintCode 86% test case 时候timeout. 
- Time O(mnh), where h = words.length, 如果 n ~ m, Time 就几乎是 O(n^2), 太慢.

```
/*
LintCode
Given a set of strings which just has lower case letters and a target string, 
output all the strings for each the edit distance with the target no greater than k.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example
Given words = ["abc", "abd", "abcd", "adc"] and target = "ac", k = 1
Return ["abc", "adc"]
*/

/*
Thoughts:
We can calculate min moves needed to reach target with dp
dp[i][j] represents # steps to convert S[0, i - 1] to T[0, j - 1]
dp[m][n] is the minimum moves

It's double sequence dp, initialize with [m+1][n+1]
dp[0][0] = 0. no sequence to compare/edit.
init: if i == 0, take j steps to become T. if j == 0, takes i steps to become S.

Apply the dp for all words

Some pre-validations: 
- length diff > k, skip
- equal to target: just return
*/
public class Solution {
    public List<String> kDistance(String[] words, String target, int k) {
        List<String> rst = new ArrayList<>();
        if (words == null || words.length == 0 || target == null || k <= 0) {
            return rst;
        }
        for (String word: words) {
            if (validate(word, target, k)) {
                rst.add(word);
            }
        }
        return rst;
    }
    
    private boolean validate(String word, String target, int k) {
        if (word.equals(target)) {
            return true;
        }
        if (Math.abs(word.length() - target.length()) > k) {
            return false;
        }
        
        int m = word.length();
        int n = target.length();
        
        int[][] dp = new int[2][n + 1];
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i % 2][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[i % 2][j] = i;
                    continue;
                }
                
                dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j - 1], Math.min(dp[i % 2][j - 1], dp[(i - 1) % 2][j])) + 1;
                if (word.charAt(i - 1) == target.charAt(j - 1)) {
                    dp[i % 2][j] = Math.min(dp[i % 2][j], dp[(i - 1) % 2][j - 1]);
                }
            }
        }
        return dp[m % 2][n] <= k;
    }
}
```