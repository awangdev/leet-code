M
1527744220
tags: DFS, BFS, Union Find, Graph

给一个数字n代表n nodes, marked from 1 ~ n, 和一串undirected edge int[][]. 

count这个graph里面有多少个独立的component.

#### Union Find
- 跟Graph Valid Tree 几乎一模一样
- 建造简单的parent[] union find
- 每个edge都union.
- **注意** union 的时候, 只需要union if rootA != rootB

#### DFS
- build graph as adjacent list: Map<Integer, List<Integer>>
- dfs for all nodes of the graph, and mark visited node
- count every dfs trip and that will be the total unions

```
/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges 
(each edge is a pair of nodes), write a function to find 
the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, [0, 1] is the same as [1, 0] and 
thus will not appear together in edges.
*/

class Solution {
    int[] parent;
    int count;
    // union function
    private void union(int a, int b) {
        int parentA = parent[a];
        int parentB = parent[b];
        if (parentA != parentB) {
            parent[parentA] = parentB;
            count--;
        }
    }

    // find function
    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // return count function
    private int query() {
        return count;
    }

    public int countComponents(int n, int[][] edges) {
        if (n == 0) {
            return 0;
        } else if (edges == null || edges.length == 0) {
            return n;
        }

        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            if (find(x) != find(y)) {
                union(x, y);    
            }
        }

        return query();
    }
}


/*
DFS with adjacent list
1. build adjacent list graph: map <int, list<int>>
2. dfs(graph, visited, int index). visited:boolean[] 
*/

class Solution {
    public int countComponents(int n, int[][] edges) {
        if (n == 0) {
            return 0;
        } else if (edges == null || edges.length == 0) {
            return n;
        }

        int result = 0;
        Map<Integer, List<Integer>> graph = buildGraph(n, edges);
        boolean[] visited = new boolean[n];

        // dfs(graph, visited, i), and count result
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, visited, i);
                result++;
            }
        }
        
        return result;
    }
    
    // build graph in form of adjacent list
    private Map<Integer, List<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!graph.containsKey(i)) {
                graph.put(i, new ArrayList<>());
            }
        }
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    // dfs: mark visited nodes, and keep dfs into children nodes
    private void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int i) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        for (int j : graph.get(i)) {
            dfs(graph, visited, j);
        }
    }
}

```