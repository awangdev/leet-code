H
1524723994
tags: DP, Backpack DP

给n种不同的物品, int[] A weight, int[] V value, 每种物品可以用无限次

问最大多少value可以装进size是 m 的包?

#### DP
- 可以无限使用物品, 就失去了last i, last unique item的意义: 因为可以重复使用.
- 所以可以转换一个角度:
- 1. 用i **种** 物品, 拼出w, 并且满足题目条件(max value). 这里因为item i可以无限次使用, 所以考虑使用了多少次K.
- 2. K虽然可以无限, 但是也被 k*A[i]所限制: 最大不能超过背包大小.
- dp[i][w]: 前i种物品, fill weight w 的背包, 最大价值是多少.
- dp[i][w] = max {dp[i - 1][w - k*A[i-1]] + kV[i-1]}, k >= 0
- Time O(nmk)
- 如果k = 0 或者 1, 其实就是 Backpack II: 拿或者不拿

#### 优化
- 优化时间复杂度, 画图发现:
- 所计算的 (dp[i - 1][j - k*A[i - 1]] + k * V[i - 1]) 
- 其实跟同一行的 dp[i][j-A[i-1]] 那个格子, 就多出了 V[i-1]
- 所以没必要每次都 loop over k times
- 简化: dp[i][j] 其中一个可能就是: dp[i][j - A[i - 1]] + V[i - 1]
- Time O(mn)

#### 空间优化到1维数组
- 根据上一个优化的情况, 画出 2 rows 网格
- 发现 dp[i][j] 取决于: 1. dp[i - 1][j], 2. dp[i][j - A[i - 1]]
- 其中: dp[i - 1][j] 是上一轮 (i-1) 的结算结果, 一定是已经算好, ready to be used 的
- 然而, 当我们 i++,j++ 之后, 在之前 row = i - 1, col < j的格子, 全部不需要.
- 降维简化: 只需要留着 weigth 这个 dimension, 而i这个dimension 可以省略: 
- (i - 1) row 不过是需要用到之前算出的旧value: 每一轮, j = [0 ~ m], 那么dp[j]本身就有记录旧值的功能.
- 变成1个一位数组
- 降维优化的重点: 看双行的左右计算方向
- Time(mn). Space(m)

