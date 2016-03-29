E

方法1: DP。爬坡到i点总共有的方法，取决于i-1点和i-2的情况。也就是DP(i-1) + DP(i-2).

还可以用滚动数组优化一点：因为用到的变量就只有i,i-1,i-2，可以被替代。
   注意要写好‘滚动’的代码。

方法2: DFS但是timeout

```
/*

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Dynamic Programming

*/

/*
    3.25.2016 recap DP
    DP[i] is dependent on wether it was reached by 2 steps or just 1 step:
    DP[i] = DP[i - 1] + DP[i - 2]
*/
public class Solution {
    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] DP = new int[n + 1];
        DP[0] = 1;
        DP[1] = 1;
        for (int i = 2; i <= n; i++) {
            DP[i] = DP[i - 1] + DP[i - 2];
        }
        return DP[n];
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