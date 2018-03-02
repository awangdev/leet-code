M
1519966780
tags: DFS, BFS, Graph

思想:
Use HashMap to mark cloned nodes.    
先能复制多少Node复制多少. 然后把neighbor 加上

方法一: DFS
1. copy the node
2. Mark 'added' using map(old, new)
3. for loop on the each one of the neighbors: map copy, record in map, and further dfs
4. once dfs completes, add newNeighbor as neighbor of the new node (get to it via map)
主要思想是: 一旦复制过了, 不必要重新复制

方法二: BFS
1. Copy the root node, then copy all the neighbors. 
2. Mark copied node in map.
3. Use queue to contain the newly added neighbors. Need to work on them in the future.

注意:
initialize map with (node, newNode)

```
/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
Hide Tags Depth-first Search Breadth-first Search Graph

    
*/
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
/*
Thougths:
DFS with map in graph.
The serialized graph is just explaination for the test input.
1. copy the node
2. Mark 'added' using map(old, new)
3. for loop on the each one of the neighbors: map copy, record in map, and further dfs
4. once dfs completes, add newNeighbor as neighbor of the new node (get to it via map)
*/
public class Solution {
    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
       
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(node, newNode);
        dfs(node);
        return newNode;
    }
    
    public void dfs(UndirectedGraphNode node) {
        if (node == null) {
            return;
        }
        for (UndirectedGraphNode neighbor: node.neighbors) {
            if (!map.containsKey(neighbor)) {
                UndirectedGraphNode newNeighbor = new UndirectedGraphNode(neighbor.label);
                map.put(neighbor, newNeighbor);
                dfs(neighbor);
            }
            map.get(node).neighbors.add(map.get(neighbor));
        }
    }
}

/*
Thougths:
BFS, same concept as DFS.
1. Copy the root node, then copy all the neighbors. 
2. Mark copied node in map.
3. Use queue to contain the neighbors for next round: if it has neighbors
*/
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        map.put(node, new UndirectedGraphNode(node.label));

        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.offer(node);
        
        while(!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();
            for (UndirectedGraphNode neighbor: curr.neighbors) {
                // Copy neighbors
                if (!map.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                }
                map.get(curr).neighbors.add(map.get(neighbor));
            }
        }
        
        return map.get(node);
    }
}
```
