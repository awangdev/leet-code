M
1518497606
tags: DP

做了Backpack I, 这个就如出一辙, 只不过: dp存的不是w可否存成功true/false. dp存的是加上sum value的最大值.
想法还是，选了A[i - 1] 或者没选A[i - 1]时候不同的value值.

O(m)的做法:   
想想，的确我们只care 最后一行，所以一个存value的就够了.
注意：和bakcpackI的 O(m)一样的，j是倒序的。如果没有更好的j，就不要更新。就是这个道理.

注意: 如果无法达到的w, 应该mark as impossible. 一种简单做法是mark as -1 in dp. 
如果有负数value, 就不能这样, 而是要开一个can[i][w]数组, 也就是backpack I 的原型.


```
/*
Given n items with size Ai and value Vi, and a backpack with size m. 
What's the maximum value can you put into the backpack?


Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. 
The maximum value is 9.

Note
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Challenge
O(n x m) memory is acceptable, can you do it in O(m) memory?

Tags Expand 
LintCode Copyright Dynamic Programming Backpack
*/

/*
Thoughts:
Dealing with value, the dp[i][w] = max value that can be formed over i tems at weight w.
Two conditions:
1. didn't pick A[i - 1]: dp[i - 1][w], value sum does not change.
2. Picked A[i - 1]: dp[i - 1][w - A[i - 1]] + V[i - 1];
Find the max of the above two, and record.
Initialize with dp[0][0] = -1: not possible to form w, so mark as -1, impossible.
*/

public class Solution {
    public int backPackII(int m, int[] A, int V[]) {
        if (A == null || V == null || A.length != V.length) {
            return 0;
        }
        int n = A.length;
        int[][] dp = new int[n + 1][m + 1]; // [5][5]
        
        for (int j = 0; j <= m; j++) {
            dp[0][j] = -1; // 0 items cannot form weight j, hence value -1, marking impossible
        }
		dp[0][0] = 0; // 0 items, 0 weight -> 0 value
    
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j]; // 0
                if (j - A[i - 1] >= 0 && dp[i - 1][j - A[i - 1]] != -1) {
                   dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + V[i - 1]); 
                }
                
            }
        }
        
        int rst = 0;
        for (int j = 0; j <= m; j++) {
            if (dp[n][j] != -1) {
                rst = Math.max(rst, dp[n][j]);
            }
        }
        return rst;
    }
}

// Rolling array
public class Solution {
    public int backPackII(int m, int[] A, int V[]) {
        if (A == null || V == null || A.length != V.length) {
            return 0;
        }
        int n = A.length;
        int[][] dp = new int[2][m + 1];
        for (int j = 0; j <= m; j++) {
            dp[0][j] = -1; // 0 items cannot form weight j, hence value -1, mark as impossible
        }
		dp[0][0] = 0; // 0 items, 0 weight -> 0 value
		int curr = 0, prev;
    
        for (int i = 1; i <= n; i++) {
			// rolling index
			prev = curr;
			curr = 1 - prev;
            for (int j = 1; j <= m; j++) {
                dp[curr][j] = dp[prev][j]; // 0
                if (j - A[i - 1] >= 0 && dp[prev][j - A[i - 1]] != -1) {
                   dp[curr][j] = Math.max(dp[curr][j], dp[prev][j - A[i - 1]] + V[i - 1]); 
                }
            }
        }
		
		int rst = 0;
        for (int j = 0; j <= m; j++) {
            if (dp[curr][j] != -1) {
                rst = Math.max(rst, dp[curr][j]);
            }
        }
        return rst;
    }
}





/*
Initialize with dp[0][0] = 0.
This will pass the test, however it's not 100% explicit
*/
public class Solution {
    public int backPackII(int m, int[] A, int V[]) {
        if (A == null || V == null || A.length != V.length) {
            return 0;
        }
        int n = A.length;
        int[][] dp = new int[n + 1][m + 1]; // [5][5]
        dp[0][0] = 0; // 0 items, 0 weight -> 0 value
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0; // 0 items cannot form weight j, hence value 0
        }
    
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j]; // 0
                if (j - A[i - 1] >= 0) {
                   dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + V[i - 1]); 
                }
            }
        }
        return dp[n][m];
    }
}

// Rolling array
public class Solution {
    public int backPackII(int m, int[] A, int V[]) {
        if (A == null || V == null || A.length != V.length) {
            return 0;
        }
        int n = A.length;
        int[][] dp = new int[2][m + 1]; // [5][5]
        dp[0][0] = 0; // 0 items, 0 weight -> 0 value
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0; // 0 items cannot form weight j, hence value 0
        }
		int curr = 0, prev;
    
        for (int i = 1; i <= n; i++) {
			// rolling index
			prev = curr;
			curr = 1 - prev;
            for (int j = 1; j <= m; j++) {
                dp[curr][j] = dp[prev][j]; // 0
                if (j - A[i - 1] >= 0) {
                   dp[curr][j] = Math.max(dp[curr][j], dp[prev][j - A[i - 1]] + V[i - 1]); 
                }
            }
        }
        return dp[curr][m];
    }
}


/*
Previous Notes.
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
	Just like in Backpack I, at the end, we only care about the last row. 
    Why not just maintain a row, always keep the max value.

	Note: Only update dp[j] if adding A[i-1] would be greater than current dp[j]

	It's a bit hard to come up with this... but it's good exercise.
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