E

Basic. Math.max track结果。
记得在有对外操作的loop后，一定要把result object清理干净。

```
/*
Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
*/

/*
Thoughts:
Maintain a min outsize, loop through all numbers.

*/

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                maxCount = Math.max(count, maxCount);
                count = 0;
            } else {
                count++;
            }
        }
        return Math.max(count, maxCount);
    }
}

```