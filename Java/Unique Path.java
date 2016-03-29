M

```
/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note
m and n will be at most 100.

Example
1,1     1,2     1,3     1,4     1,5     1,6     1,7     
2,1
3,1                                             3,7

Above is a 3 x 7 grid. How many possible unique paths are there?

Tags Expand 
Array Dynamic Programming

*/

/*
    3.25.2016 recap
    Regular DFS: each spot has 2 possible way out: right/down. We can count++ whenever we reach end.
    This pattern seems to be DP:
        Use a DP[i][j] to store: the # of possible paths to reach coordinate (i,j)
        
*/



/*
Thinking process:
f[x][y]: want to find out all possible path
To get to f[m][m] from f[m-1][n-1] has 2 way: f[m-1][n] or f[m][n-1].
After found 'f[m-1][n-1]', store it to a Hashmap with the #path.
Every node pair (x,y) should have 1 solution.
Recursively add up to (0,0), will find out the total path.

1. Own solution: user HashMap to memorize
*/
public class Solution {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    private int m,n;
    public int uniquePaths(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }
        this.m = m;
        this.n = n;
        HashMap<ArrayList<Integer>, Integer> his = 
            new HashMap<ArrayList<Integer>, Integer>();
        int right = helper(0, 1, his);
        int down = helper(1, 0, his);
        return right + down;
    }
    
    public int helper(int x, int y, HashMap<ArrayList<Integer>, Integer> his) {
        ArrayList<Integer> pair = new ArrayList<Integer>();
        pair.add(x);
        pair.add(y);
        if (his.containsKey(pair)) {
            return his.get(pair);
        }
        if (x >= this.m -1 || y >= this.n - 1) {
            his.put(pair, 1);
            return his.get(pair);
        }
        int right = helper(x, y + 1, his);
        int down = helper(x + 1, y, his);
        his.put(pair, right + down);
        return his.get(pair);
    }
}

/*

2. 9Chapter solution
Thinking process:
1. Assume (r,c) where r>=1, c>=1. Any node (r,c) has 2 ways to get to: (r-1, c) from top, or (r,c-1) from left-side.
2. (r-1, c) and (r,c-1) stores the possible paths to get to them
3. (r,c) = (r-1, c) + (r,c-1)
4. Initialize the top-row and left-column to be 1: Assuming landing on any initial node has path # of 1.
5. From top-bottom traverse
*/

public class Solution {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    //Traverse
    public int uniquePaths(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }
        int[][] matrix = new int[m][n];
        //Initialize
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            matrix[0][i] = 1;
        }
        //Traverse
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }
        return matrix[m - 1][n - 1];
    }
}


```