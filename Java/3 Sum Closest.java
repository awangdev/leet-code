M
1516610949

3Sum 的一种简单形式, 并且都没有找index, value, 而只是找个sum罢了.

double for loop。 2Sum只能用土办法 left/right 2 pointers。 O(n^2)

注意：check closest时候用long, 以免int不够用

```
/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
Return the sum of the three integers. 
Note
You may assume that each input would have exactly one solution.
Example
For example, given array S = {-1 2 1 -4}, and target = 1. The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
Tags Expand 
Two Pointers Sort Array

*/

/*
Thoughts:
3 SUM = for loop + 2SUM. Normally it'd be O(n^2).
Two pointer in the inner 2SUM..
Note: result should be initialized with first 3 indexes.
*/
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums); // nLog(n)
        long result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                long sum = nums[start] + nums[end] + nums[i];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                result = Math.abs(sum - target) < Math.abs(result - target) ? sum : result;
            }
        }
        return (int)result;
    }
}



/*
Previous notes
Thoughts:
    Similar to 3 SUM.
    Starting from the left-element, assume it's the solution. Move the 2 pointers in the right-side-array.
    Using the two pointers, trying to find ele1 + ele2 + ele3 = closest number to target.
    Note: for comparing closet, use initial value Integer.MAX_VALUE. Be aware of the overflow of integer, use long to handle.

*/
```