M
1522856793
tags: DP, Bit Manipulation, Bitwise DP

给一个数组, 算里面有多少bit 1. 

#### Bitwise DP
- 对于每一个数字, 其实很简单就能算出来: 每次 >>1, 然后 & 1 就可以count 1s. Time: 一个数字可以 >>1 O(logN) 次
- 现在要对[0 ~ num] 都计算, 也就是N个数字, 时间复杂度: O(nLogN).
- 用DP来优化, 查找过的number的1s count, 存下来在 dp[number]里面.
- 计算你顺序从 0 -> num, count过的数字就可以重复利用.
- Bit题目 用num的数值本身表示DP的状态.
- 这里, dp[i] 并不是和 dp[i-1]有逻辑关系; 而是dp[i] 和dp[i>>1], 从binary representation看出有直接关系.

```
/*
Given a non negative integer number num. 
For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's 
in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). 
But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
*/

/*
Thoughts:
Just looking at the bit representation:
0: 0000
1: 0001
2: 0010
3: 0011

check 1 and 2: 2 >> 1 becomes 1. '0001' was calculated before, so 2 should use it.
dp[i]: represents num <= i, then how many 1's are there.
dp[i>>1]: represents the binary number has less 1 bit.
dp[i]: 
    - if i's binary has a tailing '1', then dp[i] = dp[i >> 1] + 1
    - if i's binary has a tailing '0', then dp[i] = dp[i >> 1]
Combine:
dp[i] = dp[i >> 1] + i % 2;
Usually use num value itself as DP's status index.
*/
class Solution {
    public int[] countBits(int num) {
        if (num < 0) {
            return null;
        }
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            int prevNum = i >> 1;
            dp[i] = dp[prevNum] + (i % 2);
        }
        return dp;
    }
}

// & 1 will do as well, make sure to parentheses (i & 1)
class Solution {
    public int[] countBits(int num) {
        if (num < 0) {
            return null;
        }
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            int prevNum = i >> 1;
            dp[i] = dp[prevNum] + (i & 1);
        }
        return dp;
    }
}
```