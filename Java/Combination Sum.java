M

递归，backtracking. 非常normal。需要先sort.    
记得求sum时候也pass 一个sum进去，backtracking一下sum也，这样就不必每次都sum the list了。   

题目里面所同一个元素可以用n次，但是，同一种solution不能重复出现。如何handle?

1. 用一个index （我们这里用了start）来mark每次recursive的起始点。
2. 每个recursive都从for loop里面的i开始，而i = start。 也就是，下一个iteration,这个数字会有机会被重复使用。
3. 同时，确定在同一个for loop里面，不同的Index上面相同的数字，不Process两遍。用一个prev 作为checker.

假如[x1, x2, y, z], where x1 == x2， 上面做法的效果: 
我们可能有这样的结果: x1,x1,x1,y,z    
但是不会有:x1,x2,x2,y,z   
两个solution从数字上是一样的，也就是duplicated solution, 要杜绝第二种。

```
/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.



For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 

Note
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
Example
given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 

Tags Expand 
Backtracking Array

Thinking process:
Similar to 'Combination' problem, do back-tracking with ability to repeat itself at index i.
In order to stop duplicates of result entry, use a 'prev' tracker to 'continue' if a value is repeating at any index. Skip repeating integers because we've already allow unlimited # of same integer in one single solution. (IMPORTANT: will have to sort the int[] in order to detect the duplicates)
In particular, I pass a 'sum' to compare with 'target' (want to have sum == target). Some solution prefer to use 'target - someVlaue' == 0 to find solution.
*/

public class Solution {
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] num, int target) {
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
            helper(rst, list, num, target, sum, i);
            //Back track:
            sum -= num[i];
            list.remove(list.size() - 1);
            //Repeat Detection
            prev = num[i];
        }
    }
}


```