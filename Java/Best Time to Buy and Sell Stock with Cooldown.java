M
1518714005
tags: DP

Sequence DP
跟StockIII很像. 分析好HaveStock && NoStock的状态, 然后看最后一步.

```
/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. 
You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]
*/

/*
Thoughts:
Similar to Stock III, where there where 5 states: before1stBuy, have1stStock, soldAndBefore2ndBuy, have2ndStock, soldAll.
Here we have 3 states:
BeforeBuy, BuyStock, SoldStock, cooldown(beforeBuy) ====simplify===> HaveStock(buyStock), NoStock(soldStock, cooldown).
SoldStock and cooldown can be combined together, since there is no stock at hand.
dp[prices.lengh][2]
dp[i][j]: at i, what's the best profit under status j
If j % 2 == haveStock:
    before (i), no changes, dp[i - 1][j]
    before (i), bought a stock: dp[i - 1][j] - prices[i - 1];
    
If j % 2 == noStock
    before (i), no changes, dp[i - 1][j]
    before (i), sold stock + cooldown, dp[i - 2][j] + prices[i - 2];
    
dp[0][0] = 0;
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = - prices[0]; //buy
        dp[0][1] = 0; //sell
        
        for (int i = 1; i < n; i++) {
            
            // no stock (sell)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            
            // haveStock (buy)
            dp[i][0] = dp[i - 1][0];
            if (i >= 2) {
                dp[i][0] = Math.max(dp[i][0], dp[i - 2][1] - prices[i]);
            } else {
                dp[i][0] = Math.max(dp[i][0], - prices[i]);
            }
            //dp[i][0] = Math.max(dp[i - 1][0], (i >= 2 ? dp[i - 2][1] : 0) - prices[i]); // simplify
        }
        
        return dp[n - 1][1];
    }
}
```