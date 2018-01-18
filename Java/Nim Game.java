E

著名Nim游戏。
写一些，发现n=4,5,6,7,8...etc之后的情况有规律性: 谁先手拿到4就输了.
最终很简单n%4!=0就可以了

```
/*
You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.

For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.

Hint:

If there are 5 stones in the heap, could you figure out a way to remove the stones such that you will always be the winner?


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
```