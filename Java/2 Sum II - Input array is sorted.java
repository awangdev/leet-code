M
1516439332

排序好的array. Two pointer移动start和end，核查sum.
注意sum用long.

```
/*
Given an array of integers that is already sorted in ascending order, 
find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. 
Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

Tags: Array Two Pointers, Binary Search
Similar Problems: (M) Two Sum

*/


/*
Thoughts:
Two pointer sweeping.
Start, end. Check if nums[start] + nums[end] == target.
*/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return numbers;
        }
        final int[] result = new int[2];
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            long sum = (long)(numbers[start] + numbers[end]);
            if (sum == target) {
                result[0] = start + 1;
                result[1] = end + 1;
                break;
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }//end while
        return result;
    }
}

```