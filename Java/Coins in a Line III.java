H
1518677689

博弈 + 区间. 
S(x) = X - Y, 找最大数字差. 如果最大值都大于0, 就是赢了; 如果小于0, 就输了. 
dp[i][j]表示 从index(i) 到 index(j), 先手可以拿到的最大值与对手的数字差. 也就是S(x) = X - Y.
dp[i][j] = max{a[i] - dp[i + 1][j], a[j] - dp[i][j - 1]}

最后判断 dp[0][n] >= 0

区间型动态规划:
找出[i, j]区间内的性质.
子问题: 砍头, 砍尾, 砍头砍尾
loop应该基于区间的length
template: 考虑len = 1, len = 2; 设定i的时候一定是 i <= n - len; 设定j的时候, j = len + i - 1;

注意: 如果考虑计算先手[i, j]之间的最大值, 然后可能还需要两个数组, 最后用于比较先手和opponent的得分大小 => 那么就要多开维.
我们这里考虑的数字差, 刚好让人不需要计算先手的得分总值, 非常巧妙.

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