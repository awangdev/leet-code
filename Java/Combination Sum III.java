M
1526760084
tags: Array, DFS, Backtracking, Combination

给一个integer k, 和一个target n. 

从positive数字[1 ~ 9], 找到所有unique的 组合(combination) int[], size = k, 要求每个combination的和 = n.

(隐藏条件, 需要clarify): 同一个candidate integer [1 ~ 9], 只可以用一次.

#### DFS, Backtracking
- 跟Combination Sum I, II 没什么太大区别, 只不过, 一定要用k个数字, 也就是一个for loop里面的特别条件
- 考虑input: 没有重复数字 [1 ~ 9]
- 考虑candidate重复利用: 不可以重复利用, next level dfs 时候, curr index + 1
- the result is trivial, save success list into result.
- worst case: tried all numbers and cannot find: O(m!), m = 9, all possible integers in [1~9]

```
/*
Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]

*/

/*
- only 1-9 can be used. can use each number only once.
- find k numbers to sum up to n
- each solution must be unique.
- dfs, for loop, each dfs, (i+1) to next level
- decreasing n in each dfs, when n == i, return.
- be careful with the case n - i < 0, no need to dfs
- dfs: result, list, index, k, n
*/

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        // init result, check edge case
        if (k <= 0 || n <= 0) {
            return result;
        }

        // dfs
        dfs(result, new ArrayList<>(), 1, k, n);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> list,
                     int index, int k, int n) {
        // for loop
        for (int i = index; i <= 9; i++) {
            list.add(i);
            if (n == i && list.size() == k) { // found a success solution
                result.add(new ArrayList<>(list));
            } else if (n > i){
                dfs(result, list, i + 1, k, n - i);
            }
            list.remove(list.size() - 1);
        }
    }
}


```