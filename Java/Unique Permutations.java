/*
Given a list of numbers with duplicate number in it. Find all unique permutations.

Example
For numbers [1,2,2] the unique permutations are:

[

    [1,2,2],

    [2,1,2],

    [2,2,1]

]

Challenge
Do it without recursion.

Tags Expand 
Recursion

Thougths:
Use regular recursion, use a mark[] to make sure the same charactor at same postion won't be reused 
Do a backtrack on the dfs, to make sure a element has same chance of 'selectd' or 'non-solectd'
*/
class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
    	ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
    	if (nums == null || nums.size() == 0) {
    		return rst;
    	}
    	Collections.sort(nums);
    	boolean[] mark = new boolean[nums.size()];
    	dfs(nums, new ArrayList<Integer>(), rst, mark);
    	return rst;
    }

    public void dfs (ArrayList<Integer> nums, ArrayList<Integer> list,
    					ArrayList<ArrayList<Integer>> rst, boolean[] mark) {
    	if (list.size() == nums.size()) {
    		rst.add(new ArrayList<Integer>(list));
    		return;
    	}

    	for (int i = 0; i < nums.size(); i++) {
			if (mark[i] || (i != 0 && mark[i - 1] && nums.get(i) == nums.get(i - 1))) {
				continue;
			}    		
			list.add(nums.get(i));
			mark[i] = true;
			dfs(nums, list, rst, mark);
			list.remove(list.size() - 1);
			mark[i] = false;
    	}
    	return;
    }
}



