M
tags: Array

#### Basic Implementation
- O(n)
- 两个pointer， 每次计较prev和curr之间的部分.
- 然后prev = curr，向前移动一格
- TODO: check the edge case and make sure max/min of int are checked

```
/*
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].

Tags: Array
Similar Problems: (E) Summary Ranges


*/
/*

Attempt2, Thoughts:
Use two pointer to mark the prev and curr value, then verify the range in between.

matching conditoin: prev +2 >= curr.
That is,
1,...,3

1. When print range: print the missing [x,y]
2. missing x = prev+1, missing y = curr - 1;
3. Make sure prev represents the consecutive integer before missing x. 
*/

public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> rst = new ArrayList<String>();
        if (nums == null || nums.length == 0) {//Though, also covered in the for
        	rst.add(printRange(lower, upper));
        	return rst;
        } else if (lower > upper) {
        	return rst;
        }
        int prev = lower - 1;
        int curr;
        for (int i = 0; i <= nums.length; i++) {
        	curr = (i == nums.length) ? upper + 1 : nums[i];
        	if (prev + 2 <= curr) {
        		rst.add(printRange(prev + 1, curr - 1));
        	}
        	prev = curr;
        }
        return rst;
    }

    public String printRange(int from, int to) {
    	return (from == to) ? String.valueOf(from) : from + "->" + to;
    }
}


```