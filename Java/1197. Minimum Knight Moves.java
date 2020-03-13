M
tags: BFS
time: O(8^n)
space: O(8^n)

#### BFS
- `from starting point, find min steps to reach certain point`: think of BFS
    - similar: shortest path, shortest distance
- bfs: minimum steps, enumerate the possible moves
    - move closer to x or y (test 8 possible directions)
    - add possible moves in queue
- use visited to cache visited coordinates
- time: O(8^n), # of BFS branches
- space: O(8^n), # of BFS branche nodes

```
/*
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.



Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

 

Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 

Constraints:

|x| + |y| <= 300
*/

/*
- bfs: minimum steps, enumerate the possible moves
    - move closer to x or y (test 8 possible directions)
    - add possible moves in queue
*/
class Solution {
    int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    int[] dy = {-2, -1, 1, 2, -2, -1, 1, 2};
    Set<String> visited = new HashSet<>();
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited.add("0,0");
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] pos = queue.poll();
                if (pos[0]==x && pos[1]==y) return count;
                addMoves(queue, pos, x, y);
            }
            count++;
        }
        return -1;
    }
   
    private void addMoves(Queue<int[]> queue, int[] pos, int x, int y) {
        for (int i = 0; i < 8; i++) {
            int nx = pos[0] + dx[i], ny = pos[1] + dy[i]; // (x,y) at positive direction; `nx >= -1 && ny >= -1` moves towards (x,y)
            if (!visited.contains(nx+","+ny) && nx >= -1 && ny >= -1) {  
                queue.offer(new int[]{nx, ny});
                visited.add(nx+","+ny);
            }
        }
    }
}
```