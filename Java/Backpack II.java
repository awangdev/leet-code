做了backpack I, 这个就如出一辙。
想法还是，选了A[i-1] 或者没选A[i].
一路往前跑不回头。就出来了。

O(m)的做法。想想，的确我们只care 最后一行，所以一个存value的就够了。 注意：和bakcpackI的 O(m)一样的，j是倒序的。如果没有更好的j，就不要更新。就是这个道理。

```
/*
Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?


Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.

Note
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Challenge
O(n x m) memory is acceptable, can you do it in O(m) memory?

Tags Expand 
LintCode Copyright Dynamic Programming Backpack
*/


/*
	Thoughts:
	In Backpack I, we store true/false to indicate the largest j in last dp row. 
	Here, we can store dp[i][j] == max value. 

	State:
	dp[i][j] : with i-1 items that fills exaclty size j, what's the max value

	Fn:
	still, picked or did not picked A[i-1]
	1. Didn't pick. Value remains the same as if we didn't add A[i-1]
	2. Picked A[i - 1]. Hence, find out previous record dp[i-1][j - A[i - 1]], then add up the A[i-1] item's value V[i-1].
	3. Compare 1, and 2 for max value.
	dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + V[i - 1])

	Init:
	dp[0][0] = 0; // 0 item, fills size 0, and of course value -> 0

	Return:
	dp[A.length][m]

	Note:
	when creating dp, we do (A.length + 1) for row size, simply because we get used to checking A[i-1] for prevous record ... Just keep this style. Don't get confused.
*/


public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
    	if (A == null || V == null || A.length == 0 || V.length == 0 || A.length != V.length || m <= 0) {
    		return 0;
    	}
    	int[][] dp = new int[A.length + 1][m + 1];
    	dp[0][0] = 0; // 0 item, to make pack size = 0, of course value = 0.

    	for (int i = 1; i <= A.length; i++) {
    		for (int j = 0; j <= m; j++) {
    			if (j - A[i - 1] >= 0) {
    				dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
    			} else {
    				dp[i][j] = dp[i - 1][j];
    			}
    		}
    	}

    	return dp[A.length][m];
    }
}


/*
	To use just O(m) sapce.
	Just like in Backpack I, at the end, we only care about the last row. Why not just maintain a row, always keep the max value.

	Note: Only update dp[j] if adding A[i-1] would be greater than current dp[j]

	It's a bit hard to come up with this... but it's good exercise to build brain a bit.
*/

public class Solution {
   
    public int backPackII(int m, int[] A, int V[]) {
    	if (A == null || V == null || A.length == 0 || V.length == 0 || A.length != V.length || m <= 0) {
    		return 0;
    	}
    	int[]dp = new int[m + 1];
    	dp[0] = 0; // 0 item, to make pack size = 0, of course value = 0.

    	for (int i = 1; i <= A.length; i++) {
    		for (int j = m; j >= 0; j--) {
    			if (j - A[i - 1] >= 0 && dp[j - A[i - 1]] + V[i - 1] > dp[j]) {
    				dp[j] = dp[j - A[i - 1]] + V[i - 1];
    			} 
    		}
    	}

    	return dp[m];
    }
}






```