E
1517548684

比较升序的时候, 必须要估计到 i-1, i, i+1三个数位.
写出来i-1, i+1之间的关系, 然后做合理的fix.

需要真的fix数组, 因为loop through做比较时会用到fix后的数字.

```
/*
Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.
Note: The n belongs to [1, 10,000].

*/
/*
Thoughts:
loop over all i, and count the exception. If there are more than 2, return false.
Try not be tricked by the condition that nums[i] < nums[i + 1]. 
It actually implies that nums[i - 1] < nums[i] < nums[i + 1], so all 3 numbers need to meet the condition.
If the relationthips between nums[i - 1] && nums[i + 1] is wrong, fix them by chaning one of the 3 numbers:
1. nums[i] > nums[i + 1] && nums[i - 1] > nums[i + 1]: 
    because numbers ahead if i - 1 is correct, so lower the later, nums[i + 1] = nums[i]
2. nums[i] > nums[i + 1] && nums[i - 1] < nums[i + 1]: 
    raise nums[i] since it's already greater than nums[i - 1], nums[i] = nums[i + 1]
3. if i == 0 && nums[i] > nums[i + 1], there is no i-1 to worry about, directly count++;
Note: need to write some examples to figure out the edge case
*/
class Solution {
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length < 0) {
            return false;
        } else if (nums.length <= 2) {
            return true;
        }
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (i >= 1 && nums[i - 1] < nums[i + 1]) {
                    nums[i] = nums[i + 1];
                } else if (i >= 1 && nums[i - 1] > nums[i + 1]) {
                    nums[i + 1] = nums[i];
                }
                count++;
            }
            if (count >= 2) {
                return false;
            }
        }
        return true;
    }
}


```