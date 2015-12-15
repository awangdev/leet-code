这题没见过的时候，要分析分析。分析出来了些个DP解决。
However, 分析过后简直屌炸天。一个 n%3就解决了。纯粹math.
```
/*
There are n coins in a line. Two players take turns to take one or two coins from right side until there are no more coins left. The player who take the last coin wins.

Could you please decide the first play will win or lose?

Example
n = 1, return true.

n = 2, return true.

n = 3, return false.

n = 4, return true.

n = 5, return true.

Challenge
O(n) time and O(1) memory

Tags Expand 
Greedy Dynamic Programming Array Game Theory
*/

/*
Thoughts:
Clarify: '1st play will win' means: if play properly, 
1st play will surely have a way to win that 2nd play can't beat.
Make dp[i]: the result when n = i.
fn:
Think back a step, state-1:
When it's play1's turn, there might be 1 or 2 coins left so he can win. so -1, or -2.
THink back a 2nd step, state-2:
Play2 take 1 or 2 to get into state-1. play2 may take 1 or 2 coins. so again, -1 or -2.

So consider i-1, i-2, i-3, or i-4. Note, the next stemp would be for play2, then play1. 
However, if left are 1,2 coins for play2, play2 wins; if left are 4 coins, play2 wins; only when left are 3 coins, play2 will surely lose, so play1 win.
Therefore, there is just 1 case to consider: if left are 3 coins, and it's play2's turn, then play1 surely wins.
so fn:
dp[i] = dp[i-3]

Init:
dp[0] = false; dp[1], dp[2] = tru; dp[3] = false;

Result:
dp[n]

*/ 
/* O(n) time and O(n)space.*/
public class Solution {
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        if (n <= 0) {
            return false;
        }
    	if (n <= 2) {
    		return true;
    	}
    	boolean[] dp = new boolean[n + 1];
    	dp[1] = true;
    	dp[2] = true;
    	dp[3] = false;
    	for (int i = 4; i <= n; i++) {
    		dp[i] =  dp[i - 3];
    	}
    	return dp[n];
    }
}

/* 
	O(1) time and O(1)space.
	Base on the analysis in 1st attempt:
	As long as it's not product of 3
	However, this is pretty ... math. Not quite useful to test coding ability.
*/

public class Solution {
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        if (n <= 0) {
            return false;
        }
        return n % 3 != 0;
    }
}

```