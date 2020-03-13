M
tags: DP, Backpack DP, Memoization, DFS
time: O(n * S)
space: O(S)

给一串不同数额的coins, 和total amount to spent. 求 最少 用多少个coin可以组合到这个amount. 每种coins个数不限量.


#### DP, Bottom -> UP 从小到大的顺序!
- define dp[x], 积累到amount x, 最少用多少个coin
- function: `dp[x] = Math.min(dp[x], dp[x - coinValue] + 1)`. two branches based on choosing coinValue or not
- initialization
    - dp[0], zero amount uses 0 coin. so dp[0] = 0
    - Utilize `Integer.MAX_VALUE` as default val for initialize dp[x]: 1) alert error stage; 2) easy comparison

#### Method2: Memoization, DFS, Top->Down
- create subproblem: (coins, amount - pickedCoin)
- memo[i] 依然表示: min # of coints to make amount i
- initialize memo[i] = Integer.MAX_VALUE
- 先选最后一步(遍历coins),  然后dfs做同样的操作
- 记录memo[amount] 如果已经给过value, 不要重复计算, 直接return.
- time: O(n * S), worst case it runs n coins for S(amount) iterations
- space: O(S)

```
/*
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.

*/

/*
Method1: Bottom-Up DP
Last step: which coin can make amount? enumerate it
dp[i]: min # of coins to make i amount.
dp[i] = Math.min(dp[i - coinValueA], dp[i - coinValueB], dp[i - coinValueC], ...) + 1 

init: dp[0] = 0. No value, no coins. 

If dp[i] not found, set it to -1
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coin > i || dp[i - coin] == Integer.MAX_VALUE) continue; // coin too big, or last spot was invalid
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

/*
Method2: memoization, DFS
Pick one coin => amount - coin => coinChange(coins, amount - coin).
- memo[i]: min # for amount i.
- create memo[amount + 1]
- make memo global and memorize results
*/
class Solution {
    int[] memo;
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return - 1;
        memo = new int[amount + 1];
        for (int i = 1; i <= amount; i++) memo[i] = Integer.MAX_VALUE;
        return dfs(coins, amount);
    }
    
    private int dfs(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (memo[amount] != Integer.MAX_VALUE) return memo[amount];
        
        for (int coin : coins) {
            int count = dfs(coins, amount - coin);
            if (count >= 0) memo[amount] = Math.min(memo[amount], count + 1);
        }
        return memo[amount] = memo[amount] == Integer.MAX_VALUE ? -1 : memo[amount];
    }
}

```