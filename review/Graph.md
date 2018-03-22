 
 
 
## Graph (2)
**0. [Clone Graph.java](https://github.com/awangdev/LintCode/blob/master/Java/Clone%20Graph.java)**      Level: Medium
      

思想:
Use HashMap to mark cloned nodes.    
先能复制多少Node复制多少. 然后把neighbor 加上

方法一: DFS
1. copy the node
2. Mark 'added' using map(old, new)
3. for loop on the each one of the neighbors: map copy, record in map, and further dfs
4. once dfs completes, add newNeighbor as neighbor of the new node (get to it via map)
主要思想是: 一旦复制过了, 不必要重新复制

方法二: BFS
1. Copy the root node, then copy all the neighbors. 
2. Mark copied node in map.
3. Use queue to contain the newly added neighbors. Need to work on them in the future.

注意:
initialize map with (node, newNode)



---
**1. [Graph Valid Tree.java](https://github.com/awangdev/LintCode/blob/master/Java/Graph%20Valid%20Tree.java)**      Level: Medium
      

复习Union-Find的另外一个种形式。   
题目类型：查找2个元素是不是在一个set里面。如果不在，false. 如果在，那就合并成一个set,共享parent.   
存储的关键都是：元素相对的index上存着他的root parent.    

注意: 结尾要检查, 是否只剩下1个union. Tree必须连接到所有给出的node.

另一个union-find， 用hashmap的：http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/




---
