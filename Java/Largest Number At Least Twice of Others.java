E
1517560733

找最大值, 和第二大的值, 看是否符合题意, 就行了.
分析题意, 最简单方法, 可以loop 两遍: 找最值; 作比较.
但其实, 举个反例: 有一个不满足, 就够反对这个'at least twice of alllll others'.

```
/*
In a given integer array nums, there is always exactly one largest element.

Find whether the largest element in the array is at least twice as much as every other number in the array.

If it is, return the index of the largest element, otherwise return -1.

Example 1:

Input: nums = [3, 6, 1, 0]
Output: 1
Explanation: 6 is the largest integer, and for every other number in the array x,
6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
 

Example 2:

Input: nums = [1, 2, 3, 4]
Output: -1
Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
 

Note:

nums will have a length in the range [1, 50].
Every nums[i] will be an integer in the range [0, 99].
*/

/*
Thoughts:
1. find max
2. validate if max>2x of the rest?

Only need to pick one exception to break it: so find the max, and leastMax.
Do it in O(n) one time.
*/
class Solution {
    public int dominantIndex(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int max = -1;
        int leastMax = -1;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= max) {
                leastMax = max;
                max = nums[i];
                index = i;
            } else {
                leastMax = Math.max(leastMax, nums[i]);
            }
        }
        return max >= leastMax * 2 ? index : -1;
    }
}
```