E
1531623482
tags: Bit Manipulation
time: O(n), n = # of bits
space: O(1)

#### Bit Manipulation
- 理解Binary Gap的描述
- 简单的 `>>`, `&1`, track start and end point 就好了

```
/*
Given a positive integer N, find and return the longest distance 
between two consecutive 1's in the binary representation of N.

If there aren't two consecutive 1's, return 0.

 

Example 1:

Input: 22
Output: 2
Explanation: 
22 in binary is 0b10110.
In the binary representation of 22, there are three ones, and two consecutive pairs of 1's.
The first consecutive pair of 1's have distance 2.
The second consecutive pair of 1's have distance 1.
The answer is the largest of these two distances, which is 2.
Example 2:

Input: 5
Output: 2
Explanation: 
5 in binary is 0b101.
Example 3:

Input: 6
Output: 1
Explanation: 
6 in binary is 0b110.
Example 4:

Input: 8
Output: 0
Explanation: 
8 in binary is 0b1000.
There aren't any consecutive pairs of 1's in the binary representation of 8, so we return 0.

Note:

1 <= N <= 10^9
*/



class Solution {
    public int binaryGap(int N) {
        if (N <= 0) return 0;
        // mark start, end, max
        int max = 0, start = -1, end = 0;
        // iterate until N is over
        while (N > 0) {
            if ((N & 1) == 1) {
                if (start == -1) start = end;
                else {
                    max = Math.max(max, end - start);
                    start = end;
                }
            }
            end++;
            N = N >> 1;
        }
        return max;
    }
}
```