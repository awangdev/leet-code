H
1520815993
tags: Array, Hash Table, DP, Stack

#### 方法1: monotonous stack
分解开来, 其实是'Largest Rectangle in Histogram', 只不过这里要自己model heights.
一个2D array里面的rectangle, 最终也是用height * width做出来的.
巧妙在于, 把每一行当做底边, 算出这个底边, 到顶部的height: 
- 如果底边上的一个value==0, 那么算作没有height(以这个底边做rectangle, value==0的位置是空中楼阁, 不能用)
- 如果底边上的value==1, 那么就把上面的height加下来, 做成histogram

如果看具体实例, 有些row似乎是白算的, 但是没有办法, 这是一个搜索的过程, 最终会比较出最优解.

#### 方法2: DP

从边长为2的正方形看起，看左上角的那个点。   
如何确定是个正方形？首先看左上点是不是1，然后看右边，右下，下面的点是不是1.   

DP就是根据这个特征想出来。dp[i,j]: 从右下往左上推算，包括当前点在内的所能找到的最大边长。   
   注意dp[i,j]被右边，右下，下面三点的最短点所限制。这就是fn. 

Init：   
   把右边，下边两个边缘init一遍，存matrix在这两条边上的值，代表的意思也就是dp[i][j]在这些点上的初始值:变成为1 or 0.  

```
// LeetCode, Maximal Rectangle

/*
Thoughts:
Different form of 'Largest Rectangle in Histogram': 
break the problem down then we are dealing with m list of histogram.
Each row (from top to bottom), can be modeled as histogram: sum 1's from top to the current row.

After that point, just calculate largest rectangle in each modelded histogram row, using stack.
*/
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] heightMap = new int[m][n];
        
        // Prepare height map
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    heightMap[i][j] = 0;
                } else {
                    heightMap[i][j] = i == 0 ? 1 : heightMap[i - 1][j] + 1;
                }
            }
        }
        
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            maxArea = Math.max(maxArea, findLargestRectInHistogram(heightMap[i]));
        }

        return maxArea;
    }
    
    private int findLargestRectInHistogram(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int m = height.length;
        int max = 0;
        for (int i = 0; i <= m; i++) {
            int currHeight = i == m ? -1 : height[i];
            while (!stack.isEmpty() && currHeight <= height[stack.peek()]) {
                int peekHeight = height[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, peekHeight * width);
            }
            stack.push(i);
        }
        return max;
    }
}

/*
Given a 2D binary matrix filled with 0's and 1's, 
find the largest square containing all 1's and return its area.

Example
For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.

Tags Expand 
Dynamic Programming
*/

/*
	Thoughts: Seem that we need to check on right and bottom spots for 2x2 1's.
	If all size spots are clean, len++, square = newLen ^ 2.
	DP[i,j]: the longest square lengh that this matrix[i][j] reach.
	dp[i,j] = Math.min(dp[i][j+1], dp[i+1][j], dp[i+1][j+1]) 
	init: 
	dp[n-1][0 ~ m-1] = matrix[n-1][0 ~ m-1] == 0 ? 0 : 1;
	dp[0 ~ n-1][m-1] = matrix[0 ~ n-1][m-1] == 0 ? 0 : 1;
	for from rigt-bottom conor.
	Maintain a max area
*/

public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    		return 0;
    	}
    	int n = matrix.length;
    	int m = matrix[0].length;
    	int[][] dp = new int[n][m];
    	int maxLen = 0;
    	//Init
    	for (int i = 0; i < m; i++) {
    		dp[n - 1][i] = matrix[n - 1][i];
    		maxLen = Math.max(maxLen, dp[n - 1][i]);
    	}
    	for (int i = 0; i < n; i++) {
    		dp[i][m - 1] = matrix[i][m - 1];
    		maxLen = Math.max(maxLen, dp[i][m - 1]);
    	}
    	//function
    	for (int i = n - 2; i >= 0; i--) {
    		for (int j = m - 2; j >= 0; j--) {
    			if (matrix[i][j] == 1) {
    				dp[i][j] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]), dp[i + 1][j + 1]) + 1;
    				maxLen = Math.max(maxLen, dp[i][j]);
    			}
    		}
    	}
    	return maxLen * maxLen;
    }
}

```