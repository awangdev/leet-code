H
1521321013
tags: Array, Hash Table, DP, Stack

#### 方法1: monotonous stack
分解开来, 其实是'Largest Rectangle in Histogram', 只不过这里要自己model heights.
一个2D array里面的rectangle, 最终也是用height * width做出来的.
巧妙在于, 把每一行当做底边, 算出这个底边, 到顶部的height: 
- 如果底边上的一个value==0, 那么算作没有height(以这个底边做rectangle, value==0的位置是空中楼阁, 不能用)
- 如果底边上的value==1, 那么就把上面的height加下来, 做成histogram

如果看具体实例, 有些row似乎是白算的, 但是没有办法, 这是一个搜索的过程, 最终会比较出最优解.

#### 方法2: DP
Coordinate DP?

```
/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.
*/


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
```