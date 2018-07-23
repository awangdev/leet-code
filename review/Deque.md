 
 
 
## Deque (1)
**0. [Serilization and Deserialization Of Binary Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Serilization%20and%20Deserialization%20Of%20Binary%20Tree.java)**      Level: Hard      Tags: [BFS, DFS, Deque, Design, Divide and Conquer, Tree]
      

Serialize and Deserialize Binary Tree

#### DFS, Divide and Conquer
##### Serilize
- Divide and conquer: Pre-order traversal to link all nodes together
- build the string data: use '#' to represent null child. 
- the preorder string, can be parsed apart by `split(',')`

##### Deserialize
- Use a list (here we use `Deque` for the ease of get/remove in 1 function: remove()) 
- to take all parts of the parsed sring data: dfs on the Deque
- first node from the list is always the head
- '#' will be a null child: this should break dfs
- Deque is a global variable, so dfs(right child) will happen after dfs(left child) completes

#### DFS, Recursive [previous note]
- serilize: divide and conquer, pre-order traversal
- deserialize: 稍微复杂, 用dfs. 每次要truncate input string: 
- 一直dfs找left child, 接着right child until leaf is found.
- 用一个StringBuffer来hold string, 因为string 是primitive, 我们这里需要pass reference

#### BFS, Non-recursive
- using queue. 想法直观。level-order traversal. save到一个string里面就好。
- 遇到null child, 不是直接忽略, 而是assign一个Integer.MIN_VALUE, 然后 mark as '#'
- BFS需要track queue size, 每一次只process特定数量的nodes



---

