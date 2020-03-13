 
 
 
## NestedInteger (3)
**0. [339. Nested List Weight Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/339.%20Nested%20List%20Weight%20Sum.java)**      Level: Easy      Tags: [BFS, DFS, NestedInteger]
      

给一串integers, list里面可能有nest list. 算总的sum. 规则, 如果是nested list, 每深一个depth, sum要乘以depth.

#### DFS
- New interface to understand: object contains integer or object
    - Visit all && sum, consider dfs.
    - 简单的处理nested structure, dfs增加depth.
- time: visit all nodes eventually, O(n), space O(n)

#### BFS
- bfs, queue, 处理queue.size() for a level
- use a level variable to track levels
- slower since it uses extra space, worst case O(n) of all items



---

**1. [364. Nested List Weight Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/364.%20Nested%20List%20Weight%20Sum%20II.java)**      Level: Medium      Tags: [DFS, NestedInteger]
      

#### Method1: DFS
- Build a list of NestedInt
- DFS:
  - sum up integers in the list are integers
  - dfs on nested list
  - overallSum = sum * (depth+1)
  - End state: if no nested list (no more child dfs), return depth 1
- Parent level: sum up all ints and times the (depth+1)


#### Method2: BFS
- Using stack to flatten all nestedList, and process in the end
- Can actually use list, does not need to be stack.
- uses more memory



---

**2. [341. Flatten Nested List Iterator.java](https://github.com/awangdev/LintCode/blob/master/Java/341.%20Flatten%20Nested%20List%20Iterator.java)**      Level: Medium      Tags: [Design, NestedInteger, Stack]
      

#### Method1: Stack holds items from back of the list
- Option1: always set integer on top of the stack everywhere
    - if not, poping stack until the top is integer
    - code is easy
- Option2: in hasNext(), faltten the list in stack

#### Method2: DFS preprocessing.
- 用queue to store all items. Kinda hack. Defeat the purpose of the problem.
- Super fast to query next(), however, needs to holds everything in memory
- O(n)



---

