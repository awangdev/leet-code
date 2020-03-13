M
1531598157
tags: Bit Manipulation
time: O(n)
space: O(1), 32-bit array

给出Hamming Distance定义(bit format时候有多少binary diff), 求一串数字的hamming distance总和.

#### Bit Manipulation
- Bit题: 考验 bit >>, mask & 1, 还有对题目的理解能力
- Put integers in binary, and compare each column:
- for each `1`, ask: how many are different from me? all the `0`
- `# of diffs at each bit-column = #ofZero * #ofOne `
- 1. countZero[], countOne[]; 2. loop over nums and populate the two array

##### 注意雷点
- 问清楚: 10^9 < 2^31, we are okay with 32 bits
- `最终的hamming distance 要从 [1 ~ 32] 哪个bit开始算起`? 取决于 `最长`的那个binary format: 但不用先去找bit length
- 在做countZero, countOne时候, 都做32-bit; 最终做乘积的时候, 如果 `1` 或者 `0` 个数为零, 乘积自然为0.


```
/*
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2

Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
Note:
Elements of the given array are in the range of 0 to 10^9
Length of the array will not exceed 10^4.
 */

// bit manipulation
class Solution {
    public int totalHammingDistance(int[] nums) {
        int rst = 0;
        if (nums == null || nums.length == 0) return rst; // check input
        
        // populate over all nums
        int[] countZero = new int[32], countOne = new int[32];
        for (int num : nums) populateBinaryCount(countZero, countOne, num);
        
        // calc final result
        for (int i = 0; i < 32; i++) rst += countZero[i] * countOne[i];
        
        return rst;
    }
    
    private void populateBinaryCount(int[] countZero, int[] countOne, int num) {
        for (int i = 0; i < 32; i++){
            if ((num & 1) == 1) countOne[i]++;
            else countZero[i]++;
            num = (num >> 1);
        }
    }
}
```