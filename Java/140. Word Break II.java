H
tags: DP, Backtracking, DFS, Memoization, Hash Table
time: O(n!)
space: O(n!)

找出所有 word break variations, given dictionary. (`Word Break I` only checks possibility)

利用 memoization: `Map<prefix, List<suffix variations>>`

#### DFS + Memoization, pick a prefix, and find a list of suffix candidates
- IMPORANT, Memoization: `Map<prefix, List<suffix variations>>` to build substring segments. Reduces repeated calculation if the substring has been tried.
- Realize the input s expands into a tree of possible prefixes.
- Find list of candidates from subproblem, and cross-match
- DFS returns List<String> segments of target s: every for loop takes a prefix substring, and append with all suffix (result of dfs)
- Time O(n!). Worst case, permutation of unique letters: `s= 'abcdef....'`, and `dict=[a,b,c,d,e,f...]`

#### Method2: DFS on suffix + memo of failed cases, like in WordBreakI
- DFS on string: find a valid prefix, dfs on the suffix, building individual candidate in list till substring exhaust. 
- improvement:
    - use memo to record failed case (solved the timeout issue explained below)
    - use min/max to as boundary for dict check.
- core code is short; helper code is slightly longer

#### Method3: Regular DPs, kinda too slow
- 两个DP一起用, 解决了timeout的问题: when a invalid case 'aaaaaaaaa' occurs, isValid[] stops dfs from occuring
- 1. isWord[i][j], subString(i,j)是否存在dict中？
- 2. 用isWord加快 isValid[i]: [i ～ end]是否可以从dict中找到合理的解？      
- 从末尾开始查看i：因为我们需要测试isWord[i][j]时候，j>i, 而我们观察的是[i,j]这区间；       
- j>i的部分同样需要考虑，我们还需要知道isValid[0～j+1]。 所以isValid[x]这次是表示[x, end]是否valid的DP。     
- i 从 末尾到0, 可能是因为考虑到isWord[i][j]都是在[0~n]之内，所以倒过来数，坐标比较容易搞清楚。     
- (回头看Word Break I， 也有坐标反转的做法)
- 3. dfs 利用 isValid 和isWord做普通的DFS。

#### Timeout Note
- Regarding regular solution: 如果不做memoization或者dp, 'aaaaa....aaa' will repeatedly calculate same substring
- Regarding double DP solution: 在Word Break里面用了set.contains(...), 在isValid里面，i 从0开始. 但是, contains()本身是O(n); intead,用一个isWord[i][j]，就O(1)判断了i~j是不是存在dictionary

