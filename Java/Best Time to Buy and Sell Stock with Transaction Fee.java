M
1531728802
tags: Array, DP, Greedy, Sequence DP, Status DP
time: O(n)
space: O(n), O(1) rolling array

跟Stock II 一样, 买卖无限, 需先买在卖. 附加条件: 每个sell transaction要加一笔fee.

#### Sequence DP
- 与StockII一样, dp[i]: represents 前i天的最大profit.
- sell 的时候, 才完成了一次transaction, 需要扣fee; 而买入不扣fee.
- model sell on dp[i] day (which depends on dp[i-1]) and each day can be sell/buy => add status to dp[i][status]
- status[0] buy on this day, status[1] sell on this day
- dp[i][0] = Math.max(dp[i-1][0], dp[i - 1][0] - prices[i]);
- dp[i][1] = Math.max(dp[i-1][1], dp[i - 1][1] + prices[i] - fee);
- init: dp[0][0,1] = 0; dp[1][1] = 0; dp[1][0] = - prices;
- return dp[n][1]

```
/*
Your are given an array of integers prices, 
for which the i-th element is the price of a given stock on day i;
and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, 
but you need to pay the transaction fee for each transaction. 
You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 1:
Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Note:

0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.
*/

/*
- sequence dp. dp[i] represents max profit for first i days.
- model sell on dp[i] day (which depends on dp[i-1]) and each day can be sell/buy => add status to dp[i][status]
- status[0] buy on this day, status[1] sell on this day
- dp[i][0] = Math.max(dp[i-1][0], dp[i - 1][0] - prices[i]);
- dp[i][1] = Math.max(dp[i-1][1], dp[i - 1][1] + prices[i] - fee);
- init: dp[0][0,1] = 0; dp[1][1] = 0; dp[1][0] = - prices;
return dp[n][1]
*/
class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) return 0;
        // init dp[][] with n+1
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        dp[0][0] = dp[0][1] = 0;
        dp[1][1] = 0;
        dp[1][0] = - prices[0];
        
        // calculate dp
        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i - 1][1] - prices[i - 1]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i - 1][0] + prices[i - 1] - fee);
        }
        
        return dp[n][1];
    }
}

// Rolling array
class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) return 0;
        // init dp[][] with n+1
        int n = prices.length;
        int[][] dp = new int[2][2];
        dp[0][0] = dp[0][1] = 0;
        dp[1][1] = 0;
        dp[1][0] = - prices[0];
        
        // calculate dp
        for (int i = 2; i <= n; i++) {
            dp[i%2][0] = Math.max(dp[(i - 1)%2][0], dp[(i - 1)%2][1] - prices[i - 1]);
            dp[i%2][1] = Math.max(dp[(i - 1)%2][1], dp[(i - 1)%2][0] + prices[i - 1] - fee);
        }
        
        return dp[n%2][1];
    }
}
```