H
tags: DP, DFS
time: O(n^3)
space: O(n^3), memo size

special hint: `r1 + c1 = constant t = r2 + c2`, if the two points are moving at same time.

#### DFS + Memo: TOP-DOWN
- Similar concept to Minimum Path Sum
- https://leetcode.com/problems/cherry-pickup/solution/
- realize r1 + c1 = r2 + c2. Knowing 3 parameters can uniquely identify the 4th.
- assume there are 2 people starting from origin, and the 2 people can go total 4 directions
    - perform DFS based on the 4 directions
    - concern: do they visit the same spot? possible. when that happens, make sure we do not double count the grid[i][j]
- when is the end state? 
    - then anyone, for example, (r1,c1) reaches end (n-1, n-1).
    - it means the other person also reaches end
- use memo: memo[r1][c1][r2], it records any given (r1, c1, r2, c2) state


```

/*
DFS + Memo: TOP-DOWN
- https://leetcode.com/problems/cherry-pickup/solution/
- realize r1 + c1 = r2 + c2. Knowing 3 parameters can uniquely identify the 4th.
- assume there are 2 people starting from origin, and the 2 people can go total 4 directions
    - perform DFS based on the 4 directions
    - concern: do they visit the same spot? possible. when that happens, make sure we do not double count the grid[i][j]
- when is the end state? 
    - then anyone, for example, (r1,c1) reaches end (n-1, n-1).
    - it means the other person also reaches end
- use memo: memo[r1][c1][r2], it records any given (r1, c1, r2, c2) state
*/
class Solution {
    Integer[][][] memo;
    int n;
    public int cherryPickup(int[][] grid) {
        
        n = grid.length;
        memo = new Integer[n][n][n];
        return Math.max(0, dfs(grid, 0, 0, 0));
    }
    
    private int dfs(int[][] grid, int r1, int c1, int r2) {
        int c2 = r1 + c1 - r2;
        if (r1 == n || c1 == n || r2 == n || c2 == n || grid[r1][c1] == -1 || grid[r2][c2] == -1) return Integer.MIN_VALUE;
        if (r1 == n -1 && c1 == n - 1) return grid[r1][c1];
        if (memo[r1][c1][r2] != null) return memo[r1][c1][r2];
        
        int sum = grid[r1][c1];
        if (c1 != c2)  sum += grid[r2][c2];
        
        sum += Math.max(Math.max(dfs(grid, r1 + 1, c1, r2 + 1), dfs(grid, r1, c1 + 1, r2 + 1)), 
                       Math.max(dfs(grid, r1 + 1, c1, r2), dfs(grid, r1, c1 + 1, r2)));
        memo[r1][c1][r2] = sum;
        return sum;
    }
    
   
}


/*
WRONG SOLUTION: Greedy DFS, 2 Pass
- function to cal max with DFS
- rotate the grid
- perform DFS again
- problem with DP: dp does not trace the path; but dfs can mark down values after choosen

[[1,1,1,1,0,0,0],
[0,0,0,1,0,0,0],
[0,0,0,1,0,0,1],
[1,0,0,1,0,0,0],
[0,0,0,1,0,0,0],
[0,0,0,1,0,0,0],
[0,0,0,1,1,1,1]]
*/
class Solution {
    Integer[][] memo;
    Map<String, List<int[]>> pathMemo;
    public int cherryPickup(int[][] grid) {
        
        int n = grid.length, sum = 0;
        memo = new Integer[n][n];
        pathMemo = new HashMap<>();
        List<int[]> path = new ArrayList<>();
        sum += dfs(grid, path, 0, 0);
        if (memo[n - 1][n - 1] == null) return 0;
        updateGrid(grid, path);
        
        rotate(grid);
        memo = new Integer[n][n];
        pathMemo = new HashMap<>();
        sum += dfs(grid, new ArrayList<>(), 0, 0);
        
        return sum;
    }
    
    private int dfs(int[][] grid, List<int[]> path, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == -1) return 0;
        if (memo[i][j] != null)  {
            path.addAll(pathMemo.get(getKey(i, j)));
            return memo[i][j];
        }
        
        int sum = grid[i][j]; // 0 or 1
        List<int[]> rightPath = new ArrayList<>(), downPath = new ArrayList<>();
        
        int right = dfs(grid, rightPath, i, j + 1);
        int down = dfs(grid, downPath, i + 1, j);
        
        sum += Math.max(right, down);
        path.add(new int[]{i, j});
        if (right >= down) path.addAll(rightPath);
        else path.addAll(downPath);
        
        memo[i][j] = sum;
        pathMemo.put(getKey(i,j), new ArrayList<>(path));
        return sum;
    }
    
    private String getKey(int i, int j) {
        return i + "@" + j;
    }
    
    private void updateGrid(int[][] grid, List<int[]> path) {
        for (int[] pos : path) {
            grid[pos[0]][pos[1]] = 0;
        }
    }
    
    private void rotate(int[][] grid) {
        for (int i = 0; i < grid.length; i++) flipRow(grid[i]);
        for (int j = 0; j < grid[0].length; j++) {
            int start = 0, end = grid.length - 1;
            while (start != end) {
                int temp = grid[start][j];
                grid[start++][j] = grid[end][j];
                grid[end--][j] = temp;
            }
        }
    }
    
    private void flipRow(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i != j) {
            int temp = nums[j];
            nums[j--] = nums[i];
            nums[i++] = temp;
        }
    }
}
```