H
1523513634
tags: DP, Sequence DP

有int[] price of stock, 最多做 k transactions.  求最大profit.

#### DP
- 根据StockIII, 不难发现StockIV就是把状态划分为2k+1份. 那么同样的代码, 移植.

##### 注意1: 
- 如果k很大, k>n/2, 那么长度为n的数组里面, 最多也只能n/2个transaction
- 那么题目简化为stockII, 给n数组, 无限次transaction.
- 注意, status的数量是 2k+1
- Time O(NK), Space O(2k+1) to store the status

##### 注意2: 
- 最后状态是'没有stock'的都该考虑, 做一个 for 循环比较max. 
- 当然, 来一个profit variable, 不断比较, 也是可以的.

#### 方法2
- (previous notes, 熟练第一种方法的思考就可以)
- 记得要理解：为什么 i-1天的卖了又买，可以和第 i 天的卖合成一次交易？    
- 因为每天交易的price是定的。所以卖了又买，等于没卖！这就是可以合并的原因。要对价格敏感啊少年。
- Inspired from here: http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html

##### 局部最优解 vs. 全局最优解：     
- local[i][j] = max(global[i – 1][j – 1] + diff, local[i – 1][j] + diff)    
- global[i][j] = max(global[i – 1][j], local[i][j])     
- local[i][j]: 第i天，当天一定进行第j次交易的profit     
- global[i][j]: 第i天，总共进行了j次交易的profit.

- local[i][j]和global[i][j]的区别是：local[i][j]意味着在第i天一定有交易（卖出）发生。    
- 当第i天的价格高于第i-1天（即diff > 0）时，那么可以把这次交易（第i-1天买入第i天卖出）跟第i-1天的交易（卖出）合并为一次交易，即local[i][j]=local[i-1][j]+diff；    
- 当第i天的价格不高于第i-1天（即diff<=0）时，那么local[i][j]=global[i-1][j-1]+diff，而由于diff<=0，所以可写成local[i][j]=global[i-1][j-1]。    
- (Note:在我下面这个solution里面没有省去 +diff）   

- global[i][j]就是我们所求的前i天最多进行k次交易的最大收益，可分为两种情况：    
- 如果第i天没有交易（卖出），那么global[i][j]=global[i-1][j]；     
- 如果第i天有交易（卖出），那么global[i][j]=local[i][j]。    



```
/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Example
Given prices = [4,4,6,1,1,4,2,5], and k = 2, return 6.

Note
You may not engage in multiple transactions at the same time 
(i.e., you must sell the stock before you buy again).

Challenge
O(nk) time.

Tags Expand 
Dynamic Programming
*/


/*
Thoughts:
Similar to StockIII, but able to make k transactions.
K transactions divides leads to 2K + 1 different status: always have the two conditions 'before buying' and 'holding', plus the final sold status.

Equation:
on j%2 == 0 days (not having stock): 
    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + partialProfit);
on j%2 == 1 days (holding stock)
    dp[i][j] = Math.max(dp[i - 1][j] + partialProfit, dp[i - 1][j - 1]);

O(n(2*k + 1)) = O(nk)
space O(nk)
time O(nk)
*/
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0) {
            return 0;
        }
		int profit = 0;
        int n = prices.length;
        int statusLength = 2 * k + 1;

        // A side note: if k > n/2, the problem becomes easy: any n/2 number of transactions
        if (k >= n/2) {
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }
        
        int[][] dp = new int[n][statusLength];
        dp[0][0] = 0; // on day 0, having 0 stock, and with 0 transactions.

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < statusLength; j++) {
                //int partialProfit = prices[i] - prices[i - 1];
                if (j % 2 == 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i] - prices[i - 1]);
					// Find best profit when not having stock
                    profit = Math.max(profit, dp[i][j]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j] + prices[i] - prices[i - 1], dp[i - 1][j - 1]);
                }
            }
        }
        return profit;
    }
}

// Rolling array with %2
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0) {
            return 0;
        }
		int profit = 0;
        int n = prices.length;
        int statusLength = 2 * k + 1;

        // A side note: if k > n/2, the problem becomes easy: any n/2 number of transactions
        if (k >= n/2) {
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }
        
        int[][] dp = new int[2][statusLength];
        dp[0][0] = 0; // on day 0, having 0 stock, and with 0 transactions.

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < statusLength; j++) {
                //int partialProfit = prices[i] - prices[i - 1];
                if (j % 2 == 0) {
                    dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[(i - 1) % 2][j - 1] + prices[i] - prices[i - 1]);
					// Find best profit when not having stock
                    profit = Math.max(profit, dp[i % 2][j]);
                } else {
                    dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j] + prices[i] - prices[i - 1], dp[(i - 1) % 2][j - 1]);
                }
            }
        }
        return profit;
    }
}

// optimization: rolling array
// space: O(k), time: O(nk)
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0) {
            return 0;
        }
		int profit = 0;
        int n = prices.length;
		int statusLength = 2 * k + 1;

        // A side note: if k > n/2, the problem becomes easy: any n/2 number of transactions
        if (k >= n/2) {
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }

        int[][] dp = new int[2][statusLength];
        int prev, curr = 0;
        dp[0][0] = 0; // on day 0, having 0 stock, and with 0 transactions.
        
        for (int i = 1; i < n; i++) {
            // reverse rolling digit
            prev = curr;
            curr = 1 - prev;
            for (int j = 1; j < statusLength; j++) {
                //int partialProfit = prices[i] - prices[i - 1];
                if (j % 2 == 0) {
                    dp[curr][j] = Math.max(dp[prev][j], dp[prev][j - 1] + prices[i] - prices[i - 1]);
					// Find best profit when not having stock
                    profit = Math.max(profit, dp[curr][j]);
                } else {
                    dp[curr][j] = Math.max(dp[prev][j] + prices[i] - prices[i - 1], dp[prev][j - 1]);
                }
            }
        }
        return profit;
    }
}


/*
	Thoughts: http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html
	local[i][j] = max(global[i – 1][j – 1] , local[i – 1][j] + diff). WHY????
	global[i][j] = max(global[i – 1][j], local[i][j])
	
*/
class Solution {
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