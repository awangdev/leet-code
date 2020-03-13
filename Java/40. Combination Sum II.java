M
tags: Array, DFS, Backtracking, Combination
time: O(k * 2^n), k = avg rst length
space: O(n) stack depth, if not counting result size

给一串数字candidates (can have duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 只可以用一次.

#### DFS, Backtracking
- when the input has duplicates, and want to skip redundant items? 考虑重复使用的规则: 不可以重复使用
    - 1. sort.  考虑input: 有duplicate, 必须sort
    - 2. in for loop, skip same neighbor: `if (i > index && candidates[i] == candidates[i - 1]) continue;`
        - 因为在同一个level里面重复的数字在下一个dfs level里面是会被考虑到的, 这里必须skip
- the result is trivial, save success list into result.
- Time complexity: O(k * 2^n), k = average result length
    - 1) Assume on average, there are k elements in result 
    - 2) Since element can be used ONLY once, so the total # of solutions can be `C(n, k)`: `pick k out of n`
    - 3) Now let k be any number [0, n], so total # of solutions can be: `C(n,0) + C(n,1) + C(n,2) + ... C(n,n) = 2^n`
    - 4) Now: `the new ArrayList<>(list)` takes average O(k) time
    - Total: O(k * 2^n)
- Space: O(n), stack depth, if not counting results size

```
/*
Given a collection of candidate numbers (candidates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]


 */

/*
- one item can be picked once. the input candidates may have duplicates.
- IMPORTANT: 1. sort.  2. Skip adjacent item in for loop
- use dfs, for loop to aggregate candidates
- do target - val to track, and when target == 0, that’s a solution
- dfs(curr index i), instead of (i + 1): allows reuse of item

*/
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (validate(candidates, target)) return result;

        Arrays.sort(candidates); // critical to skip duplicates
        dfs(result, new ArrayList<>(), candidates, 0, target);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> list,
                     int[] candidates, int index, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        // for loop, where dfs is performed
        for (int i = index; i < candidates.length; i++) {
            // ensures at same for loop round, the same item (sorted && neighbor) won't be picked 2 times
            if (i > index && candidates[i] == candidates[i - 1]) continue;

            int value = candidates[i];
            list.add(value);
            if (target - value >= 0) dfs(result, list, candidates, i + 1, target - value);
            list.remove(list.size() - 1); // backtrack
        }
    }

    private boolean validate(int[] candidates, int target) {
        return candidates == null || candidates.length == 0 || target <= 0;
    }
}



class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (validate(candidates, target)) return result;

        Arrays.sort(candidates); // critical to skip duplicates
        dfs(result, new ArrayList<>(), candidates, 0, target);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> list,
                     int[] candidates, int index, int target) {
        // for loop, where dfs is performed
        for (int i = index; i < candidates.length; i++) {
            // ensures at same for loop round, the same item (sorted && neighbor) won't be picked 2 times
            if (i > index && candidates[i] == candidates[i - 1]) continue;

            int value = candidates[i];
            list.add(value);
            if (target == value) result.add(new ArrayList<>(list));
            else if (target - value > 0) dfs(result, list, candidates, i + 1, target - value);
            list.remove(list.size() - 1); // backtrack
        }
    }

    private boolean validate(int[] candidates, int target) {
        return candidates == null || candidates.length == 0 || target <= 0;
    }
}




```