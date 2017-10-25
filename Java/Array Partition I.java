E

从结果出发, 只需要找到加法的结果，而不强调具体配对。找到排列取单数位的规律，再考虑负数和正数的相同规律，即可找到排列求解的方法。


```
/*
Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Example 1:
Input: [1,4,3,2]

Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
Note:
n is a positive integer, which is in the range of [1, 10000].
All the integers in the array will be in the range of [-10000, 10000].
*/

class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                result += nums[i];
            }
        }
        return result;
    }
}
```