E
tags: DP, Memoization, Sequence DP

每一步可以走1步或者2步, 求总共多少种方法爬完梯子.

#### Recursive + Memoization
- 递归很好写, 但是重复计算, timeout. time: O(2^n)
- O(2^n): each n can spawn 2 dfs child, at next level, it will keep spawn. Total 2^n nodes will spawn.
- 用全局变量int[] memo 帮助减少重复计算
- O(n) time, space

#### DP
- 加法原理, 最后一步被前两种走法决定: dp[i] = dp[i - 1] + dp[i - 2]
- 基础sequence DP, int[] dp = int[n + 1];
- DP[]存的是以 1-based index的状态
- dp[i]: count # of ways to finish 前i个 台阶
- 需要知道dp[n] 的状态, 但是最大坐标是[n-1], 所以int[n+1]
    - dp[0]往往是有特殊状态的. 这里, dp[0]: 1种方式可以原地不动
    - dp[1]=1, 1种方式到达index=1, 
    - 之后的`dp[2] = dp[0]+dp[1]`: 就是dp[0]的走法 or dp[1]的走法
- O(n) space, time

#### 序列DP, 滚动数组
- [i] only associates with [i-2], [i-1].
- %2
- O(1) space

```
/*

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. 
In how many distinct ways can you climb to the top?

Dynamic Programming

*/

/*
Thoughts:
Can be recursive, it goes like: climbStairs(n-1) + climbStairs(n - 2)
That has too much of redundant calculation. 
Improve by Memoization
*/
class Solution {
    int[] memo;
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        memo = new int[n];
        return climb(n - 1) + climb(n - 2);
    }
    
    public int climb(int n) {
        if (n <= 1) return 1;
        if (memo[n] > 0) return memo[n];
        memo[n - 1] = climb(n - 1);
        memo[n - 2] = climb(n - 2);
        return memo[n - 1] + memo[n - 2];
    }
}


/*
Thoughts:
DP, consider the last step. It can be reached by 2 steps or 1 steps.
DP[i] represents # ways to reach index i.
DP[i] = DP[i - 1] + DP[i - 2].

Create DP = int [n + 1]
init: DP[0] = 1; DP[1] = 1;

Return DP[n]
*/
class Solution {
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
}

/*
Rolling array
[i] only associates with i-2, i-1.
*/
class Solution {
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[2];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i % 2] = dp[(i - 1) % 2] + dp[(i - 2) % 2];
        }
        
        return dp[n % 2];
    }
}

```