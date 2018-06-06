M
1528248097
tags: Topological Sort, BFS, DFS

#### Topological Sort BFS
- indegree tracking: Track all neighbors/childrens. 把所有的children都存在 inDegree<label, indegree count>里面
- Process with a queue: 先把所有的root加一遍(indegree == 0)，可能多个root。并且全部加到queue里面。
- BFS with Queue:
- Only when map.get(label) == 0, add into queue && rst. (indegree剪完了, 就是root啦)
- inDegree在这里就 count down indegree, 确保在后面出现的node, 一定最后process.


#### Basics about graph
- 几个graph的condition：   
- 1. 可能有多个root
- 2. directed node, 可以direct backwards.

TODO:
- build`Map<DirectedGraphNode, Integer> inDegree = new HashMap<>();` and include the root itself
- that is more traditional indegree building

```
/*
Given an directed graph, a topological order of the graph nodes is defined as follow:

For each directed edge A -> B in graph, A must before B in the order list.
The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.

Example
For graph as follow:

picture

The topological order can be:

[0, 1, 2, 3, 4, 5]
[0, 2, 3, 1, 5, 4]
...
Note
You can assume that there is at least one topological order in the graph.

Challenge
Can you do it in both BFS and DFS?

Tags Expand 
LintCode Copyright Geeks for Geeks Depth First Search Breadth First Search

*/

/**

Thoughts:
First idea is Breadth First Search.
1. Find the node which has no parent node: this will be the beginning node. 
   Use a HashMap to map all nodes with children, and whatever not in that map, is a root option.
2. Starting from this node, put all nodes in the queue (breadth-first)
3. process each node in the queue: add to array list


Note: All all possible root node (whatever not added into the map) because there could be multiple heads : (. Really need to ask about this if not sure.

 */
/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */    
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> rst = new ArrayList<DirectedGraphNode>();
        if (graph == null || graph.size() == 0) {
            return graph;
        }
        //Keep track of all neighbors in HashMap
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                int keyN = neighbor.label;
                if (!inDegree.containsKey(keyN)) {
                    inDegree.put(keyN, 0);
                }
                inDegree.put(keyN, inDegree.get(keyN) + 1);
            }
        }

        //BFS: Add root node.
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            if (!inDegree.containsKey(node.label)) {
                queue.offer(node);
                rst.add(node);
            }
        }
        //BFS: go through all children
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();  
            for (DirectedGraphNode n : node.neighbors) {
                int label = n.label;
                inDegree.put(label, inDegree.get(label) - 1);
                if (inDegree.get(label) == 0) {
                    rst.add(n);
                    queue.offer(n);
                }
            }
        }
        return rst;
    }
}

```