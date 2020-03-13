M
1531982363
tags: DP, Status DP, Coordinate DP


#### DP
- 特点: 上一步可能是swaped也可能是fixed
- 考虑A,B之间的现状: `A[i] > A[i - 1] && B[i] > B[i - 1]` 或者 `A[i] > B[i - 1] && B[i] > A[i - 1]`
- 问题: 如何把这个状态变成合理的strick-increasing状态?
- `A[i] > A[i - 1] && B[i] > B[i - 1]`: 1. 已经合理, 也不动.  2. [i], [i-1] 全部都swap
- `A[i] > B[i - 1] && B[i] > A[i - 1]`, 交错开来, 所以调换[i], 或者[i-1]: 1. 换[i-1]. 2. 换[i]
- 注意因为求min, 所以init value应该是 Integer.MAX_VALUE;

```
/*

We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation: 
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.
Note:

A, B are arrays with the same length, and that length will be in the range [1, 1000].
A[i], B[i] are integer values in the range [0, 2000].

*/

/*
dp[i]: # of swaps for first i items.
dp[0] = 0;
dp[i] = dp[i - 1] + 1 (if swap is needed)

However, we cannot swap greedily. dp[i-1] can be a result of swapping, or fixed.
You should be able to swap or not swap, as long as we fit the strict-increasing rule

Discuss happy case, and potential-swap case.

Add status to dp[i][status]. status = 0: fixed; status = 1, swapped

http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-801-minimum-swaps-to-make-sequences-increasing/
*/
class Solution {
    public int minSwap(int[] A, int[] B) {
        if (A.length != B.length) return 0;
        
        int n = A.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i][1] = Integer.MAX_VALUE;
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) { // happy case
                dp[i][0] = dp[i - 1][0];              // no need to swap
                dp[i][1] = dp[i - 1][1] + 1; // A[i-1], B[i-1] was swapped result
            }
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]);     // [i]fixed,  swaped[i-1]
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + 1); // [i]swaped, kept[i-1]
            }
        }
        return Math.min(dp[n-1][0], dp[n-1][1]);
    }
}
```