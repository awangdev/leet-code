H
1521702603
tags: Array, DP, Game Theory, Interval DP, Memoization

还是2个人拿n个coin, coin可以有不同的value. 

只不过这次选手可以从任意的一头拿, 而不限制从一头拿. 算先手会不会赢?

#### Memoization + Search
- 跟Coins in a Line II 一样, MaxiMin的思想: 找到我的劣势中的最大值
- dp[i][j] 代表在[i,j]区间上 选手最多能取的value 总和
- 同样, sum[i][j]表示[i] 到 [j]间的value总和
- 对手的最差情况, 也就是先手的最好情况:
- dp[i][j] = sum[i][j] - Math.min(dp[i][j - 1], dp[i + 1][j]);
- 这里需要search, 画出tree可以看明白是如何根据取前后而分段的.

#### 博弈 + 区间DP
- 因为是看区间[i,j]的情况, 所以可以想到是区间 DP
- 这个方法需要复习, 跟数学表达式的推断相关联: S(x) = - S(y) + m. 参考下面的公式推导.
- dp[i][j]表示 从index(i) 到 index(j), 先手可以拿到的最大值与对手的数字差. 也就是S(x).
- 其中一个S(x) = dp[i][j] = a[i] - dp[i + 1][j]
- m 取在开头, m 取在末尾的两种情况:
- dp[i][j] = max{a[i] - dp[i + 1][j], a[j] - dp[i][j - 1]}
- len = 1, 积分就是values[i]
- 最后判断 dp[0][n] >= 0, 最大数字和之差大于0, 就赢.
- 时间/空间 O(n^2)

##### 公式推导
- S(x) = X - Y, 找最大数字和之差, 这里X和Y是选手X的总分, 选手Y的总分. 
- 对于选手X而言: 如果S(x)最大值大于0, 就是赢了; 如果最大值都小于0, 就一定是输了. 
- 选手Y: S(y)来表示 对于Y,  最大数字和之差. S(y) = Y - X
- 根据S(x) 来看, 如果从 数字和X里面, 拿出一个数字 m, 也就是 X = m + Xwithout(m)
- S(x) = m + Xwithout(m) - Y = m + (Xwithout(m) - Y). 
- 如果我们从全局里面索性去掉m, 那么 S(y'') = Y - Xwithout(m)
- 那么推算下来: S(x) = m + (Xwithout(m) - Y) = m - (Y - Xwithout(m)) = m - S(y'')
- 在这个问题里面, 我们model X 和 Y的时候, 其实都是 dp[i][j], 而区别在于先手/后手.
- 将公式套用, 某一个S(x) = a[i] - dp[i + 1][j],  也就是m=a[i], 而 S(y'') = dp[i + 1][j]

##### 注意
- 如果考虑计算先手[i, j]之间的最大值, 然后可能还需要两个数组, 最后用于比较先手和opponent的得分大小 => 那么就要多开维.
- 我们这里考虑的数字差, 刚好让人不需要计算先手的得分总值, 非常巧妙.
- Trick: 利用差值公式, 推导有点难想到.

##### 区间型动态规划
- 找出[i, j]区间内的性质: dp[i][j]下标表示区间范围 [i, j]
- 子问题: 砍头, 砍尾, 砍头砍尾
- loop应该基于区间的length
- template: 考虑len = 1, len = 2; 设定i的时候一定是 i <= n - len; 设定j的时候, j = len + i - 1;


