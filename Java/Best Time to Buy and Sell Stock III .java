M

比stock II 多了一个限制：只有2次卖出机会。也就是：找峰头；然后往下再找一个峰头。

怎么样在才能Optimize两次巅峰呢？

从两边同时开始找Max！（棒棒的想法）

   leftProfit是从左往右，每个i点上的最大Profit。
   rightProfit是从i点开始到结尾，每个点上的最大profit.
   那么在i点上，就是leftProfit，和右边rightProfit的分割点。在i点，leftProfit+rightProfit相加，找最大值。

三个O(n),还是O(n)

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