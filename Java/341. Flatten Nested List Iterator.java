M
tags: Stack, Design, NestedInteger
time: O(n)
space: O(n)

#### Method1: Stack holds items from back of the list
- Option1: always set integer on top of the stack everywhere
    - if not, poping stack until the top is integer
    - code is easy
- Option2: in hasNext(), faltten the list in stack

#### Method2: DFS preprocessing.
- 用queue to store all items. Kinda hack. Defeat the purpose of the problem.
- Super fast to query next(), however, needs to holds everything in memory
- O(n)

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
Method1, Stack, Option1:
- Stack holds items from back of the list
- when popping, keep going until: 1: the processing list is exhausted, 2) the stack.peek() is a integer => return
*/
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack = new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        addListToStack(nestedList);
        flattenStackTop();
    }
    
    // Stack store list (from back to front)
    private void addListToStack(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }
    
    // Exhaust stack untill there is no list on top; it can reduce stack to empty
    private void flattenStackTop() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            addListToStack(stack.pop().getList());
        }
    }

    @Override
    public Integer next() {
        Integer num = stack.pop().getInteger();
        flattenStackTop(); // clean up stack to make sure top is integer
        return num;
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

 /*
Method1, Stack, Option2:
Store the NestedInteger object in stack.
Note: hasNext() should make sure the next value is integer, instead of array, or empty array.
Therefore, hasNext() will maintain the stack

*/
public class NestedIterator implements Iterator<Integer> {
    final Stack<NestedInteger> stack = new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        addListToStack(nestedList)
    }

    // Stack store list (from back to front)
    private void addListToStack(List<NestedInteger> nestedList) {
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
        while (!stack.isEmpty()) { // keep working on stack until the top is integer
            NestedInteger nestedInteger = stack.peek();
            if (nestedInteger.isInteger()) return true;
            // nestedInteger is list
            stack.pop();
            // flatten the list and add back to stack
            addListToStack(nestedInteger.getList())
        }
        return false;
    }
}



/*
Method2. Flatten out first, kinda hack, but fast when next(), hasNext().
Use queue to hold all items from the nested list. 
There can be list of list at many levels, so we should construct DFS to hold all the values
Space: O(n), 
Time:
O(n) initialization
O(1) next(), hasNext();
*/
public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> queue = new LinkedList<Integer>();
    
    public NestedIterator(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) return;
        dfs(nestedList);
    }
    
    private void dfs(List<NestedInteger> list) {
        for (NestedInteger nestedInteger : list) {
            if (nestedInteger.isInteger()) queue.offer(nestedInteger.getInteger());
            dfs(nestedInteger.getList());
        }
    }

    @Override
    public Integer next() {
        return !queue.isEmpty() ? queue.poll(): null;
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