```
/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
add spaces in s to construct a sentence where each word is a valid dictionary word. 

"Return all such possible sentences."

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]

*/
// Simplier solution, memoization
class Solution {
    Map<String, List<String>> memo;
    public List<String> wordBreak(String s, List<String> dict) {
        List<String> rst = new ArrayList<>();
        memo = new HashMap<>();
        return dfs(new HashSet<>(dict), s);
    }

    private List<String> dfs(Set<String> dict, String s) {
        if (memo.containsKey(s)) return memo.get(s); // calculated, just return
        List<String> rst = new ArrayList<>();
        if (s.length() == 0) return rst;
            
        if (dict.contains(s)) rst.add(s); // total match word

        // loop over form index -> n, find candidates, validate, dfs
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < s.length(); i++) {
            sb.append(s.charAt(i - 1));
            if (!dict.contains(sb.toString())) {
                continue;
            }
            String suffix = s.substring(i);
            List<String> segments = dfs(dict, suffix);
            for (String segment : segments) {
                rst.add(sb.toString() + " " + segment);
            }
        }
        memo.put(s, rst);
        return rst;
    }
}

// Just use memo, with void dfs.
class Solution {
    Map<String, List<String>> memo = new HashMap<>();
    public List<String> wordBreak(String s, List<String> dict) {
        List<String> rst = new ArrayList<>();
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0) return rst;
        
        dfs(new HashSet<>(dict), s);
        return memo.get(s);
    }

    private void dfs(Set<String> dict, String s) {
        List<String> rst = new ArrayList<>();
        if (dict.contains(s)) rst.add(s); // match word, populate suffix variation list

        // loop over form index -> n: set prefix and corss-match with all possible suffix variations
        for (int i = 1; i < s.length(); i++) {
            String prefix = s.substring(0, i);
            if (!dict.contains(prefix)) continue; // validation with dict

            String suffix = s.substring(i);
            if (suffix.length() > 0 && !memo.containsKey(suffix)) { // if calculated, skip dfs
                dfs(dict, suffix);    
            }
            List<String> segments = memo.get(suffix);
            for (String segment : segments) {
                rst.add(prefix + " " + segment);
            }
        }
        memo.put(s, rst); // save result
    }
}

// Method2: DFS like in WordBreakI, building individual candidate in list till substring exhaust
// Test front sub, and dfs on end substring. Use list to track candidates.
// End state: when i == end of string && test it with map.
// apply the same improvement as in wordBreakI: existing failure check, min/max boundary.
class Solution {
    int min = 0, max = 0;
    public List<String>wordBreak(String s, List<String> wordDict) {
        List<String> rst = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordDict);
        buildBoundary(dict);

        dfs(rst, new ArrayList<>(), dict, new HashSet<>(), s);
        return rst;
    }
    // core
    private void dfs(List<String> rst, List<String> list, Set<String> dict, Set<String> failureSet, String s) {
        int rstSize = 0;
        if (validate(dict, s)) {
            list.add(s);
            rst.add(output(list));
            list.remove(list.size() - 1);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            String front = sb.toString();
            if (validate(dict, front)) {
                String end = s.substring(i + 1);
                if (failureSet.contains(end)) continue;
                
                list.add(front);
                dfs(rst, list, dict, failureSet, end);
                list.remove(list.size() - 1);
            }
        }

        if (rst.size() == rstSize) failureSet.add(s);
    }

    // improvement fns: test boundary & dict contains
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

    // Helpers:
    private String output(List<String> list) {
        StringBuffer sb = new StringBuffer();
        for (String s : list) sb.append(s).append(" ");
        return sb.toString().trim();
    }
}




/*
Works but supper slow.
Thoughts:
Dict is a look up table. We need to backtrack on s && result list.
When iterating over s && worst case of each remaining letter is a word, time: n * (n - 1) * (n - 2) * (n - 3) ... + 1 => O(n!)

return result when s string is traversed completely.

Questions to ask:
1. all chars in s need to be used? 
2. can we jump over chars in s? NO
3. can we resume item in dict? YES

Problem:
dict.contains() is O(logN), so overall time went up to O(N! * LogN). Should improve.

DP1:
Is (i,j) a valid word form dictionary? DP[i][j] represents true/false valid word.

DP2:
for s[i,j] to be a valid entry in the result, s[0, i] has to be validated as well. 
dp[i] represents: up to ith index, all substring ahead are valid
*/
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return result;
        }
        
        boolean[][] isWord = isWord(wordDict, s);
        boolean[] isValid = validatePossibility(wordDict, isWord, s);
        helper(result, new ArrayList<String>(), isWord, isValid, s, 0);
        return result;
    }
    
    public void helper(List<String> result, List<String> list, boolean[][] isWord, boolean[] isValid, String s, int start) {
        if (!isValid[start]) {
            return;
        }
        if (start >= s.length()) {
            StringBuffer sb = new StringBuffer();
            for (String word : list) {
                sb.append(word + " ");
            }
            result.add(sb.toString().trim());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!isWord[start][i]) {//O(1)
                continue;
            }
            list.add(s.substring(start, i + 1));
            helper(result, list, isWord, isValid, s, i + 1);
            list.remove(list.size() - 1);
        }
    }
    
	// isWord[i][j]: is subString s[i, j] a word from dictionary?
    public boolean[][] isWord(List<String> wordDict, String s) {
        int n = s.length();
        boolean[][] isWord = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                isWord[i][j] = wordDict.contains(s.substring(i, j + 1));
            }
        }
        return isWord;
    }
    
	/*
		Verify: up to i letters, is it possible to satisfy the 'word break' rules?
		Need to consider ith index: sequence DP, create dp[n + 1];
		dp[i] = substring(i, j) valid && dp[after substring, j to end]
        Calculating DP from right-side of the string: from the right side, we need to know the substring is valid, then move to left and check further
	*/
    public boolean[] validatePossibility(List<String> wordDict, boolean[][] isWord, String s) {
        //optimize, find maxLength in wordDict to restrict string growth
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        int n = s.length();
        boolean[] isValid = new boolean[n + 1];
        isValid[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (isWord[i][j] && isValid[j + 1]) {
                    isValid[i] = true;
                    break;
                }
            }
        }
        return isValid;
    }
}


```
