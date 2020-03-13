M
tags: DFS, NestedInteger
time: O(n), visit all nodes
space: O(h), depth

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

```
/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 8 
Explanation: Four 1's at depth 1, one 2 at depth 2.
Example 2:

Input: [1,[4,[6]]]
Output: 17 
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
*/


/*
- Build a list of NestedInt
- End state: sum up only when all items in the list are integers (no more child dfs), return depth 1
- Parent level: sum up all ints and times the (depth+1)
*/
class Solution {
    int overallSum = 0;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        dfs(nestedList);
        return overallSum;
    }
    
    public int dfs(List<NestedInteger> nestedList) {
        List<NestedInteger> list = new ArrayList<>();
        int sum = 0;
        for (NestedInteger nestedInt : nestedList) {
            if (nestedInt.isInteger()) sum += nestedInt.getInteger();
            else list.addAll(nestedInt.getList());
        }
        if (list.isEmpty()) {
            overallSum += sum;
            return 1;
        };
        
        int depth = dfs(list) + 1;
        overallSum += sum * depth;
        return depth;
    }
}

// BFS, using stack
/*
- Use stack to flatten entire nested structure: build higher stack based on NestedIntegerList from bottom level
- When process: only process the ones that are integer (skip list since they are processed)
*/
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Stack<List<NestedInteger>> stack = new Stack<>();
        stack.push(nestedList);
        while (true) {
            List<NestedInteger> list = stack.peek();
            List<NestedInteger> newList = new ArrayList<>();
            for (NestedInteger item : list) {
                if (!item.isInteger()) newList.addAll(item.getList());
            }
            if (newList.isEmpty()) break;
            stack.push(newList);
        }
        
        // process
        int sum = 0, level = 1;
        while (!stack.isEmpty()) {
            List<NestedInteger> list = stack.pop();
            for (NestedInteger item : list) {
                if (item.isInteger()) sum += level * item.getInteger();
            }
            level++;
        }
        return sum;   
    }
}

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
```