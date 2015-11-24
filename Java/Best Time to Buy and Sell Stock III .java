两次卖出机会。那反正就是：找峰头；然后往下再找一个峰头。
怎么样在才能Optimize呢。
从两边同时开始找Max.
leftProfit是从左往右，每个i点上的最大Profit。
rightProfit是从i点开始到结尾，每个点上的最大profit.
那么把左右两边在i上，两边相加的最大值找到就可以了。
```
/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example
Tags Expand 
Array Dynamic Programming

Thinking process:
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