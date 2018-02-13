R
1518502818

可以无限使用物品, 就失去了last i, last unique item的意义: 因为可以重复使用.

1. 所以可以转换一个角度: 用i种物品, 拼出w, 并且满足题目条件(max value).
这里因为item i可以无限次使用, 所以要遍历使用了多少次K.

2. K虽然可以无限, 但是也被 k*A[i]所限制: 最大不能超过背包大小.

这样一来, 就close loop, 可以做了.

优化: Review
降维: 需要画图review
变成1个一位数组: 看双行的左右计算方向

```
/*
Given n kind of items with size Ai and value Vi( each item has an infinite number available) 
and a backpack with size m. What's the maximum value can you put into the backpack?

 Notice
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
*/

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
                for (int k = 0; k * A[i - 1] <= m; k++) {
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