/*
Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.

Note
You can not divide any item into small pieces.

Challenge
O(n x m) time and O(m) memory.

O(n x m) memory is also acceptable if you do not know how to optimize memory.

Tags Expand 
LintCode Copyright Dynamic Programming Backpack

Thoughts:
Well, I kind of forgot about how we did this in algorithm class, but here we go, after a bit research:
DP[i][j] means: if i number of items in A, can we fill the bag size of j? Then, save a boolean in DP[i][j]. That means, if i items are too much for j?

So, there are two cases: 
1. A[i] is unfortunately too big to fit into size j, so skip item A[i] and use DP[i-1][j].
2. OR, the other case: A[i] fits in well. We realize 2 things:
	a. first (i-1)th items much fit in well into the bag size of (j - A[i - 1]): DP[i-1][j - A[i-1]]. Basically says items must be fit in (true) before adding A[i]
	b. AND (j - A[i - 1]) must >= 0 to have space for next item i.

End:
Iterate through j:(m ~ 0), and return j, if DP[A.length][j] is true. We are starting from m, because we need the largest number j.

This is 2D array version: memory mxn, space mxn
*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
    	if (A == null || m == 0) {
    		return 0;
    	}

    	boolean[][] DP = new boolean[A.length + 1][m + 1];
    	DP[0][0] = true;
    	for (int i = 1; i <= A.length; i++) {
    		for (int j = 0; j <= m; j++) {
    			DP[i][j] = DP[i - 1][j] || (j - A[i - 1] >= 0 && DP[i - 1][j - A[i - 1]]);
    		}
    	}

    	for (int j = m; j >= 0; j--) {
    		if (DP[A.length][j]) {
    			return j;
    		}
    	}
    	return 0;
    }
}


/*
1D array: memory mxn, space m. Tricky tho ...

Looking at the 2D version, we are really just checking the DP with fixed i=A.length.

DP[j]: can we fit i items into j?
DP[j] == false || DP[j - A[i - 1]].
Similar two cases:
1. Can't fit in, set false;
2. Can fit in, then just return if (j - A[i - 1]) works 

Core difference: only set the DP[j] when (j - A[i - 1] >= 0 && DP[j - A[i - 1]]): since we are running from m ~ 0, we don't want to override some existing values 
*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
    	if (A == null || m == 0) {
    		return 0;
    	}

    	boolean[] DP = new boolean[m + 1];
    	DP[0] = true;
    	for (int i = 1; i <= A.length; i++) {
    		for (int j = m; j >= 0; j--) {
    			if (j - A[i - 1] >= 0 && DP[j - A[i - 1]]) {
    				DP[j] = DP[j - A[i - 1]];
    			}
    		}
    	}

    	for (int j = m; j >= 0; j--) {
    		if (DP[j]) {
    			return j;
    		}
    	}
    	return 0;
    }
}
