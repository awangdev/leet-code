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

Thinking process:
1. Clone all nodes available: using HashMap to go through all possible query. No duplicates added using HashMap.
	HashMap map has the list of all new nodes. No neighbors added yet
	<key,value> = <original node,  new node with just a label (without neighbor list)>
	At same time, the arrayList nodes has all original nodes(with neighbors) in Breadth-first order.
2. Add neighbor for nodes in map:
	- Locate the 'newNode' from map by using the key: the original node
	- loop through the original node's neighbor size
	- use original neighbor as key to get the new neighbor instance from map
	- add this new neighbor instance to the neighbor list of 'newNode'
	
*/

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        ArrayList<UndirectedGraphNode> nodes = new ArrayList<UndirectedGraphNode>();
        nodes.add(node);
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        map.put(node, new UndirectedGraphNode(node.label));
        int start = 0;
        //Clone nodes without neighbors:
        while (start < nodes.size()) {
            List<UndirectedGraphNode> neighbors = nodes.get(start++).neighbors;
            for (int i = 0; i < neighbors.size(); i++) {
                if (!map.containsKey(neighbors.get(i))) {
                    map.put(neighbors.get(i), new UndirectedGraphNode(neighbors.get(i).label));
                    nodes.add(neighbors.get(i));
                }
            }
        }
        // Clone neighbors:
        for (int i = 0; i < nodes.size(); i++) {
            UndirectedGraphNode newNode = map.get(nodes.get(i));
            for (int j = 0; j < nodes.get(i).neighbors.size(); j++) {
                newNode.neighbors.add(map.get(nodes.get(i).neighbors.get(j)));
            }
        }
        return map.get(node);    
    }
}

