M
1527969793
tags: BFS, DFS

给一个undirected graph, return 所有的component. (这道题找不到了)  

#### BFS
- BFS遍历，把每个node的neighbor都加进来. 
- 一定注意要把visit过的node Mark一下。因为curr node也会是别人的neighbor，会无限循环。      
- Component的定义：所有Component内的node必须被串联起来via path (反正这里是undirected, 只要链接上就好)     
- 这道题：其实component在input里面都已经给好了，所有能一口气visit到的，全部加进queue里面，他们就是一个component里面的了。     
- 而我们这里不需要判断他们是不是Component

#### DFS
- DFS 应该也可以 visit all nodes, mark visited.

```
/*
Find the number connected component in the undirected graph. 
Each node in the graph contains a label and a list of its neighbors. 
(a connected component (or just component) of an undirected graph is a subgraph 
in which any two vertices are connected to each other by paths, 
and which is connected to no additional vertices in the supergraph.)

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

 */

/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

/*
OPTS: 11.07.2015
Try to use ae map<Integer, false> to mark the nodes. Then do a BFS with queue
1. Mark each node in map.
2. BFS each node
3. Whenver one node is checked, mark it check
*/

public class Solution {
    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nodes == null || nodes.size() == 0) {
            return rst;
        }

        HashMap<Integer, Boolean> map = new HashMap<>();
        for (UndirectedGraphNode node : nodes) {
            map.put(node.label, false);
        }

        for (UndirectedGraphNode node : nodes) { 
            if (!map.get(node.label)) {
                bfs(rst, node, map);
            }
        }
        return rst;
    }

    public void bfs(List<List<Integer>> rst, UndirectedGraphNode node, HashMap<Integer, Boolean> map) {
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        List<Integer> list = new ArrayList<Integer>();
        queue.add(node);
        map.put(node.label, true);
        UndirectedGraphNode temp;
        while (!queue.isEmpty()) {
             temp = queue.poll();
             list.add(temp.label);
             for (UndirectedGraphNode neighbor : temp.neighbors) {
                if (!map.get(neighbor.label)) {
                    queue.offer(neighbor);
                    map.put(neighbor.label, true);
                }
             }
        }
        Collections.sort(list);
        rst.add(list);
    }
}


/*
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

```