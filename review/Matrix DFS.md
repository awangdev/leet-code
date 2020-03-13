 
 
 
## Matrix DFS (2)
**0. [Surrounded Regions.java](https://github.com/awangdev/LintCode/blob/master/Java/Surrounded%20Regions.java)**      Level: Medium      Tags: [BFS, DFS, Matrix DFS, Union Find]
      
给一个2D board, 里面是 'X' 和 'O'. 把所有被X包围的area都涂成'X'. 

从四个边的edge出发, 像感染僵尸病毒一样扩散, 把靠边的node全部mark, 然后将还是'O'的改成X, 最后回复marks -> 'O'

#### Union Find
- UnionFind里面这次用到了一个rank的概念, 需要review. rank[] 也就是在tracking每一个node所在union的size.
- 目的是: always并到大的union里面
- note: 将2D coordinate (x,y) 转换成1D: index = x * n + y

#### DFS: mark all invalid 'O'
- Reversed thinking: find surrounded nodes, how about filter out border nodes && their connections?
- Need to traverse all the border nodes, consider dfs, visit all.
- loop over border: find any 'O', and dfs to find all connected nodes, mark them as 'M'
- time: O(mn) loop over all nodes to replace remaining 'O' with 'X'

#### DFS: mark all valid 'O'
- More like a graph problem: traverse all 'O' spots, and mark as visited int[][] with area count [1 -> some number]
- Run dfs as top->bottom: mark area count and dsf into next level
- End condition: if any 'O' reaches border, mark the global map<count, false>
- keep dfs untill all connected nodes are visited.
- At the end, O(mn) loop over the matrix and mark 'X' for all the true area from map.
- Practice: write code to verify

### BFS
- TODO



---

**1. [200. Number of Islands.java](https://github.com/awangdev/LintCode/blob/master/Java/200.%20Number%20of%20Islands.java)**      Level: Medium      Tags: [BFS, DFS, Matrix DFS, Union Find]
      

给一个2Dmatrix, 里面是1和0, 找#of island.

#### Method1, DFS
- visit all nodes connected with the starting node
    - double for loop, test all starting nodes
    - val == 1: 1) count++; 2)DFS from this (i,j);
    - Mark visited (x,y) = '0'
- time: O(n), visit all nodes
- space: O(n), stack

#### Method2, Union Find
- 可以用union-find， 就像Number of island II 一样.
    - 只不过这个不Return list, 而只是# of islands
    - Union Find is independent from the problem: it models the union status of integers.
    - Return the total # of unions (which is # of islands)
- in reality: it is a bit slow.
- time: visit all nodes just once, O(n). Union Find will visit all nodes once and union them
- space: O(n), union find takes O(n) space
- 记住UnionFind的模板和几个变化(Connecting Graph I, II, III), 最后归总的代码写起来就比较简单. 

#### Method3: BFS
- use queue to hold 1 island, keep adding 4-direction islands; mark visited with '0' 
- check entire board for any remaining one.



---

