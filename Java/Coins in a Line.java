M
1521605526
tags: DP, Greedy, Game Theory

拿棋子游戏, 每个人可以拿1个或者2个, 拿走最后一个子儿的输. 问: 根据给的棋子输, 是否能确定先手的输赢?

Game Theory: 如果我要赢, 后手得到的局面一定要'有输的可能'.

#### DP, Game Theory
- 要赢, 必须保证对手拿到棋盘时, 在所有他可走的情况中, '有可能败', 那就足够.
- 设计dp[i]:表示我面对i个coins的局面时是否能赢, 取决于我拿掉1个,或者2个时, 对手是不是会可能输?
- dp[i] = !dp[i - 1] || !dp[i-2]
- 时间: O(n), 空间O(n)
- 博弈问题, 常从'我的第一步'角度分析, 因为此时局面最简单.

#### Rolling Array
空间优化O(1). Rolling array, %2

```
/*
There are n coins in a line. 
Two players take turns to take one or two coins from right side 
until there are no more coins left. 
The player who take the last coin wins.

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
Game theory: if the first-hand wants to win, the opponent should be able to lose in the following move.
The player wins if there is only 1 or 2 coins on the plate.
Goal: at current state, make a move and make sure the next play definitely loses. 
Meet either one of the conditions:
- 1st player picks 1 coin, 2nd play must lose with n - 1 coins;
- 1st player picks 2 coins, 2nd play must lose with n - 2 coins;

dp[i] = true/false: facing i coins,can player win?
dp[i] = !dp[i - 1] || !dp[i - 2];
Meaning: either at (i-1) or (i-2), as long as the opponent player has possibilty to lose, 
it's 100% win for first player.

- Size of dp should be n + 1; we are counting 0 coins.
- init: true for 1 or 2 coins
O(n)
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
        boolean[] dp = new boolean[n + 1];
        dp[0] = false; // no coins to pick.
        dp[1] = true; // the player wins; of course dp[2]=true as well for the player, but it'll be calculated below
        
        for (int i = 2; i <= n; i++) {
            dp[i] = !dp[i - 2] || !dp[i - 1];
        }
        return dp[n];
    }
}

/*
Rolling Array
[i] only associates with [i - 1] && [i - 2]: rolling array %

Time: O(n) 
Space: O(1)
*/

public class Solution {
    public boolean firstWillWin(int n) {
        if (n <= 0) {
            return false;
        }
        boolean[] dp = new boolean[2];
        dp[0] = false;
        dp[1] = true;
        for (int i = 2; i <= n; i++) {
            dp[i % 2] = !dp[(i - 1) % 2] || !dp[(i - 2) % 2];
        }
        
        return dp[n % 2];
    }
}



/*
Previous notes:
Rolling array: only need to use i, i-1, i-2
 */
public class Solution {
    public boolean firstWillWin(int n) {
        if (n <= 0) {
            return false;
        }
        if (n <= 2) {
            return true;
        }

        int start = 0;
        int mid = 1;
        int end = 2;
        boolean[] dp = new boolean[3];
        dp[start] = false; // no coins to pick.
        dp[mid] = true;
        
        for (int i = 2; i <= n; i++) {
            dp[end] = !dp[start] || !dp[mid];
            if (i != n) {
                start = (start + 1) % 3;
                mid = (mid + 1) % 3;
                end = (end + 1) % 3;
            }
        }
        return dp[end];
    }
}

/*
Not recommended.
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