```
/*
Given n kind of items with size Ai and value Vi 
(each item has an infinite number available) 
and a backpack with size m. 

What's the maximum value can you put into the backpack?

Notice
You cannot divide item into small pieces and the total size of items 
you choose should smaller or equal to m.
*/


/*
Thoughts:
dp[i][w]: first i types of items to fill weight w, find the max value.
1st loop: which type of item to pick from A
2nd loop: weight from 0 ~ m
3rd loop: # times when A[i] is used.

Goal: dp[n][m]

Condition1: didn't pick A[i - 1], dp[i][j] = dp[i - 1][j];
Condition2: pickced A[i - 1], dp[i][j] = dp[i - 1][j - k * A[i - 1]] + k * V[i - 1];

O(nmk)
*/
public class Solution {
    public int backPackIII(int[] A, int[] V, int m) {
        if (A == null || A.length == 0 || V == null || V.length == 0 || m <= 0) {
            return 0;
        }
        int n = A.length;
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 0; // 0 items to fill 0 weight, value = 0
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k * A[i - 1] <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * A[i - 1]] + k * V[i - 1]);
                }
            }
        }
        
        return dp[n][m];
    }
}

/**
Optimization1: 
- 优化时间复杂度, 画图发现:
- 所计算的 (dp[i - 1][j - k*A[i - 1]] + k * V[i - 1]) 
- 其实跟同一行的 dp[i][j-A[i-1]] 那个格子, 就多出了 V[i-1]
- 所以没必要每次都 loop over k times
- 简化: dp[i][j] 其中一个可能就是: dp[i][j - A[i - 1]] + V[i - 1]

*/
public class Solution {
    public int backPackIII(int[] A, int[] V, int m) {
        if (A == null || A.length == 0 || V == null || V.length == 0 || m <= 0) {
            return 0;
        }
        int n = A.length;
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 0; // 0 items to fill 0 weight, value = 0
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= A[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        return dp[n][m];
    }
}

/**
Optimization2: 
- 根据上一个优化的情况, 画出 2 rows 网格
- 发现 dp[i][j] 取决于: 1. dp[i - 1][j], 2. dp[i][j - A[i - 1]]
- 其中: dp[i - 1][j] 是上一轮 (i-1) 的结算结果, 一定是已经算好, ready to be used 的
- 然而, 当我们 i++,j++ 之后, 在之前 row = i - 1, col < j的格子, 全部不需要.
- 降维简化: 只需要留着 weigth 这个 dimension, 而i这个dimension 可以省略: 
- (i - 1) row 不过是需要用到之前算出的旧value: 每一轮, j = [0 ~ m], 那么dp[j]本身就有记录旧值的功能.
*/
public class Solution {
    public int backPackIII(int[] A, int[] V, int m) {
        if (A == null || A.length == 0 || V == null || V.length == 0 || m <= 0) {
            return 0;
        }
        int n = A.length;
        int[] dp = new int[m + 1]; // DP on weight
        dp[0] = 0; // 0 items to fill 0 weight, value = 0
        
        for (int i = 1; i <= n; i++) {
            for (int j = A[i - 1]; j <= m && j >= A[i - 1]; j++) {
                dp[j] = Math.max(dp[j], dp[j - A[i - 1]] + V[i - 1]);
            }
        }
        return dp[m];
    }
}

/*
Thoughts:
Can pick any item for infinite times: there is no indicator of what's being picked last.
We don't know which item && how many times it was picked.
We should consider tracking: what type of items was picked how many times
(consider once done with 1 type of item, move on to others and never re-pick)
If A[i-1] was picked 0, 1, 2 ...., k times, then
dp[i][w] = max{dp[i - 1][j - k*A[i - 1]] + k*V[i - 1]}, where k >= 0 -> infinite

Space: O(mn)
Time: O(m * m * n) = O(nm^2)
*/
public class Solution {
    public int backPackIII(int[] A, int[] V, int m) {
        if (A == null || V == null || A.length != V.length) {
            return 0;
        }
        int n = A.length;
        int[][] dp = new int[n + 1][m + 1]; // max value with i items of weight w.
        for (int j = 0; j <= m; j++) {
            dp[0][j] = -1; // 0 items cannot form j weight, hence value = 0
        }
        dp[0][0] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) { // for all weight j at i items
                for (int k = 0; k * A[i - 1] <= m; k++) { // use A[i-1] for k times
                    if (j - k * A[i - 1] >= 0 && dp[i - 1][j - k * A[i - 1]] != -1) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * A[i - 1]] + k * V[i - 1]);
                    }
                }
            }
        }
        int rst = 0;
        for (int j = 0; j <= m; j++) {
            rst = Math.max(rst, dp[n][j]);
        }
        return rst;
    }
}



// Optimization
// curve up
/*
dp[i][w] = max{dp[i - 1][j - k*A[i - 1]] + k*V[i - 1]}, where k >= 0 -> infinite
1. Every position, we are adding k*V[i - 1]
2. If we draw out how V[i-1] was being added alone with k = [0 ~ ...], we realize:
    the next i is basically: max{...all k's possibilities} + V[i - 1]
So it reduces to:
dp[i][w] = max{dp[i - 1][w], dp[i][w - A[i-1]] + V[i-1]}

*/

public class Solution {
    public int backPackIII(int[] A, int[] V, int m) {
        if (A == null || V == null || A.length != V.length) {
            return 0;
        }
        int n = A.length;
        int[][] dp = new int[n + 1][m + 1]; // max value with i items of weight w.
        for (int j = 0; j <= m; j++) {
            dp[0][j] = -1; // 0 items cannot form j weight, hence value = 0
        }
        dp[0][0] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) { // for all weight j at i items
                dp[i][j] = dp[i - 1][j];
                if (j - A[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        int rst = 0;
        for (int j = 0; j <= m; j++) {
            rst = Math.max(rst, dp[n][j]);
        }
        return rst;
    }
}
```