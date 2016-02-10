/*
Given a string s and a dictionary of words dict, determine if s can be break into a space-separated sequence of one or more dictionary words.

Example
Given s = "lintcode", dict = ["lint", "code"].

Return true because "lintcode" can be break as "lint code".

Tags Expand 
String Dynamic Programming

*/
/*
Attemp3:
Optimize attempt2: If the input s is super long, but Dict does not have that long string, then we should avoid that case, so to save time. That is, check dict's strings' max length, and incldue that in 2nd-level for loop

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
        boolean[] rst = new boolean[s.length() + 1];
        rst[0] = true;
        int maxLength = calMaxLength(dict);
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= i && j <= maxLength; j++) {
                if (rst[i - j] && dict.contains(s.substring(i - j, i))) {
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




/*
Thoughts1:
Is this: select one or more words from dict, to construct the given string.
Create DP[i][j] based on dict that says: combine i number of dict strings and j number of dict strings, do we have a combined string that contains the target?

However, this seems confusing and over-complex. We only have 1 set of variables: dict, so maybe it's now wise to create 2D DP[][].
*/