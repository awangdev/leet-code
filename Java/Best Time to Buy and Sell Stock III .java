H
1523511824
tags: Array, DP, Sequence DP

比stock II 多了一个限制：只有2次卖出机会.

#### DP加状态
- 只卖2次, 把买卖分割成5个状态模块.
- 在状态index 0, 2, 4: 没有持有股票. 1. 一直在此状态, max profit不变; 2. 刚卖掉, dp[i][前状态] + profit
- 在状态index 1, 3: 持有股票. 1. 一直在此状态, daily profit. 2. 刚刚买进, 状态改变, 但是没有profit yet: dp[i][前状态]

##### Partial profit
- 把每天的partial profit (diff)加在一起, 最终的overall profit是一样的. 唯一更好的是, 不需要记录中间买入的时间点.
- 什么时候会积累profit呢? 
- 1. 原本就持有股票的, 如果毫无动作, 那么状态不变, 积累profit diff. 
- 2. 卖出了股票, 状态改变, 积累profit diff.
- 注意: 只有在状态index: 0, 2, 4, 也就是卖掉股票的时候, 猜可以积累profit

##### Rolling Array
- [i] 只有和 [i-1] 打交道, reduce space
- O(1) space, O(n) time

#### 找峰头
- 找峰头；然后往下再找一个峰头。
- 怎么样在才能Optimize两次巅峰呢？从两边同时开始找Max！（棒棒的想法）
- leftProfit是从左往右，每个i点上的最大Profit。
- rightProfit是从i点开始到结尾，每个点上的最大profit.
- 那么在i点上，就是leftProfit，和右边rightProfit的分割点。在i点，leftProfit+rightProfit相加，找最大值。
- 三个O(n),还是O(n)

```
/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Example
Given an example [4,4,6,1,1,4,2,5], return 6.

Note
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Tags Expand 
Enumeration Forward-Backward Traversal Array

*/
/*
Thoughts:
DP: calculate the 
Able to sell 2 times. Consider the last position dp[i], is it sold 2 times? sold 1 time? before buying 1st stock? before buying 2nd stock? or right in between 1st and 2nd transaction, having 0 stock?
The status of the problem should be recorded, which leads to 2nd dimension to record these status:
0 stock, before buying | having 1st stock | sold 1st stock, having 0, before buying | having 2nd stock| sold 2nd stock
dp[i][4]: max profit at index i, where both are sold.
Move the status from 0 ~ 4, and break when reached the end.

equations are a bit complicated:
- status index 0, 2, 4:
    dp[i][j]: dp[i - 1][j - 1] + price[i] - price[i - 1] (status changed from yesterday, and profiting)
              OR: dp[i - 1][j] (status didn't change from yesterday)
- status index 1, 3
    dp[i][3]: dp[i - 1][j] + price[i] - prices[i - 1] (status didn't change from yesterday, keep profiting)
          OR: dp[i - 1][j - 1] (status changed from yesterday, just bought)

init:
dp[0][0] = 0;

One note:
Consider incremental profit sum = overall profit sum. Sum of price[i] - price[i - 1] = price[0~N]
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int profit = 0;
        int n = prices.length;
        int[][] dp = new int[n][5];
        
        dp[0][0] = 0; // No transaction on day 0
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < 5; j++) {
                // Accumulate partial profit regardless of stock status.
                int dailyPartialProfit = prices[i] - prices[i - 1];
                if (j % 2 == 0) { // j status: no stock
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + dailyPartialProfit);
                    // Find best profit when not having stock
                    profit = Math.max(profit, dp[i][j]);
                } else { // j status: have stock
                    dp[i][j] = Math.max(dp[i - 1][j] + dailyPartialProfit, dp[i - 1][j - 1]);
                }
            }
        }
        return profit;
    }
}

// Rolling array
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int profit = 0;
        int n = prices.length;
        int[][] dp = new int[2][5];
        
        dp[0][0] = 0; // No transaction on day 0
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < 5; j++) {
                // Accumulate partial profit regardless of stock status.
                int dailyPartialProfit = prices[i] - prices[i - 1];
                if (j % 2 == 0) { // j status: no stock
                    dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[(i - 1) % 2][j - 1] + dailyPartialProfit);
                    // Find best profit when not having stock
                    profit = Math.max(profit, dp[i % 2][j]);
                } else { // j status: have stock
                    dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j] + dailyPartialProfit, dp[(i - 1) % 2][j - 1]);
                }
            }
        }
        return profit;
    }
}

/*
Thoughts:
Idea from NineChapter: use two arrays to mark max values, however the max values are calculated from left/right sides.
Left[] marks max profit value in the range from a left-index to current index. Tracking left-min.
Right[] marks max profit value in the range from current index to a right-index. Tracking right-max.
If looking at right[i] and left[i] together at index i, they are always representing left-max-profit value and right-max-profit value. Add them together and get results.

*/
class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] leftProfit = new int[prices.length];
        int[] rightProfit = new int[prices.length];
        int min = prices[0];    //Default:leftProfit[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - min);
        }
        int max = prices[prices.length - 1];    //Default:rightProfit[prices.length - 1] = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            rightProfit[i] = Math.max(rightProfit[i + 1], max - prices[i]);
        }
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, leftProfit[i] + rightProfit[i]);
        }
        return profit;
    }
};





```