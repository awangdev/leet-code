M
tags: DP, Backpack DP
time: O(n)
space: O(n)

给串数字, target amount, 求总共多少种方式可以reach the amount.

#### DP
- O(MN): M, total target amount; N: size of coins
- 类似于: 网格dp, unique path 里面的2种走法: 从上到下, 从左到右
- 状态: dp[i]: sum of ways that coins can add up to i.
- Function: dp[j] += dp[j - coins[i]];
- Init: dp[0] = 1 for ease of calculation; other dp[i] = 0 by default
- note: 避免重复count, 所以 j = coins[i] as start
- 注意 coins 需要放在for loop 外面, 主导换coin的流程, 每个coin可以用无数次, 所以在每一个sum value上都尝试用一次每个coin

#### knapsack problem: backpack problem

```
/*
You are given coins of different denominations and a total amount of money. 
Write a function to compute the number of combinations that make up that amount. 
You may assume that you have infinite number of each kind of coin.

Note: You can assume that

0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10] 
Output: 1
*/

/*
Thoughts:
dp[i]: sum of all possibilities that adds up the value to be i from last step dp[i - coins[j]].
Avoid redundant calculation: put j = coins[i] as start of inner for loop
*/
class Solution {
    public int change(int amount, int[] coins) {
        if (amount == 0) return 1;
        int[] dp = new int[amount + 1];
        dp[0] = 1; // for dp, not quite meaning full: 1 way to build 0 amount
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
```