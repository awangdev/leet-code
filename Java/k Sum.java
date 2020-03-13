H
1516774576
tags: DP

DP. 公式如何想到, 还需要重新理解.

dp[i][j][m]: # of possibilities such that from j elements, pick m elements and sum up to i. 
i: [0~target]

dp[i][j][m] = dp[i][j-1][m] + dp[i - A[j - 1]][j-1][m-1]
            (i not included)   (i included)

```
/*
Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].

Return 2.
*/

/*
Thoughts:
Once learned the equation, it becomes easy:
create dp[i][j][m]: # of possibilities such that from j elements, pick m elements and sum up to i. 
i: [0~target]
HOWEVER: need to figure out how to come up with the equation

Two ways to reach dp[i][j][m]: 
If element i is not included; if i is included.

dp[i][j][m] = dp[i][j-1][m] + dp[i - A[j - 1]][j-1][m-1]
            (i not included)   (i included)

Initialization
dp[0][j][0], j=[0, A.length]: from j elemnts, pick 0 element to sum up to 0, there can be just 1 possibility, don't pick any thing: []
Therefore: dp[0][j][0] = 1;
*/

public class Solution {
    /*
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        if (A == null || A.length == 0 || k <= 0) {
            return 0;
        }
        
        final int[][][] dp = new int[target + 1][A.length + 1][k + 1];
        for (int j = 0; j <= A.length; j++) {
            dp[0][j][0] = 1;
        }
        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= A.length; j++) {
                for (int m = 1; m <= j && m <= k; m++) {
                    dp[i][j][m] = dp[i][j - 1][m];
                    if (i - A[j - 1] >= 0) {
                        dp[i][j][m] += dp[i - A[j - 1]][j - 1][m - 1];
                    }
                }
            }
        }
        return dp[target][A.length][k];
    }
}
```