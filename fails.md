#### UnionFind.union, without checking rootA != rootB
- P: Number of Connected Components in an Undirected Graph
- when rootA == rootB, union exist already, no need to union again.

#### Don't forget to map.put(i, new ArrayList<>())
- P: Number of Connected Components in an Undirected Graph
- in DFS approach when initializing the adjacent list: use `map.put(x,y)`, not `add`


#### forgot to cast int to char
- P: wordladder
- `sb.setCharAt(i, (char) j + 'a');`
- Given input list: list.contains(), list.remove() are **O(logn)**!!!
- Make sure to convert it to HashSet() if need to use contains()/remove()

#### Check input range for SegmentTree problem
- P: Segment Tree Query II
- Forgot to validate [start, end] range. 
- Need to fail if completely out of range, or override one side of the range if partial of the range is applicable
