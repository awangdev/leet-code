这个DP有点诡异. 需要斟酌。
NOT DONE YET
```
/*
There is a stone game.At the beginning of the game the player picks n piles of stones in a line.

The goal is to merge the stones in one pile observing the following rules:

At each step of the game,the player can merge two adjacent piles to a new pile.

The score is the number of stones in the new pile.

You are to determine the minimum of the total score.

Example
[3, 4, 3] return 17

[1, 1, 1, 1] return 8

[4, 4, 5, 9] return 43

Tags Expand 
Dynamic Programming
*/

/*
Thoughts:
Based on given outline.
sum[i][j] = stone sum between i and j
f[i][j]: min cost/score if we merge ith pile into jth pile. It can be f[0][1], f[0][2].. or our final result f[0][n-1]
break it by k and check both side f[start][k] and f[start][k+1]; 
Tricky: add sum[start][end] at the end.
*/

public class Solution {
    /**
     * @param A an integer array
     * @return an integer
     */
    public int stoneGame(int[] A) {
        // Algorithm: Dynamic Programming
        // state: f[start][end] denote the minimum score that we can get if we merge stones from start-th pile to end-th pile into one pile.
        // function: f[start][end] = min{f[start][k] + f[k + 1][end] + sum[start][end]}

        if (A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        
        // initialize f[i][i]
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) {
            f[i][i] = 0;
        }
        
        // preparation for sum[i][j]
        int[][] sum = new int[n][n];
        sum[0][0] = A[0];
        for (int i = 0; i < n; i++) {
            sum[i][i] = A[i];
            for (int j = i + 1; j < n; j++) {
                sum[i][j] = sum[i][j - 1] + A[j];
            }
        }
        
        // dp
        // delta is the distance between the start and end
        for (int delta = 1; delta < n; delta++) {
            for (int start = 0; start < n - delta; start++) {
                int end = start + delta;
                //initialize f[start][end]
                f[start][end] = Integer.MAX_VALUE;
                for (int k = start; k < end; k++) {
                    f[start][end] = Math.min(f[start][end], f[start][k] + f[k + 1][end] + sum[start][end]);
                }
            }
        }
        
        return f[0][n - 1];
    }
}
```