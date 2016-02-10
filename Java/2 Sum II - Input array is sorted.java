M

排序好的array. two pointer前后夹击。


```
/*
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

Tags: Array Two Pointers, Binary Search
Similar Problems: (M) Two Sum

*/


/*
Thoughts:
Do a binary search, but do not over-complicate it:
Start, end. Check if nums[start] + nums[end] == target.
binary move it: in fact, moving the two border, 1 position at a time
*/

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] rst = new int[2];
        if (nums == null || nums.length <= 1) {
            return rst;
        }
        int start = 0;
        int end = nums.length - 1;
        while(start < end) {
            long sum = (long)(nums[start] + nums[end]);
            if (target == sum) {
                rst[0] = start + 1;
                rst[1] = end + 1;
                break;
            } else if (target > sum) {
                start++;
            } else {
                end--;
            }
        }//END while
        return rst;
    }
}

```