M
1518666697

方法1: 用queue, 把需要的item全部打出来
方法2: 用stack, 把需要的item先存一行, 每次打开子序列时候, 全部加回stack.

```
/*
Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, 
the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, 
the order of elements returned by next should be: [1,4,6].
*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

 /*
Thoughts:
Store the NestedInteger object in stack.
Note: hasNext() should make sure the next value is integer, instead of array, or empty array.
Therefore, hasNext() will maintain the stack

*/
public class NestedIterator implements Iterator<Integer> {
    final Stack<NestedInteger> stack = new Stack<>();
    
    public NestedIterator(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return;
        }
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return hasNext() ? stack.pop().getInteger() : null;
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger nestedInteger = stack.peek();
            if (nestedInteger.isInteger()) {
                return true;
            }
            
            // nestedInteger is list
            stack.pop();
            // push back into stack
            for (int i = nestedInteger.getList().size() - 1; i >= 0; i--) {
                stack.push(nestedInteger.getList().get(i));
            }
        }
        return false;
    }
}



/*
Thoughts:
Use queue to hold all items from the nested list. 
There can be list of list at many levels, so we should construct DFS to hold all the values
Space: O(n), 
Time:
O(n) initialization
O(1) next(), hasNext();
*/
public class NestedIterator implements Iterator<Integer> {
    final Queue<Integer> queue = new LinkedList<Integer>();
    
    public NestedIterator(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return;
        }
        dfs(nestedList);
    }
    
    private void dfs(List<NestedInteger> list) {
        for (NestedInteger nestedInteger : list) {
            if (nestedInteger.isInteger()) {
                queue.offer(nestedInteger.getInteger());
            } else {
                dfs(nestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        if (!queue.isEmpty()) {
            return queue.poll();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();   
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
```