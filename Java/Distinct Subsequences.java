/*
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example
Given S = "rabbbit", T = "rabbit", return 3.

Challenge
Do it in O(n2) time and O(n) memory.

O(n2) memory is also acceptable if you do not know how to optimize memory.

Tags Expand 
String Dynamic Programming
*/
/*
Attempt2:
Use DP. Okay, first I had no idea how to start, but here is a reference: http://blog.csdn.net/abcbc/article/details/8978146
First of all, Map out the number of existance of T in S in a 2D map:
	  0 1 2 3 4 5 6 7
	  ---------------
	    r a b b b i t
0|	  1 1 1 1 1 1 1 1
1|	r 0 1 1 1 1 1 1 1
2|	a 0 0 1 1 1 1 1 1
3|	b 0 0 0 1 2 3 3 3
4|	b 0 0 0 0 1 3 3 3
5|	i 0 0 0 0 0 0 3 3
6|	t 0 0 0 0 0 0 0 3  

Use DP[T][S]. We realize:
1.DP[0][0] == 1; //Both null can be a match
2.DP[0][1 ~ S.length - 1] = 1;//First fow, when T=="", whatever S will have 1 subsequence: ""
3.DP[1 ~ T.length][0] = 0;// First column, when S=="", whatever T will not be subsequence of S == ""
4.When looking at each row and filling out the pixels, we realize when T exist in S[a~b], it will surely exist in S[a~b+1], taht is:
	Step1: DP[i][j] is at least equal to DP[i][j - 1];//DP[i][j] is always based on DP[i][j-1], so DP[i][j] = DP[i][j+1] + something
	Step2: So, what's that 'something' in step1?  For example, look at T[3] == 'b' against S[0 ~ 3]:
		S[0 ~ 3] has 1 'b' at S[3], and also, T[0~3] == S[0~3], that's a perfect match. SO DP[3][3] = 1
		S[0 ~ 4] has 2 'b' at S[3] and S[4]. Now imagine we pick either S[3] or S[4] to genreate T[0~3] out of S[0~4]: we have 2 possibilities.D[3][4] = 2
			Consider: D[i][j] means we picked S[j]; in our S[0 ~ 4] case, that means we picked S[4] but skipped S[3], though S[3] still counts towards another situation where we skipped S[4].
					After all, we will count whatever that we skipped into our current DP[i][j], that is DP[i][j] += T[i - 1] == S[j - 1] ? DP[i - 1][j - 1] : 0;
	Conclusion: while we for-looping through each row, if we find out S[j] and S[j - 1] both equals to T[i - 1], we want to make sure we count D[i - 1][j -1]'s previous records in!

Note:
In double for loop, set i,j <= xxxx.length(), since we've increased the 2D array by 1 block on row and col.
*/


public class Solution {
    /**
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
    	int[][] DP = new int[T.length() + 1][S.length() + 1];
    	DP[0][0] = 1;
    	for(int i = 1; i < S.length(); i++) {
    		DP[0][i] = 1;
    	}
    	for (int i = 1; i < T.length(); i++) {
    		DP[i][0] = 0;
    	}
    	for (int i = 1; i <= T.length(); i++) {
    		for (int j = 1; j <= S.length(); j++){
    			DP[i][j] = DP[i][j - 1];
    			if (T.charAt(i - 1) == S.charAt(j - 1)) {
    				DP[i][j] += DP[i - 1][j - 1];
    			}
    		}
    	}
    	return DP[T.length()][S.length()];
    }
}


/*
Attemp1:
recursive on substring of S, accumulate total count
However, exceed time limit
*/
public class Solution {
    public int numDistinct(String S, String T) {
    	if (S.length() == 0) {
    		return T.length() == 0 ? 1 : 0;
    	}
    	if (T.length() == 0) {
    		return 1;
    	}
    	int count = 0;
    	for (int i = 0; i < S.length(); i++) {
    		if (S.charAt(i) == T.charAt(0)) {
    			count += numDistinct(S.substring(i + 1), T.substring(1));
    		}
    	}
    	return count;
    }
}



/*
First Thought:
find the # of ways to get T from S, while having to follow the rules of 'subsequence'
How about: find what chars are missing in T based on S, then find the number of ways to insert the missing chars to make it back to S?
The missing chars: misChars = new ArrayList<String>();
However, time cost on this:
For example I have n missing chars from S.length == m. so I have (m + 1) places where i can insert the n chars. Then it's a mCn problem. This goes up to m!, too much. Not applicapable.

*/