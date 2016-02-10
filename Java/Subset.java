M

最基本的递归题目。   
坑：记得一开头sort一下 nums。 因为要升序。

注意：用level/index来track到哪一步。最后一level就add into rst

方法1: subset的概念，取或者不取,backtracking. 当level/index到底，return 一个list.

方法2: 用for loop backtracking. 记得：每个dfs recursive call是一种独特可能，先加进rst。

```
/*
Given a set of distinct integers, return all possible subsets.

Example
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
Note
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
Challenge
Can you do it in both recursively and iteratively?

Tags Expand 
Recursion Facebook Uber
*/

/*
    12.06.2015 recap.
    Choose or not choose
    When level == nums.length, return a entry.
*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(rst, list, nums, 0);
        
        return rst;
    }
    public void helper(List<List<Integer>> rst, ArrayList<Integer> list,
                int[] nums, int level) {
        if (level == nums.length) {
            rst.add(new ArrayList<Integer>(list));
            return;
        }
        //pick curr num
        list.add(nums[level]);
        helper(rst, list, nums, level + 1);
        list.remove(list.size() - 1);
        
        //not pick curr num
        helper(rst, list, nums, level + 1);
    }
}


//Back tracking, DFS, recursive: recursive call of (rst, list, nums, index). 
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<Integer>();
        dfs(rst, list, nums, 0);
        return rst;
    }
    
    public void dfs(List<List<Integer>> rst, ArrayList<Integer> list, int[] nums, int index){
        rst.add(new ArrayList<>(list));
        
        for( int i = index; i < nums.length; i++){
            list.add(nums[i]);
            dfs(rst, list, nums, i+1);
            list.remove(list.size() - 1);
        }
    }
}

```