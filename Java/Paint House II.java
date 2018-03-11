R
1517386676
tags: DP

一般的DP被加了状态变成2D. 
考虑最后位, 而前一位i-1又被i位的颜色限制, 于是在考虑 min dp[i] 时候, 又多了一层iteration.
所以变成了O(NK^2)

注意: 
1. 序列型dp[i]表示'前i-1个'的结果. 所以dp最好设定为 int[n + 1] size. 
    然而, 颜色在这里是状态, 所以保留在 j: [ 0~k)
2. [[8]] 这样的edge case. 跑不进for loop, 所以特殊handle.

Optimization:
如果已知每次都要从cost里面选两个不同的最小cost,那么先把最小挑出来, 就不必有第三个for loop
O(NK)

```

/*
There are a row of n houses, each house can be painted with one of the k colors. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color 0; 
costs[1][2] is the cost of painting house 1 with color 2, and so on... 

Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
*/


/*
Thoughts:
Min cost, DP.
If dp[i] represents the min cost of painting leading i-1 houses, then consider how to build dp[i]:
It'll be dp[i - 1] + int[i][?] cost. However, we don't know what was choen on index i -1, so it may require a round of traverse. Think about storing the status.
dp[i][j], represents the min of the leading (i - 1) houses' cost, also at index i - 1, color j was chosen

if chosen j at index i, loop over all possibilities at i - 1 index.
dp[i][j] = Math.max(dp[i - 1][0 ~ k] + costs[i][j])
*/
class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        } else if (costs.length == 1 && costs[0].length == 1) {
            return costs[0][0];
        }
        int minCost = Integer.MAX_VALUE;
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n + 1][k];
        // Paint 0 houese should be initialized with 0 cost
        for (int j = 0; j < k; i++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) { // iterate over house #
            for (int j = 0; j < k; j++) { // choose color
                dp[i][j] = Integer.MAX_VALUE;
                for (int m = 0; m < k; m++) { // choose adjacent color
                    if (m == j) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][m] + costs[i - 1][j]);
                }    
                if (i == n) {
                    minCost = Math.min(minCost, dp[i][j]);   
                }
            }

        }
        return minCost;
     }
}

/*
Thoughts:
Optimization.
The rule is adjacent 2 houses cannot be in same color, 
which means, they can be chosen from the lowest two costs. 
If we know the lowest 2 ahead of the 3-level-for loop, we can reduce the most-inner loop.

*/
class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        } else if (costs.length == 1 && costs[0].length == 1) {
            return costs[0][0];
        }
        int minCost = Integer.MAX_VALUE;
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n + 1][k];
        // Paint 0 houese should be initialized with 0 cost
        for (int j = 0; j < k; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) { // iterate over house #
            // Find minLarge and minSmall indexes
            int minSmallIndex = -1;
            int minLargeIndex = -1;
            for (int j = 0; j < k; j++) {
                if (minSmallIndex == -1 || dp[i - 1][j] < dp[i - 1][minSmallIndex]) {
                    minLargeIndex = minSmallIndex;
                    minSmallIndex = j;
                } else if (minLargeIndex == -1 || dp[i - 1][j] < dp[i - 1][minLargeIndex]) {
                    minLargeIndex = j;
                }
            }

            for (int j = 0; j < k; j++) { // choose color
                if (j == minSmallIndex) {
                    dp[i][j] = dp[i - 1][minLargeIndex] + costs[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][minSmallIndex] + costs[i - 1][j];
                }
                
                if (i == n) {
                    minCost = Math.min(minCost, dp[i][j]);   
                }
            }

        }
        return minCost;
     }
}


```