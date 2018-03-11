E
1517372448
tags: Array, DP

理解意思是关键:
   每天都就交易价格，n天只让买卖一次，那就找个最低价买进，找个最高价卖出。
   记录每天最小值Min是多少。O(n)
   每天都算和当下的Min买卖，profit最大多少.

这里就可以DP, memorize the min[i]: the minimum among [0 ~ i]; 然后用当天的price做减法算max.
更进一步, 用一个min来表示min[i], 因为计算中只需要当下的min.


Brutle:
每天都试着买进，然后之后的每一天尝试卖出. double for loop, O(n^2). timeout.
其中很多都是没必要的计算：[7, 1, 5, 3, 6, 4]。 if we know buyin with 1 is cheapest, we don't need to buyin at 5, 3, 6, 4 later on; we'll only sell on higher prices.

```
/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.

*/


/*
Thoughts:
Brutle - buy in on any given day (or not buy), and try to sell on any later days; find the max profit; but it timesout.

Lots of calculations are redundant: for example, if we know buyin with 1 is cheapest, we don't need to buyin at 5, 3, 6, 4 later on; we'll only sell on higher prices.
How about storing the min[i]all the time?
min[i]: from 0 ~ i the minimum
Goal: find max of (prices[i] - min[i]) that gives best profit.
*/
/*
Thoughts:
Brutle - buy in on any given day (or not buy), and try to sell on any later days; find the max profit.
But it timesout
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int profit = 0;
        int[] min = new int[prices.length];
        min[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min[i] = Math.min(min[i - 1], prices[i]);
            profit = Math.max(profit, prices[i] - min[i]);
        }
        return profit;
    }
}

/*
Improvement: No need to have array, and only find max when needed.

First to understand what this question is asking: 
Each element is a price of the same stock on that specific day. Of course we want to buy in with min-price and sell out with max-price. 
Find the min-price by looping through the array.
Find the max-profit: price[i] - min-price, which requires a loop through the loop again.
We put above 2 tasks into one-pass for loop

*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int profit = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                profit = Math.max(profit, prices[i] - min);
            } else {
                min = prices[i];
            }
        }
        return profit;
    }
}


/*
Thoughts:
Brutle - buy in on any given day (or not buy), and try to sell on any later days; find the max profit.
But it timesout.
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    max = Math.max(max, prices[j] - prices[i]);
                }
            }
        }
        return max;
    }
}



```