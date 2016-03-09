H

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

public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> rst = new ArrayList<String>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
        	return rst;
        }
    	
    	boolean[][] isWord = validateWord(s, wordDict);
    	boolean[] isValid = validatePossibility(s, wordDict, isWord);
    	
    	dfs(rst, new ArrayList<String>(), s, 0, isValid, isWord, wordDict);
     	return rst;
    }

    public void dfs(List<String> rst, ArrayList<String> list, String s, 
    				int index, boolean[] isValid, boolean[][] isWord, Set<String> set) {
    	if (!isValid[index]) {
    		return;
    	}
    	//output
    	if (index >= s.length()) {
    		StringBuffer sb = new StringBuffer();
    		for (int i = 0; i < list.size(); i++) {
    			sb.append(list.get(i));
    			if (i != list.size() - 1) {
    				sb.append(" ");
    			}
    		}
		    rst.add(sb.toString());
		    return;
    	}
    	//dfs
    	for (int i = index; i < s.length(); i++) {
    		if (!isWord[index][i]) {
    			continue;
    		}
    		
			list.add(s.substring(index, i + 1));
			dfs(rst, list, s, i + 1, isValid, isWord, set);
			list.remove(list.size() - 1);
    	}
    }

    //dp[i][j] check if s.substring(i,j) is a proper word from dictionary
    public boolean[][] validateWord(String s, Set<String> set) {
    	boolean[][] isWord = new boolean[s.length()][s.length()];
    	for (int i = 0; i < s.length(); i++) {
    		for (int j = i; j < s.length(); j++) {
    			isWord[i][j] = set.contains(s.substring(i, j + 1));
    		}
    	}
    	return isWord;
    }

    //Build the validation boolean[]
    public boolean[] validatePossibility(String s, Set<String> set, boolean[][] isWord) {
    	/*
    	boolean[] valid = new boolean[s.length() + 1];
    	valid[s.length()] = true;

    	int maxLeng = getMaxLength(set);
    	for (int i = s.length() - 1; i >= 0; i--) {
    		for (int j = i; j < s.length() && (j - i) <= maxLeng; j++) {
    			if (isWord[i][j] && valid[j + 1]) {
    				valid[i] = true;
    				break;
    			}
    		}
    	}*/
    	boolean[] valid = new boolean[s.length() + 1];
        valid[0] = true;
        int maxLength = getMaxLength(set);
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < i && j < maxLength; j++) {//iterate [0 ~ i]
                if (isWord[i - j][i] && isValid[i - j + 1])) {
                    valid[i + 1] = true;
                    break;
                }
            }
        }

    	return valid;
    }

    //Get max length, a little optimization
    public int getMaxLength(Set<String> dict) {
        int length = 0;
        for (String word : dict) {
            length = Math.max(length, word.length());
        }
        return length;
    }
}


```