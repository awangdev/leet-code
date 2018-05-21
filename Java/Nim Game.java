E
1522047752
tags: Brainteaser, DP, Game Theory

#### Brainteaser
- 著名Nim游戏
- 写一些，发现n=4,5,6,7,8...etc之后的情况有规律性: 谁先手拿到4就输了.
- 最终很简单n%4!=0就可以了,  time, space O(1)

#### DP
- 正规地找规律做, 就跟 coins in a line 一样, 按照先手后手来做
- 可以rolling array 优化空间
- Time O(n), 当然啦, 这个题目这样会timeout, 可以使用brainteaser的做法写出结果.

```
/*
You are playing the following Nim Game with your friend: 
There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. 
The one who removes the last stone will be the winner. 
You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. 
Write a function to determine whether you can win the game given the number of stones in the heap.

For example, if there are 4 stones in the heap, 
then you will never win the game: no matter 1, 2, or 3 stones you remove, 
the last stone will always be removed by your friend.

Hint:

If there are 5 stones in the heap, could you figure out a way to remove the stones 
such that you will always be the winner?


Hide Similar Problems (M) Flip Game II

*/

/*
	Thoughts:
	If n = 4, we can do the following:
	1 0 0 0
	1 1 0 0
	1 1 1 0
	But we'll fail.

	n = 5, we pick 1, 2nd player gets n = 4.
	n = 6, we pick 2, 2nd player gets n = 4.
	n = 7, we pick 3, 2nd player gets n = 4.
	n = 8, regarless whatever we pick, the opponent can make 1st gets n = 4, we fail.
	...
	...
	whenever n % 4 = 0, 1st player fail.

*/

public class Solution {
    public boolean canWinNim(int n) {
     	return n % 4 != 0;
    }
}

/*
Whether 1st hand could win or not, depend on what’s left on the opponent’s last move.
dp[i]: true/false if left with i stones.
dp[i] can win, if one of dp[i - 1], dp[i - 2] or dp[i - 3] could lose.
dp[0] = true.
*/

class Solution {
    public boolean canWinNim(int n ) {
        if (n <= 3) {
            return true;
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        dp[1] = dp[2] = dp[3] = true;
        for (int i = 4; i <= n; i++) {
            dp[i] = !(dp[i - 1] && dp[i - 2] && dp[i - 3]);
        }
        return dp[n];
    }
}

// Rolling stone
class Solution {
    public boolean canWinNim(int n ) {
        if (n <= 3) {
            return true;
        }
        boolean[] dp = new boolean[4];
        dp[0] = true;
        dp[1] = dp[2] = dp[3] = true;
        for (int i = 4; i <= n; i++) {
            dp[i % 4] = !(dp[(i - 1) % 4] && dp[(i - 2) % 4] && dp[(i - 3) % 4]);
        }
        return dp[n % 4];
    }
}





/*
Thoughts:
Game theory DP. Consider the last step:
for 1st player to win, the opponent needs to have the possibility to lose
(assume 1st player take the chance when seeing one)

Make dp[i] represents true/false 1st will win, if given i stones.
dp[i] = !dp[i - 1] || !dp[i - 2] || !dp[i - 3];

return dp[n - 1]
*/
class Solution {
    public boolean canWinNim(int n) {
        if (n <= 3) {
            return true;
        }
        boolean[] dp = new boolean[3];
        dp[0] = dp[1] = dp[2] = true;
        for (int i = 3; i < n; i++) {
            dp[i % 3] = !dp[(i - 1) % 3] || !dp[(i - 2) % 3] || !dp[(i - 3) % 3];
        }
        return dp[(n - 1) % 3];
    }
}

```