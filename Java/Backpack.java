M
1524200994
tags: DP, Backpack DP

给i本书, 每本书有自己的重量 int[] A, 背包有自己的大小M, 看最多能放多少重量的书?

#### Backpack DP 1
- 简单直白的思考 dp[i][m]: 前i本书, 背包大小为M的时候, 最多能装多种的书?
- **注意**: 背包问题, 重量weight一定要是一维.
- dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + A[i - 1]);
- 每一步都track 最大值
- 最后return dp[n][m]
- 时间空间  O(mn)
- Rolling array, 空间O(m)

#### Backpack DP 2
- true/false求解, 稍微曲线救国: 重点是, 最后, 按照weight从大到小遍历, 第一个遇到true的, index就是最大值.  
- 考虑: 用i个item (可跳过地取), 是否能装到weight w?
- 需要从'可能性'的角度考虑, 不要搞成单一的最大值问题.
- 1. 背包可装的物品大小和总承重有关.
- 2. 不要去找dp[i]前i个物品的最大总重, 找的不是这个. 
    dp[i]及时找到可放的最大sum, 但是i+1可能有更好的值, 把dp[i+1]变得更大更合适.

##### 做法
- boolean[][] dp[i][j]表示: 有前i个item, 用他们可否组成size为j的背包? true/false.
- (反过来考虑了，不是想是否超过size j, 而是考虑是否能拼出exact size == j)
- **注意**: 虽然dp里面一直存在i的位置, 实际上考虑的是在i位置的时候, 看前i-1个item.

##### 多项式规律
- 1. picked A[i-1]: 就是A[i-1]被用过, weight j 应该减去A[i-1]. 那么dp[i][j]就取决于dp[i-1][j-A[i-1]]的结果.
- 2. did not pick A[i-1]: 那就是说, 没用过A[i-1], 那么dp[i][j]就取决于上一行d[i-1][j]
- dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]]

##### 结尾
- 跑一遍dp 最下面一个row. 从末尾开始找, 最末尾的一个j (能让dp[i][j] == true)的, 就是最多能装的大小 :)   
- 时间，空间都是：O(mn)


