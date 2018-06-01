E
1517467099
tags: Array

给串数字, size=2n, 找pairs, 然后需要sum of min(pair) 最大.

(a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

#### Sort, basics
- 从结果出发, 只需要找到加法的结果，而不强调具体配对.
- 写一写example就能做
- 找到排列取单数位的规律，再考虑负数和正数的相同规律，即可找到排列求解的方法。
- sort, O(nlogn)


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

/*
Thoughts: goal is to find the half of the numbers' sum, and always pick the min value of the pair.
Also, need to make the overall sum as large as possible: can't always choose the smallest numbers, but we can choose numbers at ascending order.
1. sort array.
2. only pick the even ones (starting from index 0)
Note:
1. use long to save result: never know what sum can occur in the process.
2. sort the array
O(nlogn)
*/
class Solution {
    public int arrayPairSum(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        Arrays.sort(nums);
        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += i % 2 == 0 ? nums[i] : 0;
        }
        return (int)result;
    }
}
```