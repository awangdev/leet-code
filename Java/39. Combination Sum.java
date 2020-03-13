M
tags: Array, DFS, Backtracking, Combination
time: O(k * 2^n), k = avg rst length
space: O(k) stack depth, if not counting result size

给一串数字candidates (no duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 可以用任意多次.


#### DFS, Backtracking
- 考虑input: 没有duplicate, 不需要sort
- 考虑重复使用的规则: 可以重复使用, 那么for loop里面dfs的时候, 使用curr index i
- the result is trivial, save success list into result.
- Time and Space complexity:
    - transform the analysis as for `40. Combination Sum II`
    - Since this problem allows reuse of elemenets, assume they exist in original input as duplicates
    - time: O(k * 2^n), k = avg rst length
    - space: O(k) stack depth, if not counting result size

```
/**
Given a set of candidate numbers (candidates) (without duplicates) 
and a target number (target), find all unique combinations in candidates 
where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

 */

/*
- one item can be picked for multiple times:
- use dfs, for loop to aggregate candidates
- do target - val to track, and when target == 0, that’s a solution
- dfs(curr index i), instead of (i + 1): allows reuse of item

*/
// Method1: DFS, build result as end state

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (validate(candidates, target)) return result;

        dfs(result, new ArrayList<>(), candidates, 0, target);
        return result;
    }

    // for loop, where dfs is performed
    private void dfs(List<List<Integer>> result, List<Integer> list,
                     int[] candidates, int index, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int value = candidates[i];
            list.add(value);
            if (target - value >= 0) dfs(result, list, candidates, i, target - value);
            list.remove(list.size() - 1); // backtrack
        }
    }

    private boolean validate(int[] candidates, int target) {
        return candidates == null || candidates.length == 0 || target <= 0;
    }
}


// Method2: DFS, build result in for loop
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (validate(candidates, target)) return result;

        dfs(result, new ArrayList<>(), candidates, 0, target);
        return result;
    }

    // for loop, where dfs is performed
    private void dfs(List<List<Integer>> result, List<Integer> list,
                     int[] candidates, int index, int target) {
        for (int i = index; i < candidates.length; i++) {
            int value = candidates[i];
            list.add(value);
            if (target == value) result.add(new ArrayList<>(list)); // one closure
            else if (target - value > 0) dfs(result, list, candidates, i, target - value);
            list.remove(list.size() - 1); // backtrack
        }
    }

    private boolean validate(int[] candidates, int target) {
        return candidates == null || candidates.length == 0 || target <= 0;
    }
}


```