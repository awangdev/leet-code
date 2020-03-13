M
tags: DP, Sequence DP, Hash Table
time: O(n^2)
space: O(n)

给一个String word, 和一个字典, 检查是否word可以被劈开, 而所有substring都应该是dictionary里面的words.

#### Sequence DP
- Bottom-up, test simply case. Sequence DP.
- true/false problem, think about dp
    - 子问题: 前i个字母, 是否可以有valid break
    - check: 1) dp[j] &&  2) `if substring(j, i) valid`, for all j = [0 ~ i]
    - dp = new boolean[n + 1]; dp[0] = true;
    - test: `dp[i] |= dp[j] == true && word[j, n] in dict`. 
    - Need iterate over i = [0 ~ n], also j = [0, i]
    - When there is a way to make dp[i] == true, then break the [j ~ i] loop, move on to test dp[i++]
- Use set dict: `dict.contains()`
- Improvement: O(n) to figure out max length, so we can skip some substring[j~i] dict.contains()
- overall O(n^2) time since the double for loop

#### DFS
- Top-Down, break into small problems: Check front subString, and put the rest substring into dfs to test
- Memoization: for tested failed substring, record and do NOT test them again.
- Same Improvement as in DP: use max/min length of dict words as boundary

```
/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/

/*
Method1: DP
dp[i]: for first i letters, is it valid?
for dp[i] to be valid, the substring[0 ~ i) need to be valid:
dp[i] = dp[j] && sub(j, i); j = [0 ~ i]
dp[0] = true; // 

init dp[] = new boolean[n + 1];
*/

class Solution {
    public boolean wordBreak(String s, List<String> dict) {
        Set<String> words = new HashSet<>(dict);
        
        int n = s.length(); // init dp
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // base case, no letter, assume true for dp[1] to work

        // for loop from dp[1] ~ dp[n]
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] |= dp[j] && words.contains(s.substring(j, i));
                if (dp[i]) break;
            }
        }
        
        return dp[n];
    }
}


// Method1 Improvement: Same DP as above, improve by building boundary of min/max 
class Solution {
    int min = 0, max = 0;

    public boolean wordBreak(String s, List<String> dictWords) {
        int n = s.length(); 
        Set<String> dict = new HashSet<>(dictWords);
        buildBoundary(dict);
        
        // init dp
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // base case, no letter, assume true for dp[1] to work

        for (int i = 1; i <= n; i++) {// process dp[1] ~ dp[n]
            for (int j = 0; j < i; j++) {
                dp[i] |= dp[j] && validate(dict, s, j, i);
                if (dp[i]) break;
            }
        }
        return dp[n];
    }
    
    private boolean validate(Set<String> dict, String s, int j, int i) {
        if (i - j > max || i - j < min) return false;
        return dict.contains(s.substring(j, i));
    }
    
    private void buildBoundary(Set<String> dict) {
        for (String s : dict) {
            int len = s.length();
            min = Math.min(min, len);
            max = Math.max(max, len);
        }
    }
}


//Method2: Raw DFS + memoization, but still a bit slow
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return dfs(dict, new HashSet<>(), s);
    }
    
    private boolean dfs(Set<String> dict, Set<String> set, String s) {
        if (dict.contains(s)) return true;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append(c);
            if (dict.contains(sb.toString())) {
                String sub = s.substring(i + 1);
                if (set.contains(sub)) continue;
                if (dfs(dict, set, sub)) return true;
                set.add(sub);
            }
        }
        return false;
    }
}

//Method2 Improvement: DFS, Memoization + max/min boundary improvement
class Solution {
    int min = 0, max = 0;
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        buildBoundary(dict);
        return dfs(dict, new HashSet<>(), s);
    }
    
    private boolean dfs(Set<String> dict, Set<String> set, String s) {
        if (dict.contains(s)) return true;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append(c);
            if (validate(dict, sb.toString())) {
                String sub = s.substring(i + 1);
                if (set.contains(sub)) continue;
                if (dfs(dict, set, sub)) return true;
                set.add(sub);
            }
        }
        return false;
    }
    
    private void buildBoundary(Set<String> dict) {
        for (String s : dict) {
            int len = s.length();
            min = Math.min(min, len);
            max = Math.max(max, len);
        }
    }
    
    private boolean validate(Set<String> dict, String s) {
        int n = s.length();
        if (n > max || n < min) return false;
        return dict.contains(s);
    }
}

```
