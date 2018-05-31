#### UnionFind.union, without checking rootA != rootB
- P: Number of Connected Components in an Undirected Graph
- when rootA == rootB, union exist already, no need to union again.

#### Don't forget to map.put(i, new ArrayList<>())
- P: Number of Connected Components in an Undirected Graph
- in DFS approach when initializing the adjacent list: use `map.put(x,y)`, not `add`


