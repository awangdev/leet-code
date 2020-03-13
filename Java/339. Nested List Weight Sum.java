E
tags: DFS, BFS, NestedInteger
time: O(n)
space: O(h), h = levels

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

```
/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

Example 2:
Given the list [1,[4,[6]]], return 27. 
(one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)

*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
/*
Thoughts:
Helper function (NestedInteger, depth).
Handle Integer, List - for loop
*/
class Solution {
    public int depthSum(List<NestedInteger> nestedList) { //[[1,1],2,[1,1]]
        int sum = 0;
        for (NestedInteger childInt: nestedList) {
            sum += dfs(childInt, 1);
        }
        return sum;
    }
    
    public int dfs(NestedInteger nestInt, int depth) {
        if (nestInt.isInteger()) return nestInt.getInteger() * depth;
        int sum = 0;
        for (NestedInteger childInt: nestInt.getList()) {
            sum += dfs(childInt, depth + 1);
        }
        return sum;
    }
}

/*
Thoughts:
Iteratively, breadth-first search
Use queue, track level, handle all available integers at each level, then append to the queue.
*/
class Solution {
    public int depthSum(List<NestedInteger> nestedList) { //[[1,1],2,[1,1]]
        Queue<NestedInteger> queue = new LinkedList<>();
        addToQueue(queue, nestedList);
        
        int level = 1, sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger childInt = queue.poll();
                if (childInt.isInteger()) sum += childInt.getInteger() * level;
                else addToQueue(queue, childInt.getList());
            }
            level++;
        }
        return sum;
    }
    
    private void addToQueue(Queue<NestedInteger> queue, List<NestedInteger> nestedList) {
        for (NestedInteger childInt: nestedList) {
            queue.offer(childInt);
        }
    }
}
```
