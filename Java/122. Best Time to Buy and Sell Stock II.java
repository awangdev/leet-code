E
tags: Array, Greedy, DP, Sequence DP, Status DP
time: O(n)
space: O(1) greedy, O(n) dp

和Stock I 的区别：可以买卖多次，求总和的最大盈利.

#### 几种不同的思路
1. Greedy, 每次有相邻的diff符合profit条件, 就卖了, 最后把所有的diff加在一起. 计算delta, 其实简单粗暴, 也还不错.
2. 从低谷找peek, sell.
3. DP. (old dp solution BuyOn[], SellOn[])
4. DFS计算所有(timeout).Improvement on DFS -> DP -> calculate sellOn[i] and buyOn[i], and then return buyOn[i]. 有点难想, 但是代码简单, 也是O(n)

#### 1. Greedy
- 画图, 因为可以无限买卖, 所以只要有上升, 就有profit
- 所有卖掉的, 平移加起来, 其实就是overall best profit
- 当天卖光, 再买进.
- O(n) time

#### 2. 找涨幅最大的区间, 买卖
- 找到低谷，买进:peek = start + 1 时候，就是每次往前走一步;若没有上涨趋势，继续往低谷前进。
- 涨到峰顶，卖出:一旦有上涨趋势，进一个while loop，涨到底, 再加个profit.
- profit += prices[peek - 1] - prices[start]; 挺特别的。
- 当没有上涨趋势时候，peek-1也就是start, 所以这里刚好profit += 0.

#### 3. DP, sequence dp + status
- 想知道前i天的最大profit, 那么用sequence DP: 
- dp[i]: represents 前i天的最大profit
- 当天的是否能卖, 取决于昨天是否买进, 也就是 `昨天买了或者卖了的状态`: 加状态, dp[i][0], dp[i][1]
- `买`的状态 `dp[i][0]` = 
    - 1. 今天买入, 昨天sell后的dp[i-1][1]值 - 今天的价格price[i];
    - 2. 今天不买, compare with 昨天buy的dp[i-1][0]值.
- `卖`的状态 `dp[i][1]` = 
    - 1. 今天卖出, 昨天buy的 dp[i-1][0]值 + price[i]; 
    - 2. 今天不卖, compare with 昨天sell后的 dp[i-1][1]值.
- 注意init: 
    - dp[0][0] = dp[0][1] = 0; // day 0 buy/sell: no profit regardless of buy/sell status
    - dp[1][1] = 0; // day 1 sell: haven't bought, so just 0 profit.
    - dp[1][0] = - prices[0]; // day 1 buy: just cost of prices[0]
- Note: `int[][] dp = new int[n+1][2]`以后, index是 1-based. for loop 注意使用 `prices[i-1]`
- O(n) time, O(n) space

##### Rolling Array
- [i] 和 [i - 1] 相关联, roll


```
/*

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

*/


/*
Thoughts:
Draw a curve and realize that, only when prices[i] > prices[i - 1], 
we complete buy/sell and take the profit.
Adding more slopes can be greater than 0~N overall height diff. 

// 1. Greedy
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}

// 2. find peek and use peek 
// O(n) time, O(1) space
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int curr = 0;
        int profit = 0;
        int peek = 1;
        while(curr < prices.length) {
            while (peek < prices.length && prices[peek - 1] <= prices[peek]) {
                peek++;
            }
            profit += prices[peek - 1] - prices[curr];
            curr = peek;
            peek = curr + 1;
        }
        return profit;
    }
}
 
//3. DP: See details at notes above
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        dp[0][0] = dp[0][1] = 0;
        dp[1][0] = - prices[0];
        
        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] - prices[i-1]); // be careful, now i is 1-based
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i-1]);
        }
        return dp[n][1];
    }
}

// Rolling array
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[2][2];
        dp[0][0] = dp[0][1] = 0;
        dp[1][0] = - prices[0];
        for (int i = 2; i <= n; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] - prices[i - 1]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 1) % 2][0] + prices[i - 1]);
        }
        return dp[n % 2][1];
    }
};


/*
Thoughts:
Optimize the DFS (since it times out)
buyOn[i]: [i ~ n], if buying on day i, what's the best profit
sellOn[i]: [i ~ n], if selling on day i, what's the best profit.
equation:
buyOn[i]: on day i, we can buy && sell on day [i + 1], or do nothing.
sellOn[i]: on day i, we can sell && buy on day [i + 1], or do nothing.

buyOn[0]: max value will be calculated and saved up here.

O(n)

However, still very slow, only beat 2%
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int[] buyOn = new int[prices.length];
        int[] sellOn = new int[prices.length];
        int length = prices.length;
        buyOn[length - 1] = 0;
        sellOn[length - 1] = prices[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            buyOn[i] = Math.max(buyOn[i + 1], sellOn[i + 1] - prices[i]); // (not buying on day i; buying on day i, so - prices[i])
            sellOn[i] = Math.max(sellOn[i + 1], buyOn[i + 1] + prices[i]);// (not selling on day i; selling on day i, so + prices[i])
        }
        return Math.max(0, buyOn[0]);
    }
}




/*
Thoughts:
On given day: we can choose to buy or sell or move with no action, which generates different paths -> DFS
However: 196 / 198 test cases passed but times out.
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        return Math.max(dfs(prices, 1, true) - prices[0], dfs(prices, 1, false));
    }
    private int dfs(int[] prices, int index, boolean sellStatus) {
        if (index == prices.length - 1) {
            return sellStatus ? prices[index] : 0;
        }
        int profit = 0;
        for (int i = index; i < prices.length; i++) {
            //No action
            profit = Math.max(profit, dfs(prices, i + 1, sellStatus));
            //Sell or buy:
            int levelDelta = sellStatus ? prices[i] : - prices[i];
            profit = Math.max(profit, dfs(prices, i + 1, !sellStatus) + levelDelta);
        }
        return profit;
    }
}
```