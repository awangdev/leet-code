H
1534232150
tags: Tree, DFS, Union Find, Graph

#### Union Find
- 讨论3种情况
- http://www.cnblogs.com/grandyang/p/8445733.html

```
/*
In this problem, a rooted tree is a directed graph such that, 
there is exactly one node (the root) for which all other nodes are descendants of this node, 
plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), 
with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that 
represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. 
If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
*/

/*
- find inDegree == 2
- union and return cycle item
- if cycle not exist, return the inDegree==2 edge

*/
class Solution {
    int[] parent;
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];

        // find the edges where inDegree == 2 
        int[] first = null, second = null;
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            if (parent[y] == 0) {
                parent[y] = x;
            } else {
                first = new int[]{parent[y], y};
                second = new int[]{edge[0], edge[1]};
                edge[1] = 0; // why?
            }
        }
        // re-init unionFind
        for (int i = 0; i <= n; i++) parent[i] = i;
        
        // Union
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            if (y == 0) continue;
            int parentX = find(x), parentY = find(y);
            if (parentX == parentY) return first == null ? edge : first;
            parent[parentX] = parentY;
        }
        
        return second;
    }
    
    public int find(int x) {
        int parentX = parent[x];
        if (parentX == x) return parentX;
        return parent[x] = find(parentX);
    }
}
```