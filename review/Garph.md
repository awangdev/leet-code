 
 
 
## Garph (1)
**0. [785. Is Graph Bipartite.java](https://github.com/awangdev/LintCode/blob/master/Java/785.%20Is%20Graph%20Bipartite.java)**      Level: Medium      Tags: [BFS, DFS, Garph]
      

#### DFS marking each node with a state
- `bipartite` require each node to be in exact 1 party, which means it only has 1 state
- DFS to mark node with one state; and mark its edges as reversed state
  - If any node state has been assigned by different from desired one, return false.

#### BFS, Queue
- Use `Boolean states[i]` to represent visted & state
- Try all nodes with for loop, and skip visited nodes (similar validation rules as in dfs)
- In `int next : graph[curr]`, test next level first before adding.



---

