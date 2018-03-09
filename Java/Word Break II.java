R
1519546502
tags: DP, Backtracking

两个DP一起用.解决了timeout的问题     
1. isWord[i][j], subString(i,j)是否存在dict中？

2. 用isWord加快 isValid[i]: [i ～ end]是否可以从dict中找到合理的解？      
	从末尾开始查看i：因为我们需要测试isWord[i][j]时候，j>i, 而我们观察的是[i,j]这区间；       
	j>i的部分同样需要考虑，我们还需要知道isValid[0～j+1]。 所以isValid[x]这次是表示[x, end]是否valid的DP。     
	i 从 末尾到0, 可能是因为考虑到isWord[i][j]都是在[0~n]之内，所以倒过来数，坐标比较容易搞清楚。     
	(回头看Word Break I， 也有坐标反转的做法)

3. dfs 利用 isValid 和isWord做普通的DFS。

Note:
在Word Break里面用了set.contains(...), 在isValid里面，i 从0开始。 但是，contains()本身是O(n).     
在这道题里面应该是因为word dictionary太大，加上nest for, 变成O(n^3)所以timeout.

istead,用一个isWord[i][j]，就O(1)判断了i~j是不是存在dictionary里面。

```
/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence 
where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

Hide Company Tags Google Uber
Hide Tags Dynamic Programming Backtracking

*/

/*
Thoughts:
Dict is a look up table. We need to backtrack on s && result list.
When iterating over s && worst case of each remaining letter is a word, time: n + (n - 1) + (n - 2) + (n - 3) ... + 1 => O(n^2)

return result when s string is traversed completely.

Questions to ask:
1. all chars in s need to be used? 
2. can we jump over chars in s? NO
3. can we resume item in dict? YES

Problem:
dict.contains() is O(logN), so overall time went up to O(N^2*LogN). Should improve.

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


/*
	Previous notes:
	Thoughts: DP
	Check if s.substring(i,j) is a valid word
		state: isWord[i][j]
		function: isWord[i][j] = set.contains(s.substring(i, j + 1));
	
	Check if postFixed string is valid solution isValid[j]: [j+1, end] inclusive is valid or no?
		state: isValid[i]
		function: similar to in Word Break I

	DFS:
		if isValid(i), and isWord(i,j) then go deeper
*/

```