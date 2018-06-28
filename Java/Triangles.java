M
1530152837
tags: Array, DP, DFS, Memoization, Coordinate DP

给一个list<list<Integer>> triangle, 细节原题. 找 min path sum from root.

#### DFS + Memoization
- 其实跟给一个2D matrix没有什么区别, 可以做dfs, memoization.
- initialize memo: pathSum[i][j] = MAX_VALUE; 计算过的path省略
- Bottom-top: 先dfs到最深的path, 然后逐步网上返回
- `OR 原理: min(pathA, pathB) + currNode`
- 浪费一点空间, pathSum[n][n]. space: O(n^2), where n = triangle height
- 时间:O(n^2). Visit all nodes once: 1 + 2 + 3 + .... n = n^2

#### DP
- 跟dfs的原理很像, `OR 原理: min(pathA, pathB) + currNode`
- init dp[n-1][j] = node values
- build from bottom -> top: dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
- 跟传统的coordinate dp有所不同, inner for loop 是需要计算 j <= i, 原因是triangle的性质.
- 空间: dp[n][n]. space: O(n^2)
- 时间:O(n^2). Visit all nodes once: 1 + 2 + 3 + .... n = n^2

#### DP + O(n) space 
- Based on the DP solution: the calculation always depend on `next row` for col at `j` and `j + 1`
- 既然只depend on next row, 可以用rolling array来处理: reduce to O(n) space.
- Further: 可以降维, 把第一维彻底去掉, 变成 dp[n]
- 同样是double for loop, 但是只在乎column changes: `dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);`  

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
    //Bottom - up
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int[][] dp = new int[n][n];
        // Init bottom row
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}

// Rolling arary
public class Solution {
    //Bottom - up
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int[][] dp = new int[2][n];
        // Init bottom row
        for (int j = 0; j < n; j++) {
            dp[(n - 1) % 2][j] = triangle.get(n - 1).get(j);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i % 2][j] = Math.min(dp[(i + 1)%2][j], dp[(i + 1)%2][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
/**
From above solution, row number does not seem to matter during calculation
 */
public class Solution {
    //Bottom - up
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}

/*
2. dfs, memoization
- Go through all nodes and initialize with Integer.MAX_VALUE;
- Search from top: pathSum[i][j] = Math.min(dfs(i+1,j), dfs(i+1, j+1)) + currNode
- In dfs: if a node has been set previously, just return this value because this min value has been pre-calculated.
- If row is >= triangle.size(), return 0.
- This method can actually calculate the min sum from bottom to any point in the triangle.
// 94%
*/
public class Solution {
    int[][] pathSum;
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        pathSum = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                pathSum[i][j] = Integer.MAX_VALUE;
            }
        }
        return dfs(0, 0, triangle);
    }
    
    public int dfs(int i, int j, List<List<Integer>> triangle) {
        if (i >= triangle.size()) {
            // each row i should only have i items by triangle definition
            return 0;
        }
        if (pathSum[i][j] != Integer.MAX_VALUE) return pathSum[i][j]; // memoization

        pathSum[i][j] = Math.min(dfs(i + 1, j, triangle), dfs(i + 1, j + 1, triangle)) + triangle.get(i).get(j);
        return pathSum[i][j];
    }
}




```