M
tags: Garph, DFS, BFS
time: O(n)
space: O(n)

#### DFS marking each node with a state
- `bipartite` require each node to be in exact 1 party, which means it only has 1 state
- DFS to mark node with one state; and mark its edges as reversed state
  - If any node state has been assigned by different from desired one, return false.

#### BFS, Queue
- Use `Boolean states[i]` to represent visted & state
- Try all nodes with for loop, and skip visited nodes (similar validation rules as in dfs)
- In `int next : graph[curr]`, test next level first before adding.

```
/*
Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 

Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
*/

/*
The solution is unique: when traversing over all nodes, make sure to exhaust all edges before moving on to next node.
Validation: use a state map to record state, and validate at run time
time: O(n), visit all nodes once
space: O(n), store all states, and worst case O(n) stack
*/

class Solution {
    public boolean isBipartite(int[][] graph) {
        
        int n = graph.length; 
        int[] states = new int[n];
        for (int i = 0; i < n; i++) {
            if (states[i] != 0) continue;
            if (!dfs(graph, states, 1, i)) return false;
        }
        
        return true;
    }
    
    // traverse and validate
    private boolean dfs(int[][] graph, int[] states, int state, int node) {
        if (states[node] != 0) return states[node] == state;
        if (graph[node].length == 0) return true;
        
        states[node] = state;
        
        for (int val : graph[node]) {
            if (!dfs(graph, states, - state, val)) return false;
        }
        return true;
 
    }
}

// Method2: BFS
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length; 
        Boolean[] states = new Boolean[n];

        for (int i = 0; i < n; i++) { // some nodes has 0 edge, so try all 
            if (states[i] != null) continue; // skip visited starting node
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.offer(i);
            states[i] = true;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int next : graph[curr]) {
                    if (states[next] != null) {
                        if (states[next] == states[curr]) return false;
                        continue;
                    }
                    states[next] = !states[curr];
                    queue.offer(next);
                }
            }
        }
        return true;
    }
}
```