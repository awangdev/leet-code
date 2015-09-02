 /*
Find the number connected component in the undirected graph. Each node in the graph contains a label and a list of its neighbors. (a connected component (or just component) of an undirected graph is a subgraph in which any two vertices are connected to each other by paths, and which is connected to no additional vertices in the supergraph.)

Example
Given graph:

A------B  C
 \     |  | 
  \    |  |
   \   |  |
    \  |  |
      D   E
Return {A,B,D}, {C,E}. Since there are two connected component which is {A,B,D}, {C,E}

Note
Tags Expand 
Breadth First Search

Thoughts:
How do we check for a connected graph (any two nodes are connected)?
Maybe check for each node: each node represents a lead to a subgraph, then check if this subgraph
is valid.


1. In real case, need to ask the intervier: can we assume the given nodes are valid, so that we only
need to check for success case? That means, we assume for example a linear list A-B-C does not exist.

2. Then, we can use a 'set' to mark: we've checked this node.
3. Use a queue for BFS
4. Use a arraylist to save the results.
5. Key point: when the queue is empty(), that means one set of connected component is ready to go
6. Iterate through nodes, when it's not empty.

More Notes:Have to do Collections.sort()....somehow it want me to sort the results?
Note2: Get rid of a node from nodes, whenever add it to component ... don't forget this.
Note3: Well, there is a chance that compoents are added, queue is cleaned, but nodes are empty as well..
that means, need to catch the last case of 'remaining component' and add it to rst.


Review:
How list, ArrayList, Set, Queue work.
How to do: add, remove, sort 

Collections: Set, List, Queue

List: ArrayList

Set methods: add(), contains(?)
Queue methods: offer(E e), add(E e), poll()
ArrayList method: add(E e), isEmpty(),  remove(object o)

 */


/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nodes == null || nodes.size() == 0) {
        	return rst;
        }
        //Init:
 		Set<UndirectedGraphNode> checked = new HashSet();
        Queue<UndirectedGraphNode> queue = new LinkedList();
        ArrayList<Integer> component = new ArrayList<Integer>();
        
        queue.offer(nodes.get(0));

        while (!nodes.isEmpty()) {
        	if (queue.isEmpty()) {
        	    Collections.sort(component);
        		rst.add(component);
        		queue.offer(nodes.get(0));
        		component = new ArrayList<Integer>();
        	} else {
        		UndirectedGraphNode curr = queue.poll();
        		if (!checked.contains(curr)) {
        			checked.add(curr);
        			component.add(curr.label);
        			nodes.remove(curr);
        			for (UndirectedGraphNode node : curr.neighbors) {
        					queue.add(node);	
        			}
        		}
        	}
        }
        if (!component.isEmpty()) {
            rst.add(component);
        }
        return rst;
    }
}
