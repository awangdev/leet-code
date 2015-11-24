找涨幅最大的区间，买卖。
飞似得涨，到峰顶，就卖。
```
/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example
Tags Expand 
Greedy Array

Thinking process:
In this case, since we know the entire stock price for all days in the array, we want to this:
Sell it at nearest peek price and buy it on the next dropped price, then sell again at next peek.
Two pointers, start and peek, to track the starting point and the peek.
Two while loop:
While loop on start, until start reaches the last 2nd index, we only have 1 option to sell.
Inner while loop that finds the peek from start.
Note: peek always has index >= start+1.
Sum up all profit and return it.
Tricky thing: we are looking for max profit, but does not require to sell the stock by end of array. So if the price is dropping, we are not selling and we are not losing/winning anything.
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
        int profit = 0;
        int start = 0;
        int peek = 0;
        while (start < prices.length - 1) {
            peek = start + 1;
            while (peek < prices.length && prices[peek - 1] <= prices[peek]) {
                peek++;
            }
            profit += prices[peek - 1] - prices[start];
            start = peek;
        }
        return profit;
    }
};


```