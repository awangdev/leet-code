M
tags: DFS, BFS, Union Find, Graph 

给一个数字n代表n nodes, marked from 1 ~ n, 和一串undirected edge int[][]. 

检查这些edge是否能合成一个 valid tree

#### Method1: Union Find
- 复习Union-Find的另外一个种形式, track union size: tree does not have cycle, so eventually union size should == 1
    - 1. 查找2个元素是不是在一个union里面。如果不在，false. 如果在，那就合并成一个set, 共享parent.   
    - 2. 验证cycle: `find(x) == find(y) => cycle`
        - ideally, this edges[i] should be the very first time x and y node connect;
        - however, if they have been grouped together under same ancestor before, there exist a feedback loop (cycle) between them.
- `father[x]`: element x (index x) stores its root ancestor
- 注意: 结尾要检查, 是否只剩下1个union: Tree必须连接到所有给出的node.
- 另一个union-find, 用hashmap的:
- http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/
- Deep Dive into UnionFind: https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf

#### Method2: Build Graph in adjacency list format: `Map<Integer, Set<Integer>>` and DFS
- Very similar to `Redundant Connection`
- Create adjacency list graph: Map<Integer, List<Integer>>
- 检查: 
- 1. 是否有cycle using dfs, check boolean[] visited
- 2. 是否所有的node全部链接起来: validate if all edge connected: # of visited node should match graph size
- IMPORTANT: use `pre` node to avoid linking backward/infinite loop such as (1)->(2), and (2)->(1)

#### Method3: BFS on adjacency list graph
- traverse through adjacency list graph: `Map<Integer, List<Integer>>` 
- 1. validate cycle with set: if revisit same node
    - avoid infinite loop: remove backward mapping from child node to parent node
- 2. validate check set size for connected

```
/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
[0, 1] is the same as [1, 0] and thus will not appear together in edges.


 */


/*
Method1: Union Find
Tree should not have cycle, which means adding each edge should connect a new node and that node should not be in the tree yet.
If found cycle, return false.

Note: need to count # of unions after merging. Count should be 1 for a tree.
*/
// 简化版 unionfind
class Solution {
    int[] father;
    int count;
    public boolean validTree(int n, int[][] edges) {
        // init union find data structure
        father = new int[n];
        count = n;
        for (int i = 0; i < n; i++) father[i] = i;
        // perform union find
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0], y = edges[i][1];
            if (find(x) == find(y)) return false; // ideally, this edges[i] should be the very first time x and y node connect; however, if they have been grouped together under same ancestor before, there exist a feedback loop (cycle) between them.
            union(x, y);
        }
        return count == 1; // no other isolated sub-graph
    }
    
    private void union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX != rootY) {
            father[rootX] = rootY;
            count--;
        }
    }

    private int find(int x) {
        if (father[x] == x) return x;
        return father[x] = find(father[x]);
    }
}

/*
Method2: DFS
1. Tree should not have cycle, which means adding each edge should connect a new node 
and that node should not be in the tree yet. If found cycle, return false.

2. Validate if all edge connected: # of visited node should match graph size    
*/
class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) return false;

        Map<Integer, Set<Integer>> graph = buildGraph(n, edges);
        Set<Integer> visited = new HashSet<>();

        // dfs(graph, visited, i, -1) and validate cycle
        if (!dfs(graph, visited, 0, -1)) return false;
        
        // validate if all edge connected: # of visited node should match graph size        
        return visited.size() == graph.size();
    }
    
    // build graph in form of adjacent list
    private Map<Integer, Set<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.putIfAbsent(i, new HashSet<>());
        
        for (int[] edge: edges) { // undirected, add edge to both nodes
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    // dfs: mark visited nodes, and keep dfs into children nodes
    private boolean dfs(Map<Integer, Set<Integer>> graph, Set<Integer> visited, int curr, int pre) {
        if (visited.contains(curr)) return false;
        visited.add(curr);
        for (int child : graph.get(curr)) {
            if (child == pre) continue;
            if (!dfs(graph, visited, child, curr)) return false;
        }
        return true;
    }
}


/*
#### Method3: BFS on adjacency list graph
- validate cycle with set: if revisit same node
    - avoid infinite loop: remove curr node from child node
- validate check set size for connected
*/
class Solution {
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = buildGraph(n, edges);
        Set<Integer> visited = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited.add(0);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int child : graph.get(node)) {
                if(!visited.add(child)) return false; // exist, cycle, return false;
                queue.offer(child);
                graph.get(child).remove(node); // remove backward pointer to avoid infinite loop
            }
        }
        
        // validate if all edge connected: # of visited node should match graph size        
        return visited.size() == graph.size();
    }
    
    // build graph in form of adjacent list
    private Map<Integer, Set<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.putIfAbsent(i, new HashSet<>());
        
        for (int[] edge: edges) { // undirected, add edge to both nodes
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
}

/****************  Full-length union-find, for reading purpose, not necessary to use. *********/
/*
How to simplify `UnionFind`?
- think of what data structure is in union-find: `parnet[]`
- just impl `parent[]` and the `union(x,y)`, `find(x)` functions as private functions in class Solution
- Take 1 step back: `UnionFind` class is just a convinient wraped up data structure :)
*/
class Solution {
    public boolean validTree(int n, int[][] edges) {
        // No node, false
        if (n == 0) {
            return false;
        }
        // 1 Node without edges, true
        if (n == 1 && (edges == null || edges.length == 0)) {
            return true;
        }
        // >= 2 nodes but no edges, false;
        if (edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0) {
            return false;
        }
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            if (unionFind.find(x) == unionFind.find(y)) {
                return false;
            }
            unionFind.union(x, y);
        }
        return unionFind.query() == 1;
    }
}

class UnionFind {
    int[] father;
    int count;

    UnionFind(int x) {
        father = new int[x];
        count = x;
        for (int i = 0; i < x; i++) {
            father[i] = i;
        }
    }
    
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            father[rootX] = rootY;
            count--;
        }
    }

    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    
    public int query() {
        return count;
    }
}


```