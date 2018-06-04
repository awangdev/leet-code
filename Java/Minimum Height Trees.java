M
tags: BFS, Graph

#### Graph + BFS
- Build graph `map<node, list of node>`
- BFS to find the shortest path: when the neibhbor has the curr node as the only one neighbor, it is leaf.
- record shortest path in Map<Integer, List<Integer>> as result
- TODO: code it up.

#### Previous Solution
- removing leaf && edge

```
/*
For a undirected graph with tree characteristics, we can choose any node as the root. 
The result graph is then a rooted tree. Among all possible rooted trees, 
those with minimum height are called minimum height trees (MHTs). 
Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. 
You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Show Hint 
Note:

(1) According to the definition of tree on Wikipedia: 
“a tree is an undirected graph in which any two vertices are connected by exactly one path. 
In other words, any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the 
longest downward path between the root and a leaf.

Credits:
Special thanks to @peisi for adding this problem and creating all test cases.

Hide Company Tags Google
Hide Tags Breadth-first Search Graph
Hide Similar Problems (M) Course Schedule (M) Course Schedule II

*/


/*
    Starting from leaf with depth == 1,
        remove all leaf, and the edge
    Till the end, whatever node left, should be the root.
    
    * WHen there is only 1,2 nodes remaining. that's the rst.
    
    Put Node in  HahsMap<node, list of neighbor node>
    
    Iterative over map till map.size() <= 2
    
    border n == 2,1, just returl rst.
    edges == null, return null.
    edges.length == 1, reutrn list
*/

public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> rst = new ArrayList<Integer>();
        if (n == 1) {
            rst.add(0);
            return rst;
        }else if (n == 0 || edges == null || edges.length == 0 || edges.length != n - 1) {
            return rst;
        }
        
        //populate map
        boolean[] nodes = new boolean[n];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<Integer>());
            nodes[i] = true;
        }
        for (int i = 0; i < edges.length;  i++) {        
            if (!map.get(edges[i][0]).contains(edges[i][1])) {
                map.get(edges[i][0]).add(edges[i][1]);
            }   
            if (!map.get(edges[i][1]).contains(edges[i][0])) {
                map.get(edges[i][1]).add(edges[i][0]);
            }  
        } 
        
        //Remove list with leng == 1
        Queue<Integer> queue = new LinkedList<Integer>();
        while (n > 2) {
            for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
                if (entry.getValue().size() == 1) {
                    queue.offer(entry.getKey());
                }
            }
            while (!queue.isEmpty()) {
                n--;
                Integer key = queue.poll();
                nodes[key] = false;
                int from = map.get(key).get(0);
                map.get(from).remove(key);
                map.get(key).remove(0);
                
            }
        }
        
        //Put remaining into rst
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i]) {
                rst.add(i);
            }

        }
        
        return rst;
    }
}
```