E
1517474043
tags: Array

理解题目: 
1. single-bit always starts with '0', two-bits always start with '1'.
1. Therefore there is ONLY 1 way to reach end.

#### 方法1
Greedy.
从第一个bit开始: 如果 % 2 == 1, 一定是跳两位; 如果0, 一定是跳一位.
loop to end, and see if index reaches the end.

#### 方法2
用DP硬做了一下: 
1. 如果i位是0, 那么前面dp[i-1]或者dp[i-2] true就够了.
2. 如果i位是1, 那么i-1位必须是1才满足规则, 并且 dp[i-2]需要true.

```
/*
We have two special characters. The first character can be represented by one bit 0. The second character can be represented by two bits (10 or 11).

Now given a string represented by several bits. Return whether the last character must be a one-bit character or not. The given string will always end with a zero.

Example 1:
Input: 
bits = [1, 0, 0]
Output: True
Explanation: 
The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.
Example 2:
Input: 
bits = [1, 1, 1, 0]
Output: False
Explanation: 
The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.
Note:

1 <= len(bits) <= 1000.
bits[i] is always 0 or 1.
*/
/*
Thoughts:
Greedy.
从第一个bit开始数: 如果遇到1, 一定是跳两位;如果遇到0, 一定是跳一位.
loop to end, and see if index reaches the end.
*/
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        if (bits == null || bits.length == 0) {
            return false;
        }
        int index = 0;
        while (index < bits.length - 1) {
            index += bits[index] % 2 == 1 ? 2 : 1;
        }
        return index == bits.length - 1;
    }
}

/*
Thoughts:
check if last bit must be '0': check can it be decoded if not single-bit-0 ending?
Should check if bits with less 2 bits still valid?
DP.
dp[i]: can be decoded at i
we only actaully need to know up to dp[i - 1]

if bits[i] == 0: dp[i] = dp[i - 2] || dp[i - 1];
if bits[i] == 1: dp[i] = dp[i - 2] && bits[i - 1] == 1;

*/
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        if (bits == null || bits.length == 0) {
            return false;
        }
        int n = bits.length;
        if (n == 1) {
            return bits[0] == 0;
        }
        if (bits[n - 1] != 0) {
            return false;
        }
        
        boolean[] dp = new boolean[n];
        dp[0] = bits[0] == 0;
        dp[1] = bits[0] != 0 || bits[1] != 1;
        for (int i = 2; i < n - 1; i++) {
            if (bits[i] == 0) {
                dp[i] = dp[i - 2] || dp[i - 1];
            } else if (bits[i] == 1) {
                dp[i] = dp[i - 2] && bits[i - 1] == 1;
            }
        }
        return dp[n - 2];
    }
}
```