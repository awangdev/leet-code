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

Thoughts:
First idea is Breadth First Search.
1. Find the node which has no parent node: this will be the beginning node. Use a HashMap to map all nodes with children, and whatever not in that map, is a root option.
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
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (DirectedGraphNode node : graph) {
        	for (DirectedGraphNode neighbor : node.neighbors) {
        		int keyN = neighbor.label;
	        	if (map.containsKey(keyN)) {
	        		map.put(keyN, map.get(keyN) + 1);
	        	} else {
	        		map.put(keyN, 1);
	        	}
        	}
        }
        //BFS: Add root node. Note: 
        Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
        for (DirectedGraphNode node : graph) {
        	if (!map.containsKey(node.label)) {
        		queue.offer(node);
        		rst.add(node);
        	}
        }
        //BFS: go through all children
        while (!queue.isEmpty()) {
        	DirectedGraphNode node = queue.poll();	
        	for (DirectedGraphNode n : node.neighbors) {
        		int label = n.label;
        		map.put(label, map.get(label) - 1);
        		if (map.get(label) == 0) {
        			rst.add(n);
        			queue.offer(n);
        		}
        	}
        }
        return rst;
    }
}
