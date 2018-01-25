E

O(n)跑2遍for.
O(1)是用了两个int来存：每次到i点时，i点满足条件或不满足条件所有的longestIncreasingContinuousSubsequence.
特点：返跑一回，ans还是继续和left轮的ans作比较；求的所有情况的最大值嘛。
```
//LintCode
//跟LeetCode的 LCIS 几乎一样. https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/

/*
Give you an integer array (index from 0 to n-1, where n is the size of this array)，
find the longest increasing continuous subsequence in this array. 
(The definition of the longest increasing continuous subsequence here can be from right to left or from left to right)
Example
For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.
For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.
Note
O(n) time and O(1) extra space.
Tags Expand 
Dynamic Programming Array
*/
/*
OPTS: O(n) time and O(1) space
Thoughts: not quite DP yet, but used 1 leftRun/rightRun to store status, sort of DP.
*/
public class Solution {
    /**
     * @param A an array of Integer
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int leftRun = 1;
        int rightRun = 1;
        int ans = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                leftRun++;
            } else {
                leftRun = 1;
            }
            ans = Math.max(ans, leftRun);
        }
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                rightRun++;
            } else {
                rightRun = 1;
            }
            ans = Math.max(ans, rightRun);
        }
        return ans;
    }
}
```


九章的DP，没有用O(1)space,但是应该是为这道题的followup做准备的模式。
用dp和dfs.
每次dfs左右时都要mark一下flag,防止重跑
```
/*
Thoughts: JiuZhang's DP.
dp[i]: longest increasing continous subsequence on i, regardless if the sequence is incresing from left or right.
Use flag[i] to mark if a position has been visited. If visited (flag == 1), then immediately return dp[i] since we've calculated this before.
Note. Also mark flag[i] == -1 in each dfs interation, to prevent redudant looping (infinite loop)

Stackover flow at 96%.
*/
public class Solution {
    /**
     * @param A an array of Integer
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] dp = new int[A.length];
        int[] flag = new int[A.length];
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            dp[i] = dfs(A, dp, flag, i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int dfs(int[] A, int[] dp, int[] flag, int i){
        if (flag[i] == 1) {
            return dp[i];
        }
        int ansLeft = 1;
        int ansRight = 1;
        flag[i] = -1;
        //Increasing from left->right
        if (i - 1 >= 0 && A[i - 1] < A[i] && flag[i - 1] != -1) {
            ansLeft = dfs(A, dp, flag, i - 1) + 1;
        }
        //Increasing from right->left
        if (i + 1 < A.length && A[i] > A[i + 1] && flag[i + 1] != -1) {
            ansRight = dfs(A, dp, flag, i + 1) + 1;
        }
        flag[i] = 1;
        dp[i] = Math.max(ansLeft, ansRight);
        return dp[i];
    }
}




```







老码
```
/*
Older approach. Longer code :（
Thoughts:
1. Reverse search for the same int[] A.
    In 1,2 keep track of maxLength1. maxLength2
2. Compare maxLength, and use the largest
Note:
After for loop, need to catch the very last comparison, to get result of track ;)

*/
//[5,4,2,1,3]
public class Solution {
    /**
     * @param A an array of Integer
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int maxLength1 = 0;
        int maxLength2 = 0;
        int track = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                track++;
            } else {
                maxLength1 = track > maxLength1 ? track : maxLength1;
                track = 1;
            }
        }
        maxLength1 = track > maxLength1 ? track : maxLength1;
        track = 1;
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                track++;
            } else {
                maxLength2 = track > maxLength2 ? track : maxLength2;
                track = 1;
            }
        }
        maxLength2 = track > maxLength2 ? track : maxLength2;
        return maxLength1 > maxLength2 ? maxLength1 : maxLength2;
    }
}
```