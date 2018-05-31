E
1526012135
tags: Array, Math, Bit Manipulation

给一串unique数字, 数字取自 [0 ~ n], 无序, 找第一个skipped的数字.

#### Swap 
- 跟First Missing Positive 非常像, 只有一行代码的区别.
- swap 所有的数字, 到自己的correct position
- 最后一个for loop找到错位的index, 也就是缺的数字.

#### Bit Manipulation
- XOR will only retain bits that are different 1 ^ 0 = 1, but 0^0, 1^1 == 0
- Use that feature, 把所有value都和index XOR了
- 剩下的多余的数字, 其实是那个index无法被XOR消掉, 也就是那个缺的number value.
- 注意: 题目告诉数字是 [0 ~ n], 然而缺一个数字, 那么在[0 ~ n - 1] 里面, 最大的数字(不管缺没缺), 一定是 n = nums.length.

#### HastSet
- 全存, 找missing
- O(n) space, 不合题意

#### sorting
- sort, 找1st missing
- O(n log n) 太慢, 不合题意

```
/*

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, 
find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. 
Could you implement it using only constant extra space complexity?
*/

public class Solution {
    public int missingNumber(int[] nums) {
        // check input
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;

        // 1st loop, swap to correct location
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            while (val != i && val < n && val != nums[val]) { // val != nums[val], avoid infinitely loop
                swap(nums, val, i);
                val = nums[i];
            }
        }

        // 2nd loop, find 1st missing
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        return n;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}


public class Solution {
    public int missingNumber(int[] nums) {
        // check input
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // 1st loop, swap to correct location
        int i = 0;
        while (i < n) {
            int val = nums[i];
            if (val != i && val < n && val != nums[val]) { // val != nums[val], avoid infinitely loop
                swap(nums, val, i);
            } else {
                i++;
            }
        }

        // 2nd loop, find 1st missing
        for (i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        return n;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}

// Bit manipulation
class Solution {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
	        return 0;
        }
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i] ^ i;
        }
        return result;
    }
}



```