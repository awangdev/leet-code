E
tags: Array, DP, Sequence DP

给个array of stock prices, 限制能交易(买/买)一轮, 问如何找到最大profit.

#### min[n]
- 每天都就交易价格，n天只让买卖一次，那就找个最低价买进，找个最高价卖出
- 记录每天最小值Min是多少. O(n)
- 每天都算和当下的Min买卖，profit最大多少.

#### DP
- Find min value for first i items, new dp[n + 1].
- dp[i]: 前i天, prices最小的price是多少: min cost of first i days
- 然后用当天的price做减法dp[i]算max profit.
- Time, Space: O(n)
- 更进一步, 用一个min来表示min[i], 因为计算中只需要当下的min.

#### Rolling array
- index i only depend on [i - 2]
- Space O(n)

#### Brutle Failed
- 每天都试着买进，然后之后的每一天尝试卖出. double for loop, O(n^2). timeout.
    - 其中很多都是没必要的计算：[7, 1, 5, 3, 6, 4]
    - if we know buyin with 1 is cheapest, we dont need to buyin at 5, 3, 6, 4 later on;
    - only need to sell on higher prices.

```
/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
*/

/*
Storing the min[i]all the time?
min[i]: from 0 ~ i the minimum
Goal: find max of (prices[i] - min[i]) that gives best profit.
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int profit = 0, n = prices.length;
        int[] min = new int[n];
        min[0] = prices[0];
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(min[i - 1], prices[i]);
            profit = Math.max(profit, prices[i] - min[i]);
        }
        return profit;
    }
}

// Improvement: No need to have array, and only find max when needed.
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int profit = 0, min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) min = prices[i];
            else profit = Math.max(profit, prices[i] - min);
        }
        return profit;
    }
}

// DP
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[] dp = new int[n + 1]; // min value up to first i items
        dp[0] = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1], prices[i - 1]);
            profit = Math.max(profit, prices[i - 1] - dp[i]);
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
        int n = prices.length;
        int[] dp = new int[2];
        dp[0] = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 1; i <= n; i++) {
            dp[i % 2] = Math.min(dp[(i - 1) % 2], prices[i - 1]);
            profit = Math.max(profit, prices[i - 1] - dp[i % 2]);
        }
        return profit;
    }
}

```