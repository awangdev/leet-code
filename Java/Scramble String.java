H
1518594579
tags: DP, String

区间型
降维打击
dp[i][j][w]: 从i点和j点开始, 各自走w距离, 得到的S和T是否是scramble string.

具体思考过程看Thoughts.

注意: input s1, s2 在整个题目的主要内容里面, 几乎没有用到, 只是用在initialization时候.
很神奇, 这个initailization 打好了DP的基础, 后面一蹴而就, 用数学表达式就算出了结果.

```
/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/

/*
Thoughts:
中间砍一刀, 分开两边.
这第一要么是左右交换, 要么是不交换:
首先有了dp[i][j][k][h]: 分隔开来的S1 range[i, j] 跟 S2的[k,h]是否是scramble string?

变成了4维数组. 优化:
4维数组, 但是自由度只有3: 有3个变量可以确定第四个变量:
因为scramble string的长度相等, 所以 j - I = h - k => h = j  + k- I
也就是说, 我们可以降维, 不需要4维数组.

降维: 最后一个维度省略, 变成了 length为维度:
dp[i][j][k]: 数组S1从index i 开始, S2从index j 开始, 长度为k的子串, 是否为scramble string? 

等式需要细心写出来:
分割scramble过后, 原string定为S, 生成的string定为T
分割开来一刀, 分别割成 S1,S2, 和 T1,T2
假设S/T的总长都是k, 而分割点割在了位置为w的地方.
两种情况:
子序列不换位置: 要求分割开来的S1和T1是scramble, S2和T2是scramble
S1和T1的关系: dp[i][j][w] => s1 从 index[i] 割 w length, s2从index[j]割了 w length; 是否是scramble string
S2和T2的关系: dp[i + w][j + w][k - w] => S2和T2都在割过w length后的地方, 也就是i+w, j+w; 长度是总长减去w: k - w.
子序列换位置:要求分开的s1和s2换位置; 也就是s1对应的t1, 现在跑到了后面的位置
S1和T1的关系: s1在原地, t1换到了后面: t1还是w length, 所以位置变成了 j + (k - w)
S2和T2的关系: s2照旧在i+w的位置,  t2换到了最前面: index j

综合上面的 情况, 并且让w循环[1, k-1]
dp[i][j][k] = OR{dp[i][j][w] && dp[i + w][j + w][k - w]}, where w = [1, k-1]
	OR
	      OR{dp[i][j + k - w][w] && dp[i + w][j][k - w]}, where w = [1, k-1]
 */

 class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];
        
        // len = 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        // len = 2
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                for (int j = 0; j <= n - len; j++) {
                    // dp[i][j][len] = false; // default is false as well
                    for (int w = 1; w < len; w++) {
                        dp[i][j][len] |= (dp[i][j][w] && dp[i + w][j + w][len - w]);
                        dp[i][j][len] |= (dp[i][j + len - w][w] && dp[i + w][j][len - w]);
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}
```