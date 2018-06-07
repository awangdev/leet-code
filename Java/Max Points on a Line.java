H
tags: Array, Hash Table, Geometry


```
/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6

*/



/*
DP not applicable:
IF it's a N x N board, with max value restricted by value n x n, dp approach is feasible, because the board is bounded.
However, this question does not specify the board, but rather a list of points, where the value can be extremely large (not feasible to define dp[n][n])

Thoughts about DP
- Build N x N boolean[][], and assign points 
- dp[x][y]: max number of points to this point
- there can be 8 directions towards 1 (x,y), and half of direction overlaps
- Assume the 4 directions: right-diagonal, DOWN, left-diagonal, right
- add status to dp[x][y][directionStatus]: on spot (x,y), what's the max # points coming from this direction?
- function: dp[x][y][status] = board[x][y] ? dp[lastX][lastY][status] + 1 : 0;
where lastX = x - dx[status];
- init: 
dp[0][y][any status] = board[0][y] ? 1 : 0; 
dp[x][0][any status] = board[x][0] ? 1 : 0;
*/

```