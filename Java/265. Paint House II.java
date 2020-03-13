H
tags: DP, Sequence DP, Status DP
time: O(NK^2):
space: O(K) with rolling array

一排n个房子, 每个房子可涂成k种颜色, 涂每个房子的价钱不一样, 用costs[][]表示. 

costs[0][1]表示涂了index是0的房子, 用了color 1.

规则: 相邻的两个房子不能使同一种颜色

求: 最少的cost 

#### DP
- 跟Paint House I 几乎一模一样, 只不过paint color更多了: k colors.
    - 先考虑单纯地用dp[i]表示涂前 i 个房子的最小cost
    - 但是 dp[i] 和 dp[i-1] 两个index选什么颜色会互相影响, 难讨论, 于是加状态: 序列DP被加了状态变成2D. 
- 考    虑最后位, 而前一位i-1又被i位的颜色限制, 于是在考虑 min dp[i] 时候, 又多了一层iteration.
- 做dp[i][j]: # cost for 前 i 个房子, 所以要先pick (i-1) 房子的cost, 然后在找出 (i-2)房子的cost
- K种颜色 => O(NK^2)
- 如果不优化, 跟Paint House I 几乎是一模一样的代码
- Time O(NK^2), space(NK)
- Rolling array: reduce space to O(K)

#### 注意
- 序列型dp[i]表示'前i-1个'的结果. 所以dp最好设定为 int[n + 1] size. 
- 然而, 颜色在这里是状态, 所以保留在 j: [ 0~k)
- [[8]] 这样的edge case. 跑不进for loop, 所以特殊handle.

#### Optimization Solution
- Time: O(NK)
- 如果已知每次都要从cost里面选两个不同的最小cost,那么先把最小两个挑出来, 就不必有第三个for loop 找 min
- 每次在数列里面找: 除去自己之外的最小值, 利用最小值/次小值的思想
- 维持2个最值: 最小值/次小值. 
- 计算的时候, 如果除掉的不是最小值的index, 就给出最小值; 如果除掉的是最小值的index, 就给出次小值.
- Every loop: 1. calculate the two min vlaues for each i; 2. calcualte dp[i][j]
- 如何想到优化: 把表达式写出来, 然后看哪里可以优化
- 另外, 还是可以rolling array, reduce space complexity to O(K)

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
This generial approach O(nK^2) has almost identical code as Paint House I.
Min cost, DP.
If dp[i] represents the min cost of painting leading i-1 houses, then consider how to build dp[i]:
It'll be dp[i - 1] + int[i][?] cost. However, we don't know what was choen on index i -1, so it may require a round of traverse. Think about storing the status.
dp[i][j], represents the min of the leading (i - 1) houses' cost, also at index i - 1, color j was chosen

if chosen j at index i, loop over all possibilities at i - 1 index.
dp[i][j] = Math.max(dp[i - 1][0 ~ k] + costs[i][j])
*/
class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        if (costs.length == 1 && costs[0].length == 1) return costs[0][0];

        int n = costs.length, k = costs[0].length;
        int[][] dp = new int[n + 1][k]; //dp[0][0] = dp[0][1] = dp[0][2] ... = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < k; j++) {// Select color j at index i
                dp[i][j] = Integer.MAX_VALUE;
                for (int m = 0; m < k; m++) {// Select color k at index i-1
                    if (j == m) continue; // avoid same color
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][m] + costs[i - 1][j]);
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) min = Math.min(min, dp[n][j]);
        return min;
    }
}

// space optimization, rolling array, O(k) space; but slow
class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        if (costs.length == 1 && costs[0].length == 1) return costs[0][0];

        int n = costs.length, k = costs[0].length;
        int[][] dp = new int[2][k]; //dp[0][0] = dp[0][1] = dp[0][2] ... = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < k; j++) {// Select color j at index i
                dp[i % 2][j] = Integer.MAX_VALUE;
                for (int m = 0; m < k; m++) {// Select color k at index i-1
                    if (j == m) continue; // avoid same color
                    dp[i % 2][j] = Math.min(dp[i % 2][j], dp[(i - 1) % 2][m] + costs[i - 1][j]);
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) min = Math.min(min, dp[n % 2][j]);
        return min;
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
            // Find minSecond and min indexes
            // min value: dp[i - 1][minIndex]
            // 2nd min value: dp[i - 1][minSecIndex]
            int minIndex = -1;
            int minSecIndex = -1;
            for (int j = 0; j < k; j++) {
                if (minIndex == -1 || dp[i - 1][j] < dp[i - 1][minIndex]) {
                    minSecIndex = minIndex;
                    minIndex = j;
                } else if (minSecIndex == -1 || dp[i - 1][j] < dp[i - 1][minSecIndex]) {
                    minSecIndex = j;
                }
            }

            // DP Processing
            for (int j = 0; j < k; j++) { // choose color for house i - 1
                if (j == minIndex) { // if color at minIndex is chosen for dp[i], then the remaining min is at minSecIndex
                    dp[i][j] = dp[i - 1][minSecIndex] + costs[i - 1][j];
                } else { // if color is not chosen at minIndex, minIndex will represent the overall min
                    dp[i][j] = dp[i - 1][minIndex] + costs[i - 1][j];
                }
                
                if (i == n) {
                    minCost = Math.min(minCost, dp[i][j]);   
                }
            }

        }
        return minCost;
     }
}

// Rolling array, O(k) space, a little bit slower because of the %2 
class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        } else if (costs.length == 1 && costs[0].length == 1) {
            return costs[0][0];
        }
        int minCost = Integer.MAX_VALUE;
        int n = costs.length, k = costs[0].length;
        int[][] dp = new int[2][k]; // dp[0][j] = 0; for j=[0 ~ k)

        for (int i = 1; i <= n; i++) {
            int minIndex = -1, minSecIndex = -1;
            for (int j = 0; j < k; j++) {
                if (minIndex == -1 || dp[(i - 1) % 2][j] < dp[(i - 1) % 2][minIndex]) {
                    minSecIndex = minIndex;
                    minIndex = j;
                } else if (minSecIndex == -1 || dp[(i - 1) % 2][j] < dp[(i - 1) % 2][minSecIndex]) {
                    minSecIndex = j;
                }
            }

            // DP Processing
            for (int j = 0; j < k; j++) {
                if (j == minIndex) dp[i % 2][j] = dp[(i - 1) % 2][minSecIndex] + costs[i - 1][j];
                else dp[i % 2][j] = dp[(i - 1) % 2][minIndex] + costs[i - 1][j];
                
                if (i == n) minCost = Math.min(minCost, dp[i % 2][j]);   
            }
        }
        return minCost;
     }
}

```