```
/*
There are n coins in a line. Two players take turns to take a coin from one of the ends of the line
until there are no more coins left. The player with the larger amount of money wins.

Could you please decide the first player will win or lose?

Example
Given array A = [3,2,2], return true.

Given array A = [1,2,4], return true.

Given array A = [1,20,4], return false.

Challenge 
Follow Up Question:

If n is even. Is there any hacky algorithm that can decide whether first player will win 
or lose in O(1) memory and O(n) time?

Tags 
Array Dynamic Programming Game Theory
*/

/*
Thoughts:
MiniMax concept, memoization dp.
dp[i][j]: max sum of values a player can get in range [i, j] 
sum[i][j]: sum of value in range [i, j]
dp[i][j] = sum[i][j] - Math.min(dp[i + 1][j], dp[i][j - 1]);
*/
public class Solution {
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }
        int n = values.length;
        
        int[][] sum = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 1];
        boolean[][] visited = new boolean[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    sum[i][j] = values[i];
                } else {
                    sum[i][j] = sum[i][j - 1] + values[j];
                }
            }
        }
        
        // total
        int total = 0;
        for (int value : values) {
            total += value;
        }
        
        search(0, n - 1, visited, dp, sum, values);
        return dp[0][n - 1] > total / 2;
    }
    
    private void search(int i, int j, boolean[][] visited, int[][] dp, int[][] sum, int[] values) {
        if (visited[i][j]) {
            return;
        }
        
        visited[i][j] = true;

        if (i == j) {
            dp[i][j] = values[i];
        } else if (i > j) {
            dp[i][j] = 0;
        } else if (i + 1 == j) {
            dp[i][j] = Math.max(values[i], values[j]);
        } else {
            search(i + 1, j, visited, dp, sum, values);
            search(i, j - 1, visited, dp, sum, values);
            dp[i][j] = sum[i][j] - Math.min(dp[i + 1][j], dp[i][j - 1]);
        }
    }
}

//using flat to mark visited; actually dp[i][j] > 0 will mean visited, since coin value > 0
public class Solution {
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }
        int n = values.length;
        
        int[][] sum = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    sum[i][j] = values[i];
                } else {
                    sum[i][j] = sum[i][j - 1] + values[j];
                }
            }
        }
        
        // total
        int total = 0;
        for (int value : values) {
            total += value;
        }
        
        search(0, n - 1, dp, sum, values);
        return dp[0][n - 1] > total / 2;
    }
    
    private void search(int i, int j, int[][] dp, int[][] sum, int[] values) {
        if (dp[i][j] > 0)  {
            return;
        }
        if (i == j) {
            dp[i][j] = values[i];
        } else if (i > j) {
            dp[i][j] = 0;
        } else if (i + 1 == j) {
            dp[i][j] = Math.max(values[i], values[j]);
        } else {
            search(i + 1, j, dp, sum, values);
            search(i, j - 1, dp, sum, values);
            dp[i][j] = sum[i][j] - Math.min(dp[i + 1][j], dp[i][j - 1]);
        }
    }
}


/*

博弈. 这题, 是区间型.
区间型标志: 每人每次只能取第一个数,或者最后一个数
翻译: 每次砍头, 或者砍尾

trick, 记录自己的数字与对手的数字和之差:
S(x) = X - Y, 找最大数字差. 如果最大值都大于0, 就是赢了; 如果小于0, 就输了. 
这里我们用S(x)表示对于x先手而言的数字差, S(y)表示对于opponent y 先手而言的数字差.
假设x先手的时候, S(x) = X - Y. 这一步拿掉了大小为m的coin.
当opponent变成先手时候, 剩下的coins 被分割成x’, y’, 就有subset的S’(y) = y’ - x’
Overall S(y) = Y - X = y’ - x’ - m = S’(y) - m
同时S(x) = X - Y = -(S’(y) - m) = m - S’(y)
注意: 这里的S’(y)面对的是拿过coins剩下的局面.

dp[i][j]表示 从index(i) 到 index(j), 先手可以拿到的最大值与对手的数字差. 也就是S(x) = X - Y.

那么S(x) = X - Y = a[i] - dp[i + 1][j]; // 砍头
X = a[i]. 那里第i个coin
dp[i + 1][j]: opponent从 i 位之后能积累的最大值
a[j] - dp[i][j - 1]//砍尾

dp[i][j] = max{a[i] - dp[i + 1][j], a[j] - dp[i][j - 1]}

最后看dp[0][n] >= 0


 */
public class Solution {
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }

        int n = values.length;
        int[][] dp = new int[n][n];

        // len = 1        
        for (int i = 0; i < n; i++) {
            dp[i][i] = values[i];
        }
        
        // len = 2   
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = len + i - 1;
                dp[i][j] = Math.max(values[i] - dp[i + 1][j], values[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }
}
```