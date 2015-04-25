/*
Given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to set all bits between i and j in N equal to M (e g , M becomes a substring of N located at i and starting at j)   



Example
Given N = (10000000000)2, M = (10101)2, i = 2, j = 6

return N = (10001010100)2

Challenge
Minimum number of operations？

Tags Expand 
Cracking The Coding Interview Bit Manipulation Binary Representation

Thinking process:
Create a mask: xxxx000000xxxx.
Trick part: when it encounters negative number or dealing with index at edge index = 31, it starts having issue. Interesting fix: use long for masks.
*/

class Solution {
    /**
     *@param n, m: Two integer
     *@param i, j: Two bit positions
     *return: An integer
     */
    public int updateBits(int n, int m, int i, int j) {
        //Create mask: xxx00000xxx
        long rightMask = ~0 >> i;
        rightMask = ~(rightMask << i);// 00000xxx
        long leftMask = ~0 >> (j + 1);
        leftMask = leftMask << (j + 1);//xxxxx00000000
        long mask = leftMask | rightMask;//xxx00000xxx
        n = (int) (n & mask);
        n = (int) (n | (m << i));
        return n;
    }
}


