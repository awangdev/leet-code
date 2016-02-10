/*
Count how many 1 in binary representation of a 32-bit integer.

Example
Given 32, return 1

Given 5, return 2

Given 1023, return 9

Challenge
If the integer is n bits with m 1 bits. Can you do it in O(m) time?

Tags Expand 
Binary Bit Manipulation

Thoughts:
1. break string into char[]
2. convert char[] into integer using Character.getNumericValue()

*/




public class Solution {
    /**
     * @param num: an integer
     * @return: an integer, the number of ones in num
     */
    public int countOnes(int num) {
        if (num < 0) {
            return 0;
        }
        String bits = Integer.toBinaryString(num);
        char[] bitArray = bits.toCharArray();
        int sum = 0;
        for (int i = 0; i < bitArray.length; i++) {
            sum += Character.getNumericValue(bitArray[i]);
        }
        return sum;
    }
};
