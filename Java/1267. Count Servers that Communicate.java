M
tags: Array, Graph
time: O(mn)
space: O(m + n)

#### two axis array, cross-reference
- analyze problem, and realize we want to eliminate `isolated servers`
- count row[], count col[]
- cross-reference row[] and col[]: `row[i]==1 & col[j]==1` indicates a isolated server

### DFS brutle
- Unfortunately, this problems checks unconnected items, so dfs needs to brutlely check entire row or column
- Only add if `vertical + horizontal count` > 1
- time: O(mn) * O(m + n)

```
/*
You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.

Return the number of servers that communicate with any other server.

 

Example 1:



Input: grid = [[1,0],[0,1]]
Output: 0
Explanation: No servers can communicate with others.
Example 2:



Input: grid = [[1,0],[1,1]]
Output: 3
Explanation: All three servers can communicate with at least one other server.
Example 3:



Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
Output: 4
Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m <= 250
1 <= n <= 250
grid[i][j] == 0 or 1
*/

class Solution {
    public int countServers(int[][] grid) {
        
        int total = 0;
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m], col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    total++;
                    row[i]++;
                    col[j]++;
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && row[i] == 1 && col[j] == 1) { // both row and col has count 1; isolated server
                    total--;
                }
            }
        }
        
        return total;
    }
}

// DFS: exhaust vertical, and horizontal completely
// time: O(mn) * O(m + n)
class Solution {
    public int countServers(int[][] grid) {
        boolean visited[][] = new boolean[grid.length + 1][grid[0].length + 1];
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int count = dfs(grid, i, j, visited);
                    if (count > 1) total += count;
                }
                visited[i][j] = true;
            }   
        }
        return total;
    }
    
    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (visited[i][j] || grid[i][j] == 0) return 0; // skip 0 or visited[i][j]
            
        visited[i][j] = true;
        int count = 1;
        for (int x = 0; x < grid.length; x++) { // dfs on row
            count += dfs(grid, x, j, visited);
        }
        for (int y = 0; y < grid[0].length; y++) { // dfs on col
            count += dfs(grid, i, y, visited);
        }
        return count;
    }
    
}
```