E
Same as https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum.java

```
/*
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

Array, Hash Table
Similar Problems: (M) 3Sum, (M) 4Sum (M), Two Sum II - Input array is sorted, (E) Two Sum III - Data structure design

*/

/*
Thought:
Hash added value <num value, index>.
Check remaining value == target ?
*/
public class Solution {
    public int[] twoSum(int[] nums, int target) {
    	int[] rst = new int[2];
        if (nums == null || nums.length <= 1) {
        	return rst;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
        	int remaining = target - nums[i];
        	if (!map.containsKey(remaining)) {
        		map.put(nums[i], i);
        	} else {
        		rst[0] = map.get(remaining) + 1;
        		rst[1] = i + 1;
        		break;
        	}
        }
        return rst;
    }
}
```