有问题。还不是非常理解：
best time to buy and sell stock: 为什么 i-1天的卖了又买，可以和第 i 天的卖合成一次交易？
因为每天交易的price是定的。所以卖了又买，等于没卖！这就是可以合并的原因。要对价格敏感啊少年。

```
/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Example
Given prices = [4,4,6,1,1,4,2,5], and k = 2, return 6.

Note
You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Challenge
O(nk) time.

Tags Expand 
Dynamic Programming
*/

/*
	Thoughts: http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html
	local[i][j] = max(global[i – 1][j – 1] , local[i – 1][j] + diff). WHY????
	global[i][j] = max(global[i – 1][j], local[i][j])
	
*/
class Solution {
    /**
     * @param k: An integer
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int k, int[] prices) {
    	if (prices == null || prices.length < 2 || k <= 0) {
    		return 0;
    	}
    	if (k >= prices.length) {
    		int profit = 0;
    		for (int i = 1; i < prices.length; i++) {
    			if (prices[i] > prices[i - 1]) {
    				profit += prices[i] - prices[i - 1];
    			}
    		}
    		return profit;
    	}
    	int[][] local = new int[prices.length][k + 1];
    	int[][] global = new int[prices.length][k + 1];
    	for (int i = 1; i < prices.length; i++) {
    		int diff = prices[i] - prices[i - 1];
    		for (int j = 1; j <= k; j++) {
    			local[i][j] = Math.max(global[i-1][j-1] + diff, local[i - 1][j] + diff);
    			global[i][j] = Math.max(global[i-1][j], local[i][j]);
    		}
    	}
    	return global[prices.length - 1][k];
    }
};

```