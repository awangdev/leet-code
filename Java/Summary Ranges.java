M

```
/*
Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

Tags: Array
Similar Problems: (M) Missing Ranges


*/
/*
Thoughts: basic implementation, use a arraylist to catch candidates. 
Detect condition, and return results.
*/
public class Solution {
    public List<String> summaryRanges(int[] nums) {
     List<String> rst = new ArrayList<String>();
     if (nums == null || nums.length == 0) {
     	return rst;
     }   
     ArrayList<Integer> list = new ArrayList<Integer>();
     for (int i = 0; i < nums.length; i++) {
     	list.add(nums[i]);
     	if (i + 1 == nums.length || nums[i] + 1 != nums[i + 1]) {
     		if (list.size() == 1) {
     			rst.add(list.get(0) + "");
     		} else {
     			rst.add(list.get(0) + "->" + list.get(list.size() - 1));
     		}
     		list = new ArrayList<Integer>();
     	}
     }
     return rst;
    }
}

//O(n)
```