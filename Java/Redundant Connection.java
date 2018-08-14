M
1534222102
tags: Tree, Union Find, Graph, DFS, BFS

#### unionFind
- keyword: tree has no `cycle`.
- 一旦两个node在edge中出现, 并且parent相同, 说明这两个node不union, 也在同一个tree里面, 所以可以break them.

#### Graph, DFS
- Add graph using adjacent list, and verify cycle alone the way
- IMPORTANT: use `pre` node in dfs to prevent backward dfs
- similar to `Graph Valid Tree` where it validates cycle and also needs to validate if all nodes are connected

#### BFS
- same concept as DFS, find first redundant edge that alreay exists in graph map.

```
/*
In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

Update (2017-09-26):
We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph. For the directed graph follow up please see Redundant Connection II). We apologize for any inconvenience caused.


*/

/*
UnionFind
keyword: tree does not have cycle; if there is cycle, there must be a redundant edge that can be removed.
Goal: find that edge.
Simply write union-find methods. When parents of two nodes are the same, that means they have been visited and in same union: we can break this edge
*/
class Solution {
    int[] parent;
    public int[] findRedundantConnection(int[][] edges) {
        parent = new int[2001]; // at most 1000 edges, so at most 1000*2 + 1 nodes
        // init unionFind
        for (int i = 0; i < parent.length; i++) parent[i] = i;
        
        // Union
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            int parentX = find(x), parentY = find(y);
            if (parentX == parentY) return edge;
            parent[parentX] = parentY;
        }
        
        return new int[] {-1, -1};
    }
    
    public int find(int x) {
        int parentX = parent[x];
        if (parentX == x) return parentX;
        return parent[x] = find(parentX);
    }
        
}

// DFS
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            if (graph.containsKey(x) && hasCycle(graph, x, y, -1)) return edge;
            graph.putIfAbsent(x, new HashSet<>());
            graph.putIfAbsent(y, new HashSet<>());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        
        return new int[] {-1, -1};
    }
    
    private boolean hasCycle(Map<Integer, Set<Integer>> graph, int curr, int target, int pre) {
        if (graph.get(curr).contains(target)) return true;
        for (int num : graph.get(curr)) {
            if (num == pre) continue;
            if (hasCycle(graph, num, target, curr)) return true;
        }
        return false;
    }  
}

/*
BFS, same concept: keep adding into graph.
If item already added once, then redundant edge detected
*/
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(x);
            
            // BFS all possible nodes see if node y has been added 
            while (!queue.isEmpty()) {
                int node = queue.poll();
                if (!graph.containsKey(node)) continue;
                if (graph.get(node).contains(y)) return edge;
                for (int num : graph.get(node)) {
                    if (visited.contains(num)) continue;
                    queue.offer(num);
                    visited.add(num);
                }
            }

            // passed test, add graph nodes
            graph.putIfAbsent(x, new HashSet<>());
            graph.putIfAbsent(y, new HashSet<>());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        
        return new int[] {-1, -1};
    }  
}
```