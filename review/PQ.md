 
 
 
## PQ (1)
**0. [743. Network Delay Time.java](https://github.com/awangdev/LintCode/blob/master/Java/743.%20Network%20Delay%20Time.java)**      Level: Medium      Tags: [BFS, DFS, Graph, Heap, PQ]
      

quesiton: sorting by travel delay/time will find better answer earlier?

#### Method1: BFS with PQ on graph
- `Dijkstras algorithm` is based on repeatedly making the candidate move that has the least distance travelled.
- PQ: pick close node to vist, and add siblings back to PQ
- avoid visited
- time: O(nLogn), visit n nodes, each time insert to heap takes O(logn) time
- space: O(n)

#### Method2: DFS with Sort
- 1) build graph map, 2) traverse map, 3) prioritize short delay nodes first
- use a map`<node, timeElapsed>` globally track dealy to nodes; compare all at the end



---

