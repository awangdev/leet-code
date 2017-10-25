M

Combination DFS。 画个图想想. 每次从1~n里面pick一个数字i

因为下一层不能重新回去 [0~i]选，所以下一层recursive要从i+1开始选。

```
/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example
For example,
If n = 4 and k = 2, a solution is:
[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
Tags Expand 
Backtracking Array

Thinking process:
Use a helper method to perform recursive backtracking:add an element to next-level recursive call, and remote the entry after the recursive call.
Note: When 'new' something, cannot use 'List' because it's a abstract class. Need to new 'ArrayList'
*/

public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (n <= 0 || k <= 0) {
            return rst;
        }
        List<Integer> solution = new ArrayList<Integer>();
        helper(rst, solution, n, k, 1);// Start == 1 because we want 1 ~ n in this problem
        return rst;
    }
    public void helper(List<List<Integer>> rst, 
                List<Integer> solution, int n, int k, int start) {
        if (solution.size() == k) {
            rst.add(new ArrayList(solution));
            return;
        }        
        for (int i = start; i <= n; i++) {// <=n because we want 1 ~ n in this problem
            solution.add(i);
            helper(rst, solution, n, k, i + 1);
            solution.remove(solution.size() - 1); //Back-track
        }
    }
}


```