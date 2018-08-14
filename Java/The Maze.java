M
1534262484
tags:  DFS, BFS

#### BFS
- BFS on coordinates
- always attempt to move to end of border

```
/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

 */

// BFS
class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    public boolean hasPath(int[][] maze, int[] start, int[] dest) {
        int n = maze.length;
        int m = maze[0].length;
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start[0] + "@" + start[1]);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                if (verify(pos, dest)) return true;
                for (int j = 0; j < 4; j++) {
                    int x = pos[0], y = pos[1];
                    while (x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 0) {
                        x+=dx[j];
                        y+=dy[j];
                    }
                    x-=dx[j];
                    y-=dy[j];
                    String key = x + "@" + y;
                    if (!visited.contains(key)) {
                        visited.add(key);
                        queue.offer(new int[] {x, y});
                    }
                }
            }
        }
        return false;
    }
    
    private boolean verify(int[] pos, int[] dest) {
        return pos[0] == dest[0] && pos[1] == dest[1];
    }
}
```