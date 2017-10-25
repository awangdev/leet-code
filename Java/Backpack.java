M

DP。   
   row是item大小: 0, A[0], A[1] ... A[A.length -1]
   col是背包累积的size: 0, 1, 2, ... m.

想法：
   dp[i][j]有这么i-1个item, 用他们可否组成size为j的背包？true/false.  （反过来考虑了，不是想是否超过size j， 而是考虑是否能拼出exact size == j）。   
   注意注意：虽然dp里面一直存在i的位置，实际上考虑的是在i位置的时候，看前i-1个item.    

看一遍code，会发现：
   1. picked A[i-1]: 如果上一个item, A[i-1],被加了上来, 用j-A[i-1]看看，是否这再前一步也true. true就好啦。   
   2. did not pick A[i-1]: 那就是说，不加上A[i-1], 上一行d[i-1][j]还是需要是true。   

最后：   
   跑一边dp 最下面一个row.  从末尾开始找，最末尾的一个j (能让dp[i][j] == true)的，就是最多能装的大小 :)   

时间，空间都是：O(mn)



再有：     
O(m)时间的做法，具体看solution. 注意j是倒序的啊！   
依然是O(mn)的空间


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