```
/*
Given n items with size Ai, an integer m denotes the size of a backpack. 
How full you can fill this backpack?

Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], 
so that the max size we can fill this backpack is 10. 
If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.

Note
You can not divide any item into small pieces.

Challenge
O(n x m) time and O(m) memory.

O(n x m) memory is also acceptable if you do not know how to optimize memory.

Tags Expand 
LintCode Copyright Dynamic Programming Backpack


*/

/*
Thoughts:
Backpack problem, always consider a dimension with weight/size.
int dp[i][j]: with i items and backpack size/weight-limit of j, what's max weight can we put in?

dp[i][j] depends on last item's size && what's the state with [i-1] items.

dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + A[i - 1]);

dp[0][0] = 0; no book, 0 weight limit
dp[0][j] = 0; no book, can't fill bag
dp[i][0] = 0; i books, but weight-limit = 0, can't fill
*/
public class Solution {
    public int backPack(int m, int[] A) {
    	if (A == null || A.length < 0) {
    	    return 0;
    	}
    	int n = A.length;
    	int[][] dp = new int[n + 1][m + 1];
    	
    	// Calculcate possibility for i items to fill up w weight
    	for (int i = 1; i <= n; i++) {
    	    for (int j = 0; j <= m; j++) {
    	        // default: item(i-1) not used:
    	        dp[i][j] = dp[i - 1][j];
    	        if (j - A[i - 1] >= 0) { // possible to use item(i-1)
    	            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + A[i - 1]); // use item(i-1)
    	        }
    	    }
    	}
    	
    	return dp[n][m];
    }
}

// Rolling array
public class Solution {
    public int backPack(int m, int[] A) {
    	if (A == null || A.length < 0) {
    	    return 0;
    	}
    	int n = A.length;
    	int[][] dp = new int[2][m + 1];
    	
    	// Calculcate possibility for i items to fill up w weight
    	for (int i = 1; i <= n; i++) {
    	    for (int j = 0; j <= m; j++) {
    	        // default: item(i-1) not used:
    	        dp[i % 2][j] = dp[(i - 1) % 2][j];
    	        if (j - A[i - 1] >= 0) { // possible to use item(i-1)
    	            dp[i % 2][j] = Math.max(dp[i % 2][j], dp[(i - 1) % 2][j - A[i - 1]] + A[i - 1]); // use item(i-1)
    	        }
    	    }
    	}
    	return dp[n % 2][m];
    }
}

/*
Thoughts:
DO NOT try to find the maxSum using given values: this approach f[i] only returns max sum, 
but does not guarantee to pick the most sutible items that maximize f[n]. 

1. Always consider weight
2. Consider it as a possibility problem based on weight 0 ~ m

Using given items, can we fill backpack with size 0, size 1, size 2... size m - 1, and size m?
We save these values with boolean dp[i][w]: using i items to fill size w, where i is the A index.

Two conditions for calculating dp[i][w]
1. i - 1 items filled up w, so dp[i][w] = dp[i - 1][w]. No need to add A[i - 1].
2. i - 1 items fileld up w - A[i - 1]; once adding A[i - 1], it'll fill up w. dp[i][w] = dp[i - 1][w - A[i - 1]], so we are counting on if i - 1 items filled up weight: w - A[i - 1].

We'll loop over j = 1 ~ m, and attempt to fill all sizes with i items.

init:
dp[0][0]: using 0 items to fill 0 space, sure. True.
dp[0][1~m]: using 0 items to fill 1~m space, not gonna work. False.


*/
public class Solution {
    
    public int backPack(int m, int[] A) {
    	if (A == null || A.length < 0) {
    	    return 0;
    	}
    	int n = A.length;
    	boolean[][] dp = new boolean[n + 1][m + 1];
    	
    	// weight 0 is a valid value.
    	// items does not have 0's item, so we need to init dp based for all entries where i == 0
    	dp[0][0] = true;
    	for (int j = 1; j <= m; j++) {
    	    dp[0][j] = false;
    	}
    	
    	// Calculcate possibility for i items to fill up w weight
    	for (int i = 1; i <= n; i++) {
    	    for (int j = 0; j <= m; j++) {
    	        // default: item(i-1) not used:
    	        dp[i][j] = dp[i - 1][j];
    	        if (j - A[i - 1] >= 0) { // possible to use item(i-1)
    	            dp[i][j] |= dp[i - 1][j - A[i - 1]]; // use item(i-1)
    	        }
    	    }
    	}
    	
    	// Find max weight size that makes dp[i][j] true
    	for (int j = m; j >= 0; j--) {
    	    if (dp[n][j]) {
    	        return j;
    	    }
    	}
    	return 0;
    }
}

/*
Thoughts: rolling array.
Always use i, and i - 1 dp. can replace the two index with
curr, pre
*/
public class Solution {
    
    public int backPack(int m, int[] A) {
    	if (A == null || A.length < 0) {
    	    return 0;
    	}
    	int n = A.length;
    	boolean[][] dp = new boolean[2][m + 1];
    	int curr = 0;
    	int pre = 1;
    	// weight 0 is a valid value.
    	// items does not have 0's item, so we need to init dp based for all entries where i == 0
    	dp[curr][0] = true;
    	for (int j = 1; j <= m; j++) {
    	    dp[curr][j] = false;
    	}
    	
    	// Calculcate possibility for i items to fill up w weight
    	for (int i = 1; i <= n; i++) {
    	    curr = pre;
    	    pre = 1 - curr;
    	    for (int j = 0; j <= m; j++) {
    	        // default: item(i-1) not used:
    	        dp[curr][j] = dp[pre][j];
    	        if (j - A[i - 1] >= 0) { // possible to use item(i-1)
    	            dp[curr][j] |= dp[pre][j - A[i - 1]]; // use item(i-1)
    	        }
    	    }
    	}
    	
    	// Find max weight size that makes dp[i][j] true
    	for (int j = m; j >= 0; j--) {
    	    if (dp[curr][j]) {
    	        return j;
    	    }
    	}
    	return 0;
    }
}

/* 
    Thoughts: Recap on 12.02.2015
    State
    DP[i][j]: i is the index of Ai, and j is the size from (0 ~ m). 
        It means: depending if we added A[i-1], can we full-fill j-space? Return ture/false.
        Note: that is, even j == 0, and I have a item with size == 0. There is nothing to add, which means the backpack can reach j == 0. True.
        However, if we have a item with size == 2, but I need to fill space == 1. 
        I will either pick/not pick item of size 2; either way, can't fill a backpack with size 1. False;
    Function:
        DP[i][j] = DP[i - 1][j] || DP[i - 1][j - A[i - 1]];   // based on if previous value is true/false: 1. didn't really add A[i-1] || 2. added A[i-1].
    Init:
        DP[0][0] = true; // 0 space and 0 items, true.
        All the rest are false;
    
    Return the very last j that makes dp[A.length][j] true.
*/

public class Solution {
    public int backPack(int m, int[] A) {
        if (A == null || A.length == 0 || m <= 0) {
            return 0;
        }
        boolean[][] dp = new boolean[A.length + 1][m + 1];
        dp[0][0] = true;

        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                //j is large enough:
                if (j - A[i - 1] >= 0) {
                    //not added A[i - 1] or added A[i - 1]
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]];
                } else {// j not large enough, ofcourse not adding A[i-1]                   
                    dp[i][j] = dp[i - 1][j];
                }      
            }
        }
        //Largest possible size with dp[j] == true
        for (int j = m; j >= 0; j--) {
            if (dp[A.length][j]) {
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
    public int backPack(int m, int[] A) {
        if (A == null || m == 0) {
            return 0;
        }

        boolean[] DP = new boolean[m + 1];
        DP[0] = true;
        for (int i = 1; i <= A.length; i++) {
            for (int j = m; j >= 0; j--) {
                if (j - A[i - 1] >= 0 && DP[j - A[i - 1]]) {
                    DP[j] = true;
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

```