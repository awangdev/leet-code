M
1534299081
tags: DFS, BFS, PriorityQueue

#### BFS
- if already found a good/shorter route, skip
- `if (distMap[node.x][node.y] <= node.dist) continue;`
- This always terminates the possibility to go return to original route, because the dist will be double/higher

```
/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

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

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1
Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100
*/
// BFS with int[][] distMap
class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int m = maze.length, n = maze[0].length;
        int[][] distMap = new int[m][n];
        for (int i = 0; i < n * m; i++) {
            distMap[i/n][i%n] = Integer.MAX_VALUE;
        }
        //PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(node -> node.dist)); // a bit slow
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.dist - b.dist;
            }
        });
        pq.offer(new Node(start[0], start[1], 0));
        
        while (!pq.isEmpty()) {
            int size = pq.size();
            for (int i = 0; i < size; i++) {
                Node node = pq.poll();
                if (distMap[node.x][node.y] <= node.dist) continue; // already found a good/shorter route
                distMap[node.x][node.y] = node.dist;
                for (int j = 0; j < 4; j++) {
                    int x = node.x, y = node.y;
                    while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                        x+=dx[j];
                        y+=dy[j];
                    }
                    x-=dx[j];
                    y-=dy[j];
                    int dist = Math.abs(node.x - x) + Math.abs(node.y - y);
                    pq.offer(new Node(x, y, node.dist + dist));
                }
            }
        }
        
        if (distMap[dest[0]][dest[1]]!= Integer.MAX_VALUE) return distMap[dest[0]][dest[1]];
        return -1;
    }
    
    class Node {
        int x, y, dist;
        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}

// BFS with Map<'x@y', Int> distMap
class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int n = maze.length, m = maze[0].length;
        Map<String, Integer> distMap = new HashMap<>();
        for (int i = 0; i < n * m; i++) {
            distMap.put(getKey(i/n, i%n), Integer.MAX_VALUE);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(node -> node.dist));
        pq.offer(new Node(start[0], start[1], 0));
        
        while (!pq.isEmpty()) {
            int size = pq.size();
            for (int i = 0; i < size; i++) {
                Node node = pq.poll();
                String key = getKey(node.x, node.y);
                if (distMap.containsKey(key) && distMap.get(key) <= node.dist) continue; // already found a good/shorter route
                distMap.put(key, node.dist);
                for (int j = 0; j < 4; j++) {
                    int x = node.x, y = node.y, dist = node.dist;
                    while (x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 0) {
                        x+=dx[j];
                        y+=dy[j];
                        dist++;
                    }
                    x-=dx[j];
                    y-=dy[j];
                    dist--;
                    pq.offer(new Node(x, y, dist));
                }
            }
        }

        String key = getKey(dest[0], dest[1]);
        if (distMap.containsKey(key) && distMap.get(key) != Integer.MAX_VALUE) return distMap.get(key);
        return -1;
    }
    
    private boolean verify(Node node, int[] dest) {
        return node.x == dest[0] && node.y == dest[1];
    }
    
    private String getKey(int x, int y){
        return x + "@" + y;
    }
    
    class Node {
        int x, y, dist;
        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
```