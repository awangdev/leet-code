E

Bit Manipulation

a^b 显示出bit format里面有不同binary code的数位.

每次 (a^b)>>i 移动i位之后，   再 & 1时其实是指留下这一位的数字.

count it up

```
/*
Determine the number of bits required to convert integer A to integer B 

Example
Given n = 31, m = 14,return 2

(31)10=(11111)2

(14)10=(01110)2

Tags Expand 
Cracking The Coding Interview Bit Manipulation Binary Representation

Thinking process:
Assume the integer is 32 bit.
XOR a and b, shift by 1 bit everytime -> want to check the XORed value at index 0 : just & 1 will do.
Count the above calculated result: how many bit difference do a and b have.
*/

class Solution {
    /**
     *@param a, b: Two integer
     *return: An integer
     */
    public static int bitSwapRequired(int a, int b) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (a ^ b) >> i & 1;
        }
        return count;
    }
};



```