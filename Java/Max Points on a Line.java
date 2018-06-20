H
1529471330
tags: Array, Hash Table, Geometry, Math

给list of (x,y) coordinates. Determine  # of points on the same line

#### Observation
- If given n points, we can calculate all possible slopes. O(n^2) times
- For the two dots that generates the same slope, these dots could be on **parallel** slopes
- figure out how to prune the parallel dots

#### Trick: prune parallel dots using greatest common divider
- Devide the x and y by their greatest common divider, such that x and y can be reduced to minimum value
- All other x and y can be reduced to such condition as well
- track the final reduced (x,y) in a map: they are the key to the count
- No need to use Map<Integer, Map<Integer, Integer>> to perform 2 level mapping; just `map<String, Integer>`, where the key is "x@y"

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
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) return 0;
        int result = 0;
        Map<String, Integer> map = new HashMap<>();
        // double for loop to try all points. O(n^2)
        for (int i = 0; i < points.length; i++) {
            int max = 0, overlap = 0;
            map.clear();
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }
                int gcd = findGCD(x, y);
                if (gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }
                String key = x + "@" + y;
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
                max = Math.max(max, map.get(key));
            }
            result = Math.max(result, max + overlap + 1);
        }
        return result;
    }

    private int findGCD(int a, int b) {
        if (b == 0) return a;
        return findGCD(b, a % b);
    }
}




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