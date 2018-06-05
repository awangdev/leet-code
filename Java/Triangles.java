M
tags: Array, DP

```

/*
Given a triangle, find the minimum path sum from top to bottom. 
Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.


Thinking process:
1. Bottom-up
	- Start from the bottom row, get all values of this row. Note: in triangle, height = cols at each row. So row X has X numbers.
	- Start from (n - 1)th row and run up: calculate min from lower level + current node value.
	- Depending what is wanted, here we use a 2D int arraya and return the min sum.
*/

public class Solution {
    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    //Bottom - up
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            sum[n - 1][i] = triangle.get(n - 1).get(i);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                sum[i][j] = Math.min(sum[i + 1][j], sum[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return sum[0][0];
    }
}

/*
2. Memorize Search
	- Go through all nodes and initialize with Integer.MAX_VALUE;
	- Search from top: thislevel-current = Math.min(nextlevel-current, nextlevel-next) + thislevel-current
	- During the Search Helper, when a node has been set previously, just return this value because this min value has been pre-calculated.
		If row is >= triangle.size(), return 0.
	- This method can actually calculate the min sum from bottom to any point in the triangle.
*/

public class Solution {
    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
       
    //Version 2 : Memorize Search
    private int n;
    private ArrayList<ArrayList<Integer>> triangle;
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        this.n = triangle.size();
        this.triangle = triangle;
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                sum[i][j] = Integer.MAX_VALUE;
            }
        }
        return searchHelper(0, 0, sum);
    }
    
    public int searchHelper(int r, int c, int[][] sum) {
        if (r >= this.n) {
            return 0;
        }
        if (sum[r][c] != Integer.MAX_VALUE) {
            return sum[r][c];
        }
        sum[r][c] = Math.min(searchHelper(r + 1, c, sum), searchHelper(r + 1, c + 1, sum)) + this.triangle.get(r).get(c);
        return sum[r][c];
    }
}




```