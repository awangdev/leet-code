/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

Hide Tags Divide and Conquer Array Dynamic Programming

Thinking process:
1. DP: store calculated sum for comparison use: compare with max to get true max value.
2. Max sum from previous index to current:
	at index i, try to compare if pre + current > current index value. If yes, use pre + current; if NO, just use current as max.
	Compare max with Sum[i] for final max vlaualuealuee.

*/

public class Solution {
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] sum = new int[A.length];
        sum[0] = A[0];
        int max = sum[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = Math.max(A[i], sum[i - 1] + A[i]);
            max = Math.max(max, sum[i]);
        }
        return max;
    }
}
