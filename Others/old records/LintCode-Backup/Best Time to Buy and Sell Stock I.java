M

理解意思是关键：   
   每天都就交易价格，n天只让买卖一次，那就找个最低价买进，找个最高价卖出。
   记录每天最小值Min是多少。O(n)
   每天都算和当下的Min买卖，profit最大多少。


```
/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.

Have you met this question in a real interview? Yes
Example
Given an example [3,2,3,1,2], return 1

Tags Expand 
Greedy Enumeration Array Facebook Uber

*/

/*

Thoughts
First to understand what this question is asking: 
Each element is a price of the same stock on that specific day. Of course we want to buy in with min-price and sell out with max-price. 
Find the min-price by looping through the array.
Find the max-profit: price[i] - min-price, which requires a loop through the loop again.
We put above 2 tasks into one-pass for loop

*/
public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            profit = Math.max(profit, prices[i] - min);
        }
        return profit;
    }
}


```