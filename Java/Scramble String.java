H
1518594579
tags: DP, String, Interval DP

- 给两个string S, T. 检验他们是不是scramble string.
- scramble string 定义: string可以被分拆成binary tree的形式, 也就是切割成substring;
- 旋转了不是leaf的node之后, 形成新的substring, 这就是原来string的 scramble.


#### Interval DP 区间型
- 降维打击, 分割, 按照长度来dp.
- dp[i][j][k]: 数组S从index i 开始, T从index j 开始, 长度为k的子串, 是否为scramble string

##### Break down
- 一切两半以后, 看两种情况: , 或者不rotate这两半. 对于这些substring, 各自验证他们是否scramble.
- 不rotate分割的两半: S[part1] 对应  T[part1] && S[part2] 对应  T[part2]. 
- rotate分割的两半: S[part1] 对应  T[part2] && S[part2] 对应  T[part1]. 

##### Initialization
- len == 1的时候, 其实无法旋转, 也就是看S,T的相对应的index是否字符相等.
- initialization非常非常重要. 很神奇, 这个initailization 打好了DP的基础, 后面一蹴而就, 用数学表达式就算出了结果.
- input s1, s2 在整个题目的主要内容里面, 几乎没有用到, 只是用在initialization时候.
- More details, 看解答

```
/*
Given a string s1, we may represent it as a binary tree by partitioning it 
to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, 
it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", 
it produces a scrambled string "rgtae".

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
Want to determin the given string S can be scramble/rotate to T. 
With the tree formation, it leads us to think of the string in partition: can S[i,j] be scambled to T[i,j]?
If all substrings can be in scrambled format, it'll return true.

dp[i][j][h][k]: can S(i, j) be scrambled to T(h, k)?

Reduce it to dp[i][j][k]: starting from index i for S, index j for T, with length k. 
Can the substrings be scramble?
End: want dp[0][0][n] to be srambled.

Need size to be boolean dp[n][n][n + 1]
with len == 0, always false
with len == 1, if S[i]==T[j], then true.

with len == 2, do dp;
increase length from 1 ~ len, perform dp.

Consider two conditions:
rotate parent string || not rotating parent string

*/
class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return s1 == null && s2 == null;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];
        int len = 1;
        // Initialize with len = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        
        // len = 2
        for (len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                for (int j = 0; j <= n - len; j++) {
                    for (int w = 1; w < len; w++) { // w = length of 1st substring
                        dp[i][j][len] |= dp[i][j][w] && dp[i + w][j + w][len - w]; // not rotating parent string
                        dp[i][j][len] |= dp[i][j + (len - w)][w] && dp[i + w][j][len - w]; // not rotating parent string
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}

/*
Thoughts:
两个string, 代号 S, T
中间砍一刀, 分开两边. 要么是左右交换, 要么是不交换.
首先有了dp[i][j][k][h]: 
分隔开来的S[i, j] 跟 T[k, h]是否是scramble string?
want to have dp[0][n][0][n] == true

变成了4维数组. 优化:
4维数组, 但是自由度只有3: 有3个变量可以确定第四个变量:
因为scramble string的长度相等, 所以 j - I = h - k => h = j  + k- I
也就是说, 我们可以降维, 不需要4维数组.

降维: 最后一个维度省略, 变成了 length为维度:
*** dp[i][j][k]: 数组S从index i 开始, T从index j 开始, 长度为k的子串, 是否为scramble string? 

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