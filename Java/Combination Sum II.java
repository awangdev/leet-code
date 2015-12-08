确保Helper是用i+1，下一层的数字。

```
/*
Given a collection of candidate numbers (C) and a target number (T), 
find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
Example
For example, given candidate set 10,1,6,7,2,1,5 and target 8,

A solution set is: 

[1,7]

[1,2,5]

[2,6]

[1,1,6]

Tags Expand 
Backtracking Array

Thinking process:
Exact same idea as in Combination Sum I. The difference is, 
cannot reuse the current index in nums. Instead, in helper() function, use index of i + 1
*/
public class Solution {
    /**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        if (num == null || num.length == 0 || target < 0) {
            return rst;
        }
        Arrays.sort(num);
        helper(rst, list, num, target, 0, 0);
        return rst;
    }
    public void helper(List<List<Integer>> rst, List<Integer> list,
                int[] num, int target, int sum, int start) {
        if (sum == target) {
            rst.add(new ArrayList(list));
            return;
        } else if (sum > target) {//Stop if greater than target
            return;
        }
        int prev = -1;//Repeat detection
        for (int i = start; i < num.length; i++) {
            if (prev != -1 && prev == num[i]) {
                continue;
            }
            list.add(num[i]);
            sum += num[i];
            helper(rst, list, num, target, sum, i + 1);
            //Back track:
            sum -= num[i];
            list.remove(list.size() - 1);
            //Repeat Detection
            prev = num[i];
        }
    }
}


```