/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example
Given word1 = "mart" and word2 = "karma", return 3.

Tags Expand 
String Dynamic Programming

Thoughts:
Draw a 2D array, consider rows as word1 and cols as word2. 
DP[i][j] means the steps (edit distance) to take to transfer word1[0 ~ i] to word2[0 ~ j]
And, we have 3 different calculations for the 3 methods:
1. Replace: DP[i][j] = word1[i-1] == word2[j-1] ? DP[i - 1][j - 1] : DP[i-1][j-1] + 1;
2. Insert: DP[i][j]  = word1[i - 1][j] + 1; // missing 1 char in word1
3. Delete: DP[i][j]  = word1[i][j - 1] + 1; // extra char in word1

Note: just remember to start from i=1,j=1, because we are using DP[i-1][j-1], becareful with border case
*/



public class Solution {
    /**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
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
