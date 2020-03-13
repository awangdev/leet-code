M
tags: BFS, Graph
time: O(n)
space: O(n)

### Method1: BFS
- Find possibility to reach certain point, we can BFS: faster to find shortest candidate
- use queue to hold left, right candidates
- use set to record visited

### Method2: DFS
- attemp all nodes, use set to record visited.
- time: O(n)
- space: O(n)

```
/*

Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
 

Constraints:

1 <= arr.length <= 5 * 10^4
0 <= arr[i] < arr.length
0 <= start < arr.length


*/


/*
Method1: BFS:use queue to hold left, right candidates
*/
class Solution {
    
    public boolean canReach(int[] arr, int start) {
        
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        add(queue, arr, start);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            while (size-- > 0) {
                int index = queue.poll();
                if (!visited.add(index)) continue; // visited before
                if (arr[index] == 0) return true;
                add(queue, arr, index);
            }
        }
        return false;
    }
    
    private void add(Queue<Integer> queue, int[] arr, int start) {
        int left = start - arr[start], right = start + arr[start];
        if (left >= 0) queue.offer(left);
        if (right < arr.length) queue.offer(right);
    }
}

// Method2: DFS.
class Solution {
    public boolean canReach(int[] arr, int start) {
        return dfs(arr, new HashSet<>(), start);
    }
    
    private boolean dfs(int[] arr, Set<Integer> visited, int index) {
        if (arr[index] == 0) return true;
        if (!visited.add(index)) return false;
        if (visited.size() == arr.length) return false;
        
        int left = index - arr[index], right = index + arr[index];
        
        return (left >= 0 && dfs(arr, visited, left))
            || (right < arr.length && dfs(arr, visited, right));
    }
}
```