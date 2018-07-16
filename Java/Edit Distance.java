H
1519272534
tags: String, DP, Double Sequence DP, Sequence DP
time: O(MN)
Space: O(N)

两个字符串, A要变成B, 可以 insert/delete/replace, 找最小变化operation count

#### Double Sequence
- 考虑两个字符串的末尾index s[i], t[j]: 如果需要让这两个字符一样, 可能使用题目给出的三种operation: insert/delete/replace?
- 先calculate最坏的情况, 3种operation count + 1; 然后在比较match的情况.
- 注意, 在i或者j为0的时候, 变成另外一个数字的steps只能是全变.
- 第一步, 空间时间都是O(MN), O(MN)
- 滚动数组优化, 空间O(N)

##### Detail analysis
- insert: assume insert on s, `#ofOperation = (s[0 ~ i] to t[0 ~ j-1]) + 1;`
- delete: assume delete on t, `#ofOperatoin = (s[0 ~ i - 1] to t[0 ~ j]) + 1;`
- replace: replace both s and t, `#ofOperatoin = (s[0 ~ i - 1] to t[0 ~ j - 1]) + 1;`
- dp[i][j]代表了两个 sequence 互相之间的性质: s[0 ~ i] 转换成 s[0~j] 所需要的最少 operation count
- init: 当i==0, dp[0][j] = j; 每次都要 + j 个character; 同理, 当j==0, dp[i][0] = i;
- 而dp[i][j]有两种情况处理: `s[i] == t[j]` or `s[i] != t[j]`

##### 何时initialize
- 这种判断取决于经验: 如果知道initialization可以再 double for loop 里面一起做, 那么可以留着那么做
- 这样属于 `需要什么, initialize什么`
- 事后在做space optimization的时候, 可以轻易在 1st dimension 上做rolling array

#### Search
- 可以做, 但是不建议:这道题需要找 min count, 而不是search/find all solutions, 所以search会写的比较复杂, 牛刀杀鸡.

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
If certain character matches, no opeation needed.
If not matching, use the 3 operations and count of operations + 1
dp[i][j] represents the # of changes from A[0 ~ i - 1] to B[0 ~ j - 1]

init: for word1[0, 0] to change to words[0 ,j]: it has to be j add operations
*/
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) return 0;
        if (word1 == null || word2 == null) return word1 == null ? word2.length() : word1.length();
        
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
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else { // 3 ways to find replace, find min
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}

// Rolling array
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) return 0;
        if (word1 == null || word2 == null) return word1 == null ? word2.length() : word1.length();
        
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[2][n + 1];
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i%2][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[i%2][j] = i;
                    continue;
                }
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i%2][j] = dp[(i - 1)%2][j - 1];
                } else { // 3 ways to find replace, find min
                    dp[i%2][j] = Math.min(dp[(i - 1)%2][j - 1], Math.min(dp[i%2][j - 1], dp[(i - 1)%2][j])) + 1;
                }
            }
        }
        return dp[m%2][n];
    }
}

// Init operation ahead of double for loop
// This approach is okay, but cannot be optimized for space since dp[i][j] has to be initialized completely
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) return 0;
        if (word1 == null || word2 == null) return word1 == null ? word2.length() : word1.length();
        
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // init
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }       
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else { // 3 ways to find replace, find min
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}

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