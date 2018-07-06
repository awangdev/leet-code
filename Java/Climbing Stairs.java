E
1521695977
tags: DP, Memoization, Sequence DP

每一步可以走1步或者2步, 求总共多少种方法爬完梯子.

#### Recursive + Memoization
- 递归很好写, 但是重复计算, timeout. time: O(2^n)
- O(2^n): each n can spawn 2 dfs child, at next level, it will keep spawn. Total 2^n nodes will spawn.
- 用全局变量int[] memo 帮助减少重复计算
- O(n) time, space

#### DP
- 最后一步被前两种走法决定: dp[i] = dp[i - 1] + dp[i - 2]
- 基础sequence DP, int[] dp = int[n + 1];
- DP[]存的是以 1-based index的状态
- 需要知道dp[n] 的状态, 但是最大坐标是[n-1], 所以int[n+1]
- dp[0]往往是有特殊状态的
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
        if (n <= 1) {
            return 1;
        }
        memo = new int[n];
        return dfs(n - 1) + dfs(n - 2);
    }
    
    public int dfs(int n) {
        if (n <= 1) {
            return 1;
        }
        if (memo[n] > 0) {
            return memo[n];
        }
        memo[n - 1] = dfs(n - 1);
        memo[n - 2] = dfs(n - 2);
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

/*
    Based on the DP solution, think about rolling array.
    We only make use of i, i-1, and i-2. 
    Ideally, we can reduce them just to 3 variables

    1. Replace the variable
    2. Implement the rotation process:
        prev2 = prev1;
        prev1 = rst;
*/
public class Solution {
    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int rst = 0;
        int prev2 = 1;
        int prev1 = 1;
        for (int i = 2; i <= n; i++) {
            rst = prev1 + prev2;
            prev2 = prev1;
            prev1 = rst;
        }
        return rst;
    }
}

/*
    Recap 3.25.2016
    Naturally think of dfs. Any time when n drops to <= 0, count++
    Exceed time limit
*/

public class Solution {
    public int count = 0;
    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }
        dfs(n);
        return count;
    }

    public void dfs(int n) {
        if (n <= 0) {
            count++;
            return;
        } else if (n == 1) {
            count++;
            return;
        }
        dfs(n - 1);
        dfs(n - 2);
    }
}


/*
Thinking process:
State: at i level, f[i] is the ways to climb to i position.
Function: f[i] = f[i-1] + f[i-2]. 
    f[i] is constructed from 2 branches: 
        Last step is 1 from f[i-1]
        Last step is 2 from f[i-2]
    This idea can be presented using a tree. However we don’t need to do recursive. We just need to use two pointers to withhold 2 level’s values.
Init: The for loop starts at level2, so before level 2 there are 2 init states:
    f[0] == 1. This means we jump 2 steps from level0 to level2. 
    f[i] == 1.  This means we jump 1 steps to level1, then jump another step to level2
Answer: f[n]
*/
public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int last = 1;   //Init f[1]
        int lastlast = 1;   //Init f[0]
        int now = 0;
        for (int i = 2; i <= n; i++) {  //Start from level2
            now = last + lastlast;
            lastlast = last;
            last = now;
        }
        return now;
    }
}



```