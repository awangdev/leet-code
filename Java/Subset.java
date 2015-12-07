最基本的递归题目。
坑：记得一开头sort一下 nums。 因为要升序。

点：
用level来track到哪一步。最后一level就add into rst
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
    12.06.2015 recap:
*/
class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(rst, list, nums, 0);
        
        return rst;
    }
    public void helper(ArrayList<ArrayList<Integer>> rst, ArrayList<Integer> list,
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



 public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> source) {
        // write your code here
        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> newList = new ArrayList<Integer>();
        Collections.sort(source);
        subsetHelper(0, source, newList, output);
        return output;
    }
    
    public void subsetHelper(int pos, 
            ArrayList<Integer> source, ArrayList<Integer> newList, 
            ArrayList<ArrayList<Integer>> output){
        output.add(new ArrayList<Integer>(newList));
        
        for( int i = pos; i < source.size(); i++){
            newList.add( source.get(i));
            subsetHelper(i+1, source, newList, output);
            newList.remove( newList.size() - 1 );
        }
    }


```