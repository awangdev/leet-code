M

DP问题

从边长为2的正方形看起，看左上角的那个点。   
如何确定是个正方形？首先看左上点是不是1，然后看右边，右下，下面的点是不是1.   

DP就是根据这个特征想出来。dp[i,j]: 从右下往左上推算，包括当前点在内的所能找到的最大边长。   
   注意dp[i,j]被右边，右下，下面三点的最短点所限制。这就是fn. 

Init：   
   把右边，下边两个边缘init一遍，存matrix在这两条边上的值，代表的意思也就是dp[i][j]在这些点上的初始值:变成为1 or 0.  

```
/*
Given a 2D binary matrix filled with 0's and 1's, 
find the largest square containing all 1's and return its area.

Example
For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.

Tags Expand 
Dynamic Programming
*/

/*
	Thoughts: Seem that we need to check on right and bottom spots for 2x2 1's.
	If all size spots are clean, len++, square = newLen ^ 2.
	DP[i,j]: the longest square lengh that this matrix[i][j] reach.
	dp[i,j] = Math.min(dp[i][j+1], dp[i+1][j], dp[i+1][j+1]) 
	init: 
	dp[n-1][0 ~ m-1] = matrix[n-1][0 ~ m-1] == 0 ? 0 : 1;
	dp[0 ~ n-1][m-1] = matrix[0 ~ n-1][m-1] == 0 ? 0 : 1;
	for from rigt-bottom conor.
	Maintain a max area
*/

public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    		return 0;
    	}
    	int n = matrix.length;
    	int m = matrix[0].length;
    	int[][] dp = new int[n][m];
    	int maxLen = 0;
    	//Init
    	for (int i = 0; i < m; i++) {
    		dp[n - 1][i] = matrix[n - 1][i];
    		maxLen = Math.max(maxLen, dp[n - 1][i]);
    	}
    	for (int i = 0; i < n; i++) {
    		dp[i][m - 1] = matrix[i][m - 1];
    		maxLen = Math.max(maxLen, dp[i][m - 1]);
    	}
    	//function
    	for (int i = n - 2; i >= 0; i--) {
    		for (int j = m - 2; j >= 0; j--) {
    			if (matrix[i][j] == 1) {
    				dp[i][j] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]), dp[i + 1][j + 1]) + 1;
    				maxLen = Math.max(maxLen, dp[i][j]);
    			}
    		}
    	}
    	return maxLen * maxLen;
    }
}

```