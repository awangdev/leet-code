E

这题目很有意思. 一开始分析的太复杂, 最后按照这个哥们的想法（http://yuanhsh.iteye.com/blog/2219891） 的来做，反而简单了许多。
设定T（n）的做法，最后题目化简以后就跟Fibonacci number一样一样的。详细分析如下。
做完，还是觉得如有神。本来是个Easy题，想不到，就是搞不出。

12.13.2015再看了一下：
因为最多2个fence 颜色相同。
假设i是和 i-1不同，那么结果就是 (k-1)*dp[i - 1]
假设i是何 i-1相同，那么根据条件，i-1和i-2肯定不同。那么所有的结果就是(k-1)*dp[i-2]
加在一起就有了。
```
/*
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Tags: Dynamic Programming
Similar Problems: (E) House Robber, (M) House Robber II, (M) Paint House, (H) Paint House II

*/

/*
Thoughts:
Inspiration(http://yuanhsh.iteye.com/blog/2219891)
Consider posts from 1 ~ n. Now we look at last post, marked n:
S(n) means: last 2 fence posts have same color.
	Note: S(n) will equal to whatever that's on n-1 position.
	Also, just because n and n-1 are same, that means n-2 and n-1 have to be differnet.
SO:
S(n) = D(n - 1)
D(n) means: last 2 fence posts have different color.
	Note: for n - 1, and n-2 positions, we have 2 different conditions:
	For example: xxy, or wxy, same 2 x's or different w vs. x.
So: 
D(n) = (k - 1) * (D(n - 1) + S(n - 1))

We can also create T(n) = S(n) + D(n); //T(n) is our totoal results. Will need to return T(n);
Use above equations to figure out T(n)
T(n) = S(n) + D(n) = D(n - 1) + (k - 1) * (D(n - 1) + S(n - 1))
	= D(n - 1) + (k - 1)(T(n - 1))
	= (k - 1) * (D(n - 2) + S(n - 2)) + (k - 1)(T(n - 1))
	= (k - 1)(T(n - 1) + T(n - 2))
	Since n-2 >=1, so n>=3. We need fiture out cases for n = 0,1,2,3

Note:
n == 1: just k ways
n == 0: just 0.
k == 0: just 0;
Besides these cases, we are okay. Btw, k does not really matter as long as it's >=1, it can be plugged in.
*/

public class Solution {
    public int numWays(int n, int k) {
  		if (n <= 1 || k <= 0) {
  			return n * k;
  		}
  		int[] dp = new int[n + 1]; //index based : 1
  		dp[0] = 0;
  		dp[1] = k;
  		dp[2] = k + k*(k - 1);
  		for (int i = 3; i <= n; i++) {
  			dp[i] = (k - 1) * (dp[i - 1] + dp[i - 2]);
  		}
  		return dp[n];
    }
}
```