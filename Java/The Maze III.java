H
1534304210
tags: DFS, BFS, PriorityQueue

#### BFS
- 跟 Maze I, II 类似, 用一个 Node[][] 来存每一个(x,y)的state.
- Different from traditional BFS(shortest path): `it terminates BFS when good solution exists (distance), but will finish all possible routes`
- 1. `Termination condition`: if we already have a good/better solution on nodeMap[x][y], no need to add a new one
- 2. Always cache the node if passed the test in step1
- 3. Always offer the moved position as a new node to queue (as long as it fits condition)
- 4. Finally the item at nodeMap[target.x][target.y] will have the best solution.

```
/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

Example 2

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)
Output: "impossible"
Explanation: The ball cannot reach the hole.

Note:
There is only one ball and one hole in the maze.
Both the ball and hole exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.

*/

class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    String[] ds = {"d", "u", "r", "l"};
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        // init
        Node[][] nodeMap = new Node[m][n];
        for (int i = 0; i < n * m; i++) nodeMap[i/n][i%n] = new Node(i/n, i%n);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(ball[0], ball[1], 0, ""));
        
        // BFS
        while (!pq.isEmpty()) {
            int size = pq.size();
            for (int i = 0; i < size; i++) {
                Node node = pq.poll();
                if (nodeMap[node.x][node.y].compareTo(node) <= 0) continue; // already found a better node
                nodeMap[node.x][node.y] = node; // cache node in nodeMap since it's a good candidate
                for (int j = 0; j < 4; j++) {
                    int x = node.x, y = node.y, dist = node.dist;
                    while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0 && !isEnd(hole, x, y)) {
                        x+=dx[j];
                        y+=dy[j];
                        dist++;
                    }
                    if (!isEnd(hole, x, y)) {
                        x-=dx[j];
                        y-=dy[j];    
                        dist--;
                    }
                    // always offer to queue, as long as we can reach
                    pq.offer(new Node(x, y, dist, node.path + ds[j]));
                }
            }
        }
        
        if (nodeMap[hole[0]][hole[1]].dist != Integer.MAX_VALUE) return nodeMap[hole[0]][hole[1]].path;
        return "impossible";
    }
    
    private boolean isEnd(int[] hole, int x, int y) {
        return x == hole[0] && y == hole[1];
    }
    
    class Node implements Comparable<Node>{
        int x, y, dist;
        String path;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.dist = Integer.MAX_VALUE;
        }
        public Node(int x, int y, int dist, String path) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.path = path;
        }
        public int compareTo(Node node) {
            return dist == node.dist ? path.compareTo(node.path): dist - node.dist;
        }
    }
}

```