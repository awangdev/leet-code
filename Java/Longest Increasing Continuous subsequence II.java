O(mn) space for dp and flag.
O(mn) runtime because each spot will be marked once visited. 
这个题目的简单版本一个array的例子：从简单题目开始想DP会简单一点。每个位置，都是从其他位置（上下左右）来的dpValue +　１.　如果啥也没有的时候，init state 其实都是1， 就一个数字，不增不减嘛。
```
/*
Give you an integer matrix (with row size n, column size m)，find the longest increasing continuous subsequence in this matrix. (The definition of the longest increasing continuous subsequence here can start at any row or column and go up/down/right/left any direction).

Example
Given a matrix:

[
  [1 ,2 ,3 ,4 ,5],
  [16,17,24,23,6],
  [15,18,25,22,7],
  [14,19,20,21,8],
  [13,12,11,10,9]
]
return 25

Challenge
O(nm) time and memory.

Tags Expand 
Dynamic Programming
*/

/*
Thoughts:
Similar to JiuZhang's DP in longest increasing continuous subsequence I. 
Imagining values are coming from 4 directions so A[i][j] < A[dx][dy].
State:
DP[i][j]: coming from 4 different directions, the longest we can get on DP[i][j].
Use flag[i][j] to mark. 1: checked, return dp[i][j] directly; -1: marked, don't over going that direction
Fn:
Go 4 direcetions dfs.
Init:
Can init during dfs by giving a default value of 1 to each spot.
*/
public class Solution {
    /**
     * @param A an integer matrix
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
    	if (A == null || A.length == 0 || A[0].length == 0) {
    		return 0;
    	}
    	int n = A.length;
    	int m = A[0].length;
    	int[][] dp = new int[n][m];
    	int[][] flag = new int[n][m];
    	int ans = 0;
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < m; j++) {
    			dp[i][j] = dfs(A, dp, flag, i, j, n, m);
    			ans = Math.max(ans, dp[i][j]);
    		}
    	}
    	return ans;
    }

    public int dfs(int[][] A, int[][] dp, int[][] flag, int i, int j, int n, int m) {
    	if (flag[i][j] == 1) {
    		return dp[i][j];
    	}
    	int ans = 1;
    	flag[i][j] = -1;
    	int[] dx = {0,  0, 1, -1};
    	int[] dy = {1, -1, 0,  0};
    	//Go 4 directions
    	for (int k = 0; k < dx.length; k++) {
    		int id = i + dx[k];
    		int jd = j + dy[k];
    		if (id >= 0 && id < n && jd >= 0 && jd < m && A[i][j] < A[id][jd]) {
    			ans = Math.max(ans, dfs(A, dp, flag, id, jd, n, m) + 1);
    		} 
    	}
    	flag[i][j] = 1;
    	dp[i][j] = ans;
    	return dp[i][j];
    } 
}

```