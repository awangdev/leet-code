H
1519272534

两个字符串变话, 找最小值, two sequence DP.
考虑两个字符串变换的最后点: 相等, 互换, 还是缺少? 分析每种情况, 然后列出表达式.

注意, 在i或者j为0的时候, 变成另外一个数字的steps只能是全变.

第一步, 空间时间都是O(MN)
滚动数组优化, 空间O(N)

```
/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. 
(each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example
Given word1 = "mart" and word2 = "karma", return 3.

Tags Expand 
String Dynamic Programming
*/


/*
Thoughts:
Two sequences, looking for min # steps of changes to reach equivalence
DP[i][j] min # of steps to reach equivalence with word1[0 ~ i-1], and word2[0 ~ j-1].
To compute DP[i][j], we rely on (i-1) index and (j-1) index. Which has 3 possible conditions:
1. word1[i-1] == word2[j-1]  => dp[i -1][j - 1]
2. word1[i-1] != word2[j-1]  => repalcement dp[i - 1][j - 1] + 1
3. Insert/Delete => dp[i][j - 1] + 1 or dp[i - 1][j] + 1; where insert and delete has similar effect.

dp[i][j] takese the min of all above cases

dp[0][0] = 0;
dp[0][j] = j;
dp[i][0] = i;

Space: O(MN)
Time: O(MN)
*/
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) {
            return 0;
        }
        char[] words1 = word1 == null ? new char[0] : word1.toCharArray();
        char[] words2 = word2 == null ? new char[0] : word2.toCharArray();
        
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
         
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
				if (i == 0) {
                    dp[i][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = i;
                    continue;
                }
				dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                if (words1[i - 1] == words2[j - 1]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}


// Optimize: Rolling array
// Space O(N)
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) {
            return 0;
        }
        char[] words1 = word1 == null ? new char[0] : word1.toCharArray();
        char[] words2 = word2 == null ? new char[0] : word2.toCharArray();
        
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[2][n + 1];
        int curr = 0;
        int prev = 0;
         
        for (int i = 0; i <= m; i++) {
            prev = curr;
            curr = 1 - prev;
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[curr][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[curr][j] = i;
                    continue;
                }
				dp[curr][j] = Math.min(dp[prev][j - 1], Math.min(dp[curr][j - 1], dp[prev][j])) + 1;
                if (words1[i - 1] == words2[j - 1]) {
                    dp[curr][j] = Math.min(dp[curr][j], dp[prev][j - 1]);
                }
            }
        }
        return dp[curr][n];
    }
}

/**
Previous Notes:
Draw a 2D array, consider rows as word1 and cols as word2. 
DP[i][j] means the steps (edit distance) to take to transfer word1[0 ~ i] to word2[0 ~ j]
And, we have 3 different calculations for the 3 methods:
1. Replace: DP[i][j] = word1[i-1] == word2[j-1] ? DP[i - 1][j - 1] : DP[i-1][j-1] + 1;
2. Insert: DP[i][j]  = word1[i - 1][j] + 1; // missing 1 char in word1
3. Delete: DP[i][j]  = word1[i][j - 1] + 1; // extra char in word1

Note: just remember to start from i=1,j=1, because we are using DP[i-1][j-1], becareful with border case
 */

public class Solution {
    public int minDistance(String word1, String word2) {
		if (word1 == null && word2 != null) {
			return word2.length();
		} else if (word1 != null && word2 == null) {
			return word1.length();
		} else if (word1 == null && word2 == null) {
			return 0;
		}
		int[][] DP = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 1; i <= word1.length(); i++) {
			DP[i][0] = i;
		}
		for (int j = 1; j <= word2.length(); j++) {
			DP[0][j] = j;
		}

		for (int i = 1; i <= word1.length(); i++) {
			for (int j = 1; j <= word2.length(); j++) {
				DP[i][j] = Math.min(Math.min(DP[i - 1][j] + 1, DP[i][j - 1] + 1), word1.charAt(i - 1) == word2.charAt(j - 1) ? DP[i - 1][j - 1] : DP[i - 1][j - 1] + 1);
			}
		}

		return DP[word1.length()][word2.length()];
    }
}

```