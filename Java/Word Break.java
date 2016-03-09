M

DP

方法1:（attempt3 code）
state,rst[i]: 从[0～i] inclusive的string是否可以在dict中break开来找到？      
function: rst[i] = true if (rst[i - j] && set.contains(s.substring(i - j, i))); j in[0~i]     
1. rst[i - j] 记录的是[0, i-j]这一段是否可以break后在dict找到。     
2. 若true，再加上剩下所有[i-j, i]都能在dict找到，那么rst[i] = rst[0, i - j] && rst[i-j, i] == true

优化：找dict里面最长string, 限制j的增大。

(attempt4 code)    
与Word BreakII用同样的DP。
valid[i]: 记录从i到valid array末尾是否valid.

```
/*
Given a string s and a dictionary of words dict, determine if s can be break into a space-separated 
sequence of one or more dictionary words.

Example
Given s = "lintcode", dict = ["lint", "code"].

Return true because "lintcode" can be break as "lint code".

Tags Expand 
String Dynamic Programming

*/
/*
Attempt4: same as attempt3, but reversed how to build the validation matrix. (Same style as in Word Break II)
valid[i]: 从i 到valid.length末尾是否valid
*/
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || dict.contains(s)) {
            return true;
        }

        boolean[] valid = new boolean[s.length() + 1];
        valid[s.length()] = true;
        int maxLength = calMaxLength(dict);
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length() && (i - j) <= maxLength; j++) {//iterate [0 ~ i]
                if (valid[j + 1] && dict.contains(s.substring(i, j + 1))) {
                    valid[i] = true;
                    break;
                }
            }
        }

        return valid[0];
    }

    public int calMaxLength(Set<String> dict) {
        int length = 0;
        for (String word : dict) {
            length = Math.max(length, word.length());
        }
        return length;
    }
}


/*
Attempt3:
Optimize attempt2: If the input s is super long, but Dict does not have that long string, 
then we should avoid that case, so to save time. That is, check dict's strings' max length, 
and incldue that in 2nd-level for loop

j: last word's length, range from 0 to i.
[i - j]: the first index of current word
rst[i - j]: if s[i ~ j] returns true
s.substring(i - j, i): if s[i-j position to i position] is in dict

Note: use maxLength to optimize the solution.

*/

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || dict.contains(s)) {
            return true;
        }

/*
        boolean[] rst = new boolean[s.length() + 1];
        rst[0] = true;
        int maxLength = calMaxLength(dict);
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= i && j <= maxLength; j++) {//iterate [0 ~ i]
                if (rst[i - j] && dict.contains(s.substring(i - j, i))) {
                    rst[i] = true;
                    break;
                }
            }
        }

*/
        boolean[] rst = new boolean[s.length() + 1];
        rst[s.length()] = true;
        int maxLength = calMaxLength(dict);
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length() && (i - j) <= maxLength; j++) {//iterate [0 ~ i]
                if (rst[i + 1] && dict.contains(s.substring(i, j))) {
                    rst[i] = true;
                    break;
                }
            }
        }

        return rst[s.length()];
    }

    public int calMaxLength(Set<String> dict) {
        int length = 0;
        for (String word : dict) {
            length = Math.max(length, word.length());
        }
        return length;
    }
}



/*
DP
Attemp2, Thought:
Use boolena to denote rst[i]: s[0,i-1] can be break to match dict. For the ease to explain, let's consider rst[i+1] with actually string s[0,i];
How to calculate rst[i+1]? 
    As long as there is at least 1 way to break s[0, i], that would work. so do a for loop to check on string s[0, i]:
    For each i, use another index j, j = 0 ~ i. If rst[j] works and s[j,i+1] is in dict, that makes rst[i+1] = true.

Correct: however excceeds time limit at 97% correct
*/

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || dict.contains(s)) {
            return true;
        }
        boolean[] rst = new boolean[s.length() + 1];
        rst[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (rst[j] && dict.contains(s.substring(j, i + 1))) {
                    rst[i + 1] = true;
                    break;
                }
            }
        }
        return rst[s.length()];
    }
}

//Timeout, DFS
//Break the word into pieces: recursively break using start,end pointer.
//End pointer always moves forward, but start pointer may not. 
public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        
        return helper(s, wordDict, 0, 0);
    }
    public boolean helper(String s, Set<String> set, int start, int end) {
        if (end == s.length() - 1) {
            if (start == end) {
                return true;
            }
            return set.contains(s.substring(start));
        }
        //no break
        boolean noBreak = helper(s, set, start, end + 1);
        
        //break:
        boolean hasBreak = false;
        if (set.contains(s.substring(start, end + 1))) {
            hasBreak = helper(s, set, end + 1, end + 1);
        }
        
        return noBreak || hasBreak;
    }
}



/*
Thoughts1:
Is this: select one or more words from dict, to construct the given string.
Create DP[i][j] based on dict that says: combine i number of dict strings and j number of dict strings, do we have a combined string that contains the target?

However, this seems confusing and over-complex. We only have 1 set of variables: dict, so maybe it's now wise to create 2D DP[][].
*/
```
